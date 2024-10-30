package com.restAssured.RestAssuredPractice;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import com.restAssuredHelper.Api;
import com.restAssuredHelper.WebAutomation;
import com.restAssuredHelper.getCourses;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Authorization {

	public String getAccessToken() {

		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
		String Response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").when().post().then().assertThat().statusCode(200).extract()
				.asString();

		JsonPath jsp = new JsonPath(Response);
		String accessToken = jsp.get("access_token");
		return accessToken;

	}

	@Test
	public void GetCourseDetails() {
		String AccessToken = getAccessToken();
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/getCourseDetails";
		// given().queryParam("access_token",
		// AccessToken).when().get().then().assertThat().log().body().statusCode(401);

		getCourses gc = given().queryParams("access_token", AccessToken).when()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getCourses.class);

		System.out.println(gc.getInstructor());

		// Get price of API course
		List<Api> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
			}
		}

		// Get price of Web Course
		List<WebAutomation> web = gc.getCourses().getWebAutomation();
		for (int i = 0; i < web.size(); i++) {
			if (web.get(i).getCourseTitle().equalsIgnoreCase("Protractor")) {
				System.out.println(web.get(i).getPrice());
			}

		}
		
		//Print all Course title
		for(int i = 0; i<web.size(); i++) {
			System.out.println(web.get(i).getCourseTitle());
		}

	}

}
