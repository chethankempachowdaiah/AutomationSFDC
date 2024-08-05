package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP15_QM_TC_01_CreateQuoteByGBJ_RDI_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              0. : Create Business Account and approve it with
	 *                              data governance profile to make it active
	 * 
	 *                              a. TO create the credit check scenario for the
	 *                              business account by entering the credit check
	 *                              assign, available price and credit last review
	 *                              date. So it will not create the order until and
	 *                              unless it credit check is approved for the
	 *                              quote.
	 * 
	 *                              b. If no credit check is required then just tick
	 *                              credit check exempt from datag profile and don't
	 *                              follow step a.
	 * 
	 *                              1. Create Opportunity and create quote by GBJ
	 * 
	 *                              2. Create the service account based on the
	 *                              promotions for test data like ABA ON, ABA East,
	 *                              fibre on net
	 * 
	 *                              3. Select RDI Product based on the speed and
	 *                              contract terms, discount. a. Extract the MRR and
	 *                              NRC price from add to cart product page. NRC ->
	 *                              One time TCV, MRR: Monthly price. b. If credit
	 *                              validation is required then match the MRR + NRC
	 *                              price with credit check available and credit
	 *                              check assign.
	 * 
	 *                              4. Quote review of the product selected and
	 *                              verify the price and the items added
	 * 
	 *                              5. Select site contact and click next: a. If no
	 *                              credit check is required then order will be
	 *                              created directly.
	 * 
	 *                              b. If credit checkis required then Quote will be
	 *                              create and go for Quote approval process from
	 *                              data governance profile login and approve it
	 * 
	 *                              6. Verify order is created in draft status
	 * 
	 *                              7. Verify the order product items in order
	 *                              related
	 */

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_CreateQuoteByGBJ_RDI(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.openR4BSalesConsole();
//		sfdc.home.closeTabIfOpen();
//
//		// ***************Create business Account********//
//		sfdc.acc.createNewBusinessAccount();
//		//		sfdc.home.switchToNextWindow();
//		sfdc.cba.enterBusinessAccountInfo(false);
//		sfdc.cba.verifyBusinessAccountCreated();
//
//		
//
//		// *****Create Contact**********//
//		sfdc.cc.enterCreateContactInfo(false);
//		sfdc.cc.verifyMarkPrimaryContactReadOnly();
//		sfdc.cc.verifyContactCreated();
//		sfdc.cc.clickOnNextInCreateContact();
//
//		if (!dataTable.get("Billing_Account").equals("NA")) {
//			// Create the billing and service account only if it requires
//			// ***************Create Service Account********//
//			sfdc.csa.enterServiceAccountInfo(1);
//			sfdc.csa.clickOnNextInCreateServiceAccount();
//
//			// ***************Create Billing Account********//
//			sfdc.csa.checkCreateBillingAccount();
//			sfdc.cbia.enterBillingAccountInfo();
//			sfdc.cbia.verifyBillingAccountCreated();
//		}
//		//Ask how to handle below 2 steps for FIT and DEV
//		sf.seleU.switchWindow(1);
//		sf.seleU.closeRecentlyOpenedWindow();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();

		// *****Login With DataGovernance profile to approve the business account and
		// enter credit check values for validation
		// ** based on the test data*********
		Global.dataInput.tempBusinessAccountName = "TESTAuto_PVT210730063553";

		//sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		//sfdc.home.closeTabIfOpen();
		// sfdc.accDetails.searchBusAccGlobalSearch("BusinessPankaj03");
		//sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		//sfdc.accRelated.approveAsDataGovernanceWithOutcreditCheckExempt(dataTable);

		// If credit check value enter indicator is yes then enter the credit check
		// price values and
		// credit check exempt is untick
		// commented out for running gbj regression

//		if (dataTable.get("Credit_Check_Value_Enter").equals("Yes")) {
//			sfdc.accDetails.creditCheckEnterPrice(dataTable);
//		}
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();

		//		// **************Login with AE profile*************************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//sfdc.home.openR4BSalesConsole();
		//sfdc.home.closeTabIfOpenWithRefresh();

		// ****Search the business account****
		//credit check required acc - ABQAAuto200525019271
		
		//Global.dataInput.tempBusinessAccountName = "democheck";
		//Global.dataInput.tempBusinessAccountName = "FITUSAccountFRCR";
		// sfdc.accDetails.searchAccount(Global.dataInput.tempBusinessAccountName);
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);

//		*****Extract the credit check value entered for validation************
//		commented out
		if (dataTable.get("Credit_Check_Value_Enter").equals("Yes")) {
			sfdc.accDetails.extractCreditCheckRequiredValidationValues();
		}

		// ****Create Opportunity and select existing contact in
		// opportunity***************//
		
//		 sfdc.accRelated.createOpportunity(); sfdc.cOpp.enterOpportunityDetails();
//		 sfdc.cOpp.selecetExistingContactInOpportunity();
//		 
//		// *******Create Quote through GBJ*********//
		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectBillingAccountNext();
//
//		// ****Create Service Account with different access type group as per the test
		// data*****//
		
		//*** old script for service account creation***********
		//		sfdc.cQuote.createQuote_SelectNewServiceAccount();
		//		sfdc.csa.enterServiceAccountInfo(1);
		//		sfdc.csa.verifyServiceAccountCreated();
		//		sfdc.csa.checkServicability_AddServiceLocations(dataTable);
		//		sfdc.csa.clickOnNextInCheckServicabilityInGbj();
		//		sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();

		sfdc.cQuote.createQuote_SelectAddNewSite();
		//Commenting copy business address fxn as defect in new acc creation
		//sfdc.csa.enterServiceAccountInfoWithParameter(1, Global.dataInput.tempBusinessAccountName, "GBJ");
		sfdc.csa.enterServiceAccountInfo(1);
		sfdc.csa.clickOnNextInCreateServiceAccount();
		sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();

		// ***Add RDI Product in the cart based on the test data***********//
		sfdc.gbjRDI.addRogersBusinessSolutions_RDI(dataTable, false);

		// If the test data does't have the second addon product based on the dedicated
		// internet speed then it will have the
		// Quote review
		if (dataTable.get("SecondAdds_On").equals("NA")) {
			sfdc.quoteReview.verifyDetailsinQuoteReviewForRDI(dataTable, false);
		}

		// *****Accept Quote and create new site contact
		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.createNewSiteContact();

		// sfdc.siteCon.selectExistingSiteContact_Scrum();

		// ***Based on the input test data for credit check value entered, if the credit
		// check is required for Quote
		// before creating the order then login with datag profile and approve the
		// credit check
		System.out.println(sf.dataInput.quoteReviewcreditCheckFlag);
		if (sf.dataInput.quoteReviewcreditCheckFlag == true) {
			sfdc.quoteDetails.verifyQuoteStatus();
			sfdc.quoteRelated.verifyR4BQuoteNameApproval();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.home.logout();

			sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("BusinessPankaj03-00007170");
			sfdc.quoteRelated.approveR4BQuoteForCreditCheck();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.home.logout();

			// login with AE profile to check order has been created after approving the
			// credit check for the Quote

			sfdc.login.loginToSFDC(InputData.Profile_AE);
			sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpenWithRefresh();
			sfdc.quotes.searchQuoteGlobalSearch(sf.dataInput.quoteName);
			// sfdc.quotes.searchQuoteGlobalSearch("Aauto210319033924-00007728");
			sfdc.quoteDetails.verifyQuoteStatusAfterCreditCheck(sf.dataInput.quoteStatusFinalized, sf.dataInput.quoteStatusApproved);
			sfdc.quoteRelated.verifyOrderNumberInQuoteRelatedAfterApproved();
			sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusReadyToSubmit);

		}
		// If no credit check process is involved the it will create the order
		else {
			sfdc.orderDetails.verifyOrderNumberInOrderDetails();
			sfdc.orderDetails.verifyStatusInOrderDetails(sf.dataInput.orderStatusDraft);
		}

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.home.logout();
		softassert.assertAll();
	}


	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		Global.dataInput.address = dataTable.get("Address");
		Global.dataInput.addressStreet = dataTable.get("Address Street");
		Global.dataInput.addressCity = dataTable.get("Address City");
		Global.dataInput.addressState = dataTable.get("Address State");
		Global.dataInput.addressCountry = dataTable.get("Address Country");
		Global.dataInput.addressPostalCode = dataTable.get("Address Postal Code");
		Global.dataInput.serviceLocationAccessType = sf.dataInput.accessTypeFibre;
		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}


	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_SHEET);
	}

}
