package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:20/07/2021
 *
 *         Communities> PBF Add New SIte page objects
 */
public class Communities_PBF_AddNewSite_Objects {

	public Communities_PBF_AddNewSite_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Add a new site') or contains(text(),'Ajouter un nouvel emplacement')]")
	public WebElement addNewSiteHeader;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[@aria-label='Street No' or @aria-label='Numéro municipal']/parent::div/preceding-sibling::input"),
		@FindBy(how = How.XPATH, using = "//label[text()='Street No' or @aria-label='Numéro municipal']/parent::div/preceding-sibling::input")})
	public WebElement streetNoInput;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[text()='Street Name' or @aria-label='Nom de rue']/parent::div/preceding-sibling::input"),
		@FindBy(how = How.XPATH, using = "//label[@aria-label='Street Name' or @aria-label='Nom de rue']/parent::div/preceding-sibling::input")})
	public WebElement streetNameInput;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[text()='Street Type' or @aria-label='Type de rue']/parent::div/preceding-sibling::input"),
		@FindBy(how = How.XPATH, using = "//label[@aria-label='Street Type' or @aria-label='Type de rue']/parent::div/preceding-sibling::input")})
	public WebElement streetTypeInput;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[text()='City' or @aria-label='Ville']/parent::div/preceding-sibling::input"),
		@FindBy(how = How.XPATH, using = "//label[@aria-label='City' or @aria-label='Ville']/parent::div/preceding-sibling::input")})
	public WebElement cityInput;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[text()='Province']/parent::div/parent::div"),
		@FindBy(how = How.XPATH, using = "//label[@aria-label='Province']/parent::div/parent::div")})
	public WebElement provinceInput;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//label[text()='Postal Code' or @aria-label='Code postal']/parent::div/preceding-sibling::input"),
		@FindBy(how = How.XPATH, using = "//label[@aria-label='Postal Code' or @aria-label='Code postal']/parent::div/preceding-sibling::input")})
	public WebElement postalCodeInput;
	
	@FindBy(how = How.XPATH, using = "//div[.='Your entry does not match the allowed pattern.']")
	public WebElement patternNotMatchError;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Search for my site') or contains(text(),'Rechercher mon emplacement')]")
	public WebElement searchMySiteButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'next')]")
	public WebElement saveSiteButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'prev')]")
	public WebElement cancelAndBackButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='concatAddress']")
	public WebElement streetAddressHeaderButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='City']")
	public WebElement cityHeaderButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='Province']")
	public WebElement provinceHeaderButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='PostalCode']")
	public WebElement postalCodeHeaderButton;

	@FindBy(how = How.XPATH, using = "//*[@data-field='SuiteFloor']")
	public WebElement suiteFloorHeaderButton;
	
	@FindAll({@FindBy(xpath = "//div[contains(text(),'I don') and contains(text(),'t see my address')]"),
		@FindBy(xpath = "//div[contains(text(),'Je ne vois pas mon adresse')]")})
	public WebElement iDontSeeMyAddressOption;
	
	@FindBy(how = How.XPATH, using = "//button[@value='Confirm' or @value='continue']")
	public WebElement confirmAddressButton;
	
	@FindBy(how = How.XPATH, using = "//h1[.='At this time, there are no Rogers Internet services available at this location.']")
	public WebElement noServiceErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Step 2: Select an address') or contains(text(),'Étape 2 : Sélectionnez une adresse')]//ancestor::div[contains(@class,'open-card')]")
	public WebElement selectAddressOpenDIV;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Step 1: Search for an address') or contains(text(),'Étape 1 : Cherchez une adresse')]//ancestor::div[contains(@class,'open-card')]")
	public WebElement searchAddressOpenDIV;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Step 1: Search for an address') or contains(text(),'Étape 1 : Cherchez une adresse')]//following-sibling::h1//*[contains(text(),'Edit') or contains(text(),'Modifier')]")
	public WebElement searchAddressEditButton;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Step 2: Select an address') or contains(text(),'Étape 2 : Sélectionnez une adresse')]//following-sibling::h1//*[contains(text(),'Edit') or contains(text(),'Modifier')]")
	public WebElement selectAddressEditButton;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(@class,'address-text')]")
	public WebElement selectedAddressText;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//div[contains(@class,'table')]//*[@data-field-name='concatAddress']/div")
	public List<WebElement> serviceAddressValues;
	
	@FindBy(how = How.XPATH, using = "//*[@data-id='fibredatatable']//div[contains(@class,'table')]//*[@data-field-name='concatAddress']/div")
	public List<WebElement> serviceAddressValuesFibre;
}
