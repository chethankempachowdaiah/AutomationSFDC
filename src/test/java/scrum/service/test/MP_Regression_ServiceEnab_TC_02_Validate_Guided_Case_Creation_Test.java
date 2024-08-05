package scrum.service.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 */
public class MP_Regression_ServiceEnab_TC_02_Validate_Guided_Case_Creation_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                              ////MP Release
	 *                              Regression_Service_TC24_Validation of guided
	 *                              case creation on billing account - wireless.////
	 * 
	 *                              1. Open a billing account- Wireless.
	 * 
	 *                              2. Click on the "New Customer Case" button.
	 * 
	 *                              3. Fill in the case type, case origin, product
	 *                              family, subject and description. Select wireless
	 *                              or business phone as product.
	 *
	 *                              4. Search for the subscription and search for
	 *                              contact
	 *
	 *                              5. Check on create new contact and enter contact
	 *                              information.
	 * 
	 *                              6. Validate case is created.
	 *
	 *
	 *                              ////MP Release
	 *                              Regression_Service_TC25_Validation of guided
	 *                              case creation on Business account- Wireline.
	 *
	 *                              1. Open a Business account- Wireless.
	 *
	 *                              2. Click on the "New Customer Case" button.
	 *
	 *                              3. Fill in the case type, case origin, product
	 *                              family, subject and description. Select wireline
	 *                              as product.
	 *
	 *                              4. Validate business account and search for
	 *                              Service account.
	 *
	 *                              5. Select one service account and validate
	 *                              subscription, billing account, service account,
	 *                              business account. Select one contact.
	 *
	 *                              6. Check on create new contact and enter contact
	 *                              information.
	 *
	 *                              7. Validate case is created.
	 *
	 *                              ////MP Release
	 *                              Regression_Service_TC26_Validation of guided
	 *                              case creation on Subscription- Wireless.
	 *
	 *                              1. Open a subscription.
	 *
	 *                              2. Click on the "New Customer Case" button.
	 *
	 *                              3. Fill in the case type, case origin, product
	 *                              family, subject and description. Select wireless
	 *                              or business phone as product.
	 *
	 *                              4. Validate subscription, billing account,
	 *                              business account and contact
	 *
	 *                              5. Check on create new contact and enter contact
	 *                              information.
	 *
	 *                              6. Validate case is created.
	 *
	 *                              ////MP Release
	 *                              Regression_Service_TC27_Validation of guided
	 *                              case creation on Subscription- Wireline.
	 *
	 *                              1. Open a subscription.
	 *
	 *                              2. Click on the "New Customer Case" button.
	 *
	 *                              3. Fill in the case type, case origin, product
	 *                              family, subject and description. Select
	 *                              wireline(Internet) as product.
	 *
	 *                              4. Validate subscription, billing account,
	 *                              business account and contact
	 *
	 *                              5. Check on create new contact and enter contact
	 *                              information.
	 *
	 *                              6. Validate case is created.
	 *
	 *                              ////MP Release
	 *                              Regression_Service_TC28_Validation of guided
	 *                              case creation on service contact - wireless.
	 *
	 *                              1. Open Service contact.
	 *
	 *                              2. Click on the "New Customer Case" button.
	 *
	 *                              3. Select wireless product for product family
	 *                              and Next.
	 *
	 *                              4. Skip searching for business account and next.
	 *                              Search for billing account and check "Load
	 *                              Subscription". Search for subscription.
	 *
	 *                              5. Select one subscription and validate case
	 *                              details.
	 *
	 *                              6. Next and validate case details.
	 * 
	 *                              ///////MP Release
	 *                              Regression_Service_TC29_Validate the wireless
	 *                              guided case creation flow from cases panel_R4B
	 *                              Sales Console
	 * 
	 *                              1. AE log in and on a business account, go to
	 *                              overview and then case panel and click on create
	 *                              customer case button on the case card
	 * 
	 *                              2.Fill in the case type, case origin, product
	 *                              family, subject and description. Select wireless
	 *                              for product.
	 * 
	 *                              3. Validate business account information and
	 *                              search for a billing account
	 * 
	 *                              4. Search for subscription and contact
	 * 
	 *                              5. Validate case is created
	 * 
	 * @throws InterruptedException
	 */
	@Test(groups= {"regression"})
	public void test_Verify_Guided_Case_Creation_And_Details() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ****LOGIN To SFDC and Close all tabs*******//
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		// ****Select a billing account which has subscription related to it*******//
		 sfdc.subscription.listAllSubscriptions();
		 sfdc.subscription.openBillingAccountFromSubscriptionList();   

		// Create New Wireless Customer Case  from Billing Account and Verify details once created
	    sfdc.accDetails.searchAccountFromGlobalSearch(InputData.billingAccountWithSubscription, "Billing");
		sfdc.customerCase.createWirelessCustomerCaseFromBillingAcc();
		sfdc.caseDetails.validate_BillingOrBusAccountCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();
		
		// Create New Business Phone Customer Case  from Billing Account and Verify details once created
		sfdc.home.closeTabIfOpen();
	    sfdc.accDetails.searchAccountFromGlobalSearch(InputData.billingAccountWithSubscription, "Billing");
		sfdc.customerCase.createBusinessPhoneCustomerCaseFromBillingAcc();
		sfdc.caseDetails.validate_BillingOrBusAccountCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();
		
		//Create New Wireline Customer Case from Business Account and verify details once created
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(InputData.relatedBusinessAccountForCusCase);
	    sfdc.customerCase.createWirelineCustomerCaseFromBusinessAcc();
		sfdc.caseDetails.validate_BillingOrBusAccountCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();

		// Create New Wireless Customer Case from subscription
		sfdc.home.closeTabIfOpen();
//		sfdc.subscription.listAllSubscriptions();
		sfdc.subscription.searchSubscriptionGlobalSearch(InputData.subscriptionName);
//		sfdc.subscription.searchAndSelectSubscription();
		sfdc.customerCase.createWirelessCustomerCaseFromSubscription();
		sfdc.caseDetails.validate_SubscriptionCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();

		// Create New Wireline Customer Case from subscription
		sfdc.home.closeTabIfOpen();
//		sfdc.subscription.listAllSubscriptions();
		sfdc.subscription.searchSubscriptionGlobalSearch(InputData.subscriptionName);
//		sfdc.subscription.searchAndSelectSubscription();
	    sfdc.customerCase.createWirelineCustomerCaseFromSubscription();
		sfdc.caseDetails.validate_SubscriptionCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();

		// Create Wireless Customer case from Service Contact
		sfdc.home.closeTabIfOpen();
		sfdc.contacts.searchContactFromGlobalSearch(InputData.contactFullName);
		sfdc.conDetails.verifyContactDetailsPageHeader();
		sfdc.customerCase.createWirelessCustomerCaseFromServiceContact();
		sfdc.caseDetails.validate_ServiceContactCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();

		// Create New Wireless Customer Case from Business Account and verify details once created
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(InputData.relatedBusinessAccountForCusCase);
		sfdc.customerCase.createWirelessCustomerCaseFromBusinessAccCasePanel();
		sfdc.caseDetails.validate_BillingOrBusAccountCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();
			
		// Create New Internet & Advanced Network Case  from Service Account and Verify details once created
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobalSearch(InputData.relatedBusinessAccountForCusCase, "Service");
		sfdc.customerCase.createInternetandAdvancedNetworkCaseFromServiceAcc();
		sfdc.caseDetails.validate_BillingOrBusAccountCustomerCaseDetails();
		sfdc.home.closeTabIfOpen();						
	    sfdc.home.logout();
		softassert.assertAll();		
	}
}