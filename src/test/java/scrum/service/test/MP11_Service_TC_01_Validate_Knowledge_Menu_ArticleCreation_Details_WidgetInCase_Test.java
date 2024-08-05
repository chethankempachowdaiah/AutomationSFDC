package scrum.service.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date : 21/Jan/2020
 * 
 *         MP11_FIT_MPOSS-14934_TC83_Validate the page of an article_English
 * 
 *         MP11_FIT_MPOSS-14933_TC84_Validate Knowledge widget on Service
 *         console_English
 * 
 *         MP11_FIT_MPOSS-15574_TC85_Validate article author permission
 *         set_English
 * 
 * 
 *         MP11_FIT_MPOSS-15574_TC87_Validate article manager permission
 *         set_English
 *
 */
public class MP11_Service_TC_01_Validate_Knowledge_Menu_ArticleCreation_Details_WidgetInCase_Test extends Base {

	/**
	 * @throws Exception
	 * 
	 *                   //---- MP11_FIT_MPOSS-14934_TC83_Validate the page of an
	 *                   article_English---///
	 * 
	 *                   1. Service Login and through Knowledge object,Open an
	 *                   article
	 * 
	 *                   2. Validate the field "Content", subject, summary, context
	 *                   and file
	 * 
	 *                   3. Validate the option to specify if the article is visible
	 *                   to customer
	 * 
	 *                   4. Validate data categories, feedback component and chatter
	 *                   component
	 * 
	 *                   5. Validate compare version in version tab
	 * 
	 *                   //////////////////////////----------------------////////////////////////////////
	 * 
	 * 
	 *                   //---- MP11_FIT_MPOSS-14933_TC84_Validate Knowledge widget
	 *                   on Service console_English---///
	 * 
	 *                   1. Service Login and Validate Knowledge object tab and list
	 *                   view
	 * 
	 *                   2. Open a case and validate knowledge widget
	 * 
	 *                   3. Search articles and attach to case
	 * 
	 *                   4. Validate article attached in Related tab Articles List *
	 * 
	 *                   //////////////////////////----------------------////////////////////////////////
	 *
	 *                   //---- MP11_FIT_MPOSS-15574_TC85_Validate article author
	 *                   permission set_English ---///
	 * 
	 *                   1. Service log in and validate Knowledge list view
	 * 
	 *                   2. Click on New button and write a new article
	 * 
	 *                   3. Edit the categories on article
	 * 
	 *                   4. Edit the article on the draft
	 * 
	 *                   5. Delete the draft
	 * 
	 *                   6. Submit draft article for approval
	 * 
	 *                   7. Open a published article
	 * 
	 *                   8. Edit as draft
	 * 
	 * 
	 * 
	 *                   //////////////////////////----------------------////////////////////////////////
	 * 
	 *                   //---- MP11_FIT_MPOSS-15574_TC87_Validate article manager
	 *                   permission set_English ---///
	 * 
	 *                   1. Service log in and validate Knowledge list view
	 * 
	 *                   2. Click on New button and write a new article
	 * 
	 *                   3. Edit the categories on article
	 * 
	 *                   4. Edit the article on the draft
	 * 
	 *                   5. Delete the draft
	 * 
	 *                   6. Submit draft article for approval
	 * 
	 *                   7. Open a published article
	 * 
	 *                   8. Edit as draft
	 * 
	 *                   9. Publish a draft article
	 * 
	 *                   10. Archive a published article
	 * 
	 *                   11. Restore an archived article
	 *
	 *                   //////////////////////////----------------------////////////////////////////////
	 * 
	 * 
	 */
	@Test
	public void test_verifyKnowledgeSection() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.home.closeTabIfOpen();

		// ---- MP11_FIT_MPOSS-14934_TC83_Validate the page of an article_English---///

		// Open Article from Knowledge Section and Validate Article Details
		sfdc.knowledge.openArticleForDetailsValidation();
		sfdc.knowledgeDetails.verifyArticleDetails();
		sfdc.home.closeTabIfOpen();

		// Open Article from Knowledge Section and Validate Compare Version Section
		sfdc.knowledge.openArticleForVersionComparison();
		sfdc.knowledgeDetails.verifyCompareVersionOfArticle();
		sfdc.home.closeTabIfOpen();

		// ---- MP11_FIT_MPOSS-14933_TC84_Validate Knowledge widget on Service
		// console_English---///

		// Verify List views in Knowledge Section
		sfdc.knowledge.verifyListViews();

		// Create a new Case and Open Knowledge Widget and attach an article
		sfdc.cases.verifyCasesObject();
		sfdc.cases.selectCaseCategory(Constants.SERVICE_TYPE_SHEET);
		sfdc.cases.createCaseProactively();
		sfdc.caseKnowledge.verifyKnowledgeWidget();
		sfdc.caseKnowledge.searchAndAttachAnArticle();
		sfdc.caseRelated.verfiyCaseArticles();
		sfdc.home.closeTabIfOpen();

		// Validate Relevant articles listed in case according to subject
		sfdc.cases.verifyCasesObject();
		sfdc.cases.searchAndOpenCaseForKnowledgeSection();
		sfdc.caseKnowledge.verifyKnowledgeWidget();
		sfdc.home.closeTabIfOpen();

		////////////////////////// ----------------------//////////////////////////

		// ---- MP11_FIT_MPOSS-15574_TC85_Validate article author permission set_English
		// ---///
		// Verify knowledge Page
		sfdc.knowledge.verifyKnowledgeListPageForArticleAuthor();

		// Create new General Article and verify once created
		sfdc.knowledge.createNewGeneralArticle();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();

		// Edit Draft Article
		sfdc.knowledgeDetails.editDraftArticle();

		// Verify FeedBack section not editable
		sfdc.knowledgeDetails.verifyFeedBackNotEditable();

		// Verify Article listed in draft View
		sfdc.home.closeTabIfOpen();
		sfdc.knowledge.verifyArticleListedInDraftsView();

		// Edit Categories on Draft
		//// !!!!--------------------------------------------------------------categories
		// not being listed for me!!!!
		sfdc.knowledgeDetails.editArticleCatogories();

		// Delete Draft
		sfdc.knowledgeDetails.deleteDraft();

		// Verify Article removed from Draft List View
		sfdc.knowledge.verifyArticleNotListedInDraftsView();

		// Create new General Article and verify once created
		sfdc.knowledge.createNewGeneralArticle();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();

		// Submit for Approval and verify submitted successfully
		sfdc.knowledgeDetails.submitArticleForApproval();
		sfdc.knowledgeDetails.verifyApprovalHistory();

		// Verify Draft cannot be deleted
		sfdc.knowledgeDetails.verifyDraftNotDeletable();

		// Approve the article to publish it. Verify details after publishing.
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_OpsManager);
		sfdc.home.closeTabIfOpen();
		sfdc.knowledge.openTableView();
		sfdc.knowledge.verifyArticleListedInDraftsView();
		sfdc.knowledgeDetails.approveArticle();
		sfdc.home.logout();
		sfdc.login.loginToSFDC(InputData.Profile_Service);
		sfdc.knowledgeDetails.verifyPublishedArticle();

		// Edit as Draft
		sfdc.knowledgeDetails.editAsDraft();
		sfdc.home.closeTabIfOpen();

		sfdc.knowledge.verifyArticleListedInDraftsView();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		////////////////////////// ----------------------//////////////////////////

		// ---- MP11_FIT_MPOSS-15574_TC87_Validate article manager permission
		// set_English ---///

		// Login to opsManager profile with permissions of Article Manager, Verify
		// Knowledge List Page
		sfdc.login.loginToSFDC(InputData.Profile_OpsManager);
		sfdc.home.closeTabIfOpen();

		sfdc.knowledge.verifyKnowledgeListPageForArticleManager();
		sfdc.knowledge.openTableView();

		// Create new General Article and verify once created
		sfdc.knowledge.createNewGeneralArticle();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();

		// Edit Draft Article
		sfdc.knowledgeDetails.editDraftArticle();

		// Verify FeedBack section not editable
		sfdc.knowledgeDetails.verifyFeedBackNotEditable();

		// Verify Article listed in draft View
		sfdc.home.closeTabIfOpen();
		sfdc.knowledge.verifyArticleListedInDraftsView();

		// Edit Categories on Draft
		//// !!!!-----categories not being listed for opsProfile!!!!
		// sfdc.knowledgeDetails.editArticleCatogories();

		// Delete Draft
		sfdc.knowledgeDetails.deleteDraft();

		// Verify Article removed from Draft List View
		sfdc.knowledge.verifyArticleNotListedInDraftsView();

		// Create new General Article and verify once created
		sfdc.knowledge.createNewGeneralArticle();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();

		// Submit for Approval and verify submitted successfully
		sfdc.knowledgeDetails.submitArticleForApproval();
		sfdc.knowledgeDetails.verifyApprovalHistory();

		// Verify Draft cannot be deleted
		sfdc.knowledgeDetails.verifyDraftNotDeletable();

		// Approve the article to publish it. Verify details after publishing.
		sfdc.knowledgeDetails.approveArticle();
		sfdc.knowledgeDetails.verifyPublishedArticle();

		// Edit as Draft
		sfdc.knowledgeDetails.editAsDraft();
		sfdc.home.closeTabIfOpen();

		sfdc.knowledge.verifyArticleListedInDraftsView();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();
		sfdc.home.closeTabIfOpen();

		// Draft Article cannot be archived
		sfdc.knowledge.verifyDraftArticleCannotBeArchived();

		// Publish An Article
		sfdc.knowledge.verifyArticleListedInDraftsView();
		sfdc.knowledgeDetails.publishArticle();
		sfdc.knowledgeDetails.verifyPublishedArticle();
		sfdc.home.closeTabIfOpen();

		// Archive Published Article and verify its removed from Published List, added
		// in archived list
		sfdc.knowledge.archivePublishedArticle();
		sfdc.knowledge.verifyArticleNotInPublishedList();
		sfdc.knowledge.verifyArticleInArchiveList();
		sfdc.home.closeTabIfOpen();

		// Restore an Archived Article and verify its added to Draft List View
		sfdc.knowledge.restoreArchivedArticle();
		sfdc.knowledge.verifyArticleListedInDraftsView();
		sfdc.knowledgeDetails.verifyArticleDraftCreated();

		sfdc.home.closeTabIfOpen();

		////////////////////////// ----------------------//////////////////////////

	}

}
