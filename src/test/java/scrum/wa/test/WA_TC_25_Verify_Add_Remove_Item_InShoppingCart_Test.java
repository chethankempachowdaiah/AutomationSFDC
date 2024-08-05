package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_25_Verify_Add_Remove_Item_InShoppingCart_Test extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	double totalTCV, price, temp;

	// Test Case is for US WACC-3326
	@Test(dataProvider = "getDataPlan")
	public void Validate_AddItem_InCart(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		// ***************LOGIN AS AE***********************//
		reachTillSelectPlanPage();

		// Select wireless plan
		totalTCV = sfdc.bAccessories.get_FooterPrice();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sf.seleU.hardwait(5000);
		sf.seleU.waitTillLoading();
		totalTCV = totalTCV + Double.parseDouble(sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.FooterOneTimeTCV).replaceAll("[^\\d.]", "").trim());
		sfdc.bAccessories.validate_FooterPrice(totalTCV, "wireless Plan", InputData_WA.WACC_Plan_Size);

		// Select AddOn
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		totalTCV = sfdc.bAccessories.get_FooterPrice();
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.bAccessories.validate_FooterPrice(totalTCV, "addOn", "");

		// select Device
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		totalTCV = sfdc.bAccessories.get_FooterPrice();

		price = sfdc.shopWADevcs.get_devicePriceOnDetailsPage();
		temp = (totalTCV + price) * 36;
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		if (temp == sfdc.bAccessories.get_FooterPrice()) {
			reportStatusPass("Footer Price@: " + temp, true, true);
		}

		// Select Device Protection
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		totalTCV = sfdc.bAccessories.get_FooterPrice();

		price = sfdc.selectPro.get_dpPrice(InputData_WA.WACC_DeviceProtectionName);
		temp = totalTCV + price;
		sfdc.selectPro.clicknAddToCartBtnForDP();
		if (temp == sfdc.bAccessories.get_FooterPrice()) {
			reportStatusPass("Footer Price@: " + temp, true, true);
		}

		// Select Accessories
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		totalTCV = sfdc.bAccessories.get_FooterPrice();
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.validate_FooterPrice(totalTCV, "accessories", "");

		// Validate shopping cart
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price );
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.verifyDevice_DP_AccDetailsOnCart(InputData_WA.WACC_DeviceProtectionName, InputData_WA.WACC_DeviceProtectionCost,InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);
	}

	// Test Case is for US WACC-3326
	@Test(dataProvider = "getDataPlan")
	public void Validate_RemoveItem_InCart(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		// ***************LOGIN AS AE***********************//
		reachTillSelectPlanPage();

		// Select wireless plan
		totalTCV = sfdc.bAccessories.get_FooterPrice();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sf.seleU.hardwait(5000);
		sf.seleU.waitTillLoading();
		totalTCV = totalTCV + Double.parseDouble(sf.seleU.getTextFromWebElement(sf.bAccessoriesObj.FooterOneTimeTCV).replaceAll("[^\\d.]", "").trim());
		sfdc.bAccessories.validate_FooterPrice(totalTCV, "wireless Plan", InputData_WA.WACC_Plan_Size);
		
		// Select AddOn
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.selectAddOn(InputData_WA.WACC_AddOn_Type, InputData_WA.WACC_AddOn_Name,InputData_WA.WACC_AddOn_Availability);
		totalTCV = sfdc.bAccessories.get_FooterPrice();
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
		sfdc.bAccessories.validate_FooterPrice(totalTCV, "addOn", "");

		// select Device
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		totalTCV = sfdc.bAccessories.get_FooterPrice();

		price = sfdc.shopWADevcs.get_devicePriceOnDetailsPage();
		temp = (totalTCV + price) * 36;
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		if (temp == sfdc.bAccessories.get_FooterPrice()) {
			reportStatusPass("Footer Price@: " + temp, true, true);
		}

		// Select Device Protection
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		totalTCV = sfdc.bAccessories.get_FooterPrice();

		price = sfdc.selectPro.get_dpPrice(InputData_WA.WACC_DeviceProtectionName);
		temp = totalTCV + price;
		sfdc.selectPro.clicknAddToCartBtnForDP();
		if (temp == sfdc.bAccessories.get_FooterPrice()) {
			reportStatusPass("Footer Price@: " + temp, true, true);
		}

		// Select Accessories
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		totalTCV = sfdc.bAccessories.get_FooterPrice();
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.validate_FooterPrice(totalTCV, "accessories", "");

		// Validate shopping cart
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.verifyCartDetails(InputData_WA.WACC_Plan_Size, InputData_WA.WACC_Plan_Price, InputData_WA.WACC_Plan_Type, InputData_WA.WACC_AddOn_Name, InputData_WA.WACC_AddOn_Price );
		sfdc.shopCart.verify_MonthlyProductName_Price(InputData_WA.WACC_DeviceName, InputData_WA.WACC_DeviceCost, 1);
		sfdc.shopCart.verifyDevice_DP_AccDetailsOnCart(InputData_WA.WACC_DeviceProtectionName, InputData_WA.WACC_DeviceProtectionCost,InputData_WA.WACC_AccessoryName, InputData_WA.WACC_AccessoryCost, 1);
		
		sfdc.shopCart.selectRemoveCombo();
		sfdc.shopCart.validate_ComboRemoved_AccessoryPresent();
	}

	// Method is for common navigation for all test cases
	public void reachTillSelectPlanPage() throws Exception {
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
