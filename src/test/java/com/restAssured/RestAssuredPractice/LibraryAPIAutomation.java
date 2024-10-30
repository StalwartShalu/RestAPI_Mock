package com.restAssured.RestAssuredPractice;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restAssured.Files.Files;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import io.restassured.path.json.JsonPath;

import org.apache.commons.io.FileUtils;

public class LibraryAPIAutomation {

	@Test(dataProvider = "getData")
	public void addBook(HashMap<String, String> input) {
		RestAssured.baseURI = "http://216.10.245.166";
		String Response = given().header("Content-Type", "application/json")
				.body(Files.addBook(input.get("isbn"), input.get("aisle"))).when().post("Library/Addbook.php").then()
				.assertThat().statusCode(200).extract().asString();

		JsonPath jsp = new JsonPath(Response);
		String ID = jsp.get("ID");

		System.out.println("**************Book Added with id " + ID + " Deletion in progress**************");
		given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"ID\": \"" + ID + "\"\r\n" + "}").when().delete("/Library/DeleteBook.php").then()
				.assertThat().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = dataprovider(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\restAssured\\Files\\inputJson.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

	public List<HashMap<String, String>> dataprovider(String path) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

	}
}
