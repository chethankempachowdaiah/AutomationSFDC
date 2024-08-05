package sfdc.pages.qm;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.DataTable;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.om.SFDC_OrderDetails_Page;

import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date: 31/JAN/2020
 *
 *         SFDC Quote Details Tab
 */

public class SFDC_QuoteDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_QuoteDetails_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Quote Details Tab
	 * 
	 *                     2.Verify Quote Status is accepted
	 * 
	 *                     3.Click on Quote Account Link
	 */
	public void selectAndOpenAccountFromQuotePage() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";

			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			reportStatusPass(methodName + " Clicked on  Quote Details Tab", true, false);

			// Verify Quote Status is accepted
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted)) {
				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.quoteStatusAccepted
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Click on Quote Account Link
			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Quote Details Tab
	 * 
	 *                     2.Verify Quote Status is accepted
	 * 
	 *                     3.Click on Quote Account Link
	 */
	public void verifyQuoteStatus() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.hardwait(3000);
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			//	verifyFieldPresent("Resume Quote Button ", sf.siteCon.resumeQuoteButton);
			sf.seleU.hardwait(2000);
			// sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
			sf.seleU.hardwait(3000);
			// Verify Quote Status is accepted
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted)) {
				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.quoteStatusAccepted
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify credit check status in Quote details
			sf.seleU.hardwait(2000);
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText).trim()
					.equals(Global.dataInput.orderStatusInProgress)) {

				Global.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);

				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.orderStatusInProgress
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(methodName + "Invalid Quote Status "
						+ sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText) + " in Quote Details Page",
						true);
			}

			// Click on Quote Account Link
			//			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			//			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}
	}

//	/**Author: Pankaj 20/Jan/2022
//	 * @throws IOException 
//	 *                      This method will verify the quote status before fraud or credit check in quote details page
//	 * 
//	 *                     1.Click on Quote Details Tab
//	 * 
//	 *                     2.Verify Quote Status is accepted
//	 * 
//	 *                     3.Verify Validation check, fraud check and credit check status in the quote details tab
//	 */
//	public void verifyQuoteStatusBeforeCreditAndFraudCheck(String creditCheckStatus, String fraudCheckStatus, String validationCheckStatus) throws IOException {
//		try {
//			String methodName = "SFDC_Quote Details@: ";
//			sf.seleU.hardwait(3000);
//			// Click on Quote Details Tab
//			sf.seleU.switchToDefaultContent();
//			//	verifyFieldPresent("Resume Quote Button ", sf.siteCon.resumeQuoteButton);
//			sf.seleU.hardwait(2000);
//			// sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
//			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
//			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
//			sf.seleU.hardwait(3000);
//			// Verify Quote Status is accepted
//			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(sf.dataInput.quoteStatusAccepted)) {
//				reportStatusPass(methodName + " verified Quote Status is " + sf.dataInput.quoteStatusAccepted
//						+ " in quote details page", true, true);
//			} else {
//				reportStatusFail(
//						methodName + "Invalid Quote Status "
//								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
//								true);
//			}
//
//			// Verify credit check status in Quote details
//			sf.seleU.hardwait(2000);
//			if (sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText).trim()
//					.equals(creditCheckStatus)) {
//
//				//	sf.dataInput.notReqStatus
//				reportStatusPass(methodName + " verified Quote Status for credit check is " + creditCheckStatus
//						+ " in quote details page", true, true);
//			} else {
//				reportStatusFail(methodName + "Invalid Quote Status "
//						+ sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText) + " in Quote Details Page",
//						true);
//			}
//
//			// Verify Fraud check status in Quote details
//			sf.seleU.hardwait(2000);
//			if (sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText).trim()
//					.equals(creditCheckStatus)) {
//
//				//	sf.dataInput.notReqStatus
//				reportStatusPass(methodName + " verified Quote Status for credit check is " + fraudCheckStatus
//						+ " in quote details page", true, true);
//			} else {
//				reportStatusFail(methodName + "Invalid Quote Status "
//						+ sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText) + " in Quote Details Page",
//						true);
//			}
//
//			// Verify Validation check status in Quote details
//			sf.seleU.hardwait(2000);
//			if (sf.seleU.getTextFromWebElement(sf.qd.quoteValidationCheckStatusText).trim()
//					.equals(validationCheckStatus)) {
//			
//				reportStatusPass(methodName + " verified Quote Status for credit check is " + validationCheckStatus
//						+ " in quote details page", true, true);
//			} else {
//				reportStatusFail(methodName + "Invalid Quote Status "
//						+ sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText) + " in Quote Details Page",
//						true);
//			}
//
//			// Click on Quote Account Link
//			//			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
//			//			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);
//
//			sf.seleU.hardwait(5000);
//		} catch (Throwable e) {
//			reportStatusFail(" Error in selecting account from Quote details page", e);
//			e.printStackTrace();
//		}
//	}

	/**Author - Chethan K
	 * @throws IOException
	 * 
	 *                     1.verify contract status after revoking esign
	 * 
	 * 
	 */
	public void verifyContractStatus() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";	
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText)
					.equals(Global.dataInput.orderStatusCancelled)) {
				reportStatusPass(methodName + " verified Contract Status is " + Global.dataInput.orderStatusCancelled
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(methodName + "Invalid contract Status "
						+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
						true);
			}}
		catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}

	}

	/**Author - Chethan K
	 * @throws IOException
	 * 
	 *                     1.Verify the Quote Status after credit check approved
	 * 
	 * 
	 */
	public void verifyQuoteStatusAfterCreditCheck(String quoteStatus, String creditCheckStatus) throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.hardwait(3000);
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			if (sf.qd.quoteDetailsTab.size() > 1) {
				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(1));
			} else {
				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			}
			sf.seleU.hardwait(3000);

			// Verify Quote Status is accepted
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(quoteStatus)) {
				reportStatusPass(methodName + " verified Quote Status is " + quoteStatus
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify credit check status in Quote details
			sf.seleU.hardwait(2000);
			if (!creditCheckStatus.equals("")) {
				if (sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText).trim().equals(creditCheckStatus)) {
					Global.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);
					reportStatusPass(
							methodName + " verified creditCheck Status is " + creditCheckStatus + " in quote details page",
							true, true);
				} else {
					reportStatusFail(methodName + "Invalid creditCheck Status "
							+ sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText)
							+ " in Quote Details Page", true);
				}
			}

			// Click on Quote Account Link
			//			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			//			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Verify the Quote Status after fraud check approved
	 *  
	 */
	public void verifyQuoteStatusAfterFraudCheck(String quoteStatus, String fraudCheckStatus) throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.hardwait(3000);
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.seleU.hardwait(3000);

			// Verify Quote Status is accepted
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(quoteStatus)) {
				reportStatusPass(methodName + " verified Quote Status is " + quoteStatus
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify fraud check status in Quote details
			SFDC_OrderDetails_Page.verifyCreditAndFraudChecks("","Approved");
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Quote Details Tab
	 * 
	 *                     2.Verify Quote Status is accepted
	 * 
	 *                     3.Click on Quote Account Link
	 */
	public void openOpportunityFromQuotePage() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.hardwait(3000);
			// Click on Quote Details Tab
			sf.seleU.clickElementByJSE(sf.qd.opportunityNameLink);
			reportStatusPass(methodName + " Clicked on opportunity Name Link", true, false);

			// Verify Quote Status is accepted
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted)) {
				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.quoteStatusAccepted
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Click on Quote Account Link
			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}
	}

	/**Author - Chethan K
	 * @throws IOException
	 * 
	 *                     1.Click on ResumeQuotePage
	 * 
	 * 
	 */
	public void clickOnResumeQuotePage() throws IOException {
		try {
			String methodName = "SFDC_Resume Quote @: ";

			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();

			sf.seleU.clickElementByJSE(sf.siteCon.resumeQuoteButton);
			reportStatusPass(methodName + " Clicked on Resume Quote Button", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Clicking resume quote", e);
			e.printStackTrace();
		}
	}
	/*
	 * Author - Chethan K
	 * @throws IOException
	 * 
	 *  login as fraudOps to paas/reject NFDB validation
	 *  login as AE to check the validation check
	 */ 				

	public void verifyValidationCheck(String nfdbAction) throws IOException {
		String methodName = "SFDC_verifyValidationCheck @: ";

		try {
			// check if Validation check status is required/Accounts Receivable
			if (Global.dataInput.validationCheckStatus.equals("Required") || Global.dataInput.validationCheckStatus.equals("Accounts Receivable") )
			{
				String quoteName = Global.dataInput.quoteName;
				reportStatusPass(methodName + " Quote name is " + quoteName, true, false);	
				performNFDBPassReject(quoteName, nfdbAction);
			}

		}catch (Throwable e) {
			reportStatusFail(" Error in verify ValidationCheck method", e);
			e.printStackTrace();
		}
	}

	/**
	 * Author - Chethan K
	 * @throws IOException
	 * 
	 * 			1. Checks if the fraud and credit check is in progress and 
	 * 				then approves the credit and fraud review
	 */
	public void verifyCreditFraudStatus(String isCreditRequired, String isFraudRequired, String isApprove) throws IOException {
		String creditQuoteName = "", fraudQuoteName = "", isCreditStatus = "", isFraudStatus = "";
		String methodName = "SFDC_verifyCreditFraudStatus @: ";
		sf.seleU.refreshPage();
		sf.seleU.hardwait(3000);
		try {
			if (isCreditRequired.equals("Yes")) {
				sf.seleU.hardwait(2000);
				creditQuoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteApprovalNumber.get(0));
				reportStatusPass(methodName + " Credit Quote name is " + creditQuoteName, true, false);
				isCreditStatus = sf.seleU.getTextFromWebElement(sf.qd.fraudOrCreditRequired.get(0));
				reportStatusPass(methodName + " Credit Status is " + isCreditStatus, true, false);
			}
			if (isFraudRequired.equals("Yes")) {
				int index = 1;
				if (!isCreditRequired.equals("Yes")) {
					index = 0;
				}
				sf.seleU.hardwait(2000);
				fraudQuoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteApprovalNumber.get(index));
				reportStatusPass(methodName + " Fraud Quote  name is " + fraudQuoteName, true, false);
				isFraudStatus = sf.seleU.getTextFromWebElement(sf.qd.fraudOrCreditRequired.get(index));
				reportStatusPass(methodName + " Fraud Status is " + isFraudStatus, true, false);
			}


			if (isCreditStatus.equals("In Progress") || isCreditStatus.equals("Required")) // check if In Progress or Required

			{
				checkCreditOrFraud(creditQuoteName, true, isApprove);
			}
			if (isFraudStatus.equals("In Progress"))// check if In Progress for fraud
			{
				checkCreditOrFraud(fraudQuoteName, false, isApprove);
			}

		}catch (Throwable e) {
			reportStatusFail(" Error in verifying Credit/Fraud Status method", e);
			e.printStackTrace();
		}
	}
	/**Author - Chethan K
	 * @throws IOException
	 * 
	 *                     1.Click on Related Tab
	 *                     2.Select the contract number
	 *                     3.click on Revoke Esign button
	 * 
	 * 
	 */
	public void revokeESign() throws IOException {
		try {

			String methodName = "SFDC_revokeESign @: ";
			// Click on related Tab
			sf.seleU.clickElementByJSE(sf.siteCon.relatedButton);
			sf.seleU.clickElementByJSE(sf.siteCon.contractsButton.get(5));
			reportStatusPass(methodName + " Clicked on contracts Button", true, false);
			//
			sf.seleU.clickElementByJSE(sf.siteCon.revokeESignButton);


		} catch (Throwable e) {
			reportStatusFail(" Error in Clicking on  contracts ", e);
			e.printStackTrace();
		}
	}



	/** Author - Chethan K
	 * @throws Exception
	 * 
	 *                   Get AE details
	 */
	public void GetAEAndProductDetails(Hashtable<String, String> dataTable) throws Exception
	{
		try {
			String methodName = "SFDC_GetAEDetails @: ";
			// Capture Account Name			
			Global.dataInput.aeEmailId = sf.seleU.getTextFromWebElement(sf.co.accountName);
			// Capture AE name
			sf.seleU.clickElementByJSE(sf.co.opportunityOwner);
			Global.dataInput.aeName = sf.seleU.getTextFromWebElement(sf.co.aeName);
			Global.dataInput.aeEmailId = sf.seleU.getTextFromWebElement(sf.co.aeEmailID);
			reportStatusPass(methodName + "AE Name: " + Global.dataInput.aeName + " "+ "AE email id:" + Global.dataInput.aeEmailId, true, false);

			//capture product name
			if (dataTable.get("Product Name").contains(" with") && dataTable.get("Product Name").contains(" Bundle")) {
				Global.dataInput.selectedProduct = dataTable.get("Product Name").replace(" with", ",");
				Global.dataInput.selectedProduct = Global.dataInput.selectedProduct.substring(0,
						Global.dataInput.selectedProduct.indexOf(" Bundle"));
			}
			else if (dataTable.get("Product Name").contains(" and") && dataTable.get("Product Name").contains(" Bundle")) {
				Global.dataInput.selectedProduct = dataTable.get("Product Name").replace(" and", ",");
				Global.dataInput.selectedProduct = Global.dataInput.selectedProduct.substring(0,
						Global.dataInput.selectedProduct.indexOf(" Bundle"));
			}
			else
			{
				Global.dataInput.selectedProduct = dataTable.get("Product Name");
			}
			reportStatusPass(methodName + "Selected Product: " + Global.dataInput.selectedProduct, true, false);
			//return back
			//sfdc.home.closeLastOpenedTab();
		} catch (Throwable e) {
			reportStatusFail("Error in getting AE details  ", e);
			e.printStackTrace();
		}
	}


	/** Author - Chethan K
	 * @throws IOException
	 * 
	 *                     1.Logout from AE account
	 *                     2.Login with creditOps/FraudOps profile 
	 *                     3.Approve the credit / Fraud approval request
	 * 
	 * 
	 */
	public void checkCreditOrFraud(String quoteName, boolean isCredit, String isApprove) throws IOException {
		String methodName = "SFDC_checkCreditOrFraud @: ";
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			int quoteNameCount;
			boolean success = false;
			if(isCredit)
			{
				//Login as CreditOps to approve/reject the credit check
				sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
				reportStatusPass(methodName + " Logged in as Profile_CreditOps",true, true);
			}
			else
			{
				//Login as FraudOps to approve/reject the fraus check
				sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
				sfdc.home.closeTabIfOpenWithRefresh();
				reportStatusPass(methodName + "Logged in as Profile_FraudOps",true, true);
			}
			sfdc.home.closeTabIfOpen();

			sfdc.home.openR4BQuoteApproval();	
			if(sf.seleU.isElementDisplayed(sf.home.r4bSearchBox )) {
				//find the search box to search the quote
				sf.seleU.clickElementByJSE(sf.home.r4bSearchBox);
				//enter the sf.dataInput.quoteName
				sf.seleU.enterText(sf.home.r4bSearchBox, quoteName);
				sf.home.r4bSearchBox.sendKeys(Keys.RETURN);
			} else {
				sf.seleU.clickElementByJSE(sf.home.searchBoxButton);
				sf.seleU.wait(2000);
				//enter the sf.dataInput.quoteName
				sf.seleU.enterText(sf.home.searchBox, quoteName);
				sf.home.searchBox.sendKeys(Keys.RETURN);
				sf.seleU.wait(2000);
			}

			List<WebElement> quoteElement = getSpecificQuoteNameElement(quoteName);
			if (quoteElement.size() > 1)
				sf.seleU.clickElementByJSE(getSpecificQuoteNameElement(quoteName).get(1));
			else
				sf.seleU.clickElementByJSE(getSpecificQuoteNameElement(quoteName).get(0));

			reportStatusPass(methodName + "Clicked on Quote approval number",true, true);

			//click on Related tab
			sf.seleU.clickElementByJSE(sf.r4Bquote.relatedButton);
			if(isApprove.equals("Approve")){
				//click on Approve button
				sfdc.r4Bquoteapp.approveR4BQuote();
				reportStatusPass(methodName + "Approved Credit/Fraud",true, true);
			}
			else {
				//click on Reject button
				sfdc.r4Bquoteapp.rejectR4BQuote(); 
				reportStatusPass(methodName + "Rejected Credit/Fraud",true, true);
			}

			sfdc.home.logout();


		} catch (Throwable e) {
			reportStatusFail(methodName +" Error in verifying  Credit/Fraud Check", e);
			e.printStackTrace();
		}
	}



	/**
	 * Author - Chethan K
	 * 
	 * @throws IOException
	 * 
	 *                     1.Verify the Quote Status after validation,credit,fraud
	 *                     checks
	 * 
	 * 
	 */
	public void verifyQuoteStatusAfterValidationCheck(String quoteStatus, String quoteValidationCheckStatusText,
			String creditCheckStatus, String fraudCheckStatus) throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			WebElement ActualElement;
			sf.seleU.hardwait(3000);
			//click on quote details
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.hardwait(4000);
			if (sf.qd.quoteDetailsTab.size() > 1) {
				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(1));
			} else {
				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			}
			reportStatusPass(methodName + " Clicked on  Quote Details Tab", true, true);
			sf.seleU.hardwait(3000);
			
			// Verify Quote Status in Quote details
			if (sf.qd.quoteStatusValueTextList.size() > 1)
				ActualElement = sf.qd.quoteStatusValueTextList.get(1);
				else
					ActualElement = sf.qd.quoteStatusValueTextList.get(0);
			if (sf.seleU.getTextFromWebElement(ActualElement).equals(quoteStatus)) {
				reportStatusPass(methodName + " verified Quote Status is " + quoteStatus + " in quote details page",
						true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify validation check status in Quote details
			sf.seleU.hardwait(2000);
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteValidationCheckStatusText)
					.equals(quoteValidationCheckStatusText)) {
				reportStatusPass(methodName + " verified Validation Check Status is " + quoteValidationCheckStatusText
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(methodName + "Invalid ValidationCheck Status "
						+ sf.seleU.getTextFromWebElement(sf.qd.quoteValidationCheckStatusText)
						+ " in Quote Details Page", true);
			}

			// Verify credit check status in Quote details
			sf.seleU.hardwait(2000);
			if (!creditCheckStatus.equals("")) {
				if (sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText).trim().equals(creditCheckStatus)) {
					Global.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);
					reportStatusPass(methodName + " verified creditCheck Status is " + creditCheckStatus
							+ " in quote details page", true, true);
				} else {
					reportStatusFail(methodName + "Invalid credit Check Status "
							+ sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText)
							+ " in Quote Details Page", true);
				}
			}

			if (!fraudCheckStatus.equals("")) {
				if (sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText).trim().equals(fraudCheckStatus)) {
					Global.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);
					reportStatusPass(methodName + " verified fraudCheck Status is " + fraudCheckStatus
							+ " in quote details page", true, true);
				} else {
					reportStatusFail(methodName + "Invalid fraudCheck Status "
							+ sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText)
							+ " in Quote Details Page", true);
				}
			}

			// Click on Quote Account Link
			// sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			// reportStatusPass(methodName + " Clicked on Account link on Quote Details
			// Page", true, false);

			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting account from Quote details page", e);
			e.printStackTrace();
		}
	}




	/** Author - Chethan K
	 * @throws IOException
	 * 
	 *                     1.Logout from AE account
	 *                     2.Login with creditOps/FraudOps profile 
	 *                     3.Approve the credit / Fraud approval request
	 * 
	 * 
	 */
	public void performNFDBPassReject(String quoteName, String nfdbAction) throws IOException {
		String methodName = "SFDC_performNFDBPassReject @: ";
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			int quoteNameCount;
			boolean success = false;

			//Login as FraudOps to pass/reject the NFDB check
			sfdc.login.loginToSFDC(InputData.Profile_FraudOps);

			reportStatusPass(methodName + "Logged in as Profile_FraudOps",true, true);		

			sfdc.home.closeTabIfOpen();

			sf.seleU.clickElementByJSE(sf.home.searchBoxFraudOps);
			reportStatusPass(methodName + "clicked on global search ",true, true);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.searchBoxFraud);
			reportStatusPass(methodName + "Clicked on search Box",true, true);	
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchBoxFraud, quoteName);
			reportStatusPass(methodName + "Enterd quote name",true, true);	
			sf.seleU.enterText(sf.home.searchBoxFraud, Keys.ENTER);



			List<WebElement> quoteElement = getSpecificQuoteNameElement(quoteName);

			if (quoteElement.size() > 1)
				sf.seleU.clickElementByJSE(quoteElement.get(1));
			else
				sf.seleU.clickElementByJSE(quoteElement.get(0));

			reportStatusPass(methodName + "Clicked on Quote name",true, true);

			//click on Related tab
			sf.seleU.clickElementByJSE(sf.r4Bquote.relatedButton);
			if(nfdbAction.equals("Pass")){
				sf.seleU.hardwait(3000);
				//click on pass button
				sfdc.r4Bquoteapp.passNFDB();
				reportStatusPass(methodName + "Approved NFDB",true, true);
			}
			else {
				//click on Reject button
				sfdc.r4Bquoteapp.rejectNFDB(); 
				reportStatusPass(methodName + "Rejected NFDB",true, true);
			}

			//sfdc.home.logout();


		} catch (Throwable e) {
			reportStatusFail(methodName +" Error in performing NFDB pass/reject action", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param product
	 * @param attributeColumn
	 * @return
	 * 
	 *         Get element based on the quote approval num
	 *         column
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	public List<WebElement> getSpecificQuoteNameElement(String quoteApprovalNum) throws IOException {

		List<WebElement> prod_attr_ele = null;
		//		String attr_locator = "//a[contains(@title,'"+ quoteApprovalNum +"')]";
		String attr_locator = "//tbody//tr//th//span[@class='slds-grid slds-grid--align-spread']//a[contains(.,'" + quoteApprovalNum + "')]";
		try {
			prod_attr_ele = sf.seleU.findElements(By.xpath(attr_locator));

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return prod_attr_ele;
	}


	/**
	 * @throws IOException
	 * 
	 *                     1.Click on ResumeQuotePage
	 * 
	 * 
	 */
	public void clickOnOpportunityNameLink() throws IOException {
		try {
			String methodName = "SFDC_Opportunity_Name_Link@: ";

			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.seleU.hardwait(1000);
			sf.seleU.ScrolltoElement(sf.qd.opportunityNameLink);
			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.qd.opportunityNameLink);
			reportStatusPass(methodName + " Clicked on Opp Name Link", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Clicking Opp Name Link", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on ResumeQuotePage
	 * 
	 * 
	 */
	public void verifyQuoteNumberInQuotePage() throws IOException {
		try {
			String methodName = "SFDC_Verify_QuoteNumber_QuotePage@: ";

			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			if(sf.seleU.isElementDisplayed(sf.qd.quoteNameText)) {
				sf.seleU.waitForLoading();
				Global.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + "Quote Name is present : " +Global.dataInput.quoteName, true, false);
				InputData.quoteNumber = sf.seleU.getTextFromWebElement(sf.qd.quoteNumberText);
				reportStatusPass(methodName + "Quote Number is present : " +InputData.quoteNumber , true, false);
			} else {
				reportStatusFail("Quote Number is not present in Quote page", true);
			}

			sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail(" Error in Clicking Opp Name Link", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(
						fieldName + " is present" + " with value as " + sf.seleU.getTextFromWebElement(element), true,
						true);
			} else {
				reportStatusPass(
						fieldName + " is not present" + " with value as " + sf.seleU.getTextFromWebElement(element),
						true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}





	/**
	 * @throws IOException
	 * 
	 *                     Capture the service account address in Quote details page
	 *                     
	 *                     1.Click on service account from Quote Details page
	 *                     
	 *                     2.Click on premises
	 *                     
	 *                     3.Capture the street address,city,state,postal code
	 *                     
	 * 
	 * 
	 */
	public void serviceAddressInQuoteDetails(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Opportunity_Name_Link@: ";

			if(dataTable.get("Credit_Required").equals("No") && dataTable.get("Fraud_Required").equals("No")) {
				sf.seleU.switchToDefaultContent();
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTabs);
				reportStatusPass( methodName+ " clicked on Details Tab", true, false);
				sf.seleU.hardwait(3000);
				//click quote
				sf.seleU.clickElementByJSE(sf.orDetails.QuoteLink);
				reportStatusPass( methodName+ " clicked on Quote", true, false);

			}
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
			if (sf.qd.quoteDetailsTab.size() > 1)
				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(1));
			else
				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			reportStatusPass( methodName+ " clicked on Quote Details Tab", true, false);
			sf.seleU.hardwait(1000);
			Global.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);
			reportStatusPass(methodName + " Quote name is : " + Global.dataInput.quoteName, true, false);
			Global.dataInput.creditCheckStatus = sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText);
			reportStatusPass(methodName + " Credit Check Status is : " + Global.dataInput.creditCheckStatus, true, false);
			Global.dataInput.fraudCheckStatus = sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText);
			reportStatusPass(methodName + " Fraud Check Status is : " + Global.dataInput.fraudCheckStatus, true, false);
			Global.dataInput.validationCheckStatus = sf.seleU.getTextFromWebElement(sf.qd.quoteValidationCheckStatusText);
			reportStatusPass(methodName + " Validation Check Status is : " + Global.dataInput.validationCheckStatus, true, false);

			//		sf.seleU.ScrolltoElement(sf.qd.opportunityNameLink);
			//		sf.seleU.hardwait(1000);
			// Click on Service Account
			if (sf.qd.quoteDetailsTab.size() > 1)
				sf.seleU.clickElementByJSE(sf.qd.serviceAccount.get(1));
			else
				sf.seleU.clickElementByJSE(sf.qd.serviceAccount.get(0));
			reportStatusPass(methodName + " Clicked on Service Account", true, false);
			//		sf.dataInput.aeName = sf.seleU.getTextFromWebElement(sf.co.aeName);
			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.qd.premises);
			reportStatusPass(methodName + " Clicked on Premises", true, false);
			sf.seleU.hardwait(1000);
			Global.dataInput.serviceStreetAddress=sf.seleU.getTextFromWebElement(sf.qd.streetAddress);
			reportStatusPass(methodName +" StreetAddress is : " + Global.dataInput.serviceStreetAddress, true, false);
			Global.dataInput.serviceCity =sf.seleU.getTextFromWebElement(sf.qd.city);
			Global.dataInput.serviceState=sf.seleU.getTextFromWebElement(sf.qd.state);
			Global.dataInput.servicePostalCode=sf.seleU.getTextFromWebElement(sf.qd.postalCode);
			Global.dataInput.serviceCountry=sf.seleU.getTextFromWebElement(sf.qd.country);


			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in getting serviceAddressInQuoteDetails", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 *                     
	 */
	public void clickRelatedTab() throws IOException {
		try {
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.qd.quoteRelatedTab.get(0));
			reportStatusPass(" Clicked on Related Tab", true, false);
			sf.seleU.hardwait(1000);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking Quote Related Tab", e);
			e.printStackTrace();
		}
	}

	public void verifyQuoteStatusWACC(String status) throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.waitForLoading();
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			//	verifyFieldPresent("Resume Quote Button ", sf.siteCon.resumeQuoteButton);
			sf.seleU.waitForLoading();
			// sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
			sf.seleU.waitForLoading();
			// Verify Quote Status is accepted
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).contains(status) 
					|| sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equalsIgnoreCase(status)) {
				reportStatusPass(methodName + " verified Quote Status is " + status
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}
		}
		catch (Throwable e) {
			reportStatusFail(" Error in Quote status verification  verification", e);
			e.printStackTrace();
		}


	}


	/**Author - Chethan K
	 * @throws IOException
	 * Check if Task is created for AE to select site contact
	 * for not Providing site contact information.
	 * Validate the Task subject                                     
	 */

	public void verifyTaskCreatedForAE() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";

			if (sf.seleU.isElementDisplayed(sf.qd.notificationIcon.get(0))) {
				sf.seleU.clickOnElement(sf.qd.notificationIcon.get(0));
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.qd.taskNotificationText);
			}
			reportStatusPass(methodName + " Clicked on notification Icon", true, true);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.qd.taskNotificationText);
			reportStatusPass(methodName + " Clicked on notification Text", true, true);
			// Verify the Task Subject
			if (sf.seleU.getTextFromWebElement(sf.qd.taskSubject.get(sf.qd.taskSubject.size()-1)).equalsIgnoreCase(Constants.Task_Subject)) {
				reportStatusPass(methodName + " verified Task Subject ", true, true);
			} else {
				reportStatusFail(
						methodName + "Error in validating Task Subject "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.qd.orderID);
			reportStatusPass(methodName + " Clicked on Order number", true, true);

		}
		catch (Throwable e) {
			reportStatusFail(" Error in Quote status verification  verification", e);
			e.printStackTrace();
		}}



	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Quote Details Tab
	 * 
	 *                     2.Verify Quote Status is accepted
	 * 
	 *                     3.Verify Fraud Status should be Not Required  based on billing country as Canada
	 */
	public void verifyFraudStatusIfCanada() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
			sf.seleU.hardwait(20000);
			// Verify Quote Status is finalized
			if( (sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted) ) || 
					(sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusFinalized) )) {
				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.quoteStatusFinalized
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify Fraud Review status in Quote details
			sf.seleU.hardwait(4000);
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText).trim()
					.equals(Global.dataInput.notReqStatus)) {

				//	sf.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);

				reportStatusPass(methodName + " verified Quote status= Accepted/Finalized with Fraud review status=Not required  when billing address country=canada", true, true);
			} else {
				reportStatusFail(methodName + "Verification fails Quote status= Accepted/Finalized with Fraud review status=Not required  when billing address country=canada "
						+ sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText) + " in Quote Details Page",
						true);
			}

			// Click on Quote Account Link
			//			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			//			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Quote and Its fields from Quote details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Quote Details Tab
	 * 
	 *                     2.Verify Quote Status is accepted
	 * 
	 *                     3.Verify Fraud Status should be In progress based on Business acc with High Risk FNF
	 */
	public void verifyFraudStatusIfHighRiskFNF() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
			sf.seleU.hardwait(20000);
			// Verify Quote Status is finalized
			if ((sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted)) ||
					(sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusFinalized)) ){
				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.quoteStatusFinalized
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify Fraud Review status in Quote details
			sf.seleU.hardwait(2000);
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText).trim()
					.equals(Global.dataInput.reqStatus)) {

				//	sf.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);

				reportStatusPass(methodName + " Verified as AE if Quote is created on business account with High Risk FNF than automatically route for Fraud Review.", true, true);
			} else {
				reportStatusFail(methodName + "Verification fails - When AE if Quote is created on business account with High Risk FNF than automatically route for Fraud Review. "
						+ sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText) + " in Quote Details Page",
						true);
			}

			// Click on Quote Account Link
			//			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			//			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Quote and Its fields from Quote details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Quote Details Tab
	 * 
	 *                     2.Verify Quote Status is accepted
	 * 
	 *                     3.Verify Fraud Status should be In progress based on High Risk FNF
	 */
	public void verifyFraudStatusIfNotCanada() throws IOException {
		try {
			String methodName = "SFDC_Quote Details@: ";
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);
			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
			sf.seleU.hardwait(20000);
			// Verify Quote Status is finalized
			if ((sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted)) ||
					(sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText).equals(Global.dataInput.quoteStatusAccepted)) ){
				reportStatusPass(methodName + " verified Quote Status is " + Global.dataInput.quoteStatusFinalized
						+ " in quote details page", true, true);
			} else {
				reportStatusFail(
						methodName + "Invalid Quote Status "
								+ sf.seleU.getTextFromWebElement(sf.qd.quoteStatusValueText) + " in Quote Details Page",
								true);
			}

			// Verify Fraud Review status in Quote details
			sf.seleU.hardwait(2000);
			if (sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText).trim()
					.equals(Global.dataInput.reqStatus)) {

				//	sf.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);

				reportStatusPass(methodName + " verified Quote status= Accepted/Finalized with Fraud review status=In Progress  when billing address country=International", true, true);
			} else {
				reportStatusFail(methodName + "Verification fails Quote status= Accepted/Finalized with Fraud review status=In Progress  when billing address country=International "
						+ sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText) + " in Quote Details Page",
						true);
			}

			// Click on Quote Account Link
			//			sf.seleU.clickElementByJSE(sf.qd.quoteAccountNameValueLinkText);
			//			reportStatusPass(methodName + " Clicked on Account link on Quote Details Page", true, false);

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Quote and Its fields from Quote details page", e);
			e.printStackTrace();
		}

	}
	public void clickOnQuoteTab_DeclineSce(String accountName) {
		String path ="(//a[contains(@title,'"+accountName+"-"+"') and contains(@class,'tabHeader slds-tabs--default__link slds-p-righ')])[1]";
		WebElement element=driver.findElement(By.xpath(path));
		sf.seleU.clickElementByJSE(element);
	}

	/**
	 * @author Satish.Doraiswamy
	 *Apr. 5, 2022
	 * Validating Resume Quote and New Note Button status
	 */
	public void validateResumeQuoteAndOtherButtons() throws IOException {
		boolean resumeButton=false;
		boolean newNoteButton=false;
		try {
			String methodName = "SFDC_Resume Quote @: ";

			// Click on Quote Details Tab
			sf.seleU.switchToDefaultContent();

			resumeButton=sf.seleU.isElementEnabled(sf.siteCon.resumeQuoteButton);
			newNoteButton=sf.seleU.isElementEnabled(sf.siteCon.newNoteButton);
			if(resumeButton && newNoteButton) {
				reportStatusPass(methodName + " Resume Quote & New Note Button is Enabled !!", true, false);
			}else {
				reportStatusFail(methodName + " Resume Quote & New Note Button is Disabled !!", true);	
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Clicking resume quote", e);
			e.printStackTrace();
		}
	}
	
	public void clickOnResumeQuoteButton() throws Exception {
		sf.seleU.clickElementByJSE(sf.siteCon.resumeQuoteButton);
		sf.seleU.waitTillLoading();
	}
	
	/**
	 * @author Satish.Doraiswamy
	 *May 2, 2022
	 * Edit Total Credit limit Assigned for Credit Ops Account
	 * @throws IOException 
	 */
	public void editCreditLimitAssignedVale(String creditLimitAssignedValue) throws IOException {
		try {
			String methodName = "SFDC_Resume Quote @: ";

			sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.qd.quoteEditTotalCreditLimitButton);
			sf.seleU.waitElementToBeVisible(sf.qd.quoteEditTotalCreditLimitTextBox);
			sf.seleU.clearAndEnterText(sf.qd.quoteEditTotalCreditLimitTextBox, creditLimitAssignedValue);
			sf.seleU.waitElementToBeVisible(sf.qd.quoteEditTotalCreditLimitSaveButton);
			sf.seleU.clickElementByJSE(sf.qd.quoteEditTotalCreditLimitSaveButton);
			
		} catch (Throwable e) {
			reportStatusFail(" Error in Clicking resume quote", e);
			e.printStackTrace();
		}
	}

}

