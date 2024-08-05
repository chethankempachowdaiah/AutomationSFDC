package sfdc.page_objects.wacc;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_ReviewOrder_Objects {

	public WACC_ReviewOrder_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(normalize-space(),'after tax bill credit per line')]")
	public WebElement taxBillCredit;

	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'PDF Copy') or contains(normalize-space(),'copie PDF')]")
	public WebElement pdfSummary;

	//	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Email order summary']")
	//	public WebElement emailSummary;

	@FindBy(how = How.XPATH, using = "(//vlocity_cmt-omniscript-text-block[@data-omni-key='orderReviewAccountOrder']//div[contains(normalize-space(),'s order') or contains(normalize-space(),'Commande')])[3]")
	public WebElement accountName;

	@FindBy(how = How.XPATH, using = "//span[contains(normalize-space(),'credit per line')]")
	public WebElement activationCredit;

	@FindAll({@FindBy(how = How.XPATH, using = "(//div[@c-pbftermsandconditions_pbftermsandconditions and contains(normalize-space(),'Terms') and contains(normalize-space(),'Conditions')])[2]"),
		@FindBy(how = How.XPATH, using = "//div[@c-pbftermsandconditions_pbftermsandconditions and normalize-space()='Modalités']")})
	public WebElement termsConditions;

	@FindBy(how = How.XPATH, using = "//span[@c-pbftermsandconditions_pbftermsandconditions and contains(normalize-space(),'details') or contains(normalize-space(),'détails')]")
	public WebElement termsConditionsShowLink;


	@FindBy(how = How.XPATH, using = "//span[contains(normalize-space(),'Business Agreement in PDF') or contains(normalize-space(),'Affaires en PDF')]")
	public WebElement pdfBusinessAgreement;

	@FindBy(how = How.XPATH, using = "//span[contains(normalize-space(),'Wireless Specific Terms in PDF') or contains(normalize-space(),'conditions spécifiques au sans-fil en PDF')]")
	public WebElement pdfWirelessTerms;

	@FindAll({@FindBy(how = How.XPATH, using = "(//span[contains(normalize-space(),'quote')][contains(normalize-space(),'verbally')])[2]/ancestor::span//input[@type='radio']"),
		@FindBy(how = How.XPATH, using = "(//span[contains(normalize-space(),'acceptée verbalement')])[2]/ancestor::span//input[@type='radio']")})
	public WebElement verbalQuoteAcceptOption;

	@FindBy(how = How.XPATH, using = "//div[@class='info-content']//span[@class='t1' or contains(text(),'Credit')]")
	public WebElement verbalQuoteAcceptCondition;

	@FindAll({@FindBy(how = How.XPATH, using = "(//span[contains(normalize-space(),'need') and contains(normalize-space(),'e-signature')])[2]/ancestor::span//input[@type='radio']"),
			@FindBy(how = How.XPATH, using =  "(//span[contains(normalize-space(),'signature électronique')])[2]/ancestor::span//input[@type='radio']")})
	public WebElement eSignatureOption;

	@FindBy(how = How.XPATH, using = "//div[@class='info-content']//span[@class='t1' or contains(text(),'E-signature') or contains(text(),'signature électronique')]")
	public WebElement eSignatureCondition;	

	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Send') and contains(normalize-space(),'approval')] | //button[contains(normalize-space(),'Envoyer pour approbation')]")
	public WebElement sendForApprovalButton;

	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Place') and contains(normalize-space(),'Order')] | //button[contains(normalize-space(),'Passer une commande')]")
	public WebElement placeOrderButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button//span[normalize-space()='Done' or normalize-space()='fini']"),
		@FindBy(how = How.XPATH, using = "//button[normalize-space()='Done']") })
	public WebElement doneButton;	

	@FindBy(how = How.XPATH, using = "//button[(normalize-space()='Edit' or normalize-space()='Modifier') and contains(@class,'button b1')]")
	public WebElement editShippingAddressButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'heading') and contains(normalize-space(),'address') and contains(normalize-space(),'Shipping') and contains(normalize-space(),'Edit')]")
	public WebElement shippingAddressHeader;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'heading') and contains(normalize-space(),'Edit') and  contains(normalize-space(),'shipping')]")
	public WebElement shippingContactHeader;	

	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key=\"Street\"]")
	public WebElement streetInput;

	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key=\"City\"]")
	public WebElement cityInput;

	@FindBy(how = How.XPATH, using = "//label[@aria-label=\"Province\"]/ancestor::div[@role=\"none\"]//input")
	public WebElement stateDropdown1;
	
	@FindBy(how = How.XPATH, using = "//li[@class=\"nds-listbox__item\"]//div[@role='option']")
	public List<WebElement> dropdownOptions;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key=\"PostalCode\"]")
	public WebElement postalCodeInput;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//button//span[normalize-space()='Save' or normalize-space()='Enregistrer']"),
		@FindBy(how = How.XPATH, using = "//span[normalize-space()='Save']")})
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//div[contains(normalize-space(),'Shipping') and contains(normalize-space(),'address') or contains(normalize-space(),'Adresse de livraison')]/ancestor::div[contains(@class,'serviceAddress')]")
	public WebElement shippingAddressText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'contact') and contains(text(),'Shipping') or contains(text(),'Contact responsable de la livraison')]/following-sibling::div")
	public List<WebElement> shippingContactText;
	
	@FindBy(how = How.XPATH, using = "//button[(normalize-space()='Edit' or normalize-space()='Modifier') and contains(@class,'b2')]")
	public WebElement editShippingContactButton;

	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key=\"ContactFirstName\"]//input[@type=\"text\"]")
	public WebElement firstNameInput;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key=\"ContactLastName\"]//input[@type=\"text\"]")
	public WebElement lastNameInput;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-text[@data-omni-key=\"ContactJobTitle\"]//input[@type=\"text\"]")
	public WebElement jobTitleInput;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-email[@data-omni-key=\"ContactEmail\"]//input[@type=\"email\"]")
	public WebElement contactEmailInput;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-telephone[@data-omni-key=\"ContactPhone\"]")
	public WebElement contactPhoneInput;
	
	@FindBy(how = How.XPATH, using = "//label[@aria-label=\"Salutation (Optional)\"]/ancestor::div[@role=\"none\"]//input")
	public WebElement salutationDropdown;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Duplicate Contacts']")
	public WebElement duplicateContacts;
	
	@FindBy(how = How.XPATH, using = "//tr//td[5]/lightning-primitive-cell-factory[@data-label=\"Account Name\"]/span/div/lightning-base-formatted-text")
	public List<WebElement> accountNameOptions;
	
	@FindBy(how = How.XPATH, using = "//tr//th/lightning-primitive-cell-factory[@data-label=\"Contact Name\"]/span/div/lightning-base-formatted-text")
	public List<WebElement> contactNameOptions;
	
	@FindBy(how = How.XPATH, using = "//span[@class=\"slds-radio\"]")
	public List<WebElement> radioOptions;
	
	@FindBy(how = How.XPATH, using = "//button//span[normalize-space()='Save' and contains(@class,'next-button')]")
	public WebElement saveButtonDupContacts;
	
	@FindBy(how = How.XPATH, using = ".//span[contains(@class,'back-button')]")
	public WebElement BacktoPrvs;
	
	@FindBy(how = How.XPATH, using = ".//button[contains(text(),'Cancel') or normalize-space()='Annuler et revenir']")
	public WebElement cancelAndBack;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Business Plan') or contains(text(),'Forfait sans-fil Affaires')]/../../following-sibling::div[2]")
	public WebElement planQuantity;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Wireless' or normalize-space()='Services sans-fil']/following-sibling::div[1]")
	public WebElement termsAndConditionURL;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Download order summary' or normalize-space()='Télécharger le sommaire de la commande']")
	public WebElement downloadOrderSummary;
	
	@FindBy(how = How.XPATH, using = "//button[contains(normalize-space(),'Email Quote Summary') or contains(normalize-space(),'Envoyer le sommaire de la commande par courriel')]")
	public WebElement emailQuoteSummary;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Email successfully sent' or normalize-space()='Courriel envoyé avec succès']")
	public WebElement emailSuccessMessage;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Back to review and place order' or normalize-space()='Retourner à Vérifier et passer la commande']")
	public WebElement BackToReviewOrder;
	
	
	
	
	
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Please enter numeric characters')]")
	public WebElement accntNumErrorMsg;

	@FindBy(how = How.XPATH, using = "(//div/label[text()='Account number']//abbr)[1]")
	public WebElement accntNumReqField;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'not eligible for port in')]")
	public List<WebElement> notEligiblePortIn;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Edit']")
	public WebElement editNumber;
	
	@FindBy(how = How.XPATH, using = "//label//span[normalize-space()='Transfer number']//ancestor::span//input[@type='radio']")
	public List<WebElement> transferNumberRadio;
	
	@FindBy(how = How.XPATH, using = "//div[@class='nds-m-vertical_medium']")
	public List<WebElement> noOfQuantity;
	
	@FindBy(how = How.XPATH, using = "//div[@class='parent-container']//vlocity_cmt-omniscript-block[not(contains(@class,'nds-hide'))]//label[contains(text(),'First Name')]//abbr")
	public List<WebElement> firstNameReqList;
	
	@FindBy(how = How.XPATH, using = "//div[@class='parent-container']//vlocity_cmt-omniscript-block[not(contains(@class,'nds-hide'))]//label[contains(text(),'First Name')]//abbr")
	public List<WebElement> LastNameReqList;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Back to check eligibility')]")
	public WebElement backToCheckEligi;
	
	@FindBy(how = How.XPATH, using = "//div[@class='parent-container']")
	public List<WebElement> parentContainer;
	
	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'First Name')]//abbr)[1]")
	public WebElement firstNameNewNum;
	
	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'Last Name')]//abbr)[1]")
	public WebElement lastNameNewNum;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'current')]//following-sibling::span[@class='title slds-path__title']")
	public WebElement currentStage;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Resume Quote']")
	public WebElement resumeQuote;
	
	@FindBy(how = How.XPATH, using = "//ng-form[@id='ErrorMsg']//h2[contains(text(),'cannot be resumed')]")
	public WebElement errorResumeQuote;
}