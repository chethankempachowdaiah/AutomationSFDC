package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya,, Date:06/08/2020
 * 
 *         Communities>View cases> My Business Case Details
 *
 */
public class Communities_MyBusinessCaseDetails_Objects {

	public Communities_MyBusinessCaseDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(how = How.XPATH, using = "//a[contains(.,'Cases Summary')]/following-sibling::span"),
			@FindBy(how = How.XPATH, using = "//a[contains(.,'Case Summary')]/following-sibling::span") })
	public WebElement caseSummaryCaseDetails;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Details')]")
	public WebElement caseDetailsTab;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Comments')]")
	public WebElement caseCommentsTab;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Case #')]/following-sibling::p")
	public WebElement caseNumber;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Account Name')]/following-sibling::p")
	public WebElement accountNumber;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Status')]/following-sibling::p")
	public WebElement caseStatus;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Submission Date')]/following-sibling::p")
	public WebElement caseSubmissionDate;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Completion') and contains(.,'Date')]/following-sibling::p")
	public WebElement caseCompletionDateField;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Reason')]/following-sibling::p")
	public WebElement caseReason;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Product')]/following-sibling::p")
	public WebElement caseProduct;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Subject')]/following-sibling::p")
	public WebElement caseSubject;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Description')]/following-sibling::p")
	public WebElement caseDescription;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'My Attachments')]")
	public WebElement myAttachmentsField;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Upload Files')]")
	public WebElement uploadFilesButton;

	@FindBy(how = How.XPATH, using = "//textarea[@data-id='taCaseComments']")
	public WebElement postCommentInput;

	@FindAll({ @FindBy(how = How.XPATH, using = "//c-button/button[contains(.,'Submit')]"),
			@FindBy(how = How.XPATH, using = "//button[contains(.,'Submit')]") })
	public WebElement submitButton;

	@FindBy(how = How.XPATH, using = "//*[@data-field-name='userName']//span")
	public WebElement commentUserName;

	@FindBy(how = How.XPATH, using = "//*[@data-field-name='createdDate']//span")
	public WebElement commentCreatedDate;

	@FindBy(how = How.XPATH, using = "//*[@data-field-name='CommentBody']//span")
	public WebElement commentBody;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'attachments-list')]")
	public List<WebElement> attachmentList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'attachments-list')]//img[@alt='Delete']")
	public List<WebElement> deleteAttachmentIcon;

	@FindBy(how = How.XPATH, using = "//button[text()='Confirm']")
	public WebElement deleteConfirmButton;

	@FindBy(how = How.XPATH, using = "//span[.='Are you sure you want to delete this attachment?']")
	public WebElement deleteConfirmMessage;

	@FindBy(how = How.XPATH, using = "//p[.='Post']")
	public WebElement postCommentHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'table')]")
	public WebElement commentsTable;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Case Type')]/following-sibling::p")
	public WebElement caseType;

	@FindBy(how = How.XPATH, using = "//div[.='Create a New Case']")
	public WebElement createCaseHeader;

	@FindBy(how = How.XPATH, using = "//input[@value='Service']/parent::span")
	public WebElement billingAndAccountMgtCaseType;

	@FindBy(how = How.XPATH, using = "//input[@value='Tier_1_Tech_Support']/parent::span")
	public WebElement technicalSupportCaseType;

	@FindBy(how = How.XPATH, using = "//*[contains(@data-id,'Description')]//textarea")
	public WebElement additionalDetailsTextArea;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Create case')]")
	public WebElement createCaseButton;

	@FindBy(how = How.XPATH, using = "//div[.='We have created a new case for you.']")
	public WebElement caseCreatedSuccessMessage;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'#')]")
	public WebElement caseNumberOnCaseCreation;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Account Number')]//ancestor::*[@data-omni-key='TAAccountPhoneNumber-Block' or @data-omni-key='TAAccountNumber-Block']//li/div[@role='option']/span/span")
	public List<WebElement> accountPhoneNumberDropdownOptions;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='txtReason']//label//span[contains(@class,'label')]")
	public List<WebElement> reasonsList;

	@FindBy(how = How.XPATH, using = "//label[text()='Bill Month']//ancestor::*[@data-omni-key='selectBillMonth']//div[@role='option']")
	public List<WebElement> billMonthOptions;

	@FindBy(how = How.XPATH, using = "//c-combobox//label[@aria-label='Reason']//parent::div/preceding-sibling::input")
	public WebElement reasonDropdown;

	@FindBy(how = How.XPATH, using = "(//span[@c-dgtlselectablecell_dgtlselectablecell][@class=\"nds-radio_faux\"])[1]")
	public WebElement commCaseSelectAddressRadioBtn;

	@FindBy(how = How.XPATH, using = "(//label[text()='Street Address'])[1]/parent::div/preceding-sibling::input")
	public WebElement commCaseStrtAdd;

	@FindBy(how = How.XPATH, using = "//*[.='Street Address']")
	public WebElement commCaseStrtAddLabel;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='TXT_City']//label[text()='City']/parent::div/preceding-sibling::input")
	public WebElement commCaseCity;

	@FindBy(how = How.XPATH, using = "//a[@data-field='serviceAddressCity']//*[.='City']")
	public WebElement commCaseCityLabel;

	@FindBy(how = How.XPATH, using = "//a[@data-field='serviceAddressPostalCode']//*[.='Postal Code']")
	public WebElement commCasePostalCodeLabel;

	@FindBy(how = How.XPATH, using = "//*[.='Province']")
	public WebElement commCaseProvinceLabel;

	@FindBy(how = How.XPATH, using = "(//label[text()='Province']/parent::div)[1]/preceding-sibling::input")
	public WebElement commCaseProvince;

	@FindBy(how = How.XPATH, using = "(//div[@data-value=\"ON\"])[1]")
	public WebElement commCaseDdOptn_ON;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='FirstName']//label[text()='First Name']/parent::div/preceding-sibling::input")
	public WebElement commCaseFirstName;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='LastName']//label[text()='Last Name']/parent::div/preceding-sibling::input")
	public WebElement commCaseLastName;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='EmailAddress']//label[text()='Email Address']/parent::div/preceding-sibling::input")
	public WebElement commCaseEmailAdd;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='PhoneNumber']//label[text()='Phone Number']/parent::div/preceding-sibling::input")
	public WebElement commCasePhoneNumber;

	@FindBy(how = How.XPATH, using = "(//label[text()='Please enter your Service ID'])[1]/parent::div/preceding-sibling::input")
	public WebElement commCaseServiceID;

	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='radioServiceIdDF']//span[contains(text(),'No, I don')]//preceding-sibling::span")
	public WebElement noServieIdKnownRadio;
	
	@FindBy(how = How.XPATH, using = "//p[.='Do you have a Circuit Identification (ID) number?']")
	public WebElement doYouHaveCircuitIDLabel;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Find your circuit ID on your Rogers')]")
	public WebElement circuitIDHelpText;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='radioCircuitIDDF']//input[@value='Yes']//parent::span")
	public WebElement yesCircuitIDKnownRadio;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='radioCircuitIDDF']//span[contains(text(),'No, I don')]//preceding-sibling::span")
	public WebElement noCircuitIdKnownRadio;
	
	@FindBy(how = How.XPATH, using = "(//label[text()='Please enter your Circuit ID'])[1]/parent::div/preceding-sibling::input")
	public WebElement commCaseCircuitID;
	
	@FindBy(how = How.XPATH, using = "//c-combobox//label[@aria-label='Product']//parent::div/preceding-sibling::input")
	public WebElement productDropdown;

	@FindBy(how = How.XPATH, using = "//div[@role='option']")
	public List<WebElement> dropdownOptions;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Subject')]/parent::div/preceding-sibling::input")
	public WebElement subjectInput;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Description')]/parent::div/preceding-sibling::textarea")
	public WebElement descriptionInput;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Cases Summary')]")
	public WebElement caseSubmissionCaseSummaryLink;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'heading') and contains(.,'Thanks! You have successfully created a new case.')]")
	public WebElement caseCreationThankYouMessage;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Thank you for your continued business and trusting Rogers')]")
	public WebElement caseCreationThanksDetailedMessage;

}
