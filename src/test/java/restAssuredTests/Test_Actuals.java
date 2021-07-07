package restAssuredTests;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import TestData.Constants;
import io.restassured.response.Response;
import utils.CommonMethods;

public class Test_Actuals {

	@Test(dependsOnGroups = "Homepage")
	public void verifyActualsDetails()
	{
		String apiURL = "https://napperlc3.corp.intranet/cgi-bin/POLLDSLAM/"+Constants.ActualsURL+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		//Verifying SYSTEM SPECIFIC INFORMATION
		
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_NAME"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_DESC"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_UP_TIME"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_LOCATION"));
		
		//Verifying CARD SPECIFIC INFORMATION
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_SLOT_STATE"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_SLOT_SERVICE_STATE"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_TYPE"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_SN"));
//		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_REV"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_UP_TIME"));
		
		//Verifying PHYSICAL PORT 
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.BONDING_STATUS"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.BONDING_GROUP_ID"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.NUM_PROVISIONED_LINKS"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.NUM_ACTIVE_LINKS"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.NUM_OPERATIONAL_LINKS"));
		
		//Verifying SIGNAL QUALITY STATISTICS
        String[] listPort = {"PORT_STATUS","PORT_STATUS_2"};
        String[] listUpstream = {"ADTRAN_UP_STREAM","ADTRAN_UP_STREAM_2"};
        String[] listDownstream = {"ADTRAN_DOWN_STREAM","ADTRAN_DOWN_STREAM_2"};
        
		if(Constants.TransportType.contains("Pair Bonded"))
		{
//			String PORT1= "PORT_STATUS";
//			String PORT2 = "PORT_STATUS_2";
			for(int i=0;i<listPort.length;i++) {
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".PORT_ADMIN_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".PORT_OPER_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".LINE_LATENCY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".ASSIGNED_PROFILE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".PORT_MODE"));}
			
			for(int j=0;j<listUpstream.length;j++) {
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@OUTPUT_POWER"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@NOISE_MARGIN"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@ATTENUATION"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@DELAY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@PROV_DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@PERCENT_TRAIN_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listUpstream[j]+".@MAX_ATTAINABLE_DATA_RATE"));}
			
			for(int k=0;k<listDownstream.length;k++) {
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@OUTPUT_POWER"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@NOISE_MARGIN"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@ATTENUATION"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@DELAY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@PROV_DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@PERCENT_TRAIN_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listDownstream[k]+".@MAX_ATTAINABLE_DATA_RATE"));}
			
			}
		
		
	}
}
