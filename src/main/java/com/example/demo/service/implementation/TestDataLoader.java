package com.example.demo.service.implementation;

import com.example.demo.entity.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TestDataLoader {

  private ObjectMapper objectMapper;
  private ResourceLoader resourceLoader;

  public TestDataLoader(ObjectMapper objectMapper, ResourceLoader resourceLoader) {
    this.objectMapper = objectMapper;
    this.resourceLoader = resourceLoader;
  }

  public Map<List<String>, List<String>> loadTestData(Long taskId) throws IOException {

    Resource resource = resourceLoader.getResource("classpath:data/task" + taskId + ".json");
    String s = FileUtils.readFileToString(resource.getFile());

    TestData testData = objectMapper.readValue(s, TestData.class);
    return testData.toInputOutput();
  }

  public Map<String, String> flattenData(Map<List<String>, List<String>> testData){
    Map<String, String> result = new HashMap<>();
    for (Map.Entry<List<String>, List<String>> e : testData.entrySet()) {
      result.put(flatten(e.getKey()), flatten(e.getValue()));
    }
    return result;
  }

  private String flatten(List<String> data)
  {
    return Joiner.on(",").join(data.stream().sequential().map((Function<String, String>) s -> '"' + s + '"').collect(Collectors.toList()));
  }
}
