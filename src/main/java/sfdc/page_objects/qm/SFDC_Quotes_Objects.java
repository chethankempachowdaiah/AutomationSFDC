package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Quote page objects
 */
public class SFDC_Quotes_Objects {

	public SFDC_Quotes_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search this list...']")
	public WebElement searchQuoteInput;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Quotes')]")
	public WebElement allQuotesOption;

	@FindBy(how = How.XPATH, using = "//tbody//tr//td//span//a[@data-refid='recordId']")
	public List<WebElement> quotesAllRecords;

	@FindAll({
		@FindBy(xpath = "//input[contains(@placeholder,'Search Quotes and more') or contains(@placeholder,'Recherchez des Devis et plus')]"),
		@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search...')]"),
		@FindBy(how = How.XPATH, using = "//input[contains(@title,'Search Quotes and more')]")})
	
	public WebElement globalQuoteSearch;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Quote') or contains(.,'Devis')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> quoteGlobalSearchResult;
	


}
