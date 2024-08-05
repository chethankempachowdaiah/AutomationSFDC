package com.Regression_WA_FIT_test;

import java.io.IOException;
import java.util.Hashtable;

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
 *Mar. 8, 2022
 * MP 26
 *US - 3637
 * TC_001_Verify Fraud High Risk Flag H with BYOD Device should be in Shopping cart
 * TC_002_Verify Fraud High Risk Flag H Shop to Device continue button should be Grayed out
 * TC_003_Verify Fraud High Risk Flag H with BYOD Device combo in cart
 * 
 */

public class WA_TC_03_3637_VerifyFraudHighRiskFlag_Test extends BaseDataProvider {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @author Satish.Doraiswamy
	 *Mar. 8, 2022
	 *US - 3637
	 * TC_001_Verify Fraud High Risk Flag H with BYOD Device should not be in Shopping cart
	 * TC_002_Verify Fraud High Risk Flag H Shop to Device continue button should be Grayed out
	 * TC_003_Verify Fraud High Risk Flag H with BYOD Device combo in cart
	 * TC_004_Verify Fraud High Risk Flag F with Add Device and DP plan validate in cart
	 * TC_005_Verify Fraud High Risk Flag FnF with Add Device and DP plan validate in cart
	 */

	@Test(dataProvider = "getDataPlan")
	public void verifyFraud_HighRisKFlag_H_WithBYOD(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	


		// ***************LOGIN AS Fruad Ops"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_FraudOps);
		updateCreditRiskFlag(InputData_WA.contact_Business_R4B_FraudCheck, CreditAndFraudRiskCreditOptios.HIGHRISK_H.getCreditAndFraudRiskCreditOptios());
		logout();
		
		// ***************LOGIN AS AE Profile"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_AE);
		searchAccountAndCreateOppertunity(InputData_WA.contact_Business_R4B_FraudCheck, InputData_WA.contact_Business_R4B);
		createQuoteAndBYODAddCart(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Size, InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Availability);
		
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
				InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);

	}

	/**
	 * @author Satish.Doraiswamy
	 *Mar. 10, 2022
	 *US - 3637
	 * TC_004_Verify Fraud High Risk Flag F with Add Device and DP plan validate in cart
	 * 
	 * 
	 */
	@Test(dataProvider = "getDataPlan")
	public void verifyFraud_HighRisKFlag_F_WithBYOD(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	


		// ***************LOGIN AS Fruad Ops"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_FraudOps);
		updateCreditRiskFlag(InputData_WA.contact_Business_R4B_FraudCheck, CreditAndFraudRiskCreditOptios.HIGHRISK_F.getCreditAndFraudRiskCreditOptios());
		logout();
		
		// ***************LOGIN AS AE Profile"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_AE);
		searchAccountAndCreateOppertunity(InputData_WA.contact_Business_R4B_FraudCheck, InputData_WA.contact_Business_R4B);
		createQuoteAndAddCart(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Size, InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Availability);
		
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
				InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);

	}

	/**
	 * @author Satish.Doraiswamy
	 *Mar. 10, 2022
	 *US - 3637
	 * TC_005_Verify Fraud High Risk Flag FnF with Add Device and DP plan validate in cart
	 * 
	 * 
	 */
	@Test(dataProvider = "getDataPlan")
	public void verifyFraud_HighRisKFlag_FNF_WithBYOD(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	


		// ***************LOGIN AS Fruad Ops"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_FraudOps);
		updateCreditRiskFlag(InputData_WA.contact_Business_R4B_FraudCheck, CreditAndFraudRiskCreditOptios.HIGHRISK_FNF.getCreditAndFraudRiskCreditOptios());
		logout();
		
		// ***************LOGIN AS AE Profile"***********************//
		loginToSFDCAndCloseOpenTab(InputData.Profile_AE);
		searchAccountAndCreateOppertunity(InputData_WA.contact_Business_R4B_FraudCheck, InputData_WA.contact_Business_R4B);
		createQuoteAndAddCart(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Size, InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Availability);
		
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
				InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);

	}

	public void searchAccountAndCreateOppertunity(String accountName,String contactBusinessName) throws Exception {
		// ***************LOGIN AS AE Profile***********************//
		sfdc.accDetails.searchBusAccGlobalSearch(accountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(contactBusinessName);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
	}

	public void updateCreditRiskFlag(String accountName,String creditRiskFlag) throws IOException {
		// ***************Update Credit Risk Flag"***********************//
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.contact_Business_R4B_FraudCheck);
		sfdc.accDetails.creditNFraud_CreditRiskValue_Option(CreditAndFraudRiskCreditOptios.HIGHRISK_F.getCreditAndFraudRiskCreditOptios());
	}

	public void loginToSFDCAndCloseOpenTab(String profile) throws IOException {
		sfdc.login.loginToSFDC(profile);
		sfdc.home.closeTabIfOpen();
	}

	public void createQuoteAndBYODAddCart(String dataType,String planType,String planSize,String addOnType,String addOnName,
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
//		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.selectAddOn.continueByodDeviceAndClickOnShopCart();
	}
	
	public void createQuoteAndAddCart(String dataType,String planType,String planSize,String addOnType,String addOnName,
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