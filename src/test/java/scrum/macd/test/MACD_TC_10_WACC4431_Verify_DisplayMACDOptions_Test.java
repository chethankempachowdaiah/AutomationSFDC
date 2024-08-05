package scrum.macd.test;

import com.framework.base.MyListener;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TC_10_WACC4431_Verify_DisplayMACDOptions_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-4431
	@Test(dataProvider = "getDataPlan")
	public void validate_MACDFlows_ServiceProfile(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		//reachTillSelectBillingAccount("965830490", "");
		// ***************LOGIN AS Service***********************//
		  sfdc.login.loginToSFDC(InputData.Profile_Service);
		  sfdc.home.closeTabIfOpen();
		  sfdc.accManagement.selectBillingAccount("965830490");
		  sfdc.accManagement.select_ManageAccountButton("Skip");
		  sfdc.accManagement.verify_MACDFlows();
		  sfdc.accManagement.select_AccManagementOption("Service Blocks");
		  sfdc.home.closeTabIfOpen(); 
		  sfdc.home.logout();
		
	}

	//Test Case is for US  WACC-4431
	@Test(dataProvider = "getDataPlan")
	public void validate_MACDFlows_DeliveryProfile(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		 
		// ***************LOGIN AS Delivery***********************//
		  sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		  sfdc.home.closeTabIfOpen();
		  sfdc.accManagement.selectBillingAccount("965830490");
		  sfdc.accManagement.select_ManageAccountButton("Skip");
		  sfdc.accManagement.verify_MACDFlows();
		  sfdc.accManagement.select_AccManagementOption("Service Blocks");
		  sfdc.home.closeTabIfOpen(); 
		  sfdc.home.logout(); 	
	}	 
	
	    //Test Case is for US  WACC-4431
		@Test(dataProvider = "getDataPlan")
		public void validate_MACDFlows_FraudOpsProfile(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);		
		
			// ***************LOGIN AS Fraud Ops***********************//
			  sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
			  sfdc.home.closeTabIfOpen();
			  sfdc.accManagement.selectBillingAccount("965830490");
			  sfdc.accManagement.select_ManageAccountButton("Skip");
			  sfdc.accManagement.verify_MACDFlows();
			  sfdc.accManagement.select_AccManagementOption("Service Blocks");
			  sfdc.home.closeTabIfOpen();
			  sfdc.home.logout();
		} 
		 
		//Test Case is for US  WACC-4431
		@Test(dataProvider = "getDataPlan")
		public void validate_MACDFlows_CreditOpsProfile(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);		
		
		// ***************LOGIN AS Credit Ops***********************//
		  sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		  sfdc.home.closeTabIfOpen();
		  sfdc.accManagement.selectBillingAccount("965830490");
		  sfdc.accManagement.select_ManageAccountButton("Skip");
		  sfdc.accManagement.verify_MACDFlows();
		  sfdc.accManagement.select_AccManagementOption("Service Blocks");
		  sfdc.home.closeTabIfOpen(); sfdc.home.logout();
		}
		
		//Test Case is for US  WACC-4431
		@Test(dataProvider = "getDataPlan")
		public void validate_MACDFlows_SystemAdminProfile(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);				
		
			// ***************LOGIN AS System Admin ***********************//
			sfdc.login.loginToSFDC(InputData.Profile_SystemAdmin);
			sfdc.home.closeTabIfOpen();
			sfdc.accManagement.selectBillingAccount("965830490");		
			sfdc.accManagement.select_ManageAccountButton("Skip");
			sfdc.accManagement.verify_MACDFlows();
			sfdc.accManagement.select_AccManagementOption("Service Blocks");
			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();			
	    }	
		
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}


