package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Nandan.More, Date: 14/Oct/2021
 *
 *         SFDC Lead page objects
 */

public class SFDC_Campaigns_Objects {

	public SFDC_Campaigns_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[.='New']")
	public WebElement newCampaignButton;

	@FindBy(how = How.XPATH, using = "//span[.='Campaigns']//ancestor::ol")
	public WebElement campaignPageHeader;

	@FindBy(how = How.XPATH, using = "//input[@class=' input' and @maxlength='80']")
	public WebElement campaignNameTextBox;
	
	@FindBy(how = How.XPATH, using = "//span[.='Start Date']//ancestor::label[@data-aura-class='uiLabel']//following-sibling::div[@class='form-element']//a[contains(@class,'datePicker-openIcon display')]")
	public WebElement startDateIcon;
	
	@FindBy(how = How.XPATH, using = "//span[.='End Date']//ancestor::label[@data-aura-class='uiLabel']//following-sibling::div[@class='form-element']//a[contains(@class,'datePicker-openIcon display')]")
	public WebElement endDateIcon;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'today')]")
	public WebElement buttonDateToday;
	
	@FindBy(how = How.XPATH, using = "//span[.='Start Date']//ancestor::label[@data-aura-class='uiLabel']//following-sibling::div[@class='form-element']/input[contains(@class,'input')]")
	public WebElement startDateRead;
	
	@FindBy(how = How.XPATH, using = "//span[.='End Date']//ancestor::label[@data-aura-class='uiLabel']//following-sibling::div[@class='form-element']/input[contains(@class,'input')]")
	public WebElement endDateRead;
	
	@FindBy(how = How.XPATH, using = "//span[.='Show more actions']//ancestor::button")
	public WebElement showMoreActionsButton;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Save']")
	public WebElement saveCampaign;
	
	@FindBy(how = How.XPATH, using = "//span[.='Active']")
	public WebElement activeName;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Cancel']")
	public WebElement cancelButton;
	
	@FindBy(how = How.XPATH, using = "//textarea[@class=' textarea']")
	public WebElement textArea;
	
	@FindBy(how = How.XPATH, using = "//div[@data-aura-class='forcePageBlockSectionRow']//following-sibling::button[@title='Edit Campaign Name']")
	public WebElement editForm;
	
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox']")
	public WebElement activeCheckbox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,' virtualAutocompleteOptionText')][contains(text(),'All Active Campaigns')]")
	public WebElement allActiveCampaignsOption;
	
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'Recently Viewed')]")
	public WebElement allCampaignsOption;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@title='#0 - Event - Account Review']"),
		@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId']") })
	public List <WebElement> campaignAllRecords;
	
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//span[@title='Campaign History']"),
		@FindBy(how = How.XPATH, using = "//span[contains(.,'Campaign History')]//ancestor::a") })
	public List<WebElement> campaignHistory;	
	
	@FindBy(how = How.XPATH, using = "//div[@data-aura-class='forceRelatedListSingleContainer']//div[@class='slds-card__footer']/span[@class='view-all-label']")
	public List<WebElement> viewall;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Campaign Name']")
	public WebElement campaignName;	
	
	@FindBy(how = How.XPATH, using = "//span[@title='Start Date']")
	public WebElement sDate;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Type']")
	public WebElement type;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Created Date']")
	public WebElement createdDate;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Status']")
	public WebElement status;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Responded']")
	public WebElement responded;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Member Status Updated']")
	public WebElement mStatusUp;
	
	@FindBy(how = How.XPATH, using = "//span[.='Campaign Record Type']")
	public WebElement camRecordType;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[@class='forceOutputRecordType']//span[.='Standard']"),
		@FindBy(how = How.XPATH, using = "//span[.='Standard']") })
	public WebElement standard;
	
	@FindBy(how = How.XPATH, using = "//div[@class='forceOutputRecordType']//span[.='Master']")
	public WebElement master;
	
	@FindBy( how = How.XPATH, using = "//forcegenerated-flexipage_contact_record_page_contact__view_js//ancestor::a[.='Related']")	
	public WebElement relatedTab;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//span[.='Details']"),
		@FindBy(how = How.XPATH, using = "//forcegenerated-flexipage_contact_record_page_contact__view_js//ancestor::a[.='Details']") })
	public WebElement detailsTab;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//a/span[contains(text(),'Campaign History')]"),
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Campaign')]") })
	public WebElement camHistory;
	
	//@FindBy(how = How.XPATH, using = "//span[contains(text(),'Campaign History')]"),
	
	@FindBy(how = How.XPATH, using = "//*[@title='Select a List View']")
	public WebElement listViewIcon;
	

}
