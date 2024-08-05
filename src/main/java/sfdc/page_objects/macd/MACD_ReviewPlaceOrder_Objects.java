package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_ReviewPlaceOrder_Objects  {

		public MACD_ReviewPlaceOrder_Objects(WebDriver driver) {
			// TODO Auto-generated constructor stub
			PageFactory.initElements(driver, this);
		}

		@FindBy(how = How.XPATH, using = "//div[contains(text(),'One-time fees')]//following-sibling::div/div")
		public List<WebElement> appleCareProdDtlsInOneTimeSec;
		
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'One-time fees')]//following-sibling::div[contains(text(),'Tax will be applied later')]/preceding::div[text()='One-time fees subtotal']")
		public WebElement oneTimeFeesSubTotalText;
		
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'One-time fees')]//following-sibling::div[contains(text(),'Tax will be applied later')]//following-sibling::div")
		public WebElement oneTimeFeesSubTotalPrice;
		
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Changes in monthly fee')]//following-sibling::div[contains(text(),'Tax will be applied later')]//following-sibling::div")
		public WebElement monthlyFeesChang;
		
		@FindBy(how = How.XPATH, using = "//span[text()='Total one-time fees']//following-sibling::span")
		public WebElement totalOneTimeFeesSubText;
		
		@FindBy(how = How.XPATH, using = "//span[text()='Total one-time fees']/../following::div[@class='price']")
		public WebElement totalOneTimeFees;
		
		@FindBy(how = How.XPATH, using = "//span[text()='New Total monthly fees']/../following-sibling::div/div//div[@class='price']")
		public WebElement newTotalMonthlyFee;
		
		@FindBy(how = How.XPATH, using = "//span[text()='New Total monthly fees']/../following-sibling::div/div//div[@class='price']/following-sibling::div/div/div[2]")
		public WebElement newTotalMonthlySubFee;
		
		@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Current Monthly fees subtotal')]/../following-sibling::div/div)[3]")
		public WebElement currentMonthlyFeesSubTotal;
		
// 		@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Current Monthly fees subtotal')]/../following-sibling::div/div)[3]")
// 		public WebElement termsAndConditionLink;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'You’ve recently made changes to your wireless service(s).')]/following-sibling::p")
		public WebElement recentlyMadeWirelessServiceText;	

		@FindBy(how = How.XPATH, using = "//div[text()='Wireless']/following-sibling::div[2]//a")
		public WebElement termsAndConditionLink;	
	}

