package sfdc.page_objects.wacc;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_Product_Selection_Objects {

	public WACC_Product_Selection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Products selection' or normalize-space()='Sélection de produits']")
	public WebElement productsHeader;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[normalize-space()='Browse through the categories of business products to see how our solutions can help.']"),
	@FindBy(how = How.XPATH, using = "//div[ normalize-space()='Sélection de produits']/following-sibling::div[1]")})
	public WebElement productSubHeader;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@value='INTERNET_V2']"),
		@FindBy(how = How.XPATH, using = "//button[@value='INTERNET']") })
	public WebElement shopBusinessInternetPlansButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@value='TV_V2']") })
	public WebElement shopBusinessTVPlansButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@value='RDI_V2']"),
		@FindBy(how = How.XPATH, using = "//button[@value='RDI_V2']") })
	public WebElement shopDedicatedInternetPlansButton;

	@FindBy(how = How.XPATH, using = "(//div[normalize-space()='Wireless Plans'])[2]")
	public WebElement wirelessPlansText;

	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Shop') and contains(normalize-space(),'Wireless') and contains(normalize-space(),'Plans')] | //button[normalize-space()='Magasiner les forfaits sans-fil']")
	public WebElement shopWirelessPlansButton;

	@FindBy(how = How.XPATH, using = "//div[text()='Rogers Wireless Plans' or text()='Forfaits sans-fil de Rogers']")
	public WebElement wirelessPlansHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Select your Rogers wireless plan')]")
	public WebElement wirelessPlansSubHeader;

	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Voice and Data' or normalize-space()='Voix et données']")
	public WebElement voiceAndDataTab;

	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Data Only' or normalize-space()='Données seulement']")
	public WebElement dataOnlyTab;

	@FindBy(how = How.XPATH, using = "//div[@data-id='bucketsize']")
	public WebElement planValue;

	@FindBy(how = How.XPATH, using = "//p[contains(normalize-space(),'Choose') or contains(normalize-space(),'Choisissez')]")
	public WebElement chosePlanTypeText;

	@FindBy(how = How.XPATH, using = "//span//input[@type='radio' and @value='Standalone']")
	public WebElement standaloneOption;

	@FindBy(how = How.XPATH, using = "//span//input[@type='radio' and @value='Pooled']")
	public WebElement pooledOption;

	@FindAll({@FindBy(how = How.XPATH, using = "//button[normalize-space()='Add to cart' or normalize-space()='Add to Cart'][@disabled]"),
		@FindBy(how = How.XPATH, using = "//button[normalize-space()='Ajouter au panier' or normalize-space()='Ajouter au Panier'][@disabled]")})
	public WebElement disabledAddToCart;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue to add-ons' or normalize-space()='Poursuivre vers les options'][@disabled]")
	public WebElement disabledAddOns;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Add to cart' or normalize-space()='Add to Cart' or normalize-space()='Ajouter au panier' or normalize-space()='Ajouter au Panier']")
	public WebElement addToCart;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue to add-ons' or normalize-space()='Poursuivre vers les options']")
	public WebElement continueToAddOns;

	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid']//div[@class='price']")
	public WebElement priceValue ;

	@FindBy(how = How.XPATH, using = "//div[@class='device-attr']")
	public WebElement productSelected;

	@FindBy(how = How.XPATH, using = "(//button[@class='previous-button'])[1]")
	public WebElement backToPrevious;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Recurring TCV' or normalize-space()='Valeur totale des coûts récurrents'])[1]/following-sibling::div")
	public WebElement footerRecurringTcvPrice;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Total Costs' or normalize-space()='Coûts totaux'])[1]/following-sibling::div")
	public WebElement totalCostsValue;

	@FindBy(how = How.XPATH, using = "//div[@class='nds-col'][contains(normalize-space(),'post tax credit available per line') or contains(normalize-space(), 'crédit après les taxes pour chaque ligne')]")
	public WebElement postCreditAmountNote;

	@FindBy(how = How.XPATH, using = ".//button[normalize-space()='Shop Wireless Devices']")
	public WebElement shopWirelessDevBtn;

	//	@FindBy(how = How.XPATH, using = "//div[text()='//div[text()='Add extra features to your plan']")
	//    public WebElement shopWirelessDevBtn;


	@FindBy(how = How.XPATH, using = "//div[contains(@class,'addon-text')  and (contains(normalize-space(),'Add extra features to your plan') or contains(normalize-space(),'Ajoutez d’autres fonctions à votre forfait'))]")
	public WebElement addExtraFeatBottomLink;

	@FindBy(how = How.XPATH, using = "//div[text()='3. Choose your device']")
	public WebElement choseDeviceBottomlink;

	@FindBy(how = How.XPATH, using = "//div[text()='4. Add device protection']")

	//@FindBy(how = How.XPATH, using = "//div[contains(text(),'4. Add device protection & accessories')]")
	public WebElement deviceProtectionBottomlink;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue to add-ons' or normalize-space()='Poursuivre vers les options']")
	public WebElement continueToAddOnsBtn;

	@FindBy(how = How.XPATH, using = "//button[text() = 'Browse accessories']")
	public WebElement browseAccessories;
	
	@FindBy(how= How.XPATH, using = "//*[contains(text(), 'item was added to your cart')]")
	public WebElement addToCartSuccessText;
	
	@FindBy(how= How.XPATH, using = "//*[contains(text(), 'Continue shopping')]")
	public WebElement addToCartSuccessSubText;
	
	@FindBy(how= How.XPATH, using = "//*[@class='messageBanner']")
	public WebElement addToCartSuccessBanner;
	
	@FindBy(how = How.XPATH, using = "(//*[contains(@class,'horizontal--medium')])[1]")
	public WebElement addToCartDP;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'item-price_text promo')]")
	public List<WebElement> dpPrice;
	
	@FindBy(how = How.XPATH, using = "//*[@class='device-attr' or contains(@class,'around--medium text-fonts')]")
	public List<WebElement> itemList;
	
	@FindBy(how = How.XPATH, using = "//*[@class='device-attr' or contains(@class,'around--medium text-fonts')]")
	public List<WebElement> itemListAfterClickCloseBut;

	@FindBy(how = How.XPATH, using = "//button//span[contains(normalize-space(),'Skip without')]")
	public WebElement skipDeviceProtectionLink;	

	@FindBy(how = How.XPATH, using = "//slot//div[contains(@class,'font offer-bucketsize')]")
	public List<WebElement> planSizeList;	

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Update cart']")
	public WebElement updateCart;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(text(),'your Rogers wireless plan')]")
	public WebElement headerWirelessPlans;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(text(),'Shop for accessories')]")
	public WebElement headerAccePage;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'nds-text-heading--large-regular')]")
	public WebElement headerProductSelection;	
	
	@FindBy(how = How.XPATH, using = ".//div[contains(text(),'Wireless Plans')]/../..")
	public WebElement wirelessTile;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(text(),'Wireless Plans')]")
	public WebElement wirelessPlansTileheader;
	
	@FindBy(how = How.XPATH, using = ".//li[contains(text(),'Keep connected')]")
	public WebElement wirelessPlansTileDescription;	
	
	@FindBy(how = How.XPATH, using = ".//button[@class='shop-button']")
	public WebElement shopProducts;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'slds-pill__label text-adjustment slds-m-left_xx-small')]")
	public WebElement deviceFilterOptionName;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'slds-button reset-filter-btn resetlabel')]")
	public WebElement deviceFilterResetButton;	
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'addon-text')  and (contains(normalize-space(),'Add extra features to your plan') or contains(normalize-space(),'Ajoutez d’autres fonctions à votre forfait'))]")
	public WebElement addExtraFeature;	

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Choose your device')]")
	public WebElement chooseDevice;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Add device protection')]")
	public WebElement deviceProtection;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue to shopping cart']")
	public WebElement continueToShoppingCart;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'radio')]/preceding-sibling::span")
	public List<WebElement> DPradioBtn;	
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Device Protection')]/../preceding-sibling::span[contains(@class,'checkbox')]")
	public WebElement DPcheckboxBtn;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'want any device protection')]/preceding-sibling::span[contains(@class,'radio')]")
	public WebElement iDontWantDP;
	
	@FindBy(how = How.XPATH, using = "//div[contains(normalize-space(),'Device Protection: ')]//span/following-sibling::span")
	public WebElement nameOfSelctdDP;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Leave edit')]")
	public WebElement leaveEdit;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Protect your device')]")
	public WebElement DPcaption;
	
	@FindBy(how = How.XPATH, using = "//div[@class='slds-grid']//div[@class='slds-grid slds-grid_vertical']")
	public List<WebElement> DPtilesSideBySide;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Apple Care']/parent::label/parent::span/parent::div/parent::fieldset/parent::div/following-sibling::div//p[contains(text(),'theft')]")
	public WebElement AppleCareDesc;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Device Protection' or text()='Protection de l’appareil']/parent::label/parent::span/parent::div/parent::fieldset/parent::div/following-sibling::div//p[contains(text(),'theft')]")
	public WebElement DeviceProtDesc;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'item-price_text promo')]")
	public WebElement dpPriceSingleDP;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'loss or theft')]")
	public WebElement SingleDPdesc;
	
}

