package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:02/04/2021
 *
 *         Communities> PBF page objects
 */
public class Communities_PBF_Objects {

	public Communities_PBF_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//img[@alt='Rogers Logo'] ")
	public WebElement rogersLogo;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Where would you like your services to be located') or contains(text(),'Où souhaitez-vous installer vos services')]")
	public WebElement letsGetStartedHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Select a business account to begin') or contains(text(),'Sélectionnez un compte Affaires pour commencer')]")
	public WebElement selectAddressFlowText;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select a business account') or contains(text(),'Sélectionnez un compte Affaires')]//ancestor::div[contains(@class,'combobox')]//input")
	public WebElement businessAccountDefaultValue;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select a business account') or contains(text(),'Sélectionnez un compte Affaires')]//ancestor::div[contains(@class,'combobox')]//li/div")
	public List<WebElement> businessAccountValuesToSelect;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Select a business account') or contains(text(),'Sélectionnez un compte Affaires')]//ancestor::div[contains(@class,'combobox')]//li//span[contains(@class,'option')]")
	public List<WebElement> businessAccountValues;

	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//div[contains(@class,'table')]//*[@data-field-name='serviceAddressStreetConcat']/div")
	public List<WebElement> serviceAddressValues;
	
	@FindBy(how = How.XPATH, using = "//label[.='Select a business account' or contains(text(),'Sélectionnez un compte Affaires')]//ancestor::div[contains(@class,'combobox')]")
	public WebElement businessAccDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[.='Select a business account to begin' or contains(text(),'Sélectionnez un compte Affaires pour commencer')]")
	public WebElement headingForBusAccDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[.='You will need to create one order per business account' or contains(text(),'Vous devrez créer une commande pour chaque compte Affaires')]")
	public WebElement subHeadingForBusAccDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nextBtn']//button")
	public WebElement nextButton;

	@FindAll({@FindBy(how = How.XPATH, using = "//span[@class='nds-radio_faux']"),
			@FindBy(how = How.XPATH, using = "//span[@class='nds-checkbox_faux']")})
	public List<WebElement> serviceAddressRadioButtons;
	

	@FindBy(how = How.XPATH, using = "//*[@data-id='fibredatatable']//div[contains(@class,'table')]//span[@class='nds-radio_faux']")
	public List<WebElement> serviceAddressFibreRadioButtons;
	
	
	@FindBy(xpath = "//*[@class='table-border']//*[contains(@class,'data-table-body')]//slot/c-dgtl-generic-cell/div")
	public WebElement siteContactName;
	
	@FindBy(how = How.XPATH, using = "//span[@class='nds-checkbox_faux']")
	public List<WebElement> serviceAddressRadioButtonSquare;
		
	@FindBy(how = How.XPATH, using = "//div[.='Shopping cart']")
	public WebElement shoppingCartLabel;
	
	@FindBy(how = How.XPATH, using = "//label[@aria-label='Filter by Keywords']/parent::span/parent::div/preceding-sibling::input")
	public WebElement filterInputBox;
	
	@FindBy(how = How.XPATH, using = "(//label[@aria-label='Filter by Keywords']/parent::span/parent::div/preceding-sibling::input)[2]")
	public WebElement filterInputBoxFibre;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//div[contains(@class,'table-head')]/parent::div/following-sibling::div/b")
	public WebElement noSiteFoundMessage;
	
	@FindBy(how = How.ID, using = "username")
	public WebElement userNameInputBox;
	
	@FindBy(how = How.ID, using = "password")
	public WebElement passwordInputBox;
	
	@FindBy(how = How.ID, using = "Login")
	public WebElement loginButton;
	
	@FindBy(how = How.XPATH, using = "//a[.='Français']")
	public WebElement frenchLangButton;
	
	@FindBy(how = How.XPATH, using = "//a[.='English']")
	public WebElement englishLangButton;
	
	@FindAll ({ 
		@FindBy(xpath = "//*[@data-omni-key='LWC_ServiceAddressSelectionTable']//span[contains(text(),'Total')]"),
		@FindBy(xpath = "//*[@data-omni-key='LWC_ServiceableLocations']//span[contains(text(),'Total')]")
	})
	public WebElement totalServiceAddresses;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='serviceAddressStreetConcat']")
	public WebElement streetAddressHeaderButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='serviceAddressCity']")
	public WebElement cityHeaderButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='serviceAddressProvince']")
	public WebElement provinceHeaderButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field='serviceAddressPostalCode']")
	public WebElement postalCodeHeaderButton;

	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//button[@title='Next']/parent::c-button")
	public WebElement nextPageButtonParent;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='LWC_ServiceAddressSelectionTable']//div[@class='desktop']//button[@title='Next']")
	public WebElement nextPageButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='LWC_ServiceAddressSelectionTable']//div[@class='desktop']//button[@title='Previous']")
	public WebElement previousPageButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//button[@title='Previous']/parent::c-button")
	public WebElement previousPageButtonParent;
	
	@FindAll ({@FindBy(xpath = "//button[contains(text(),'Add new site') or contains(text(),'Ajouter un nouvel emplacement')]")})
	public WebElement addNewSiteButton;
	
	
}
