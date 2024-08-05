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
 *         Select Party Relationships>Details
 *
 */
public class SFDC_PartyRelationships_Objects {

	public SFDC_PartyRelationships_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//table[@data-aura-class='uiVirtualDataTable']//tbody//tr/th[1]//a")
	public List<WebElement> relationshipNameAllRows;

	@FindBy(how = How.XPATH, using = "//tbody//tr/td[4]//a")
	public List<WebElement> sourcePartyIdAllRows;

	@FindBy(how = How.XPATH, using = "//tbody//tr/td[5]//a")
	public List<WebElement> targetPartyIdAllRows;

	@FindBy(how = How.XPATH, using = "//li//a[contains(.,'Details')]")
	public WebElement detailsTab;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Party Relationship')]/following::a[contains(text(),'Details')]")
	public WebElement details;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Relationships Name')]/parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement relationshipNameValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Target Party Id')]/parent::div//following-sibling::div//a//span")
	public WebElement targetPartyIdValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Source Party Id')]/parent::div//following-sibling::div//a//span")
	public WebElement sourcePartyIdValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Primary Role')]/parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement primaryRoleValue;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Target Role')]/parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement targetRoleValue;

}
