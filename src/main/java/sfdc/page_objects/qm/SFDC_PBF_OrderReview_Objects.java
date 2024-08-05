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
 *         SFDC> PBF order review page objects
 */
public class SFDC_PBF_OrderReview_Objects {

	public SFDC_PBF_OrderReview_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(text(),'Please advise the client if they need an e-signature') or contains(text(),'Si une signature électronique est requise, veuillez en informer le client')]")
	public WebElement eSignatureSection;
	
	@FindBy(xpath = "//span[contains(text(),'No, they will accept the quote verbally') or contains(text(),'Non, la soumission peut être acceptée verbalement')]//ancestor::label/preceding-sibling::input")
	public WebElement noESignatureRadio;

	@FindBy(xpath = "//span[contains(text(),'Yes, they need an e-signature') or contains(text(),'Oui, une signature électronique est requise')]//preceding-sibling::span")
	public WebElement needESignatureRadio;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'E-signature from the signing authority is required') or contains(text(),'Une signature électronique du signataire autorisé est requise')]")
	public WebElement eSignatureRequiredMessage;

	@FindBy(how = How.XPATH, using = "//button[.='Send for approval' or .='Envoyer pour approbation']")
	public WebElement sendForApprovalButton;
	
	@FindBy(how = How.XPATH, using = "//a[.='Edit']/parent::div/following-sibling::div")
	public WebElement siteContactName;
	
	@FindBy(how = How.XPATH, using = "//a[.='Edit']/parent::div/following-sibling::div[2]")
	public WebElement siteContactPhone;
	
	@FindBy(how = How.XPATH, using = "//a[.='Edit']/parent::div/following-sibling::div[3]")
	public WebElement siteContactEmail;
	
	
}
