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
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.data.InputData_WA.AgreementOrderDetails;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.communities.Communities_CompleteOrderFlow_Page;

/**
 * @author Satish.Doraiswamy
 *Mar. 1, 2022
 * MP-26
 * US Number  : 
 * 1.1932(Verify Duplicate Port Number)
 * 2.2762(Verify PDF Document Terms and Conditions)
 * 3.2764 (Verify PDF Terms and Condition Link and other values)
 * 4.2757 (Verify Prepared by: (Name, email, phone number),Quote number: Presented date:)
 */
public class WA_TC_01_1932_Verify_PortNumber_Duplicate_Test extends BaseDataProvider {
	
	SFDC_AllPages sfdc = new SFDC_AllPages();


	@Test(dataProvider = "getDataPlan")
	public void verifyPDFDocsTermsNCondtion_PortNumber_Duplicate(Hashtable<String, String> dataTable) throws Exception {
		String existingPhNum="4169313456";
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		
		sfdc.accDetails.searchBusAccGlobalSearch(InputData_WA.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.wait_Till_Load_CreateQuote_Button();
		sfdc.cQuote.clickCreateQuotePbfButton_FIT();
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
		sfdc.bAccessories.resetAccessoriesFilter();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.getPostTaxCreditIfAvailable();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.reviewOrder();
		sfdc.reOrder.validateTermsAndConditionsPDFDocs();
		
		//Validating 2764
		String termsConditionURL= Constants.TERMSANDCONDITIONS_TV;
		sfdc.reOrder.validate_TermsAndConditionLink(termsConditionURL);
		sfdc.reOrder.select_SendPDFCopy_button();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +Constants.PROPOSAL_PDF);
		
		//Validating 2762 AND 2757
		sfdc.reOrder.select_DownloadOrderSummary_button();
	
		sfdc.reOrder.validate_text_InPDF(termsConditionURL,Constants.DOWNLOADS_LOCATION +"Proposal.pdf" );
		
		 String value = PDFHelper.readPDFFileLineByLine(Constants.DOWNLOADS_LOCATION +"Proposal.pdf" );
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.PREPARED_FOR.getAgreementOrderDetails());
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.PREPARED_BY.getAgreementOrderDetails());
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.PROPOSAL.getAgreementOrderDetails());
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.QUOTE_NUMBER.getAgreementOrderDetails());
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.ORDER_SUMMARY.getAgreementOrderDetails());
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.PRESENT_DATE.getAgreementOrderDetails());
		 PDFHelper.verifyPreparedForData(value,AgreementOrderDetails.VALID_THROUGH.getAgreementOrderDetails());
		 PDFHelper.verifyAttentionData(value,AgreementOrderDetails.ATTENTION.getAgreementOrderDetails());
		 PDFHelper.verifyAgreementOrderSummaryTableColumnData(value,AgreementOrderDetails.ORDER_SUMMARY.getAgreementOrderDetails());
		 PDFHelper.verifyAgreementTotalForAllLineTableColumnData(value,AgreementOrderDetails.TOTAL_FOR_ALL_LINES.getAgreementOrderDetails());
		 PDFHelper.verifyTermsAndConditionURLAndText(value);
		 sfdc.reOrder.select_BackToReviewOrder_button();
		
		sfdc.reOrder.acceptQuoteOptions();
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sf.seleU.hardwait(5000);
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();

		if (InputData_WA.WACC_eSignature.equals("Yes")) {
			sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(InputData_WA.siteContactEmailId, "Signed");
			sfdc.orderDetails.verifyContractAndQuoteDetailsWithTimeSpanCheck(Global.dataInput.awaitingSign,
					Global.dataInput.signedStatus);
			sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);

		} else {
			sfdc.quoteDetails.verifyQuoteStatusWACC(InputData_WA.WACC_quoteStatus);
		}
		sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(Global.dataInput.orderNumber);
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusReadyToSubmit);
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
	if (InputData_WA.env.equalsIgnoreCase("WADEVQA") || InputData_WA.env.equalsIgnoreCase("PREFIT")) {
			sfdc.comLogin.loginToWaccCommunities();
		} else {
			sfdc.comLogin.loginToCommunities();
		}

//		 Global.dataInput.orderNumber = "00117523";

		sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
		sfdc.comOrdFlow.selectRequestToPortNumber(existingPhNum);
		//Validating 1932
		Communities_CompleteOrderFlow_Page.verifyFieldValue("Eligibilty Check Number Details", sf.comOrderFlowObj.numberDetails,
				InputData_WA.PORT_NUM_ELIGIBILITY_CHECK_DUPLICATE_MSG);
		
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