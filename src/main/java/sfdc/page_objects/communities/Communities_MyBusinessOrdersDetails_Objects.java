package sfdc.page_objects.communities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla,, Date:08/11/2020
 * 
 *         Communities>View Orders> My Business Orders Details
 *
 */
public class Communities_MyBusinessOrdersDetails_Objects {

	public Communities_MyBusinessOrdersDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(.,'Details')]")
	public WebElement orderDetailsTab;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Order #') or contains(text(),'No de commande')]/following-sibling::p")
	public WebElement orderNumber;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Account Name') or contains(text(),'Nom du compte')]/following-sibling::p")
	public WebElement accountName;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Status')]/following-sibling::p")
	public WebElement orderStatus;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Activated Date') or contains(text(),'Date d’activation')]/following-sibling::p")
	public WebElement orderActivatedDate;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Order Start Date') or contains(text(),'Date de début de la commande')]/following-sibling::p")
	public WebElement orderStartDate;

	@FindBy(xpath = "//p[contains(.,'Service Account #') or contains(text(),'No du compte de service')]/following-sibling::p")
	public WebElement serviceAccountName;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Monthly Charges') or contains(text(),'Frais mensuels')]/following-sibling::p")
	public WebElement orderMonthlyCharges;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Product(s)') or contains(text(),'Produit(s)')]/parent::div//li[contains(@class,'item_detail')]")
	public List<WebElement> orderProductList;
	
	@FindBy(how = How.XPATH, using = "//p[contains(.,'Product(s)') or contains(text(),'Produit(s)')]/parent::div//p[contains(.,'Product(s)') or contains(text(),'Produit(s)')]/parent::div//p[contains(@class,'item_detail')]")
	public WebElement orderMainProduct;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Product(s)') or contains(text(),'Produit(s)')]/parent::div//li[contains(@class,'item_detail')]/ul/li")
	public List<WebElement> orderProductSubList;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='OrderNumber']/div/a")
	public List<WebElement> readyToSubmitorderProductList;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'One Time Charges') or contains(text(),'Frais uniques')]/following-sibling::p")
	public WebElement orderOneTimeCharges;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Customer Name') or contains(text(),'Nom du client')]/following-sibling::p")
	public WebElement customerName;

	@FindBy(how = How.XPATH, using = "//button[@class='filter-button'][@value='In Progress']")
	public List<WebElement> inProgressOrderDetailsButton;

	@FindBy(how = How.XPATH, using = "//button[@class='filter-button'][@value='Ready to Submit']")
	public WebElement readyToSubmitOrderDetailsButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-table')]//following::slot/c-dgtl-order-id-cell/div/a")
	public List<WebElement> orderProductListInDevStage;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-table')]//following::slot/c-dgtl-generic-cell[3]/div")
	public List<WebElement> orderProductListStatus;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Complete')]/preceding-sibling::p")
	public WebElement orderReviewSpecSheetCompleteButton;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'nds-grid nds-m-bottom--large total-footer')]//button[contains(.,'Next')]")
	public WebElement orderReviewSpecSheetNextButton;

}
