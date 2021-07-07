package restAssuredTests;


import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;


public class Test_LoginWithTN{

//	@BeforeClass
//	public void setup()
//	{
//		RestAssured.useRelaxedHTTPSValidation();
//	}
	
	@Test
	public static String verifyLoginWithTN()
	{
		String apiURL= "https://napperlc3.corp.intranet/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		CommonMethods.verifyStatusCode(responseData, 200);
				
		if(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.TN").isEmpty())
		{
			assertTrue(true);
		}
		else if(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.ERRREC.ERROR_TET").isEmpty())	
		{
			assertTrue(true);
		}
		else
		{
			assertTrue(false);
		}
		
		return (CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.DSLAM_IP_ADDRESS"));
		
	}
	
}
