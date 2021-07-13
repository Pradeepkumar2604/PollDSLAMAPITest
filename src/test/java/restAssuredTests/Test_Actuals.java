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
		String apiURL = Constants.TEST_ENV+"/cgi-bin/POLLDSLAM/"+Constants.ActualsURL+"&XML=1&ADDLXML=1";
		
		Response responseData = CommonMethods.responseCapture(apiURL);
		
		//Verifying SYSTEM SPECIFIC INFORMATION
		
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_NAME"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_DESC"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_UP_TIME"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_SYS_INFO.ADTRAN_SYS_LOCATION"));
		
		new CommonMethods().getLogger().info("SYSTEM SPECIFIC INFORMATION is displayed");
		
		//Verifying CARD SPECIFIC INFORMATION
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_SLOT_STATE"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_SLOT_SERVICE_STATE"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_TYPE"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_SN"));
//		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_REV"));
		assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.ADTRAN_CARD_INFO.ADTRAN_CARD_UP_TIME"));
		
		new CommonMethods().getLogger().info("CARD SPECIFIC INFORMATION is displayed");
		
		//Verifying SIGNAL QUALITY STATISTICS
        String[] listPort = {"PORT_STATUS","PORT_STATUS_2"};
        String[] liststream = {"ADTRAN_UP_STREAM","ADTRAN_UP_STREAM_2","ADTRAN_DOWN_STREAM","ADTRAN_DOWN_STREAM_2"};
     
		if(Constants.TransportType.contains("Pair Bonded"))
		{
			new CommonMethods().getLogger().info("Transport type is of type Pair bonded");
			//Verifying PHYSICAL PORT 
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.BONDING_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.BONDING_GROUP_ID"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.NUM_PROVISIONED_LINKS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.NUM_ACTIVE_LINKS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.BONDING_GROUP_ASSIGNMENT.NUM_OPERATIONAL_LINKS"));
			new CommonMethods().getLogger().info("PHYSICAL PORT is displayed");
			
			for(int i=0;i<listPort.length;i++) {
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".PORT_ADMIN_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".PORT_OPER_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".LINE_LATENCY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".ASSIGNED_PROFILE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+listPort[i]+".PORT_MODE"));}
			
			for(int j=0;j<liststream.length;j++) {
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@OUTPUT_POWER"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@NOISE_MARGIN"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@ATTENUATION"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@DELAY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@PROV_DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@PERCENT_TRAIN_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+liststream[j]+".@MAX_ATTAINABLE_DATA_RATE"));}
			
			new CommonMethods().getLogger().info("SIGNAL QUALITY STATISTICS is displayed for pair bonded");
			}
		else
		{
			new CommonMethods().getLogger().info("Transport type is of type Non pair bonded");
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.PORT_STATUS.PORT_ADMIN_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.PORT_STATUS.PORT_OPER_STATUS"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.PORT_STATUS.ASSIGNED_PROFILE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.PORT_STATUS.LINE_LATENCY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS.PORT_STATUS.PORT_MODE"));
			
			String[] stream= {"ADTRAN_UP_STREAM","ADTRAN_DOWN_STREAM "};
			for(int k=0;k<stream.length;k++){
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@OUTPUT_POWER"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@NOISE_MARGIN"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@ATTENUATION"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@DELAY"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@PROV_DATA_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@PERCENT_TRAIN_RATE"));
			assertTrue(!CommonMethods.stringContentValidation(responseData, "ADTRAN_ACTUALS."+stream[k]+".@MAX_ATTAINABLE_DATA_RATE"));
			}
			
			new CommonMethods().getLogger().info("SIGNAL QUALITY STATISTICS is displayed for non pair bonded");
		}
		
		
	}
}
