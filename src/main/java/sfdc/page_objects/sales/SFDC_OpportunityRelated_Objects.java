package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date 20/Jan/2020
 * 
 *         Opportunity Related Tab Page
 * 
 * 
 */
public class SFDC_OpportunityRelated_Objects {

	public SFDC_OpportunityRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(xpath = "//li[@title='Notes']/following-sibling::li[@title='Related']"),
			@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active lafPageHost')]//following::li[@title='Related']") })
	public WebElement opportunityRelatedTab;

	@FindBy(how = How.XPATH, using = "//div[@class='listViewContent']//th[contains(.,'Quote Number')]//ancestor::thead//following-sibling::tbody//tr//th[1]//a")
	public List<WebElement> quotesQuoteNumberAllRows;

	@FindBy(how = How.XPATH, using = "//span[@title='Contact Roles']//following::ul[contains(@class,'uiAbstractList')]/li//a[@data-refid='recordId']")
	public List<WebElement> contactRoleNumberAllRows;

}
