package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
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
 *         Test verifies IBLC Products Price, cost , one time cost etc in Hybrid
 *         Cart
 *
 */
//@Listeners(com.framework.base.MyListener.class)
public class MP12_QM_TC_02_HybridCart_IBLC_PriceCost_Validation_Test extends BaseDataProvider {

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 */
	@Test(dataProvider = "getCostAndPriceInfo")
	public void test_verifyQuotePriceAndCost(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);
		ScreenDocs.createScreenDoc(dataTable.get("Serial No") + "_" + dataTable.get("Product"));

		sfdc.login.loginToSFDC(InputData.userid_ae);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		if (dataTable.get("Region").equals("NF")) {

			// sfdc.accDetails.searchAccount(sf.dataInput.account_ATL_NF);
			sfdc.opp.verifyOpportunitiesObject();
			sfdc.opp.selectOpportunity(sf.dataInput.account_ATL_NF);

		} else if (dataTable.get("Region").equals("NB")) {

			// sfdc.accDetails.searchAccount(sf.dataInput.account_ATL_NB);
			sfdc.opp.verifyOpportunitiesObject();
			sfdc.opp.selectOpportunity(sf.dataInput.account_ATL_NB);

		} else {

			// sfdc.accDetails.searchAccount(sf.dataInput.account_ATL);
			sfdc.opp.verifyOpportunitiesObject();
			sfdc.opp.selectOpportunity(sf.dataInput.account_ON);

		}

		// sfdc.accRelated.createOpportunity();
		// sfdc.cOpp.enterOpportunityDetails();
		// sfdc.cOpp.selecetExistingContactInOpportunity();

		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		if (dataTable.get("Product_Type").equals("Promo"))

		{
			sfdc.cpqHome.addPromotionToCart();

		} else if (dataTable.get("Product_Type").equals("IBLC + Int Bundle")) {
			sfdc.cpqHome.addPromotionToCart();
			sfdc.cpqHome.addProductToCart(dataTable.get("Internet_Product"));

		} else {
			sfdc.cpqHome.addProductToCart();
		}

		sfdc.cpqHome.addProductToCart(Global.dataInput.iblcUnlistedNumber);
		sfdc.cpqHome.addProductToCart(Global.dataInput.iblcTechnicalInstallJacks);
		sfdc.cpqHome.addProductToCart(Global.dataInput.iblcTechnicalInstallPhoneLines);
		sfdc.cpqHome.verifyProductAddedToCart();

		sfdc.iblcHybridCart.verify_IBLC_Offer_Promo_Bundle_HybridCart(dataTable);

		// sfdc.cpqHome.acceptQuote();
		// sfdc.gdPdf.sendQuoteEmail();

		// sfdc.siteCon.selectExistingSiteContact_Scrum();
		// sfdc.siteCon.verifySelectExistingNewContactText();
		// sfdc.siteCon.createNewSiteContact();
		// sfdc.orderDetails.verifyOrderNumberInOrderDetails();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		ScreenDocs.saveScreenDoc(dataTable.get("Serial No") + "_" + dataTable.get("Product"));

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
		return getDataSetsRunMode(Constants.CPQ_PRICE_PLAN_FILE, Constants.PRICE_COST_IBLC_SHEET);
	}
}
