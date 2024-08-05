package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:03/11/2021
 *
 *         SFDC> PBF TV Add-Ons selection page objects
 */
public class SFDC_PBF_Office365AddOns_Selection_Objects {

	public SFDC_PBF_Office365AddOns_Selection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll ({
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'365 plan')]"),
		@FindBy(how = How.XPATH, using = "//div[.='Rogers Office 365 Add-Ons']") })
	public WebElement offAddOnHeader;
	
	@FindAll ({
		@FindBy(how = How.XPATH, using = "//span[@c-pbfosaddonoffice_pbfosaddonoffice='' and contains(@class,'heading')]"),
		@FindBy(how = How.XPATH, using = "//*[@c-pbfosaddonoffice_pbfosaddonoffice='']//div[contains(@class,'vertical--l')]") })
	public List<WebElement> offAddOnsProductNames;
	
	@FindBy(how = How.XPATH, using = "//button[.='View more details']")
	public List<WebElement> viewMoreDetailsButton;
	
	@FindAll ({
		@FindBy(how = How.XPATH, using = "//span[@c-pbfosaddonoffice_pbfosaddonoffice='']//li"),	
		@FindBy(how = How.XPATH, using = "//div[.='PRODUCT DETAILS']//following-sibling::ul//li")})
	public List<WebElement> productDetailsAddOns;
	
	@FindBy(how = How.XPATH, using = "//button[@name='increment']")
	public List<WebElement> increementAddOnButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Added To Cart']")
	public WebElement addedToCartButton;
	
	
}
