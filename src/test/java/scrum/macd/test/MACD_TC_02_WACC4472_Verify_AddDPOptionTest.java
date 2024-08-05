package scrum.macd.test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MACD_TC_02_WACC4472_Verify_AddDPOptionTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-4472
	@Test(dataProvider = "getDataPlan")
	public void validate_ExistingDP_AddAppleCare(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillReviewWilessLine("965796451","");
		sfdc.reviewWALine.validate_Product("20GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_Product("Device Protection");
		sfdc.reviewWALine.select_Add_addonsBtn();
		sfdc.macdSelAddon.validate_ExistingAddOn("Device Protection", "15.99");
		String[] addonList= {"Apple Care"};
		sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
		sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Device Protection", "Device Protection");
 		sfdc.macdSelAddon.selectAddOn("Device Protection", "Apple Care");
			
	}
	//Test Case is for US  WACC-4472
		@Test(dataProvider = "getDataPlan")
		public void validate_ExistingAppleCare_AddNewDP(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWilessLine("965806706","");
			sfdc.reviewWALine.validate_Product("60GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.validate_Product("Apple Care +");
			sfdc.reviewWALine.select_Add_addonsBtn();	
			sfdc.macdSelAddon.validate_ExistingAddOn("Apple Care", "199");
			String[] addonList= {"Device Protection"};
			sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
			sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Device Protection", "Apple Care");
			sfdc.macdSelAddon.selectAddOn("Device Protection", "Device Protection");
		}
		//Test Case is for US  WACC-4472
		@Test(dataProvider = "getDataPlan")
		public void validate_ExistingDP_ForSamsungDevice(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWilessLine("965807118", "");
			sfdc.reviewWALine.validate_Product("100GB Pooled");
			sfdc.reviewWALine.validate_Product("Samsung Galaxy Z Fold3 5G");
			sfdc.reviewWALine.validate_Product("Device Protection");
			sfdc.reviewWALine.select_Add_addonsBtn();
			sfdc.macdSelAddon.validate_ExistingAddOn("Device Protection", "6.99");
			String[] addonList= {};
			sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
			sfdc.macdSelAddon.validate_ExistingAddOnNotDisplayed("Device Protection", "Device Protection");				
		}
		//Test Case is for US  WACC-4472
		@Test(dataProvider = "getDataPlan")
		public void validate_NoExistingDP_ForSamsungDevice(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWilessLine("965816937", "1010004121");
			sfdc.reviewWALine.validate_Product("100GB Pooled");
			sfdc.reviewWALine.validate_Product("Samsung Galaxy Z Fold3 5G");
			sfdc.reviewWALine.validate_Product("Device Protection");
			sfdc.reviewWALine.select_Add_addonsBtn();
			sfdc.macdSelAddon.validate_ExistingAddOnNotPresent("Device Protection");
			String[] addonList= {"Device Protection"};
			sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
			sfdc.macdSelAddon.selectAddOn("Device Protection", "Device Protection");								
		}
		//Test Case is for US  WACC-4472
		@Test(dataProvider = "getDataPlan")
		public void validate_NoExistingDP_ForAppleDevice(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillReviewWilessLine("965810542", "");
			sfdc.reviewWALine.validate_Product("40GB Pooled");
			sfdc.reviewWALine.validate_Product("iPhone 11");
			sfdc.reviewWALine.select_Add_addonsBtn();
			sfdc.macdSelAddon.validate_ExistingAddOnNotPresent("Device Protection");
			sfdc.macdSelAddon.validate_ExistingAddOnNotPresent("Apple Care");
			String[] addonList= {"Device Protection", "Apple Care"};
			sfdc.macdSelAddon.validate_AddOnPresent("Device Protection",addonList);
			sfdc.macdSelAddon.selectAddOn("Device Protection", "Apple Care");								
		}		

//Method is for common navigation for all test cases
	public void reachTillReviewWilessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Add-On");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
