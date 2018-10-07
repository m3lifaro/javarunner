package com.example.demo.service.implementation;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

@Service
public class TestInstanceProducer {

  public static final String CLASS_NAME_PREFIX = "Task";

  private final FreeMarkerConfig freeMarkerConfig;

  private final TestDataLoader testDataLoader;

    @Autowired
    public TestInstanceProducer(FreeMarkerConfig freeMarkerConfig, TestDataLoader testDataLoader) {
        this.freeMarkerConfig = freeMarkerConfig;
        this.testDataLoader = testDataLoader;
    }

    public Class newTestInstance(long executionId, long taskId, String classString) throws IOException, TemplateException {
    String runnerStringResult = classString.replace(CLASS_NAME_PREFIX, CLASS_NAME_PREFIX + executionId);

    Map<List<String>, List<String>> testData = testDataLoader.loadTestData(executionId);

    Map map = prepareTemplateData(executionId, testDataLoader.flattenData(testData), runnerStringResult);

    StringWriter stringWriter = new StringWriter();
    freeMarkerConfig.getConfiguration().getTemplate("test-template.ftl").process(map, stringWriter);

    return Reflect.compile("com.example.demo.RunnerTest" + executionId, stringWriter.toString()).get();
  }

  private Map prepareTemplateData(long executionId, Map<String, String> testData, String runnerStringResult) {
    Map templateData = new HashMap();
    templateData.put("testData", testData);
    templateData.put("testClassName", "RunnerTest" + executionId);
    templateData.put("classString", StringEscapeUtils.escapeJava(runnerStringResult));
    templateData.put("className", "com.example.demo." + CLASS_NAME_PREFIX + executionId);
    return templateData;
  }
}
