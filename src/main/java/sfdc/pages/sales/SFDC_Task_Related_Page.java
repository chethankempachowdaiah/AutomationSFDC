package sfdc.pages.sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 19/Apr/2021
 * 
 *         SFDC Tasks page
 */
public class SFDC_Task_Related_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_TaskRelated@: ";

	public SFDC_Task_Related_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 * 				Verify Task Related - Notes Section             
	 * 
	 */
	public void verifyNotesSection() throws IOException {
		try {

			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.taskRelated.relatedTab);
			sf.seleU.wait(5000);
			
			// Verify Notes Section is displayed
			verifyFieldDisplayed("Notes Section", sf.taskRelated.notesSection);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying task details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 				Verify Task Related - Create Notes and verify           
	 * 
	 */
	public void createAndVerifyNote() throws IOException {
		try {

			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.taskRelated.newNotesButton);
			sf.seleU.wait(3000);
			
			// Verify Task Details Header is displayed
			verifyFieldDisplayed("Note Dailog Box", sf.taskRelated.notesDialogBox);
			
			//Fill Note Details
			sf.seleU.enterText(sf.taskRelated.notesTitle, addOn_1);
			sf.seleU.clickElementByJSE(sf.taskRelated.notesContentBox);
			reportStatusPass(methodName + " Enter text for Notes" , true, true);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.taskRelated.notesContentBox, addOn_1);
			sf.seleU.clickElementByJSE(sf.taskRelated.notesDoneButton);
			reportStatusPass(methodName + " Created a new Note" , true, true);
			
			//Verify Notes Created
			verifyFieldDisplayed("New Note in Notes Section", sf.taskRelated.notesFirstRowData);
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating new note", e);
			e.printStackTrace();
		}
	}
	 
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + "" + fieldName + " is not displayed", true);
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
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}