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
public class SFDC_TaskRelated_Objects {

	public SFDC_TaskRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a/span[@title='Notes']")
	public WebElement notesSection;
	
	@FindBy(how = How.XPATH, using = "//a[.='New']")
	public WebElement newNotesButton;
	
	@FindBy(how = How.XPATH, using = "//a[.='Related']")
	public WebElement relatedTab;
	
	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//div[contains(@class,'notes')]")
	public WebElement notesDialogBox;
	
	@FindBy(how = How.XPATH, using = "//div[@data-placeholder='Enter a note...']")
	public WebElement notesContentBox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@class,'notesTitle')]")
	public WebElement notesTitle;
	
	@FindBy(how = How.XPATH, using = "//span[.='Done']/parent::button")
	public WebElement notesDoneButton;
	
	@FindBy(how = How.XPATH, using = "//a/span[@title='Notes']//ancestor::header/parent::div/following-sibling::div//tbody/tr")
	public WebElement notesFirstRowData;
}
