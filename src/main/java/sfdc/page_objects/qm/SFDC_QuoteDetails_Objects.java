package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 31/JAN/2020
 *
 *         SFDC Quote Details Tab
 */
public class SFDC_QuoteDetails_Objects {

	public SFDC_QuoteDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//li//a[@class='tabHeader']//span[@class='title' and text()='Details' or text()='Détails']")
	public List<WebElement> quoteDetailsTab;
	
	@FindBy(how = How.XPATH, using = "//li//a[@class='tabHeader']//span[@class='title' and text()='Related']")
	public List<WebElement> quoteRelatedTab;

	@FindBy(how = How.XPATH, using = "//span[text()='Status' or text()='État']/parent::div//following-sibling::div//span//span")
	public WebElement quoteStatusValueText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Status']/parent::div//following-sibling::div//span//span")
	public List<WebElement> quoteStatusValueTextList;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "//span[contains(text(),\"Quote Finalized – Select/Add Site Contact from the\")]"),
	@FindBy(how = How.XPATH, using = "//span[@class='notification-text-title uiOutputText']/Add Site Contact from the")})
	public WebElement taskNotificationText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Related To']/parent::div//following-sibling::div//span//a")
	public WebElement orderID;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'test-id__field-value slds-form-element__static slds-grow')]//span[@class='uiOutputText']")
	public List<WebElement> taskSubject;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Account Name']/parent::div//following-sibling::div//span[contains(@class,'test-id__field-value')]//a[@data-refid='recordId']")
	public WebElement quoteAccountNameValueLinkText;

	@FindBy(how = How.XPATH, using = "//div[@class='headerButtonBody']//*[name()='path' and contains(@d,'M46 33h-.5')]")
	public List<WebElement> notificationIcon;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Credit Check Status') or contains(text(),'État de la vérification de crédit')]/parent::div/following-sibling::div/span/span")
	public WebElement quoteCreditCheckStatusText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Fraud Review Status')]/parent::div/following-sibling::div/span/span")
	public WebElement quoteFraudReviewStatusText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Validation Check')]/parent::div/following-sibling::div/span/span")
	public WebElement quoteValidationCheckStatusText;
	
	//contractStatus
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Credit Check Status')]/parent::div/following-sibling::div/span/span")
	public WebElement contractStatusText;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//div[@class='outputLookupContainer forceOutputLookupWithPreview' and contains(.,'App')]/a")
	public List<WebElement> quoteApprovalNumber;
	
	@FindAll({@FindBy(xpath = "//*[text()='Quote Name' or text()='Nom du devis']/ancestor::div/following-sibling::div/span/span"),
		@FindBy(xpath = "//span[contains(text(),'Quote Name')]/parent::div/following-sibling::div/span/span")})
	public WebElement quoteNameText;

	@FindBy(how = How.XPATH, using = "//span[text()='Opportunity Name']/parent::div//following-sibling::div//span[contains(@class,'test-id__field-value')]//a[@data-refid='recordId']")
	public WebElement opportunityNameLink;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Service Account']/parent::div//following-sibling::div//span[contains(@class,'test-id__field-value')]//a[@data-refid='recordId']")
	public List<WebElement> serviceAccount;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Premises']//parent::div//following-sibling::div//span//a//span")
	public WebElement premises;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Street Address']/parent::div//following-sibling::div//span//slot//slot//lightning-formatted-text")
	public WebElement streetAddress;
	
	@FindBy(how = How.XPATH, using = "//span[text()='City']/parent::div//following-sibling::div//span//slot//slot//lightning-formatted-text")
	public WebElement city;
	
	@FindBy(how = How.XPATH, using = "//span[text()='State']/parent::div//following-sibling::div//span//slot//slot//lightning-formatted-text")
	public WebElement state;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Postal Code']/parent::div//following-sibling::div//span//slot//slot//lightning-formatted-text")
	public WebElement postalCode;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Country']/parent::div//following-sibling::div//span//slot//slot//lightning-formatted-text")
	public WebElement country;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//div[@class='outputLookupContainer forceOutputLookupWithPreview' ]/following::span[.='In Progress']")
	public List<WebElement> fraudOrCreditRequired;
	
	@FindBy(xpath = "//span[contains(text(),'QuoteNumber') or contains(text(),'Numéro du devis') or contains(text(),'Quote Number')]/ancestor::div/following-sibling::div/span/span")
	public WebElement quoteNumberText;
	
	@FindBy(xpath="//button[contains(@title,'Edit Total Credit Limit Assigned')]")
	public WebElement quoteEditTotalCreditLimitButton;
	
	@FindBy(xpath="//label[contains(text(),'Total Credit Limit Assigned')]//ancestor::records-record-layout-row//input")
	public WebElement quoteEditTotalCreditLimitTextBox;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	public WebElement quoteEditTotalCreditLimitSaveButton;
	
	
	
	
}
