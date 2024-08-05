package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 23/Nov/2020
 *
 *         SFDC Cases page objects
 */
public class SFDC_Customer_Case_Objects {

	public SFDC_Customer_Case_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public WebElement caseFormIframe;

	@FindBy(how = How.XPATH, using = "//iframe[@id='iFrameResizer5']")
	public WebElement caseFormForBusinessCaseIframe;

	@FindAll({ @FindBy(xpath = "//span[.='New Customer Case']/parent::a"),
			@FindBy(xpath = "//button[.='New Customer Case']"),
			@FindBy(xpath = "//a[@title='Create Customer Case']"),
			@FindBy(xpath = "//a[@name='Create_Customer_Case']"),
			@FindBy(xpath = "//a[@title='CreateCustomerCase']")})
	public WebElement newCustomerCaseButton;
	
	@FindBy(xpath = "//section[contains(@class,'tab')]//a[@title='New Customer Case']")
	public List<WebElement> createCustomerCaseButton;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'active') and contains(@class,'ConsoleTab')]//iframe")
	public WebElement newCusCaseFrame;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Case Type')]")
	public WebElement caseTypeLabel;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Case Type')]")
	public List<WebElement> caseTypeLabelList;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectCaseType'][@value='Service']")
	public WebElement customerServiceCaseTypeRadioButton;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectCaseType'][@value='Tier_1_Tech_Support']")
	public WebElement technicalSupportCaseTypeRadioButton;

	@FindAll({ @FindBy(id = "CaseCreationBasicDetails_nextBtn") })
	public WebElement caseCaseCreationBasicDetailsNextBtn;

	@FindBy(how = How.ID, using = "CaseOrigin")
	public WebElement caseOriginDropdown;

	@FindBy(how = How.ID, using = "ProductFamily")
	public WebElement productFamilyDropdown;

	@FindBy(how = How.ID, using = "Subject")
	public WebElement subjectInputField;

	@FindBy(how = How.ID, using = "Description")
	public WebElement descriptionInputField;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Subscription')]/parent::div//input[contains(@id,'Subscriptions')]")
	public WebElement subscriptionsInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CCF_SearchSubscriptonForContact']")
	public WebElement searchSubscriptionsInput;

	@FindAll({ @FindBy(how = How.XPATH, using = "//input[@id='CCF_SubscriptionSearchOrSelect'][@value='Select']"),
			@FindBy(how = How.XPATH, using = "//input[@id='CCF_SubscriptionSearchOrSelectForContact'][@value='Select']") })
	public WebElement selectSubscriptionRadioButton;

	@FindBy(how = How.XPATH, using = "//input[@id='CCF_SubscriptionSearchOrSelectForContact'][@value='Search']")
	public WebElement searchSubscriptionRadioButton;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Subscription')]/parent::div//input[contains(@id,'Subscriptions')]//following-sibling::ul/li[contains(@ng-click,'Value')]")
	public WebElement subscriptionsValue;
	
	@FindBy(how = How.ID, using = "CCF_BillingAccName")
	public WebElement billingAccountName;
	
	@FindAll({ @FindBy(how = How.ID, using = "CCF_BusinessAccName"),
			@FindBy(how = How.XPATH, using = "//input[@id='CCF_BusinessAccountNameRO']") })
	public WebElement businessAccountName;

	@FindBy(how = How.XPATH, using = "//input[@id='CCF_BusinessAccountNameRO']")
	public List<WebElement> businessAccountNameInFrame;

	@FindBy(how = How.ID, using = "CCF_ServiceAccName")
	public WebElement serviceAccountName;

	@FindBy(how = How.ID, using = "ContactName")
	public WebElement contactName;

	@FindBy(how = How.ID, using = "CCF_CreateNewContact")
	public WebElement createNewContactCheckBox;

	@FindBy(how = How.ID, using = "CCF_CaseAccociatedRecords_nextBtn")
	public WebElement associatedRecordNextButton;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_AccountType' and @value='Business']")
	public WebElement accountTypeBusinessRadioButton;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select Relationship Type')]")
	public WebElement selectRelationshipTypeLabel;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'RelationshipTypeBusiness')]//following-sibling::span[contains(@class,'label')]")
	public List<WebElement> businessRelationshipTypeValues;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[.='General']//preceding-sibling::input[@id='CC_ASRelationshipTypeBusiness']"),
			@FindBy(how = How.XPATH, using = "//span[.='General']//preceding-sibling::input[@id='CC_RelationshipType']") })
	public WebElement relationshipTypeGeneral;

	@FindBy(how = How.ID, using = "CC_SelectAccount")
	public WebElement searchAccountInput;

	@FindBy(how = How.ID, using = "CC_ContactDetails_nextBtn")
	public WebElement contactDetailsNextBtn;

	@FindBy(how = How.ID, using = "CC_CreateNextContact_nextBtn")
	public WebElement reviewContactNextBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_DoNotEmail']")
	public WebElement doNotEmailCheckBox;

	@FindBy(how = How.XPATH, using = "//input[@id='CC_DoNotCall']")
	public WebElement doNotCallCheckBox;

	@FindBy(how = How.ID, using = "CCF_SearchContact")
	public WebElement searchContactInput;

	@FindBy(how = How.ID, using = "SearchBusinessAccountSourceAsContact")
	public WebElement searchBusinessAccount;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//div[contains(text(),'Business Account')]//ancestor::div[contains(@class,'control')]//ul/li/a"),
			@FindBy(how = How.XPATH, using = "//span[contains(text(),'Search Account')]//ancestor::div[contains(@class,'control')]//ul/li/a") })
	public WebElement businessAccountSearchResult;

	@FindBy(how = How.ID, using = "CCF_ProceedWithCaseCreation")
	public WebElement createCaseWithoutRelatedDetailsCheckBox;

	@FindBy(how = How.ID, using = "CCF_SearchBAWithoutBusinessAccount")
	public WebElement searchBillingAccount;
	
	@FindBy(how = How.ID, using = "CCF_SearchBillingAccount")
	public WebElement billingAccount;

	@FindAll({
        @FindBy(how = How.XPATH, using = "//input[@id='SearchBillingAccount']"),
		@FindBy(how = How.ID, using = "SearchBillingAccountBusiness"),		
		@FindBy(how = How.ID, using = "SearchBillingAccount")})
	public WebElement searchBillingAccountForBusiness;

	@FindBy(how = How.XPATH, using = "//input[@value='BillingAccount']")
	public WebElement billingAccRadioButton;
	
	@FindBy(how = How.ID, using = "SearchByOptions_nextBtn")
	public WebElement searchByOptionsNextButton;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Billing Account')]//ancestor::div[contains(@class,'control')]//ul/li/a")
	public WebElement billingAccountSearchResult;

	@FindBy(how = How.ID, using = "CCF_LoadRelatedSubscriptions")
	public WebElement loadSubscriptionCheckBox;

	@FindBy(how = How.ID, using = "LoadSubscriptionsForSelectedBillingAccountWithoutBusinessAcc")
	public WebElement searchSubscriptionInputForBillingAccWithoutBA;

	@FindBy(how = How.XPATH, using = "//span[.='Close Create Customer Case']//parent::button")
	public WebElement closeCreateCusCase;

	@FindBy(how = How.ID, using = "CCF_AccountNumber")
	public WebElement searchServiceAccountInput;

	@FindBy(how = How.ID, using = "CCF_DRExtractPremises")
	public WebElement searchServiceAccountButton;

	@FindBy(how = How.XPATH, using = "//*[@id='DisplayMatchingServiceAddress']//tr[1]//input")
	public WebElement firstServiceAccountInSearchList;

	@FindBy(how = How.ID, using = "CCF_ProductType")
	public WebElement subscriptionName;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Contact')]//ancestor::div[contains(@class,'control')]//ul/li/a")
	public WebElement contactSearchResult;

}
