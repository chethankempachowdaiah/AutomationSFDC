package sfdc.page_objects.partnercommunities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date : 14/05/2021
 * 
 *         Partner Communities Account Details page 
 *
 */
public class PartnerCommunities_AccountDetails_Objects {

	public PartnerCommunities_AccountDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Account Name')]/parent::div/following-sibling::div//span[@class='uiOutputText']")
	public WebElement accountNameValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Status')]/parent::div/following-sibling::div//span/span")
	public WebElement statusValueText;

	@FindBy(how = How.XPATH, using = "//span[@class='test-id__field-label'][contains(text(),'Account Record Type')]/parent::div/following-sibling::div//div/span")
	public List<WebElement> accountRecordTypeValueText;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Accounts')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//td//a")
	public List<WebElement> accountLinkGlobalSearchResult;


}
