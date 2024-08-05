package sfdc.pages.service;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class Email_Mailinator_Page extends MyListener {

	// Creating all the pages Object to interact with pages

	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Email_Mailinator_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Mailinator@: ";
	}

	/**
	 * @throws InterruptedException Opens a new tab, and Navigate to
	 *                              "Mailinator.com" URL click on search box enter
	 *                              the communities user click on go button lands on
	 *                              email inbox page and verify it click on the
	 *                              first email verifies the subject value with the
	 *                              case number
	 * 
	 */

	public void verifyEmailAtMailinator() throws IOException {
		try {
			String communitiesUser = sf.commData.communities_userid.split("@")[0];
			sf.seleU.openNewTab();
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(2);
			sf.seleU.hardwait(2000);

			// open mailinator.com
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, communitiesUser);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);

			if (sf.seleU.getCurrentUrl().contains(communitiesUser)) {
				reportStatusPass(methodName + "Verified : Communities user's inbox", true, true);
			} else {

				reportStatusFail(methodName, true);
			}
			reportStatusPass(methodName + "Verified Subject : Sandbox: Your Case # " + addOn_1 + " has been created",
					true, true);
			sf.seleU.hardwait(2000);
			sf.seleU.clickOnElement(sf.mailinator.emailMailinator);

			sf.seleU.hardwait(5000);
			sf.seleU.switchToFrame(sf.mailinator.frameEmailBody);

			if (sf.seleU.isElementPresent(sf.mailinator.caseIDMailinator)) {
				reportStatusPass(
						methodName + "Case number is present in email body :"
								+ sf.seleU.getTextFromWebElement(sf.mailinator.caseIDMailinator).toString(),
								true, true);
			} else {
				reportStatusFail(methodName + "Cannot find Case Number in Email Body", true);
			}
			if (sf.seleU.isElementDisplayed(sf.mailinator.subjectMailinator)) {
				reportStatusPass(
						methodName + "Subject is present in email body : "
								+ sf.seleU.getTextFromWebElement(sf.mailinator.subjectMailinator).toString(),
								true, true);
			} else {
				reportStatusFail(methodName + "Cannot find Subject in Email Body", true);

			}
			if (sf.seleU.isElementDisplayed(sf.mailinator.dateTimeMailinator)) {
				reportStatusPass(
						methodName + "Created date time is present in email body : "
								+ sf.seleU.getTextFromWebElement(sf.mailinator.dateTimeMailinator).toString(),
								true, true);
			} else {
				reportStatusFail(methodName + "Cannot find Case Created timestamp in Email Body", true);

			}

			if (sf.seleU.isElementDisplayed(sf.mailinator.verbiageCommunityLink)) {
				reportStatusPass(
						methodName + "Verbiage Community Link : "
								+ sf.seleU.getTextFromWebElement(sf.mailinator.verbiageCommunityLink).toString(),
								true, true);
			} else {
				reportStatusFail(methodName + "Cannot find Verbiage for community link in Email Body", true);

			}
		} catch (Exception e) {
			reportStatusFail(methodName + " Error in verifying the Email", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws InterruptedException Opens a new tab, and Navigate to
	 *                              "Mailinator.com" URL click on search box enter
	 *                              the contact email id click on go button lands on
	 *                              email inbox page and verify it click on the
	 *                              first email verifies the subject value with the
	 *                              quote email details
	 * 
	 */

	public void verifyQuoteDetailsInEmailAtMailinator(String emailID, String textToVerify, boolean isSiteContact)
			throws IOException {
		try {
			String contactEmailId = emailID.split("@")[0];
			sf.seleU.openNewTab();
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(2);
			sf.seleU.hardwait(2000);

			// open mailinator.com in the new tab using the mailinator URL
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			// Click on the search field to search the emailId
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, contactEmailId);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);

			// Verify if the mailbox has the email
			if (sf.seleU.getCurrentUrl().contains(contactEmailId)) {
				reportStatusPass(methodName + "Verified : mailinator contact order inbox", true, true);
			} else {

				reportStatusFail(methodName, true);
			}

			// Click on the emailsubject in the Inbox to open the email
			sf.seleU.clickOnElement(sf.mailinator.emailSubject);
			reportStatusPass(methodName + "Verified : mailinator inbox", true, true);
			// Verify the recieverEmailId in the email
			verifyFieldValue("ReceiverEmaiID ", sf.cableTaskItems.toReceiverID, contactEmailId);

			sf.seleU.hardwait(5000);
			sf.seleU.switchToFrame(sf.mailinator.frameEmailBody);

			sf.seleU.wait(2000);
			// Verify the paragraph in the email
			if (isSiteContact) {
				sf.seleU.ScrolltoElement(sf.mailinator.paragraphInMailinatorEmailSC);
				verifyFieldValue("Email Body", sf.mailinator.paragraphInMailinatorEmailSC, textToVerify);
			} else {
				sf.seleU.ScrolltoElement(sf.mailinator.paragraphInMailinatorEmail);
				verifyFieldValue("Email Body", sf.mailinator.paragraphInMailinatorEmail, textToVerify);
			}
			// Close the mailinator tab & point back to SF application
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Exception e) {
			reportStatusFail(methodName + "  Error in verifying the Email Attributes", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException calls the corresponding EMail method depending on the
	 *                     quote review type
	 * Validate the email received by site contact based on credit/fraud check required/not required
	 */
	public void verifyEmailForGBJ(Hashtable<String, String> dataTable, boolean isSiteContact) throws IOException {
		try {
			if (InputData.env.equals("ITDEVSTAGE")) {
				String emailBody = "";
				if (dataTable.get("Region").contains("ATL")) {
					sf.dataInput.businessAccName  = InputData.account_ATL;
				} else {
					sf.dataInput.businessAccName  = InputData.account_ON;}

				//if (dataTable.get("Credit_Required").equals("Yes") || dataTable.get("Fraud_Required").equals("Yes")) {
				emailBody = "Hello " + sf.dataInput.siteContactName +",\n\n" + Constants.EMAIL_SITECONTACT_TEXT1 + sf.dataInput.signingAuthName + " "
						+ Constants.EMAIL_SITECONTACT_TEXT2 + sf.dataInput.serviceStreetAddress + " "
						+ sf.dataInput.serviceCity + ", " + sf.dataInput.serviceState + " "
						+ sf.dataInput.servicePostalCode + Constants.EMAIL_SITECONTACT_TEXT3 + "\n\n"
						+ "Business Name: " + sf.dataInput.businessAccName + "\n" + "Products: "
						+ sf.dataInput.selectedProduct + "\n\n\n"
						+ "Please contact me directly if you have any questions.\n" + sf.dataInput.aeName + "\n\n"
						+ "Rogers Communications\n" + "TEL"  + "\n" + "Email "
						+ sf.dataInput.aeEmailId;
				reportStatusPass("Captured Site contact email body when Credit or Fraud check is Yes",true, true);
				//				} else {
				//					emailBody = Constants.EMAIL_SITECONTACT_TEXT1 + sf.dataInput.primaryContact + " "
				//							+ Constants.EMAIL_SITECONTACT_TEXT2 + sf.dataInput.serviceLocation
				//							+ Constants.EMAIL_SITECONTACT_TEXT3;
				//					reportStatusPass("Captured Site contact email body when both Credit and Fraud is No",true, true);
				//				}
				// Added null check for Hybridcart flow
				if (dataTable.get("QuoteReview_Type") == null
						|| dataTable.get("QuoteReview_Type").contains("AcceptQuote")) {
					if (!isSiteContact) {
						reportStatusPass(methodName + "Signing Authority Email Id: " + sf.dataInput.sf.dataInput.signingAuthEmailIdValue, true,
								true);
						verifyQuoteDetailsInEmailAtMailinator(sf.dataInput.signingAuthEmailIdValue, Constants.EMAIL_ACCEPT_TEXT,
								false);
					} else {
						reportStatusPass(methodName + "Sitecontact Email Id: " + sf.dataInput.siteContactEmailId, true,
								true);
						verifyQuoteDetailsInEmailAtMailinator(sf.dataInput.siteContactEmailId, emailBody, true);
					}
				} else if (dataTable.get("QuoteReview_Type").contains("EmailQuote")) {
					if (!isSiteContact) {
						reportStatusPass(methodName + "Signing Authority Email Id: " + sf.dataInput.emailIdValue, true,
								true);
						verifyQuoteDetailsInEmailAtMailinator(sf.dataInput.emailIdValue, Constants.EMAIL_PROPOSAL_TEXT,
								false);
					} else {
						reportStatusPass(methodName + "Sitecontact Email Id: " + sf.dataInput.siteContactEmailId, true,
								true);
						verifyQuoteDetailsInEmailAtMailinator(sf.dataInput.siteContactEmailId,
								"Hello " + sf.dataInput.siteContactName + ",\n" + "\n"
										+ Constants.EMAIL_SITECONTACT_TEXT1 + sf.dataInput.primaryContact + " "
										+ Constants.EMAIL_SITECONTACT_TEXT2 + sf.dataInput.serviceLocation
										+ Constants.EMAIL_SITECONTACT_TEXT3,
										true);
					}
					// ESignature Flow
				} else if (dataTable.get("QuoteReview_Type").contains("ESignature")) {
					reportStatusPass("Esignature Email verification flow",true, true);
					reviewAndSignInEmailAtMailinator(sf.dataInput.signingAuthEmailIdValue,
							dataTable.get("Esignature_Status").toString());
				}
			}
		} catch (Exception e) {
			reportStatusFail(methodName + "  Error in verifying the Email in mailinator", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws InterruptedException Opens a new tab, and Navigate to
	 *                              "Mailinator.com" URL click on Review document
	 *                              enter Click on "Sign" & drag it on the contract
	 *                              document & complete the "Adopt & Sign" flow for
	 *                              the e-signatures.
	 * 
	 * 
	 */

	public void reviewAndSignInEmailAtMailinator(String emailID, String eSignStatus) throws IOException {
		try {

			String contactEmailId = emailID.split("@")[0];
			String fromEmailId = "dse_demo@docusign.net";
			String userReferenced = "Rogers Communications Inc.";
			String emailReferenced = "docusignintegration@rci.rogers.com";

			sf.seleU.openNewTab();
			sf.seleU.waitForLoading();
			sf.seleU.switchWindow(2);
			sf.seleU.waitForLoading();
			// open mailinator.com in the new tab using the mailinator URL
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			// Click on the search field to search the emailId
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.waitForLoading();
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, contactEmailId);
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);
			// Verify if the mailbox has the email
			sf.seleU.waitForLoading();
			if (sf.seleU.getCurrentUrl().contains(contactEmailId)) {
				reportStatusPass(methodName + "Verified : mailinator contact order inbox", true, true);
			} else {
				reportStatusFail(methodName, true);
			}
			//sf.seleU.refreshPage();
			//sf.seleU.hardwait(2000);
			// Click on the email subject in the Inbox to open the email
			sf.seleU.clickOnElement(sf.mailinator.emailSubjectEsign);
			reportStatusPass(methodName + "Verified : mailinator inbox", true, true);
			// Verify the recieverEmailId in the email
			verifyFieldValue("To email as", sf.cableTaskItems.toReceiverID, contactEmailId);
//			if (InputData_WA.product_type.equalsIgnoreCase("Wireless")) {
//				verifyFieldValue("From email as ", sf.cableTaskItems.fromReceiverID, "dse_NA3@docusign.net");
//			}
			
			//			verifyFieldValue("User referenced in the body as ", sf.cableTaskItems.userReferenced, userReferenced);
			//			verifyFieldValue("Email referenced in the body as ", sf.cableTaskItems.emailReferenced, emailReferenced);
			sf.seleU.waitForLoading();
			sf.seleU.switchToFrame(sf.mailinator.frameEmailBody);
			//sf.seleU.wait(2000);
			// click on Review button
			sf.seleU.clickElementByJSE(sf.mailinator.reviewDocumentInDocuSign);
			reportStatusPass(methodName + "Clicked on Review Document button in Docusign email in mailinator", true,
					true);
			sf.seleU.switchWindow(3);
			if (eSignStatus.contains("Signed")) {
				// click on checkbox in new window- I agree to use electronic records and
				sf.seleU.clickOnElement(sf.mailinator.disclosureCheckbox);
				// click on Continue button
				sf.seleU.clickOnElement(sf.mailinator.continueButton);
				reportStatusPass(methodName + "Clicked on Continue button in Docusign email in mailinator", true, true);
				// click on Start button
				sf.seleU.clickOnElement(sf.mailinator.startDocuSign);
				reportStatusPass(methodName + "Clicked on Start in Docusign email in mailinator", true, true);
				// click on pre-sign link
				sf.seleU.clickOnElement(sf.mailinator.signDocuSign);
				reportStatusPass(methodName + "Clicked on Sign in Docusign email in mailinator", true, true);
				sf.seleU.switchToActiveElement();
				// Adopt and sign new window
				sf.seleU.clickOnElement(sf.mailinator.adoptAndSign);
				reportStatusPass(methodName + "Clicked on Adopt and Sign button in Docusign email in mailinator", true,
						true);

				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.enterTitle))
					sf.seleU.clearAndEnterText(sf.mailinator.enterTitle, "Mrs");
				sf.seleU.waitForLoading();

				sf.seleU.clickOnElement(sf.mailinator.finishButton);
				reportStatusPass(methodName + "Clicked on Finish button in Docusign email in mailinator", true, true);
				// pop up will open and click on no thanks button
				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.noThanksButton)) {
					sf.seleU.clickOnElement(sf.mailinator.noThanksButton);
					reportStatusPass(methodName + "Clicked on No thanks button in Docusign email in mailinator", true,
							true);
				}
				// final continue button pop up comes
				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.finalContinueButton)) {
					sf.seleU.clickOnElement(sf.mailinator.finalContinueButton);
					reportStatusPass(methodName + "Clicked on final continue button in Docusign email in mailinator", true,
							true);
				}
				sf.seleU.waitForLoading();
				verifyFieldPresent("You have finished signing" , sf.mailinator.finishSigningText);

			} else {
				sf.seleU.clickOnElement(sf.mailinator.otherActionsDD);
				sf.seleU.clickOnElement(sf.mailinator.declineSign);
				sf.seleU.switchToActiveElement();
				sf.seleU.clickOnElement(sf.mailinator.declineContinue);
				sf.seleU.switchToActiveElement();
				sf.seleU.clickOnElement(sf.mailinator.declineContinueReason);
				reportStatusPass(methodName + "Clicked on Decline to Sign & Continue button", true,
						true);
			}
			// Close the mailinator tab & point back to SF application
			sf.seleU.switchWindow(1);
			// close tabs
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Exception e) {
			reportStatusFail(methodName + "  Error in verifying the Email Attributes", e);
			e.printStackTrace();
		}

	}

	/**Pankaj Agarwal 07/March/2022
	 * @throws InterruptedException Opens a new tab, and Navigate to
	 *                              "Mailinator.com" URL click on Review document
	 *                              enter Click on "Sign" & drag it for all the multiste products on the contract
	 *                              document & complete the "Adopt & Sign" flow for
	 *                              the e-signatures.
	 * 
	 * 
	 */

	public void reviewAndSignInEmailAtMailinator_ForMultisiteOrders(String emailID, String eSignStatus) throws IOException {
		try {

			String contactEmailId = emailID.split("@")[0];
			String fromEmailId = "dse_demo@docusign.net";
			String userReferenced = "Rogers Communications Inc.";
			String emailReferenced = "docusignintegration@rci.rogers.com";
			boolean flag = true;

			sf.seleU.openNewTab();
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(2);
			sf.seleU.hardwait(2000);
			// open mailinator.com in the new tab using the mailinator URL
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			// Click on the search field to search the emailId
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, contactEmailId);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);
			// Verify if the mailbox has the email
			sf.seleU.hardwait(2000);
			if (sf.seleU.getCurrentUrl().contains(contactEmailId)) {
				reportStatusPass(methodName + "Verified : mailinator contact order inbox", true, true);
			} else {
				reportStatusFail(methodName, true);
			}
			//sf.seleU.refreshPage();
			//sf.seleU.hardwait(2000);
			// Click on the email subject in the Inbox to open the email
			sf.seleU.clickOnElement(sf.mailinator.emailSubjectEsign);
			reportStatusPass(methodName + "Verified : mailinator inbox", true, true);
			// Verify the recieverEmailId in the email
			verifyFieldValue("To email as", sf.cableTaskItems.toReceiverID, contactEmailId);
			//			if (InputData_WA.product_type.equalsIgnoreCase("Wireless")) {
			//				verifyFieldValue("From email as ", sf.cableTaskItems.fromReceiverID, "dse_NA3@docusign.net");
			//			}

			//			verifyFieldValue("User referenced in the body as ", sf.cableTaskItems.userReferenced, userReferenced);
			//			verifyFieldValue("Email referenced in the body as ", sf.cableTaskItems.emailReferenced, emailReferenced);
			sf.seleU.hardwait(5000);
			sf.seleU.switchToFrame(sf.mailinator.frameEmailBody);
			//sf.seleU.wait(2000);
			// click on Review button
			sf.seleU.clickElementByJSE(sf.mailinator.reviewDocumentInDocuSign);
			reportStatusPass(methodName + "Clicked on Review Document button in Docusign email in mailinator", true,
					true);
			sf.seleU.switchWindow(3);
			if (eSignStatus.contains("Signed")) {
				// click on checkbox in new window- I agree to use electronic records and
				sf.seleU.clickOnElement(sf.mailinator.disclosureCheckbox);
				// click on Continue button
				sf.seleU.clickOnElement(sf.mailinator.continueButton);
				reportStatusPass(methodName + "Clicked on Continue button in Docusign email in mailinator", true, true);
				// click on Start button
				sf.seleU.clickOnElement(sf.mailinator.startDocuSign);
				reportStatusPass(methodName + "Clicked on Start in Docusign email in mailinator", true, true);
		//		InputData_Communities.mulPBFNumOfSites = 3;

				while(InputData_Communities.mulPBFNumOfSites >= 1) {

					if(sf.mailinator.signDocuSignHere.size() == InputData_Communities.mulPBFNumOfSites) {
						reportStatusPass(methodName + "No of sites are matched with the no of sign", true, true);
					} else {
						reportStatusFail(methodName + "No of sites are not matched in the mailinator email", true);
					}
					// click on pre-sign link
					if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.signDocuSignHere.get(0)))
						sf.seleU.clickOnElement(sf.mailinator.signDocuSignHere.get(0));
					reportStatusPass(methodName + "Clicked on Sign in Docusign email in mailinator", true, true);

					// Adopt and sign new window
					if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.adoptAndSign)) {
						sf.seleU.switchToActiveElement();
						sf.seleU.clickOnElement(sf.mailinator.adoptAndSign);			
						reportStatusPass(methodName + "Clicked on Adopt and Sign button in Docusign email in mailinator", true,
								true);
					}

					// only for the first sign title needs to be entered
					if(flag) {
						if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.enterTitle))
							sf.seleU.clearAndEnterText(sf.mailinator.enterTitle, "Mrs");
						flag = false;
					}

					if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.signNextButton))
						sf.seleU.clickOnElement(sf.mailinator.signNextButton);

					InputData_Communities.mulPBFNumOfSites = InputData_Communities.mulPBFNumOfSites -1;

					sf.seleU.wait(5000);

				}

				sf.seleU.clickOnElement(sf.mailinator.finishButton);
				reportStatusPass(methodName + "Clicked on Finish button in Docusign email in mailinator", true, true);
				// pop up will open and click on no thanks button
				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.noThanksButton)) {
					sf.seleU.clickOnElement(sf.mailinator.noThanksButton);
					reportStatusPass(methodName + "Clicked on No thanks button in Docusign email in mailinator", true,
							true);
				}
				// final continue button pop up comes
				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.finalContinueButton)) {
					sf.seleU.clickOnElement(sf.mailinator.finalContinueButton);
					reportStatusPass(methodName + "Clicked on final continue button in Docusign email in mailinator", true,
							true);
				}
				sf.seleU.wait(2000);
				verifyFieldPresent("You have finished signing" , sf.mailinator.finishSigningText);

			} else {
				sf.seleU.clickOnElement(sf.mailinator.otherActionsDD);
				sf.seleU.clickOnElement(sf.mailinator.declineSign);
				sf.seleU.switchToActiveElement();
				sf.seleU.clickOnElement(sf.mailinator.declineContinue);
				sf.seleU.switchToActiveElement();
				sf.seleU.clickOnElement(sf.mailinator.declineContinueReason);
				reportStatusPass(methodName + "Clicked on Decline to Sign & Continue button", true,
						true);
			}
			// Close the mailinator tab & point back to SF application
			sf.seleU.switchWindow(1);
			// close tabs
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Exception e) {
			reportStatusFail(methodName + "  Error in verifying the Email Attributes", e);
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
			if (sf.seleU.isElementDisplayedWithYellowHighlight(element)) {
				reportStatusPass(fieldName + " is present" + " with value as "
						+ sf.seleU.getTextFromWebElementWithYellowHighlight(element), true, false);
			} else {
				reportStatusFail(fieldName + " is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().toUpperCase().contains(expectedText.trim().toUpperCase())) {
				reportStatusPass("Validated " + fieldName + " : " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + "\n And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail("Error in Field verification", e);
			e.printStackTrace();
		}
	}
	public void reviewAndDownloadInEmailAtMailinator(String emailID) throws IOException {
		try {

			String contactEmailId = emailID.split("@")[0];
			String fromEmailId = "dse_demo@docusign.net";
			String userReferenced = "Rogers Communications Inc.";
			String emailReferenced = "docusignintegration@rci.rogers.com";

			sf.seleU.openNewTab();
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(2);
			sf.seleU.hardwait(2000);
			// open mailinator.com in the new tab using the mailinator URL
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			// Click on the search field to search the emailId
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, contactEmailId);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);
			// Verify if the mailbox has the email
			sf.seleU.hardwait(2000);
			if (sf.seleU.getCurrentUrl().contains(contactEmailId)) {
				reportStatusPass(methodName + "Verified : mailinator contact order inbox", true, true);
			} else {
				reportStatusFail(methodName, true);
			}

			// Click on the email subject in the Inbox to open the email
			sf.seleU.clickOnElement(sf.mailinator.emailSubjectEsign);
			reportStatusPass(methodName + "Verified : mailinator inbox", true, true);
			// Verify the recieverEmailId in the email
			verifyFieldValue("To email as", sf.cableTaskItems.toReceiverID, contactEmailId);
			if (InputData_WA.product_type.equalsIgnoreCase("Wireless")) {
				verifyFieldValue("From email as ", sf.cableTaskItems.fromReceiverID, "dse_NA3@docusign.net");
			}
			sf.seleU.hardwait(5000);
			sf.seleU.switchToFrame(sf.mailinator.frameEmailBody);
			// click on Review button
			sf.seleU.clickElementByJSE(sf.mailinator.reviewDocumentInDocuSign);
			reportStatusPass(methodName + "Clicked on Review Document button in Docusign email in mailinator", true,
					true);
			sf.seleU.switchWindow(3);

			// Close the mailinator tab & point back to SF application
			sf.seleU.clickOnElement(sf.mailinator.disclosureCheckbox);
			// click on Continue button
			sf.seleU.clickOnElement(sf.mailinator.continueButton);
			reportStatusPass(methodName + "Clicked on Continue button in Docusign email in mailinator", true, true);

			//click on download pdf
			sf.seleU.clickElementByJSE(sf.mailinator.downloadDocuSign);
			sf.seleU.clickElementByJSE(sf.mailinator.combinedPDFDocuSign);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + "pdf downloaded from DocuSign.", true, true);

			sf.seleU.switchWindow(1);
			// close tabs
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Exception e) {
			reportStatusFail(methodName + "  Error in verifying the Email Attributes", e);
			e.printStackTrace();
		}

	}

	/**
	 * @author Satish.Doraiswamy
	 * @Date 25-02-2022
	 * Note : Keep window Active and DONT USE SYSTEM while performing Drag and Drop.
	 * 
	 * @throws InterruptedException Opens a new tab, and Navigate to
	 *                              "Mailinator.com" URL click on Review document
	 *                              enter Click on "Name" & drag it on the contract
	 *                              document & complete the "Adopt & Sign" flow for
	 *                              the e-signatures.
	 * 
	 * 
	 */

	public void reviewAndSignInEmailAtMailinator_New(String emailID, String eSignStatus) throws IOException {
		try {

			String contactEmailId = emailID.split("@")[0];
			String fromEmailId = "dse_demo@docusign.net";
			String userReferenced = "Rogers Communications Inc.";
			String emailReferenced = "docusignintegration@rci.rogers.com";

			sf.seleU.openNewTab();
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(2);
			sf.seleU.hardwait(2000);
			// open mailinator.com in the new tab using the mailinator URL
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			// Click on the search field to search the emailId
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, contactEmailId);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);
			// Verify if the mailbox has the email
			sf.seleU.hardwait(2000);
			if (sf.seleU.getCurrentUrl().contains(contactEmailId)) {
				reportStatusPass(methodName + "Verified : mailinator contact order inbox", true, true);
			} else {
				reportStatusFail(methodName, true);
			}
			//sf.seleU.refreshPage();
			//sf.seleU.hardwait(2000);
			// Click on the email subject in the Inbox to open the email
			sf.seleU.clickOnElement(sf.mailinator.emailSubjectEsign);
			reportStatusPass(methodName + "Verified : mailinator inbox", true, true);
			// Verify the recieverEmailId in the email
			verifyFieldValue("To email as", sf.cableTaskItems.toReceiverID, contactEmailId);
			if (InputData_WA.product_type.equalsIgnoreCase("Wireless") && InputData_WA.env.equalsIgnoreCase("WADEVQA")) {
				verifyFieldValue("From email as ", sf.cableTaskItems.fromReceiverID, "dse_NA3@docusign.net");
			}else {
				verifyFieldValue("From email as ", sf.cableTaskItems.fromReceiverID, fromEmailId);
			}

			//			verifyFieldValue("User referenced in the body as ", sf.cableTaskItems.userReferenced, userReferenced);
			//			verifyFieldValue("Email referenced in the body as ", sf.cableTaskItems.emailReferenced, emailReferenced);
			sf.seleU.hardwait(5000);
			sf.seleU.switchToFrame(sf.mailinator.frameEmailBody);
			//sf.seleU.wait(2000);
			// click on Review button
			sf.seleU.clickElementByJSE(sf.mailinator.reviewDocumentInDocuSign);
			reportStatusPass(methodName + "Clicked on Review Document button in Docusign email in mailinator", true,
					true);
			sf.seleU.switchWindow(3);
			if (eSignStatus.contains("Signed")) {
				// click on checkbox in new window- I agree to use electronic records and
				sf.seleU.clickOnElement(sf.mailinator.disclosureCheckbox);
				// click on Continue button
				sf.seleU.clickOnElement(sf.mailinator.continueButton);
				reportStatusPass(methodName + "Clicked on Continue button in Docusign email in mailinator", true, true);
				// click on Start button
				sf.seleU.clickOnElement(sf.mailinator.startDocuSign);
				reportStatusPass(methodName + "Clicked on Start in Docusign email in mailinator", true, true);
				// click on pre-sign link
				sf.seleU.clickOnElement(sf.mailinator.signDocuSign);
				reportStatusPass(methodName + "Clicked on Sign in Docusign email in mailinator", true, true);
				sf.seleU.switchToActiveElement();
				// Adopt and sign new window
				sf.seleU.clickOnElement(sf.mailinator.adoptAndSign);
				reportStatusPass(methodName + "Clicked on Adopt and Sign button in Docusign email in mailinator", true,
						true);
				/*
				 * 
				 * Inserting signature for E-Sign Flow
				 * Note : Keep window Active and DONT USE SYSTEM while performing Drag and Drop
				 */
				sf.seleU.dragAndDropElement(sf.mailinator.nameButtonDigitalSignMailinatorEmail, sf.mailinator.agreementPageInDigitalSignMailinator);
				sf.seleU.wait(3000);
				reportStatusPass(methodName + "Drag and Drop the Name Element", true,
						true);
				sf.seleU.wait(3000);
				sf.seleU.dragAndDropElement(sf.mailinator.dateButtonDigitalSignMailinatorEmail, sf.mailinator.agreementPageInDigitalSignMailinator);
				sf.seleU.wait(3000);
				//				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.enterTitle))
				//					sf.seleU.clearAndEnterText(sf.mailinator.enterTitle, "Mrs");
				sf.seleU.wait(3000);
				sf.seleU.ScrolltoElement(sf.mailinator.finishButton);
				sf.seleU.wait(3000);
				sf.seleU.clickOnElement(sf.mailinator.finishButton);
				reportStatusPass(methodName + "Clicked on Finish button in Docusign email in mailinator", true, true);
				// pop up will open and click on no thanks button
				sf.seleU.wait(5000);
				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.noThanksButton)) {
					sf.seleU.clickOnElement(sf.mailinator.noThanksButton);
					reportStatusPass(methodName + "Clicked on No thanks button in Docusign email in mailinator", true,
							true);
				}
				// final continue button pop up comes
				if(sf.seleU.isElementDisplayedWithYellowHighlight(sf.mailinator.finalContinueButton)) {
					sf.seleU.clickOnElement(sf.mailinator.finalContinueButton);
					reportStatusPass(methodName + "Clicked on final continue button in Docusign email in mailinator", true,
							true);
				}
				sf.seleU.wait(2000);
				verifyFieldPresent("You have finished signing" , sf.mailinator.finishSigningText);

			} else {
				sf.seleU.wait(10000);
				sf.seleU.waitElementToBeVisible(sf.mailinator.otherActionsDD);
				sf.seleU.clickOnElement(sf.mailinator.otherActionsDD);
				sf.seleU.wait(2000);
				sf.seleU.clickOnElement(sf.mailinator.declineSign);
				sf.seleU.switchToActiveElement();
				sf.seleU.clickElementByJSE(sf.mailinator.declineContinueButton);
				sf.seleU.wait(2000);
				sf.seleU.enterText(sf.mailinator.declineReasonText, Global.dataInput.deniedStatus);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.mailinator.declineSubmitButton);
				sf.seleU.wait(4000);
				String declineMsg=sf.seleU.getTextFromWebElement(sf.mailinator.declineSubmitMsgText);
				if(declineMsg.trim().contains(InputData_WA.DECLINE_SUCCESS_MSG)) {
				reportStatusPass(methodName + "Order has been Declined Successfully !!!", true,
						true);
				}else {
					reportStatusFail(methodName + "Order not been Declined Successfully !!!", true);
				}
			}
			// Close the mailinator tab & point back to SF application
			sf.seleU.switchWindow(1);
			// close tabs
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Exception e) {
			reportStatusFail(methodName + "  Error in verifying the Email Attributes", e);
			e.printStackTrace();
		}

	}
}