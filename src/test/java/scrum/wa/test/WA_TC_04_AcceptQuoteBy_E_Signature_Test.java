package scrum.wa.test;

import org.testng.annotations.Test;
import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Sakshi.Lnu, Date : 28/07/2021
 * 
 *       Accept Quote by E-signature 
 *
 */
public class WA_TC_04_AcceptQuoteBy_E_Signature_Test extends Base {

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                             When user accepts the Quote by E-signature
	 *                             Verify Quote Status changes to  "Awaiting e-signature
	 *                             
	 */

	SFDC_AllPages sfdc = new SFDC_AllPages();
	@Test()
	public void verifyGenerateDocumentByESignature_Test() throws Exception {

		sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobSearch(InputData_WA.account_Business_R4B, "Business");
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType("Voice and Data", "Pooled", "40GB");
		sfdc.selectPro.clickOnPlansAddToCart();
		sfdc.selectAddOn.continueByodDeviceAndClickOnShopCart();
		sfdc.shopCart.clickProceedToCheckoutBtn();
		sfdc.reOrder.acceptQuoteByESign();
		sfdc.genDoc.generateDocument("word");
		sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
		sfdc.quoteDetails.verifyQuoteStatusWACC("Awaiting Signature");
		//sfdc.orderDetails.verifyOrderStatusInOrderDetails("draft");
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();	
		
	}
}
