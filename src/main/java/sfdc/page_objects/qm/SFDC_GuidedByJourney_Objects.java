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
 *         Guided By Journey common Objcts for all types of products
 *
 */
public class SFDC_GuidedByJourney_Objects {

	public SFDC_GuidedByJourney_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'TV')]")
	public WebElement tvProduct;

	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'Internet')]")
	public WebElement internetProduct;

	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'Business Phone')]")
	public WebElement iBLCProduct;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Next') and @value='next']")
	public WebElement nextButton;
	
	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'3-year term') or contains(.,'3 Year Term')]")
	public WebElement threeYearTermRadioButton;

	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'Monthly Term')]")
	public WebElement monthlyTermRadioButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Promotions and Discounts')]")
	public List<WebElement> promotionsAndDiscountsAllLinks;

	@FindBy(how = How.XPATH, using = "//button[@name='increment']")
	public WebElement promotionsAndDiscountsIncrementButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Add to Cart')]")
	public List<WebElement> addToCartAllButtons;

	@FindBy(how = How.XPATH, using = "//div[@class='catalog-name']")
	public List<WebElement> catalogNames;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Remove from Cart') or contains(.,'Retirer du panier')]")
	public List<WebElement> removeFromCartAllButtons;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Basic']//ancestor::div[contains(@class,'offer-card')]//div[contains(@class,'offer-price')]//button[contains(.,'Add to Cart')]")
	public WebElement iblcBusinessPhoneBasicAddToCart;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Pro']//ancestor::div[contains(@class,'offer-card')]//div[contains(@class,'offer-price')]//button[contains(.,'Add to Cart')]")
	public WebElement iblcBusinessPhoneProAddToCart;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Standard']//ancestor::div[contains(@class,'offer-card')]//div[contains(@class,'offer-price')]//button[contains(.,'Add to Cart')]")
	public WebElement iblcBusinessPhoneStandardAddToCart;


	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Basic']//ancestor::div[contains(@class,'offer-card')]//div[@class='price']")
	public WebElement iblcBusinessPhoneBasicPrice;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Pro']//ancestor::div[contains(@class,'offer-card')]//div[contains(@class,'offer-price')]//div[@class='price']")
	public WebElement iblcBusinessPhoneProPrice;

	@FindBy(how = How.XPATH, using = "//div[text()='Business Phone Standard']//ancestor::div[contains(@class,'offer-card')]//div[contains(@class,'offer-price')]//div[@class='price']")
	public WebElement iblcBusinessPhoneStandardPrice;
	
	@FindBy(how = How.XPATH, using = "//div[@class='additional-desc']//div[contains(@class,'banner-ribbon-text')]")
	public List<WebElement> offerIncludedABAText;

}
