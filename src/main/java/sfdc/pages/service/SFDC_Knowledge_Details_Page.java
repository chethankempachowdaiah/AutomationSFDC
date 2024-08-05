package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 20/Jan/2021
 * 
 *         SFDC Knowledge Details page
 */
public class SFDC_Knowledge_Details_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	static String methodName = "SFDC_KnowledgeDetails@: ";

	public SFDC_Knowledge_Details_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Article details
	 * 
	 *                     Validate the field "Content". It is a rich text field to
	 *                     store article body.
	 * 
	 *                     Article record type is General Article.
	 * 
	 *                     Field are displayed: subject(Title), summary,
	 *                     context(Content) and files.
	 * 
	 *                     Files are attached on the right side
	 * 
	 *                     Validate the option to specify if the article is visible
	 *                     to customer
	 * 
	 *                     Validate data categories-: Product, Function and Content
	 *                     Type
	 * 
	 *                     Validate feedback component - thumb up/down
	 * 
	 *                     Validate chatter component - on the right side of the
	 *                     article
	 * 
	 */
	public void verifyArticleDetails() throws IOException {
		try {

			// Verify Article Details
			verifyFieldDisplayed("Knowledge Header", sf.knowledgeDetails.knowledgeHeaderText);
			verifyFieldValue("Article Title", sf.knowledgeDetails.articleNameHeader,
					sf.dataInput.articleNameForDetailValidation);
			verifyFieldValue("Article Record Type", sf.knowledgeDetails.articleRecordType,
					sf.dataInput.articleRecordType);

			verifyFieldDisplayed("Summary Section", sf.knowledgeDetails.summarySection);
			reportStatusPass(
					methodName + " Summary Text : " + sf.seleU.getTextFromWebElement(sf.knowledgeDetails.summaryText),
					false, false);

			verifyFieldDisplayed("Title(Subject) Section", sf.knowledgeDetails.titleSection);
			reportStatusPass(methodName + " Title(Subject) Text : "
					+ sf.seleU.getTextFromWebElement(sf.knowledgeDetails.titleText), false, false);

			verifyFieldDisplayed("Visible to Customer Section", sf.knowledgeDetails.visibleToCustomerSection);
			verifyFieldDisplayed("Visible to Customer Section CheckBox Image",
					sf.knowledgeDetails.visibleToCustomerCheckBoxImage);

			verifyFieldDisplayed("Content Section", sf.knowledgeDetails.contentSection);
			reportStatusPass(
					methodName + " Content Text : " + sf.seleU.getTextFromWebElement(sf.knowledgeDetails.contentText),
					false, false);

			verifyFieldDisplayed("FeedBack Component", sf.knowledgeDetails.feedbackComponent);
			verifyFieldDisplayed("FeedBack Component- Like Button", sf.knowledgeDetails.feedBackLikeButton);
			verifyFieldDisplayed("FeedBack Component- Like Button", sf.knowledgeDetails.feedBackDislikeButton);

			verifyFieldDisplayed("Files Section", sf.knowledgeDetails.filesSection);

			verifyFieldDisplayed("Feed Section(Chatter Component)", sf.knowledgeDetails.feedSection);
			verifyFieldDisplayed("Search Feed Input Box", sf.knowledgeDetails.searchFeedInputBox);
			verifyFieldDisplayed("Feed Section - Comments Box", sf.knowledgeDetails.writeCommentInputBox);

			// Comment on article
			sf.seleU.ScrolltoElement(sf.knowledgeDetails.writeCommentInputBox);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.writeCommentInputBox);
			sf.seleU.wait(5000);
			sf.seleU.enterText(sf.knowledgeDetails.writeCommentTextArea, addOn_1);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.commentButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + "Added comment on article : " + addOn_1, true, false);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful article details validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate compare version in version tab
	 * 
	 *                     User is able to compare two versions of the article
	 * 
	 */
	public void verifyCompareVersionOfArticle() throws IOException {
		try {

			// Go to versions tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.versionsTab);

			// Verify Versions Page
			verifyFieldDisplayed("Current Version Label", sf.knowledgeDetails.currentVersion);
			verifyFieldDisplayed("Other Version Label", sf.knowledgeDetails.otherVersionLabel);

			// Choose other version for comparison
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.chooseVersion);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.knowledgeDetails.otherVersionDropdownOption);

			// Click on compare button to load other version details
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.compareButton);

			// Validate Comparison of versions
			verifySizeOfListWebElements("Information Section", sf.knowledgeDetails.informationSectionInCompare, 2);

			verifySizeOfListWebElements("Title Section", sf.knowledgeDetails.titleSectionInCompare, 2);
			printTextOfListWebElements("Title Text", sf.knowledgeDetails.titleTextInCompare);

			verifySizeOfListWebElements("URL Name Section", sf.knowledgeDetails.urlNameInCompare, 2);
			printTextOfListWebElements("URL Name Text", sf.knowledgeDetails.urlNameTextInCompare);

			verifySizeOfListWebElements("Summary Section", sf.knowledgeDetails.summarySectionInCompare, 2);
			printTextOfListWebElements("Summary Text", sf.knowledgeDetails.summaryTextInCompare);

			verifySizeOfListWebElements("Details Section", sf.knowledgeDetails.detailsSectionInCompare, 2);

			verifySizeOfListWebElements("Article Total View Section", sf.knowledgeDetails.articleTotalViewInCompare, 2);
			printTextOfListWebElements("Article Total View Count", sf.knowledgeDetails.articleTotalViewTextInCompare);

			verifySizeOfListWebElements("Content Section", sf.knowledgeDetails.contentSectionInCompare, 2);

			verifySizeOfListWebElements("System Information Section",
					sf.knowledgeDetails.systemInformationSectionInCompare, 2);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful article version comparison", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Article record type is General Article.
	 * 
	 *                     Publication status is Draft.
	 * 
	 *                     Version Number is 0 for a draft.
	 * 
	 */
	@SuppressWarnings("static-access")
	public void verifyArticleDraftCreated() throws IOException {

		try {

			sf.seleU.wait(3000);
			verifyFieldDisplayed("Knowledge Header", sf.knowledgeDetails.knowledgeHeaderText);
			verifyFieldValue("Article Title", sf.knowledgeDetails.articleNameHeader, sf.dataInput.articleName);
			verifyFieldValue("Article Record Type", sf.knowledgeDetails.articleRecordType,
					sf.dataInput.articleRecordType);
			verifyFieldValue("Publication Status", sf.knowledgeDetails.publicationStatus,
					sf.dataInput.publicationStatusDraft);
			verifyFieldValue("Version Number", sf.knowledgeDetails.articleVersionNumber,
					sf.dataInput.articleVersionZero);

		} catch (Throwable e) {
			reportStatusFail(" Cannot verify draft article details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Publication status is Published.
	 * 
	 *                     Version Number is not 0.
	 * 
	 *                     Feedback is enabled.
	 * 
	 *                     Chatter is enabled
	 * 
	 */
	public void verifyPublishedArticle() throws IOException {

		try {

			sf.seleU.wait(3000);
			sf.seleU.refreshPage();
			verifyFieldDisplayed("Knowledge Header", sf.knowledgeDetails.knowledgeHeaderText);
			verifyFieldValue("Article Title", sf.knowledgeDetails.articleNameHeader, sf.dataInput.articleName);
			verifyFieldValue("Article Record Type", sf.knowledgeDetails.articleRecordType,
					sf.dataInput.articleRecordType);
			verifyFieldValue("Publication Status", sf.knowledgeDetails.publicationStatus,
					sf.dataInput.publicationStatusPublished);
			verifyFieldNotValue("Version Number", sf.knowledgeDetails.articleVersionNumber,
					sf.dataInput.articleVersionZero);
			verifyFieldEnabled("FeedBack Like Button", sf.knowledgeDetails.feedBackLikeButton);
			verifyFieldEnabled("FeedBack DisLike Button", sf.knowledgeDetails.feedBackDislikeButton);
			verifyFieldDisplayed("Chatter Section", sf.knowledgeDetails.writeCommentInputBox);

		} catch (Throwable e) {
			reportStatusFail(" Cannot verify published article details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Publish Article.
	 * 
	 */
	public void publishArticle() throws IOException {

		try {
			// Click on Publish Button and verify Dialog Box opened
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.knowledgeDetails.publishArticle);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Publish Article Dialog Header", sf.knowledgeDetails.publishDialogHeader);
			verifyFieldDisplayed("Publish Now Span", sf.knowledgeDetails.publishNowSpan);
			verifyFieldDisplayed("Publish On a Date Span", sf.knowledgeDetails.publishOnADateSpan);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.publishDialogPubButton);

		} catch (Throwable e) {
			reportStatusFail(" Cannot publish article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Approve Article.
	 * 
	 */
	public void approveArticle() throws IOException {

		try {
			// Click on Approve Button and verify Dialog Box opened
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.relatedTab);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Approve Button", sf.knowledgeDetails.approveButton);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.approveButton);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Approve Article Dialog Box Header", sf.knowledgeDetails.approveKnowledgeDialogHeader);

			sf.seleU.clickElementByJSE(sf.knowledgeDetails.approveDialogButton);
			sf.seleU.wait(3000);
			verifyFieldValue("Publication Status", sf.knowledgeDetails.publicationStatus,
					sf.dataInput.publicationStatusPublished);
		} catch (Throwable e) {
			reportStatusFail(" Cannot approve article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Submit Draft Article for approval.
	 * 
	 */
	public void submitArticleForApproval() throws IOException {

		try {
			// Click on Submit for Approval Button and verify Dialog Box opened
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.submitForApprovalArticle);
			sf.seleU.wait(3000);
			verifyFieldDisplayed("Submit Article For Approval Dialog Header",
					sf.knowledgeDetails.submitForApprovalDialogHeader);

			// Enter comments and submit
			sf.seleU.enterText(sf.knowledgeDetails.commentsForApproval, addOn_1);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.submitButton);

		} catch (Throwable e) {
			reportStatusFail(" Cannot submit article for approval", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Approve request is in the related approval
	 *                     history.
	 * 
	 */
	public void verifyApprovalHistory() throws IOException {

		try {
			// Go to Related Tab
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.relatedTab);

			// Verify Approval History Section
			verifyFieldDisplayed("Approval History Section", sf.knowledgeDetails.approvalHistorySection);
			verifyFieldDisplayed("Approval History List", sf.knowledgeDetails.approvalList);

		} catch (Throwable e) {
			reportStatusFail(" Cannot verify approve request in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Title, summary, content, visible to customer are
	 *                     editable.
	 * 
	 *                     Draft article is able to be edited
	 * 
	 */
	public void editDraftArticle() throws IOException {

		try {

			// Edit the Draft article - Title, Summary, Content, Visible To Customer
			sf.seleU.clickOnElement(sf.knowledgeDetails.editArticle);
			sf.seleU.wait(4000);
			verifyFieldDisplayed("Edit Draft Header", sf.knowledgeDetails.editArticleDialogHeader);
			verifyFieldDisplayed("Information Section", sf.knowledgeDetails.editArticleDialogInformationSection);

			sf.dataInput.articleName = sf.dataInput.articleName + AdditionalUtilities.generateRandomCharacters(2);
			sf.seleU.clearAndEnterText(sf.knowledge.titleInputBox, sf.dataInput.articleName);
			sf.seleU.clearAndEnterText(sf.knowledge.summaryTextArea, sf.dataInput.articleName);
			sf.seleU.switchToFrame(sf.knowledge.contentEditorIFrame);
			sf.seleU.switchToFrame(sf.knowledge.contentEditorSubFrame);
			sf.seleU.wait(3000);

			sf.seleU.clearAndEnterText(sf.knowledge.contentEditorTextArea, sf.dataInput.articleName);
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.knowledge.visibleToCustomerCheckBox);
			reportStatusPass(methodName + " Edited title, summary, content and visible to customer : "
					+ sf.dataInput.articleName, true, false);

			sf.seleU.clickElementByJSE(sf.knowledge.saveArticleButton);
			sf.seleU.wait(3000);
			// Verify edited successfully
			verifyFieldValue("Edited Title", sf.knowledgeDetails.titleText, sf.dataInput.articleName);
			verifyFieldValue("Edited Summary", sf.knowledgeDetails.summaryText, sf.dataInput.articleName);

		} catch (Throwable e) {
			reportStatusFail(" Cannot edit draft article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Edit Article categories.
	 * 
	 *                     Product, Function and Content type (Values refer to
	 *                     MPOSS-14935).
	 * 
	 *                     Multiple selection is allowed
	 * 
	 */
	public void editArticleCatogories() throws IOException {

		try {
			// Click on Edit
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.categoryEditOpenIcon);
			sf.seleU.wait(1000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.categoryEditButton);

			// Verify Edit Categories Dialog Box is displayed
			verifyFieldDisplayed("Edit Article Categories Dialog Header",
					sf.knowledgeDetails.categoryEditDialogBoxHeader);

			// Verify 3 types of Categories Present
			sf.dataInput.setArticleCategoriesList();
			List<String> expectedArticleCategories = sf.dataInput.articleCategories;
			List<String> actualArticleCategories = new ArrayList<String>();

			// Loop over number of List Views to be verified
			for (int i = 0; i < sf.knowledgeDetails.categoryList.size(); i++) {
				actualArticleCategories.add(sf.seleU.getTextFromWebElement(sf.knowledgeDetails.categoryList.get(i)));
			}
			reportStatusPass(methodName + " Extracted all article categories to verify with expected list", false,
					false);

			// sort lists for comparison
			Collections.sort(expectedArticleCategories);
			Collections.sort(actualArticleCategories);

			// Verify expected list is equal to actual List

			if (expectedArticleCategories.equals(actualArticleCategories)) {
				reportStatusPass(methodName + " All expected article categories are present : "
						+ AdditionalUtilities.getAsString(actualArticleCategories), true, true);
			} else {
				reportStatusFail(methodName + " All expected article categories are not present :: Expected List--> "
						+ AdditionalUtilities.getAsString(expectedArticleCategories) + "  Actual List --> "
						+ AdditionalUtilities.getAsString(actualArticleCategories), true);
			}

			// Select Function and Product type categpry
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.categoryFunctionCheckBox);
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.categoryProductCheckBox);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Selected Function and Product category for Article", true, false);

			// Verify Multi Select was successful
			verifyFieldDisplayed("Selected Category : Function", sf.knowledgeDetails.selectedCategoryFunction);
			verifyFieldDisplayed("Selected Category : Product", sf.knowledgeDetails.selectedCategpryProduct);

			// Save
			sf.seleU.clickElementByJSE(sf.knowledge.saveArticleButton);

			// Verify Categories Edited successfully
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Categories(2) Span", sf.knowledgeDetails.categoriesNumberSpan);

		} catch (Throwable e) {
			reportStatusFail(" Cannot edit article categories", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Published Article can be edited.
	 * 
	 */
	public void editAsDraft() throws IOException {

		try {

			// Edit the Draft article - Title, Summary, Content, Visible To Customer
			sf.seleU.clickOnElement(sf.knowledgeDetails.editAsDraft);
			sf.seleU.wait(4000);
			verifyFieldDisplayed("Edit As Draft Header", sf.knowledgeDetails.editAsDraftDialogHeader);
			verifyFieldDisplayed("Edit As Draft Message", sf.knowledgeDetails.editAsDraftTextMessage);

			sf.seleU.clickElementByJSE(sf.knowledgeDetails.editAsDraftDialogButton);
			sf.seleU.wait(4000);
			sf.seleU.clearAndEnterText(sf.knowledge.summaryTextArea, sf.dataInput.articleName + addOn_2);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.knowledge.saveArticleButton);
			sf.seleU.wait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Cannot edit draft article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Feedback section is not editable in Draft Article
	 */
	public void verifyFeedBackNotEditable() throws IOException {
		try {

			// Verify field not editable
			verifyFieldDisabled("Like Button", sf.knowledgeDetails.feedBackLikeButton);
			verifyFieldDisabled("Dislike Button", sf.knowledgeDetails.feedBackDislikeButton);

		} catch (Throwable e) {
			reportStatusFail(" Cannot verify feedback section is uneditable in draft article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Delete Draft
	 * 
	 */
	public void deleteDraft() throws IOException {
		try {

			deleteDraftArticle();

			// Verify Draft Deleted Successfully
			// verifyFieldNotDisplayed("Deletion Success Message",
			// sf.knowledgeDetails.deleteSuccessMessage);

		} catch (Throwable e) {
			reportStatusFail(" Cannot delete draft article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Try to Delete Draft and verify deletion failure message
	 * 
	 */
	public void verifyDraftNotDeletable() throws IOException {
		try {

			deleteDraftArticle();
			// Verify Draft was not Deleted Successfully
			verifyFieldDisplayed("Delete Draft Button", sf.knowledgeDetails.deleteDraft);
			verifyFieldDisplayed("Related Tab of Article", sf.knowledgeDetails.relatedTab);
			reportStatusPass(methodName + " Verified Darft was not deleted", true, true);
		} catch (Throwable e) {
			reportStatusFail(" Cannot verify that draft article is not deletable", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Perform Delete Draft
	 * 
	 */
	public void deleteDraftArticle() throws IOException {
		try {

			// Click on Delete Draft button
			sf.seleU.wait(4000);
			if (!sf.seleU.isElementDisplayed(sf.knowledgeDetails.deleteDraft)) {
				sf.seleU.clickOnElement(sf.knowledgeDetails.showMoreActions);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.knowledgeDetails.deleteDraftDropDownButton);
			} else {
				sf.seleU.clickElementByJSE(sf.knowledgeDetails.deleteDraft);
			}
			// Verify Delete Draft Dialog Box
			sf.seleU.wait(4000);
			verifyFieldDisplayed("Delete Draft Dialog Header", sf.knowledgeDetails.deleteDraftDialogHeader);
			verifyFieldDisplayed("Delete Draft Warning Message", sf.knowledgeDetails.deleteDraftDialogWarningText);

			// Confirm Deletion
			sf.seleU.clickElementByJSE(sf.knowledgeDetails.deleteDraftDialogDelButton);

		} catch (Throwable e) {
			reportStatusFail(" Cannot delete draft article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is not editable
	 */
	public void verifyFieldDisabled(String fieldName, WebElement element) throws IOException {
		try {

			if (!element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is disabled and not editable", true,
						true);
			} else {
				reportStatusFail(methodName + " Field " + fieldName + " is not a disabled field, It should be disabled",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is enabled
	 */
	public void verifyFieldEnabled(String fieldName, WebElement element) throws IOException {
		try {

			if (element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is enabled", true, true);
			} else {
				reportStatusFail(methodName + " Field " + fieldName + " is disabled field, It should be enabled", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Size of List<WebElement>
	 */
	public void verifySizeOfListWebElements(String fieldName, List<WebElement> element, int expectedSize)
			throws IOException {
		try {
			sf.seleU.wait(3000);
			// Verify Field value matches the expected result
			if (element.size() == expectedSize) {
				reportStatusPass(methodName + " Validated " + fieldName + " is present for " + expectedSize
						+ " versions of article for comparison.", true, true);

			} else {
				reportStatusFail(methodName + " Actual number of " + fieldName + " present for article is "
						+ element.size() + " And Expected One is " + expectedSize + " for comparison", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying size of List of webelements", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Print text of all elements in List<WebElement>
	 */
	public void printTextOfListWebElements(String fieldName, List<WebElement> element) throws IOException {
		try {
			sf.seleU.wait(3000);
			for (int i = 0; i < element.size(); i++)
				reportStatusPass(methodName + " Text of " + fieldName + ", Occurrence no. " + i + " is "
						+ sf.seleU.getTextFromWebElement(element.get(i)), false, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in printing text of web elements", e);
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
			sf.seleU.wait(3000);
			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + "Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + "Actual Value for " + fieldName + " is " + element.getText()
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
	 *                     Verify Field value does not matches the expected result
	 */
	public void verifyFieldNotValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			sf.seleU.wait(3000);
			// Verify Field value matches the expected result
			if (!sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + "Validated " + fieldName + " does not have the text as " + expectedText,
						true, true);
			} else {
				reportStatusFail(methodName + "Field has same value, should have been different than expectedText: "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Wait for element to be visible
	 */
	public void waitForElementToBeVisible(WebElement element) throws IOException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Throwable e) {
			reportStatusFail(" Error in waiting for element to be present", e);
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
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
