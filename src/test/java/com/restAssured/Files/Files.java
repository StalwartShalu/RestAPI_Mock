package com.restAssured.Files;

public class Files {

	public static String AddPlace() {
		String JsonBody = "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n" + "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}\r\n" + "";
		return JsonBody;

	}

	public static String mockResponse() {

		String mockResp = "{\r\n" + "	\"dashboard\": {\r\n" + "		\"purchaseAmount\": 910,\r\n"
				+ "		\"website\": \"rahulshettyacademy.com\"\r\n" + "	},\r\n" + "	\"courses\": [\r\n"
				+ "		{\r\n" + "			\"title\": \"Selenium Python\",\r\n" + "			\"price\": 50,\r\n"
				+ "			\"copies\": 6\r\n" + "		},\r\n" + "		{\r\n" + "			\"title\": \"Cypress\",\r\n"
				+ "			\"price\": 40,\r\n" + "			\"copies\": 4\r\n" + "		},\r\n" + "		{\r\n"
				+ "			\"title\": \"RPA\",\r\n" + "			\"price\": 45,\r\n" + "			\"copies\": 10\r\n"
				+ "		},\r\n" + "		{\r\n" + "			\"title\": \"Appium\",\r\n" + "			\"price\": 4,\r\n"
				+ "			\"copies\": 20\r\n" + "		}\r\n" + "	]\r\n" + "}";
		return mockResp;
	}

	public static String addBook(String isbn, String aisle) {

		String JsonPayload = "{\r\n" + "\"name\":\"Java\",\r\n" + "\"isbn\":\"" + isbn + "\",\r\n" + "\"aisle\":\""
				+ aisle + "\",\r\n" + "\"author\":\"Shalini\"\r\n" + "}\r\n" + " \r\n" + "";

		return JsonPayload;
	}

	public static String createBug() {
		String BugPayload = "{\r\n" + "    \"fields\": {\r\n" + "        \"project\": {\r\n"
				+ "            \"key\": \"SCRUM\"\r\n" + "        },\r\n"
				+ "        \"summary\": \"[REST] Error in Json Paylod \",\r\n" + "        \"description\": {\r\n"
				+ "            \"type\": \"doc\",\r\n" + "            \"version\": 1,\r\n"
				+ "            \"content\": [\r\n" + "                {\r\n"
				+ "                    \"type\": \"paragraph\",\r\n" + "                    \"content\": [\r\n"
				+ "                        {\r\n" + "                            \"type\": \"text\",\r\n"
				+ "                            \"text\": \"Attachments to bug using Automation\"\r\n"
				+ "                        }\r\n" + "                    ]\r\n" + "                }\r\n"
				+ "            ]\r\n" + "        },\r\n" + "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n" + "        }\r\n" + "    }\r\n" + "}";
		return BugPayload;
	}

}
