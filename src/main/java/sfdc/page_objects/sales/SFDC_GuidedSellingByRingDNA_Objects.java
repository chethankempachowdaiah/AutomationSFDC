package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * 			 Date 08/Sep/2021
 * 
 *         Guided Selling By RingDNA Objects
 * 
 * 
 *
 */
public class SFDC_GuidedSellingByRingDNA_Objects {

	public SFDC_GuidedSellingByRingDNA_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='Contacts']/span[.='Contacts']")
	public WebElement contacts;
	
	@FindBy(how = How.XPATH, using = "//*[@title='Select a List View']")
	public WebElement listViewIcon;

	@FindAll({@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Contacts')]"),
		      @FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'Tous les contacts')]")})
	public WebElement allContactsOption;
	
	@FindBy(how = How.XPATH, using = "//tbody//tr//th[1]//a[@data-refid='recordId']")
	public List<WebElement> contactsAllRecords;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Contacts')]/ancestor::div[contains(@class,'listViewManager')]//tbody//th//a")
	public WebElement firstContactInList;
	
	@FindBy(how = How.XPATH, using = "//span[.='Guided Selling Fields']")
	public List <WebElement> guidedSellingFields;
	
	@FindBy(how = How.XPATH, using = "//a[.='Details']")
	public List<WebElement> details;
	
	
	@FindBy(how = How.XPATH, using = "//a[@title='Leads']/span[.='Leads']")
	public WebElement leads;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Open Leads')]")
	public WebElement allLeadsOption;
	
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Lead')]/ancestor::div[contains(@class,'listViewManager')]//tbody//th//a")
	public WebElement firstLeadInList;
	
	
	@FindBy(how = How.XPATH, using = "//span[.='Number Of Deferred Sequence Actions']")
	public WebElement noDefered;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//span[.='Sequence Id']"),
	      @FindBy(how = How.XPATH, using = "//span[.='Sequence ID']")})
	public WebElement sequenceId;
	
	@FindBy(how = How.XPATH, using = "//span[.='Number Of Performed Sequence Actions']")
	public WebElement noPferformedSeq;
	
	@FindBy(how = How.XPATH, using = "//span[.='isActivated']")
	public WebElement isActivated;
	
	@FindBy(how = How.XPATH, using = "//span[.='Number Of Sequence Emails to Reply']")
	public WebElement noSeqEmail;
	
	@FindBy(how = How.XPATH, using = "//span[.='EntranceCriteria Matched Date']")
	public WebElement entrance; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Number Of Sequence Emails to Opened']")
	public WebElement noSeqOpn; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Opened Sequence Email']")
	public WebElement openSeq; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Number Of Sequence Emails Sent']")
	public WebElement noSeqSent; 

	@FindBy(how = How.XPATH, using = "//span[.='SequencePerformed']")
	public WebElement seqPer; 
	
	@FindBy(how = How.XPATH, using = "//span[.='SequenceAssignmentError']")
	public WebElement seqErr; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Replied to Sequence Email']")
	public WebElement repSeq; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Sequence Type']")
	public WebElement seqType; 
	
	@FindBy(how = How.XPATH, using = "//span[.='Priority']")
	public WebElement priority; 
	
	@FindBy(how = How.XPATH, using = "//span[.='SMS Opt Out']")
	public WebElement sMSOptOut;
	
	@FindBy(how = How.XPATH, using = "//span[.='Call Attempts']")
	public WebElement callAttempts;
	
	@FindBy(how = How.XPATH, using = "//span[.='Days Since Last Attempt (Call or Email)']")
	public WebElement daysSinceLastAttempt;
	
	@FindBy(how = How.XPATH, using = "//span[.='Email Attempts']")
	public WebElement emailAttempts;
	
	@FindBy(how = How.XPATH, using = "//span[.='First Inbound Call']")
	public WebElement firstInboundCall;
	
	@FindBy(how = How.XPATH, using = "//span[.='First Inbound Message']")
	public WebElement firstInboundMessage;
	
	@FindBy(how = How.XPATH, using = "//span[.='First Outbound Call']")
	public WebElement firstOutboundCall;
	
	@FindBy(how = How.XPATH, using = "//span[.='First Outbound Message']")
	public WebElement firstOutboundMessage;
	
	@FindBy(how = How.XPATH, using = "//span[.='Last Email Attempt']")
	public WebElement lastEmailAttempt;
	
	@FindBy(how = How.XPATH, using = "//span[.='Last Inbound Call']")
	public WebElement lastInboundCall;
	
	@FindBy(how = How.XPATH, using = "//span[.='Last Inbound Message']")
	public WebElement lastInboundMessage;
	
	@FindBy(how = How.XPATH, using = "//span[.='Last Outbound Call']")
	public WebElement lastOutboundCall;
	
	@FindBy(how = How.XPATH, using = "//span[.='Last Outbound Message']")
	public WebElement lastOutboundMessage;
	
	@FindBy(how = How.XPATH, using = "//span[.='Message Attempts']")
	public WebElement messageAttempts;
	
	@FindBy(how = How.XPATH, using = "//span[.='Response Type']")
	public WebElement responseType;
	
	@FindBy(how = How.XPATH, using = "//span[.='RingDNA Context']")
	public WebElement ringDNAContext;
	
	@FindBy(how = How.XPATH, using = "//span[.='Time Since Last Call Attempt (Days)']")
	public WebElement timeSinceLastCallAttemptDays;
	
	@FindBy(how = How.XPATH, using = "//span[.='Time Since Last Call Attempt (Minutes)']")
	public WebElement timeSinceLastCallAttemptMinutes;
	
	
	@FindBy(how = How.XPATH, using = "//span[.='Time Since Last Email Attempt (Days)']")
	public WebElement timeSinceLastEmailAttemptDays;
	
	
	@FindBy(how = How.XPATH, using = "//span[.='Time Since Last Email Attempt (Minutes)']")
	public WebElement timeSinceLastEmailAttemptMinutes;
	
	@FindBy(how = How.XPATH, using = "//span[.='Time Since Last Message Attempt (Days)']")
	public WebElement timeSinceLastMessageAttemptDays;
	
	@FindBy(how = How.XPATH, using = "//span[.='Time Since Last Message Attempt (Mins)']")
	public WebElement timeSinceLastMessageAttemptMins;
	
	@FindBy(how = How.XPATH, using = "//span[.='Time to First Dial (Minutes)']")
	public WebElement timetoFirstDialMinutes;
	
	@FindBy(how = How.XPATH, using = "//span[.='Time to First Response (Minutes)']")
	public WebElement timetoFirstResponseMinutes;
	
	
	@FindBy(how = How.XPATH, using = "//span[.='RingDNA Call Detail Record']")
	public WebElement ringDNACallDetailRecord;
	
	@FindBy(how = How.XPATH, using = "//span[.='Show more actions']")
	public WebElement showMoreActions;
	
	@FindBy(how = How.XPATH, using = "//span[.='Close this window']")
	public WebElement closeLogSalesWindow;
	
	@FindBy(how = How.XPATH, using = "//label/span[.='Comments']")
	public List <WebElement> comments;
	
	@FindBy(how = How.XPATH, using = "//div[@class='uiMenu']//ancestor::a[.='Open']")
	public WebElement dropDownStatus;
	
	@FindBy(how = How.XPATH, using = "//a[@title='New Task']")
	public WebElement newTask;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Sent']")
	public WebElement sent;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Delivered']")
	public WebElement delivered;
		
	@FindBy(how = How.XPATH, using = "//a[@title='Received']")
	public WebElement received;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Undelivered']")
	public WebElement undelivered; 
	
	@FindBy(how = How.XPATH, using = "//a[@title='Queued']")
	public WebElement queued;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@class='forceChangeRecordTypeFooter']//following-sibling::button"),
	      @FindBy(how = How.XPATH, using = "//span[.='Next']//ancestor::button")})
	public List <WebElement> next;
		
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'sectionContent')]")
	public List <WebElement> gsbFrame;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Add to Sequence']")
	public WebElement addToSequenceButton; 
	
	@FindBy(how = How.XPATH, using = "//a[@title='Remove From Sequence']")
	public WebElement removeFromSequenceButton; 
	
	@FindBy(how = How.XPATH, using = "//h1[.='All Actions']")
	public List <WebElement> allActions; 
	
	@FindBy(xpath = "//a[@title='Actions']/span[.='Actions']")
	public WebElement actions;
	
	@FindBy(xpath = "//a[@title='Sequence Settings']/span[.='Sequence Settings']")
	public WebElement seqSetting;
	
	@FindBy(xpath = "//h1[.='Guided Selling Settings']")
	public List <WebElement> guidedSellingSet;
	
	@FindBy(xpath = "//button[.='Grant All Permission']")
	public List <WebElement> grantAllPer;
}

