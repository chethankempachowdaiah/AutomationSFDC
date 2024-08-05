package scrum.qm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.XlsAction;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_QM;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_QM_InterOff365TV_PriceValidation_EPC_Release extends Base{
	/**
	 * @throws Exception
	 */
	@Test(dataProvider = "PBFAgentData")
	public void test_validate_PBF_POC_Cable_PriceValidation_EPC_Release(Hashtable<String, String> dataTable) throws Exception {
		int rowStart = 0; int rowNum = 0;int rowNext = 0; int count = 0;
		rowStart = Integer.parseInt(dataTable.get("SrNo.")); 	

		// As we need to read from the second row and SrNo. initial value be set as 1
		rowNum = rowStart+1;

		SFDC_AllPages sfdc = new SFDC_AllPages();
		InputData_Communities.setDataForPBFE2E(dataTable);
		System.out.println("Row no executed is " + rowNum);		

		sfdc.login.loginToSFDC(InputData.Profile_AE);
				sfdc.home.closeTabIfOpen();
						sfdc.accDetails.searchBusAccGlobalSearch(sf.dataInput.businessAccountName);
						sfdc.accRelated.createOpportunity();
						sfdc.cOpp.enterOpportunityDetails();
						sfdc.cOpp.selecetExistingContactInOpportunity();
					//sf.dataInput.signingAuthEmailIdValue = "flow@mailinator.com";
				// Create Quote BY PBF
				sfdc.cQuote.clickCreateQuotePbfButton();
				sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();
			//	sfdc.siteSelPBF.verifyPBFLandingPage();
			//	sfdc.siteSelPBF.verifyLegendSection();
		
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
		rowNum = 20;
		outerloop:
			while (true) {
				InputData_QM.setDataFor_QM_Internet_Offic365_TV(Constants.PBF_AGENT_SHEET_ALLENV_BITVOFF, rowNum, reader);	
				System.out.println("Row no executed is " + rowNum);		

				// Validate shopping Cart Page and Proceed
				sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

				if (!InputData_Communities.commPBFBundledFirstProd.equals("TV")) {
					// Click on Shop Plans for Business Internet
					sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

					if(InputData_Communities.commPBFProductTerm.equalsIgnoreCase("Monthly Term")) {
						sfdc.planSelPBF.selectMonthlyTerm();
					} else {
						// Validate 3 year and monthly term options
						sfdc.planSelPBF.validatePlanTermsOptionsForBI();
					}

					//To handle the pop up screen
					sfdc.planSelPBF.handlePBFpopup();
					// Validate discount and promo section
					if(InputData_Communities.commPBFPromo.equalsIgnoreCase("Yes")) {
						sfdc.planSelPBF.selectInternetProductType();
						//	sfdc.planSelPBF.validateDiscountAndPromoSections();

						// Select Discount product
						sfdc.planSelPBF.selectDiscountAndPromoSectionsProduct();

						sfdc.commPBFCablePlan.clickNextOnBusInt();

						//Add to Cart an AddON and proceed to shopping Cart
						sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();

						// Validate Price details of added Office 365AddOn
						//	sfdc.commPBFShopCart.validateShoppingCartPageWithAddedOff365Product();

						// Validate User lands on Shopping Cart Page with added product
						InputData_Communities.commPBFProductPrice = InputData_Communities.discountPrice;
						sfdc.commPBFShopCart.validatePriceDetailsForAddedProduct();

					} else {
						//** for monthly
						sfdc.planSelPBF.selectInternetProductType_Monthly();

						sfdc.commPBFCablePlan.clickNextOnBusInt();

						//Add to Cart an AddON and proceed to shopping Cart
						sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();

						// Validate Price details of added Office 365AddOn
						//	sfdc.commPBFShopCart.validateShoppingCartPageWithAddedOff365Product();
					}

					// Validate TCV margin
					sfdc.planSelPBF.validate_BusInt_Off365_TCV();
					//Click on Add Business Products
					sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();
				} 

				//Click on Business TV Shop Plans
				sfdc.siteSelPBF.moveToSiteSelectionPageForBusTV();

				//Select TV Product
				sfdc.commPBFTVPlan.selectTVProduct();

				// Addons To be Selected
				sfdc.commPBFTVAddons.addTVAddon();

				sfdc.commPBFCablePlan.proceedtoCheckout();

				// after adding TV product if the business internet and office 365 products needs to be added
				if (InputData_Communities.commPBFBundledFirstProd.equals("TV")) {

					//Validate shopping Cart Page and Proceed
					sfdc.commPBFShopCart.quickValidateShoppingCartPageAndProceed();

					//Click on Shop Plans for Business Internet
					sfdc.siteSelPBF.moveToSiteSelectionPageForBusInt();

					sfdc.planSelPBF.selectInternetProductType_Monthly();

					if (!InputData_Communities.commPBFOffAddOnName.equals("NA")) {

						sfdc.commPBFCablePlan.clickNextOnBusInt();

						//Add to Cart an AddON and proceed to shopping Cart
						sfdc.off365AddOnPBF.addToCartOff365AddOnProductAndProceed();

						// Validate User lands on Shopping Cart Page with added product
						//	sfdc.commPBFShopCart.validateShoppingCartPageWithAddedOff365Product();
					}

					sfdc.commPBFShopCart.validatePriceDetailsForTVBusIntOff365Product();

				} else {

					sfdc.commPBFShopCart.validatePriceDetailsForBusIntOff365TVProduct();
				}
				// Validate TCV margin
				sfdc.planSelPBF.validate_BusInt_Off365_TV_TCV();

				// Remove Product
				sfdc.commPBFShopCart.removeProduct_POC();

				rowNext = 	rowNum +1;
				InputData_QM.runMode = reader.getCellData(Constants.PBF_AGENT_SHEET_ALLENV_BITVOFF, "RunMode", rowNext);
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
		return getDataSetsRunMode(Constants.TESTDATA_COMM_PRICING_VALIDATION_FILE, Constants.PBF_AGENT_SHEET_ALLENV_BITVOFF);
	}
}
