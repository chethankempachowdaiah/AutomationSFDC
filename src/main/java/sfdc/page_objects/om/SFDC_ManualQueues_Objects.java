package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Manual Queues page objects
 */
public class SFDC_ManualQueues_Objects {

	public SFDC_ManualQueues_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All')]")
	public WebElement allOption;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th[1]//a[@data-refid='recordId' and contains(@title,'Queue') or contains(@title, '365')]")
	public List<WebElement> manualQueuesAllRecords;

	@FindBy(how = How.XPATH, using = "//span/a[@title='Delivery Specialist Queue']")
	public WebElement deliverySpecialistQueue;

	@FindBy(how = How.XPATH, using = "//span/a[@title='Service Designer Queue']")
	public WebElement serviceDesignerQueue;

	@FindBy(how = How.XPATH, using = "//span/a[@title='Technical Implementation Specialist Queue']")
	public WebElement techImpSpecQueue;
	
	@FindBy(how = How.XPATH, using = "//span/a[@title='TechM Queue']")
	public WebElement techMQueue;
	
	@FindBy(how = How.XPATH, using = "//span/a[@title='Cable Order Provisioning']")
	public WebElement cableOrderQueue;
	
	@FindBy(how = How.XPATH, using = "//span/a[@title='Cable Error Handling']")
	public WebElement cableErrorHandlingOrderQueue;
	
	@FindBy(how = How.XPATH, using = "//slot[contains(@class,'slds-page-header')]//lightning-formatted-text[text()='Cable Error Handling']")
	public WebElement cableErrorHandlingOrderQueueTextHeader;
	
	@FindBy(how = How.XPATH, using = "//span/a[@title='Office 365']")
	public WebElement office365OrderQueue;

	@FindBy(how = How.XPATH, using = "//span/a[@title='Salesforce Production Support Team']")
	public WebElement salesforceProdSupportcQueue;
}
