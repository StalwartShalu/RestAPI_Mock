package com.restAssured.RestAssuredPractice;

import com.restAssured.Files.Files;

import io.restassured.path.json.JsonPath;

public class mockResponsePractice {

	public static void main(String[] args) {
		
		JsonPath jsp = new JsonPath(Files.mockResponse());
		int size = jsp.get("courses.size()");
		System.out.println("Size of Course " + size);
		int purchaseAmt = jsp.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is " + purchaseAmt);
		int sum =0;
		System.out.println("List of courses and its price details");
		for(int i=0; i<size; i++) {
			String title = jsp.get("courses["+i+"].title");
			int price = jsp.getInt("courses["+i+"].price");
			int copies1 = jsp.getInt("courses["+i+"].copies");
			System.out.println(title + " : " + price );
			sum = sum + (copies1*price);
		}
		
		int copies = jsp.getInt("courses[2].copies");
		System.out.println("Number of RPA copies " +copies);
		if(sum<=purchaseAmt) {
				System.out.println("Purchase amount is " + sum + " --> Within the limit");
			}
			else {
				System.out.println("Purchase amount is " + sum + "-->Exceeded");
			}
		}

}
