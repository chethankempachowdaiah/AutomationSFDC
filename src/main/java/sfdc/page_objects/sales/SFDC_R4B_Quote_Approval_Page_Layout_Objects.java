package sfdc.page_objects.sales;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Nandan.More, Date: 24/June/2021
 *
 *         SFDC R4B_Quotes_Approval_Objects
 */

public class SFDC_R4B_Quote_Approval_Page_Layout_Objects {
	
	public SFDC_R4B_Quote_Approval_Page_Layout_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
  
	@FindBy(how = How.XPATH, using = "//span[.='Select a List View']//ancestor::button[@title='Select a List View']")
	public WebElement selectListViewDropdown;
	
	@FindBy(how = How.XPATH, using = "//span[.='Recently Viewed']")
	public WebElement allQuotes;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId'][@title='App-000003']")
	public WebElement quoteNumbersAllRows;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'entityNameTitle ')]")
	public WebElement r4BHeading;
	
	@FindBy(how = How.XPATH, using = "//span[.='Edit Related Quote']//ancestor::button")
	public WebElement editRelatedQuote;
	
	@FindAll({
		@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//span[text()='Related' or text()='Associé'][@class='title']"),
		@FindBy(how = How.XPATH, using = "//a[contains(.,'Related')] [@id='relatedListsTab__item']"),
		@FindBy(how = How.XPATH, using = "//a[contains(.,'Related')] [@data-tab-name='relatedListsTab']")})
	public WebElement relatedButton;
	
	
	@FindBy(how = How.XPATH, using = "//a[.='Quote Details']")
	public WebElement qd;
	
	@FindBy(how = How.XPATH, using = "//span[@title='R4B Quotes Approval']")
	public WebElement r4bApproval;	
	
	@FindBy(how = How.XPATH, using = "//a[text()='Details']")
	public WebElement r4bApprovalDeatils;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Approval Type')]/parent::div/following-sibling::div/span/slot/lightning-formatted-text")
	public WebElement r4bApprovalType;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Approval Status')]/parent::div/following-sibling::div/span/slot/lightning-formatted-text")
	public WebElement r4bApprovalStatus;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Approval Time')]/parent::div/following-sibling::div/span/slot/lightning-formatted-text")
	public WebElement r4bApprovalTime;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId'][@id='87:3361;a']")
	public WebElement r4bquotename;	
	
	@FindBy(how = How.XPATH, using = "//span[.='Account Name']")
	public WebElement qdAccName;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Approve' or @title='Approuver']")
	public WebElement approveButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Approve' or .='Approuver']")
	public WebElement finalApproveButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@title, 'Reject')] [text()='Reject']")
	public WebElement rejectButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(., 'Reject')] [@class=' label bBody']")
	public WebElement finalRejectButton;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Pass']")
	public WebElement NFDBPassButton;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Save']")
	public WebElement confirmNFDBButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Reject')]")
	public WebElement NFDBRejectButton;
	
	@FindBy(how = How.XPATH, using = "//span[.='Recurring Total']//ancestor::div[(@class='riseTransitionEnabled')]")
	public WebElement qdRTotal;
	
	@FindBy(how = How.XPATH, using = "//span[.='Effective One Time Total']")
	public WebElement qdOneTimeTotal; 
	
	@FindBy(how = How.XPATH, using = "//span[.='One Time Margin Total']")
	public WebElement qdOneTimeMargin; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Recurring Margin Total']")
	public WebElement qdRecurringMargin; 
	
	@FindBy(how = How.XPATH, using = "//a[.='Account Details']")
	public WebElement ad; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Last Credit Review']")
	public WebElement adLastCredit; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Credit Review Exempt']")
	public WebElement adCreditReview; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Legal Name']")
	public WebElement adLegalName; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Credit Limit Available']")
	public WebElement adCreditLimit;
	
	@FindBy(how = How.XPATH, using = "//span[.='Industry']")
	public WebElement adIndustry;
	
	@FindBy(how = How.XPATH, using = "//span[.='Contact']")
	public WebElement adContact;
	
	@FindBy(how = How.XPATH, using = "//span[.='Parent Account']")
	public WebElement adParentAccount;
	
	@FindBy(how = How.XPATH, using = "//span[.='Sub-Industry']")
	public WebElement adSubIndustry;
	
	@FindBy(how = How.XPATH, using = "//span[.='Auto R1DUNS']")
	public WebElement adAutoR1DUNS;
	
	@FindBy(how = How.XPATH, using = "//span[.='DUNS']")
	public WebElement adDUNS;
	
	@FindBy(how = How.XPATH, using = "//span[.='Phone']")
	public WebElement adPhone;
	
	@FindBy(how = How.XPATH, using = "//span[.='R1DUNS']")
	public WebElement adR1DUNS;
	
	@FindBy(how = How.XPATH, using = "//span[.='Sales Segment']")
	public WebElement adSalesSegment;
	
	@FindBy(how = How.XPATH, using = "//button[@name='CancelEdit']")
	public WebElement cancelEdit;
	
	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement saveEdit;
	
	@FindBy(how = How.XPATH, using = "//span[.='Related Quote']")
	public WebElement relatedQuote;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId']")
	public WebElement quoteAllRecords;
	
	
}
