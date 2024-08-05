package sfdc.page_objects.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 20/02/2020
 * 
 *         Omni Channel (Email to case)
 *
 */
public class SFDC_EmailToCase_OmniChannel_Objects {

	public SFDC_EmailToCase_OmniChannel_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Omni-Channel')]")
	public WebElement omniChannelTab;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Change your Omni-Channel status')]//preceding-sibling::*//parent::button")
	public WebElement changeOmniChannelStatusButton;

	@FindBy(how = How.XPATH, using = "//a/span[contains(.,'Available ')]")
	public WebElement availableEmailStatusOption;

	@FindBy(how = How.XPATH, using = "//a/span[contains(.,'Offline')]")
	public WebElement offlineEmailStatusOption;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'Available ')]")
	public WebElement availableEmailCurrentStatus;

	@FindBy(how = How.XPATH, using = "//div/span[contains(.,'Offline')]")
	public WebElement offlineEmailCurrentStatus;

	@FindBy(how = How.XPATH, using = "//button[contains(@title,'Decline Case')]")
	public WebElement declineCaseButton;

	@FindBy(how = How.XPATH, using = "//button[contains(@title,'Accept Case')]")
	public WebElement acceptCaseButton;

	@FindBy(how = How.XPATH, using = "//div[@class='primaryField']//span//span")
	public WebElement caseHeaderText;

}
