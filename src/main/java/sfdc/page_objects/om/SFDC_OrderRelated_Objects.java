package sfdc.page_objects.om;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Order Related Tab
 */
public class SFDC_OrderRelated_Objects {

	public SFDC_OrderRelated_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindAll({ 
			@FindBy(xpath = "//a[@aria-selected='false']//span[contains(.,'Related')]"),
			@FindBy(xpath = "//li[contains(@class,'active')]//a[@title='Related' or @title='Associé']//span[contains(.,'Related') or contains(.,'Associé')]"),
			@FindBy(xpath = "//div[contains(@class,'windowViewMode-maximized active')]//a[@title='Related']//span[contains(.,'Related')]"),
			@FindBy(xpath = "//*[contains(@class,'row row-main')]//ul//a[contains(text(),'Related')]"),})
	public WebElement orderRelatedTab;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'windowViewMode-maximized active')]//a[@title='Related']//span[contains(.,'Related')]")
	public WebElement orderRelatedTabs;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'Orchestration Plan Name')]/ancestor::thead/following-sibling::tbody/tr/th//a")
	public WebElement orderOrchestrationPlanNumber;

	@FindBy(how = How.XPATH, using = "//span[.='Superseding Order']")
	public WebElement supersedingOrderSection;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Superseding Order')]/ancestor::article//th//a")
	public WebElement supersedingOrderNumber;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by ')]//following::table/tbody/tr/th[@class='slds-cell-edit cellContainer']/span/a")
	public List<WebElement> orderProductNameInFilteredTable;
	
	@FindBy(how = How.XPATH, using = "//input[@name='OrderItem-Product2.Name']")
	public WebElement orderProductInputEnter;
	
	@FindBy(how = How.XPATH, using = "//span[contains(.,'Order Products')]/parent::span")
	public WebElement orderProductViewALLClick;
	
	@FindBy(how = How.XPATH, using = "//section[contains(@class,'tabContent active oneConsoleTab')]//span[contains(text(),'Filtered by ')]//following::table/tbody/tr/td[2]/span/span")
	public List<WebElement> orderProductQuantityInFilteredTable;
	
}
