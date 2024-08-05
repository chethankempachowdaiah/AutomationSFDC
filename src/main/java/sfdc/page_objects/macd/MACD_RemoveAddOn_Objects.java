package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_RemoveAddOn_Objects {
	public MACD_RemoveAddOn_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using = "(//div[normalize-space()='Remove Wireless add-ons'])[1]")
	public WebElement removeHeader;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'addon-item-text_large')]")
	public List<WebElement> removeAddonList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'addon-item-text_large')]/../preceding-sibling::span")
	public List<WebElement> removeAddonCheckBox;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'addon-item-price_text')]")
	public List<WebElement> removeAddonPrice;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Remove add-ons and Continue']")
	public WebElement removeAndContinueBtn;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Select effective removal date')]")
	public WebElement selectEffectiveDate;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Confirm effective date(s)'][@class='confirmBtn']")
	public WebElement confirmEffectiveDateButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(normalize-space(),'AppleCare')]")
	public WebElement appleCareHeader;
	
	@FindBy(how = How.XPATH, using = "//p[contains(normalize-space(),'AppleCare')]")
	public WebElement appleCareSubHeader;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'info-block-container')]")
	public WebElement appleCareBlock;
	
	@FindBy(how = How.XPATH, using = "//div[@class='summary-section']//div[@data-name='Device Protection']")
	public WebElement dpSection;
	
	@FindBy(how = How.XPATH, using = "//div[@class='font nds-p-bottom_large']")
	public WebElement warningMsgSubHeader;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'text-heading--small')]")
	public WebElement warningMessage;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'text-body--regular-bold')]")
	public WebElement warningMessageText;
	
	@FindBy(how = How.XPATH, using = "(//div/*[contains(@class,'text-body_regular')])[last()]")
	public WebElement warningMessageSubText;
	
	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Confirm & proceed')]")
	public WebElement confirmAndProceedBtn;
	
	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Cancel')]")
	public WebElement cancelBtn;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'existingaddontext')]")
	public WebElement reviewRemovableAddon;
	

	@FindBy(how = How.XPATH, using = "//div[@class='pricebox']//div[normalize-space()='Device Protection']")
	public WebElement reviewRemovableDP;

	@FindBy(how = How.XPATH, using = "//div[text()='Step 2 of 2']")
	public WebElement step2of2;
	
	@FindBy(how = How.XPATH, using = "//span[@class='textedit']")
	public WebElement edit;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'addon-item-text_large')]/../preceding-sibling::span")
	public WebElement AddonCheckBox;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'page-header') and contains(text(),'Review')]")
	public WebElement reviewOrderHeader;

}
