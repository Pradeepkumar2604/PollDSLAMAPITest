package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_CustomerOrderInfo {
	@Test(dependsOnGroups="Homepage")
	public void verifyFrontPage()
	{
		String apiURL= Constants.TEST_ENV+"/cgi-bin/POLLDSLAM/dslam6100Int.pl?telephoneNum="
					+Constants.TeleNum+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		//Ordered speed not available
		
				assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.CUSTOMER_INFORMATION.ORDER_NO").isEmpty());
			//	assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.SUPP_LEVEL").isEmpty());
				assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.ISSUED_DATE").isEmpty());
				assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.DUE_DATE").isEmpty());
				assertTrue(!CommonMethods.fetchxmlStringData(responseData, "POLL_DSLAM_ISY.COMMON_DATA_FEED_INVENTORY.CIRCUIT_ID").isEmpty());
	}		

}
