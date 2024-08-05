package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 16/Apr/2021
 * 
 *         MPOSS-36366_TC01_Validate that  on Business Account Page Layout "Log a Sales Activity" is present in the Highlight Panel.
 *         MPOSS-36366_TC02_Validate that  on Service Account Page Layout "Log a Sales Activity" is present in the Highlight Panel.
 *         MPOSS-36366_TC03_Validate that  on Billing Account Page Layout "Log a Sales Activity" is present in the Highlight Panel.
 *         MPOSS-36366_TC04_Validate that  on Business Account Page Layout hitting  "Log a Sales Activity" button , details of "Type" field.
 *         MPOSS-36366_TC05_Validate that  on Business Account Page Layout hitting  "Log a Sales Activity" button , details of "Interaction Type" field.
 *         MPOSS-36366_TC06_Validate that  on Business Account Page Layout hitting  "Log a Sales Activity" button , details of "Subject " field.
 *         MPOSS-36366_TC07_Validate that  on Business Account Page Layout hitting  "Log a Sales Activity" button , details of "Solution Discussed " field.
 *         MPOSS-36366_TC08_Validate that  on Business Account Page Layout hitting  "Log a Sales Activity" button , once it is saved , Notes  section is displayed under Related tab.
 *         MPOSS-36366_TC09_Validate that  as AE , user can "Log a sales Activity" from "Lead" record
 *			
 *		   TC001_MPOSS-33403_AE Validate  as an AE user I should be able to create Tasks for Leads using the "Log a Sales Activity" button.
 *		   TC002_MPOSS-33403_AE Validate  as an AE user I should be able to create Tasks for Contact using the "Log a Sales Activity" button.
 *		   TC003_MPOSS-33403_AE Validate  as an AE user I should be able to create Tasks for Opportunity using the "Log a Sales Activity" button.
 *		   TC004_MPOSS-33403_AE Validate  as an AE user I should be able to create Tasks for Service Account using the "Log a Sales Activity" button.
 *		   TC005_MPOSS-33403_AE Validate  as an AE user I should be able to create Event for Leads using the "Log a Sales Activity" button.
 *		   TC006_MPOSS-33403_AE Validate  as an AE user I should be able to create Event for Contact using the "Log a Sales Activity" button.
 *		   TC007_MPOSS-33403_AE Validate  as an AE user I should be able to create Event for Opportunity using the "Log a Sales Activity" button.
 *		   TC008_MPOSS-33403_AE Validate  as an AE user I should be able to create Event for Service Account using the "Log a Sales Activity" button.
 * 
 */
public class Pl02_SP1_MPOSS_36366_Log_A_Sales_Activity_Campaign_Leads_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                                               
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_logANActivity_Accounts_Leads() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		
		//Open any Service Account
		sfdc.accDetails.searchAccountFromGlobalSearch(sf.dataInput.serviceAccountNameForLogAnActivity, sf.dataInput.acc_RecordType_Service);
		
		//Validate Log a sales Activity button
		sfdc.accDetails.validateLogSalesActivityButton();
		sfdc.home.closeTabIfOpen();
		
		//Open any Billing Account
		sfdc.accDetails.searchAccountFromGlobalSearch(sf.dataInput.billingAccountNameForLogAnActivity, sf.dataInput.acc_RecordType_Billing);
		
		//Validate Log a sales Activity button
		sfdc.accDetails.validateLogSalesActivityButton();
		sfdc.home.closeTabIfOpen();
		
		//Open Any Business Account
		sfdc.accDetails.searchAccountFromGlobalSearch(sf.dataInput.relatedBusinessAccountForCusCase, sf.dataInput.acc_RecordType_Business);
		
		//Validate Log a sales Activity button
		sfdc.accDetails.validateLogSalesActivityButton();
				
		//Log an Activity through Business Account and verify
		sfdc.accDetails.logAnActivity();
		
		//Validate Mandatory Fields
		sfdc.task.validateMandatoryFieldsForTaskFormPg1();
		
		//Validate DropdownValues For All Fields
		sfdc.task.validateDropdownValuesTaskFormPg1();
		
		//Validate Help Text for Type Field
		//sfdc.task.validateHelpTextForTypeField();
		
		//Validate DropdownValues For All Fields
		sfdc.task.validateDropdownValuesTaskFormPg2();
		
		//Create Task and verify
		sfdc.task.createTask();
		sfdc.taskDetails.verifyTaskDetails();
				
		//Verify Notes Section
		sfdc.taskRelated.verifyNotesSection();
		sfdc.taskRelated.createAndVerifyNote();
		sfdc.taskDetails.switchToMainWindow();
		sfdc.home.closeTabIfOpen();

		//Create Lead (ITDEVSTAGE)    PI22_01_SP04
		sfdc.lead.createLead();  
		sfdc.accDetails.logAnActivity();
		sfdc.task.createTask();
		sfdc.taskDetails.verifyTaskDetails();
		sfdc.home.closeTabIfOpen();		
		sfdc.home.logout();
		
		softassert.assertAll();

	}

}
