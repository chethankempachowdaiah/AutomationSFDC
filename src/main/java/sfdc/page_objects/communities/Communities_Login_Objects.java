package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:09/06/2020
 *
 *         Communities login page objects
 */

public class Communities_Login_Objects {

	public Communities_Login_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Email Address')]//following-sibling::input")
	public WebElement adminEmailAddressInput;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Password')]//following-sibling::input")
	public WebElement adminPasswordInput;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Generate Token')]")
	public WebElement generateTokenButton;

	@FindBy(how = How.XPATH, using = "//a/span/span[contains(.,'Sign in')]")
	public WebElement signInLink;

	@FindBy(how = How.XPATH, using = "//input[@name='mailAddress']")
	public WebElement userIdInput;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Continue')]")
	public WebElement continueButton;

	@FindBy(how = How.XPATH, using = "//input[@name='Password']")
	public WebElement passwordInput;

	@FindBy(how = How.XPATH, using = "//button[@value='submit']")
	public WebElement signInButton;

	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	public WebElement userNameInputDevStage;

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	public WebElement passwordInputDevStage;

	@FindBy(how = How.XPATH, using = "//input[@id='Login']")
	public WebElement loginButtonDevStage;

	@FindBy(how = How.XPATH, using = "//a/span/span[contains(.,'Sign in')]")
	public List<WebElement> signInLinkDevStage;

}
