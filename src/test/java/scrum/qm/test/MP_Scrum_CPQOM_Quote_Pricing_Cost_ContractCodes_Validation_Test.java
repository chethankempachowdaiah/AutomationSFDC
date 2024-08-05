package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date : 11/may/2020
 * 
 *         Test verifies contract code and contract name on Super System Promo
 *         codes
 *
 */
//@Listeners(com.framework.base.MyListener.class)
public class MP_Scrum_CPQOM_Quote_Pricing_Cost_ContractCodes_Validation_Test extends BaseDataProvider {

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 */
	@Test(dataProvider = "getCostAndPriceInfo")
	public void test_verifyQuotePriceAndCost(Hashtable<String, String> dataTable) throws Exception {
		if (dataTable.get("Active_Inactive").equals("Activated")) {
			SFDC_AllPages sfdc = new SFDC_AllPages();
			setInputData(dataTable);
			ScreenDocs.createScreenDoc(dataTable.get("Serial No") + "_" + dataTable.get("Product"));

			sfdc.login.loginToSFDC(InputData.userid_ae);
			// sfdc.home.openR4BSalesConsole();
			sfdc.home.closeTabIfOpen();

			if (dataTable.get("Region").contains("ATL")) {

				sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.account_ATL);
				// sfdc.opp.verifyOpportunitiesObject();
				// sfdc.opp.selectOpportunity(sf.dataInput.account_ATL);
			} else {

				sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.account_ON);
				// sfdc.opp.verifyOpportunitiesObject();
				// sfdc.opp.selectOpportunity(sf.dataInput.account_ON);
			}

			sfdc.accRelated.createOpportunity();
			sfdc.cOpp.enterOpportunityDetails();
			// sfdc.cOpp.enterOpportunityDetailsDebug3();
			sfdc.cOpp.selecetExistingContactInOpportunity();

			sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
			sfdc.cQuote.createQuote_SelectExistingServiceAccount();
			// sfdc.cQuote.createQuote_SelectExistingServiceAccountDebug3();

			if (dataTable.get("Term_Promo").contains("Monthly")) {
				//selects first available product from list 
				//sfdc.cpqHome.addProductToCart();
				
				//selects V2 catalog product from the list 
				sfdc.cpqHome.addV2ProductToCart(dataTable.get("Product"));
			} else {
				sfdc.cpqHome.addPromotionToCart();
			}

			// sfdc.cpqHome.validateProductPriceAndCostScrum(dataTable);
			if (!dataTable.get("Recurring  Charge Internet").contains("NO TEST DATA")) {
				sfdc.cpqHome.validateProductRecurringPriceCostMargin(dataTable);
			}

			if (!(dataTable.get("Basic_VIP").trim().equals("NA"))) {
				sfdc.cpqHome.addSTBGroup(false);
			}

			sfdc.cpqHome.acceptQuote();
			sfdc.gdPdf.sendQuoteNextButton();
			// sfdc.gdPdf.sendQuoteEmailDebug3();

			sfdc.siteCon.selectExistingSiteContact_Scrum();
			// sfdc.siteCon.verifySelectExistingNewContactText();
			// sfdc.siteCon.createNewSiteContact();
			sfdc.orderDetails.verifyOrderNumberInOrderDetails();

			sfdc.home.closeTabIfOpen();
			sfdc.home.logout();
//			sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//			sfdc.home.closeTabIfOpen();
//
//			sfdc.mques.selectManualQueueAccProvQue();
//			sfdc.manQue.pickUpOrderInQueue();
//			sfdc.crSSOrd.validateContractCodeName(dataTable);
//
//			sfdc.home.closeTabIfOpen();
//			sfdc.home.logout();

			ScreenDocs.saveScreenDoc(dataTable.get("Serial No") + "_" + dataTable.get("Product"));
		} else {
			test.log(Status.PASS, MarkupHelper.createLabel("This Promo is currently deactivated "
					+ dataTable.get("Serial No") + "_" + dataTable.get("Product"), ExtentColor.BLUE));

		}
	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		InputData.quoteNumber = dataTable.get("Quote Number");
		Global.dataInput.quoteProductName = dataTable.get("Product");

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
	public Object[][] getCostAndPriceInfo() throws IOException {
		return getDataSetsRunMode(Constants.CPQ_PRICE_PLAN_FILE, Constants.PRICE_COST_SHEET);
	}
}
