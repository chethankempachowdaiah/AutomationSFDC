package sfdc.page_objects.sales;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 10/Jan/2021
 *
 *         SFDC Task Details page objects
 */
public class SFDC_TaskDetails_Objects {

	public SFDC_TaskDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1//div[.='Task']")
	public WebElement taskDetailsHeader;
	
	@FindBy(how = How.XPATH, using = "//h1//div[.='Event']")
	public WebElement eventDetailsHeader;

	@FindBy(how = How.XPATH, using = "//h1//span")
	public WebElement subjectInTaskDetails;

	@FindBy(how = How.XPATH, using = "//span[.='Name']//following-sibling::div//a")
	public WebElement accountNameInTaskDetails;
	
	@FindBy(how = How.XPATH, using = "//span[.='Type']//parent::div//following-sibling::div/span/span")
	public WebElement typeOfTaskInTaskDetails;
	
	@FindBy(how = How.XPATH, using = "//span[.='Solutions Discussed']//parent::div//following-sibling::div/span/span")
	public WebElement solutionsDiscussedInTaskDetails;
	
	@FindBy(how = How.XPATH, using = "//span[.='Activity Sub Type']//parent::div//following-sibling::div/span/span")
	public WebElement interactionTypeInTaskDetails;
	
}
