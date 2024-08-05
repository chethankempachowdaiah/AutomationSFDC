package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 * 
 *
 *         SFDC Complete Vlocity Order Page(Manual Queues> Service Delivery
 *         Queue>Pick Up>Complete Vlocity Order)
 */
public class SFDC_CompleteVlocityOrder_Objects {

	public SFDC_CompleteVlocityOrder_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Please verify Supersystem')]//strong")
	public WebElement superSystemCANText;

	@FindBy(how = How.XPATH, using = "//*[@id='OrderNumberStep1']//a")
	public WebElement orderNumber;

	@FindBy(how = How.XPATH, using = "//*[@id='OrderStatusStep1']//p/p")
	public WebElement orderStatus;

	@FindBy(how = How.XPATH, using = "//*[@id='BusAccountName1']//p/p")
	public WebElement businessAccountName;

	@FindBy(how = How.XPATH, using = "//p/p[contains(.,'SGI Dealer Code :')]")
	public WebElement sgiDealerCode;

	@FindBy(how = How.XPATH, using = "//p/p[contains(.,'Vision21') and contains(., 'Dealer Code')]")
	public WebElement v21DealerCode;

	@FindBy(how = How.XPATH, using = "//p/p[contains(.,'Supersystem CAN')]")
	public WebElement superSystemCANIntentProducts;

	@FindBy(how = How.XPATH, using = "//*[@id='ProdList']//tr[1]/td[1]")
	public WebElement office365ProductName;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'ConfirmedSSWON')]//following-sibling::span")
	public List<WebElement> confirmedCheckbox;

	@FindBy(how = How.XPATH, using = "//*[@id='SSWON1']//p/p")
	public WebElement workOrderNumber1;

	@FindBy(how = How.XPATH, using = "//*[@id='SSWON2']//p/p")
	public WebElement workOrderNumber2;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Assign To Me')]")
	public WebElement assignToMeCheckbox;

	@FindBy(how = How.XPATH, using = "//div[@id='AssignTask']/p")
	public WebElement assignTaskConfirmButton;

	@FindBy(how = How.XPATH, using = "//*[@id='AssignedToUser']")
	public WebElement assignedToUserText;

	@FindBy(how = How.XPATH, using = "//*[@id='DisplayState']")
	public WebElement displayStateText;

	@FindBy(how = How.XPATH, using = "//button[@label='Complete']/span")
	public WebElement completeButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Complete')]")
	public WebElement sendOrderToPICompleteButton;
	
	@FindBy(how = How.XPATH, using = "//button/span[contains(.,'Complete')]")
	public WebElement billingActivationCompleteButton;
	
	@FindBy(how = How.XPATH, using = "//label[@class='slds-checkbox_faux']//following::button/span[contains(.,'Complete')]")
	public WebElement newCompleteButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Complete']")
	public WebElement monitorCompleteButton;

	@FindBy(how = How.XPATH, using = "//label/input[@id='FailTask']/following-sibling::span")
	public WebElement failTaskCheckBox;

	@FindBy(how = How.XPATH, using = "//textarea[@id='BusinessFailureText']")
	public WebElement businessFailureText;

	@FindBy(how = How.XPATH, using = "//div[@id='Step1_nextBtn']")
	public WebElement office365NextButton;

}
