package utils;

import static io.restassured.RestAssured.given;


import TestData.Constants;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class CommonMethods {
	
	//Fecthing response method
	public static Response responseCapture(String apiURL)
	{	
	Response response = given()
	.auth()
	.basic(Constants.UserName, PwdEncryption.Decodepwd(Constants.Password))
	.when()
	.get(apiURL);
	return response;
	}
	
	//Verifying Status Code method
	public static void verifyStatusCode(Response response,int expectedStatusCode)
	{
		response.then().statusCode(expectedStatusCode);
	}
	
	//Verifying TN method
	public static void verifyResponseBodyData(Response response, String xpath,String expected)
	{
		response.then().body(hasXPath(xpath, containsString(expected)));		
	}

	//Fetching response text from the response
	public static String fetchxmlStringData(Response response,String nodePath)
	{
		return (response.xmlPath().getString(nodePath));
		
	}
	
	
	
	
	
	

}
