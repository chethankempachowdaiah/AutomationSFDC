package sfdc.page_objects.service;

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
 *         SFDC Account Related(tab) page (for Buniess/Service/Billing)
 *
 */
public class SFDC_AccountRelated_Objects {

	public SFDC_AccountRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ 
	@FindBy(how = How.XPATH, using = "//li[@title='Related']"),
	@FindBy(how = How.XPATH, using = "//a[.='Related']")})
	public WebElement relatedTab;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Cases']//following::a//span")
	public List<WebElement> fraudCaseNumber;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//li[@title='Details']//a"),
			@FindBy(how = How.XPATH, using = "//a[.='Details']"),
			@FindBy(how = How.XPATH, using = "//a[@data-label='Details']") })
	public WebElement detailsTab;

	@FindBy(how = How.XPATH, using = "//li[@title='Related']//a")
	public List<WebElement> allRelatedTab;

    @FindAll({
	@FindBy(how = How.XPATH, using = "//a[@data-label='Contacts']"),
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Contacts')]/ancestor::a)[3]")})
	public WebElement contactTab;

	@FindBy(how = How.XPATH, using = "//a//div[contains(text(),'New Contact')]")
	public WebElement newContact;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Contact Name')]/following::tr//th//span//a")
	public List<WebElement> allContacts;

	@FindBy(how = How.XPATH, using = "//span[@title='Opportunities']")
	public WebElement opportunitiesSectionText;

	@FindBy(how = How.XPATH, using = "//a[@title='New Opportunity' or @title='Nouvelle opportunité']")
	public WebElement newOpportunityButton;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Orders']")
	public WebElement ordersSubTabLink;

	@FindBy(how = How.XPATH, using = " //h2//span[@title='Orders']//ancestor::header//parent::div[contains(@class,'header')]//following-sibling::div//li//a[@data-refid='recordId']")
	public List<WebElement> ordersAllLinks;

	@FindBy(how = How.XPATH, using = "//span[@title='Opportunities']")
	public WebElement opportunitySubTabLink;

	@FindBy(how = How.XPATH, using = "//span[@title='Opportunities']//following::ul[@class='uiAbstractList']//a[contains(@class,'textUnderline')]")
	public List<WebElement> opportunityNameLink;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Approval History']")
	public WebElement approvalHistorySubTabLink;

	@FindBy(how = How.XPATH, using = "//th[@title='Step Name']//ancestor::thead//following-sibling::tbody//th//a")
	public List<WebElement> approvalAllLinks;

	@FindBy(how = How.XPATH, using = "//h1[.='Approval History']")
	public WebElement approvalHistoryDetailsPageHeader;
	
	@FindBy(how = How.XPATH, using = "//h2//span[@title='Competitive Insights']")
	public WebElement competitiveInsights;

	@FindBy(how = How.XPATH, using = "//h1[.='Approval History']//ancestor::div[contains(@class,'listViewManager')]//tbody//th//a")
	public List<WebElement> approvalDetailsPageAllLinks;

	@FindBy(how = How.XPATH, using = "//span[.='Details']//ancestor::div[contains(@class,'row')]//span[.='Industry']")
	public WebElement industryForApproval;

	@FindBy(how = How.XPATH, using = "//span[.='Details']//ancestor::div[contains(@class,'row')]//span[.='Sub-Industry']")
	public WebElement subIndustryForApproval;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'primaryField')]//div[@title='Approve']")
	public WebElement singleRequestApproveButton;

	@FindBy(how = How.XPATH, using = "//div[@class='actionsContainer']//li/a/div[@title='Approve']")
	public WebElement dataGovernanceApproveButton;

	@FindBy(how = How.XPATH, using = "//div[@class='actionsContainer']//li/a/div[@title='Reject']")
	public WebElement dataGovernanceRejectButton;

	@FindBy(how = How.XPATH, using = "//textarea")
	public WebElement commentTextArea;

	@FindBy(how = How.XPATH, using = "//button/span[contains(.,'Approve')]")
	public WebElement approveAccountButton;

	@FindBy(how = How.XPATH, using = "//button/span[contains(.,'Reject')]")
	public WebElement rejectAccountButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Account was approved.')]")
	public WebElement accountWasApprovedMsg;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Account was rejected.')]")
	public WebElement accountWasRejectedMsg;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Service Assets']")
	public WebElement assetsSubTabLink;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Billing Assets']")
	public WebElement billingAssetsSubTabLink;

	@FindBy(how = How.XPATH, using = "//th[@title='Asset Name']//ancestor::thead//following-sibling::tbody//th//a")
	public List<WebElement> assetsAllLinks;

	@FindBy(how = How.XPATH, using = "//th[@aria-label='Item Number']/following-sibling::th[@title='Asset Name']//ancestor::thead//following-sibling::tbody//th//a")
	public List<WebElement> assetsAllLinksInServiceAssets;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Account History']")
	public WebElement accountHistorySubTabLink;

	@FindBy(how = How.XPATH, using = "//span[@title='Original Value']//ancestor::table//tr//td[1]//span")
	public List<WebElement> accountHistoryFieldAllRows;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Field']/following::tr//td/descendant::lightning-formatted-text")
	public List<WebElement> accountHistoryFieldAll;

	@FindBy(how = How.XPATH, using = "//span[@title='Original Value']//ancestor::table//tbody//tr//td[3]//span//lightning-formatted-text")
	public List<WebElement> accountHistoryOriginalValueAllRows;

	@FindBy(how = How.XPATH, using = "//span[@title='Original Value']//ancestor::table//tbody//tr//td[4]//span//lightning-formatted-text")
	public List<WebElement> accountHistoryNewValueAllRows;

	@FindBy(how = How.XPATH, using = "//ul//li//div[@class='filerow']")
	public List<WebElement> fileRowAllValues;
	
	@FindBy(how = How.XPATH, using = "//h2//a//span[contains(text(),'Entitlements')]")
	public WebElement entitlements;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Standard Service Support']")
	public WebElement standardServiceSupport;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Standard Technical Support']")
	public WebElement standardTechnicalSupport;
	
	@FindBy(how = How.XPATH, using = "//h2//a//span[contains(text(),'Notes')]")
	public WebElement notes;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Add Files')]/preceding::span[contains(text(),'Files')]/following-sibling::span")
	public WebElement numberOfFiles;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Service Groups']")
	public WebElement serviceGroupTab;

	@FindBy(how = How.XPATH, using = "//th[@data-label='Group Name']/ancestor::tbody//tr//th//a")
	public List<WebElement> serviceGroupNameAllValues;

	@FindBy(how = How.XPATH, using = "//th[@data-label='Group Name']/ancestor::tbody//tr//td[1]//a")
	public List<WebElement> serviceGroupEmailAllValues;

	@FindBy(how = How.XPATH, using = "//th[@data-label='Group Name']/ancestor::tbody//tr//td[2]//span")
	public List<WebElement> serviceGroupPhoneAllValues;

	@FindBy(how = How.XPATH, using = "//th[@data-label='Group Name']/ancestor::tbody//tr//td[3]//span")
	public List<WebElement> serviceGroupsProductsSupportedAllValues;

	@FindBy(how = How.XPATH, using = "//th[@data-label='Group Name']/ancestor::tbody//tr//td[4]//lightning-primitive-icon")
	public WebElement serviceGroupOptionsImg;

	@FindBy(how = How.XPATH, using = "//a[@title='Delete']")
	public WebElement deleteOptionInServiceGroup;

	@FindBy(how = How.XPATH, using = "//button/span[contains(.,'Delete')]")
	public WebElement serviceGroupDeleteButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Service Group') and contains(.,'was deleted')]")
	public WebElement serviceGroupDeletedMsg;

	@FindBy(how = How.XPATH, using = "//span[text()='Related Contacts']/parent::span[@class='view-all-label']")
	public WebElement relatedContactsViewAll;

	@FindBy(how = How.XPATH, using = "(//li[@class='oneActionsDropDown']//following::a[contains(@class,'slds-button')][@aria-disabled='false'])[last()]")
	public WebElement relatedContactsArrow;

	@FindBy(how = How.XPATH, using = "//li/a[@title='View Relationship'][@role='menuitem'][@aria-disabled='false']")
	public WebElement relatedContactsArrowOption;

	@FindBy(how = How.XPATH, using = "//span[text()='ACR Permissions']/parent::div/following-sibling::div/span/span")
	public List<WebElement> accountContactRelationshipAcr;

	@FindBy(how = How.XPATH, using = "//button[@title='More Tabs']")
	public WebElement moreTabs;

	@FindBy(how = How.XPATH, using = "//span[text()='Related Contacts']/parent::span[contains(text(),'View All')]")
	public WebElement relatedContactViewAll;

	@FindBy(how = How.XPATH, using = "//span[text()='Approval History']/parent::span[contains(text(),'View All')]")
	public List<WebElement> viewAllApprovalHistory;

	// ACR Locators In Related Contact List for filter

	@FindBy(how = How.XPATH, using = "//input[@name='AccountContactRelation-Contact.Name']")
	public WebElement quickFilterEnterContactName;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by ')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']/span/a")
	public List<WebElement> contactNameText;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by')]//following::table/tbody/tr/td[2]/span/a")
	public List<WebElement> accountNameText;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[6]/span/span"),
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ACR Permissions')]/following::tbody//tr//td[7]//span//span")})
	public List<WebElement> contactAcrPermissionText;
	
	@FindBy(how = How.XPATH, using = "(//span[@title='Account Name']/ancestor::thead/following-sibling::tbody)[2]//th//a")
	public List<WebElement> accountNameValue;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[7]/span/span/img"),
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Direct')]/following::tbody//tr//td[8]//span//span//img")})
	public List<WebElement> contactAcrDirectCheckBox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Account Record Type')]/following::tbody//tr//td[5]//span//span")
	public List<WebElement> accountRecordTypeValue;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[5]/span/span")
	public List<WebElement> contactRoleTypeText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'Block')]//span[.='Active']")
	public WebElement acrInfoActive;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'Block')]//span[.='Start Date']")
	public WebElement acrInfoStartDate;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'Block')]//span[.='End Date']")
	public WebElement acrInfoEndDate;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'Block')]//span[.='Active']/parent::div/following-sibling::div//span[contains(@class,'field-value')]")
	public WebElement acrInfoActiveValue;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'Block')]//span[.='Start Date']/parent::div/following-sibling::div//span[contains(@class,'field-value')]")
	public WebElement acrInfoStartDateValue;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'Block')]//span[.='End Date']/parent::div/following-sibling::div//span[contains(@class,'field-value')]")
	public WebElement acrInfoEndDateValue;
	
	@FindBy(how = How.XPATH, using = "//div[@class='oneRecordHomeFlexipage2Wrapper']//following-sibling::span[.='Cases']")
	public WebElement cases;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId']")
	public List<WebElement> caseAllRecords;
	
	@FindBy(how = How.XPATH, using = "//slot[@name='header']//span[.='Show more actions']//ancestor::button")
	public WebElement onCaseDropDownButton;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[.='Add New Service']//ancestor::a"),
		@FindBy(how = How.XPATH, using = "//button[.='Add New Service']") })
	public WebElement addNewServiceOnCase;
	
}
