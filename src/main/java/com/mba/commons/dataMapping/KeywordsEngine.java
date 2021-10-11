package com.mba.commons.dataMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.mba.models.APImapping;
import com.mba.models.JsonUpdateIdentification;
import com.mba.models.ObjectModel;

/**
 * @author Shenilton
 * @version 2.1
 */

public class KeywordsEngine {

	public static List<ObjectModel> returnValue(String input) {
		List<ObjectModel> mapping = new ArrayList<ObjectModel>();
		List<String> actionList = new ArrayList<String>();
		List<String> objectList = new ArrayList<String>();
		for (String outer : splitBy(input, " , ")) {
			List<String> inner = splitBy(outer, "-");
			if (inner.size() == 2) {
				String action = inner.get(0);
				actionList.add(action);
				String elements = inner.get(1);
				objectList.add(elements);
				mapping.add(new ObjectModel(action, elements));
			}
		}
		return mapping;
	}

	public APImapping apiMapping(String input) {
		APImapping mapping = null;
		int count = -1;
		String method;
		String requestLocation = null;
		
		List<JsonUpdateIdentification> dataUpdat = new ArrayList<JsonUpdateIdentification>();
		String resource = null;
		
		List<String> listingValues = splitBy(input, " :: ");
		method = listingValues.get(0);
		requestLocation = listingValues.get(2);
		
		List<String> listingValuesSub = splitBy(listingValues.get(1), " || ");
		if (!listingValuesSub.get(0).contains("NA")) {
			resource = listingValuesSub.get(0);
		}
		List<String> listingValuesSubInner = splitBy(listingValuesSub.get(1), " , ");
		
		for (String splitter : listingValuesSubInner) {
			count++;
			if (listingValuesSubInner.size() - 1 != count) {
				List<String> equal = splitBy(splitter, "=");
				JsonUpdateIdentification innerAddition = new JsonUpdateIdentification();
				innerAddition.setDataType(equal.get(1).split("-")[1]);
				innerAddition.setKey(equal.get(0));
				innerAddition.setValue(equal.get(1).split("-")[0]);
				dataUpdat.add(innerAddition);
			}
			mapping = new APImapping(method, requestLocation, dataUpdat, resource);
		}
		return mapping;

	}

	private static List<String> splitBy(String toSplit, String delimiter) {
		List<String> tokens = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(toSplit, delimiter);
		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

	public static ArrayList<String> corDinateSplitter(String input) {
		System.out.println(input);
		ArrayList<String> list = new ArrayList<String>();
		String[] parts = input.split(",");
		String part1 = parts[0].trim();
		String part2 = parts[1].trim();
		list = new ArrayList<String>();
		list.add(part1);
		list.add(part2);
		return list;
	}

	public static void main(String[] args) {
	}
}