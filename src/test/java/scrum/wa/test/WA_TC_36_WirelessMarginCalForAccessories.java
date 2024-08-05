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

public class WA_TC_36_WirelessMarginCalForAccessories extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	double OneTimeTCV, TotalTCV, acceFullPrice;
	int noOfMonths;
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 		Test Case is for US WACC-2823 
	 *      Validate margin calculations with adding accessories
	 *      Validate margin calculations after updating accessories cart quantity  
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceswithAccessories(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);
		
		reachTillShopAccessoriesPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sf.seleU.waitTillLoading();
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		acceFullPrice = sfdc.shopWADevcs.get_deviceFullPriceOnDetailsPage(sf.shopWADevobj.acceFullPricewithoutDecimal,sf.shopWADevobj.acceFullPriceonlyDecimal);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "), OneTimeTCV, acceFullPrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), TotalTCV, acceFullPrice, "Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		sfdc.shopCart.updateCartQtyPlansAcce("2");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "), OneTimeTCV, acceFullPrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), TotalTCV, acceFullPrice, "Total TCV ");
		
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 * 		Test Case is for US WACC-2823 
	 * 		First Device then plan
	 *      Validate margin calculations with adding accessories
	 *      Validate margin calculations after updating accessories cart quantity  
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceWithAcceFirstDeviceandPlan(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(waData.account_Business_R4B);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		noOfMonths = sfdc.shopWADevcs.get_numberOfMonths(sf.shopWADevobj.financeMonthstab);
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
		sf.seleU.getTextFromWebElement(sf.shopWADevobj.getWirelessDeviceAdded);
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sf.seleU.wait(6000);
		sf.seleU.waitTillLoading();
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.bAccessories.clickOnViewDetailsBtn("All accessories", InputData_WA.WACC_AccessoryName);
		acceFullPrice = sfdc.shopWADevcs.get_deviceFullPriceOnDetailsPage(sf.shopWADevobj.acceFullPricewithoutDecimal,sf.shopWADevobj.acceFullPriceonlyDecimal);
		sfdc.accessoryDetails.clicknAddToCartBtnAccDetailsPage();
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "), OneTimeTCV, acceFullPrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), TotalTCV, acceFullPrice, "Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		sfdc.shopCart.updateCartQtyPlansAcce("2");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), 
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "), OneTimeTCV, acceFullPrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"), TotalTCV, acceFullPrice, "Total TCV ");
	}
	
	public void reachTillShopAccessoriesPage(String deviceBrand, String deviceModel) throws Exception {
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
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sfdc.selectAddOn.clickOnContinueToDevice();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		noOfMonths = sfdc.shopWADevcs.get_numberOfMonths(sf.shopWADevobj.financeMonthstab);
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

	}
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
