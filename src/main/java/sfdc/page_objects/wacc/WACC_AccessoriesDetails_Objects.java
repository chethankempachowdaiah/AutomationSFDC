package sfdc.page_objects.wacc;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_AccessoriesDetails_Objects {
	
	public WACC_AccessoriesDetails_Objects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using = "//button[contains(@class,'button_stretch addCart')]")
	public WebElement addToCart;
	
	@FindBy(how = How.XPATH, using = "//input[@name='numericInput']")
	public WebElement inputQuantity;
	
	@FindBy(how = How.XPATH, using = "//button[@name = 'increment']")
	public WebElement quantityPlus;
	
	@FindBy(how = How.XPATH, using = "//button[@name = 'decrement']")
	public WebElement quantityMinus;
	
	@FindBy(how= How.XPATH, using = "//c-mp-os-tooltip")
	public WebElement toolTipMessage;
	
	@FindBy(how = How.XPATH, using = "//span[text() = 'Back to accessories ']")
	public WebElement backToAccessoriesBtn;
	
	@FindBy(how= How.XPATH, using = "//div[contains(text(),'Total price')]")
	public WebElement priceText;
	
	@FindBy(how= How.XPATH, using = "//div[contains(text(),'Before taxes,') or contains(text(),'Avant taxes,')]")
	public WebElement taxText;
	
	@FindBy(how= How.XPATH, using = "//div[contains(@class,'colorButtons')]")
	public List<WebElement> accessoriesColorList;
	
	@FindBy(how= How.XPATH, using = "//span[@class='dynamicValues']")
	public WebElement colorName;

	@FindBy(how= How.XPATH, using = "//*[@class='brand-name']")
	public WebElement brandNameAccDetails;

	@FindBy(how= How.XPATH, using = "//div[@class='device-model']")
	public WebElement accessoryNameAccDetails;
	
	@FindBy(how = How.XPATH, using = "//*[@class='price']")
	public WebElement priceOnADetails;
	
	@FindBy(how = How.XPATH, using = "//*[@class='price-secondary']/following-sibling::div[contains(@class,'price-secondary')]")
	public WebElement desimalPriceOnADetails;
	
	@FindBy(how = How.XPATH, using = "(//span[contains(@class,'availability-label')])[1]")
	public WebElement availableText;
	
	@FindBy(how = How.XPATH, using = "//span[@class='availability-label']")
	public WebElement shippedTimeText;
	
	@FindBy(how = How.XPATH, using = "//span[@class='baseMrc']")
	public WebElement basePrice;
	
}
