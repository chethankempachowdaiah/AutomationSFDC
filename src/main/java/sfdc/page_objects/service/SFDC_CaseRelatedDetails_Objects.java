package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Robin.Mangla, Date : 29/OCT/2021
 * 
 *         Case Related Details Page to capture objects related to case for internal fraud case
 *         
 *
 */
public class SFDC_CaseRelatedDetails_Objects {

	public SFDC_CaseRelatedDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Account Search')]/following::input")
	public List<WebElement> accountSearch;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Search Billing Account')]/following::input")
	public List<WebElement> searchBillingAccount;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Search Contact')]/following::input")
	public List<WebElement> searchContact;
			
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Close')]/parent::button")
	public List<WebElement> buttonClose;
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Ticket Number']/parent::vlocity_cmt-omniscript-step/following-sibling::div//vlocity_cmt-button")
	public WebElement closeButton;
	
	@FindBy(how = How.XPATH, using = "(//h1[text()='Ticket Number']/following::vlocity_cmt-omniscript-formula)[3]")
	public WebElement errorMessageAPI;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Case Number')]/parent::div/following-sibling::div")
	public List<WebElement> caseNumber;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Owner')]/parent::div/following-sibling::div//span[@class='displayLabel']//slot")
	public WebElement fraudRiskCaseOwner;
	
	@FindBy(how = How.XPATH, using = "//*[@data-statue='true']")
	public WebElement flexCardValues;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(.,'Status')]/parent::div/following-sibling::div//lightning-formatted-text)[2]")
	public WebElement caseStatus;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(.,'Priority')]//parent::div//following-sibling::div//lightning-formatted-text)[2]")
	public WebElement priorityField;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(.,'Case Number')]/parent::div/following-sibling::div//lightning-formatted-text)[2]")
	public WebElement caseNumberField;
	
	@FindBy(how = How.XPATH, using = "(//span[@title='External Tickets']/parent::a)[1]")
	public WebElement externalTicketsIcon;
	
	@FindBy(how = How.XPATH, using = "(//div[@title='New']/parent::a)[2]")
	public WebElement externalTicketsNew;
	
	@FindBy(how = How.XPATH, using = "(//span[@title='External Tickets']/parent::a)[2]")
	public WebElement externalTicketsIconNew;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='External Tickets']/following::tbody//th//a")
	public WebElement externalTicketNumber;
	
	@FindAll({
	@FindBy(how = How.XPATH, using = "(//button[@title='Edit Status'])[3]"),
	@FindBy(how = How.XPATH, using = "(//button[@title='Edit Status'])[2]")})
	public WebElement statusDropdownBox;
	
	@FindBy(how = How.XPATH, using = "//button[@data-value='In Progress']/following-sibling::div//lightning-icon")
	public WebElement statusDropdown;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-combobox-item[@data-value='Closed']")
	public WebElement closedStatus;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Case']/parent::div/following-sibling::div//a//slot//span")
	public WebElement parentCaseNumber;
	
	@FindBy(how = How.XPATH, using = "//a[@data-label='Comments' and @role='tab']")
	public WebElement commentsTab;
	
	@FindBy(how = How.XPATH, using = "(//a[@title='New' and @role='button'])[2]")
	public WebElement newTab;
		
	@FindBy(how = How.XPATH, using = "//textarea[@role='textbox']")
	public WebElement inputBox;
		
	@FindBy(how = How.XPATH, using = "//button[@type='button' and @title='Save']")
	public WebElement saveButton;
	
	@FindBy(how = How.XPATH, using = "//th[@aria-label='Comment']/following::tbody//td[3]//span")
	public WebElement caseCommentsText;
	
	@FindBy(how = How.XPATH, using = "//span[text()='External Ticket Number']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement etmCaseNumber;
		
	@FindBy(how = How.XPATH, using = "//span[text()='Type 1']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement type1Field;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Type 2']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement type2Field;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Type 3']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement type3Field;

	@FindBy(how = How.XPATH, using = "//span[text()='Type 4']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement type4Field;

	@FindBy(how = How.XPATH, using = "//span[text()='Type 5']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement type5Field;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Flex Attributes']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement flexAttributeField;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Record Type']/parent::div/following-sibling::div//records-record-type//div//div//span")
	public WebElement recordTypeField;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Preferred Communication Method']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement communicationMethodField;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Preferred Communication Channel']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement communicationChannelField;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Product Line']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement productLineField;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Preferred Communication Time']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement communicationTimeField;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Update']")
	public WebElement updateButton;
	
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='AdditionalNotes']")
	public WebElement additionalNotesRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @value='Cancel']")
	public WebElement cancelTicketRadioButton;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-block[@data-omni-key='Cancel']")
	public WebElement cancelTicketConfirmMessage;
	
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @ value='No']")
	public WebElement noRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@type='radio' and @ value='Yes']")
	public WebElement yesRadioButton;
	
	@FindBy(how = How.XPATH, using = "//td[text()='This Ticket is already cancelled.']")
	public WebElement alreadyCanceledMessage;
		
	@FindBy(how = How.XPATH, using = "//c-textarea[@data-id='Comments']//textarea")
	public WebElement additionalNotesInputBox;
    
	@FindBy(how = How.XPATH, using = "//label[text()='Ticket Updated']/parent::div/following-sibling::input")
	public List<WebElement> ticketUpdateMessageConfirmation;

	@FindBy(how = How.XPATH, using = "//h2[text()='Tabs']/following::span[text()='Flex Attributes']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement flexAttributesField;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='Status']/parent::div/following-sibling::div//lightning-formatted-text)[2]")
	public WebElement status;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Parent Case')]/parent::div/following-sibling::div//a//span")
	public WebElement parentCaseField;
	
	@FindBy(how = How.XPATH, using = "//h2//span[@title='Related Cases']/parent::a")
	public WebElement relatedCasesIcon;
		
	@FindAll({
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Cases')]/following::tbody//th//a"),
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Related Cases')]/following::tbody//th//a")})
	public WebElement fraudCaseNumberLink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Case History')]/parent::a")
	public List<WebElement> caseHistoryIcon;
		
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Emails')]/parent::a")
	public List<WebElement> emailsIcon;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Account')]/parent::div/following-sibling::div//a//span")
	public WebElement billingAccountField;
	
}
