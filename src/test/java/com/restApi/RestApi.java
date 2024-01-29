package com.restApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestApi {

	String jSessionId;

	public Response testLogin() {
		// Set the base URI for your application
		RestAssured.baseURI = "http://localhost:8080/FlyAway";

		// Define the parameters for the login request
		String email = "qwer@1234";
		String password = "12345";

		// Perform the login request
		Response response = RestAssured.given().formParam("email_id", email).formParam("pwd", password).when()
				.post("/loginaction").then().statusCode(302).extract().response();
		String sessionCookie = response.getCookie("JSESSIONID");
		this.jSessionId = sessionCookie;

		response = RestAssured.given().cookie("JSESSIONID", sessionCookie).when().get("/login");
//		System.out.println(response.getStatusCode());
//		System.out.println(response.getBody().asString());
		return response;

//		RestAssured.given(). cookie("JSESSIONID",sessionCookie).when().get("/memberbookings") .then()
//        .statusCode(200).log().all().extract().response();
	}

	public Response getFlightsOnGivenDate() {
		RestAssured.baseURI = "http://localhost:8080/FlyAway";
		Response response = RestAssured.given().cookie("JSESSIONID", jSessionId).when().get("/home");
		//System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		return response;
	}

	public Response  getBookedFlights() {
		RestAssured.baseURI = "http://localhost:8080/FlyAway";
		Response response = RestAssured.given().cookie("JSESSIONID", jSessionId).when().get("/memberbookings");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		return response;
	}

	

}
