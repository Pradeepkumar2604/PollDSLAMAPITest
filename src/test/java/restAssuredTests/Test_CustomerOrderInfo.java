package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_CustomerOrderInfo {
	@Test(dependsOnGroups="Homepage")
	public void verifyCustomerOrderInfo()
	{
		String apiURL= Constants.TEST_ENV+"/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		//Ordered speed not available		
				assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.CUSTOMER_INFORMATION.ORDER_NO"),"ORDER_NO not exist");
			    //assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUPP_LEVEL"),"SUPP_LEVEL not exist");
				assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.ISSUED_DATE"),"ISSUED_DATE not exist");
				assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.DUE_DATE"),"DUE_DATE not exist");
				assertTrue(!CommonMethods.stringContentValidation(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.CIRCUIT_ID"),"CIRCUIT_ID not exist");
				
				new CommonMethods().getLogger().info("CUSTOMER ORDER INFORMATION is displayed");
	}		

}
