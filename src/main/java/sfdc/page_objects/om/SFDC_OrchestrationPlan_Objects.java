package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Orchestration Plan Page
 */
public class SFDC_OrchestrationPlan_Objects {

	public SFDC_OrchestrationPlan_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Orchestration Plan')]//following-sibling::slot//lightning-formatted-text")
	public WebElement orchestrationPlanHeaderValueText;

	@FindBy(how = How.XPATH, using = "//div//span[text()='Orchestration Plan Name']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement orchestrationPlanNumberValueText;

	@FindBy(how = How.XPATH, using = "//th[text()='State']//ancestor::table//td/span")
	public WebElement orchestrationStateValueText;

	@FindBy(how = How.XPATH, using = "//div[@ctrl='PlanViewController']//div[@class='TaskAction']//a[contains(.,'Start Order')]")
	public List<WebElement> taskStartOrderHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'item-header') and contains(.,'Start Order')]//following-sibling::div[contains(@class,'item-body-text')]//div[@class='opv_cardItem']")
	public WebElement taskTypeInStartOrderHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Create Service Account and Work Order in Supersystem')]")
	public WebElement taskCreateServiceAccountAndWorkOrderHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'item-header') and contains(.,'Create Service Account and Work Order in Supersystem')]//following-sibling::div[contains(@class,'item-body-text')]//div[@class='opv_cardItem']")
	public WebElement taskTypeInCreateServiceAccountAndWorkOrderHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Vlocity Order completion')]")
	public WebElement taskVlocityOrderCompletionOrderHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'item-header') and contains(.,'Vlocity Order completion')]//following-sibling::div[contains(@class,'item-body-text')]//div[@class='opv_cardItem']")
	public WebElement taskTypeInVlocityOrderCompletionOrderHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'PONR')]")
	public WebElement taskPONRHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'item-header') and contains(.,'PONR')]//following-sibling::div[contains(@class,'item-body-text')]//div[@class='opv_cardItem']")
	public WebElement taskTypeInPONRHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Create Assets')]")
	public WebElement taskCreateAssetsHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'item-header') and contains(.,'Create Assets')]//following-sibling::div[contains(@class,'item-body-text')]//div[@class='opv_cardItem']")
	public WebElement taskTypeInCreateAssetsHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Complete Order')]")
	public WebElement taskCompleteOrderHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'item-header') and contains(.,'Complete Order')]//following-sibling::div[contains(@class,'item-body-text')]//div[@class='opv_cardItem']")
	public WebElement taskTypeInCompleteOrderHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Start Order (Milestone) is Cancelled')]")
	public WebElement titleCancelledStartOrder;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Service Account and Work Order in Supersystem (Manual Task in) is Discarded')]")
	public WebElement titleCancelledCreateServiceAccountAndWorkOrder;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Vlocity Order completion (Manual Task in) is Discarded')]")
	public WebElement titleCancelledVlocityOrderCompletion;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'PONR (Milestone) is Discarded')]")
	public WebElement titleCancelledPONR;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Assets (Auto Task) is Discarded')]")
	public WebElement titleCancelledCreateAutoTasks;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Complete Order (Milestone) is Discarded')]")
	public WebElement titleCancelledCompleteOrder;

	@FindBy(how = How.XPATH, using = "//iframe[@id='asset-User_Dashboard']")
	public WebElement orchesPlanIFrame;

	@FindBy(how = How.XPATH, using = "//div[@data-tooltip='User Dashboard']/h1")
	public WebElement orchesPlanUserDashboardText;

	@FindBy(how = How.XPATH, using = "//div[@data-tooltip='Manager Dashboard']")
	public WebElement orchesPlanManageDashboardText;

	@FindBy(how = How.XPATH, using = "//span[@data-tooltip='Start Order']/parent::div/button")
	public WebElement orchesItemNameActionsArrowClick;

	@FindBy(how = How.XPATH, using = "//div[@class='row data']/div[contains(@class,'cell col-0-cell')]/div[@class='content string']")
	public List<WebElement> orchesItemNameOrderNumberInRecord;

	@FindBy(how = How.XPATH, using = "//div[@class='menu-item-label']")
	public List<WebElement> orchesItemActionsInRecord;

	@FindBy(how = How.XPATH, using = "//li[@class='vlocity_cmt__OrchestrationItem__c.Complete_Task']")
	public WebElement orchesItemActionsCompleteTask;

	@FindBy(how = How.XPATH, using = "//li[@class='vlocity_cmt__OrchestrationItem__c.Assign_to_Me']")
	public WebElement orchesItemActionsAssgnToMeTask;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'modal-header slds-modal__header')]/h2")
	public WebElement orchesItemActionPopUpHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'visibilitySwitcher')]//following::div[contains(@class,'test-id__field-label-container slds-form-element__label')]/span[@class='test-id__field-label']")
	public List<WebElement> orchesItemActionPopUpFieldText;

	@FindBy(how = How.XPATH, using = "//span[text()='Site Name']")
	public WebElement orchesItemActionPopUpFieldSiteNameText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'visibilitySwitcher')]//following::div[contains(@class,'uiInput')]/label/span[text()='CAN Number']")
	public WebElement orchesItemActionPopUpCanNumFieldText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'visibilitySwitcher')]//following::div[contains(@class,'uiInput')]/label/span[text()='CAN Number']/following::input[contains(@class,'input')]")
	public WebElement orchesItemActionPopUpCanNumFieldInput;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'visibilitySwitcher')]//following::div[contains(@class,'uiInput')]/label/span[text()='CAN Number']//following::button/span[text()='Save']")
	public WebElement orchesItemActionCompleteTaskSaveButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'DESKTOP uiContainerManager')]//following::div[@class='modal-footer slds-modal__footer']//span[text()='Save']")
	public WebElement orchesItemActionAssignToMeSaveButton;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Service Address')]/parent::p")
	public List<WebElement> orchesItemPlanSreviceAddressText;

	// *** RDI Locators In Orchestration Plan

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public WebElement orchesPlanItemCompleteIFrame;
	
	@FindBy(how = How.XPATH, using = "(//iframe[@title='accessibility title'])[2]")
	public WebElement orchesPlanItemCompleteIFrameFitStage;

	@FindBy(how = How.XPATH, using = "//span[@class='icon icon-v-minus']")
	public WebElement orchesPlanItemMinusClick;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Start Order (Milestone) is Completed')]")
	public WebElement orchesItemPlanStartOrderCompletionRDI;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Start Order')]")
	public WebElement orchesItemPlanStartOrderCompletionLinkRDI;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Assign Order (Auto Task) is Completed')]")
	public WebElement orchesItemPlanAssignOrderCompletionRDI;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Assign Order')]")
	public WebElement orchesItemPlanAssignOrderCompletionLinkRDI;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send Welcome Letter (Auto Task)')]")
	public WebElement orchesItemPlanSendLetterOrderCompletionRDI;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Send Welcome Letter')]")
	public WebElement orchesItemPlanSendLetterCompletionLinkRDI;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Billing Activation Failure (Manual Task in) is Completed')]")
	public WebElement orchesItemPlanBillingActivationFailureCompletionRDI;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Billing Activation Failure')]")
	public WebElement orchesItemPlanBillingActivationFailureCompletionLinkRDI;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Activate Billing (Callout to) is Completed')]")
	public WebElement orchesItemPlanAcivateBillingActivationFailureCompletionRDI;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Activate Billing')]")
	public WebElement orchesItemPlanActivateBillingActivationFailureCompletionLinkRDI;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Complete Circuit Design And IP Assignment')]")
	public WebElement orchesItemPlanCompleteCircuitDesignTask;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Complete Access & Service Provisioning (Manual Task in) is Ready')]/following-sibling::div//i")
	public WebElement orchesItemPlanCompleteCircuitDesignClick;
	
	// *** Cable Order Locators In Orchestration Plan

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Review Order Details (Manual Task in) is Completed')]")
	public WebElement orchesItemPlanReviewOrderDetailsCompletionCable;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Cable Order (Manual Task in) is Completed')]")
	public WebElement orchesItemPlanCreateCableOrderTaskCompletionCable;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send Appointment Letter (Auto Task)')]")
	public WebElement orchesItemPlanAppointementLetterCompletionCable;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Send Appointment Letter')]")
	public WebElement orchesItemPlanAppointementLetterTaskLink;


	@FindBy(how = How.XPATH, using = "//a[contains(.,'Review Order Details')]")
	public WebElement orchesItemPlanReviewOrderDetailsCompletionLinkCable;

	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Cable Order (Manual Task in) is ')]/following-sibling::div//i")
	public WebElement orchesItemPlanCreateCableOrderClick;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Review Order Details (Manual Task in) is')]/following-sibling::div//i")
	public WebElement orchesItemPlanReviewOrderDetailsOrderClick;
	
	
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Create Account in SS Task Failure')]")
	public WebElement orchesItemPlanCreateAccountSSFailureOrderLink;
	
	@FindBy(how = How.XPATH, using = "(//button[@title='Edit State']//preceding-sibling::span)[3]")
	public WebElement orchesItemPlanCreateAccountSSFailureState;
	
	@FindBy(how = How.XPATH, using = "(//button[@title='Edit State']//preceding-sibling::span)[1]")
	public WebElement orchesItemPlanCreateAccountSSFailureStateForFITStage;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//label[contains(text(),'State')]/..//div/lightning-base-combobox/div/div/input"),
		@FindBy(how = How.XPATH, using = "//label[contains(text(),'State')]/..//div/lightning-base-combobox/div/div/button") })
	public WebElement orchesItemPlanCreateAccountSSFailurePendingState;
	
//	@FindBy(how = How.XPATH, using = "//label[contains(text(),'State')]/..//div/lightning-base-combobox/div/div/input")
//	public WebElement orchesItemPlanCreateAccountSSFailurePendingState;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Save']")
	public WebElement orchesItemPlanCreateAccountSSFailureStaeSaveButton;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'State')]/..//div/lightning-base-combobox/div/div/following-sibling::div/lightning-base-combobox-item/span/span")
	public List<WebElement> orchesItemPlanCreateAccountSSFailureDropDownList;
	
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'State')]/..//div/lightning-base-combobox/div/div/following-sibling::div/lightning-base-combobox-item")
	public List<WebElement> orchesItemPlanCreateAccountSSFailureDropDownAttribute;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Account in SS Task Failure (Manual Task in) is ')]/following-sibling::div//i")
	public WebElement orchesItemPlanCreateAccountSSFailureOrderClick;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Create Work Order in SS Task Failure')]")
	public WebElement orchesItemPlanCreateWordOrderSSFailureOrderLink;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Update Account')]")
	public WebElement orchesItemPlanUpdateAccountCANCompleted;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Account in SS (CAN Creation) (Callout to)')]")
	public WebElement orchesItemPlanCreateAccountCalloutCompleted;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'CAN Creation')]")
	public WebElement orchesItemPlaCANCreationCompleted;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Update Account CAN in SF')]")
	public WebElement orchesItemPlanUpdateAccountAutoTaskOrderLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Create Account in SS (CAN Creation)')]")
	public WebElement orchesItemPlanCreateAccountCalloutTaskOrderLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'CAN Creation Completed')]")
	public WebElement orchesItemPlanCANCreationAutoTaskOrderLink;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Work Order in SS Task Failure (Manual Task in) is ')]/following-sibling::div//i")
	public WebElement orchesItemPlanCreateWorkOrderSSFailureOrderClick;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Create Cable Order')]")
	public WebElement orchesItemPlanCreateCableOrderCompletionLinkCable;
	
	@FindBy(how = How.XPATH, using = "//div/ng-include/div[2]/div/a")
	public List<WebElement> orchesItemPlanAllTaskCompletionLinkCable;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Account Activation')]")
	public WebElement orchesItemPlanAccountActivationOrderLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Send Closure Letter')]")
	public WebElement orchesItemPlanClosureLetterOrderLink;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send Closure Letter (Auto Task)')]")
	public WebElement orchesItemPlanClosureLetterCompletionCable;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Office 365 Order Creation')]")
	public WebElement orchesItemPlanOffice365TaskCompletionLinkCable;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Office 365 Order Creation (Manual Task in) is Completed')]")
	public WebElement orchesItemPlanOffice365CompletionCable;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'PONR Completed (Milestone) is Completed')]")
	public WebElement orchesItemPlanPONRCompletedTaskCable;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Office 365 Order Creation (Manual Task in) is Ready')]")
	public WebElement orchesItemPlanOffice365BeforeCompletion;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'is Frozen')]")
	public List<WebElement> orchesItemPlanFrozentaskStatus;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'is')]")
	public List<WebElement> orchesItemPlanTaskStatus;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Orchestration Plan')]")
	public WebElement orchesItemPlanTextGlobal;
	
	@FindBy(how = How.XPATH, using = "//span[text() ='Orchestration Plan']")
	public WebElement orchesItemPlanText;
	
	// RDI Order
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Complete Circuit Design (Manual Task in) is')]/following-sibling::div//i")
	public WebElement orchesItemPlanCompleteCircuitDesignOrderClick;
	
	// Wireless: Objects
	
	@FindBy(how = How.XPATH, using = "//button[text()='Print']")
	public WebElement printButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'BAN Creation (Callout to) is')]")
	public WebElement orchesPlanBANCreation;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send Credit Request (Callout to) is')]")
	public WebElement orchesPlanSendCreditRequest;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send SIM (Callout to) is')]")
	public WebElement orchesPlanSendSIM;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send SIM Event (Waiting for event) is')]")
	public WebElement orchesPlanSendSimEvent;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Wireless PONR (Milestone) is')]")
	public WebElement orchesPlanWirelessPONR;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Activate CTN (Callout to) is')]")
	public WebElement orchesPlanActivateCTN;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Compute Tax (Callout to) is')]")
	public WebElement orchesPlanComputeTax;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Validate/Submit Order (Callout to) is')]")
	public WebElement orchesPlanValidateSubmitOrder;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Validate/Submit Callback Event (Waiting for event) is')]")
	public WebElement orchesPlanValidateSubmitOrderEvent;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Validate/Submit Order For Accessories (Callout to) is')]")
	public WebElement orchesPlanValidateSubmitOrderAccessories;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Ship Request (Callout to) is')]")
	public WebElement orchesPlanShipRequest;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Ship Request Event (Waiting for event)')]")
	public WebElement orchesPlanShipRequestEvent;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Send Closure Letter (Auto Task) is')]")
	public WebElement orchesPlanValidateSendClosureLetter;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'Create Assets (Auto Task) is')]")
	public WebElement orchesPlanCreateAssets;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title,'End Process (Milestone) is')]")
	public WebElement orchesPlanEndProcess;
	
	// BAN Manual tasks
	@FindBy(how = How.XPATH, using = "//span[text()='Manually Complete']/preceding-sibling::span")
	public WebElement banTaskCheckboxManualCompletion;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Complete']")
	public WebElement completeButton;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Fallout Information']")
	public WebElement fallOutInformationText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Vision21 Account Information')]//following::input[contains(@placeholder,'Enter Tentative BAN')]")
	public WebElement fallOutEnterBanNO;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Continue')]")
	public WebElement fallOutContinueButton;
	

}
