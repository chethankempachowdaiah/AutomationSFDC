package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 14/jan/2020
 * 
 *         SFDC Create Opportunity Page
 *
 */
public class SFDC_CreateOpportunity_Objects {

	public SFDC_CreateOpportunity_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// @FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	// public WebElement selectAproductiFrame;

	@FindBy(how = How.XPATH, using = "//div[@class='oneAlohaPage']//iframe[@title='accessibility title']")
	public WebElement selectAproductiFrame;

	@FindBy(how = How.XPATH, using = "//select[@id='AvailableSites']")
	public WebElement availableSitesDropdown;
	
	@FindBy(how = How.XPATH, using = "//select[@id='R4ProductOptions']")
	public WebElement availableR4BProductOptions;
	
	@FindBy(how = How.XPATH, using = "//select[@id='NeedAccessToClassicData']")
	public WebElement availableSecondDD;
	
	@FindBy(how = How.XPATH, using = "//select[@id='Non-NegotiableContractSign']")
	public WebElement availableThirdDD;

	@FindBy(how = How.XPATH, using = "//select[@id='AvailableProductType']")
	public WebElement availableProductTypeDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='AvailableProducts']")
	public WebElement availbleProductsDebug3;

	@FindBy(how = How.XPATH, using = "//select[@id='AvailableSubProducts']")
	public WebElement availbleSubProductsDebug3;

	@FindBy(how = How.XPATH, using = "//div[@id='SiteAndProductTypeSelection_nextBtn']")
	public WebElement siteAndProductTypeSelectionNextButton;
	
	@FindBy(how = How.XPATH, using = "//div[@id='OpportunityRedirectQuestions_nextBtn']")
	public WebElement opportunityRedirectQuestionsNextButton;

	@FindBy(how = How.XPATH, using = "//a//lightning-icon//span[contains(.,'vlocity_cmt__OmniScriptUniversalPage')]")
	public WebElement opportunitySelectproductTab;

	@FindBy(how = How.XPATH, using = "//select[@id='AvailableSingleProduct']")
	public WebElement productFamilyDropdown;

	@FindBy(how = How.XPATH, using = "//select[contains(@id,'AvailableSubSingle')]")
	public WebElement productDropdown;

	@FindBy(how = How.XPATH, using = "//div[@id='ProductSelection_nextBtn']")
	public WebElement productSelection_nextBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='ECC_NoMatchReturned_nextBtn']")
	public WebElement fileNotFound_nextBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='CompanyName']")
	public WebElement opportunityCompanyNameText;

	@FindBy(how = How.XPATH, using = "//input[@id='CloseDate']")
	public WebElement opportunityClosedDateInput;

	@FindBy(how = How.XPATH, using = "//select[@id='Stage']")
	public WebElement opportunityStageDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='Type']")
	public WebElement opportunityTypeDropdown;

	@FindBy(how = How.XPATH, using = "//input[@id='Amount']")
	public WebElement opportunityAmountInput;

	@FindBy(how = How.XPATH, using = "//select[@id='IsOppInfluencedByCampaign']")
	public WebElement opportunityCampaignInfluencerDropdown;
	
	@FindBy(how = How.XPATH, using = "//select[@id='CreditSelect']")
	public WebElement businessCreditInput;

	@FindBy(how = How.XPATH, using = "//input[@id='NextStep']")
	public WebElement opportunityNextStepInput;

	@FindBy(how = How.XPATH, using = "//div[@id='PromptForOpportunityInformation_nextBtn']")
	public WebElement promptForOpportunityInformation_nextBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='CreateContact' and @type='checkbox']/following-sibling::span")
	public WebElement opportunityCreateContactCheckbox;

	@FindBy(how = How.XPATH, using = "//div[@id='ContactList_nextBtn']/p")
	public WebElement opportunityContactList_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(.,'Account Name')]/parent::div/following-sibling::div//span//a//span")
	public WebElement accountName;
	
	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(.,'Opportunity Owner')]/parent::div/following-sibling::div//span//a")
	public WebElement opportunityOwner;
	
	@FindBy(how = How.XPATH, using = "//div[@class='primaryFieldAndActions truncate primaryField highlightsH1 slds-m-right--small']//span[@class='uiOutputText']")
	public WebElement aeName;
	
	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']//span[@class='forceOutputPhone ']")
	public List<WebElement> aeTelNum;
	
	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-value slds-form-element__static slds-grow  is-read-only']//a[@class='emailuiFormattedEmail']")
	public WebElement aeEmailID;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::thead/following-sibling::tbody/tr/td[3]")
	public List<WebElement> opportunityContactNameList;

	@FindBy(how = How.XPATH, using = "//*[@id='Contact']/div/ng-include/div/table/tbody/tr/td[3]/div")
	public List<WebElement> opportunityExistingContactNameList;

	@FindAll({@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::thead/following-sibling::tbody/tr/td[1]/label//span"),
	@FindBy(how = How.XPATH, using = "//div[@title='Select']/ancestor::thead/following-sibling::tbody/tr/td[1]/label//span")})
	public List<WebElement> opportunitySelectContactListCheckbox;

	@FindAll({@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::thead/following-sibling::tbody/tr/td[5]//select"),
		@FindBy(how = How.XPATH, using = "//div[@title='Select']/ancestor::thead/following-sibling::tbody/tr/td[5]//select"	)})
	public List<WebElement> opportunityContactRoleListDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[.='Internet and Advanced Networks']")
	public WebElement internetAndAdvancedNetworksButton;
	
	@FindBy(how = How.XPATH, using = "//input[@value='IAN-BUSIN']//following-sibling::label//span[.='Business Internet']")
	public WebElement businessInternetButton;
	
	@FindBy(how = How.XPATH, using = "//span[.='Next']//ancestor::button")
	public WebElement nextButtonTran;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@id='comboboxId-570']"),
		@FindBy(how = How.XPATH, using = "//input[@autocomplete='new-password']") })
	public WebElement tranOppCampQues;
		
	@FindBy(how = How.XPATH, using = "//div[@data-value='No']")
	public WebElement selectNo;
	
	@FindBy(how = How.XPATH, using = "//ng-form[@id='ECCFileNotFoundMsg']//ancestor::h5/text()")
	public WebElement messageFNF;


}
