package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;
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
 * @author Chethan Create Quote and validate quote status in HybridCart via
 *         Accept quote, Email quote, Esignature (earlier in GBJ flow) Also DO
 *         NOT Provide site contact information Validate if Task is created for
 *         AE to select site contact Validate Task Subject,Order status
 * 
 */
public class MP_Reg_QM_TC035_ValidateTaskCreatedToSelectSiteContact extends Base {

	@Test(dataProvider = "getEPCChangesInfo")
	public void test_CreateQuoteInHybridCart(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
			
		setInputData(dataTable);

		// ***************LOGIN AS AE***********************//

		sfdc.login.loginToSFDC(InputData.userid_ae);
		// sfdc.home.openR4BSalesConsole();
		
		

		sfdc.home.closeTabIfOpen();
		if (dataTable.get("Region").contains("ATL")) {

			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ATL);
		} else {
			sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
		}

//		sfdc.opp.verifyOpportunitiesObject();
//		sfdc.opp.selectOpportunity("Raj New Single Site Single Product IAN-BUSIN");

		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

		// Capture email attributes and select signing authority,service account
		sfdc.quoteDetails.GetAEAndProductDetails(dataTable);
		sfdc.home.closeLastOpenedTab();
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		// add products to cart
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
		} else {
			sfdc.cpqHome.addPromotionToCart();
		}
		// add STB to cart
		if (dataTable.get("Add STB Group").equals("Yes")) {
			if (dataTable.get("Product Name").contains("Internet") && dataTable.get("Product Name").contains("TV"))
				sfdc.cpqHome.addSTBGroup(true);
			else
				sfdc.cpqHome.addSTBGroup(false);
		}

		// copied from GBJ flow

		// Verify quote status after accepting quote
		if (dataTable.get("QuoteReview_Type").contains("AcceptQuote")) {
			sfdc.cpqHome.acceptQuote();

			// sfdc.gdPdf.verifyAllProductPricesInPDF(dataTable, Constants.ACCEPTED_PDF);
			sfdc.gdPdf.sendQuoteNextButton();

			// Verify email received by "signing authority"
			sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);

			sfdc.siteCon.selectExistingSiteContact_Scrum();
			// capture business acc name, order,
			// sfdc.gbjCart.captureEmailBodyAttributes();
			sfdc.quoteDetails.serviceAddressInQuoteDetails(dataTable);

			sfdc.home.closeLastOpenedTabs(2);

			// Verify email received by "site contact"
			sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, true);
			sfdc.home.closeLastOpenedTabs(1);

			if (dataTable.get("Product _Type").equals("Wireless")) {
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusActivated);
			} else {
				sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);

			}
		}
		// Verify quote status after Email quote
		else if (dataTable.get("QuoteReview_Type").contains("EmailQuote")) {
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

		}
		// Verify quote status after requesting esignature
		else if (dataTable.get("QuoteReview_Type").contains("ESignature")) {

			sfdc.cpqHome.requestEsignature();

			sfdc.gdPdf.sendQuoteNextButtonAfterResumeQuote();

			sfdc.siteCon.selectExistingSiteContact_Scrum();

			sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.awaitingSign, sf.dataInput.awaitingSign, "",
					"");
			// Handle Esign Signed functionality
			if (dataTable.get("Esignature_Status").contains("Signed")) {

				sfdc.quoteDetails.verifyCreditFraudStatus(dataTable.get("Credit_Required"),
						dataTable.get("Fraud_Required"), dataTable.get("CreditFraud_Status"));

				sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);
				// Adding a delay of 15mins for the job to run for the quote to change from
				// Awaiting Signature to Finalized status
				sf.seleU.wait(900000);
				sf.seleU.refreshPage();

				sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.finalisedStatus, sf.dataInput.signedStatus,
						sf.dataInput.notReqStatus, sf.dataInput.notReqStatus);
				sfdc.orderDetails.verifyShipToContactInOrder();

			}
			// Handle Esign decline functionality
			else if (dataTable.get("Esignature_Status").contains("Declined")) {

				sfdc.mailinatorPage.verifyEmailForGBJ(dataTable, false);
				// Adding a delay of 15mins for the job to run for the quote to change from
				// Awaiting Signature to Finalized status
				sf.seleU.wait(900000);
				sf.seleU.refreshPage();

				sfdc.orderDetails.verifyContractAndQuoteDetails(sf.dataInput.deniedStatus, sf.dataInput.declinedStatus,
						"", "");
			} else {
				// Handle Esign Revoke functionality
				sfdc.quoteDetails.revokeESign();
				sfdc.quoteDetails.verifyContractStatus();
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
