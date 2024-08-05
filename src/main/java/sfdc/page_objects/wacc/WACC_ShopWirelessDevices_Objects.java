package sfdc.page_objects.wacc;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_ShopWirelessDevices_Objects {

	public WACC_ShopWirelessDevices_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[.='Bring your own device']")
	public WebElement byodLabel;

	@FindBy(how = How.XPATH, using = "//button[text()='Bring my device']")
	public WebElement byodBtn;
	
	

	@FindBy(how = How.XPATH, using = "//button[text()='Add device protection & accessories']")
	public WebElement devProtAccBtn;

	@FindBy(how = How.XPATH, using = "//div//p[.='Bring your own device for your new price plan']")
	public WebElement labelBYODPage;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'catalog-brands')]")
	public List<WebElement> listBrands;

	@FindBy(how = How.XPATH, using = "(.//button[.='View Details'])[1]")
	public WebElement viewDetailsbtn;

	@FindBy(how = How.XPATH, using = ".//button[contains(@class,'addCart')]")
	public WebElement addToCartBtnDevDetPge;

	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Back to browse devices']")
	public WebElement backToBrowDevBtn;

	@FindBy(how = How.CLASS_NAME, using = "prod-desc")
	public WebElement expandDetails;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Shop') and contains(normalize-space(),'Products')]"),
			@FindBy(how = How.XPATH, using = "//button[@class='shop-button']") })
	public WebElement shopProductsButton;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'catalog_font')]//span[.='Smartphones']")
	public WebElement smartphonestab;

	@FindBy(how = How.XPATH, using = ".//label[normalize-space()='Pay with financing']")
	public WebElement payWithFinancingLbl;

	@FindBy(how = How.XPATH, using = "(.//div[@class='device-attr'])[1]")
	public WebElement getWirelessDeviceAdded;

	// New Locators for Wireless Devices
	@FindBy(how = How.XPATH, using = ".//button[@value='WRLS_DEVICE_CATLOG']")
	public WebElement shopWirelessDeviceBtn;

	@FindBy(how = How.XPATH, using = ".//div[@data-id='Apple']")
	public WebElement SelectAppleDevice;

	@FindBy(how = How.XPATH, using = ".//span[contains(@class,'backButtonLabel')]")
	public WebElement reachedDeviceDetailPage;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'offerDiv')]//span['priceDetails']")
	public WebElement verifyOfferfromDeviceDetail;

	@FindBy(how = How.XPATH, using = ".//div[@class='arrow-left-device']/..//div[@class='financeBtnPrice']")
	public WebElement financedPrice;

	@FindBy(how = How.XPATH, using = ".//div[@class='colour-holder']//div[contains(@class,'deSelected-color')]")
	public WebElement selectDifferentColor;

	@FindBy(how = How.XPATH, using = ".//div[@class='capacityHolder']//div[contains(@class,' catalog-brands_deselected')]")
	public WebElement selectDifferentCapacity;

	@FindBy(how = How.XPATH, using = ".//lightning-button/button[contains(@class,'button_neutral') and text()='Continue']")
	public WebElement lightngContinueButtn;

	@FindBy(how = How.XPATH, using = ".//button[contains(@class,'forceActionButton')]/span")
	public WebElement okLigtngBtn;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'product-tile')]/div[contains(@class,'device-name')]")
	public List<WebElement> selectDeviceName;

	@FindBy(how = How.XPATH, using = "/..//div[contains(@class,'amount')]/span")
	public By fullPrice;

	@FindBy(how = How.XPATH, using = ".//span[contains(@class,'availability-label')]")
	public List<WebElement> deviceAvailability;

	@FindBy(how = How.XPATH, using = ".//span[contains(@class,'dynamicValues')]")
	public List<WebElement> checkDeviceAttriLabels;

	@FindBy(how = How.XPATH, using = "(.//div[@class='device-attr'])[1]")
	public WebElement getDeviceDetails;

	@FindBy(how = How.XPATH, using = ".//button[contains(@class,'addCart')]/span")
	public WebElement AddToCart;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'device-name')][contains(text(),'Smartphones (')]")
	public WebElement countNoSmartphones;

	@FindBy(how = How.XPATH, using = ".//div[@class='device-attr']")
	public List<WebElement> getOrderSeqList;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'addon-text')]//span/..")
	public WebElement getAccordionOrderAddon;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'accessory')]")
	public WebElement getAccordionOrderAcc;

	@FindBy(how = How.XPATH, using = ".//div[@class='device-model']")
	public WebElement deviceModel;

	@FindBy(how = How.XPATH, using = ".//div[@class='colour-label']/span")
	public WebElement colourSelected;

	@FindBy(how = How.XPATH, using = ".//div[@class='capacity-label']/span")
	public WebElement sizeSelected;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'offerDiv')]//span['priceDetails']")
	public List<WebElement> verifyOfferfrmDevDetlList;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'product-tile-box')]")
	public List<WebElement> getDeviceCount;

	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Filters']/div")
	public WebElement btnFilters;

	@FindBy(how = How.XPATH, using = "//div/div[.='Operating System']")
	public WebElement lblOperatingSys;

	@FindBy(how = How.XPATH, using = "//label[.='iOS']/./span[contains(@class,'checkbox')]")
	public WebElement chkboxIos;

	@FindBy(how = How.XPATH, using = "//label[.='Android']/./span[contains(@class,'checkbox')]")
	public WebElement chkboxAndriod;

	@FindBy(how = How.XPATH, using = "//label[.='Other']/./span[contains(@class,'checkbox')]")
	public WebElement chkboxOthers;

	@FindBy(how = How.NAME, using = "viewBtn")
	public WebElement btnView;

	@FindBy(how = How.NAME, using = "//button[contains(@class,'reset-filter')]")
	public WebElement btnResetFilter;

	@FindBy(how = How.XPATH, using = ".//button[@value='cancel']/span")
	public WebElement skpWithoutAdingPrtcn;

	@FindBy(how = How.XPATH, using = ".//div[@class='arrow-left-device']/..//div[contains(@class,'financeBtnLabel')]")
	public WebElement financeTenure;

	@FindBy(how = How.XPATH, using = ".//label[contains(@for,'Full')]/span[contains(@class,'radio_faux')]")
	public WebElement deviceFullPriceCheckbox;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'selected')]//label[contains(@class,'financeBtnLabel')]")
	public WebElement FullPricePaySelected;

	@FindBy(how = How.XPATH, using = ".//div[@class='price']")
	public WebElement fullPriceOfDevce;

	@FindBy(how = How.XPATH, using = ".//div[@data-id='2 Year' and contains(@class,'deselected')]")
	public WebElement select2YrFinceOfDvce;

	@FindBy(how = How.XPATH, using = ".//div[@data-id='3 Year' and contains(@class,'deselected')]")
	public WebElement select3YrFinceOfDvce;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'amount')]/span")
	public List<WebElement> FullPriceDevceListPge;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'amount')]")
	public List<WebElement> FincePriceDevceListPge;

	@FindBy(how = How.XPATH, using = ".//button[.='View Details']")
	public List<WebElement> viewDetailsBtns;

	@FindBy(how = How.XPATH, using = "(.//div[contains(@class,'selected-addonname')]/..//div[@class='selected-addonlinepriceline'])[1]")
	public WebElement systemSetpOrgnlFee;

	@FindBy(how = How.XPATH, using = "(.//div[contains(@class,'selected-addonname')]/../..//div[contains(@class,'selected-cutoffprice')])[1]")
	public WebElement systemSetpDiscntdFee;

	@FindBy(how = How.XPATH, using = "//*[@class='financePricevalue']//div[@class='price']")
	public WebElement FincePriceDeviceDetwithoutDecimal;
	
	@FindBy(how = How.XPATH, using = "//*[@class='financePricevalue']//div[contains(@class,'price-secondary ')]")
	public WebElement FincePriceDeviceDetwithDecimal;

	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'product-tile')]//button[contains(text(),'View Details')]")
	public List<WebElement> deviceList;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'capacity-label')]//span")
	public WebElement capacityOnDeviceDetails;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(), 'Full price : ')]")
	public List<WebElement> devicePriceLstOnDeviceList;
	

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'backButtonLabel')]")
	public WebElement clickBackToBrowseDevices;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'fullPriced')]//div[@class='price']")
	public WebElement deviceFullPricewithoutDecimal;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'fullPriced')]//div[contains(@class,'price-secondary ')])[1]")
	public WebElement deviceFullPriceonlyDecimal;
	
	@FindBy(how = How.XPATH, using = "//div[@class='slds-grid oneTimeFee']//div[@class='nds-grid']//div[@class='price']")
	public WebElement acceFullPricewithoutDecimal;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='slds-grid oneTimeFee']//div[@class='nds-grid'])[2]//div[2]")
	public WebElement acceFullPriceonlyDecimal;
	
	
	//Web elements for footer section price values
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'desktop-element')]//div[.='Recurring TCV' or .='Valeur totale des coûts récurrents']/following-sibling::div")
	public WebElement RecurringTCV;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'desktop-element')]//div[.='One Time TCV' or .='Valeur totale unique des coûts']/following-sibling::div")
	public WebElement oneTimeTCV;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'desktop-element')]//div[.='Total TCV' or .='Valeur totale des coûts']/following-sibling::div")
	public WebElement totalTCV;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'desktop-element')]//div[.='Margin TCV' or .='Marge de la valeur totale des coûts']/following-sibling::div")
	public WebElement marginTCV;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'desktop-element')]//div[.='Monthly Recurring Charge' or .='Total mensuel']/following-sibling::div")
	public WebElement monthlyRecurringCharge;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'desktop-element')]//div[.='Total Costs' or .='Coûts totaux']/following-sibling::div")
	public WebElement totalCosts;
	
	//web element for finance monthly tab
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'catalog_font_selected')]//div[contains(@class,'financeBtnLabel')]")
	public WebElement financeMonthstab;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'slds-grid_vertical')]//div[contains(@class,'promo')]/span)[1]")
	public WebElement appleCarePrice;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'slds-grid_vertical')]//div[contains(@class,'promo')]/span)[2]")
	public WebElement deviceProtectionPrice;

	@FindBy(how = How.XPATH, using = "//p[@class='help-text_width_adjust']")
	public WebElement helpToolTipText;
	
	@FindBy(how = How.XPATH, using = "//span[@class='help-text_heading']//*[contains(@data-id,'RGR_WRLS')]")
	public List<WebElement> helpToolTipIcon;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Taxes extra')]")
	public List<WebElement> financeTextList;
	
	@FindBy(how = How.XPATH, using = "//div[@class='payment-label']/span")
	public WebElement PaymentOptionTextDeviceDet;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'financeBtnLabel')]")
	public List<WebElement> financeOption;
	
	@FindBy(how = How.XPATH, using = "//span[@class='help-text_heading']//*[contains(@class,'left_x-small')]")
	public WebElement helpIconDeviceDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@class='defaultDevice']/img")
	public List<WebElement> deviceImageList;
	
	@FindBy(how = How.XPATH, using = ".//div[contains(@class,'small catalog-brands')]")
	public List<WebElement> deviceCapacityList;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Bring my device']")
	public WebElement bringMyDeviceBtn;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Confirm updating cart']")
	public WebElement confirmUpdatingCartBtn;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Cancel and go back']")
	public WebElement cancelAndGoBackBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@class='payment-label']//span[contains(text(),'(financing)')]")
	public WebElement noOfMonthsFinancingText;
	
	@FindBy(how = How.XPATH, using = "//div[@data-id='3 Year']//div[@class='nds-grid nds-wrap']")
	public WebElement thirtySixmonthsFinanceBox;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'monthlyFee')]//div[@class='feehint']")
	public WebElement totalMonthlydetails;
}
