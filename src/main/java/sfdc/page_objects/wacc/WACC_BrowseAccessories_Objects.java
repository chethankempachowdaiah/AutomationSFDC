package sfdc.page_objects.wacc;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_BrowseAccessories_Objects {

	public WACC_BrowseAccessories_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(how = How.XPATH, using = "//img[@src='/resource/AllaccessoriesSelected']")
	public WebElement allAccessories;
		
	@FindBy(how = How.XPATH, using = "//*[@class='price']")
	public List<WebElement> accessoriesPrice;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'device-name ')]")
	public List<WebElement> accessoriesList;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'device-name ')]/following-sibling::button")
	public List<WebElement> viewDetails;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'device-name ')]/preceding-sibling::div[@class='text_font']")
	public List<WebElement> brandList;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Brands']/parent::div/following-sibling::div//span[contains(@class,'element__label')]")
	public List<WebElement> brandFilter;
	
	@FindBy(how = How.XPATH, using = "//div[@data-id='multi-section']//div[contains(@class,'label-style')]")
	public List<WebElement> deviceCompatibilityList;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Price range']/parent::div/following-sibling::div//span[contains(@class,'element__label')]")
	public List<WebElement> priceFilter;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Filter')]")
	public WebElement filter;
	
	@FindBy(how = How.XPATH, using = "//img[@data-id='close-panel']")
	public WebElement filterCloseIcon;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'filter-footer')]//button[contains(@class,'resetlabel')]")
	public WebElement resetfilterOnFilterBar;
	
	@FindBy(how= How.XPATH, using = "//div[text()='Offers']")
	public WebElement offers;
	
	@FindBy(how= How.XPATH, using = "//div[text()='Offers']/following-sibling::div//span")
	public WebElement offersExpandIcon;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Promos & discounts']")
	public WebElement PromosDiscounts;	
	
	@FindBy(how= How.XPATH, using = "//span[@class='slds-form-element__label']")
	public List<WebElement> filterList;
	
	@FindBy(how= How.XPATH, using = "//button[text()='View']")
	public WebElement viewBtn;
	
	@FindBy(how= How.XPATH, using = "//span[contains(@class,'pill__label text-adjustment')]")
	public List<WebElement> filterBubble;
	
	@FindBy(how= How.XPATH, using = "//span[contains(@class,'pill__label text-adjustment')]/following-sibling::button")
	public List<WebElement> filterBubbleCloseIcon;
	
	@FindBy(how= How.XPATH, using = "//button[contains(@class,'reset-filter-btn resetlabel')]")
	public WebElement resetFilter;
	
	@FindBy(how= How.XPATH, using = "//*[contains(@class,'text-heading_medium device-name')]")
	public WebElement accessoriesCount;
	
	@FindBy(how= How.XPATH, using = "//button[normalize-space()='Proceed to shopping cart' or normalize-space()='Aller au panier d’achats']")
	public WebElement proceedToShoppingCartBtn;	
	
	@FindBy(how= How.XPATH, using = "//div[text()='Device Compatibility']")
	public WebElement deviceCompatibility;
	
	@FindBy(how= How.XPATH, using = "(//div[contains(@class, 'col_bump-right')])[2]")
	public WebElement dCExpandIcon;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Special Offer']")
	public List<WebElement> specialOfferTextList;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class, 'form-element__control-animated-label')]")
	public WebElement sortBy;
	
	@FindBy(how = How.XPATH, using = "//*[@class='price']")
	public List<WebElement> priceListWIthoutDecimal;
	
	@FindBy(how = How.XPATH, using = "//*[@class='price-secondary']/following-sibling::div[contains(@class,'price-secondary')]")
	public List<WebElement> priceListWithDesimal;
	
	@FindBy(how = How.XPATH, using = "(//div[text()='Total TCV' or text()='Valeur totale des coûts']/following-sibling::div)[1]")
	public WebElement footerTotalTCVPrice;
	
	@FindBy(how = How.XPATH, using = "(//div[text()='Total Costs' or text()='Coûts totaux']/following-sibling::div)[1]")
	public WebElement footerTotalCostPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'vertical_medium')]//button[contains(@class,'resetlabel')]")
	public WebElement sidebarResetFilters;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-col label-style']")
	public List<WebElement> brandnames;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'offerTag')]//following-sibling::div[contains(@class,'discountApplied')]")
	public List<WebElement> AcceOrigPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'offerTag')]")
	public List<WebElement> specialoffer;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'catalog_font')]//span[contains(text(),'All accessories')]")
	public WebElement allAccessoriesTile;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'catalog_font')]//span[contains(text(),'Power and cables')]")
	public WebElement powerAndCablesTile;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'catalog_font')]//span[contains(text(),'Headset/Bluetooth')]")
	public WebElement headsetOrBluetoothTile;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'catalog_font')]//span[contains(text(),'screen protectors')]")
	public WebElement casesAndScreenProtectors;
	
	@FindBy(how = How.XPATH, using = "//input[@data-value='Featured']")
	public WebElement sortByFeatured;
	
	@FindBy(how = How.XPATH, using = "//input[@data-value='High']")
	public WebElement sortByhigh;
	
	@FindBy(how = How.XPATH, using = "//input[@data-value='Low']")
	public WebElement sortByLow;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'nds-icon-utility-down')]")
	public WebElement sortByDrpdwn;
	
	@FindBy(how = How.XPATH, using = "(//span[.='Price (High to Low)'])[1]/parent::div[@data-value='High']/parent::li[@class='nds-listbox__item']")
	public WebElement sortHightoLow;
	
	@FindBy(how = How.XPATH, using = "(//span[.='Price (Low to High)'])[1]/parent::div[@data-value='Low']/parent::li[@class='nds-listbox__item']")
	public WebElement sortLowtoHigh;
	
	@FindBy(how = How.XPATH, using = "//label[.='Sort by']")
	public WebElement sortbyLabel;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'protectors')]")
	public WebElement casesAndScreenProtText;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'cables')]")
	public WebElement powerAndCablesText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'label-style')]")
	public List<WebElement> deviceBrandListInDC;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'label-style')]/preceding-sibling::div")
	public List<WebElement> deviceBrandexpandIconInDC;
	
	@FindBy(how = How.XPATH, using = "(//div[text()='One Time TCV' or text()='Valeur totale unique des coûts']/following-sibling::div)[1]")
	public WebElement FooterOneTimeTCV;
	
}


