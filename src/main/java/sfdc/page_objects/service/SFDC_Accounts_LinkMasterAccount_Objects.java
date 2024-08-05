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
 *         Select Account> Create Relationship> Validate Account (Link Master
 *         Account)
 *
 */
public class SFDC_Accounts_LinkMasterAccount_Objects {

	public SFDC_Accounts_LinkMasterAccount_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'add-checkboxisMaster')]//following-sibling::label")
	public List<WebElement> addMasterAccountIconAllRows;

	@FindBy(how = How.XPATH, using = "//input[contains(@id, 'add-checkbox-')]//following-sibling::label")
	public List<WebElement> addSelectIconAllRows;

	@FindBy(how = How.XPATH, using = "//tbody[@class='ins']/tr/td[3]/a")
	public List<WebElement> accountsAllRows;

	@FindBy(how = How.XPATH, using = "//input[@id='MergeAccountName']")
	public WebElement mergeAccountNameInput;

	@FindBy(how = How.XPATH, using = "//input[@id='MergeTradeStyle']")
	public WebElement mergeAccountLegalNameInput;

	@FindBy(how = How.XPATH, using = "//input[@id='MergeR1DUNS']")
	public WebElement mergeAccountR1DUNSInput;

	@FindBy(how = How.XPATH, using = "//input[@id='MergeDUNS']")
	public WebElement mergeAccountDUNSInput;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectMergeAccounts']")
	public WebElement selectMergeAccountInput;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectMergeAccounts']//following-sibling::ul//li[@role='button' and @ng-click='setViewValue(obj)']")
	public List<WebElement> mergeAccountOptionsAll;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectRelationshipType']")
	public WebElement selectRelationshipType;

	@FindBy(how = How.XPATH, using = "//input[@id='SelectRelationshipType']//following-sibling::ul//li[@role='button' and @ng-click='setViewValue(obj)']")
	public List<WebElement> relationshipTypeOptionsAll;

	@FindBy(how = How.XPATH, using = "//div[@id='AccountDuplicate_nextBtn']")
	public WebElement accountDuplicateNextButton;

	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")
	public WebElement alertOkButton;

	@FindBy(how = How.XPATH, using = "//div[@id='DelinkAccount_nextBtn']")
	public WebElement delinkAccountNextButton;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Relationship is removed successfully') and contains(@class,'message description')]")
	public WebElement relationshipRemovedMsg;

}
