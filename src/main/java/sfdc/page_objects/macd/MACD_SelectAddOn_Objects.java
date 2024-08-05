package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_SelectAddOn_Objects {
	public MACD_SelectAddOn_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//button[@data-id='chooseDevice']")
	public WebElement continueToDateSel;
	
	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Skip')]")
	public WebElement skipAddon;

	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Edit']")
	public WebElement editButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'addIconDiv')]")
	public WebElement addIcon;
	
	@FindBy(how = How.XPATH, using = "//div[@class='confirmBtn']")
	public WebElement confirmEffectiveDate;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'radiotext')]")
	public List<WebElement> effectiveDateList;
	
	@FindBy(how = How.XPATH, using = "//input[@type='text']")
	public WebElement dateInput;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'small radio-options_text')]/preceding-sibling::span/following-sibling::span")
	public List<WebElement> addonList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'bottom_small radio-options_text')]")
	public List<WebElement> voiceMailAddonList;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[text()='Step 1 of 2']/following-sibling::div//div[contains(text(),'Wireless Add-ons')]/following-sibling::div/div[contains(@class,'p-top_medium text-font')]"),
			@FindBy(how = How.XPATH, using = "(//div[text()='Step 1 of 2']/following-sibling::div//div[@class='nds-grid grid1 text-Addons_large2'])[2]")})
	public WebElement addedExtraAddon; 
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@class='summary-section']//div[@class='custom-box']/div"),
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'small radio-options_text')]/ancestor::div[contains(@class,'voice-box slds-p-around_large')]")})
	public WebElement addOnBox;
		
	@FindBy(how = How.XPATH, using = "//span[text()='No voicemail add-on']")
	public WebElement noVoiceMailAddOnText; 
	
	@FindBy(how = How.XPATH, using = "//span[text()='No voicemail add-on']/preceding-sibling::span")
	public WebElement noVoiceMailAddOnRadioButton; 
	
	@FindBy(how = How.XPATH, using = "//div/p[text()='I do not wish to add any voicemail add-ons']")
	public WebElement noVoiceMailAddOnSubText; 
	
	@FindBy(how = How.XPATH, using = "//button[text()='Update add-ons']")
	public WebElement updateAddOnButton; 
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Conflicting add-ons')])[1]")
	public WebElement conflictAddOnHeaderMessage; 
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),' The device is already protected by AppleCare+ and cannot be removed. Device Protection and AppleCare+ cannot both be applied to the same device.')]/parent::div/span[contains(text(),'Device protection')]")
	public WebElement deviceProtectionNotMsg; 
	
	@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Your new add-on(s) conflict with one or more of your existing add-ons. If you continue, your current, conflicting add-ons will be removed and replaced with your new add-ons')])[1]")
	public WebElement conflictAddOnMessage; 
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Replace existing add-on')])[1]")
	public WebElement replaceExistingAddOnButton; 
	
	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Cancel')])[1]")
	public WebElement replaceExistingAddOnCancelButton; 
	
	@FindBy(how = How.XPATH, using = "(//div[@class='editbox2']/following-sibling::div//div[contains(@class,'text-Addons_large2')])[2]")
	public WebElement addedExtraDP;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='editbox2step']/following-sibling::div//div[contains(@class,'text-Addons_large2')]/../following-sibling::div/div[@class='amt'])[2]")
	public WebElement addedDPPrice;
	
	@FindBy(how = How.XPATH, using ="(//div[@class='editbox2step']/following-sibling::div//div[contains(@class,'text-Addons_large2')]/../following-sibling::div//div[@class='uom'])[2]")
	public WebElement addedDPFeeType;

	@FindBy(how = How.XPATH, using = "//td[contains(@data-id,'dayId')]")
	public List<WebElement> calendarDaysList;
	
	@FindBy(how = How.XPATH, using = "//h2[@data-id='selected_month']")
	public WebElement calendarMonth;
	
	@FindBy(how = How.XPATH, using = "//*[@class='nextMonth']/*[local-name()='svg']")
	public WebElement calendarNextMonthIcon;
	
	@FindBy(how = How.XPATH, using = "//span[@class='ACt1']")
	public WebElement addAppleCareHeader;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='ACt2'])[1]")
	public WebElement addAppleCareText1;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='ACt2'])[2]")
	public WebElement addAppleCareText2;
	
	@FindBy(how = How.XPATH, using = "(//div[@class='ACt2'])[2]")
	public WebElement addAppleCareBlock;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'vertical_small text-heading_large')]")
	public List<WebElement> addOnHeadingList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'addon-item-text_large')]")
	public List<WebElement> addOnList;
	
	@FindBy(how = How.XPATH, using = "//*[@name='voicemail']/following-sibling::label//span[contains(@class,'radio-options_text')]")
	public List<WebElement> voiceMailAddOnList;
	
	@FindBy(how = How.XPATH, using = "//*[@name='voicemail']/following-sibling::label//span[contains(@class,'radio_faux')]")
	public List<WebElement> voiceMailAddOnRadioBtnList;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'No Offer')]//preceding-sibling::span[@class='nds-radio_faux']")
	public List<WebElement> noOfferRadio;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'No Offer')]//preceding-sibling::span[@class='nds-radio_faux']//following-sibling::span")
	public List<WebElement> promoRadio;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Skip')]")
	public WebElement skipAddOns;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Back to previous']")
	public WebElement backToPrevious;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue']")
	public WebElement continueBtn;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'info-block-container')]")
	public WebElement DPblockContent;

}
