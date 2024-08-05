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

public class WA_TC_73_ViewingAndValidating3YearPricingFlow_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-5947 & WACC-5854
	 *                              Viewing and validating 36 month pricing flow
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_headerDetails_InPDF(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillCreateQuotePage();

		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,
				InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.check_36MonthsFinancingInDeviceSection();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price );
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.clickProceedToCheckoutBtn();

		// validate url in PDF Copy of Quote Summary to Customer
		String[] threeYearCheck = { "36-month","$19.44" };
		sfdc.reOrder.select_SendPDFCopy_button();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION + "Proposal.pdf");
		sfdc.reOrder.select_DownloadOrderSummary_button();
		sfdc.reOrder.validate_arrayText_InPDF(threeYearCheck, Constants.DOWNLOADS_LOCATION + "Proposal.pdf");
		sfdc.reOrder.select_BackToReviewOrder_button();

		// validate url in PDF Verbal Agreement
		
		sfdc.reOrder.acceptQuoteVerbally();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION + "Agreement.pdf");
		sfdc.reOrder.select_DownloadOrderSummary_button();
		sfdc.reOrder.validate_arrayText_InPDF(threeYearCheck, Constants.DOWNLOADS_LOCATION + "Agreement.pdf");
		sfdc.reOrder.select_BackToReviewOrder_button();

		// validate url in eSign Agreement
		
		sfdc.reOrder.acceptQuoteByESign();
		PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION + "ContractAgreement.pdf");
		//sfdc.genDoc.generateDocument("pdf");
		sfdc.genDoc.generateDocuement(InputData.quoteNumber);
		sfdc.reOrder.validate_arrayText_InPDF(threeYearCheck, Constants.DOWNLOADS_LOCATION + "ContractAgreement.pdf");
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