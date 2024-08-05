package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Pankaj Agarwal, Date: 18/Dec/2020
 *
 *         SFDC_B2B Account page objects


 */
public class SFDC_b2b_Account_Objects {

	public SFDC_b2b_Account_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//li[@id='home_Tab']/a")
	public WebElement homeTabButton;

	@FindBy(how = How.XPATH, using = "//h1[text()='Tab Navigation']//following::li/a[contains(text(),'Accounts')][@title='Accounts Tab']")
	public WebElement accountNavigationButton;
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Recent Accounts')]//following::td/input[@title='New']")
	public WebElement newNavigationButton;
	
	@FindBy(how = How.XPATH, using = "//h2[@class='mainTitle']//following::td/div/select")
	public WebElement accountRecordTypeText;
	
	@FindBy(how = How.XPATH, using = "//h2[@class='mainTitle']//following::td[@id='bottomButtonRow']/input[@title='Continue']")
	public WebElement accountContinueButton;
	
	@FindBy(how = How.XPATH, using = "//table/tbody/tr/td/h2[contains(text(),'Account Detail')]")
	public WebElement accountDetailTagText;
	
	@FindBy(how = How.XPATH, using = "//table/tbody/tr/td/div[contains(text(),'Prospect')]")
	public WebElement accountDetailStatusText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Company Name')]//following::div[@class='requiredBlock']/following-sibling::input[@type='text']")
	public WebElement companyNameInput;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='MAL ID / DUNS']/parent::td/following-sibling::td/input)")
	public WebElement malIDInput;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Account Status')]//following::div[@class='requiredInput']/span/select")
	public WebElement accountStatusDropdownInput;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='Account Status']//following::select)[1]") 
	public WebElement accountStatusDropdown;
	
	@FindBy(how = How.XPATH, using = "//td[text()='Account Status']//following::td")
	public List<WebElement> accountStatusTextBox;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'R4B Total Organization Employee Size')]/parent::td/following-sibling::td/input")
	public WebElement noOfEmployeesInput;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Franchisee Location')]/following::td[@class='dataCol']/span/select")
	public WebElement franchiseLocationDropDownInput;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Franchisee Owner')]/following::td/span/span/select")
	public WebElement franchiseOwnerDropdownInput;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Account Edit')]//following::td/input[@title='Save']")
	public WebElement accountSaveButton;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//td//div/input[@id='phSearchInput']")
	public WebElement searchEnterInput;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//td//div/input[@id='phSearchInput']/parent::div/following-sibling::div/input")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th/a")
	public List<WebElement> accountsAllRecords;
	
	@FindBy(how = How.XPATH, using = "(//tbody//tr//th/a[@data-seclke='Account'])[1]")
	public WebElement accountsAllRecordsCompanyLink;
	
	@FindBy(how = How.XPATH, using = "(//tbody//tr//th/a[@data-seclke='Account']/parent::th/following::td)[1]")
	public WebElement accountsAllRecordsTradeText;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr[contains(@class,'dataRow')]")
	public List<WebElement> accountsAllClmnRecords;
	
	@FindBy(how = How.XPATH, using = "(//td[text()='AutoDUNS']//following::td/div)[1]")
	public WebElement accDetailsAutoDunsNumText;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='Main Phone']/following::td/div[contains(@id,'ileinner')])[1]")
	public WebElement accDetailsPhoneTextBox;
	
	@FindBy(how = How.XPATH, using = "(//*[text()='R4B Total Organization Employee Size']/following::td/div[contains(@id,'ileinner')])[1]")
	public WebElement r4BEmpSizeTextBox;
	
	@FindBy(how = How.XPATH, using = "(//td[text()='R4B Total Organization Employee Size']//following::td/div)[1]/parent::td")
	public WebElement editR4bEmpTextBox;
	
	@FindBy(how = How.XPATH, using = "//*[text()='R4B Total Organization Employee Size']/following::td/div/input")
	public WebElement accDetailsEnterR4bEmp;
	
	@FindBy(how = How.XPATH, using = "(//tbody/tr/td/input[contains(@value,'Save')])[2]")
	public WebElement accDetailsSaveButton;
	
	@FindBy(how = How.XPATH, using ="//lightning-formatted-text[contains(text(),'R4B')]")
	public WebElement r4B_B2B_Field;
	
}
