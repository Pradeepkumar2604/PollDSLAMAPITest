package restAssuredTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasXPath;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.CommonMethods;

public class InitialPageOfPollDslam {

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
		
		CommonMethods.verifyStatusCode(responseData, 200);  // Status code validation
		
		CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TN", Constants.TeleNum);    //TN Validation
		
		//Transport type validation
		String responseTransportTypeText = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.TRANSPORT_TYPE");
		
		switch(responseTransportTypeText){
		case "IPDATA VDSL2 Pair Bonded":
			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "IPDATA VDSL2 Pair Bonded");
			break;
		case "ATM":
			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "ATM");
			break;
		case "IPDAT AVDSL2 Pair Bonded":
			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "IPDATA ADSL2 Pair Bonded");
			break;
		}
		
		//DSLAM DEVICE Information, Inventory, and Status Checks Validation
		//Train rate , end to end and recent calls detais are not there in xml
		
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DSLAM_CHASSIS_TYPE")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DSLAM_IP")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DEVICE_TID")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.CLLI_NODE")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.NI_RACK_ID")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.UNIT")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.PING_DSLAM_LINK")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.TN_LIST_LINK")!=null);
		assertTrue(CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.CDBI_REPORTING_LINK")!=null);
		
		
		
	
		
			
		
		
		 
	}
}
