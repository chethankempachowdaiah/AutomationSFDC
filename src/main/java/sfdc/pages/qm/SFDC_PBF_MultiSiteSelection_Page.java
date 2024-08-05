package sfdc.pages.qm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.GetExcelData;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.communities.Communities_PBF_AddNewSite_Page;
import sfdc.pages.communities.Communities_PBF_Page;

/**
 * @author Anukriti.Chawla, Date:19 Jan 2022
 *
 *         SFDC Persona Based Buy Flow Page
 */
public class SFDC_PBF_MultiSiteSelection_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static Communities_PBF_AddNewSite_Page commPBFAddSite;
	public static HashMap<String, HashMap<String,String>> pbfAddresses = new HashMap<>();


	public SFDC_PBF_MultiSiteSelection_Page() {
		sf = new SFDC_AllPageObjects();
		commPBFAddSite = new Communities_PBF_AddNewSite_Page();
		methodName = "SFDC_PBF_MultiSiteSelection@:";
	}

	/**
	 * 
	 *  	//Ac00461038//
	 *  
	 * @throws IOException
	 * 
	 *                     Verify SIte Selection Page for Multisite
	 * 
	 */
	public void verifyMulSiteSelPage() throws IOException {
		try {

			//sf.seleU.wait(5000);

			verifyFieldDisplayed("PBF Page - Rogers Logo on Top", sf.comPBF.rogersLogo);

			verifyFieldDisplayed("PBF Page - Where Would you like your services Header", sf.siteSelPBF.letsGetStartedHeader);

			verifyFieldDisplayed("0 of 5 sites Selected label", sf.mulSiteSel.siteSelectionThreshHoldLabel);

			verifyFieldDisplayed("Review your sites message",sf.mulSiteSel.reviewSitesSelectedMessage);

			if (sf.mulSiteSel.serviceAddressCheckboxes.size()>= 5) {

				//Select 5 service addresses and check threshhold messages
				for (int i=0; i<5 ; i++) {
					sf.seleU.clickElementByJSE(sf.mulSiteSel.serviceAddressCheckboxes.get(i));
					sf.seleU.wait(1000);
				}
				reportStatusPass(methodName + " Selected 5 sites to check threshhold messages", true, true);
				sf.seleU.scrollUpByCoOrdinates();
				sf.seleU.scrollUpByCoOrdinates();
				verifyFieldDisplayed("Selected Max of 5 sites", sf.mulSiteSel.selectedMaxSitesLabel);
				verifyFieldDisplayed("Remove a selected site to add more message", sf.mulSiteSel.removeToAddNewMessage);
				verifyFieldDisplayed("You've selected maximum of 5 sites message", sf.mulSiteSel.allSiteSelectedLabel);

				//Deselect all checkboxes back
				for (int j=0; j<5 ; j++) {
					sf.seleU.clickElementByJSE(sf.mulSiteSel.serviceAddressCheckboxes.get(j));
					sf.seleU.wait(1000);
				}
				reportStatusPass(methodName + " De-Selected all the 5 sites", true, false);

			} else {
				reportStatusPass(methodName + " Sites are less than threshhold limit so skipping verifications of threshold messages", false, false);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying PBF Site Selection page for multisite is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 	//Ac00461038//
	 * @throws IOException
	 * 
	 *                      Load All the flow data from excel to HashMap for addresses
	 * 
	 */ 
	public static void getAddresses() {
		try {
			String cellDataString = "";

			InputStream dataFile = new FileInputStream(Constants.TESTDATA_COMM_FILE);
			XSSFWorkbook wb = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = wb.getSheet(Constants.PBF_ADDRESSES);
			DataFormatter df = new DataFormatter();

			for (int row = 1; row <= sheet.getLastRowNum(); row++) {
				HashMap<String,String> eachRowData = new HashMap<>();
				for (int col = 2; col < sheet.getRow(row).getLastCellNum(); col++) {
					String value = df.formatCellValue(sheet.getRow(row).getCell(col));
					eachRowData.put(sheet.getRow(0).getCell(col).toString(), value);
				}
				pbfAddresses.put(sheet.getRow(row).getCell(0).toString() 
						+ "_" + sheet.getRow(row).getCell(1).toString(), eachRowData);

			}

			dataFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 		//Ac00461038//
	 * @throws IOException
	 * 
	 *                      Select multiple sites as read from the excel
	 * 
	 */
	public void selectMultiSites() throws IOException {

		try {

			//Select number of sites as read from excel
			for (int i=1; i<= InputData_Communities.mulPBFNumOfSites ; i++) {

				HashMap<String, String> siteWiseData = InputData_Communities.sitesData.get("Site" + i);

				String productName = siteWiseData.get("Product Name");
				String typeOfAddress = siteWiseData.get("Type Of Address");
				String selectedAddress = "";
				switch(productName) {

				case "RDI" :
					selectedAddress = selectRDIAddress(typeOfAddress);
					break;

				case "BusinessInternet,TV" :
					selectedAddress = selectBusinessInternetSite(typeOfAddress);
					break;

				case "BusinessInternet" :
					selectedAddress = selectBusinessInternetSite(typeOfAddress);
					break;

				case "TV" :
					selectedAddress = selectBusinessInternetSite(typeOfAddress);
					break;

				case "TV,BusinessInternet" :
					selectedAddress = selectBusinessInternetSite(typeOfAddress);
					break;

				default :
					reportStatusFail(methodName + " Could not match the product name with existing known products",  true);
				}
				InputData_Communities.selectedAddress.put("Site" + i, selectedAddress);

			}
			sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
			sf.seleU.wait(5000);
			//sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting multisites is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 		//Ac00461038//
	 * @throws IOException
	 * 
	 *                     Add new sites /Select existing Sites as per excel
	 * 
	 */
	public void addOrSelectMultiSites() throws IOException {

		try {

			//Select number of sites as read from excel
			for (int i=1; i<= InputData_Communities.mulPBFNumOfSites ; i++) {

				HashMap<String, String> siteWiseData = InputData_Communities.sitesData.get("Site" + i);

				String productName = siteWiseData.get("Product Name");
				String typeOfAddress = siteWiseData.get("Type Of Address");
				String selectedAddress = "";

				if (InputData_Communities.mulPBFAddNewSite.contains("Site" + i)) {
					selectedAddress = addNewAddress(productName, typeOfAddress);

				} else {
					switch(productName) {

					case "RDI" :
						selectedAddress = selectRDIAddress(typeOfAddress);
						break;

					case "BusinessInternet,TV" :
						selectedAddress = selectBusinessInternetSite(typeOfAddress);
						break;

					case "BusinessInternet" :
						selectedAddress = selectBusinessInternetSite(typeOfAddress);
						break;

					case "TV" :
						selectedAddress = selectBusinessInternetSite(typeOfAddress);
						break;

					case "TV,BusinessInternet" :
						selectedAddress = selectBusinessInternetSite(typeOfAddress);
						break;

					default :
						reportStatusFail(methodName + " Could not match the product name with existing known products",  true);
					}
				}
				InputData_Communities.selectedAddress.put("Site" + i, selectedAddress);
			}

			sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
			sf.seleU.wait(5000);
			//sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting multisites is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI services.
	 * 
	 */
	public String selectRDIAddress(String typeOfAddress) throws IOException {

		String addressSelected = "NA";
		try {
			SFDC_PBF_SiteSelection_Page siteSel = new  SFDC_PBF_SiteSelection_Page();
			sf.seleU.wait(5000);


			sf.seleU.scrollByCoOrdinates(3);
			if(typeOfAddress.equalsIgnoreCase("ONNET"))
				addressSelected = siteSel.selectRDIONNetWithoutPromoAddress(false);
			else if(typeOfAddress.equalsIgnoreCase("ABAON"))
				addressSelected = siteSel.selectRDIABAONNetAddress(false);
			else if(typeOfAddress.equalsIgnoreCase("ABAEAST"))
				addressSelected = siteSel.selectRDIABAEASTNetAddress(false);
			else if(typeOfAddress.equalsIgnoreCase("NEARNET"))
				addressSelected = siteSel.selectRDINearNetWithoutPromoAddress(false);

			//If next page was reached, below code will make sure we are back on page 1
			while (isFieldEnabled("Previous Page Button", sf.comPBF.previousPageButton)) {
				sf.seleU.clickOnElement(sf.comPBF.previousPageButtonParent);
				reportStatusPass(methodName + "Clicked on Previous page in service Addresses Table" , true, true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with RDI serviceability is unscuccesfull", e);
			e.printStackTrace();
		}
		return addressSelected;
	}
	/**
	 * 
	 * 		//Ac00461038//
	 * @throws IOException
	 * 
	 *                     Select Business Internet site based on province/region
	 * 
	 */
	public String selectBusinessInternetSite(String typeOfAddress) throws IOException {

		try {
			int j=2;
			boolean bILocationFound = false;
			List<String> provinces = new ArrayList<String>();

			if (InputData_Communities.ontarioRegionProvinces.contains(typeOfAddress))
				provinces = InputData_Communities.ontarioRegionProvinces;
			else
				provinces = InputData_Communities.atlanticRegionProvinces;

			for (int i=0 ; i< sf.mulSiteSel.businessInternetRowRadioButton.size();i++) {
				if (sf.seleU.isElementDisplayed(sf.mulSiteSel.businessInternetRowRadioButton.get(i))
						&& (provinces.contains(sf.seleU.getTextFromWebElement(sf.mulSiteSel.businessInternetRowProvince.get(i)))
								&& !sf.seleU.isElementSelected(sf.mulSiteSel.businessInternetRowRadioButton.get(i)))) {
					//					WebElement fg = sf.siteSelPBF.businessInternetRowRadioButton;
					//					System.out.println(fg.getCssValue("background") + "oj");
					System.out.println(sf.seleU.isElementSelected(sf.mulSiteSel.serviceAddressCheckboxSelected.get(i)));

					if(!sf.seleU.isElementSelected(sf.mulSiteSel.serviceAddressCheckboxSelected.get(i))) {
						sf.seleU.clickElementByJSE(sf.mulSiteSel.businessInternetRowRadioButton.get(i));
						bILocationFound = true;
						InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.mulSiteSel.businessInternetRowSerrviceAddressValue.get(i));
						reportStatusPass(methodName + " Found a business Internet location and selected. : " + InputData_Communities.commPBFselectedAddress
								, true, true);
						break;
					}

				}	
			}
			if(!bILocationFound) {

				if ( sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
					while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
						sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
						reportStatusPass(methodName + "Clicked on Next page in service Addresses Table, Current Page : " + j , true, true);
						for (int k= 0 ; k<sf.mulSiteSel.businessInternetRowRadioButton.size();k++) {
							if (sf.seleU.isElementDisplayed(sf.mulSiteSel.businessInternetRowRadioButton.get(k))
									&& (provinces.contains(sf.seleU.getTextFromWebElement(sf.mulSiteSel.businessInternetRowProvince.get(k)))
											&& !sf.seleU.isElementSelected(sf.mulSiteSel.businessInternetRowRadioButton.get(k)))) {

								sf.seleU.clickElementByJSE(sf.mulSiteSel.businessInternetRowRadioButton.get(k));
								bILocationFound = true;
								InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.mulSiteSel.businessInternetRowSerrviceAddressValue.get(k));
								reportStatusPass(methodName + " Found a business Internet location and selected. : " + InputData_Communities.commPBFselectedAddress
										, true, true);

								break;
							}
						}
						j++;
						if (bILocationFound)
							break;
					}

				}

			}

			if(!bILocationFound) {
				reportStatusFail(methodName + " Not able to find any Business Internet location", true);
				InputData_Communities.commPBFselectedAddress = "NA";
			}
			//If next page was reached, below code will make sure we are back on page 1
			while (isFieldEnabled("Previous Page Button", sf.comPBF.previousPageButton)) {
				sf.seleU.clickOnElement(sf.comPBF.previousPageButtonParent);
				reportStatusPass(methodName + "Clicked on Previous page in service Addresses Table" , true, true);
			}


		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting address with Business internet icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
		return InputData_Communities.commPBFselectedAddress;
	}
	/**
	 * @throws IOException
	 * 
	 *                      Select site with RDI services.
	 * 
	 */
	public String addNewAddress(String product, String regionOrPromo) throws IOException {

		String addressSelected = "NA";
		try {
			Communities_PBF_Page.addNewSite();
			HashMap<String, String> newSiteData = pbfAddresses.get(product + "_" + regionOrPromo);
			InputData_Communities.mulPBFSiteAddressStreetNo = newSiteData.get("Street No");
			InputData_Communities.mulPBFSiteAddressStreetName =  newSiteData.get("Street Name");
			InputData_Communities.mulPBFSiteAddressStreetType =  newSiteData.get("Street Type");
			InputData_Communities.mulPBFSiteAddressCity =  newSiteData.get("City");
			InputData_Communities.mulPBFSiteAddressPostalCode =  newSiteData.get("Postal Code");
			InputData_Communities.mulPBFSiteAddressProvince =  newSiteData.get("Province");
			sf.seleU.clearTextByMouseAction(sf.comPBFAddSite.streetNoInput);
			sf.seleU.clearTextByMouseAction(sf.comPBFAddSite.streetNameInput);
			sf.seleU.clearTextByMouseAction(sf.comPBFAddSite.streetTypeInput);
			sf.seleU.clearTextByMouseAction(sf.comPBFAddSite.cityInput);
			sf.seleU.clearTextByMouseAction(sf.comPBFAddSite.postalCodeInput);

			sf.seleU.clearAndEnterText(sf.comPBFAddSite.streetNoInput, InputData_Communities.mulPBFSiteAddressStreetNo);
			sf.seleU.clearAndEnterText(sf.comPBFAddSite.streetNameInput, InputData_Communities.mulPBFSiteAddressStreetName);
			sf.seleU.clearAndEnterText(sf.comPBFAddSite.streetTypeInput, InputData_Communities.mulPBFSiteAddressStreetType);
			sf.seleU.clearAndEnterText(sf.comPBFAddSite.cityInput, InputData_Communities.mulPBFSiteAddressCity);
			reportStatusPass(methodName + " Entered :\n Street no- " + InputData_Communities.mulPBFSiteAddressStreetNo
					+ "\n Street Type- " + InputData_Communities.mulPBFSiteAddressStreetType
					+ "\n Street Name- " + InputData_Communities.mulPBFSiteAddressStreetName
					+ "\n City- " + InputData_Communities.mulPBFSiteAddressCity
					, true, true);


			//Fill Province
			sf.seleU.clickOnElement( sf.comPBFAddSite.provinceInput);
			sf.seleU.wait(2000);
			sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[.='" +  InputData_Communities.mulPBFSiteAddressProvince + "']/parent::div")));


			//Fill Valid postal code
			sf.seleU.clearAndEnterText(sf.comPBFAddSite.postalCodeInput, InputData_Communities.mulPBFSiteAddressPostalCode);
			sf.seleU.enterText(sf.comPBFAddSite.postalCodeInput,  Keys.TAB);
			reportStatusPass(methodName + " Entered Province- " + InputData_Communities.mulPBFSiteAddressProvince 
					+ " and Postal code- " + InputData_Communities.mulPBFSiteAddressPostalCode, false, false);

			sf.seleU.wait(10000);
			//Click on Search my site
			sf.seleU.clickElementByJSE(sf.comPBFAddSite.searchMySiteButton);
			sf.seleU.wait(4000);
			reportStatusPass(methodName + " Clicked on Search My Site button", true, true);

			if (product.contains("RDI")) {
				sf.seleU.clickElementByJSE(sf.comPBF.filterInputBoxFibre);
				sf.seleU.wait(5000);
				sf.seleU.enterText(sf.comPBF.filterInputBoxFibre, InputData_Communities.mulPBFSiteAddressStreetName);
				reportStatusPass(methodName + " Searched " + InputData_Communities.mulPBFSiteAddressStreetName + " in filter by keywords Box", true, true);

				sf.seleU.wait(5000);
				addressSelected = sf.seleU.getTextFromWebElement(sf.comPBFAddSite.serviceAddressValuesFibre.get(0));
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressFibreRadioButtons.get(0));

			} else {
				sf.seleU.clickElementByJSE(sf.comPBF.filterInputBox);
				sf.seleU.wait(5000);
				sf.seleU.enterText(sf.comPBF.filterInputBox, InputData_Communities.mulPBFSiteAddressStreetName);
				reportStatusPass(methodName + " Searched " + InputData_Communities.mulPBFSiteAddressStreetName + " in filter by keywords Box", true, true);

				sf.seleU.wait(5000);
				addressSelected = sf.seleU.getTextFromWebElement(sf.comPBFAddSite.serviceAddressValues.get(0));
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));

			}

			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Selected first address from list : "
					+ addressSelected, true, true);

			sf.seleU.clickElementByJSE(sf.comPBFAddSite.confirmAddressButton);
			sf.seleU.wait(3000);

			sf.seleU.clickElementByJSE(sf.comPBFAddSite.saveSiteButton);
			sf.seleU.wait(13000);
			reportStatusPass(methodName + " Clicked on confirm and save site address", true, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Adding New address is unscuccesfull", e);
			e.printStackTrace();
		}
		return addressSelected;
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
