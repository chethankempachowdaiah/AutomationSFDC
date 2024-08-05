package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 20/Apr/2021
 *
 *         SFDC Lead page objects
 */

public class SFDC_Leads_Objects {

	public SFDC_Leads_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[.='New']")
	public WebElement newLeadButton;

	@FindBy(how = How.XPATH, using = "//h2[.='New Lead']")
	public WebElement leadPageHeader;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='First Name']")
	public WebElement firstNameTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Last Name']")
	public WebElement lastNameTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Company']")
	public WebElement companyTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Email']")
	public WebElement emailTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Phone']")
	public WebElement phoneTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='NumberOfEmployees']")
	public WebElement noEmpTextBox;
	
	@FindBy(how = How.XPATH, using = "//textarea[@name='street']")
	public WebElement streetTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='city']")
	public WebElement cityTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='province']")
	public WebElement stateTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='postalCode']")
	public WebElement postalCodeTextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@name='country']")
	public WebElement countryTextBox;
	
	@FindBy(how = How.XPATH, using = "//span[.='Show more actions']//ancestor::button")
	public WebElement showMoreActionsButton;
	
	@FindBy(how = How.XPATH, using = "//span[.='Convert']//ancestor::a")
	public WebElement convertLead;
	
	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement saveLead;
	
	@FindBy(how = How.XPATH, using = "//h1/div[.='Lead']")
	public WebElement leadDetailPageHeader;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,' virtualAutocompleteOptionText')][contains(text(),'All Open Leads')]")
	public WebElement allOpenLeadsOption;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//a[@data-refid='recordId']")
	public List<WebElement> leadsAllRecords;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Lead-search-input']")
	public WebElement leadSearch;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'No items to display')]")
	public WebElement noSearch;
	
	@FindBy(how = How.XPATH, using = "//div[@title='New Report']/parent::a")
	public WebElement newReport;
	
	@FindBy(how = How.XPATH, using = "//iframe[contains(@title,'Report Builder')]")
	public WebElement iframeReportBuilder;
	
	@FindBy(how = How.XPATH, using = "//div[@class='ReactModalPortal']//ancestor::a[.='Leads']")
	public WebElement reportLead;
	
	@FindBy(how = How.XPATH, using = "//button[.='Continue']")
	public WebElement reptcontinue;
	
	@FindBy(how = How.XPATH, using = "//button[.='Run']")
	public WebElement runReport;
	
	@FindBy(how = How.XPATH, using = "//span[.='Search report table']//ancestor::button")
	public WebElement searchReportButton;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search report table...']")
	public WebElement searchReportInput;
	
	@FindBy(how = How.XPATH, using = "//div[@class='num-of-results']/p[.='1/2']")
	public WebElement match;
	
	@FindBy(how = How.XPATH, using = "//label[.='Sub-Industry']")
	public WebElement subIndustrylabel;
	
	@FindBy(how = How.XPATH, using = "//button[@aria-label='Language Preference, --None--']")
	public WebElement subIndustryButton;
	

}
