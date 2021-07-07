package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_LoginHomePage {
	
	@Test(groups="Homepage")
	public static void verifyLoginWithTN()
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
		
		Constants.IPAddress=CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.DSLAM_IP_ADDRESS");
		Constants.ActualsURL = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.NI1LIM.DSLAM_ACTUALS_URI");
		Constants.TransportType = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.TRANSPORT_TYPE");
		System.out.println("Homepage "+Constants.TransportType);
		
	}
	

}
