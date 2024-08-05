package sfdc.page_objects.qm;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya,Date: 30/Jan/2019
 * 
 *         SFDC Quote_GenerateDocument Page(Opportunity>create Quote>Configure
 *         Quote>Accept Quote>Generate Document)
 *
 */
public class SFDC_Quote_GenerateDocument_Objects {

	public SFDC_Quote_GenerateDocument_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//iframe[@title='accessibility title']")
	public List<WebElement> accessbilityTitleFrame;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'Send Email')]")
	public WebElement sendEmailButton;

	//@FindBy(how = How.XPATH, using = "//button[@id='GenerateDocument_nextBtn'] or //div[@id='vlc-step-cancel-btn']//following-sibling::button[@id='GenerateDocument_nextBtn']")
	//public List<WebElement> generateDocumentNextButton;
	@FindAll({	
		@FindBy(xpath = "//button[@value='next']"),
		@FindBy(xpath = "//button[contains(@id,'GenerateDocument_nextBtn') or contains(@id,'generateDocument_nextBtn') ]")})	
	public List<WebElement> generateDocumentNextButton;
	
	@FindBy(xpath = "//div[@id='vlc-step-cancel-btn']//following-sibling::button[@id='GenerateDocument_nextBtn' or @id='generateDocument_nextBtn']") 
	public List<WebElement> generateDocumentNextSecondButton;

	@FindBy(how = How.XPATH, using = "//button[@id='alert-ok-button']")
	public WebElement pdfGenerationCompleteAlertOkButton;

	@FindBy(how = How.XPATH, using = "//p[contains(.,'PDFGenerationComplete!')]")
	public List<WebElement> pdfGenerationCompleteAlertText;
	
	@FindBy(how = How.XPATH, using = "//span[text() ='Download order summary']")
	public List<WebElement> downloadPDFButton;

	@FindBy(how = How.XPATH, using = "//section[@id='GenerateDocument']//following::iframe")
	public WebElement PDFFrame;


}
