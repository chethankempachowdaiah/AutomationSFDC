package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_ReviewOrderDetailsComplete_Objects {

	public SFDC_ReviewOrderDetailsComplete_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),' General Information')]")
	public WebElement generalInformationHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Order Number')]/parent::div//following::span/a")
	public WebElement orderDetailsText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Order Status')]/following-sibling::span")
	public WebElement orderStatusText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Business Name')]/following-sibling::span")
	public WebElement businessNameText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service Address')]/following-sibling::span")
	public WebElement serviceAddressText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Delivery Specialist')]/following-sibling::span")
	public WebElement deliverySpecialistText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Account Executive')]/following-sibling::span")
	public WebElement accountExecutiveText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Address Contract Type')]/following-sibling::span")
	public WebElement addressContractTypeText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Additonal Information')]")
	public WebElement additionalInformationText;
	
//	@FindAll({ @FindBy(how = How.XPATH, using = "//span[text()='Order Type']//following-sibling::span"),
//		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Order Type')]//following-sibling::span") })
//	public WebElement orderTypeText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Order Type')]//following-sibling::span")
	public WebElement orderTypeText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Order Type')]")
	public WebElement billingAccountText;

	@FindBy(how = How.XPATH, using = "//span[contains(@title,'Trade Name')]")
	public WebElement tradeNameText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sales Segment')]/following-sibling::span")
	public WebElement salesSegmentText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sales Region')]")
	public WebElement salesRegion;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sales Contact Email')]/following-sibling::span")
	public WebElement salesContactEmailText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sales Contact Phone')]/following-sibling::span")
	public WebElement salesContactPhoneValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Site Name')]/following-sibling::span")
	public WebElement siteNameText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Credit Limit Approved')]/parent::div/div/label/span/following-sibling::span")
	public WebElement creditLimitApprovedValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Hours of Operation :')]/following-sibling::span")
	public WebElement hoursOfOperationValue;

	@FindBy(how = How.XPATH, using = "//div[@class='slds-checkbox']/input[@type='checkbox']")
	public WebElement reviewNotRequiredCheckBox;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Authorized User Name')]/following-sibling::span")
	public WebElement authorizedUserNameText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Authorized User Phone')]/following-sibling::span")
	public WebElement authorizedContactText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Authorized User Email')]/following-sibling::span")
	public WebElement authorizedUserEmaiText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Authorized User Language')]/following-sibling::span")
	public WebElement authorizedUserLanguageText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Site Contact Name')]/following-sibling::span")
	public WebElement siteContactNameText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Site Contact Phone')]/following-sibling::span")
	public WebElement siteContactPhoneText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Site Contact Email')]/following-sibling::span")
	public WebElement siteContactEmailText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Site Contact Language')]/following-sibling::span")
	public WebElement siteContactLanguageText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sales Contact Name')]/following-sibling::span")
	public WebElement salesContactNameText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Product Information')]")
	public WebElement productInformationHeaderText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Circuit Details')]")
	public WebElement circuitDetailsHeaderText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IBLc Product Information')]")
	public WebElement iblcProductInformationText;
	
//	@FindAll({ @FindBy(xpath = "(//span[contains(text(),'Service Details')]//following::span[text()='Name']/following-sibling::span)[1]"),
//		@FindBy(xpath = "(//span[contains(text(),'Service Details')]//following::span[contains(text(),'Name')]/following-sibling::span)[1]")})
//	public WebElement productDetailsNameText;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Service Details')]//following::span[contains(text(),'Name')]/following-sibling::span)[1]")
	public WebElement productDetailsNameText;
	
//	@FindAll({ @FindBy(xpath = "//span[contains(text(),'Circuit Details')]//following::span[text()='Name']/following-sibling::span"),
//		@FindBy(xpath = "//span[contains(text(),'Circuit Details')]//following::span[contains(text(),'Name')]/following-sibling::span")})
//	public WebElement cirDetailsNameText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Circuit Details')]//following::span[contains(text(),'Name')]/following-sibling::span")
	public WebElement cirDetailsNameText;

	@FindAll({ @FindBy(xpath = "//span[text()='Speed']/following-sibling::span"),
		@FindBy(xpath = "(//span[contains(text(),'Speed')]/following-sibling::span)[1]")})
	public WebElement productSpeedDetailsText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Speed')]/following-sibling::span")
	public List<WebElement> productSpeedValue;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Access Speed']/following-sibling::span")
	public WebElement accessSpeedValue;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Service Billing Details']")
	public WebElement serviceBillingDetailsText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service MRR')]/following-sibling::span")
	public WebElement serviceMRRPrice;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access MRR')]/following-sibling::span")
	public WebElement accessMRRPrice;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service NRR')]/following-sibling::span")
	public WebElement serviceNRRPrice;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access NRR')]/following-sibling::span")
	public WebElement accessNRRPrice;
	
//	@FindAll({ @FindBy(xpath = "//span[text()='Access Type']/following-sibling::span"),
//		@FindBy(xpath = "//span[contains(text(),'Access Type')]/following-sibling::span")})
//	public WebElement accessTypeValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access Type')]/following-sibling::span")
	public WebElement accessTypeValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Contract Term')]/following-sibling::span")
	public WebElement contractDetailsText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Term Details')]/following-sibling::span")
	public WebElement termDetailsText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Validate Status')]")
	public WebElement validateStatusHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Validate Status')]//following::span[contains(text(),'Cancel')]")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//label[@class='slds-checkbox_faux']")
	public WebElement vaidateOrderStatusCheckBox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Send E')]")
	public WebElement sendEmailButton;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Please send e-mail before completing the task')]")
	public WebElement validateSendButtonCompleteMsg;
	
//	@FindBy(how = How.XPATH, using = "//div[@class='windowViewMode-maximized active lafPageHost']//following::iframe[@title='accessibility title']")
//	public WebElement taskFrame;
	
	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public List<WebElement> taskFrame;
	
	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")
	public WebElement vaidateOrderStatusCheckBoxError;

	// For Create/Verfy Billing Account xpaths
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Create/Verify Billing Account')]")
	public WebElement billingHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Type of Business')]/following-sibling::span")
	public WebElement typeOfBusinessText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Demarcation Location')]/following-sibling::span")
	public WebElement demarcationLocationText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Demarcation Location')]/following-sibling::span")
	public List<WebElement> demarcationLocationTexts;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Number of Employees')]/following-sibling::span")
	public WebElement noOfEmpText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Premises Information')]/following-sibling::span")
	public WebElement premisesInformationText;

//	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Credit Limit Approved')]/parent::div/div/label/span/following-sibling::span")
//	public WebElement creditApprovedText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Contract Term')]/following-sibling::span")
	public WebElement contractTermText;

//	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Name : ')]/following-sibling::span[@title='Authorized User Name']")
//	public List<WebElement> signingAuthorityContactNameText; // Use it for signing, site, billing, technical contact
																// name
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Authorized User Name')]//following-sibling::span")
	public List<WebElement> signingAuthorityContactNameText; 
	
	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Phone')]/following-sibling::span[contains(@title,'Authorized')]")
	public List<WebElement> signingAuthorityPhoneText;

	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Email')]/following-sibling::span[contains(@title,'Authorized')]")
	public List<WebElement> signingAuthorityEmailText;

	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Language')]/following-sibling::span[contains(@title,'Authorized')]")
	public List<WebElement> signingAuthorityLanguagePrefText;
	
	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Name')]/following-sibling::span[contains(@title,'Billing')]")
	public List<WebElement> billingContactNameText; // Use it for signing, site, billing, technical contact
																// name
	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Phone')]/following-sibling::span[contains(@title,'Billing')]")
	public List<WebElement> billingContactPhoneText;

	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Email')]/following-sibling::span[contains(@title,'Billing')]")
	public List<WebElement> billingContactEmailText;

	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Language')]/following-sibling::span[contains(@title,'Billing')]")
	public List<WebElement> billingLanguagePrefText;
	
	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Name : ')]/following-sibling::span[contains(@title,'Technical')]")
	public List<WebElement> technicalContactNameText; // Use it for signing, site, billing, technical contact
																// name
	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Phone')]/following-sibling::span[contains(@title,'Technical')]")
	public List<WebElement> technicalContactPhoneText;

	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Email')]/following-sibling::span[contains(@title,'Technical')]")
	public List<WebElement> technicalContactEmailText;

	@FindBy(how = How.XPATH, using = "//slot/div/span[contains(text(),'Language')]/following-sibling::span[contains(@title,'Technical')]")
	public List<WebElement> technicalLanguagePrefText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Information')]")
	public WebElement billingInformationHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Enter Billing Address')]/parent::label/parent::div/following-sibling::div/input")
	public WebElement billingAddressEnter;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Years Of Business')]/parent::label/parent::div/following-sibling::div/input")
	public WebElement billingAddressEnterYearBusiness;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Account Number')]/parent::label/parent::div/following-sibling::div/input")
	public WebElement billingAccountNumberEnter;
	
	@FindBy(how = How.XPATH, using = "//label[@class='slds-checkbox_faux']")
	public WebElement validateStatusCheckBoxClick;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Account Number')]/parent::label/parent::div/following-sibling::div[contains(text(),'Account Number Should be 6 Digit')]")
	public WebElement accountNoSixDigitErrorText;

	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Order is valid. Proceed with Completing the Task')]")
	public WebElement successMessageText;
	
	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Order cancellation is in progress')]")
	public WebElement orderCancellationMessageText;
	
	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Order is Frozen')]")
	public WebElement orderFrozenMessageText;
	
	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Order is now cancelled')]")
	public WebElement orderCanceledMessageText;

	// review Spec Sheet

	//@FindBy(how = How.XPATH, using = "//label[@aria-label='Demarcation Location']/parent::div/following-sibling::div/input")
	//public WebElement demarcationLocTextInSpecSheet;
	@FindBy(how = How.XPATH, using = "//input[@id='input1-113']")
	public WebElement demarcationLocTextInSpecSheet;
	
	//@FindBy(how = How.XPATH, using = "//label[@aria-label='Circuit Type']/parent::div/following-sibling::div/input")
	//public WebElement circuitTypeTextInSpecSheet;
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='CircuitType']/slot[1]/c-input[1]/div[1]/div[2]")
	public WebElement circuitTypeTextInSpecSheet;
	
	//@FindBy(how = How.XPATH, using = "//label[@aria-label='Access Type']/parent::div/following-sibling::div/input")
	//public WebElement accessTypeTextInSpecSheet;
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='AccessType']/slot[1]/c-input[1]/div[1]/div[2]")
	public WebElement accessTypeTextInSpecSheet;
	
	//@FindBy(how = How.XPATH, using = "//label[@aria-label='Service Provider']/parent::div/following-sibling::div/input")
	//public WebElement serviceProviderTextInSpecSheet;
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='ServiceProvider']/slot[1]/c-input[1]/div[1]/div[2]")
	public WebElement serviceProviderTextInSpecSheet;

	//@FindBy(how = How.XPATH, using = "//label[@aria-label='Access Provider']/parent::div/following-sibling::div/input")
	//public WebElement accessProviderTextInSpecSheet;
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='AccessProvider']/slot[1]/c-input[1]/div[1]/div[2]")
	public WebElement accessProviderTextInSpecSheet;

	@FindBy(how = How.XPATH, using = "//span[text()='Handoff Interface']/ancestor::label/parent::div/following-sibling::div//input")
	public WebElement handOffInterfaceDropDown;

	//@FindBy(how = How.XPATH, using = "//span[text()='Order Type']/parent::label/parent::div/following-sibling::div//input")
	//public WebElement orderTypeInSpecText;
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='OrderType']/slot[1]/c-input[1]/div[1]/div[2]")
	public WebElement orderTypeInSpecText;

	@FindBy(how = How.XPATH, using = "//span[text()='Port Type']/parent::label/parent::div/following-sibling::div//input")
	public WebElement portTypeInSpecText;

	@FindBy(how = How.XPATH, using = "//span[text()='Port Speed']/parent::label/parent::div/following-sibling::div//input")
	public WebElement portSpeedDropDown;

	@FindBy(how = How.XPATH, using = "//span[text()='Encapsulation Type']/ancestor::label/parent::div/following-sibling::div//input")
	public WebElement encapsulationTypeDropDown;

	@FindBy(how = How.XPATH, using = "//label[text()='VLAN ID']/parent::div/following-sibling::div/textarea")
	public WebElement vLanIdValueInSpecSheet;

	//@FindBy(how = How.XPATH, using = "//span[text()='Service Speed']/ancestor::label/parent::div/following-sibling::div//input")
	//public WebElement serviceSpeedTextInSpec;
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key='ServiceSpeed']/slot[1]/c-input[1]/div[1]/div[2]")
	public WebElement serviceSpeedTextInSpec;

	@FindBy(how = How.XPATH, using = "//textarea[@id='PortableIPHolder']")
	public WebElement portableIpBoxValue;

	@FindBy(how = How.XPATH, using = "//input[@value='No'][@id='IPExists']/parent::label/span[contains(@class,'slds-radio')]")
	public WebElement portableIPRadioButton;

	@FindBy(how = How.XPATH, using = "//input[@id='NewIPAllocation'][@value='No']/parent::label/span[contains(@class,'slds-radio')]")
	public WebElement newIPBlocksRadioButton;

	@FindBy(how = How.XPATH, using = "//*[text()='IP Version']/parent::label/parent::div/following-sibling::div/div/div/input")
	public WebElement ipVersionDrpDownInSpecSheet;

	@FindBy(how = How.XPATH, using = "//*[text()='IPV4 WAN Block']/parent::label/parent::div/following-sibling::div/div/div/input")
	public WebElement ipV4WAnBlockSpecSheet;

	@FindBy(how = How.XPATH, using = "//*[text()='IPV4 LAN Block']/parent::label/parent::div/following-sibling::div/div/div/input")
	public WebElement ipV4LAnBlockSpecSheet;
	
	@FindBy(how = How.XPATH, using = "//*[text()='IPV6 WAN Block']/parent::label/parent::div/following-sibling::div/div/div/input")
	public WebElement ipV6WAnBlockSpecSheet;

	@FindBy(how = How.XPATH, using = "//*[text()='IPV6 LAN Block']/parent::label/parent::div/following-sibling::div/div/div/input")
	public WebElement ipV6LAnBlockSpecSheet;

//	@FindBy(how = How.XPATH, using = "//input[@id='DNS'][@value='No']/parent::label/span[contains(@class,'slds-radio')]")
//	public WebElement dnsRadioButton;
//
//	@FindBy(how = How.XPATH, using = "//input[@id='DNS'][@value='No']/parent::label")
//	public WebElement dnsRadioButtonOption;
	
	@FindBy(how = How.XPATH, using = "//*[text()='DNS Required ?']/parent::label//following-sibling::div/span/input[@value='Yes']/parent::span/label/span[@class='slds-radio_faux']")
	public WebElement dnsRadioButtonYesOption;
	
	@FindBy(how = How.XPATH, using = "//*[text()='DNS Required ?']/parent::label//following-sibling::div/span/input[@value='No']/parent::span/label/span[@class='slds-radio_faux']")
	public WebElement dnsRadioButtonNoOption;

	@FindBy(how = How.XPATH, using = "//input[@id='IPExists'][@value='No']/parent::label")
	public WebElement existingPortIpRadioButtonOption;

	@FindBy(how = How.XPATH, using = "//input[@id='NewIPAllocation'][@value='No']/parent::label")
	public WebElement newIpRadioButtonOption;

	@FindBy(how = How.XPATH, using = "//input[@id='NewIPAllocation'][@value='Yes']/following-sibling::span[contains(@class,'slds-radio')]")
	public WebElement newIpRadioButtonYesOption;

	@FindBy(how = How.XPATH, using = "//input[@id='NewIPAllocation'][@value='Yes']/parent::label")
	public WebElement newIpRadioButtonYesOptionAttribute;

	@FindBy(how = How.XPATH, using = "//select[@id='IPVersionHolder']")
	public WebElement ipVersionDropDown;

	@FindBy(how = How.XPATH, using = "//select[@id='IPV4WANHolder']")
	public WebElement ipVersion4WanBlockDropDown;

	@FindBy(how = How.XPATH, using = "//select[@id='IPV4LANHolder']")
	public WebElement ipVersion4LanBlockDropDown;

	// Service Designer Queue
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service Type')]/following-sibling::span")
	public WebElement serviceTypeProductDetails;
	
//	@FindAll({ @FindBy(xpath = "//span[contains(text(),'Handoff')]/following-sibling::span"),
//		@FindBy(xpath = "//span[text()='Handoff']/following-sibling::span")})
//	public WebElement handOff;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Handoff')]/following-sibling::span")
	public WebElement handOff;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IP Version')]/parent::label/parent::div/following-sibling::div//input")
	public WebElement iPVersion;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IPV4 WAN')]/parent::label/parent::div/following-sibling::div//input")
	public WebElement iPV4Wan;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IPV4 LAN')]/parent::label/parent::div/following-sibling::div//input")
	public WebElement iPV4Lan;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Equipment')]/following-sibling::span")
	public WebElement equipmentDetailsHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Equipment')]/following-sibling::span")
	public WebElement equipment;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access Speed')]/following-sibling::span")
	public WebElement accessSpeed;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Number')]/following-sibling::span")
	public WebElement projectNumber_CompleteCircuit;
	
	

	// Send Order To P&I
	@FindBy(how = How.XPATH, using = "//span[text()='Name : ']/following-sibling::span")
	public WebElement siteContactNameInSendOrder;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Email Address')]/following-sibling::span")
	public WebElement siteContactEmailAddInSendOrder;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Language')]/following-sibling::span")
	public WebElement siteContactLangPrefInSendOrder;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service Speed')]/following-sibling::span")
	public WebElement siteContactServiceSpeedInSendOrder;

	@FindBy(how = How.XPATH, using = "//*[text()='Oracle Number']/parent::label/parent::div/following-sibling::div/input")
	public WebElement oracleNumberInput;

	@FindBy(how = How.XPATH, using = "//*[text()='Oracle Amount']/parent::div/following-sibling::div/input")
	public WebElement oracleAmountInput;


	// PHUB Information
	@FindBy(how = How.XPATH, using = "//input[@id='PHUBValue']")
	public WebElement pHUBInput;

	// Monitor RPATS Fibre Buil
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Number')]//following-sibling::span")
	public WebElement projectNumber;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Owner')]//following-sibling::span")
	public WebElement projectOwner;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Type')]//following-sibling::span")
	public WebElement projectType;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Manager')]//following-sibling::span")
	public WebElement projectManager;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Associated Project')]//following-sibling::span")
	public WebElement associatedProject;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Program Manager')]//following-sibling::span")
	public WebElement programManager;

	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Status')]/following-sibling::span)[2]")
	public WebElement status;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'PHUB')]/following-sibling::span")
	public WebElement phub;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Created Date')]/following-sibling::span")
	public WebElement createdDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Start Date')]//following-sibling::span")
	public WebElement startDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Survey Completed')]/following-sibling::span")
	public WebElement surveyCompletedDate;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Committed')]/following-sibling::span")
	public WebElement commitedServiceDate;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Group')]/following-sibling::span")
	public WebElement group;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Design Completed')]/following-sibling::span")
	public WebElement designCompletedDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Splice Assign')]/following-sibling::span")
	public WebElement spliceAssignReqDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Project Issue')]/following-sibling::span")
	public WebElement projectIssueDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Assigned Date')]/following-sibling::span")
	public WebElement assignedDate;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'RPATS')]/following-sibling::span")
	public WebElement rpatsCurrentCompletionDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Optics Activated Date')]/following-sibling::span")
	public WebElement opticeActivatedDate;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Optics Activate Completion Date')]/following-sibling::span")
	public WebElement opticeActivateCompletionDate;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'As Built Issued')]/following-sibling::span")
	public WebElement eeToSSDate;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Monitor')]")
	public WebElement monitorInforHeaderText;

	@FindBy(how = How.XPATH, using = "(//button/span[contains(text(),'Send FOC Letter')])[1]")
	public WebElement sendFocLetterButton;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Planning Comments']")
	public WebElement planningCommentsText;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Construction Comments']")
	public WebElement constructionCommentsText;

	// FOC Letter Locators
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Installation and Activation')]")
	public List<WebElement> installationAndActivationHeaderText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please ensure the following is completed before')]")
	public WebElement ensureFollowingCompletedMsgText;
	
	
	//@FindBy(how = How.XPATH, using = "//span[text()=' Space is available within the rack or a 24\"x 24\" area on the wall']")
	//public WebElement spaceIsAvailableMsgText;
	@FindBy(how = How.XPATH, using = "//tbody/tr[8]/td[1]/p[1]/span[1]")
	public WebElement spaceIsAvailableMsgText;
	
	//@FindBy(how = How.XPATH, using = "//span[text()=' Access to the main building telephone/electrical rooms, building']")
	//public WebElement accessToMainBuildingMsgText;
	@FindBy(how = How.XPATH, using = "//tbody/tr[9]/td[1]/p[1]/span[1]")
	public WebElement accessToMainBuildingMsgText;
	
	//@FindBy(how = How.XPATH, using = "//span[text()=' A surge-protected power source (with 4-115V power outlets) is']")
	//public WebElement asurgeProtectedMsgText;
	@FindBy(how = How.XPATH, using = "//tbody/tr[10]/td[1]/p[1]/span[1]")
	public WebElement asurgeProtectedMsgText;
	
	//@FindBy(how = How.XPATH, using = "//span[contains(text(),'Once your services are installed,')]")
	//public WebElement onceYourServiceMsgText;
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Once your')]")
	public WebElement onceYourServiceMsgText;
	
	//@FindBy(how = How.XPATH, using = "//span[contains(text(),'If you have any questions you can reach me')]")
	//public WebElement ifYouHaveAnyQuestionMsgText;
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='© 2022 Rogers Communications Inc.']")
	public WebElement yearverification;
	
	
	//RDI CLOSURE LETTER
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Your activation has been successfully confirmed.']")
	public WebElement activationconfirmmsg;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Thank You']")
	public WebElement thankyou;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Dedicated Internet Service')]")
	public WebElement servicesinstalledbox;
	
	@FindBy(how = How.XPATH, using = "//tbody/tr[6]/td[1]/p[1]")
	public WebElement accountnobox;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Enterprise ')]")
	public WebElement videolink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'As a valued ')]")
	public WebElement valuedcxmsg;
	
	@FindBy(how = How.XPATH, using = "//tbody/tr[10]/td[1]/p[1]")
	public WebElement technicalsupport;
	
	@FindBy(how = How.XPATH, using = "//tbody/tr[9]/td[1]/p[1]/span[1]")
	public WebElement phonenumber;
	
	@FindBy(how = How.XPATH, using = "//tbody/tr[10]/td[1]/p[2]")
	public WebElement businessinquiries;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service ID ')]")
	public WebElement Closureletter_msg1;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'24/7 ')]")
	public WebElement Closureletter_msg2;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'View Invoices & Payment ')]")
	public WebElement Closureletter_msg3;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='self-serve option']")
	public WebElement Closureletter_msg6;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'  personally want to thank you for choosing Rogers. ')]")
	public WebElement Closureletter_msg5;
	
	@FindBy(how = How.XPATH, using = "//a[normalize-space()='https://eportal.rogers.com']")
	public WebElement Closureletter_msg4;
	
	
	//RDI WELCOME LETTER
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Following is an overview of what your can expect.']")
	public WebElement Overview_msg;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Step 1: Confirm Billing Information']")
	public WebElement cnfrmbillinf;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'To ensure we are communicating with the appropriat')]")
	public WebElement cnfrmbillinf_desc;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Step 2: Site Survey']")
	public WebElement sitesurvey;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Within the next 5 business days, you will be conta')]")
	public WebElement sitesurveymsg1;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='On the day of the site survey visit:']")
	public WebElement sitesurveymsg2;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'A site contact is required to be available and awa')]")
	public WebElement sitesurveymsg3;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access to the main building telephone/electrical r')]")
	public WebElement sitesurveymsg4;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Step 3: Installation Details']")
	public WebElement Installationdetails;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'After the site survey is complete, within 5 busine')]")
	public WebElement Installationdetailsmsg1;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please review the confirmed installation date and ')]")
	public WebElement Installationdetailsmsg2;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Step 4: Activation']")
	public WebElement activation;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='On the day of activation:']")
	public WebElement activationmsg1;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'A technical contact is required to be available on')]")
	public WebElement activationmsg2;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Step 5: Billing and Support']")
	public WebElement billingandsupport;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Within 1 business day of successful service activa')]")
	public WebElement billingandsupportmsg1;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Once you receive this email:']")
	public WebElement billingandsupportmsg2;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please retain the letter for reference as it will ')]")
	public WebElement billingandsupportmsg3;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'If you have questions you can reach me at')]")
	public WebElement billingandsupportmsg4;
	
	@FindBy(how = How.XPATH, using = "//*[normalize-space()='© 2022 Rogers Communications']")
	public WebElement yearverificationWelcomeletter;
	
	
	// Complete Circuit Design
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Circuit Information')]")
	public WebElement circuitInformationHeaderText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Service Details')]")
	public WebElement serviceDetailsHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'VLAN')]/following-sibling::span")
	public WebElement vLanIdInCircuitValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'PHUB')]/following-sibling::span")
	public WebElement pHUBValueInCircuit;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IP Version')]/following-sibling::span")
	public WebElement ipVersionValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Portable IP')]/following-sibling::span")
	public WebElement existingPortableIP;

	@FindBy(how = How.XPATH, using = "//input[@id='IPAssignmentValue']")
	public WebElement ipAssignmentIpload;

	@FindBy(how = How.XPATH, using = "//label[@for='IPAssignmentValue']")
	public WebElement ipAssignmentIpload1;

	@FindBy(how = How.XPATH, using = "//div[@class='vlc-slds-transparent']/input")
	public WebElement ipAssignmentIpload2;

	@FindBy(how = How.XPATH, using = "/html/body/span[1]/div/span/div/ng-view/div/div/bptree/child[3]/div/section/form/div[1]/div/child[3]/div/ng-form/div/div/div/child/div/ng-form/div[1]/div/div/input")
	public WebElement ipAssignmentIpload3;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IPV4 LAN')]/following-sibling::span")
	public WebElement iPV4LanValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IPV4 WAN')]/following-sibling::span")
	public WebElement iPV4WanValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IPV6 LAN')]/following-sibling::span")
	public WebElement iPV6LanValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IPV6 WAN')]/following-sibling::span")
	public WebElement iPV6WanValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Owner')]/following-sibling::span")
	public WebElement projectOwnerInCompleCircuit;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Project Number')]/following-sibling::span")
	public WebElement projectNumberInCompleCircuit;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Port:')]/following-sibling::span")
	public WebElement portNoVAlue;

	@FindBy(how = How.XPATH, using = "//span[text()='Enter CLLI ID/Site ID']/parent::label/parent::div/following-sibling::div/input")
	public WebElement cLFIValueEnter;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'CLLI Value Should be 8 to 22 Alphanumeric Characters')]")
	public WebElement cLFIValueEnterErrorValidation;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Enter Notes']/parent::div/following-sibling::div/textarea")
	public WebElement textAreaEnter;

	@FindBy(how = How.XPATH, using = "//span[text()='CLR Attachment']/parent::label/parent::div/following-sibling::div//label/span/lightning-primitive-icon")
	public WebElement clrupload;
	
//	@FindBy(how = How.XPATH, using = "//span[text()='IP Assignment Attachment']/parent::label/parent::div/following-sibling::div//label/span/lightning-primitive-icon")
//	public WebElement ipAddressAssignmentAttachment;
	
	@FindBy(how = How.XPATH, using = "//span[text()='IP Assignment Attachment']/parent::label/parent::div/following-sibling::div//label/span")
	public WebElement ipAddressAssignmentAttachment;

	@FindBy(how = How.XPATH, using = "//span[text()='Enter EVC ID']/parent::label/parent::div/following-sibling::div/input")
	public WebElement evcIdEnter;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Minimum length of 22')]")
	public WebElement evcIdEnterErrorValidationMinLen;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Maximum length of 22')]")
	public WebElement evcIdEnterErrorValidationMaxValue;

	@FindBy(how = How.XPATH, using = "//span[text()='Enter Access CIRC ID']/parent::label/parent::div/following-sibling::div/input")
	public WebElement accessCirCidEnter;

	@FindBy(how = How.XPATH, using = "//span[text()='Enter Customer Port ID']/parent::label/parent::div/following-sibling::div/input")
	public WebElement customerPortIdEnter;

	@FindBy(how = How.XPATH, using = "//span[text()='Enter Device ID']/parent::label/parent::div/following-sibling::div/input")
	public WebElement deviceIdEnter;
	
	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Please attach applicable file before completing the task')]")
	public List<WebElement> uploadErrorValidationText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Done']")
	public WebElement doneButton;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'IP Assignment Information')]//following::button/span[text()='Save']")
	public WebElement saveButton;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'The values you have entered have been saved.')]")
	public WebElement taskUpdatedText;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Save is Complete')]")
	public WebElement saveCompleteText;
	
	// Schedule TIS for IP Assignment

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'DNS')]/following-sibling::span")
	public WebElement dNSValue;

	// Complete IP Assignment
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'IP Assignment Information')]")
	public WebElement ipAssignmentInformationText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='IP Assignment Attachment']/parent::label/parent::div/following-sibling::div//label/span")
	public List<WebElement> ipAssignmentUploadButton;
	
	// Schedule TIS for TTC
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Circuit Type')]/following-sibling::span")
	public WebElement circuitType;
	
//	@FindAll({ @FindBy(xpath = "//span[contains(text(),'Access Provider')]/following-sibling::span"),
//		@FindBy(xpath = "//span[text()='Access Provider']/following-sibling::span")})
//	public WebElement accessProviderValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access Provider')]/following-sibling::span")
	public WebElement accessProviderValue;

//	@FindAll({ @FindBy(xpath = "//span[contains(text(),'Service Provider')]/following-sibling::span"),
//		@FindBy(xpath = "//span[text()='Service Provider']/following-sibling::span")})
//	public WebElement serviceProviderValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Service Provider')]/following-sibling::span")
	public WebElement serviceProviderValue;
	
//	@FindAll({ @FindBy(xpath = "//span[text()='Encapsulation Type']/following-sibling::span"),
//		@FindBy(xpath = "//span[contains(text(),'Encapsulation Type')]/following-sibling::span")})
//	public WebElement encapsulationTypeValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Encapsulation Type')]/following-sibling::span")
	public WebElement encapsulationTypeValue;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Port')]/following-sibling::span)[1]")
	public WebElement portNo;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Port Type')]/following-sibling::span")
	public WebElement portTypeValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Port Speed')]/following-sibling::span")
	public WebElement portSpeed;

	// Complete access provisioning queue
	@FindBy(how = How.XPATH, using = "//input[@id='EthernetTestResults']")
	public WebElement ethernetTestUpload;

	@FindBy(how = How.XPATH, using = "//span[text()='Port Config']/parent::label/parent::div/following-sibling::div//input")
	public WebElement portConfig;

	@FindBy(how = How.XPATH, using = "//span[text()='Ethernet Test Results']/parent::label/parent::div/following-sibling::div//label/span/lightning-primitive-icon")
	public WebElement ethernetTestResultUpload;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Phone')]/following-sibling::span")
	public WebElement siteContactPhoneInCompleteAccess;

	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Name')]/following-sibling::span)[2]")
	public WebElement siteContactNameInCompleteAccessText;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Site Name')]/following-sibling::span)[1]")
	public WebElement siteContactNameValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Email')]/following-sibling::span")
	public WebElement siteContactEmailInCompleteAccessText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Device ID')]/following-sibling::span")
	public WebElement deviceIdValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Customer Port ID')]/following-sibling::span")
	public WebElement customerPortIdValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Access CIRC ID')]/following-sibling::span")
	public WebElement accessCIRCIdValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Circuit Design')]/following::a[contains(text(),'CLR_')]")
	public WebElement circuitDesignAttachmentValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IP Address Assignments')]/following::a[contains(text(),'IPAssigned_')]")
	public WebElement ipAddressAttachmentValue;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Ethernet Test Information')]/following::button[contains(text(),'EthernetTestResults_')]")
	public WebElement ethernetTestResultsAttachmentValue;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'CLR Attachment')]/following::button[contains(text(),'CLR_')]")
	public WebElement clRResultsAttachmentValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Language')]/following-sibling::span")
	public WebElement siteContactLanguagePrefText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Account Number')]/following-sibling::span")
	public WebElement billingAccountNumberText;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Billing Account')]/following-sibling::span)[1]")
	public WebElement billingAccountName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Source')]/following-sibling::span")
	public WebElement billingAccountSourcerText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Circuit Type')]/following-sibling::span")
	public WebElement circuitTypeText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'CLFI')]/following-sibling::span")
	public WebElement cLFITypeText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'EVC ID')]/following-sibling::span")
	public WebElement evcIDText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'EVC Notes')]/following-sibling::span")
	public WebElement evcNotesText;

	@FindBy(how = How.XPATH, using = "//span[text()='Demarcation Location']/parent::label/parent::div/following-sibling::div//input")
	public WebElement demarcationLocationTextInAccessProvsioning;

	@FindBy(how = How.XPATH, using = "//span[text()='HandOff Interface']/parent::label/parent::div/following-sibling::div//input")
	public WebElement handOffInterfacedropDownInAccess;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Ethernet Test Information')]//following::button/span[text()='Save']")
	public WebElement saveButtonForCompleteAccess;

	@FindBy(how = How.XPATH, using = "//*[@alt='download']")
	public List<WebElement> docDownloadClick;

	@FindBy(how = How.XPATH, using = "//span[text()='Billing Activation Completed']/preceding-sibling::span")
	public WebElement billingActivatonCheckBox;
}
