package scrum.wa.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_55_Verify_TermsAndConditionURl_InPDF extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-3766, WACC-5108 & WACC-3215
	@Test(dataProvider = "getDataPlan")
	public void validate_TermAndConditionURL_InPDF(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillCreateQuotePage();
		
		sfdc.selectPro.verifyWirelessProducts();		
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
		

		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		
		//Verify cart details
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price );
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName, InputData_WA.WACC_DeviceProtectionCost,1);
			
		sfdc.shopCart.clickProceedToCheckoutBtn();		
		sfdc.reOrder.validateTermsAndConditions();
		
		String termsConditionURL= "https://www.rogers.com/r4b/legal/business-wireless-terms-en.pdf";
		sfdc.reOrder.validate_TermsAndConditionLink(termsConditionURL);
				
		//validate url in PDF Copy of Quote Summary to Customer
		sfdc.reOrder.select_SendPDFCopy_button();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
		sfdc.reOrder.select_DownloadOrderSummary_button();
		sfdc.reOrder.validate_text_InPDF(termsConditionURL,Constants.DOWNLOADS_LOCATION +"Proposal.pdf" );
		sfdc.reOrder.select_EmailQuoteSummary_button();
		sfdc.reOrder.select_BackToReviewOrder_button();
		
		//validate url in PDF Verbal Agreement
		sfdc.reOrder.acceptQuoteVerbally();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +"Agreement.pdf");
		sfdc.reOrder.select_DownloadOrderSummary_button();
		sfdc.reOrder.validate_text_InPDF(termsConditionURL,Constants.DOWNLOADS_LOCATION +"Agreement.pdf");
		sfdc.reOrder.select_BackToReviewOrder_button();
		
		//validate url in eSign Agreement
		sfdc.reOrder.acceptQuoteByESign();
		
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +"Agreement_for_your_DocuSign_Signature.pdf");
		sfdc.mailinatorPage.reviewAndDownloadInEmailAtMailinator(InputData_WA.siteContactEmailId);
		sfdc.reOrder.validate_text_InPDF(termsConditionURL,Constants.DOWNLOADS_LOCATION+"Agreement_for_your_DocuSign_Signature.pdf" );
}

// Method is for common navigation for all test cases
public void reachTillCreateQuotePage() throws Exception {
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

}

@DataProvider
public Object[][] getDataPlan() throws IOException {
	return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
}

}
