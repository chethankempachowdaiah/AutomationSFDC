package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date 20/03/2020
 * 
 *         Change primary contact (Account>Change primary contact)
 *
 */
public class SFDC_ChangePrimaryContact_Objects {

	public SFDC_ChangePrimaryContact_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@id='PrimaryContactName']")
	public WebElement primaryContactText;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_CBUpdatePrimaryContact']")
	public List<WebElement> changePrimaryContactCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_TypeAheadPrimaryContact']")
	public WebElement selectPrimaryContactInput;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_FirstName']")
	public WebElement contactFirstName;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_LastName']")
	public WebElement contactLastName;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_Email']")
	public WebElement contactEmail;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_Phone']")
	public WebElement contactPhone;

	@FindBy(how = How.XPATH, using = "//input[@id='CPC_UploadFiles']")
	public WebElement contactEvidenceUpload;

	@FindBy(how = How.XPATH, using = "//div[@id='CPC_PrimaryContactDetails_nextBtn']")
	public WebElement updatePrimaryContactNextButton;

}
