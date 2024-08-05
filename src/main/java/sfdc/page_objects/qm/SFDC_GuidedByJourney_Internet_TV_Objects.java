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
 *         Guided By Journey (Internet & TV) Page Objects
 *
 */
public class SFDC_GuidedByJourney_Internet_TV_Objects {

	public SFDC_GuidedByJourney_Internet_TV_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[contains(@data-value,'RGR_INT')]/parent::div")
	public List<WebElement> selectGradeInternet;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Select TV Grade') or contains(.,'Select Business TV Grade')]/parent::div/preceding-sibling::input")
	public List<WebElement> selectGradeTV;

	@FindBy(how = How.XPATH, using = "//ul[@role='presentation']/li//div[@role='option']")
	public List<WebElement> productOptionsList;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'Promo') or contains(@id,'Term')  and contains(@id, '150')]/following-sibling::label[@class='nds-radio__label']//span[2]")
	public WebElement promo150U;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'Promo') or contains(@id,'Term') and contains(@id, '500')]/following-sibling::label[@class='nds-radio__label']//span[2]")
	public WebElement promo500U;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'Promo') or contains(@id,'Term') and starts-with(@id, '1G')]/following-sibling::label[@class='nds-radio__label']//span[2]")
	public WebElement promo1G;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'Promo') and @type='radio']/following-sibling::label")
	public List<WebElement> promocodesRadioButtons;

	@FindBy(how = How.XPATH, using = "//div[@class='price']")
	public List<WebElement> price;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'heading') and contains(.,'Microsoft')]")
	public List<WebElement> microsoft365AddOnHeading;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'heading') and contains(.,'Terminal')]")
	public List<WebElement> tvAddOnTerminal;

	@FindBy(how = How.XPATH, using = "//input[@name='numericInput']")
	public List<WebElement> productTerminalQtyNumeric;

	@FindBy(how = How.XPATH, using = "//button[@data-name='updateCart']")
	public WebElement updateCartButton;

	@FindBy(how = How.XPATH, using = "//button[@value='acceptQuote']")
	public WebElement acceptQuoteButton;
	
	@FindBy(how = How.XPATH, using = "//button[@value='emailQuote']")
	public WebElement emailQuoteButton;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th//div[@class='outputLookupContainer forceOutputLookupWithPreview']//a")
	public List<WebElement> selectedProduct;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Account Name']//parent::div//following-sibling::div//span//span")
	public WebElement businessAccName;
	
	@FindBy(how = How.XPATH, using = "//*[text() = 'Details' and @class='title']")
	public WebElement detailsTab;
	
	@FindBy(how = How.XPATH, using = "//button[@value='eSignature']")
	public WebElement eSignatureButton;	

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'Monthly Total')]//preceding-sibling::div")
	public WebElement monthlyTotal;

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'Recurring TCV')]//preceding-sibling::div")
	public WebElement recurringTCV;

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'One Time TCV')]//preceding-sibling::div")
	public WebElement oneTimeTCV;

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'Total TCV')]//preceding-sibling::div")
	public WebElement totalTCV;

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'Total Costs')]//preceding-sibling::div")
	public WebElement totalCosts;

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'TCV Margin $')]//preceding-sibling::div")
	public WebElement tcvMarginValue;

	@FindBy(how = How.XPATH, using = "//div[@class='price-line-item']/div[contains(.,'TCV Margin %')]//preceding-sibling::div")
	public WebElement tcvMarginPercentage;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Checkout')]")
	public WebElement checkoutButton;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Continue')]")
	public WebElement continueButton;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Email failed')]")
	public WebElement emailErrorPopUp;

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public WebElement emailErrorIFrame;

}
