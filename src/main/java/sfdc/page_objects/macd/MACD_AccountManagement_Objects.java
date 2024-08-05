package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_AccountManagement_Objects {
	
	public MACD_AccountManagement_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}


	@FindAll({ @FindBy(xpath = "//div[@title='Manage Account']"),
	@FindBy(xpath = "//button[text()='MACD']")})
	public WebElement manageAccountButton;
	
	@FindBy(how = How.XPATH,using = "//*[normalize-space()='Account Management']")
	public WebElement accManagementHeader;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'account-name')]")
	public WebElement selectPhoneNumberHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='AccountNumber']/div")
	public List<WebElement> accNumberList;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='PhoneNumber']/div")
	public List<WebElement> phoneNumberList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'radio_faux')]")
	public List<WebElement> selectNameRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//button[@value='continue']")
	public WebElement selectNumberBtn;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm & proceed']")
	public WebElement confirmAndProceedBtn;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Changes to your Service']")
	public WebElement changesToYourServiceHeader;
	
	@FindBy(how = How.XPATH, using = "//button[@value='cancel']")
	public WebElement cancelPhoneNumberChange;
	
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[1]")
	public WebElement searchTelephone;
	
	@FindBy(how = How.XPATH, using = "//input[@type='radio']")
	public List<WebElement> selectNumberRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Price Plan Change']")
	public WebElement pricePlanChangeTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Add a Line']")
	public WebElement addALineTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Add-On']")
	public WebElement addOnTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Add or Remove SIM']")
	public WebElement addOrRemoveSIMTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Swap SIM']")
	public WebElement swapSIMTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Hardware Upgrade']")
	public WebElement hardwareUpgradeTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Lorem Ipsum H1']")
	public WebElement loremIpsumH1Tile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Lorem Ipsum H2']")
	public WebElement loremIpsumH2Tile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Port In']")
	public WebElement portInTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Port Out']")
	public WebElement portOutTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Transfer of Responsibility']")
	public WebElement transferOfResponsibilityTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Telephone Number Change']")
	public WebElement telephoneNumberChangeTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Lorem Ipsum transfer']")
	public WebElement loremIpsumTransferTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Display Name']")
	public WebElement displayNameTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Change Voicemail Password']")
	public WebElement changeVoicemailPasswordTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Language Change']")
	public WebElement languageChangeTile;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Service Blocks']")
	public WebElement serviceBlocksTile;

	@FindBy(how = How.XPATH, using = "//input[contains(@class,'vlocity-input')]")
	public WebElement searchTelephoneNo;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'tableRowCell')]")
	public List<WebElement> fetchData;
	
	@FindBy(how = How.XPATH, using = "//*[@title='Manage Account']")
	public WebElement manageAccBtn;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-grid title')][1]")
	public WebElement caseTitle;
	
	@FindBy(how = How.XPATH, using="//div[normalize-space()='Create Case']")
	public WebElement createCase;
	
	@FindBy(how = How.XPATH, using="//div[normalize-space()='Skip case creation']")
	public WebElement skipCaseCreation;
	
}
