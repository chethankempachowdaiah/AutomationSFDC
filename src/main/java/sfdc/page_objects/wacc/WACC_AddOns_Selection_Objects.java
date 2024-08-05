package sfdc.page_objects.wacc;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_AddOns_Selection_Objects {

	public WACC_AddOns_Selection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-edit')]")
	public WebElement editbuttton;

	@FindBy(how = How.XPATH, using = "//*[name()='svg'][@data-key='chevronup']//ancestor::div//div[normalize-space()='SMS' or normalize-space()='Message texte']")
	public WebElement expandSMS;

	@FindBy(how = How.XPATH, using = "//*[name()='svg'][@data-key='chevronup']//ancestor::div//div[normalize-space()='Roaming' or normalize-space()='Itinérance']")
	public WebElement expandRoaming;

	@FindBy(how = How.XPATH, using = "//*[name()='svg'][@data-key='chevronup']//ancestor::div//div[normalize-space()='Voicemail' or normalize-space()='Messagerie vocale']")
	public WebElement expandVoicemail;

	@FindBy(how = How.XPATH, using = "//*[name()='svg'][@data-key='chevronup']//ancestor::div//div[normalize-space()='Long Distance' or normalize-space()='Interurbains']")
	public WebElement expandLongDistance;

	@FindAll({@FindBy(how = How.XPATH, using = "//*[contains(text(), 'US LD')]/ancestor::div[contains(@class,'around_large slds-m-bottom_large')]//*[contains(normalize-space(),'No Offer')]/preceding-sibling::span"),
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Interurbains aux États-Uni')]/ancestor::div[contains(@class,'around_large slds-m-bottom_large')]//*[contains(normalize-space(),'Aucune offre')]/preceding-sibling::span")})
	public WebElement noOfferUSLDRadio;

	@FindAll({@FindBy(how = How.XPATH, using = "//*[contains(text(), 'International LD Saver')]/ancestor::div[contains(@class,'around_large slds-m-bottom_large')]//*[contains(normalize-space(),'No Offer')]/preceding-sibling::span"),
		@FindBy(how = How.XPATH, using = "//*[contains(text(), 'InterÉpargne outre-mer')]/ancestor::div[contains(@class,'around_large slds-m-bottom_large')]//*[contains(normalize-space(),'Aucune offre')]/preceding-sibling::span")})
	public WebElement noOfferIntLDsaverRadio;

	@FindBy(how = How.XPATH, using = "//span[normalize-space()='US LD' or normalize-space()='Interurbains aux États-Unis']/preceding-sibling::span[contains(@class,'radio_faux')]")
	public WebElement wirelessUSLDRadio;

	@FindBy(how = How.XPATH, using = "//span[normalize-space()='International LD Saver' or normalize-space()='InterÉpargne outre-mer']/preceding-sibling::span[contains(@class,'radio_faux')]")
	public WebElement wirelessIntLDSaverRadio;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='US LD' or normalize-space()='Interurbains aux États-Unis']//ancestor::label//input[@type='checkbox']")
	public WebElement USLD_AddOn;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='International LD Saver' or normalize-space()='InterÉpargne outre-mer']//ancestor::label//input[@type='checkbox']")
	public WebElement InternationalLDSaver_AddOn;

	@FindBy(how = How.XPATH, using = "//label//span[contains(normalize-space(),'Unlimited Canada to US') or contains(normalize-space(),'Messagerie texte et multimédia illimitée du Canada')]//ancestor::label//input[@type='checkbox']")
	public WebElement unlimitedSMS_AddOn;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Canada + US' or normalize-space()='Itinérance au Canada et aux États-Unis']//ancestor::label//input[@type='checkbox']")
	public WebElement roaming_AddOn;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Premium Voicemail to Text' or normalize-space()='Messagerie vocale à texto supérieure']//ancestor::span//input[@type='radio']")
	public WebElement premiumVoiceMailText;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='iPhone Visual Voicemail' or normalize-space()='Messagerie vocale visuelle pour iPhone']//ancestor::span//input[@type='radio']")
	public WebElement iPhVoicemailText;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='US LD' or normalize-space()='Interurbains aux États-Unis']//ancestor::div[1]//parent::div//parent::div//following-sibling::div[2]")
	public WebElement USLDpriceValue;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='International LD Saver' or normalize-space()='InterÉpargne outre-mer']//ancestor::div[1]//parent::div//parent::div//following-sibling::div[2]")
	public WebElement intLDSaverpriceValue;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Premium Voicemail to Text' or normalize-space()='Messagerie vocale à texto supérieure']//ancestor::span//parent::div//parent::fieldset//parent::div//parent::div//div[3]")
	public WebElement premiumVoicemailPriceValue;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='iPhone Visual Voicemail' or normalize-space()='Messagerie vocale visuelle pour iPhone']//ancestor::span//parent::div//parent::fieldset//parent::div//parent::div//div[3]")
	public WebElement iPhVoicemailPriceValue;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Unlimited Canada to US/Intl SMS/MMS' or normalize-space()='Messagerie texte et multimédia illimitée du Canada vers les É.-U. et l’étranger']//ancestor::div[1]//parent::div//parent::div//following-sibling::div[2]")
	public WebElement smsPriceValue;

	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Canada + US' or normalize-space()='Itinérance au Canada et aux États-Unis']//ancestor::div[1]//parent::div//parent::div//following-sibling::div[2]")
	public WebElement roamingPriceValue;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Add to cart' or normalize-space()='Add to Cart' or normalize-space()='Ajouter au panier'][@disabled]")
	public WebElement disabledAddToCart;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Add to cart' or normalize-space()='Add to Cart' or normalize-space()='Add add-ons']")
	public WebElement addToCart;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Update cart' or normalize-space()='Ajouter au panier']")
	public WebElement updateCart;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Proceed to shopping cart' or normalize-space()='Aller au panier d’achats']")
	public WebElement proceedToShopCartButton;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'total-footer')])[1]//div[normalize-space()='Recurring TCV' or normalize-space()='Valeur totale des coûts récurrents']/following-sibling::div")
	public WebElement footerRecurringTcvPrice;

    @FindBy(how = How.XPATH, using = "//div[normalize-space()='Shopping Cart' or normalize-space()='Shopping cart'] | //div[normalize-space()='Panier d’achats']")
	public WebElement shopCartHeader;

	@FindBy(how = How.XPATH, using = "(//button[@class='previous-button'])[1]")
	public WebElement backToPrevious;
	
	@FindBy(how = How.XPATH, using = "//button[@c-wacatalog_wacatalog and normalize-space()='Continue to Device']")
	public WebElement btnContiueToDev;

	@FindBy(how = How.XPATH, using = "//div[@c-wacatalog_wacatalog]//button[@c-wacatalog_wacatalog and normalize-space()='Continue to Device']")
	public WebElement continueToDeviceSelectionButton;
	
	@FindBy(how = How.XPATH, using = "//div[@c-wacatalog_wacatalog]//button[@c-wacatalog_wacatalog and normalize-space()='Bring your own device']")
	public WebElement byodSelectionButton;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Add device protection & accessories']")
	public WebElement addDeviceProtectionButton;
	
	@FindBy(how = How.XPATH, using = "//div[@c-wacatalog_wacatalog]//span[contains(text(),'Continue')]")
	public WebElement deviceTypeSelectHeader;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Back to browse devices']")
	public WebElement btnBackToBrowseDev;	

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'vertical_small text-heading_large')]")
	public List<WebElement> addOnHeadingList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'addon-item-text_large')]")
	public List<WebElement> addOnList;
	
	@FindBy(how = How.XPATH, using = "//*[@name='voicemail']/following-sibling::label//span[contains(@class,'radio-options_text')]")
	public List<WebElement> voiceMailAddOnList;
	
	@FindBy(how = How.XPATH, using = "//*[@name='voicemail']/following-sibling::label//span[contains(@class,'radio_faux')]")
	public List<WebElement> voiceMailAddOnRadioBtnList;

	/*
	 * @FindBy(how = How.XPATH, using =
	 * "//div[contains(@class,'wrapper')]//div[contains(@class,'addon-text')]//span[@class='close_font']")
	 * public WebElement btnClose;
	 */
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'wrapper')]//span[@class='close_font']")
	public WebElement btnClose;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'-checkbox__label')]/preceding-sibling::span")
	public List<WebElement> addOnradioBtnList;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue']")
	public WebElement continueButtonForBYOD;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue to Plans']")
	public WebElement continueToPlansButton;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm selection']")
	public WebElement confirmselectionButton;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Cancel edit']")
	public WebElement cancelEditButton;

}
