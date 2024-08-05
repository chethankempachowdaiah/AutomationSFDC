package scrum.wa.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

public class WA_TC_07_Complete_OrchestrationTasks_Test {

	/**
	 * @author Pankaj Agarwal, Date : 23/03/2022
	 * 
	 *       Accept Quote by E-signature 
	 *
	 */
	public class WA_TC_07_OrchestrationTasks_Test extends Base {

		/**
		 * @throws IOException
		 * @throws InterruptedException
		 * 
		 *                             1.Create a Order, 
		 *                             2.Submit Order, 
		 *                             3.Login as BA
		 *                             4. Open orders and select inProgress Order
		 *                             5. Go to Orchestration plan....
		 *                             6. And verify if are the automated tasks are completed and if not complete "Manually"
		 *                             
		 */

		SFDC_AllPages sfdc = new SFDC_AllPages();
		@Test()
		public void verifyGenerateDocumentByESignature_Test() throws Exception {

			sfdc = new SFDC_AllPages();
			test = reports.createTest(this.getClass().getName());
			// ***************LOGIN AS AE***********************//
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			
			sfdc.orders.selectOrder();		
			sfdc.orderRelated.verifyOrchestrationPlanInOrder();	
			
			//sfdc.home.openR4BSalesConsole();
//			sfdc.home.closeTabIfOpen();
//			sfdc.accDetails.searchAccountFromGlobSearch(InputData_WA.account_Business_R4B, "Business");
//			//sfdc.accRelated.clickOnOpportunity();
//			sfdc.accRelated.createOpportunity();
//			sfdc.cOpp.enterOpportunityDetails();
//			sfdc.cOpp.selecetExistingContactInOpportunity(waData.contact_Business_R4B);
//			sfdc.cQuote.clickCreateQuotePbfButton();
//			sfdc.selectPro.verifyWirelessProducts();
//			sfdc.selectPro.selectDataPlanAndType("Voice and Data", "Pooled", "40GB");
//			sfdc.selectPro.continueToAddOnsButton();
//			sfdc.selectAddOn.selectRoamingPlans();
//			//sfdc.selectPro.clickOnPlansAddToCart();
//			sfdc.selectAddOn.continueByodDeviceAndClickOnShopCart();
//			sfdc.home.closeLastOpenedTab();
//			sfdc.cQuote.clickCreateQuotePbfButton();
//			sfdc.shopCart.clickProceedToCheckoutBtn();
//			sfdc.reOrder.acceptQuoteVerbally();
//			sfdc.genDoc.generatePdfToAcceptQuoteVerbally(sf.dataInput.quoteNumber);
//			sfdc.quoteDetails.verifyQuoteNumberInQuotePage();
//			sfdc.quoteDetails.verifyQuoteStatusWACC("Finalized");
//			sfdc.quoteRelated.getOrderNumberFromQuoteRelatedTab(sf.dataInput.orderNumber);
//			sfdc.quoteRelated.clickOnOrderNoFromQuote();
//			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);
//			sfdc.home.logout();
//			sfdc.comLogin.loginToWaccCommunities();
//			sfdc.comMyBusCases.verifyMyBusniessOrders();
//			sfdc.comOrderDetails.clickOnIncompleteOrderNumber();
//			sfdc.comOrdFlow.selectRequestToPortOrNewNumber("New Number"); 
//			sfdc.comLogin.logoutFromCommunities();
			softassert.assertAll();
		}
	}
}
