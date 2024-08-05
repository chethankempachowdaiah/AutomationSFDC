package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya,Date: 30/Jan/2019
 * 
 *         SFDC Quote_SelectSiteContact Page(Opportunity>create Quote>Configure
 *         Quote>Accept Quote>Generate Document>Send Email> Select Site Contact)
 *
 */
public class SFDC_Quote_SelectSiteContact_Objects {

	public SFDC_Quote_SelectSiteContact_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public List<WebElement> accessibilityFrame;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'SendEmail failed')]")
	public WebElement sendEmailFailedMsg;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Continue')]")
	public WebElement sendEmailErrorContinueButton;

	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")
	public WebElement errorAlertOkButton;

	@FindBy(how = How.XPATH, using = "//*[@id='SelectSiteContact']//h1[contains(.,'Select Site Contact')]")
	public WebElement selectSiteContactText;

	//Fixed existing iframe issue by pointing to right frame
	@FindBy(how = How.XPATH, using = "//*[@id='SelectSiteContact']//h1[contains(.,'Select Site Contact')]")
	public List<WebElement> selectSiteContactTextList;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//button[@value='continue']"),
		@FindBy(how = How.XPATH, using = "//*[contains(@id,'SiteContact_nextBtn')]")})
	public WebElement selectSiteContactNextButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Add a contact')]")
	public List<WebElement> addAContactButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//iframe[@title='accessibility title']")
	public WebElement addAContactButtonResumeQuoteIFrame;

	@FindBy(how = How.XPATH, using = "//child[5]/div/section/form/div[1]/div/child[2]/div/ng-form/div/ng-include/div/div[2]/span[2]/button")
	public WebElement addAContactButtonAfterResumeQuote;

	@FindBy(how = How.XPATH, using = "//select[@id='Salutation']")
	public WebElement siteConSalutation;

	@FindBy(how = How.XPATH, using = "//input[@id='FirstName']")
	public WebElement siteConFirstName;

	@FindBy(how = How.XPATH, using = "//input[@id='LastName']")
	public WebElement siteConLastName;

	@FindBy(how = How.XPATH, using = "//input[@id='EmailAddress']")
	public WebElement siteConEmailAddress;

	@FindBy(how = How.XPATH, using = "//input[@id='Phone']")
	public WebElement siteConPhone;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Next') and @id='ContactDetails_nextBtn']")
	public WebElement createSiteConNext;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Save for later') and @role='button']")
	public WebElement saveForLaterButton;

	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")
	public WebElement orderSavedInDraftAlertOk;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_SCCreateNewContact']")
	public WebElement siteContactcreateNewContactCheckBox;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//span[@class='nds-radio_faux']"),
		@FindBy(how = How.XPATH, using = "//table[contains(@ng-if,'SiteContactCount ')]//td[1]/label[contains(@class,'checkbox')]/input/following-sibling::span")})
	public List<WebElement> selectExistingContactCheckBoxAll;
	
	@FindBy(how = How.XPATH, using = "//table[contains(@ng-if,'SiteContactCount ')]//td[1]/label[contains(@class,'checkbox')]/input")
	public List<WebElement> selectExistingContactCheckBoxInputTagAll;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//*[@class='table-border']//*[contains(@class,'data-table-body')]//slot/c-dgtl-generic-cell[@data-field-name='ContactName']/div"),
		@FindBy(how = How.XPATH, using = "//label[@class='nds-checkbox']/parent::td/following-sibling::td[1]/div")})
	public List<WebElement> selectExistingContactNameAll;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//*[@class='table-border']//*[contains(@class,'data-table-body')]//slot/c-dgtl-generic-cell[@data-field-name='ContactEmail']/div"),
	@FindBy(how = How.XPATH, using = "//label[@class='nds-checkbox']/parent::td/following-sibling::td[2]/div")})
	public List<WebElement> selectExistingContactEmailAll;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'You may continue but an Order cannot be submitted without a Site Contact')]")
	public WebElement siteContactText;

	@FindBy(how = How.XPATH, using = "//div[@title='Resume Quote']")
	public WebElement resumeQuoteButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//following-sibling::a")
	public List<WebElement> contractsButton;
	
	@FindBy(how = How.XPATH, using = "//span[@class='title' and text()='Related']")
	public WebElement relatedButton;

	@FindBy(how = How.XPATH, using = "//div[@title='Revoke eSignatures']")
	public WebElement revokeESignButton;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Filter text']")
	public WebElement filterInputBox;
	
	@FindBy(how = How.XPATH, using = "//div[@title='New Note']")
	public WebElement newNoteButton;
	
	
	
}
