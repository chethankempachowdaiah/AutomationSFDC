package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 10/Jan/2021
 *
 *         SFDC Task Creation page objects
 */
public class SFDC_TaskCreation_Objects {

	public SFDC_TaskCreation_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//section[contains(@class,'active')]//div[contains(@class,'slds')]/iframe[contains(@title,'access')]")
	public WebElement createTaskIFrame;
	
	@FindBy(how = How.XPATH, using = "//iframe[contains(@title,'accessibility title')]")
	public WebElement createNewEventIFrame;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//span[.='Create a Task']"),
		@FindBy(how = How.XPATH, using = "//h2//span[.='Log an Activity']")})
	public WebElement createTaskHeader;

	@FindAll({
		@FindBy(how = How.XPATH, using = "(//div[contains(text(),'Type')])[1]//ancestor::div[@class='bBody']//select"),
		@FindBy(how = How.XPATH, using = "//select[@name='typeFromAccountFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='typeFromOpportunityFlow']"),
		@FindBy(how = How.XPATH, using ="//select[@name='typeFromLeadFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='typeFromContactFlow']")})
	public WebElement typeOfTask;
	
	
		@FindBy(how = How.XPATH, using = "//select[@name='Subject_Event']")
		public WebElement subjectEventOfTask;
		
		@FindBy(how = How.XPATH, using = "//span[.='New Event']//ancestor::b")
		public WebElement newEvent;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Description')]")
		public WebElement description;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Start Date Time')]")
		public WebElement startDateTime;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'End Date Time')]")
		public WebElement endDateTime;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Location')]")
		public WebElement location;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Reminder Set')]")
		public WebElement reminderSet;
		
		@FindBy(how = How.XPATH, using = "//input[@name='Reminder_Set_Event']")
		public WebElement reminderSetChkBox;
		
		@FindBy(how = How.XPATH, using = "//a[contains(text(),'Event')]")
		public WebElement eventLink;
		
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Reminder Date/Time')]")
		public WebElement reminderSetTimeDate;
	
		@FindBy(how = How.XPATH, using = "//span[contains(text(),'Reminder Date/Time')]//following::button[@title='Select a date for Date']")
		public WebElement selectDate;
		
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Interaction Type')]//ancestor::div[@class='bBody']//select"),
		@FindBy(how = How.XPATH, using = "//select[@name='activitiSubTypeFromOpportunityFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='activitySubTypeFromContactFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='activitySubTypeFromLeadFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='activitySubTypeFromAccountFlow']")})
	public WebElement interactionTypeOfTask;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Activity Sub Type')]//ancestor::div[@class='bBody']//select")
	public WebElement activitySubTypeOfTask;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Subject')]//ancestor::div[@class='bBody']//select"),
		@FindBy(how = How.XPATH, using = "//select[@name='subjectFromContactFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='subjectFromOpportunityFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='subjectFromLeadFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='subjectFromAccountFlow']")})
	public WebElement subjectOfTask;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Status')]//ancestor::div[@class='bBody']//select"),
		@FindBy(how = How.XPATH, using = "//select[@name='statusFromContactFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='statusFromOpportunityFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='statusFromLeadFlow']"),
		@FindBy(how = How.XPATH, using = "//select[@name='statusFromAccountFlow']")})
	public WebElement statusOfTask;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Related To')]//ancestor::lightning-lookup//input")
	public WebElement relatedToOfTask;
	
	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Name')]//ancestor::lightning-lookup//input")
	public WebElement accountNameOfTask;
	
	@FindBy(how = How.XPATH, using = "//button[.='Next']")
	public WebElement nextButton;
	
	@FindBy(how = How.XPATH, using = "//option[@value='Executive_Brief']")
	public WebElement optionExecutive_Brief;
	
	@FindBy(how = How.XPATH, using = "//button[.='Previous']")
	public WebElement previousButton;
	
	@FindAll({ 
		@FindBy(how = How.XPATH, using = "//select[@name='solutionDiscussedElement']"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Solution Discussed')]//ancestor::div[@class='bBody']//select")})
	public WebElement solutionDiscussedForTask;
	
	@FindAll({ 
		@FindBy(how = How.XPATH, using = "//select[@name='marketingCampaignOptInElement']"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Marketing Campaign Opt-In')]//ancestor::div[@class='bBody']//select")})
	public WebElement marketingCampaignOptInForTask;
	
	@FindAll({ 
		@FindBy(how = How.XPATH, using = "//select[@name='nextStepsElement']"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Next Steps')]//ancestor::div[@class='bBody']//select")})
	public WebElement nextStepsForTask;
	
	@FindBy(how = How.XPATH, using = "//span[.='Due Date']//ancestor::div[contains(@class,'form')]//input")
	public WebElement dueDateForTask;
	
	@FindAll({ 
		@FindBy(how = How.XPATH, using = "//select[@name='Customer_Churn_Risk']"),
		@FindBy(how = How.XPATH, using = "//div[contains(text(),'Customer Churn Risk')]//ancestor::div[@class='bBody']//select")})
	public WebElement customerChurnRiskForTask;
	
	@FindBy(how = How.XPATH, using = "//p[contains(.,'has been created successfully.')]")
	public WebElement taskCreationSuccessMessage;
	
	@FindBy(how = How.XPATH, using = "//button[.='Finish']")
	public WebElement finishButton;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Task')]")
	public WebElement taskLink;
	
	@FindAll({ 
	@FindBy(how = How.XPATH, using = "//div[@id='input-label-1-0']//child::button"),
	@FindBy(how = How.XPATH, using = "//*[@id='input-label-1-0']/lightning-helptext/div/button/lightning-primitive-icon/svg/g/path"),
	@FindBy(how = How.XPATH, using = "//span[.='Help']//ancestor::button")})
	public List <WebElement> helpLinkForTypeOfTask;
	
	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//p/i")
	public List<WebElement> helpTextForTypeOfTask;
	
	@FindBy(how = How.XPATH, using = "//span[.='Help']")
	public WebElement closeHelpBox;
	
	
}
