package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya,, Date:19/07/2020
 * 
 *         Communities>Home
 *
 */
public class Communities_Home_Objects {

	public Communities_Home_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//img[@title='Rogers Logo']")
	public WebElement rogersLogoLink;

	@FindBy(how = How.XPATH, using = "//div[@class='email-text']")
	public WebElement userEmailText;

	@FindBy(how = How.XPATH, using = "//div[@class='email-text']/parent::a")
	public WebElement userEmailTitle;

	@FindAll({ @FindBy(how = How.XPATH, using = "//a//img[@class='img-individual']"),
			@FindBy(how = How.XPATH, using = "//a//i[contains(@class,'user')]") })
	public WebElement profileImage;

	@FindBy(how = How.XPATH, using = "//a[@title='Sign Out']")
	public WebElement signoutLink;

	@FindBy(how = How.XPATH, using = "//a[@class='my-profile preview']")
	public WebElement profilePreference;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'MyBusiness Hub')]")
	public WebElement myBusinessHubInHeader;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'mbh-pages')]/a[contains(.,'Dashboard')]")
	public WebElement dashboardLink;

	@FindBy(how = How.XPATH, using = "//li[contains(@class,'mbh-pages')]/a[contains(.,'Preferences')]")
	public WebElement preferencesLink;

	@FindBy(how = How.XPATH, using = "//app-contentful-badges//div[contains(@class,'badgeTitle')]")
	public WebElement communitiesBadge;

	@FindBy(how = How.XPATH, using = "//div[.='My Orders and Cases']//parent::a")
	public WebElement communitiesBadgeViewCases;

	@FindBy(how = How.XPATH, using = "//h3[contains(.,'MyBusiness Hub Associated Products & Services')]")
	public WebElement myBusinessHubAssociatedProductsAndServices;

	@FindBy(how = How.XPATH, using = "//span[@class='slider round']")
	public WebElement myBusinessCasesToggleButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Display')]")
	public WebElement toggleDisplayText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Hide')]")
	public WebElement toggleHideText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Add more services')]")
	public WebElement addMoreServicesButton;

	@FindBy(how = How.XPATH, using = "//div[@class='link']")
	public List<WebElement> addMoreServicesLinks;

	@FindBy(how = How.XPATH, using = "//input[@type='checkbox']/following-sibling::label/h3")
	public List<WebElement> addMoreServicesHeaders;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Cancel')]")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Quick Links')]//img")
	public WebElement quickLinks;

	@FindBy(how = How.XPATH, using = "//a[@title='MyBusiness Cases']")
	public WebElement myBusinessCasesLink;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'MyBusiness Hub')]")
	public WebElement myBusinessHubLink;

	@FindBy(how = How.XPATH, using = "//div[@class='footer-links']//a[contains(.,'Contact Us')]")
	public WebElement footerContactUsLink;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'bottom-footer')]/div/div/div[contains(.,'©') and contains(.,'2022') and contains(.,'Rogers Communication')]")
	public WebElement footer2022RogersCommunication;

	@FindBy(how = How.XPATH, using = "//div[@class='footer-links']//a[contains(.,'Privacy Policy')]")
	public WebElement footerPrivacyPolicyLink;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Privacy, CRTC & Consumer Codes')]")
	public WebElement privacyCRTConsumerCodesHeader;

	@FindBy(how = How.XPATH, using = "//div[@class='footer-links']//a[contains(.,'Terms and Conditions')]")
	public WebElement footerTermsAndConditionsLink;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Terms & Conditions')]")
	public WebElement supportTermsAndConditionsHeader;

	@FindBy(how = How.XPATH, using = "//div[@class='footer-links']//a[contains(.,'FAQs')]")
	public WebElement footerFAQsLink;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'FAQs')]")
	public WebElement faqsHeader;

	@FindBy(how = How.XPATH, using = "//a[@class='footer-language-toggle']")
	public WebElement footerLanguageToggle;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'bottom-footer')]")
	public WebElement footerLine;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'My Orders and Cases')]")
	public WebElement myOrderAndCasesText;

	@FindBy(how = How.XPATH, using = "//img[@class='img-individual']/parent::a//following::i/div/img")
	public WebElement signOutImg;
	
	@FindBy(xpath = "//*[contains(text(),'My Orders and Cases')]")
	public WebElement orderAndCaseHeader;
	
	@FindBy(xpath = "//*[contains(text(),'Order Summary') or contains(text(),'Sommaire des commandes')]")
	public WebElement orderSummaryHeader;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//vlocity_cmt-data-table//a[@data-field='OrderNumber']")
	public WebElement orderNumberField;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//vlocity_cmt-data-table//a[@data-field='SubmissionDate']")
	public WebElement submissionDateField;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//vlocity_cmt-data-table//a[@data-field='Status' or @data-field='StatusFr']")
	public WebElement statusField;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//vlocity_cmt-data-table//a[@data-field='ServiceAddressDevices']")
	public WebElement serviceAddressDevicesField;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//vlocity_cmt-data-table//a[@data-field='Products']")
	public WebElement productsField;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//button[@title='Next']//parent::c-button")
	public WebElement nextPageButton;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//button[@title='Next']")
	public WebElement nextPage;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//button[@title='Previous']//parent::c-button")
	public WebElement previousPageButton;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//button[@title='Previous']")
	public WebElement previousPage;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//input")
	public WebElement searchInputBox;
	
	@FindBy(xpath = "//*[contains(text(),'Order Summary')]/ancestor::c-dgtl-custom-search-bar//span")
	public WebElement totalOrders;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//*[contains(@class,'data-table-body')]//*[@data-field-name='OrderNumber']//a")
	public List<WebElement> orderNumberList;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//*[contains(@class,'data-table-body')]//*[@data-field-name='OrderNumber']//img//parent::a")
	public List<WebElement> orderNumberSubOrderLink;
	
	@FindBy(xpath = "//*[@data-field-name='OrderNumber']//a//img//ancestor::div[@role='row']//*[@data-field-name='ServiceAddressDevices']/div")
	public List<WebElement> orderNumberSubOrderCorresServiceAddress;
	
	@FindBy(xpath = "//c-dgtl-orders-card-lwc//*[contains(@class,'data-table-body')]//*[@data-field-name='ServiceAddressDevices']/div")
	public List<WebElement> serviceAddressList;
	
	@FindBy(xpath = "//*[@role='table']//div[contains(@class,'table-body')]/div")
	public List<WebElement> listChildOrder;
	
	@FindBy(xpath = "//*[@role='table']//div[contains(@class,'table-body')]//a")
	public WebElement backToOrderSummary;
	
	@FindBy(xpath = "//c-dgtl-custom-search-bar//input")
	public WebElement searchBoxChildOrder;
	
	@FindBy(xpath = "//*[contains(@class,'data-table-body')]//*[@data-field-name='OrderNumber']//a")
	public List<WebElement> childOrderNumber;
	
	@FindBy(xpath = "//*[contains(@class,'data-table-body')]//*[@data-field-name='ServiceAddress']/div")
	public List<WebElement> serviceAddresschildOrder;
	
	@FindBy(xpath = "//*[contains(@class,'footer-language-toggle')]")
	public WebElement languageToggle;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Order #')]/following-sibling::p")
	public WebElement childOrder;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Account Name')]/following-sibling::p")
	public WebElement accountName;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Order Start Date')]/following-sibling::p")
	public WebElement orderStartDate;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Status')]/following-sibling::p")
	public WebElement status;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Monthly Charges')]/following-sibling::p")
	public WebElement monthlyFees;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'One Time Charges')]/following-sibling::p")
	public WebElement oneTimeFees;
	
	@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Customer Name')]/following-sibling::p")
	public WebElement customerName;
	
	@FindAll({@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Product(s)')]/following-sibling::ul/li/p"),
			@FindBy(xpath = "//c-dgtl-case-detail-custom-step//p[contains(text(),'Product(s)')]/following-sibling::ol/div/li")})
	public WebElement productName;
	
	

}
