package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Sakshi.Lnu, Date:26/08/2021
 *
 *         Communities> Complete Order Flow page objects
 */
public class Communities_CompleteOrderFlow_Objects {

	public Communities_CompleteOrderFlow_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Transfer numbers and request new numbers from Rogers']")
	public WebElement customerInforFormHeading;
	
	@FindBy(how = How.XPATH, using = "//c-wa-customer-info-form//div[contains(normalize-space(),'Transfer numbers')]")
	public WebElement transferNumberHeader;
	
	@FindBy(how = How.XPATH, using = "//div[@class=\"order-details\"]")
	public WebElement orderNumberField;
	
	@FindBy(how = How.XPATH, using = "//div[contains(normalize-space(),'Step 1 of 2')]/parent::c-wa-port-eligible-providers")
	public WebElement step1EligibilityCheck;
		
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Select your request']")
	public WebElement selectRequestHeader;
	
	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Request new number']//ancestor::span//input[@type='radio']")
	public WebElement requestNewNumberRadio;
	
	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Transfer number']//ancestor::span//input[@type='radio']")
	public WebElement transferNumberRadio;
	
	@FindBy(how = How.XPATH, using = "//label[normalize-space()='Phone Number']/following-sibling::input[@class=\"input1\"]")
	public WebElement portNumberInputField;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Check Eligibility']")
	public WebElement checkEligibiltyButton;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Go to provide information']")
	public WebElement provideInfoButton;
	
	@FindBy(how = How.XPATH, using = "//div[@c-pbfreviewforminfo_pbfreviewforminfo]/*[name()='svg']")
	public WebElement eligibiltyCheck;

	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Review Eligibiltiy Details']/ancestor::c-pbf-customer-info-form")
	public WebElement reviewEligibiltyHeader;
	
	@FindBy(how = How.XPATH, using = "(//div[@c-pbfreviewforminfo_pbfreviewforminfo]//div[contains(@class,'table-product')])[3]")
	public WebElement numberDetails;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()=\"Go to provide information\" and @disabled]")
	public WebElement disabledProvideInfoButton;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Edit']/span")
	public WebElement editButton;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Review Eligibility']")
	public WebElement reviewEligibilityButton;
	
	@FindBy(how = How.XPATH, using = "//p[contains(normalize-space(),'Step 2 of 2 Provide Information')]")
	public WebElement step2EligibilityCheck;
	
	@FindBy(how = How.XPATH, using = "//div[ @class='container']/div/div[contains(text(),'Line 01 :')]")
	public WebElement requestedType;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[contains(@data-omni-key,\"AssignedUserFirstName\")]//input")
	public WebElement assignedFirstNameInput;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[contains(@data-omni-key,\"AssignedUserLastName\")]//input")
	public WebElement assignedLastNameInput;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[contains(@data-omni-key,\"AssignedUser\")]//input")
	public WebElement assignedUserInput;
	//transfer number

	@FindBy(how = How.XPATH, using = "(//div/label[text()='Account number']/parent::div/preceding-sibling::input[@type='text'])[1]")
	public WebElement accountNumberInput;
	
	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='IMEI']//ancestor::span//input[@type='radio']")
	public WebElement imeiDeviceTypeRadio;
	
	@FindBy(how = How.XPATH, using = "//div/label[text()='IMEI']/parent::div/preceding-sibling::input[@type='text']")
	public WebElement imeiInput;
	
	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='ESN']//ancestor::span//input[@type='radio']")
	public WebElement esnDeviceTypeRadio;
	
	@FindBy(how = How.XPATH, using = "//div/label[text()='ESN']/parent::div/preceding-sibling::input[@type='text']")
	public WebElement esnInput;
		
	//new number 
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-select[@data-omni-key=\"Province\"]")
	public WebElement proviceDropdown;
	
	@FindBy(how = How.XPATH, using = "//c-customer-info-omni-select-element[@data-omni-key=\"City\"]")
	public WebElement cityDropdown;
	
	@FindBy(how = How.XPATH, using = "//button//span[text()='Save details']")
	public WebElement saveDetailsButton;
	
	@FindBy(how = How.XPATH, using = "//button[@disabled]//span[text()='Submit request']")
	public WebElement disabledSubmitButton;
	
	@FindBy(how = How.XPATH, using = "//div[@role='listbox']/child::ul/child::li/div[@role='option']")
	public List<WebElement> provinceDropDownOptions;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Submit request']")
	public WebElement submitRequestButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Thank')]")
	public WebElement thankTitleText;
	
	@FindBy(how = How.XPATH, using = "//span[@class='order-number']")
	public WebElement orderNumberConfirmation;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Go')]")
	public WebElement goToOrderLink;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//c-customer-info-omni-select-element[@data-omni-key=\"City\"]//div[@role='listbox']/child::ul/child::li/div[@role='option']"),
		@FindBy(how = How.XPATH, using = "(//ul[contains(@class,'dropdown-container listbox')])[2]//span[contains(@class,'option-text_entity')]")})
		public List<WebElement> cityDropDownOptions;
	
	@FindBy(how = How.XPATH, using = "//p[normalize-space()='911 Limitations']")
	public WebElement limitationHeader;
	
	@FindBy(how = How.XPATH, using = "//span[@c-pbfosdownloadpdf_pbfosdownloadpdf]")
	public WebElement downloadPDFbtn;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'checkbox_faux')]")
	public WebElement agreeChkBox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'label checkbox-text')]")
	public WebElement agreeChkBoxText;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'back-button-size')]")
	public WebElement limitationBackBtn;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'save-button-margin')]")
	public WebElement limitationSubmitBtn;
	
}
