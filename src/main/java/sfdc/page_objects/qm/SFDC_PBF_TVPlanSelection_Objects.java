package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SFDC_PBF_TVPlanSelection_Objects {
	
	public SFDC_PBF_TVPlanSelection_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[contains(text(),'Monthly Term')]")
	public WebElement monthlyTermRadio;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[contains(text(),'3 Year Term')]")
	public WebElement yearlyTermRadio;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[@class='catalog-name']")
	public List<WebElement> plansTV;
	
	@FindBy(xpath = "//c-mp-os-grade//*[contains(@class,'combobox_container')]")
	public List<WebElement> subPlansSection;
	
	@FindBy(xpath = "//c-mp-os-grade//li/div/span/span")
	public List<WebElement> subPlansTV;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[@class='catalog-name']/..//dt")
	public WebElement planDescription;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[contains(text(),'View more details')]")
	public List<WebElement> viewMoreDetails;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[contains(@class,'product-details-header')]")
	public WebElement productDetailsHeader;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[@value='addCart']")
	public List<WebElement> addToCartList;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[@value='addCart']")
	public List<WebElement> addToCart;
	
	@FindBy(xpath = "//*[contains(@class,'offer-container')]//*[@value='removeCart']")
	public WebElement removeCart;
	
	@FindBy(xpath = "//button[@value='next']")
	public WebElement nextbtn;

}
