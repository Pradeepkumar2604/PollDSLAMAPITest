package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.istack.logging.Logger;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_LoginHomePage {
	
	@Test(groups="Homepage")
	public static void verifyLoginWithTN()
	{
		String apiURL= Constants.TEST_ENV+"/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		CommonMethods.verifyStatusCode(responseData, 200);
				
		if(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.TN").isEmpty())
		{
			assertTrue(true);
			new CommonMethods().getLogger().info("Valid TN member PollDSLAM initial page is displayed");
		}
		else if(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.ERRREC.ERROR_TEXT").isEmpty())	
		{
			new CommonMethods().getLogger().info("TN number is invalid!!!!!");
			new CommonMethods().getLogger().info(responseData.xmlPath().getString(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.ERRREC.ERROR_TEXT")));
			assertTrue(false);
		}
			
		Constants.IPAddress=CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.DSLAM_IP_ADDRESS");
		Constants.ActualsURL = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.NI1LIM.DSLAM_ACTUALS_URI");
		Constants.TransportType = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.TRANSPORT_TYPE");
		
	}
	

}
;