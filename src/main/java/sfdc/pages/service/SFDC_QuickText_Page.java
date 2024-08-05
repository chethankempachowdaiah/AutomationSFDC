package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 29/MAY/2020
 * 
 *         List View> Quick Text> Quick Text Page Objects
 *
 */
public class SFDC_QuickText_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;

	public SFDC_QuickText_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Quick Text From menu
	 */
	public void selectQuickText() throws IOException {
		try {

			String methodName = "SFDC_Quick Text@: ";

			// Click on app launcher and search for Quick Text
			sf.seleU.clickElementByJSE(sf.home.applauncher);

			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, sf.dataInput.quickTextObject);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(sf.dataInput.quickTextObject)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}

			reportStatusPass(methodName + " Clicked Quick Text Object ", true, false);
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting Quick Text ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on New Quick Text Button
	 * 
	 *                              Enter Quick Text Information
	 * 
	 *                              Validate Quick Text Information
	 */
	public void createQuickText() throws IOException, InterruptedException {

		try {
			String methodName = "SFDC_Create Quick Text@: ";

			// Click on New Quick Text Button
			sf.seleU.clickElementByJSE(sf.qt.newquickTextButton);
			reportStatusPass(methodName + "Clicked on New Quick Text Button", true, false);

			// Enter Quick Text Information
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.qt.quickTextName, sf.dataInput.quickTextName);
			sf.seleU.clickElementByJSE(sf.qt.relatedToDropdown);
			sf.seleU.clickElementByJSE(sf.qt.relatedToCaseOption);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.qt.fieldDropdown);
			sf.seleU.clickElementByJSE(sf.qt.fieldCaseNumberOption);
			sf.seleU.enterText(sf.qt.quikcTextMsgArea, sf.dataInput.quickTextName);
			sf.seleU.enterText(sf.qt.quikcTextMsgArea, Keys.TAB);
			sf.seleU.clickElementByJSE(sf.qt.insertButton);
			sf.seleU.clickElementByJSE(sf.qt.categoryDropdown);
			sf.seleU.clickElementByJSE(sf.qt.categoryGreetingsOption);
			sf.seleU.clickElementByJSE(sf.qt.saveButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Entered Quick Text Information for new Quick Text", true, false);
			verifyFieldDisplayed("Quick Text Created Msg", sf.qt.quickTextCreatedMsg);

			// Validate Quick Text Information
			verifyFieldValue("Quick Text Name", sf.qt.quickTextNameValue, sf.dataInput.quickTextName);
			verifyFieldValue("Quick Text Message", sf.qt.quickTextMsgValue,
					sf.dataInput.quickTextName + " {!Case.CaseNumber}");
			verifyFieldValue("Quick Text Category", sf.qt.categoryValue, sf.dataInput.quickTextCategory);
			verifyFieldValue("Quick Text Channel", sf.qt.channelValue, sf.dataInput.quickTextChannel);

		} catch (Throwable e) {
			reportStatusFail(" Error in Creating Quick Text ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Quick Text
	 * 
	 *                     Delete Quick Text
	 * 
	 *                     Verify Quick Text deleted Successfully
	 */
	public void deleteQuickText() throws IOException {
		try {

			String methodName = "SFDC_Create Quick Text@: ";

			// Search Quick Text
			sf.seleU.enterText(sf.qt.quickTextSearch, sf.dataInput.quickTextName);
			sf.seleU.enterText(sf.qt.quickTextSearch, Keys.ENTER);
			reportStatusPass(methodName + "Entered Quick Text Name in quick text search box", true, false);

			// Delete Quick Text

			sf.seleU.wait(4000);
			for (int i = 0; i < sf.qt.quickTextNameAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.qt.quickTextNameAllRows.get(i))
						.equals(sf.dataInput.quickTextName)) {
					sf.seleU.clickElementByJSE(sf.qt.showActionsIconAllRows.get(i));
					sf.seleU.clickElementByJSE(sf.qt.deleteTextIcon);
					sf.seleU.clickElementByJSE(sf.qt.deleteButton);
					break;
				}
			}

			// Verify Quick Text deleted Successfully
			// verifyFieldDisplayed("Quick Text Deleted Msg", sf.qt.quickTextDeletedMsg);

		} catch (Throwable e) {
			reportStatusFail(" Error in deleting Quick Text ", e);
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
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
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
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field is displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail("Error: " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
