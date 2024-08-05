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

public class WA_TC_44_validate_DP_addtocart_or_SkipDP extends MyListener {
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2296 First Device then Validate DP add to
	 *                              cart and skip DP
	 *                              select Apple care and click skip DP then land on wireless plans page 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_DPfirstDeviceandPlan_SelectAppleCaSkipDP(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPageFirstDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.shopWADevcs.validate_DPaddtoCartnotEnableandSkipDpEnable();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.shopWADevcs.validate_DPaddtoCartandSkipDpBothClickable();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.shopWADevcs.validate_landingOnWirelessPlansOrAccepage("wireless plans");
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2296 First Device then Validate DP add to
	 *                              cart and skip DP
	 *                              select Device Protection and click skip DP then land on wireless plans page 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_DPfirstDeviceandPlan_SelectDevProtSkipDP(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPageFirstDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.shopWADevcs.validate_DPaddtoCartnotEnableandSkipDpEnable();
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.shopWADevcs.validate_DPaddtoCartandSkipDpBothClickable();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.shopWADevcs.validate_landingOnWirelessPlansOrAccepage("wireless plans");
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2296 First Device then Validate DP add to
	 *                              cart and add DP, and skip DP
	 *                              click skip DP then land on wireless plans page 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_DPfirstDevice_DPAppleCareaddToCart(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPageFirstDevice(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.shopWADevcs.validate_DPaddtoCartnotEnableandSkipDpEnable();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.shopWADevcs.validate_DPaddtoCartandSkipDpBothClickable();
		//Added DP to the cart
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sfdc.shopWADevcs.validate_landingOnWirelessPlansOrAccepage("wireless plans");
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2296 First Plan then device 
	 *                              Validate DP add to cart and skip DP
	 *                              select Apple care and click skip DP then land on accessories page 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_DPfirstPlanandDeviceApplecare(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.shopWADevcs.validate_DPaddtoCartnotEnableandSkipDpEnable();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.shopWADevcs.validate_DPaddtoCartandSkipDpBothClickable();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.shopWADevcs.validate_landingOnWirelessPlansOrAccepage("accessories page");
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2296 First Plan then device 
	 *                              Validate DP add to cart and skip DP
	 *                              select device protection and click skip DP then land on accessories page 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_DPfirstPlanandDeviceselectDevProt(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.shopWADevcs.validate_DPaddtoCartnotEnableandSkipDpEnable();
		sfdc.selectPro.selectDeviceProtection("Device Protection");
		sfdc.shopWADevcs.validate_DPaddtoCartandSkipDpBothClickable();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.shopWADevcs.validate_landingOnWirelessPlansOrAccepage("accessories page");
	}
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Test Case is for US WACC-2296 First Plan then device 
	 *                              Validate DP add to cart and skip DP
	 *                              click skip DP then land on accessories page 
	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_DPfirstPlanandDeviceDPaddToCart(Hashtable<String, String> dataTable) throws Exception {

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillDPPage(InputData_WA.WACC_DeviceBrand, InputData_WA.WACC_DeviceName);
		sfdc.shopWADevcs.validate_DPaddtoCartnotEnableandSkipDpEnable();
		sfdc.selectPro.selectDeviceProtection(InputData_WA.WACC_DeviceProtectionName);
		sfdc.shopWADevcs.validate_DPaddtoCartandSkipDpBothClickable();
		//Added DP to the cart
		sfdc.selectPro.clicknAddToCartBtnForDP();
		sf.seleU.waitTillLoading();
		sfdc.selectPro.select_Browse_Accessories();
		sfdc.shopWADevcs.validate_landingOnWirelessPlansOrAccepage("accessories page");
	}

	public void reachTillDPPageFirstDevice(String deviceBrand, String deviceModel) throws Exception {
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
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sf.seleU.waitTillLoading();
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
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();

	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
