package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:07/07/2021
 *
 *         Communities> PBF Cable Plan Selection page objects
 */
public class Communities_PBF_CablePlanSelection_Objects {

	public Communities_PBF_CablePlanSelection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'package-speed-info')]/div[contains(@class,'heading')]")
	public List<WebElement> plansTitleHeader;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'pbf-os-radio-label')]")
	public List<WebElement> allPlansProductFeature;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'pbf-os-radio-label')]/preceding-sibling::span")
	public List<WebElement> allPlansProductFeatureRadioClick;
	
	@FindBy(how = How.XPATH, using = "//span[.='Download Speed' or .='Vitesse de téléchargement']/following-sibling::span") 
	public List<WebElement> downloadSpeeds;
	
	@FindBy(xpath = "//*[contains(text(),'a 3 year subscription') or contains(text(),'On a 3 Year Term') or contains(text(),'Avec un Abonnement de 3')]")
	public List<WebElement> threeYearTermText;
	
	@FindBy(xpath = "//*[contains(text(),'On a Monthly Term') or contains(text(),'Avec un Abonnement mensuel')]")
	public List<WebElement> monthlyTermText;
	
	@FindBy(xpath = "//span[contains(text(),'Advantage WiFi with LTE backup') or contains(text(),'WiFi Avantage') and contains(text(),'avec connexion secondaire LTE')]//parent::label//following-sibling::span//img[@alt='Tool Tip']/parent::div")
	public List<WebElement> toolTipIconForAdvantageWiFiLTEbackup;
	
	@FindBy(xpath = "//span[contains(text(),'Advantage WiFi') or contains(text(),'WiFi Avantage') and not(contains(text(),'LTE'))]//parent::label//following-sibling::span//img[@alt='Tool Tip']/parent::div")
	public List<WebElement> toolTipIconForAdvantageWiFi;
	
	@FindBy(xpath = "//span[contains(text(),'Business Internet') or contains(text(),'Internet Affaires') and not(contains(text(),'LTE'))]//parent::label//following-sibling::span//img[@alt='Tool Tip']/parent::div")
	public List<WebElement> toolTipIconForBusinessInternet;
	
	@FindBy(xpath = "//span[contains(text(),'Business Internet with LTE Backup') or contains(text(),'Internet Affaires') and contains(text(),'avec connexion secondaire LTE')]//parent::label//following-sibling::span//img[@alt='Tool Tip']/parent::div")
	public List<WebElement> toolTipIconForBusinessInternetLTEbackup;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'tooltip-body')]")
	public WebElement activeToolTipText;
	
	@FindBy(how = How.XPATH, using = "//img[@alt='Close']/parent::div")
	public WebElement closeToolTipIcon;
	
	@FindBy(how = How.XPATH, using = "//div[.='Gigabit']/parent::div[contains(@class,'package-speed')]//ancestor::div[contains(@class,'tile')]//div[contains(@class,'short-desc')]")
	public WebElement shortDescGigaBitPack;
	
	@FindBy(how = How.XPATH, using = "//div[.='500u' or .='500i']/parent::div[contains(@class,'package-speed')]//ancestor::div[contains(@class,'tile')]//div[contains(@class,'short-desc')]")
	public WebElement shortDesc500UPack;
	
	@FindBy(how = How.XPATH, using = "//div[.='150u' or .='150i']/parent::div[contains(@class,'package-speed')]//ancestor::div[contains(@class,'tile')]//div[contains(@class,'short-desc')]")
	public WebElement shortDesc150UPack;
	
	@FindBy(how = How.XPATH, using = "//div[.='30u' or .='30i']/parent::div[contains(@class,'package-speed')]//ancestor::div[contains(@class,'tile')]//div[contains(@class,'short-desc')]")
	public WebElement shortDesc30UPack;
	
	@FindBy(how = How.XPATH, using = "//div[.='All plans include']/following-sibling::div/div")
	public List<WebElement> allPlansIncludeList;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'package-feature')]")
	public List<WebElement> packageFeaturesDiv;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'package-pricing')]")
	public List<WebElement> packagePricingDiv;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'package-speed')]")
	public List<WebElement> packageSpeedDiv;
	
	@FindAll({	
		@FindBy(how = How.XPATH, using = "//*[@data-omni-key='PriceAndSpeedOptionsStep']//div[@class='circle-mobile']"),
		@FindBy(how = How.XPATH, using = "//*[@data-omni-key='PriceAndSpeedOptionsStep']//div[@class='circle']"),
		@FindBy(how = How.XPATH, using = "//*[@data-omni-key='DedicatedInternetCatalog']//div[@class='circle']")})
	public WebElement shoppingCartIconProductCount;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'selected-tile-container')]")
	public WebElement selectedDivContainer;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Proceed to shopping cart') or contains(text(),'Aller au panier d')]")
	public WebElement proceedToShoppingCartButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Add to cart') or contains(.,'Ajouter au panier')]")
	public List<WebElement> addToCartAllButtons;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-spinner_brand nds-spinner nds-is-absolute']")
	public WebElement pageLoading;
	
	/*
	 * @FindBy(how = How.XPATH, using =
	 * "//div[text()='Gigabit']//ancestor::div[contains(@class,'package-tile')]//button[contains(.,'Add to cart')]"
	 * ) public WebElement addToCartGigaBitProduct;
	 * 
	 * @FindBy(how = How.XPATH, using =
	 * "//div[text()='500u']//ancestor::div[contains(@class,'package-tile')]//button[contains(.,'Add to cart')]"
	 * ) public WebElement addToCart500uProduct;
	 * 
	 * @FindBy(how = How.XPATH, using =
	 * "//div[text()='150u']//ancestor::div[contains(@class,'package-tile')]//button[contains(.,'Add to cart')]"
	 * ) public WebElement addToCart150uProduct;
	 * 
	 * @FindBy(how = How.XPATH, using =
	 * "//div[text()='30u']//ancestor::div[contains(@class,'package-tile')]//button[contains(.,'Add to cart')]"
	 * ) public WebElement addToCart30uProduct;
	 */
	
	
	
}
