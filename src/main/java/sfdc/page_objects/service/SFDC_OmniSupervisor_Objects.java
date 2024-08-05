package sfdc.page_objects.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 04/Nov/2019
 *
 *         SFDC Account page objects
 */
public class SFDC_OmniSupervisor_Objects {

	public SFDC_OmniSupervisor_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='supervisorAgentsTab']/span[contains(.,'Agents')]")
	public WebElement agentsTabText;

	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='supervisorQueuesTab']/span[contains(.,'Queues Backlog')]")
	public WebElement queuesBacklogTabText;

	@FindBy(how = How.XPATH, using = "//a[@data-tab-name='supervisorWorkTab']/span[contains(.,'Assigned Work')]")
	public WebElement assignedWorkTabText;
}
