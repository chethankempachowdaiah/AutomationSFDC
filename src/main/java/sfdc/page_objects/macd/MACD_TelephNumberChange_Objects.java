package sfdc.page_objects.macd;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MACD_TelephNumberChange_Objects {
	public MACD_TelephNumberChange_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Telephone number change')]")
	public WebElement telePhNumChangeHeader;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Proceed next']")
	public WebElement proceedNext;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-dropdown-trigger_click')]//div[contains(@class,'nds-input-has-icon_right')]")
	public WebElement provinceDropdown;
	
	@FindBy(how = How.XPATH, using = "//label[text()='City']/parent::div[contains(@class,'nds-align-middle')]//preceding-sibling::input[contains(@class,'nds-input')]")
	public WebElement city;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Proceed next']/parent::button")
	public WebElement proceedNextSelectLoc;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Step 1: Location')]/following-sibling::div//div[text()='Edit']")
	public WebElement editStepOne;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Select phone number']/parent::button")
	public WebElement selectPhoneNumber;
}