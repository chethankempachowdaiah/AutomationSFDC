package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date 17/Jan/2020
 * 
 *         Opportunity Details Page
 * 
 * 
 *
 */
public class SFDC_OpportunityDetails_Objects {

	public SFDC_OpportunityDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='test-id__field-label'][contains(text(),'Opportunity Name')]/parent::div/following-sibling::div//span//lightning-formatted-text")
	public WebElement opportunityDetailsNameValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(.,'Account Name')]/parent::div/following-sibling::div//span//a//span")
	public WebElement opportunityDetailsAccountNameValueText;

	@FindBy(xpath = "//button[@title='Edit Type']/parent::div/child::span/child::slot/child::lightning-formatted-text")
	public WebElement opportunityDetailsTypeValueText;
	
	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][starts-with(text(),'Opportunity Record Type')]/parent::div/following-sibling::div//span//span")
	public WebElement opportunityRecordTypeValueText;

	@FindBy(xpath = "//span[@class='test-id__field-label'][contains(text(),'Close Date')]/parent::div/following-sibling::div//span//lightning-formatted-text")
	public WebElement opportunityDetailsCloseDateValueText;

	@FindBy(xpath = "//span[@class='test-id__field-label'][contains(text(),'Stage')]/parent::div/following-sibling::div//span//lightning-formatted-text")
	public WebElement opportunityDetailsStageValueText;

	@FindBy(xpath = "//span[@class='test-id__field-label'][contains(text(),'Next Step')]/parent::div/following-sibling::div//span//lightning-formatted-text")
	public WebElement opportunityDetailsNextStepValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Opportunity Owner')]/parent::div/following-sibling::div//span//a//span")
	public WebElement opportunityDetailsOpportunityOwnerValueText;

	@FindBy(xpath = "//span[@class='test-id__field-label'][text()='Amount']/parent::div/following-sibling::div//span//lightning-formatted-text")
	public WebElement opportunityDetailsAmountValueText;

	@FindBy(xpath = "//span[contains(.,'Opportunity Score')]")
	public WebElement scoreLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Opportunity Score')]//parent::div//following-sibling::lightning-helptext//button//lightning-primitive-icon")
	public WebElement scoreInfoIcon;

	@FindAll({
			@FindBy(xpath = "//body//lightning-primitive-bubble//div[contains(.,'The Opportunity Score uses data science and machine learning')]"),
			@FindBy(xpath = "//lightning-primitive-bubble//div[contains(.,'The Opportunity Score uses data science and machine learning')]") })
	public WebElement scoreInformationText;

	@FindAll({
			@FindBy(xpath = "//span[contains(.,'Closed Lost Primary Reason')]//parent::div//following-sibling::div//span[1]//span"),
			@FindBy(xpath = "//span[contains(.,'Closed Lost Primary Reason')]//parent::div//following-sibling::div//span[1]//lightning-formatted-text") })
	public WebElement closedLostPrimaryReasonValueText;

	@FindAll({
			@FindBy(xpath = "//span[contains(.,'Closed Lost Secondary Reason')]//parent::div//following-sibling::div//span[1]//span"),
			@FindBy(xpath = "//span[contains(.,'Closed Lost Secondary Reason')]//parent::div//following-sibling::div//span[1]//lightning-formatted-text") })
	public WebElement closedLostSecondaryReasonValueText;

	@FindAll({
			@FindBy(xpath = "//span[contains(.,'Competitor Lost To')]//parent::div//following-sibling::div//span[1]//span"),
			@FindBy(xpath = "//span[contains(.,'Competitor Lost To')]//parent::div//following-sibling::div//span[1]//lightning-formatted-text") })
	public WebElement closedLostCompetitorToValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Forecast Category')]//parent::div//following-sibling::div//button[@title='Edit Forecast Category']")
	public WebElement editForcastCategoryButton;

	@FindAll({ @FindBy(xpath = "//span[contains(.,'Forecast Category')]//parent::span//following-sibling::div//a"),
			@FindBy(xpath = "//span[contains(.,'Forecast Category')]//parent::span//following-sibling::div//button") })
	public WebElement forcastCategoryEditDropdownLink;

	@FindBy(how = How.XPATH, using = "//lightning-base-combobox-item[@role='option']//span//span")
	public List<WebElement> forcastCategoryAllLinks;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Save')]")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//button[@name='CancelEdit' and contains(.,'Cancel') or @title='Cancel']")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//div[.='Opportunity']//ancestor::div[contains(@class,'header')]//span[.='Show more actions']//parent::button")
	public WebElement showMoreActionsButton;
	
	@FindBy(how = How.XPATH, using = "//input[@name='NextStep']")
	public WebElement oppNextStep;
	
	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit' and contains(.,'Save') or @title='SaveEdit']")
	public WebElement oppSave;
}
