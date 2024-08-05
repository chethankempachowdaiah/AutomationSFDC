package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_ChangeWirelessPlans_Objects {
	public MACD_ChangeWirelessPlans_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@data-label='Voice and Data']")
	public WebElement voiceAndData;
	
	@FindBy(how = How.XPATH, using = "//a[@data-label='Data Only']")
	public WebElement dataOnly;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'current plan')]")
	public WebElement currentPlanText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'offer-bucketsize')]")
	public List<WebElement> planSize;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Pooled']")
	public List<WebElement> pooledRadio;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Standalone']")
	public List<WebElement> standaloneRadio;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Select plan']")
	public List<WebElement> selectPlan;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Plan selected']")
	public List<WebElement> planSelectedText;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'date selection')]")
	public WebElement contToDateSelection;
	
	@FindBy(how = How.XPATH, using = "//div[@class='pricebox']//div[contains(@class,'nds-p-top_medium text-font')]")
	public WebElement newPlanFeature;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'nds-p-bottom_small')]//input[@type='radio']")
	public List<WebElement> dateradioBtnList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'nds-p-bottom_small')]//span[contains(@class,'label radiotext')]")
	public List<WebElement> dateRadioTextList;
	
	@FindBy(how = How.XPATH, using = "//span[@class='cartMessage']")
	public WebElement successCartMsg;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'conflicting discount')]")
	public WebElement conflictDiscountHeader;
	
	@FindBy(how = How.XPATH, using = "//div[@class='pill-header-container']")
	public WebElement conflictDiscountcontainer;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Remove discount')]//parent::button[@value='Next']")
	public WebElement removeDiscountAndProceed;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Cancel Wireless plan')]//parent::button[@value='Previous']")
	public WebElement cancelWirelessPlanChange;
}
