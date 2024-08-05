package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 20/FEB/2020
 * 
 *         Case Details Page(Email to case> Accpet case from omni channel> case
 *         details page)
 *
 */
public class SFDC_CaseDetails_Objects {

	public SFDC_CaseDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div//h1//slot[@name='primaryField']//lightning-formatted-text")
	public WebElement casePrimaryFieldValueText;

	@FindAll({ @FindBy(xpath = "//span[contains(.,'Case Owner')]//parent::div//following-sibling::div//a//span"),
			@FindBy(xpath = "//span[contains(.,'Case Owner')]//parent::div//following-sibling::div//span//span//div//span") })
	public WebElement caseOwnerFieldValueText;

	@FindBy(how = How.XPATH, using = "(//strong[text()='Business Account']/ancestor::vlocity_cmt-output-field/parent::div/following-sibling::div//a)[1]")
	public WebElement businessAccountLink;
	
	@FindBy(how = How.XPATH, using = "(//strong[text()='Billing Account']/ancestor::vlocity_cmt-output-field/parent::div/following-sibling::div//a)[1]")
	public WebElement billingAccountLink;
	
	@FindBy(how = How.XPATH, using = "(//strong[text()='Contact']/ancestor::vlocity_cmt-output-field/parent::div/following-sibling::div//a)[1]")
	public WebElement contactLink;
	
	@FindBy(how = How.XPATH, using = "//lightning-icon[@title='Billing Account']/following-sibling::lightning-formatted-url//a")
	public WebElement billingAccountField;
	
	@FindBy(how = How.XPATH, using = "//lightning-icon[@title='Contact']/following-sibling::lightning-formatted-url//a")
	public WebElement contactField;
	
	@FindBy(how = How.XPATH, using = "//lightning-formatted-text[text()='Relationship Score']")
	public WebElement relationshipScoreField;
	
	@FindBy(how = How.XPATH, using = "//lightning-formatted-text[text()='Relationship Score']/parent::slot/parent::lightning-layout-item/following-sibling::lightning-layout-item")
	public WebElement relationshipScoreValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Number')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement caseNumberFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Status')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement statusFieldValueText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Priority')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement priorityFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Type')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement TypeFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Sub Type')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement SubTypeFieldValueText;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Contact Name')]//parent::div//following-sibling::div//span//a//span"),
	@FindBy(how = How.XPATH, using = "(//span[starts-with(.,'Contact Name')]//parent::div//following-sibling::div//span)[1]")})
	public WebElement contactNameValueText;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='New Contact']")
	public WebElement newContactField;
	
	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Contact Email')]//parent::div//following-sibling::div//span//a")
	public WebElement contactEmailValueText;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Manage Contact']")
	public WebElement manageContactButton;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and @title='Close']/parent::div/preceding-sibling::div")
	public WebElement addContactMessage;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//strong[text()='Business Account']/ancestor::vlocity_cmt-output-field/parent::div/following-sibling::div//a"),
	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Account Name')]//parent::div//following-sibling::div//span//a//span")})
	public WebElement accountNameValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Origin')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement caseOriginFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Product Family')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement productFamilyFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Reason')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement caseReasonFieldValueText;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Record Type')]//parent::div//following-sibling::div//force-record-type//span"),
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Case Record Type')]/parent::div/following-sibling::div//records-record-type//div//div//span")})
	public WebElement caseRecordTypeFieldValueText;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Change Record Type' and @type='button']")
	public WebElement changeRecordTypeButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='SNOW Integration']/parent::div/preceding-sibling::div//input")
	public WebElement snowIntegrationRadioButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subject')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement subjectFieldValueText;

	@FindBy(how = How.XPATH, using = "//*[.='Additional Information']//ancestor::h3/parent::div//span[.='Description']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement descriptionFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Internal Comments')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement internalCommentsFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Web Email')]//parent::div//following-sibling::div//a")
	public WebElement webEmailFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Last Modified By')]//parent::div//following-sibling::div//a//span")
	public WebElement lastModifiedByFieldValueText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Validate Case Details']")
	public WebElement validateCaseDetailsButton;
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Validate Case Details']/following::input")
	public List<WebElement> subscriptionInputBox;
		
	@FindBy(how = How.XPATH, using = "//span[text()='Update']/parent::button")
	public WebElement updateButton;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Subscription']/ancestor::vlocity_cmt-output-field/parent::div/following-sibling::div//a")
	public WebElement subscriptionLink;
	
	@FindBy(how = How.XPATH, using = "(//lightning-formatted-text[text()='Record Type']/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//lightning-formatted-text)[1]")
	public WebElement recordTypeValue;
	
	@FindBy(how = How.XPATH, using = "(//lightning-formatted-text[text()='Status']/ancestor::lightning-layout-item/following-sibling::lightning-layout-item//lightning-formatted-text)[1]")
	public WebElement statusValue;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Internal Case')]")
	public WebElement internalCase;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ETM')]/following::input")
	public List<WebElement> caseOrigin;
		
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Case Type')]/following::input")
	public List<WebElement> buttonFraudRiskType;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/parent::label/parent::div/child::input)[1]")
	public WebElement buttonETM;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='External Ticket']/parent::label/parent::div/child::input)[1]")
	public WebElement buttonExternalTicket;
	
	@FindBy(how = How.XPATH, using = "//span[text()='External Ticket Number']/parent::label/parent::div/following-sibling::div//input")
	public WebElement externalTicketNumberTextBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='External Ticket']/following::span[text()='Source']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement sourceInputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='External Ticket']/following::span[text()='Status']/parent::label/parent::div/following-sibling::div/child::div/child::div)[3]")
	public WebElement statusValueBox;
		
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='ETM L1']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement etmL1InputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='ETM L2']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement etmL2InputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='ETM L3']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement etmL3InputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='ETM L4']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement etmL4InputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='ETM L5']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement etmL5InputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='Status']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement statusInputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[text()='Product Line']/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement productLineInputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[contains(text(),'Preferred Communication Method')]/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement preferredCommunicationMethodInputBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='ETM']/following::span[contains(text(),'Preferred Communication Time')]/parent::label/parent::div/following-sibling::div/child::div/child::div)[1]")
	public WebElement preferredCommunicationTimeInputBox;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Enter account')]/ancestor::th/following-sibling::td//button")
	public WebElement accountNumberEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Date of Payment')]/ancestor::th/following-sibling::td//button")
	public WebElement dateOfPaymentEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Amount of Payment')]/ancestor::th/following-sibling::td//button")
	public WebElement amountOfPaymentEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Where payment was made')]/ancestor::th/following-sibling::td//button")
	public WebElement paymentMadeEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Location Street Number')]/ancestor::th/following-sibling::td//button")
	public WebElement streetNumberEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Location Street Name')]/ancestor::th/following-sibling::td//button")
	public WebElement streetNameEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Location City')]/ancestor::th/following-sibling::td//button")
	public WebElement cityEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Location Postal Code')]/ancestor::th/following-sibling::td//button")
	public WebElement postalCodeEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Location Province')]/ancestor::th/following-sibling::td//button")
	public WebElement provinceEditor;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-formatted-text[contains(text(),'Full Account')]/ancestor::th/following-sibling::td//button")
	public WebElement accountDisconnectEditor;
		
	@FindBy(how = How.XPATH, using = "//form//input")
	public WebElement accountNumberInputBox;
			
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='ETM L1']/following::ul//li//span//span")
	public List<WebElement> etmL1Options;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='ETM L2']/following::ul//li//span//span")
	public List<WebElement> etmL2Options;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='ETM L3']/following::ul//li//span//span")
	public List<WebElement> etmL3Options;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='ETM L4']/following::ul//li//span//span")
	public List<WebElement> etmL4Options;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='ETM L5']/following::ul//li//span//span")
	public List<WebElement> etmL5Options;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='Status']/following::ul//li//span//span")
	public List<WebElement> statusOptions;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "(//span[text()='Status'])[6]/following::ul//li//div//span//span"),
	@FindBy(how = How.XPATH, using = "(//span[text()='Status'])[5]/following::ul//li//div//span//span")})
	public List<WebElement> statusOptionsExternalType;
		
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[text()='Product Line']/following::ul//li//span//span")
	public List<WebElement> productLineOptions;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[contains(text(),'Preferred Communication Method')]/following::ul//li//span//span")
	public List<WebElement> preferredCommunicationMethodOptions;
	
	@FindBy(how = How.XPATH, using = "//span[text()='ETM']/following::span[contains(text(),'Preferred Communication Time')]/following::ul//li//span//span")
	public List<WebElement> preferredCommunicationTimeOptions;
		
	@FindBy(how = How.XPATH, using = "//input[@data-value='Internal']/following::span[contains(text(),'Product Family')]/parent::label/parent::div/following-sibling::div//div")
	public List<WebElement> productFamilyInputBox;
	
	@FindBy(how = How.XPATH, using = "//input[@data-value='Internal']/following::span[text()='Category']/parent::label/parent::div/following-sibling::div//div")
	public List<WebElement> categoryInputBox;
	
	@FindBy(how = How.XPATH, using = "//input[@data-value='Internal']/following::span[text()='Sub Category']/parent::label/parent::div/following-sibling::div//div")
	public List<WebElement> subcategoryInputBox;
	
	@FindBy(how = How.XPATH, using = "//input[@data-value='Wireless']")
	public WebElement productFamilyWireless;
				
	@FindBy(how = How.XPATH, using = "//input[@data-value='Internal']/following::span[text()='Sub Category']/following::ul//li//span//span")
	public List<WebElement> subCategoryOptions;
			
	@FindBy(how = How.XPATH, using = "//input[@data-value='Internal']/following::span[text()='Category']/following::ul//li//span//span")
	public List<WebElement> categoryOptions;
				
	@FindBy(how = How.XPATH, using = "//input[@data-value='Internal']/following::span[contains(text(),'Product Family')]/following::ul//li//span//span")
	public List<WebElement> productFamilyOptions;
	
	@FindBy(how = How.XPATH, using = "//span[text()='External Ticket']/following::span[text()='Source']/following::ul//li//span//span")
	public List<WebElement> sourceOptions;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Info')]/parent::div")
	public List<WebElement> toolTip;
		
	@FindAll({
	@FindBy(how = How.XPATH, using = "//button[@label='Next']"),
	@FindBy(how = How.XPATH, using = "//button[@type='button' and @aria-label='Next Step']"),
	@FindBy(how = How.XPATH, using = "//span[@aria-label='Next Step']/parent::button"),
	@FindBy(how = How.XPATH, using = "//span[@dir='ltr' and text()='Next']")})
	public WebElement nextButton;
		
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Created By')]//parent::div//following-sibling::div//a")
	public WebElement createdByFieldValueText;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Status']")
	public WebElement editStatusButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Priority']")
	public WebElement editPriorityButton;
		
	@FindBy(how = How.XPATH, using = "//button[@title='Edit Account Name']")
	public WebElement editAccountNameButton;
		
	@FindBy(how = How.XPATH, using = "//button[@title='Edit Type']")
	public WebElement editTypeButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Case Origin']")
	public WebElement editCaseOriginButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Case Reason']")
	public WebElement editCaseReasonButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Subject']")
	public WebElement editSubjectButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Description']")
	public WebElement editDescriptionButton;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Status')]//following-sibling::div//button")
	public WebElement statusEditDropdown;

	@FindBy(how = How.XPATH, using = "//span[@title='Closed']")
	public WebElement statusEditOptionClosed;

	@FindBy(how = How.XPATH, using = "//span[@title='In Progress']")
	public WebElement statusEditOptionInProgress;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Resolved']")
	public WebElement statusEditOptionResolved;

	@FindAll({ 
	@FindBy(how = How.XPATH, using = "//button[@title='Save']"),
	@FindBy(how = How.XPATH, using = "//button[text()='Save']")})
	public WebElement saveButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@title='Cancel']"),
			@FindBy(how = How.XPATH, using = "//button[text()='Cancel']") })
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Save')]")
	public WebElement save;

	@FindBy(how = How.XPATH, using = "//li[contains(.,'Please take the case ownership before updating the case.')]")
	public WebElement pleaseTakeOwnershipMsg;

	@FindBy(how = How.XPATH, using = "//button[@title='Close error dialog']")
	public WebElement closeErrorDialogueButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Undo']")
	public WebElement undoButton;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Priority')]//following-sibling::div//button")
	public WebElement priorityEditDropdown;

	@FindBy(how = How.XPATH, using = "//span[@title='Low']")
	public WebElement priorityEditOptionLow;

	@FindBy(how = How.XPATH, using = "//span[@title='Medium']")
	public WebElement priorityEditOptionMedium;
	
	@FindBy(how = How.XPATH, using = "//span[@title='High']")
	public WebElement priorityEditOptionHigh;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Critical']")
	public WebElement priorityEditOptionCritical;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Planning']")
	public WebElement priorityEditOptionPlanning;

	@FindBy(how = How.XPATH, using = "//label[starts-with(.,'Type')]//following-sibling::div//input")
	public WebElement typeEditDropdown;

	@FindBy(how = How.XPATH, using = "//span[@title='Problem']")
	public WebElement typeEditOptionProblem;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Case Origin')]//following-sibling::div//button")	
	public WebElement caseOriginEditDropdown;

	@FindBy(how = How.XPATH, using = "//span[@title='Phone']")
	public WebElement caseOriginEditOptionPhone;

	@FindBy(how = How.XPATH, using = "//span[@title='Email']")
	public WebElement caseOriginEditOptionEmail;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Case Reason')]//following-sibling::div//button")	
	public WebElement caseReasonEditDropdown;

	@FindBy(how = How.XPATH, using = "//span[@title='Billing Inquiry']")
	public WebElement caseReasonEditOptionBillingInquiry;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Subject')]//following-sibling::div//input")	
	public WebElement subjectEditInput;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Account Name')]//following-sibling::div//input")	
	public WebElement accountNameInput;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Description')]//following-sibling::div//textarea")
	public WebElement descriptionEditInput;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Mark Status as Complete')]/ancestor::button")
	public WebElement markStatusAsCompleteButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Status changed successfully')]")
	public WebElement statusChangedSuccessfullyMsg;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Please take the case ownership before updating the case.')]")
	public WebElement pleaseTakeOwnershipMsgInStatus;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Please take the case ownership before updating the case.')]//ancestor::div//following-sibling::button[@title='Close']")
	public WebElement closeErrorMsgButton;

	@FindBy(how = How.XPATH, using = "//a[@title='In Progress']")
	public WebElement inProgressTab;

	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='New']")
	public WebElement newTab;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Mark as Current Status')]//parent::button")
	public WebElement markAsCurrentStatusButton;

	@FindBy(how = How.XPATH, using = "//a[@class='tabHeader']//span[contains(.,'Email')]")
	public WebElement emailTab;

	@FindBy(how = How.XPATH, using = "//button[@title='Compose']")
	public WebElement composeEmailButton;

	@FindBy(how = How.XPATH, using = "//li[contains(.,'Please take case ownership before sending email to the customer')]")
	public WebElement emailSendReviewErrorMsg;

	@FindBy(how = How.XPATH, using = "//button[text()='Change Owner']")
	public WebElement changeOwnerButton;

	@FindBy(how = How.XPATH, using = "(//input[contains(@placeholder,'Search Users')])[1]")
	public WebElement searchPeopleInput;

	@FindBy(how = How.XPATH, using = "//div[@class='name']//a")
	public WebElement searchedOwnerName;

	@FindBy(how = How.XPATH, using = "//span[@class='pillText']")
	public WebElement updatedCaseOwnerValue;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//button[@title='Submit']"),
		@FindBy(how = How.XPATH, using = "//button[@value='change owner']")})
	public WebElement submitChangeCaseOwnereButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'now owns the record for ')]")
	public WebElement ownerUpdatedMsg;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Customer Reason')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement caseCustomerReason;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Category')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement caseCategory;

	@FindBy(how = How.XPATH, using = "//span[.='Select Closed Status']//parent::button")
	public WebElement selectClosedStatusButton;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//button[@type='button' and @title='Close']"),
	@FindBy(how = How.XPATH, using = "//button[@title='Close']")})
	public WebElement closeButton;
	
	@FindBy(how = How.XPATH, using = "(//button[@title='Close'])[4]")
	public WebElement subscriptionCloseButton;
	
	@FindBy(how = How.XPATH, using = "//h2[@title='Next Best Action']/parent::div/parent::div/parent::header/ancestor::article")
	public WebElement nextBestActionField;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Customer is interested, start the process']")
	public WebElement customerInterestButton;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'https')]")
	public WebElement cableRenewalLink;
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Access Denied']")
	public WebElement accessDeniedField;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Finish']")
	public WebElement finishButton;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Accepted']")
	public WebElement acceptedField;

	@FindBy(how = How.XPATH, using = "//select[contains(@class,'stepAction')]")
	public WebElement selectClosedStatusDropdown;

	@FindBy(how = How.XPATH, using = "//span[.='Closed']//parent::a")
	public WebElement closedButton;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'Subscription')]//ancestor::vlocity_cmt-output-field//parent::div//following-sibling::div//a"),
	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Subscription')]//parent::div//following-sibling::div//span//a//span")})
	public WebElement subscriptionFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[.='Case created']")
	public WebElement caseCreatedSpan;

	@FindBy(how = How.XPATH, using = "//span[@title='Other']")
	public WebElement caseReasonEditOptionOther;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Product Family')]//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement caseProductFieldValueText;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'Billing Account')]//ancestor::vlocity_cmt-output-field//parent::div//following-sibling::div//a"),
	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Billing Account')]//parent::div//following-sibling::div//span//a//span")})
	public WebElement billingAccountNameValueText;

	@FindBy(how = How.XPATH, using = "//a[contains(@aria-label,'Enter new owner name—Current Selection')]")
	public WebElement selectChangeUser;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Queues']")
	public WebElement queuesOptionForOwner;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Queues...']")
	public WebElement searchQueuesInput;

	@FindBy(how = How.XPATH, using = "//div[.='Default Queue']//ancestor::a")
	public WebElement searchResultDefaultQueue;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Default Queue']")
	public WebElement resultDefaultQueue;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Tier 2 - Wireline']")
	public WebElement wirelineQueueNew;
	
	@FindBy(how = How.XPATH, using = "//div[@title='Tier 2 - Wireline']")
	public WebElement wirelineQueue;


	@FindBy(how = How.XPATH, using = "//div//span[@title=\"Users\"]")
	public WebElement searcResultUsersOption;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Users...']")
	public WebElement searchUsersInput;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'error')]")
	public WebElement caseEditErrorMessage;

	@FindBy(how = How.XPATH, using = "//span[@class=\"toastMessage forceActionsText\"]")
	public WebElement notAllowedToChangeStatusText;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'is-active')]//a[@data-tab-name='Awaiting Third Party']")
	public WebElement activeThirdPartyTab;
	
	@FindBy(how = How.XPATH, using = "//li[contains(@class,'is-active')]//a[@title='Awaiting Customer Response']")
	public WebElement activeAwaitingCustomerResponseTab;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Notifications']/ancestor::button")
	public WebElement notificationsButton;
	
	@FindBy(how = How.XPATH, using = "//h2[@class='titleName']/parent::div/following-sibling::div//span[contains(text(),'Awaiting Customer Response')]")
	public WebElement notificationsText;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Case status has changed')]/following-sibling::span)[1]")
	public WebElement notificationsStatusText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'New Case comment')]/following-sibling::span")
	public WebElement notificationsCommentsText;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Close Notifications']")
	public WebElement closeNotificationsIcon;

	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='Resolved']")
	public WebElement resolvedTab;

	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='Awaiting Third Party']")
	public WebElement awaitingThirdPartyTab;
	
	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='Awaiting Customer Response']")
	public WebElement awaitingCustomerResponseTab;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Class']")
	public WebElement editCaseClassButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Sub Class']")
	public WebElement editCaseSubClassButton;

	@FindBy(how = How.XPATH, using = "(//label[contains(.,'Class')]//following-sibling::div//button)[2]")
	public WebElement editCaseSubClassField;

	@FindBy(how = How.XPATH, using = "//span[@title='Wire']")
	public WebElement editCaseClassWireOption;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Rogers Business Self-Serve']")
	public WebElement editCaseRogersBusiness;

	@FindBy(how = How.XPATH, using = "//span[@title='Service Failure']")
	public WebElement editCaseSubClassSeviceFailureOption;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Access']")
	public WebElement editCaseSubClassAccess;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Product Family']")
	public WebElement editProductFamilyButton;

	@FindBy(how = How.XPATH, using = " //label[contains(.,'Product Family')]//following-sibling::div//button")
	public WebElement editCaseProductFamilyField;

	@FindBy(how = How.XPATH, using = "//span[@title='Business Phone']")
	public WebElement editCaseProductFamilyBusinessPhoneOption;

	@FindBy(how = How.XPATH, using = "//span[@title='Device and Hardware']")
	public WebElement caseReasonEditOptionDeviceandHardware;

	@FindBy(how = How.XPATH, using = "(//label[contains(.,'Class')]//following-sibling::div//button)[1]")
	public WebElement editCaseClassField;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Category']")
	public WebElement editCategoryButton;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Category')]//following-sibling::div//button")
	public WebElement editCategoryField;

	@FindBy(how = How.XPATH, using = "//span[@title='Long Distance']")
	public WebElement editCaseCategoryLongDistanceOption;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Application']")
	public WebElement editCaseCategoryApplication;


	@FindBy(how = How.XPATH, using = "//span[@title='Device and Hardware']")
	public WebElement editCaseReasonDeviceandHardwareOption;

	@FindBy(how = How.XPATH, using = "//button[@title=\"Close\"]")
	public WebElement errorCloseButton;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No Available offers!')]")
	public WebElement noAvaiableOffers;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No milestones to show.')]")
	public WebElement MileStoneTimer;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='milestoneTimerText ontrackTimer']//span)[2]")
	public WebElement resolutionTime;
	
	@FindBy(how = How.XPATH, using = "//span[@class='milestonesTitle']/ancestor::header/parent::div/following-sibling::div//div[@class='noPendingMilestoneMessage']")
	public WebElement milestoneMessage;

	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Case Owner')])[2]/parent::div/following-sibling::div")
	public WebElement caseOwner;

	@FindBy(how = How.XPATH, using = "(//button[@title=\"More Tabs\"])[1]")
	public WebElement moreTab;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'External Tickets')]")
	public WebElement externalTickets;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Details')]")
	public WebElement detailsBtn;

	@FindBy(how = How.XPATH, using = "//a[@data-tab-value='detailTab']")
	public WebElement detailsTab;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'(1)')]")
	public WebElement tcktCount;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'(0)')]")
	public WebElement tcktCount0;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ServiceNow')]")
	public WebElement servNow;

	@FindBy(how = How.XPATH, using = "//a[@data-refid=\"recordId\"]")
	public WebElement incedentNum;

	@FindBy(how = How.XPATH, using = "//div[@title='Tier 2 - Wireline']")
	public WebElement tier2WireLineDD;

}
