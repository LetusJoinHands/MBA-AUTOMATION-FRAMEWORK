package com.mba.models;

import java.util.List;

public class APImapping {
	
	private String method;
	private String requestLocation;
	private List<JsonUpdateIdentification> dataUpdat;
	private String resource;
	public APImapping() {
		
	}
	
	public APImapping(String method, String requestLocation, List<JsonUpdateIdentification> dataUpdat,
			String resource) {
		super();
		this.method = method;
		this.requestLocation = requestLocation;
		this.dataUpdat = dataUpdat;
		this.resource = resource;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRequestLocation() {
		return requestLocation;
	}
	public void setRequestLocation(String requestLocation) {
		this.requestLocation = requestLocation;
	}
	public List<JsonUpdateIdentification> getDataUpdat() {
		return dataUpdat;
	}
	public void setDataUpdat(List<JsonUpdateIdentification> dataUpdat) {
		this.dataUpdat = dataUpdat;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
}
