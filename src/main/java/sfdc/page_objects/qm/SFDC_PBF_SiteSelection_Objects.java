package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date:26/07/2021
 *
 *         SFDC> PBF page objects
 */
public class SFDC_PBF_SiteSelection_Objects {

	public SFDC_PBF_SiteSelection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//vlocity_cmt-omniscript-text-block[not(contains(@class,'hide'))]//div[contains(text(),'Where would you like your services to be located') or contains(text(),'Où souhaitez-vous installer vos services')]")
	public WebElement letsGetStartedHeader;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'legend-container')]")
	public WebElement legendSection;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Business Internet') or contains(text(),'Internet Affaires')]")
	public WebElement businessInternetIconInLegend;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'TV for Business') or contains(text(),'Télé Affaires')]")
	public WebElement businessTVIconInLegend;
	
	@FindBy(xpath = "//span[contains(text(),'RDI On-Net') or contains(text(),'Internet spécialisé de Rogers dans la zone desservie par le réseau')]")
	public WebElement rdiOnNetIconInLegend;
	
	@FindBy(xpath = "//span[contains(text(),'RDI Near-Net') or contains(text(),'Internet spécialisé de Rogers à proximité de la zone desservie par le réseau')]")
	public WebElement rdiNearNetIconInLegend;
	
	@FindBy(how = How.XPATH, using = "//img[contains(@class,'available-service')]")
	public List<WebElement> servicesAvailableIconsForAllSites;
	
	@FindAll({@FindBy(xpath = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//span[@class='nds-checkbox_faux']"),
		@FindBy(xpath= "//div[@class='desktop']//span[@class='nds-checkbox_faux']"),
		@FindBy(xpath = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']"),
		})
	public WebElement businessInternetRowRadioButton;
	
	@FindBy(how = How.XPATH, using = "//*[@data-field-name='serviceAddressProvince']/div")
	public WebElement businessInternetRowProvince;
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div"),
		@FindBy(xpath = "//div[contains(@class,'data-table-body')]//c-dgtl-generic-cell[@data-field-name='serviceAddressStreetConcat']/div")})
	
	public WebElement businessInternetRowSerrviceAddressValue;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']")
	public WebElement busIntTvRowRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Internet')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div")
	public WebElement busIntTvRowSerrviceAddressValue;

	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'On-Net')]//ancestor::div[contains(@class,'row')]//span[@class='nds-checkbox_faux']"),
		@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'On-Net')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']")})
	public WebElement rdiOnNetRowRadioButton;
	
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'ABA-ON')]//ancestor::div[contains(@class,'row')]//span[@class='nds-checkbox_faux']"),
				@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'ABA-ON')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']")})
	public WebElement rdiABAOnNetRowRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'ABA-ON')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div")
	public WebElement rdiABAOnNetRowServiceAddressValue;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'ABA-E')]//ancestor::div[contains(@class,'row')]//span[@class='nds-checkbox_faux']"),
		@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'ABA-E')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']")})
	public WebElement rdiABAEastNetRowRadioButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'ABA-E')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div")
	public WebElement rdiABAEastNetRowServiceAddressValue;
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'On-Net')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div")
	public WebElement rdiOnNetRowSerrviceAddressValue;
	
	@FindAll({
		@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Near-Net')]//ancestor::div[contains(@class,'row')]//span[@class='nds-checkbox_faux']"),
			@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Near-Net')]//ancestor::div[contains(@class,'row')]//span[@class='nds-radio_faux']")})
	public WebElement rdiNearNetRowRadioButton;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='desktop']//img[contains(@src,'Near-Net')]//ancestor::div[contains(@class,'row')]//*[@data-field-name='serviceAddressStreetConcat']/div")
	public WebElement rdiNearNetRowSerrviceAddressValue;
	
	
}
