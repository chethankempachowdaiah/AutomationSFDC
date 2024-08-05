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

public class MACD_TC_22_WACC5100_5938_DisplayInitialCTNBlockSummary_CheckExistingBlocks_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	/**Author@: Priyanka Tawade
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                ==>  Test Case is for US WACC-5100 , WACC-5938 <==
	 * 1. Select the CTN with existing blocks               
	 * User should able to see Initial CTN Block Summary (Block Type, Block Reason, Block Reason)
	 * on Wireless Line page.
	 * User should able to see Remove Block button on Wireless Line page.  
	 * 2. Select the CTN without existing blocks
	 * User should lands on the screen to add blocks (WACC-5101)                           
	 */      
	@Test(dataProvider = "getDataPlan")
	public void verify_ServiceBlockDetails_RemoveBlock(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillAccManagementPage();
		sfdc.accManagement.searchCTNUsingLastThreeDigit("897");
		sfdc.accManagement.click_SelectNumberButton("4162194897");
		sfdc.reviewWALine.verify_InitialCTNBlockSummary_RemoveBlock("Block Telephone Number","Stolen","May 5th 2022");
	}
	
	@Test(dataProvider = "getDataPlan")
	public void validate_CTNWithoutExistingBlocks(Hashtable<String, String> dataTable) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		test = reports.createTest(this.getClass().getName());
		InputData_WA.setDataForWACCProducts(dataTable);	
		
		reachTillAccManagementPage();
	//	sfdc.accManagement.searchCTNUsingLastThreeDigit("898");
		sfdc.accManagement.click_SelectNumberButton("4162194898");
		sfdc.macdblkservice.verify_BlockCTN_Checkbox_Des();
		
	}
	
	//Method is for common navigation for all test cases
	public void reachTillAccManagementPage() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		
		// ***************LOGIN AS Service***********************//
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();
		sfdc.accManagement.selectBillingAccount("967030800");
		sfdc.accManagement.select_ManageAccButton();
		//sfdc.accManagement.select_MACDButton();
		//sfdc.accManagement.verify_MACDFlows();
		sfdc.accManagement.select_AccManagementOption("Service Blocks");
		
    }	
	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	} 			
}
