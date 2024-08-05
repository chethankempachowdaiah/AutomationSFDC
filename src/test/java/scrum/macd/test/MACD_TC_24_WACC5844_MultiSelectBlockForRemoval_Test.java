package scrum.macd.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_24_WACC5844_MultiSelectBlockForRemoval_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	
	//Method is for common navigation for all test cases
	public void reachTillSelectManageAccount() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS Service***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount("967030800");
		sfdc.accManagement.select_ManageAccButton();
		//sfdc.accManagement.select_MACDButton();
		//sfdc.accManagement.verify_MACDFlows();
		//sfdc.accManagement.select_AccManagementOption("Service Blocks");
		
    }	
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	} 					
}
