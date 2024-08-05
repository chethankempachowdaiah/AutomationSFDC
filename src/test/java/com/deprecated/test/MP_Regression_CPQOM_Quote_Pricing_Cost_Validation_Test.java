package com.deprecated.test;

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
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

//@Listeners(com.framework.listeners.MyListener.class)
public class MP_Regression_CPQOM_Quote_Pricing_Cost_Validation_Test extends BaseDataProvider {

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select a Quote Based on the Region(ON or ATL)
	 * 
	 *                     Validate Quote Product/promotion price and cost based on
	 *                     the Input data
	 * 
	 *                     Total 60 products and 9 promotions are validated as of
	 *                     26th March 2020
	 */
	@Test(dataProvider = "getCostAndPriceInfo")
	public void test_verifyQuotePriceAndCost(Hashtable<String, String> dataTable) throws IOException {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.quotes.searchQuote();
		sfdc.cpqQuoteRec.clickOnHConfigure();

		if (dataTable.get("Promo").equals("Yes")) {
			sfdc.cpqHome.deleteProductFromCart();
			sfdc.cpqHome.addPromotionToCart();
			sfdc.cpqHome.validateProductPriceAndCost(dataTable);
			sfdc.cpqHome.deletePromotionFromCart();
		} else {
			sfdc.cpqHome.deleteProductFromCart();
			sfdc.cpqHome.selectProductInHybridCart();
			sfdc.cpqHome.configureQuote();
			sfdc.cpqHome.validateProductPriceAndCost(dataTable);
			sfdc.cpqHome.deleteProductFromCart();
		}

		sfdc.home.closeTabIfOpen();
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

		Global.dataInput.quoteNumber = dataTable.get("Quote Number");
		Global.dataInput.quoteProductName = dataTable.get("Product");
		Global.dataInput.quoteServiceLocation = dataTable.get("Region");
		Global.dataInput.quoteContractTerm = dataTable.get("Term");
		Global.dataInput.quoteInternetDownloadSpeed = dataTable.get("Download Speed");
		Global.dataInput.quoteInternetUploadSpeed = dataTable.get("Upload Speed");
		Global.dataInput.quoteStaticIp = dataTable.get("Static Ip");

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
		return getDataSets(Constants.PRICE_COST_FILE, Constants.PRICE_COST_SHEET);
	}

}
