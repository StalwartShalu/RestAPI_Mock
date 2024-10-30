package com.restAssured.RestAssuredPractice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BaseTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		System.out.println("*************POST REQUEST*************");
		String responseBody = given().header("Content-Type", "application/json").queryParam("key", "qaclick123")
				.body(com.restAssured.Files.Files.AddPlace()).when().post("/maps/api/place/add/json").then()
				.assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)")
				.extract().asString();

		JsonPath jsp = new JsonPath(responseBody);
		String placeId = jsp.get("place_id");
		System.out.println("Location added, Id is " + placeId);

		System.out.println("*************PUT REQUEST*************");
		String address = "Trichy TN";

		String updatedResponse = given().header("Content-Type", "application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeId+"\",\r\n"
						+ "\"address\":\""+address+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "")
				.when().put("/maps/api/place/update/json").then().assertThat().statusCode(200).extract().asString();
		JsonPath jspUpdate = new JsonPath(updatedResponse);
		String Message = jspUpdate.get("msg");
		System.out.println(Message);

		System.out.println("*************GET REQUEST*************");
		String Response = given().queryParam("place_id", placeId).queryParam("key", "qaclick123").when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath jsp_Resp = new JsonPath(Response);
		String AddedAddress = jsp_Resp.get("address");
		
		Assert.assertEquals(address, AddedAddress);
	}
}
