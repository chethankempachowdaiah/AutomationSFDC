package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


/**
 * @author Anukriti.Chawla, Date:16/08/2021
 *
 *         SFDC> PBF Order confirmation page objects
 */
public class SFDC_PBF_OrderConfirmationScreen_Objects {

	public SFDC_PBF_OrderConfirmationScreen_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Thank you, your order is now complete') or contains(text(),'Merci, votre commande est maintenant terminée')]")
	public WebElement orderConfirmationPageHeader;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'#')]")
	public WebElement orderNumberLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'View order') or contains(text(),'Voir la commande')]")
	public WebElement viewOrderLink;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Done') or contains(text(),'Terminer')]")
	public WebElement doneButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'#')]")
	public WebElement qouteName;
	
	@FindBy(how = How.XPATH, using = "//div[.='A site contact must be selected before this order can be completed.']")
	public WebElement siteContactSelectMessage;
	
	@FindBy(how = How.XPATH, using = "//span[.='Thank you, your order is almost complete']")
	public WebElement orderAlmostCompleteMessage;

	@FindBy(how = How.XPATH, using = "//span[.='Thank you, your order is now pending' or .='Merci, votre commande est en attente']")
	public WebElement quoteConfirmationPageHeader;
	
}
