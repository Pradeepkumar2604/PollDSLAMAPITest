package utils;

import static io.restassured.RestAssured.given;


import TestData.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import com.sun.istack.logging.Logger;

public class CommonMethods {
	
	//Fecthing response method
	public static Response responseCapture(String apiURL)
	{	
		RestAssured.useRelaxedHTTPSValidation();
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

	//Fetching String response text from the response
	public static String fetchxmlStringData(Response response,String nodePath)
	{
		return (response.xmlPath().getString(nodePath));
		
	}
	
	//Fetching int response text from response
	public static int fetchxmlIntData(Response response,String nodePath)
	{
		return (response.xmlPath().getInt(nodePath));
		
	}
	
	public static boolean stringContentValidation(Response response,String nodePath) {
		
		return (response.xmlPath().get(nodePath).toString().isEmpty());
	}
	
	public Logger getLogger() {
		 Constants.log = Logger.getLogger(this.getClass());		
		 return Constants.log;
	}

}
