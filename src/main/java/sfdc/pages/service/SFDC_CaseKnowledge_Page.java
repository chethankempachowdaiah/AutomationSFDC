package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 20/Jan/2021
 * 
 */
public class SFDC_CaseKnowledge_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_CaseKnowledge_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Case Knowledge@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open a case and validate knowledge widget
	 * 
	 *                     Knowledge widget is displayed next to the related list as
	 *                     a tab.
	 * 
	 *                     Relevant articles are displayed in the knowledge widget
	 *                     based on the case subject
	 * 
	 * 
	 */
	public void verifyKnowledgeWidget() throws IOException {

		try {
			// Open Knowledge Tab
			sf.seleU.hardwait(10000);
			sf.seleU.clickOnElement(sf.cases.moreTabs);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseKnowledge.knowledgeTab);

			reportStatusPass(methodName + "Clicked on Case Knowledge tab", true, true);

			// Verify Knowledge tab opened successfully
			verifyFieldDisplayed("Knowledge Header Span", sf.caseKnowledge.knowledgeHeaderSpan);

			// Print number of relevant articles listed
			if (sf.seleU.isElementDisplayed(sf.caseKnowledge.numberofRelevantArticlesListed)) {
				reportStatusPass(
						methodName + "Number of Relevant articles listed : "
								+ sf.seleU.getTextFromWebElement(sf.caseKnowledge.numberofRelevantArticlesListed),
						true, false);

				// Print names of all relevant articles listed
				for (int i = 0; i < sf.caseKnowledge.relevantArticlesListLinks.size(); i++)
					reportStatusPass(
							methodName + "Relevant Article listed no. " + i + " --> "
									+ sf.seleU.getTextFromWebElement(sf.caseKnowledge.relevantArticlesListLinks.get(i)),
							false, false);
			}

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful validation for knowledge widget in Case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Articles can be searched.
	 * 
	 *                     Articles are displayed in the search result and can be
	 *                     attached to the case
	 * 
	 */
	public void searchAndAttachAnArticle() throws IOException {

		try {

			// Search an article to attach to the case
			sf.seleU.enterText(sf.caseKnowledge.searchKnowledgeInputBox, sf.dataInput.articleNameForDetailValidation);
			sf.seleU.enterText(sf.caseKnowledge.searchKnowledgeInputBox, Keys.ENTER);

			// Print names of all results of searched articles
			for (int i = 0; i < sf.caseKnowledge.searchedArticleLinks.size(); i++)
				reportStatusPass(
						methodName + "Searched Article listed no. " + i + " --> "
								+ sf.seleU.getTextFromWebElement(sf.caseKnowledge.searchedArticleLinks.get(i)),
						true, false);

			// Attach article to Case
			sf.seleU.clickElementByJSE(sf.caseKnowledge.showMoreButtonForArticleOptions);
			sf.seleU.hardwait(2000);
			sf.seleU.clickOnElement(sf.caseKnowledge.attachArticleButton);
			reportStatusPass(methodName + "Attached article to case", false, false);

		} catch (Throwable e) {
			reportStatusFail(" Unable to attach article to Case", e);
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
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
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
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + "Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
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
}
