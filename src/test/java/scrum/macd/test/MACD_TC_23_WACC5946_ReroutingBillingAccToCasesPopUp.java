package scrum.macd.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_23_WACC5946_ReroutingBillingAccToCasesPopUp extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	@Test(dataProvider = "getDataPlan")
	public void verify_CasesPopUpMessage(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillSelectManageAccount();
		sfdc.accManagement.verify_CasesPopUpTitle();
		
	}
	
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
