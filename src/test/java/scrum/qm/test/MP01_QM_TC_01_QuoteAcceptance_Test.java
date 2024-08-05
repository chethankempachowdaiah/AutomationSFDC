package scrum.qm.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 26/JAN/2020
 * 
 *         Verify that Sales Rep can Accept Quote.
 *
 */
public class MP01_QM_TC_01_QuoteAcceptance_Test extends Base {
	/**
	 * @throws Exception
	 * 
	 *                   * ***********Pre-Requisite:***********
	 * 
	 *                   1. Business Account is created.
	 * 
	 *                   2. Service account is created.
	 * 
	 *                   3. Premises is stamped on service account.
	 * 
	 *                   4. Opportunity is created.
	 * 
	 *                   5. Quote is created.
	 * 
	 *                   6. User is logged in as a "Sales Rep"
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Test Steps*********************
	 * 
	 *                   NOTE: when the "Create Quote" button is clicked on the
	 *                   Opportunity, system craetes a quote and navigates to the
	 *                   quote page directly.
	 * 
	 *                   1. If Price List is not auto populated, then select the
	 *                   "Rogers SMB" Price List.
	 * 
	 *                   2. Select Product from the product Console & click on "Add
	 *                   to Cart" button.
	 * 
	 *                   3. Click on the down arrow (show actions) button on the
	 *                   root product in the cart & click on "Configure" option.
	 * 
	 *                   4. Select options from drop downs in these configuration
	 *                   parameters.
	 * 
	 *                   5. Click on "Accept Quote".
	 * 
	 *                   6. Click on "Send Email".
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Expected Result****************
	 * 
	 *                   1. Products start appearing in the product console based on
	 *                   the premises ( ON / ATL).
	 * 
	 *                   2. Product is added to the cart.
	 * 
	 *                   3. The configuration parameters will be displayed on the
	 *                   right.
	 * 
	 *                   ____> Contract Term
	 * 
	 *                   ____> Service Location
	 * 
	 *                   ____>Internet Speeds (Download & Upload)
	 * 
	 *                   4. Product is configured & buttons "Accept Quote" ,
	 *                   "Generate & Email Quote" will be enabled.
	 * 
	 *                   5. Quote PDF will be generated & quote preview will be
	 *                   shown & "Send Email" button will be enabled.
	 * 
	 *                   6.1 Quote Email will be sent to the "Signing Authority"
	 *                   that was selected during the opportunity & Quote creation
	 *                   Process.
	 * 
	 *                   6.2 User will be prompted to enter "Site Contact"
	 *                   Information.
	 * 
	 *                   ****************************************
	 */
	@Test
	public void test_quote_acceptQuote() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.parentAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		sfdc.oppDetails.validateOpportunityDetails(Global.dataInput.parentAccountName);
		//sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

//		sfdc.cQuote.createQuote_SelectNewServiceAccount();
//
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
//		sfdc.cQuote.createQuote_SelectCreatedServiceAccount();

		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.verifySelectExistingNewContactText();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
