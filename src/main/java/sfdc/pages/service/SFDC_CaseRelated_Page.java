package sfdc.pages.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_EmailToCase_Lib;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 20/FEB/2020
 * 
 *         Case Related Page(Email to case> Accpet case from omni channel> case
 *         Related page)
 *
 */
public class SFDC_CaseRelated_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static SFDC_Files_Page filesPage;

	public SFDC_CaseRelated_Page() {
		sf = new SFDC_AllPageObjects();
		filesPage = new SFDC_Files_Page();
		methodName = "SFDC_Case Related@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case Emails are attached
	 */
	public void validateCaseEmails() throws IOException {
		try {

			sf.seleU.wait(10000);
			// Select related tab and verify attachments section
			if(sf.seleU.isElementDisplayed(sf.caseRelated.caseRelatedTab))
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.caseDetails.moreTab);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			}
			reportStatusPass(methodName + "Clicked on Case Related tab", true, true);

			// Verify Case Emails are attached
			if (sf.seleU.getTextFromWebElement(sf.caseRelated.caseEmailsAllLinks.get(0))
					.contains("Your Case # " + SFDC_EmailToCase_Lib.caseID + " has been created")
					&& sf.seleU.getTextFromWebElement(sf.caseRelated.caseEmailsAllLinks.get(1))
							.equals(SFDC_EmailToCase_Lib.subjectLine)) {

				reportStatusPass(methodName + "Case Emails are validated", true, true);

			} else {

				reportStatusFail(methodName + " Invalid Emails in case ", true);

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Invalid Emails in case ", e);
			e.printStackTrace();
		}
	}
	
	public void validateTouchPointScoreAndDetails() throws IOException
	{
		try
		{
		  sf.seleU.hardwait(5000);
		  sf.seleU.clickElementByJSE(sf.home.listViewIconNew);
		  sf.seleU.hardwait(3000);
		  sf.seleU.clickElementByJSE(sf.home.allIcon);
		  sf.seleU.hardwait(5000);
		  sf.seleU.clickElementByJSE(sf.home.surveyResponsesIcon.get(0));
		  sf.seleU.hardwait(3000);
		 		  
//		  sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
//		  reportStatusPass(methodName + "Clicked on Related Tab", true, false);
//		  sf.seleU.hardwait(4000);
//		  sf.seleU.clickElementByJSE(sf.caseRelated.showAllIcon);
//		  reportStatusPass(methodName + "Clicked on ShowAll", true, false);
//		  sf.seleU.hardwait(5000);
//		  sf.seleU.clickElementByJSE(sf.caseRelated.surveyResponsesLink);
//		  reportStatusPass(methodName + "Clicked on SurveyResponses", true, false);
//		  sf.seleU.hardwait(3000);
//		  sf.seleU.clickElementByJSE(sf.caseRelated.surveyResponsesIdLink);
//		  reportStatusPass(methodName + "Clicked on SurveyResponses ID", true, false);
//		  sf.seleU.hardwait(3000);
		  
		  if(sf.seleU.isElementDisplayed(sf.caseRelated.touchPointScore))
		  {
			  reportStatusPass("TouchPoint Score is Present", true, true);
		  }
		  else
		  {
			  reportStatusFail("Error in validating details and TouchPoint Score", true);
		  }
		  sf.seleU.hardwait(3000);
		}
		catch(Throwable e)
		{
			reportStatusFail("Error in validating details and TouchPoint Score", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on case creatin Email Link
	 * 
	 *                     Click on Reply Email Button
	 * 
	 *                     Enter Reply Email Body
	 * 
	 *                     Click on Send Button
	 * 
	 *                     Verify Email Sent Message
	 * 
	 */
	public void agentReply() throws IOException {
		try {

			// Click on case creation Email Link
			sf.seleU.clickElementByJSE(sf.caseRelated.caseEmailsAllLinks.get(0));
			reportStatusPass(methodName + "Clicked on case creatin Email Link", true, false);

			sf.seleU.hardwait(2000);

			// Click on Reply Email Button
			sf.seleU.clickElementByJSE(sf.caseRelated.replyEmailButton);
			reportStatusPass(methodName + "Clicked on Reply Email Button", true, false);

			sf.seleU.hardwait(2000);

			// Enter Reply Email Body
			sf.seleU.switchToFrame(sf.caseRelated.emailEditorFrame);
			sf.seleU.switchToFrame(sf.caseRelated.emailBodyFrame);
			sf.seleU.clickElementByJSE(sf.caseRelated.emailBodyArea);
			sf.seleU.clearAndEnterText(sf.caseRelated.emailBodyArea, sf.dataInput.replyEmailBody);
			reportStatusPass(methodName + "Entered Reply Email Body as " + sf.dataInput.replyEmailBody, true, false);

			// Click on Send Button
			sf.seleU.switchToDefaultContent();

			sf.seleU.clickElementByJSE(sf.caseRelated.emailSendButton);
			reportStatusPass(methodName + "Cliked on Send Email Button", true, false);

			sf.seleU.hardwait(2000);

			// Verify Email Sent Message
			if (sf.seleU.isElementDisplayed(sf.caseRelated.emailSentMessage)) {
				reportStatusPass(methodName + "Agent Successfully Replied", true, true);
			} else {
				reportStatusFail(methodName + " Error in agent email reply", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in agent email reply", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on case tab
	 * 
	 *                     Verify Case Reply Emails are attached
	 */
	public void verifyAgentReply() throws IOException {
		try {

			// Click on case tab
			sf.seleU.clickElementByJSE(
					driver.findElements(By.xpath("//a[@title='" + SFDC_EmailToCase_Lib.caseID + "']//span[2]")).get(1));

			sf.seleU.wait(2000);
			sf.seleU.refreshPage();
			sf.seleU.wait(5000);

			sf.seleU.wait(10000);
			// Select related tab and verify attachments section
			if(sf.seleU.isElementDisplayed(sf.caseRelated.caseRelatedTab))
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.caseDetails.moreTab);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			}reportStatusPass(methodName + "Clicked on Case Related tab", true, true);

			sf.seleU.wait(2000);

			// Verify Case Reply Emails are attached
			if (sf.seleU.getTextFromWebElement(sf.caseRelated.caseEmailsAllLinks.get(0))
					.contains("RE: Your Case # " + SFDC_EmailToCase_Lib.caseID + " has been created")
					&& sf.seleU.getTextFromWebElement(sf.caseRelated.caseEmailsAllLinks.get(1))
							.contains("Your Case # " + SFDC_EmailToCase_Lib.caseID + " has been created")
					&& sf.seleU.getTextFromWebElement(sf.caseRelated.caseEmailsAllLinks.get(2))
							.contains(SFDC_EmailToCase_Lib.subjectLine)) {

				reportStatusPass(methodName + "Case Email reply is  validated", true, true);

			} else {
				reportStatusFail(methodName + " Invalid case Email reply ", true);
			}

			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying agent email reply", true);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Related Tab Click on Case Comments Verify Comments
	 *                     details
	 * 
	 */
	public void verifyCaseComments() throws IOException {
		try {

			sf.seleU.wait(10000);
			// Select related tab and verify comments section
			if(sf.seleU.isElementDisplayed(sf.caseRelated.caseRelatedTab))
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.caseDetails.moreTab);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			}
			verifyFieldDisplayed("Comments section", sf.caseRelated.commentsSection);
			sf.seleU.wait(5000);

			// Click on Comments Section
			sf.seleU.clickOnElement(sf.caseRelated.commentsSection);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Comments Page and its header", sf.caseRelated.commentsHeaderText);

			// Verify comments table
			verifyFieldDisplayed("Comment UserName", sf.caseRelated.commentsTableUserName);
			verifyFieldDisplayed("Comment Created Date", sf.caseRelated.commentsTableCreatedDate);
			verifyDate(sf.caseRelated.commentsTableCreatedDate);
			verifyFieldValue("Comment Body", sf.caseRelated.commentsTableCommentsText,
					sf.dataInput.caseStatusNew + addOn_1);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Verifing Case Comments", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Related Tab Click on Case Attachments Verify
	 *                     Attachments details
	 * 
	 */
	public void verifyCaseAttachments() throws IOException {
		try {
			sf.seleU.wait(10000);
			// Select related tab and verify attachments section
			if(sf.seleU.isElementDisplayed(sf.caseRelated.caseRelatedTab))
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.caseDetails.moreTab);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			}
			sf.seleU.wait(4000);
			for (int i = 0; i <= 5; i++) {
				
				sf.seleU.enterText(sf.caseRelated.caseRelatedTab, Keys.ARROW_DOWN);
				//sf.seleU.clickOnElement(driver.findElement(By.xpath("")));
				if (sf.seleU.isElementPresent(sf.caseRelated.attachmentsSection)) {
					reportStatusPass(methodName + " Found Attachment section", true, false);
					break;
				}
			}
			sf.seleU.wait(4000);

			// Click on Attachments Section
			sf.seleU.clickElementByJSE(sf.caseRelated.attachmentsSection);
			sf.seleU.wait(10000);

			// Verify Attachments table and file
			verifyFieldDisplayed("Attachments Page and its header", sf.caseRelated.attachmentsHeaderText);
			verifyFieldDisplayed("Attachments File", sf.caseRelated.attachmentsTableFirstFileName);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying attachments section in Case details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Case Attachments Verify Attachments table is
	 *                     displayed Click on options for first attachment Click on
	 *                     "Share" Click on "Who can access" Click on "Enable toggle
	 *                     button" and enable view for Customer
	 * 
	 */
	public void enableCustomerViewForAttachment() throws IOException {
		try {

			// verifyCaseAttachments();
			sf.seleU.hardwait(5000);
			// Click on Options and then Share
			sf.seleU.clickOnElement(sf.caseRelated.attachmentsTableFirstFileLink);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.attachmentFileShareButton);
			reportStatusPass(methodName + "Clicked on Share button from file options in Attachments table", true,
					false);

			// Click on Who can access and enable customer view
			sf.seleU.clickOnElement(sf.caseRelated.attachmentWhoCanAccessButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelated.attachmentCustomerViewToggle);
			reportStatusPass(methodName + "Clicked on Who can Access in Share window and enabled customer view", true,
					false);

			// Click on Done button
			sf.seleU.hardwait(4000);
			sf.seleU.clickOnElement(sf.caseRelated.attachmentSharePopUpDone);
			reportStatusPass(methodName + "Clicked on Done button in Share window", true, false);

			// Refresh Page
			sf.seleU.refreshPage();

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in enabling customer view for file in Case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Related Tab Click on Case Attachments Upload
	 *                     Attachments
	 * 
	 */
	public void UploadCaseAttachments() throws IOException {
		try {

			sf.seleU.wait(10000);
			// Select related tab and verify attachments section
			if(sf.seleU.isElementDisplayed(sf.caseRelated.caseRelatedTab))
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.caseDetails.moreTab);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			}
			for (int i = 0; i <= 5; i++) {
				sf.seleU.scrollByCoOrdinates(1);
				if (sf.seleU.isElementPresent(sf.caseRelated.attachmentsSection)) {
					reportStatusPass(methodName + "Found Attachment section", true, false);
					break;
				}
			}
			sf.seleU.wait(4000);
			verifyFieldDisplayed("Attachments section", sf.caseRelated.attachmentsSection);
			sf.seleU.clickElementByJSE(sf.caseRelated.attachmentsSection);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelated.addFilesButton);
			sf.seleU.wait(2000);
			filesPage.uploadFiles();

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in uploading attachments in Case Related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Article attached to the case is visible in the related
	 *                     article list
	 * 
	 */
	public void verfiyCaseArticles() throws IOException {
		try {

			// Select related tab and verify articles section
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(5000);
			for (int i = 0; i <= 5; i++) {
				sf.seleU.scrollByCoOrdinates(1);
				if (sf.seleU.isElementPresent(sf.caseRelated.articlesSection)) {
					reportStatusPass(methodName + "Found Article section", true, false);
					break;
				}
			}
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelated.articlesSection);
			sf.seleU.wait(10000);

			// Verify Articles table and article listed or not
			verifyFieldDisplayed("Article Page and its header", sf.caseRelated.articlesHeaderText);
			verifyFieldDisplayed("Article Link", sf.caseRelated.articlesTableFirstArticleLink);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying articles in Case Related Tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Field verification", e);
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
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Date value is a valid timestamp
	 */
	public boolean verifyDate(WebElement dateElement) throws IOException {

		try {
			DateFormat sdf = new SimpleDateFormat("dd/mm/yyyy h:m a");
			sdf.setLenient(false);
			String createdDate = sf.seleU.getTextFromWebElement(dateElement);
			createdDate = createdDate.replace(" at", "");
			try {
				sdf.parse(createdDate);
				reportStatusPass(methodName + " Validated " + createdDate + " is in correct date format", true, true);

			} catch (DateTimeParseException e) {
				reportStatusFail(methodName + createdDate + " is not in correct DateTime format", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in date verification", e);
			e.printStackTrace();
		}

		return true;
	}
}
