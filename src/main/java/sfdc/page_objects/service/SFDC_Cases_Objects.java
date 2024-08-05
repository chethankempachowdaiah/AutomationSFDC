package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2020
 *
 *         SFDC Cases page objects
 */
public class SFDC_Cases_Objects {

	public SFDC_Cases_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Open Cases')]")
	public WebElement allOpenCasesOption;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Cases and more...']")
	public WebElement globalCasesAndMoreSearch;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Cases')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> caseLinkGlobalSearchResult;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//td[3]//a[@data-refid='recordId']")
	public List<WebElement> caseNumberAllRows;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td[5]//a[@data-refid='recordId'] ")
	public List<WebElement> caseSubjectAllRows;

	@FindAll({ 
	@FindBy(xpath = "//div[@title='New Customer Case']"), 	
	@FindBy(xpath = "//div[@title='Nouveau']"),
	@FindBy(xpath = "//a[text()='Create New Case']")})
	public WebElement newCaseButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Customer Service')]/preceding::span[@class='slds-radio--faux ng-scope']")
	public WebElement serviceRadioButton;

	@FindBy(how = How.XPATH, using = "//button[text()='Create case']")
	public WebElement createCaseButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Your case number is')]//a")
	public WebElement caseNumber;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='SelectCaseType']//following-sibling::span[contains(.,'Customer Service')]"),
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='Service']")})
	public List<WebElement> caseserviceRadio;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectCaseType']//following-sibling::span[contains(.,'Technical Support')]")
	public List<WebElement> casestechnicalsupportRadio;

	@FindBy(how = How.XPATH, using = "//input[@value='Tier_1_Tech_Support']")
	public WebElement tier1TechSupportRadioButton;

	@FindBy(how = How.XPATH, using = "//select[@id='CaseOrigin']")
	public WebElement caseOrigin;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "//option[@value='Phone']"),
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='Business Phone']")})
	public WebElement casePhoneOptn;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'ProductFamily')]")
	public WebElement caseProductFamily;

	@FindBy(how = How.XPATH, using = "//option[@value='Internet of Things']")
	public WebElement caseIOTOptn;

	@FindBy(how = How.XPATH, using = "//input[@id='Subject']")
	public WebElement caseSubject;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "//textarea[@id='Description']"),
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'enter any additional details')]/parent::div/parent::div/child::textarea")})
	public WebElement caseDescription;
			
	@FindBy(how = How.XPATH, using = "//input[@id='CCF_SearchContact']")
	public WebElement contactInputBox;
	
	@FindBy(how = How.XPATH, using = "//div[@id='CaseCreationBasicDetails_nextBtn']")	
	public WebElement caseNextBtn;
	
	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")	
	public WebElement okButton;
	
	@FindBy(how = How.XPATH, using = "//small[text()='Please select correct case origin']/parent::div")	
	public WebElement caseOriginMessage;
	
	@FindBy(how = How.XPATH, using = "//span[@class='slds-checkbox--faux']")
	public WebElement caseCheckBox;

	@FindBy(how = How.XPATH, using = "//div[@id='SearchByOptions_nextBtn']//p[contains(text(),'Next')]")
	public WebElement caseSearchByOptnNextBtn;

	@FindAll({ 
	@FindBy(xpath = "//span[contains(.,'Next')]"), 
	@FindBy(xpath = "//span[contains(.,'Suivant')]") })
	public WebElement nextButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Status')]//parent::span//following-sibling::div//a")
	public WebElement statusField;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a[@title='New']")
	public WebElement newStatusValue;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a[@title='Awaiting Third Party']")
	public WebElement awaitingThirdPartyStatusValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Priority')]//parent::span//following-sibling::div//a")
	public WebElement priorityField;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a[@title='Medium']")
	public WebElement mediumPriorityValue;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a[@title='Service Request']")
	public WebElement serviceRequestTypeValue;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a[@title='Add WiFi Calling']")
	public WebElement addWifiCallingSubTypeValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Origin')]//parent::span//following-sibling::div//a")
	public WebElement caseOriginField;

	@FindBy(how = How.XPATH, using = "	//div[@class='select-options']//li[@role='presentation']//a[@title='Phone']")
	public WebElement PhonecaseOriginValue;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Reason')]//parent::span//following-sibling::div//a"),
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='Other']")})
	public WebElement caseReasonField;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a[@title='Billing Inquiry']")
	public WebElement billingInquirycaseReasonValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subject')]//parent::label//following-sibling::input")
	public WebElement subjectField;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Description')]//parent::label//following-sibling::textarea")
	public WebElement descriptionField;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Internal Comments')]//parent::label//following-sibling::textarea")
	public WebElement internalComments;

	@FindBy(how = How.XPATH, using = "//button[@title='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case') and contains(., 'was created.')]")
	public WebElement caseCreatedMsg;

	@FindBy(xpath = "//span[contains(.,'Product Family')]//parent::span//following-sibling::div//a")
	public WebElement productFamilyField;

	@FindBy(xpath = "//span[contains(.,'Category')]//parent::span//following-sibling::div//a")
	public WebElement categoryField;

	@FindBy(xpath = "//span[starts-with(.,'Class')]//parent::span//following-sibling::div//a")
	public WebElement classField;

	@FindBy(xpath = "//span[starts-with(.,'Sub Class')]//parent::span//following-sibling::div//a")
	public WebElement subClassField;

	@FindBy(xpath = "//span[starts-with(.,'Customer Reason')]//parent::span//following-sibling::div//a")
	public WebElement customerReasonField;

	@FindBy(xpath = "//span[contains(.,'Famille de produits')]//parent::span//following-sibling::div//a")
	public WebElement productFamilyFieldFrench;

	@FindBy(xpath = "//span[contains(.,'Catégorie')]//parent::span//following-sibling::div//a")
	public WebElement categoryFieldFrench;

	@FindBy(xpath = "//span[contains(.,'Classe')]//parent::span//following-sibling::div//a")
	public WebElement classFieldFrench;

	@FindBy(xpath = "//span[contains(.,'Sous-classe')]//parent::span//following-sibling::div//a")
	public WebElement subClassFieldFrench;

	@FindBy(how = How.XPATH, using = "//div[@class='select-options']//li[@role='presentation']//a")
	public List<WebElement> categoryAllOptions;

	@FindBy(how = How.XPATH, using = "//*[contains(@placeholder,'Search this list')]")
	public WebElement searchCaseInputBox;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'My Cases')]")
	public WebElement myCasesOption;

	@FindBy(how = How.XPATH, using = "//button[@title='More Tabs']")
	public WebElement moreTabs;

	@FindBy(how = How.XPATH, using = "//input[@title=\"Search Contacts\"]")
	public WebElement caseContactName;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td[1]//a[@data-refid='recordId']")
	public List<WebElement> searchedContactsAllRecords;

	@FindBy(how = How.XPATH, using = "	//div[@class='select-options']//li[@role='presentation']//a[@title='Proactive']")
	public WebElement ProactivecaseOriginValue;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td[9]//span//span")
	public List<WebElement> caseRecordTypeAllRows;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td[5]//span//span")
	public List<WebElement> caseStatusAllRows;

	@FindBy(how = How.XPATH, using = "//thead//span[.='Case Record Type']/parent::a")
	public WebElement caseRecordTypeHeader;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Cases and more...' or @placeholder='Search..']")
	public WebElement caseGlobalSearchInput;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Case')]/ancestor::div[contains(@class,'SearchResult')]//tbody//tr//th//a")
	public List<WebElement> caseSearchResult;
}
