package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:19/01/2022
 *
 *         SFDC> PBF multisite Shopping cart page objects
 */
public class SFDC_PBF_MultiSite_ShoppingCart_Objects {

	public SFDC_PBF_MultiSite_ShoppingCart_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[.='Need to add a new site or edit selected sites?']")
	public WebElement needNewSiteEditSiteLabel;
	
	@FindBy(xpath = "//button[.='Add sites']")
	public WebElement addSiteButton;
	
	@FindBy(xpath = "//div[contains(@class,'serviceAddress')]")
	public List<WebElement> addedSitesBlocks;
	
	@FindBy(how = How.XPATH, using = "//div[.='Service Address']/following-sibling::div[1]")
	public List<WebElement> serviceAddressText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Service Address') or contains(text(),'Adresse de service')]")
	public List<WebElement> serviceAddressSubHeading;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Show more')]")
	public List<WebElement> showMoreButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Monthly fees']//following-sibling::div//div[@class='nds-grid']//div[@c-pbfosquoteproductitems_pbfosquoteproductitems='' and contains(@class,'text-body--regular')]")
	public List<WebElement> addedProductName;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'term')]")
	public List<WebElement> addedProductTerm;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid']//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(@class,'text-body--regular')]")
	public List<WebElement> addedAddOnsName;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-grid']//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(@class,'text-body--regular')]//ancestor::div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(@class,'nds-wrap')]//div[3]")
	public List<WebElement> addedAddOnsQty;
	
	@FindBy(how = How.XPATH, using = "//div[text()='One-time fees']")
	public List<WebElement> oneTimeFeesForEachSection;
	
}
