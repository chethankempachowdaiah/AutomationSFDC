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

public class MACD_TC_21_WACC4441_AddBlock_OrderConfirmationScreen_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	/**Author@: Priyanka Tawade
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                ==>  Test Case is for US WACC-4441  <==
	 * User should able to see the Order Confirmation Screen,Choose Another Number button 
	 * and Go To Manage Account button.
	 * Click “Choose Another Number”. After clicking Choose Another Number user lands on CTN Selection Screen.
	 * Click "Go To Manage Account" . After clicking Go To Manage Account user lands on Account Management Screen.                             
	 */      
	//Test Case is for US  WACC-4441
		@Test(dataProvider = "getDataPlan")
		public void verify_OrderConfirmationScreen_ChooseAnotherNo(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillAccManagementPage();
			sfdc.accManagement.click_SelectNumberButton("4162194898");
			
			// Verify Block CTN Checkbox and description in step 1 
			sfdc.macdblkservice.verify_BlockCTN_Checkbox_Des();
			sfdc.macdblkservice.click_Continue();
			
			//Select block reasons in step 2
			sfdc.macdblkservice.validate_BlockReason("Lost");
			
			//Select Block Effective Date in step 3
			//sfdc.macdblkservice.selectBlockEffectiveDate("Today:");
			//sfdc.macdblkservice.selectEffectiveDateFromCalendar("5 May");
			sfdc.macdblkservice.click_ConfirmBlockDate();
			
			sfdc.macdReviewSumScreen.verify_UserName_CTN("P Tawade","4162194898");
			sfdc.macdReviewSumScreen.verify_pendingBlksOnThisLine("Block Telephone Number","Lost","May 10th 2022");
			
			// Click proceed on Review order summary page
			sfdc.macdReviewSumScreen.click_Proceed();
			
			sfdc.macdReviewSumScreen.verify_OrderConfirmationScreenWithTwoButtons("Choose another number","Go to manage account");
		
		    sfdc.macdReviewSumScreen.click_ChooseAnotherNoBtn();
		}	
		
		//Test Case is for US  WACC-4441
		@Test(dataProvider = "getDataPlan")
		public void verify_OrderConfirmationScreen_GoToManageAcc(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillAccManagementPage();
			sfdc.accManagement.click_SelectNumberButton("4162194898");
			
			// Verify Block CTN Checkbox and description in step 1 
			sfdc.macdblkservice.verify_BlockCTN_Checkbox_Des();
			sfdc.macdblkservice.click_Continue();
			
			//Select block reasons in step 2
			sfdc.macdblkservice.validate_BlockReason("Lost");
			
			//Select Block Effective Date in step 3
			//sfdc.macdblkservice.selectBlockEffectiveDate("Today:");
			//sfdc.macdblkservice.selectEffectiveDateFromCalendar("5 May");
			sfdc.macdblkservice.click_ConfirmBlockDate();
			
			sfdc.macdReviewSumScreen.verify_UserName_CTN("P Tawade","4162194898");
			sfdc.macdReviewSumScreen.verify_pendingBlksOnThisLine("Block Telephone Number","Lost","May 10th 2022");
			
			// Click proceed on Review order summary page
			sfdc.macdReviewSumScreen.click_Proceed();
			
			sfdc.macdReviewSumScreen.verify_OrderConfirmationScreenWithTwoButtons("Choose another number","Go to manage account");
		
		    sfdc.macdReviewSumScreen.click_GoToManageAccBtn();
		}			
		//Method is for common navigation for all test cases
		public void reachTillAccManagementPage() throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			
			// ***************LOGIN AS Service***********************//
			sfdc.login.loginToSFDC(InputData.Profile_Service);
			sfdc.home.closeTabIfOpen();
			sfdc.accManagement.selectBillingAccount("967030800");
			sfdc.accManagement.select_ManageAccountButton("Skip");
			//sfdc.accManagement.verify_MACDFlows();
			sfdc.accManagement.select_AccManagementOption("Service Blocks");
			
	    }	
		
		@DataProvider
		public Object[][] getDataPlan() throws IOException {
			return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
		} 	
}
