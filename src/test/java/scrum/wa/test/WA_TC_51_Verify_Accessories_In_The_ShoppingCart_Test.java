package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_51_Verify_Accessories_In_The_ShoppingCart_Test extends Base{
	
	/**
	 * @author Shruti.desai1, Date : 28/01/2022
	 * 
	 *     WACC-2758:  Accessories in the Shopping Cart
	 *
	 */
	
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	//Test Case is for US WACC-2758
	@Test(dataProvider = "getDataPlan")
	public void Validate_Accessories_color_quantity_removeOption_ViewOnShoppingCart(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.enterQuantityAccDetailsPage("3");
		
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		/* 
		//add accessories with different SKU
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories","AirPods 2 In-Ear Bluetooth Headphones with Charging Case White");
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories","Commuter Protective Case Black for iPhone 11");
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		*/
		// Click on Proceed to Shopping Cart button
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		
		// Verify added accessories in cart
		sfdc.shopCart.verify_Accessories_Color_Quantity_InCart(InputData_WA.WACC_AccessoryName, 1);
		//sfdc.shopCart.verify_Accessories_Color_Quantity_InCart("AirPods 2 In-Ear Bluetooth Headphones with Charging Case White", 1);
		//sfdc.shopCart.verify_Accessories_Color_Quantity_InCart("Commuter Protective Case Black for iPhone 11", 1);
		
		//validate accessories is the part of OTC section with One-time fees section
		sfdc.shopCart.validate_ProductCostType(InputData_WA.WACC_AccessoryName);
		//sfdc.shopCart.validate_ProductCostType("AirPods 2 In-Ear Bluetooth Headphones with Charging Case White");
		//sfdc.shopCart.validate_ProductCostType("Commuter Protective Case Black for iPhone 11");
		
		//Change Quantity of Accessories in the Shopping Cart
		sfdc.shopCart.validate_IncreaseAccQuantity_InCart(InputData_WA.WACC_AccessoryName,4);
		
		/*
		//remove accessories from the cart and validate it
		sfdc.shopCart.validate_RemoveAccLink_InCart(InputData_WA.WACC_AccessoryName);
		sfdc.shopCart.click_RemoveAccLink_InCart(InputData_WA.WACC_AccessoryName);
		sfdc.shopCart.validate_AccessoryRemoved_InCart(InputData_WA.WACC_AccessoryName);
		sfdc.shopCart.click_RemoveAccLink_InCart("AirPods 2 In-Ear Bluetooth Headphones with Charging Case White");
		sfdc.shopCart.validate_AccessoryRemoved_InCart("AirPods 2 In-Ear Bluetooth Headphones with Charging Case White");
		sfdc.shopCart.click_RemoveAccLink_InCart("Commuter Protective Case Black for iPhone 11");
		sfdc.shopCart.validate_AccessoryRemoved_InCart("Commuter Protective Case Black for iPhone 11");
		*/
			
	}
	
	
	//Test Case is for US WACC-2758 :Accessories in the Shopping Cart
	//Test Case is for US WACC-2759 :Accessories in the Order Summary
	@Test(dataProvider = "getDataPlan")
	public void verify_Accessories_In_OrderSummary_Page(Hashtable<String, String> dataTable) throws Exception  {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	

		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,InputData_WA.WACC_DeviceProtectionName);
		//add accessories
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories",InputData_WA.WACC_AccessoryName);
		sfdc.accessoryDetails.enterQuantityAccDetailsPage("3");
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.accessoryDetails.select_BackToAccessoriesLink();
		sfdc.bAccessories.select_ResetFilterOnFilterBubble();
		sf.seleU.clickElementByJSE(sf.bAccessoriesObj.viewBtn);
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories","IPHONE 11/XR ANTIBACTERIAL TEMPERED GLASS WITH INSTALLER");
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		//verifying shopping cart page
		sfdc.shopCart.validate_ProductCostType(InputData_WA.WACC_AccessoryName);
		sfdc.shopCart.validate_ProductCostType("IPHONE 11/XR ANTIBACTERIAL TEMPERED GLASS WITH INSTALLER");
		sfdc.shopCart.verifyItemsQuantity("accessory",InputData_WA.WACC_AccessoryName,3);
	}
	
		
	//Method is for common navigation for all test cases
	public void reachTillShopAccessoriesPage(String deviceBrand, String deviceModel, String deviceProtection) throws Exception {
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
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,InputData_WA.WACC_Plan_Size );
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.selectPro.selectDeviceProtection(deviceProtection);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.selectPro.select_Browse_Accessories();
	}
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
