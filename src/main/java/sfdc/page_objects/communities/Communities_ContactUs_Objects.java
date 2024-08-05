package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date:10/06/2020
 *
 *         Communities> Contact Us page objects
 */
public class Communities_ContactUs_Objects {

	public Communities_ContactUs_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Contact Us')]")
	public WebElement contactUsTextHeader;

	@FindBy(how = How.XPATH, using = "//span[.='Email us']")
	public WebElement emailButton;

	@FindBy(how = How.XPATH, using = "//input[@name='OptionSelectionRadio']//following-sibling::label//div[contains(.,'Phone')]")
	public WebElement phoneButton;

	@FindBy(how = How.XPATH, using = "//input[@name='OptionSelectionRadio']//following-sibling::label//div[contains(.,'Chat')]")
	public WebElement chatButton;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'For Wireline Solutions, Cable/Fiber Internet, Data Center & Cloud:') and @class='large-title']")
	public WebElement forWirelessSolutionsHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'For Wireline Solutions, Cable/Fiber Internet, Data Center & Cloud:')]/following-sibling::div[@class='medium-title']")
	public WebElement wirelessSolutionPhone;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'For Technical Support:') and @class='large-title']")
	public WebElement forTechnicalSupportHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'For Technical Support:') and @class='large-title']//following-sibling::div//span")
	public WebElement techSupportPhone;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'Hours of Operation:')]/following-sibling::span[contains(.,'8:30 AM to 5:00 PM')]")
	public WebElement wirelessSolutionHours;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'Outside of Canada?')]/following-sibling::span[contains(.,'Call 1-524-734-7699')]")
	public List<WebElement> outsideCanadaCall;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'Hours of Operation:')]/following-sibling::span[contains(.,'24/7')]")
	public WebElement hoursOfOperation24By7;

	@FindBy(how = How.XPATH, using = "//label[text()='Account Name']//parent::div/preceding-sibling::input")
	public WebElement accountNameDropdown;

	@FindBy(how = How.XPATH, using = "//div[@class='contactus-thankyou-heading' and contains(.,'Thank you for contacting us!')]")
	public WebElement thankYouMsgHeader;

	@FindBy(how = How.XPATH, using = "//div[@class='contactus-thankyou-message' ]")
	public WebElement thankYouMsg;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'For Account & Billing Support:')]//div/span[contains(.,'Hours of Operation:')]/following-sibling::span[contains(.,'9AM to 5PM (EST)')]")
	public WebElement chatHoursOfOperation;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,' Note: If you have account inquiries please chat with one of our Business Specialists by clicking on the chat button on the right of this page. ')]")
	public WebElement chatIfYouHaveAccountInquiryText;

	@FindBy(how = How.XPATH, using = "//button[@aria-label='Chat with an Expert' or @aria-label='Agent Offline']")
	public WebElement agentOfflineButton;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='TAAccountNumber-Block' or @data-omni-key='TAAccountPhoneNumber-Block']//label[contains(.,'Account Number')]/parent::div/preceding-sibling::input")
	public WebElement accountNumberInput;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='BillingAccountNumberBlock']//label[contains(.,'Account Number')]/parent::div/preceding-sibling::input")
	public WebElement billingAccountNumberInput;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='TAAccountNumber-Block']//label[contains(.,'Account Number')]")
	public WebElement accountNumberLabel;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='BillInquiryBlock']//label[contains(.,'charge')]/parent::div/preceding-sibling::input")
	public WebElement typeOfChargeInput;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='BillInquiryBlock']//li//div[@role='option']")
	public List<WebElement> typeOfChargeValues;
	
	@FindBy(how = How.XPATH, using = "//c-combobox//label[text()='Bill Month']//parent::div/preceding-sibling::input")
	public WebElement invoiceMonth;

	@FindBy(how = How.XPATH, using = "//c-combobox//label[contains(text(),'Month')]")
	public WebElement invoiceMonthLabel;

	@FindBy(how = How.XPATH, using = "//c-combobox//label[text()='Report Type']//parent::div/preceding-sibling::input")
	public WebElement reportsTypeDropdown;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='RD_SHOW_TOR_FORM']//span[.='No']/label/span")
	public WebElement torNoOption;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='RD_SHOW_TOR_FORM']//span[.='Yes']/label/span")
	public WebElement torYesOption;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'IMEI Number')]/parent::div/preceding-sibling::input")
	public List<WebElement> imeiNumberInput;

	@FindBy(how = How.XPATH, using = "//label[text()='IMEI Number']")
	public WebElement imeiNumberLabel;

	@FindAll({ @FindBy(xpath = "(//*[.='Add'])[0]"), @FindBy(xpath = "(//*[.='Add'])[1]") })
	public WebElement addImeiButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'link') and text()='Delete']")
	public WebElement deleteImeiButton;

	@FindBy(how = How.XPATH, using = "//*[.='Invalid IMEI']")
	public WebElement invalidIMEIErrorMessage;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='IMEIHelpText']//p")
	public WebElement imeiPleaseNoteMessage;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'heading') and contains(.,'To create another new case, Please click below.')]")
	public WebElement createAnotherCaseMessage;

	@FindBy(how = How.XPATH, using = "//div[.='For Wireless, Cable Internet & TV:']")
	public WebElement contactByPhoneForWirelessCIAndTV;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'Outside of Canada?')]/following-sibling::span")
	public WebElement contactByPhoneOutsideCanadaForWirelessCIAndTV;

	@FindBy(how = How.XPATH, using = "//div[.='For Wireline:']")
	public WebElement contactByPhoneForWireline;

	@FindBy(how = How.XPATH, using = "//div[.='For Data Centre & Cloud:']")
	public WebElement contactByPhoneForDataCentreAndCloud;

	@FindBy(how = How.XPATH, using = "//div[.='For Wireless, Cable Internet & TV:']/following-sibling::div//span")
	public WebElement contactByPhoneForWirelessCIAndTVValue;

	@FindBy(how = How.XPATH, using = "//div[.='For Wireline:']/following-sibling::div//span")
	public WebElement contactByPhoneForWirelineValue;

	@FindBy(how = How.XPATH, using = "//div[.='For Data Centre & Cloud:']/following-sibling::div//span")
	public WebElement contactByPhoneForDataCentreAndCloudValue;

	@FindBy(how = How.XPATH, using = "//div[.='For Wireless, Cable Internet & TV:']/parent::div//span[.='Hours of Operation: ']/following-sibling::span")
	public WebElement hoursOfOperationForWirelessCIAndTV;

	@FindBy(how = How.XPATH, using = "//div[.='For Wireline:']/parent::div//span[.='Hours of Operation: ']/following-sibling::span")
	public WebElement hoursOfOperationForWireline;

	@FindBy(how = How.XPATH, using = "//div[.='For Data Centre & Cloud:']/parent::div//span[.='Hours of Operation: ']/following-sibling::span")
	public WebElement hoursOfOperationForDataCentreAndCloud;

	@FindBy(how = How.XPATH, using = "//div[.='For Technical Support:']/parent::div//span[.='Hours of Operation: ']/following-sibling::span")
	public WebElement hoursOfOperationForTechnicalSupport;

	@FindBy(how = How.XPATH, using = "//p[.='Account Information']")
	public WebElement accountInformationSection;

	@FindBy(how = How.XPATH, using = "//p[.='Product Information']")
	public WebElement productInformationSection;

	@FindBy(how = How.XPATH, using = "//p[.='Add Any Additional Information']")
	public WebElement addAnyAdditionalInformationSection;

	@FindBy(how = How.XPATH, using = "//p[.='Add Attachments']")
	public WebElement addAttachmentsSection;

	@FindBy(how = How.XPATH, using = "//label[.='Name']/parent::div/preceding-sibling::input")
	public WebElement nameInput;

	// Move to CreateCase Object File once we mmerge contact page objects
	
	
	@FindBy(how = How.XPATH, using = "//label[text()='Account Name']//ancestor::*[@data-omni-key='txtAccountName']//div[@role='option']")
	public List<WebElement> accountNameOptions;

	@FindBy(how = How.XPATH, using = "//label[text()='Report Type']//ancestor::*[@data-omni-key='selectReportType']//div[@role='option']")
	public List<WebElement> reportTypeOptions;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Report Type')]")
	public WebElement reportTypeLabel;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'report types')]//span")
	public List<WebElement> reportTypeTipMessageSpan;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'report types')]//a")
	public WebElement reportTypeTipMessageLink;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[contains(text(),'Account Number')]//ancestor::div[@role='combobox']//li/div[@role='option']"),
		@FindBy(how = How.XPATH, using = "//label[contains(text(),'Business Phone')]//ancestor::div[@role='combobox']//li/div[@role='option']")})
	public List<WebElement> businessPhoneNumberDropdownOptions;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Wireless Phone Number')]//ancestor::*[@data-omni-key='TAWirelessPhoneNumber-Block']//li/div[@role='option']")
	public List<WebElement> wirelessPhoneNumberDropdownOptions;
	
	@FindBy(how = How.XPATH, using = "//label[@aria-label='Wireless Phone Number']/parent::div/preceding-sibling::input")
	public WebElement wirelessNumberInput;
	
	@FindBy(how = How.XPATH, using = "//label[@aria-label='Business Phone Number']/parent::div/preceding-sibling::input")
	public WebElement businessNumberInput;
	
	@FindBy(how = How.XPATH, using = "//label[@aria-label='Please enter any additional details for this case so we can better help you']")
	public WebElement caseDescTextAreaHelpText;
	
	@FindBy(how = How.XPATH, using = "//p[contains(.,' confidential information such as password.')]")
	public WebElement caseDescNoteText;
	
	@FindBy(how = How.XPATH, using = "//p[.='Select the address you need support at:']")
	public WebElement selectAddressLabel;
	
	@FindBy(how = How.XPATH, using = "//p[.='Review or add the contact for the selected site:']")
	public WebElement siteContactLabel;
	
	@FindBy(how = How.XPATH, using = "//div[.='Site Contact']/parent::div/parent::div//following-sibling::div")
	public WebElement siteContactDetails;
	
	@FindBy(how = How.XPATH, using = "(//button[.='Edit'])[2]")
	public WebElement editSiteContactButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Select Site Contact']")
	public WebElement selectSiteContactPageHeader;
	
	@FindBy(how = How.XPATH, using = "//div[.='Cancel']/parent::button")
	public WebElement selectSiteContactCancelButton;
	
	
	
}
