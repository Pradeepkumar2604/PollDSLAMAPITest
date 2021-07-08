package restAssuredTests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_TNCompositionList {

	@Test(dependsOnGroups = "Homepage")
	public void verifyTNCompositionList()
	{		
		String apiURL = Constants.TEST_ENV+"/cgi-bin/QC/DSL/dslamTNsAuth.pl?NEIp="+Constants.IPAddress+"&XML=1&ADDLXML=1";
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		int totalTNsCount = CommonMethods.fetchxmlIntData(responseData, "DSLAM_TN_REPORT.TOTAL_TNS");
		
		int nodeChildrenCount = responseData.then().extract().xmlPath().getNodeChildren("DSLAM_TN_REPORT.TN_LIST.TN").size();
	
		if(totalTNsCount==nodeChildrenCount)
		{
			for(int i=0;i<totalTNsCount;i++)
			{
				
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@number").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@nodename").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@rack").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@dslamSSP").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@potsSSP").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@visp").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@speed").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@fid").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@transport").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@authStatus").toString().isEmpty());
			assertTrue(!responseData.xmlPath().get("DSLAM_TN_REPORT.TN_LIST.TN["+i+"].@authTimeSecs").toString().isEmpty());
			}
		}

	}
}
	

