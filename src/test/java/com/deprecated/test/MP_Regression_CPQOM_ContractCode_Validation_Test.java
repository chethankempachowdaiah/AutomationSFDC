package com.deprecated.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Priyanka.Acharya, 12/06/2020
 * 
 *         Verify that the promo Codes are displayed on task1 for 36 Month
 *         Promos. There are 8 in Production Currently.
 * 
 * 
 */

public class MP_Regression_CPQOM_ContractCode_Validation_Test extends Base {

	/**
	 * 
	 * 
	 * ******PROMOTION NAME****........*****CONTRACT CODE******
	 * 
	 * 1. 500u Business Internet - 3 Years Promo : X9C
	 * 
	 * 2. 1G Business Internet Pro - 3 Years Promo: X8X
	 * 
	 * 3. 150u Business Internet WiFi - 3 Years Promo: XE2
	 * 
	 * 4. 500u Business Internet WiFi - 3 Years Promo: XE2
	 * 
	 * 5. 1G Business Internet WiFi - 3 Years Promo: XE2
	 * 
	 * 6. 150u Business Internet WiFi Pro - 3 Years Promo: XE5
	 * 
	 * 7. 500u Business Internet WiFi Pro - 3 Years Promo: XE5
	 * 
	 * 8. 1G Business Internet WiFi Pro - 3 Years Promo: XE5
	 * 
	 */
	@Test(dataProvider = "getContractCodes")
	public void test_CotractCode_Validation(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		Global.dataInput.quoteProductName = dataTable.get("Promotion_Name");

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.opp.verifyOpportunitiesObject();

		sfdc.opp.selectOpportunity(sf.dataInput.account_ON);
		sfdc.cQuote.createQuote_SelectExistingSigningAuthority();
		sfdc.cQuote.createQuote_SelectExistingServiceAccount();

		sfdc.cpqHome.addPromotionToCart();

		sfdc.cpqHome.acceptQuote();
		sfdc.gdPdf.sendQuoteNextButton();
		sfdc.siteCon.verifySelectExistingNewContactText();
		sfdc.siteCon.selectExistingSiteContact_Scrum();

		sfdc.orderDetails.verifyOrderNumberInOrderDetails();

		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
		sfdc.home.closeTabIfOpen();

		// sfdc.mques.selectManualQueueAccProvQue();
		sfdc.mques.verifyManualQueuesObject();
		sfdc.mques.selectAccountProvisioningQueue();
		sfdc.manQue.pickUpOrderInQueue();
		sfdc.crSSOrd.validateInternetContractCode(dataTable);

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getContractCodes() throws IOException {
		return getDataSets(Constants.MP_TESTDATA_FILE, Constants.CONTRACT_CODE_SHEET);
	}

}
