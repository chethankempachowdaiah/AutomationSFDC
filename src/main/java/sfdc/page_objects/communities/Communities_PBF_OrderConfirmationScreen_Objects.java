package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


/**
 * @author Anukriti.Chawla, Date:13/07/2021
 *
 *         Communities> PBF Order confirmation page objects
 */
public class Communities_PBF_OrderConfirmationScreen_Objects {

	public Communities_PBF_OrderConfirmationScreen_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[.='Thank you, your order is now complete' or .='Merci, votre commande est maintenant terminée']")
	public WebElement orderConfirmationPageHeader;
	
	@FindBy(how = How.XPATH, using = "//span[.='Your order has been received, however it is pending approval']")
	public WebElement quoteConfirmationPageHeader;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'#')]")
	public WebElement orderNumberLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'View order') or contains(text(),'Voir la commande')]")
	public WebElement viewOrderLink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'#')]")
	public WebElement qouteName;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Go to My Orders')]")
	public WebElement goToMyOrdersLink;
	
	
}
