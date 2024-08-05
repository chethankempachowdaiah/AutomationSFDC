package com.deprecated.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, Date : 07/July/2020
 * 
 * 
 *
 */
@Listeners(com.framework.base.MyListener.class)
public class MP_Scrum_CPQOM_GBJ_Price_Margin_Validation_Test extends BaseDataProvider {

	@BeforeTest
	public void setOpportunity() throws IOException, InterruptedException {
		SFDC_AllPages sfdc = new SFDC_AllPages();
		test = reports.createTest(this.getClass().getName());
		dataInput = new InputData();

		sfdc.login.loginToSFDC(InputData.userid_ae);
		// sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		// sfdc.opp.verifyOpportunitiesObject();

		// sfdc.opp.selectOpportunity("testGBJcartview");
		// sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		// sfdc.cQuote.createQuote_SelectExistingServiceAccount();
	}

	@Test(dataProvider = "getGBJPriceMarginInfo")
	public void test_CPQOM_GBJ_Price_Margin_Validation(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		sfdc.gbjCart.addProductAndVerifyPriceMarginInGBJ(dataTable);

	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getGBJPriceMarginInfo() throws IOException {
		return getDataSets(Constants.RECURRING_PRICE_COST_SCRUM_FILE, Constants.GBJ_SHEET);
	}
}
