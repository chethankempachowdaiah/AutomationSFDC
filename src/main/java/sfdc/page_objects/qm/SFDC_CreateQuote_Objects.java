package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ravi Ahirwar, Date : 17/jan/2020
 * 
 *         SFDC Create Quote Page
 *
 */
public class SFDC_CreateQuote_Objects {

	public SFDC_CreateQuote_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create Quote')]")
	public WebElement opportunityDetailCreateQuoteButton;

	@FindAll({@FindBy(how = How.XPATH, using = "//button[contains(.,'Create/Resume Quote')]"),
		@FindBy(how = How.XPATH, using = "//a[contains(.,'Create/Resume Quote')]"),
		@FindBy(xpath = "//button[contains(text(),'Créer ou reprendre un devis')]"),
		@FindBy(how = How.XPATH, using = "//*[text()='Create Quote']"),
		@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create/Resume')]"),
		@FindBy(how = How.XPATH, using = "//button[contains(.,'Create Quote by PBF')]"),
		@FindBy(how = How.XPATH, using = "//a[@title='Create Quote by PBF']"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Create/Resume')]"),
		@FindBy(how = How.XPATH, using = "//a[@title='Create Quote PBF Wireline']")})
	public WebElement opportunityDetailCreateQuotePBFButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Create/Resume Quote')]")
	public WebElement createQuoteThruPBFButton;
		
	@FindBy(how = How.XPATH, using = "//button[text()='Create Quote']")
	public List<WebElement> opportunityDetailCreateQuoteButtons;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//button[contains(.,'Create Quote by Guided Journey') or contains(.,'Create Quote GJ') ]"),
			@FindBy(how = How.XPATH, using = "//div[contains(.,'Create Quote by Guided Journey')]/parent::a") })
	public WebElement opportunityDetailCreateQuoteByGuidedJourneyButton;

	@FindBy(how = How.XPATH, using = "//*[@id='SelectSigningAuthority']//table//tbody//tr/td[2]")
	public List<WebElement> opportunityContactNameList;

	@FindBy(how = How.XPATH, using = "//*[@id='SelectSigningAuthority']//table//tbody//tr/td[1]//input/following-sibling::span")
	public List<WebElement> opportunityContactListCheckbox;

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public WebElement opportunityContactFrame;

	@FindBy(how = How.XPATH, using = "//button[@class='slds-button slds-button_icon-border-filled']//ancestor::li//span[@class='slds-assistive-text' and text()='Show more actions']")
	public List<WebElement> editDropdown;
	
	@FindBy(how = How.XPATH, using = "//runtime_platform_actions-action-renderer[@title='Create Quote']//span[text()='Create Quote']")
	public WebElement CreateQuoteButtoninDropDown;

	@FindBy(how = How.XPATH, using = "//a[@name='Create_Quote_by_Guided_Journey']//span")
	public WebElement createQuoteByGbj;

	@FindBy(how = How.XPATH, using = "//*[@id='SelectSigningAuthority']//table//tbody//tr/td[4]")
	public List<WebElement> opportunityContactRoleTextList;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@id='AccountContactSA_nextBtn']"),
			@FindBy(how = How.XPATH, using = "//button[@id='AccountContactSA_nextBtn']") })
	public WebElement selectPrimarySigningAuthoritynextButton;

	@FindBy(how = How.XPATH, using = "//input[@id='CreateAccount']//following-sibling::span")
	public WebElement createServiceAccountCheckbox;

	@FindBy(how = How.XPATH, using = "//div[@id='CSR_CreateServiceAccount_nextBtn']")
	public WebElement createServiceAccountNextButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//iFrame[@title='accessibility title']")
	public WebElement createServiceAccountFrame;

	@FindBy(how = How.XPATH, using = "//*[@id='GetServiceAccountBlock']//table//tbody//tr/td[2]")
	public List<WebElement> createQuoteSelectServiceAccountNameList;

	@FindBy(how = How.XPATH, using = "//input[@id='search']")
	public WebElement searchAccountInFilter;

	@FindBy(how = How.XPATH, using = "//select[@ng-model='p.Serviceability']")
	public WebElement selectONNetDebug3;

	@FindBy(how = How.XPATH, using = "//*[@id='GetServiceAccountBlock']//table//tbody//tr/td[1]//span[contains(@class,'checkbox')]")
	public List<WebElement> createQuoteSelectServiceAccountCheckBoxList;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::table//tbody[@class='ins']//tr//td[6]"),
			@FindBy(how = How.XPATH, using = "//th[contains(.,'Site Name')]/ancestor::table//tbody//tr/td[6]/div") })
	public List<WebElement> createQuoteServiceAbilityListCable;

	@FindAll({
			@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::table//tbody[@class='ins']//tr//td[6]"),
			@FindBy(how = How.XPATH, using = "//th[contains(.,'Site Name')]/ancestor::table//tbody//tr/td[7]/div") })
	public List<WebElement> createQuoteServiceAbilityListFibre;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Site Name')]/ancestor::table//tbody//tr/td[7]/div")
	public List<WebElement> createQuoteServiceAbilityListCableHoverText;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[@id='GetServiceAccountStep_nextBtn']"),
			@FindBy(how = How.XPATH, using = "//button[@id='GetServiceAccountStep_nextBtn']") })
	public WebElement createQuoteGetServiceAccountStep_nextBtn;

	@FindBy(how = How.XPATH, using = "//div[@id='GetBillingAccountStep_nextBtn']/p")
	public WebElement createQuoteGetBillingAccountStep_nextBtn;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::table//tbody[@class='ins']//tr//td[9]")
	public List<WebElement> createQuoteServiceAbilityPromoABA_On;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::table//tbody[@class='ins']//tr//td[12]")
	public List<WebElement> createQuoteServiceAbilityPromoABA_East;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::table//tbody[@class='ins']//tr//td[7]")
	public List<WebElement> createQuoteServiceAbilityFibre_OnNearNet;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Add new site')]")
	public WebElement addNewSiteButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//*[@id='SelectSigningAuthority']//table//tbody//tr/td[3]"),
		@FindBy(how = How.XPATH, using = "//th[contains(.,'Select')]/ancestor::thead/following-sibling::tbody/tr[1]/td[4]//div") })
	public WebElement emailId;

	@FindBy(how = How.XPATH, using = "//*[@id='SelectSigningAuthority']//table//tbody//tr/td[2]")
	public WebElement nameSignAuth;

	@FindBy(how = How.XPATH, using = "//*[@id='SelectSiteContact']//table//tbody//tr/td[3]")
	public WebElement emailIdSiteContact;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Create/Resume Quote')]")
	public WebElement opportunityDetailCreateQuoteButton_FIT;
}
