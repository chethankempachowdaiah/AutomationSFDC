package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 27/Sept/2019
 *
 *         SFDC Account page objects
 */
public class SFDC_Accounts_Objects {

	public SFDC_Accounts_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//lightning-icon[@class='slds-button__icon slds-icon-utility-down slds-icon_container forceIcon']//*[@class='slds-icon slds-icon-text-default slds-icon_x-small']")
	public WebElement accountsNavigationButton;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Accounts') or contains(text(),'Tous les comptes')]"),
			@FindBy(how = How.XPATH, using = "(//div[@class='list uiAbstractList forceVirtualAutocompleteMenuList']//div//ul//li//a//span[contains(text(),'Recently Viewed')])[1]") })
	public WebElement allAccountsOption;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'My Accounts')]")
	public WebElement myAccountsOption;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'Business')]"),
			@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Business Accounts')]") })

	public WebElement businessAccountsOption;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'Recently Viewed') or contains(text(),'Récemment visualisés')]")
	public WebElement recentlyViewedAccountOption;

	@FindBy(how = How.XPATH, using = "//tbody//tr")
	public List<WebElement> allRecordRows;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId']")
	public List<WebElement> accountsAllRecords;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td//a[@data-refid='recordId']")
	public List<WebElement> parentAccountsAllRecords;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th/span/span[contains(@class,'slds')]")
	public List<WebElement> statusTextRows;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Opportunity')]/parent::div/following-sibling::div//input[@placeholder='Search']")
	public WebElement opportunitySearchInput;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case')]/parent::div/following-sibling::div//input[@placeholder='Search']")
	public WebElement caseSearchInput;

	@FindBy(how = How.XPATH, using = "//td[@data-label='Owner']")
	public List<WebElement> opportunityOwnerAllRecords;

	@FindBy(how = How.XPATH, using = "//td[@data-label='Stage']")
	public List<WebElement> opportunityStageAllRecords;

	@FindBy(how = How.XPATH, using = "//td[@data-label='Status']")
	public List<WebElement> caseStatusAllRecords;

	@FindBy(how = How.XPATH, using = "//button[@class='slds-button slds-button_icon-border-filled']")
	public List<WebElement> controlDropDown;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Create Contact')]")
	public List<WebElement> createContactIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Primary Contact')]")
	public List<WebElement> changePrimaryContactIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Employee Size')]")
	public List<WebElement> changeEmployeeSizeIcon;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'New Service Account')]")
	public List<WebElement> newServiceAccountIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Business Address')]")
	public List<WebElement> changeBusinessAddress;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Primary Contact')]")
	public List<WebElement> changePrimaryContact;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_TypeAheadPrimaryContact']")
	public WebElement selectPrimaryContactTextBox;

	@FindBy(how = How.XPATH, using = "//p[@title='Contact']/following-sibling::p/descendant::a")
	public WebElement primaryContactIcon;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_CBUpdatePrimaryContact']")
	public List<WebElement> changePrimaryContactCheckBox;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'New Customer Case')]")
	public WebElement newCustomerCaseIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Change Legal Name')]")
	public List<WebElement> changeLegalNameIcon;

	@FindBy(how = How.XPATH, using = "//div[@ng-repeat='action in data.actions']//a[contains(@title,'Create') and contains(@title, 'Relationship')]")
	public List<WebElement> createRelationshipIcon;

	@FindBy(how = How.XPATH, using = "//div[@ng-repeat='action in data.actions']//a[contains(@title,'Remove') and contains(@title, 'Relationship')]")
	public List<WebElement> removeRelationshipIcon;

	@FindBy(how = How.XPATH, using = "//a//div[contains(.,'New')]")
	public WebElement newAccountButton;
	
	@FindBy(how = How.XPATH, using = "//a//div[contains(.,'New LWC')]")
	public WebElement newAccountButtonLWC;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//input[@id='CA_ShowAccountType']//following-sibling::span[contains(.,'Business')]"),
			@FindBy(how = How.XPATH, using = "//input[@id='CA_ShowAccountTypeForPartners']//following-sibling::span[contains(.,'Business')]") })
	public List<WebElement> businessAccountRadio;

	@FindBy(how = How.XPATH, using = "//input[@id='CA_ShowAccountType']//following-sibling::span[contains(.,'Service')]")
	public List<WebElement> serviceAccountRadio;

	@FindBy(how = How.XPATH, using = "//input[@id='CA_ShowAccountType']//following-sibling::span[contains(.,'Billing')]")
	public List<WebElement> billingAccountRadio;

	@FindBy(how = How.XPATH, using = "//div[@id='CA_SelectAccountType_nextBtn' and @role='button']/p[contains(.,'Next')]")
	public WebElement selectAccountType_nextBtn;

	@FindBy(how = How.XPATH, using = "//p[@title='Please review the change in employee size for the account referenced']//a")
	public List<WebElement> employeeSizeChangeCustomerStory;

	@FindBy(how = How.XPATH, using = "//input[@role='combobox']")
	public WebElement allSearchDropdown;

	@FindBy(how = How.XPATH, using = "//span[@title='Accounts']")
	public WebElement allSearchAccountOption;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search Accounts and more') or @placeholder='Search...'] |//input[contains(@title,'Search Accounts and more')]")
	public WebElement globalAccountSearch;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Accounts and more...']")
	public WebElement globalSearch;

	@FindBy(how = How.XPATH, using = "//*[@aria-label='Search' or @aria-label='Rechercher']")
	public WebElement search;

	@FindBy(how = How.XPATH, using = "//*[contains(@placeholder,'Search..') or contains(@placeholder,'Recherchez des Comptes et plus...') or contains(@placeholder,'Search Accounts and more..')]")
	public WebElement searchAccountGlobalAfterClick;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Accounts and more...']")
	public WebElement globalAccountAndMoreSearch;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@aria-label='Search']"),
			@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Accounts and more...')]") })
	public WebElement accountSearchOptions;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Minimize')]/parent::button/child::lightning-primitive-icon")
	public WebElement minimizeButton;

	@FindBy(how = How.XPATH, using = "//input[contains(@title,'Search Opportunities and more')] | //input[contains(@placeholder,'Search Opportunities and more')]")
	public WebElement globalOpportunitySearch;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Accounts') or contains(.,'Comptes')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> accountLinkGlobalSearchResult;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Accounts') or contains(.,'Comptes')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr/td[3]/span/span")
	public List<WebElement> billingAccountNoGlobalSearchResult;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='viewMore'])[1]")
	public WebElement accountViewMore;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Opportunities')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> opportunitiesLinkGlobalSearchResult;

	// @FindBy(how = How.XPATH, using =
	// "//h2[contains(.,'Accounts')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//td[1]//a")
	// public List<WebElement> accountTypeGlobalSearchResult;
	@FindAll({
			@FindBy(how = How.XPATH, using = "//h2[contains(.,'Accounts')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//td[2]/span"),
			@FindBy(how = How.XPATH, using = "//h2[contains(.,'Accounts')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//td[2]/span/span") })
	public List<WebElement> accountTypeGlobalSearchResultOption2;

	@FindAll({
			@FindBy(xpath = "//h2[contains(.,'Accounts') or contains(.,'Comptes')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//td[2]/span/span"),
			@FindBy(xpath = "//h2[contains(.,'Accounts') or contains(.,'Comptes')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//td[1]//a") })
	public List<WebElement> accountTypeGlobalSearchResult;

	@FindBy(how = How.XPATH, using = "//span[@title='Legal Name']")
	public WebElement legalNameColumn;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Accounts')]/ancestor::div[contains(@class,'SearchResult')]//tbody//tr//th//a")
	public List<WebElement> accountLinkSearchResult;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'forceSearchResultsMultiScope')]//span[contains(text(),'Status')]/following::tr//td[8]")
	public List<WebElement> statusFieldAllColumnValues;

	@FindBy(how = How.XPATH, using = "//span[text()='Links']")
	public WebElement links;

	@FindBy(how = How.XPATH, using = "//a[text()='Voice of R4B']")
	public WebElement voiceOfLinks;

	@FindBy(how = How.XPATH, using = "//div[@id='title_square']//div//text()")
	public WebElement titleVoiceOfR4B;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Service Accounts')]")
	public WebElement allServiceAccountOption;

	@FindBy(how = How.XPATH, using = "//a[text()='Create New Salesforce Case']")
	public WebElement createNewCase;

	@FindBy(how = How.XPATH, using = "//a[text()='Create New Compensation Case']")
	public WebElement createCompensationCase;

	@FindBy(how = How.XPATH, using = "//a[text()='Salesforce Classic Issues and Workarounds']")
	public WebElement salesforceClassIAW;

	@FindBy(how = How.XPATH, using = "//a[text()='Salesforce Lightning Sales Support Chatter Group']")
	public WebElement salesforceLSSCG;

	@FindBy(how = How.XPATH, using = "//a[text()='Wireless Offer Grid']")
	public WebElement wirelessOfferGrid;

	@FindBy(how = How.XPATH, using = "//a[text()='Wireline Offer Grid']")
	public WebElement wirelineOfferGrid;

	@FindBy(how = How.XPATH, using = "//a[text()='Mission Possible Hub']")
	public WebElement missionPosiibleHub;

	@FindAll({ @FindBy(how = How.XPATH, using = "(//div[@role='option']//span[contains(@title,'accounts')])[1]"),
			@FindBy(how = How.XPATH, using = "//span//mark[normalize-space()='accounts']") })
	public WebElement accGlobalSearchMark;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'accounts')]/ancestor::div[contains(@class,'predictedResults')]//tbody//tr//th//span//a")
	public List<WebElement> accountLinkGlobalSearchItems;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'accounts')]/ancestor::div[contains(@class,'predictedResults')]//tbody//tr//td[2]//span//span"),
			@FindBy(how = How.XPATH, using = "//h2//span[contains(.,'accounts')]/ancestor::div[contains(@class,'predictedResults')]//tbody//tr//td[2]//span"), })
	public List<WebElement> accountTypeGlobalSearchResultItems;

	@FindBy(how = How.XPATH, using = "//span[@title=\"Status\"]/following::thead/following::tbody//tr//td[7]")
	public List<WebElement> statusFieldAllColumnItems;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Business Accounts')]")
	public WebElement allBusAccountOption;
	
	@FindBy(xpath="//span[contains(text(),'Total Credit Limit Assigned')]//parent::div//following-sibling::div//span//lightning-formatted-text")
	public WebElement totalCreditLimitAssignedText;

}
