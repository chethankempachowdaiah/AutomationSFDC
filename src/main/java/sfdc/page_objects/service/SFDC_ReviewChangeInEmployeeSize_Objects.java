package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 30/04/2020
 *
 *         SFDC Account > Change Employee Size> Cutsomer Story> Task>Please
 *         review the change in employee size for the account referenced
 */
public class SFDC_ReviewChangeInEmployeeSize_Objects {

	public SFDC_ReviewChangeInEmployeeSize_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[@title='Related To']//following-sibling::div//a[@data-refid='recordId']")
	public List<WebElement> relatedToAccount;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subject')]//parent::div//following-sibling::div//span//span")
	public WebElement subjectValue;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Assigned To')]//parent::div//following-sibling::div//span//span")
	public WebElement assinedToValue;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Comments')]//parent::div//following-sibling::div//span//span")
	public WebElement commentsValue;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Priority')]//parent::div//following-sibling::div//span//span")
	public WebElement priorityValue;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Status')]//parent::div//following-sibling::div//span//span")
	public WebElement statusValue;

}
