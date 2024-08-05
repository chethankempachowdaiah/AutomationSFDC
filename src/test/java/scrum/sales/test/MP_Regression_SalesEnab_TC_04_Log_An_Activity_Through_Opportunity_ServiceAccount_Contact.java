package scrum.sales.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date : 7/Jan/2021
 * 
 * 
 *         ////-----////
 * 
 *         ////---MP Release Regression_Sales_TC18_Validate as Account Executive
 *         "Log an Activity" from contact page layout is able to create task
 *         successfully ---////
 * 
 *         1. Login as Account Executive 2. Validate that by default the app is
 *         selected as " R4B Sales Console". 3. Click "Contacts" >> View all 4.
 *         Select any Contact 5. Validate that on the Contact page of any
 *         Contact has "Log an Activity" button is present 6. Click on "Log an
 *         Activity" button 7. select the standard fields and make a note of the
 *         value selected under 'Subject field' 8. Click on Next 9. Select the
 *         Custom fields 10. click on Next 11. A Task has been created
 *         successfully' page will be appeared 12. Verify Task details recently
 *         created.
 *
 *         ////-----////
 * 
 * 
 *         ////---MP Release Regression_Sales_TC19_Validate as Account Executive
 *         "Log an Activity" from Opportunity page layout task creation is done
 *         successfully ---////
 * 
 *         1. Login as Account Executive 2. Validate that by default the app is
 *         selected as " R4B Sales Console". 3. Click "Opportunity" >> View all
 *         4. Select any Opportunity 5. Validate that on the Opportunity page of
 *         any Contact has "Log an Activity" button is present 6. Click on "Log
 *         an Activity" button 7. select the standard fields and make a note of
 *         the value selected under 'Subject field' 8. Click on Next 9. Select
 *         the Custom fields 10. click on Next 11. A Task has been created
 *         successfully' page will be appeared 12. Verify Task details recently
 *         created.
 * 
 *         ////-----////
 * 
 * 
 *         ////---MP Release Regression_Sales_TC20_Validate as Account Executive
 *         "Log an Activity" from Service Account page layout creation of tasks
 *         are done successfully. ---////
 * 
 *         1. Login as Account Executive 2. Validate that by default the app is
 *         selected as " R4B Sales Console". 3. Click "Accounts" >> View all 4.
 *         Select any Service Account 5. Validate that on the Service Account
 *         page "Log an Activity" button is present 6. Click on "Log an
 *         Activity" button 7. select the standard fields and make a note of the
 *         value selected under 'Subject field' 8. Click on Next 9. Select the
 *         Custom fields 10. click on Next 11. A Task has been created
 *         successfully' page will be appeared 12. Verify Task details recently
 *         created.
 * 
 *         ////-----////
 *         MPOSS: Sales–21PI03-Sprint 6 MPOSS-49543 - Log a Sales activity enhancements
 *         
 *         MPOSS-49543_TC_01 Verify As A AE User under Contacts>>Open Any Contact>>Log a sales activity>>Within the ""Subject"" drop-down menu on the first page ""Event Entertainment / Relationship Building"" option is present.
 *
 *			MPOSS-49543_TC_02 Verify As A AE User under Opportunity>>Open Any Opportunity>>Log a sales activity>>Within the ""Subject"" drop-down menu on the first page ""Event Entertainment / Relationship Building"" option is present.

 *			MPOSS-49543_TC_03 Verify As A AE User under Account>>Open Any Bus Account>>Log a sales activity>>Within the ""Subject"" drop-down menu on the first page ""Event Entertainment / Relationship Building"" option is present.

 *			MPOSS-49543_TC_04 Verify As A AE User under Lead>>Open Any Lead>>Log a sales activity>>Within the ""Subject"" drop-down menu on the first page ""Event Entertainment / Relationship Building"" option is present.

 *			MPOSS-49543_TC_05 Verify As A AE User under Contacts>>Open Any Contact>>Log a sales activity>>On the second page within the ""Solution Discussed"" drop-down menu ""General Business Discussion"" option is present.

 *			MPOSS-49543_TC_06 Verify As A AE User under Opportunity>>Open Any Opportunity>>Log a sales activity>>On the second page within the ""Solution Discussed"" drop-down menu ""General Business Discussion"" option is present.

 *			MPOSS-49543_TC_07 Verify As A AE User under Accounts>>Open Any Account>>Log a sales activity>>On the second page within the ""Solution Discussed"" drop-down menu ""General Business Discussion"" option is present.

 *			MPOSS-49543_TC_08 Verify As A AE User under Leads>>Open Any Leads>>Log a sales activity>>On the second page within the ""Solution Discussed"" drop-down menu ""General Business Discussion"" option is present.
 *         
 * 
 */

public class MP_Regression_SalesEnab_TC_04_Log_An_Activity_Through_Opportunity_ServiceAccount_Contact extends Base {

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 */
	@Test()
	public void test_logAnActivity() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();

		// Open contact from My contacts and Log An Activity through it
		sfdc.contacts.openFirstContactFromMyContacts();
		sfdc.conDetails.logAnActivity();
		sfdc.task.createTask();
		
		sfdc.taskDetails.verifyTaskDetails();
		sfdc.taskDetails.switchToMainWindow();
		sfdc.home.closeTabIfOpen();

		// Open any opportunity and Log An Activity through it
		sfdc.opp.verifyOpportunitiesObject();
		sfdc.opp.selectOpportunity(0);
		sfdc.oppDetails.logAnActivity();
		sfdc.task.createTask();
		sfdc.taskDetails.verifyTaskDetails();
		sfdc.taskDetails.switchToMainWindow();
		sfdc.home.closeTabIfOpen();

		// Open any service account and Log An Activity through it
		sfdc.accDetails.searchServiceAccountFromGlobalSearch();
		sfdc.accDetails.logAnActivity();
		sfdc.task.createTask();
		sfdc.taskDetails.verifyTaskDetails();
		sfdc.taskDetails.switchToMainWindow();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}
