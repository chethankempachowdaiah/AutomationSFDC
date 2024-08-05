package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Email_Mailinator_Objects {

	public Email_Mailinator_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({
	@FindBy(how = How.XPATH, using = "//input[@aria-label=\"Enter Inbox Name\"]"),
	@FindBy(how = How.XPATH, using = "//input[@aria-label=\"Enter public inbox\"]")})
	public WebElement searchFieldMailinator;

	@FindBy(how = How.XPATH, using = "//*[contains(@id,\"row_autoe200616045588\")]/td[3]")
	public WebElement emailMailinator;


	@FindAll({
		@FindBy(how = How.XPATH, using = "//button[.='GO']"),
		@FindBy(how = How.XPATH, using = "//button[@aria-label=\"Go to public\"]")})
	public WebElement gOMailinator;

	@FindBy(how = How.XPATH, using = "//div[@id=\"msgpane\"]//table//tbody//tr[1]//td[2]")
	public WebElement subjectMailMailinator;

	@FindBy(how = How.XPATH, using = "//div//p[contains(text(),'Case No.')]")
	public WebElement caseIDMailinator;

	@FindBy(how = How.XPATH, using = "//p//b[text()='Subject:']")
	public WebElement subjectMailinator;

	@FindBy(how = How.XPATH, using = "//p//b[text()='Created Date/Time:']")
	public WebElement dateTimeMailinator;

	@FindBy(how = How.XPATH, using = "//iframe[@title='HTML Email Message Body']")
	public WebElement frameEmailBody;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'title')]//preceding-sibling::input")
	public WebElement titleTextBox;

	@FindBy(how = How.XPATH, using = "//div//p[contains(text(),'Track and manage')]")
	public WebElement verbiageCommunityLink;

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Sandbox:')]")
	public WebElement emailSubject; 

	@FindBy(how = How.XPATH, using = "//td[contains(text(),'Agreement for your DocuSign Signature')]")
	public WebElement emailSubjectEsign;

	@FindBy(how = How.XPATH, using = "//span[normalize-space()='REVIEW DOCUMENT']")
	public WebElement reviewDocumentInDocuSign;
	
	@FindBy(how = How.XPATH, using = "//label[@for='disclosureAccepted']")
	public WebElement disclosureCheckbox;
	
	@FindBy(how = How.XPATH, using = "//button[@id='otherActionsButton']")
	public WebElement otherActionsDD;
	
	@FindBy(how = How.XPATH, using = "//div[@id='otherActionsMenu']//button[@role='menuitem'][normalize-space()='Decline to Sign']")
	public WebElement declineSign;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue']")
	public WebElement continueButton;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Start']")
	public WebElement startDocuSign;
			
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Sign'] or //span[normalize-space()='NEXT']")
	public WebElement preSignDocuSign;
	
	@FindBy(how = How.XPATH, using = "//i[@class='sign-arrow']")
	public WebElement signDocuSign;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Sign Here')]//parent::span//preceding-sibling::div/div/div/following-sibling::i[@class='sign-arrow']")
	public List<WebElement> signDocuSignHere;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Next']")
	public WebElement signNextButton;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Adopt and Sign']")
	public WebElement adoptAndSign;
	
	@FindBy(how = How.XPATH, using = "//input[@type='text'][contains(@class,'main-text')]")
	public WebElement enterTitle;
	
	@FindBy(how = How.XPATH, using = "//button[@id='action-bar-btn-finish']")
	public WebElement finishButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'save-a-copy-done-content')]//following::button[normalize-space()='Continue']")
	public WebElement finalContinueButton;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'You’ve finished signing!')]")
	public WebElement finishSigningText;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-lg btn-minor']")
	public WebElement noThanksButton;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Back to Inbox')]/parent::div/parent::div/following-sibling::div/div/div/div[contains(text(),'To')]/following-sibling::div")
	public WebElement toReceiverID;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Back to Inbox')]/parent::div/parent::div/following-sibling::div/div/div/div[contains(text(),'From')]/following-sibling::div")
	public WebElement fromReceiverID;

	@FindBy(how = How.XPATH, using ="/html/body/div/div[1]/div[1]/p[2]")
	public WebElement paragraphInMailinatorEmail;
	
	@FindBy(how = How.XPATH, using ="/html/body")
	public WebElement paragraphInMailinatorEmailSC;
	
	@FindBy(how = How.XPATH, using ="//button[@class='olv-button olv-ignore-transform css-h0jnig']")
	public WebElement declineContinue;
	
	@FindBy(how = How.XPATH, using ="//*[@id=\"decline-to-sign-modal-content\"]/div[3]/button[1]")
	public WebElement declineContinueReason;
	
	@FindBy(how = How.XPATH, using ="(//*[@class ='SVGInline-svg'])[3]")
	public WebElement pdfDownloadButton;
	
	@FindBy(how = How.XPATH, using ="(//button[@tab-type='FullName' and contains(@data-qa,'Full')])[1]")
	public WebElement nameButtonDigitalSignMailinatorEmail;
	
	@FindBy(how = How.XPATH, using ="//span[contains(text(),'2 of ')]//ancestor::div[@class='page page-loaded']")

	public WebElement agreementPageInDigitalSignMailinator;
	
	@FindBy(how = How.XPATH, using ="(//button[@tab-type='DateSigned' and contains(@data-qa,'DateSigned')])[1]")
	public WebElement dateButtonDigitalSignMailinatorEmail;
	
	@FindBy(how = How.XPATH, using ="(//button[@tab-type='Title' and contains(@data-qa,'Title')])[1]")
	public WebElement titleButtonDigitalSignMailinatorEmail;
	
	@FindBy(how = How.XPATH, using ="//button[@title='Download']")
	public WebElement downloadDocuSign;
	
	@FindBy(how = How.XPATH, using ="//span[text()='Combined PDF']")
	public WebElement combinedPDFDocuSign;
	
	@FindBy(how = How.XPATH, using ="//button//span[contains(text(),'Continue')]")
	public WebElement declineContinueButton;
	
	@FindBy(how = How.XPATH, using ="//textarea[contains(@name,'reason')]")
	public WebElement declineReasonText;
	
	@FindBy(how = How.XPATH, using ="//button[@type='submit' and contains(text(),'Decline to Sign')]")
	public WebElement declineSubmitButton;
	
	@FindBy(how = How.XPATH, using ="//h1[contains(text(),'You Declined to Sign')]")
	public WebElement declineSubmitMsgText;
	
	
	
	
	
}
