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
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Chethan Create Quote > Accept Quote ON Select Site Contact page, DO
 *         NOT Provide site contact information Validate if Task is created for
 *         AE to select site contact Validate Task Subject if Validation Check
 *         status is required, login as FraudOps/creditOps > perform pass/Reject
 *         if credit/fraud check status is required, perform Approve/Reject
 *         login as AE and check the quote/order status
 */
public class MP_Reg_QM_Validate_quote_status_after_NFDB extends Base {

	@Test(dataProvider = "getEPCChangesInfo")
	public void test_Validate_quote_status_after_NFDB(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		// ***************LOGIN AS AE***********************//

		sfdc.login.loginToSFDC(InputData.userid_ae);
		
		

		sfdc.home.closeTabIfOpen();

		if (dataTable.get("Region").contains("ATL")) {
			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ATL);
		} else {
			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
			

			// sfdc.opp.verifyOpportunitiesObject(); //
			// sfdc.opp.selectOpportunity("AccwithUSaddressCRFR New116");

			sfdc.accRelated.createOpportunity();
			sfdc.cOpp.enterOpportunityDetails();
			sfdc.cOpp.selecetExistingContactInOpportunity();

			// Capture email attributes
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

			if (dataTable.get("QuoteReview_Type").contains("ESignature")) {
				sfdc.cpqHome.requestEsignature();
				sfdc.gdPdf.sendQuoteNextButtonAfterResumeQuote();
				// Handle Esign Signed functionality
				if (dataTable.get("Esignature_Status").contains("Signed")) {
					sfdc.mailinatorPage.reviewAndSignInEmailAtMailinator(sf.dataInput.signingAuthEmailIdValue,
							dataTable.get("Esignature_Status").toString());
					// Adding a delay of 15mins for the job to run for the quote to change from
					// Awaiting Signature to Finalized status
					sf.seleU.wait(900000);
					sf.seleU.refreshPage();
//					sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.finalisedStatus, sf.dataInput.signedStatus,
//							sf.dataInput.notReqStatus, sf.dataInput.notReqStatus);
//					sfdc.orderDetails.verifyShipToContactInOrder();
				}
				// Handle Esign decline functionality
				else if (dataTable.get("Esignature_Status").contains("Declined")) {
					// Adding a delay of 15mins for the job to run for the quote to change from
					// Awaiting Signature to Finalized status
					sf.seleU.wait(900000);
					sf.seleU.refreshPage();
					sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.deniedStatus,
							sf.dataInput.declinedStatus, "", "");
				}
			} else {
				sfdc.cpqHome.acceptQuote();
				sfdc.gdPdf.sendQuoteNextButton();
				sfdc.siteCon.selectExistingSiteContact_Scrum();
			}

			sfdc.quoteDetails.serviceAddressInQuoteDetails(dataTable);
			sfdc.home.closeLastOpenedTabs(2);
			sfdc.quoteDetails.clickRelatedTab();
			// NFDB : Perform NFDB action pass/reject if the validation check status is
			// required/Accounts Receivable
			sfdc.quoteDetails.verifyValidationCheck(dataTable.get("NFDB_Action"));

			if (dataTable.get("NFDB_Action").equals("Pass")) {
				// CRFR : Perform CR and FR approve/reject if the status is required
				sfdc.quoteDetails.verifyCreditFraudStatus(dataTable.get("Credit_Required"),
						dataTable.get("Fraud_Required"), dataTable.get("CreditFraud_Status"));
				sfdc.login.loginToSFDC(InputData.Profile_AE);
				sfdc.quotes.searchQuoteGlobalSearchInDevStage(sf.dataInput.quoteName);
				// sfdc.quoteDetails.verifyTaskCreatedForAE();
				// click on update order details button
				// sfdc.orders.updateOrderDetails();
				// Click on new site contact after clicking update order details button
				// sfdc.siteCon.createNewSiteContactAfterRsumeQuote();
				if (dataTable.get("CreditFraud_Status").equals("Approve")
						&& dataTable.get("Credit_Required").equals("No")
						&& dataTable.get("Fraud_Required").equals("Yes")) {

					sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusFinalized,
							sf.dataInput.passStatus, sf.dataInput.quoteStatusApproved, sf.dataInput.notReqStatus);
				} else if (dataTable.get("CreditFraud_Status").equals("Approve")
						&& dataTable.get("Credit_Required").equals("Yes")
						&& dataTable.get("Fraud_Required").equals("No")) {
					sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusFinalized,
							sf.dataInput.passStatus, sf.dataInput.notReqStatus, sf.dataInput.quoteStatusApproved);
				}

				else if (dataTable.get("CreditFraud_Status").equals("Approve")
						&& dataTable.get("Credit_Required").equals("Yes")
						&& dataTable.get("Fraud_Required").equals("Yes")) {
					sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusFinalized,
							sf.dataInput.passStatus, sf.dataInput.quoteStatusApproved,
							sf.dataInput.quoteStatusApproved);
				}
			}

			else {

				sfdc.login.loginToSFDC(InputData.Profile_AE);

				sfdc.quotes.searchQuoteGlobalSearchInDevStage(sf.dataInput.quoteName);

				sfdc.quoteDetails.verifyQuoteStatusAfterValidationCheck(sf.dataInput.quoteStatusRejected,
						sf.dataInput.quoteStatusRejected, sf.dataInput.requiredStatus, sf.dataInput.requiredStatus);

			}

			sfdc.home.closeTabIfOpen();

			sfdc.home.logout();
		}
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
