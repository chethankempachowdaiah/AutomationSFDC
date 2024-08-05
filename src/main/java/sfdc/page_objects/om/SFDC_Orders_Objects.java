package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Order page objects
 */
public class SFDC_Orders_Objects {

	public SFDC_Orders_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Orders')]")
	public WebElement allOrdersOption;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search this list...' or @placeholder='Recherchez dans cette liste...' and @name='Order-search-input']")
	public WebElement searchOrderInput;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th[1]//a[@data-refid='recordId']")
	public List<WebElement> ordersAllRecords;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Orders')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> ordersAllRecordList;

	@FindBy(how = How.XPATH, using = "//tbody/tr/td[5]/span/span")
	public List<WebElement> ordersAllRecordsStatusActive;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Master Orders')]")
	public WebElement allMasterOrderIconOption;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Draft Orders')]")
	public WebElement allDraftOrdersListIcon;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Show filters']/parent::button")
	public WebElement quickFilterButton;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Order Record Type']")
	public WebElement orderRecordType;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Master Order']")
	public WebElement orderRecordTypeMasterOrder;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'not equal to')]")
	public WebElement orderRecordTypeNotEquals;
	
	@FindBy(how = How.XPATH, using = "//span[text()='equals']")
	public WebElement orderRecordTypeEquals;
	
//	@FindAll({ @FindBy(xpath = "//tbody/tr/td[6]/span/span"),
//		@FindBy(xpath = "//tbody/tr/td[5]/span/span")})
//	public List<WebElement> ordersAllRecordsStatusActive;

	@FindBy(how = How.XPATH, using = "//tbody/tr/td[8]/span/span")
	public List<WebElement> ordersAllRecordsfulfilmentStatusActive;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search Orders and more') or contains(@title,'Search Orders and more')]")
	public WebElement ordersGlobalSearch;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search this list...')]")
	public WebElement ordersNewGlobalSearch;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@title,'Search...')]")
	public WebElement globalSearch;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'oneWorkspace active')]//following::table/tbody/tr/th/span/a")
	public List<WebElement> globalSerachOrdersAllRecords;
	
	@FindBy(how = How.XPATH, using = "//div[@title='Cancel Order']")
	public WebElement cancelOrderButton;
	
	@FindBy(how = How.XPATH, using = "//div[@title='Request Cancellation']")
	public WebElement requestCancelationOrderButton;
	
	@FindBy(how = How.XPATH, using = "//div[@title='Reinstate Order']")
	public WebElement reinstateOrderButton;
	
	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public WebElement iframeCancelOrder;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Confirm Cancellation')]")
	public List<WebElement> orderCancelationTextText;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Do you want to cancel this order?')]")
	public WebElement orderDoYouWantCancelText;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Waiting for plan to be frozen.')]")
	public WebElement orderWaitingForPlanFrozenText;
	
	@FindBy(how = How.XPATH, using = "//div/h1[contains(text(),'Order Submission Results')]")
	public List<WebElement> orderSubmissionResultText;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Capture Issue Details')]//ancestor::section[@class='slds-page-header vlc-slds-page--header']")
	public WebElement orderRequestCancellationResultText;
	
	@FindBy(how = How.XPATH, using = "//*[@id='IssueDetails']")
	public WebElement issueEnterText;
	
	@FindBy(how = How.XPATH, using = "//div/p[contains(text(),'Your order has crossed Point of No Return, Order cannot be cancelled.')]")
	public WebElement orderCancelMsgText;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Next')]")
	public WebElement conformationNextButton;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ResultsGroup_nextBtn']/p[contains(text(),'Next')]")
	public WebElement nextButton;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ResultsGroupForPONR_nextBtn']/p[contains(text(),'Next')]")
	public WebElement ponrReachedCancelNextButton;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Proceed')]")
	public WebElement proceedButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='headerButtonBody']")
	public WebElement notificationiconButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Create Work Order in SS has failed for Order')]")
	public WebElement workOrderNotificationIconMessage;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Create Account in SS (CAN Creation) has failed for Order')]")
	public WebElement createAccountNotificationIconMessage;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Committed In Service Date']/parent::div/following-sibling::div/span")
	public WebElement commitedInserviceDateForRDI;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Refresh'][@class='slds-action_text']")
	public WebElement commitedInserviceDatRefreshClick;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Task Failure for Order')]")
	public List<WebElement> notificationiconPopUpOrderFailureMsg;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'is cancelled')]")
	public List<WebElement> notificationiconPopUpOrderCancelMsg;
	
	@FindBy(how = How.XPATH, using = "//a[@title=\"Show one more action\"]")
	public WebElement moreActionButton;
	
	@FindBy(how = How.XPATH, using = "//div[@title='Submit Order']")
	public WebElement submitOrderButton;

}
