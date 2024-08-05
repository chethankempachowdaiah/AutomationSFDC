package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 20/FEB/2020
 * 
 *         Case Related Page(Email to case> Accpet case from omni channel> case
 *         Related page)
 *
 */
public class SFDC_CaseRelated_Objects {

	public SFDC_CaseRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "(//span[@title='SurveyResponses']/parent::a)[1]")
	public WebElement surveyResponsesLink;
	
	@FindBy(how = How.XPATH, using = "//h1[@title='SurveyResponses']/following::tbody//th//a")
	public WebElement surveyResponsesIdLink;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Touchpoint Score')]/parent::div/parent::div/parent::div")
	public WebElement touchPointScore;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Show All')]")
	public WebElement showAllIcon;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Related')]")
	public WebElement caseRelatedTab;
    
	@FindBy(how = How.XPATH, using = "(//a[contains(.,'Related')])[2]")
	public WebElement caseRelatedTabNew;
					
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Email')]//ancestor::header//parent::div//following-sibling::div//h3[contains(@class,'primaryField')]//a")
	public List<WebElement> caseEmailsAllLinks;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Show More')]//preceding-sibling::*")
	public List<WebElement> emailShowMoreActions;

	@FindBy(how = How.XPATH, using = "//ul[contains(@class,'forceActionsContainer')]//a//div[@title='Reply']")
	public WebElement replyEmailButton;

	@FindBy(how = How.XPATH, using = "//iframe[@title='CK Editor Container']")
	public WebElement emailEditorFrame;

	@FindBy(how = How.XPATH, using = "//iframe[@title='Email Body']")
	public WebElement emailBodyFrame;

	@FindBy(how = How.XPATH, using = "//body[@contenteditable='true']")
	public WebElement emailBodyArea;

	@FindBy(how = How.XPATH, using = "//button//span[contains(.,'Send')]")
	public WebElement emailSendButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Email sent.')]")
	public WebElement emailSentMessage;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Search quick text...')]//parent::label//following-sibling::div//input")
	public WebElement searchQuickTextInput;

	@FindBy(how = How.XPATH, using = "//div[@class='listItemName']//mark")
	public List<WebElement> allQuickTextNames;

	@FindBy(how = How.XPATH, using = "//*[@slot='tabs']//span[@title='Case Comments']")
	public WebElement commentsSection;

	@FindBy(how = How.XPATH, using = "//h1[@title='Case Comments']")
	public WebElement commentsHeaderText;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Case Comments')]/ancestor::div[contains(@class,'test-listViewManager')]//th[1]//a")
	public WebElement commentsTableUserName;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Case Comments')]/ancestor::div[contains(@class,'test-listViewManager')]//span[contains(@class,'DateTime')]")
	public WebElement commentsTableCreatedDate;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Case Comments')]/ancestor::div[contains(@class,'test-listViewManager')]//td[4]")
	public WebElement commentsTableCommentsText;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//span[@title='Files']//parent::a"),
		@FindBy(how = How.XPATH, using = "//span[@title='Attachments']//parent::a")})
	public WebElement attachmentsSection;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//h1[@title='Files']"),
		@FindBy(how = How.XPATH, using = "//h1[@title='Attachments']")})
	public WebElement attachmentsHeaderText;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//h1[contains(.,'Files')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'FileTitle')]//span"),
		@FindBy(how = How.XPATH, using = "//h1[contains(.,'Attachments')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'FileTitle')]//span")})
	public WebElement attachmentsTableFirstFileName;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//h1[contains(.,'Files')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'FileTitle')]//ancestor::a"),
		@FindBy(how = How.XPATH, using = "//h1[contains(.,'Attachments')]/ancestor::div[contains(@class,'test-listViewManager')]//*[contains(@class,'FileTitle')]//ancestor::a")})
	public WebElement attachmentsTableFirstFileLink;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//h1[contains(.,'Files')]/ancestor::div[contains(@class,'test-listViewManager')]//tbody//*[@data-key='down']//ancestor::a"),
	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Attachments')]/ancestor::div[contains(@class,'test-listViewManager')]//tbody//*[@data-key='down']//ancestor::a")})
	public WebElement attachmentFileOptions;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Add Files']")
	public WebElement addFilesButton;

	@FindBy(how = How.XPATH, using = "//img[contains(@class,'Share')]//parent::button")
	public WebElement attachmentFileShareButton;

	@FindBy(how = How.XPATH, using = "//*[.='Who Can Access']//ancestor::button")
	public WebElement attachmentWhoCanAccessButton;

	@FindBy(how = How.XPATH, using = "//input[contains(@id,'toggle')]")
	public WebElement attachmentCustomerViewToggle;

	@FindBy(how = How.XPATH, using = "//span[.='Done']//parent::button")
	public WebElement attachmentSharePopUpDone;

	@FindBy(how = How.XPATH, using = "//h2//span[@title='Articles']//parent::a")
	public WebElement articlesSection;

	@FindBy(how = How.XPATH, using = "//h1[@title='Articles']")
	public WebElement articlesHeaderText;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Articles')]/ancestor::div[contains(@class,'test-listViewManager')]//tbody//th//a")
	public WebElement articlesTableFirstArticleLink;

}
