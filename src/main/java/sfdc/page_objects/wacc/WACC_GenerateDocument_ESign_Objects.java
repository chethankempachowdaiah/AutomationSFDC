package sfdc.page_objects.wacc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class WACC_GenerateDocument_ESign_Objects {

	public WACC_GenerateDocument_ESign_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1[normalize-space()='Generate Document']")
	public WebElement generateDocumentHeader;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Contract Preview']")
	public WebElement generateDocumentSubHeader;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Download Word']")
	public WebElement downloadWord;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Download PDF']")
	public WebElement downloadPdf;
	
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Next' and @id=\"generateDocument_nextBtn\"]")
	public WebElement nextButton;
	

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[normalize-space()='Shopping Cart' or v='Panier d’achats']"),
			@FindBy(how = How.XPATH, using = "//div[normalize-space()='Shopping cart']") })
	public WebElement shopCartHeader;
	
	//****** No to use this in future
	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Continue']")
	public WebElement continueButton;
	
	@FindBy(how = How.XPATH, using = "//div[normalize-space()='Order summary' or normalize-space()='Sommaire de la commande']")
	public WebElement orderSummaryHeader;
	
	@FindBy(how = How.XPATH, using = "//button//span[normalize-space()='Download order summary' or normalize-space()='Télécharger le sommaire de la commande']")
	public WebElement downloadOrderSummaryPdf;
	
	@FindBy(how = How.XPATH, using = "//button[(contains(normalize-space(),'Confirm') and contains(normalize-space(),'Order')) or normalize-space()='Confirmer la commande']")
	public WebElement confirmOrder;
	
	@FindAll({ @FindBy(how = How.XPATH, using = "//button//span[normalize-space()='Done' or normalize-space()='fini']"),
		@FindBy(how = How.XPATH, using = "//button[normalize-space()='Done']") })
	public WebElement doneButton;
	
	@FindBy(how = How.XPATH, using = "//span[@class='kot']")
	public WebElement quoteNumberText;
	
	@FindBy(how = How.XPATH, using = "//div[@class='document']")
	public WebElement document;
	
}
