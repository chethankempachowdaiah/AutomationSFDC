package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 14/jan/2020
 * 
 *         SFDC Account Details page (for Buniess/Service/Billing)
 *
 */
public class SFDC_AccountDetails_Objects {

	public SFDC_AccountDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Relationship Score')]/ancestor::lightning-formatted-rich-text/ancestor::vlocity_cmt-output-field/parent::div/parent::div")
	public WebElement relationshipScoreIcon;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Relationship Score')]/ancestor::lightning-formatted-rich-text/ancestor::vlocity_cmt-output-field/parent::div/following-sibling::div//a")
	public WebElement relationshipScoreDetails;
	
	@FindBy(how = How.XPATH, using = "//div[@slot='flyout']")
	public WebElement relationshipScoreSurveyReport;
	
	@FindBy(how = How.XPATH, using = "//button[@title='View Account Hierarchy' or @name='AccountHierarchy']")
	public WebElement accountHierarchyButton;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'hierarchyTriggerCellCurrentRecord')]")
	public WebElement currentAccountInHierarchy;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'hierarchyTriggerCellLink textUnderline')]")
	public List<WebElement> allAccountsInHierarchy;

	@FindBy(how = How.XPATH, using = "//a[@id='detailTab__item' or contains(.,'Details')]")
	public List<WebElement> detailsTabAll;

	@FindBy(how = How.XPATH, using = "//a[@id='detailTab__item' or contains(.,'Details')]")
	public WebElement detailsTab;

	@FindBy(how = How.XPATH, using = "//a[.='Overview']")
	public WebElement overviewTab;
	
	@FindBy(how = How.XPATH, using = "//footer/button[contains(.,'Close')]")
	public WebElement closeButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Account Name')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement accountNameValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Contact')]/parent::div/following-sibling::div//span//slot//div//a")
	public WebElement contactValueText;
	
	@FindBy(how = How.XPATH, using = "//p[@title='Contact']/parent::div/descendant::records-hoverable-link//a")
	public List<WebElement> contactLink;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][text()='Status']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement statusValueText;
	
	@FindBy(how = How.XPATH, using = "(//span[@class='test-id__field-label'][text()='Status']/parent::div/following-sibling::div//lightning-formatted-text)[2]")
	public WebElement statusValueTextNew;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Account Record Type')]/parent::div/following-sibling::div//records-record-type//div//div//span")
	public WebElement accountRecordTypeValueText;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Account Record Type')]/parent::div/following-sibling::div//records-record-type//div//div//span)[2]")
	public WebElement accountRecordTypeValueTextNew;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Root Account']/parent::div/following-sibling::div//a//span")
	public WebElement rootAccountValueText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Root Account']/parent::div/parent::div")
	public WebElement rootAccountField;

    @FindAll({
	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Shipping Address')]/parent::div/following-sibling::div//slot[@slot='outputField']//a/div[1]"),
	@FindBy(how = How.XPATH, using = "(//span[text()='Shipping Address']/parent::div/following-sibling::div//span)[2]")})
	public WebElement shippingAddressValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Account Owner')]/parent::div/following-sibling::div//records-hoverable-link//a//slot//slot//span")
	public WebElement accountOwnerValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Employees')]/parent::div/following-sibling::div//slot[@slot='outputField']//lightning-formatted-number")
	public WebElement employeesValueText;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Employees')]/following-sibling::p/slot/lightning-formatted-number")
	public WebElement newEmployeesValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Phone')]/parent::div/following-sibling::div//lightning-formatted-phone//a")
	public WebElement phoneValueText;

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Legal Name')]/following-sibling::p//lightning-formatted-text")
	public WebElement legalNameValueText;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Legal Name')]/following-sibling::p/slot/lightning-formatted-text")
	public WebElement newLegalNameValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Parent Account')]/parent::div/following-sibling::div//a//span")
	public WebElement parentAccountValueText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Parent Account']/parent::div/following-sibling::div/child::button")
	public WebElement editParentAccountButton;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Parent Account']/parent::lightning-grouped-combobox/child::div//button")
	public WebElement parentAccountBox;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Parent Account']/parent::lightning-grouped-combobox/child::div//input")
	public WebElement parentAccountInputText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Account Number')]/parent::div/following-sibling::div//lightning-formatted-text")
	public List<WebElement> accountNumberFieldValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Premises')]/parent::div/following-sibling::div//records-hoverable-link//a//slot//slot//span")
	public WebElement premisesValueInput;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Premises')]/parent::div/following-sibling::div//records-hoverable-link//a")
	public WebElement premisesValueInputLink;

	@FindBy(how = How.XPATH, using = "//input[@id='UA_CompanyName']")
	public List<WebElement> businessAccountNameAsCompanyName;

	@FindBy(how = How.XPATH, using = "//input[@id='UA_CurrentAddress']")
	public WebElement businessAddressAsCurrentAddress;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='UA_EmployeeSize']"),
	@FindBy(how = How.XPATH, using = "//label[text()='Number of Employees']/parent::div/parent::div/child::input")})
	public List<WebElement> noOfEmployeesInput;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Employee Size')]")
	public List<WebElement> employeeTag;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='UA_Attachment']"),
	@FindBy(how = How.XPATH, using = "//span[text()='Or drop files']/ancestor::label/parent::slot/child::input")})
	public List<WebElement> uploadDocumentInput;
	
	@FindBy(how = How.XPATH, using = "//span[.='Next']/parent::Button")
	public List<WebElement> accountDetailsNextButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Sales Segment']")
	public WebElement editSalesSegmentButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Credit Review Exempt']")
	public WebElement editCreditReviewButton;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Sales Segment')]/parent::*//following-sibling::div//input"),
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Sales Segment')]/following-sibling::div//div/child::div/child::div")})
	public WebElement editSalesSegmentInput;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Industry')]/parent::*//following-sibling::div//input")
	public WebElement industryInput;

	@FindBy(how = How.XPATH, using = "//span[@title='Mid-Market']")
	public WebElement midMarketSegment;

	@FindBy(how = How.XPATH, using = "//span[@title='SMB']")
	public WebElement smbSegment;

	@FindBy(how = How.XPATH, using = "//span[@title='Financial Services' or @title='Finance']")
	public WebElement fsSegment;

	@FindBy(how = How.XPATH, using = "//input[@name='Credit_Review_Exempt__c']")
	public WebElement creditReviewExemptCheckbox;

	@FindBy(how = How.XPATH, using = "//lightning-button/button[contains(.,'Save')]")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sales Segment')]/parent::div/following-sibling::div//span")
	public WebElement salesSegementValueInput;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td[5]")
	public List<WebElement> accountsAllRecordActiveClmn;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//*[text()='Employees']/parent::div//ancestor::lightning-formatted-number"),
		@FindBy(how = How.XPATH, using = "(//*[text()='Employees']/parent::div/following-sibling::div//lightning-formatted-number")})
		public WebElement noOfEmpInDetails;

	@FindBy(how = How.XPATH, using = "//*[text()='Last Credit Review']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement lastCreditReviewDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Credit Limit Available')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement creditLimitAvailableValue;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Credit Limit Assigned')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement creditLimitAssignedValue;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Last Credit Review')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement lastCreditReviewValue;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Total Credit Limit Assigned']")
	public WebElement creditLimitAssignedEditButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Credit Limit Available']")
	public WebElement creditLimitAvailableEditButton;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'Total_Credit_Limit_Assigned__c')]")
	public WebElement enterCreditLimitAssign;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'Credit_Limit_Available__c')]")
	public WebElement enterCreditLimitAvailable;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'Last_Credit_Review__c')]")
	public WebElement enterLastCreditReviewDate;

	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement saveButtonCreditCheck;

	@FindBy(how = How.XPATH, using = "//*[text()='Status']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement accountInactive;

	@FindBy(how = How.XPATH, using = "//span[.='Show more actions']/parent::button")
	public WebElement showMoreActions;

	@FindBy(how = How.XPATH, using = "//button[@title='More Tabs']")
	public WebElement showMoreTabs;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Industry']")
	public WebElement editIndustryButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Sub-Industry']")
	public WebElement editSubIndustryButton;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Sub-Industry')]/parent::*//following-sibling::div//input")
	public WebElement subIndustryInput;

	@FindBy(how = How.XPATH, using = "//span[@title='Infrastructure Services']")
	public WebElement infraServicesIndustryOption;
	
	@FindBy(how = How.XPATH, using = "//span[text()='current']/ancestor::tr/following-sibling::tr//a")
	public WebElement serviceAccountLink;

	@FindBy(how = How.XPATH, using = "//span[@title='Other transportation']")
	public WebElement otherTransportationSubIndOption;

	@FindBy(how = How.XPATH, using = "//span[@title='Banking']")
	public WebElement bankingSubIndOption;

	@FindBy(how = How.XPATH, using = "//span[@title='Utilities']")
	public WebElement utilitiesSubIndOption;

	@FindBy(how = How.XPATH, using = "//*[.='Industry']/ancestor::div[contains(@class,'parent')]//button")
	public WebElement helpTextIndustryButton;

	@FindBy(how = How.XPATH, using = "//*[.='Sub-Industry']/ancestor::div[contains(@class,'parent')]//button")
	public WebElement helpTextSubIndustryButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'tooltip-bubble')]")
	public WebElement helpTextIndustry;

	@FindBy(how = How.XPATH, using = "//a[contains(@href,'Opportuni')]//span[@class='view-all-label']")
	public WebElement viewAllOpportunities;

	@FindBy(how = How.XPATH, using = "//button[@name='CancelEdit']")
	public WebElement cancelEdit;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Last Credit Review']")
	public WebElement lastCreditReviewEditButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Credit Risk Value']")
	public WebElement creditRiskValueEditButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Employees']")
	public WebElement editEmployeesButton;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'NumberOfEmployees')]")
	public WebElement enterNumberOfEmployees;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Phone']")
	public WebElement editPhoneButton;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'Phone')]")
	public WebElement enterPhone;

	@FindBy(how = How.XPATH, using = "//a[.='Premise Details']")
	public WebElement premiseDetailsLink;

	@FindBy(how = How.XPATH, using = "//button[.='Check Serviceability']")
	public WebElement checkServiceability;

	@FindBy(how = How.XPATH, using = "//button[@title='Change Owner']")
	public WebElement changeOwnerButton;

	@FindBy(how = How.XPATH, using = "//input[@title='Search Users']")
	public List<WebElement> searchUsersInput;

	@FindBy(how = How.XPATH, using = "//div[@class='name']//following-sibling::a")
	public WebElement selectOwner;

	@FindBy(how = How.XPATH, using = "//button[@name='change owner']")
	public WebElement changeOwnerAfterSelection;

	@FindBy(how = How.XPATH, using = "//div[@id='brandBand_3']//following-sibling::span[contains(.,'Unassigned ')]")
	public List<WebElement> assignedTerritories;

	@FindBy(how = How.XPATH, using = "//flexipage-component2[3]//div[@class='slds-card__footer']//span[@class='view-all-label']")
	public List<WebElement> viewall;
	
	@FindBy(how = How.XPATH, using = "//a[.='Opportunities']")
	public WebElement OppTabDG;
	
	@FindBy(how = How.XPATH, using = "//span[.='High Risk-FnF']")
	public WebElement highRiskFnF;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Contact']/parent::div//ancestor::a")
	public WebElement contactlink;

//	@FindBy(how = How.XPATH, using = "//a[.='Premise Details']")
//	public WebElement premiseDetailsLink;
//
//	@FindBy(how = How.XPATH, using = "//button[.='Check Serviceability']")
//	public WebElement checkServiceability;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Credit & Fraud')]")
	public WebElement creditNFraudCheckHeaderElement;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Chosen')]//parent::div//li")
	public List<WebElement> creditNFraud_ChoseOptions_List_Element;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Credit & Fraud')]//ancestor::div//button[contains(text(),'Save')]")
	public WebElement creditNFraudCheckCreditRiskSaveButton;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Credit & Fraud')]//ancestor::div//button[contains(text(),'Cancel')]")
	public WebElement creditNFraudCheckCreditRiskCancelButton;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Move selection to Available')]")
	public WebElement creditNFraudCheckCreditRiskMoveSelectionToAvailableButton;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Move selection to Chosen')]")
	public WebElement creditNFraudCheckCreditRiskMoveSelectionToChosenButton;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'Edit Credit Risk V')]//parent::button")
	public WebElement creditNFraudCheckEditButton;
	
	@FindBy(how = How.XPATH, using ="//span[.='Done']/parent::button")
	public WebElement done;
	
	@FindBy(how = How.XPATH, using ="//span[.='Billing Address']/parent::div/parent::div//ancestor::lightning-formatted-address/child::a")
	public WebElement newBusAddress;
	
	@FindBy(how = How.XPATH, using ="//lightning-formatted-text[contains(text(),'R4B')]")
	public WebElement r4BField;
	

}
