package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 30/JAN/2020
 *
 *         SFDC Quote Related Tab
 */
public class SFDC_QuoteRelated_Objects {

	public SFDC_QuoteRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//span[text()='Related']"),
			@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//span[text()='Related' or text()='Associé'][@class='title']"),
			@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//li[@title='Related']/a") })
	public WebElement relatedTab;
	
	@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//li[@title='Related' or @title='Associé']/a")
	public WebElement r4BApprovalRelatedTab;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//span[text()='Related'][@class='title']")
	public WebElement quoteRelatedTab;

	@FindBy(how = How.XPATH, using = "//a/span[@title='Files']")
	public WebElement filesSubTab;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'pdf')]")
	public List<WebElement> filesAttachmentAllLinks;

	@FindBy(how = How.XPATH, using = "//div[@class='loaded page tall']")
	public WebElement loadedPagePDFAttachment;

	@FindBy(how = How.XPATH, using = "//button[@title='Close']/img")
	public WebElement closePDFButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active lafPageHost')]//span[@class='view-all-label']")
	public List<WebElement> quoteLineItemsViewAllClick;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//div/span[contains(text(),'Sorted by')]//following::div[@class='uiVirtualDataTable indicator']//following-sibling::table//tbody//th[@class='slds-cell-edit cellContainer']/span/a")
	public List<WebElement> quoteLineItemsProductName;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Sorted')]//following::span[text()='Product']//following::tbody/tr/th/span/a")
	public List<WebElement> orderProductItemsName;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Sorted')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']/span/a")
	public List<WebElement> productItemsName;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Sorted')]//following::table/tbody/tr/td[2]/span/span")
	public List<WebElement> productItemQuantity;

	@FindBy(how = How.XPATH, using = "//*[text()='Order Products']//following::span[@class='view-all-label']")
	public List<WebElement> orderProductItemsViewAllClick;

	@FindBy(how = How.XPATH, using = "//input[@name='OrderItem-Product2.Name']")
	public WebElement orderProductItemsEnter;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Approval Type') or contains(.,'Type d’approbation')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public WebElement quoteApprovalNameLink;
	
	// Sse the below locator for either credit and fraud approval both present
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Approval Type') or contains(.,'Type d’approbation')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public List<WebElement> quoteApprovalNameLink_URL;
	
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Process') or contains(.,'Type d’approbation')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public List<WebElement> r4bHistoryQuoteLink_URL;
	
	@FindBy(how = How.XPATH, using = "//span[text()='R4B Quotes Approval'][@title='R4B Quotes Approval']")
	public WebElement r4BQuoteApprovalText;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='R4B Field History'])[1]")
	public WebElement r4BApprovalHistoryText;
	
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Approval Type')]/ancestor::thead/following-sibling::tbody/tr/td[3]/span/span")
	public List<WebElement> quoteApprovalType;
	
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Process')]/ancestor::thead/following-sibling::tbody/tr/td[3]/span/span")
	public List<WebElement> r4bHistoryQuoteApprovalType;
	
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Approval Type')]/ancestor::thead/following-sibling::tbody/tr/td[2]/span/span")
	public List<WebElement> quoteApprovalStatusType;
	
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Process')]/ancestor::thead/following-sibling::tbody/tr/td[4]/span/span")
	public List<WebElement> r4bHistoryQuoteApprovalValueType;
	
	@FindBy(how = How.XPATH, using = "//th[contains(.,'Process')]/ancestor::thead/following-sibling::tbody/tr/td[5]/span/span")
	public List<WebElement> r4bHistoryQuoteFinalStatus;

	//@FindAll({ @FindBy(xpath = "(//th[contains(.,'Order')]/ancestor::table/parent::div[contains(@class,'slds-table')]//tbody/tr/th//a)[3]"),
	//	@FindBy(xpath = "(//th[contains(.,'Order')]/ancestor::table/parent::div[contains(@class,'action')]//following-sibling::tbody/tr/th//a)[2]")})	
	//public WebElement orderNameLinkInInQuoteRelated;
	
	@FindBy(how = How.XPATH, using = "//div[@class='windowViewMode-maximized active lafPageHost']//th[contains(.,'Order')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public WebElement orderNameLinkInInQuoteRelated;
	
	@FindBy(how = How.XPATH, using = "//div[@class='windowViewMode-maximized active lafPageHost']//th[contains(.,'Order')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public List<WebElement> orderNameLinkInQuoteRelated;
	
	@FindBy(how = How.XPATH, using = "(//span[@title='Orders' or @title='Commandes'])[1]")
	public WebElement ordersHeader;
	
	@FindBy(how = How.XPATH, using = "//div[@class='windowViewMode-maximized active lafPageHost']//th[contains(.,'Order')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public WebElement orderNameLinkInInQuoteRelated1;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Approval Status']")
	public WebElement quoteApprovalClick;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Approval Status')]/parent::lightning-combobox//div/input")
	public WebElement quoteApprovalDropdown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Approval Status')]/parent::lightning-combobox//div/lightning-base-combobox-item[@data-value='Approved']")
	public WebElement quoteApprovalDropdownValueApproved;

	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement quoteApprovalSaveButton;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Approve' or @title='Approuver']")
	public WebElement approveQouteButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Approve' or .='Approuver']")
	public WebElement saveApprove;
	
	@FindBy(how = How.XPATH, using = "//div[@class='windowViewMode-maximized active lafPageHost']//th[contains(.,'Order') or contains(.,'commande')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public List<WebElement> orderNamesLinkInInQuoteRelated1;
	
	@FindBy(how = How.XPATH, using = "(//a[@title='Enterprise Sales App'])[2]")
	public WebElement quoteTab;
	
	@FindBy(how = How.XPATH, using = "(//span[@class='view-all-label'])[2]")
	public WebElement orderViewAll;
}
