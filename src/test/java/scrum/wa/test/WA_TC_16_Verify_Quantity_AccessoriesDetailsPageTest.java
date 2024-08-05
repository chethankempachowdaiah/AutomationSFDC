package scrum.wa.test;

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

public class WA_TC_16_Verify_Quantity_AccessoriesDetailsPageTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US  WACC-1984
	@Test(dataProvider = "getDataPlan")
	public void validate_Accessory_Qty_Zero(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.enterQuantityAccDetailsPage("0");
		//sfdc.bAccessories.validate_Quantity_ErrorMessage("Value must be greater than or equal to 1.");
		sfdc.accessoryDetails.validate_AddToCartButtonDisable();
	}

	//Test Case is for US WACC-1984
	@Test(dataProvider = "getDataPlan")
	public void validate_Accessory_Qty_More_Than_100(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.enterQuantityAccDetailsPage("101");
		//sfdc.bAccessories.validate_Quantity_ErrorMessage("Value must be less than or equal to 100.");
		sfdc.accessoryDetails.validate_AddToCartButtonDisable();
	}

	//Test Case is for US WACC-1984
	@Test(dataProvider = "getDataPlan")
	public void validate_Accessory_Qty_between_1To100(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);

		sfdc.accessoryDetails.validateQuantityOnAccDetailsPage("33");

	}
	
//Method is for common navigation for all test cases
	public void reachTillShopAccessoriesPage(String deviceBrand, String deviceModel) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.select_Browse_Accessories();
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
