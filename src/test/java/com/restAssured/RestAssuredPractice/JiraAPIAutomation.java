package com.restAssured.RestAssuredPractice;

import org.testng.annotations.Test;

import com.restAssured.Files.Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraAPIAutomation {
	String Auth = "Basic aXlhbHJhaTAxQGdtYWlsLmNvbTpBVEFUVDN4RmZHRjA5MmhvV3M3SFVsZXljVzhya0U0Q0dPQWtOek5"
			+ "Xc3I2cjJHWkc1Vi1OdUhjeHN6THJNMzVpOHNlYXFCWUdVWTdrS1lhdWxBWXNidGNPZWhXUG05VEdYY1BZWDctc0pDQmRx"
			+ "RENXdGNKRTU5WlZRYmlWXzNHWGdxS0FXc0tjSFBTbFRtTXRuanotQnBpSU1ONGVQS0d6cFhTRTdHZzFhN0NEMmVFRmVfME44N2c9REY0MEU0RUY=";

	
	@Test
	public void createBug() {
		RestAssured.baseURI = "https://iyalrai01.atlassian.net";
		String response = given().header("Content-Type", "application/json").header("Authorization", Auth)
				.body(Files.createBug()).when().post("rest/api/3/issue").then().assertThat().log().all().extract()
				.asString();

		JsonPath jsp = new JsonPath(response);
		String id = jsp.get("id");
		String key = jsp.get("key");
		System.out.println("Bug " + key + "Created and its Id is " + id);

		// Add Attachment

		given().header("X-Atlassian-Token", "no-check").header("Authorization", Auth).pathParam("id", id)
				.multiPart("file", new File("C:\\Users\\Shali\\Downloads\\Screenshot.png")).when()
				.post("rest/api/3/issue/{id}/attachments").then().assertThat().log().body().statusCode(200);
	}

	@Test
	public void getStatus() {
		RestAssured.baseURI = "https://iyalrai01.atlassian.net";
		given().header("Authorization", Auth).when().get("rest/api/3/status").then().assertThat().log().body()
				.statusCode(200);
	}
}
