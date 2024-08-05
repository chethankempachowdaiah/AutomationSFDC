package scrum.sales.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * 
 *
 *  Territory Management_MPOSS-45166  
 */
public class MPOSS_21PI03_SP_6_Create_Unassigned_Account_for_Territories_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                                               
	 * @throws InterruptedException
	 * 
	 */
	@Test(dataProvider = "getAccountDetails")
	public void test_Create_Accounts_for_Territories(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);
       
        sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedUser);   
		sfdc.home.openR4BSalesConsole();
		System.out.println(Global.dataInput.territories);
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		
		//Global.saveTestData(InputData_Sales.SMB_Rules_Test_Data, InputData_Sales.resultsSheet , "Accounts_Created", Global.dataInput.businessAccountName, InputData_Communities.commCaseSlNo);
		sfdc.home.closeTabIfOpen(); 
		sfdc.home.logout();
		 
		//sfdc.accDetails.changeOwner(); //...Descoped as this is no longer useful
		//sfdc.csa.clickOnNextInCreateServiceAccountWithoutChkServicibility(); 
			
		sfdc.login.loginToSFDC(InputData_Sales.Profile_SBEUnassigned);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedWholesale);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();   
			
		sfdc.login.loginToSFDC(InputData_Sales.Profile_PendingUnassigned);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
			
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedBC);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
						
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedABMW);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();   
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedAT);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedON);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();  
		
	
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedGTA);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		System.out.println(Global.dataInput.territories);
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_UnassignedQC);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccount();
		sfdc.cba.enterBusinessAccountInfo(true);
		sfdc.cba.verifyBusinessAccountCreatedWithoutReview();
		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.verifyMarkPrimaryContactReadOnly();
		sfdc.cc.verifyContactCreated();
		sfdc.csa.noBillingAccountClickOnNext();
		sfdc.accDetails.VerifyTerritories();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
			
		softassert.assertAll();

	}
	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		Global.dataInput.numberOfEmployees  = dataTable.get("CBA_Number of Employees");
		Global.dataInput.address = dataTable.get("Address");
		Global.dataInput.addressStreet = dataTable.get("Address Street");
		Global.dataInput.addressCity = dataTable.get("Address City");
		Global.dataInput.addressState = dataTable.get("Address State");
	//	Global.dataInput.addressCountry = dataTable.get("Address Country");
		Global.dataInput.addressPostalCode = dataTable.get("Address Postal Code");
		Global.dataInput.territories = dataTable.get("Territories");
		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}
	@DataProvider
	
	public Object[][] getAccountDetails() throws IOException {
		
		return getDataSetsRunMode(InputData_Sales.SMB_Rules_Test_Data, InputData_Sales.unassignedQCSheet);
	}
	
	
}

