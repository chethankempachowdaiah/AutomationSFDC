package scrum.qm.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 30/JAN/2020
 * 
 *         Order Creation- Verify that system creates an order once the Quote is
 *         accepted but Site Contact information is missing.
 *
 */
public class MP01_QM_TC_03_Quote_CreateOrder_Test extends Base {
	/**
	 * @throws Exception
	 * 
	 *                   *************Pre-Requisite:***********
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
	 *                   7. Sales Rep Accepts Quote.
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Test Steps*********************
	 * 
	 *                   1.Configure Quote.
	 * 
	 *                   2. Click on 'Accept Quote".
	 * 
	 *                   3. Send Email.
	 * 
	 *                   4. Click "save For Later" on site contact page.
	 * 
	 *                   5. Click "OK" on the pop up message for - order is created
	 *                   and is in draft. You will have to proceed with order
	 *                   submission from orders page by providing site contact
	 *                   information.
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Expected Result****************
	 * 
	 *                   Order Page will be displayed.
	 * 
	 *                   Order ID will be shown.
	 * 
	 *                   Order will be created and will be in "Draft" state.
	 * 
	 *                   Select Site Contact" button will be enabled to input site
	 *                   contact details in order to submit order.
	 * 
	 * 
	 *                   Order Status: Ready To Submit.
	 * 
	 *                   Status: Draft
	 * 
	 *                   *****************************************
	 * 
	 * 
	 */
	@Test
	public void test_createOrder() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.parentAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// sfdc.cOpp.opportunityCreateContact();

//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.clickContactDetailsNext();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//		sfdc.cOpp.selectOpportunityContactRole();
//		sfdc.oppDetails.validateOpportunityDetails(sf.dataInput.parentAccountName);
//		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
//		
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		// sfdc.cQuote.createQuote_SelectNewServiceAccount();

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
		//sfdc.siteCon.selectSaveForLater();
		sfdc.home.closeTabIfOpen();

		sfdc.acc.selectAccount();
		sfdc.quoteDetails.selectAndOpenAccountFromQuotePage();
		sfdc.accRelated.viewOrdersInAccountRelatedTab();
		sfdc.orderDetails.verifyCreatedOrderInOrderDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();

	}
}
