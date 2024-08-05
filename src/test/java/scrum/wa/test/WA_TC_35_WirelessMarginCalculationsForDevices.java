package scrum.wa.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_35_WirelessMarginCalculationsForDevices extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	double fullPrice, monthlyPrice, OneTimeTCV, TotalTCV, TotalCosts, MonthlyRecurringCharge;

	int noOfMonths;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2822 Validate margin
	 *                              calculations with pay full device price
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPricesPayFullDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillSelectDevice();
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		sfdc.shopWADevcs.selectDevicePaywithFullPrice();
		fullPrice = sfdc.shopWADevcs.get_deviceFullPriceOnDetailsPage(sf.shopWADevobj.deviceFullPricewithoutDecimal,
				sf.shopWADevobj.deviceFullPriceonlyDecimal);
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, fullPrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				TotalTCV, fullPrice, "Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2822 Validate margin
	 *                              calculations with financing, Validate, margin
	 *                              calculations after updating cart quantity
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceswithFinance(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillSelectDevice();
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalCosts = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs ");
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge ");
		sfdc.shopWADevcs.selectWirelessDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName,
				"PostWirelessPlans");
		monthlyPrice = sfdc.shopWADevcs.get_devicePriceOnDetailsPage();
		noOfMonths = sfdc.shopWADevcs.get_numberOfMonths(sf.shopWADevobj.financeMonthstab);
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(6000);
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_EqualsFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_AddFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				MonthlyRecurringCharge, monthlyPrice, "monthly Recurring Charge ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		TotalCosts = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs ");
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		sfdc.shopCart.updateCartQtyPlans("2");
		sf.seleU.wait(6000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_EqualsFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");
		sfdc.shopWADevcs.get_SubFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				(MonthlyRecurringCharge * 2), (sfdc.shopCart.get_flatDiscountPrice() * 2), "Monthly Recurring Charge");
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2822 Validate margin
	 *                              calculations first device and then plan
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceFirstDeviceandPlan(Hashtable<String, String> dataTable) throws Exception {

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
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "), "Total TCV ");

	}

	public void reachTillSelectDevice() throws Exception {
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
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sfdc.selectAddOn.clickOnContinueToDevice();

	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}