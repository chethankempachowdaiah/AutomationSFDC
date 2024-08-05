package sfdc.page_objects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Anukriti.Chawla, Date: 20/Jan/2021
 *
 *         SFDC Knowledge page objects
 */
public class SFDC_Knowledge_Objects {

	public SFDC_Knowledge_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//ul[@role='listbox']/li//span[contains(@class,'OptionText')]")
	public List<WebElement> listViews;

	@FindBy(how = How.XPATH, using = "//span[.='Archived Articles']//parent::a")
	public WebElement archivedArticles;

	@FindBy(how = How.XPATH, using = "//span[.='Draft Articles']//parent::a")
	public WebElement draftArticles;

	@FindBy(how = How.XPATH, using = "//div[@title='Display as Split View']")
	public WebElement defaultViewIsSplitView;

	@FindBy(how = How.XPATH, using = "//div[@title='Display as Split View']/button")
	public WebElement defaultSplitViewButton;

	@FindBy(how = How.XPATH, using = "//li[@title='Table']/a")
	public WebElement tableViewButton;

	@FindBy(how = How.XPATH, using = "//span[.='Published English Articles']//parent::a")
	public WebElement publishedEnglishArticles;

	@FindBy(how = How.XPATH, using = "//span[.='Published French Articles']//parent::a")
	public WebElement publishedFrenchArticles;

	@FindBy(how = How.XPATH, using = "//span[.='Recently Viewed']//parent::a")
	public WebElement recentlyViewed;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Knowledge')]/ancestor::div[contains(@class,'listViewManager')]//tbody/tr")
	public List<WebElement> listTrKnowledge;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Knowledge')]/ancestor::div[contains(@class,'listViewManager')]//td//input[@type='checkbox']/following-sibling::span[contains(@class,'checkbox')]")
	public List<WebElement> listTrKnowledgeSelectCheckBox;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Knowledge')]/ancestor::div[contains(@class,'listViewManager')]//tbody//th//a")
	public List<WebElement> listArticleLinks;

	@FindBy(how = How.XPATH, using = "//span[.='Article Number']/parent::a")
	public WebElement articleNumberToggle;

	@FindBy(how = How.XPATH, using = "//a[@title='New']")
	public WebElement newArticleButton;

	@FindBy(how = How.XPATH, using = "//a[@title='Publish']")
	public WebElement publishArticleButton;

	@FindBy(how = How.XPATH, using = "//a[@title='Archive']")
	public WebElement archiveArticleButton;

	@FindBy(how = How.XPATH, using = "//a[@title='Restore']")
	public WebElement restoreArticleButton;

	@FindBy(how = How.XPATH, using = "//a[@title='Delete Draft']")
	public WebElement deleteDraftButton;

	@FindBy(how = How.XPATH, using = "//span[.='General Article']")
	public WebElement generalArticleSpan;

	@FindBy(how = How.XPATH, using = "(//span[.='New Knowledge'])[1]")
	public WebElement newKnowledgeSpan;

	@FindBy(how = How.XPATH, using = "//span[.='Next']/parent::button")
	public WebElement nextButton;

	@FindBy(how = How.XPATH, using = "(//span[.='New Knowledge: General Article'])[1]")
	public WebElement newKnowledgeGeneralArticleSpan;

	@FindBy(how = How.XPATH, using = "//span[.='Title']")
	public WebElement titleSpan;

	@FindBy(how = How.XPATH, using = "//span[.='Title']/parent::label/following-sibling::input")
	public WebElement titleInputBox;

	@FindBy(how = How.XPATH, using = "//span[.='URL Name']")
	public WebElement urlNameSpan;

	@FindBy(how = How.XPATH, using = "//span[.='URL Name']/parent::label/following-sibling::input")
	public WebElement urlNameInputBox;

	@FindBy(how = How.XPATH, using = "//label//span[text()='Summary']")
	public WebElement summarySpan;

	@FindBy(how = How.XPATH, using = "//span[.='Summary']/parent::label/following-sibling::textarea")
	public WebElement summaryTextArea;

	@FindBy(how = How.XPATH, using = "//span[.='Details']")
	public WebElement detailsSection;

	@FindBy(how = How.XPATH, using = "//span[.='Visible to Customer']")
	public WebElement visibleToCustomerSpan;

	@FindBy(how = How.XPATH, using = "//span[.='Visible to Customer']/parent::label/following-sibling::input")
	public WebElement visibleToCustomerCheckBox;

	@FindBy(how = How.XPATH, using = "//span[contains(@class,'label')]//span[.='Language']")
	public WebElement languageSpan;

	@FindBy(how = How.XPATH, using = "//span[.='Language']/parent::span/following-sibling::div//a")
	public WebElement selectedLanguage;

	@FindBy(how = How.XPATH, using = "//h3//span[.='Content']")
	public WebElement contentSection;

	@FindBy(how = How.XPATH, using = "//iframe[contains(@title,'Editor')]")
	public WebElement contentEditorIFrame;

	@FindBy(how = How.XPATH, using = "//iframe[contains(@title,'Rich Text Editor')]")
	public WebElement contentEditorSubFrame;

	@FindBy(how = How.CSS, using = "body")
	public WebElement contentEditorTextArea;

	@FindBy(how = How.XPATH, using = "//h3/span[.='System Information']")
	public WebElement systemInformationSection;

	@FindAll({ @FindBy(how = How.XPATH, using = "//button[@title='Save']"),
			@FindBy(how = How.XPATH, using = "//button[.='Save']") })
	public WebElement saveArticleButton;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Archive 1 article')]")
	public WebElement archiveArticleDialogHeader;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'You can archive only published articles without drafts.')]")
	public WebElement archiveArticleMessage;

	@FindBy(how = How.XPATH, using = "//button[.='Archive']")
	public WebElement archiveDialogArchButton;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'archive 1 article because of a conflict')]")
	public WebElement archiveErrorMessage;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'article was archived')]")
	public WebElement archiveSucessMessage;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),\"restored\")]")
	public WebElement restoreSucessMessage;

	@FindBy(how = How.XPATH, using = "//*[.='Restore archived article?']")
	public WebElement restoreArticleDialogHeader;

	@FindBy(how = How.XPATH, using = "//p[.='Restoring creates a draft from this version. You’ll need to publish it to make it available.']")
	public WebElement restoreArticleDialogMessage;

	@FindBy(how = How.XPATH, using = "//button[.='Restore']")
	public WebElement restoreDialogRestButton;

}
