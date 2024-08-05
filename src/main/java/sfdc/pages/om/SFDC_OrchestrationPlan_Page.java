package sfdc.pages.om;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Orchestration Plan Page
 */
public class SFDC_OrchestrationPlan_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_OrchestrationPlan_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Orchestration_Plan@: ";
	}

	/**
	 * @throws Exception
	 * 
	 *                   Verify Orchestration Plan Header, Orchestration Plan Number
	 *                   and Orchestration State
	 * 
	 * 
	 *                   Verify all 6 orechestrarion task and task type are
	 *                   displayed correctly
	 * 
	 */
	public void verifyOrchestrationTaskCreatedxyz() throws Exception {

		/*
		 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
		 * State
		 */
		verifyFieldValue("Orchestration Plan Header", sf.orchPlan.orchestrationPlanHeaderValueText,
				sf.dataInput.orchestrationPlanName);
		/*
		 * verifyFieldValue("Orchestration Plan Number",
		 * sf.orchPlan.orchestrationPlanNumberValueText,
		 * sf.dataInput.orchestrationPlanName); verifyFieldValue("Orchestration State",
		 * sf.orchPlan.orchestrationStateValueText,
		 * sf.dataInput.orchestrationStateInProgress);
		 */

		sf.seleU.switchToDefaultContent();
		sf.seleU.switchToElementFrame(sf.orchPlan.taskStartOrderHeader);
		sf.seleU.scrollByCoOrdinates(1);
		sf.seleU.scrollToBottom();
		/*
		 * Verify 'Start Order' orechestrarion task and task type are displayed
		 * correctly
		 */
		verifyTaskType("Start Order", sf.orchPlan.taskStartOrderHeader.get(0), sf.orchPlan.taskTypeInStartOrderHeader,
				sf.dataInput.orchestrationTaskTypeMilestone);

		/*
		 * Verify 'Create Service Account and Work Order in Supersystem' orechestrarion
		 * task and task type are displayed correctly
		 */

		verifyTaskType("Create Service Account and Work Order in Supersystem",
				sf.orchPlan.taskCreateServiceAccountAndWorkOrderHeader,
				sf.orchPlan.taskTypeInCreateServiceAccountAndWorkOrderHeader,
				sf.dataInput.orchestrationTaskTypeManualTaskIn + "\n"
						+ sf.dataInput.orchestrationTaskTypeAccountProvisioningQueue);

		/*
		 * Verify 'Vlocity Order completion' orechestrarion task and task type are
		 * displayed correctly
		 */
		verifyTaskType("Vlocity Order completion", sf.orchPlan.taskVlocityOrderCompletionOrderHeader,
				sf.orchPlan.taskTypeInVlocityOrderCompletionOrderHeader, sf.dataInput.orchestrationTaskTypeManualTaskIn
				+ "\n" + sf.dataInput.orchestrationTaskTypeServiceDeliveryQueue);

		/*
		 * Verify 'PONR' orechestrarion task and task type are displayed correctly
		 */
		verifyTaskType("PONR", sf.orchPlan.taskPONRHeader, sf.orchPlan.taskTypeInPONRHeader,
				sf.dataInput.orchestrationTaskTypeMilestone);

		/*
		 * Verify 'Create Assets' orechestrarion task and task type are displayed
		 * correctly
		 */
		verifyTaskType("Create Assets", sf.orchPlan.taskCreateAssetsHeader, sf.orchPlan.taskTypeInCreateAssetsHeader,
				sf.dataInput.orchestrationTaskTypeAutoTask);

		/*
		 * Verify 'Complete Order' orechestrarion task and task type are displayed
		 * correctly
		 */

		verifyTaskType("Complete Order", sf.orchPlan.taskCompleteOrderHeader, sf.orchPlan.taskTypeInCompleteOrderHeader,
				sf.dataInput.orchestrationTaskTypeMilestone);

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Orchestration Plan Header, Orchestration Plan
	 *                     Number and Orchestration State
	 * 
	 *                     Verify all orchestration plan cancelled
	 * 
	 */
	public void verifyOrchestrationTaskCancelled() throws IOException {

		/*
		 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
		 * State
		 */
		verifyFieldValue("Orchestration Plan Header", sf.orchPlan.orchestrationPlanHeaderValueText,
				sf.dataInput.orchestrationPlanName);
		/*
		 * verifyFieldValue("Orchestration Plan Number",
		 * sf.orchPlan.orchestrationPlanNumberValueText,
		 * sf.dataInput.orchestrationPlanName); verifyFieldValue("Orchestration State",
		 * sf.orchPlan.orchestrationStateValueText,
		 * sf.dataInput.orchestrationStateCompleted);
		 */

		sf.seleU.switchToDefaultContent();
		sf.seleU.switchToElementFrame(sf.orchPlan.taskStartOrderHeader);

		// Verify all orchestration plan cancelled
		verifyFieldDisplayed("Start Order (Milestone)", sf.orchPlan.titleCancelledStartOrder);
		verifyFieldDisplayed("Create Service Account and Work Order",
				sf.orchPlan.titleCancelledCreateServiceAccountAndWorkOrder);
		verifyFieldDisplayed("Vlocity Order completion", sf.orchPlan.titleCancelledVlocityOrderCompletion);
		verifyFieldDisplayed("PONR (Milestone)", sf.orchPlan.titleCancelledPONR);
		verifyFieldDisplayed("Create Assets (Auto Task)", sf.orchPlan.titleCancelledCreateAutoTasks);
		verifyFieldDisplayed("Complete Order (Milestone)", sf.orchPlan.titleCancelledCompleteOrder);

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Orchestration Plan State is completed
	 */
	public void verifyOrchestrationPlanStateComplete() throws IOException {

		// Verify Orchestration Plan State is completed
		verifyFieldValue("Orchestration State is Complete", sf.orchPlan.orchestrationStateValueText,
				sf.dataInput.orchestrationStateCompleted);

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on 'Create Service Account and Work Order in
	 *                     Supersystem'
	 */
	public void selectOrchestrationTaskCreateServiceAccountAndWorkOrder() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.orchPlan.taskStartOrderHeader);

			// Click on 'Create Service Account and Work Order in Supersystem'
			sf.seleU.clickElementByJSE(sf.orchPlan.taskCreateServiceAccountAndWorkOrderHeader);
			reportStatusPass(methodName + " Clicked on 'Create Service Account and Work Order in Supersystem'", true,
					false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting 'Create Service Account and Work Order in Supersystem'", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on 'Vlocity Order completion'
	 */
	public void selectVlocityOrderCompletion() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.orchPlan.taskStartOrderHeader);

			// Click on 'Vlocity Order completion'
			sf.seleU.clickElementByJSE(sf.orchPlan.taskVlocityOrderCompletionOrderHeader);
			reportStatusPass(methodName + " Clicked on 'Vlocity Order completion'", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting 'Vlocity Order completion'", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Orchestration Plan dashboard Header, click on
	 *                     the arrow near the start items 2. Click on the Order no
	 *                     from the list in the Orchestration dashboard 3. verify
	 *                     all the list of task field text
	 * 
	 */
	public void verifyOrchestrationPageDashboard() throws IOException {
		String methodName = "SFDC_Verify Orchestration Dashboard@: ";
		try {

			// Verify all orchestration plan cancelled
			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanIFrame);

			// Verify dashboard header text value text
			verifyFieldDisplayed("Orchestration Plan Dashboard Text ", sf.orchPlan.orchesPlanUserDashboardText);
			verifyFieldDisplayed("Orchestration Plan Manager Dashboard Text",
					sf.orchPlan.orchesPlanManageDashboardText);
			sf.seleU.wait(1000);

			// Click on the orchestration item action arrow
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemNameActionsArrowClick);
			reportStatusPass(methodName + " Clicked on 'Create Service Account and Work Order in Supersystem'", true,
					false);

			// Click on the order number after selecting the dropdown arrow
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemNameOrderNumberInRecord.get(0));
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on 'Orchestration Item Order Number down Arrow", true, false);
			sf.seleU.wait(1000);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemActionsInRecord.get(0));

			// Verify all the actions field text after selecting order number dropdown arrow
			verifyFieldValue("Orchestration Item Action Open Record", sf.orchPlan.orchesItemActionsInRecord.get(0),
					sf.dataInput.orchestrationItemActionOpenRecord);
			sf.seleU.hardwait(1000);

			verifyFieldValue("Orchestration Item Action Cancel Record", sf.orchPlan.orchesItemActionsInRecord.get(1),
					sf.dataInput.orchestrationItemActionCancelTask);

			verifyFieldValue("Orchestration Item Action Pend Task", sf.orchPlan.orchesItemActionsInRecord.get(2),
					sf.dataInput.orchestrationItemActionPendTask);
			sf.seleU.hardwait(1000);

			verifyFieldValue("Orchestration Item Action Assign To Me", sf.orchPlan.orchesItemActionsInRecord.get(3),
					sf.dataInput.orchestrationItemActionAssignToMe);
			sf.seleU.hardwait(1000);

			verifyFieldValue("Orchestration Item Action Complete Task", sf.orchPlan.orchesItemActionsInRecord.get(4),
					sf.dataInput.orchestrationItemActionCompleteTask);
			sf.seleU.hardwait(1000);

			verifyFieldValue("Orchestration Item Action Fail Task", sf.orchPlan.orchesItemActionsInRecord.get(5),
					sf.dataInput.orchestrationItemActionFailTask);

			sf.seleU.wait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Error in veryfying field value in dashboard of Orchestration Plan Page'", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select complete task action after selecting order
	 *                     number dropdown arrow 2. Verify all the list of field
	 *                     text from the complete task popup 3. enter the updated
	 *                     CanNo and save it
	 */
	public void selectCompleteTaskForOrder() throws IOException {
		try {

			methodName = "SFDC_Select Complete Task And Verify@: ";
			sf.seleU.scrollToTop();
			sf.seleU.wait(3000);
			// Click Complete task from the list of actions
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemActionsCompleteTask);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on 'Orchestration Item action Complete Task", true, false);
			sf.seleU.hardwait(3000);

			// Verify Complete task Header In Pop up Window
			sf.seleU.switchToDefaultContent();
			verifyFieldDisplayed("Orches Item Complete Task Header on Pop Up ",
					sf.orchPlan.orchesItemActionPopUpHeader);

			// Verify all the fields in complete task pop up window
			sf.seleU.hardwait(2000);

			verifyFieldValue("Orchestration Item Complete Task Business Name Field",
					sf.orchPlan.orchesItemActionPopUpFieldText.get(0),
					sf.dataInput.orchestrationItemActionTaskBusinessName);

			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Complete Task Site Contact Field",
					sf.orchPlan.orchesItemActionPopUpFieldText.get(1),
					sf.dataInput.orchestrationItemActionTaskSiteContact);

			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Complete Task Billing Address Name Field",
					sf.orchPlan.orchesItemActionPopUpFieldText.get(2),
					sf.dataInput.orchestrationItemActionTaskBillingAddress);

			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Complete Task Service Address Name Field",
					sf.orchPlan.orchesItemActionPopUpFieldText.get(3),
					sf.dataInput.orchestrationItemActionTaskServiceAddress);

			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Sales Agent Contact Name Field",
					sf.orchPlan.orchesItemActionPopUpFieldText.get(4),
					sf.dataInput.orchestrationItemActionTaskSalesAgentContact);

			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Site Name Field", sf.orchPlan.orchesItemActionPopUpFieldText.get(5),
					sf.dataInput.orchestrationItemActionTaskSiteName);

			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Signing Authority Contact Name Field",
					sf.orchPlan.orchesItemActionPopUpFieldText.get(6),
					sf.dataInput.orchestrationItemActionTaskSignAuthContact);

			// Verify Can Number Field
			sf.seleU.hardwait(1000);
			verifyFieldValue("Orchestration Item Action Fail Task", sf.orchPlan.orchesItemActionPopUpCanNumFieldText,
					sf.dataInput.orchestrationItemActionTaskCanNumber);

			// Entering the updated can number
			sf.seleU.hardwait(1000);
			sf.seleU.clearAndEnterText(sf.orchPlan.orchesItemActionPopUpCanNumFieldInput, sf.dataInput.superSystemCAN);
			reportStatusPass(methodName + "Entered the updated Can Number as " + sf.dataInput.superSystemCAN, true,
					true);

			// Click on the window to take out the cursor position
			sf.seleU.hardwait(1000);
			WebElement ele = driver.findElement(By.xpath("//div[@class='container EDIT forceQuickActionLayout']"));
			ele.click();

			// Click Save button
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemActionCompleteTaskSaveButton);
			reportStatusPass(methodName + " Clicked on 'Orchestration Item action PopUp Save Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Error in veryfing the field in complete task list of actions'", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on 'select assign to me task action from the list
	 *                     of action'
	 */
	public void selectAssignToMeTaskForOrder() throws IOException {
		try {
			String methodName = "SFDC_Assign To Me Task@: ";
			sf.seleU.scrollToTop();
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemActionsAssgnToMeTask); // check scroll
			// Select assign to me task
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemActionsAssgnToMeTask);

			reportStatusPass(methodName + " Clicked on 'Orchestration Item action Assign TO Me Task", true, false);

			sf.seleU.wait(3000);
			sf.seleU.hardwait(1000);

			// Verify Complete task Header In Pop up Window
			sf.seleU.switchToDefaultContent();

			// Click Save button
			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemActionAssignToMeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save button in assign to me task pop up window", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in assign to me task action'", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp1
	 * @throws IOException
	 * 
	 *                    Verify whether task is completed as green in the plan diagram for RDI                  
	 * 
	 */
	public void verifyOrchestrationPlanForRDIGlobalBeforeCompleteTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanStartOrderCompletionLinkRDI);
			sf.seleU.scrollByCoOrdinates(1);
			verifyTaskIsDisplayed("Start Order Task", sf.orchPlan.orchesItemPlanStartOrderCompletionLinkRDI);
			verifyFieldAttributeValue("Start Order Task is ", sf.orchPlan.orchesItemPlanStartOrderCompletionRDI, "Start Order (Milestone) is Completed");

			verifyTaskIsDisplayed("Assign Order task", sf.orchPlan.orchesItemPlanAssignOrderCompletionLinkRDI);
			verifyFieldAttributeValue(" Assign task is ", sf.orchPlan.orchesItemPlanAssignOrderCompletionRDI, "Assign Order (Auto Task) is Completed");

			verifyTaskIsDisplayed("Send Welcome Letter", sf.orchPlan.orchesItemPlanSendLetterCompletionLinkRDI);
			verifyFieldAttributeValue("Send Welcome Letter task ", sf.orchPlan.orchesItemPlanSendLetterOrderCompletionRDI, "Send Welcome Letter (Auto Task) is Completed");

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for for RDI Before Complete Task", e);
		}
	}

	/**PA PI2Sp1
	 * @throws IOException
	 * 
	 *                    Verify whether task is completed as green in the plan diagram for RDI                  
	 * 
	 */
	public void verifyOrchestrationPlanForRDIGlobalAfterCompleteTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemMinusClick);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			sf.seleU.scrollByCoOrdinates(1);
			for(int i=0; i < 3; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanBillingActivationFailureCompletionLinkRDI);
			verifyTaskIsDisplayed("Billing Activation Failure Task", sf.orchPlan.orchesItemPlanBillingActivationFailureCompletionLinkRDI);
			verifyFieldAttributeValue("Billing Activation Failure Task ", sf.orchPlan.orchesItemPlanBillingActivationFailureCompletionRDI, "Billing Activation Failure (Manual Task in) is Completed");

			sf.seleU.wait(2000);
			verifyTaskIsDisplayed("Activate Billing Task", sf.orchPlan.orchesItemPlanActivateBillingActivationFailureCompletionLinkRDI);
			verifyFieldAttributeValue("Activate Billing Task ", sf.orchPlan.orchesItemPlanAcivateBillingActivationFailureCompletionRDI, "Activate Billing (Callout to) is Completed");

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for for RDI After Complete Task", e);
		}
	}

	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify whether task is completed as green in the plan diagram for RDI                  
	 * 
	 */
	public void verifyOrchestrationPlanForCableOrderAfterCompleteTask() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);
			for(int i=0; i < 3; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateCableOrderTaskCompletionCable);
			sf.seleU.scrollByCoOrdinates(1);
			//		verifyTaskIsDisplayed("Review Order Details is ", sf.orchPlan.orchesItemPlanReviewOrderDetailsCompletionLinkCable);
			verifyFieldAttributeValue("Create Cable Order Task ", sf.orchPlan.orchesItemPlanCreateCableOrderTaskCompletionCable, 
					"Create Cable Order (Manual Task in) is Completed");
			sf.seleU.switchToDefaultContent();

		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for create cable order task items after complete task", e);
		}
	}

	//	

	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Open Create order details task from Orchestration Plan                
	 * 
	 */
	public void clickOnCreateCableOrderTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			sf.seleU.scrollByCoOrdinates(1);
			for(int i=0; i < 1; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.wait(2000);

			//	sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateCableOrderCompletionLinkCable);
			verifyTaskIsDisplayed("Create Cable Order ", sf.orchPlan.orchesItemPlanCreateCableOrderCompletionLinkCable);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanCreateCableOrderClick);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateCableOrderClick);
			reportStatusPass(methodName + " Clicked on 'Create Cable Order Task", true, false);

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in clicking create cable order task items", e);
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    open Review order details task from Orchestration Plan            
	 * 
	 */
	public void clickOnReviewOrderDetailsTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			sf.seleU.scrollByCoOrdinates(1);
			for(int i=0; i < 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.wait(2000);

			//		sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanReviewOrderDetailsCompletionLinkCable);
			verifyTaskIsDisplayed("Review Order Details ", sf.orchPlan.orchesItemPlanReviewOrderDetailsCompletionLinkCable);

			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanReviewOrderDetailsOrderClick);
			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanReviewOrderDetailsOrderClick);
			reportStatusPass(methodName + " Clicked on 'Review Order Details Task", true, false);
			sf.seleU.switchToDefaultContent();
			//	sf.seleU.refreshPage();

		} catch (Exception e) {
			reportStatusFail(" Error in clicking review order details task", e);
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void completeReviewOrderDetailsTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();

			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.ScrolltoElement(sf.rODComplete.vaidateOrderStatusCheckBox);
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
			}

			//			// verify success message text
			//			verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton);
			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false); 

			sf.seleU.refreshPage();

		} catch (Exception e) {
			reportStatusFail(" Error in completing review order details task", e);
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
	public void verifyOrchestrationPlanForWelcomeLetterAfterCompleteTask() throws IOException {
		try {

			sf.seleU.refreshPage();
			sf.seleU.refreshPage();
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i <= 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.scrollToTop();
			//			for(int i=0; i <= 4; i ++) {
			//				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			//			}
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanSendLetterCompletionLinkRDI);
			sf.seleU.scrollByCoOrdinates(1);

			verifyTaskIsDisplayed("Welcome Letter Task ", sf.orchPlan.orchesItemPlanSendLetterCompletionLinkRDI);
			//		verifyFieldAttributeValue("office 365 order creation  is ", sf.orchPlan.orchesItemPlanOffice365CompletionCable, "Office 365 Order Creation (Manual Task in) is Completed");

			if (sf.seleU.getElementAttribute(sf.orchPlan.orchesItemPlanSendLetterOrderCompletionRDI, "title").
					contains("Send Welcome Letter (Auto Task) is Completed")) {
				reportStatusPass(methodName + " Validated welcome letter task is completed by itself", true, true);
			} else{

				// if the task is not completed automatically then it will complete manually.
				sf.omData.welcomeEmailTaskStatus_CompletedManually = true;
				completeWelcomeLetterTaskManually();	
			}
			sf.seleU.switchToDefaultContent();

		} catch (Exception e) {
			reportStatusFail(" Error in verification for welcome letter after complete task", e);
			e.printStackTrace();
		}
	}



	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    complete the welcome letter task manually for cable L2           
	 * 
	 */
	public void completeWelcomeLetterTaskManually() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.refreshPage();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 2; i ++) {
				sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.scrollToTop();

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanSendLetterCompletionLinkRDI);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanSendLetterCompletionLinkRDI);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanSendLetterCompletionLinkRDI);
			reportStatusPass(methodName + " Clicked on 'Welcome Letter Task", true, false);
			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(4000);

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In Welcome Letter Task Pending State", true, false);

			withOutSelectClassDropDownOptions(
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the task status to Completed", true, false);

			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in completing welcome letter task manually", e);
			e.printStackTrace();
		}
	}




	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void changeCreateAccountInSSTaskFailureState(String state) throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			//			sf.seleU.switchToDefaultContent();
			sf.seleU.refreshPage();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderLink);
			verifyTaskIsDisplayed("Create Account In SS task Failure manual task ", 
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderLink);

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderLink);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure to make it ready", true, false);

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(3000);

			// change with OR condition in the locator

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure Pending State", true, false);

			if (!sf.dataInput.env.equals("ITDEVSTAGE")) {		
				sf.seleU.withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,state);
			} else {
				sf.seleU.withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,state);
			}

			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the task status to Completed", true, false);
			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in changing the state for create account in SS task failure", e);
			e.printStackTrace();
		}
	}
	
	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void changeCreateAccountInSSTaskFailureState_ToComplete(String state) throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			//			sf.seleU.switchToDefaultContent();
			sf.seleU.refreshPage();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderLink);
			verifyTaskIsDisplayed("Create Account In SS task Failure manual task ", 
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderLink);

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderLink);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure to make it ready", true, false);

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(3000);

			// change with OR condition in the locator

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure Pending State", true, false);

			if (!sf.dataInput.env.equals("ITDEVSTAGE")) {		
				withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,state);
			} else {
				sf.seleU.withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,state);
			}

			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the task status to Completed", true, false);
			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in changing the state for create account in SS task failure To Complete", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void clickOnCreateAccountInSSTaskFailureTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.wait(6000);
			sf.seleU.refreshPage();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 2; i ++) {
				sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderClick);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderClick);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureOrderClick);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Filure", true, false);
			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Error in verifying field value in Office 365 Task", e);
			e.printStackTrace();
		}
	}

	/*
	 * @throws IOException
	 * 
	 *                    Verify Update Account CAN && CAN Creation Completed Task is auto completed 
	 *                    in the orchestration plan         .
	 * 
	 */
	public void verifyCreateAccountCallOut_CompleteTask() throws IOException {
		try {


			sf.seleU.refreshPage();
			sf.seleU.refreshPage();
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i <= 3; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateAccountCalloutCompleted);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(8000);

			//			verifyFieldAttributeValue("Update Account Creation  is ", sf.orchPlan.orchesItemPlanUpdateAccountCANCompleted, "Update Account CAN in SF (Auto Task) is Completed");
			//			verifyFieldAttributeValue("CAN Creation Task  is ", sf.orchPlan.orchesItemPlaCANCreationCompleted, "CAN Creation Completed (Milestone) is Completed");

			if (sf.seleU.getElementAttribute(sf.orchPlan.orchesItemPlanCreateAccountCalloutCompleted, "title").
					contains("Create Account in SS (CAN Creation) (Callout to) is Completed")) {

				reportStatusPass(methodName + " Create Account in SS (CAN Creation) (Callout to) is Completed", true, true);
			} else{
				completeTaskManually(sf.orchPlan.orchesItemPlanCreateAccountCalloutTaskOrderLink, "createAccountCallOutTask");
			}

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for UpdateAccountCreation And CANCreationCompleteTask", e);
			e.printStackTrace();
		}
	}

	/*
	 * @throws IOException
	 * 
	 *                    Verify Update Account CAN && CAN Creation Completed Task is auto completed 
	 *                    in the orchestration plan         .
	 * 
	 */
	public void verifyUpdateAccountCreation_AndCANCreationCompleteTask() throws IOException {
		try {


			for(int i=0; i <= 3; i ++) {
				sf.seleU.refreshPage();
			}
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.wait(15000);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i <= 3; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanUpdateAccountAutoTaskOrderLink);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(10000);

			//			verifyFieldAttributeValue("Update Account Creation  is ", sf.orchPlan.orchesItemPlanUpdateAccountCANCompleted, "Update Account CAN in SF (Auto Task) is Completed");
			//			verifyFieldAttributeValue("CAN Creation Task  is ", sf.orchPlan.orchesItemPlaCANCreationCompleted, "CAN Creation Completed (Milestone) is Completed");

			if (sf.seleU.getElementAttribute(sf.orchPlan.orchesItemPlanUpdateAccountCANCompleted, "title").
					contains("Update Account CAN in SF (Auto Task) is Completed") || 
					sf.seleU.getElementAttribute(sf.orchPlan.orchesItemPlaCANCreationCompleted, "title").
					contains("CAN Creation Completed (Milestone) is Completed")) {

				reportStatusPass(methodName + " Validated Update Account task is completed by itself", true, true);
			} else{
			//	completeTaskManually(sf.orchPlan.orchesItemPlanUpdateAccountAutoTaskOrderLink, "updateAccountTask");
				reportStatusFail(methodName + " Validated Update Account task is not completed by itself", true);
			}

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for UpdateAccountCreation And CANCreationCompleteTask", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Complete the Update Account Task Manually               
	 * 
	 */
	public void completeTaskManually(WebElement openTaskClick, String task) throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			//			sf.seleU.refreshPage();
			//			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			//			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);
			//
			//			for(int i=0; i < 2; i ++) {
			//				sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			//				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			//			}
			//
			//			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			//			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(openTaskClick);
			reportStatusPass(methodName + " Clicked on the Task", true, false);
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(4000);

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure Pending State", true, false);

			if(task.equals("createAccountCallOutTask")) {
				withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");
			} else {
				sf.seleU.withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");
			}
			

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the task status to Completed", true, false);

			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in verifying field value in Office 365 Task", e);
			e.printStackTrace();
		}
	}



	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void changeWorkOrderInSSTaskFailureState() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);
			verifyTaskIsDisplayed("Create work Order in SS Task Failure ", sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);

			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);
			reportStatusPass(methodName + " Clicked on 'Create WorkOrder In SS Task Filure", true, false);

			sf.seleU.switchToDefaultContent();

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure Pending State", true, false);

			sf.seleU.withOutSelectClassDropDownOptions(
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Ready");

			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the work order task status to ready", true, false);
			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in changing the state for create work order in SS task failure", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void clickOnCreateWorkOrderInSSTaskFailureTask() throws IOException {
		try {
			sf.seleU.wait(7000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);
			verifyTaskIsDisplayed("Create work Order in SS Task Failure ", sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);

			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanCreateWordOrderSSFailureOrderLink);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateWorkOrderSSFailureOrderClick);
			reportStatusPass(methodName + " Clicked on 'Create WorkOrder In SS Task Filure", true, false);

			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Error in clicking on create work order ", e);
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
	public void verifyOrchestrationPlanForAppointmentLetterAfterCompleteTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.refreshPage();
			sf.seleU.refreshPage();

			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i <= 2; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanAppointementLetterTaskLink);
			sf.seleU.scrollByCoOrdinates(2);

			verifyTaskIsDisplayed("Appointment Letter Task ", sf.orchPlan.orchesItemPlanAppointementLetterTaskLink);
			//		verifyFieldAttributeValue("office 365 order creation  is ", sf.orchPlan.orchesItemPlanOffice365CompletionCable, "Office 365 Order Creation (Manual Task in) is Completed");

			sf.seleU.wait(10000);
			if (sf.seleU.getElementAttribute(sf.orchPlan.orchesItemPlanAppointementLetterCompletionCable, "title").
					contains("Send Appointment Letter (Auto Task) is Completed")) {
				reportStatusPass(methodName + " Validated Appointment letter task is completed by itself", true, true);
			} else{
				sf.omData.apointmentEmailTaskStatus_CompletedManually = true;
				completeAppointmentLetterTaskManually();
			}
			//		sf.omData.emailTaskStatus_CompletedManually = false;
			sf.seleU.switchToDefaultContent();

		} catch (Exception e) {
			reportStatusFail(" Error in verification for appointment letter after complete task", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Complete the closure Letter Manually               
	 * 
	 */
	public void completeAppointmentLetterTaskManually() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.refreshPage();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 2; i ++) {
				sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanAppointementLetterTaskLink);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanAppointementLetterTaskLink);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanAppointementLetterTaskLink);
			reportStatusPass(methodName + " Clicked on 'Appointment Letter Task", true, false);
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(4000);

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Appointment letter task in pending state", true, false);

			withOutSelectClassDropDownOptions(
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the appointment letter task status to Completed", true, false);

			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in completing the appointment letter task manually", e);
			e.printStackTrace();
		}
	}


	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void clickOnAccountActivationTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.refreshPage();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 2; i ++) {
				sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanAccountActivationOrderLink);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanAccountActivationOrderLink);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanAccountActivationOrderLink);
			reportStatusPass(methodName + " Clicked on 'Account Activation task item", true, false);
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(6000);

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure Pending State", true, false);

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {	
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");
			} else if((sf.dataInput.env.equals("ITQATEST"))) {
				sf.seleU.withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");
			} else {
				sf.seleU.withOutSelectClassDropDownOptions(
						sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");
			}
			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the task status to Completed", true, false);

			sf.seleU.wait(8000);
		} catch (Throwable e) {
			reportStatusFail(" Error in changing the state of account activation tasks", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify PONR Task Is completed after account activation task                
	 * 
	 */
	public void verifyPONRTaskIsCompleted() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.refreshPage();
			sf.seleU.refreshPage();
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(6000);
			//	if (sf.dataInput.env.equals("ITDEVSTAGE")) {
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);
			//			} else {
			//				sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrameFitStage);
			//				sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrameFitStage);
			//			}
			sf.seleU.wait(2000);
			for(int i=0; i <= 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.scrollByCoOrdinates(2);

			verifyFieldAttributeValue("PONR Task is  ", sf.orchPlan.orchesItemPlanPONRCompletedTaskCable, "PONR Completed (Milestone) is Completed");

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in firld verification for PONR task completion", e);
			e.printStackTrace();
		}
	}


	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void verifyAllTaskItemsOrchPlanForCableOrder() throws IOException {
		try {
			int count  = 0;
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			sf.seleU.scrollByCoOrdinates(1);

			for(int i=0; i < 3; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.wait(2000);

			//	sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanCreateCableOrderCompletionLinkCable);
			for(int i =2; i< sf.orchPlan.orchesItemPlanAllTaskCompletionLinkCable.size(); i++) {

				verifyTaskIsDisplay("All tasks IN orchestration Plan ", sf.orchPlan.orchesItemPlanAllTaskCompletionLinkCable.get(i));
				count ++;
			}
			//			if(count == 17) {
			//				reportStatusPass(methodName + " All task items are visible in the Orchestration Plan for cable tasks" , true, false);
			//			}
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanCreateCableOrderClick);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateCableOrderClick);
			reportStatusPass(methodName + " Clicked on 'Create Cable Order Task", true, false);

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for create cable order task items", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void verifyAllTaskItemsStaus(String status) throws IOException {
		try {
			int counting  = 0;
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.switchToDefaultContent();
			sf.seleU.refreshPage();
			sf.seleU.wait(8000);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			//	sf.seleU.scrollByCoOrdinates(1);

			for(int i=0; i < 5; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanTextGlobal);
			sf.seleU.switchToDefaultContent();
	//		sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanText);
			sf.seleU.wait(2000);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			// Verify Field value matches the expected result
			verify_TaskStatus_InOrchestrationPlan(status, sf.orchPlan.orchesItemPlanTaskStatus);

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in verification of status for the tasks", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                   Pick complete circuit design task              
	 * 
	 */
	public void pickCompleteCircuitDesign(String taskName) throws IOException {
		try {
			int counting  = 0;
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.switchToDefaultContent();
			sf.seleU.refreshPage();
			sf.seleU.wait(8000);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
			//	sf.seleU.scrollByCoOrdinates(1);

			for(int i=0; i < 5; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanTextGlobal);
			sf.seleU.switchToDefaultContent();
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanText);
			sf.seleU.wait(2000);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			WebElement ele = driver.findElement(By.xpath("//div[contains(@title,'"+taskName+" (Manual Task in) is')]/following-sibling::div//i"));

			// Click complete circuit design task
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.wait(5000);
			reportStatusPass(" Picked Task from Orchestration plan is " + taskName, true, false);

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in verification of status for the tasks", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp1
	 * @throws IOException
	 * 
	 *                    Verify whether task is completed as green in the plan diagram for RDI                  
	 * 
	 */
	public void verifyOrchestrationPlanForOffice365BeforeCompleteTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);
			for(int i=0; i <= 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanStartOrderCompletionLinkRDI);
			sf.seleU.scrollByCoOrdinates(1);
			verifyTaskIsDisplayed("office 365 Task", sf.orchPlan.orchesItemPlanOffice365TaskCompletionLinkCable);
			verifyFieldAttributeValue("Start Order Task is ", sf.orchPlan.orchesItemPlanOffice365BeforeCompletion, "Office 365 Order Creation (Manual Task in) is Ready");

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for for RDI Before Complete Task", e);
			e.printStackTrace();
		}
	}


	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify whether office 365 task is completed as green in the plan diagram for RDI                  
	 * 
	 */
	public void verifyOrchestrationPlanForOffice365AfterCompleteTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.switchToDefaultContent();

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {
				sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
				sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);
			} else {
				sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrameFitStage);
				sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrameFitStage);
			}
			for(int i=0; i <= 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}
			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanOffice365TaskCompletionLinkCable);
			sf.seleU.scrollByCoOrdinates(1);

			verifyTaskIsDisplayed("Office 365 task is ", sf.orchPlan.orchesItemPlanOffice365TaskCompletionLinkCable);
			verifyFieldAttributeValue("office 365 order creation  is ", sf.orchPlan.orchesItemPlanOffice365CompletionCable, "Office 365 Order Creation (Manual Task in) is Completed");

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in Field verification for create cable order task items after complete task", e);
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
	public void verifyOrchestrationPlanForClosureLetterAfterCompleteTask() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			sf.seleU.refreshPage();
			sf.seleU.refreshPage();
			sf.seleU.switchToDefaultContent();
			//		if (sf.dataInput.env.equals("ITDEVSTAGE")) {
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);
			//			} else {
			//				sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrameFitStage);
			//				sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrameFitStage);
			//			}

			for(int i=0; i <= 4; i ++) {
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			sf.seleU.scrollByCoOrdinates(2);

			verifyTaskIsDisplayed("Closure Letter Task ", sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			//		verifyFieldAttributeValue("office 365 order creation  is ", sf.orchPlan.orchesItemPlanOffice365CompletionCable, "Office 365 Order Creation (Manual Task in) is Completed");

			sf.seleU.wait(8000);
			if (sf.seleU.getElementAttribute(sf.orchPlan.orchesItemPlanClosureLetterCompletionCable, "title").
					contains("Send Closure Letter (Auto Task) is Completed")) {
				reportStatusPass(methodName + " Validated closure letter task is completed by itself", true, true);
			} else{
				sf.omData.closureEmailTaskStatus_CompletedManually = true;
				completeSendClosureLetterTaskManually();
			}
			sf.seleU.switchToDefaultContent();

		} catch (Exception e) {
			reportStatusFail(" Error in verification for closure letter after complete task", e);
			e.printStackTrace();
		}
	}

	/**PA PI2Sp3
	 * @throws IOException
	 * 
	 *                    Complete the closure Letter Manually               
	 * 
	 */
	public void completeSendClosureLetterTaskManually() throws IOException {
		try {
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */

			sf.seleU.refreshPage();

			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesPlanItemCompleteIFrame);
			sf.seleU.switchToFrame(sf.orchPlan.orchesPlanItemCompleteIFrame);

			for(int i=0; i < 2; i ++) {
				sf.seleU.ScrolltoElement(sf.orchPlan.orchesPlanItemMinusClick);
				sf.seleU.clickElementByJSE(sf.orchPlan.orchesPlanItemMinusClick);
			}

			sf.seleU.ScrolltoElement(sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			sf.seleU.waitElementToBeVisible(sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanClosureLetterOrderLink);
			reportStatusPass(methodName + " Clicked on 'Closure Letter Task", true, false);
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.wait(4000);

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {			
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureState);
			} else {
				sf.seleU.doubleclickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStateForFITStage);
			}

			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailurePendingState);
			reportStatusPass(methodName + " Clicked on 'Create Account In SS Task Failure Pending State", true, false);

			withOutSelectClassDropDownOptions(
					sf.orchPlan.orchesItemPlanCreateAccountSSFailureDropDownList,"Completed");

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.orchPlan.orchesItemPlanCreateAccountSSFailureStaeSaveButton);
			reportStatusPass(methodName + " Clicked on 'Save Button after changing the task status to Completed", true, false);

			sf.seleU.wait(6000);
		} catch (Throwable e) {
			reportStatusFail(" Error in verifying field value in Office 365 Task", e);
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

	/**
	 * @throws IOException
	 * 
	 *                     Verify if the Element is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify if the Element is displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is cancelled and  displayed as: "
						+ element.getText(), true, true);
			} else {
				reportStatusFail(methodName + " Element" + fieldName + " is not displayed as cancelled", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param taskName
	 * @param task
	 * @param taskType
	 * @param expectedTaskType
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 * 
	 *                              Validate Task
	 * 
	 *                              Validate Task Type
	 */
	public void verifyTaskType(String taskName, WebElement task, WebElement taskType, String expectedTaskType)
			throws IOException, InterruptedException, Exception {

		// Validate Task
		if (sf.seleU.isElementDisplayed(task)) {

			reportStatusPass(methodName + " Verified Task : " + sf.seleU.getTextFromWebElement(task) + " is displayed",
					true, true);

		} else {
			reportStatusFail(methodName + " Invalid task for : " + taskName, true);
		}

		// Validate Task Type
		if (sf.seleU.getTextFromWebElement(taskType).contains(expectedTaskType)) {
			reportStatusPass(methodName + " Verified Task Type for : " + sf.seleU.getTextFromWebElement(task) + " as "
					+ sf.seleU.getTextFromWebElement(taskType), true, true);

		} else {
			reportStatusFail(methodName + " Expected Task Type for : " + sf.seleU.getTextFromWebElement(task) + " is "
					+ expectedTaskType + " . Actual one is " + sf.seleU.getTextFromWebElement(taskType), true);
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

	public void verifyTaskIsDisplay(String expectedTaskType, WebElement task)
			throws IOException{

		try {

			// Validate Task
			if (sf.seleU.isElementDisplayed(task)) {

				reportStatusPass(methodName + " Verified Task : " + sf.seleU.getTextFromWebElement(task) + " is displayed",
						true, true);

			} 


		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	public void withOutSelectClassDropDownOptions(List<WebElement> ele, String value) throws IOException{ {
		try {


			List<WebElement> opt = new ArrayList<WebElement>();
			opt = ele;
			for (int i = 0; i < opt.size(); i++) {
				Thread.sleep(1000);
				String text = opt.get(i).getText().trim();
				if (text.equals(value)) {
					opt.get(i-1).click();
					logger.info(value + " is correctly shown and selected");
					System.out.println(value + " is correctly shown and selected");
					break;
				} else {
					System.out.println(value + " value is not selected " + value);
				}
			} 
		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	}


	/**PA PI2Sp2
	 * @throws IOException
	 * 
	 *                    Verify whether task isPresent and click ok button                
	 * 
	 */
	public void verify_TaskStatus_InOrchestrationPlan(String status, List<WebElement> taskStatusList) throws IOException {
		try {
			int counting  = 0; int j = 1;
			/*
			 * Verify Orchestration Plan Header, Orchestration Plan Number and Orchestration
			 * State
			 */
			ArrayList ar = new ArrayList();


			// Verify Field value matches the expected result
			for(int i = 0; i < taskStatusList.size(); i++) {
				sf.seleU.wait(2000);
				//		System.out.println(sf.seleU.getElementAttribute(taskStatusList.get(i), "title"));
				if (sf.seleU.getElementAttribute(taskStatusList.get(i), "title").trim().contains(status)) {
					ar.add(j + "." + " " + sf.seleU.getElementAttribute(taskStatusList.get(i), "title") + "\n");
					counting++;
					j++;
					//	break;
				} 
			}
			reportStatusPass(methodName + "Total task " + status + "is :"  + counting + " " + AdditionalUtilities.getAsString(ar), true, true);

			sf.seleU.switchToDefaultContent();
		} catch (Exception e) {
			reportStatusFail(" Error in verification of status for the tasks", e);
			e.printStackTrace();
		}
	}


}
