package sfdc.pages.sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 10/Jan/2021
 * 
 *         SFDC Task Creation page
 */
public class SFDC_Task_Creation_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Task@: ";

	public SFDC_Task_Creation_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 * 				Select the standard fields
	 * 
	 *  			Make a note of the value selected under 'Subject field'
	 *  
	 *  			Click on Next
	 *  
	 *  			Select the Custom fields
	 *  
	 *  			Click on Next
	 *  
	 *  			A Task has been created successfully' page will be appeared 
	 *                  
	 * 
	 */
	public void createTask() throws IOException {
		try {

			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();
			//Switch to Iframe
			sf.seleU.switchToFrame(sf.task.createTaskIFrame);
			sf.seleU.wait(5000);
			// Verify Create Task form is displayed
			verifyFieldDisplayed("Create Task Form", sf.task.createTaskHeader);
			validateDropdownValuesTaskFormPg1();
			sf.seleU.wait(5000);
			//Print value set by default under Type select box and save for later
			sf.dataInput.taskTypeForActivity = sf.seleU.getSelectedOptionText(sf.task.typeOfTask);
			reportStatusPass(methodName + " Type of Task is selected as : " +  sf.dataInput.taskTypeForActivity, true, false); 
			
			//Print value set by default under Activity Sub Type select box and save for later
			sf.dataInput.interactionTypeOfTask = sf.seleU.getSelectedOptionText(sf.task.interactionTypeOfTask);
			reportStatusPass(methodName + " Interaction-Type of Task is selected as : " +  sf.dataInput.interactionTypeOfTask, true, true);
				
			//Print value set by default under Subject select box and save for later
			//for even
			//sf.seleU.selectTextFromDropDown(sf.task.subjectOfTask,"Executive Brief");
			//for task
			sf.seleU.selectTextFromDropDown(sf.task.subjectOfTask,"Solution Negotiation");
			
			sf.seleU.wait(5000);
			
			sf.dataInput.taskSubject = sf.seleU.getSelectedOptionText(sf.task.subjectOfTask);
			reportStatusPass(methodName + " Subject for Task is selected as : " +  sf.dataInput.taskSubject, true, true); 
			
			//Print value set by default under Status select box
			reportStatusPass(methodName + " Status of Task selected : " +  sf.seleU.getSelectedOptionText(sf.task.statusOfTask), true, true); 
			
			//Print value set by default under Related To Input Box
			if (sf.seleU.isElementDisplayed(sf.task.relatedToOfTask))
				reportStatusPass(methodName + " Related To value of Task is : " +  sf.seleU.getTextFromWebElement(sf.task.relatedToOfTask), true, true); 
			
			//Print value set by default under Account Name input box and save for later
			if (sf.seleU.isElementDisplayed(sf.task.accountNameOfTask)) {
				sf.dataInput.taskAccountName = sf.seleU.getTextFromWebElement(sf.task.accountNameOfTask);
				reportStatusPass(methodName + " Account Name for Task is : " +  sf.dataInput.taskAccountName, true, true);
			}	
			
			//Click on Next
			sf.seleU.clickElementByJSE(sf.task.nextButton);
			sf.seleU.wait(5000);
//			if (sf.dataInput.env.equals("ITDEVSTAGE"))
//			{
//				validateEvent();
//			
//			}
//			else
//			{
			validateDropdownValues("Soltion Discussed", sf.task.solutionDiscussedForTask, sf.dataInput.taskSolutionDiscussedList );
			sf.seleU.wait(5000);
			//Fill Custom Fields Form
			//Select first value for Solution Discussed and save its value for later
			sf.seleU.selectIndexFromDropDown(sf.task.solutionDiscussedForTask, 0);
			sf.dataInput.taskSolutionDiscussed = sf.seleU.getSelectedOptionText(sf.task.solutionDiscussedForTask);
			reportStatusPass(methodName + " Solution Discussed for Task is selected as : " +  sf.dataInput.taskSolutionDiscussed, true, true); 
			
			//Print values present under Marketing Campaign Opt In select box
			reportStatusPass(methodName + " Marketing Campaign Opt In Values : " +  sf.seleU.getDropDownOptions(sf.task.marketingCampaignOptInForTask), true, true); 
			
			//Print value set by default under Next Steps select box
			reportStatusPass(methodName + " Next Steps of Task is selected as : " +  sf.seleU.getSelectedOptionText(sf.task.nextStepsForTask), true, true); 
			
			//Print value set for Due Date
			reportStatusPass(methodName + " Due Date for Task is : " + sf.seleU.getTextFromWebElement(sf.task.dueDateForTask),true,true);
			//Print value set by default under Customer Churn Risk select Box
			reportStatusPass(methodName + " Related To value of Task is : " +  sf.seleU.getSelectedOptionText(sf.task.customerChurnRiskForTask), true, true); 
		
			//Click on Next
			sf.seleU.clickElementByJSE(sf.task.nextButton);
			sf.seleU.wait(5000);
			
			//Verify task success message
			verifyFieldDisplayed("Task creation Success Message", sf.task.taskCreationSuccessMessage);
			sf.seleU.wait(5000);
			
			//Click on Task link
			sf.seleU.clickElementByJSE(sf.task.taskLink);
			sf.seleU.wait(5000);
//			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating task", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 				
	 * 
	 * 				Validate help text for type field
	 *             
	 * 
	 */
	 
	private void validateDropdownValues(String fieldName, WebElement element, List<String> expectedValuesInDropdown) throws IOException {

		try {
			
			//Validate Values in Dropdown
			List<String> actualValuesInDropdown = sf.seleU.getDropDownOptions(element);
			Collections.sort(expectedValuesInDropdown);
			Collections.sort(actualValuesInDropdown);
			
			// Verify expected values are equal to actual ones

			if (expectedValuesInDropdown.equals(actualValuesInDropdown)) {
				reportStatusPass(methodName + " All expected values for field '" + fieldName + "' are present in the dropdown : "
						+ AdditionalUtilities.getAsString(actualValuesInDropdown), true, true);
			} else {
				reportStatusFail(methodName + " All expected type values for field '" + fieldName + "' are not present in the dropdown :: Expected Values--> "
						+ AdditionalUtilities.getAsString(expectedValuesInDropdown) + "  Actual Values--> "
						+ AdditionalUtilities.getAsString(actualValuesInDropdown), true);
			}

			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating task dropdowns", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 * 				Validate Event 
	 *             
	 */
	 
	private void validateEvent() throws IOException {

		try {
			//new code 22 PI1 Sprint 04 
			
				sf.seleU.wait(5000);
				// Verify New Event is displayed
				verifyFieldDisplayed("New Event", sf.task.newEvent);
				sf.seleU.wait(1000);
				//Print value set by default under Subject select box and save for later
				sf.salesData.taskNewEventSubjectForActivity = sf.seleU.getSelectedOptionText(sf.task.subjectEventOfTask);
				reportStatusPass(methodName + " Subject Event of Task is selected as : " +  sf.salesData.taskNewEventSubjectForActivity , true, false); 
				
				// Verify Description is displayed
				verifyFieldDisplayed("Description", sf.task.description);
				
				// Verify Start Date Time is displayed
				verifyFieldDisplayed("Start Date Time", sf.task.startDateTime);
				
				// Verify End Date Time is displayed
				verifyFieldDisplayed("End Date Time", sf.task.endDateTime);
				
				// Verify Location is displayed
				verifyFieldDisplayed("Location", sf.task.location);
				
				// Verify Reminder Set is displayed
				verifyFieldDisplayed("Reminder Set", sf.task.reminderSet);
				
				// Check box checked
				sf.seleU.clickElementByJSE(sf.task.reminderSetChkBox);
				sf.seleU.wait(3000);
				
				// Verify Reminder Set Date Time  is displayed
				verifyFieldDisplayed("Reminder Set", sf.task.reminderSetTimeDate);
				
				//Click on Next
				sf.seleU.clickElementByJSE(sf.task.nextButton);
				sf.seleU.wait(5000);
				
				//Verify Event success message
				verifyFieldDisplayed("Event creation Success Message", sf.task.taskCreationSuccessMessage);
				
				//Click on Task link
				sf.seleU.clickElementByJSE(sf.task.eventLink);
				sf.seleU.wait(5000);
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating task dropdowns", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Help text for type field
	 */
	public void validateHelpTextForTypeField() throws IOException {
		try {
			sf.seleU.wait(3000);
			sf.seleU.switchToFrame(sf.task.createTaskIFrame);
			
			//Validate help text for Type field
			sf.seleU.clickElementByJSE(sf.task.helpLinkForTypeOfTask.get(0));
			sf.seleU.wait(2000);
			
			List<String> actualValuesForTypeFieldHelp = new ArrayList<>();
			Collections.sort(sf.dataInput.taskTypeHelpText);
			Collections.sort(actualValuesForTypeFieldHelp);
			
			// Verify expected type values are equal to actual ones
			for (int i=0; i<sf.task.helpTextForTypeOfTask.size(); i++)
				actualValuesForTypeFieldHelp.add(sf.seleU.getTextFromWebElement(sf.task.helpTextForTypeOfTask.get(i)));
				
			if (sf.dataInput.taskTypeHelpText.equals(actualValuesForTypeFieldHelp)) {
				reportStatusPass(methodName + " All expected type values for help text are present in Task Help Link : "
						+ AdditionalUtilities.getAsString(actualValuesForTypeFieldHelp), true, true);
			} else {
				reportStatusFail(methodName + " All expected type values for help are not present in Task Help Link :: Expected Value--> "
						+ AdditionalUtilities.getAsString(sf.dataInput.taskTypeHelpText) + "  Actual Value--> "
						+ AdditionalUtilities.getAsString(actualValuesForTypeFieldHelp), true);
			}
			
			//Close Help Dialog
			sf.seleU.clickElementByJSE(sf.task.closeHelpBox);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating help text for type field", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify dropdown values of type, interaction type, subject
	 */
	public void validateDropdownValuesTaskFormPg1() throws IOException {
		try {
			sf.seleU.wait(3000);

			//Validate type values - New customer, existing customer
			validateDropdownValues("Type of Task", sf.task.typeOfTask, sf.dataInput.taskTypes );
			
			//Validate Interaction type values - 1-1 Call, 	Conference Call, Customer visit - On Premise, Customer Visit - Off Premise
			validateDropdownValues("Interaction Type of Task", sf.task.interactionTypeOfTask, sf.dataInput.taskInteractionTypes);
			
			//Validate Subject values - Follow-Up Meeting, Retention, Service Renewal, Cross Sell-upsell, Quaterly Business Review (QBR), Customer Initiated, Qualifying Meeting, Solution Negotiation, Contract Negotiation, Executive Engagement, Executive Brief
			validateDropdownValues("Subject of Task", sf.task.subjectOfTask, sf.dataInput.taskSubjectList );
			
						
		} catch (Throwable e) {
			reportStatusFail(" Error in verification for dropdown values of all fields of task form page 1", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify dropdown values of Solution Discussed Field
	 */
	public void validateDropdownValuesTaskFormPg2() throws IOException {
		try {
			
			sf.seleU.clickElementByJSE(sf.task.nextButton);
			sf.seleU.wait(3000);
			
			//Validate type values - Fixed Network,Data Center &amp; Cloud,Security,Wireless,Wireless Unison,Fixed Network Unison,Business Collaboration,IoT M2M,IoT Solutions
			validateDropdownValues("Soltion Discussed", sf.task.solutionDiscussedForTask, sf.dataInput.taskSolutionDiscussedList );
			
			sf.seleU.clickElementByJSE(sf.task.previousButton);
			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Error in verification for dropdown values of all fields of task form page 2", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify type, interaction type, subject and status are mandatory field
	 */
	public void validateMandatoryFieldsForTaskFormPg1() throws IOException {
		try {
			sf.seleU.wait(3000);
			//Switch to Iframe
			sf.seleU.switchToFrame(sf.task.createTaskIFrame);
			sf.seleU.wait(5000);
			// Verify mandatory fields
			verifyFieldIsRequired("Type of Task", sf.task.typeOfTask);
			verifyFieldIsRequired("Subject of Task", sf.task.subjectOfTask);
			verifyFieldIsRequired("Status of Task", sf.task.statusOfTask);
			verifyFieldIsRequired("Activity Sub-Type of Task", sf.task.interactionTypeOfTask);

		} catch (Throwable e) {
			reportStatusFail(" Error in verification for mandatory fields of task form page 1", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is mandatory fields
	 */
	public void verifyFieldIsRequired(String fieldName, WebElement field) throws IOException {

		try {

			try {
				field.getAttribute("required");
				reportStatusPass(methodName + " Validated " + fieldName + " field is required field", true, true);
			}
				catch(Exception e) {
				reportStatusFail(
						methodName + " Field " + fieldName + "is not a mandatory field, It should be a mandatory field",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in validating required field", e);
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

}