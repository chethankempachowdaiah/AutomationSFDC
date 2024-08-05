package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_CableOrder_ManualQueueTask_Objects {


	public SFDC_CableOrder_ManualQueueTask_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Product Name')]/following-sibling::span")
	public List<WebElement> productNameValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Additional Information')]")
	public WebElement additionalInformationText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Installation Details')]//following::span[contains(text(),'Product Name')]/following-sibling::span")
	public List<WebElement> phoneInstallationDetails;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Office 365 Product Information')]//following::span[contains(text(),'Product Name')]/following-sibling::span")
	public List<WebElement> office365ProductNameValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Promo Code')]/following-sibling::span")
	public WebElement promoCodeValue;

	@FindBy(how = How.XPATH, using = "//span[@title='Billing Account']")
	public WebElement billingAccountCANNo;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Complete')]/parent::button")
	public WebElement completeButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Complete')]/parent::button/span")
	public WebElement completeButton_ForRPATasks;

	@FindBy(how = How.XPATH, using = "//span[text()='Complete']")
	public WebElement createCableCompleteButton;

	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Order is valid. Proceed with Completing the Task')]")
	public WebElement orderCompletedMessageText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IBLc Product Information')]")
	public WebElement iblcProductHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'TV Product Information')]")
	public WebElement tvProductHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Internet Product Information')]")
	public WebElement internetProductHeaderText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Term Details')]/following-sibling::span")
	public WebElement termDetailsText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'O365 Product Information')]")
	public WebElement office365ProductHeaderText;

	@FindBy(how = How.XPATH, using = "//span[text() = ' Name : ']/following-sibling::span")
	public List<WebElement> iblcEquip_InstallDetailsName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Phone Line')]")
	public List<WebElement> iblcPhoneLine;

	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Phone Line')]//following::span[contains(text(),'Product Name')]/following-sibling::span")
	public List<WebElement> iblcPhoneLineProductName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Phone Line')]//following::button[@class='section-control slds-button slds-button_reset slds-accordion__summary-action']")
	public List<WebElement> iblcPhoneLineExpandArrow;

	@FindBy(how = How.XPATH, using = "//span[@title='IBLc Provisioning Information']")
	public WebElement iblcProvisioningHearderText;

	@FindBy(how = How.XPATH, using = "//span[@title='Port Request Information (LSR)']")
	public WebElement portRequestInformationText;

	@FindBy(how = How.XPATH, using = "//div[@title='Phone Number']/parent::th/parent::tr/parent::thead/following-sibling::tbody/tr/td/div")
	public List<WebElement> portRequestInformationListClmn;

	@FindBy(how = How.XPATH, using = "(//div[@title='Phone Number']/parent::th/parent::tr/parent::thead/following-sibling::tbody/tr)")
	public List<WebElement> portRequestInformationListRow;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'TV Product Information')]")
	public WebElement tvProductDetailsInformationText;

	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Product Details')])[last()]/../../../following-sibling::div/slot/div")
	public List<WebElement> tvProductDetailsName;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Internet Product Information')]")
	public WebElement internetProductDetailsInformationText;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Product Details')])[1]/../../../following-sibling::div/slot/div")
	public List<WebElement> internetProductDetailsName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Promo Name')]//following-sibling::span")
	public List <WebElement> promoName;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Promo Code')]//following-sibling::span")
	public List <WebElement> promoDetails;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Promo Details')]/../../../following-sibling::div/slot/div")
	public List<WebElement> internetAndIBLCPromoDetailsName;

	@FindBy(how = How.XPATH, using = "//div[@title='Listing Name']/parent::th/parent::tr/parent::thead/following-sibling::tbody/tr/td/div")
	public List<WebElement> directoryListingInformationList;

	@FindBy(how = How.XPATH, using = "//*[text()='Directory Listing Information']")
	public WebElement directoryListingInformationText;

	@FindBy(how = How.XPATH, using = "//*[text()='V21 Account and SS Order Information']")
	public WebElement v21AccountSSOHeaderText;

	@FindBy(how = How.XPATH, using = "//*[text()='V21 Consolidated BAN Information']")
	public WebElement v21ConsolidatedBanHeaderText;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Enter V21 BAN Number')]")
	public WebElement v21BanNumberEnter;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Supersystem Install Order Information')]")
	public WebElement supersystemInstallOrderInfoText;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Supersystem Pre-Inspection Order Information')]//following::input[@class='vlocity-input slds-input']")
	public List<WebElement> supersystemINSOrderNoEnter;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Minimum length of 7.')]")
	public WebElement supersystemINSOrderNoMinLengtText;

	@FindBy(how = How.XPATH, using = "//input[@data-id = 'date-picker-slds-input']")
	public List<WebElement> supersystemINSOrderAppointmentDateEnter;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Supersystem Pre-Inspection Order Information')]")
	public WebElement supersystemPreInspectionOrderHeaderText;

	@FindBy(how = How.XPATH, using = "//input[contains(@class, 'slds-input slds-combobox__input slds-combobox')]")
	public List<WebElement> supersystemINSEnterOderTime;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'IBLc Product Information')]//following::span[contains(text(), 'Promo')]//following-sibling::span")
	public List<WebElement> iblcPromoCodeValue;

	@FindBy(how = How.XPATH, using = "//span[@title='OSP Invoices']//following::span[@class='vpl-confirmation-value']")
	public List<WebElement> iblcEmergencyAndOSPInvoiceAttach;

	// Create Account In SS Failure

	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Create Account in SS Task Failure')]")
	public WebElement createAccountInSSFailureHeaderText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Create CAN Manually')]/preceding-sibling::span")
	public List<WebElement> createCanManuallyClick;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Next')]/parent::button")
	public WebElement nextButtonForCreateCanManually;

	@FindBy(how = How.XPATH, using = "//input[@class='vlocity-input slds-input']")
	public WebElement enterSuperSystemCAN;

	@FindBy(how = How.XPATH, using = "//span[text()='Complete']")
	public WebElement clickOnCompleteButtonAfterCANNo;
	
	// Create Work Order In SS TASK Failure:
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Create Work Order in SS Task Failure')]")
	public WebElement createWorkOrderInSSFailureHeaderText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Create Work Order Manually')]/preceding-sibling::span")
	public List<WebElement> createWorkOrderManuallyClick;

	// Email send appointment letter

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Back to Inbox')]/parent::div/parent::div/following-sibling::div/div/div/div[contains(text(),'To')]/following-sibling::div")
	public WebElement toReceiverID;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Back to Inbox')]/parent::div/parent::div/following-sibling::div/div/div/div[contains(text(),'From')]/following-sibling::div")
	public WebElement fromAddressID;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Back to Inbox')]/parent::div/parent::div/following-sibling::div/div/div/div[contains(text(),'From')]/following-sibling::div")
	public WebElement fromReceiverID;

	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Rogers Communications Inc.']")
	public WebElement userReferenced;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Rogers Communications Inc.']//following-sibling::div")
	public WebElement emailReferenced;
	
	@FindBy(how = How.XPATH, using = "//iframe[@title='HTML Email Message Body']")
	public List<WebElement> frameEmailBody;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Services to be installed')]")
	public WebElement servicesToBeInstalled;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Service Address')]")
	public WebElement servicesAddressHeaderText;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Service Address')]/preceding-sibling::ul/following-sibling::div")
	public List<WebElement> servicesAddressText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Service Address')]/following-sibling::div")
	public WebElement closureLetterServicesAddressText;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Delivery Specialist')]/preceding-sibling::div")
	public WebElement deliverySpecialistName;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Delivery Specialist')]/following-sibling::div[2]")
	public WebElement deliverySpecialistEmailIdName;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Pre-Site Inspection Date and Time')]/following-sibling::div")
	public WebElement preInspectionDateAndTime;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Installation and Activation Date and Time')]/following-sibling::div")
	public WebElement installationActivationDateAndTime;

	// Office 365

	@FindBy(how = How.XPATH, using = "//*[contains(.,'Order Number')]/ancestor::div[@class='slds-accordion__summary']//following-sibling::div//a")
	public WebElement office365TaskOrderNumber;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'Order Status')]/ancestor::div[@class='slds-accordion__summary']//following-sibling::div//p")
	public WebElement office365TaskOrderStatus;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'Business Name')]/ancestor::div[@class='slds-accordion__summary']//following-sibling::div//p")
	public WebElement office365TaskBusinessName;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'Service Address')]/ancestor::div[@class='slds-accordion__summary']//following-sibling::div//p")
	public WebElement office365TaskServiceAddressName;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Sales Agent Contact')]//following::p[contains(text(),'Name :')]")
	public List<WebElement> salesContactName;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Sales Agent Contact')]//following::p[contains(text(),'Email :')]")
	public List<WebElement> salesContactEmaiID;

	@FindBy(how = How.XPATH, using = "//*[contains(.,'Office 365 products')][@role='status']//following::div/p[contains(text(),'Term Duration')]")
	public WebElement office365TermDuration;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Order Number')]/parent::label/following-sibling::div//p/a")
	public WebElement office365ProductHeaderTextInTask;
	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Customer Data')]/ancestor::div[@class='slds-accordion__summary']//following-sibling::div//p)[1]")
	public WebElement supersystemCANNO;
	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Customer Data')]/ancestor::div[@class='slds-accordion__summary']//following-sibling::div//p)[2]")
	public WebElement v21BANNO;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Task Assignment And State')]//following::span[contains(.,'Assign To Me')]//preceding-sibling::span")
	public WebElement assignToMeCheckBoxClick;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Task Assigned')]//parent::*/parent::div")
	public WebElement taskAssigedToUser;
	
	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'State')]//parent::*/parent::div")
	public WebElement taskAssigedState;

	@FindBy(how = How.XPATH, using = "//button[@label='Confirm']")
	public WebElement confirmButton;

	@FindBy(how = How.XPATH, using = "//button[@label='Next']")
	public WebElement nextButton;

	// Fail tAsk Button

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Fail Task')]/parent::label")
	public WebElement failTaskButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Cancel Task Failure')]/parent::label")
	public WebElement cancelFaiTaskFailureButton;

	@FindBy(how = How.XPATH, using = "//textarea[@placeholder = 'Failure Reason']")
	public WebElement enterTextArea;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Cancel Task Failure')]")
	public WebElement cancelFailureText;
	
	// order Cancel Message 
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Order is cancelled')]")
	public WebElement orderCancelledText;


	@FindBy(how = How.XPATH, using ="/html/body/div/div[1]/div[1]/p[2]")
	public WebElement paragraphInMailinatorEmail;

	// Welcome letter 
	@FindBy(how = How.XPATH, using = "(//td/div[contains(text(),'Getting Started')]/parent::td/parent::tr/following-sibling::tr/td)[1]")
	public WebElement gettingStartedText;

	// Closure Letter
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Account Number')]")
	public WebElement accountNumberText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Account Number')]//following-sibling::div")
	public List<WebElement> accountNumber;

}

