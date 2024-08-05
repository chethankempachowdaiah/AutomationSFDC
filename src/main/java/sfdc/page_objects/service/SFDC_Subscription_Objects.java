package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 15/Sept/2020
 *
 *         SFDC Subscriptions page objects
 */
public class SFDC_Subscription_Objects {

	public SFDC_Subscription_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search apps and items...']")
	public WebElement searchAppsAndItemsTextBox;

	@FindBy(how = How.XPATH, using = "//a[@data-label='Subscriptions']")
	public WebElement subsciptionsItem;
	
	@FindBy(how = How.XPATH, using = "//a[@data-label='Subscriptions']/span/span[contains(.,'Subscriptions')]")
	public WebElement subscriptionsMenu;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Subscriptions and more...']")
	public WebElement globalSubscriptionAndMoreSearch;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> subscriptionGlobalSearchResult;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//*[contains(@title,'Select List View')]"),
	@FindBy(how = How.XPATH, using = "//*[contains(@title,'Select a List View')]")})
	public WebElement listViewNavigationButton;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All')]")
	public WebElement allOption;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Name']"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Name']") })
	public List<WebElement> listTitleName;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Name']")
	public WebElement billedSubscriptionsName;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Product Type']")
	public WebElement billedSubscriptionsProductType;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Record Type']")
	public WebElement billedSubscriptionsRecordType;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Phone']")
	public WebElement billedSubscriptionsPhone;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Status']")
	public WebElement billedSubscriptionsStatus;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Subscription Start Date']")
	public WebElement billedSubscriptionsStartDate;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='Billed Subscriptions']/following::span[@title='Subscription End Date']")
	public WebElement billedSubscriptionsEndDate;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Product Type']"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Product Type']") })
	public List<WebElement> listTitleProductType;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Record Type']"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Record Type']") })
	public List<WebElement> listTitleRecordType;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[contains(@title,'Status')]"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Status']") })
	public List<WebElement> listTitleStatus;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[contains(@title,'Billing Account')]")
	public List<WebElement> listTitleBillingAccount;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[contains(@title,'Phone')]"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Phone']") })
	public List<WebElement> listTitlePhone;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[contains(@title,'Subscription Start Date')]"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Subscription Start Date']") })
	public List<WebElement> listTitleSubScriptionStartDate;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[contains(@title,'Subscription End Date')]"),
			@FindBy(how = How.XPATH, using = "//*[contains(.,'Billed Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//span[@title='Subscription End Date']") })
	public List<WebElement> listTitleSubScriptionEndDate;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search Subscriptions and more')]"),
		@FindBy(how = How.XPATH, using = "//input[contains(@title,'Search Subscriptions and more')]")})
	public WebElement globalSearchSubScription;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//tbody/tr")
	public List<WebElement> listTrSubscriptions;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//tbody/tr[5]//td[5]//a")
	public WebElement billingAccountLink;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//tbody/tr//td[3]/span/span")
	public List<WebElement> recordTypeListSubscription;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//tbody/tr//th/span/a")
	public List<WebElement> nameListSubscription;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//tbody/tr/th/span/a")
	public WebElement nameOfFirstSubscription;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Subscriptions')]")
	public WebElement titleSubscriptions;

//	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'SearchResultsGridHeader')]//following-sibling::div//*[@class='slds-truncate'][@title='Name']")
//	public WebElement searchListTitleName;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Subscription')]/following::p[@title='Product Type']")
	public WebElement searchListTitleProductType;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Subscription')]/following::p[@title='Record Type']")
	public WebElement searchListTitleRecordType;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Subscription')]/following::p[@title='Status']")
	public WebElement searchListTitleStatus;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Subscription')]/following::p[@title='Billing Account']")
	public WebElement searchListTitleBillingAccount;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Subscription')]/following::p[@title='Phone']")
	public WebElement searchListTitlePhone;

    @FindAll({
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'slds-grid sfaOutputNameWithHierarchyIcon')]/span[contains(@class,'custom-truncate uiOutputText')]"),
	@FindBy(how = How.XPATH, using = "//div[text()='Account']/following-sibling::slot//lightning-formatted-text")})
	public WebElement billingAccountName;

	@FindBy(how = How.XPATH, using = "//a[@id='relatedListsTab__item']")
	public WebElement relatedTabItem;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Related Contacts']/parent::a")
	public WebElement relatedContacts;

	@FindBy(how = How.XPATH, using = "//span[@class='view-all-label'][contains(text(),'View All')]")
	public List<WebElement> viewAllBilledSubscriptions;

	@FindBy(how = How.XPATH, using = "//a[@class='toggle slds-th__action slds-text-link--reset ']/span[@title='Name']")
	public List<WebElement> listTitleNames;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Subscription Number')]")
	public WebElement sysInfoSubscriptionNumberLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Created By')]")
	public WebElement sysInfoCreatedByLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Last Modified By')]")
	public WebElement sysInfoLastModifiedByLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Last Modified By')]/ancestor::div[contains(@class,'slds-form-element')]//*[contains(@data-output-element-id,'output-field')]/lightning-formatted-text")
	public WebElement sysInfoLastModifiedByValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Created By')]/ancestor::div[contains(@class,'slds-form-element')]//*[contains(@data-output-element-id,'output-field')]/lightning-formatted-text")
	public WebElement sysInfoCreatedByValue;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Subscription Number')]/ancestor::div[contains(@class,'slds-form-element')]//*[contains(@data-output-element-id,'output-field')]")
	public WebElement sysInfoSubscriptionNumberValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'listViewManager')]//tbody//tr[5]//td[4]")
	public WebElement activeStatusSpan;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Subscriptions')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public WebElement subscriptionLinkGlobalSearchResult;

	@FindBy(how = How.XPATH, using = "//h1//lightning-formatted-text")
	public WebElement subscriptionNameHeader;

}
