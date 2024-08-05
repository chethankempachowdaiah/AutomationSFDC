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
public class SFDC_PBF_TVAddOns_Selection_Objects {

	public SFDC_PBF_TVAddOns_Selection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[.='Rogers Business TV Add-Ons']")
	public WebElement tvAddOnHeader;
	
	@FindBy(how = How.XPATH, using = "//*[@c-pbfosaddonstitching_pbfosaddonstitching='']//div[contains(@class,'heading')]")
	public List<WebElement> tvAddOnsProductNames;
	
	@FindBy(how = How.XPATH, using = "//button[.='View more details']")
	public List<WebElement> viewMoreDetailsButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Product Details']//following-sibling::ul//li")
	public List<WebElement> productDetailsAddOns;
	
	@FindBy(how = How.XPATH, using = "//button[@name='increment']")
	public List<WebElement> increementAddOnButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Update Cart']")
	public WebElement updateCart;
	
}
