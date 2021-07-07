package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_PingDSlam {

	@Test(dependsOnGroups = "Homepage")
	public void verifyPingDSLAM()
	{
		String apiURL="https://napperlc3.corp.intranet/cgi-bin/POLLDSLAM/pingDevice.pl?Ip="+Constants.IPAddress+"&abbrev=1&XML=1&ADDLXML=1";
		Response responseData = CommonMethods.responseCapture(apiURL);
		if(CommonMethods.fetchxmlStringData(responseData, "PING_DEVICE.RESULT").equals("SUCCESS"))
		{
			assertTrue(CommonMethods.fetchxmlStringData(responseData, "PING_DEVICE.PING_SUCCESS_RATE").equals("100 %"));
		}
	}
}
