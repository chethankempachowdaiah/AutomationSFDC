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

/**
 * @author Priyanka.Acharya, Date : 15/Nov/2020
 * 
 *         Create Quote by Guided By Journey for below set of IBLC products
 *         Variations
 * 
 *         1.IBLC Standalone - 3 Year
 * 
 *         2. IBLC Standalone - Monthly
 * 
 *         3.IBLC Standalone - 3 Year - Multi product and Multi line
 * 
 *         4.IBLC Standalone - Monthly - Multi product and Multi line
 * 
 *         5.IBLC Bundle  - Internet and IBLC
 * 
 *         6.IBLC Bundle  - Internet and IBLC(S24)
 *
 * 
 */
public class MP12_QM_TC_01_CreateQuoteByGBJ_IBLC_Test extends Base {

	@Test(dataProvider = "getTestData_IBLC_GBJ")
	public void test_CreateQuoteByGBJ_IBLC(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sfdc.login.loginToSFDC(InputData.userid_ae);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		sfdc.accDetails.searchBusAccGlobalSearch(InputData.account_ON);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();

//		sfdc.opp.verifyOpportunitiesObject();
//		sfdc.opp.searchOpportunityInGlobalSearch(InputData.account_ON);

		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		if (!dataTable.get("Internet Product Name").equals("NA")) {
			sfdc.gbjCart.addRogersBusinessSolutions_Internet(dataTable, false, true);
			sfdc.gbjCart.addRogersOffice365AddOns(dataTable, false);
			sfdc.gbjCart.hitNext();
		}

		sfdc.gbjIBLC.add_BusinessPhone_GBJ(dataTable, false);
		sfdc.gbjIBLC.addALine_IBLC(dataTable);
		sfdc.gbjIBLC.editLine_IBLC(dataTable);
		sfdc.gbjCart.hitNext();
		sfdc.gbjCart.hitCheckOut();

		sfdc.gbjCart.acceptQuoteInGBJ();
		sfdc.gdPdf.sendQuoteNextButton();

//		sfdc.gbjCart.acceptQuoteInGBJ();
//		sfdc.gdPdf.clickDownloadPDFButton();
//		sfdc.gdPdf.verifyContentFromPDF(Constants.ACCEPTED_PDF, Constants.TERMSANDCONDITIONS_INTERNET_IBLC);
//		sfdc.gbjCart.handleEmailActionError();
//		sfdc.gdPdf.sendQuoteNextButton();
//		sfdc.gbjCart.handleEmailActionError();

		sfdc.siteCon.selectExistingSiteContact_Scrum();
		// sfdc.siteCon.verifySelectExistingNewContactText();
		// sfdc.siteCon.createNewSiteContact();

		sfdc.gbjCart.handleEmailActionError();

		sfdc.gbjProv.verifyProvisioningDetailsPage();
		sfdc.gbjProv.fillProvisioningLineAndPortDetails(dataTable);
		sfdc.gbjProv.uploadInvoiceOnProvisioningPage();
		sfdc.gbjProv.uploadEmergencyDisclaimerOnProvisioningPage();
		sfdc.gbjProv.fillDirectoryDetailsOnProvisioningPage();
		sfdc.gbjCart.hitNext();

		// sfdc.orderDetails.verifyOrderNumberInOrderDetails();
		sfdc.orderDetails.verifyCreatedOrderInOrderDetailsTab(Global.dataInput.orderStatusInProgress);

		// sfdc.home.closeTabIfOpen();
	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
		Global.dataInput.office365ProductName = dataTable.get("Office 365 AddOn");

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
	public Object[][] getTestData_IBLC_GBJ() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_IBLC_SHEET);
	}
}
