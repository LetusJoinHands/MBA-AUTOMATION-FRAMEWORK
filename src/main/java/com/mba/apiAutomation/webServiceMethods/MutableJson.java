package com.mba.apiAutomation.webServiceMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.mba.commons.reusableFunction.FileHandling;

public class MutableJson {

	public static void main(String[] args) {
		MutableJson json = new MutableJson(new HashMap<String, Object>() {
			{
				put("store", new HashMap<String, Object>() {
					{
						put("name", "Some Store");
						put("books", Arrays.asList(new HashMap<String, Object>() {
							{
								put("isbn", "111");
							}
						}, new HashMap<String, Object>() {
							{
								put("isbn", "222");
							}
						}));
					}
				});
			}
		});

		System.out.println("Before:\t" + json.map());

		json.update("$.store.name", "Book Store");
		json.update("$.store.books[0].isbn", "444");
		json.update("$.store.books[1].isbn", "555");

		System.out.println("After:\t" + json.map());
	}

	private final Map<String, Object> json;

	public MutableJson(Map<String, Object> json) {
		this.json = json;
	}

	public Map<String, Object> map() {
		return json;
	}

	public void update(String path, Object newValue) {
		updateJson(this.json, Path.parse(path), newValue);
	}

	public void jsonUpdate(FileHandling file, String path, String jpath, Object updateValue) throws Exception {
		String returnFile = file.readingFile(path);
		@SuppressWarnings("unchecked")
		Map<String, Object> obj = new ObjectMapper().readValue(returnFile, Map.class);
		MutableJson jsonMapper = new MutableJson(obj);
		jsonMapper.update(jpath, updateValue);
		GsonBuilder builder = new GsonBuilder();
		Gson gsonObject = builder.create();
		String jsonObject = gsonObject.toJson(gsonObject);
		file.writtingToFile(path, jsonObject);
	}

	public String jsonGet(FileHandling file, String path, String jpath) throws Exception {
		DocumentContext contex = JsonPath.parse(file.readingFile(path));
		JsonPath pathing = JsonPath.compile(jpath);
		String value = "";
		try {
			value = contex.read(pathing);
		} catch (Exception e) {
		}
		return value;

	}

	private void updateJson(Map<String, Object> data, Iterator<Token> path, Object newValue) {
		Token token = path.next();
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			if (!token.accept(entry.getKey(), entry.getValue())) {
				continue;
			}

			if (path.hasNext()) {
				Object value = token.value(entry.getValue());
				if (value instanceof Map) {
					updateJson((Map<String, Object>) value, path, newValue);
				}
			} else {
				token.update(entry, newValue);
			}
		}
	}
}

class Path {
	public static Iterator<Token> parse(String path) {
		if (path.isEmpty()) {
			return Collections.<Token>emptyList().iterator();
		}
		if (path.startsWith("$.")) {
			path = path.substring(2);
		}

		List<Token> tokens = new ArrayList<>();
		for (String part : path.split("\\.")) {
			if (part.matches("\\w+\\[\\d+\\]")) {
				String fieldName = part.substring(0, part.indexOf('['));
				int index = Integer.parseInt(part.substring(part.indexOf('[') + 1, part.indexOf(']')));
				tokens.add(new ArrayToken(fieldName, index));
			} else {
				tokens.add(new FieldToken(part));
			}
		}
		;

		return tokens.iterator();
	}
}

abstract class Token {

	protected final String fieldName;

	Token(String fieldName) {
		this.fieldName = fieldName;
	}

	public abstract Object value(Object value);

	public abstract boolean accept(String key, Object value);

	public abstract void update(Map.Entry<String, Object> entry, Object newValue);
}

class FieldToken extends Token {

	FieldToken(String fieldName) {
		super(fieldName);
	}

	@Override
	public Object value(Object value) {
		return value;
	}

	@Override
	public boolean accept(String key, Object value) {
		return fieldName.equals(key);
	}

	@Override
	public void update(Map.Entry<String, Object> entry, Object newValue) {
		entry.setValue(newValue);
	}
}

class ArrayToken extends Token {

	private final int index;

	ArrayToken(String fieldName, int index) {
		super(fieldName);
		this.index = index;
	}

	@Override
	public Object value(Object value) {
		return ((List) value).get(index);
	}

	@Override
	public boolean accept(String key, Object value) {
		return fieldName.equals(key) && value instanceof List && ((List) value).size() > index;
	}

	@Override
	public void update(Map.Entry<String, Object> entry, Object newValue) {
		List list = (List) entry.getValue();
		list.set(index, newValue);
	}
}