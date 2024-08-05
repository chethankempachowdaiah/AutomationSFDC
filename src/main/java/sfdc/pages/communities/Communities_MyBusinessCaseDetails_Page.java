package sfdc.pages.communities;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class Communities_MyBusinessCaseDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static SFDC_Files_Page filesPage;

	public Communities_MyBusinessCaseDetails_Page() {
		sf = new SFDC_AllPageObjects();
		filesPage = new SFDC_Files_Page();
		methodName = "Communities_My Business Case Details@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify Fields
	 * 
	 *                     Select Case Comments and Enter Comment and Verify
	 */
	public void myBusinessCasesViewCaseDetails() throws IOException {
		try {
			sf.seleU.wait(10000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + " Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(10000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);
			verifyFieldValue("Account Number", sf.comCaseDetails.accountNumber,
					InputData_Communities.communities_account);
			verifyFieldValue("Case Status", sf.comCaseDetails.caseStatus, InputData.caseStatusNew);
			verifyFieldHasValue("Case Submission Date", sf.comCaseDetails.caseSubmissionDate);
			verifyFieldDisplayed("Case Completion Date Field", sf.comCaseDetails.caseCompletionDateField);
			verifyFieldValue("Case Reason", sf.comCaseDetails.caseReason, Global.commData.communities_CaseReason);
			verifyFieldValue("Case Product", sf.comCaseDetails.caseProduct, Global.commData.communities_CaseProduct);
			verifyFieldValue("Case Subject", sf.comCaseDetails.caseSubject, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case Description", sf.comCaseDetails.caseDescription, InputData.caseStatusNew + addOn_1);
			verifyFieldDisplayed("My Attachments Field", sf.comCaseDetails.myAttachmentsField);
			verifyFieldDisplayed("Upload Files Button", sf.comCaseDetails.uploadFilesButton);

			// Select Case Comments and Enter Comment and Verify
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseCommentsTab);
			sf.seleU.wait(3000);

			sf.seleU.enterText(sf.comCaseDetails.postCommentInput, InputData.caseStatusNew + addOn_1);
			sf.seleU.clickElementByJSE(sf.comCaseDetails.submitButton);
			sf.seleU.wait(4000);

			verifyFieldDisplayed("Comment UserName", sf.comCaseDetails.commentUserName);
			verifyFieldDisplayed("Comment Created Date", sf.comCaseDetails.commentCreatedDate);
			verifyFieldValue("Comment Body", sf.comCaseDetails.commentBody, InputData.caseStatusNew + addOn_1);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Viewing Case Details is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText) || expectedText.contains(sf.seleU.getTextFromWebElement(element))) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
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
			DateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy H:m a");
			sdf.setLenient(false);
			String createdDate = sf.seleU.getTextFromWebElement(dateElement);
			createdDate = createdDate.replace(" at", "");
			try {
				sdf.parse(createdDate);
				reportStatusPass(methodName + " Validated " + createdDate + " is in correct date format", true, true);

			} catch (DateTimeParseException e) {
				reportStatusFail(methodName + " " + createdDate + " is not in correct DateTime format", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in date verification", e);
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify field is displayed on page or not
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying field is displayed", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify few Fields
	 * 
	 *                     Select Case Comments , Verify submit button disabled
	 *                     until we post a comment
	 * 
	 *                     Enter Comment and Verify
	 */
	public void myBusinessCasesAddAndVerifyComments() throws IOException {
		try {
			sf.seleU.wait(60000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(60000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);
			verifyFieldHasValue("Account Number", sf.comCaseDetails.accountNumber);
			verifyFieldValue("Case Status", sf.comCaseDetails.caseStatus, InputData.caseStatusNew);
			verifyFieldHasValue("Case Submission Date", sf.comCaseDetails.caseSubmissionDate);

			// Select Case Comments and verify sumbit is disabled until we fill text in
			// comments
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseCommentsTab);
			sf.seleU.wait(3000);
			verifyFieldDisabled("Submit Comments Button", sf.comCaseDetails.submitButton);

			// Verify header and textarea present for commenting
			verifyFieldDisplayed("Comment Textarea", sf.comCaseDetails.postCommentInput);
			verifyFieldDisplayed("Comment Header Post", sf.comCaseDetails.postCommentHeader);

			// Enter Comment and Verify
			sf.seleU.enterText(sf.comCaseDetails.postCommentInput, InputData.caseStatusNew + addOn_1);
			verifyFieldDisplayed("Submit Comments Button", sf.comCaseDetails.submitButton);

			sf.seleU.clickElementByJSE(sf.comCaseDetails.submitButton);
			sf.seleU.wait(4000);

			verifyFieldDisplayed("Tabular view of comments", sf.comCaseDetails.commentsTable);
			verifyFieldDisplayed("Comment UserName", sf.comCaseDetails.commentUserName);
			verifyFieldDisplayed("Comment Created Date", sf.comCaseDetails.commentCreatedDate);
			verifyDate(sf.comCaseDetails.commentCreatedDate);
			verifyFieldValue("Comment Body", sf.comCaseDetails.commentBody, InputData.caseStatusNew + addOn_1);

			// CLose browser and switch back to main window
			sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(methodName + "Adding and verifying comments in case is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify few Fields
	 * 
	 *                     Select Case Comments , Verify submit button disabled
	 *                     until we post a comment
	 * 
	 *                     Enter Comment and Verify
	 */
	public void myBusinessCasesVerifySubjectTruncation() throws IOException {
		try {
			sf.seleU.wait(60000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(60000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			String expectedSubject = StringUtils.substring(InputData_Communities.caseDesc255Chars, 0, 252) +"...";
			verifyFieldValue("Case Subject Truncated after 255 characters", sf.comCaseDetails.caseSubject,expectedSubject );
			verifyFieldValue("Case Description", sf.comCaseDetails.caseDescription, InputData_Communities.caseDesc255Chars);
			

		} catch (Throwable e) {
			reportStatusFail(methodName + "Adding and verifying comments in case is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify few Fields
	 * 
	 *                     Verify Attachments present
	 * 
	 *                     Delete Attachment and verify confirmation message appears
	 *                     before deletion
	 */
	public void myBusinessCasesVerifyAndDeleteAttachment() throws IOException {
		try {

			// Select Case and Open Case Details
			if (sf.seleU.isElementDisplayed(sf.comCaseDetails.caseNumberOnCaseCreation)) {
				sf.seleU.clickElementByJSE(sf.comCaseDetails.caseNumberOnCaseCreation);
				reportStatusPass(methodName + " Selected Case : " + InputData_Communities.caseNumber, true, true);
				sf.seleU.wait(80000);
			}
			else {
				sf.seleU.wait(80000);
				// Select Case and Open Case Details
				sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
				reportStatusPass(methodName + "Selected Case : " + InputData.caseNumber, true, true);
				sf.seleU.wait(80000);
			}
			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);
			verifyFieldValue("Account Number", sf.comCaseDetails.accountNumber,
					InputData_Communities.commCaseAccountName);
			verifyFieldValue("Case Status", sf.comCaseDetails.caseStatus, InputData.caseStatusNew);
			verifyFieldHasValue("Case Submission Date", sf.comCaseDetails.caseSubmissionDate);

			// Verify Attachments section has attachments
			verifyFieldDisplayed("attachment", sf.comCaseDetails.attachmentList.get(0));

			// Verify attachement section has expected number of attachments
			if (sf.comCaseDetails.attachmentList.size() == InputData_Communities.noOfFilesToBeUploadedInCases)
				reportStatusPass(methodName + " Validated number of attachments present. Expected -- > "
						+ InputData_Communities.noOfFilesToBeUploadedInCases + " ,Actual --> "
						+ sf.comCaseDetails.attachmentList.size(), true, true);
			else
				reportStatusFail(methodName + " Expected number of attachments are not present. Expected -- > "
						+ InputData_Communities.noOfFilesToBeUploadedInCases + " ,Actual --> "
						+ sf.comCaseDetails.attachmentList.size(), true);

			// Verify delete option is present for attachment
			verifyFieldDisplayed("Delete Attachment", sf.comCaseDetails.deleteAttachmentIcon.get(0));

			// Verify attachement section has expected number of delete icons
			if (sf.comCaseDetails.deleteAttachmentIcon.size() == InputData_Communities.noOfFilesToBeUploadedInCases)
				reportStatusPass(methodName + " Validated number of delete icons present. Expected -- > "
						+ InputData_Communities.noOfFilesToBeUploadedInCases + " ,Actual --> "
						+ sf.comCaseDetails.deleteAttachmentIcon.size(), true, true);
			else
				reportStatusFail(methodName + " Expected number of delete icons are not present. Expected -- > "
						+ InputData_Communities.noOfFilesToBeUploadedInCases + " ,Actual --> "
						+ sf.comCaseDetails.deleteAttachmentIcon.size(), true);

			// Delete Attachment and verify confirmation message appears before deletion
			sf.seleU.wait(4000);
			sf.seleU.clickOnElement(sf.comCaseDetails.deleteAttachmentIcon.get(0));
			sf.seleU.wait(4000);
			verifyFieldDisplayed("Delete Confirmation Popup", sf.comCaseDetails.deleteConfirmMessage);
			sf.seleU.clickOnElement(sf.comCaseDetails.deleteConfirmButton);
			sf.seleU.wait(10000);
			InputData_Communities.noOfFilesToBeUploadedInCases = InputData_Communities.noOfFilesToBeUploadedInCases - 1;

			// Verify attachment deleted successfully
			if (InputData_Communities.noOfFilesToBeUploadedInCases > 0) {
				if (sf.comCaseDetails.attachmentList.size() == InputData_Communities.noOfFilesToBeUploadedInCases)
					reportStatusPass(methodName + " Attachment is deleted from list.", true, true);
				else
					reportStatusFail(methodName + " Attachment is still there in the list", true);
			} else {

				verifyElementNotPresent("attachment", sf.comCaseDetails.attachmentList);
			}

			// CLose browser and switch back to main window
			// sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying and deleting attachment is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify few Fields
	 * 
	 *                     Verify Attachments present
	 * 
	 *                     Verify Attachment is not deletable
	 */
	public void myBusinessCasesVerifyAttachmentNotDeletable() throws IOException {
		try {
			sf.seleU.wait(60000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(60000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);

			// Verify Attachments section has attachments
			verifyFieldDisplayed("attachment", sf.comCaseDetails.attachmentList.get(0));

			// Verify delete option is present for attachment
			verifyFieldDisplayed("attachment", sf.comCaseDetails.deleteAttachmentIcon.get(0));

			// Delete Attachment and verify confirmation message appears before deletion
			sf.seleU.wait(4000);
			sf.seleU.clickOnElement(sf.comCaseDetails.deleteAttachmentIcon.get(0));
			sf.seleU.wait(4000);
			verifyFieldDisplayed("Delete Confirmation Popup", sf.comCaseDetails.deleteConfirmMessage);
			sf.seleU.clickOnElement(sf.comCaseDetails.deleteConfirmButton);

			// Verify attachment still exist even after deletion
			sf.seleU.wait(4000);
			if (sf.comCaseDetails.attachmentList.size() > 0)
				reportStatusPass(methodName + " Validated: Attachment is not deletable", true, false);
			else
				reportStatusFail(methodName + " Validation Fail: Attachment is deletable.", true);

			// CLose browser and switch back to main window
			sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying attachment is not deletable was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select existing Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify upload file button is
	 *                     present
	 * 
	 *                     Upload an attachment
	 * 
	 *                     Verify attachment uploaded successfully
	 */
	public void myBusinessCasesUploadAttachmentInExistingCase() throws IOException {
		try {

			sf.seleU.wait(40000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(40000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(4000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);

			// Upload attachment twice
			for (int i = 0; i < 2; i++)
				filesPage.uploadFiles();
				
			sf.seleU.wait(4000);
			// Verify Attachments section has attachments
			verifyFieldDisplayed("attachment", sf.comCaseDetails.attachmentList.get(0));

			// Verify attachement section has expected number of attachments
			if (sf.comCaseDetails.attachmentList.size() == 2)
				reportStatusPass(methodName + " Validated number of attachments present. Expected -- > 2"
						+ " ,Actual --> " + sf.comCaseDetails.attachmentList.size(), true, true);
			else
				reportStatusFail(methodName + " Expected number of attachments are not present. Expected -- > 2"
						+ " ,Actual --> " + sf.comCaseDetails.attachmentList.size(), true);

			// Verify delete option is present for attachment
			verifyFieldDisplayed("attachment", sf.comCaseDetails.deleteAttachmentIcon.get(0));

			// Verify attachement section has expected number of delete icons
			if (sf.comCaseDetails.deleteAttachmentIcon.size() == 2)
				reportStatusPass(methodName + " Validated number of delete icons present. Expected -- > 2"
						+ " ,Actual --> " + sf.comCaseDetails.deleteAttachmentIcon.size(), true, true);
			else
				reportStatusFail(methodName + " Expected number of delete icons are not present. Expected -- > 2"
						+ " ,Actual --> " + sf.comCaseDetails.deleteAttachmentIcon.size(), true);

			// CLose browser and switch back to main window
			//sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(methodName + "Uploading attachment in existing case is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify Fields
	 * 
	 */
	public void myBusinessCasesViewCaseDetails(String productName, String reasonName) throws IOException {
		try {
			sf.seleU.wait(10000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + "Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(10000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);
			verifyFieldValue("Account Number", sf.comCaseDetails.accountNumber,
					InputData_Communities.communities_account);
			verifyFieldValue("Case Status", sf.comCaseDetails.caseStatus, InputData.caseStatusNew);
			verifyFieldHasValue("Case Submission Date", sf.comCaseDetails.caseSubmissionDate);
			verifyFieldDisplayed("Case Completion Date Field", sf.comCaseDetails.caseCompletionDateField);
			verifyFieldValue("Case Reason", sf.comCaseDetails.caseReason, reasonName);
			verifyFieldValue("Case Product", sf.comCaseDetails.caseProduct, productName);
			verifyFieldValue("Case Subject", sf.comCaseDetails.caseSubject, InputData.caseStatusNew + addOn_1);
			verifyFieldDisplayed("Case Description", sf.comCaseDetails.caseDescription);
			verifyFieldDisplayed("My Attachments Field", sf.comCaseDetails.myAttachmentsField);
			verifyFieldDisplayed("Upload Files Button", sf.comCaseDetails.uploadFilesButton);

			InputData_Communities.recentCaseProduct = productName;
			InputData_Communities.recentCaseReason = reasonName;

		} catch (Throwable e) {
			reportStatusFail(methodName + "Viewing Case Details is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Case and Open Case Details
	 * 
	 *                     Select Case Details and Verify Fields
	 * 
	 */
	public void myBusinessCasesVerifyClosedCase() throws IOException {
		try {

			sf.seleU.wait(50000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comMBC.caseSummary_caseNumberAllLinks.get(0));
			reportStatusPass(methodName + " Selected Case : " + InputData.caseNumber, true, true);
			sf.seleU.wait(50000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);

			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData.caseNumber);
			verifyFieldValue("Account Number", sf.comCaseDetails.accountNumber,
					InputData_Communities.commCaseAccountName);
			verifyFieldValue("Case Status", sf.comCaseDetails.caseStatus, InputData.caseStatusClosed);

			// Upload button should not be present
			verifyFieldNotDisplayed(" Upload Files Button", sf.comCaseDetails.uploadFilesButton);

			// Go to comments section and validate textarea is not present
			sf.seleU.clickOnElement(sf.comCaseDetails.caseCommentsTab);
			sf.seleU.wait(4000);
			verifyFieldNotDisplayed(" Comments textarea", sf.comCaseDetails.postCommentInput);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying closed case is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is disabled
	 */
	public void verifyFieldDisabled(String fieldName, WebElement element) throws IOException {
		try {

			if (!element.isDisplayed()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is disabled and not displayed", true,
						true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + " is not a disabled field, It should be a disabled", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify element is not present
	 */
	public void verifyElementNotPresent(String fieldName, List<WebElement> element) throws IOException {
		try {

			if (element.size() == 0)
				reportStatusPass(methodName + " Validated " + fieldName + " field is not present", true, true);
			else
				reportStatusFail(methodName + " Element " + fieldName + " is present, It should not be present", true);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying element is not present", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field has value/text
	 */
	public void verifyFieldHasValue(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field has some value/non-empty
			if (!sf.seleU.getTextFromWebElement(element).isEmpty())
				reportStatusPass(
						methodName + " " + fieldName + " field has value : " + sf.seleU.getTextFromWebElement(element),
						true, true);
			else
				reportStatusFail(methodName + " " + fieldName + " field value is not populated and is empty", true);

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify field is not displayed
	 */
	public static void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {

		try {
			// Verify field is not displayed
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Verified " + fieldName + " is not displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is displayed, It should not be displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	///****************** For new sheet data***********************/////
	/**
	 * @throws IOException
	 * 
	 *                     Click on case link recently created
	 * 
	 *                     Select Case Details and Verify Fields
	 *                     
	 *                     Account Name: Business Account Name
	 *                     Status :New for newly created case
	 *                     Case Type:As entered during case creation
	 *                     Product: As entered during case creation
	 *                     Reason:As entered during case creation
	 *                     Submission Date: Date when case is opened
	 *                     Completion Date: To populate when You close the case, else will show "-"
	 *                      
	 */
	public void validateCaseDetails() throws IOException {
		try {
			sf.seleU.wait(3000);
			// Select Case and Open Case Details
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseNumberOnCaseCreation);
			reportStatusPass(methodName + " Selected Case : " + InputData_Communities.caseNumber, true, true);
			sf.seleU.wait(60000);

			verifyFieldDisplayed("Case Summary Case Details", sf.comCaseDetails.caseSummaryCaseDetails);

			// Select Case Details and Verify Fields
			sf.seleU.clickElementByJSE(sf.comCaseDetails.caseDetailsTab);
			sf.seleU.wait(2000);
			
			verifyFieldValue("Case Type", sf.comCaseDetails.caseType,
					InputData_Communities.commCaseRecordType);
			
			verifyFieldValue("Case Number", sf.comCaseDetails.caseNumber, InputData_Communities.caseNumber);
			verifyFieldValue("Account Number", sf.comCaseDetails.accountNumber,
					InputData_Communities.commCaseAccountName);
			verifyFieldValue("Case Status", sf.comCaseDetails.caseStatus, InputData.caseStatusNew);
			verifyFieldHasValue("Case Submission Date", sf.comCaseDetails.caseSubmissionDate);
			verifyFieldValue("Case Completion Date Field", sf.comCaseDetails.caseCompletionDateField,"-");
			verifyFieldValue("Case Reason", sf.comCaseDetails.caseReason, InputData_Communities.commCaseReason);
			verifyFieldValue("Case Product", sf.comCaseDetails.caseProduct, InputData_Communities.commCaseProduct);
			verifyFieldValue("Case Description", sf.comCaseDetails.caseDescription, InputData.caseStatusNew + addOn_1);
			if(!(InputData_Communities.commCaseSiteAddress==null) && !InputData_Communities.commCaseSiteAddress.equalsIgnoreCase("NA")) 
				verifyFieldValue("Case Description- Site Address", sf.comCaseDetails.caseDescription,InputData_Communities.commCaseSiteAddress);
			if(!(InputData_Communities.commCaseSiteContact==null) && !InputData_Communities.commCaseSiteContact.equalsIgnoreCase("NA")) 
				verifyFieldValue("Case Description-Site Contact", sf.comCaseDetails.caseDescription,InputData_Communities.commCaseSiteContact);
				
			
		} catch (Throwable e) {
			reportStatusFail(" Error in verification of case details", e);
			e.printStackTrace();
		}
	}
}
