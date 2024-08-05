package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_BlockService_Object {

	public MACD_BlockService_Object(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Block Telephone Number']")
	public WebElement blockctn;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'nds-checkbox_faux')]")
	public WebElement checkbox;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'slds-size_5-of-12')]")
	public WebElement description;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Continue')]")
	public WebElement continueBtn;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Step 2: Select a block reason']")
	public WebElement selectBlkReason;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Choose a reason for blocking the service on wireless plan.']")
	public WebElement selectBlkReasonDes;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Confirm block date>']")
	public WebElement confirmBlockDate;
	
	@FindBy(how = How.XPATH, using = "//input[@type='text']")
	public WebElement dateInput;
	
	@FindBy(how = How.XPATH, using = "//h2[@data-id='selected_month']")
	public WebElement calendarMonth;
	
	@FindBy(how = How.XPATH, using = "//td[contains(@data-id,'dayId')]")
	public List<WebElement> calendarDaysList;
	
	@FindBy(how = How.XPATH, using = "//*[@class='nextMonth']/*[local-name()='svg']")
	public WebElement calendarNextMonthIcon;
	
}
