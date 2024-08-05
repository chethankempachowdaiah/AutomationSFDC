package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 23/04/2020
 * 
 *         SFDC Pending Order Report(Manual Queue > Pending Report)
 * 
 *         Report: Orchestration Item with Order Order with Pending Task
 *
 */
public class SFDC_OrderWithPendingTask_Objects {

	public SFDC_OrderWithPendingTask_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div//table//tr/td[1]//a")
	public List<WebElement> ordersAllRows;

	@FindBy(how = How.XPATH, using = "//div//table//tr/td[7]//a")
	public List<WebElement> orchestrationItemAllRows;

}
