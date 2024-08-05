package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Orchestration Item Page
 */
public class SFDC_OrchestrationItem_Objects {

	public SFDC_OrchestrationItem_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div//span[text()='State']//parent::div//following-sibling::div//lightning-formatted-text")
	public List<WebElement> orchestrationStateValueText;

	@FindBy(how = How.XPATH, using = "//div//span[text()='State']//parent::div//following-sibling::div//button")
	public WebElement orchestrationStateEditButton;

	@FindBy(how = How.XPATH, using = "//label[text()='State']//following-sibling::div//input")
	public WebElement selectStateInput;

	@FindBy(how = How.XPATH, using = "//span[@title='Running']")
	public WebElement stateOptionRunning;

	@FindBy(how = How.XPATH, using = "//button[@name='SaveEdit']")
	public WebElement saveButton;

}
