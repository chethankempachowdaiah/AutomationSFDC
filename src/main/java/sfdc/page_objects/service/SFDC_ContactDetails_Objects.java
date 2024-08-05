package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.sfdc.data.InputData;

/**
 * @author Priyanka.Acharya, Date : 27/April/2020
 * 
 *         SFDC Contact Details page
 */
public class SFDC_ContactDetails_Objects {

	public SFDC_ContactDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@id='detailTab__item']")
	public List<WebElement> detailsTab;

	@FindBy(how = How.XPATH, using = "//a[@id='relatedListsTab__item']")
	public List<WebElement> relatedTab;
		
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Related Accounts')]//ancestor::div//following-sibling::div//div[@class='listItemBody withActions']//a")
	public WebElement serviceAccountInRelatedAccount;
		
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Related List Quick Links')]/following::span[contains(text(),'R4B Quotes Approval')]/parent::slot/parent::a")
	public WebElement r4bQuotesApprovalIcon;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='R4B Quotes Approval']/following::tbody//th//a")
	public WebElement approvalId;
	
	@FindBy(how = How.XPATH, using = "//lightning-base-combobox-item[@data-value='Approved']")
	public WebElement approvedoption;
		
	@FindBy(how = How.XPATH, using = "//h2//span[contains(@title,'Cases')]/parent::a")
	public WebElement casesIcon;
	
	@FindBy(how = How.XPATH, using = "//h2//span[contains(@title,'Cases')]/following::a")
	public List<WebElement> fraudCaseNumber;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@title,'Related Accounts')]")
	public WebElement relatedAccounts;
		
	@FindBy(how = How.XPATH, using = "//div[@title='Manage Relationships']/following::div[@title='Roles:']/following-sibling::div//span")
	public WebElement rolesAdmin;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ContactId')]/following::span")
	public List<WebElement> contactidValue;
	
	@FindBy(how = How.XPATH, using = "//span[@id='window']")
	public WebElement useridValue;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Edit Approval Status']")
	public WebElement editApprovalStatus;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Approval Status')]/following::div")
	public List<WebElement> approvalStatusBox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Related Accounts')]//ancestor::div//following-sibling::div//div[@class='listItemBody withActions']//ul//li[2]//div[@title='Roles:']//following-sibling::div//span")
	public WebElement roleSiteContactText;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Contact Record Type')]//following-sibling::p//span")
	public WebElement contactRecordTypeText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'  and starts-with(.,'Name')]/parent::div/following-sibling::div//lightning-formatted-name")
	public WebElement contactNameText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Contact Owner')]/parent::div/following-sibling::div//force-hoverable-link//a")
	public WebElement contactOwnerText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Account Name')]/parent::div/following-sibling::div//force-hoverable-link//a/slot/slot/span")
	public WebElement accountNameText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Title')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement contactTitle;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Language Preference')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement languagePreferenceText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Status')]/parent::div/following-sibling::div//lightning-formatted-text")
	public WebElement contactStatusText_ForServiceConsole;
	
    @FindAll({
	@FindBy(how = How.XPATH, using = "(//span[@class='test-id__field-label'][contains(text(),'Status')]/parent::div/following-sibling::div//lightning-formatted-text)[3]"),
	@FindBy(how = How.XPATH, using = "(//span[@class='test-id__field-label'][contains(text(),'Status')]/parent::div/following-sibling::div//lightning-formatted-text)[2]")})
	public WebElement contactStatusText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Phone')]/parent::div/following-sibling::div//a")
	public WebElement contactPhoneText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Email')]/parent::div/following-sibling::div//a")
	public WebElement contactEmailText;

	@FindBy(how = How.XPATH, using = "//div[@title='Add Relationship']")
	public WebElement addRelationShipButton;

	@FindBy(how = How.XPATH, using = "//input[@title='Search Accounts']")
	public WebElement searchAccountsInput;

	@FindBy(how = How.XPATH, using = "//tbody//tr[1]/td[1]//a")
	public WebElement searchedAccountName;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Available')]/div/ul[@role='listbox']/li/div/span/span")
	public List<WebElement> availableRoleList;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Chosen')]/div/ul[@role='listbox']/li/div/span/span")
	public WebElement chosenRole;

	@FindBy(how = How.XPATH, using = "//button[@title='Move selection to Chosen']")
	public WebElement moveSelectionToChosenButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Account Contact Relationship')]")
	public WebElement accountContactRelationCreatedMsg;

	@FindBy(how = How.XPATH, using = "//header[contains(.,'Related Accounts')]/parent::div/following-sibling::div//a[@data-refid='recordId']")
	public List<WebElement> accountsInRelatedAccounts;

	@FindBy(how = How.XPATH, using = "//div[@title='Direct:']/following-sibling::div//span//img")
	public List<WebElement> directCheckboxes;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Direct')]/following::img")
	public List<WebElement> checkBoxes;
			
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Related Accounts')]/following::span[@title='Account Name']/following::tr//th//a")
	public List<WebElement> accountNames;

	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Related Accounts')]/following::span[@title='Account Name']/following::tr//td[11]/descendant::a")
	public WebElement actionsButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'End Date')]/following::ul[@class='scrollable']//li//a")
	public List<WebElement> removeRelationship;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Remove Account Contact')]/following::span[contains(text(),'Remove Relationship')]")
	public WebElement removeRelationshipWindow;
	
	@FindBy(how = How.XPATH, using = "//div[@title='Roles:']/following-sibling::div//span")
	public List<WebElement> rolesValue;

	@FindBy(how = How.XPATH, using = "//header[contains(.,'Related Accounts')]/parent::div/following-sibling::div//lightning-icon")
	public List<WebElement> relatedAccountShowMoreActions;

	@FindBy(how = How.XPATH, using = "//li[@role='presentation']//a[contains(@title,'Relationship')]/div")
	public List<WebElement> showMoreActuionsAllRelationpOptions;

	@FindBy(how = How.XPATH, using = "//button[@title='Remove Relationship']")
	public WebElement removeRelationshipButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Account Contact Relationship')]")
	public WebElement accountContactRelationDeletedMsg;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Enable Community User')]")
	public WebElement enableCommunityUserButton;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Troubleshoot User')]")
	public WebElement troubleshootUserButton;

	@FindBy(how = How.XPATH, using = "//h1//div[.='Contact']")
	public WebElement contactSpan;

	@FindBy(how = How.XPATH, using = "//button[@name='Edit']")
	public WebElement contactEditButton;

	@FindBy(how = How.XPATH, using = "(//input[@type='tel'])[1]")
	public WebElement contactEditPhoneEnter;

	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement contactEditSaveButton;

	@FindBy(how = How.XPATH, using = "(//span[text()='Save & New'])")
	public WebElement contactSaveNew;

	@FindBy(how = How.XPATH, using = "(//button[@title='Edit Contact Number']/span)[1]")
	public WebElement editContactNumber;
	
	@FindBy(how = How.XPATH, using = "(//table[@class='detailList']//tbody//tr[2]//td[4]//div)[1]")
	public List<WebElement> contactNumberValue;
	
	@FindBy(how = How.XPATH, using = "//table[@class='detailList']//tbody//tr[2]//td[2]//a")
	public WebElement companyNameValue;

	@FindBy(how = How.XPATH, using = "(//input[@name='vlocity_cmt__ContactNumber__c'])")
	public WebElement enterContactNumber;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Phone']")
	public WebElement editContactPhone;

	@FindBy(how = How.XPATH, using = "//input[@name='Phone']")
	public WebElement inputPhoneTextBox;

	@FindBy(how = How.XPATH, using = "//label[.='Phone']")
	public WebElement enterHomePhoneInEdit;

	@FindBy(how = How.XPATH, using = "//force-form-footer//div//button[@title='Save']/parent::lightning-button/button")
	public WebElement editSave;

	@FindAll({
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Manage Relationships')]"),
	@FindBy(how = How.XPATH, using = "//lightning-button//button[text()='Manage Relationships']")})
	public WebElement manageRelationshipTabButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Add Indirect Roles')]")
	public List<WebElement> addIndirectRoles;
		
	@FindBy(how = How.XPATH, using = "//input[@id='RM_SearchAccount']")
	public WebElement searchAccount;
		
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billing Address')]/following::label[contains(text(),'Roles')]/following::ul//li//label//span")
	public List<WebElement> roles;
	
	@FindBy(how = How.XPATH, using = "//div[@id='RM_ModifyContactRoles_nextBtn']")
	public WebElement nextButton;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'View All')]")
	public WebElement viewAllButton;

	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Modify Roles')]")
	public List<WebElement> modifyRolesTagMessage;

	@FindBy(how = How.XPATH, using = "//span[@class='ng-binding ng-scope']")
	public List<WebElement> directRole;

	@FindBy(how = How.XPATH, using = "(//span[text()='General']/preceding-sibling::span)[2]")
	public WebElement directGeneralRole;

	@FindBy(how = How.XPATH, using = "(//div[@class='vlc-slds-edit-block--cards_images'])")
	public WebElement directImageClick;

	@FindBy(how = How.XPATH, using = "(//div[@class='vlc-slds-edit-block--cards_images'])")
	public List<WebElement> formClick;

	@FindBy(how = How.XPATH, using = "//iframe[contains(@name,'vfFrameId')]")
	public List<WebElement> iframe;

	@FindBy(how = How.XPATH, using = "//iframe[@id='iframeScrtWidget']")
	public WebElement iframeNew;

	@FindBy(how = How.XPATH, using = "//*[@id='Eblk_RM_UpdateDirect']/div/div/form/div/div[2]/div[4]/ng-form/span")
	public WebElement accountNameClick;

	@FindBy(how = How.XPATH, using = "//input[@id='RM_ContactFirstName']")
	public WebElement contactFirstNameTextDisplay;

	@FindBy(how = How.XPATH, using = "//input[@id='RM_ContactLastName']")
	public WebElement contactLastNameTextDisplay;

	@FindBy(how = How.XPATH, using = "//input[@id='RM_ContactEmail']")
	public WebElement contactEmailTextDisplay;

	@FindBy(how = How.XPATH, using = "//strong[contains(text(),'Review Contact Details')]")
	public WebElement reviewContactHeaderText;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Direct Role')]")
	public WebElement directRoleHeaderText;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Add Indirect Roles')]")
	public WebElement inDirectRoleHeaderText;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Add Indirect Roles')]")
	public WebElement indirectRoleTextClick;

	@FindBy(how = How.XPATH, using = "//div[@id='RM_ModifyContactRoles_nextBtn']")
	public WebElement directRoleNextButton;

	@FindBy(how = How.XPATH, using = "//input[@id='RM_SearchAccount']")
	public WebElement indirectSearchAccount;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[7]/label/span[1]")
	public List<WebElement> directGeneralCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='General']/preceding-sibling::input")
	public List<WebElement> checkDirectGeneral;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[1]/label/span[1]")
	public List<WebElement> directAdministratorCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='Administrator']/preceding-sibling::input")
	public List<WebElement> checkDirectAdministrator;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[2]/label/span[1]")
	public List<WebElement> directDeciderCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='Decider']/preceding-sibling::input")
	public List<WebElement> checkDirectDecider;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[3]/label/span[1]")
	public List<WebElement> directCoachCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='Coach/Influencer']/preceding-sibling::input")
	public List<WebElement> checkDirectCoach;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[4]/label/span[1]")
	public List<WebElement> directSigningAuthority;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='Signing Authority']/preceding-sibling::input")
	public List<WebElement> checkDirectSigningAuthorityCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[5]/label/span[1]")
	public List<WebElement> directAssessorCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='Assessor']/preceding-sibling::input")
	public List<WebElement> checkDirectAssessor;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles|0']/div/div[1]/ul/li[6]/label/span[1]")
	public List<WebElement> directEndorserCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_DirectRoles']//following::span[text()='Endorser']/preceding-sibling::input")
	public List<WebElement> checkDirectEndorser;

	@FindBy(how = How.XPATH, using = "//button[text()='Save']")
	public WebElement directSaveButton;

	@FindBy(how = How.XPATH, using = "(//*[contains(text(),'Add Indirect Roles')])[2]")
	public WebElement addIndirectRoleBox;

	@FindBy(how = How.XPATH, using = "//form[@name='loopform']//input[@id='RM_DirectRoles']")
	public List<WebElement> directBusinessCombineRolesCheckBox;

	@FindBy(how = How.XPATH, using = "//form[@name='loopform']//input[@id='RM_DirectRoles']//following-sibling::span[contains(@class,'label')]")
	public List<WebElement> directBusinessCombineRolesText;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_NewRoleService|0']/div/div[1]/ul/li/label/span[1]")
	public List<WebElement> indirectServiceCombineRoleCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_NewRoleService|0']/div/div[1]/ul/li/label/span[2]")
	public List<WebElement> indirectServiceCombineRolesText;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_NewRoleBilling|0']/div/div[1]/ul/li/label/span[1]")
	public List<WebElement> indirectBillingCombineRoleCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_NewRoleBilling|0']/div/div[1]/ul/li/label/span[2]")
	public List<WebElement> indirectBillingCombineRolesText;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_NewRoleBusiness|0']/div/div[1]/ul/li/label/span[1]")
	public List<WebElement> indirectBusinessCombineCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@id='RM_NewRoleBusiness|0']/div/div[1]/ul/li/label/span[2]")
	public List<WebElement> indirectBusinessCombineRolesText;

	@FindBy(how = How.XPATH, using = "//p[text()='Next']")
	public WebElement indirectNextButton;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Click here for more information about managing roles')]")
	public WebElement informationKnowledgeLink;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Click here for more information about managing roles')]")
	public WebElement checkKnowledgeLink;

	@FindBy(how = How.XPATH, using = "//div/input[@id='AccountBillingAddress']")
	public WebElement billingAddressTextBox;

	@FindAll({ 
	@FindBy(xpath = "(//li[@title='Related']/a)[2]"), 
	@FindBy(xpath = "//li[@title='Related']/a"),
	@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//li[@title='Related']/a")})
	public WebElement contactRelatedTabButton;

	@FindBy(how = How.XPATH, using = "//a/span[@title='Contact History']//following::span[text()='View All']")
	public WebElement contactHistViewAllClick;

	@FindBy(how = How.XPATH, using = "//a/span[@title='Related Accounts']//following::span[text()='View All']")
	public List<WebElement> relatedAccountsViewAllClick;

	@FindBy(how = How.XPATH, using = "(//lightning-formatted-phone/a[contains(@href,'tel')])[2]")
	public WebElement conatctDetailsPhoneNoText;

	@FindBy(how = How.XPATH, using = "(//a[@class='emailuiFormattedEmail'])[1]")
	public WebElement conatctDetailsEmail;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr/td[2]/span/span[@class='slds-truncate']")
	public List<WebElement> contactPhoneInViewAllText;

	@FindBy(how = How.XPATH, using = "//table/tbody/tr/td[5]//span[@class='slds-truncate']/span")
	public List<WebElement> contactNewValuePhoneNumText;

	@FindBy(how = How.XPATH, using = "//div[text()='Direct:']//following::span/img[contains(@class,' checked')]")
	public WebElement contactDirectChecked;

	@FindBy(how = How.XPATH, using = "(//*[text()='Roles:']/following::div/span)[1]")
	public WebElement contactRoles;

	@FindBy(how = How.XPATH, using = "//span[text()='Mailing Address']/parent::div/following-sibling::div//lightning-formatted-address//div[@class='slds-truncate']")
	public List<WebElement> contactDetailsMailingAddressText;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//a/span[@title='Account Name']")
	public WebElement contactAccountNameHeaderClick;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by Account Name')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']/span/a")
	public List<WebElement> contactAccountNameText;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by Account Name')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[4]/span/span")
	public List<WebElement> contactAccountRecordTypeText;

    @FindAll({
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by Account Name')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[6]/span/span"),
	@FindBy(how = How.XPATH, using = "//h1[@title='Related Contacts']/following::tr//th[@title='ACR Permissions']/following::td[5]//span//span")})
	public List<WebElement> contactAcrPermissionText;
    
    @FindAll({
    @FindBy(how = How.XPATH, using = "//h1[@title='Related Contacts']/following::tr//th[@title='Contact Name']/following::tr//th//a"),
	@FindBy(how = How.XPATH, using = "//h1[@title='Contacts']/following::tr//th[@title='Contact Name']/following::tr//th//a")})
	public WebElement contactNameIcon;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by Account Name')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[7]/span/span/img")
	public List<WebElement> contactAcrDirectCheckBox;

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by Account Name')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']//following::td[1]/span/span")
	public List<WebElement> contactRelationshipTypeText;

	@FindBy(how = How.XPATH, using = "//button[@title='Show quick filters']/lightning-primitive-icon")
	public WebElement contactQuickFilterClick;

	@FindBy(how = How.XPATH, using = "//button[@title='Hide quick filters']/lightning-primitive-icon")
	public WebElement contactHideFilterClick;

	@FindBy(how = How.XPATH, using = "//input[@name='AccountContactRelation-Account.Name']")
	public WebElement contactQuickFilterEnterAccountName;

	@FindBy(how = How.XPATH, using = "//button[@title='Apply']")
	public WebElement contactQuickFilterApplyButton;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'slds-icon-utility-close slds-icon_container')]/lightning-primitive-icon/*[@data-key='close']")
	public WebElement contactQuickFilterCloseButton;

	@FindBy(how = How.XPATH, using = "//button[@title='Clear All Filters']")
	public WebElement contactQuickFilterClearClick;

	@FindAll ({
		@FindBy(how = How.XPATH, using = "(//a[contains(@title,'Show 2 more actions')]"),
		@FindBy(how = How.XPATH, using = "//*[contains(.,'Related Contacts')]//ancestor::div[contains(@class,'ListDesktop')]//tbody//span[.='Show Actions']")})
	public List<WebElement> contactDropDownArrowClick;

	@FindBy(how = How.XPATH, using = "//li/a[@title='View Relationship']")
	public WebElement contactViewRelationshipOption;

	@FindBy(how = How.XPATH, using = "//li/a[@title='Remove Relationship']")
	public WebElement contactRemoveRelationshipOption;

	@FindBy(how = How.XPATH, using = "(//span[text()='Remove Relationship'][@class=' label bBody'])[1]")
	public WebElement contactRemoveRelationshipButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'outputLookupContainer')]/a")
	public List<WebElement> contactLinkInAccountPage;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'RelationshipType')]//following-sibling::span[contains(.,'Site Contact')]/preceding-sibling::span")
	public WebElement serviceRelationshipSiteContact;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'RelationshipType')]//following-sibling::span[contains(.,'Site Contact')]/preceding-sibling::span/preceding-sibling::input")
	public WebElement checkServiceRelationshipSiteContact;

	@FindBy(how = How.XPATH, using = "//section[@class='tabContent active oneConsoleTab']//div[contains(@class,'iframe-parent slds-template_iframe slds-card')]/iframe[contains(@name,'vfFrameId')]")
	public WebElement deactivateContactIframe;

	@FindBy(how = How.XPATH, using = "//lightning-button/button[text()='Deactivate Contact']")
	public WebElement deactivateContactButton;

	@FindBy(how = How.XPATH, using = "//lightning-button/button[text()='Activate Contact']")
	public WebElement activateContactButton;

	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Deactivate Contact')]")
	public WebElement deactivateContactHeaderMessage;

	@FindBy(how = How.XPATH, using = "//div[@class='slds-form-element__control']/p[contains(text(),' The contact is the only contact with the directly associated business')]")
	public WebElement deactivateValidateMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Please confirm deactivation of the contact')]")
	public WebElement deactivateContactConformation;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Deactivate Contact')]/preceding::span[@class='slds-checkbox--faux']")
	public WebElement deactivateContactCheckBox;
		
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Activate Contact')]/preceding::span[@class='slds-checkbox--faux']")
	public WebElement activateContactCheckBox;

	@FindBy(how = How.XPATH, using = "//ng-form[@id='UC_ContactCommunityStatus']//following::strong")
	public WebElement contactCommunityUserTagText;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Continue')]")
	public WebElement deactivateContinueButton;

	@FindBy(how = How.XPATH, using = "//input[@id='UC_UserIsActiveText']")
	public WebElement contactCommunityUserStatus;

	@FindBy(how = How.XPATH, using = "//div[@class='slds-form-element__control']//span[contains(text(),'The Contact is already Inactive. Please click Next button')]")
	public WebElement alreadyDeactivateMsg;

	@FindBy(how = How.XPATH, using = "//div[@class='slds-form-element__control']//span[contains(text(),'The Contact is already Active. Please click Next button')]")
	public WebElement alreadyActivateMsg;

	@FindBy(how = How.XPATH, using = "//div[@id='UC_UpdateContactUser_nextBtn']/p[text()='Next']")
	public WebElement deactivateNextButtonClick;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Do Not Call')]/preceding-sibling::span[@class='slds-checkbox_faux']")
	public WebElement contactDoNotCallCheckBox;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Email Opt Out')]/preceding-sibling::span[@class='slds-checkbox_faux']")
	public WebElement contactEmailOptOutCheckBox;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Email Opt Out')]/parent::button[@title='Edit Email Opt Out']")
	public WebElement contactEmailOptOutEditClick;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Do Not Call')]/parent::button[@title='Edit Do Not Call']")
	public WebElement contactDoNotCallEditClick;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Do Not Call')]/parent::div")
	public WebElement contactDoNotText;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Email Opt Out')]/parent::div")
	public WebElement contactEmailOptOutText;

	@FindBy(how = How.XPATH, using = "//span[text()='Community Access']")
	public WebElement contactCommunityAccessLabelText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][text()='UserId']//following::a/span[@id='window']")
	public WebElement contactCommunityAccessUserIdValue;

	@FindBy(how = How.XPATH, using = "//ng-form[@id='UC_ContactHasCommunityAccessMessage']/div/p/span")
	public WebElement contactActiveCommunityMsg;

	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement saveEditButton;

	@FindAll({ @FindBy(how = How.XPATH, using = "//span[.='Show more actions']//ancestor::button"),
		@FindBy(how = How.XPATH, using = "//div[.='Contact']//ancestor::div[contains(@class,'header')]//span[.='Show more actions']//parent::button") })
	public WebElement showMoreActionsButton;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[.='Log an Activity']//parent::a"),
			@FindBy(how = How.XPATH, using = "//button[.='Log an Activity']") })
	public WebElement logAnActivityButton;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[.='Log a Sales Activity']//parent::a"),
		@FindBy(how = How.XPATH, using = "//span[.='Log a Sales Activity']"),
		@FindBy(how = How.XPATH, using = "//span[.='Log a Sales Activity']//parent::a//span"),
		@FindBy(how = How.XPATH, using = "//button[.='Log a Sales Activity']") })
	public WebElement logASalesActivityButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Eblk_RM_UpdateDirect']//form//div[contains(@class,'container')]//div//span[@class='ng-binding ng-scope']")
	public List<WebElement> directRoleBusinessAccounts;
	
	@FindBy(how = How.XPATH, using = "//*[@id='RM_UpdateIndirectBusiness']//form//div[contains(@class,'container')]//div//span[@class='ng-binding ng-scope']")
	public List<WebElement> inDirectRoleBusinessAccounts;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//span[.='Account Ownership Transfer']//parent::a"),
		@FindBy(how = How.XPATH, using = "//button[.='Account Ownership Transfer']") })
	public WebElement accountOwnershipTransfer;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper-body']//ancestor::p")
	public WebElement accountOwnershipTransferMessage;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper-body']//ancestor::p")
	public WebElement accountOwnershipTransferMessageManagerFlow;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search People...']")
	public List <WebElement> nameOfNewContactOwner;
	
	@FindBy(how = How.XPATH, using = "//select[@name='Base_Revenue_Quota_Transfer']")
	public List <WebElement> baseRevenueQuotaTransfer;
	
	@FindBy(how = How.XPATH, using = "//input[@name='Acquisition_Margin_TCV_Quota_Transfer']")
	public WebElement acquisitionMarginTCVQuotaTransfer;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Next']")
	public WebElement nextButtonAccOwnerTransfer;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Next')]")
	public WebElement nextButtonAccOwnerTransferManagerFlow;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'NIS')]")
	public WebElement selectAEProfile;
	
	@FindBy(how = How.XPATH, using = "//span[.='Contact Owner']")
	public WebElement contactOwner;
	
	@FindBy(how = How.XPATH, using = "//footer/button[.='Close']")
	public WebElement closeAlert;
	
	@FindBy(xpath = "//lst-related-list-container//*[contains(@class,'forceRelatedListSingleContainer')]//*[contains(text(),'View All')]")
	public WebElement viewAllRelatedAccount;
	
	@FindBy(xpath = "//*[contains(@class,'forceRelatedListDesktop')]//tbody//th//a")
	public List<WebElement> listBusinessAccountName;
	
	@FindBy(xpath = "//*[contains(@class,'forceRelatedListDesktop')]//tbody//td[2]/span/span")
	public List<WebElement> listContactRole;
	
	@FindBy(xpath = "//*[contains(@class,'forceRelatedListDesktop')]//thead/tr/th[6]//a")
	public WebElement accountTypeHeader;
	
	@FindBy(xpath = "//*[contains(@class,'forceRelatedListDesktop')]//tbody//td[5]/span/span")
	public WebElement accountType;
	
	@FindBy(xpath = "//span[.='New Service Account']//parent::a")
	public WebElement newServiceAccButton;
	

}
