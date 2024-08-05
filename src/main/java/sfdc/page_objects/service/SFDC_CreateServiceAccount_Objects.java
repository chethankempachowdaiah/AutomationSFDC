package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 09/jan/2020
 * 
 *         SFDC Create Service Account
 *
 */
public class SFDC_CreateServiceAccount_Objects {

	public SFDC_CreateServiceAccount_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//p/p[contains(.,'Parent Account')]")
	public WebElement parentAccount;

	@FindBy(how = How.XPATH, using = "//input[@id='ChooseParentAccount']")
	public WebElement service_ChooseParentAccountInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_AccountName']")
	public List<WebElement> serviceAccountNameInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_AccountNumber']")
	public List<WebElement> serviceAccountNumberInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_AccountSource']")
	public List<WebElement> serviceAccountSourceInput;

	@FindBy(how = How.XPATH, using = "//div[@id='CSR_CopyBusinessAddressToServiceAddressFromKnowAccount']")
	public List<WebElement> copyBusinessAddressButton;
	
	@FindBy(how = How.XPATH, using = "//div[@id='CSR_CopyBusinessAddressToService']/p")
	public WebElement copyBusinessAccountAddressButton;
	
	@FindBy(how = How.XPATH, using = "//button[@type='button' and text()='Search for my site']")
	public WebElement searchSiteButton;
	
	@FindBy(how = How.XPATH, using = "//button[@id='CSR_CopyBusinessAddressToServiceAddressFromKnowAccount']")
	public WebElement copyBusinessAccountAddressButtonDuringGBJ;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_ShippingAddress']")
	public List<WebElement> serviceAddressInput;

	@FindBy(how = How.XPATH, using = "//select[@id='CSR_ShippingPremiseType']")
	public List<WebElement> servicePremiseTypeInput;
	
	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'Street No')]//ancestor::div/child::input)[2]")
	public WebElement serviceStreetNoInput;

	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'Street Name')]//ancestor::div/child::input)[2]")
	public WebElement serviceStreetNameInput;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Street Type')]//ancestor::div/child::input")
	public WebElement serviceStreetTypeInput;
	
	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'City')]//ancestor::div/child::input)[2]")
	public WebElement serviceAccCityInput;

	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'Province')]//ancestor::div/child::input)[2]")
	public WebElement serviceAccProvinceInput;
	
	@FindBy(how = How.XPATH, using = "(//label[contains(text(),'Postal Code')]//ancestor::div/child::input)[2]")
	public WebElement serviceAccPostalCodeInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='CSR_ShippingStreet']")
	public List<WebElement> serviceStreetInput;

	@FindBy(how = How.XPATH, using = "//select[@id='CSR_ShippingState']")
	public List<WebElement> serviceProvinceInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_ShippingCity']")
	public List<WebElement> serviceCityInput;

	@FindAll({@FindBy(how = How.XPATH, using = "//select[@id='CSR_ShippingCountry']"),
			 @FindBy(how = How.XPATH, using = "//select[@id='CSR_CountrySelect']")})
	public List<WebElement> serviceCountrySelect;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_ShippingCountry']")
	public List<WebElement> serviceCountryInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CSR_ShippingPostalCode']")
	public List<WebElement> servicePostalCodeInput;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Add') and @role='button']")
	public WebElement serviceAccountAddButton;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//div[@id='CSR_CreateServiceAccount_nextBtn']"),
	@FindBy(how = How.XPATH, using = "//button[@id='CSR_CreateServiceAccount_nextBtn']")})
	public WebElement cSR_CreateServiceAccount_nextBtn;

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public WebElement accessbilityFrame;

	@FindAll({@FindBy(how = How.XPATH, using = "//div[@id='CheckServiceability_nextBtn']/p"),
		@FindBy(how = How.XPATH, using = "//button[@id='CheckServiceability_nextBtn']")})
	public WebElement checkServicabilityNextButton;

	@FindAll({@FindBy(how = How.XPATH, using = "//div[@id='CheckServiceability_prevBtn']/p"),
		@FindBy(how = How.XPATH, using = "//button[@id='CheckServiceability_prevBtn']")})
	public WebElement checkServicabilityPreviousButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CSR_ServiceAccountCreated']")
	public WebElement serviceAccountCreatedMsg;

	@FindBy(how = How.XPATH, using = "//*[@id='CSR_AccountCreationsConfirmation_nextBtn']")
	public WebElement cSR_AccountCreationsConfirmation_nextBtn;

	@FindBy(how = How.XPATH, using = "//input[@id='CA_CreateBillingAccountCheck']")
	public WebElement cA_CreateBillingAccountCheck;

	@FindBy(how = How.XPATH, using = "//div[@id='CA_ConfirmatioinStepOfSA_nextBtn']")
	public WebElement cA_ConfirmatioinStepOfSA_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//button[@value='continue' and @type='button']")
	public WebElement confirmButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Save and add site')]/parent::button")
	public WebElement saveAndAddSiteButton;
	
	@FindBy(how = How.XPATH, using = "//iframe[@id='iframe']")
	public WebElement checkServicabilityFrame;

	@FindBy(how = How.XPATH, using = "//input[@name='city']")
	public WebElement serviceLocationCity;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@name='aTypeGroup']"),
	@FindBy(how = How.XPATH, using = "//button[@name='aTypeGroup']")})
	public WebElement accessTypeGroup;

	@FindBy(how = How.XPATH, using = "//span[@title='Cable']")
	public WebElement accessTypeCable;
    
	@FindBy(how = How.XPATH, using = "(//input[@type='radio' and @name='fibrelocations' and @value='on']/parent::span/child::label//span)[2]")
	public WebElement accessTypeFibreNew;

	@FindBy(how = How.XPATH, using = "//span[@title='Fibre']")
	public WebElement accessTypeFibre;

	@FindBy(how = How.XPATH, using = "//span[@title='Promo ABA - ON']")
	public WebElement accessTypeABA_On;

	@FindBy(how = How.XPATH, using = "//span[@title='Promo ABA - East']")
	public WebElement accessTypeABA_East;

	@FindBy(how = How.XPATH, using = "//table//tr//td[1]//lightning-primitive-cell-checkbox//label")
	public List<WebElement> serviceLocationCheckBoxAll;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Add Service Locations')]")
	public WebElement addServiceLocationButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Street Address']/parent::div/following-sibling::div//slot//lightning-formatted-text")
	public WebElement premiseStreetAddressText;

	@FindBy(how = How.XPATH, using = "//span[text()='City']/parent::div/following-sibling::div//slot//lightning-formatted-text")
	public WebElement premiseCityText;

	@FindBy(how = How.XPATH, using = "//span[text()='State']/parent::div/following-sibling::div//slot//lightning-formatted-text")
	public WebElement premiseStateText;

	@FindBy(how = How.XPATH, using = "//span[text()='Postal Code']/parent::div/following-sibling::div//slot//lightning-formatted-text")
	public WebElement premisePostalCodeText;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'sectionContent')]//following::span[text()='Last Modified By']/parent::div[contains(@class,'test-id__field-label-container slds-form-element')]//following::lightning-formatted-text")
	public WebElement premiseDetailsServiceLastModified;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'sectionContent')]//following::span[text()='Last Modified By']/parent::div[contains(@class,'test-id__field-label-container slds-form-element')]//following::a/span")
	public WebElement premiseDetailsServiceLastModifiedBy;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'sectionContent')]//following::span[text()='Account Number']/parent::div[contains(@class,'test-id__field-label-container slds-form-element')]/following-sibling::div/span/slot/slot/lightning-formatted-text")
	public WebElement premiseDetailsAccountNumber;

	@FindBy(how = How.XPATH, using = "//div[contains(@id,'sectionContent')]//following::span[text()='Account Source']/parent::div//following::slot/lightning-formatted-text")
	public WebElement premiseDetailsAccountSource;

	@FindBy(how = How.XPATH, using = "//span[text()='Country']/parent::div/following-sibling::div//slot//lightning-formatted-text")
	public WebElement premiseCountryText;

	@FindBy(how = How.XPATH, using = "//span[text()='Premises']/parent::div/following-sibling::div//a/span")
	public WebElement premiseServiceLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@class,'slds-card__header-link slds-truncate slds-show')][text()='Premise Details']//following::span[@class='uiOutputTextArea']")
	public WebElement premiseServiceServiceabilityOutputText;

	@FindBy(how = How.XPATH, using = "//button[@name='vlocity_cmt__Premises__c.Check_Serviceability']")
	public WebElement premiseServiceCheckServicabilityButton;

	@FindBy(how = How.XPATH, using = "//table//tr//td[8]//lightning-base-formatted-text")
	public List<WebElement> premiseServiceAccessType;

	@FindBy(how = How.XPATH, using = "//table//tr//td[7]//lightning-base-formatted-text")
	public List<WebElement> premiseServiceAccessTypeGroup;

	@FindBy(how = How.XPATH, using = "//button[text()='Next']")
	public WebElement premiseServiceAccessNextButton;
	
	@FindBy(how = How.XPATH, using = "//button[text()='Previous']")
	public WebElement premiseServiceAccessPreviousButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Premises Type']/parent::div/following-sibling::div//slot//lightning-formatted-text")
	public WebElement premisesType;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr/th/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")
	public List<WebElement> premiseServiceLocationFloor;

	@FindBy(how = How.XPATH, using = "//span[text()='Close this window']")
	public WebElement addServiceLocationCloseButton;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//div[@id='CSR_AccountCreationsConfirmation_nextBtn']/p"),
	@FindBy(how = How.XPATH, using = "//button[@id='CSR_AccountCreationsConfirmation_nextBtn']")})
	public WebElement cSR_ConfirmedCreateServiceAccount_nextBtn;
	
	@FindBy(how = How.XPATH, using = "//input[@name='suit']")
	public WebElement serviceLocationSuiteFloor;
	
	@FindBy(how = How.XPATH, using = "//span[.='Access Type']")
	public WebElement accessType;
	
	@FindBy(how = How.XPATH, using = "//input[@id='CSR_SelectAddress']")
	public WebElement selectSerAddress;
	
	@FindBy(how = How.XPATH, using = "//div[@ng-click='selectAddress(address)']")
	public WebElement clickAdd;
	
	@FindBy(how = How.XPATH, using = "//vlocity_cmt-omniscript-checkbox[@data-omni-key='OverrideAddress']//ancestor::div/child::input")
	public WebElement chkBox;
	
	@FindBy(how = How.XPATH, using = "//label[contains(.,'Select Address')]/parent::div/parent::div/child::input")
	public WebElement inputSerAddress;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'No results were found')]")
	public WebElement noResults;
	

}
