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

public class MACD_TC_18_WACC5101_SelectBlockType_Test extends MyListener{

	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	
	/**Author@: Priyanka Tawade
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                ==>  Test Case is for US WACC-5101  <==
	 * User should able to see the option for Block CTN with a checkbox and a short 
	 * description in the block service page.
	 * User should able to select the block Type in the available blocks,For MVP the
	 * only option is block CTN.
	 * click continue to jump into step 2.
	 *                              
	 */      
	//Test Case is for US  WACC-5101
		@Test(dataProvider = "getDataPlan")
		public void verify_BlockType(Hashtable<String, String> dataTable) throws Exception {
			sfdc = new SFDC_AllPages();
			sf = new SFDC_AllPageObjects();
			test = reports.createTest(this.getClass().getName());
			InputData_WA.setDataForWACCProducts(dataTable);	
			
			reachTillAccManagementPage();
			sfdc.accManagement.click_SelectNumberButton("4162194898");
			sfdc.macdblkservice.verify_BlockCTN_Checkbox_Des();
			sfdc.macdblkservice.click_Continue();
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
			sfdc.accManagement.select_AccManagementOption("Service Blocks");
			//sfdc.accManagement.click_SelectNumberButton(phoneNum);

	    }	
	@DataProvider
	public Object[][] getDataPlan() throws IOException {
		return getDataSetsRunMode(Constants.WA_TESTDATA_FILE, Constants.WIRELESS_PLANS_SELECTION_SHEET);
	} 	
}
