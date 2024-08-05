package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 20/Jan/2021
 * 
 *         SFDC Knowledge page
 */
public class SFDC_Knowledge_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Knowledge@: ";

	public SFDC_Knowledge_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify List Views in Knowledge Section
	 * 
	 */
	public void verifyListViews() throws IOException {
		try {
			openKnowledgeListView("NA", null);

			reportStatusPass(methodName + " Opened list view from Knowledge Section.", false, false);

			Global.dataInput.setArticleListViews();
			List<String> expectedArticleViews = InputData.articleListViews;
			List<String> actualArticleViews = new ArrayList<String>();

			// Loop over number of List Views to be verified
			for (int i = 0; i < sf.knowledge.listViews.size(); i++) {
				actualArticleViews.add(sf.seleU.getTextFromWebElement(sf.knowledge.listViews.get(i)));
			}
			reportStatusPass(
					methodName
							+ " Extracted all article list views from Knowledge Section to verify with expected list",
					false, false);

			// sort lists for comparison
			Collections.sort(expectedArticleViews);
			Collections.sort(actualArticleViews);

			// Verify expected list is equal to actual List

			if (expectedArticleViews.equals(actualArticleViews)) {
				reportStatusPass(methodName + " All expected article list views are present in knowledge section : "
						+ AdditionalUtilities.getAsString(actualArticleViews), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected article list views are not present in knowledge section :: Expected List Views--> "
						+ AdditionalUtilities.getAsString(expectedArticleViews) + "  Actual List Views--> "
						+ AdditionalUtilities.getAsString(actualArticleViews), true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Knowledge List View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Knowledge List Page
	 * 
	 *                     New and delete draft buttons are available
	 * 
	 */
	public void verifyKnowledgeListPageForArticleAuthor() throws IOException {
		try {

			openKnowledgeListView("Draft Articles", sf.knowledge.draftArticles);
			verifyFieldDisplayed("New Article Button", sf.knowledge.newArticleButton);
			verifyFieldDisplayed("Delete Draft Button", sf.knowledge.deleteDraftButton);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Knowledge List Page for Article Author", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Knowledge List Page for Article Manager
	 * 
	 *                     New, Delete draft , Restore and Archive buttons are
	 *                     available
	 * 
	 */
	public void verifyKnowledgeListPageForArticleManager() throws IOException {
		try {
			openKnowledgeListView("Draft Articles", sf.knowledge.draftArticles);
			verifyFieldDisplayed("New Article Button", sf.knowledge.newArticleButton);
			verifyFieldDisplayed("Delete Draft Button", sf.knowledge.deleteDraftButton);
			verifyFieldDisplayed("Restore Article Button", sf.knowledge.restoreArticleButton);
			verifyFieldDisplayed("Archive Article Button", sf.knowledge.archiveArticleButton);
			verifyFieldDisplayed("Publish Article Button", sf.knowledge.publishArticleButton);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Knowledge List Page for Article Manager", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open Specified Knowledge List View
	 * 
	 */
	public void openKnowledgeListView(String viewName, WebElement listView) throws IOException {
		try {
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Knowledge
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.knowledgeMenu);
			reportStatusPass(methodName + " Selected Knowledge from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select Draft articles
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			if (!viewName.equalsIgnoreCase("NA")) {
				sf.seleU.clickElementByJSE(listView);
				reportStatusPass(methodName + " Selected " + viewName + " list view from Knowledge Section.", true,
						false);
				sf.seleU.hardwait(2000);
			}

		} catch (Throwable e) {
			reportStatusFail(" Cannot open Knowledge List View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open Table View if not already opened.
	 * 
	 */
	public void openTableView() throws IOException {
		try {
			if (sf.seleU.isElementDisplayed(sf.knowledge.defaultViewIsSplitView)) {
				sf.seleU.clickElementByJSE(sf.knowledge.defaultSplitViewButton);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.knowledge.tableViewButton);
				reportStatusPass(methodName + " Changed the Split View to Table View", true, false);

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot Open Table View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify created Draft Article is listed in Drafts List
	 *                     View
	 * 
	 */
	public void verifyArticleListedInDraftsView() throws IOException {
		try {

			openKnowledgeListView("Draft Articles", sf.knowledge.draftArticles);

			// Find newly created article in the articles listed
			if (!findArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!findArticle()) {
					reportStatusFail(methodName + " Cannot find expected article (" + InputData.articleName
							+ ") in the Drafts list view", true);
				} else {
					reportStatusPass(methodName + " The new article (" + InputData.articleName
							+ ") is listed in the Drafts List View", true, false);
				}
			} else {
				reportStatusPass(methodName + " The new article (" + InputData.articleName
						+ ") is listed in the Drafts List View", true, false);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Draft article presence in Draft List View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Draft Article cannot be archived
	 * 
	 */
	public void verifyDraftArticleCannotBeArchived() throws IOException {
		try {

			openKnowledgeListView("Draft Articles", sf.knowledge.draftArticles);
			System.out.println("1");
			// Find newly created draft article in the articles listed and select it for
			// archiving
			if (!selectArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!selectArticle()) {
					reportStatusFail(methodName + " Cannot find expected article (" + InputData.articleName
							+ ") in the Drafts list view", true);
				} else {
					reportStatusPass(
							methodName + " Selected article (" + InputData.articleName + ") in the Drafts List View",
							true, false);
				}
			} else {
				reportStatusPass(
						methodName + " Selected article (" + InputData.articleName + ") in the Drafts List View", true,
						false);
			}

			// Archive
			sf.seleU.clickOnElement(sf.knowledge.archiveArticleButton);
			sf.seleU.wait(4000);

			// Verify Archive Dialog Box
			verifyFieldDisplayed("Archive Article Dialog Box Header", sf.knowledge.archiveArticleDialogHeader);
			verifyFieldDisplayed("Archive Article Dialog Box Message", sf.knowledge.archiveArticleMessage);
			// Click on Archive in Dialog Box and verify Error message
			sf.seleU.clickElementByJSE(sf.knowledge.archiveDialogArchButton);

			sf.seleU.wait(2000);

			verifyFieldDisplayed("Archive Draft Article Error Message", sf.knowledge.archiveErrorMessage);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Draft article cannot be archived", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Archive Published Article
	 * 
	 */
	public void archivePublishedArticle() throws IOException {
		try {

			openKnowledgeListView("Published English Articles", sf.knowledge.publishedEnglishArticles);

			// Find newly created published article in the articles listed and select it for
			// archiving
			if (!selectArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!selectArticle()) {
					reportStatusFail(methodName + " Cannot find expected article (" + InputData.articleName
							+ ") in the Published list view", true);
				} else {
					reportStatusPass(
							methodName + " Selected article (" + InputData.articleName + ") in the Published List View",
							true, false);
				}
			} else {
				reportStatusPass(
						methodName + " Selected article (" + InputData.articleName + ") in the Published List View",
						true, false);
			}

			// Archive
			sf.seleU.clickElementByJSE(sf.knowledge.archiveArticleButton);
			sf.seleU.wait(4000);

			// Verify Archive Dialog Box
			verifyFieldDisplayed("Archive Article Dialog Box Header", sf.knowledge.archiveArticleDialogHeader);
			verifyFieldDisplayed("Archive Article Dialog Box Message", sf.knowledge.archiveArticleMessage);

			// Click on Archive in Dialog Box and verify success message
			sf.seleU.clickElementByJSE(sf.knowledge.archiveDialogArchButton);
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Archive Draft Article Success Message", sf.knowledge.archiveSucessMessage);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot archive Published Article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Restore Archived Article
	 * 
	 */
	public void restoreArchivedArticle() throws IOException {
		try {

			openKnowledgeListView("Archived Articles", sf.knowledge.archivedArticles);

			// Find archived article in the articles listed and select it for restoring
			if (!selectArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!selectArticle()) {
					reportStatusFail(methodName + " Cannot find expected article (" + InputData.articleName
							+ ") in the Archived list view", true);
				} else {
					reportStatusPass(
							methodName + " Selected article (" + InputData.articleName + ") in the Archived List View",
							true, false);
				}
			} else {
				reportStatusPass(
						methodName + " Selected article (" + InputData.articleName + ") in the Archived List View",
						true, false);
			}

			// Restore
			sf.seleU.clickElementByJSE(sf.knowledge.restoreArticleButton);
			sf.seleU.wait(4000);

			// Verify Restore Dialog Box
			verifyFieldDisplayed("Restore Article Dialog Box Header", sf.knowledge.restoreArticleDialogHeader);
			verifyFieldDisplayed("Restore Article Dialog Box Message", sf.knowledge.restoreArticleDialogMessage);

			// Click on Restore in Dialog Box and verify success message
			sf.seleU.clickElementByJSE(sf.knowledge.restoreDialogRestButton);
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Restore Article Success Message", sf.knowledge.restoreSucessMessage);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot restore archived Article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Article is not listed in Published List View
	 * 
	 */
	public void verifyArticleNotInPublishedList() throws IOException {
		try {

			openKnowledgeListView("Published List View", sf.knowledge.publishedEnglishArticles);

			// Find article is not present in Published List
			if (!findArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!findArticle()) {
					reportStatusPass(methodName + " Cannot find article (" + InputData.articleName
							+ ") in the Published list view as expected", true, true);
				} else {
					reportStatusFail(
							methodName
									+ "Found article in Published List View, It should have been removed from the list",
							true);
				}
			} else {
				reportStatusFail(
						methodName + "Found article in Published List View, It should have been removed from the list",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Published article absence in Published List View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Article is listed in Archive List View
	 * 
	 */
	public void verifyArticleInArchiveList() throws IOException {
		try {

			openKnowledgeListView("Archive List View", sf.knowledge.archivedArticles);

			// Find article is present in Archived List
			if (!findArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!findArticle()) {
					reportStatusFail(methodName + " Cannot find expected article (" + InputData.articleName
							+ ") in the Archived list view", true);
				} else {
					reportStatusPass(methodName + " The article (" + InputData.articleName
							+ ") is listed in the Archived List View", true, false);
				}
			} else {
				reportStatusPass(
						methodName + " The article (" + InputData.articleName + ") is listed in the Archived List View",
						true, false);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify article presence in Archived List View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify deleted Draft Article is not listed in Drafts List
	 *                     View
	 * 
	 */
	public void verifyArticleNotListedInDraftsView() throws IOException {
		try {

			openKnowledgeListView("Draft List View", sf.knowledge.draftArticles);

			// Find draft article is not listed in Draft Articles
			if (!findArticle()) {
				reportStatusPass(methodName + " Sorting by article number column to find the article", false, false);
				sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " Sorted by article number column.", true, false);
				if (!findArticle()) {
					reportStatusPass(methodName + " Cannot find deleted article (" + InputData.articleName
							+ ") in the Drafts list view as expected", true, true);
				} else {
					reportStatusFail(methodName
							+ "Found deleted article in Drafts List View, It should have been removed from the list",
							true);
				}
			} else {
				reportStatusFail(methodName
						+ "Found deleted article in Drafts List View, It should have been removed from the list", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify Draft article absence in Draft List View", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Find created Draft Article is listed in Drafts List View
	 * 
	 */
	public boolean findArticle() throws IOException {

		boolean found = false;
		try {
			for (int i = 0; i < sf.knowledge.listArticleLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.knowledge.listArticleLinks.get(i))
						.equalsIgnoreCase(InputData.articleName)) {
					found = true;
					sf.seleU.clickElementByJSE(sf.knowledge.listArticleLinks.get(i));
					break;
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot find Draft article in Draft List View", e);
			e.printStackTrace();
		}
		return found;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Find and open Article
	 * 
	 */
	public boolean findAndOpenArticle(String articleName) throws IOException {

		boolean found = false;

		try {
			for (int i = 0; i < sf.knowledge.listArticleLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.knowledge.listArticleLinks.get(i))
						.equalsIgnoreCase(articleName)) {
					reportStatusPass(methodName + " Found article with name : " + articleName, true, true);
					sf.seleU.clickOnElement(sf.knowledge.listArticleLinks.get(i));
					reportStatusPass(methodName + " Opening the found article", false, false);
					sf.seleU.hardwait(5000);
					found = true;
					break;
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot find and open article", e);
			e.printStackTrace();
		}
		return found;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Find Article and select it
	 * 
	 */
	public boolean selectArticle() throws IOException {

		boolean found = false;
		try {
			for (int i = 0; i < sf.knowledge.listArticleLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.knowledge.listArticleLinks.get(i))
						.equalsIgnoreCase(InputData.articleName)) {
					found = true;
					sf.seleU.clickOnElement(sf.knowledge.listTrKnowledgeSelectCheckBox.get(i));
					break;
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot select expected article from list", e);
			e.printStackTrace();
		}
		return found;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on New button and write a new article
	 * 
	 *                     New article is created
	 */
	public void createNewGeneralArticle() throws IOException {
		try {

			// Click on New and validate landing Page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.knowledge.newArticleButton);
			reportStatusPass(methodName + " Clicked on New Article button", false, false);
			sf.seleU.hardwait(3000);
			verifyFieldDisplayed("New Knowledge Span", sf.knowledge.newKnowledgeSpan);
			verifyFieldDisplayed("General Article Span", sf.knowledge.generalArticleSpan);

			// Click Next
			sf.seleU.clickElementByJSE(sf.knowledge.nextButton);

			// Verify Landing Page (Fill details of article)
			verifyFieldDisplayed("New Knowledge : General Article Span", sf.knowledge.newKnowledgeGeneralArticleSpan);

			// Verify and fill the form
			verifyFieldDisplayed("Title Span", sf.knowledge.titleSpan);
			InputData.articleName = InputData.articleName + AdditionalUtilities.generateRandomCharacters(2);
			sf.seleU.enterText(sf.knowledge.titleInputBox, InputData.articleName);

			verifyFieldDisplayed("URL Name Span", sf.knowledge.urlNameSpan);
			sf.seleU.enterText(sf.knowledge.urlNameInputBox, InputData.articleName);

			verifyFieldDisplayed("Summary Textarea", sf.knowledge.summarySpan);
			sf.seleU.enterText(sf.knowledge.summaryTextArea, InputData.articleName);

			verifyFieldDisplayed("Details Section", sf.knowledge.detailsSection);
			verifyFieldDisplayed("Visible to Customer Section", sf.knowledge.visibleToCustomerSpan);
			verifyFieldDisplayed("Visible to Customer Checkbox", sf.knowledge.visibleToCustomerCheckBox);
			verifyFieldDisplayed("Language Span", sf.knowledge.languageSpan);

			reportStatusPass(methodName + " Selected Language is : "
					+ sf.seleU.getTextFromWebElement(sf.knowledge.selectedLanguage), false, false);

			verifyFieldDisplayed("Contents Section", sf.knowledge.contentSection);

			// Switch to Content frame and enter content
			sf.seleU.switchToFrame(sf.knowledge.contentEditorIFrame);
			sf.seleU.switchToFrame(sf.knowledge.contentEditorSubFrame);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.knowledge.contentEditorTextArea, InputData.articleName);
			sf.seleU.switchToDefaultContent();
			verifyFieldDisplayed("System Information Section", sf.knowledge.systemInformationSection);

			sf.seleU.clickElementByJSE(sf.knowledge.saveArticleButton);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating new article", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Through Knowledge object, Open an article
	 * 
	 */
	public void openArticle(String articleName) throws IOException {
		try {

			openKnowledgeListView("Published English Articles", sf.knowledge.publishedEnglishArticles);

			// Verify Articles are displayed
			if (sf.knowledge.listTrKnowledge.size() > 0) {

				reportStatusPass(methodName + " Articles are present", true, true);

				// Find and open required article
				if (!findAndOpenArticle(articleName)) {
					reportStatusPass(methodName + " Sorting by article number column to find the article", false,
							false);
					sf.seleU.clickElementByJSE(sf.knowledge.articleNumberToggle);
					sf.seleU.hardwait(5000);
					reportStatusPass(methodName + " Sorted by article number column.", true, false);
					if (!findAndOpenArticle(articleName)) {
						reportStatusFail(methodName + " Cannot find expected article (" + articleName + ")", true);
					}
				}
			} else {
				reportStatusFail(methodName + " No Article present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot open article from knowledge menu", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Through Knowledge object, Open an article
	 * 
	 *                     Opening article for details validation
	 * 
	 */
	public void openArticleForDetailsValidation() throws IOException {
		try {
			openArticle(InputData.articleNameForDetailValidation);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot open article from knowledge menu", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Through Knowledge object, Open an article
	 * 
	 *                     Opening article for version comparison
	 * 
	 */
	public void openArticleForVersionComparison() throws IOException {
		try {
			openArticle(InputData.articleNameForVersionValidation);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot open article from knowledge menu", e);
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
			reportStatusFail(methodName + " Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
