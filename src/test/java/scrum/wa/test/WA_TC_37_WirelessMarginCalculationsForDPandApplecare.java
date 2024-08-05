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

public class WA_TC_37_WirelessMarginCalculationsForDPandApplecare extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	double OneTimeTCV, TotalTCV, AppleCarePrice, DeviceProtectionPrice, MonthlyRecurringCharge;
	int noOfMonths;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2824 Validate margin
	 *                              calculations with apple care Validate, margin
	 *                              calculations after updating cart quantity
	 */

	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceswithAppleCare(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sf.seleU.waitTillLoading();
		sf.seleU.wait(4000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		AppleCarePrice = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.appleCarePrice, "Apple Care");
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, AppleCarePrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				TotalTCV, AppleCarePrice, "Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		sfdc.shopCart.updateCartQtyPlans("2");
		sf.seleU.wait(10000);
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, OneTimeTCV, "One time TCV ");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "), "Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				(MonthlyRecurringCharge * 2), (sfdc.shopCart.get_flatDiscountPrice() * 2), "Monthly Recurring Charge");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2824 Validate margin
	 *                              calculations with Device protection, Validate
	 *                              margin calculations after updating cart quantity
	 */
//	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceswithDeviceProtection(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sf.seleU.waitTillLoading();
		sf.seleU.wait(4000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		DeviceProtectionPrice = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.deviceProtectionPrice,
				"Device Protection ");
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_AddFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				MonthlyRecurringCharge, DeviceProtectionPrice, "monthly Recurring Charge ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		sfdc.shopCart.updateCartQtyPlans("2");
		sf.seleU.wait(10000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				(MonthlyRecurringCharge * 2), (sfdc.shopCart.get_flatDiscountPrice() * 2), "Monthly Recurring Charge");
		sfdc.shopWADevcs.get_EqualsFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, "One time TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2824 Validate margin
	 *                              calculations first device with apple care, Validate margin
	 *                              calculations after updating cart quantity
	 */
	
//	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceswithAppleCareFirstDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPFirstDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sf.seleU.waitTillLoading();
		sf.seleU.wait(4000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		AppleCarePrice = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.appleCarePrice, "Apple Care");
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, AppleCarePrice, "One time TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				TotalTCV, AppleCarePrice, "Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "total TCV"),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total costs");
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		sfdc.shopCart.updateCartQtyPlans("2");
		sf.seleU.wait(10000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				(MonthlyRecurringCharge * 2), (sfdc.shopCart.get_flatDiscountPrice() * 2), "Monthly Recurring Charge");
		sfdc.shopWADevcs.get_EqualsFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, "One time TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2824 Validate margin
	 *                              calculations first device with Device protection, Validate
	 *                              margin calculations after updating cart quantity
	 */
	
//	@Test(dataProvider = "getDataPlan")
	public void validate_FooterPriceswithDeviceProtectionFirstDevice(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPFirstDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sf.seleU.waitTillLoading();
		sf.seleU.wait(4000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		TotalTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV ");
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		DeviceProtectionPrice = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.deviceProtectionPrice,
				"Device Protection ");
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_AddFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge "),
				MonthlyRecurringCharge, DeviceProtectionPrice, "monthly Recurring Charge ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");
		sfdc.selectPro.selectDataPlanAndType(InputData_WA.WACC_Data_Type, InputData_WA.WACC_Plan_Type,
				InputData_WA.WACC_Plan_Size);
		sfdc.selectPro.continueToAddOnsButton();
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sf.seleU.waitTillLoading();
		sf.seleU.wait(5000);
		sfdc.bAccessories.clickProceedToShoppingCartBtn();
		MonthlyRecurringCharge = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge,
				"Monthly Recurring Charge");
		sfdc.shopCart.updateCartQtyPlans("2");
		sf.seleU.wait(10000);
		OneTimeTCV = sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV ");
		sfdc.shopWADevcs.get_MultipleFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				noOfMonths, "Recurring TCV ");
		sfdc.shopWADevcs.get_AddFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.RecurringTCV, "Recurring TCV "), OneTimeTCV,
				"Total TCV ");
		sfdc.shopWADevcs.get_SubFooter(
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.monthlyRecurringCharge, "Monthly Recurring Charge"),
				(MonthlyRecurringCharge * 2), (sfdc.shopCart.get_flatDiscountPrice() * 2), "Monthly Recurring Charge");
		sfdc.shopWADevcs.get_EqualsFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.oneTimeTCV, "One Time TCV "),
				OneTimeTCV, "One time TCV ");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total Costs "), "Margin TCV");
		sfdc.shopWADevcs.get_SubFooter(sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalCosts, "Total costs "),
				sfdc.shopWADevcs.get_FooterPrice(sf.shopWADevobj.totalTCV, "Total TCV "),
				sfdc.shopWADevcs.get_FooterMarginTCV(sf.shopWADevobj.marginTCV, "Margin TCV "), "Total Costs ");
	}
	
	public void reachTillDPPage(String deviceBrand, String deviceModel) throws Exception {
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
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		noOfMonths = sfdc.shopWADevcs.get_numberOfMonths(sf.shopWADevobj.financeMonthstab);
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

	}

	public void reachTillDPFirstDevice(String deviceBrand, String deviceModel) throws Exception {

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
		sfdc.selectPro.selectShopeWirelessDevices();
		sfdc.shopWADevcs.selectWirelessDevice(deviceBrand, deviceModel, "PostWirelessPlans");
		noOfMonths = sfdc.shopWADevcs.get_numberOfMonths(sf.shopWADevobj.financeMonthstab);
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
		
	}
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}

}
