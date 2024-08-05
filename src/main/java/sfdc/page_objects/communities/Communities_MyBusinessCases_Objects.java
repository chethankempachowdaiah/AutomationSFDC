package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya,, Date:19/07/2020
 * 
 *         Communities>View cases
 *
 */
public class Communities_MyBusinessCases_Objects {

	public Communities_MyBusinessCases_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'My Orders and Cases')]")
	public WebElement myBusinessCasesHeader;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Search Cases')]/parent::div/parent::div//input")
	public WebElement searchCasesTextBox;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Number of rows')]/preceding::b")
	public WebElement noRecordsToDisplayMessage;

	@FindBy(how = How.XPATH, using = "//div//a[contains(.,'Create New Case')]  | //button[contains(.,'Create case')]")
	public WebElement createNewCasesLink;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Business Phone']/following-sibling::label//span")
	public List<WebElement> productBusinessPhone;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Internet']/following-sibling::label//span")
	public List<WebElement> productInternetAndAdvancedNetwork;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Wireless']/following-sibling::label//span")
	public List<WebElement> productWireless;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Data Centre & Cloud']/following-sibling::label//span")
	public List<WebElement> productDataCentreAndCloud;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Internet of Things']/following-sibling::label//span")
	public List<WebElement> productInternetOfThings;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Enterprise Mobility Management']/following-sibling::label//span")
	public List<WebElement> productEnterpriseMobilityManagement;
	
	@FindBy(how = How.XPATH, using = "//input[@value='SaaS']/following-sibling::label//span")
	public List<WebElement> productSaaS;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//div[contains(@class,'table-head')] | //span[contains(.,'Case Summary')]")
	public WebElement caseSummary_caseSummaryTableHeader;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='CaseNumber']")
	public WebElement caseSummary_caseNumberColumnName;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//div//span[contains(.,'Account Name')] | //vlocity_cmt-data-table//div//span[contains(.,'Account Name')]")
	public WebElement caseSummary_accountNameColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='CaseNumber']//parent::div//following-sibling::div//span[contains(.,'Status')]")
	public WebElement caseSummary_statusColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='CaseNumber']//parent::div//following-sibling::div//span[contains(.,'Reason')]")
	public WebElement caseSummary_reasonColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='CaseNumber']//parent::div//following-sibling::div//span[contains(.,'Product')]")
	public WebElement caseSummary_productColumnName;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//*[@data-field-name='CaseNumber']/a"),
			@FindBy(how = How.XPATH, using = "//*[@data-field-name='CaseNumber']//a") })
	public List<WebElement> caseSummary_caseNumberAllLinks;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//*[@data-field-name='AccountName']//span")
	public List<WebElement> caseSummary_accountNameAllValues;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//*[@data-field-name='Status']//span")
	public List<WebElement> caseSummary_statusAllValues;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//*[@data-field-name='Reason']//span")
	public List<WebElement> caseSummary_reasonAllValues;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Case Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//*[@data-field-name='Product']//span")
	public List<WebElement> caseSummary_productAllValues;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//span[contains(.,'Order Summary')]"),
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Order Summary')]") })
	public WebElement orderSummary_tableHeader;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='OrderNumber']")
	public WebElement orderSummary_orderNumberColumnName;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Order Summary')]//parent::div//following-sibling::div//vlocity_cmt-data-table//div[@class='tableHead']//div//span[contains(.,'Account Name')]")
	public WebElement orderSummary_accountNameColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='OrderNumber']//ancestor::div/following-sibling::div//a[contains(.,'Start Date')]")
	public WebElement orderSummary_orderDateColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='OrderNumber']//ancestor::div/following-sibling::div//a[contains(.,'Product')]")
	public WebElement orderSummary_productColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='OrderNumber']//ancestor::div/following-sibling::div//a[contains(.,'Status')]")
	public WebElement orderSummary_orderStatusColumnName;

	@FindBy(how = How.XPATH, using = "//div//vlocity_cmt-data-table//a[@data-field='OrderNumber']//ancestor::div/following-sibling::div//a[contains(.,'Address')]")
	public WebElement orderSummary_orderAddressColumnName;

	@FindBy(how = How.XPATH, using = "//button[@value='All Cases']")
	public WebElement caseFilterAllCases;

	@FindBy(how = How.XPATH, using = "//button[@value='New']")
	public WebElement caseFilterNew;

	@FindBy(how = How.XPATH, using = "//button[@value='New']/following-sibling::button[@value='In Progress']")
	public WebElement caseFilterInProgress;

	@FindBy(how = How.XPATH, using = "//button[@value='Awaiting Customer Response']")
	public WebElement caseFilterAwaitingCustomerResponse;

	@FindBy(how = How.XPATH, using = "//button[@value='Closed']")
	public WebElement caseFilterClosed;

	@FindBy(how = How.XPATH, using = "//button[@value='All Orders']")
	public WebElement orderFilterAllOrders;

	@FindBy(how = How.XPATH, using = "//button[@value='Ready to Submit']")
	public WebElement orderFilterReadyToSubmit;

	@FindBy(how = How.XPATH, using = "//button[@value='In Progress']")
	public WebElement orderFilterInProgress;

	@FindBy(how = How.XPATH, using = "//button[@value='Activated']")
	public WebElement orderFilterActivated;

	@FindBy(how = How.XPATH, using = "//button[@value='Cancelled']")
	public WebElement orderFilterCancelled;

	@FindBy(how = How.XPATH, using = "//div[.='In Progress']//ancestor::div[contains(@class,'tableRow')]//*[@data-label='Order number']//a")
	public List<WebElement> orderNumberLinkForInProgressOrder;

	@FindBy(how = How.XPATH, using = "//div[.='Activated']//ancestor::div[contains(@class,'tableRow')]//*[@data-label='Order number']//a")
	public List<WebElement> orderNumberLinkForActivatedOrder;

	@FindBy(how = How.XPATH, using = "//div[.='Cancelled']//ancestor::div[contains(@class,'tableRow')]//*[@data-label='Order number']//a")
	public List<WebElement> orderNumberLinkForCancelledOrder;

	@FindBy(how = How.XPATH, using = "//div[.='Ready To Submit']//ancestor::div[contains(@class,'tableRow')]//*[@data-field-name = 'OrderNumber']/div/a")
	public List<WebElement> orderNumberLinkForSubmitOrder;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'tableRow')]//*[@data-label='Order number']//a)[2]")
	public WebElement orderNumberLinkForSubOrder;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'tableRow')]//*[@data-label='Order number']//a")
	public List<WebElement> orderNumberLinksListForSubOrder;
			
	@FindBy(how = How.XPATH, using = "//a[.='Order Summary']")
	public WebElement orderSummaryLink;

	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Incomplete']")
	public List<WebElement> orderNumberLinkForIncomplete;
	
	@FindBy(how = How.XPATH, using = "//a[@type='button']//p") 
	public WebElement completeFormLink;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']") 
	public WebElement continueFormUpdatedLink;
	
	
}
