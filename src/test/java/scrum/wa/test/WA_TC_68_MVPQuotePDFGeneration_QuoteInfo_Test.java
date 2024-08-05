package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_68_MVPQuotePDFGeneration_QuoteInfo_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	// Test Case is for US WACC-2757
		@Test(dataProvider = "getDataPlan")
		public void validate_ViewQuoteInfo(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillBrowseAccessories();
			
			sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
			sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
			sfdc.shopCart.click_ProceedToShopCartBtn();
			sfdc.shopCart.clickProceedToCheckoutBtn();
			
			// validate Prepared by, Quote Number, Presented date in PDF Copy of Quote Summary to Customer
			String[] pdfProposaltext = {"Prepared by:" , "Priyanka AE" , "priyanka.tawade@rci.rogers.com" ,
										"(932) 267-3097" , "Quote number:" , "Presented date:" , "Valid through:"};
			sfdc.reOrder.select_SendPDFCopy_button();
			sf.seleU.waitTillLoading();
			PDFHelper.deletefile_IfExist(Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
			sfdc.reOrder.select_DownloadOrderSummary_button();
			sfdc.reOrder.validate_arrayText_InPDF(pdfProposaltext, Constants.DOWNLOADS_LOCATION +"Proposal.pdf");
			//sfdc.reOrder.select_BackToReviewOrder_button();
			
		}
	
		//Method is for common navigation for all test cases
		public void reachTillBrowseAccessories() throws Exception {
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
			//sfdc.selectPro.selectPlanType();
			sfdc.selectPro.verifyWirelessProducts();
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
			sfdc.selectPro.clickOnPlansAddToCart();
			sfdc.selectPro.continueToAddOnsButton();
			sfdc.selectAddOn.validateLongDistancePlans(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectAddOn.clickOnContinueToDevice();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand,InputData_WA.WACC_DeviceName ,"PostWirelessPlans");
			sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
			sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
			sfdc.selectPro.clicknAddToCartBtnForDP();
			sfdc.selectPro.select_Browse_Accessories();
		}
		
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
