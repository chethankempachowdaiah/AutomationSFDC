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
 * @author Anukriti.Chawla, Date : 10/Jan/2021
 * 
 *         SFDC Tasks page
 */
public class SFDC_Task_Details_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_TaskDetails@: ";

	public SFDC_Task_Details_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 * 				Verify Task Details - Header, Subject, AccountName, Type, activitySUbType             
	 * 
	 */
	public void verifyTaskDetails() throws IOException {
		try {

			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
//			if (sf.dataInput.env.equals("ITDEVSTAGE"))
//			{
//				eventDeatils();
//			
//			}
//			else
//			{
					
			// Verify Task Details Header is displayed
			verifyFieldDisplayed("Task Details Header", sf.taskDetails.taskDetailsHeader);
			
			//Verify Subject in  Task Details
			verifyFieldValue("Task Subject", sf.taskDetails.subjectInTaskDetails, sf.dataInput.taskSubject); 
			
			//Verify Type of Task in  Task Details
			verifyFieldValue("Task Type", sf.taskDetails.typeOfTaskInTaskDetails, sf.dataInput.taskTypeForActivity); 
			
			//Verify Account name in  Task Details
			if(sf.seleU.isElementPresent(sf.taskDetails.accountNameInTaskDetails) & !(sf.dataInput.taskAccountName==null))
				verifyFieldValue("Task Account Name", sf.taskDetails.accountNameInTaskDetails, sf.dataInput.taskAccountName); 
			
			//Verify Solution Discussed in  Task Details
			verifyFieldValue("Task Solution Discussed", sf.taskDetails.solutionsDiscussedInTaskDetails, sf.dataInput.taskSolutionDiscussed); 
			
			//Verify Interaction Type in  Task Details
			verifyFieldValue("Task Interaction/Activity SubType", sf.taskDetails.interactionTypeInTaskDetails, sf.dataInput.interactionTypeOfTask);
//			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying task details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 				Switch Back to Window1             
	 * 
	 */
	public void switchToMainWindow() throws IOException {
		try {
			
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Switching back to Window 1", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 * 				Event Details           
	 * 
	 */
	public void eventDeatils() throws IOException {
		try {
			
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
			
			// Verify Event Details Header is displayed
			verifyFieldDisplayed("Event Details Header", sf.taskDetails.eventDetailsHeader);		
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying event details", e);
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
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
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