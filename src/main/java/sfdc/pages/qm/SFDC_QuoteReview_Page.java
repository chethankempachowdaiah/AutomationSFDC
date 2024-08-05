package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 15/oct/2020
 * 
 *         SFDC Quote Review Page ( Create Quote By GBJ> Add product> Checkout>
 *         Quote Review Page)
 *
 */
public class SFDC_QuoteReview_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_QuoteReview_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_GBJ_Quote_Review@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Add Free Installation Button
	 * 
	 *                     Verify Free Installation
	 */
	public void addFreeInstallationInGBJ() throws IOException {
		try {

			sf.seleU.scrollByCoOrdinates(1);

			if (Global.dataInput.quoteProductName.contains("WiFi")) {
				verifyNoFreeInstallationForWifi();
			} else {

				// Click on Add Free Installation Button
				sf.seleU.clickElementByJSE(sf.quoteReview.addFreeInstallationButton);
				reportStatusPass(methodName + "Clicked on Free Installation", true, false);
				sf.seleU.wait(15000);

				// Verify Free Installation
				verifyFieldValue("Free Installation", sf.quoteReview.freeInstallationText, "Free Installation");
				verifyFieldValue("Free Installation Price", sf.quoteReview.freeInstallationPriceText, "0.00");
				// verifyFieldValue("One Time Free Installation",
				// sf.quoteReview.oneTimeFeesTotal, ".00");

			}
		} catch (Throwable e) {
			reportStatusFail("Error while Adding Free Installation ", e);
			e.printStackTrace();

		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verified No free Installation for WiFI/Wifi pro
	 */
	public void verifyNoFreeInstallationForWifi() throws IOException {
		try {

			sf.seleU.scrollByCoOrdinates(1);

			// Verified No free Installation for WiFI/Wifi pro
			if (!sf.seleU.isElementPresent(sf.quoteReview.addFreeInstallationButton)) {
				reportStatusPass(methodName + "Verified No free Installation for " + Global.dataInput.quoteProductName,
						true, false);
			} else {
				reportStatusFail("Error while Verifying No Free Installation for " + Global.dataInput.quoteProductName,
						true);
			}

		} catch (Throwable e) {
			reportStatusFail("Error while Verifying Free Installation ", e);
			e.printStackTrace();

		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify Product Details Section
	 * 
	 *                     Verify Account Information Section
	 * 
	 *                     Verify Order Summary Section
	 * 
	 *                     Verify Installtion Fee
	 */
	public void verifyDetailsinQuoteReview(Hashtable<String, String> dataTable, boolean verifyAccounInfo)
			throws IOException {
		try {

			// Verify Account Information Section
			sf.seleU.clickElementByJSE(sf.quoteReview.accountInformationHeader);
			reportStatusPass(methodName + "Clicked on Account Information Header", true, false);
			
			// ********Verify Credit Check required text*******************************//
						verifyFieldPresent("Credit Check Required Field Text ", sf.quoteReview.creditCheckRequiredText);
						if (sf.seleU.isElementPresent(sf.quoteReview.creditCheckRequiredText)) {
							if (sf.seleU.getTextFromWebElement(sf.quoteReview.creditCheckRequiredText).trim()
									.equals("Credit check Required")) {
								Global.dataInput.quoteReviewcreditCheckFlag = true;
								reportStatusPass(methodName + "Credit Check is required", true, false);
							} else {
								reportStatusPass(methodName + "Credit Check Not required", true, false);
							}
						}
			
			
			sf.dataInput.primaryContact = sf.seleU.getTextFromWebElement(sf.quoteReview.contactNameText.get(0)) ;
			sf.dataInput.serviceLocation = sf.seleU.getTextFromWebElement(sf.quoteReview.contactNameText.get(1)) + " "
					+ sf.seleU.getTextFromWebElement(sf.quoteReview.contactEmailIdText.get(1)) 
					+ " " + sf.seleU.getTextFromWebElement(sf.quoteReview.contactZipCodeText.get(1));
			
			sf.dataInput.businessAccName = Global.dataInput.businessAccountName;
			sf.dataInput.selectedProduct = Global.dataInput.tvProductName;
			
			
			
			if (verifyAccounInfo) {

				verifyFieldValue("Account Name prepared for", sf.quoteReview.preparedForAccountName,
						Global.dataInput.businessAccountName);
				verifyFieldValue("Attention-Contact Name", sf.quoteReview.contactNameText.get(0),
						Global.dataInput.accountContact);
				verifyFieldValue("Service Address", sf.quoteReview.contactNameText.get(1),
						Global.dataInput.addressStreet);

				verifyFieldValue("Attention-Contact Email", sf.quoteReview.contactEmailIdText.get(0),
						Global.dataInput.contactEmailAddress);
				verifyFieldValue("Address City", sf.quoteReview.contactEmailIdText.get(1),
						Global.dataInput.addressCity);

				verifyFieldValue("Service Address Zip", sf.quoteReview.contactZipCodeText.get(1),
						Global.dataInput.addressPostalCode.replaceAll(" ", ""));
			}

			// Verify Product Details Section
			if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
				verifyFieldValue("Product Details TV", sf.quoteReview.tvProductDetails, Global.dataInput.tvProductName);

				// Verify Order Summary Section
				sf.seleU.scrollByCoOrdinates(2);

				verifyFieldValue("Order Summary: Product Details TV", sf.quoteReview.productsDetailsInOrder.get(0),
						Global.dataInput.tvProductName);

				// verifyFieldValue("Order Summary: TV ADD-ONS",
				// sf.quoteReview.tvAddOnsText.get(0),
				// dataTable.get("Rogers TV AddOn Product"));

				verifyFieldValue("Order Summary: TV-Price", sf.quoteReview.productsDetailsInOrder.get(0),
						dataTable.get("Recurring  Charge TV"));
			}

			else if (dataTable.get("Product _Type").equals(InputData.internetProduct)) {
				verifyFieldValue("Product Details Internet", sf.quoteReview.internetProductDetails,
						Global.dataInput.quoteProductName);

				// Verify Order Summary Section
				sf.seleU.scrollByCoOrdinates(2);

				verifyFieldValue("Order Summary: Product Details Internet",
						sf.quoteReview.productsDetailsInOrder.get(0), Global.dataInput.quoteProductName);
				verifyFieldValue("Order Summary: COLLABORATION ADD-ONS",
						sf.quoteReview.microsoftCollaorationText.get(0), dataTable.get("Office 365 AddOn"));

				verifyFieldValue("Order Summary:  Internet-Price", sf.quoteReview.productsDetailsInOrder.get(0),
						dataTable.get("Recurring Charge Internet"));

				verifyFieldValue("Order Summary:  office 365-Price", sf.quoteReview.microsoftCollaorationText.get(2),
						dataTable.get("Office 365 Charge"));

			} else if (dataTable.get("Product _Type").contains(InputData.internetProduct)
					&& dataTable.get("Product _Type").contains(InputData.tvProduct)) {

				verifyFieldValue("Product Details Internet", sf.quoteReview.internetProductDetails,
						Global.dataInput.quoteProductName);

				verifyFieldValue("Product Details TV", sf.quoteReview.tvProductDetails, Global.dataInput.tvProductName);

				// Verify Order Summary Section
				sf.seleU.scrollByCoOrdinates(2);

				verifyFieldValue("Order Summary: Product Details Internet",
						sf.quoteReview.productsDetailsInOrder.get(0), Global.dataInput.quoteProductName);
				verifyFieldValue("Order Summary: Product Details TV", sf.quoteReview.productsDetailsInOrder.get(1),
						Global.dataInput.tvProductName);
				verifyFieldValue("Order Summary: COLLABORATION ADD-ONS",
						sf.quoteReview.microsoftCollaorationText.get(0), dataTable.get("Office 365 AddOn"));

				// verifyFieldValue("Order Summary: TV ADD-ONS",
				// sf.quoteReview.tvAddOnsText.get(0),
				// dataTable.get("Rogers TV AddOn Product"));

				verifyFieldValue("Order Summary:  Internet-Price", sf.quoteReview.productsDetailsInOrder.get(0),
						dataTable.get("Recurring Charge Internet"));
				verifyFieldValue("Order Summary: TV-Price", sf.quoteReview.productsDetailsInOrder.get(1),
						dataTable.get("Recurring  Charge TV"));
				verifyFieldValue("Order Summary:  office 365-Price", sf.quoteReview.microsoftCollaorationText.get(2),
						dataTable.get("Office 365 Charge"));
			}
			if (verifyAccounInfo) {

				// Verify Installtion Fee
				sf.seleU.scrollByCoOrdinates(1);

				verifyFieldValue("Internet Installation Fee", sf.quoteReview.internetInstallationFeeText.get(0),
						dataTable.get("ONE TIME TCV"));
				verifyFieldValue("One Time Total Fee", sf.quoteReview.oneTimeFeesTotal,
						dataTable.get("ONE TIME TCV").split("\\.")[0]);
			}
			sf.seleU.wait(5000);

		} catch (

		Throwable e) {
			reportStatusFail("Error in Reviewing Quote Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify Product Details Section
	 * 
	 *                     Verify Account Information Section
	 * 
	 *                     Verify Order Summary Section
	 * 
	 *                     Verify Installtion Fee
	 */
	public void verifyDetailsinQuoteReviewWithDiffProduct(Hashtable<String, String> dataTable, boolean verifyAccounInfo)
			throws IOException {
		try {

			// Verify Account Information Section
			sf.seleU.waitElementToBeVisible(sf.quoteReview.accountInformationHeader);
			sf.seleU.clickElementByJSE(sf.quoteReview.accountInformationHeader);
			reportStatusPass(methodName + "Clicked on Account Information Header", true, false);

			if (verifyAccounInfo) {

				verifyFieldValue("Account Name prepared for", sf.quoteReview.preparedForAccountName,
						Global.dataInput.businessAccountName);
				verifyFieldValue("Attention-Contact Name", sf.quoteReview.contactNameText.get(0),
						Global.dataInput.accountContact);
				verifyFieldValue("Service Address", sf.quoteReview.contactNameText.get(1),
						Global.dataInput.addressStreet);

				verifyFieldValue("Attention-Contact Email", sf.quoteReview.contactEmailIdText.get(0),
						Global.dataInput.contactEmailAddress);
				verifyFieldValue("Address City", sf.quoteReview.contactEmailIdText.get(1),
						Global.dataInput.addressCity);

				verifyFieldValue("Service Address Zip", sf.quoteReview.contactZipCodeText.get(1),
						Global.dataInput.addressPostalCode.replaceAll(" ", ""));
			}

			// Verify Product Details Section
			if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
				verifyFieldValue("Product Details TV", sf.quoteReview.tvProductDetails, Global.dataInput.tvProductName);
			} else {
				verifyFieldValue("Product Details Internet", sf.quoteReview.internetProductDetails,
						Global.dataInput.quoteProductName);
				verifyFieldValue("Product Details TV", sf.quoteReview.tvProductDetails,
						dataTable.get("TV Product Name"));

			}

			// Verify Order Summary Section
			sf.seleU.scrollByCoOrdinates(2);

			if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {

				verifyFieldValue("Order Summary: Product Details TV", sf.quoteReview.productsDetailsInOrder.get(0),
						Global.dataInput.tvProductName);

				verifyFieldValue("Order Summary: TV-Price", sf.quoteReview.productsDetailsInOrder.get(0),
						dataTable.get("Recurring  Charge TV"));

			} else {
				// verify the product name
				verifyFieldValue("Order Summary: Product Details Internet", sf.quoteReview.productsDetailInOrder.get(0),
						dataTable.get("Internet Product Name"));

				verifyFieldValue("Order Summary: Product Details TV", sf.quoteReview.productsDetailInOrder.get(2),
						dataTable.get("TV Product Name"));

				// Verify the office 365 quantity and name
				if (!dataTable.get("Office 365 AddOn").equals("NA")) {

					String officeAddOnQuantityArray[] = dataTable.get("Office 365 AddOn Quantity").split(",");
					String officeAddOnArray[] = dataTable.get("Office 365 AddOn").trim().split(",");
					List<String> arList = new ArrayList<String>(Arrays.asList(officeAddOnArray));

					// verify the Tv add on Product Name
					for (int i = 0; i < sf.quoteReview.microsoftCollaorationText.size(); i = i + 3) {
						for (int j = 0; j < arList.size(); j++) {
							if (sf.seleU.getTextFromWebElement(sf.quoteReview.microsoftCollaorationText.get(i))
									.equals(officeAddOnArray[j].trim())
									&& sf.seleU
											.getTextFromWebElement(sf.quoteReview.microsoftCollaorationText.get(i + 1))
											.trim().equals(officeAddOnQuantityArray[j].trim())) {

								reportStatusPass(methodName + " Selected office 365 product adds on is : "
										+ officeAddOnArray[j] + " with quantity as " + officeAddOnQuantityArray[j],
										true, true);
								break;
							}
						}
					}
				}

				// Verify the tv adds on quantity and name
				if (!dataTable.get("Rogers TV AddOn Product").equals("NA")) {

					String tvAddOnQuantityArray[] = dataTable.get("Rogers TV AddOn Quantity").split(",");
					String tvAddOnArray[] = dataTable.get("Rogers TV AddOn Product").trim().split(",");
					List<String> arList = new ArrayList<String>(Arrays.asList(tvAddOnArray));

					// verify the Tv add on Product Name
					for (int i = 0; i < sf.quoteReview.tvAddOnsText.size(); i = i + 3) {
						for (int j = 0; j < arList.size(); j++) {
							if (sf.seleU.getTextFromWebElement(sf.quoteReview.tvAddOnsText.get(i))
									.equals(tvAddOnArray[j].trim())
									&& sf.seleU.getTextFromWebElement(sf.quoteReview.tvAddOnsText.get(i + 1)).trim()
											.equals(tvAddOnQuantityArray[j].trim())) {

								reportStatusPass(methodName + " Selected tv adds on is : " + tvAddOnArray[j]
										+ " with quantity as " + tvAddOnQuantityArray[j], true, true);
								break;
							}
						}
					}
				}

			}

			// Verify Installtion Fee
			sf.seleU.scrollByCoOrdinates(1);
			verifyFieldValue("Internet Installation Fee", sf.quoteReview.internetInstallationFeeText.get(0),
					dataTable.get("ONE TIME TCV"));
			verifyFieldValue("One Time Total Fee", sf.quoteReview.oneTimeFeesTotal,
					dataTable.get("ONE TIME TCV").split("\\.")[0]);

			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail("Error in Reviewing Quote Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify Product Details Section
	 * 
	 *                     Verify Account Information Section
	 * 
	 *                     Verify Order Summary Section
	 * 
	 *                     Verify Installtion Fee
	 */
	public void verifyBusinessPhoneDetailsInQuoteReview(Hashtable<String, String> dataTable) throws IOException {
		try {

			// Verify Account Information Section
			sf.seleU.clickElementByJSE(sf.quoteReview.accountInformationHeader);
			reportStatusPass(methodName + "Clicked on Account Information Header", true, false);

			verifyFieldValue("Account Name prepared for", sf.quoteReview.preparedForAccountName,
					Global.dataInput.businessAccountName);
			verifyFieldValue("Service Address", sf.quoteReview.contactNameText.get(1), Global.dataInput.addressStreet);

			verifyFieldValue("Attention-Contact Email", sf.quoteReview.contactEmailIdText.get(0),
					Global.dataInput.contactEmailAddress);
			verifyFieldValue("Address City", sf.quoteReview.contactEmailIdText.get(1), Global.dataInput.addressCity);

			verifyFieldValue("Service Address Zip", sf.quoteReview.contactZipCodeText.get(1),
					Global.dataInput.addressPostalCode.replaceAll(" ", ""));

			// Verify Product Details Section
			if (dataTable.get("Product _Type").contains(InputData.tvProduct)) {
				verifyFieldValue("Product Details TV", sf.quoteReview.tvProductDetails,
						dataTable.get("TV Product Name"));
			}
			if (dataTable.get("Product _Type").contains(InputData.internetProduct)) {

				verifyFieldValue("Product Details Internet", sf.quoteReview.internetProductDetails,
						dataTable.get("Internet Product Name"));
			}
			verifyIBLCProduct(dataTable, dataTable.get("IBLC Product_1"));
			verifyIBLCProduct(dataTable, dataTable.get("IBLC Product_2"));
			verifyIBLCProduct(dataTable, dataTable.get("IBLC Product_3"));

			// Verify Order Summary Section
			sf.seleU.scrollByCoOrdinates(2);

			if (dataTable.get("Product _Type").contains(InputData.tvProduct)) {

				verifyFieldValue("Order Summary: Product Details TV", sf.quoteReview.productsDetailsInOrder.get(0),
						Global.dataInput.tvProductName);

				verifyFieldValue("Order Summary: TV ADD-ONS", sf.quoteReview.tvAddOnsText.get(0),
						dataTable.get("Rogers TV AddOn Product"));

			}
			if (dataTable.get("Product _Type").contains(InputData.internetProduct)) {

				verifyFieldValue("Order Summary: Product Details Internet",
						sf.quoteReview.productsDetailsInOrder.get(0), dataTable.get("Internet Product Name"));

				verifyFieldValue("Order Summary: COLLABORATION ADD-ONS",
						sf.quoteReview.microsoftCollaorationText.get(0), dataTable.get("Office 365 AddOn"));

			}

			List<String> expectedBusinessPhoneProducts = new ArrayList<String>();
			List<String> actualBusinessPhoneProducts = new ArrayList<String>();
			expectedBusinessPhoneProducts.add(dataTable.get("IBLC Product_1"));
			expectedBusinessPhoneProducts.add(dataTable.get("IBLC Product_2"));
			expectedBusinessPhoneProducts.add(dataTable.get("IBLC Product_3"));
			expectedBusinessPhoneProducts.remove("NA");
			expectedBusinessPhoneProducts.remove("NA");
			for (int i = 0; i < sf.quoteReview.productsDetailsInOrder.size(); i++) {
				String productName = sf.seleU.getTextFromWebElement(sf.quoteReview.productsDetailsInOrder.get(i))
						.split("\n")[0];
				if (productName.equalsIgnoreCase(Global.dataInput.iblcBasic)
						|| productName.equalsIgnoreCase(Global.dataInput.iblcPro)
						|| productName.equalsIgnoreCase(Global.dataInput.iblcStandard))
					actualBusinessPhoneProducts.add(sf.seleU
							.getTextFromWebElement(sf.quoteReview.productsDetailsInOrder.get(i)).split("\n")[0]);
			}

			reportStatusPass(methodName + " Extracted Products from the Order Summary Section", false, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessPhoneProducts);
			Collections.sort(actualBusinessPhoneProducts);

			// Verify expectedTitles list is equal to actualTitles List

			if (expectedBusinessPhoneProducts.equals(actualBusinessPhoneProducts)) {
				reportStatusPass(methodName + " All expected Business Phone Products are present in Order Summary : "
						+ AdditionalUtilities.getAsString(actualBusinessPhoneProducts), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected Business Phone Products are not present in Order Summary:: Expected Products --> "
						+ AdditionalUtilities.getAsString(expectedBusinessPhoneProducts) + "  Actual Products--> "
						+ AdditionalUtilities.getAsString(actualBusinessPhoneProducts), true);
			}

			for (int i = 0; i < sf.quoteReview.orderSummaryAddOnsSection.size() - 1; i++) {

				verifyFieldDisplayed("Order Summary - Add-Ons Section",
						sf.quoteReview.orderSummaryAddOnsSection.get(i));
			}
		} catch (Throwable e) {
			reportStatusFail("Error in Reviewing Quote Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @param dataTable
	 * @param iBLCproduct
	 * @throws IOException
	 * 
	 *                     Iterate and Verify the IBLC Product to be added to Cart
	 */
	public void verifyIBLCProduct(Hashtable<String, String> dataTable, String iBLCproduct) throws IOException {

		try {
			if (iBLCproduct.equals(Global.dataInput.iblcBasic)) {
				verifyFieldValue("Product Details : ", sf.quoteReview.businessPhoneBasicProductDetails,
						Global.dataInput.iblcBasic);
				sf.seleU.wait(25000);
			}
			if (iBLCproduct.equals(Global.dataInput.iblcStandard)) {
				verifyFieldValue("Product Details : ", sf.quoteReview.businessPhoneStandardProductDetails,
						Global.dataInput.iblcStandard);
			}
			if (iBLCproduct.equals(Global.dataInput.iblcPro)) {
				verifyFieldValue("Product Details : ", sf.quoteReview.businessPhoneProProductDetails,
						Global.dataInput.iblcPro);
			}
		} catch (Throwable e) {
			reportStatusFail("Error in Reviewing Quote Details", e);
			e.printStackTrace();

		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify Product Details Section
	 * 
	 *                     Verify Account Information Section
	 * 
	 *                     Verify Order Summary Section
	 * 
	 *                     Verify Installtion Fee
	 * 
	 *                     Verify second addon fibre network access product
	 */
	public void verifyDetailsinQuoteReviewForRDI(Hashtable<String, String> dataTable, boolean secondAddsOn)
			throws IOException {
		try {

			// Verify Account Information Section
			sf.seleU.wait(10000);
			sf.seleU.clickElementByJSE(sf.quoteReview.accountInformationHeader);
			reportStatusPass(methodName + "Clicked on Account Information Header", true, false);

			// verifyFieldValue("Account Name prepared for",
			// sf.quoteReview.preparedForAccountName,
			// Global.dataInput.businessAccountName);
			// verifyFieldValueNoFormat("Account Name prepared for",
			// sf.quoteReview.preparedForAccountName,
			// Global.dataInput.tempBusinessAccountName);
			// verifyFieldValueNoFormat("Attention-Contact Name",
			// sf.quoteReview.contactNameText.get(0),
			// Global.dataInput.primaryContact);
			// verifyFieldValue("Attention-Contact Email",
			// sf.quoteReview.contactEmailIdText.get(0),
			// Global.dataInput.contactEmailAddress);
			// // System.out.println(sf.dataInput.primaryContact);
			//
			// verifyFieldValue("Service Address", sf.quoteReview.contactNameText.get(1),
			// Global.dataInput.addressStreet);
			//
			// verifyFieldValue("Address City", sf.quoteReview.contactEmailIdText.get(1),
			// Global.dataInput.addressCity);
			//
			// verifyFieldValue("Service Address Zip",
			// sf.quoteReview.contactZipCodeText.get(1),
			// Global.dataInput.addressPostalCode.replaceAll(" ", ""));
			//

			// ********Verify Credit Check required text*******************************//
			verifyFieldPresent("Credit Check Required Field Text ", sf.quoteReview.creditCheckRequiredText);
			if (sf.seleU.isElementPresent(sf.quoteReview.creditCheckRequiredText)) {
				if (sf.seleU.getTextFromWebElement(sf.quoteReview.creditCheckRequiredText).trim()
						.equals("Credit check Required")) {
					Global.dataInput.quoteReviewcreditCheckFlag = true;
					reportStatusPass(methodName + "Credit Check is required", true, false);
				} else {
					reportStatusPass(methodName + "Credit Check Not required", true, false);
				}
			}

			// Verify Product Details Section

			verifyFieldValues("Product Details Dedicated Internet Service ",
					sf.quoteReview.rDIDediactedInternetProductDetailsText, dataTable.get("Dedicated_Internet_Product"));

			verifyFieldValues("Product Details is Ethernet Fibre Network Access ",
					sf.quoteReview.rDIEthernetFibreProductDetailsText, dataTable.get("Advanced_Networks"));

			// Verify Order Summary Section
			sf.seleU.scrollByCoOrdinates(2);

			// Verify Installtion Fee
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(sf.quoteReview.promoDiscountTextDisplay);
			if (sf.seleU.isElementDisplayed(sf.quoteReview.promoDiscountTextDisplay)) {
				reportStatusPass(methodName + "Offer included text is "
						+ sf.seleU.getTextFromWebElement(sf.quoteReview.promoDiscountTextDisplay), true, false);
			}

			String numberMonthly[] = dataTable.get("RDI MONTHLY TOTAL").split("\\.");
			String numberMonthlySecond[] = dataTable.get("RDI MONTHLY TOTAL_SecondAddsOn").split("\\.");

			// Verify first and second add on product
			if (secondAddsOn == false) {
				 verifyFieldValueNoFormat("Monthly Fees Total ",
				 sf.quoteReview.monthlyRDIFeesTotal, numberMonthly[0]);
				verifyFieldValueNoFormat("One Time Total Fee", sf.quoteReview.oneTimeRDINetworkInstallFeesTotal,
						dataTable.get("One Time Cost").split("\\.")[0]);
			} else { // verify the fees only for first add on product
				verifyFieldValueNoFormat("Monthly Fees Total ", sf.quoteReview.monthlyRDIFeesTotal,
						numberMonthlySecond[0]);
				verifyFieldValueNoFormat("One Time Total Fee", sf.quoteReview.oneTimeRDINetworkInstallFeesTotal,
						dataTable.get("One Time Cost_SecondAddsOn").split("\\.")[0]);
			}
		} catch (Throwable e) {
			reportStatusFail("Error in Reviewing Quote Details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValueNoFormat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification of No Format", e);
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
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is present", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
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
			if (!expectedText.equals("NA")) {
				// Verify Field value matches the expected result
				if (sf.seleU.getTextFromWebElement(element).trim().toUpperCase()
						.contains(expectedText.trim().toUpperCase())) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is " + element.getText()
							+ " And Expected One is " + expectedText, true);
				}
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
	public void verifyFieldValues(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			if (!expectedText.equals("NA")) {
				// Verify Field value matches the expected result
				if (expectedText.trim().toUpperCase()
						.contains(sf.seleU.getTextFromWebElement(element).trim().toUpperCase())) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is " + element.getText()
							+ " And Expected One is " + expectedText, true);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
