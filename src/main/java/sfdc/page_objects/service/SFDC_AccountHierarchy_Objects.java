package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 18/JUNE/2020
 * 
 *         Select Account> Account Overview> Account Hierarchy
 *
 */
public class SFDC_AccountHierarchy_Objects {

	public SFDC_AccountHierarchy_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Overview')]")
	public WebElement overviewTab;
	
	@FindBy(how = How.XPATH, using = "//a[.='Overview']")
	public List<WebElement> overview;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'Account Hierarchy')]")
	public WebElement accountHierarchyButton;

	@FindBy(how = How.XPATH, using = "//div[@class='listViewContent']//tbody//tr//th[1]//a")
	public List<WebElement> accountNameAllRows;

	@FindBy(how = How.XPATH, using = "//div[@class='listViewContent']//tbody//tr//th//a//parent::div//following-sibling::span")
	public List<WebElement> accountNameCurrent;

	@FindBy(how = How.XPATH, using = "//span[.='current']/ancestor::tr//following-sibling::tr//td[1]/span")
	public List<WebElement> accountType;
	
	@FindBy(how = How.XPATH, using = "//span[.='current']/ancestor::tr//following-sibling::tr//th//a")
	public List<WebElement> currentAccSubAccs;
	
	@FindBy(how = How.XPATH, using = "//span[.='current']/parent::div/button[@title='Expand']")
	public WebElement expandCurrentAcc;
	
}
