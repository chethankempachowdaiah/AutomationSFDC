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

public class WA_TC_32_VerifyDP_InCartTest extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	// Test Case is for US WACC-2760
	@Test(dataProvider = "getDataPlan")
	public void Validate_DP_IncreasePlan_OnShopCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillCreateQuotePage();

		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
		String deviceCapacity = sfdc.shopWADevcs.getDeviceSelectedCapacity();
		String deviceColor = sfdc.shopWADevcs.getDeviceSelectedColor();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();

		// Verify cart details
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);
		sfdc.shopCart.validate_DPName_Price("Device Protection","15.99", 1);

		sfdc.shopCart.validate_ProductCostType("Device Protection");
		sfdc.shopCart.validateRelatedDeviceDetailsForDP("Device Protection", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);

		// update plan Quantity
		sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price, 2);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 2);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 2);
		sfdc.shopCart.validate_DPName_Price("Device Protection","15.99", 2);
		
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.shopCart.validate_DPName_Price("Device Protection","15.99", 1);

		sfdc.shopCart.validate_ProductCostType("Device Protection");
		sfdc.shopCart.validateRelatedDeviceDetailsForDP("Device Protection", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);
	}

	// Test Case is for US WACC-2760
	@Test(dataProvider = "getDataPlan")
	public void Validate_DP_IncreaseDevice_OnShopCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillCreateQuotePage();

		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		String deviceCapacity = sfdc.shopWADevcs.getDeviceSelectedCapacity();
		String deviceColor = sfdc.shopWADevcs.getDeviceSelectedColor();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection("Apple Care");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();

		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);
		sfdc.shopCart.validate_DPName_Price("Apple Care","199", 1);

		sfdc.shopCart.validate_ProductCostType("Apple Care");
		sfdc.shopCart.validateRelatedDeviceDetailsForDP("Apple Care", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);
		
		// update plan Quantity
		sfdc.shopCart.updateCartQtyPlans("2");
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price, 2);
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 2);
		sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 2);
		sfdc.shopCart.validate_DPName_Price("Apple Care","199", 2);

		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.shopCart.validate_DPName_Price("Apple Care","199", 2);

		sfdc.shopCart.validate_ProductCostType("Apple Care");
		sfdc.shopCart.validateRelatedDeviceDetailsForDP("Apple Care", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);
	}
	// Test Case is for US WACC-2760
		@Test(dataProvider = "getDataPlan")
		public void Validate_DP_removePlanAndAddNewDP_OnShopCart(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);

			reachTillCreateQuotePage();

			sfdc.selectPro.verifyWirelessProducts();
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
			sfdc.selectPro.continueToAddOnsButton();
			sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectAddOn.clickOnContinueToDevice();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
					"PostWirelessPlans");
			String deviceCapacity = sfdc.shopWADevcs.getDeviceSelectedCapacity();
			String deviceColor = sfdc.shopWADevcs.getDeviceSelectedColor();
			sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
			sfdc.selectPro.selectDeviceProtection("Apple Care");
			sfdc.selectPro.clicknAddToCartBtnForDP();
			sfdc.bAccessories.clickProceedToShoppingCartBtn();

			// Verify cart details
			sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
					InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
			sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
			sfdc.shopCart.validate_DPName_Price("Apple Care","199", 1);

			sfdc.shopCart.validate_ProductCostType("Apple Care");
			sfdc.shopCart.validateRelatedDeviceDetailsForDP("Apple Care", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);

			//validate remove plan
			sfdc.shopCart.selectRemoveCombo();
			sfdc.shopCart.validate_EmptyCart();
			//Add new Configuration
			sfdc.shopCart.clickShopProducts();
			sfdc.selectPro.verifyWirelessProducts();
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
			sfdc.selectPro.continueToAddOnsButton();
			sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectAddOn.clickOnContinueToDevice();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
			deviceCapacity = sfdc.shopWADevcs.getDeviceSelectedCapacity();
			deviceColor = sfdc.shopWADevcs.getDeviceSelectedColor();
			sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
			sfdc.selectPro.selectDeviceProtection("Device Protection");
			sfdc.selectPro.clicknAddToCartBtnForDP();
			sfdc.bAccessories.clickProceedToShoppingCartBtn();
			// Verify cart details
			sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,
					InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
			sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
			sfdc.shopCart.validate_DPName_Price("Device Protection","15.99", 1);

			sfdc.shopCart.validate_ProductCostType("Device Protection");
			sfdc.shopCart.validateRelatedDeviceDetailsForDP("Device Protection", InputData_WA.WACC_DeviceBrand,deviceCapacity, deviceColor);
			
			sfdc.shopCart.clickProceedToCheckoutBtn();
			sfdc.shopCart.validate_DPName_Price("Device Protection","15.99", 1);

			sfdc.shopCart.validate_ProductCostType("Device Protection");
			sfdc.shopCart.validateRelatedDeviceDetailsForDP("Device Protection", InputData_WA.WACC_DeviceBrand,deviceCapacity, deviceColor);

		}

		// Test Case is for US WACC-2760
		@Test(dataProvider = "getDataPlan")
		public void Validate_DP_removeDeviceAndAddNewDP_OnShopCart(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);

			reachTillCreateQuotePage();

			sfdc.selectPro.selectShopeWirelessDevices();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
					"PostWirelessPlans");
			String deviceCapacity = sfdc.shopWADevcs.getDeviceSelectedCapacity();
			String deviceColor = sfdc.shopWADevcs.getDeviceSelectedColor();
			sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
			sfdc.selectPro.selectDeviceProtection("Device Protection");
			sfdc.selectPro.clicknAddToCartBtnForDP();
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
			sfdc.selectPro.continueToAddOnsButton();
			sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectPro.select_Browse_Accessories();
			sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
			sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
			sfdc.bAccessories.clickProceedToShoppingCartBtn();

			sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
			sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
			sfdc.shopCart.verify_AccessoryName_Price(InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);
			sfdc.shopCart.validate_DPName_Price("Device Protection","15.99", 1);

			sfdc.shopCart.validate_ProductCostType("Device Protection");
			sfdc.shopCart.validateRelatedDeviceDetailsForDP("Device Protection", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);
			
			//validate remove plan
			sfdc.shopCart.selectRemoveCombo();
			sfdc.shopCart.validate_EmptyCart();
			//Add new Configuration
			sfdc.shopCart.clickShopProducts();
			sfdc.selectPro.selectShopeWirelessDevices();
			sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,"PostWirelessPlans");
			deviceCapacity = sfdc.shopWADevcs.getDeviceSelectedCapacity();
			deviceColor = sfdc.shopWADevcs.getDeviceSelectedColor();
			sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
			sfdc.selectPro.selectDeviceProtection("Apple Care");
			sfdc.selectPro.clicknAddToCartBtnForDP();
			sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size);
			sfdc.selectPro.continueToAddOnsButton();
			sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
			sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectPro.select_Browse_Accessories();
			sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
			sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
			sfdc.bAccessories.clickProceedToShoppingCartBtn();
			// Verify cart details
			sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price,InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price);
			sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
			sfdc.shopCart.validate_DPName_Price("Apple Care","199", 1);

			sfdc.shopCart.validate_ProductCostType("Apple Care");
			sfdc.shopCart.validateRelatedDeviceDetailsForDP("Apple Care", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);
			
			sfdc.shopCart.clickProceedToCheckoutBtn();
			sfdc.shopCart.validate_DPName_Price("Apple Care","199", 1);

			sfdc.shopCart.validate_ProductCostType("Apple Care");
			sfdc.shopCart.validateRelatedDeviceDetailsForDP("Apple Care", InputData_WA.WACC_DeviceBrand, deviceCapacity, deviceColor);
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
