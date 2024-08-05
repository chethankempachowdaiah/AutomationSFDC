package sfdc.page_objects.wacc;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_ShoppingCart_Objects {

	public WACC_ShoppingCart_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='nds-p-around--medium text-heading_large header-text'][contains(normalize-space(),'My Cart') or contains(normalize-space(),'Mon panier')]")
	public WebElement myCartHeader;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Proceed to checkout' or normalize-space()='Passer à la caisse']")
	public WebElement proceedToCheckoutBtn;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'nds-grid') and (contains(normalize-space(),'GB') or contains(normalize-space(),'Go'))])[3]")
	public WebElement productName;

	@FindAll({@FindBy(how = How.XPATH, using = "(//button[normalize-space()='Remove']/parent::div[contains(normalize-space(),'/mo')])[1]"),
		@FindBy(how = How.XPATH, using = "(//button[normalize-space()='Retirer']/parent::div[contains(normalize-space(),'/mois')])[1]")})
	public WebElement productUnitPrice;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Business Plan') or contains(text(),'Forfait sans-fil')]/../../following-sibling::div[3]//*[contains(@class,'selected')]")
	public WebElement productLineTotal;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'nds-grid') and (contains(normalize-space(),'GB') or contains(normalize-space(),'Go'))])[3]/parent::div/following-sibling::div[2]")
	public WebElement productQuantity;

	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid'][contains(normalize-space(),'with') or contains(normalize-space(),'avec')]")
	public WebElement addOnName;
	
	//@FindBy(how = How.XPATH, using = "(//div[contains(@class,'addonlineprice')][contains(normalize-space(),'/mo')])[1]")
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'bottom_x-small')]//div[contains(@class,'addonlineprice')])[1]")
	public WebElement addOnUnitPrice;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid'][contains(normalize-space(),'with') or contains(normalize-space(),'avec')]/parent::div/following-sibling::div[2]")
	public WebElement addOnQuantity;

//	@FindBy(how = How.XPATH, using = "//div[contains(@class,\"nds-float--right nds-grid\") and contains(normalize-space(),'/mo')]")
//	public WebElement monthlyPriceTotal;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Total monthly fees' or normalize-space()='Total des frais mensuels']/../..//div[@c-pbfospricedisplay_pbfospricedisplay and @class='price']")
	public WebElement monthlyPriceTotal;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'heading')][contains(normalize-space(),'Review') or contains(normalize-space(),'Vérifiez')]")
	public WebElement placeOrderHeader;

//	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='One Time TCV'])[1]")
//	public WebElement OneTimeprice;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Total one-time fees' or normalize-space()='Total des frais uniques']/../..//div[@c-pbfospricedisplay_pbfospricedisplay and @class='price']")
	public WebElement OneTimeprice;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Recurring TCV' or normalize-space()='Valeur totale des coûts récurrents'])[1]")
	public WebElement footerRecurring;

	@FindAll({@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Total TCV'])[1]"),
		@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Valeur totale des coûts'])[1]")})
	public WebElement totalTCV;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Total Costs' or normalize-space()='Coûts totaux'])[1]")
	public WebElement totalCosts;

	@FindBy(how = How.XPATH, using = "(//div[normalize-space()='Recurring TCV' or normalize-space()='Valeur totale des coûts récurrents']/following-sibling::div[contains(text(),'$')])[1]")
	public WebElement footerRecurringTcvPrice;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'-regular testone')]/a")
	public WebElement seeHideDetailsLink;
	
	@FindBy(how = How.XPATH, using = "//div//span[contains(normalize-space(),'Plan type :') or contains(normalize-space(),'Type de forfait :')]")
	public WebElement planType;

	@FindBy(how = How.CLASS_NAME, using = "prod-desc")
	public WebElement expandDetails;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Shop') and contains(normalize-space(),'Products')]"),
			@FindBy(how = How.XPATH, using = "//button[@class='shop-button']") })
	public WebElement shopProductsButton;

	@FindAll({@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Shop')][contains(normalize-space(),'Wireless')][contains(normalize-space(),'Plans')]"),
		@FindBy(how = How.XPATH, using = "//button[normalize-space()='Magasiner les forfaits sans-fil']")})
	public WebElement shopWirelessPlansButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-heading_large')][contains(normalize-space(),'tax')][contains(normalize-space(),'credit') or contains(normalize-space(),'crédit')]")
	public WebElement postTaxCreditreditHeader;

	@FindBy(how = How.XPATH, using = "//button[@class=\"nds-button nds-button_neutral\"]")
	public WebElement applyOfferButton;

	@FindBy(how = How.XPATH, using = "//span[@class='nds-radio']//input[@type='radio']/following::label")
	public List<WebElement> postTaxCreditOfferOptions;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'price-header')][normalize-space()='Total Costs' or normalize-space()='Coûts totaux'])[1]/following-sibling::div")
	public WebElement totalCostsValue;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-heading_large')][contains(normalize-space(),'Select post tax credit: ') or contains(normalize-space(),'Sélectionnez le crédit après les taxes:')]")
	public WebElement selectedCredit;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'dropdown')]//input[@placeholder='Select an Option']")
	public WebElement updateQuantityDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'icon_right')]//button[@data-value='1']")
	public WebElement updateQuantityDropdown1;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'dropdown-element')]/lightning-base-combobox-item[@data-value]")
	public List<WebElement> updateQuantityDropdownOptions1;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Select an Option']/parent::div/following-sibling::div//lightning-base-combobox-item[@role='option']")
	public List<WebElement> updateQuantityDropdownOptions;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'selected-addonqty')])[1]")
	public WebElement updatedAddOnQuantity;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'total-footer')])[1]//button[normalize-space()='Proceed to shopping cart' or normalize-space()='Aller au panier d’achats']")
	public WebElement proceedToShopCartButton;
	
	@FindBy(how = How.XPATH, using = "(.//div[contains(@class,'testone')]/a[contains(@data-my-id,'')]/../../div[1])[2] | //c-pbf-os-quote-summary-one-time-items[2]/div[contains(@class,'add-on-item')]/div[contains(@class,'addonname')]")
	public WebElement fetchDeviceNameforPlanFlow;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'testone')]/a[contains(@data-my-id,'')]/../../span[@c-pbfosdevicesummary_pbfosdevicesummary]")
	public WebElement fetchDeviceInstlmntFrame;
	
	@FindBy(how = How.XPATH, using = "(.//div[contains(@class,'selected-addOnListlineprice') or contains(@class,'selected-cutoffprice')])[1]")
	public WebElement fetchDevicefinancePrceCart;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'testone')]/a[contains(@data-my-id,'') and @c-pbfosdevicesummary_pbfosdevicesummary]")
	public WebElement hideDetailsDevice;
	
	@FindBy(how = How.XPATH, using = "(.//c-pbf-os-quote-summary-one-time-items[2]/div[contains(@class,'add-on-item')]//div[contains(@class,'selected-addonlineprice')])[1]")
	public WebElement fullDevicePriceCart;
	
	@FindBy(how = How.XPATH, using = "(.//div[contains(@class,'selected-addOnListlineprice') or contains(@class,'selected-cutoffprice')])[1]/..//div[contains(@class,'selected-addonlinepriceline')]")
	public WebElement StrikeOutPriceCart;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Shopping cart' or text()='Panier d’achats']")
	public WebElement shoppingCartHeader;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'listbox__option_plain')]")
	public List<WebElement> accessoriesQuantityList;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'selected-addonname')]")
	public List<WebElement> itemNameList;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'selected-addonname')]/following-sibling::div/div[contains(@class,'selected-addonlineprice')]")
	public List<WebElement> priceList;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'text-body--regular')]//button[contains(text(),'Remove') or contains(text(),'Retirer')])[1]")
	public WebElement removeCombo;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'By continuing,')]")
	public WebElement alertText;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Yes')]")
	public WebElement alertOK;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Cancel')]")
	public WebElement alertCancel;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'empty-cart-message')]")
	public WebElement emptyCartText;
	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'One-time fees') or contains(text(),'Des frais uniques')])[1]/following-sibling::*//*[contains(@class,'selected-addonname')]")
	public WebElement accessoryInCart;
	
	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Monthly fees subtotal') or contains(text(),'Total partiel des frais mensuels')]/following-sibling::div[contains(@class,'text-body--regular')])[2]")
	public WebElement monthlyFeeSubTotal;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Flat discounting')]/../../following-sibling::div[1]/*[contains(@class,'redcolor')]")
	public List<WebElement> flatDiscountUnitPrice;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Flat discounting')]/../../following-sibling::div[3]//*[contains(@class,'redcolor')]")
	public List<WebElement> flatDiscountLineTotal;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Flat discounting applied')]")
	public List<WebElement> flatDiscountText;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Business Plan') or contains(text(),'Forfait sans-fil Affaires')]/../../following-sibling::div[1]/*[contains(@class,'selected-addonlinepriceline')]")
	public List<WebElement> productUnitPriceLst;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Business Plan') or contains(text(),'Forfait sans-fil Affaires')]/../../following-sibling::div[3]/*[contains(@class,'selected-addonlinepriceline')]")
	public List<WebElement> productLineTotalLst;
	
	@FindBy(how = How.XPATH, using = "(//div[normalize-space()='Total TCV' or normalize-space()='Valeur totale des coûts']/following-sibling::div[contains(text(),'$')])[1]")
	public WebElement totalTcvPrice;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(normalize-space(),'Monthly Recurring') or contains(normalize-space(),'Total mensuel')]/following-sibling::div[contains(text(),'$')])[1]")
	public WebElement monthlyRecurringPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'-body--regular')][contains(text(),'with') or contains(text(),'avec')]")
	public List<WebElement> addOnLst;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'-body--regular')][contains(text(),'with') or contains(text(),'avec')]/../../following-sibling::div[3]")
	public List<WebElement> addOntotalPriceLst;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Business Plan') or contains(text(),'Forfait sans-fil Affaires')]/../../following-sibling::div[2]//span")
	public WebElement planQuantityInput;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'with 36 mo')]//ancestor::div[@class='nds-grid nds-size--1-of-1']//a[contains(text(),'Hide') or contains(text(),'Masquer')]")
	public WebElement device36FinHideDetails;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Flat discounting applied')]")
	public WebElement flatDiscount;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'selected-addonname')]")
	public List<WebElement> oneTimeFeeNameLst;	

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'slds-input-has-icon_right')])[2]")
	public WebElement updateQuantityDropdownAcce;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'slds-listbox slds-listbox_vertical')]//lightning-base-combobox-item[@data-value]")
	public List<WebElement> updateQuantityDropdownOptionsAcce;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Proceed to shopping cart' or normalize-space()='Aller au panier d’achats']")
	public WebElement proceedToShoppingCart;
	
	@FindBy(how = How.XPATH, using = "//*[@class='nds-text-body--regular']")
	public List<WebElement> monthlyFeeNameLst;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='System Setup Fee' or normalize-space()='Frais de configuration de système']")
	public WebElement systemSetUpFeeText;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='System Setup Fee' or normalize-space()='Frais de configuration de système']/following-sibling::div/*[contains(text(),'$')]")
	public List<WebElement> systemSetUpPriceList;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Setup fee waived')]")
	public WebElement setupFeeWaivedText;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Device Protection' or normalize-space()='Protection de l’appareil']/..//span")
	public WebElement dpDetails;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Apple Care']/../following-sibling::div//span")
	public WebElement appleCareDetails;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'testone')]/a[contains(@data-my-id,'') and @c-pbfosdevicesummary_pbfosdevicesummary]")
	public WebElement showMoreDetailsLink;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-text-align--right')][1]//div[contains(@class,'selected-cutoffprice')][1]")
	public WebElement discountedPrice;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-text-align--right')][1]//div[contains(@class,'selected-addonlinepriceline')][1]")
	public WebElement originalPrice;

}


