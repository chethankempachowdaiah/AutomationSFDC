package sfdc.page_objects.om;

import java.io.IOException;
import java.util.Hashtable;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.qm.SFDC_QuoteReview_Page;

/**
 * @author Pankaj Agarwal
 * 
 *         Guided By Journey Page for RDI
 *
 */

public class SFDC_GuidedByJourney_RDI_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_GuidedByJourney_RDI_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_CPQ Guided By journey RDI@: ";
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select Dedicated Internet Product
	 * 
	 *                     Select Contract Term
	 * 
	 *                     Select Product Speed and add to cart
	 * 
	 *                     Add Discount for the product
	 * 
	 *                     Extract MRR + One Time TCV values for credit check
	 *                     validation
	 * 
	 *                     Verify second adds on product for dedicated internet
	 *                     speed 50 Mbps
	 */
	public void addRogersBusinessSolutions_RDI(Hashtable<String, String> dataTable, boolean validateTCV)
			throws IOException {

		try {

			// Select RDI Product
			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(sf.gbjRDI.dedicatedInternetProduct);
			reportStatusPass(methodName + " Selected RDI Internet Product", true, false);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked On Next button after selecting Dedicated Internet", true, false);
			sf.seleU.wait(5000);

			// Select Contract Term Monthly/3 Years
			selectContractTermForRDI(dataTable);

			// Select service speed and add to cart
			sf.seleU.wait(2000);
			boolean dedicatedInternetOffersProductSpeed = false;
			int index = 0;
			int count = 0;
			for (int i = 0; i < sf.gbj.catalogNames.size(); i++) {

				sf.seleU.scrollByCoOrdinates(1);
				System.out.println(dataTable.get("Product Speed"));
				sf.seleU.wait(3000);
				if (sf.seleU.getTextFromWebElement(sf.gbj.catalogNames.get(i)).trim()
						.equalsIgnoreCase(dataTable.get("Product Speed"))) {
					sf.seleU.ScrolltoElement(sf.gbj.catalogNames.get(i));
					sf.seleU.wait(2000);

					// fetch the index value
					reportStatusPass(methodName + " Clicked on Add to cart Button", true, false);
					dedicatedInternetOffersProductSpeed = true;
					index = i;
					break;
				}
			}

			// Apply discount and promos if the indicator for it is yes on the test data and
			// then add to cart
			sf.seleU.wait(4000);
			sf.seleU.ScrolltoElement(sf.gbj.promotionsAndDiscountsAllLinks.get(index));
			sf.seleU.wait(4000);
			if (sf.seleU.isElementPresent(sf.gbj.promotionsAndDiscountsAllLinks.get(index))) {
				if (!dataTable.get("Discount").equals("NA")) {
					sf.seleU.clickElementByJSE(sf.gbj.promotionsAndDiscountsAllLinks.get(index));
					int discount = Integer.valueOf(dataTable.get("Discount"));
					for (int i = 0; i < discount; i++) {
						sf.seleU.wait(1000);
						sf.seleU.clickElementByJSE(sf.gbj.promotionsAndDiscountsIncrementButton);
						count++;
					}
					reportStatusPass(methodName + "clicked on "
							+ sf.seleU.getTextFromWebElement(sf.gbj.promotionsAndDiscountsAllLinks.get(index))
							+ " with discount added as " + count + " %", true, false);
				}
			}

			// Add to cart
			sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(index));
			sf.seleU.wait(20000);
			if (dedicatedInternetOffersProductSpeed) {
				reportStatusPass(methodName + " Successfully found and added "
						+ sf.seleU.getTextFromWebElement(sf.gbj.catalogNames.get(index)), true, false);
			} else {
				reportStatusFail(methodName + " No Product added : ", true);
			}

			// If it requires credit check validation and credit check value enter indicator
			// is marked as yes in datasheet
			// then fetch MRR + One time TCV values
			if (dataTable.get("Credit_Check_Value_Enter").equals("Yes")) {
				creditCheckRequiredValidationInGBJ();
			}

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked On Next Button in Dedicated Internet Offers Product Page", true,
					false);
			sf.seleU.wait(2000);

			// Verify second access fibre adds on for internet speed 50 MBPS
			if (dataTable.get("SecondAdds_On").equals("Yes")) {
				verifySecondFibreNetwrokAccessAddOns(dataTable);
			}

			// if only one one adds is present in fibre access addon page
			else {
				if (sf.seleU.isElementDisplayed(sf.gbjRDI.fibreAccessDefaultCheckboxAddsOn.get(0))) {
					reportStatusPass(
							methodName + "Fibre access default prodict added is "
									+ sf.seleU.getTextFromWebElement(sf.gbjRDI.fibreAccessDefaultProductText.get(0)),
									true, false);
				}

				if (sf.seleU.isElementDisplayed(sf.gbjRDI.fibreAccessAddsOnAbaOfferText)) {
					reportStatusPass(
							methodName + "Offer included text is "
									+ sf.seleU.getTextFromWebElement(sf.gbjRDI.fibreAccessAddsOnAbaOfferText),
									true, false);
				}
				sf.seleU.wait(6000);

				// Click on next button in fibre access add ons page
				if (sf.seleU.isElementPresent(sf.gbj.nextButton)) {
					sf.seleU.wait(3000);
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.gbj.nextButton);
					reportStatusPass(methodName + "Clicked on Next Button in fibre adds on page", true, false);
					sf.seleU.wait(2000);
				}
			}

			sf.seleU.wait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding RDI Product to cart in GBJ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param contarctTerm
	 * @throws IOException
	 * @throws InterruptedException For internet speed 50 MBPS, it will have add on
	 *                              in fibre access network page. 1. By default
	 *                              first addon will be selected in fibre adds on
	 *                              page 2. Click on next button 3. Go to quote
	 *                              review page for validation 4. click on previous
	 *                              button to move back to fibre adds on page 5.
	 *                              click on second addon and click on update cart
	 *                              button 6. Click on next button again 7. It will
	 *                              move to quote review page
	 * 
	 */
	public void verifySecondFibreNetwrokAccessAddOns(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {
		try {
			// Select Monthly
			boolean secondAddsOn = false;
			// Network access product is selected in default in fibre access adds on page
			if (sf.gbjRDI.fibreAccessDefaultCheckboxAddsOn.size() >= 1) {
				for (int i = 0; i < sf.gbjRDI.fibreAccessDefaultCheckboxAddsOn.size(); i++) {

					//	for first fibre adds on as default 
					if (i == 0) {
						if (sf.seleU.isElementPresent(sf.gbj.nextButton)) {
							
							reportStatusPass(
									methodName + "Fibre access default prodict added is "
											+ sf.seleU.getTextFromWebElement(sf.gbjRDI.fibreAccessDefaultProductText.get(i+1)),
											true, false);
							
							sf.seleU.clickElementByJSE(sf.gbj.nextButton);
							reportStatusPass(methodName + "Clicked on Next Button in fibre adds on page", true, false);
							
						}
					}
					sf.seleU.wait(4000);
					// If we have two adds on then again we need to come back and update the 
					// cart items with second adds on product so i=1
					if (i == 1) {

						sf.seleU.clickElementByJSE(sf.gbjRDI.fibreAccessDefaultCheckboxAddsOn.get(i-1));
						reportStatusPass(
								methodName + "Fibre access default prodict added is "
										+ sf.seleU.getTextFromWebElement(sf.gbjRDI.fibreAccessDefaultProductText.get(i-1)),
										true, false);
						sf.seleU.wait(4000);

						if (sf.seleU.isElementPresent(sf.gbjRDI.updateCartButton)) {
							sf.seleU.clickElementByJSE(sf.gbjRDI.updateCartButton);
							sf.seleU.wait(20000);
							sf.seleU.hardwait(4000);
							reportStatusPass(methodName + "Clicked on Update Cart Button in fibre adds on page", true,
									false);
						}
						sf.seleU.wait(5000);
						if (sf.seleU.isElementPresent(sf.gbj.nextButton)) {
							sf.seleU.clickElementByJSE(sf.gbj.nextButton);
							reportStatusPass(methodName + "Clicked on Next Button in fibre adds on page", true, false);
						}
					}

					// Got to quote review page to verify the product being addede
					goToReviewQuotePage(dataTable, secondAddsOn);

					sf.seleU.wait(4000);
					if (i == 0) {
						sf.seleU.clickElementByJSE(sf.gbjRDI.previousButton);
						secondAddsOn = true;
					}
					sf.seleU.wait(2000);
				}
			}
			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Error While veifying second addon product in fibre add on network access page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     creating the object to move to quote review page
	 * 
	 */
	private void goToReviewQuotePage(Hashtable<String, String> dataTable, boolean secondAddsOn) throws IOException {
		// TODO Auto-generated method stub
		try {
			SFDC_QuoteReview_Page quoteReviewPage = new SFDC_QuoteReview_Page();

			quoteReviewPage.verifyDetailsinQuoteReviewForRDI(dataTable, secondAddsOn);

		} catch (Throwable e) {
			reportStatusFail(" Error in creating the object for quote review page", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param contarctTerm
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select Monthly/5 Year Term
	 */
	public void selectContractTermForRDI(Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		// Select Monthly
		if (dataTable.get("Contract_Term").equals(sf.dataInput.contractTerm60Months)) {
			sf.seleU.clickElementByJSE(sf.gbjRDI.fiveYearTermRadioButton);
			reportStatusPass(methodName + " Selected five year Term Radio Button", true, false);
			sf.seleU.wait(8000);
		}
		// Select 3 Year
		else {
			sf.seleU.clickElementByJSE(sf.gbj.threeYearTermRadioButton);
			reportStatusPass(methodName + " Selected Three Year Term Radio Button", true, false);
			sf.seleU.wait(8000);
		}

	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Extract monthly total and one time tcv value
	 *                              from rogers add to product page
	 * 
	 *                              Validate the condition for credit check
	 *                              depending on the values entered
	 * 
	 * 
	 */
	public void creditCheckRequiredValidationInGBJ() throws IOException, InterruptedException {
		try {
			String methodName = "SFDC_Credit Check Required In GBJ@: ";
			String oneTimeTCV = "";
			String monthlyTotal = "";
			int quote_MRR_Plus_NRC_Price = 0;
			int oneTimeTCVPrice = 0;
			int monthlyTotalPrice = 0;
			// click on account details tab button

			sf.seleU.hardwait(5000);

			monthlyTotal = sf.seleU.getTextFromWebElement(sf.gbjCart.monthlyTotal).split("\\.")[0].replace("$", "")
					.replace(",", "");
			oneTimeTCV = sf.seleU.getTextFromWebElement(sf.gbjCart.oneTimeTCV).split("\\.")[0].replace("$", "")
					.replace(",", "");
			;

			reportStatusPass(methodName + "Monthly total is " + monthlyTotal + " One Time TCV is" + oneTimeTCV, true,
					false);
			monthlyTotalPrice = Integer.valueOf(monthlyTotal);
			oneTimeTCVPrice = Integer.valueOf(oneTimeTCV);

			// Adding monthly total and one time tcv price to get Quote MRR + NRC Price
			quote_MRR_Plus_NRC_Price = monthlyTotalPrice + oneTimeTCVPrice;

			if (quote_MRR_Plus_NRC_Price > sf.dataInput.credit_Available
					&& quote_MRR_Plus_NRC_Price < sf.dataInput.credit_Assigned
					&& sf.dataInput.credit_Assigned > sf.dataInput.credit_Available) {

				reportStatusPass(methodName + "Credit Check not required as " + "MRR+NRC Price "
						+ quote_MRR_Plus_NRC_Price + " > " + "Credit Available " + sf.dataInput.credit_Available + "\n"
						+ "Credit Limit Available " + sf.dataInput.credit_Available + " < " + "Credit Limit Assign "
						+ sf.dataInput.credit_Assigned + "\n" + "MRR+NRC Price " + quote_MRR_Plus_NRC_Price + " < "
						+ "Credit Assigned" + sf.dataInput.credit_Assigned
						+ " And last review date is less then 30 days old", true, false);

			} else if (quote_MRR_Plus_NRC_Price > sf.dataInput.credit_Available
					&& quote_MRR_Plus_NRC_Price > sf.dataInput.credit_Assigned
					&& sf.dataInput.credit_Assigned > sf.dataInput.credit_Available) {

				reportStatusPass(methodName + "Credit Check required as " + "MRR+NRC Price " + quote_MRR_Plus_NRC_Price
						+ " > " + "Credit Available " + sf.dataInput.credit_Available + "\n" + "Credit Limit Available "
						+ sf.dataInput.credit_Available + " < " + "Credit Limit Assign " + sf.dataInput.credit_Assigned
						+ "\n" + "MRR+NRC Price " + quote_MRR_Plus_NRC_Price + " > " + "Credit Assigned"
						+ sf.dataInput.credit_Assigned, true, false);

			} else if (quote_MRR_Plus_NRC_Price < sf.dataInput.credit_Available
					&& sf.dataInput.credit_Assigned >= sf.dataInput.credit_Available) {

				reportStatusPass(methodName + "Credit Check Not required as " + "MRR+NRC Price "
						+ quote_MRR_Plus_NRC_Price + " < " + "Credit Available " + sf.dataInput.credit_Available + "\n"
						+ "Credit Limit Available " + sf.dataInput.credit_Available + " =< " + "Credit Limit Assign "
						+ sf.dataInput.credit_Assigned + " And last review date is empty", true, false);
			} else if (quote_MRR_Plus_NRC_Price > sf.dataInput.credit_Available
					&& sf.dataInput.credit_Assigned > sf.dataInput.credit_Available) {

				reportStatusPass(methodName + "Credit Check required as " + "MRR+NRC Price " + quote_MRR_Plus_NRC_Price
						+ " > " + "Credit Available " + sf.dataInput.credit_Available + "\n" + "Credit Limit Available "
						+ sf.dataInput.credit_Available + " < " + "Credit Limit Assign " + sf.dataInput.credit_Assigned,
						true, false);
			}

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail("Error in verifying credit check required", e);
			e.printStackTrace();
		}
	}
}
