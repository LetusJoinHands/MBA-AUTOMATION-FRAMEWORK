package com.mba.apiAutomation.webServiceMethods;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebServices {

	
	public void callAPI(String URL, String method, String parameter) {
		 RestAssured.baseURI =URL;
		 RequestSpecification request = RestAssured.given();
		 Response response = null;
		 if(method.equalsIgnoreCase("POST")) {		
			 request.body(parameter);
			 response = request.post();
		 }else if(method.equalsIgnoreCase("GET")){
			  parameter="/"+parameter;
			  response = request.request(Method.GET,parameter);
		 }else if(method.equalsIgnoreCase("DELETE")) {
			 request.body(parameter);
			 response=request.delete();
		 }else if(method.equalsIgnoreCase("PUT")) {
			 request.body(parameter);
			 response=request.put();
		 }
		 if(response!=null) {
			 int statusCode = response.getStatusCode();
			 System.out.println(statusCode);
			 Assert.assertEquals(statusCode, 200);
			
		 }
		
		}
}
