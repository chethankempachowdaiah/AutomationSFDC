package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 09/jan/2020
 * 
 *         SFDC Create Busniess Account
 *
 */
public class SFDC_CreateBusinessAccount_Objects {

	public SFDC_CreateBusinessAccount_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({	
		@FindBy(how = How.XPATH, using = "//input[@id='CBA_EnterAccountName']"),
		@FindBy(how = How.XPATH, using = "//label[contains(text(),'Account Name')]/parent::div/parent::div/child::input")})
		public WebElement accountNameInput;

	@FindAll({	
		@FindBy(how = How.XPATH, using = "//input[@id='CBA_EnterTradeName']"),
		@FindBy(how = How.XPATH, using = "//label[contains(text(),'Legal Name')]/parent::div/parent::div/child::input")})
		public WebElement legalNameInput;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//select[@id='CBA_EnterVerticalGroup']"),
	@FindBy(how = How.XPATH, using = "//label[text()='Industry']/parent::div/parent::div")})
	public WebElement verticalGroupSelect;
			
	@FindBy(how = How.XPATH, using = "//label[text()='Country']/parent::div/parent::div")
	public WebElement countryDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Financial Services']")
	public WebElement financialServicesOption;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Banking']")
	public WebElement bankingOption;
	
	@FindBy(how = How.XPATH, using = "//div[@data-label='Canada']")
	public WebElement countryCanadaOption;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Industry']/parent::div/parent::div/parent::div/parent::div/parent::div")
	public WebElement industryClick;  
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//select[@id='CBA_EnterVertical']"),
	@FindBy(how = How.XPATH, using = "//label[text()='Sub-Industry']/parent::div/parent::div")})
	public WebElement verticalSelect;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Sub-Industry']/parent::div/parent::div/parent::div/parent::div/parent::div")
	public WebElement subIndustryClick;
	
	@FindBy(how = How.XPATH, using = "//div[@data-value='Financial Services']")
	public WebElement selectFinancialSer;
	
	@FindBy(how = How.XPATH, using = "//div[@data-value='Banking']")
	public WebElement selectBanking;

	@FindBy(how = How.XPATH, using = "//label[@for='CBA_EnterVerticalGroup']//span")
	public WebElement industryLabel;

	@FindBy(how = How.XPATH, using = "//label[@for='CBA_EnterVertical']//span")
	public WebElement subIndustryLabel;

	@FindAll({	
	@FindBy(how = How.XPATH, using = "//input[@id='UA_SelectAddress']"),
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Address')]/parent::div/parent::div/child::input")})
	public WebElement busAddressInput;
	
	@FindAll({	
	@FindBy(how = How.XPATH, using = "//input[@id=\"UA_SelectAddress\"]/parent::div/child::div/child::div"),
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Address')]/parent::div/parent::div/following::div/child::div/child::ul")})
	public WebElement busAddressInputclick;
	
	@FindAll({	
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_EnterPhone']"),
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Phone')]/parent::div/parent::div/child::input")})
	public WebElement enterPhoneInput;

	@FindAll({	
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_EnterEmployeeSize']"),
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Number of Employees')]/parent::div/parent::div/child::input")})
	public WebElement employeeSizeInput;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//select[@id='CBA_CountrySelect']"),
	@FindBy(how = How.XPATH, using = "//label[text()='Country']/parent::div/parent::div")})
	public WebElement countrySelect;
	
	@FindBy(how = How.XPATH, using = "//div[@data-value='Canada']")
	public WebElement selectCanada;
		
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Unit')]/parent::div/parent::div")
	public WebElement unitBox;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Country']/parent::div/parent::div/parent::div/parent::div/parent::div")
	public WebElement countryClick;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_ChooseParentAccount']")
	public WebElement chooseParentAccountInput;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SelectAddress']"),
	@FindBy(how = How.XPATH, using ="//label[text()='Select Address']/parent::div/parent::div/input")})
	public WebElement billingAddressInput;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Address')]/parent::div/parent::div/following::div/child::div/child::ul")
	public WebElement businessAddress;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Duplicate accounts found with')]")
	public WebElement dupCheck;

	@FindAll({	
	@FindBy(how = How.XPATH, using = "//span[@class='nds-checkbox_faux']"),
	@FindBy(how = How.XPATH, using = "//input[@id='CSR_OverrideAddress']"),
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox']"),
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_OverrideAddress']")})
	public WebElement canotFindAddressCheckbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='UA_OverrideAddress']")
	public List<WebElement> cannotFindAddressCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_Unit']")
	public WebElement billingAddressUnit;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_BillingStreet']"),
	@FindBy(how = How.XPATH, using = "//input[@id='UA_BillingStreet']")})
	public WebElement billingAddressstreet;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Street No.']/parent::div/parent::div/child::input")
	public WebElement billingAddressStreetNumber;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Street Name']/parent::div/parent::div/child::input")
	public WebElement billingAddressStreetName;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Street Type']/parent::div/parent::div/child::input")
	public WebElement billingAddressStreetType;

	@FindBy(how = How.XPATH, using = "//div[@data-label='ON']")
	public WebElement billingProvinceON;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//select[@id='CBA_BillingState']"),
	@FindBy(how = How.XPATH, using = "//select[@id='UA_BillingState']"),
	@FindBy(how = How.XPATH, using = "//label[text()='Province']/parent::div/parent::div")})
	public WebElement billingAddressProvince;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_BillingCity']"),
	@FindBy(how = How.XPATH, using = "//input[@id='UA_BillingCity']"),
	@FindBy(how = How.XPATH, using = "//label[text()='City']/parent::div/parent::div/child::input")})
	public WebElement billingAddressCity;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_BillingPostalCode']"),
	@FindBy(how = How.XPATH, using = "//input[@id='UA_BillingPostalCode']"),
	@FindBy(how = How.XPATH, using = "//label[text()='Postal Code']/parent::div/parent::div/child::input")})
	public WebElement billingAddressPostalCode;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='CBA_BillingPostalCode']//input")
	public WebElement billingAddressPostalCodeRevised;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_BillingCountry']"),
	@FindBy(how = How.XPATH, using = "//input[@id='UA_BillingCountry']")})
	public WebElement billingAddressCountry;
	
	@FindBy(how = How.XPATH, using = "(//label[@role='button'])[2]")
	public WebElement attachmentsIcon;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Address')]/parent::div/following-sibling::div//div")
	public List<WebElement> billingAddressIcon;
	
	@FindBy(how = How.XPATH, using = "//input[@type='file']")
	public WebElement uploadDocument;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Continue to next step']")
	public WebElement continueToNextStep;

	@FindBy(how = How.XPATH, using = "//div[@ng-if='showTypeaheadOptions']//div")
	public List<WebElement> billingAddressTypeAheadOption;

	@FindBy(how = How.XPATH, using = "//span[@class='slds-radio--faux ng-scope']/following-sibling::span[contains(.,'No, I would like to create a separate service address')]")
	public WebElement separateServiceAddressradio;

	@FindBy(how = How.XPATH, using = "//div[@id='CaptureBusinessAccountDetails_nextBtn']")
	public WebElement captureBusinessAccountDetails_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'No matches were found, a manual review will be required.')]")
	public WebElement escMsg;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//*[@id='CBA_NoDuplicateAccountMsg']//strong"),
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No potential duplicates found for the account')]")})
	public  List<WebElement> noDuplicateAccountMsg;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//*[@id='CBA_AccountDuplicateMessage']//strong"),
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Duplicate accounts found')]")})
	public List<WebElement> duplicateAccountMsg;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No potential duplicates found for the account')]//span")
	public WebElement businessAccountValue;

	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'No matches were found, a manual review will be required. Select')]")
	public List<WebElement> accountValidationMsg;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Account Name')]//ancestor::thead//following-sibling::tbody[@class='ins']//tr//td[1]//input")
	public List<WebElement> duplicateTableAccountSelectCheckboxAllRows;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Account Name')]//ancestor::thead//following-sibling::tbody[@class='ins']//tr//td[2]")
	public List<WebElement> duplicateTableAccountNameAllRows;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Account Name')]//ancestor::thead//following-sibling::tbody[@class='ins']//tr//td[4]")
	public List<WebElement> duplicateTableMatchingPerAllRows;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SelectNextAction']//following-sibling::span[contains(.,'child within the selected account')]")
	public WebElement selectNextActionChildWithinAccountRadio;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SelectNextAction']//following-sibling::span[contains(.,'I would like to continue creating a new account')]"),
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='CNA']")})
	public WebElement selectNextActionCreateNewAccountRadio;

	@FindBy(how = How.XPATH, using = "//div[@id='CBA_NoDuplicates_nextBtn']")
	public WebElement noDuplicates_nextBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='CBA_AccountDuplicate_nextBtn']")
	public WebElement accountDuplicates_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'CBA_ReviewService')]//p[contains(text(),'Next')]")
	public WebElement nextBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='CBA_ReviewServiceAddressWithSameBusinessAddress_nextBtn']")
	public WebElement reviewServiceAddress_nextBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='CBA_ReviewServiceAddressWithSameBusinessAddress']/section/div/div/div/h1")
	public WebElement reviewServiceAddressHeaderText;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SSAStreet']")
	public WebElement reviewServiceAddress_StreetText;

	@FindBy(how = How.XPATH, using = "//select[@id='CBA_SSAStateCanada']")
	public WebElement reviewServiceAddress_ProvinceText;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SSACity']")
	public WebElement reviewServiceAddress_ProvinceCity;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SSAPostalCodeCanada']")
	public WebElement reviewServiceAddress_PostalCode;

	@FindBy(how = How.XPATH, using = "//input[@id='CBA_SSACountry']")
	public WebElement reviewServiceAddress_Country;

	@FindBy(how = How.XPATH, using = "//span[@class='slds-radio--faux ng-scope']/following-sibling::span[contains(.,'No, I would like to create a separate service address')]")
	public WebElement sameServiceAddressradio;

	@FindBy(how = How.XPATH, using = "//select[@id='CBA_CountrySelect']//option[@label='United States']")
	public WebElement countrySelectUnitedStates;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//div[@id='ESC_NoMatchesFound_nextBtn']"),
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'No matches were found')]")})
	public WebElement eSC_NoMatchesFound_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@data-value='Manufacturing & Resources']")
	public WebElement selectManufactResources;
	
	@FindBy(how = How.XPATH, using = "//div[@data-value='Manufacturing']")
	public WebElement selectManufacturing;
	
	@FindBy(how = How.XPATH, using = "//div[.='External Validation']")
	public WebElement externalValidation;
	
	@FindBy(how = How.XPATH, using = "//div[.='Legal name will be updated to selected item']")
	public WebElement legalNameMsg;
	
	@FindBy(how = How.XPATH, using = "//span[@class='nds-radio_faux']")
	public WebElement escOptions;
	
	@FindBy(how = How.XPATH, using = "//div[.='Duplicate account check 2']")
	public WebElement dupChkMsg2;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Legal Name']")
	public WebElement legalName;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Type']")
	public WebElement escType;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Status']")
	public WebElement escStatus;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Confidence Score']")
	public WebElement escScore;
	
	@FindBy(how = How.XPATH, using = "//lightning-formatted-text[.='Active']")
	public WebElement accStatus;
	
	@FindBy(how = How.XPATH, using = "//lightning-formatted-text[.='TARGET']")
	public WebElement legalNameValue;
	

}
