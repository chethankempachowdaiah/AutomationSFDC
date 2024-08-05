package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 15/oct/2020
 * 
 *         SFDC Quote Review Page ( Create Quote By GBJ> Add product> Checkout>
 *         Quote Review Page)
 *
 */
public class SFDC_QuoteReview_Objects {

	public SFDC_QuoteReview_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product')]//div[contains(@class,'product-detail')]//div[text()='Internet']/parent::div/following-sibling::div/div")
	public WebElement internetProductDetails;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product')]//div[contains(@class,'product-detail')]//div[contains(text(),'TV')]/parent::div/following-sibling::div/div")
	public WebElement tvProductDetails;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-heading') and contains(.,'Account Information')]")
	public WebElement accountInformationHeader;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-heading') and contains(.,'Account Information')]//ancestor::div[contains(@class, 'clickable-section header-section')]/following-sibling::div//div[contains(.,'Prepared for:')]")
	public WebElement preparedForAccountName;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Attention')]//following-sibling::div[@class='text-body_regular']/div[1]")
	public List<WebElement> contactNameText;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Attention')]//following-sibling::div[@class='text-body_regular']/div[2]")
	public List<WebElement> contactEmailIdText;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Service Address')]//following-sibling::div[@class='text-body_regular']/div[3]")
	public List<WebElement> contactZipCodeText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'add-on-header')]//following-sibling::div/div[1]")
	public List<WebElement> productsDetailsInOrder;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'add-on-header')]//following-sibling::div/div[1]/div/div/div[@class='nds-grid']/div")
	public List<WebElement> productsDetailInOrder;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'add-on-header') and contains(.,'Add-Ons')]//parent::div//parent::div//following-sibling::div/div[contains(@class,'add-on-item')]")
	public List<WebElement> microsoftCollaorationText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'add-on-header') and contains(.,'TV Add')]//parent::div//parent::div//following-sibling::div/div[contains(@class,'add-on-item')]")
	public List<WebElement> tvAddOnsText;

	@FindBy(how = How.XPATH, using = "//div[ contains(@class,'text-body_regular') and contains(.,'Installation')]//following-sibling::div")
	public List<WebElement> internetInstallationFeeText;

	@FindBy(how = How.XPATH, using = "//button[@name='addDiscount']")
	public WebElement addFreeInstallationButton;

	@FindBy(how = How.XPATH, using = "//button[@name='removeDiscount']//parent::div//preceding-sibling::div")
	public WebElement freeInstallationText;

	@FindBy(how = How.XPATH, using = "//button[@name='removeDiscount']//parent::div//preceding-sibling::div/parent::div//following-sibling::div/div")
	public WebElement freeInstallationPriceText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'header-section')]/div[contains(.,'One-time Fees Total')]//following-sibling::div")
	public WebElement oneTimeFeesTotal;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Dedicated Internet Service')][contains(@class,'text-body')]")
	public WebElement rDIDediactedInternetProductDetailsText;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Ethernet Fibre Network Access E')]")
	public WebElement rDIEthernetFibreProductDetailsText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'add-on-header')]//following::*[text()='Ethernet Fibre Network Access Installation']")
	public WebElement rDIOneTimeProductText;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'header-section')]//div[contains(.,'Monthly Fees Total')]//following::div[@class='price'])[1]")
	public WebElement monthlyRDIFeesTotal;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'nds-p-around_small pill-bar')]")
	public WebElement promoDiscountTextDisplay;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'header-section')]/div[contains(.,'One-time Fees Total')]//following::div[@class='price']")
	public WebElement oneTimeRDINetworkInstallFeesTotal;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'alert-title')]")
	public WebElement creditCheckRequiredText;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product-detail')]//following-sibling::div/div[text()='Business Phone Basic']/parent::div")
	public WebElement businessPhoneBasicProductDetails;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product-detail')]//following-sibling::div/div[text()='Business Phone Pro']/parent::div")
	public WebElement businessPhoneProProductDetails;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product-detail')]//following-sibling::div/div[text()='Business Phone Standard']/parent::div")
	public WebElement businessPhoneStandardProductDetails;

	@FindBy(how = How.XPATH, using = "//c-mp-os-add-on-summary/div")
	public List<WebElement> orderSummaryAddOnsSection;

}
