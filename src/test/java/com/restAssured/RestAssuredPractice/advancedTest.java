package com.restAssured.RestAssuredPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import com.restAssuredHelper.addPlaceAPI;
import com.restAssuredHelper.location;
import static org.hamcrest.Matchers.*;

public class advancedTest {

	// Add API
	@Test
	public void addMapsAPI() {

		// Serialization
		addPlaceAPI api = new addPlaceAPI();
		api.setAccuracy(50);
		api.setAddress("Ariyamangalam");
		api.setLanguage("Tamil");
		api.setName("Shalini Home");
		api.setPhone_number("9597976646");
		api.setWebsite("www.stalwartshalu.com");
		List<String> types = new ArrayList();
		types.add("Soap");
		types.add("dress");
		api.setTypes(types);
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		api.setLocation(loc);

		// Request Spec Builders
		RequestSpecification Req = (RequestSpecification) new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setAccept(ContentType.JSON).build();

		ResponseSpecification Res = (ResponseSpecification) new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).expectBody("status", equalTo("OK")).build();

		given().spec(Req).body(api).log().body().when().post("/maps/api/place/add/json").then().assertThat().log().body().spec(Res)
				.extract();

	}

}
