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
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_57_Verify_SaveForLater_Resume_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-3333
	@Test(dataProvider = "getDataPlan")
	public void validate_AddedPlanInCart_SavedAndResume_AfterClose(Hashtable<String, String> dataTable)
			throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillPlanSelectionPage();
		// navigate till select plan page
		sfdc.selectPro.verifyWirelessProducts();
		// close the quote tab
		sfdc.home.closeLastOpenedTab();
		sfdc.cQuote.clickCreateQuotePbfButton();

		// validate empty cart
		sfdc.shopCart.validate_ShoppingCartPage_Displayed();
		sfdc.shopCart.validate_EmptyCart();
		// click on shop Product
		sfdc.shopCart.clickOnShopProductOnShopCart();

		// Add Plan
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		// close the quote tab
		sfdc.home.closeLastOpenedTab();

		// validate shopping cart page
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.shopCart.validate_ShoppingCartPage_Displayed();
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Price, 1);
	}

	// Test Case is for US WACC-3333, WACC-1617
	@Test(dataProvider = "getDataPlan")
	public void validate_UpdatedQuantityInCart_SavedAndResume_AfterClose(Hashtable<String, String> dataTable)
			throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillPlanSelectionPage();
		// navigate till select plan page
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		// US WACC-1617
		sfdc.selectPro.validate_BrowseAcc_ContinueShoppingCartBtn();
		sfdc.selectPro.selectContinueToShoppingCartBtn();

		// Verify cart details
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,InputData_WA.WACC_DeviceProtectionCost, 1);

		// Update the quantity
		sfdc.shopCart.updateCartQtyPlans("3");
		sf.seleU.waitTillLoading();
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Price, 3);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price, 3);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 3);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,InputData_WA.WACC_DeviceProtectionCost, 3);
		// close the quote tab
		sfdc.home.closeLastOpenedTab();
		sf.seleU.waitTillLoading();

		// validate shopping cart page
		sfdc.cQuote.clickCreateQuotePbfButton();

		sfdc.shopCart.validate_ShoppingCartPage_Displayed();
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Price, 3);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price, 3);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 3);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,
				InputData_WA.WACC_DeviceProtectionCost, 3);
	}

	// Test Case is for US WACC-3333, WACC-1617
	@Test(dataProvider = "getDataPlan")
	public void validate_UpdatedShippingAddressReviewOrder_SavedAndResume_AfterClose(
			Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		String streetName = null, city = null, state = null, postalCode = null;

		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillPlanSelectionPage();
		// navigate till select plan page
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();

		sfdc.bAccessories.select_ResetFilterOnFilterBubble();

		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();

		// Verify cart details
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,InputData_WA.WACC_DeviceProtectionCost, 1);

		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.reviewOrder();

		// getting address data from MP test data sheet and concat with current system
		// Date time
		streetName = Global.dataInput.addressStreet + AdditionalUtilities.generateRandomDigit(1000);
		city = Global.dataInput.addressCity + AdditionalUtilities.generateRandomDigit(1000);
		state = Global.dataInput.addressState;
		postalCode = Global.dataInput.addressPostalCode;

		sfdc.reOrder.edit_validateShippingAddress(streetName, city, state, postalCode);
		// close the quote tab
		sfdc.home.closeLastOpenedTab();

		// validate shopping cart page
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.shopCart.validate_ShoppingCartPage_Displayed();
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,InputData_WA.WACC_DeviceProtectionCost, 1);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);

		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.reviewOrder();
		sfdc.reOrder.validateShippingAddress(streetName, city, state, postalCode);
	}

	// Test Case is for US WACC-3333, WACC-1617
	@Test(dataProvider = "getDataPlan")
	public void validate_AddedItemInCart_SavedAndResume_DeviceFirst(Hashtable<String, String> dataTable)
			throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillPlanSelectionPage();
		sfdc.selectPro.selectShopeWirelessDevices();

		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sf.seleU.hardwait(5000);
		sfdc.selectPro.continueToAddOnsButton();
		sf.seleU.hardwait(5000);
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		// US WACC-1617
		sfdc.selectPro.validate_BrowseAcc_ContinueShoppingCartBtn();

		sfdc.home.closeLastOpenedTab();

		// validate shopping cart page
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.shopCart.validate_ShoppingCartPage_Displayed();
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_Plan_Type, InputData_WA.WACC_Plan_Price, 1);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price, 1);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.validate_DPName_Price(InputData_WA.WACC_DeviceProtectionName,InputData_WA.WACC_DeviceProtectionCost, 1);
	}

// Method is for common navigation for all test cases
	public void reachTillPlanSelectionPage() throws Exception {
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
