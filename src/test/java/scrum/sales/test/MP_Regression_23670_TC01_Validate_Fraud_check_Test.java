package scrum.sales.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * 
 * 
 *         MPOSS 23670_TC01_ Validate Fraud check when billing country=Canada and No active billing account
 *         
 *         MPOSS 23670_TC04_ Validate Fraud check when billing country=International 
 *         
 *         MPOSS-37777 TC01 Validate as AE is Quote is created on business account with "High Risk FNF" than automatically route for Fraud Review.
 *
 *		   MPOSS-34040_TC02_Validate when Fraud is Rejected ,status of Quote is changed to Rejected 
 */
public class MP_Regression_23670_TC01_Validate_Fraud_check_Test extends Base {
	/**
	 * @throws Exception
	 * 
	 *                   * ***********Pre-Requisite:***********
	 * 
	 *                   1. Login as AE.
	 * 
	 *                   2. Create an opportunity 
	 * 
	 *                   3. Account setting   s
	 * 
	 *                   4. (Total Credit Limit assigned=Not blank)
	 * 
	 *                   5. such that Credit check is Req
	 * 
	 *                   *****************************************
	 * 
	 *                   **********Test Steps*********************
	 * 
	 *                   1. Account Billing address country=Canada and there is no Active billing account assoiated.
	 * 
	 *                   2. Create a quote
	 * 
	 *                   3.  Accept quote
	 * 
	 *                   4. Quote status= Accepted with Fraud review status=Not required
	 * 
	 *                  
	 *                   *****************************************
	 * 
	 *                   **********Expected Result****************
	 * 
	 *                   1. Products start appearing in the product console based on
	 *                   the premises.
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
	 *                   5 .Quote Email will be sent to the "Signing Authority"
	 *                   that was selected during the opportunity & Quote creation
	 *                   Process.
	 * 
	 *                   6.User will be prompted to enter "Site Contact"
	 *                   Information.
	 *                   7.NO Fraud check when billing address country=canada
	 *                   
	 *                   ****************************************
	 *                   
	 *                  Same as above for address country=International (eg USA)
	 *                  1. Account Billing address country=International (eg:USA) 
	 *					2. Create a quote
	 *					3. Accept quote
	 *					4. Quote status= Accepted with Fraud review status=In Progress
	 *        
	 * 					****************************************
	 *                  High Risk FNF
	 *                  1. Account Billing address country=Canada and there is no Active billing account assoiated BUT "Credit Risk Value" marked as "High Risk FNF"
	 *					NOTE : This field is only visible to Credit Ops and Fraud Ops profiles.
	 *					2. Create a quote
	 *					3. Accept quote
	 *					4. Quote status= Accepted with Fraud review status=Required 
	 *
	 *                   ****************************************
	 *                  1.Create a quote
	 *					2. Quote is in Accepted status with Credit=In Progress, Fraud =In Progress
	 *					3. Approval objects are created 
	 *					4. As a Credit/Fraud ops profile, Credit is approved,Fraud is Rejected
	 *					5. Status of quote changes to ""Rejected""
	 *					6. No option for AE to continue the quote
	 *					7. Validate that opportunity status does not change and Create Quote option is available
"

	 */
	@Test
	public void test_quote_acceptQuote_Validate_Fraud_Check() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		//We are using already created business account where country= Canada and No billing account is associated with it.
		Global.dataInput.tempBusinessAccountName = "TESTAuto_PVT21080200102";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();
		
		sfdc.cOpp.selectOpportunityContactRole();
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		//sfdc.cQuote.createQuote_SelectNewServiceAccount();

		//sfdc.csa.enterServiceAccountInfo(1);
		//sfdc.csa.clickOnNextInCreateServiceAccount();

		//sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();
		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.selectExistingSiteContact();
		sfdc.orderDetails.verifyQuoteLinkInOrderDetails();
		//sfdc.siteCon.verifySelectExistingNewContactText();
       //Verify  Quote status= Accepted with Fraud review status=Not required
		sfdc.quoteDetails.verifyFraudStatusIfCanada();
		//sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		//We are using already created business account where country= USA and No billing account is associated with it.
		Global.dataInput.tempBusinessAccountName = "TESTAuto_PVT21080200103";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		//sfdc.cQuote.createQuote_SelectNewServiceAccount();

		//sfdc.csa.enterServiceAccountInfo(1);
		//sfdc.csa.clickOnNextInCreateServiceAccount();

		//sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();
		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		//sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.selectExistingSiteContact();
		sfdc.orderDetails.verifyQuoteLinkInOrderDetails();
		//Verify  Quote status= Accepted with Fraud review status=Required/In Progress
		sfdc.quoteDetails.verifyFraudStatusIfNotCanada();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		//We are using already created business account where country= Canada and with "High Risk FNF" assigned
		Global.dataInput.tempBusinessAccountName = "TESTAuto_PVT21080200101";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		//sfdc.cQuote.createQuote_SelectNewServiceAccount();

		//sfdc.csa.enterServiceAccountInfo(1);
		//sfdc.csa.clickOnNextInCreateServiceAccount();

		//sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();
		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		//sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.selectExistingSiteContact();
		sfdc.orderDetails.verifyQuoteLinkInOrderDetails();
		//Verify  Quote status= Accepted with Fraud review status=Required/In Progress
		sfdc.quoteDetails.verifyFraudStatusIfHighRiskFNF();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		
		//We are using already created business account where country= USA and No billing account is associated with it.
		Global.dataInput.tempBusinessAccountName = "DemoFor_T";  //"TESTAuto_PVT21080200103";
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.opportunityCreateContact();

		sfdc.cc.enterCreateContactInfo(false);
		sfdc.cc.clickContactDetailsNext();
		sfdc.cc.verifyContactCreated();
		sfdc.cc.clickOnNextInCreateContact();

		sfdc.cOpp.selectOpportunityContactRole();
		sfdc.cQuote.createQuote_SelectContatRole_SigningAuthority();
		
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		//sfdc.cQuote.createQuote_SelectNewServiceAccount();

		//sfdc.csa.enterServiceAccountInfo(1);
		//sfdc.csa.clickOnNextInCreateServiceAccount();

		//sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();
		sfdc.cpqHome.verifyQuoteNumberInCPQHome();
		sfdc.cpqHome.selectPriceList();
		sfdc.cpqHome.selectProductInHybridCart();

		sfdc.cpqHome.configureQuote();
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		//sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.selectExistingSiteContact();
		sfdc.orderDetails.verifyQuoteLinkInOrderDetails();
		//Verify  Quote status= Accepted with Fraud review status=Required/In Progress
		sfdc.quoteDetails.verifyFraudStatusIfNotCanada();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout(); 
		
		//Login as Fraud Support
		sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
		//sfdc.home.navigateURL();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BQuoteApproval();
		
		
		//Open any quote from R4B Quotes 
	     sfdc.r4Bquoteapp.seletAndOpendR4BQuoteApproval();
	     sfdc.r4Bquoteapp.searchQuote(sf.dataInput.quoteNumber);

	   //Reject the Quote
	    sfdc.r4Bquoteapp.rejectR4BQuote();
			
		softassert.assertAll();

	}
}
