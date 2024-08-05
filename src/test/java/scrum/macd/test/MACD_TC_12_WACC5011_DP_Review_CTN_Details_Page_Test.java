package scrum.macd.test;

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

public class MACD_TC_12_WACC5011_DP_Review_CTN_Details_Page_Test extends MyListener {

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;

	/**Author@: Pankaj Agarwal
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                            ==>  Test Case is for US WACC-5011  <==
	 * User should be able to see the following fields in the box beside the device protection:
       Action ("Removing"), Effective date (date chosen in flow, with calendar icon), Total
       The total field should be monthly for device protection (e.g., -$15.00/mo).
       On clicking the cancel changes, The device protection is removed from this box.
	 *                              
	 *                             
//	 */
	@Test(dataProvider = "getDataPlan")
	public void validate_Removing_DeviceProtection_In_ChangesToWirelessAddOns(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965796451", "");
		sfdc.reviewWALine.validate_Product("20GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_DeviceProduct_InWirelessAddOnSection("Device Protection");
		sfdc.reviewWALine.select_Remove_addonsBtn();
		sfdc.macdRemoveAddOn.validate_DPsectionPresent();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "15.99");
		String[] removeProduct = {"Device Protection", "Unlimited Canada to US/Intl SMS/MMS"};
		sfdc.macdRemoveAddOn.selectMultipleAddOnAndClickRemove(removeProduct);
		sfdc.macdSelAddon.validate_ExtraDPInPlan("Device Protection");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
		sfdc.macdSelAddon.selectEffectiveDate("Unlimited Canada to US/Intl SMS/MMS","Today:","");
		sfdc.macdSelAddon.selectEffectiveDate("Device Protection","Choose a date","2022/04/11");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		String productName[] = {"Device Protection"}; String action[] = {"Removing"}; String price[] = {"15.99"}; String change = "Cancel change";
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, change);	
	}
	
	@Test(dataProvider = "getDataPlan")
	public void validate_Removing_DeviceProtection_In_ChangesToWirelessAddOns1(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);

		reachTillReviewWirelessLine("965796451", "");
		sfdc.reviewWALine.validate_Product("20GB Pooled");
		sfdc.reviewWALine.validate_Product("iPhone 11");
		sfdc.reviewWALine.validate_DeviceProduct_InWirelessAddOnSection("Device Protection");
		sfdc.reviewWALine.select_Remove_addonsBtn();
		sfdc.macdRemoveAddOn.validate_DPsectionPresent();
		sfdc.macdRemoveAddOn.validateAddOnPresent("Device Protection", "15.99");
		String[] removeProduct = {"Device Protection"};
		sfdc.macdRemoveAddOn.selectMultipleAddOnAndClickRemove(removeProduct);
		sfdc.macdSelAddon.validate_ExtraDPInPlan("Device Protection");
		sfdc.macdRemoveAddOn.validate_EffectiveDatePagePresent();
	//	sfdc.macdSelAddon.selectEffectiveDate("Unlimited Canada to US/Intl SMS/MMS","Today:","");
		sfdc.macdSelAddon.selectEffectiveDate("Device Protection","Choose a date","2022/04/18");
		sfdc.macdRemoveAddOn.select_ConfirmEffectiveDateButton();
		sfdc.reviewWALine.validateSuccessfullAddOnChangesMessage();
		String productName[] = {"Device Protection"}; String action[] = {"Removing"}; String price[] = {"15.99"}; String change = "Cancel change";
		sfdc.reviewWALine.validate_ChangesToWirelessAddOns(productName, action, price, change);	
	}
	
	public void reachTillReviewWirelessLine(String billingAcc, String phoneNum) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount(billingAcc);
		sfdc.accManagement.select_ManageAccountButton("Skip");
		sfdc.accManagement.select_AccManagementOption("Add-On");
		sfdc.accManagement.click_SelectNumberButton(phoneNum);
	}

	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	}
}
