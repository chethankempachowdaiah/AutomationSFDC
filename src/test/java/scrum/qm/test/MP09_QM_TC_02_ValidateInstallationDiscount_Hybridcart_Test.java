package scrum.qm.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date 24/09/2020
 * 
 *         Add and verify Installation Discount to Hybrid cart
 *
 */
public class MP09_QM_TC_02_ValidateInstallationDiscount_Hybridcart_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                     *************Test Steps******************
	 * 
	 *                     "Add product ""150u Business Internet"" to cart.
	 * 
	 *                     Click on ""Discounts"" Tab.
	 * 
	 *                     Add ""INSTALLATION DISCOUNT"" to cart.
	 * 
	 *                     Click on ""Send Email"".
	 * 
	 *                     Enter Site Contact Information & click on ""Next""."
	 * 
	 * 
	 *                     ****************Actual Results*************
	 * 
	 *                     "Accept quote button should be enabled.
	 * 
	 *                     Internet Installtion should be discounted at 100%.
	 * 
	 *                     The original installation charge is 99.99$. That should
	 *                     become 0$.
	 * 
	 *                     quote should be accepted. PDF to be attached in email.
	 * 
	 *                     Order to be created & in status ""Draft"" & ""In
	 *                     Progress"""
	 * 
	 * 
	 */
	@Test
	public void test_ValidateInstallationDiscount_Hybridcart() throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// sfdc.accDetails.searchAccount(sf.dataInput.account_ON);
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.account_ON);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		sfdc.cpqHome.addProductToCart();
		sfdc.cpqHome.verifyProductAddedToCart();
		sfdc.cpqHome.addDiscountToCart();

		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.createNewSiteContact();
		sfdc.orderDetails.verifyOrderNumberInOrderDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

	}
}
