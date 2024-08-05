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
 *         SFDC Knowledge Details page objects
 */
public class SFDC_Knowledge_Details_Objects {

	public SFDC_Knowledge_Details_Objects(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//h1//div[.='Knowledge']")
	public WebElement knowledgeHeaderText;

	@FindBy(how = How.XPATH, using = "//h1//div[.='Knowledge']/following-sibling::div/span")
	public WebElement articleNameHeader;

	@FindBy(how = How.XPATH, using = "//span[@title='Article Record Type']/following-sibling::div//span")
	public WebElement articleRecordType;

	@FindBy(how = How.XPATH, using = "//span[@title='Publication Status']/following-sibling::div//span")
	public WebElement publicationStatus;

	@FindBy(how = How.XPATH, using = "//span[@title='Version Number']/following-sibling::div//span")
	public WebElement articleVersionNumber;

	@FindBy(how = How.XPATH, using = "//div[.='Edit']/parent::a")
	public WebElement editArticle;

	@FindBy(how = How.XPATH, using = "//div[.='Edit as Draft']/parent::a")
	public WebElement editAsDraft;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Edit a draft of')]")
	public WebElement editAsDraftDialogHeader;

	@FindBy(how = How.XPATH, using = "//div[.='The published version will remain online until you publish this draft.']")
	public WebElement editAsDraftTextMessage;

	@FindBy(how = How.XPATH, using = "//button[.='Edit as Draft']")
	public WebElement editAsDraftDialogButton;

	@FindBy(how = How.XPATH, using = "//div[.='Submit for Approval']/parent::a")
	public WebElement submitForApprovalArticle;

	@FindBy(how = How.XPATH, using = "//h2[.='Submit for Approval']")
	public WebElement submitForApprovalDialogHeader;

	@FindBy(how = How.XPATH, using = "//label[.='Comments']/following-sibling::textarea")
	public WebElement commentsForApproval;

	@FindBy(how = How.XPATH, using = "//button[.='Submit']")
	public WebElement submitButton;

	@FindBy(how = How.XPATH, using = "//div[.='Edit']//ancestor::li/following-sibling::li//div[.='Publish']/parent::a")
	public WebElement publishArticle;

	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//h2[contains(text(),'Edit')]")
	public WebElement editArticleDialogHeader;

	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//span[.='Information']")
	public WebElement editArticleDialogInformationSection;

	@FindBy(how = How.XPATH, using = "//div[.='Edit']//ancestor::li/following-sibling::li//span[contains(text(),'actions')]//ancestor::a")
	public WebElement showMoreActions;

	@FindAll({ @FindBy(how = How.XPATH, using = "//div[contains(@class,'maximized active')]//a[.='Delete Draft']"),
			@FindBy(how = How.XPATH, using = "//a[.='Delete Draft']"),
			@FindBy(how = How.XPATH, using = "//div[.='Edit']/ancestor::li/following-sibling::li//a[.='Delete Draft']") })
	public WebElement deleteDraft;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'DropDownMenuList')]//a[.='Delete Draft']")
	public WebElement deleteDraftDropDownButton;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Delete draft of')]")
	public WebElement deleteDraftDialogHeader;

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'recover deleted drafts.')]/parent::div")
	public WebElement deleteDraftDialogWarningText;

	@FindBy(how = How.XPATH, using = "//button[.='Delete']")
	public WebElement deleteDraftDialogDelButton;

	@FindBy(how = How.XPATH, using = "//a[.='Approve']")
	public WebElement approveButton;

	@FindBy(how = How.XPATH, using = "//h2[.='Approve Knowledge']")
	public WebElement approveKnowledgeDialogHeader;

	@FindBy(how = How.XPATH, using = "//button[.='Approve']")
	public WebElement approveDialogButton;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Publish')]")
	public WebElement publishDialogHeader;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Publish Now')]")
	public WebElement publishNowSpan;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Schedule publication on:')]")
	public WebElement publishOnADateSpan;

	@FindBy(how = How.XPATH, using = "//button[.='Publish']")
	public WebElement publishDialogPubButton;

	@FindBy(how = How.XPATH, using = "//span[.='Summary']/parent::div")
	public WebElement summarySection;

	@FindBy(how = How.XPATH, using = "//span[.='Summary']/parent::div/following-sibling::div//span[contains(@class,'Text')]")
	public WebElement summaryText;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'wasn't deleted')]")
	public WebElement deleteErrorMessage;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'was deleted')]")
	public WebElement deleteSuccessMessage;

	@FindBy(how = How.XPATH, using = "//span[.='Edit']//ancestor::a")
	public WebElement categoryEditOpenIcon;

	@FindBy(how = How.XPATH, using = "//li/a[.='Edit' and @role='menuitem']")
	public WebElement categoryEditButton;

	@FindBy(how = How.XPATH, using = "//span[.='Edit Article Categories']")
	public WebElement categoryEditDialogBoxHeader;

	@FindBy(how = How.XPATH, using = "//div[@role='dialog']//tbody//tr[@aria-level='1']/th/span")
	public List<WebElement> categoryList;

	@FindBy(how = How.XPATH, using = "//input[@id='Function:All']")
	public WebElement categoryFunctionCheckBox;

	@FindBy(how = How.XPATH, using = "//input[@id='Product:All']")
	public WebElement categoryProductCheckBox;

	@FindBy(how = How.XPATH, using = "//span[@title='Function (1)']")
	public WebElement selectedCategoryFunction;

	@FindBy(how = How.XPATH, using = "//span[@title='Product (1)']")
	public WebElement selectedCategpryProduct;

	@FindBy(how = How.XPATH, using = "//span[.='Categories (2)']")
	public WebElement categoriesNumberSpan;

	@FindBy(how = How.XPATH, using = "//span[.='Title']/parent::div")
	public WebElement titleSection;

	@FindBy(how = How.XPATH, using = "//span[.='Title']/parent::div/following-sibling::div//span[contains(@class,'Text')]")
	public WebElement titleText;

	@FindBy(how = How.XPATH, using = "//span[.='Visible to Customer']")
	public WebElement visibleToCustomerSection;

	@FindBy(how = How.XPATH, using = "//img[contains(@class,'check')]")
	public WebElement visibleToCustomerCheckBoxImage;

	@FindBy(how = How.XPATH, using = "//span[.='Content']")
	public WebElement contentSection;

	@FindBy(how = How.XPATH, using = "//span[.='Content']//parent::div/following-sibling::div//div[contains(@class,'RichText')]")
	public WebElement contentText;

	@FindBy(how = How.XPATH, using = "//*[contains(@class,'Vote')]")
	public WebElement feedbackComponent;

	@FindBy(how = How.XPATH, using = "//button[.='Like']")
	public WebElement feedBackLikeButton;

	@FindBy(how = How.XPATH, using = "//button[.='Dislike']")
	public WebElement feedBackDislikeButton;

	@FindBy(how = How.XPATH, using = "//span[.='Files']")
	public WebElement filesSection;

	@FindBy(how = How.XPATH, using = "//span[.='Feed']")
	public WebElement feedSection;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search this feed')]")
	public WebElement searchFeedInputBox;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Write a comment')]")
	public WebElement writeCommentInputBox;

	@FindBy(how = How.XPATH, using = "//div[@data-placeholder='Write a comment...']")
	public WebElement writeCommentTextArea;

	@FindBy(how = How.XPATH, using = "//button[.='Comment']")
	public WebElement commentButton;

	@FindBy(how = How.XPATH, using = "//span[.='Versions']/ancestor::a")
	public WebElement versionsTab;

	@FindBy(how = How.XPATH, using = "//span[.='Related']/ancestor::a")
	public WebElement relatedTab;

	@FindBy(how = How.XPATH, using = "//span[.='Approval History']")
	public WebElement approvalHistorySection;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'RelatedList')]//tbody//th//a")
	public WebElement approvalList;

	@FindBy(how = How.XPATH, using = "//a[.='Recall']")
	public WebElement recallApprovalButton;

	@FindBy(how = How.XPATH, using = "//span[.='Current Version']")
	public WebElement currentVersion;

	@FindBy(how = How.XPATH, using = "//label[.='Other Version']")
	public WebElement otherVersionLabel;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Choose')]")
	public WebElement chooseVersion;

	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Choose')]/parent::div/following-sibling::div//span[contains(@class,'body')]/span")
	public WebElement otherVersionDropdownOption;

	@FindBy(how = How.XPATH, using = "//button[.='Compare']")
	public WebElement compareButton;

	@FindBy(how = How.XPATH, using = "//h3/div[.='Information']")
	public List<WebElement> informationSectionInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='Title']")
	public List<WebElement> titleSectionInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='Title']/following-sibling::div")
	public List<WebElement> titleTextInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='URL Name']")
	public List<WebElement> urlNameInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='URL Name']/following-sibling::div")
	public List<WebElement> urlNameTextInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='Summary']")
	public List<WebElement> summarySectionInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='Summary']/following-sibling::div")
	public List<WebElement> summaryTextInCompare;

	@FindBy(how = How.XPATH, using = "//h3//div[.='Details']")
	public List<WebElement> detailsSectionInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='Article Total View Count']")
	public List<WebElement> articleTotalViewInCompare;

	@FindBy(how = How.XPATH, using = "//td//div[.='Article Total View Count']/following-sibling::div")
	public List<WebElement> articleTotalViewTextInCompare;

	@FindBy(how = How.XPATH, using = "//h3/div[.='Content']")
	public List<WebElement> contentSectionInCompare;

	@FindBy(how = How.XPATH, using = "//h3/div[.='Content']")
	public List<WebElement> systemInformationSectionInCompare;

}
