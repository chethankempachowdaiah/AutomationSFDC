package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_29_VerifyFlatDiscountOnQuoteInCartTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-3409, WACC-2776
	@Test(dataProvider = "getDataPlan")
	public void Validate_FlatDiscountTextInCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillCreateQuotePage();
		reachTillShoppingCartPage();

		sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.shopCart.clickProceedToCheckoutBtn();
		//Validate Flat discount on Order summary page.
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.reOrder.clickCancelAndBackButton();
		
		sfdc.shopCart.updateCartQtyPlans("1");
		sfdc.shopCart.validate_RemovePlanComboAlert(
				"By continuing, your flat discounting will be removed from all available lines.", "CANCEL");

		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.shopCart.updateCartQtyPlans("1");
		sfdc.shopCart.validate_RemovePlanComboAlert(
				"By continuing, your flat discounting will be removed from all available lines.", "OK");

		sfdc.shopCart.validateNoFlatDiscountQuoteText();
		
		sfdc.shopCart.clickProceedToCheckoutBtn();
		//Validate Flat discount on Order summary page.
		sfdc.shopCart.validateNoFlatDiscountQuoteText();
	}

	// Test Case is for US WACC-3409
	@Test(dataProvider = "getDataPlan")
	public void Validate_FlatDiscountText_AfterRemovePlanInCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillCreateQuotePage();
		reachTillShoppingCartPage();
		sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.shopCart.selectRemoveCombo();
		sfdc.shopCart.validate_RemovePlanComboAlert(
				"By continuing, your flat discounting will be removed from all available lines.", "OK");
		sfdc.shopCart.validateNoFlatDiscountQuoteText();
	}

	// Test Case is for US WACC-2776
	@Test(dataProvider = "getDataPlan")
	public void Validate_FlatDiscountTextWithMultipleLine(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillCreateQuotePage();
		reachTillShoppingCartPage();
		sfdc.shopCart.validateNoFlatDiscountQuoteText();
		
		sfdc.shopCart.clickOnShopProductOnShopCart();
		reachTillShoppingCartPage();
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		
		sfdc.shopCart.clickProceedToCheckoutBtn();
		//Validate Flat discount on Order summary page.
		sfdc.shopCart.validateFlatDiscountQuoteText_Price("Flat discounting applied ($12.50/line)");
		sfdc.reOrder.clickCancelAndBackButton();
		
		sfdc.shopCart.selectRemoveCombo();
		sfdc.shopCart.validate_RemovePlanComboAlert(
				"By continuing, your flat discounting will be removed from all available lines.", "OK");
		sfdc.shopCart.validateNoFlatDiscountQuoteText();
		
		sfdc.shopCart.clickProceedToCheckoutBtn();
		//Validate Flat discount on Order summary page.
		sfdc.shopCart.validateNoFlatDiscountQuoteText();
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
	public void reachTillShoppingCartPage() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
