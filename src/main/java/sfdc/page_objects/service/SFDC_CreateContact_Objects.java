package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 10/jan/2020
 * 
 *         SFDC Create Contact
 *
 */
public class SFDC_CreateContact_Objects {

	public SFDC_CreateContact_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'AccountType')]//following-sibling::span[contains(.,'Service')]")
	public WebElement siteContactRadio;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'AccountType')]//following-sibling::span[contains(.,'Business')]")
	public WebElement businessContactRadio;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'AccountType')]//following-sibling::span[contains(.,'Service')]")
	public WebElement serviceContactRadio;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_SelectAccount']")
	public WebElement contactSelectAccountInput;

	@FindBy(how = How.XPATH, using = "//select[@id='CC_Salutation']")
	public List<WebElement> contactSalutationDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@role='listbox']//ul[@role='presentation']//li//div//span//span")
	public List<WebElement> salutationOption;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Salutation')]/ancestor::div[@role='none']/child::input")
	public WebElement salutation;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Salutation')]/parent::div/parent::div")
	public WebElement salutationsDropdown;

	@FindBy(how = How.XPATH, using = "//div[@role='combobox']")
	public WebElement Salutation;

	@FindBy(how = How.XPATH, using = "//select[@id='CC_Salutation']")
	public WebElement contactSalutationNewDropdown;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_Title']")
	public WebElement contactTitleInput;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Title')]/parent::div/parent::div//input[contains(@id,'input')]")
	public WebElement TitleInput;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CC_FirstName']"),
	@FindBy(how = How.XPATH, using = "//input[@id='CPC_FirstName']")})
	public WebElement contactFirstNameInput;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'First Name')]/parent::div/parent::div//input[contains(@id,'input')]")
	public WebElement FirstNameInput;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CC_LastName']"),
	@FindBy(how = How.XPATH, using = "//input[@id='CPC_LastName']")})
	public WebElement contactLastNameInput;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Last Name')]/parent::div/parent::div//input[contains(@id,'input')]")
	public WebElement LastNameInput;
	
	@FindBy(how = How.XPATH, using = "(//button[@type='button' and contains(@title,'Close')])[2]")
	public WebElement createContactClose;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CC_EmailAddress']"),
	@FindBy(how = How.XPATH, using = "//input[@id='CPC_Email']")})
	public WebElement contactEmailAddressInput;
	
	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	public WebElement EmailAddressInput;

    @FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CC_Phone']"),
    @FindBy(how = How.XPATH, using = "//input[@id='CPC_Phone']")})
	public WebElement contactPhoneInput;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Phone')]/parent::div/parent::div//input[contains(@id,'input')]")
	public WebElement phoneInput;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'CC_RelationshipType')]")
	public WebElement contactRelationshipTypeDropdown;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'RelationshipType')]//following-sibling::span[contains(.,'General')]")
	public WebElement contactRelationshipTypeGeneral;

	@FindBy(how = How.XPATH, using = "//select[@id='CC_LanguagePreference']")
	public WebElement languageDropdown;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'English')]/ancestor::span/child::input")
	public WebElement languageRadioButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'English')]/preceding::span[@class='nds-radio_faux']")
	public WebElement languageButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'English')]")
	public WebElement englishLanguage;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//button[contains(@title,'next step')]"),
	@FindBy(how = How.XPATH, using = "//button[contains(@title,'Next Step')]")})
	public WebElement nextStep;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Or drop files')]/parent::label//span[1]/parent::label")
	public WebElement uploadFiles;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Proceed with Manual process')]/preceding::span[@class='nds-radio_faux']")
	public WebElement buttonManualProcess;
	
	@FindBy(how = How.XPATH, using = "(//span[@class='nds-radio_faux'])[2]")
	public WebElement SAContactRbtn;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a")
	public List<WebElement> recentlyViewedContacts;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Name')]/following::lightning-formatted-name")
	public WebElement contactName;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Search Account')]/ancestor::div/child::input")
	public WebElement searchAccount;

	@FindBy(how = How.XPATH, using = "//input[@name='Custom']/preceding::span[@data-select='Select']")
	public WebElement accessLevelBusiness;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Custom']/following::span[@data-select='Select']")
	public WebElement accessLevelCustom;
		
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Showing table for Accounts in:')]/following::div[@class='nds-text-heading--small']")
	public WebElement parentBusinessAccount;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Billing']")
	public WebElement billingAccountField;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Active']")
	public WebElement activeStatusField;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Account Name']")
	public WebElement accountNameField;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-data-table-body']//c-svc-c-c-account-navigate-cell//div[@class='text']")
	public WebElement accountNameValue;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Show More']")
	public WebElement showMoreButton;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Show More']/following-sibling::div//ul//li//a//span")
	public List<WebElement> assignRoleOption;
		
	@FindBy(how = How.XPATH, using = "//button[text()='Proceed']")
	public WebElement proceedButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Filter']/parent::button")
	public WebElement filterButton;
	
	@FindBy(how = How.XPATH, using = "//input[@name='accounttype' and @value='Service']")
	public WebElement serviceAccountRadioButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Apply']/parent::button")
	public WebElement applyButton;
	
	@FindBy(how = How.XPATH, using = "//input[@name='accountstatus' and @value='Active']")
	public WebElement activeRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Address']")
	public WebElement addressField;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Role']")
	public WebElement roleField;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Account Number']")
	public WebElement accountNumberField;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='Signing Authority']"),
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Signing Authority')]/preceding-sibling::span")})
	public WebElement buttonSigningAuthority;
		
	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='Administrator']"),
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox' and @value='Administrator']"),
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Administrator')]/preceding-sibling::span")})
	public WebElement checkboxAdministrator;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'None')]/preceding-sibling::span")
	public WebElement buttonNone;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Endorser')]/preceding-sibling::span")
	public WebElement roleEndorser;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Assessor')]/preceding-sibling::span")
	public WebElement roleAssessor;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Decider')]/preceding-sibling::span")
	public WebElement roleDecider;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Coach/Influencer')]/preceding-sibling::span")
	public WebElement roleCoachInfluencer;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[contains(@id,'CC_PrimaryContact')]"),
	@FindBy(how = How.XPATH, using = "//span[text()='Mark As Primary Contact']/ancestor::label/parent::div//input")})
	public WebElement primaryContactCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MarkAsSiteContact']//following-sibling::span")
	public WebElement siteContactCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@value='Y']//following-sibling::span[contains(.,'Yes')]")
	public WebElement updateExistingContactYesRadio;

	@FindBy(how = How.XPATH, using = "//input[@value='N']//following-sibling::span[contains(.,'No')]")
	public WebElement updateExistingContactNoRadio;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Address')]/ancestor::div/input"),
	@FindBy(how = How.XPATH, using = "//input[@id='CC_SelectMailingAddress']"),
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing Address')]/ancestor::div/child::input"),
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Mailing Address')]/ancestor::vlocity_cmt-omniscript-text-block/following-sibling::vlocity_cmt-omniscript-typeahead-block//input")})
	public WebElement contactMailingAddress;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_OverrideAddress']")
	public WebElement cannotFindAddressCheckbox;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing Unit')]/preceding::input[@type='checkbox']")
	public WebElement cannotFindAddress;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MailingUnit']")
	public WebElement mailingAddressUnit;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MailingStreet']")
	public WebElement mailingAddressstreet;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing Street')]/ancestor::div/child::input[contains(@id,'input')]")
	public WebElement mailingstreet;

	@FindBy(how = How.XPATH, using = "//select[@id='CC_MailingState']")
	public WebElement mailingAddressProvince;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing Country')]/following::div[@role='none']")
	public WebElement dropDownMailingProvince;
	
	@FindBy(how = How.XPATH, using = "//div[@role='listbox']/child::ul/child::li//div")
	public List<WebElement> mailingProvince;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MailingCity']")
	public WebElement mailingAddressCity;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing City')]/ancestor::div/child::input[contains(@id,'input')]")
	public WebElement mailingCity;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MailingPostalCode']")
	public WebElement mailingAddressPostalCode;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing Postal Code')]/ancestor::div/child::input[contains(@id,'input')]")
	public WebElement mailingPostalCode;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MailingCountry']")
	public WebElement mailingAddressCountry;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Mailing Country')]/ancestor::div/child::input[contains(@id,'input')]")
	public WebElement mailingCountry;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Contact Phone Number')]/ancestor::div/child::input[contains(@id,'input')]")
	public WebElement contactNumber;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@value='Internet of Things']"),
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Internet of Things')]/ancestor::span/child::input[contains(@type,'checkbox')]")})
	public WebElement internetOfThingsOption;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No Duplicates Found')]"),
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Duplicate contacts found')]")})
	public WebElement noDuplicatesFound;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='Match %']/ancestor::div/following-sibling::div//c-dgtl-generic-cell//div)[5]")
	public WebElement matchingField;
		
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Signing Authority role')]")
	public WebElement signingAuthorityMessage;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='showTypeaheadOptions']//div")
	public WebElement mailingAddressTypeAheadOption;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_MailingStreet']")
	public WebElement contactMailingStreetInput;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//div[@id='CC_ContactDetails_nextBtn']"),
	@FindBy(how = How.XPATH, using = "//div[@id='CPC_PrimaryContactDetails_nextBtn']")})
	public WebElement contactDetails_nextBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_RadioContactActionBusiness']//following-sibling::span[contains(.,'Create a new contact')]")
	public WebElement selectNextActionCreateNewContactRadio;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CC_RadioContactActionBusiness']//following-sibling::span[contains(.,'Update the contact')]"),
	@FindBy(how = How.XPATH, using = "//input[@value='UpdateSelected']")})
	public WebElement selectNextActionUpdateExistingContactRadio;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_CreateNextContact_nextBtn']/p")
	public WebElement validateContact_NextBtnNoDuplicates;

	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")
	public WebElement alertOkButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Continue')]")
	public WebElement continueButton;

	@FindBy(how = How.XPATH, using = "//p//p[contains(.,'potential matches found for')]")
	public WebElement contactDuplicateMessage;

	@FindBy(how = How.XPATH, using = "//label[@for='CC_DuplicateContacts']//following-sibling::ng-include//table//input[@type='checkbox']//following-sibling::span")
	public List<WebElement> duplicateContactCheckboxAll;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@name='options']/parent::span/child::label//span"),
	@FindBy(xpath = "//input[@id='CC_RadioContactActionBusiness1']//following-sibling::span[contains(.,'Update the contact')]"),
	@FindBy(xpath = "//span[contains(text(),'Update the contact with modified fields and create account contact relationship If one does not exist. Inactive contact will be activated')]/preceding-sibling::span") })
	public WebElement updateContactRadio;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_ValidateContact_nextBtn']")
	public WebElement validateContact_nextBtn;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Create Another Contact')]")
	public WebElement createAnotherContactButton;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_CreateNextContact_nextBtn']/p")
	public WebElement reviewAnotherContact_NextBtn;

	@FindBy(how = How.XPATH, using = "//iframe[contains(@name,'vfFrameId')]")
	public WebElement reviewContact_Frame;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_ReviewContact']//a")
	public WebElement reviewContactNameText;

	@FindBy(xpath = "//*[@id='CC_ReviewContact']//a//following::p[contains(text(),'Manage Relationships')]")
	public WebElement manageRelationshipsText;

	@FindBy(how = How.CSS, using = "div[id='CC_CreateNextContact_nextBtn']")
	public WebElement createNextContact_nextBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='TASearchAccount']")
	public WebElement contactSearchAccountInput;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_SearchAccount_nextBtn']")
	public WebElement cc_SearchAccount_nextBtn;

	@FindAll({ @FindBy(xpath = "//h5[contains(.,'Contact Name:')]//a"),
			@FindBy(xpath = "//p[contains(.,'Contact Name:')]//a") })
	public WebElement contactDetailsContactNameLink;

	@FindBy(how = How.XPATH, using = "//h5[contains(.,'Account Name:')]//a")
	public WebElement contactDetailsAccountNameLink;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_ReviewContact_nextBtn']")
	public WebElement reviewContactNextButton;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_SCCreateNewContact']")
	public WebElement searchContactCreateNewContactCheckbox;

	@FindBy(how = How.XPATH, using = "//tbody/tr")
	public List<WebElement> potentialMatchContacts;

	@FindBy(how = How.XPATH, using = "//tbody/tr/td/label/span")
	public List<WebElement> potentialMatchCheckBoxContacts;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr/td[2]")
	public List<WebElement> potentialMatchContactName;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr/td[7]")
	public List<WebElement> potentialMatchContactStatusText;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_DuplicateContacts']/div/ng-include/div/table/tbody/tr/td[8]")
	public List<WebElement> potentialMatchContactRecordTypeText;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_DuplicateContacts']/div/ng-include/div/table/tbody/tr/td[3]")
	public List<WebElement> potentialMatchContactMatchPercent;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_DuplicateContacts']/div/ng-include/div/table/tbody/tr/td[4]")
	public List<WebElement> potentialMatchContactEmail;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_DuplicateContacts']/div/ng-include/div/table/tbody/tr/td[6]")
	public List<WebElement> potentialMatchContactAccountName;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_DuplicateContacts']/div/ng-include/div/table/tbody/tr/td[2]")
	public List<WebElement> potentialMatchDuplicateContactName;

	@FindBy(how = How.XPATH, using = "//tbody/tr/td/div[@class='slds-truncate']")
	public List<WebElement> potentialMatchContactsAccountName;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_CreateNextContact_nextBtn']/p[text()='Next']")
	public WebElement nextClickAfterContact;

	@FindBy(how = How.XPATH, using = "//p[text()='Account Name']/parent::div//following::p//a/span")
	public WebElement conatctDetailsAccountNameText;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_ASRelationshipTypeBusiness|0']/div/div[1]/ul/li/label/span[2]")
	public List<WebElement> businessAccountCombineRoles;

	@FindBy(how = How.XPATH, using = "//div[@title='Create Another Contact']/p[text()='Create Another Contact']")
	public WebElement createNewContact;

	@FindBy(how = How.XPATH, using = "//*[@id='CC_ASRelationshipTypeBusiness|0']/div/div[1]/ul/li/label/span[1]")
	public List<WebElement> businessAccountCombineCheckBox;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_ValidateContact_nextBtn']/p")
	public WebElement validateNewContact_nextBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='CC_CreateNextContact_nextBtn']/p")
	public WebElement createContact_nextBtn;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Update the contact with modified fields and create account contact relationship If one does not exist. Inactive contact will be activated')]/preceding-sibling::span")
	public WebElement updateContactRadio_WithCreateNewContact;
	
	@FindBy(how = How.XPATH, using = "//div[@id='CA_ConfirmatioinStepOfSA_nextBtn']//p[.='Next']")
	public WebElement withoutBillingAcc_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Contacts')]")
	public WebElement allContactsOption;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No duplicate contacts found. Please Continue to')]")
	public WebElement uniqueContact;


}
