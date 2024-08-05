package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Opportunities page objects
 */

public class SFDC_Opportunities_Objects {

	public SFDC_Opportunities_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'My Opportunities')]")
	public WebElement myOpportunitiesOption;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'virtualAutocompleteOptionText')][contains(text(),'All Opportunities')]")
	public WebElement allOpportunitiesOption;

	@FindBy(how = How.XPATH, using = "//tbody//tr//th[1]//a[@data-refid='recordId']")
	public List<WebElement> opportunitiesAllRecords;
	
	@FindBy(how = How.XPATH, using = "//span[@title='Stage']//ancestor::table//tbody//tr//th[1]//a[@data-refid='recordId']")
	public List<WebElement> opportunitiesAllRecordsInViewAllList;
	
	@FindBy(how = How.XPATH, using = "//span[@class=' virtualAutocompleteOptionText'][contains(.,'CBF Opportunities')]")
	public List<WebElement> cbfOpportunitiesOption;

}
