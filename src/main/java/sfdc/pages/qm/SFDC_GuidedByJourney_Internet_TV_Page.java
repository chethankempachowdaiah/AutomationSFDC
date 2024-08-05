package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya
 * 
 *         Guided By Journey Page Objects for Internet & TV Products
 *
 */
public class SFDC_GuidedByJourney_Internet_TV_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_GuidedByJourney_Internet_TV_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_CPQ Guided By journey Internet & TV @: ";
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select Interenet Product
	 * 
	 *                     Select Contract Term Monthly/3 Years
	 * 
	 *                     Select Speed and Internet Grade
	 * 
	 *                     Add promo and Discounts if applicable
	 * 
	 *                     Validate Product Price
	 * 
	 *                     Add Internet Product to cart
	 */
	public void addRogersBusinessSolutions_Internet(Hashtable<String, String> dataTable, boolean validateTCV,
			boolean additionalPromo) throws IOException {

		try {

			// Select Interenet Product
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.gbj.internetProduct);
			sf.seleU.waitElementToBeVisible(sf.gbj.internetProduct);
			sf.seleU.clickElementByJSE(sf.gbj.internetProduct);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Selected Internet Product", true, false);
			sf.seleU.wait(15000);

			// Select Contract Term Monthly/3 Years
			selectContractTerm(dataTable);

			// Select Speed and Internet Grade
			String speedCatalog = dataTable.get("Speed");
			if (speedCatalog.equals("1G")) {
				speedCatalog = "Gigabit";
				sf.seleU.scrollByCoOrdinates(1);
			}
			// 150: no scroll, 1G: 1 scroll, 500u: 3 scroll, 30u: 2 scroll
			else if (speedCatalog.contains("30")) {
				sf.seleU.scrollByCoOrdinates(2);
			} else if (speedCatalog.contains("500")) {
				sf.seleU.scrollByCoOrdinates(3);
			}

			if (dataTable.get("Contract_Term").equals(InputData.contractTermMonthly) && speedCatalog.contains("150")) {
				sf.seleU.scrollByCoOrdinates(2);
			}
			sf.seleU.wait(5000);

			boolean internetAdded = false;
			for (int i = 0; i < sf.gbj.catalogNames.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.gbj.catalogNames.get(i)).equalsIgnoreCase(speedCatalog)) {

					// Select Internet Grade IG/500U/150U/30U
					sf.seleU.clickOnElement(sf.gbjCart.selectGradeInternet.get(i));
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Selected Internet Grade as " + dataTable.get("Speed"), true, false);

					// Select Internet Grade Product
					internetAdded = selectInternetProductGrade(dataTable.get("Internet Product Name"));

					// Add promo and Discounts if applicable
					if (dataTable.get("Promo").equalsIgnoreCase("Yes")) {
						if (additionalPromo)
							applyAdditionalInternetPromo(dataTable, i);
						else
							applyInternetPromo(dataTable, i);
					}

					// Validate Product Price
					if (!dataTable.get("Recurring Charge Internet").equals("NA")) {
						validatePrice("Recurring Charge Internet ", sf.gbjCart.price.get(i),
								dataTable.get("Recurring Charge Internet"));
					}

					// Add Internet Product to cart
					sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Clicked on Add to cart Button", true, false);
					break;
				}
			}
			sf.seleU.wait(20000);

			if (internetAdded) {
				reportStatusPass(methodName + " Successfully found and added " + dataTable.get("Internet Product Name"),
						true, false);
			} else {
				reportStatusFail(methodName + " No Product added : " + dataTable.get("Internet Product Name"), true);
			}

			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked On Next Button in Internet Product Page", true, false);
			sf.seleU.wait(15000);

			if (validateTCV) {
				validate_Internet_TCV(dataTable);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding Internt Product to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Internet TCV
	 */
	public void validate_Internet_TCV(Hashtable<String, String> dataTable) throws IOException {

		InputData.monthylyTotal_num = Double.parseDouble(dataTable.get("Recurring Charge Internet"));
		InputData.recurringTCV_num = Double.parseDouble(dataTable.get("Recurring Charge Internet")) * 36;
		InputData.onetimeTCV_num = Double.parseDouble(dataTable.get("ONE TIME TCV"));
		InputData.totalTCV_num = InputData.recurringTCV_num + InputData.onetimeTCV_num;
		InputData.totalCost_num = (Double.parseDouble(dataTable.get("Recurring  Cost Internet")) * 36)
				+ Double.parseDouble(dataTable.get("One Time Cost"));
		InputData.tcvMargin_num = InputData.totalTCV_num - InputData.totalCost_num;
		InputData.tcvMarginPercentage_num = InputData.tcvMargin_num * 100 / InputData.totalTCV_num;

		sf.seleU.wait(30000);

		verifyTCV("MONTHLY TOTAL", sf.gbjCart.monthlyTotal, InputData.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.gbjCart.recurringTCV, InputData.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.gbjCart.oneTimeTCV, InputData.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.gbjCart.totalTCV, InputData.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.gbjCart.totalCosts, InputData.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.gbjCart.tcvMarginValue, InputData.tcvMargin_num);
		verifyTCV("TCV MARGIN %", sf.gbjCart.tcvMarginPercentage, InputData.tcvMarginPercentage_num);

	}

	/**
	 * @param productName
	 * @return
	 * @throws Exception
	 * 
	 *                   Select Internet Product Grade from product dropdown
	 */
	public boolean selectInternetProductGrade(String productName) throws Exception {

		boolean isInternetAdded = false;
		for (int j = 0; j < sf.gbjCart.productOptionsList.size(); j++) {
			sf.seleU.wait(1000);
			if (sf.seleU.isElementDisplayed(sf.gbjCart.productOptionsList.get(j)) && sf.seleU
					.getTextFromWebElement(sf.gbjCart.productOptionsList.get(j)).equalsIgnoreCase(productName)) {
				sf.seleU.clickOnElement(sf.gbjCart.productOptionsList.get(j));
				isInternetAdded = true;
				break;
			}
		}

		sf.seleU.wait(8000);

		// Verify Internet Product Added successfully
		if (isInternetAdded) {
			reportStatusPass(methodName + " Added Internet Product to cart :" + productName, true, true);
		} else {
			reportStatusFail(methodName + " Error While adding Internet Product to cart in GBJ", true);
		}
		return isInternetAdded;

	}

	/**
	 * @param contarctTerm
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select Monthly/3 Year Term
	 */
	public void selectContractTerm(Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		// Select Monthly
		if (dataTable.get("Contract_Term").equals(InputData.contractTermMonthly)) {
			sf.seleU.clickElementByJSE(sf.gbj.monthlyTermRadioButton);
			reportStatusPass(methodName + " Selected Monthly Term Radio Button", true, false);
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
	 * @param speed
	 * 
	 *              Apply promo to the Added product
	 * @throws Exception
	 */
	public void applyInternetPromo(Hashtable<String, String> dataTable, int prod) throws Exception {

		sf.seleU.clickElementByJSE(sf.gbj.promotionsAndDiscountsAllLinks.get(prod));
		sf.seleU.wait(5000);

		// promo for 150U
		if (sf.seleU.isElementDisplayed(sf.gbjCart.promo150U) && sf.seleU.getTextFromWebElement(sf.gbjCart.promo150U)
				.toLowerCase().contains(dataTable.get("Speed").toLowerCase())) {
			sf.seleU.clickElementByJSE(sf.gbjCart.promo150U);
		}

		// Promo for 500U
		else if (sf.seleU.isElementDisplayed(sf.gbjCart.promo500U)
				&& sf.seleU.getTextFromWebElement(sf.gbjCart.promo500U).toLowerCase()
						.contains(dataTable.get("Speed").toLowerCase())) {
			sf.seleU.clickElementByJSE(sf.gbjCart.promo500U);
		}

		// Promo for 1G
		else if (sf.seleU.isElementDisplayed(sf.gbjCart.promo1G) && sf.seleU.getTextFromWebElement(sf.gbjCart.promo1G)
				.toLowerCase().contains(dataTable.get("Speed").toLowerCase())) {
			sf.seleU.clickElementByJSE(sf.gbjCart.promo1G);
		}
		reportStatusPass(methodName + " Applied Promo Code for " + dataTable.get("Speed"), true, false);
		sf.seleU.wait(4000);
	}

	/**
	 * @param speed
	 * 
	 *              Apply additional promos to the Added product
	 * @throws Exception
	 */
	public void applyAdditionalInternetPromo(Hashtable<String, String> dataTable, int prod) throws Exception {
		try {
			sf.seleU.clickElementByJSE(sf.gbj.promotionsAndDiscountsAllLinks.get(prod));
			sf.seleU.wait(5000);
			String promoName = dataTable.get("Internet Promo");
			if (!promoName.equals("NA")) {
				if (sf.seleU.isElementDisplayed(getSpecific_GBJ_IBLC_Element(promoName))) {
					sf.seleU.clickElementByJSE(getSpecific_GBJ_IBLC_Element(promoName));
				}
			}
			reportStatusPass(methodName + " Applied Promo Code for " + promoName, true, false);
			sf.seleU.wait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Error in adding IBLC Internet Promo", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select Office 365 AddOn
	 * 
	 *                     Add Office 365 AddOn to cart
	 */
	public void addRogersOffice365AddOns(Hashtable<String, String> dataTable, boolean validateTCV) throws IOException {
		try {

			boolean isOffice365Added = false;
			// Select Office 365 AddOn
			for (int i = 0; i < sf.gbjCart.microsoft365AddOnHeading.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.gbjCart.microsoft365AddOnHeading.get(i))
						.equals(dataTable.get("Office 365 AddOn"))) {

					validatePrice("Microsoft 365 Price ", sf.gbjCart.price.get(i), dataTable.get("Office 365 Charge"));

					// Add Office 365 AddOn to cart
					sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(i));
					isOffice365Added = true;
					sf.seleU.wait(20000);
					break;
				}
			}

			if (isOffice365Added) {
				reportStatusPass(methodName + " Added Office 365 AddOn to cart :" + dataTable.get("Office 365 AddOn"),
						true, true);
			} else {
				reportStatusFail(methodName + " No Office 365 Products Availble on the Page", true);
			}

			if (validateTCV) {
				validate_Internet_Office365_TCV(dataTable);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding Office 365 Product to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Add Rogers Office 365 TV multiple Quantity for specifc
	 *                     Rogers TV AddOn Product
	 * 
	 *                     Update cart and Hit Next
	 */
	public void addRogersOffice365MultipleQuantityAddOns(Hashtable<String, String> dataTable, boolean validateTCV)
			throws IOException {
		try {

			boolean isOffice365Added = false;
			boolean is365PremiumAdded = false;
			int k = 0;
			// Select Office 365 AddOn

			// convert arrays to list after split
			String officeAddOnQuantityArray[] = dataTable.get("Office 365 AddOn Quantity").split(",");
			String officeAddOnArray[] = dataTable.get("Office 365 AddOn").trim().split(",");
			List<String> arList = new ArrayList<String>(Arrays.asList(officeAddOnArray));

			for (int i = 0; i < sf.gbjCart.microsoft365AddOnHeading.size(); i++) {
				sf.seleU.wait(1000);

				for (int j = 0; j < arList.size(); j++) {

					if (sf.seleU.getTextFromWebElement(sf.gbjCart.microsoft365AddOnHeading.get(i)).trim()
							.equals(officeAddOnArray[j].trim())) {

						// Add Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product
						sf.seleU.clearAndEnterText(sf.gbjCart.productTerminalQtyNumeric.get(i),
								officeAddOnQuantityArray[j].trim());

						sf.seleU.enterText(sf.gbjCart.productTerminalQtyNumeric.get(i), Keys.TAB);

						// Add to cart - Once we add the product to the cart ADD to cart is not visible,
						// so reset the index.
						// Product which are yet to be added the index will again start from 0 for add
						// to cart.

						if (i == 0) {
							sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(i));
						} else {
							k = 0;
							sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(k));
						}

						sf.seleU.wait(5000);
						sf.seleU.waitElementToBeVisible(sf.gbjCart.microsoft365AddOnHeading.get(i));
						reportStatusPass(
								methodName + " Selected Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product : "
										+ officeAddOnArray[j] + " with quantity as " + officeAddOnQuantityArray[j],
								true, true);

						break;
					}
				}
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding Office 365 Product to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	public void validate_Internet_Office365_TCV(Hashtable<String, String> dataTable) throws IOException {

		InputData.monthylyTotal_num = InputData.monthylyTotal_num
				+ Double.parseDouble(dataTable.get("Office 365 Charge"));
		InputData.recurringTCV_num = InputData.recurringTCV_num
				+ Double.parseDouble(dataTable.get("Office 365 Charge")) * 36;
		InputData.onetimeTCV_num = Double.parseDouble(dataTable.get("ONE TIME TCV"));
		InputData.totalTCV_num = InputData.totalTCV_num + Double.parseDouble(dataTable.get("Office 365 Charge")) * 36;
		InputData.totalCost_num = InputData.totalCost_num + Double.parseDouble(dataTable.get("Office 365 Cost")) * 36;
		InputData.tcvMargin_num = InputData.totalTCV_num - InputData.totalCost_num;
		InputData.tcvMarginPercentage_num = InputData.tcvMargin_num * 100 / InputData.totalTCV_num;

		sf.seleU.wait(15000);

		verifyTCV("MONTHLY TOTAL", sf.gbjCart.monthlyTotal, InputData.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.gbjCart.recurringTCV, InputData.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.gbjCart.oneTimeTCV, InputData.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.gbjCart.totalTCV, InputData.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.gbjCart.totalCosts, InputData.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.gbjCart.tcvMarginValue, InputData.tcvMargin_num);
		verifyTCV("TCV MARGIN %", sf.gbjCart.tcvMarginPercentage, InputData.tcvMarginPercentage_num);

	}

	/**
	 * Click on Next Button
	 */
	public void hitNext() {
		sf.seleU.clickElementByJSE(sf.gbj.nextButton);
		sf.seleU.wait(17000);
	}

	/**
	 * Click on Checkout Button
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void hitCheckOut() throws IOException, InterruptedException {
		if (sf.seleU.isElementDisplayed(sf.gbjCart.checkoutButton)) {
			sf.seleU.clickElementByJSE(sf.gbjCart.checkoutButton);
			reportStatusPass(methodName + " Clicked on Check Out", true, true);
			sf.seleU.wait(28000);
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select Tv Product
	 * 
	 *                     Select TV Grade Basic/VIP
	 * 
	 *                     Select TV Grade Private/Public
	 * 
	 *                     Add TV Product to cart
	 * 
	 *                     Verify TV Product Added successfully
	 */
	public void addRogersBusinessSolutions_TV(Hashtable<String, String> dataTable, boolean validateTCV)
			throws IOException {

		try {
			// Select Tv Product
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.gbj.tvProduct);
			sf.seleU.clickElementByJSE(sf.gbj.tvProduct);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Selected TV Product", true, false);
			sf.seleU.wait(12000);

			boolean isTvAdded = false;
			for (int i = 0; i < sf.gbj.catalogNames.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.gbj.catalogNames.get(i)).equals(dataTable.get("Basic_VIP"))) {

					// Select TV Grade Basic/VIP
					sf.seleU.scrollByCoOrdinates(1);
					sf.seleU.clickOnElement(sf.gbjCart.selectGradeTV.get(i));
					sf.seleU.wait(2000);
					reportStatusPass(methodName + " Selected TV Grade as " + dataTable.get("Basic_VIP"), true, false);

					// Select TV Grade Private/Public
					for (int j = 0; j < sf.gbjCart.productOptionsList.size(); j++) {
						if (sf.seleU.isElementDisplayed(sf.gbjCart.productOptionsList.get(j))
								&& sf.seleU.getTextFromWebElement(sf.gbjCart.productOptionsList.get(j))
										.equals(dataTable.get("TV Product Name"))) {
							sf.seleU.clickOnElement(sf.gbjCart.productOptionsList.get(j));
							isTvAdded = true;
							break;
						}
					}
					sf.seleU.wait(7000);
					reportStatusPass(methodName + " Selected TV Prodct as " + dataTable.get("TV Product Name"), true,
							false);

					// Add TV Product to cart
					sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added TV Prodct to cart: " + dataTable.get("TV Product Name"), true,
							false);
					break;

				}
			}
			sf.seleU.wait(25000);

			// Verify TV Product Added successfully
			if (isTvAdded) {
				reportStatusPass(methodName + " Added TV Product to cart :" + dataTable.get("Basic_VIP"), true, true);
				sf.seleU.clickElementByJSE(sf.gbj.nextButton);

			} else {
				reportStatusFail(methodName + " Error While adding TV Product to cart in GBJ", true);
			}

			sf.seleU.wait(20000);

			if (validateTCV) {
				if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
					validate_TV_TCV(dataTable);
				} else {
					validate_Internet_Office365_TV_TCV(dataTable);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding TV Product to cart in GBJ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select Tv Product
	 * 
	 *                     Select TV Grade Basic/VIP
	 * 
	 *                     Select TV Grade Private/Public
	 * 
	 *                     Add TV Product to cart
	 * 
	 *                     Verify TV Product Added successfully
	 */
	public void addRogersBusinessSolutions_TV_WithOutGrade(Hashtable<String, String> dataTable, boolean validateTCV)
			throws IOException {

		try {
			// Select Tv Product
			// sf.seleU.waitElementToBeVisible(sf.gbj.tvProduct);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.gbj.tvProduct);
			sf.seleU.clickElementByJSE(sf.gbj.tvProduct);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Selected TV Product", true, false);
			sf.seleU.wait(12000);

			boolean isTvAdded = false;
			for (int i = 0; i < sf.gbj.catalogNames.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.gbj.catalogNames.get(i)).equals(dataTable.get("Basic_VIP"))) {

					if (!dataTable.get("TV Product Name").equals("NA")) {

						// Select TV Grade Basic/VIP
						sf.seleU.scrollByCoOrdinates(1);
						sf.seleU.clickElementByJSE(sf.gbjCart.selectGradeTV.get(i));
						sf.seleU.wait(2000);
						reportStatusPass(methodName + " Selected TV Grade as " + dataTable.get("Basic_VIP"), true,
								false);

						// Select TV Grade Private/Public
						for (int j = 0; j < sf.gbjCart.productOptionsList.size(); j++) {
							if (sf.seleU.isElementDisplayed(sf.gbjCart.productOptionsList.get(j))
									&& sf.seleU.getTextFromWebElement(sf.gbjCart.productOptionsList.get(j))
											.equals(dataTable.get("TV Product Name"))) {
								sf.seleU.clickOnElement(sf.gbjCart.productOptionsList.get(j));
								isTvAdded = true;
								break;
							}
						}
						sf.seleU.wait(7000);
						reportStatusPass(methodName + " Selected TV Prodct as " + dataTable.get("TV Product Name"),
								true, false);
					}
					// Add TV Product to cart
					sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(i));
					reportStatusPass(methodName + " Added TV Prodct to cart: " + dataTable.get("TV Product Name"), true,
							false);
					// } // for TV grade Basic/VIP
					break;

				}
			}
			sf.seleU.wait(25000);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			// Verify TV Product Added successfully
			if (isTvAdded) {
				reportStatusPass(methodName + " Added TV Product to cart :" + dataTable.get("Basic_VIP"), true, true);
				sf.seleU.clickElementByJSE(sf.gbj.nextButton);

			} else {
				reportStatusFail(methodName + " Error While adding TV Product to cart in GBJ", true);
			}

			sf.seleU.wait(20000);

			if (validateTCV) {
				if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
					validate_TV_TCV(dataTable);
				} else {
					validate_Internet_Office365_TV_TCV(dataTable);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding TV Product to cart in GBJ", e);
			e.printStackTrace();
		}

	}

	public void validate_Internet_Office365_TV_TCV(Hashtable<String, String> dataTable) throws IOException {

		InputData.monthylyTotal_num = InputData.monthylyTotal_num
				+ Double.parseDouble(dataTable.get("Recurring  Charge TV"));
		InputData.recurringTCV_num = InputData.recurringTCV_num
				+ (Double.parseDouble(dataTable.get("Recurring  Charge TV")) * 36);
		InputData.onetimeTCV_num = Double.parseDouble(dataTable.get("ONE TIME TCV"));
		InputData.totalTCV_num = InputData.recurringTCV_num + InputData.onetimeTCV_num;
		InputData.totalCost_num = InputData.totalCost_num
				+ (Double.parseDouble(dataTable.get("Recurring  Cost TV")) * 36);
		InputData.tcvMargin_num = InputData.totalTCV_num - InputData.totalCost_num;
		InputData.tcvMarginPercentage_num = InputData.tcvMargin_num * 100 / InputData.totalTCV_num;

		sf.seleU.wait(15000);

		verifyTCV("MONTHLY TOTAL", sf.gbjCart.monthlyTotal, InputData.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.gbjCart.recurringTCV, InputData.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.gbjCart.oneTimeTCV, InputData.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.gbjCart.totalTCV, InputData.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.gbjCart.totalCosts, InputData.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.gbjCart.tcvMarginValue, InputData.tcvMargin_num);
		verifyTCV("TCV MARGIN %", sf.gbjCart.tcvMarginPercentage, InputData.tcvMarginPercentage_num);

	}

	public void validate_TV_TCV(Hashtable<String, String> dataTable) throws IOException {

		InputData.monthylyTotal_num = Double.parseDouble(dataTable.get("Recurring  Charge TV"));
		InputData.recurringTCV_num = Double.parseDouble(dataTable.get("Recurring  Charge TV"));
		InputData.onetimeTCV_num = Double.parseDouble(dataTable.get("ONE TIME TCV"));
		InputData.totalTCV_num = InputData.recurringTCV_num + InputData.onetimeTCV_num;
		InputData.totalCost_num = Double.parseDouble(dataTable.get("Recurring  Cost TV"))
				+ Double.parseDouble(dataTable.get("One Time Cost"));
		InputData.tcvMargin_num = InputData.totalTCV_num - InputData.totalCost_num;
		InputData.tcvMarginPercentage_num = InputData.tcvMargin_num * 100 / InputData.totalTCV_num;

		sf.seleU.wait(15000);

		verifyTCV("MONTHLY TOTAL", sf.gbjCart.monthlyTotal, InputData.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.gbjCart.recurringTCV, InputData.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.gbjCart.oneTimeTCV, InputData.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.gbjCart.totalTCV, InputData.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.gbjCart.totalCosts, InputData.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.gbjCart.tcvMarginValue, InputData.tcvMargin_num);
		verifyTCV("TCV MARGIN %", sf.gbjCart.tcvMarginPercentage, InputData.tcvMarginPercentage_num);

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Add Rogers TV AddOn Quantity for specifc Rogers TV AddOn
	 *                     Product
	 * 
	 *                     Update cart and Hit Next
	 */
	public void addRogersTVAddOns(Hashtable<String, String> dataTable, boolean validateTCV) throws IOException {
		try {

			boolean isTvAddOnAdded = false;
			for (int i = 0; i < sf.gbjCart.tvAddOnTerminal.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.gbjCart.tvAddOnTerminal.get(i))
						.equals(dataTable.get("Rogers TV AddOn Product"))) {

					// Add Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product
					sf.seleU.clearAndEnterText(sf.gbjCart.productTerminalQtyNumeric.get(i),
							dataTable.get("Rogers TV AddOn Quantity"));

					reportStatusPass(
							methodName + " Selected Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product :"
									+ dataTable.get("Rogers TV AddOn Product"),
							true, false);

					// Update cart and Hit Next
					sf.seleU.enterText(sf.gbjCart.productTerminalQtyNumeric.get(i), Keys.TAB);
					sf.seleU.wait(5000);
					sf.seleU.clickElementByJSE(sf.gbjCart.updateCartButton);
					reportStatusPass(methodName + " Clicked on Update Cart Button for "
							+ dataTable.get("Rogers TV AddOn Product"), true, false);
					isTvAddOnAdded = true;
					sf.seleU.wait(20000);
					break;
				}
			}

			if (validateTCV) {
				if (dataTable.get("Product _Type").equals(InputData.tvProduct)) {
					validate_TV_RogersTvAddOn_TCV(dataTable);
				} else {
					validate_Internet_Office365_TV_RogersTvAddOn_TCV(dataTable);
				}
			}

			if (isTvAddOnAdded) {
				sf.seleU.clickElementByJSE(sf.gbj.nextButton);
				reportStatusPass(methodName + " Added Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product :"
						+ dataTable.get("Rogers TV AddOn Product"), true, true);
			} else {
				reportStatusFail(methodName + " Error While adding Rogers TV Add Ons to cart in GBJ", true);
			}
			sf.seleU.wait(15000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding Rogers TV Add Ons to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Add Rogers TV AddOn multiple Quantity for specifc Rogers
	 *                     TV AddOn Product
	 * 
	 *                     Update cart and Hit Next
	 */
	public void addRogers_MultipleTVAddOns(Hashtable<String, String> dataTable, boolean validateTCV)
			throws IOException {
		try {

			boolean isTvAddOnAdded = false;
			boolean isHDTerminalAdded = false;

			// convert arrays to list after split
			String tvAddOnQuantityArray[] = dataTable.get("Rogers TV AddOn Quantity").split(",");

			String tvAddOnArray[] = dataTable.get("Rogers TV AddOn Product").trim().split(",");
			List<String> arList = new ArrayList<String>(Arrays.asList(tvAddOnArray));

			for (int i = 0; i < sf.gbjCart.tvAddOnTerminal.size(); i++) {
				sf.seleU.wait(1000);

				for (int j = 0; j < arList.size(); j++) {

					if (sf.seleU.getTextFromWebElement(sf.gbjCart.tvAddOnTerminal.get(i)).trim()
							.equals(tvAddOnArray[j].trim())) {

						// Add Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product
						sf.seleU.clearAndEnterText(sf.gbjCart.productTerminalQtyNumeric.get(i),
								tvAddOnQuantityArray[j].trim());

						sf.seleU.enterText(sf.gbjCart.productTerminalQtyNumeric.get(i), Keys.TAB);

						reportStatusPass(
								methodName + " Selected Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product : "
										+ tvAddOnArray[j] + " with quantity as " + tvAddOnQuantityArray[j],
								true, true);
						break;
					}
				}
			}

			// for (int j = 0; j< sf.gbjCart.tvAddOnTerminal.size(); j++) {
			// if (sf.seleU.getTextFromWebElement(sf.gbjCart.tvAddOnTerminal.get(j))
			// .equals("Rogers TV AddOn HD PVR TerminalProduct")) {
			//
			// // Add Rogers TV AddOn Quantity for specifc Rogers TV AddOn Product
			// sf.seleU.clearAndEnterText(sf.gbjCart.productTerminalQtyNumeric.get(j),
			// dataTable.get("Rogers TV AddOn Quantity"));
			//
			// sf.seleU.enterText(sf.gbjCart.productTerminalQtyNumeric.get(j), Keys.TAB);
			//
			// reportStatusPass(
			// methodName + " Selected Rogers TV AddOn Quantity for specifc Rogers TV AddOn
			// Product :"
			// + "HD PVR Terminal" + " with qunatity as " + dataTable.get("Rogers TV AddOn
			// Quantity"),
			// true, true);
			// isHDTerminalAdded = true;
			// break;
			// }
			// }

			// Update cart and Hit Next
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.gbjCart.updateCartButton);
			reportStatusPass(methodName + " Clicked on Update Cart Button for "
					+ dataTable.get("Rogers TV AddOn Product") + " And " + "HD PVR Terminal", true, false);
			sf.seleU.wait(5000);
			//
			// if (isHDTerminalAdded) {
			//
			// reportStatusPass(methodName + " Added Rogers TV AddOn Quantity for specifc
			// Rogers TV AddOn Product :"
			// + dataTable.get("Rogers TV AddOn Product"), true, true);
			// } else {
			// reportStatusFail(methodName + " Error While adding Rogers TV Add Ons to cart
			// in GBJ", true);
			// }

			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			sf.seleU.wait(15000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding Rogers TV Add Ons to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	public void validate_Internet_Office365_TV_RogersTvAddOn_TCV(Hashtable<String, String> dataTable)
			throws IOException {

		InputData.monthylyTotal_num = InputData.monthylyTotal_num
				+ Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity"))
						* Double.parseDouble(dataTable.get("STB Price"))
				+ (Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity")) - 1)
						* Double.parseDouble(dataTable.get("Additional TV Pack Price"));
		InputData.recurringTCV_num = InputData.recurringTCV_num
				+ Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity"))
						* Double.parseDouble(dataTable.get("STB Price")) * 36
				+ (Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity")) - 1)
						* Double.parseDouble(dataTable.get("Additional TV Pack Price")) * 36;
		InputData.onetimeTCV_num = Double.parseDouble(dataTable.get("ONE TIME TCV"));
		InputData.totalTCV_num = InputData.recurringTCV_num + InputData.onetimeTCV_num;
		InputData.totalCost_num = InputData.totalCost_num
				+ Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity"))
						* Double.parseDouble(dataTable.get("STB Cost")) * 36
				+ (Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity")) - 1)
						* Double.parseDouble(dataTable.get("Additional TV Pack Cost")) * 36;
		InputData.tcvMargin_num = InputData.totalTCV_num - InputData.totalCost_num;
		InputData.tcvMarginPercentage_num = InputData.tcvMargin_num * 100 / InputData.totalTCV_num;

		sf.seleU.wait(15000);

		verifyTCV("MONTHLY TOTAL", sf.gbjCart.monthlyTotal, InputData.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.gbjCart.recurringTCV, InputData.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.gbjCart.oneTimeTCV, InputData.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.gbjCart.totalTCV, InputData.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.gbjCart.totalCosts, InputData.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.gbjCart.tcvMarginValue, InputData.tcvMargin_num);
		verifyTCV("TCV MARGIN %", sf.gbjCart.tcvMarginPercentage, InputData.tcvMarginPercentage_num);

	}

	public void validate_TV_RogersTvAddOn_TCV(Hashtable<String, String> dataTable) throws IOException {

		InputData.monthylyTotal_num = InputData.monthylyTotal_num
				+ Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity"))
						* Double.parseDouble(dataTable.get("STB Price"))
				+ (Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity")) - 1)
						* Double.parseDouble(dataTable.get("Additional TV Pack Price"));
		InputData.recurringTCV_num = InputData.recurringTCV_num
				+ Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity"))
						* Double.parseDouble(dataTable.get("STB Price"))
				+ (Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity")) - 1)
						* Double.parseDouble(dataTable.get("Additional TV Pack Price"));
		InputData.onetimeTCV_num = Double.parseDouble(dataTable.get("ONE TIME TCV"));
		InputData.totalTCV_num = InputData.recurringTCV_num + InputData.onetimeTCV_num;
		InputData.totalCost_num = InputData.totalCost_num
				+ Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity"))
						* Double.parseDouble(dataTable.get("STB Cost"))
				+ (Double.parseDouble(dataTable.get("Rogers TV AddOn Quantity")) - 1)
						* Double.parseDouble(dataTable.get("Additional TV Pack Cost"));
		InputData.tcvMargin_num = InputData.totalTCV_num - InputData.totalCost_num;
		InputData.tcvMarginPercentage_num = InputData.tcvMargin_num * 100 / InputData.totalTCV_num;

		sf.seleU.wait(15000);

		verifyTCV("MONTHLY TOTAL", sf.gbjCart.monthlyTotal, InputData.monthylyTotal_num);
		verifyTCV("RECURRING TCV", sf.gbjCart.recurringTCV, InputData.recurringTCV_num);
		verifyTCV("ONE TIME TCV", sf.gbjCart.oneTimeTCV, InputData.onetimeTCV_num);
		verifyTCV("TOTAL TCV", sf.gbjCart.totalTCV, InputData.totalTCV_num);
		verifyTCV("TOTAL COSTS", sf.gbjCart.totalCosts, InputData.totalCost_num);
		verifyTCV("TCV MARGIN $", sf.gbjCart.tcvMarginValue, InputData.tcvMargin_num);
		verifyTCV("TCV MARGIN %", sf.gbjCart.tcvMarginPercentage, InputData.tcvMarginPercentage_num);

	}

	/**
	 * @throws IOException
	 * 
	 *                     Accept Quote in GBJ Flow
	 */
	public void acceptQuoteInGBJ() throws IOException {
		try {

			// Accept Quote in GBJ Flow
			sf.seleU.clickElementByJSE(sf.gbjCart.acceptQuoteButton);
			reportStatusPass(methodName + " Accepted Quote in GBJ Flow", true, false);
			sf.seleU.wait(15000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Accepting Quote in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Author : Chethan K
	 * 
	 * @throws IOException
	 * 
	 *                     To capture emailBodyAttributes"
	 */
	public void captureEmailBodyAttributes() throws IOException {
		try {
			sf.dataInput.selectedProduct = sf.seleU.getTextFromWebElement(sf.gbjCart.selectedProduct.get(0));
			sf.seleU.clickElementByJSE(sf.gbjCart.detailsTab);
			sf.dataInput.businessAccName = sf.seleU.getTextFromWebElement(sf.gbjCart.businessAccName);
		} catch (Throwable e) {
			reportStatusFail(" Error in capturing emailBodyAttributes", e);
			e.printStackTrace();
		}

	}

	/**
	 * Author Chethan K
	 * 
	 * @throws IOException
	 * 
	 *                     Email Quote in GBJ Flow
	 */
	public void emailQuoteInGBJ() throws IOException {
		try {

			// Email Quote in GBJ Flow
			sf.seleU.clickElementByJSE(sf.gbjCart.emailQuoteButton);
			reportStatusPass(methodName + " Email Quote in GBJ Flow", true, false);
			sf.seleU.wait(15000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Emailing Quote in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Esignature Quote in GBJ Flow
	 */
	public void eSignatureQuoteInGBJ() throws IOException {
		try {

			// Esignature Quote in GBJ Flow
			sf.seleU.clickElementByJSE(sf.gbjCart.eSignatureButton);
			reportStatusPass(methodName + " Esignature Quote in GBJ Flow", true, false);
			sf.seleU.wait(15000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Esignature Quote in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Continue if Email Send Error occurs
	 */
	public void handleEmailActionError() throws IOException {
		try {

			// Press Continue in PopUp
			sf.seleU.switchToFrame(sf.gbjCart.emailErrorIFrame);
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.gbjCart.emailErrorPopUp)) {
				sf.seleU.clickElementByJSE(sf.gbjCart.continueButton);
				reportStatusPass(methodName + " Clicked Continue in Email Send Error PopUp", true, false);
				sf.seleU.wait(15000);
			}
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Accepting Quote in GBJ", e);
			e.printStackTrace();
		}
	}

	public void addProductAndVerifyPriceMarginInGBJ(Hashtable<String, String> dataTable) throws IOException {

		String methodName = "SFDC_CPQ Guided By journey Add products To Cart@: ";

		try {

			sf.seleU.wait(5000);

			sf.seleU.clickElementByJSE(sf.gbj.internetProduct);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Selected Internet Product", true, false);
			sf.seleU.wait(9000);

			sf.seleU.clickElementByJSE(sf.gbj.threeYearTermRadioButton);
			reportStatusPass(methodName + " Selected Three Year Term Radio Button", true, false);
			sf.seleU.wait(5000);

			String productName = dataTable.get("Product").split("-")[0].trim();

			// sf.seleU.clickElementByJSE(sf.gbjCart.gradeDropdownAllValues.get(2));

			sf.seleU.wait(5000);
			if (sf.seleU.isElementDisplayed(sf.gbjCart.productOptionsList.get(0))) {
				System.out.println(sf.gbjCart.productOptionsList.get(0).getText());

			}

			sf.seleU.wait(5000);
			System.out.println(sf.gbjCart.productOptionsList.get(0).getText());
			// sf.seleU.clickElementByJSE(sf.gbjCart.productOptionsList.get(1));

		} catch (Throwable e) {
			reportStatusFail(" Error while adding product to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws IOException
	 * 
	 *                     Verify field value matches the exepected value
	 */
	public void verifyTCV(String fieldName, WebElement ele, double expectedValue_num) throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Quote Configuration on GBJ Quote Cart@: ";
			double actualValue_num;
			double expectedValueRoundedOff;

			sf.seleU.scrollByCoOrdinates(1);

			if (fieldName.contains("%")) {
				String text = sf.seleU.getTextFromWebElement(ele);
				actualValue_num = Math.round(Double.parseDouble(text.substring(0, text.length() - 1)));
				expectedValueRoundedOff = Math.round(expectedValue_num);
				reportStatusPass(methodName + " Rounded off margin " + sf.seleU.getTextFromWebElement(ele) + " to "
						+ actualValue_num, false, true);

				System.out.println("..............Actual.......... :" + actualValue_num);
				System.out.println("..............Expected.........:" + expectedValueRoundedOff);

			} else {
				String text = sf.seleU.getTextFromWebElement(ele);
				actualValue_num = Double.parseDouble(text.substring(1, text.length()).replaceAll(",", ""));
				expectedValueRoundedOff = AdditionalUtilities.roundOffDouble2DecimalPlaces(expectedValue_num);

				System.out.println(".....**********Actual.......... :" + actualValue_num);
				System.out.println(".....**********Expected.........:" + expectedValueRoundedOff);

			}

			if (actualValue_num == expectedValueRoundedOff) {
				reportStatusPass(methodName + " Validated product " + fieldName + " as  " + expectedValueRoundedOff,
						true, true);
			} else {
				reportStatusFail(methodName + " Expected value for product " + fieldName + " is " + expectedValue_num
						+ " Actual value for Product " + fieldName + " is " + actualValue_num, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field value verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws Exception
	 * 
	 *                   Validate Price Field
	 */
	public void validatePrice(String fieldName, WebElement ele, String expectedValue) throws Exception {

		String actualValue = sf.seleU.getTextFromWebElement(ele);
		if (expectedValue.contains(actualValue)) {
			reportStatusPass(" Validated product " + fieldName + " as  " + expectedValue, true, true);
		} else {
			reportStatusFail(" Expected value for product " + fieldName + " is " + expectedValue
					+ " Actual value for Product " + fieldName + " is " + actualValue, true);
		}
	}

	/**
	 * @param product
	 * @param attributeColumn
	 * @return
	 * 
	 *         Get IBLC Hybrid cart Element based on the product and attribute
	 *         column
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	public WebElement getSpecific_GBJ_IBLC_Element(String product) throws IOException {

		WebElement prod_attr_ele = null;
		String attr_locator = "//input[contains(@id,'" + product
				+ "')]/following-sibling::label[@class='nds-radio__label']//span[2]";
		try {
			prod_attr_ele = sf.seleU.findElement(By.xpath(attr_locator));

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return prod_attr_ele;
	}

}
