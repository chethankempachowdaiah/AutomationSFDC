package sfdc.page_objects.sales;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date 20/Jan/2020
 * 
 *         Opportunity Close Information Page(Create Opportunity>create Quote>
 *         Servicability : OffNet>Opportunity Close Information)
 * 
 * 
 */
public class SFDC_OpportunityCloseInfo_Objects {

	public SFDC_OpportunityCloseInfo_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//select[@id='ClosedLostReason']")
	public WebElement closedLostPrimaryReasonDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='ClosedLostSubReason']")
	public WebElement closedLostSecondaryReasonDropdown;

	@FindBy(how = How.XPATH, using = "//select[@id='ClosedLostCompetitors']")
	public WebElement closedLostCompetitorLostToDropdown;

	@FindBy(how = How.XPATH, using = "//div[@id='OpportunityCloseInformation_nextBtn']")
	public WebElement closedLostCloseInformation_nextBtn;

}
