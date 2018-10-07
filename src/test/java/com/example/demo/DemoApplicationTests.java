package com.example.demo;

import com.example.demo.entity.TaskExecutionRequest;
import com.example.demo.entity.TaskExecutionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	TaskReceiver taskReceiver;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void test() throws Exception {
		String string = getString();

		MvcResult mvcResult = mockMvc.perform(post("/execute").content(string).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(200))
				.andReturn();
		TaskExecutionResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TaskExecutionResponse.class);
		Assert.assertEquals(2, response.getOutput().size());
		System.out.println();

	}

	private String getString() throws IOException {
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

		TaskExecutionRequest request = new TaskExecutionRequest();
		request.setTaskSource(s);
		request.setRqUid("123");
		List<List<String>> input = new ArrayList<>();
		input.add(Lists.newArrayList("Yes", "No"));
		input.add(Lists.newArrayList("Yes", "Yes"));
		request.setInput(input);
		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, request);
		return stringWriter.toString();
	}


}
