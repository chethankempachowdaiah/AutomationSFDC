package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 08/Sep/2021
 * 
 * MPOSS-47606 Create New Contact and Lead Layouts
 * 
 *         MPOSS-47606_TC_01 Verify Guided Selling Fields under Contact Layout.
 *         
 *         MPOSS-47606_TC_02  Verify Guided Selling Fields under Lead Layout.
 *         
 *         MPOSS-47606_TC_03 Verify Lead Layout page should defaults to "Details" section  not Activities
 *         
 *         MPOSS-47606_TC_04 Verify Contact layout page should defaults to  "Details" sections not Activities
 *         
 *         MPOSS-47606_TC_05 Verify New Contact Page Layout   should show up on the Sales consoles E4
 *         
 *         MPOSS-47606_TC_06 Verify New Lead Page Layout  should show up on the Sales consoles E4
 *         
 *         MPOSS-47606_TC_07 Verify New Contact Page Layout   should show up on the Service consoles E4
 *          
 *         MPOSS-47606_TC_08 Verify New Lead Page Layout   should show up on the Service consoles E4
 *         
 *         MPOSS-47606_TC_09 Under Contact layout, Verify Add to Sequence Button under drop down and its clickable.
 *
 * 	       MPOSS-47606_TC_10 Under Lead layout,Verify Add to Sequence Button under drop down and its clickable.
 * 
 * 	  	   MPOSS-47606_TC_11  Hide "Guided Selling by ringDNA" app from all other profiles.
 * 
 * 	   	   MPOSS-47606_TC_12 After Selecting R4B Sales Console,Verify Guided Selling Fields under Contact Layout. 
 * 
 * 	  	   MPOSS-47606_TC_13  After Selecting R4B Sales Console,Verify Guided Selling Fields under Lead Layout. 
 * 
 *        MPOSS-47606_TC_14 After Selecting R4B Service Console,Verify Guided Selling Fields under Contact Layout. 
 * 
 * MPOSS-44072<Testing>Test the Install of Guided Selling for RingDNA
 * 
 * 			 * 
 * 	  	  MPOSS-44072_TC_4 Under Contact layout, Verify Add to Sequence Button under drop down and its clickable. (Sales Console)
 * 
 * 	  	  MPOSS-44072_TC_5 Under Lead layout,Verify Add to Sequence Button under drop down and its clickable. (Sales Console)
 * 
 * 	      MPOSS-44072_TC_6 Under Contact layout, Verify Add to Sequence Button under drop down and its clickable. (Service Console)
 * 
 * 	  	  MPOSS-44072_TC_07  verify  “Participant Actions” related list
 * 
 * 	  	  MPOSS-44072_TC_8 Under Contact layout, Verify Remove from Sequence Button under drop down and its clickable.
 * 
 * 	  	  MPOSS-44072_TC_09 Under Lead layout,Verify ARemove from Sequence Button under drop down and its clickable.
 * 
 * 	  	  MPOSS-44072_TC_10 Verify NIS user will be able to See a list of Guided Selling users who are mapped to a ringDNA admin console user.
 * 
 * 	  	  MPOSS-44072_TC_11 Verify NIS user will be able to See Hit Grant All Permissions will assign necessary standard object permissions to each user.
 * 
 * 
 * MPOSS-52936- Ring DNA fields on page layouts and Task Status Values
 * 
 * 		MPOSS-52936_TC_01 Verify As A NIS Profile RingDNA Call Detail Record fields under contact layout 
 * 
 * 		MPOSS-52936_TC_02 Verify As A NIS Profile RingDNA Call Detail Record fields under Lead layout .
 * 
 * 		MPOSS-52936_TC_03 Verify As A NIS Profile Comments fields under log a sales.
 * 
 * 		MPOSS-52936_TC_04 Verify As A NIS Profile Status dropdown fields under log a sales.
 *    
 */
public class MP_21PI03_SP05_MPOSS_47606_Create_New_Contact_and_Lead_Layouts_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 * MPOSS: Sales–21PI03-Sprint 5         
	 *                                     
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_Create_New_Contact_and_Lead_Layouts_For_NIS() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		//Negative Scenario- Verify ringDNA App is disable for AE.
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openGuidedSellingByRingDNAAE();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Contact.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openGuidedSellingByRingDNA();
		sfdc.gsringDNA.selectContactGS();
		//Open any contact and validate scenarios 
		sfdc.gsringDNA.validateGuidedSellingFieldsUnderContact();
		sfdc.home.logout();
		
		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Contact.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.openGuidedSellingByRingDNA();
		// validate scenarios 		
		sfdc.gsringDNA.verifyParticipantActions();
		sfdc.home.logout();

		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Contact.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.openGuidedSellingByRingDNA();
		//Open any contact and validate scenarios 
		sfdc.gsringDNA.verifyListofGuidedSellingUsers();
		sfdc.home.logout();
		
		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Lead.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.openGuidedSellingByRingDNA();
		sfdc.gsringDNA.selectLeadGS();
		//Open any Lead and validate scenarios 
		sfdc.gsringDNA.validateGuidedSellingFieldsUnderLead();
		sfdc.home.logout(); 
		
		
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sfdc.home.openTasks();
		//validation of comment field under Log a sales call
		sfdc.gsringDNA.tasksCommnentsValidation();
		//validation of status drop down values
		sfdc.gsringDNA.tasksLogSalesActivity();
		
		sfdc.home.logout();
		
		
		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Sales Console Contact.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();
		sfdc.gsringDNA.selectContactTab();
		sfdc.gsringDNA.selectContactGS();
		//Open any contact and validate scenarios 
		sfdc.gsringDNA.validateGuidedSellingFieldsUnderContactDet();
		sfdc.home.logout();
		
		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Sales Console Lead.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BSalesConsole();
		sfdc.gsringDNA.selectLeadTab();
		sfdc.gsringDNA.selectLeadGS();
		//Open any Lead and validate scenarios 
		sfdc.gsringDNA.validateGuidedSellingFieldsUnderLead();
		sfdc.home.logout();
		
		//Positive Scenario- Verify ringDNA App is enable for NIS and New Layout fields Verification under Service Console Contact.
		sfdc.login.loginToSFDC(InputData_Sales.Profile_NIS);
		sf.seleU.refreshPage();
		sfdc.home.closeTabIfOpen();
		sfdc.gsringDNA.openServiceConsole();
		sfdc.gsringDNA.selectContactTab();
		sfdc.gsringDNA.selectContactGSforService();
		//Open any contact and validate scenarios 
		sfdc.gsringDNA.validateGuidedSellingFieldsUnderContact();
		sfdc.home.logout();
		
		softassert.assertAll();

	}

}
