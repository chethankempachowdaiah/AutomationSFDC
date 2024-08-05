package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 20/jan/2020
 * 
 *         SFDC CPQ Home Page(Opportunity> Create Quote> Select Contact> Select
 *         service account)
 *
 */
public class SFDC_CPQHome_Objects {

	public SFDC_CPQHome_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@ctrl='CPQHeaderController']//h1")
	public List<WebElement> cpqHeaderControllerText;

	@FindBy(how = How.XPATH, using = "//div//button[contains(@title,'Record') and contains(@title,'View')]")
	public WebElement reviewQuoteRecordButton;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Price') and contains(.,'List')  ]//following-sibling::button//span[contains(.,'Select') and contains(.,'Price') and contains(.,'List')]")
	public List<WebElement> selectPriceList;

	@FindBy(how = How.XPATH, using = "//a[@role='menuitemcheckbox']//span[@title='Rogers SMB Price List']")
	public WebElement rogersSMBPriceList;

	@FindBy(xpath = "//label//span[contains(.,'PRODUCTS')]")
	public WebElement productsTab;

	@FindBy(xpath = "//label//span[contains(.,'PROMOTIONS')]")
	public WebElement promotionsTab;

	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'DISCOUNTS')]")
	public WebElement discountsButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Qualified')]")
	public WebElement qualifiedButton;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search']")
	public WebElement searchProductPromotion;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'cpq-product-item')]//button[contains(.,'Add to Cart')]")
	public List<WebElement> addToCartButtonsAllProduct;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'slds-colslds-small-size_2-of-12 slds-medium-size_2-of-12 slds-large-size_2-of-12')]//a[contains(.,'More')]")
	public List<WebElement> addToCartMore;

	
	@FindBy(how = How.XPATH, using = "//tbody//tr[2]//td[2]//div")
	public WebElement productCode;
	
	@FindBy(how = How.XPATH, using = "//button[ @class ='slds-button slds-button--brand' ]")
	public WebElement addToCartInPopUp;
	
	@FindBy(how = How.XPATH, using = "//div[@class='slds-modal__footer slds-is-relative']//button[contains(.,'Close')]")
	public WebElement closeInPopUp;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'cpq-promotion-item')]//button[contains(.,'Add to Cart')]")
	public List<WebElement> addToCartButtonsAllPromotion;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'cpq-discount')]/button[contains(.,'Add to Cart')]")
	public List<WebElement> addToCartButtonsAllDiscounts;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Apply')]")
	public WebElement applyPromotionButton;

	@FindBy(how = How.XPATH, using = "//span[@cpq-translate='Product2.Name']")
	public List<WebElement> productNameTextAllProduct;

	@FindBy(how = How.XPATH, using = "//p[@cpq-translate='Promotion.Name']")
	public List<WebElement> promotionNameTextAllProduct;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'cpq-discount-name')]")
	public List<WebElement> discountNameTextAllProduct;

	@FindBy(how = How.XPATH, using = "//span[@cpq-translate='Product2.Name']//parent::button")
	public List<WebElement> quoteCartProductNameText;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Promotions')]")
	public WebElement promotionsLinkIncart;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Cart')]")
	public WebElement cartLink;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Order')]//following-sibling::div/span[contains(.,'Installation Discount')]")
	public WebElement installationDiscount;

	@FindBy(how = How.XPATH, using = "//button[@title='Show Actions' or contains(@title,'CPQShowActions')]//span")
	public List<WebElement> quoteCartShowActonDropdown;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Configure')]//ancestor-or-self::li//a//span")
	public List<WebElement> quoteCartConfigureLink;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Delete')]//ancestor-or-self::li//a//span")
	public WebElement quoteProductDeleteLink;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Delete')]")
	public WebElement deleteButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Cancel')]//following-sibling::button[contains(.,'Delete')]")
	public WebElement deletePromotionButton;

	@FindBy(how = How.XPATH, using = "//form[@name='productconfig']//h3[contains(.,'Contract Term')]")
	public WebElement quoteCartConfigContractTermText;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Contract Term')]//following-sibling::div//select[contains(@name,'productconfig_field')]")
	public WebElement quoteCartConfigContractTermDropDown;

	@FindBy(how = How.XPATH, using = "//form[@name='productconfig']//legend//label[contains(.,'Static Ips')]")
	public WebElement staticIpsLabel;

	@FindBy(how = How.XPATH, using = "//form[@name='productconfig']//input[@value='1']")
	public WebElement staticIps_1_radio;

	@FindBy(how = How.XPATH, using = "//form[@name='productconfig']//input[@value='5']")
	public WebElement staticIps_5_radio;

	@FindBy(how = How.XPATH, using = "//form[@name='productconfig']//h3[contains(.,'Service Location')]")
	public WebElement quoteCartConfigServiceLocationText;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Service Location')]//following-sibling::div//select[contains(@name,'productconfig_field')]")
	public WebElement quoteCartConfigServiceLocationDropDown;

	@FindBy(how = How.XPATH, using = "//form[@name='productconfig']//div[@class='slds-section']//h3[contains(.,'Internet Speeds')]")
	public WebElement quoteCartConfigInternetSpeedText;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Download Speed')]//following-sibling::div//select[contains(@name,'productconfig_field')]")
	public WebElement quoteCartConfigDownloadSpeedDropDown;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Upload Speed')]//following-sibling::div//select[contains(@name,'productconfig_field')]")
	public WebElement quoteCartConfigUploadSpeedDropDown;

	@FindBy(how = How.XPATH, using = "//button[@id='cpq-custom-view-button']//span[@title='Select an Option']")
	public WebElement cpqCustomViewButton;

	@FindBy(how = How.XPATH, using = "//li//a//span[contains(.,'Advanced')]")
	public WebElement advancedViewOption;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Internet') or contains(.,'Advantage')]//ancestor::div[@class='cpq-item-base-product']//div[3]//span//span")
	public List<WebElement> recurringChargeAllValues;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Internet') or contains(.,'Advantage')]//ancestor::div[@class='cpq-item-base-product']//div[7]//span//span")
	public List<WebElement> recurringCostAllValues;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Internet') or contains(.,'Advantage')]//ancestor::div[@class='cpq-item-base-product']//div[11]//div")
	public List<WebElement> recurringMarginAllValues;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Installation')]//ancestor::div[@class='cpq-item-base-product']//div[5]//span//span")
	public WebElement installationOneTimeChargeValue;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@title,'TV_INSTALLATION')]//ancestor::div[@class='cpq-item-base-product']//div[5]//span//span")
	public WebElement installationOneTimeChargeValueTV;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Installation')]//ancestor::div[@class='cpq-item-base-product']//div[9]//span//span")
	public WebElement installationOneTimeCostValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'TV')]//ancestor::div[@class='cpq-item-base-product']//div[3]//span//span")
	public WebElement bundledTVRecurringCharge;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'TV')]//ancestor::div[@class='cpq-item-base-product']//div[7]//span//span")
	public WebElement bundledTVRecurringCost;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'TV')]//ancestor::div[@class='cpq-item-base-product']//div[11]//div")
	public WebElement bundledTVRecurringMargin;

	@FindBy(how = How.XPATH, using = "//*[@src=\"'cpq-product-cart-item-child'\"]//div[contains(@class, 'cpq-item-base-product')]/button[contains(.,'Add to Cart')]")
	public List<WebElement> promtionInCartSTBGroupAddtoCartAllButtons;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'SD Digital Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[contains(.,'Add to Cart')]")
	public WebElement sdDigitalTerminalAddtoCartButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'SD Digital Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement sdDigitalTerminalShowAction;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'HD Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[contains(.,'Add to Cart')]")
	public WebElement hdTerminalAddtoCartButton;

	//Added webelement list for pointing to right frame
	@FindBy(how = How.XPATH, using = "//span[contains(.,'HD Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[contains(.,'Add to Cart')]")
	public List<WebElement> hdTerminalAddtoCartButtonList;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'HD Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement hdTerminalTerminalShowAction;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'HD PVR Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[contains(.,'Add to Cart')]")
	public WebElement hdPVRTerminalAddtoCartButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'4K Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[contains(.,'Add to Cart')]")
	public WebElement terminal4kAddtoCartButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'4K PVR Terminal')]//ancestor::div[@class='cpq-item-base-product']//button[contains(.,'Add to Cart')]")
	public WebElement terminal4kPVRAddtoCartButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Terminal')]//ancestor::div[@class='cpq-item-base-product']//div[3]//span//span")
	public WebElement terminalSTBGroupRecurringCharge;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Terminal')]//ancestor::div[@class='cpq-item-base-product']//div[7]//span//span")
	public WebElement terminalSTBGroupRecurringCost;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Accept Quote')]")
	public WebElement quoteCartConfigAcceptQuoteButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(.,'View Record') and @class='slds-button slds-button_neutral']")
	public List<WebElement> viewRecordBeforeESIGN;
	
	@FindBy(how = How.XPATH, using = "//li[@class='slds-button slds-button--icon-border-filled oneActionsDropDown']//a[@class='slds-grid slds-grid--vertical-align-center slds-grid--align-center sldsButtonHeightFix' and @title='Show 3 more actions']//span")
	public List<WebElement> dropdownBeforeESIGN;
		
	@FindBy(how = How.XPATH, using = "//div[@title='Request For E-Signatures']")
	public List<WebElement> Esignature;
	
	//Added webelement list for pointing to right frame
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Accept Quote')]")
	public List<WebElement> quoteCartConfigAcceptQuoteButtonList;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//iframe")
	public WebElement continueButtonIframe;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Continue')]")
	public WebElement continueButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Email Quote')]")
	public WebElement quoteCartConfigGenerateEmailButton;

}
