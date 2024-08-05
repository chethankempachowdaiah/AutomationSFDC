package sfdc.page_objects.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 15/JULY/2020
 * 
 *         Contact>Enable Community User
 *
 */
public class SFDC_EnableCommunityUser_Objects {

	public SFDC_EnableCommunityUser_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='oneAlohaPage']//iframe[@title='accessibility title']")
	public WebElement EnableCommunityFrame;

	@FindBy(how = How.XPATH, using = "//input[@id='CM_FirstName']")
	public WebElement contactFirstNameValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CM_LastName']")
	public WebElement contactLastNameValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CM_Email']")
	public WebElement contactEmailValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CM_LanguagePreference']")
	public WebElement contactPreferredLanguageValue;

	@FindBy(how = How.XPATH, using = "//input[@id='CM_UserGUID']")
	public WebElement commuserGUID;

	@FindBy(how = How.XPATH, using = "//div[@role='button']/div/div[contains(@class, 'addbutton')]")
	public WebElement supportDetailsNewButton;

	@FindBy(how = How.XPATH, using = "//label[@for='CM_ServiceGroupName']/following-sibling::div//input[@id='CM_ServiceGroupName']")
	public WebElement supportGroupNameInput;

	@FindBy(how = How.XPATH, using = "//label[@for='CM_SupportEmail']//following-sibling::div//input[@id='CM_SupportEmail']")
	public WebElement supportGroupEmailInput;

	@FindBy(how = How.XPATH, using = "//label[@for='CM_SupportPhone']//following-sibling::div//input[@id='CM_SupportPhone']")
	public WebElement suppportGroupPhoneInput;

	@FindBy(how = How.XPATH, using = "//header[contains(.,'Support Details')]//following-sibling::div//label[@for='CM_ProductSupported']/following-sibling::div//select[@id='CM_ProductSupported'  and @name='loopname']")
	public WebElement productSupportedDropdownDropdown;

	@FindBy(how = How.XPATH, using = "//footer/button[contains(.,'Save')]")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//form[contains(@name,'EditBlockFormMain')]")
	public WebElement supportGroupDetails;

	@FindBy(how = How.XPATH, using = "//div[@id='CM_CreateUser_nextBtn']")
	public WebElement createUserNextButton;

	@FindBy(how = How.CSS, using = "#CM_SuccessBlk>div>p>h3")
	public WebElement userCreatedSuccessMsg;

	@FindBy(how = How.XPATH, using = "//div[@id='CM_ReviewUser_nextBtn']")
	public WebElement reviewUserNextButton;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Community user') and contains(.,'already exists.')]")
	public WebElement communityUserAlreadyExistsMsg;

	@FindBy(how = How.XPATH, using = "//h3[contains(.,'Activation Time Out.Please click the below button to resend the activation email.')]")
	public WebElement activationTimeOutMsg;

	@FindBy(how = How.XPATH, using = "//div[@id='CM_GetUserStatus_nextBtn']")
	public WebElement getUserStatusNextButton;

}
