package com.mba.models;

public class JsonUpdateIdentification {

	private String key;
	public String value;
	public String dataType;

	public JsonUpdateIdentification(){
		
	}
	public JsonUpdateIdentification(String key, String value, String dataType) {
		super();
		this.key = key;
		this.value = value;
		this.dataType = dataType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}


	
}
