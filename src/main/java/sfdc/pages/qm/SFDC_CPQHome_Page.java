package sfdc.pages.qm;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 20/jan/2020
 * 
 *         SFDC CPQ Home Page(Opportunity> Create Quote> Select Contact> Select
 *         service account)
 *
 */
public class SFDC_CPQHome_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_CPQHome_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Verify Quote Created Successfully
	 * 
	 *                     2- Save Quote number for future reference
	 * 
	 */
	public void verifyQuoteNumberInCPQHome() throws IOException {

		try {

			String methodName = "SFDC_CPQ Home@: ";

			// 1- Verify Quote Created Successfully
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.cpqHeaderControllerText);
			if (sf.seleU.getTextFromWebElement(sf.cpqHome.cpqHeaderControllerText.get(0)).length() > 0) {

				// Save Quote number for future reference
				InputData.quoteNumber = sf.seleU.getTextFromWebElement(sf.cpqHome.cpqHeaderControllerText.get(0));

				reportStatusPass(
						methodName + " Quote Number Verified as :"
								+ sf.seleU.getTextFromWebElement(sf.cpqHome.cpqHeaderControllerText.get(0)),
						true, true);
			} else {
				reportStatusFail(methodName + " Invalid Quote Number", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Invalid Quote in CPQ Home Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Review Quote Record Button
	 * 
	 */
	public void clickOnReviewQuoteRecordButton() throws IOException {
		try {
			String methodName = "SFDC_CPQ Home@: ";

			// Click on Review Quote Record Button
			sf.seleU.clickElementByJSE(sf.cpqHome.reviewQuoteRecordButton);
			reportStatusPass(methodName + " Clicked on Review Quote Record Button", true, false);

			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(15000);
		} catch (Throwable e) {
			reportStatusFail(" Invalid Quote in CPQ Home Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1.Select price list :SMB Price List as Price
	 *                              List
	 */
	public void selectPriceList() throws IOException, InterruptedException {
		String methodName = "SFDC_CPQ Home Add Products to Cart@: ";

		try {
			// Select price list : SMB Price List as Price List
			// sf.seleU.switchToElementFrame(sf.cpqHome.selectPriceList);
			if (sf.seleU.isElementPresent(sf.cpqHome.selectPriceList)) {
				sf.seleU.clickElementByJSE(sf.cpqHome.selectPriceList.get(0));
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.cpqHome.rogersSMBPriceList);
				reportStatusPass(methodName + " Selected Rogers SMB Price List as Price List", true, false);
			}

			sf.seleU.hardwait(10000);

		} catch (Throwable e) {
			reportStatusFail(" Failure in Selecting PriceList", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 * 
	 *                     Verify the Product and add the product to cart
	 * 
	 *                     Validate product successfully added to cart
	 */
	public void selectProductInHybridCart() throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Add Products to Cart@: ";

			if (Global.dataInput.quoteContractTerm.contains("36 Months")
					|| Global.dataInput.quotetempProduct.length() > 0) {
				Global.dataInput.quoteProductName = Global.dataInput.quotetempProduct;
			}
			if (addProductToCart()) {
				reportStatusPass(methodName + " Added product " + Global.dataInput.quoteProductName + " to cart", true,
						true);
			} else {
				addAnyAvailbleProduct();
			}
			verifyProductAddedToCart();

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding product to cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 * 
	 *                     Verify the Product and add the product to cart
	 * 
	 *                     Validate product successfully added to cart
	 */
	public boolean addProductToCart() throws IOException {
		boolean isProductFound = false;
		String methodName = "SFDC_CPQ Home Add Products to Cart@: ";
		try {

			// Verify the Product and add the product to cart

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.addToCartButtonsAllProduct);

			sf.seleU.clickElementByJSE(sf.cpqHome.productsTab);
			sf.seleU.clickElementByJSE(sf.cpqHome.qualifiedButton);
			sf.seleU.hardwait(2000);

			sf.seleU.clearAndEnterText(sf.cpqHome.searchProductPromotion, Global.dataInput.quoteProductName);
			sf.seleU.hardwait(2000);

			for (int i = 0; i < sf.cpqHome.addToCartButtonsAllProduct.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cpqHome.productNameTextAllProduct.get(i))
						.equalsIgnoreCase(Global.dataInput.quoteProductName)) {
					sf.seleU.clickElementByJSE(sf.cpqHome.addToCartButtonsAllProduct.get(i));
					isProductFound = true;
					break;

				}
			}
			if (isProductFound) {
				reportStatusPass(methodName + " Added Product " + Global.dataInput.quoteProductName + " to cart", true,
						true);
			} else {
				reportStatusFail(methodName + " No Product found as " + Global.dataInput.quoteProductName, true);
			}

			sf.seleU.wait(9000);
			//sf.seleU.refreshPage();
			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding product to cart", e);
			e.printStackTrace();
		}
		return isProductFound;
	}
	
	
	
	
	/**
	 * @throws IOException
	 * 
	 * 
	 * 
	 *                     Verify the V2 catalog Products and add the product to cart
	 * 
	 *                     Validate product successfully added to cart
	 */
	public boolean addV2ProductToCart(String productName) throws IOException {
		boolean isProductFound = false;
		try {
			String methodName = "SFDC_CPQ Home Add Products to Cart@: ";
			// Verify the Product and add the product to cart

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.addToCartButtonsAllProduct);

			sf.seleU.clickElementByJSE(sf.cpqHome.productsTab);
			sf.seleU.clickElementByJSE(sf.cpqHome.qualifiedButton);
			sf.seleU.hardwait(2000);

			sf.seleU.clearAndEnterText(sf.cpqHome.searchProductPromotion, productName);
			sf.seleU.hardwait(2000);

			for (int i = 0; i < sf.cpqHome.addToCartButtonsAllProduct.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cpqHome.productNameTextAllProduct.get(i))
						.equalsIgnoreCase(productName)) 
				{
					//clicks on more  
					sf.seleU.clickElementByJSE(sf.cpqHome.addToCartMore.get(i));
					sf.seleU.switchToActiveElement();
					//searches the product with keyword V2 and add to cart
					if (sf.seleU.getTextFromWebElement(sf.cpqHome.productCode).contains("V2")) {
						sf.seleU.hardwait(4000);
						sf.seleU.clickElementByJSE(sf.cpqHome.addToCartInPopUp);
						sf.seleU.hardwait(20000);
						isProductFound = true;
						break;
					}
					
					else {
						sf.seleU.clickElementByJSE(sf.cpqHome.closeInPopUp);
						sf.seleU.hardwait(3000);
					}
				}
			}

			if (isProductFound) {
				reportStatusPass(methodName + " Added V2 catalog Product " + productName + " to cart", true, true);
			} else {
				reportStatusFail(methodName + " No V2 catalog Product found as " + productName, true);
			}

			sf.seleU.hardwait(3000);
			sf.seleU.refreshPage();
			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding product to cart", e);
			e.printStackTrace();
		}
		return isProductFound;
	}
	
	

	/**
	 * @throws IOException
	 * 
	 * 
	 * 
	 *                     Verify the Product and add the product to cart
	 * 
	 *                     Validate product successfully added to cart
	 */
	public boolean addProductToCart(String productName) throws IOException {
		boolean isProductFound = false;
		try {
			String methodName = "SFDC_CPQ Home Add Products to Cart@: ";
			// Verify the Product and add the product to cart
			sf.seleU.refreshPage();
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.addToCartButtonsAllProduct);

			sf.seleU.clickElementByJSE(sf.cpqHome.productsTab);
			sf.seleU.clickElementByJSE(sf.cpqHome.qualifiedButton);
			sf.seleU.hardwait(2000);

			sf.seleU.clearAndEnterText(sf.cpqHome.searchProductPromotion, productName);
			sf.seleU.hardwait(2000);

			for (int i = 0; i < sf.cpqHome.addToCartButtonsAllProduct.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cpqHome.productNameTextAllProduct.get(i))
						.equalsIgnoreCase(productName)) {
					sf.seleU.clickElementByJSE(sf.cpqHome.addToCartButtonsAllProduct.get(i));
					sf.seleU.hardwait(5000);
					isProductFound = true;
					break;

				}
			}

			if (isProductFound) {
				reportStatusPass(methodName + " Added Product " + productName + " to cart", true, true);
			} else {
				reportStatusFail(methodName + " No Product found as " + productName, true);
			}

			sf.seleU.wait(10000);
			sf.seleU.refreshPage();
			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding product to cart", e);
			e.printStackTrace();
		}
		return isProductFound;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select availble product to cart
	 */
	public void addAnyAvailbleProduct() throws IOException {
		try {
			String methodName = "SFDC_CPQ Home Add Products to Cart@: ";

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.addToCartButtonsAllProduct);

			sf.seleU.clickElementByJSE(sf.cpqHome.productsTab);
			sf.seleU.clickElementByJSE(sf.cpqHome.qualifiedButton);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.cpqHome.addToCartButtonsAllProduct.get(0));
			reportStatusPass(methodName + "  selecting any availble product ", true, true);

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding product to cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate product successfully added to cart
	 */
	public void verifyProductAddedToCart() throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Add Products to Cart@: ";

			sf.seleU.wait(20000);

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText);

			// Validate product successfully added to cart
			if (sf.seleU.isElementPresent(sf.cpqHome.quoteCartShowActonDropdown)) {
				reportStatusPass(methodName + " Verified that product " + Global.dataInput.quoteProductName
						+ " is successfully added to cart", true, true);
			} else {
				reportStatusFail(methodName + " Failure in adding product  to cart", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding product to cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the Promotion and add the Promotion to cart
	 * 
	 *                     Validate Promotion successfully added to cart
	 * 
	 *                     Verify Accept Quote Button is Enabled
	 * 
	 *                     Verify Generate & Email Quote Button is Enabled
	 */
	public void addPromotionToCart() throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Add Promotions to Cart@: ";

			// Verify the Promotion and add the Promotion to cart
			boolean isPromoFound = false;
			sf.seleU.hardwait(2000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.addToCartButtonsAllPromotion);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cpqHome.promotionsTab);
			sf.seleU.clickElementByJSE(sf.cpqHome.qualifiedButton);
			sf.seleU.hardwait(2000);

			sf.seleU.clearAndEnterText(sf.cpqHome.searchProductPromotion, Global.dataInput.quoteProductName);
			sf.seleU.hardwait(2000);

			for (int i = 0; i < sf.cpqHome.addToCartButtonsAllPromotion.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cpqHome.promotionNameTextAllProduct.get(i)).trim()
						.equalsIgnoreCase(Global.dataInput.quoteProductName.trim())
						|| sf.seleU.getTextFromWebElement(sf.cpqHome.promotionNameTextAllProduct.get(i)).trim()
								.contains(Global.dataInput.quoteProductName.trim())) {
					sf.seleU.clickElementByJSE(sf.cpqHome.addToCartButtonsAllPromotion.get(i));
					sf.seleU.wait(3000);
					sf.seleU.clickElementByJSE(sf.cpqHome.applyPromotionButton);
					isPromoFound = true;
					break;

				}
			}

			if (isPromoFound) {
				sf.seleU.clickElementByJSE(sf.cpqHome.promotionsLinkIncart);
				reportStatusPass(methodName + " Added Promo " + Global.dataInput.quoteProductName + " to cart", true,
						true);

			} else {
				reportStatusFail(methodName + " No Promo found as " + Global.dataInput.quoteProductName, true);
			}

			// sf.seleU.switchToDefaultContent();
			// sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText);

			sf.seleU.wait(10000);

			// sf.seleU.clickElementByJSE(sf.cpqHome.promotionsLinkIncart);
			// sf.seleU.wait(15000);

			sf.seleU.clickElementByJSE(sf.cpqHome.cartLink);
			sf.seleU.wait(10000);
			sf.seleU.refreshPage();
			sf.seleU.wait(20000);

			/*
			 * sf.seleU.hardwait(5000);
			 * 
			 * int counter = 0; boolean productAdded = false;
			 * 
			 * while (!productAdded) {
			 * 
			 * sf.seleU.switchToDefaultContent(); sf.seleU.refreshPage();
			 * sf.seleU.hardwait(15000);
			 * 
			 * try { sf.seleU.switchToDefaultContent();
			 * sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText); } catch
			 * (Exception e) { sf.seleU.hardwait(9000); sf.seleU.refreshPage();
			 * sf.seleU.hardwait(15000); sf.seleU.switchToDefaultContent();
			 * sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText); } if
			 * (sf.seleU.isElementPresent(sf.cpqHome.quoteCartShowActonDropdown)) {
			 * productAdded = true; break; }
			 * sf.seleU.clickElementByJSE(sf.cpqHome.cartLink);
			 * 
			 * if (counter > 3) { break; } counter++; }
			 * 
			 * // Validate Promotion successfully added to cart if (productAdded) {
			 * 
			 * reportStatusPass(methodName + " Verified that promotion " +
			 * sf.dataInput.quoteProductName + " is successfully added to cart", true,
			 * true); } else { reportStatusFail(methodName +
			 * " Failure in adding promtoion  to cart", true); }
			 */

			// sf.seleU.hardwait(5000);

			// Verify Accept Quote Button is Enabled
			/*
			 * if (sf.cpqHome.quoteCartConfigAcceptQuoteButton.isEnabled()) {
			 * reportStatusPass(methodName + " Accept Quote Button is Enabled ", true,
			 * true); } else { reportStatusFail(methodName +
			 * " Accept Quote Button is Disabled ", true); }
			 * 
			 * // Verify Generate & Email Quote Button is Enabled if
			 * (sf.cpqHome.quoteCartConfigGenerateEmailButton.isEnabled()) {
			 * reportStatusPass(methodName + " Generate & Email Quote Button  is Enabled ",
			 * true, true); } else { reportStatusFail(methodName +
			 * " Generate & Email Quote Button  is Disabled ", true); }
			 */
		} catch (Throwable e) {
			reportStatusFail(" Failure in adding Promotions to cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Discounts tab
	 * 
	 *                     Add installation Dicount to Cart
	 * 
	 *                     Verify Discount added successfully
	 */
	public void addDiscountToCart() throws IOException {
		try {

			// Select Discounts tab
			sf.seleU.clickElementByJSE(sf.cpqHome.discountsButton);
			sf.seleU.clickElementByJSE(sf.cpqHome.qualifiedButton);
			sf.seleU.wait(5000);
			reportStatusPass(" Selected Discounts Tab", true, true);

			sf.seleU.clearAndEnterText(sf.cpqHome.searchProductPromotion, InputData.installationDiscounts);
			sf.seleU.wait(2000);

			boolean isDisountFound = false;
			for (int i = 0; i < sf.cpqHome.addToCartButtonsAllDiscounts.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cpqHome.discountNameTextAllProduct.get(i)).trim()
						.equalsIgnoreCase(InputData.installationDiscounts.trim())) {
					sf.seleU.clickElementByJSE(sf.cpqHome.addToCartButtonsAllDiscounts.get(i));
					isDisountFound = true;
					break;

				}
			}

			// Add installation Dicount to Cart
			if (isDisountFound) {
				reportStatusPass(" Adding Installation Discount to Product", true, true);
			} else {
				reportStatusFail(" Failure in adding Discounts to cart", true);
			}
			sf.seleU.wait(15000);

			// Verify Discount added successfully
			verifyFieldDisplayed("Installation Discount", sf.cpqHome.installationDiscount);
			sf.seleU.clickElementByJSE(sf.cpqHome.cartLink);
			sf.seleU.wait(10000);
			verifyFieldValue("Internet Installation ", sf.cpqHome.installationOneTimeChargeValue, "$0.00");

		} catch (Throwable e) {
			reportStatusFail(" Failure in adding Discounts to cart", e);
			e.printStackTrace();
		}
	}

	public void addSTBGroup(boolean isTVAndInternet) throws IOException {
		try {

			int counterTerminal = 0;
			boolean terminalAdded = false;

			// Fixed existing iframe issue by pointing to right frame
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.hdTerminalAddtoCartButtonList);
			// sf.seleU.clickElementByJSE(sf.cpqHome.sdDigitalTerminalAddtoCartButton);
			sf.seleU.clickElementByJSE(sf.cpqHome.hdTerminalAddtoCartButton);
			while (!terminalAdded) {

				sf.seleU.wait(12000);

				// if (sf.seleU.isElementDisplayed(sf.cpqHome.sdDigitalTerminalShowAction)) {
				if (sf.seleU.isElementDisplayed(sf.cpqHome.hdTerminalTerminalShowAction)) {
					terminalAdded = true;
					reportStatusPass(" Added STB Group " + " to cart", true, true);
					break;
				}

				if (counterTerminal > 6) {
					break;
				}
				counterTerminal++;
			}
			//validating TV Installation is waived off when the cart has a TV+Internet bundle
			if(isTVAndInternet)
				verifyFieldValue("TV Installation ", sf.cpqHome.installationOneTimeChargeValueTV, "$0.00");
			
			sf.seleU.wait(20000);
			sf.seleU.switchToDefaultContent();
		} catch (Throwable e) {
			reportStatusFail(" Failure in adding STB Group", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Product name is found in the cart
	 * 
	 *                     Click on Show Actions dropdown for the product and click
	 *                     on configure
	 * 
	 *                     Verify All Product configuration details fields are
	 *                     displayed
	 * 
	 *                     For Ignite Product, Static Ip Field should be displayed
	 * 
	 *                     Configure the quote and Accpet
	 */
	public void configureQuote() throws IOException {

		try {
			if (!sf.cpqHome.quoteCartConfigAcceptQuoteButton.isEnabled()) {
				String methodName = "SFDC_CPQ Home Quote Configuration on Quote Cart@: ";
				sf.seleU.switchToDefaultContent();
				sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText);

				// Verify Product name is found in the cart
				boolean isProductAddedToCart = false;
				for (int i = 0; i < sf.cpqHome.quoteCartProductNameText.size(); i++) {

					if (sf.seleU.getElementAttribute(sf.cpqHome.quoteCartProductNameText.get(i), "title")
							.equals(Global.dataInput.quoteProductName)) {

						isProductAddedToCart = true;
						sf.seleU.hardwait(5000);

						// Click on Show Actions dropdown for the product and click on configure
						sf.seleU.clickElementByJSE(sf.cpqHome.quoteCartShowActonDropdown.get(i));
						reportStatusPass(methodName + " Clicked on Show Actions Dropdown", true, false);

						sf.seleU.clickElementByJSE(sf.cpqHome.quoteCartConfigureLink.get(i));
						reportStatusPass(methodName + " Clicked on Configure Button", true, false);

						sf.seleU.hardwait(5000);
						/*
						 * Verify All Product configuration details fields are displayed
						 * 
						 * 1.Contract Term 2.Service Location 3.Internet Speeds 4.Contract Term Dropdown
						 * 5.Service Location Dropdown 6.Download Speed Dropdown 7.Upload Speed Dropdown
						 * 
						 * 
						 */
						verifyFieldDisplayed("Contract Term", sf.cpqHome.quoteCartConfigContractTermText);
						verifyFieldDisplayed("Service Location", sf.cpqHome.quoteCartConfigServiceLocationText);
						verifyFieldDisplayed("Internet Speeds", sf.cpqHome.quoteCartConfigInternetSpeedText);
						verifyFieldDisplayed("Contract Term Dropdown", sf.cpqHome.quoteCartConfigContractTermDropDown);

						verifyFieldDisplayed("Service Location Dropdown",
								sf.cpqHome.quoteCartConfigServiceLocationDropDown);

						verifyFieldDisplayed("Download Speed Dropdown",
								sf.cpqHome.quoteCartConfigDownloadSpeedDropDown);
						verifyFieldDisplayed("Upload Speed Dropdown", sf.cpqHome.quoteCartConfigUploadSpeedDropDown);

						// For Ignite Product, Static Ip Field should be displayed
						if (Global.dataInput.quoteProductName.equals("Ignite")) {
							verifyFieldDisplayed("Static Ips", sf.cpqHome.staticIpsLabel);
						}
						// Verify Service Location Dropdown is readonly
						if (sf.seleU.getElementAttribute(sf.cpqHome.quoteCartConfigServiceLocationDropDown, "disabled")
								.equals("true")
								&& sf.seleU
										.getSelectedTextFromDropDown(sf.cpqHome.quoteCartConfigServiceLocationDropDown)
										.equals(Global.dataInput.quoteServiceLocation)) {
							reportStatusPass(methodName
									+ " Service Location verified as ReadOnly and Service location pre-selected as "
									+ sf.seleU.getSelectedTextFromDropDown(
											sf.cpqHome.quoteCartConfigServiceLocationDropDown),
									true, true);
						} else {
							reportStatusFail(methodName + " Service Location is not readonly", true);
						}
						break;

					}
				}

				if (isProductAddedToCart) {

					// Verify Accept Quote Button is Disabled
					if (!sf.cpqHome.quoteCartConfigAcceptQuoteButton.isEnabled()) {
						reportStatusPass(methodName + " Accept Quote Button is Disabled ", true, true);
					} else {
						reportStatusFail(methodName + " Accept Quote Button is Enabled ", true);
					}

					// Verify Generate & Email Quote Button is Disabled
					if (!sf.cpqHome.quoteCartConfigGenerateEmailButton.isEnabled()) {
						reportStatusPass(methodName + " Generate & Email Quote Button  is Disabled ", true, true);
					} else {
						reportStatusFail(methodName + " Generate & Email Quote Button  is Enabled ", true);
					}

					// Select Contract Term
					sf.seleU.selectTextFromDropDown(sf.cpqHome.quoteCartConfigContractTermDropDown,
							Global.dataInput.quoteContractTerm);
					reportStatusPass(methodName + " Selected Contract term as : " + Global.dataInput.quoteContractTerm,
							true, false);
					sf.seleU.hardwait(2000);
					// Select Internet Download Speed
					sf.seleU.selectTextFromDropDown(sf.cpqHome.quoteCartConfigDownloadSpeedDropDown,
							Global.dataInput.quoteInternetDownloadSpeed);

					reportStatusPass(methodName + " Selected Internet DownLoad Speed as : "
							+ Global.dataInput.quoteInternetDownloadSpeed, true, false);
					sf.seleU.hardwait(2000);

					// Select Internet Upload Speed
					sf.seleU.selectTextFromDropDown(sf.cpqHome.quoteCartConfigUploadSpeedDropDown,
							Global.dataInput.quoteInternetUploadSpeed);

					reportStatusPass(methodName + " Selected Internet Upload Speed as : "
							+ Global.dataInput.quoteInternetUploadSpeed, true, false);
					sf.seleU.hardwait(2000);

					// For Ignite product, configure static Ip
					if (Global.dataInput.quoteProductName.equals("Ignite")) {

						if (Global.dataInput.quoteStaticIp.equals("1")) {
							sf.seleU.clickElementByJSE(sf.cpqHome.staticIps_1_radio);
						} else if (Global.dataInput.quoteStaticIp.equals("5")) {
							sf.seleU.clickElementByJSE(sf.cpqHome.staticIps_5_radio);
						}
						reportStatusPass(methodName + " Selected static IP as : " + Global.dataInput.quoteStaticIp,
								true, false);
						sf.seleU.hardwait(2000);

					}
				} else {
					reportStatusFail(methodName + "Quote Configuration Fields are not populated", true);
				}

				sf.seleU.hardwait(20000);

				// Verify Accept Quote Button is Enabled
				if (sf.cpqHome.quoteCartConfigAcceptQuoteButton.isEnabled()) {
					reportStatusPass(methodName + " Accept Quote Button is Enabled ", true, true);
				} else {
					reportStatusFail(methodName + " Accept Quote Button is Disabled ", true);
				}

				// Verify Generate & Email Quote Button is Enabled
				if (sf.cpqHome.quoteCartConfigGenerateEmailButton.isEnabled()) {
					reportStatusPass(methodName + " Generate & Email Quote Button  is Enabled ", true, true);
				} else {
					reportStatusFail(methodName + " Generate & Email Quote Button  is Disabled ", true);
				}

				sf.seleU.hardwait(5000);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Quote Configuration", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param price
	 * @param cost
	 * @throws IOException
	 * 
	 *                     Validate Product Price
	 * 
	 *                     Validate Product Cost
	 */
	public void validateProductPriceAndCost(Hashtable<String, String> dataTable) throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Quote Configuration on Quote Cart@: ";

			// Validate Product Price
			if (sf.seleU.getTextFromWebElement(sf.cpqHome.recurringChargeAllValues.get(0))
					.equals("$" + dataTable.get("Price"))) {
				reportStatusPass(methodName + " Validated product price as  $" + dataTable.get("Price"), true, true);
			} else {
				reportStatusFail(methodName + " Expected product price as $" + dataTable.get("Price")
						+ " Actual Product price as "
						+ sf.seleU.getTextFromWebElement(sf.cpqHome.recurringChargeAllValues.get(0)), true);
			}

			// Validate Product Cost
			if (sf.seleU.getTextFromWebElement(sf.cpqHome.recurringCostAllValues.get(0))
					.equals("$" + dataTable.get("Cost To Use"))) {
				reportStatusPass(methodName + " Validated product Cost as  $" + dataTable.get("Cost To Use"), true,
						true);
			} else {
				reportStatusFail(methodName + " Expected product Cost as $" + dataTable.get("Cost To Use")
						+ " Actual Product price as "
						+ sf.seleU.getTextFromWebElement(sf.cpqHome.recurringCostAllValues.get(0)), true);
			}

			// Validate One Time Charge and One Time Cost

			if (sf.seleU.getTextFromWebElement(sf.cpqHome.installationOneTimeChargeValue)
					.equals("$" + dataTable.get("One Time Charge"))
					&& sf.seleU.getTextFromWebElement(sf.cpqHome.installationOneTimeCostValue)
							.equals("$" + dataTable.get("One Time Cost"))) {
				reportStatusPass(
						methodName + " Validated One time Charge  and one time cost as  $"
								+ dataTable.get("One Time Charge") + " and $" + dataTable.get("One Time Cost"),
						true, true);
			} else {
				reportStatusFail(methodName + " Invalid one time charge and cost -->"
						+ sf.seleU.getTextFromWebElement(sf.cpqHome.installationOneTimeChargeValue) + " and "
						+ sf.seleU.getTextFromWebElement(sf.cpqHome.installationOneTimeCostValue), true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Validating Product Price and Cost", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Scrum specific validation "CPQ-OM: valiadte STB group
	 *                     charge and group cost
	 */
	public void validateProductPriceAndCostScrum(Hashtable<String, String> dataTable) throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Quote Configuration on Quote Cart@: ";

			// Validate STB Group Charge and STB Group Cost

			if (sf.seleU.getTextFromWebElement(sf.cpqHome.terminalSTBGroupRecurringCharge)
					.equals("$" + dataTable.get("STB Group Charge"))
					&& sf.seleU.getTextFromWebElement(sf.cpqHome.terminalSTBGroupRecurringCost)
							.equals("$" + dataTable.get("STB Group Cost"))) {
				reportStatusPass(
						methodName + " Validated STB Group Charge  and STB Group Cost as  $"
								+ dataTable.get("STB Group Charge") + " and $" + dataTable.get("STB Group Cost"),
						true, true);
			} else {
				reportStatusFail(methodName + " Invalid STB Group  charge and cost -->"
						+ sf.seleU.getTextFromWebElement(sf.cpqHome.terminalSTBGroupRecurringCharge) + " and "
						+ sf.seleU.getTextFromWebElement(sf.cpqHome.terminalSTBGroupRecurringCost), true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Validating Product Price and Cost", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Validate Recurring Charge , Recurring price and Recurring
	 *                     margin for TV and internet
	 */
	public void validateProductRecurringPriceCostMargin(Hashtable<String, String> dataTable) throws IOException {
		try {

			// sf.seleU.clickElementByJSE(sf.cpqHome.promotionsLinkIncart);
			// sf.seleU.clickElementByJSE(sf.cpqHome.cartLink);
			// sf.seleU.refreshPage();
			// sf.seleU.hardwait(15000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText);

			// Select Advance View
			if (!(sf.seleU.getTextFromWebElement(sf.cpqHome.cpqCustomViewButton).contains("Advanced View"))) {
				sf.seleU.clickElementByJSE(sf.cpqHome.cpqCustomViewButton);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.cpqHome.advancedViewOption);
				sf.seleU.wait(2000);
			}

			String priceCostInfoInternet = " . Expected Recurring Price Charge, Cost, Margin  are::  Internet::: "
					+ dataTable.get("Recurring  Charge Internet") + "___" + dataTable.get("Recurring  Cost Internet")
					+ "___" + dataTable.get("Recurring Margin- Int");

			// Internet
			if (!(dataTable.get("Int_TV").trim().equals("TV"))) {

				verifyFieldValue("Recurring  Charge Internet", sf.cpqHome.recurringChargeAllValues.get(0),
						"$" + dataTable.get("Recurring  Charge Internet"));
				verifyFieldValue("Recurring  Cost Internet", sf.cpqHome.recurringCostAllValues.get(0),
						"$" + dataTable.get("Recurring  Cost Internet"));
				verifyFieldValue("Recurring Margin- Int", sf.cpqHome.recurringMarginAllValues.get(0),
						dataTable.get("Recurring Margin- Int"));

				ScreenDocs.captureScreenShot(docxDataSpecific, runDataSpecific, outDataSpecific,
						dataTable.get("Product") + priceCostInfoInternet);
			}
			// TV

			if (!(dataTable.get("Basic_VIP").trim().equals("NA"))) {

				verifyFieldValue("Recurring  Charge TV", sf.cpqHome.bundledTVRecurringCharge,
						"$" + dataTable.get("Recurring  Charge TV"));
				verifyFieldValue("Recurring  Cost TV", sf.cpqHome.bundledTVRecurringCost,
						"$" + dataTable.get("Recurring  Cost TV"));
				verifyFieldValue("Recurring Margin-TV", sf.cpqHome.bundledTVRecurringMargin,
						dataTable.get("Recurring Margin-TV"));

				String priceCostInfoTV = " . Expected Recurring Price Charge, Cost, Margin  are::  " + "  TV::: "
						+ dataTable.get("Recurring  Charge TV") + "___" + dataTable.get("Recurring  Cost TV") + "___"
						+ dataTable.get("Recurring Margin-TV");

				sf.seleU.ScrolltoElement(sf.cpqHome.bundledTVRecurringMargin);

				ScreenDocs.captureScreenShot(docxDataSpecific, runDataSpecific, outDataSpecific,
						dataTable.get("Product") + priceCostInfoTV);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Validating Product Price and Cost", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Show Actions dropdown for the product
	 * 
	 *                     Delete the Product
	 */
	public void deleteProductFromCart() throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Delete Product From cart@: ";
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText);

			// Click on Show Actions dropdown for the product
			if (sf.cpqHome.quoteCartShowActonDropdown.size() > 0) {
				sf.seleU.clickElementByJSE(sf.cpqHome.quoteCartShowActonDropdown.get(0));
				reportStatusPass(methodName + " Clicked on Show Actions Dropdown", true, false);

				// Delete the Product
				sf.seleU.clickElementByJSE(sf.cpqHome.quoteProductDeleteLink);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.cpqHome.deleteButton);
				reportStatusPass(methodName + " Deleted the product from cart", true, false);

				sf.seleU.hardwait(6000);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Deleting the product from cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Promtion Link In cart
	 * 
	 *                     Click on Delete Button
	 */
	public void deletePromotionFromCart() throws IOException {
		try {
			String methodName = "SFDC_CPQ Home Delete Promotion From cart@: ";

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartProductNameText);

			// Click on Show Actions dropdown for the product
			if (sf.cpqHome.quoteCartShowActonDropdown.size() > 0) {

				// Click on Promtion Link In cart
				sf.seleU.clickElementByJSE(sf.cpqHome.promotionsLinkIncart);
				reportStatusPass(methodName + " Clicked on Promtion Link In cart", true, false);
				sf.seleU.hardwait(6000);

				// Click on Delete Button

				sf.seleU.clickElementByJSE(sf.cpqHome.deleteButton);
				sf.seleU.hardwait(4000);
				sf.seleU.clickElementByJSE(sf.cpqHome.deletePromotionButton);
				reportStatusPass(methodName + " Deleted the Promotion from cart", true, false);

				sf.seleU.hardwait(6000);

			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Deleting the Promotion from cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Accept the Quote
	 * 
	 */
	public void acceptQuote() throws IOException {
		try {

			// Click on Accept the Quote
			String methodName = "SFDC_CPQ Home Quote Configuration on Quote Cart@: ";
			sf.seleU.wait(4000);
			// Fixed Existing iframe issue by pointing to right frame
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.quoteCartConfigAcceptQuoteButtonList);

			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.cpqHome.quoteCartConfigAcceptQuoteButton);
			int maxCount = 10;
			int i = 0;
			while (sf.seleU.isElementPresent(sf.cpqHome.quoteCartConfigAcceptQuoteButton)) {
				sf.seleU.clickElementByJSE(sf.cpqHome.quoteCartConfigAcceptQuoteButton);
				sf.seleU.wait(4000);
				i++;
				if (i == maxCount) {
					break;
				}
			}

			reportStatusPass(methodName + " Clicked on Accept Quote Button", true, false);

			sf.seleU.wait(30000);

		} catch (

		Throwable e) {
			reportStatusFail(" Error in accepting the quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *   Click on 'Request for E-signature' in hybrid cart flow via ViewRecord button 
	 *   after adding product to cart
	 * 
	 */
	public void requestEsignature() throws IOException {
		try {

			
			String methodName = "Request ESignature in HybridCart@: ";
			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.viewRecordBeforeESIGN);
			sf.seleU.clickElementByJSE(sf.cpqHome.viewRecordBeforeESIGN.get(0));
			reportStatusPass(methodName + " clicked on Viewrecord button", true, false);
			
			sf.seleU.hardwait(2000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqHome.Esignature);
//			if (sf.cpqHome.dropdownBeforeESIGN.size() > 1) {
//				sf.seleU.clickElementByJSE(sf.cpqHome.dropdownBeforeESIGN.get(1));
//			} else {
//				sf.seleU.clickElementByJSE(sf.cpqHome.dropdownBeforeESIGN.get(0));
//			}
//			sf.seleU.hardwait(2000);
			//click on request for e signature
			sf.seleU.clickElementByJSE(sf.cpqHome.Esignature.get(0));
			reportStatusPass(methodName + " clicked on Request for E-signature Button", true, false);
			sf.seleU.wait(40000);

		} catch (

		Throwable e) {
			reportStatusFail(" Error in  clicking request  for e-signature", e);
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on continue button after accepting the quote if
	 *                     close date is less then future date
	 * 
	 */
	public void clickContinue() throws IOException {
		try {

			// Click on Accept the Quote
			String methodName = "SFDC_CPQ Home Quote Configuration on Quote Cart@: ";
			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.cpqHome.continueButtonIframe);
			sf.seleU.ScrolltoElement(sf.cpqHome.continueButton);
			sf.seleU.hardwait(2000);
			int maxCount = 10;
			int i = 0;
			while (sf.seleU.isElementPresent(sf.cpqHome.continueButton)) {
				sf.seleU.clickElementByJSE(sf.cpqHome.continueButton);
				sf.seleU.wait(2000);
				i++;
				if (i == maxCount) {
					break;
				}
			}

			reportStatusPass(methodName + " Clicked on conitnue Button if the opp close date is less then future date",
					true, false);

			sf.seleU.wait(30000);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking continue button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is mandatory fields
	 */
	public void verifyFieldIsRequired(String fieldName, WebElement field) throws IOException {
		try {
			if (field.getAttribute("required").equals("true")) {
				reportStatusPass(" Validated " + fieldName + " field is required field", true, true);
			} else {
				reportStatusFail(" Field " + fieldName + "is not a mandatory field", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Error in validating required field", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (element.isDisplayed()) {
				reportStatusPass(
						"Validated " + fieldName + " is " + "Displayed as " + sf.seleU.getTextFromWebElement(element),
						true, true);
			} else {
				reportStatusFail("Field " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
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
	public void verifyFieldValue(String fieldName, WebElement ele, String expectedValue) throws IOException {
		try {

			String methodName = "SFDC_CPQ Home Quote Configuration on Quote Cart@: ";
			String actualValue;
			if (fieldName.contains("Margin")) {
				String text = sf.seleU.getTextFromWebElement(ele);
				actualValue = String.valueOf(Math.round(Double.parseDouble(text.substring(0, text.length() - 1))))
						+ "%";
				reportStatusPass(methodName + " Rounded off margin " + sf.seleU.getTextFromWebElement(ele) + " to "
						+ actualValue, false, true);

			} else {
				actualValue = sf.seleU.getTextFromWebElement(ele);

			}

			if (actualValue.equals(expectedValue)) {
				reportStatusPass(methodName + " Validated product " + fieldName + " as  " + expectedValue, true, true);
			} else {
				// sf.seleU.highlightFailedEle(ele);
				reportStatusFail(methodName + " Expected value for product " + fieldName + " is " + expectedValue
						+ " Actual value for Product " + fieldName + " is " + actualValue, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field value verification", e);
			e.printStackTrace();
		}
	}
}
