package com.Regression_WA_FIT_test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.data.InputData_WA.CreditAndFraudRiskCreditOptios;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Satish.Doraiswamy
 *Mar. 31, 2022
 * MP 27
 *US - 2789
 *US -5441 
 * TC_001_Verify Accessories Status.
 * 1. "Available and in stock" and Add Button Status
 * 2. "Delayed for future shipping" and Add Button Status
 * 3. "Discontinued item" and Add Button Status
 * 4.  Accessory Stock Status Check -5441
 * 
 * 
 */

public class WA_TC_04_2789_VerifyAccessories_Avaialbility_Add_Button_Status_Test extends BaseDataProvider {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * TC_001_Verify Accessories Status.
	 * 1. "Available and in stock" and Add Button Status
	 * 2. "Delayed for future shipping" and Add Button Status
	 * 3. "Discontinued item" and Add Button Status
	 */

	@Test(dataProvider = "getDataPlan")
	public void verifyAccessoriesStockMsgAndAddButtonStatus(Hashtable<String, String> dataTable) throws Exception {
//		String inStockAcc="Iphone 11/XR Antibacterial Tempered Glass with Installer";
		String inStockAcc="IPHONE 11/XR ANTIBACTERIAL TEMPERED GLASS WITH INSTALLER";
		String delayedAcc="AirPods Pro Bluetooth Headphone with Wireless Charging Case White";
		String disContinuedAcc="30W USB-C Power Adapter White";
		String discontinueWithSKUDoesntOrInvalid="AirPods Pro Bluetooth Headphone with Wireless Charging Case White";
		boolean status=false;
		
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		// ***************LOGIN AS AE Profile"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_AE);
		searchAccountAndCreateOppertunity(InputData_WA.account_Business_R4B, InputData_WA.contact_Business_R4B);
		createQuoteAndSelectAccessories(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Size, InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Availability);
		
		// "Available and in stock" and Add Button Status
		sfdc.bAccessories.clickOnAccessoriesViewDetailsBtn("All accessories",inStockAcc);
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		status=sfdc.accessoryDetails.validate_AddToCartButton_Status();
		Assert.assertTrue(status,"Add Button is Disabled");
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		
		//"Delayed for future shipping" and Add Button Status
		sfdc.bAccessories.clickOnAccessoriesViewDetailsBtn("All accessories",delayedAcc);
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		status=sfdc.accessoryDetails.validate_AddToCartButton_Status();
		Assert.assertTrue(status,"Add Button is Disabled");
		sfdc.accessoryDetails.select_BackToAccessoriesLink();

		// "Discontinued item" and Add Button Status
		sfdc.bAccessories.clickOnAccessoriesViewDetailsBtn("All accessories",disContinuedAcc);
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		status=sfdc.accessoryDetails.validate_AddToCartButton_Status();
		Assert.assertTrue(status,"Add Button is Enabled");
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		
		// SKU Does'nt Exist or Invalid "Discontinued item" and Add Button Status
		sfdc.bAccessories.clickOnAccessoriesViewDetailsBtn("All accessories",discontinueWithSKUDoesntOrInvalid);
		sfdc.accessoryDetails.verifyAccessoriesAvailability();
		status=sfdc.accessoryDetails.validate_AddToCartButton_Status();
		Assert.assertTrue(status,"Add Button is Enabled");
		sfdc.accessoryDetails.select_BackToAccessoriesLink();


	}

	public void searchAccountAndCreateOppertunity(String accountName,String contactBusinessName) throws Exception {
		// ***************LOGIN AS AE Profile***********************//
		sfdc.accDetails.searchBusAccGlobalSearch(accountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(contactBusinessName);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
	}


	public void loginToSFDCAndCloseOpenTab(String profile) throws IOException {
		sfdc.login.loginToSFDC(profile);
		sfdc.home.closeTabIfOpen();
	}

	public void createQuoteAndSelectAccessories(String dataType,String planType,String planSize,String addOnType,String addOnName,
			String addOnAvailability) throws Exception {
		sfdc.cQuote.clickCreateQuotePbfButton_FIT();
		sfdc.selectPro.verifyWirelessProducts();
		if (InputData_WA.WACC_Data_Type.equalsIgnoreCase("Voice and Data")) {
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
					InputData_WA.WACC_Plan_Size);
		} else {
			sfdc.selectPro.validateDataPlans();
			sfdc.selectPro.clickOnPlansAddToCart();
		}
		sf.seleU.hardwait(5000);
		sfdc.selectPro.continueToAddOnsButton();
		sf.seleU.hardwait(5000);
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice("Apple", "iPhone 11","PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();

	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void logout() throws IOException {
		// TODO Auto-generated method stub
		sfdc.home.logout();
	}
	
	/**
	 * @return
	 * @throws IOException
	 * 
	 *             Data Provider to fetch multiple set of data and assign them to 2D
	 *             Object Array
	 */
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		// return getDataSetsRunMode(Constants.WA_TESTDATA_FILE,
		// Constants.WIRELESS_PLANS_SELECTION_SHEET_PreProd);
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT);
	}
}