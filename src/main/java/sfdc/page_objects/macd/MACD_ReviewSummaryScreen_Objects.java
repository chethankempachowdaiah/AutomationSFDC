package sfdc.page_objects.macd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_ReviewSummaryScreen_Objects {

	public MACD_ReviewSummaryScreen_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Review your Rogers Wireless line']")
	public WebElement wirelessLineHeader;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Pending blocks on this line']")
	public WebElement pendingBlks;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@class,'user-name')]")
	public WebElement username;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'phone')]")
	public WebElement phone;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Block')]")
	public WebElement block;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Block Reason')]")
	public WebElement blockReason;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Block Date')]")
	public WebElement blockDate;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'nds-button--brand')]")
	public WebElement proceed;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'topic')]")
	public WebElement title;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'text-edit')]")
	public WebElement clickEdit;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'detail')]")
	public WebElement detail;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'t4')]")
	public WebElement chooseAnotherNo;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'t5')]")
	public WebElement goToMngAcc;
}
