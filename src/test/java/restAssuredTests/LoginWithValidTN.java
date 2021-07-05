package restAssuredTests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.CommonMethods;


public class LoginWithValidTN{

	@BeforeClass
	public void setup()
	{
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	@Test
	public void verifyLoginWithTN()
	{
		String apiURL= "https://napperlc3.corp.intranet/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		CommonMethods.verifyStatusCode(responseData, 200);
		
		CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TN", Constants.TeleNum);
					
	}
}
