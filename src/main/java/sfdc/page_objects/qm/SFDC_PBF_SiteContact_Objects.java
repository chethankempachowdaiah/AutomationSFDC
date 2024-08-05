package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:17/08/2021
 *
 *         SFDC> PBF Site Contact page objects
 */
public class SFDC_PBF_SiteContact_Objects {

	public SFDC_PBF_SiteContact_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//button[contains(.,'Add a Contact') or contains(.,'Ajouter un contact')]")
	public WebElement addAContactButton;

	@FindBy(xpath = "//label[contains(text(),'First Name') or contains(text(),'Prénom')]/parent::div/preceding-sibling::input")
	public WebElement siteConFirstName;

	@FindBy(xpath = "//label[contains(text(),'Last Name') or contains(text(),'Nom de famille')]/parent::div/preceding-sibling::input")
	public WebElement siteConLastName;

	@FindBy(xpath = "//label[contains(text(),'Email') or contains(text(),'Adresse courriel')]/parent::div/preceding-sibling::input")
	public WebElement siteConEmailAddress;

	@FindBy(xpath = "//label[contains(text(),'Phone') or contains(text(),'Numéro de téléphone mobile')]/parent::div/preceding-sibling::input")
	public WebElement siteConPhone;

	@FindBy(xpath = "//button[contains(.,'Continue') or contains(@value,'continue') or contains(.,'Continuer')]")
	public WebElement continueButton;

}
