package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date : 15/Sep/2020
 * 
 *         Test verifies titles of Subscriptions list/table
 * 
 * 
 *         Verifies global search is listing subscriptions or not
 * 
 *         Verifies titles of subscription list displayed after global search
 *
 *
 *         Test opens Related List billing subscriptions
 * 
 *         Verifies titles of billing subscription list thus displayed
 *
 *
 *         Opens Wireless and wireline subscriptions from subscription list
 * 
 *         Verifies system Information section
 *
 */
public class MP_Regression_ServiceEnab_TC_01_Validate_Subscription_Page_Test extends Base {

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 */
//	@SuppressWarnings("static-access")
	@Test
	public void test_verifysubscriptionList() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		// Verify subscription list titles
		sfdc.subscription.listAllSubscriptions();
		sfdc.subscription.verifySusbcriptionListTitles();

		// Verify subscriptions list has some data populated
		sfdc.subscription.verifySusbcriptionListDataSize();

		// Search subscription globally and verify
//		sfdc.subscription.searchSubscriptionGlobally();
		sfdc.subscription.searchSubscriptionGlobalSearch(InputData.subscriptionName);
		sfdc.subscription.verifySearchedSusbcriptionListTitles();
		sfdc.home.closeTabIfOpen();

		// Verify Billed subscription titles
		sfdc.subscription.listAllSubscriptions();
		sfdc.subscription.openBillingAccountFromSubscriptionList();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobalSearch(InputData.billingAccountWithSubscription, "Billing");
		sfdc.subscription.openBilledSubscriptionsFromRelatedListAndVerifyTitles();
		sfdc.home.closeTabIfOpen();

		// Open a Wireless subscription and verify system information
		sfdc.subscription.listAllSubscriptions();
		sfdc.subscription.openSubscriptionByRecordType(InputData.wirelessSubscription);
		sfdc.subscription.verifySystemInformationDetails();
		sfdc.home.closeTabIfOpen();

		// Open a Wireline subscription and verify system information
		sfdc.subscription.listAllSubscriptions();
		sfdc.subscription.openSubscriptionByRecordType(InputData.wirelineSubscription);
		sfdc.subscription.verifySystemInformationDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
