package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 17/11/2020
 * 
 *         IBLC Hybrid Cart (Opportunity> Create Quote> Add IBLC product to
 *         cart)
 *
 */

public class SFDC_HybridCart_IBLC_Objects {

	public SFDC_HybridCart_IBLC_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Business Phone Long Distance Plan']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement businessPhoneLongDistancePlan_ShowActions_Button;

	@FindBy(how = How.XPATH, using = "//span[text()='Business Phone Long Distance Plan']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]//following-sibling::div//span[contains(.,'Delete')]//ancestor-or-self::li//a//span")
	public WebElement businessPhoneLongDistancePlan_Delete_Button;

	@FindBy(how = How.XPATH, using = "//span[text()='Unlimited North American LD']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement unlimitedNorthAmericanLD_ShowActions_Button;

	@FindBy(how = How.XPATH, using = "//span[text()='Unlimited North American LD']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]//following-sibling::div//span[contains(.,'Delete')]//ancestor-or-self::li//a//span")
	public WebElement unlimitedNorthAmericanLD_Delete_Button;

	@FindBy(how = How.XPATH, using = "//h2[contains(.,'Delete Item')]/parent::div/following-sibling::div/button[contains(.,'Cancel')]/following-sibling::button[contains(.,'Delete') and @ng-repeat='button in buttons']")
	public WebElement deleteButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Administrative Features Group']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement administrativeFeaturesGroup_ShowActions_Button;

	@FindBy(how = How.XPATH, using = "//span[text()='Administrative Features Group']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]//following-sibling::div//span[contains(.,'Configure')]//ancestor-or-self::li//a//span")
	public WebElement administrativeFeaturesGroup_Configure_Button;

	@FindBy(how = How.XPATH, using = "//label/span[contains(.,'Block')]")
	public List<WebElement> administrativeFeaturesGroup_IBLC_Attributes;

	@FindBy(how = How.XPATH, using = "//span[text()='Voice Features Group']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement voiceFeaturesGroup_ShowActions_Button;

	@FindBy(how = How.XPATH, using = "//span[text()='Voice Features Group']//ancestor::div[@class='cpq-item-base-product']//div[12]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]//following-sibling::div//span[contains(.,'Configure')]//ancestor-or-self::li//a//span")
	public WebElement voiceFeaturesGroup_Configure_Button;

	@FindBy(how = How.XPATH, using = "//input[contains(@name,'productconfig_field')]/following-sibling::span[2]")
	public List<WebElement> voiceFeaturesGroup_IBLC_Attributes;

	@FindBy(how = How.XPATH, using = "//label[contains(.,'Number of Selected Features')]//following-sibling::div//input")
	public WebElement voiceFeaturesGroup_NoOfSelectedAttr_Input;

	@FindBy(how = How.XPATH, using = "//span[text()='Distinctive Ring']//ancestor::div[@class='cpq-item-base-product']//input")
	public WebElement distinctiveRingQtyInput;

	@FindBy(how = How.XPATH, using = "//span[text()='Technician Install - Phone Lines']//ancestor::div[@class='cpq-item-base-product']//div[11]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]")
	public WebElement technicalInstallPhoneLines_ShowActions_Button;

	@FindBy(how = How.XPATH, using = "//span[text()='Technician Install - Phone Lines']//ancestor::div[@class='cpq-item-base-product']//div[11]//button[@title='Show Actions' or contains(@title,'CPQShowActions')]//following-sibling::div//span[contains(.,'Configure')]//ancestor-or-self::li//a//span")
	public WebElement technicalInstallPhoneLines_Configure_Button;

	@FindBy(how = How.XPATH, using = "//select[contains(@name,'productconfig_field')]")
	public WebElement technicalInstallPhoneLines_NumberOfLines_Select;

	@FindBy(how = How.XPATH, using = "//button[contains(@ng-click,'close')]")
	public WebElement iblcAttributes_Configuration_CloseButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Technician Install - Jacks']//ancestor::div[@class='cpq-item-base-product']//input")
	public WebElement technicalInstallJacks_QtyInput;

	@FindBy(how = How.XPATH, using = "//div[contains(.,'Monthly Recurring Total') and @class='slds-text-body--small']")
	public WebElement monthlyRecurringTotalText;

}
