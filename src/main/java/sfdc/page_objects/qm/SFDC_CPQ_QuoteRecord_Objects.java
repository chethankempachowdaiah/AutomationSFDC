package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 20/jan/2020
 * 
 *         CPQ Review Quote record(Opportunity> Create Quote> Select Contact>
 *         Select service account>Review Quote record)
 *
 */
public class SFDC_CPQ_QuoteRecord_Objects {

	public SFDC_CPQ_QuoteRecord_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Quote Number')]//following-sibling::div//span")
	public List<WebElement> quoteNumberText;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Opportunity Name')]//parent::div//following-sibling::div//a")
	public WebElement opportunityNameLink;

	@FindBy(how = How.XPATH, using = "//a[contains(.,'HConfigure')]")
	public WebElement hConfigureButton;

}
