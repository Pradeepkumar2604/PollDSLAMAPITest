package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_InitialPageOfPollDslam {

	@Test(dependsOnGroups="Homepage")
	public void verifyFrontPage()
	{
		String apiURL= "https://napperlc3.corp.intranet/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
	//	CommonMethods.verifyStatusCode(responseData, 200);  // Status code validation
		
	//	CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TN", Constants.TeleNum);    //TN Validation
		
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
		
		Constants.IPAddress = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.DSLAM_IP_ADDRESS");
		//System.out.println(Constants.IPAddress);
				
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DSLAM_CHASSIS_TYPE").isEmpty(),"DSLAM_CHASSIS_TYPE not exist");
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DSLAM_IP").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DEVICE_TID").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.CLLI_NODE").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.NI_RACK_ID").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.UNIT").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.PING_DSLAM_LINK").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.TN_LIST_LINK").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.CDBI_REPORTING_LINK").isEmpty());
		
		//DSLAM PORT Information and Utilities Validation		
				
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.POTS_SHELF").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.LINE_CODE").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.ACTUALS_LINK").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.AUTHENTICATION_CHECK_LINK").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.DSL_EXPRESSE_LINK").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.LOOP_QUAL_LINK").isEmpty());
//		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.BOUNCE_PORT_A_LINK").isEmpty());
//		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.BOUNCE_PORT_B_LINK").isEmpty());
//		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.BOUNCE_PORT_AB_LINK").isEmpty());
		
		//CUSTOMER ORDER INFORMATION validation
		//Ordered speed not avilable
		
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.CUSTOMER_INFORMATION.ORDER_NO").isEmpty());
	//	assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUPP_LEVEL").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.ISSUED_DATE").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.DUE_DATE").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.CIRCUIT_ID").isEmpty());
		
		//ORDER AND ETHERNET PATH STATUS validation
		
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SERVICE_ID").isEmpty());
		assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUBSCRIBER_STATUS").isEmpty());
			
		
		 
	}
}
