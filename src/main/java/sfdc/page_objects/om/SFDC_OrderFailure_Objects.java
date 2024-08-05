package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 28/march/2020
 *
 *         SFDC Order Failure Page (Order>order Activity>Order failure
 *         activity>Order failure page)
 */
public class SFDC_OrderFailure_Objects {

	public SFDC_OrderFailure_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[@title='Related To']//following-sibling::div//a")
	public WebElement relatedToOrderLink;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Subject')]//parent::div//following-sibling::div//span//span")
	public WebElement orderFailureSubject;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Status')]//parent::div//following-sibling::div//span//span")
	public List<WebElement> orderStatusFieldValueText;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Comments')]//parent::div//following-sibling::div//span//span")
	public WebElement commentsValueText;

	@FindBy(how = How.XPATH, using = "//button[@title='Edit Comments']")
	public WebElement editCommentButton;

	@FindBy(how = How.XPATH, using = "//textarea")
	public WebElement commentTextArea;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Status')]//parent::div//following-sibling::div//a")
	public WebElement statusDropdown;

	@FindBy(how = How.XPATH, using = "//li//a[@title='Completed']")
	public WebElement orderStatusComletedOptionLink;

	@FindBy(how = How.XPATH, using = "//button[@title='Save']")
	public WebElement saveButton;

}
