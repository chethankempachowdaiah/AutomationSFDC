package sfdc.page_objects.om;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 03/FEB/2020
 * 
 *         SFDC Create Supersystem Order (Manual Queue>Account Provisioning
 *         Queue Details>Pick Up> Create Supersystem Order)
 *
 */
public class SFDC_CreateSuperSystemOrder_Objects {

	public SFDC_CreateSuperSystemOrder_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Vision21 Dealer Code :')]/following-sibling::span")
	public WebElement v21DealerCode;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'SGI Dealer Code :')]/following-sibling::span")
	public WebElement sgiDealerCode;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Business Name : ')]/following-sibling::span")
	public WebElement businessAccountName;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Order Number :')]/following-sibling::span")
	public WebElement orderNumber;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Order State')]/following-sibling::span")
	public WebElement orderState;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Site Name')]/following-sibling::span")
	public WebElement siteName;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Site Contact Name')]/following-sibling::span")
	public WebElement siteContactName;

	@FindBy(xpath = "//h3[contains(.,'Internet Product Details')]//following-sibling::div//div[@class='ng-binding']")
	public WebElement internetProductsContractCode;

	@FindBy(xpath = "//h3[contains(.,'Internet Product Details')]//following-sibling::div//div[contains(@ng-repeat,'internetbill_name')]")
	public WebElement internetProductsContractName;

	@FindBy(xpath = "//h3[contains(.,'TV Product Details')]//following-sibling::div//div[@class='ng-binding']")
	public WebElement tvProductsContractCode;

	@FindBy(xpath = "//h3[contains(.,'TV Product Details')]//following-sibling::div//div[contains(@ng-repeat,'tv_promoName')]")
	public WebElement tvProductsContractName;

	@FindBy(how = How.XPATH, using = "//div[contains(@ng-repeat,'internetcis_prodName')]")
	public WebElement speedInInternetProduct;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Assign To Me')]")
	public WebElement assignToMeCheckbox;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Change State')]")
	public WebElement changeStateCheckbox;

	@FindBy(how = How.XPATH, using = "//select[@id='stateVal' or @id='State']")
	public WebElement selectStateDropdown;

	@FindBy(how = How.XPATH, using = "//div[@id='IPChangeTaskState']")
	public WebElement changeStateButton;

	@FindBy(how = How.XPATH, using = "//div//div[@role='button' and contains(.,'Cancel')]")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//div//button[@id='alert-ok-button']")
	public WebElement cancelOkButton;

	@FindBy(how = How.XPATH, using = "//*[@id='taskAssignedTo']/b")
	public WebElement assignedToUserText;

	@FindBy(how = How.XPATH, using = "//*[@id='currentState']/b")
	public WebElement displayStateText;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Fail Task')]")
	public WebElement failTaskCheckBox;

	@FindBy(how = How.XPATH, using = "//textarea[@id='failureText']")
	public WebElement businessFailureText;

	@FindBy(how = How.XPATH, using = "//input[@id='ssCAN']")
	public WebElement superSystemCANNumberInput;

	@FindBy(how = How.XPATH, using = "//input[@id='v21BAN']")
	public WebElement consolidatedV21BANInput;

	@FindBy(how = How.XPATH, using = "//input[@id='woNumber1']")
	public WebElement superSystemWorkOrderNumber1Input;

	@FindBy(how = How.XPATH, using = "//span[@title='Add']")
	public WebElement workOrderAddButton;

	@FindBy(how = How.XPATH, using = "//input[@id='woNumber2']")
	public WebElement superSystemWorkOrderNumber2Input;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Verify Order')]")
	public WebElement validateOrderStatusCheck;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Success! Proceed with Task')]")
	public WebElement successTaskProceed;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Complete')]")
	public WebElement persistSupersystemCANCompleteButton;

}
