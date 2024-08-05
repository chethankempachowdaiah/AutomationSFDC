package scrum.sales.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 27/Aug/2021
 * 
 *         MP Release Regression_Sales_TC28_Validate the details on Check serviceability Page
 *        
 *		  TC_01 Validate that  by default all the filters will be blank (As per New US the filters will not be blank) and "Access type group" will have "Select Access Type Group" value displayed.
 
 *		  TC_02 Validate that  "Add Serviceable Location" button is present.
 
 *		  TC_03 Validate that Existing serviceable locations w.r.t Postal code of the service account ( if present in database ) will be displayed.
 
 *		  TC_04 Validate that Pagination buttons ( Next and Previous ) are present.
 
 *		  TC_05 Validate that "Previous" and "Next" button for Check Serviceability page are present. 
 * 
 *        MP Release Regression_Sales_TC30_Validate user is able to add  Existing serviceable location to Premise for existing service account.
 */
public class MP_Release_Regression_Sales_TC28_Validate_the_details_on_Check_serviceability_page_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 *                 1. User is able to login successfully
	 *					2.R4B sales Console app is selected as default
	 *					3.User is able to Click on New button on account page
	 *					4.Below account type is visible for selection
	 *					-Business 
	 *					-Service 
	 *					-Billing 
	 *					5.Able to select service account and able to hit on next 
	 *					6.Redirected to "Select Service Accounts" page.
	 *					7.User is able to chose parent account which is mandatory field.
	 *					8.User is able to copy business address
	 *					9.User is able to enter Service account name and able to click on Hit
	 *					10.Service Account confirmation page is  displayed. and able to Hit "Next"
	 *					11. Account Executive Service account is created , user is able to see page  for "Check Serviceability" page
	 *					12.Following values are populated by default
	 *					a) by default all the filters are blank and "Access type group"  have "Select Access Type Group" 
	 *					b) "Add Serviceable Location" button is present
	 *					c) Existing serviceable locations w.r.t Postal code of the service account ( if present in database ) will be displayed.
	 *					c) Pagination buttons ( Next and Previous )
	 *					d) "Previous" and "Next" is also visible  for Check Serviceability page. 
	 * 
	 *                   20. 12. Validate that 
	 *					a) by default all the filters will be blank and "Access type group" will have "Select Access Type Group" value displayed.
	 *					b) "Add Serviceable Location" button .
	 *					c) Existing serviceable locations w.r.t Postal code of the service account ( if present in database ) will be displayed.
	 *					c) Pagination buttons ( Next and Previous )*
	 *					d) "Previous" and "Next" button for Check Serviceability page. 
	 * 
	 */
	@Test
	public void test_ValidateDetailsOnServiceabilityPage() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		/*Validate the Check Serviceability Button Exist on Service Account >>Premise*/
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.selectAccountTab();
		sfdc.acc.selectAnyServiceAccount();
		sfdc.accDetails.verifyCheckServiceabilityButtonExixt();
		sfdc.accDetails.addExistingServiceableLocationToPremise();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	
		/*TC28_Validate the details on Check serviceability Page*/
/*		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sf.seleU.refreshPage();
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewServiceAccount();
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.verifyServiceAccountCreated();
		sfdc.csa.newEnterServiceAccInfo(1);
		sfdc.csa.verifyAddServiceableLocationButtonExist();
		sfdc.csa.verifyAccessTypeExist();
		sfdc.csa.verifPaginationPreviousButtonExist();
		sfdc.csa.verifyPaginationNextButtonExist();
		sfdc.csa.verifyPreviousButtonExist();
		sfdc.csa.verifyNextButtonExist();
		sfdc.csa.checkServicabilityAddServiceLocationsClickNext();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();*/
		softassert.assertAll();

	}
}

