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
 *         Communities> PBF Order Review page objects
 */
public class Communities_PBF_OrderReview_Objects {

	public Communities_PBF_OrderReview_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[.='Review & place order' or .='Vérifiez et passez la commande']")
	public WebElement orderReviewPageHeader;

	@FindBy(how = How.XPATH, using = "//div[.='Order contact' or .='Contact responsable de la commande']")
	public WebElement orderContactLabel;

	@FindBy(how = How.XPATH, using = "//div[.='Order contact' or .='Contact responsable de la commande']/following-sibling::div[1]")
	public WebElement orderContactName;
	
	@FindBy(how = How.XPATH, using = "//div[.='Order contact' or .='Contact responsable de la commande']/following-sibling::div[2]")
	public WebElement orderContactPhoneNumber;
	
	@FindBy(how = How.XPATH, using = "//div[.='Order contact' or .='Contact responsable de la commande']/following-sibling::div[3]")
	public WebElement orderContactEmailID;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Signing') or contains(text(),'Signataire autorisé')]")
	public WebElement signingAuthorityLabel;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Signing') or contains(text(),'Signataire autorisé')]/following-sibling::div[1]")
	public WebElement signingAuthorityName;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Signing') or contains(text(),'Signataire autorisé')]/following-sibling::div[2]")
	public WebElement signingAuthorityPhoneNumber;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Signing') or contains(text(),'Signataire autorisé')]/following-sibling::div[3]")
	public WebElement signingAuthorityEmailID;
	
@FindAll({
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Billing Address') or contains(text(),'Adresse de facturation')]"),
@FindBy(how = How.XPATH, using = "//c-pbf-os-account-info-tile//*[contains(text(),'Billing Address') or contains(text(),'Adresse de facturation')]")})

	public WebElement orderContactBillingAddressLabel;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Billing Address') or contains(text(),'Adresse de facturation')]/following-sibling::div[1]")
	public WebElement orderContactBillingAddressValue;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//button[.='Send PDF Copy of Quote Summary to Customer' or .='Ouvrir une copie PDF du sommaire de la commande']"),
		@FindBy(how = How.XPATH, using = "//button[.='Open PDF copy of order summary']")})
	public WebElement openPDFCopyOrderSummaryButton;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Download order summary') or contains(text(),'Télécharger le sommaire de la commande')]")
	public WebElement downloadOrderSummaryButton;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//button[.='Email Quote Summary' or .='Envoyer le sommaire de la commande par courriel']"),
		@FindBy(how = How.XPATH, using = "//button[.='Email order summary']")})
	public WebElement emailOrderSummary;
	
	@FindBy(how = How.XPATH, using = "//div[.='Email successfully sent' or contains(text(),'Courriel envoyé avec succès')]")
	public WebElement emailSentMessage;
	
	@FindBy(how = How.XPATH, using = "//div[@class='DocumentContainer']")
	public List<WebElement> documentContainerDiv;
	
	@FindBy(how = How.XPATH, using = "//div[.='Back to review and place order' or .='Retourner à Vérifier et passer la commande']/button")
	public WebElement backToOrderReviewPageButton;
	
	@FindBy(how = How.XPATH, using = "//div[.='Terms & Conditions' or .='Modalités']")
	public WebElement termAndConditionsSection;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Show details') or contains(text(),'Afficher les détails')]/parent::a")
	public WebElement showDetails;
	
	@FindBy(how = How.XPATH, using = "//div[@class='term-container']//div[contains(@class,'text-body')]")
	public WebElement termAndConditionsText;
	
	@FindAll({@FindBy(xpath = "//button[.='Open Rogers for Business Agreement in PDF']"),
		@FindBy(xpath = "//*[contains(text(),'Rogers') and contains(text(),'service') and contains(text(),'Affaires en PDF')]")})
	public WebElement rogersBusinessAgreementButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Open Office 365 Specific Terms in PDF']")
	public WebElement rogersOff365TermsButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Open Business Internet Specific Terms in PDF']")
	public WebElement rogersBusinessInternetSpecificTermsButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Open Business TV Specific Terms in PDF']")
	public WebElement rogersBusinessTVTermsButton;
	
	@FindBy(how = How.XPATH, using = "//button[.='Open Dedicated Internet Specific Terms in PDF']")
	public WebElement rogersRDITermsButton;
	
	@FindAll({
		@FindBy(xpath = "//button[.='Open Business Internet Specific Terms in PDF']"),
		@FindBy(xpath = "//*[contains(text(),'Ouvrir les modalités spécifiques d') and contains(text(),'Internet Affaires en PDF')]"),
		@FindBy(xpath = "//button[.='Open Dedicated Internet Specific Terms in PDF']"),
		@FindBy(xpath = "//button[.='Open Business TV Specific Terms in PDF']")})
	public WebElement rogersBusinessInternetTermsButton;
	
	@FindBy(how = How.XPATH, using = "//input[@type='checkbox']")
	public WebElement termsAndConditionsCheckbox;
	
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Place Order') or contains(.,'Passer une commande')]")
	public WebElement placeOrderButton;
	
	@FindBy(how = How.XPATH, using = "//*[.='Cancel and back' or .='Annuler et revenir']")
	public WebElement cancelAndBackButton;
	
	@FindBy(how = How.XPATH, using = "//span[.='Credit check not required']")
	public WebElement creditCheckNotReqMessage;
	
	@FindBy(how = How.XPATH, using = "//span[.='Credit check required']")
	public WebElement creditCheckReqMessage;
	
	
	@FindBy(how = How.XPATH, using = "//p[@class='nds-text-body_regular']")
	public WebElement creditCheckMessagedescription;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Confirm Order') or contains(.,'Confirmer la commande')]")
	public WebElement confirmOrderButton;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add contact') or contains(text(),'Ajouter contact')]")
	public WebElement addContactButton;
	
}
