package com.mba.apiAutomation.webServiceMethods;

import org.testng.annotations.Test;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.filter.ValueNode.JsonNode;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

public class JsonUpdate {
	
	/*
	*/
	private static final Configuration configuration = Configuration.builder()
			.jsonProvider(new JacksonJsonNodeJsonProvider()).mappingProvider(new JacksonMappingProvider()).build();

	@Test
	public void a_value_can_be_updated() {

		String originalJson = "{\n" + "\"session\":\n" + "    {\n" + "        \"name\":\"JSESSIONID\",\n"
				+ "        \"value\":\"5864FD56A1F84D5B0233E641B5D63B52\"\n" + "    },\n" + "\"loginInfo\":\n"
				+ "    {\n" + "        \"loginCount\":77,\n"
				+ "        \"previousLoginTime\":\"2014-12-02T11:11:58.561+0530\"\n" + "    }\n" + "}";

		DocumentContext json = JsonPath.using(configuration).parse(originalJson);
		DocumentContext result = json.map("$..name", (currentValue, configuration) -> {
			return currentValue.toString().toUpperCase();
		});
		System.out.println(result.jsonString());
	}
}
