package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2020
 *
 *         SFDC Contact page objects
 */
public class SFDC_Contacts_Objects {

	public SFDC_Contacts_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Contacts')]"),
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'Tous les contacts')]")})
	public WebElement allContactsOption;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'My Contacts')]")
	public WebElement myContactsOption;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'Recently Viewed Contacts')]")
	public WebElement recentlyViewedContactsOption;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th[1]//a[@data-refid='recordId']")
	public List<WebElement> contactsAllRecords;

	@FindBy(how = How.XPATH, using = "//div[@title='New']")
	public WebElement newContactButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Contacts')]/ancestor::div[contains(@class,'listViewManager')]//tbody//th//a")
	public WebElement firstContactInList;
	
//	@FindBy(how = How.XPATH, using = "//input[contains(@title,'Search Contacts and more')]")
//	public WebElement globalContactSearch;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search Contacts and more')]")
	public WebElement globalContactSearch;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Contacts')]/ancestor::div[contains(@class,'resultsItem')]//tbody//tr//th//a")
	public List<WebElement> contactLinkGlobalSearchResult;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Contacts')]/ancestor::div[contains(@class,'resultsItem')]//a[contains(@class,'emailuiFormattedEmail')]")
	public List<WebElement> contactEmailGlobalSearchResult;
	
}
