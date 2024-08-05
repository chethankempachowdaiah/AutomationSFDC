package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_PBF_TV_AddOns_Selection_Objects {
	
	public SFDC_PBF_TV_AddOns_Selection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[contains(text(),'Rogers Business TV Add-Ons')]")
	public WebElement addOnPageHeader;
	
	@FindBy(xpath = "//c-mp-os-qty-tile//*[contains(@class,'heading')]")
	public List<WebElement> addOnHeaderList;
	
	@FindBy(xpath = "//c-mp-os-qty-tile//*[contains(@class,'description')]")
	public List<WebElement> productDescriptionList;	
	
	@FindBy(xpath = "//button[@name='increment']")
	public List<WebElement> incrementAddOnButton;
	
	@FindBy(xpath = "//button[@name='decrement']")
	public List<WebElement> decrementAddOnButton;
	
	@FindBy(xpath = "//button[contains(text(),'Update Cart')]")
	public WebElement updateCartButton;
	
	@FindBy(xpath = "//c-pbf-os-footer//*[contains(@class,'shopping-cart')]")
	public WebElement shoppingCartButton;

}
