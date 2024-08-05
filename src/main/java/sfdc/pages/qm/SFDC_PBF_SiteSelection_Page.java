package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.communities.Communities_PBF_AddNewSite_Page;
import sfdc.pages.communities.Communities_PBF_Page;

/**
 * @author Anukriti.Chawla, Date:28 July 2021
 *
 *         SFDC Persona Based Buy Flow Page
 */
public class SFDC_PBF_SiteSelection_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static Communities_PBF_AddNewSite_Page commPBFAddSite;

	public SFDC_PBF_SiteSelection_Page() {
		sf = new SFDC_AllPageObjects();
		commPBFAddSite = new Communities_PBF_AddNewSite_Page();
		methodName = "SFDC_PBF_SiteSelection@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify PBF landing Page
	 * 
	 */
	public static void moveToSiteSelectionPageForBusInt() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.scrollByCoOrdinates(2);
			if (sf.seleU.isElementDisplayed(sf.comPBFShopCart.buyBusinessProductsButton))
				sf.seleU.clickOnElement(sf.comPBFShopCart.buyBusinessProductsButton);
			//sf.seleU.wait(5000);

			if (sf.seleU.isElementDisplayed(sf.proSel.shopBusinessInternetPlansButton))
				sf.seleU.clickElementByJSE(sf.proSel.shopBusinessInternetPlansButton);
			//sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Move to Site Selection page is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify PBF landing Page
	 * 
	 */
	public static void moveToSiteSelectionPageForBusTV() throws IOException {
		try {
			//sf.seleU.wait(5000);
			sf.seleU.scrollByCoOrdinates(2);
			if (sf.seleU.isElementDisplayed(sf.comPBFShopCart.buyBusinessProductsButton))
				sf.seleU.clickOnElement(sf.comPBFShopCart.buyBusinessProductsButton);
			//sf.seleU.wait(5000);

			if (sf.seleU.isElementDisplayed(sf.proSel.shopBusinessTVPlansButton))
				sf.seleU.clickElementByJSE(sf.proSel.shopBusinessTVPlansButton);
			//sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Move to Site Selection page is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify PBF landing Page
	 * 
	 */
	public static void moveToSiteSelectionPageForDedInt() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.scrollByCoOrdinates(2);
			if (sf.seleU.isElementDisplayed(sf.comPBFShopCart.buyBusinessProductsButton))
				sf.seleU.clickOnElement(sf.comPBFShopCart.buyBusinessProductsButton);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementDisplayed(sf.proSel.shopDedicatedInternetPlansButton))
				sf.seleU.clickElementByJSE(sf.proSel.shopDedicatedInternetPlansButton);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Move to Site Selection page is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify PBF landing Page
	 * 
	 */
	public void verifyPBFLandingPage() throws IOException {
		try {

			//sf.seleU.wait(5000);

			verifyFieldDisplayed("PBF Page - Rogers Logo on Top", sf.comPBF.rogersLogo);

			verifyFieldDisplayed("PBF Page - Where Would you like your services Header", sf.siteSelPBF.letsGetStartedHeader);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying PBF landing page is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Legend section should be displayed with icon for Business Internet.
	 * 
	 */
	public void verifyLegendSection() throws IOException {
		try {

			//sf.seleU.wait(5000);

			verifyFieldDisplayed("Legends Section", sf.siteSelPBF.legendSection);

			verifyFieldDisplayed("Business Internet Icon in Legends Section", sf.siteSelPBF.businessInternetIconInLegend);

			verifyFieldDisplayed("TV for Business Icon in Legends Section", sf.siteSelPBF.businessTVIconInLegend);

			if(sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE")) {
				verifyFieldDisplayed("RDI-OnNet Icon in Legends Section", sf.siteSelPBF.rdiOnNetIconInLegend);

				verifyFieldDisplayed("RDI-NearNet Icon in Legends Section", sf.siteSelPBF.rdiNearNetIconInLegend);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Legend section is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                      Select site with Business Internet in services.
	 * 
	 */
	public void selectBusinessInternetAddress() throws IOException {
		try {

			//sf.seleU.wait(5000);
			int i=2;
			boolean bILocationFound = false;
			List<String> provinces = new ArrayList<String>();

			if (InputData_Communities.ontarioRegionProvinces.contains(InputData_Communities.commPBFSiteAddressProvince))
				provinces = InputData_Communities.ontarioRegionProvinces;
			else
				provinces = InputData_Communities.atlanticRegionProvinces;

			if (sf.seleU.isElementDisplayed(sf.siteSelPBF.businessInternetRowRadioButton)
					&& (provinces.contains(sf.seleU.getTextFromWebElement(sf.siteSelPBF.businessInternetRowProvince)))) {
				sf.seleU.clickElementByJSE(sf.siteSelPBF.businessInternetRowRadioButton);
				bILocationFound = true;
				InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.businessInternetRowSerrviceAddressValue);
				reportStatusPass(methodName + " Found a business Internet location and selected. : " + InputData_Communities.commPBFselectedAddress
						, true, true);

				sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
				sf.seleU.wait(5000);
			}
			else {
				if ( sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
					while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
						sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
						reportStatusPass(methodName + "Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
						if (sf.seleU.isElementDisplayed(sf.siteSelPBF.businessInternetRowRadioButton)
								&& (provinces.contains(sf.seleU.getTextFromWebElement(sf.siteSelPBF.businessInternetRowProvince)))) {

							sf.seleU.clickElementByJSE(sf.siteSelPBF.businessInternetRowRadioButton);
							bILocationFound = true;
							InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.businessInternetRowSerrviceAddressValue);
							reportStatusPass(methodName + " Found a business Internet location and selected. : " + InputData_Communities.commPBFselectedAddress
									, true, true);
							sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
							//sf.seleU.wait(5000);
							break;
						}
						i++;
					}
				}
			}
			if(!bILocationFound) {
				reportStatusPass(methodName + " Not able to find any Business Internet location, so will add a new site", true, true);
				Communities_PBF_Page.addNewSite();
				commPBFAddSite.fillSiteInfo();
				commPBFAddSite.verifyServiceAddressesTableLayout();
				commPBFAddSite.selectAddress();
				commPBFAddSite.confirmAndProceed();
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with Business internet icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI-ON Net in services.
	 * 
	 */
	public void selectRDIOnNetAddress() throws IOException {
		try {

			sf.seleU.wait(5000);
	
			String addressSelected = "";

			sf.seleU.scrollByCoOrdinates(3);
			if(InputData_Communities.commPBFPromo.equals("No"))
				addressSelected = selectRDIONNetWithoutPromoAddress(true);
			else if(InputData_Communities.commPBFPromo.equals("AO"))
				addressSelected = selectRDIABAONNetAddress(true);
			else if(InputData_Communities.commPBFPromo.equals("AE"))
				addressSelected = selectRDIABAEASTNetAddress(true);
			else if(InputData_Communities.commPBFPromo.equals("NN"))
        addressSelected = selectRDINearNetWithoutPromoAddress(true);
			
			
			if(addressSelected.equals("NA")) {

				reportStatusPass(methodName + "Not able to find any Dedicated Internet location, so will add a new site", true, true);
				Communities_PBF_Page.addNewSite();
				commPBFAddSite.fillSiteInfo();
				commPBFAddSite.verifyServiceAddressesTableLayout();
				commPBFAddSite.selectAddress();
				commPBFAddSite.confirmAndProceed();

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with RDI-OnNet icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI-ON Net in services - Without Promo.
	 * 
	 */

	public String selectRDIONNetWithoutPromoAddress(boolean proceed) throws IOException {
		
		boolean bILocationFound = false; int i=2;
		try {
			if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiOnNetRowRadioButton)
					&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiOnNetRowRadioButton)) {
				sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiOnNetRowRadioButton);
				bILocationFound = true;
				InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiOnNetRowSerrviceAddressValue);
				reportStatusPass(methodName + " Found a RDI-OnNet location and selected. : " + InputData_Communities.commPBFselectedAddress
						, true, true);

				if (proceed) {
					sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
					sf.seleU.wait(5000);
				}
			}
			else {
				if ( sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
					while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
						sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
						reportStatusPass(methodName + " Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
						if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiOnNetRowRadioButton)
								&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiOnNetRowRadioButton)) {
							sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiOnNetRowRadioButton);
							bILocationFound = true;
							InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiOnNetRowSerrviceAddressValue);
							reportStatusPass(methodName + " Found a RDI-OnNet location and selected. : " + InputData_Communities.commPBFselectedAddress
									, true, true);
							if (proceed) {
								sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
								sf.seleU.wait(5000);
							}
							break;
						}
						i++;
					}
				}

			}
			if(!bILocationFound) {
				reportStatusFail(methodName + " Not able to find any Dedicated Internet location", true);
				InputData_Communities.commPBFselectedAddress ="NA";
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with RDI-OnNet (Without Promo) icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
		return InputData_Communities.commPBFselectedAddress;
	}

	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI-Near Net in services - Without Promo.
	 * 
	 */

	public String selectRDINearNetWithoutPromoAddress(boolean proceed) throws IOException {
	
		boolean bILocationFound = false; int i=2;
		try {
			if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiNearNetRowRadioButton)
					&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiNearNetRowRadioButton)) {
				sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiNearNetRowRadioButton);
				bILocationFound = true;
				InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiNearNetRowSerrviceAddressValue);
				reportStatusPass(methodName + " Found a RDI-OnNet location and selected. : " + InputData_Communities.commPBFselectedAddress
						, true, true);
      if (proceed) {
					sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
					sf.seleU.wait(5000);
				}
			}
			else {
				if ( sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
					while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
						sf.seleU.wait(2000);
						sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
						reportStatusPass(methodName + " Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
						if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiNearNetRowRadioButton)
								&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiNearNetRowRadioButton)) {
							sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiNearNetRowRadioButton);
							bILocationFound = true;
							InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiNearNetRowSerrviceAddressValue);
							reportStatusPass(methodName + " Found a RDI-OnNet location and selected. : " + InputData_Communities.commPBFselectedAddress
									, true, true);
							if (proceed) {
								sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
								sf.seleU.wait(5000);
							}
							break;
						}
						i++;
					}
				}

			}
			if(!bILocationFound) {
				reportStatusFail(methodName + " Not able to find any Dedicated Internet location", true);
				InputData_Communities.commPBFselectedAddress ="NA";
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with RDI-OnNet (Without Promo) icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
		return InputData_Communities.commPBFselectedAddress;
	}

	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI-ON Net in services - ABA-ON.
	 * 
	 */

	public String selectRDIABAONNetAddress(boolean proceed) throws IOException {

		boolean bILocationFound = false; int i=2;
		try {
			if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiABAOnNetRowRadioButton)
					&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiABAOnNetRowRadioButton)) {
				sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiABAOnNetRowRadioButton);
				bILocationFound = true;
				InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiABAOnNetRowServiceAddressValue);
				reportStatusPass(methodName + " Found a RDI-OnNet ABA ON location and selected. : " + InputData_Communities.commPBFselectedAddress
						, true, true);
if (proceed) {
					sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
					sf.seleU.wait(5000);
				}
			}
			else {
				if ( sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
					while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
						sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
						reportStatusPass(methodName + " Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
						if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiABAOnNetRowRadioButton)
								&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiABAOnNetRowRadioButton)) {
							sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiABAOnNetRowRadioButton);
							bILocationFound = true;
							InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiABAOnNetRowServiceAddressValue);
							reportStatusPass(methodName + " Found a RDI-OnNet ABA ON location and selected. : " + InputData_Communities.commPBFselectedAddress
									, true, true);
							if (proceed) {
								sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
								sf.seleU.wait(5000);
							}
							break;
						}
						i++;
					}
				}

			}

			if(!bILocationFound) {
				reportStatusFail(methodName + " Not able to find any Dedicated Internet location", true);
				InputData_Communities.commPBFselectedAddress ="NA";
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with RDI-OnNet (ABA -ON) icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
		return InputData_Communities.commPBFselectedAddress;
	}

	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI-ON Net in services -ABA EAST.
	 * 
	 */
public String selectRDIABAEASTNetAddress(boolean proceed) throws IOException {
	
		boolean bILocationFound = false; int i=2;
		try {
			if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiABAEastNetRowRadioButton)
					&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiABAEastNetRowRadioButton)) {
				sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiABAEastNetRowRadioButton);
				bILocationFound = true;
				InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiABAEastNetRowServiceAddressValue);
				reportStatusPass(methodName + " Found a RDI-OnNet ABA-EAST location and selected. : " + InputData_Communities.commPBFselectedAddress
						, true, true);
	if (proceed) {
					sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
					sf.seleU.wait(5000);
				}
			
			}
			else {
				if ( sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
					while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
						sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
						reportStatusPass(methodName + " Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
						if (sf.seleU.isElementDisplayed(sf.siteSelPBF.rdiABAEastNetRowRadioButton)
								&& !sf.seleU.isElementSelected(sf.siteSelPBF.rdiABAEastNetRowRadioButton)) {
							sf.seleU.clickElementByJSE(sf.siteSelPBF.rdiABAEastNetRowRadioButton);
							bILocationFound = true;
							InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.siteSelPBF.rdiABAEastNetRowServiceAddressValue);
							reportStatusPass(methodName + " Found a RDI-OnNet ABA - EAST location and selected. : " + InputData_Communities.commPBFselectedAddress
									, true, true);
							if (proceed) {
								sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
								sf.seleU.wait(5000);
							}
							break;
						}
						i++;
					}
				}

			}

			if(!bILocationFound) {
				reportStatusFail(methodName + " Not able to find any Dedicated Internet location", true);
				InputData_Communities.commPBFselectedAddress ="NA";
			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with RDI-OnNet (ABA-East) icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
		return InputData_Communities.commPBFselectedAddress;
	}
	/**
	 * @throws IOException
	 * 
	 *                      Services available column should depict the correct icons for Business Internet.
	 * 
	 */
	public void verifyIconInServiceColumn() throws IOException {
		try {

			//sf.seleU.wait(5000);

			if (sf.siteSelPBF.servicesAvailableIconsForAllSites.size()>0)
				reportStatusPass(methodName + " Business Internet Icon is present for "
						+ sf.siteSelPBF.servicesAvailableIconsForAllSites.size() + " sites", true, true);
			else
				reportStatusFail(methodName + " No Business Internet Icon is present in Service Location column", true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Business internet icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Return Field is enabled/not
	 */
	public static boolean isFieldEnabled(String fieldName, WebElement element) throws IOException {

		boolean enabled = false;

		try {

			if (element.isEnabled()) {
				enabled = true;
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
		return enabled;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
