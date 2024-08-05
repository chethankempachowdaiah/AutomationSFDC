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

public class WA_TC_27_VerifyChangeItemQuantityInShoppingCartPageTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-3329
	@Test(dataProvider = "getDataPlan")
	public void Validate_IncreaseDecreaseQuantityInCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopWirelessPlan();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();

		// Click on Proceed to Shopping Cart button
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		// Increase the Quantity in cart
		sfdc.shopCart.updateCartQtyPlans("7");
		sfdc.shopCart.validateQuantityInCart("Fin Business", InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_DeviceName,
				InputData_WA.WACC_DeviceProtectionName, InputData_WA.WACC_AccessoryName, 7, 1);

		// Decrease the Quantity in Cart
		sfdc.shopCart.updateCartQtyPlans("3");
		sfdc.shopCart.validateQuantityInCart("Fin Business", InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_DeviceName,
				InputData_WA.WACC_DeviceProtectionName, InputData_WA.WACC_AccessoryName, 3, 1);
	}

	// Test Case is for US WACC-3329
	@Test(dataProvider = "getDataPlan")
	public void Validate_RemovePlanComboInCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillShopWirelessPlan();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();

		// Click on Proceed to Shopping Cart button
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		// Remove Price Plan in cart
		sfdc.shopCart.selectRemoveCombo();
		sfdc.shopCart.validate_ComboRemoved_AccessoryPresent();
	}
	// Test Case is for US WACC-3329
		@Test(dataProvider = "getDataPlan")
		public void Validate_EmptyCartAfterRemovePlan(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);

			reachTillShopWirelessPlan();
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
			sfdc.selectPro.continueToAddOnsButton();
			sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectAddOn.clickOnContinueToDevice();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
					"PostWirelessPlans");
			sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
			sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
			sfdc.selectPro.clicknAddToCartBtnForDP();
			
			// Click on Proceed to Shopping Cart button
			sfdc.bAccessories.clickProceedToShoppingCartBtn();
			// Remove Price Plan in cart
			sfdc.shopCart.selectRemoveCombo();
			sfdc.shopCart.validate_EmptyCart();
		}

	// Method is for common navigation for all test cases
	public void reachTillShopWirelessPlan() throws Exception {
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
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
