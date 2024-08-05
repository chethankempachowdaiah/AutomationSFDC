package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 29/MAY/2020
 * 
 *         List View> Quick Text> Quick Text Page Objects
 *
 */
public class SFDC_QuickText_Objects {

	public SFDC_QuickText_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@type='search' and @name='QuickText-search-input']")
	public WebElement quickTextSearch;

	@FindBy(how = How.XPATH, using = "//th//span//a")
	public List<WebElement> quickTextNameAllRows;

	@FindBy(how = How.XPATH, using = "//a/lightning-icon[contains(.,'Show Actions')]")
	public List<WebElement> showActionsIconAllRows;

	@FindBy(how = How.XPATH, using = "//a[@title='Delete']")
	public WebElement deleteTextIcon;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Delete')]")
	public WebElement deleteButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Quick Text') and contains(.,'was deleted.')]")
	public WebElement quickTextDeletedMsg;

	@FindBy(how = How.XPATH, using = "//a//div[contains(.,'New Quick Text')]")
	public WebElement newquickTextButton;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Quick Text Name')]//parent::label//following-sibling::input")
	public WebElement quickTextName;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Related To')]//following-sibling::div//a[contains(.,'Choose...')]")
	public WebElement relatedToDropdown;

	@FindBy(how = How.XPATH, using = "//li//a[@title='Case']")
	public WebElement relatedToCaseOption;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Field')]//following-sibling::div//a[contains(.,'Choose...')]")
	public WebElement fieldDropdown;

	@FindBy(how = How.XPATH, using = "//li//a[contains(.,'Case Number')]")
	public WebElement fieldCaseNumberOption;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Insert')]")
	public WebElement insertButton;

	@FindBy(how = How.XPATH, using = "//textarea[@id='quickTextMessageInputTextArea']")
	public WebElement quikcTextMsgArea;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Category')]//parent::span//following-sibling::div//a")
	public WebElement categoryDropdown;

	@FindBy(how = How.XPATH, using = "//li//a[contains(.,'Greetings')]")
	public WebElement categoryGreetingsOption;

	@FindBy(how = How.XPATH, using = "//button[@title='Save']//span[text()='Save']")
	public WebElement saveButton;

	@FindBy(how = How.XPATH, using = " //span[contains(.,'Quick Text') and contains(.,'was created.')]")
	public WebElement quickTextCreatedMsg;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Quick Text Name')]//parent::div//following-sibling::div//span//span")
	public WebElement quickTextNameValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Message') and @class='test-id__field-label']//parent::div//following-sibling::div//span//span")
	public WebElement quickTextMsgValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Category')]//parent::div//following-sibling::div//span//span")
	public WebElement categoryValue;

	@FindBy(how = How.XPATH, using = "//span[starts-with(.,'Channel')]//parent::div//following-sibling::div//span//span[@class='uiOutputText']")
	public WebElement channelValue;
}
