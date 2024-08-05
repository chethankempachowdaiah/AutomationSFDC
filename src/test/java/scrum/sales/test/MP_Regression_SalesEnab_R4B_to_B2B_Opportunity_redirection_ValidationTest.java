package scrum.sales.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.BaseBrowser;
import com.framework.base.BaseDataProvider;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date: 07/Dec/2021
 *
MPOSS-54000 TC 01 AE validate on Busines Account If the ""Sales Segment"" type is the Public Sector  then click the ""New Opportunity""user should be redirected to B2B org.
MPOSS-54000 TC 02 AE validate on Busines Account If the ""Sales Segment"" type is the  Federal then click the ""New Opportunity""user should be redirected to B2B org.
MPOSS-54000 TC 03 AE validate on Busines Account If the ""Sales Segment"" type is the wholesale then click the ""New Opportunity""user should be redirected to B2B org.
POSS-54000 TC 04 AE validate on Busines Account AND IF the "Sales Segment" type is Corporate then continue with Opportunity creation flow.
MPOSS-54000 TC 05 AE validate on Busines Account AND IF the "Sales Segment" type is Commercial then continue with Opportunity creation flow.
MPOSS-54000 TC 06 AE validate on Busines Account AND IF the "Sales Segment" type is , SMB then continue with Opportunity creation flow.
MPOSS-54000 TC 07 AE validate on Busines Account AND IF the "Sales Segment" type is , Small then continue with Opportunity creation flow.

 * 
 * 
 */
	public class MP_Regression_SalesEnab_R4B_to_B2B_Opportunity_redirection_ValidationTest extends BaseDataProvider {

		/**
		 * @throws Exception
		 */
		@Test(dataProvider = "SMBData")
		public void test_R4B_to_B2B_Opportunity_redirection(Hashtable<String, String> dataTable) throws Exception {
		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Sales.SMBData(dataTable);
	
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		// Open Account with sales segment as per data
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);  
	    sfdc.accDetails.verifySalesSegments();
		sfdc.accRelated.createOpportunity();
		sfdc.accDetails.verifyOpportunityRedirection();
		sfdc.home.closeTabIfOpen();
		//sf.seleU.openURL("https://r4b--itqatest.my.salesforce.com/");
		sfdc.home.logout();
		
		//Commented below code as we are running regression pack for AE Profile only 
		
		// *********Login as NIS*****//
/*		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		// Open Account with sales segment as per data
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);  
	    sfdc.accDetails.verifySalesSegments();
		sfdc.accRelated.createOpportunity();
		sfdc.accDetails.verifyOpportunityRedirection();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		
		//-----Login as Business Admin------
		//intializeChrome(false);
		//sfdc = new SFDC_AllPages();
		sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		// Open Account with sales segment as per data
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);  
	    sfdc.accDetails.verifySalesSegments();
		sfdc.accRelated.createOpportunity();
		sfdc.accDetails.verifyOpportunityRedirection();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//--------Login as Data Governance----
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);  
	    //sfdc.accDetails.verifySalesSegments();
		sfdc.accRelated.createOpportunityThrDGProfile();
		sfdc.accDetails.verifyOpportunityRedirection();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	*/	
		softassert.assertAll();
	}
		
		/**
		 * @return
		 * @throws IOException
		 * 
		 *                     Data Provider to fetch multiple set of data and assign
		 *                     them to 2D Object Array
		 */
		@DataProvider
		public Object[][] SMBData() throws IOException {
			return getDataSetsRunMode(InputData_Sales.SMB_Rules_Test_Data, InputData_Sales.smbData);
		}
}

