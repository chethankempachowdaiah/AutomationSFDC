package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:03/08/2021
 *
 *         SFDC> PBF Cable Plan selection page objects
 */
public class SFDC_PBF_CablePlan_Selection_Objects {

	public SFDC_PBF_CablePlan_Selection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'3 Year Term') or contains(text(),'Abonnement de 3')]/preceding-sibling::span")
	public WebElement threeYearTermRadio;
	
	@FindBy(xpath = "//span[contains(text(),'Monthly') or contains(text(),'Abonnement mensuel')]/preceding-sibling::span")
	public WebElement monthlyTermRadio;
	
	@FindBy(xpath = "//span[contains(text(),'Monthly Term')]")
	public WebElement monthlyTermText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'5 Year Term')]/preceding-sibling::span")
	public WebElement fiveYearTermRadio;

	@FindBy(how = How.XPATH, using = "//button[.='Discounts and promos' or .='Rabais et promotions']")
	public List<WebElement> discountAndPromosButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Promotions and Discounts']")
	public List<WebElement> discountAndPromosButtonForRDI;
	
	@FindBy(how = How.XPATH, using = "//div[.='Special offers available:']/parent::div")
	public WebElement discountAndPromoSection;
	
	@FindBy(how = How.XPATH, using = "//div[.='Offers Available (select one):']/parent::div/parent::div")
	public WebElement discountAndPromoSectionForRDI;
	
	@FindBy(xpath = "//div[.='Special offers available:' or .='Offres spéciales proposées']/parent::div//span[contains(@class,'radio-text')]")
	public List<WebElement> discountAndPromosList;
	
	@FindBy(xpath = "//div[.='Special offers available:' or .='Offres spéciales proposées']/parent::div//span[contains(@class,'radio-text')]")
	public List<WebElement> productItemListName;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'radio-label')]")
	public List<WebElement> subPlans;
	
	@FindAll({
	@FindBy(xpath = "//div[contains(@class,'footer')]//div[.='Monthly Total']/following-sibling::div"),
	@FindBy(xpath = "//div[contains(@class,'footer')]//div[.='Monthly Recurring Charge' or .='Total mensuel']/following-sibling::div")})
	public WebElement monthlyTotal;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='Monthly Recurring Charge' or .='Total mensuel']/following-sibling::div")
	public List<WebElement> monthlyTotalMultisite;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='Recurring TCV' or .='Valeur totale des coûts récurrents']/following-sibling::div")
	public WebElement recurringTCV;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='One Time TCV' or .='Valeur totale unique des coûts']/following-sibling::div")
	public WebElement oneTimeTCV;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='One Time TCV' or .='Valeur totale unique des coûts']/following-sibling::div")
	public List<WebElement> multisite_OneTimeTCV;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='Total TCV' or .='Valeur totale des coûts']/following-sibling::div")
	public WebElement totalTCV;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='Total Costs' or .='Coûts totaux']/following-sibling::div")
	public WebElement totalCosts;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'footer')]//div[.='Margin TCV' or .='Marge de la valeur totale des coûts']/following-sibling::div")
	public WebElement tcvMarginValue;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'catalog-name')]")
	public List<WebElement> rdiPlansTitleHeader;
	
	@FindBy(how = How.XPATH, using = "//button[.='View more details']")
	public List<WebElement> rdiPlansViewDetailsButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Product details']/following-sibling::ul/li")
	public List<WebElement> rdiPlansProductDetails;
	
	@FindBy(how = How.XPATH, using = "//div[.='Fibre Access Add-Ons']")
	public WebElement fibreAccessPageHeader;
	
	@FindBy(how = How.XPATH, using = "//span[.='Ethernet Fibre Network Access E1000']/label/span")
	public WebElement ethernetE1000Option;
	
	@FindBy(how = How.XPATH, using = "//span[.='Ethernet Fibre Network Access E100']/label/span")
	public WebElement ethernetE100Option;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product-title')]/div")
	public WebElement ethernetE1000Price;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'product-title')]/div)[2]")
	public WebElement ethernetE100Price;
	
	@FindBy(how = How.XPATH, using = "//button[@value='updateCart']")
	public WebElement ethernetPlanUpdateCartButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Cancel']")
	public WebElement ethernetAccessPageCancelButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Next']")
	public WebElement nextButton;
	
	@FindBy(how = How.XPATH, using = "//button[@value='next']")
	public WebElement nextButtonForInternet;
	
	@FindBy(how = How.XPATH, using = "//*[@data-omni-key='DedicatedInternetCatalog']//button[.='Next']")
	public WebElement nextButtonForEthernet;
	
	@FindBy(xpath = "//button[@name='increment']")
	public WebElement increment;
	
	@FindBy(xpath = "//button[@name='decrement']")
	public WebElement decrement;	
	
	@FindBy(xpath = "//input[@name='numericInput']")
	public WebElement discountValue;
	
	@FindBy(xpath = "//button[contains(text(),'Reset offers')]")
	public WebElement resetOffers;
	
	@FindBy(xpath = "//div[contains(text(),'Offers Available')]")
	public WebElement offersAvailableSection;
	
	@FindBy(xpath = "//div[contains(@class,'offer-price-card')]//div[@class='price']")
	public List<WebElement> price;
	
	@FindBy(xpath = "//div[@class='price']//following-sibling::div/div/div[2]")
	public List<WebElement> decimalPrice;
	
	@FindBy(xpath = "//div[contains(@class,'offer-price-card')]/div/div[2]/div")
	public WebElement discountSectionInPrice;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Sorry to interrupt')]")
	public List<WebElement> popuppresencemsg;

	@FindBy(how = How.XPATH, using = "//button[@title='OK']")
	public WebElement popupokbtn;

	
	
	
	
}
