package sfdc.page_objects.om;

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
 *         SFDC Order Details Tab
 */
public class SFDC_OrderDetails_Objects {

	public SFDC_OrderDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ 
		@FindBy(xpath = "//a[@title='Details']//span[contains(.,'Details')]"),	
		@FindBy(xpath = "//span[contains(.,'Details') or contains(.,'Détails')]"),
		@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//a[@title='Details']//span[contains(.,'Details')]") })
	public WebElement orderDetailsTab;
	
	@FindBy(xpath = "//a[@title='Related' or @title='Associé']")
	public WebElement orderRelatedTab;

	@FindAll({ 
		@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//a[@title='Details' or @title='Détails']//span[contains(.,'Details') or  contains(.,'Détails')]"),
		@FindBy(how = How.XPATH, using = "//a[@title='Details' or @title='Détails']//span[contains(.,'Details') or contains(.,'Détails')]")})
	public WebElement orderDetailsTabs;

	@FindBy(xpath = "//span[text()='Status' or text()='Statut']//parent::div//following-sibling::div//span//span")
	public List<WebElement> statusFieldValueText;
	
	@FindBy(xpath = "//span[text()='Credit Check Status' or contains(text(),'État de la vérification de crédit')]//parent::div//following-sibling::div//span//span")
	public List<WebElement> creditStatusText;
	
	@FindBy(xpath = "//span[text()='Fraud Review Status' or contains(text(),'examen pour fraude')]//parent::div//following-sibling::div//span//span")
	public List<WebElement> fraudStatusText;
	
//	@FindAll({ @FindBy(xpath = "//th[contains(.,'Contract') or contains(.,'Nom du contrat')]/ancestor::thead/following-sibling::tbody/tr/td[4]/span/span"),
//		@FindBy(xpath = "//div[@title='Status:']//following-sibling::div//span") })
//	public List<WebElement> contractStatusFieldValueText;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//th[contains(.,'Contract') or contains(.,'Nom du contrat')]/ancestor::thead/following-sibling::tbody/tr/td[4]/span/span"),
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Contracts') or contains(.,'Nom du contrat')]/ancestor::div[contains(@class,'page-header forceRelatedListCardHeader')]/following-sibling::div//div[@title='Status:']/following-sibling::div/span")})
	public List<WebElement> contractStatusFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Order Status' or text()='État de la commande']//parent::div//following-sibling::div//span//span")
	public WebElement orderStatusFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Order Number' or text()='Numéro de commande']//parent::div//following-sibling::div//span//span")
	public WebElement orderNumberFieldValueText;
	
	@FindBy(how = How.XPATH, using = "//th[text()='Order Number']/following::th//div//a")
	public WebElement orderNumberInQuoteRelated;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Ship To Contact')]/following-sibling::div")
	public WebElement shipToContactFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Service Account']//parent::div//following-sibling::div//span//a")
	public WebElement serviceAccountValueLink;

	// Billing And Service Account Asset verification locators
	@FindBy(how = How.XPATH, using = "//span[text()='Billing Account']//parent::div//following-sibling::div//span//a")
	public WebElement billingAccountLink;

	@FindAll({ @FindBy(how = How.XPATH, using = "//span[text()='Status']/parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text"),
		@FindBy(how = How.XPATH, using = "//span[text()='Status']/parent::div/following-sibling::div/span/slot/lightning-formatted-text")})
	public WebElement billingAccountStatus;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Account Source']/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement billingAccountSource;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Account Record Type']/parent::div/following-sibling::div//span[text() = 'Billing']")
	public WebElement billingAccountRecordType;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[text()='Account Record Type']/parent::div/following-sibling::div//span[text() = 'Service']"),
		@FindBy(how = How.XPATH, using = "//span[text()='Account Number']/parent::div/following-sibling::div/span/slot/lightning-formatted-text)='Account Record Type']/parent::div/following-sibling::div//span[text() = 'Service']") })
	public WebElement serviceAccountRecordType;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[text()='Account Number']/parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text"),
		@FindBy(how = How.XPATH, using = "//span[text()='Account Number']/parent::div/following-sibling::div/span/slot/lightning-formatted-text") })
	public WebElement billingAccountNumber;

//	@FindBy(how = How.XPATH, using = "//span[text()='Account Number']/parent::div/following-sibling::div/span/slot/slot/lightning-formatted-text")
//	public WebElement billingAccountNumber;

	@FindBy(how = How.XPATH, using = "//span[text()='Invoiced By']/parent::div/following-sibling::div//a")
	public WebElement billingAccountInvoicedLink;

	@FindBy(how = How.XPATH, using = "//li/a[text()='Related']")
	public List<WebElement> billingAccountRelatedTab;

	@FindBy(how = How.XPATH, using = "//span[@title='Billing Assets']//following::div[@class='slds-card__footer']/span")
	public List<WebElement> invoiceBillingAssetsLink;

	//
	@FindAll({ @FindBy(xpath = "//span[text()='Account Name']//parent::div//following-sibling::div//span//a"),
		@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//span[text()='Account Name']//parent::div//following-sibling::div//span//a") })
	public WebElement businessAccountValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Ship To Contact']//parent::div//following-sibling::div//span//a")
	public WebElement siteContactValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Company Authorized By']//parent::div//following-sibling::div//span//a")
	public WebElement companyAuthorValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Customer Authorized By']//parent::div//following-sibling::div//span//a")
	public WebElement customerAuthorValueText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Email')]/parent::div/following-sibling::div/span/a")
	public WebElement companyAuthorEmailId;

	@FindBy(how = How.XPATH, using = "//span[@title='Effective Total: Monthly Charges']/following-sibling::div/div/span[@class='forceOutputCurrency']")
	public WebElement effectiveMonthCharge;

	@FindBy(how = How.XPATH, using = "//span[@title='Effective Order Total']/following-sibling::div/div/span[@class='forceOutputCurrency']")
	public WebElement effectiveOrderTotal;

	@FindBy(how = How.XPATH, using = "//span[text()='Created By']//parent::div//following-sibling::div//span//a")
	public WebElement createdByValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Quote']//parent::div//following-sibling::div//span//a")
	public WebElement QuoteLink;

	@FindBy(how = How.XPATH, using = "//div[@title='Select site contact']")
	public WebElement selectSiteContactButton;

	@FindBy(how = How.XPATH, using = "//div[@title='Cancel Order']")
	public WebElement cancelOrderButton;

	@FindBy(how = How.XPATH, using = "//input[@id='Cancel']")
	public List<WebElement> cancelOrderConfirmationCheckbox;

	@FindBy(how = How.XPATH, using = "//div[@id='Confirmation_nextBtn']")
	public WebElement cancelConfirmationNextButton;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'is cancelled') and contains(.,'Order')]")
	public WebElement orderCancelledMsg;

	@FindBy(how = How.ID, using = "ResultsGroup_nextBtn")
	public WebElement cancelInfoMsgNextButton;

	@FindBy(how = How.XPATH, using = "//button//span[contains(.,'Show All Activities')]")
	public WebElement showAllActivityButton;

	@FindBy(how = How.XPATH, using = "//a[@title='Order Failure']")
	public WebElement orderFailureLink;

	@FindBy(how = How.XPATH, using = "//button[@title='Upcoming & Overdue']//following::div/a[contains(@title,'Quote Finalized ')]")
	public WebElement upcomingTaskNotification;

	@FindBy(how = How.XPATH, using = "//div[@title='Update Order Details']")
	public WebElement updateOrderDetails;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Show one more action']")
	public WebElement showMoreActions;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Task']//following-sibling::div/span[contains(text(),'is being reinstated to an In Progress State')]")
	public WebElement taskNotificationHeaderText;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[contains(.,'Priority')]/parent::div/following-sibling::div/span/span")
	public WebElement taskPriority;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[contains(.,'Due Date')]/parent::div/following-sibling::div/span/span")
	public WebElement taskDueDate;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[contains(.,'Subject')]/parent::div/following-sibling::div/span/span")
	public WebElement taskSubject;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[contains(.,'Comments')]/parent::div/following-sibling::div/span/span")
	public WebElement taskComments;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[text() = 'Assigned To']/parent::div/following-sibling::div/span//a")
	public WebElement taskAssignedTo;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[contains(.,'Related To')]//parent::div/following-sibling::div/span//a")
	public WebElement taskOrderRealtedTo;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabs__content active uiTab')]//span[contains(.,'Status')]/parent::div/following-sibling::div/span/span")
	public WebElement taskStatus;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Mark Complete')]")
	public WebElement markCompleteButton;

	@FindBy(how = How.XPATH, using = "//a[contains(@title,'Show 3 more actions')]")
	public WebElement taskStatusChangeArrowClick;
	
	@FindAll({@FindBy(xpath = "//span[contains(text(),'Order Products')]/ancestor::article//tbody//a"),
		@FindBy(xpath = "//*[contains(text(),'Quote Line Items') or contains(text(),'ments de devis')]/ancestor::lst-list-view-manager-header/following-sibling::div//tbody//a")})
	public List<WebElement> orderProductValueText;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Order Products')]/ancestor::div[contains(@aria-label,'Order')]//tbody//a[contains(@href,'lig') or contains(@href,'partner')]")
	public List<WebElement> orderProductValuesViewAllList;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Edit Technical Specifications Sheet']")
	public WebElement editTechSpecsSheet;
	
	@FindBy(how = How.XPATH, using = "//button[.='Yes, override the sheet']")
	public WebElement overRideSheetDataButton;
	
	@FindBy(xpath = "//*[contains(@class,'HomeTemplateDesktop')]")
	public WebElement OrderHomePage;
	
	@FindBy(xpath = "//a[contains(@title,'Related') or contains(@title,'Associé')]")
	public List<WebElement> orderRelated;
	
	@FindBy(xpath = "//ul//*[contains(@title,'Order Status') or contains(@title,'État de la commande')]//parent::div/div/div/span")
	public WebElement masterOrderStatusFieldText;
	
	@FindBy(xpath = "//*[contains(@class,'entityNameTitle')]//parent::h1//following-sibling::div/span")
	public List<WebElement> masterOrderNumber;
	
	@FindBy(xpath = "//span[@title='Orders']//ancestor::article//th[contains(text(),'Order Number')]//ancestor::table//tbody//a")
	public WebElement childOrderLink;
	
	@FindBy(xpath = "//span[@title='Orders']//ancestor::article//th[contains(text(),'Order Status') or contains(@title,'État de la commande')]//ancestor::table//tbody//td[3]/span")
	public WebElement childOrderStatusFieldText;
	
	@FindBy(xpath = "//*[contains(@title,'Order Products')]//ancestor::article//th[contains(text(),'Product')]//ancestor::table/tbody//th//a")
	public WebElement masterOrderProductFieldText;
	
	@FindBy(xpath = "//a[@title='Details' or @title='Détails']")
	public List<WebElement> childOrderDetails;
	
	@FindBy(xpath = "//*[text()='Master Order' or text()='Parent Order']//parent::div//following-sibling::div//a")
	public WebElement masterOrderFieldText;
	
	@FindBy(xpath = "//*[text()='Ship To Contact' or contains(text(),'Contact d') and contains(text(),'expédition')]//parent::div//following-sibling::div//a")
	public WebElement shipToContactFieldText;
	
	@FindBy(xpath = "//*[text()='Billing Account' or contains(text(),'Compte de facturation')]//parent::div//following-sibling::div//a")
	public WebElement billingAccount;
	
	@FindBy(xpath = "//a[contains(@title,'Order Decomposition')]")
	public WebElement orderDecomposition;
	
	@FindBy(xpath = "//*[contains(text(),'Source Orders')]//parent::div//a[contains(text(),'Master Order Product')]")
	public WebElement orderDecompositionMasterOrderProduct;
	
	@FindAll({
		@FindBy(xpath = "//*[contains(text(),'Orders') or contains(text(),'Commandes')]//ancestor::div[contains(@class,'ListSi')]//tbody//th//a"),
		@FindBy(xpath = "//*[contains(text(),'Orders') or contains(text(),'Commandes')]//ancestor::lst-list-view-manager-header/following-sibling::div//tbody//th//a")})
	public List<WebElement> orders;

	@FindBy(xpath = "//a[text()='Mobile Price Plan Service']/../parent::div/following-sibling::c-node-attrs//div[@data-attr-code='ATTR_WIRELESS_SOC']")
	public WebElement mobPricePlanSOC;
}
