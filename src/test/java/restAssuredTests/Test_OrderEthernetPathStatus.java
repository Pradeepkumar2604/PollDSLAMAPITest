package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_OrderEthernetPathStatus {
	
	@Test(dependsOnGroups="Homepage")
	public void verifyFrontPage()
	{
		String apiURL= Constants.TEST_ENV+"/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SERVICE_ID"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUBSCRIBER_STATUS"));
		
		int ethernetTableSize = responseData.xmlPath().getList("POLL_DSLAM_ISY.BOARD_LIST.BOARD").size();
		for(int i=0;i<ethernetTableSize;i++)
		{
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@BOARD_NAME"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@CONNECTION"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@NODENAME"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@NODEIP"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@SHELF"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@SLOT"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@PORT"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].@VLAN"));
			CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.BOARD_LIST.BOARD["+i+"].CONNECTION_CIRCUIT_ID");
			
		}
	}

}
