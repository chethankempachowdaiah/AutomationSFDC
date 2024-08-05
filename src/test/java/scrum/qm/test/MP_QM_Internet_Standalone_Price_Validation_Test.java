package scrum.qm.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.utilities.XlsAction;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_QM;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_QM_Internet_Standalone_Price_Validation_Test extends Base {

	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_InterNet_Standalone_PriceValidation_Agent(Hashtable<String, String> dataTable) throws Exception {
		int rowStart = 0; int rowNum = 0;int rowNext = 0; int count = 0;
		rowStart = Integer.parseInt(dataTable.get("Sno.")); 	
		rowNum = rowStart+1;
		
		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sf.dataInput.businessAccountName = "Reg-Rework";
		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		//	sf.dataInput.signingAuthEmailIdValue = "flow@mailinator.com";
		// Create Quote BY PBF
		sfdc.cQuote.clickCreateQuotePbfButton_New();
		sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			// Verify New Site added in PBF appears in related accounts in SF

			// Extract No if service addesses before adding new site in PBF
			//			sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
			//			sfdc.home.closeTabIfOpen();
			//			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
			//			sfdc.accHirchy.extractAllRelatedServiceAccForBusAcc();
			//			sfdc.accHirchy.extractNoOfRelatedServiceAcc();
			//
			//			// Add New Site in PBF
			//			sf.seleU.switchWindow(1);
			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectAddress();

			sfdc.commPBFAddSite.confirmAndProceed();
			//			sf.seleU.switchWindow(2);
			//
			//			// Verify New Site added in PBF appears in related accounts in SF
			//			sfdc.accHirchy.verifyNoOfRelatedServiceAccIncreased();
			//			sf.seleU.switchWindow(1);
			//			sf.seleU.closeRecentlyOpenedWindow();

		} else {
			// Verify Service location has Business Internet plans
			sfdc.siteSelPBF.verifyIconInServiceColumn();
			sfdc.siteSelPBF.selectBusinessInternetAddress();
			}
		

		// Set the data from the excel file for the execution
		XlsAction reader = new XlsAction(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE);	
		outerloop:
			while (true) {
				InputData_QM.setDataFor_QM_Internet_Standalone(Constants.PBF_AGENT_SHEET_ALLENV_BI, rowNum, reader);	
				System.out.println("Row no executed is " + rowNum);

				// Validate shopping Cart Page and Proceed
				sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

				// Click on Shop Plans for Business Internet
				sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

				if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("Monthly Term")) {
					sfdc.planSelPBF.selectMonthlyTerm();
				} else {
					// Validate 3 year and monthly term options
					sfdc.planSelPBF.validatePlanTermsOptionsForBI();
				}
                 
				sfdc.planSelPBF.handlePBFpopup();
				// Validate discount and promo section
				if(InputData_Communities.commPBFPromo.equalsIgnoreCase("Yes")) {
					sfdc.planSelPBF.selectInternetProductType();
					//	sfdc.planSelPBF.validateDiscountAndPromoSections();

					// Select Discount product
					sfdc.planSelPBF.selectDiscountAndPromoSectionsProduct();

					// Click on Proceed to check out
					sfdc.commPBFCablePlan.proceedtoCheckout();

					// Validate TCV margin
					sfdc.planSelPBF.validate_Internet_TCV_Discount_Product();

					// Validate User lands on Shopping Cart Page with added product
					InputData_Communities.commPBFProductPrice = InputData_Communities.discountPrice;
					sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();

				} else {

					sfdc.planSelPBF.selectInternetProductType();

					// Add to Cart a product and proceed to checkout
					sfdc.commPBFCablePlan.addToCartProduct_Quickly();

					sfdc.commPBFCablePlan.proceedtoCheckout();

					// Validate User lands on Shopping Cart Page with added product
					sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();

					// Validate TCV margin
					sfdc.planSelPBF.validate_Internet_TCV();
				}

				// Remove Product
				sfdc.commPBFShopCart.removeProduct_POC();
				
				// It will read the next excel record row and will verify the value of the run mode clmn
				// If the run mode is Yes for the next row then it come out of the while loop and will start a new browser session
				// And validate the prices on the new opportunity
				rowNext = 	rowNum +1;
				InputData_QM.runMode = reader.getCellData(Constants.PBF_AGENT_SHEET_ALLENV_BI, "RunMode", rowNext);
				rowNum ++;
				if (InputData_QM.runMode.equalsIgnoreCase("Yes")) {
					System.out.println("Terminate and validate the price on the new opportunity");
					break outerloop;
				}
			}

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();


	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] PBFAgentData() throws IOException {
		return getDataSetsRunMode(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE, Constants.PBF_AGENT_SHEET_ALLENV_BI);
	}
}
