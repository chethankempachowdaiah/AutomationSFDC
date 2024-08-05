package scrum.qm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

public class MP_QM_RDI_Pricing_Validation_ABA_EAST_Test extends Base {

	/** Price Valdation for ABA EAST Promos of RDI Products
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_RDI_ABA_EAST_Promos_Price_Validation_EPC_Release(Hashtable<String, String> dataTable) throws Exception {
		
		int rowStart = 0; int rowNum = 0;int rowNext = 0; int count = 0;
		rowStart = Integer.parseInt(dataTable.get("SrNo.")); 	
		rowNum = rowStart+1;
		
		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//		sfdc.home.closeTabIfOpen();

		//		sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		//		sfdc.accRelated.createOpportunity();
		//		sfdc.cOpp.enterOpportunityDetails();
		//		sfdc.cOpp.selecetExistingContactInOpportunity();
		//
		//		// Create Quote BY PBF
		//		sfdc.cQuote.clickCreateQuotePbfButton();
		//		sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();
		//		sfdc.siteSelPBF.verifyPBFLandingPage();
		//		sfdc.siteSelPBF.verifyLegendSection();
		//
		//		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
		//			// Verify New Site added in PBF appears in related accounts in SF
		//
		//			// Extract No if service addesses before adding new site in PBF
		//			sfdc.login.loginToSFDCInNewTab(InputData.Profile_AE);
		//			sfdc.home.closeTabIfOpen();
		//			sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
		//			sfdc.accHirchy.extractAllRelatedServiceAccForBusAcc();
		//			sfdc.accHirchy.extractNoOfRelatedServiceAcc();
		//
		//			// Add New Site in PBF
		//			sf.seleU.switchWindow(1);
		//			sfdc.commPBF.addNewSite();
		//			sfdc.commPBFAddSite.fillSiteInfo();
		//			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
		//			sfdc.commPBFAddSite.selectAddress();
		//
		//			sfdc.commPBFAddSite.confirmAndProceed();
		//			sf.seleU.switchWindow(2);
		//
		//			// Verify New Site added in PBF appears in related accounts in SF
		//			sfdc.accHirchy.verifyNoOfRelatedServiceAccIncreased();
		//			sf.seleU.switchWindow(1);
		//			sf.seleU.closeRecentlyOpenedWindow();
		//
		//		} else {
		//
		//			sfdc.siteSelPBF.selectRDIOnNetAddress();
		//
		//		}
		
		// Set the data from the excel file for the execution
        XlsAction reader = new XlsAction(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE);	    			
       
        outerloop:
			while (true) {
				InputData_QM.setDataFor_QM_RDI(Constants.PBF_AGENT_SHEET_RDI_PRICE_VALIDATION_ABA_EAST, rowNum, reader);	
				System.out.println("Row no executed is " + rowNum);
				
			// Validate shopping Cart Page and Proceed
			sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

			// Click on Shop Plans for Business Internet
			sfdc.siteSelPBF.moveToSiteSelectionPageForDedInt();

			// Select 5 year term Product
			if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("5 year term") ) {
				sfdc.planSelPBF.selectYearTerm();
			}

			if (Integer.parseInt(InputData_Communities.discount) > 0) {
				// Validate discount and promo section
				sfdc.planSelPBF.discountAndPromoSectionsForRDI();

				// Validate boundary check for discount and validation of Product Price
				sfdc.planSelPBF.validateBoundaryValueCheckDiscountAndPromoSectionsForRDI();

				// Applied discount and validation of Product Price
				sfdc.planSelPBF.validateDiscountAndPromoSectionsForRDI();

			} 

			sfdc.planSelPBF.addToCartRDIProductAndProceed();
			// Validate User lands on Shopping Cart Page with added product
			sfdc.commPBFShopCart.validateShoppingCartPageWithAddedRDIProduct();

			// Validate Price details of added product
			sfdc.commPBFShopCart.validatePriceDetailsForAddedRDIProduct();
			// Validate TCV margin
			sfdc.planSelPBF.validate_RDI_TCV();
			// Remove the product
			sfdc.commPBFShopCart.removeProduct_POC();
			rowNext = 	rowNum +1;
			
			InputData_QM.runMode = reader.getCellData(Constants.PBF_AGENT_SHEET_RDI_PRICE_VALIDATION_ABA_EAST, "RunMode", rowNext);
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE, Constants.PBF_AGENT_SHEET_RDI_PRICE_VALIDATION_ABA_EAST);
	}
}
