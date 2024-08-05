package sfdc.page_objects.partnercommunities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date 14/05/2021
 * 
 *         Partner Comm Opportunity Details Objects
 * 
 * 
 *
 */
public class PartnerCommunities_OpportunityDetails_Objects {

	public PartnerCommunities_OpportunityDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='test-id__field-label'][contains(text(),'Opportunity Name')]/parent::div/following-sibling::div//span[@class='uiOutputText']")
	public WebElement opportunityDetailsNameValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(.,'Account Name')]/parent::div/following-sibling::div//span//a")
	public WebElement opportunityDetailsAccountNameValueText;

	@FindBy(xpath = "//span[@class='test-id__field-label'][starts-with(text(),'Type')]/parent::div/following-sibling::div//span/span")
	public WebElement opportunityDetailsTypeValueText;

}
