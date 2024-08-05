package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:19/01/2022
 *
 *         SFDC> PBF page objects
 */
public class SFDC_PBF_MultiSiteSelection_Objects {

	public SFDC_PBF_MultiSiteSelection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'0 of 5 Selected')]")
	public WebElement siteSelectionThreshHoldLabel;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'ve selected your maximum of 5 sites.')]")
	public WebElement selectedMaxSitesLabel;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Please first remove a selected site if') and contains(text(),'prefer to choose another one.')]")
	public WebElement removeToAddNewMessage;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'5 of 5 Selected')]")
	public WebElement allSiteSelectedLabel;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Review your sites below and select up to a maximum of 5 where ')]")
	public WebElement reviewSitesSelectedMessage;
	
	@FindAll({@FindBy(xpath = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//span[@class='nds-checkbox_faux']"),
		@FindBy(xpath = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']"),
		})
	public List<WebElement> businessInternetRowRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressProvince']/div")
	public List<WebElement> businessInternetRowProvince;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div"),
		@FindBy(xpath = "//div[contains(@class,'data-table-body')]//c-dgtl-generic-cell[@data-field-name='serviceAddressStreetConcat']/div")})
	
	public List<WebElement> businessInternetRowSerrviceAddressValue;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Your new site has been added but cannot be selected.')]")
	public WebElement newSiteAddedButCantBeSelectedLabel;
	
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'//*[contains(text(),'ve selected your maximum of 5 sites. To select your new site, please first deselect a currently-selected site.')]')]")
	public WebElement newSiteAddedButCantBeSelectedMessage;
	
	@FindBy(xpath= "//div[@class='desktop']//span[@class='nds-checkbox_faux']")
	public List<WebElement> serviceAddressCheckboxes;
	
	@FindBy(xpath= "//div[@class='desktop']//span[@class='nds-checkbox_faux']/ancestor::span/input[@data-id='pbfcheckbox']")
	public List<WebElement> serviceAddressCheckboxSelected;
	
}
