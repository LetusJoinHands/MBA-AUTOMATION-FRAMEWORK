package com.mba.apiAutomation.webServiceMethods;

import static io.restassured.RestAssured.given;

import com.mba.commons.dataMapping.DataImp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Shenilton
 * @version 19-07-2020
 */

public class ReusableFunctionAPI {

	public String baseURI = DataImp.getInstance().preReqsite("URI");
	
	public String getURI(String uri) {
		return this.baseURI = uri;	
	}

	public Response methodConsolidation(String method, String parameter, String querry) {
		RestAssured.baseURI = baseURI;
		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		Response response = null;
		switch (method) {
		case "POST-STATUS":
			request.body(parameter);
			response = request.post(querry);
			break;
		case "GET-STATUS":
			request.pathParam("id", querry.split("/")[2]);
			response = request.get("/"+querry.split("/")[1]+"/{id}");
			break;
		case "DELETE-STATUS":
			response = request.delete(querry);
			break;
		case "PUT-STATUS":
			request.body(parameter);
			request.pathParam("id", querry.split("/")[2]);
			response = request.put("/"+querry.split("/")[1]+"/{id}");
			break;
		default:
		}
		return response;
	}

	public Response getRequest(String endpoint, String pathParam) {
		RestAssured.baseURI = baseURI;
		Response response = given().when().pathParam(pathParam.split(",")[0], pathParam.split(",")[1]).get(endpoint);
		return response;
	}

	public Response getRequestWIthOutParm(String endpoint, String pathParam) {
		RestAssured.baseURI = baseURI;
		Response response = given().when().get(endpoint);
		return response;
	}

	public Response postRequest(String endpoint, String payload) {
		{
			RestAssured.baseURI = baseURI;
			Response response = given().queryParam("key", "value").body(payload).when().post(endpoint);
			return response;
		}
	}

	public Response putRequest(String endpoint, String payload) {
		RestAssured.baseURI = baseURI;
		Response response = given().queryParam("key", "value").body(payload).when().put(endpoint);
		return response;

	}

	public Response putRequest(String endpoint, String payload, String pathParam) {
		RestAssured.baseURI = baseURI;
		Response response = given().pathParam(pathParam.split(",")[0], pathParam.split(",")[1]).body(payload).when()
				.put(endpoint);
		return response;

	}

	public Response deleteRequest(String endpoint, String payload, String pathParam) {
		RestAssured.baseURI = baseURI;
		Response response = given().pathParam(pathParam.split(",")[0], pathParam.split(",")[1]).body(payload).when()
				.delete(endpoint);
		return response;
	}
}
