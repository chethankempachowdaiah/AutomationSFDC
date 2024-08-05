package com.sfdc.page_objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 26/Sept/2019, Last Modified By Anukriti,Date:
 *         08/Oct/2020
 *
 *         SFDC Home page objects
 */
public class SFDC_Home_Objects {

	public SFDC_Home_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// div[@data-aura-class='forceEntityIcon']

	@FindAll({
		@FindBy(how = How.XPATH, using = "//img[@title='User']//ancestor::button"),
		@FindBy(how = How.XPATH, using = "//img[contains(@src,'profilephoto')]//ancestor::a")})
	public WebElement userImg;

	@FindBy(how = How.XPATH, using = "//img[@class='chatter-avatarStyle']/parent::span[contains(@class,'globalHeaderProfilePhoto')]")
	public WebElement userImg_B2B;

	@FindBy(how = How.XPATH, using = "//h1[@class='profile-card-name']/a")
	public WebElement profileNameText;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Log Out') or contains(.,'Logout') or contains(.,'Déconnecter')]")
	public WebElement logoutMenu;

	@FindAll({ @FindBy(how = How.CSS, using = "button[aria-label='Show Navigation Menu']"),
		@FindBy(how = How.CSS, using = "button[aria-label='Afficher le menu de navigation']") })
	public WebElement showNavigationMenu;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search this list...']")
	public WebElement searchInput;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Opportunity']/span/span[contains(.,'Opportunities')]")
	public WebElement opportunitiesMenu;

    @FindAll({
	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Account']/span/span[contains(.,'Accounts') or contains(.,'Comptes')]"),
	@FindBy(how = How.XPATH, using = "//a[@title='Accounts']")})
	public WebElement accountsMenu;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Contact']/span/span[contains(.,'Contacts')]")
	public WebElement contactsMenu;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Quote']/span/span[contains(.,'Quotes') or contains(.,'Devis')]")
	public WebElement quotesMenu;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Lead']/span/span[contains(.,'Leads')]")
	public WebElement leadMenu;

	@FindAll({ @FindBy(how = How.XPATH, using = "//a[@data-itemid='Case']/span/span[contains(.,'Cases')]"),
		@FindBy(how = How.XPATH, using = "// a[@data-itemid='Case']/span/span[contains(.,'Requêtes')]") })
	public WebElement casesMenu;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Party Relationships')]")
	public WebElement partyRelationshipsMenu;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='QuickText']/span/span[contains(.,'Quick Text')]")
	public WebElement quickTextMenu;

	@FindAll({ @FindBy(how = How.XPATH, using = "(//*[@title='Select List View'])[last()]"),
		@FindBy(how = How.XPATH, using = "//button[@title='Select a List View' or @title='Sélectionner une vue de liste']") })
	public WebElement listViewIcon;

	@FindBy(how = How.XPATH, using = "(//button[@title='Select a List View'])[2]")
	public WebElement listViewIconNew;
	
	@FindBy(how = How.XPATH, using = "(//div[text()='List Views']/parent::li/following-sibling::li//a)[1]")
	public WebElement allIcon;
	
	@FindBy(how = How.XPATH, using = "//input[@role='combobox' and @aria-autocomplete='list']")
	public WebElement searchInputBox;
	
	@FindBy(how = How.XPATH, using = "//mark[text()='Survey Accounts']/ancestor::a")
	public WebElement surveyAccountsLink;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Relationship Score']/following::tbody//td[4]//span//span")
	public List<WebElement> relationshipScoreValues;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Account Name']/following::tbody//th//span//a")
	public List<WebElement> accountNameLinks;

	@FindBy(how = How.XPATH, using = "//span[@title='SurveyResponses']/following::tbody[2]//th//a")
	public List<WebElement> surveyResponsesIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Orders')]//ancestor::nav//following-sibling::div//a[@title='Select List View']")
	public WebElement ordersListViewIcon;

	//	@FindBy(how = How.XPATH, using = "//span[contains(.,'Manual Queues')]//ancestor::nav//following-sibling::div//a[@title='Select List View']/lightning-icon")
	//	public WebElement manualQueueListViewIcon;

	@FindBy(how = How.XPATH, using = "//div/button[@title='Select List View']")
	public WebElement manualQueueListViewIcon;

	@FindBy(how = How.XPATH, using = "//a[@title='Pending Order']")
	public WebElement pendingOrderButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Close Service Delivery Queue']")
	public WebElement closeServiceDeliveryQueue;

	@FindBy(how = How.XPATH, using = "//button[@title='Close Account Provisioning Queue']")
	public WebElement closeAccountProvisioningQueue;

	@FindBy(how = How.XPATH, using = "//button[@title='Close Office 365']")
	public WebElement closeOffice365;

	@FindBy(how = How.XPATH, using = "//a[@title='Omni Supervisor']/span/span[contains(.,'Omni Supervisor')]")
	public WebElement omniSupervisorMenu;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Accounts and more...']")
	public WebElement searchAccountsAndMoreInput;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'oneConsoleTabItem')]//button[contains(@title, 'Close') or contains(@title,'Fermer')]")
	public List<WebElement> closeTab;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'App Launcher') or contains(text(),'Lanceur d') and contains(text(),'application')]/preceding-sibling::div")
	public WebElement applauncher;

	@FindAll({
		@FindBy(how = How.XPATH, using = "(//input[contains(@placeholder,\"Search this list...\")][@name=\"R4B_Quote_Approval__c-search-input\"])[2]"),
		@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search R4B Quotes Approval and more') or contains(@placeholder,'Recherchez des Devis et plus')]")
	})
	public WebElement r4bSearchBox;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@class='slds-form-element__control slds-grow slds-input-has-icon slds-input-has-icon_left-right']//input[(@placeholder='Search Quotes and more...')]"),
	})
	public WebElement searchBoxFraud;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'slds-truncate outputLookupLink slds-truncate outputLookupLink')]")
	public List<WebElement> quoteName;

	@FindBy(how = How.XPATH, using = "//div//button[contains(text(),'View All')]")
	public WebElement viewAllButton;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search apps or items...']")
	public WebElement searchAppPlaceHolderInput;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Search apps and items...')]/following-sibling::div//input")
	public WebElement searchAppItemInput;

	@FindBy(how = How.XPATH, using = "//a//span//p")
	public List<WebElement> searchedApp;
	
	@FindBy(how = How.XPATH, using = "//a//span//p//b")
	public List<WebElement> searchedAppNew;

	@FindBy(how = How.XPATH, using = "//lightning-formatted-rich-text//mark")
	public List<WebElement> appTileAllLinks;

	@FindBy(how = How.XPATH, using = "//lightning-formatted-rich-text")
	public List<WebElement> appTextAllLinks;

	@FindBy(how = How.XPATH, using = "//span[contains(@title,'Console')]")
	public WebElement consoleTitleText;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'globalCreateTrigger')]")
	public WebElement plusIcon;

	@FindBy(how = How.XPATH, using = "//a//span[contains(.,'Switch to B2B Org')]")
	public WebElement switchToB2BOrg;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Home']/span/span[contains(.,Home)]")
	public WebElement HomeMenu;

	@FindBy(how = How.XPATH, using = "//span[text()='Orders' or text()='Commandes']")
	public WebElement homeMenuOrders;

	@FindBy(how = How.CSS, using = ".active > .tabHeader > .title")
	public WebElement activeTabInHome;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'homeLanding')]//span[@class='title']")
	public List<WebElement> tabItemsInHome;

	@FindBy(how = How.LINK_TEXT, using = "My Activities")
	public WebElement myActivitiesTab;

	@FindBy(how = How.LINK_TEXT, using = "My Accounts")
	public WebElement myAccountsTab;

	@FindBy(how = How.XPATH, using = "//a[contains(@title,'Open Tasks')]")
	public WebElement openTasksSection;

	@FindBy(how = How.XPATH, using = "//h2[contains(@class,'listViewTitle')]//a[contains(@title,'My Accounts')]")
	public WebElement myAccountsSection;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Open Tasks')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'uiVirtualDataTable')]/tbody/tr")
	public List<WebElement> openTasksTableRows;

	@FindBy(how = How.XPATH, using = "//span[.='Last Modified Date/Time']/parent::a")
	public WebElement lastModifiedColumnTaskSection;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Open Tasks')]/ancestor::div[contains(@class,'test-listViewManager')]//tbody/tr//span[contains(@class,'DateTime')]")
	public WebElement lastModifiedColumnValueTaskSection;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'System Information')]/ancestor::div[contains(@class,'test-id__section')]//following-sibling::div//*[contains(text(),'Last Modified By')]/ancestor::div[contains(@class,'slds-form-element')]//span[contains(@class,'DateTime')]")
	public WebElement lastModifiedActualValueInTaskCreation;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'My Accounts')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'uiVirtualDataTable')]/tbody/tr")
	public List<WebElement> accountsTableRows;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'My Accounts')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'uiVirtualDataTable')]/tbody/tr//th//a")
	public List<WebElement> accountNames;

	@FindBy(how = How.XPATH, using = "//a[@title='New Task']")
	public WebElement newTaskButton;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'My Accounts')]/ancestor::div[contains(@class,'test-listViewManager')]//*[.='New']/a")
	public WebElement newAccountButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Log a Sales Call')]")
	public WebElement newTaskLogSalesRadio;

	@FindBy(how = How.XPATH, using = "//div[@class='forceChangeRecordTypeFooter']/button[.='Next']")
	public WebElement newTaskNextButton;

	@FindBy(how = How.XPATH, using = "//span[.='Type']/ancestor::div[contains(@class,'uiInput')]//*[contains(@class,'select')]")
	public WebElement newTaskTypeDropDown;

	@FindBy(how = How.XPATH, using = "//a[@title='Base']")
	public WebElement newTaskTypeBase;

	@FindBy(how = How.XPATH, using = "//a[@title='New Customer']")
	public WebElement newTaskTypeNewCustomer;

	@FindBy(how = How.XPATH, using = "//span[.='Status']/ancestor::div[contains(@class,'uiInput')]//a[contains(@class,'select')]")
	public WebElement newTaskStatusDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Subject')]/ancestor::div[contains(@class,'slds-form-element__control')]//*[contains(@type,'text')]")
	public WebElement newTaskSubjectTextBox;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[.='Select a date']"),
		@FindBy(how = How.XPATH, using = "//button[@title='Select a date']"),
		@FindBy(how = How.XPATH, using = "//a[contains(@class,'datePicker-openIcon display')]")})
	public WebElement newTaskDueDateIcon;

	@FindBy(how = How.XPATH, using = "//button[.='Select a date']/parent::lightning-button-icon/preceding-sibling::input")
	public WebElement newTaskDueDateInput;

	@FindBy(how = How.XPATH, using = "//button[contains(@class,'today')]")
	public WebElement newTaskDueDateToday;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'button-container-inner')]/button[.='Save']")
	public WebElement newTaskSaveButton;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'My Opportunities')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'uiVirtualDataTable')]/tbody/tr")
	public List<WebElement> opportunitiesTableRows;

	@FindBy(how = How.XPATH, using = "//a[contains(@title,'My Opportunities')]")
	public WebElement opportunitiesSection;

	@FindBy(how = How.XPATH, using = "//h2[contains(@class,'listViewTitle')]//a[contains(@title,'My Account Cases')]")
	public WebElement myAccountCasesSection;

	@FindBy(how = How.XPATH, using = "//a[@title='Knowledge']/span/span[contains(.,'Knowledge')]")
	public WebElement knowledgeMenu;

	@FindAll({ @FindBy(how = How.XPATH, using = "//a[.='Paramètres']"),
		@FindBy(how = How.XPATH, using = "//a[.='Settings']") })
	public WebElement userSettingsLink;

	@FindBy(how = How.XPATH, using = "//input[@role='combobox']")
	public WebElement showMenuOptionsForSearch;

	@FindBy(how = How.XPATH, using = "//span[@title='Accounts']")
	public WebElement accountsFromMenuOptionsForSearch;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search...']")
	public WebElement searchBox;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Search...']")
	public WebElement searchBoxFraudOps;

	@FindBy(how = How.XPATH, using = "//span[.='My Performance']")
	public WebElement myPerformance;

	@FindBy(how = How.XPATH, using = "//div[@id=\"dashboard-0FK4g00000001eiGAA-0\"]")
	public List <WebElement> dashBoard;

	@FindBy(how = How.XPATH, using = "//div[@data-tooltip='R4B Sales opportunities']/h1")
	public List <WebElement> salesOpportunities;

	@FindBy(how = How.XPATH, using = "//div[.='Opportunity Stage']")
	public List <WebElement> opportunityStage;

	@FindBy(how = How.XPATH, using = "//div[.='Opportunity Close Date']")
	public List <WebElement> opportunityCloseDate;

	@FindBy(how = How.XPATH, using = "//div[.='Quote Status']")
	public List <WebElement> quoteStatus;

	@FindBy(how = How.XPATH, using = "//div[.='Order Status']")
	public List <WebElement> orderStatus;

	@FindBy(how = How.XPATH, using = "//div[.='Order Created Date']")
	public List <WebElement> orderCreatedDate;

	@FindBy(how = How.XPATH, using = "//div[.='Channel']")
	public List <WebElement> channel;

	@FindBy(how = How.XPATH, using = "//div[.='Sales Segment']")
	public List <WebElement> salesSegment;

	@FindBy(how = How.XPATH, using = "//div[.='Order Close Date']")
	public List <WebElement> orderCloseDate;

	@FindBy(how = How.XPATH, using = "//button[@aria-label='Search' or @aria-label='Rechercher']")
	public WebElement searchBoxButton;

	@FindBy(how = How.XPATH, using = "//p[.='No results']")
	public List<WebElement> noResults;

	@FindBy(how = How.XPATH, using = "//a[@data-itemid='Order']/span/span[contains(.,'Orders')]")
	public WebElement ordersMenu;
	
	@FindBy(how = How.XPATH, using = "//lightning-datepicker//input[@type='text']")
	public WebElement inputDate;

}
