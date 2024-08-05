package sfdc.page_objects.sales;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class SFDC_Setup_Objects {

	public SFDC_Setup_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='oneUserProfileCard']//h1/a[@class='profile-link-label']")
	public WebElement userProfileLoginName;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//img[@title='User']//ancestor::button"),
		@FindBy(how = How.XPATH, using = "//img[contains(@src,'profilephoto')]//ancestor::a")})
	public WebElement userImg;

	@FindBy(how = How.XPATH, using = "//div[@title='User Detail']//ancestor::a")
	public WebElement userDetails;
	
	@FindBy(how = How.XPATH, using = "//span[.='Setup']//ancestor::a")
	public WebElement setupIcon;
	
	@FindBy(how = How.XPATH, using = "//li[@id='related_setup_app_home']//ancestor::a")
	public List <WebElement> setupButton;
	
	@FindBy(how = How.XPATH, using = "//input[@title='Search Setup']")
	public WebElement searchSetup;
	
	@FindBy(how = How.XPATH, using = "//a[.='Create New Lead']")
	public WebElement linkCreateNewLead;
	
	@FindBy(how = How.XPATH, using = "//span[.='Permission Sets']")
	public WebElement permissionSetLabel;
	
	@FindBy(how = How.XPATH, using = "//div[@class='setupcontent']")
	public WebElement div;
	
	@FindBy(how = How.XPATH, using = "//h2[.='Create New Lead']")
	public List <WebElement> createNewLead;
	
}

