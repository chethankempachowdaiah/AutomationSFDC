package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Priyanka.Acharya, Date : 20/FEB/2020
 * 
 *         Case Related Page(Email to case> Accpet case from omni channel> case
 *         Related page)
 *
 */
public class SFDC_CaseKnowledge_Objects {

	public SFDC_CaseKnowledge_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[.='Knowledge']")
	public WebElement knowledgeTab;

	@FindBy(how = How.XPATH, using = "//span[.='Knowledge']")
	public WebElement knowledgeHeaderSpan;

	@FindBy(how = How.XPATH, using = "//input[@title='Search Knowledge']")
	public WebElement searchKnowledgeInputBox;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'searchResultsSummary')]//div[not(contains(@class, 'hide'))]/p")
	public WebElement numberofRelevantArticlesListed;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'articleSearchContainer')]//ul/li//a")
	public List<WebElement> relevantArticlesListLinks;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'articleSearchContainer')]//button[@title='Show More']")
	public WebElement showMoreButtonForArticleOptions;

	@FindBy(how = How.XPATH, using = "//a[.='Attach Article']")
	public WebElement attachArticleButton;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'was attached to the case')]")
	public WebElement articleAttachSuccessMessage;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'articleSearchContainer')]//ul/li//a[contains(@class,'itemTitle')]")
	public List<WebElement> searchedArticleLinks;

}
