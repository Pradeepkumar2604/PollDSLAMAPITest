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
		String apiURL= Constants.TEST_ENV+"/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		//Transport type validation
		String responseTransportTypeText = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.TRANSPORT_TYPE");
		
//		switch(responseTransportTypeText){
//		case "IPDATA VDSL2 Pair Bonded":
//			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "IPDATA VDSL2 Pair Bonded");
//			break;
//		case "ATM":
//			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "ATM");
//			break;
//		case "IPDAT AVDSL2 Pair Bonded":
//			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "IPDATA ADSL2 Pair Bonded");
//			break;
//		}
		if(responseTransportTypeText.equals("IPDATA VDSL2 Pair Bonded"))
		{
			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "IPDATA VDSL2 Pair Bonded");
		}
		else if(responseTransportTypeText.equals("ATM"))
		{
			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "ATM");
		}
		else
		{
			CommonMethods.verifyResponseBodyData(responseData, "/POLL_DSLAM_ISYS/TRANSPORT_TYPE", "IPDATA VDSL2");
		}
		new CommonMethods().getLogger().info("Transport type of customer is "+responseTransportTypeText);
		//DSLAM DEVICE Information, Inventory, and Status Checks Validation
		//Train rate , end to end and recent calls detais are not there in xml
		
		//Constants.IPAddress = CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.DSLAM_IP_ADDRESS");
		
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DSLAM_CHASSIS_TYPE"),"DSLAM_CHASSIS_TYPE not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DSLAM_IP"),"DSLAM_IP not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.DEVICE_TID"),"DEVICE_TID not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.CLLI_NODE"),"CLLI_NODE not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.NI_RACK_ID"),"NI_RACK_ID not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_DEVICE_INVENTORY.UNIT"),"UNIT not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.PING_DSLAM_LINK"),"PING_DSLAM_LINK not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.TN_LIST_LINK"),"TN_LIST_LINK not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.CDBI_REPORTING_LINK"),"CDBI_REPORTING_LINK not exist");
		
		new CommonMethods().getLogger().info("DSLAM DEVICE Information, Inventory, and Status Checks is displayed");
		
		//DSLAM PORT Information and Utilities Validation
		
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.POTS_SHELF"),"POTS_SHELF not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.DSLAM_CONFIG.LINE_CODE"),"LINE_CODE not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.ACTUALS_LINK"),"ACTUALS_LINK not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.AUTHENTICATION_CHECK_LINK"),"AUTHENTICATION_CHECK_LINK not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.DSL_EXPRESSE_LINK"),"DSL_EXPRESSE_LINK not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.LOOP_QUAL_LINK"),"LOOP_QUAL_LINK not exist");
//		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.BOUNCE_PORT_A_LINK"),"BOUNCE_PORT_A_LINK not exist");
//		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.BOUNCE_PORT_B_LINK"),"BOUNCE_PORT_B_LINK not exist");
//		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.POLL_DSLAM_FRONT_PAGE_LINKS.BOUNCE_PORT_AB_LINK"),"BOUNCE_PORT_AB_LINK not exist");
		
		new CommonMethods().getLogger().info("DSLAM PORT Information and Utilities is displayed");
		
		//CUSTOMER ORDER INFORMATION validation
		//Ordered speed not available
		
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.CUSTOMER_INFORMATION.ORDER_NO"),"ORDER_NO not exist");
	    //assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUPP_LEVEL"),"SUPP_LEVEL not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.ISSUED_DATE"),"ISSUED_DATE not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.DUE_DATE"),"DUE_DATE not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.CIRCUIT_ID"),"CIRCUIT_ID not exist");
		
		new CommonMethods().getLogger().info("CUSTOMER ORDER INFORMATION is displayed");
		
		//ORDER AND ETHERNET PATH STATUS validation
	
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SERVICE_ID"),"SERVICE_ID not exist");
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUBSCRIBER_STATUS"),"SUBSCRIBER_STATUS not exist");
		
		new CommonMethods().getLogger().info("ORDER AND ETHERNET PATH STATUS is displayed");
			
		
		 
	}
}
