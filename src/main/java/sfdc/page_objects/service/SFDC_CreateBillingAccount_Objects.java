package sfdc.page_objects.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 13/jan/2020
 * 
 *         SFDC Create Billing Account
 *
 */
public class SFDC_CreateBillingAccount_Objects {

	public SFDC_CreateBillingAccount_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='CBiA_ChooseParentAccount']")
	public WebElement billing_ChooseParentAccountInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CBiA_AccountName']")
	public WebElement billingAccountNameInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CBiA_AccountNumber']")
	public WebElement billingAccountNumberInput;

	@FindBy(how = How.XPATH, using = "//select[@id='CBiA_AccountSource']")
	public WebElement billingAccountSourceInput;

	@FindBy(how = How.XPATH, using = "//div[@id='CBiA_CreateBillingAccount_nextBtn']")
	public WebElement cBiA_CreateBillingAccount_nextBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='CBiA_BillingAccountCreated']")
	public WebElement billingAccountCreatedText;

	@FindBy(how = How.XPATH, using = "//*[@id='CBiA_BillingAccountConfirmation_nextBtn']")
	public WebElement cBiA_BillingAccountConfirmation_nextBtn;

}
