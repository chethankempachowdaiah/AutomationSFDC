package sfdc.pages.service;

import java.io.IOException;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_ServiceAccountRelated_Page extends MyListener {

	/**
	 *  @author Pankaj Agarwal, Date 06/April/2020
	 * @throws IOException
	 * 
	 *                     Verify Service Related Account Information
	 * 
	 * 
	 */

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public String methodName;

	public SFDC_ServiceAccountRelated_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_ServiceAccount_Related@: ";
	}

	/**

	 * @throws IOException
	 * 
	 *                     1. Click on Service Account In Order Details TAb
	 * 
	 *                     2. Click on Service Account Related button
	 *                     
	 *                     3. Click on dedicated Internet Product in service assets
	 * 
	 * 
	 */
	public void clickOnDedicatedInternetProductInServiceAsset(Hashtable<String, String> dataTable) throws IOException {
		try {
			int serviceAssetList = 0; int j = 0;
			// Click on Related tab under account view
			sf.seleU.hardwait(1000);

			sf.seleU.clickElementByJSE(sf.saccRelated.serviceRelatedTab);	
			reportStatusPass(methodName + " Clicked on related tab in Service Accounts Page", true, false);
			sf.seleU.hardwait(3000);

			// click on View All In Service Assets OF Service Account
			sf.seleU.clickElementByJSE(sf.saccRelated.serviceAssetViewAllClick.get(0));
			reportStatusPass(methodName + " Clicked on New Opportunity Button", true, false);
			sf.seleU.wait(2000);

			// click on Asset Name Sort
			sf.seleU.clickElementByJSE(sf.saccRelated.assetNameSort);
			reportStatusPass(methodName + " Clicked on Asset name Sort", true, false);
			sf.seleU.wait(2000);

			//			// Scroll to last element until the page end
			//			int  j =0;
			//			while(j<4) {	
			//			sf.seleU.waitElementToBeVisible(sf.saccRelated.productItemsNameInServiceAssetList.get(sf.saccRelated.productItemsNameInServiceAssetList.size()-1));
			//			sf.seleU.ScrolltoElement(sf.saccRelated.productItemsNameInServiceAssetList.get(sf.saccRelated.productItemsNameInServiceAssetList.size()-1));
			//			j++;
			//			}

			serviceAssetList = sf.saccRelated.productItemsNameInServiceAssetList.size();
			// Start the index from the last row as we need from the last record.
			for (int i = 0 ; i < serviceAssetList; i++) {
				sf.seleU.wait(1000);

				// Matching with RDI Product Name With the product which was added during the
				// intial stage
				// on rogers business solution page


				if (sf.seleU.getTextFromWebElement(sf.saccRelated.productItemsNameInServiceAssetList.get(i)).trim()
						.contains(dataTable.get("Dedicated_Internet_Product").trim())) {

					// 	If the product is matched then pick up the next product for asset verification
					//					j = i;
					//	
					//				 if (sf.seleU.getTextFromWebElement(sf.saccRelated.productItemsNameInServiceAssetList.get(j+1)).trim()
					//							.contains(dataTable.get("Dedicated_Internet_Product").trim())) {

					reportStatusPass(
							methodName + "Product is found in the Service Asset items list " + 
									sf.seleU.getTextFromWebElement(sf.saccRelated.productItemsNameInServiceAssetList.get(i)),
									true, true);

					sf.seleU.clickElementByJSE(sf.saccRelated.productItemsNameInServiceAssetList.get(i));
					sf.seleU.hardwait(1000);				
					break;
					//			}
				}


			}

		} catch (Throwable e) {
			reportStatusFail(" Selecting product from service asset is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     Verify the RDI products and extract the product speed
	 * 
	 * 
	 */
	public void verifyAssetDetailsForCable(Hashtable<String, String> dataTable, String accountType) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(8000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.saccRelated.serviceAccountRelatedTab);
			sf.seleU.wait(4000);
			reportStatusPass(methodName + " Clicked on Account Related Tab", true, false);


			sf.seleU.wait(8000);

			// Click on asset items view all, for billing ans service asset view all click locator is different
			if(accountType.equals("billing")) {
				sf.seleU.clickElementByJSE(sf.saccRelated.billing_AssetViewAll);
			} else {
				sf.seleU.clickElementByJSE(sf.saccRelated.service_AssetViewAll);
			}

			reportStatusPass(methodName + " Clicked on view all", true, false);
			sf.seleU.wait(2000);

			// Verify all the below once for IBLC
			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {
				filterOrderProductItemsAndVerify(dataTable.get("IBLC Product_1").split(",")[0], dataTable.get("IBLC_Lines").split(",")[0]);
				filterOrderProductItemsAndVerify(dataTable.get("IBLC Product_1").split(",")[1], dataTable.get("IBLC_Lines").split(",")[1]);
				filterOrderProductItemsAndVerify(dataTable.get("Equipment_Details_Name"), dataTable.get("Equipment_Details_Quantity"));
				filterOrderProductItemsAndVerify(dataTable.get("Installation_Detail_Product_Name").split(",")[1], dataTable.get("Line_And_Jack_Qty").split(",")[1]);
				//			filterOrderProductItemsAndVerify(dataTable.get("Installation_Detail_Product_Name").split(",")[0], "1"); // change it
				//			filterOrderProductItemsAndVerify(dataTable.get("Long Distance Plan"), dataTable.get("Long Distance Quantity"));
			}

			// Verify Tv Product product 
			if(!dataTable.get("TV Product Name").trim().equals("NA")) {

				filterOrderProductItemsAndVerify(dataTable.get("Internet Product Name"), "1");
				String tvAddOnProductArray[] = dataTable.get("Rogers TV AddOn Product").split(",");
				//		String tvAddOnProductQuantityArray[] = dataTable.get("Rogers TV AddOn Product").split(",");

				for(int i =0; i<tvAddOnProductArray.length; i++) {	
					filterOrderProductItemsAndVerify(tvAddOnProductArray[i], "1");
				}
			}

			// Verify Internet Product product 

			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {
				filterOrderProductItemsAndVerify(dataTable.get("Internet Product Name"), "1");
				filterOrderProductItemsAndVerify(dataTable.get("Internet Equipment Details"), "1");
				
				// only for the internet product with advantage wifi service
				if(!dataTable.get("Internet_wifi_Service").trim().equals("NA")) {
					String wifi_ServiceProduct[] = dataTable.get("Internet_wifi_Service").split(",");
					for(int i = 0; i<wifi_ServiceProduct.length; i++) {
						filterOrderProductItemsAndVerify(wifi_ServiceProduct[i], "1");
					}
				}
			}

			if(!dataTable.get("Office 365 AddOn").trim().equals("NA") && !accountType.equals("billing")) {

				String office365ProductArray[] = dataTable.get("Office 365 AddOn").split(",");
				//			String office365ProductQuantityArray[] = dataTable.get("Office 365 AddOn Quantity").split(",");
				for(int i = 0; i<office365ProductArray.length; i++) {
					filterOrderProductItemsAndVerify(office365ProductArray[i], "1");

				}	
			}


		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}

	/**PA PI02 - SP01
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     Verify the RDI products and extract the product speed
	 * 
	 * 
	 */
	public void verifyAssetDetailsForRDI(Hashtable<String, String> dataTable, String accountType) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(8000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.saccRelated.serviceAccountRelatedTab);
			sf.seleU.wait(4000);
			reportStatusPass(methodName + " Clicked on Account Related Tab", true, false);
			sf.seleU.wait(8000);

			// Click on asset items view all, for billing ans service asset view all click locator is different
			if(accountType.equals("billing")) {
				sf.seleU.clickElementByJSE(sf.saccRelated.billing_AssetViewAll);
			} else {
				sf.seleU.clickElementByJSE(sf.saccRelated.service_AssetViewAll);
			}

			reportStatusPass(methodName + " Clicked on view all", true, false);
			sf.seleU.wait(2000);

			// Verify all the below once for RDI
			filterOrderProductItemsAndVerify(sf.omData.dedicatedInternetProduct, "1");
			filterOrderProductItemsAndVerify(sf.omData.advancedNetworks, "1");
			filterOrderProductItemsAndVerify("Terminating Equipment", "1");

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List for RDI", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related Tab
	 * 
	 *                     Click on view all in product items
	 * 
	 *                     Filter and verify all the product from order product items
	 * 
	 * 
	 */
	public void filterOrderProductItemsAndVerify(String expected_Product_Name, String expected_Quantity) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			boolean isFound = false; int quantity = 0; String actQuantity; 
			int index = 0;

			//			sf.seleU.waitElementToBeVisible(sf.orRelated.orderProductViewALLClick);
			//			// Click on related account viewAll in contact details page
			//			sf.seleU.clickElementByJSE(sf.orRelated.orderProductViewALLClick);
			//			reportStatusPass(methodName + "Clicked on the Related Accounts View all", true, false);
			//			sf.seleU.wait(2000);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterClick);
			reportStatusPass(methodName + "Clicked on related accounts Quick Filter button", true, false);
			sf.seleU.wait(1000);

			// Enter the account name to be filtered
			sf.seleU.clearAndEnterText(sf.saccRelated.assetProductInputEnter, expected_Product_Name);
			reportStatusPass(methodName + "Enter the Account name as " + expected_Product_Name, true, false);
			sf.seleU.hardwait(3000);

			// Click on apply button after entering the account name
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterApplyButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Clicked on quick filter apply button ", true, false);

			// Click on Quick filter hide button
			sf.seleU.clickElementByJSE(sf.cd.contactHideFilterClick);

			for (int i = 0; i < sf.orRelated.orderProductNameInFilteredTable.size(); i++) {
				sf.seleU.wait(1000);

				// Matching with Order Product Name Abbreviation type and extract the name and the quantity

				if (sf.seleU.getTextFromWebElement(sf.orRelated.orderProductNameInFilteredTable.get(i)).trim()
						.contains(expected_Product_Name.trim())) {

					if(sf.orRelated.orderProductNameInFilteredTable.size() > 0) {
						quantity = quantity + 1;

						isFound = true;
						index = i;

					} else{
						reportStatusFail(methodName
								+ "Record is Not found in the order product list " + expected_Product_Name, true);
					}
				}
			}

			if(isFound == true) {
				System.out.println(expected_Quantity);
				if(quantity == Integer.parseInt(expected_Quantity.trim())) {
					reportStatusPass(methodName
							+ "Record is found in the order product list "
							+ sf.seleU.getTextFromWebElement(sf.orRelated.orderProductNameInFilteredTable.get(index)) + " with quantity as "
							+ quantity, true, true);
				}  else {
					reportStatusFail(methodName
							+ "Quantity is not matched", true);
				}
			}  else{
				reportStatusFail(methodName
						+ "Record is Not found in the order product list for " + expected_Product_Name, true);
			}
		}
		catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Asset List", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Asset filed attribute values for RDI
	 * 

	 */
	public void verifyRDI_AttributeValue(Hashtable<String, String> dataTable) throws IOException {

		try {
			sf.seleU.hardwait(2000);
			sf.seleU.switchToDefaultContent();

			// verify RDI Attributes
			sf.seleU.waitElementToBeVisible(sf.saccRelated.rdiIframe);
			sf.seleU.switchToFrame(sf.saccRelated.rdiIframe);
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(sf.saccRelated.rdiSpeedText);
			verifyFieldValue("RDI Speed", sf.saccRelated.rdiSpeedText, sf.dataInput.quoteInternetUploadSpeed);
			verifyFieldPresent("OfferType ", sf.saccRelated.rdiOfferTypeText);
			verifyFieldValue("PortType ", sf.saccRelated.rdiPortTypeText, "Dedicated");
			verifyFieldValue("Port Speed ", sf.saccRelated.rdiPortSpeedText, "Auto");
			sf.seleU.wait(1000);
			verifyFieldValue("EVC", sf.saccRelated.rdiEVCText, sf.dataInput.evcID);
			verifyFieldValue("Contract Term", sf.saccRelated.rdiContractTermText, sf.omData.contractTerms);
			verifyFieldPresent("Spec Sheet", sf.saccRelated.rdiSpecSheetText);
			sf.seleU.wait(1000);
			//  Verify RDI specsheet Attribute
			sf.seleU.ScrolltoElement(sf.saccRelated.rdiEncapsulationTypeText);
			verifyFieldPresent("Encapsulation Type ", sf.saccRelated.rdiEncapsulationTypeText);
			sf.seleU.hardwait(1000);

			verifyFieldPresent("Port Speed ", sf.saccRelated.rdiProductCodeText);
			verifyFieldValue("DNS Required ", sf.saccRelated.rdiDNSText, sf.omData.dnsRequired);
			verifyFieldValue("VLanID ", sf.saccRelated.vLanIDText, sf.omData.vLANID);

			sf.seleU.hardwait(1000);

			//			if(isElementDisplayed(sf.saccRelated.rdiIPv4WANText)) {
			//				sf.seleU.ScrolltoElement(sf.saccRelated.rdiIPv4WANText);
			//				verifyFieldValue("IP Version", sf.saccRelated.rdiIPVersionText, dataTable.get("iPversion"));
			//				verifyFieldValue("IPv4 WAN Block Assignment ", sf.saccRelated.rdiIPv4WANText, dataTable.get("ipV4WAnBlockSpecSheet"));
			//				verifyFieldValue("IPv4 LAN Block Assignment ", sf.saccRelated.rdiIPv4LANText, dataTable.get("ipV4LAnBlockSpecSheet"));
			//			}
			if(sf.omData.iPversion.trim().equals("IPv4")){
				verifyFieldValue("Ip Version ",sf.saccRelated.rdiIPVersionText, sf.omData.iPversion);
				verifyFieldValue("IpV4 WanBlock ",sf.saccRelated.rdiIPv4WANText, sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IpV4 LanBlock ",sf.saccRelated.rdiIPv4LANText, sf.omData.ipV4LAnBlockSpecSheet);
			} else if (sf.omData.iPversion.equals("IPv6")) {
				verifyFieldValue("Ip Version ",sf.saccRelated.rdiIPVersionText, sf.omData.iPversion);
				verifyFieldValue("IpV6 WanBlock ",sf.saccRelated.rdiIPv6WANText, sf.omData.ipV6WAnBlockSpecSheet);
				verifyFieldValue("IpV6 LanBlock ",sf.saccRelated.rdiIPv6LANText, sf.omData.ipV6LAnBlockSpecSheet);
			}	else {
				verifyFieldValue("Ip Version ",sf.saccRelated.rdiIPVersionText, sf.omData.iPversion);
				verifyFieldValue("IpV4 WanBlock ",sf.saccRelated.rdiIPv4WANText, sf.omData.ipV4WAnBlockSpecSheet);
				verifyFieldValue("IpV4 LanBlock ",sf.saccRelated.rdiIPv4LANText, sf.omData.ipV4LAnBlockSpecSheet);
				verifyFieldValue("IpV6 WanBlock ",sf.saccRelated.rdiIPv6WANText, sf.omData.ipV6WAnBlockSpecSheet);
				verifyFieldValue("IpV6 LanBlock ",sf.saccRelated.rdiIPv6LANText, sf.omData.ipV6LAnBlockSpecSheet);
			}	

			sf.seleU.wait(1000);
		} catch (Throwable e) {
			reportStatusFail(" Error in verifying asset value for rdi attribute", e);
			e.printStackTrace();
		}
	}



	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (isElementDisplayed(element)) {
				reportStatusPass(
						fieldName + " is present" + " with value as " + getTextFromWebElement(element), true,
						false);
			} else {
				reportStatusFail(
						fieldName + " is not present" ,
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			sf.seleU.waitElementToBeVisible(element);
			if (element.isDisplayed()) {
				sf.seleU.highLightElementYellow(element);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public String getTextFromWebElement(WebElement element) throws Exception {
		String gettext = null;
		try {
			sf.seleU.waitElementToBeVisible(element);
			gettext = element.getText();
			sf.seleU.highLightElementYellow(element);
			if (gettext.length() == 0) {
				gettext = element.getAttribute("value");
			}
			logger.info("Element text is: " + gettext);

		} catch (NoSuchElementException e) {
			logger.info("No element with name " + element + "was found.");
		}
		return gettext;
	}


}
