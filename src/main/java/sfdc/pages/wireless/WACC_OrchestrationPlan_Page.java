package sfdc.pages.wireless;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author Pankaj Agarwal, Date: 23/03/2022
 *
 *         SFDC WACC Orchestration Plan Page
 */

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WACC_OrchestrationPlan_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public boolean banTask_StatusFailed,validateSubmitOrder_StatusFailed = false;

	public WACC_OrchestrationPlan_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Orchestration_WACC_Plan@: ";
	}

	/**@author Pankaj.Agarwal Date: 12/04/2022
	 * @throws IOException
	 * 
	 *                    Verify whether BAN callout task is completed as green in the plan      
	 *                    if not completed then manually complete it  from the manual queue         
	 * 
	 */
	public void verifyBANTasksInOrchestrationPlan() throws IOException {
		try {
	
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			planRefreshAndMinimize();
			sf.seleU.scrollByCoOrdinates(2);

			verifyFieldAttributeValue("Send Welcome Letter ", sf.orchPlan.orchesItemPlanSendLetterOrderCompletionRDI, "Send Welcome Letter (Auto Task) is Completed");
			sf.seleU.wait(8000);
			if (sf.seleU.getElementAttribute(sf.orchPlan.orchesPlanBANCreation, "title").
					contains("BAN Creation (Callout to) is Completed")) {
				reportStatusPass(methodName + " Validated BAN Creation is completed by itself", true, true);
			} else{			
				sf.waData.wacc_BaANTask_StatusFailed = true;
			}
					
		} catch (Exception e) {
			reportStatusFail(" Error in verification for closure letter after complete task", e);
			e.printStackTrace();
		}
	}
	
	/**@author Pankaj.Agarwal Date: 12/04/2022
	 * @throws IOException
	 * 
	 *                    Complete BAN Manual tasks manually    
	 * 
	 */
	public void BANCreation_ManualTasks() throws IOException {
		try {
	
			if(sf.seleU.isElementDisplayed(sf.orchPlan.banTaskCheckboxManualCompletion)) {
				sf.seleU.ScrolltoElementPageCenter(sf.orchPlan.banTaskCheckboxManualCompletion);
				sf.seleU.clickElementByJSE(sf.orchPlan.banTaskCheckboxManualCompletion);
				reportStatusPass(methodName + " clicked on the manual completion", true, false);
				sf.seleU.clickElementByJSE(sf.orchPlan.completeButton);
				
			} else {
				reportStatusFail(methodName + " BAN Task checkbox is not visible", true);	
			}
			
			if(sf.seleU.isElementDisplayed(sf.orchPlan.fallOutInformationText)) {
				sf.seleU.ScrolltoElementPageCenter(sf.orchPlan.fallOutEnterBanNO);
				sf.seleU.enterText(sf.orchPlan.fallOutEnterBanNO, sf.omData.v21BanNumberEnter);
				reportStatusPass(methodName + " entered the BAN No as " + sf.omData.v21BanNumberEnter, true, false);
				sf.seleU.clickElementByJSE(sf.orchPlan.fallOutContinueButton);
				reportStatusPass(methodName + " clicked on the continue button", true, false);
			//	sf.seleU.clickElementByJSE(sf.orchPlan.completeButton);
				
			} else {
				reportStatusFail(methodName + " Fall out information text is not visible", true);	
			}
					
		} catch (Exception e) {
			reportStatusFail(" Error in verification for closure letter after complete task", e);
			e.printStackTrace();
		}
	}
	
	
	
	/**@author Pankaj.Agarwal Date: 12/04/2022
	 * @throws IOException
	 * 
	 *                    Verify whether closure letter task is completed as green in the plan diagram for RDI       
	 *                    if not completed then manually complete it           
	 * 
	 */
	public void verifyTasksInOrchesnPlanBeforeValidateSubmitOrder() throws IOException {
		try {			
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			planRefreshAndMinimize();
			sf.seleU.scrollByCoOrdinates(2);
			
			verifyFieldAttributeValue("Send Credit Request", sf.orchPlan.orchesPlanSendCreditRequest, "Send Credit Request (Callout to) is Completed");
			verifyFieldAttributeValue("Send SIM", sf.orchPlan.orchesPlanSendSIM, "Send SIM (Callout to) is Completed");
			waitForEventTask("Send Sim Wait For Event", sf.orchPlan.orchesPlanSendSIM, "Send SIM (Callout to) is Completed");
			verifyFieldAttributeValue("Wirless PONR", sf.orchPlan.orchesPlanWirelessPONR, "Wireless PONR (Milestone) is Completed");
			verifyFieldAttributeValue("Activate CTN", sf.orchPlan.orchesPlanActivateCTN, "Activate CTN (Callout to) is Completed");
			verifyFieldAttributeValue("Compute Tax", sf.orchPlan.orchesPlanComputeTax, "Compute Tax (Callout to) is Completed");
		
		} catch (Exception e) {
			reportStatusFail(" Error in verification for closure letter after complete task", e);
			e.printStackTrace();
		}
	}
	
	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify whether closure letter task is completed as green in the plan diagram for RDI       
	 *                    if not completed then manually complete it           
	 * 
	 */
	public boolean verifyVaildateSubmitOrderTask() throws IOException {
			
		try {
				planRefreshAndMinimize();
				sf.seleU.scrollByCoOrdinates(2);

				verifyFieldAttributeValue("Send Welcome Letter ", sf.orchPlan.orchesItemPlanSendLetterOrderCompletionRDI, "Send Welcome Letter (Auto Task) is Completed");
				sf.seleU.wait(8000);
				if (sf.seleU.getElementAttribute(sf.orchPlan.orchesPlanValidateSubmitOrder, "title").
						contains("BAN Creation (Callout to) is Completed")) {
					reportStatusPass(methodName + " Validated Submit Order task is completed by itself", true, true);
				} else{			
					validateSubmitOrder_StatusFailed = true;
				}
						
			} catch (Exception e) {
				reportStatusFail(" Error in verification for validate submit order task", e);
				e.printStackTrace();
			}
			
		return validateSubmitOrder_StatusFailed;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void waitForEventTask(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			int count  = 0;
			sf.seleU.wait(60000);
			planRefreshAndMinimize();
			// Verify Send SIM Event Task is completed
			while (true) {
				if (sf.seleU.getElementAttribute(element, "title").
						contains(expectedText)) {
					reportStatusPass(methodName + " Validated Send SIM Event (Waiting for event) is Completed", true, true);
					break;

				} else if (!sf.seleU.getElementAttribute(element, "title").
						contains(expectedText)){
					sf.seleU.wait(60000);			
					count++;

					if(count == 15) {
						reportStatusFail(methodName + " Not able to change the send sim event task state to completed after waiting for 15 mins", true);
						break;

					}
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Refresh the Orchestration plan and Minimize
	 */
	public void planRefreshAndMinimize() throws IOException {
		try {

			sf.seleU.refreshPage();
			sf.seleU.switchToDefaultContent();

			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i <= 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

		} catch (Throwable e) {
			reportStatusFail(" plan Refresh And Minimize", e);
			e.printStackTrace();
		}
	}
	
//	/**
//	 * @throws IOException
//	 * 
//	 *                     Refresh the Orchestration plan and Minimize
//	 */
//	public void printToMaximize() throws IOException {
//		try {
//
//			planRefreshAndMinimize();
//			sf.seleU.clickElementByJSE(sf.orchPlan.printButton);			
//
//		} catch (Throwable e) {
//			reportStatusFail(" plan Refresh And Minimize", e);
//			e.printStackTrace();
//		}
//	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).equals(expectedText)) {
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
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldAttributeValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getElementAttribute(element, "title").equals(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is completed for " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + sf.seleU.getElementAttribute(element, "title")
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	public void verifyTaskIsDisplayed(String expectedTaskType, WebElement task)
			throws IOException{

		try {
			// Validate Task
			if (sf.seleU.isElementDisplayed(task)) {

				reportStatusPass(methodName + " Verified Task : " + sf.seleU.getTextFromWebElement(task) + " is displayed",
						true, true);

			} else {
				reportStatusFail(methodName + " Invalid task for : " + expectedTaskType, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
