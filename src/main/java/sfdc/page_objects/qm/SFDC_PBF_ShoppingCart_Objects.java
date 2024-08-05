package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:11/08/2021
 *
 *         SFDC> PBF page objects
 */
public class SFDC_PBF_ShoppingCart_Objects {

	public SFDC_PBF_ShoppingCart_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Waive Installation fee') or contains(text(),'Annuler les frais')]")
	public WebElement waiveOffInstallationButton;
	
	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'cutoffprice')])[1]")
	public WebElement afterWaiveOffInstallationUnitPrice;

	@FindBy(how = How.XPATH, using = "(//div[contains(@class,'cutoffprice')])[2]")
	public WebElement afterWaiveOffInstallationLineTotalPrice;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add installation fee') or contains(text(),'Ajouter frais d')]")
	public WebElement addInstallationFeeButton;
	
	@FindBy(how = How.XPATH, using = "//button[@value='updateCart']")
	public WebElement updateCartButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'edit-button')]//a[.='Edit']")
	public WebElement editEthernetPlanButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(.,'Microsoft 365') and contains(@class,'text-body')]")
	public WebElement off365AddOnProductName;
	
	@FindBy(how = How.XPATH, using = "//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(.,'365')]/parent::div//div[3][contains(@class,'body--regular')]")
	public WebElement off365AddOnProductQty;
	
	@FindBy(how = How.XPATH, using = "//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(.,'365')]/parent::div//div[2][contains(@class,'regular')]/span")
	public WebElement off365AddOnProductPrice;
	
	@FindBy(how = How.XPATH, using = "//div[@c-pbfosaddonsummary_pbfosaddonsummary='' and contains(.,'365')]/parent::div//div[4][contains(@class,'regular')]/span")
	public WebElement off365AddOnProductLineTotal;
	
	
}
