package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
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
@Listeners(com.framework.base.MyListener.class)
public class MP_Scrum_CPQOM_Quote_Recurring_PriceCostMargin_Validation_Test extends BaseDataProvider {

	@BeforeTest
	public void setOpportunity() throws IOException, InterruptedException {
		SFDC_AllPages sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		dataInput = new InputData();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.opp.verifyOpportunitiesObject();

		sfdc.opp.selectOpportunity(sf.dataInput.account_ON);
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Login as ae
	 * 
	 *                              select existing opportunity
	 * 
	 *                              select Signing Authority and Service Account
	 * 
	 *                              Add promotion to cart
	 * 
	 *                              Validate Quote Price and Cost in the cart
	 * 
	 *                              Accept Quote and Send Email
	 * 
	 *                              Select Site Contact
	 * 
	 *                              Login as delivery
	 * 
	 *                              Select 'Account Provisioning Queue' in manual
	 *                              queue
	 * 
	 *                              Pick up order and Validate contract code and
	 *                              name in supersyetem order
	 * 
	 */
	@Test(dataProvider = "getCostAndPriceInfo")
	public void test_verifyQuotePriceAndCost(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		if (dataTable.get("Active_Inactive").equals("Activated")) {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			setInputData(dataTable);
			ScreenDocs.createScreenDoc(dataTable.get("Serial No") + "_" + dataTable.get("Product"));

			if (dataTable.get("Region").contains("ATL")) {
				test.log(Status.PASS, MarkupHelper.createLabel("Skipping as it is ATL region", ExtentColor.BLUE));
			} else {

				if (dataTable.get("Term_Promo").contains("Monthly")) {
					sfdc.cpqHome.deleteProductFromCart();
					sfdc.cpqHome.addProductToCart();
					sfdc.cpqHome.validateProductRecurringPriceCostMargin(dataTable);
					sfdc.cpqHome.deleteProductFromCart();
				} else {
					sfdc.cpqHome.deletePromotionFromCart();
					sfdc.cpqHome.addPromotionToCart();
					sfdc.cpqHome.validateProductRecurringPriceCostMargin(dataTable);
					sfdc.cpqHome.deletePromotionFromCart();

				}

			}

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
		// return getDataSets(Constants.RECURRING_PRICE_COST_SCRUM_FILE,
		// Constants.RECURRING_PRICE_COST_SHEET);
		return getDataSetsRunMode(Constants.CPQ_PRICE_PLAN_FILE, Constants.PRICE_COST_SHEET);
	}
}
