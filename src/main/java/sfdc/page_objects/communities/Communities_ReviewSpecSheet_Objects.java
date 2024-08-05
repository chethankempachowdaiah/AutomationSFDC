package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Pankaj Agarwal,  ** This class comprises of details on review spec sheet
 *         for Orders in communities, date 26/03/2021
 *
 */
public class Communities_ReviewSpecSheet_Objects {

	public Communities_ReviewSpecSheet_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(@class,'page-title')]")
	public WebElement reviewSpecSheet_PageTitle;

	@FindBy(how = How.XPATH, using = "//h1[contains(@class,'page-title')]//following::div[contains(.,'Product Summary')]/div/div")
	public WebElement reviewSpecSheet_ProductSummary;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Demarcation Location')]/parent::div/parent::div/input")
	public WebElement reviewSpecSheet_EnterDemarcationLocation;

	@FindBy(how = How.XPATH, using = "//input[@data-value = 'MMF']")
	public WebElement handOffInterfaceMMF;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Hand off Interface')]/parent::div/parent::div")
	public WebElement reviewSpecSheet_HandOffInterfaceDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Hand off')]/parent::div/parent::div/parent::div/following-sibling::div[contains(@class,'nds-form-element')]/div/ul/li//span/span")
	public List<WebElement> reviewSpecSheet_HandOffInterfaceDropDownDevStageValues;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Encapsulation Type')]/parent::div/parent::div")
	public WebElement reviewSpecSheet_EncapsulationTypeDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Encapsulation Type')]/parent::div/parent::div/parent::div/following-sibling::div[contains(@class,'nds-form-element')]/div/ul/li//span/span")
	public List<WebElement> reviewSpecSheet_EncapsulationTypeDropDownDevStageValues;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'DNS Required')]/parent::div/parent::div")
	public WebElement reviewSpecSheet_DNSRequiredDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'DNS')]/parent::div/parent::div/parent::div/following-sibling::div[contains(@class,'nds-form-element')]/div/ul/li//span/span")
	public List<WebElement> reviewSpecSheet_DNSRequiredDropDownDevStageValues;

	@FindBy(how = How.XPATH, using = "//button[text()='Submit'][contains(@class,'medium')]")
	public WebElement reviewSpecSheet_SubmitButton;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'VLAN Id')]/parent::div/parent::div/input")
	public WebElement reviewSpecSheet_vLanId;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Do you require a New IP Address from Rogers?')]//following::slot/c-radio-group/div/fieldset/div/span[2]/label/span[1]")
	public WebElement newIpAddressRadioOption;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Do you require a New IP Address from Rogers?')]//following::span[contains(.,'Yes, I do')]/preceding-sibling::span")
	public WebElement newIpAddressYesRadioOption;

	@FindBy(how = How.XPATH, using = "//input[@data-value='IPv4']")
	public WebElement ipVersionDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'IP Version')]/parent::div/parent::div/parent::div/following-sibling::div[contains(@class,'nds-form-element')]/div/ul/li//span/span")
	public List<WebElement> ipVersionDropDownOptionValues;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'IP V4 LAN Block')]/parent::div/../input")
	public WebElement ipV4LanBlockDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'IP V4 LAN Block')]/parent::div/parent::div/parent::div/following-sibling::div[contains(@class,'nds-form-element')]/div/ul/li//span/span")
	public List<WebElement> ipV4LanBlockDropDownOptionValues;
	
	@FindBy(how = How.XPATH, using = "//div[.='Billing Account Information']")
	public WebElement billingInfoSection;
	
	@FindBy(how = How.XPATH, using = "//div[.='Would you like your new Dedicated Internet service added to an existing bill?']")
	public WebElement billingQueBanner;
	
	@FindBy(how = How.XPATH, using = "//div[.='If you select yes, please choose a billing account below to which you want to add your new Dedicated Internet service.']")
	public WebElement billingQueOption1;
	
	@FindBy(how = How.XPATH, using = "//div[.='If you select no, a new bill will be created for your new Dedicated Internet service using the primary business address on file.']")
	public WebElement billingQueOption2;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@class,'radio')]//span[contains(@class,'radio')]")
	public WebElement billingQueRadioYes;
	
	@FindBy(how = How.XPATH, using = "(//label[contains(@class,'radio')]//span[contains(@class,'radio')])[2]")
	public WebElement billingQueRadioNo;
	
	@FindBy(how = How.XPATH, using = "//a[@data-field='AccountName']")
	public WebElement billingAccontDetailsColumnAccName;
	
	@FindBy(how = How.XPATH, using = "//a[@data-field='AccountNumber']")
	public WebElement billingAccontDetailsColumnAccNumber;
	
	@FindBy(how = How.XPATH, using = "//a[@data-field='AccountSource']")
	public WebElement billingAccontDetailsColumnAccSource;
	
	@FindBy(how = How.XPATH, using = "//a[@data-field='BillingAddress']")
	public WebElement billingAccontDetailsColumnAccBillingAddress;
	
	@FindBy(how = How.XPATH, using = "//b[.='No records to display']")
	public WebElement noRecordsToDisplayMessage;
	
	@FindBy(how = How.XPATH, using = "//*[@class='dataTableCell']//span[@class='nds-radio']")
	public WebElement billingRowRadioButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='AccountName']//div")
	public WebElement billingAccountName;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='AccountSource']//div")
	public WebElement billigAccountSource;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='AccountNumber']//div")
	public WebElement billingAccountNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='BillingAddress']//div")
	public WebElement billingAccountAddress;

}
