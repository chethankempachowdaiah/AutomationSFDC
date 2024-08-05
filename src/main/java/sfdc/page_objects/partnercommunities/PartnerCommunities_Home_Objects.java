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
 *         Partner Communities home page objects
 */

public class PartnerCommunities_Home_Objects {

	public PartnerCommunities_Home_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//img[contains(@class,'profileIcon')]")
	public WebElement profileIcon;

	@FindBy(how = How.XPATH, using = "//button[.='Sales']")
	public WebElement salesMenuButton;

	@FindBy(how = How.XPATH, using = "//a[.='Account']")
	public WebElement accountOptionLink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Opportunit')]/parent::a")
	public WebElement opportunityOptionLink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'profileName')]/parent::a")
	public WebElement profileNameLink;
	
	@FindBy(how = How.XPATH, using = "//span[.='All Accounts']/parent::a")
	public WebElement allAccountsListOption;
	
	@FindBy(how = How.XPATH, using = "//span[.='All Billing Accounts']/parent::a")
	public WebElement billingAccountsListOption;
	
	@FindBy(how = How.XPATH, using = "//table//tbody//tr")
	public WebElement accountsTabledata;
	

}
