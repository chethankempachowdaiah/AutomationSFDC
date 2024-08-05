package sfdc.page_objects.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date 20/Jan/2020
 * 
 *         Service Account Premises Details Page
 * 
 * 
 */
public class SFDC_ServiceAccountPremisesDetails_Objects {

	public SFDC_ServiceAccountPremisesDetails_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Premises')]//ancestor::slot[@name='header']/parent::div/following-sibling::div//a[@data-label='Details']")
	public WebElement premisesDetailsTab;

	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Premises')]//ancestor::slot[@name='header']/parent::div/following-sibling::div//a[@data-label='Related']")
	public WebElement premisesRelatedTab;

	@FindBy(how = How.XPATH, using = "//th[contains(.,'ServiceableLocation Name')]//ancestor::table//tbody//tr[1]/th//a")
	public WebElement serviceLocationName;

	@FindBy(how = How.XPATH, using = "//span[text()='Name']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesNameValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Street Address']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesStreetAddressValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='City']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesCityValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='State']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesStatAddressValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Country']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesCountryValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Premises Type']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesTypeValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Postal Code']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement postalCodeValueText;

	@FindBy(how = How.XPATH, using = "(//span[contains(text(),'Owner')]/parent::div/following-sibling::div//records-hoverable-link//a//slot//slot//span)[2]")
	public WebElement premisesOwnerValueText;

	@FindBy(how = How.XPATH, using = "//span[text()='Unit / Apartment']//parent::div//following-sibling::div//lightning-formatted-text")
	public WebElement premisesApartmentValueText;

}
