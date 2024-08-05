package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya
 * 
 *         Guided By Journey (IBLC ) Page Objects
 *
 */
public class SFDC_GuidedByJourney_IBLC_Objects {

	public SFDC_GuidedByJourney_IBLC_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//label/span[contains(.,'Promo 1')]")
	public List<WebElement> iblcPromo1_allRadioBtn;

	@FindBy(how = How.XPATH, using = "//label/span[contains(.,'Promo 2')]")
	public List<WebElement> iblcPromo2_allRadioBtn;

	@FindBy(how = How.XPATH, using = "//label/span[contains(.,'Bundle')]")
	public List<WebElement> iblcInternetBundlePromo_allRadioBtn;

	@FindBy(how = How.XPATH, using = "//div[@data-name='editline']")
	public List<WebElement> iblcEditLine_allLinks;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Add a line')]")
	public List<WebElement> iblcAddALine_allBtn;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Rogers Business Phone')]")
	public WebElement iblc_rogersBusinessPhone_Text;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Basic']/parent::div//button[contains(.,'Add a line')]")
	public WebElement iblc_Basic_AddALine_Btn;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Standard']/parent::div//button[contains(.,'Add a line')]")
	public WebElement iblc_Standard_AddALine_Btn;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Pro']/parent::div//button[contains(.,'Add a line')]")
	public WebElement iblc_Pro_AddALine_Btn;

	@FindBy(how = How.XPATH, using = "//input[@name='numericInput']")
	public List<WebElement> iblc_numeric_Input;

	@FindBy(how = How.XPATH, using = "//button[@value='updateCart']")
	public WebElement lineQty_UpdateCart_Btn;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Ported')]//span[contains(.,'Ported')]")
	public List<WebElement> ported_RadioBtn;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Native')]//span[contains(.,'Native')]")
	public List<WebElement> native_RadioBtn;

	@FindBy(how = How.XPATH, using = "//label[@class='nds-checkbox__label']//span[2]")
	public List<WebElement> voiceFeatureGroup_allCheckBox;

	@FindBy(how = How.XPATH, using = "//label[@class='nds-checkbox__label']//preceding-sibling::input")
	public List<WebElement> voiceFeatureGroup_allChkBoxInput;

	@FindBy(how = How.XPATH, using = "//button[@value='updateCart']")
	public WebElement updateCartButton;

	@FindBy(how = How.XPATH, using = "//label[@class='nds-radio__label']/span[2]")
	public List<WebElement> longDistancePlan_AllRadioBtns;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'Distinctive')]/following-sibling::label//span[1]")
	public List<WebElement> distinctiveRing_AllCheckBoxes;

	@FindBy(how = How.XPATH, using = "//input[contains(@type,'checkbox') ]//following-sibling::label[@class='nds-checkbox__label']//span[2]")
	public List<WebElement> specialBlockFeatures_AllCheckBoxes;

	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Operator Call Block') or contains(@value,'Toll Block') or contains(@value,'Toll Block - International\\')  ]//following-sibling::label[@class='nds-checkbox__label']//span[2]")
	public List<WebElement> admFeaturesGrp_AllCheckBoxes;

	@FindBy(how = How.XPATH, using = "//input[@value='Unlisted Number']/following-sibling::label//span[2]")
	public WebElement unlistedNumber_checkBox;

}
