package sfdc.page_objects.partnercommunities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:14/05/2021
 *
 *         Partner Communities login page objects
 */

public class PartnerCommunities_Login_Objects {

	public PartnerCommunities_Login_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Username']")
	public WebElement userNameInput;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Password']")
	public WebElement passwordInput;

	@FindBy(how = How.XPATH, using = "//span[.='Log in']/parent::button")
	public WebElement loginButton;

}
