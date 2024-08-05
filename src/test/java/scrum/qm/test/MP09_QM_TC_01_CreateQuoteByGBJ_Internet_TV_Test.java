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
 * @author Priyanka.Acharya, Date : 07/July/2020
 * 
 *         Create Quote by Guided By Journey for below set of products
 * 
 *         1. Standalone - Internet 3 year term
 * 
 *         2. Standalone - Internet - Promos
 * 
 *         3. Standalone - Internet Monthly
 * 
 *         4. Standalone - Monthly TV
 * 
 *         5. Bundle - Internet + TV
 *
 *         6. Microsoft office 365 products
 * 
 *         7. Validate the prices in the PDF
 * 
 *         US - MPOSS-37635 EPC change for Office 365 fix - Implemented by
 *         ChethanK
 */
public class MP09_QM_TC_01_CreateQuoteByGBJ_Internet_TV_Test extends Base {

	@Test(dataProvider = "getGBJPriceMarginInfo")
	public void test_CreateQuoteByGBJ_Internet_TV(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
//		sfdc.cOpp.GetAEDetails();

//		    sfdc.opp.verifyOpportunitiesObject(); 
//		sfdc.opp.selectOpportunity(sf.dataInput.account_ON);

		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		if (dataTable.get("Product _Type").equals(InputData.internetProduct)) {
			sfdc.gbjCart.addRogersBusinessSolutions_Internet(dataTable, false, false);
			sfdc.gbjCart.addRogersOffice365AddOns(dataTable, false);
			sfdc.gbjCart.hitCheckOut();
		}

		else if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
			sfdc.gbjCart.addRogersBusinessSolutions_TV(dataTable, false);
			sfdc.gbjCart.addRogersTVAddOns(dataTable, false);
			sfdc.gbjCart.hitCheckOut();
		}

		else if (dataTable.get("Product _Type").contains(InputData.internetProduct)
				&& dataTable.get("Product _Type").contains(InputData.tvProduct)) {

			sfdc.gbjCart.addRogersBusinessSolutions_Internet(dataTable, false, false);
			sfdc.gbjCart.addRogersOffice365AddOns(dataTable, false);
			sfdc.gbjCart.hitNext();
			sfdc.gbjCart.addRogersBusinessSolutions_TV(dataTable, false);
			sfdc.gbjCart.addRogersTVAddOns(dataTable, false);

		}
		sfdc.quoteReview.verifyDetailsinQuoteReview(dataTable, false);

		/*
		 * Fetch the QuoteReview_Type input from data sheet and proceed with
		 * acceptquote/emailquote/ESignature Flow accordingly
		 */

		if (true) {

			sfdc.gbjCart.acceptQuoteInGBJ();
			sfdc.gdPdf.sendQuoteNextButton();
			sfdc.siteCon.selectExistingSiteContact_Scrum();

			sfdc.orderDetails.verifySubmittedOrderInOrderDetails();

			sfdc.home.closeTabIfOpen();

		} else {
			if (dataTable.get("QuoteReview_Type").contains("AcceptQuote")) {
				sfdc.gbjCart.acceptQuoteInGBJ();

				sfdc.gdPdf.verifyAllProductPricesInPDF(dataTable, Constants.ACCEPTED_PDF);
				sfdc.gdPdf.sendQuoteNextButton();

				// Verify email received by signing authority
				sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);

				sfdc.siteCon.selectExistingSiteContact_Scrum();
				// TODO- capture business acc name, order,
				// sfdc.gbjCart.captureEmailBodyAttributes();
				// Verify email received by site contact
				sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, true);

				sfdc.quoteDetails.verifyCreditFraudStatus(dataTable.get("Credit_Required"),
						dataTable.get("Fraud_Required"), dataTable.get("CreditFraud_Status"));
				if (dataTable.get("CreditFraud_Status").equals("Approve")) {
					sfdc.orderDetails.verifySubmittedOrderInOrderDetails();
				} else {
					sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusRejected,
							sf.dataInput.quoteStatusRejected);
				}
			} else if (dataTable.get("QuoteReview_Type").contains("EmailQuote")) {
				sfdc.gbjCart.emailQuoteInGBJ();
				sfdc.gdPdf.verifyAllProductPricesInPDF(dataTable, Constants.PROPOSAL_PDF);
				sfdc.gdPdf.sendQuoteNextButton();
				sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);

				sfdc.quoteDetails.clickOnResumeQuotePage();
				sfdc.gbjCart.acceptQuoteInGBJ();
				sfdc.gdPdf.sendQuoteNextButtonAfterResumeQuote();
				sfdc.siteCon.selectExistingSiteContact_Scrum();

				sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, true);
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

			} else if (dataTable.get("QuoteReview_Type").contains("ESignature")) {

				sfdc.gbjCart.eSignatureQuoteInGBJ();
				sfdc.gdPdf.sendQuoteNextButton();
				sfdc.siteCon.selectExistingSiteContact_Scrum();
				sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign,
						"", "");
				if (dataTable.get("Esignature_Status").contains("Signed")) {
					sfdc.quoteDetails.verifyCreditFraudStatus(dataTable.get("Credit_Required"),
							dataTable.get("Fraud_Required"), dataTable.get("CreditFraud_Status"));
					sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);
					// Adding a delay of 15mins for the job to run for the quote to change from
					// Awaiting Signature to Finalized status
					sf.seleU.wait(900000);
					sf.seleU.refreshPage();
					sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.finalisedStatus,
							sf.dataInput.signedStatus, sf.dataInput.notReqStatus, sf.dataInput.notReqStatus);

				} // Handle Esign decline functionality
				else if (dataTable.get("Esignature_Status").contains("Declined")) {
					sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);
					// Adding a delay of 15mins for the job to run for the quote to change from
					// Awaiting Signature to Finalized status
					sf.seleU.wait(900000);
					sf.seleU.refreshPage();
					sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.deniedStatus,
							sf.dataInput.declinedStatus, "", "");
				} else {
					// Handle Esign Revoke functionality
					sfdc.quoteDetails.revokeESign();
					sfdc.quoteDetails.verifyContractStatus();
				}

			}
		}
		sfdc.home.logout();

	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
		Global.dataInput.office365ProductName = dataTable.get("Office 365 AddOn");
		Global.dataInput.tvProductName = dataTable.get("TV Product Name");

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
	public Object[][] getGBJPriceMarginInfo() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_SHEET);
	}
}
