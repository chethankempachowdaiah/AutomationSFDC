package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya
 * 
 *         Commoditised Internet product- offer CPQ & OM
 * 
 *         Commoditised internet product- promotion CPQ & OM
 * 
 *         Commoditised TV - Offer - CPQ & OM
 * 
 *         Commoditised TV + Internet Bundle- promo - CPQ & OM
 * 
 *         Office 365 - CPQ & OM
 * 
 *         IBLC - Offers - CPQ & OM
 * 
 *         IBLC Promo - CPQ & OM
 * 
 *         IBLC + Int Bundle - CPQ & OM
 * 
 *         Validate the prices in the PDF
 * 
 *         Send email and Validate email in mailinator - US - MPOSS-38514
 *         
 *         Approve credit/fraud check
 *         
 *			Validate email body received by Site Contact       
 *        
 */
public class MP09_QM_TC_03_CreateQuoteInHybridCart_Test extends Base {

	@Test(dataProvider = "getEPCChangesInfo")
	public void test_CreateQuoteInHybridCart(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);
		
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.userid_ae);
		//sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

//		// ***************Create business Account********//
//		sfdc.acc.createNewBusinessAccount();
//		sfdc.cba.enterBusinessAccountInfo(false);
//		sfdc.cba.verifyBusinessAccountCreated();
//
//		// ***************Create Contact********//
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//
//		// ***************Create Service Account********//
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
//		sfdc.csa.noBillingAccountClickOnNext();
//
//		// *********Login as Data Governance and Approve business Account******//
//		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
//		sfdc.home.closeTabIfOpen();
//		// sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//		sfdc.accRelated.approveAsDataGovernance();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//
//		// ***************LOGIN AS AE***********************//
//		sfdc.login.loginToSFDC(InputData.userid_ae);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();

//		// ***************Create Opportunity***********************//
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);

		// ********************************//

		if (dataTable.get("Region").contains("ATL")) {

			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ATL);
		} else {

		sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
				
		}
	
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		//Capture email attributes 
		sfdc.quoteDetails.GetAEAndProductDetails(dataTable);
		sfdc.home.closeLastOpenedTab();
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();
		if (dataTable.get("Type").equals("Product")) {

			if (dataTable.get("Product Name").contains("Microsoft 365")) {
				Global.dataInput.quoteProductName = dataTable.get("Product Name").split("#")[1].trim();
				sfdc.cpqHome.addProductToCart();
				Global.dataInput.office365ProductName = dataTable.get("Product Name").split("#")[0].trim();
				sfdc.cpqHome.addProductToCart(Global.dataInput.office365ProductName);
				sfdc.cpqHome.verifyProductAddedToCart();
			} else {
				sfdc.cpqHome.addProductToCart();
				sfdc.cpqHome.verifyProductAddedToCart();
			}
		}

		else {
			sfdc.cpqHome.addPromotionToCart();
		}
		if (dataTable.get("Add STB Group").equals("Yes")) {
			if (dataTable.get("Product Name").contains("Internet") && dataTable.get("Product Name").contains("TV"))
				sfdc.cpqHome.addSTBGroup(true);
			else
				sfdc.cpqHome.addSTBGroup(false);
		}
		
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		// Verify email received by Signing Authority
		sfdc.mailinatorPage.verifyQuoteDetailsInEmailAtMailinator(sf.dataInput.signingAuthEmailIdValue,
				Constants.EMAIL_ACCEPT_TEXT, false);
		
//		sfdc.siteCon.verifySelectExistingNewContactText();
//		sfdc.siteCon.createNewSiteContact();
		sfdc.siteCon.selectExistingSiteContact_Scrum();		
		// Capture email attributes
		if (dataTable.get("NFDB_Action").equals("PASS")|| (dataTable.get("NFDB_Action").equals("REJECT"))){
			sfdc.quoteDetails.serviceAddressInQuoteDetails(dataTable);

			sfdc.home.closeLastOpenedTabs(2);
			sfdc.quoteDetails.clickRelatedTab();
			sfdc.quoteDetails.verifyCreditFraudStatus(dataTable.get("Credit_Required"), dataTable.get("Fraud_Required"),
					dataTable.get("CreditFraud_Status"));
			// Verify email received by site contact
			sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, true);
			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			
		}
		else {
			sfdc.orderDetails.verifySubmittedOrderInOrderDetails();
				}
		
		
		if((dataTable.get("Credit_Required").equals("No") && dataTable.get("Fraud_Required").equals("No"))
			|| (dataTable.get("Credit_Required").equals("Yes") && dataTable.get("Fraud_Required").equals("No"))
			|| (dataTable.get("Credit_Required").equals("No") && dataTable.get("Fraud_Required").equals("Yes"))){
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					"");
		}else if(dataTable.get("CreditFraud_Status").equals("Approve")) {
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized,
					sf.dataInput.quoteStatusApproved);
		}else if(dataTable.get("CreditFraud_Status").equals("Reject")) {
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusRejected,
					sf.dataInput.quoteStatusRejected);
		}

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//
//		sfdc.mques.selectManualQueueAccProvQue();
//		sfdc.manQue.pickUpOrderInQueue();
//		sfdc.crSSOrd.createSuperSystemOrder();
//
//		sfdc.mques.selectServiceDeliveryQueue();
//		sfdc.manQue.pickUpOrderInQueue();
//		sfdc.complVlctyOdr.completeVlocityOrder();
//
//		if (dataTable.get("Product Name").contains("Microsoft 365")) {
//			sfdc.mques.selectOffice365();
//			sfdc.manQue.pickUpOrderInQueue();
//			sfdc.complVlctyOdr.completeOffice365Order();
//			sfdc.mques.selectOffice365();
//			sfdc.manQue.verifyOrderNotInQueue();
//			sfdc.home.closeTabIfOpen();
//		}
//		sfdc.home.logout();
//
//		// ***************LOGIN AS AE***********************//
//		sfdc.login.loginToSFDC(InputData.userid_ae);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();
//
//		// **********Verify Order Activated and Assets Created********//
//		sfdc.acc.verifyAccountsObject();
//		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
//		sfdc.accRelated.viewOrdersInAccountRelatedTab();
//		sfdc.orderDetails.verifyActivatedOrderInOrderDetails();
//		sfdc.accRelated.verifyAssetsCreated();
//		sfdc.home.closeTabIfOpen();
//
//		// **********Verify Opportunity is closed own********//
//		sfdc.opp.verifyOpportunitiesObject();
//		sfdc.opp.selectOpportunity(sf.dataInput.businessAccountName);
//		sfdc.oppDetails.verifyOpportunityIsClosedOwn();
//		sfdc.home.closeTabIfOpen();
//
//		// ***************Verify Billing Account Created********//
//		sfdc.acc.verifyAccountsObject();
//		sfdc.accDetails.verifyBillingAccountCreated(sf.dataInput.billingCAN);
//
//		if (dataTable.get("Test Case Name").contains("365")) {
//			sfdc.home.closeTabIfOpen();
//			sfdc.accDetails.verifyBillingAccountCreated(sf.dataInput.billingBAN);
//			sfdc.accDetails.verifyAssetsInBillingAccount();
//		}
//
//		if (dataTable.get("Test Case Name").contains("IBLC")) {
//			sfdc.home.closeTabIfOpen();
//			sfdc.accDetails.verifyBillingAccountCreated(sf.dataInput.billingBAN);
//		}
//
//		// **********Close Tabs and Log out********//
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//		softassert.assertAll();
	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		Global.dataInput.quoteProductName = dataTable.get("Product Name");
		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getEPCChangesInfo() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.EPC_SHEET);
	}

}
