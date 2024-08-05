package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 03/FEB/2020
 * 
 *         SFDC Manual Queue>Account Provisioning Queue Details
 *
 */
public class SFDC_ManualQueueQueueDetails_Objects {

	public SFDC_ManualQueueQueueDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//iframe[@title='sessionserver']")
	public WebElement itemsInQueueFrame;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//a[contains(.,'Pick Up')]"),
		@FindBy(how = How.XPATH, using = "//a[contains(.,'Pick up')]")})
	public List<WebElement> actionPickUpAllLinks;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Installation and Activation')]")
	public WebElement Instlttr;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Welcome To Rogers']")
	public WebElement Welcomeletter;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Getting Started')]")
	public WebElement WelcomeletterCable;
	
	@FindBy(how = How.XPATH, using = "//a[@title='Thank You']")
	public WebElement Closureletter;
	

	@FindBy(how = How.XPATH, using = "//span[@title='Filter']/parent::button")
	public WebElement filterButton;

	@FindBy(how = How.XPATH, using = "//*[contains(.,'Order Number') or contains(.,'Order/Fulfilment Request')]/following-sibling::span//input")
	public WebElement orderNumberInput;
//
	@FindBy(how = How.XPATH, using = "//button[contains(.,'Search')]")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//tr[contains(@class,'heading')]//ancestor::thead//following-sibling::tbody//tr//td[6]//a")
	public List<WebElement> orderFulfilmentRequestAllLinks;

	//@FindBy(how = How.XPATH, using = "//tr[contains(@class,'heading')]//ancestor::thead//following-sibling::tbody//tr//td[2]//a/span")
	@FindBy(how = How.XPATH, using = "//tr[contains(@class,'heading')]//ancestor::thead//following-sibling::tbody//tr//td[2]//a")
	public List<WebElement> orderFulfilmentAllNameRequests;

	@FindBy(how = How.XPATH, using = "//tr[contains(@class,'heading')]//ancestor::thead//following-sibling::tbody//tr//td[3]/span/span")
	public List<WebElement> orderStateAllLinks;

	@FindBy(how = How.XPATH, using = "//tr[contains(@class,'heading')]//ancestor::thead//following-sibling::tbody//tr//td[7]/span")
	public List<WebElement> assignedToUserAllLinks;

	@FindBy(how = How.XPATH, using = "//tr[contains(@class,'heading')]//ancestor::thead//following-sibling::tbody//tr[1]//td[7]/span")
	public WebElement assignedToUserLink;
}
