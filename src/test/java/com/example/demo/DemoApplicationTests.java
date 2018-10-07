package com.example.demo;

import com.example.demo.entity.TaskExecutionRequest;
import com.example.demo.entity.TaskExecutionResponse;
import com.example.demo.service.implementation.TestInstanceProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	TestInstanceProducer testInstanceProducer;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	TaskReceiver taskReceiver;

	String s = "package com.example.demo;\n" +
			"\n" +
			"import java.util.ArrayList;\n" +
			"import java.util.List;\n" +
			"\n" +
			"public class Task {\n" +
			"\n" +
			"  public List<String> run(List<String> input){\n" +
			"\n" +
			"    List<String> output = new ArrayList<>();\n" +
			"\n" +
			"    output.add(String.valueOf(input.get(0).equals(input.get(1))));\n" +
			"\n" +
			"\n" +
			"    return output;\n" +
			"  }\n" +
			"}\n";

/*	@Test
	public void runController() throws IOException, TemplateException {

		TaskExecutionRequest request = new TaskExecutionRequest();
		request.setRqUid("12345");
		request.setTask("1");
		request.setTaskSource(s);
		TaskExecutionResponse taskExecutionResponse = taskReceiver.executeRequest(request);
		System.out.println(taskExecutionResponse);

	}*/

	@Test
	public void stringToJson() throws IOException {
		String s = "package com.example.demo;\n" +
				"\n" +
				"import java.util.ArrayList;\n" +
				"import java.util.List;\n" +
				"\n" +
				"public class Task {\n" +
				"\n" +
				"  public List<String> run(List<String> input){\n" +
				"\n" +
				"    List<String> output = new ArrayList<>();\n" +
				"\n" +
				"    output.add(String.valueOf(input.get(0).equals(input.get(1))));\n" +
				"\n" +
				"    return output;\n" +
				"  }\n" +
				"}";

		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, s);
		System.out.println(stringWriter);

	}
	/*@Test
	public void contextLoads() throws IOException, TemplateException {

		TestData testData = new TestData();
		Map<Long, List<String>> testInput = new HashMap<>();
		testInput.put(0L, Lists.newArrayList("Bla, Bla"));
		testInput.put(1L, Lists.newArrayList("Yes", "No"));
		testData.setInput(testInput);

		Map<Long, List<String>> testOutput = new HashMap<>();
		testOutput.put(0L, Lists.newArrayList("Yes"));
		testOutput.put(1L, Lists.newArrayList("No"));
		testData.setOutput(testOutput);

		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, testData);
		System.out.println(stringWriter);
	}*/


}
