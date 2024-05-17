package com.base;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	
	RequestSpecification reqSpec;
	public Response response;
	
	public void addHeader(String key, String value) {
		reqSpec= RestAssured.given().header(key, value);
		
	}
	
	public void addHeaders(Headers headers) {
		reqSpec= RestAssured.given().headers(headers);
		
	}
	
	public void addFormData(String key, File file) {
		reqSpec= reqSpec.multiPart(key, file);
		
	}
	
	public void addBody(String body) {
		reqSpec= reqSpec.body(body);
		
	}
	
	public void addBody(Object body) {
		reqSpec=reqSpec.body(body);
	}
	
	public void addBasicAuth(String userName, String password) {
		reqSpec= reqSpec.auth().preemptive().basic(userName, password);
		
	}
	public Response addReqType(String type, String endPoint) {
		switch(type) {
		case "GET":
			response = reqSpec.get(endPoint);
		break;
		case "POST":
			response = reqSpec.post(endPoint);
		break;	
		case "PUT":
			response = reqSpec.put(endPoint);
		break;
		case "PATCH":
			response = reqSpec.patch(endPoint);
		break;
		case "DELETE":
			response = reqSpec.delete(endPoint);
		break;
		
		default:
		break;	
	}
		return response;
		
}
	public int getResStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}
	public String getResBodyAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}

}
