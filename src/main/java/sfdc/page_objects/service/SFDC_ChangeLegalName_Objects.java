package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date 01/05/2020
 * 
 *         Change Legal Name(Account>Change Legal Name)
 *
 */
public class SFDC_ChangeLegalName_Objects {

	public SFDC_ChangeLegalName_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='CN_LegalName']")
	public List<WebElement> legalNameInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_UploadFile']")
	public WebElement uploadLetterOfAssignmentInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_AccountName']")
	public WebElement accountNameValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_AccountOwner']")
	public WebElement accountOwnerValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_BillingStreet']")
	public WebElement streetValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_Phone']")
	public WebElement phoneValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_BillingCity']")
	public WebElement cityValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_BillingState']")
	public WebElement provinceValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_BillingCountry']")
	public WebElement countryValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CN_BillingPostalCode']")
	public WebElement postalCodeValue;

	@FindBy(how = How.XPATH, using = "//div[@id='CLN_LegalNameStep_nextBtn']")
	public WebElement legalNameChangeNext;

	@FindBy(how = How.XPATH, using = "//label/span[contains(.,'Proceed with the legal name change.')]")
	public WebElement proceedWithLegalNameChangeRadio;

	@FindBy(how = How.XPATH, using = "//div[@id='CN_DuplicateList_nextBtn']")
	public WebElement legalNameDuplicateListNextButton;

	@FindBy(how = How.XPATH, using = "//p//p[contains(.,'Legal Name change request for the account') and contains(.,' is submitted successfully.')]")
	public List<WebElement> legalNameChangeSubmittedSuccess;

	@FindBy(how = How.XPATH, using = "//div[@id='CN_ReviewDetails_nextBtn']")
	public WebElement reviewLegalNameChangeNext;

}
