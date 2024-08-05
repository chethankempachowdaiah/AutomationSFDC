package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_GuidedByJourney_RDI_Objects {

	public SFDC_GuidedByJourney_RDI_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'Dedicated Internet')]")
	public WebElement dedicatedInternetProduct;
	
	@FindBy(how = How.XPATH, using = "//label//span[contains(.,'5 Year Term')]")
	public WebElement fiveYearTermRadioButton;
	
	@FindBy(how = How.XPATH, using = "//span[@class='nds-radio_faux']")
	public List<WebElement> fibreAccessDefaultCheckboxAddsOn;
	
	@FindBy(how = How.XPATH, using = "//span[@class='nds-radio_faux']/following-sibling::span")
	public List<WebElement> fibreAccessDefaultProductText;
	
	@FindBy(how = How.XPATH, using = "//span[@class='nds-radio_faux']//following::span/img/following-sibling::span")
	public WebElement fibreAccessAddsOnAbaOfferText;
	
	@FindBy(how = How.XPATH, using = "//button[@value='previous']")
	public WebElement previousButton;
	
	@FindBy(how = How.XPATH, using = "//button[@value='updateCart']")
	public WebElement updateCartButton;
}
