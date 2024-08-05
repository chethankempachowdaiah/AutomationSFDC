package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla
 * 
 *         Guided By Journey Provisioning Details Object
 *
 */
public class SFDC_GuidedByJourney_IBLC_Provisioning_Details_Objects {

	public SFDC_GuidedByJourney_IBLC_Provisioning_Details_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[.='Provisioning Details']")
	public WebElement provisioningDetailsHeader;

	@FindBy(how = How.XPATH, using = "//div[.='Please enter provisioning Information']")
	public WebElement provisioningDetailsSubHeader;

	@FindBy(how = How.XPATH, using = "//div[.='Business Phone Line']")
	public WebElement businessPhoneLineHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Business Phone')]/following-sibling::div[contains(@class,'enabled')]")
	public List<WebElement> businessPhoneLinesExpand;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Porting Request')]/following-sibling::div[contains(@class,'enabled')]")
	public WebElement portingRequestLinesExpand;

	@FindBy(how = How.XPATH, using = "//*[@data-name='EditPhoneLine']")
	public List<WebElement> editPhoneLineButton;

	@FindBy(how = How.XPATH, using = "//*[@data-name='EditPortingRequest']")
	public List<WebElement> editPortingRequestButton;

	@FindBy(how = How.XPATH, using = "//img[contains(@class,'warning')]")
	public List<WebElement> warningForIncompleteDataImage;

	@FindBy(how = How.XPATH, using = "//section[@role='dialog']")
	public WebElement dialogBox;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Edit Business Phone')]")
	public WebElement editBusinessPhoneDialogHeader;

	@FindBy(how = How.XPATH, using = "//p[.='Line Details']")
	public WebElement lineDetailsHeader;

	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-telephone[not(contains(@class,'nds-hide'))]//input[@type='tel']")
	public List<WebElement> phoneInputBox;

	@FindBy(how = How.XPATH, using = "//label[.='Line Purpose']/parent::div/following-sibling::span/parent::div")
	public WebElement linePurposeDropdown;

	@FindBy(how = How.XPATH, using = "//div[@data-value='Security']")
	public WebElement linePurposeValueSecurity;

	@FindBy(how = How.XPATH, using = "//div[@data-value='Elevator']")
	public WebElement linePurposeValueElevator;

	@FindBy(how = How.XPATH, using = "//div[@data-value='Alarm']")
	public WebElement linePurposeValueAlarm;

	@FindBy(how = How.XPATH, using = "//label[.='Caller ID Name']/parent::div/preceding-sibling::input")
	public WebElement callerIDNameInput;

	@FindBy(how = How.XPATH, using = "//section[@role='dialog']//label[.='Number Type']/parent::div/following-sibling::span/parent::div")
	public List<WebElement> numberTypeDropdown;

	@FindBy(how = How.XPATH, using = "//section[@role='dialog']//span[contains(@class,'option') and .='Native']/ancestor::li/div")
	public List<WebElement> numberTypeValueNative;

	@FindBy(how = How.XPATH, using = "//section[@role='dialog']//span[contains(@class,'option') and .='Ported']/ancestor::li/div")
	public List<WebElement> numberTypeValuePorted;

	@FindBy(how = How.XPATH, using = "//section[@role='dialog']//button[.='Save']")
	public WebElement saveButtonOnDialogBox;

	@FindBy(how = How.XPATH, using = "//img[contains(@class,'complete')]")
	public List<WebElement> dataCompletionInlineImage;

	@FindBy(how = How.XPATH, using = "//div[.='Emergency Disclaimer Attachments']")
	public WebElement emergencyDisclaimerAttachmentsSection;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Emergency Disclaimer documents')]")
	public WebElement emergencyDisclaimerAttachmentsNote;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Edit Porting Request')]")
	public WebElement editPortingRequestDialogHeader;

	@FindBy(how = How.XPATH, using = "//label[.='DSL']/parent::div/following-sibling::span/parent::div")
	public WebElement dslDropdown;

	@FindBy(how = How.XPATH, using = "//div[@data-label='Yes']")
	public WebElement dslValueYes;

	@FindBy(how = How.XPATH, using = "//div[@data-label='No']")
	public WebElement dslValueNo;

	@FindBy(how = How.XPATH, using = "//label[.='Service Provider']/parent::div/preceding-sibling::input")
	public WebElement serviceProviderInputBox;

	@FindBy(how = How.XPATH, using = "//div[.='Invoice Attachments']")
	public WebElement invoiceAtachmentsSection;

	@FindBy(how = How.XPATH, using = "//div[.='Directory Listing']")
	public WebElement directoryListingSection;

	@FindBy(how = How.XPATH, using = "//label[@aria-label='Listing Name']/parent::div/parent::div/input")
	public WebElement listingNameInputBox;

	@FindBy(how = How.XPATH, using = "//label[@aria-label='Listing Number']/parent::div/preceding-sibling::input")
	public WebElement listingNumberInputBox;

	@FindBy(how = How.XPATH, using = "//label[@aria-label='Sub Listing Number']/parent::div/preceding-sibling::input")
	public WebElement subListingNumberInputBox;

	@FindBy(how = How.XPATH, using = "//div[@class='invoice-section']//span[.='Upload Files']")
	public WebElement invoiceAttachmentUploadButton;

	@FindBy(how = How.XPATH, using = "//div[@class='emergency-disclaimer-section']//span[.='Upload Files']")
	public WebElement emergencyDisclaimerUploadButton;

}
