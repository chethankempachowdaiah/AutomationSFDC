
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
 *         SFDC_B2B Contact page objects


 */
public class SFDC_b2b_Contact_Objects {

	public SFDC_b2b_Contact_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='listRelatedObject contactBlock']//td/input[@name='newContact']")
	public WebElement newContactButton;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'New Contact')]//following::span/input[@data-fieldname='accountid']")
	public WebElement newContactCompanyName;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'First Name')]//following::td/input[@id='name_firstcon2']")
	public WebElement contactFirstNameInput;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Last Name')]//following::td/div/input[@name='name_lastcon2']")
	public WebElement contactLastNameInput;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Language Preference')]/parent::td/following-sibling::td//select")
	public WebElement languagePreferenceDropdownOption;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Contact Type')]//parent::span/parent::td/following-sibling::td//select")
	public WebElement contactTypeDropdownOption;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Phone']/parent::td/following-sibling::td/input")
	public WebElement contactPhoneInput;
	
	@FindBy(how = How.XPATH, using = "//tbody//td[@class='pbTitle']//h2[contains(text(),'Contact Edit')]/parent::td/following-sibling::td/input[contains(@value,' Save')]")
	public WebElement contactSaveButton;
	
	@FindBy(how = How.XPATH, using = "//td/h2[text()='Contact Detail']")
	public WebElement contactDetailsTagText;
	
	@FindBy(how = How.XPATH, using = "(//tbody//tr//td[contains(text(),'Name')]/parent::tr/td[contains(@class,'dataCol')]/div)[1]")
	public WebElement contactDetailsNameText;
	
	@FindBy(how = How.XPATH, using = "(//td[text()='Phone']/following::td/div)[1]")
	public WebElement contactDetailsTextPhoneNum;
	
	@FindBy(how = How.XPATH, using = "(//input[contains(@value,'Edit')])[1]")
	public WebElement editButton;
	
	@FindBy(how = How.XPATH, using = "(//td/input[contains(@onblur,'formatPhone')])[1]")
	public WebElement contactDetailsPhoneEditInput;
	
	@FindBy(how = How.XPATH, using = "(//input[contains(@value,'Save')])[1]")
	public WebElement contactDetailsEditSaveButton;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//td//div/input[@id='phSearchInput']")
	public WebElement searchEnter;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//td//div/input[@id='phSearchInput']/parent::div/following-sibling::div/input")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th/a[@data-seclke='Account']")
	public List<WebElement> accountsAllRecords;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th/a[@data-seclke='Contact']")
	public List<WebElement> contactNameTextAllRecordLink;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Email']/parent::td/following-sibling::td/input")
	public WebElement emailInput;
	
	

}
