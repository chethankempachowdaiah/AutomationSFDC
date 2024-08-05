package sfdc.pages.qm;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya
 * 
 *         Guided By Journey Page Objects for IBLC Products
 *
 */

public class SFDC_GuidedByJourney_IBLC_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_GuidedByJourney_IBLC_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_CPQ Guided By journey IBLC @: ";
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select IBLC Product
	 * 
	 *                     Select Contract Term Monthly/3 Years
	 * 
	 *                     Add promo and Discounts if applicable
	 * 
	 *                     Add IBLC Product to cart
	 * 
	 *                     Verify IBLC Successfully added to cart
	 */
	public void add_BusinessPhone_GBJ(Hashtable<String, String> dataTable, boolean validateTCV) throws IOException {

		try {

			// Select IBLC Product
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.gbj.iBLCProduct);
			sf.seleU.clickElementByJSE(sf.gbj.iBLCProduct);
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Selected Business Phone Product", true, false);
			sf.seleU.wait(15000);

			// Select Contract Term Monthly/3 Years
			selectContractTerm(dataTable);

			// Apply IBLC Promo if Applicable
			applyIBLCPromo(dataTable);

			addIBLCToCart(dataTable, dataTable.get("IBLC Product_1"));
			addIBLCToCart(dataTable, dataTable.get("IBLC Product_2"));
			addIBLCToCart(dataTable, dataTable.get("IBLC Product_3"));

			// Hit Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked On Next Button in Business Phone Product Page", true, false);
			sf.seleU.wait(15000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While adding Business Phone Product to cart in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param contarctTerm
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select Monthly/3 Year Term
	 */
	public void selectContractTerm(Hashtable<String, String> dataTable) throws IOException, InterruptedException {

		try {

			// Select Monthly
			if (dataTable.get("Contract_Term").equals(sf.dataInput.contractTermMonthly)) {
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
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Selecting Contract Term IBLC in GBJ", e);
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
	public void addIBLCToCart(Hashtable<String, String> dataTable, String iBLCproduct) throws IOException {

		try {
			// Iterate and Verify the IBLC Product to be added to Cart
			boolean isIBLCAdded = false;
			if (iBLCproduct.equals(sf.dataInput.iblcBasic)) {
				// Validate IBLC Product Price
				if (!dataTable.get("Recurring Charge Phone").equals("NA")) {
					validateIBLCPrice("Recurring Charge Phone ", sf.gbj.iblcBusinessPhoneBasicPrice,
							dataTable.get("Recurring Charge Phone"));
				}

				sf.seleU.clickElementByJSE(sf.gbj.iblcBusinessPhoneBasicAddToCart);
				reportStatusPass(methodName + " Added IBLC Prodct to cart: " + iBLCproduct, true, false);
				isIBLCAdded = true;
				sf.seleU.wait(25000);
			}
			if (iBLCproduct.equals(sf.dataInput.iblcStandard)) {
				// Validate IBLC Product Price
				if (!dataTable.get("Recurring Charge Phone").equals("NA")) {
					validateIBLCPrice("Recurring Charge Phone ", sf.gbj.iblcBusinessPhoneStandardPrice,
							dataTable.get("Recurring Charge Phone"));
				}

				sf.seleU.clickElementByJSE(sf.gbj.iblcBusinessPhoneStandardAddToCart);
				reportStatusPass(methodName + " Added IBLC Prodct to cart: " + iBLCproduct, true, false);
				isIBLCAdded = true;
				sf.seleU.wait(25000);
			}
			if (iBLCproduct.equals(sf.dataInput.iblcPro)) {
				// Validate IBLC Product Price
				if (!dataTable.get("Recurring Charge Phone").equals("NA")) {
					validateIBLCPrice("Recurring Charge Phone ", sf.gbj.iblcBusinessPhoneProPrice,
							dataTable.get("Recurring Charge Phone"));
				}

				sf.seleU.clickElementByJSE(sf.gbj.iblcBusinessPhoneProAddToCart);
				reportStatusPass(methodName + " Added IBLC Prodct to cart: " + iBLCproduct, true, false);
				isIBLCAdded = true;
				sf.seleU.wait(25000);
			}

			// Verify IBLC Successfully added to cart
			if (isIBLCAdded) {
				reportStatusPass(methodName + " Successfully found and added " + iBLCproduct, true, false);
			} else {
				if ((!iBLCproduct.equals("NA")))
					reportStatusFail(methodName + " No Product added : " + iBLCproduct, true);
			}

		} catch (

		Throwable e) {
			reportStatusFail(methodName + " Error While Adding IBLC Product to Cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @param prod
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Click on Promotions and Discounts link
	 * 
	 *                              Apply Promo(IBLC Promo 1/Promo2)
	 * 
	 */
	public void applyIBLCPromo(Hashtable<String, String> dataTable) throws IOException, InterruptedException {//

		try {

			if (!dataTable.get("IBLC Promo").equals("NA")) {
				// Click on Promotions and Discounts link
				sf.seleU.clickElementByJSE(sf.gbj.promotionsAndDiscountsAllLinks.get(0));
				sf.seleU.wait(15000);

				// Apply Promo(IBLC Promo 1/Promo2)
				if (dataTable.get("IBLC Promo").contains("Promo 1"))
					sf.seleU.clickElementByJSE(sf.gbjIBLC.iblcPromo1_allRadioBtn.get(0));
				else if (dataTable.get("IBLC Promo").contains("Promo 2"))
					sf.seleU.clickElementByJSE(sf.gbjIBLC.iblcPromo2_allRadioBtn.get(0));
				else
					sf.seleU.clickElementByJSE(sf.gbjIBLC.iblcInternetBundlePromo_allRadioBtn.get(0));

				reportStatusPass(methodName + " Selected IBLC Promo : " + dataTable.get("IBLC Promo"), true, false);
				sf.seleU.wait(8000);

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Applying Promo in IBLC in GBJ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Iterate and add a line for respective IBLC Product
	 */
	public void addALine_IBLC(Hashtable<String, String> dataTable) throws IOException {
		try {

			if (!dataTable.get("IBLC_Lines").equals("NA")) {

				int linesToBeAdded = Integer.parseInt(dataTable.get("IBLC_Lines")) - 1;

				// Iterate and add a line for respective IBLC Product
				addIBLCLine(dataTable.get("IBLC Product_1"), linesToBeAdded);
				// addIBLCLine(dataTable.get("IBLC Product_2"), linesToBeAdded);
				// addIBLCLine(dataTable.get("IBLC Product_3"), linesToBeAdded);

			}

			// Update Quantity for each line added
			for (int i = 0; i < sf.gbjIBLC.iblc_numeric_Input.size(); i++) {
				sf.seleU.clearAndEnterText(sf.gbjIBLC.iblc_numeric_Input.get(i), dataTable.get("IBLC_Line_Qty"));
				sf.seleU.wait(2000);
				sf.seleU.enterText(sf.gbjIBLC.iblc_numeric_Input.get(i), Keys.TAB);
				sf.seleU.clickElementByJSE(sf.gbjIBLC.iblc_rogersBusinessPhone_Text);
				sf.seleU.wait(2000);
			}

			// Update the Cart post adding the Line Quantity
			sf.seleU.clickElementByJSE(sf.gbjIBLC.lineQty_UpdateCart_Btn);
			reportStatusPass(methodName + " Clicked on Update Cart after entering line Quanity", true, false);
			sf.seleU.wait(8000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Adding a Line in IBLC in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param iblcProduct
	 * @param noOfLines
	 * @throws IOException
	 * 
	 *                     Add No of Lines based on the IBLC product
	 */
	public void addIBLCLine(String iblcProduct, int noOfLines) throws IOException {
		try {

			// Add No of Lines based on the IBLC product
			if (!iblcProduct.equals("NA")) {
				for (int j = 0; j < noOfLines; j++) {

					if (iblcProduct.equals(sf.dataInput.iblcStandard))
						sf.seleU.clickElementByJSE(sf.gbjIBLC.iblc_Standard_AddALine_Btn);

					if (iblcProduct.equals(sf.dataInput.iblcBasic))
						sf.seleU.clickElementByJSE(sf.gbjIBLC.iblc_Basic_AddALine_Btn);

					if (iblcProduct.equals(sf.dataInput.iblcPro))
						sf.seleU.clickElementByJSE(sf.gbjIBLC.iblc_Pro_AddALine_Btn);

					reportStatusPass(methodName + " Added a Line for " + iblcProduct, true, false);
					sf.seleU.wait(8000);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Adding a Line in IBLC in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Click on Edit Line Link
	 * 
	 *                     Edit Each Line Features and Hit next
	 * 
	 *                     Update Cart on Equipment and Installation and Hit next
	 * 
	 *                     Update Quantity for each line added
	 * 
	 *                     Select Unlisted Number Checkbox
	 * 
	 *                     Update the Cart post adding Additional Installation and
	 *                     Account Features
	 * 
	 * 
	 */
	public void editLine_IBLC(Hashtable<String, String> dataTable) throws IOException {

		try {

			for (int i = 0; i < sf.gbjIBLC.iblcEditLine_allLinks.size(); i++) {

				// Click on Edit Line Link
				sf.seleU.clickElementByJSE(sf.gbjIBLC.iblcEditLine_allLinks.get(i));
				reportStatusPass(methodName + "Clicked on 'Edit' Button in IBLC", true, false);
				sf.seleU.wait(8000);

				// Edit Each Line Features
				editBusinessPhoneLineFeatures(dataTable);
			}

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked on Next button post adding/editing all IBLC Lines", true, false);
			sf.seleU.wait(10000);

			// Update Cart on Equipment and Installation
			sf.seleU.clickElementByJSE(sf.gbjIBLC.updateCartButton);
			reportStatusPass(methodName + " Clicked on Update cart button on Equipment and Installation", true, false);
			sf.seleU.wait(8000);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked on Next button on Equipment and Installation", true, false);
			sf.seleU.wait(10000);

			// Update Quantity for each line added
			sf.seleU.clearAndEnterText(sf.gbjIBLC.iblc_numeric_Input.get(0), dataTable.get("Jack_Qty"));
			reportStatusPass(methodName + " Configured the Quantity for Technician Install - Jacks as: "
					+ dataTable.get("Jack_Qty"), true, false);
			sf.seleU.wait(2000);

			// Select Unlisted Number Checkbox
			sf.seleU.clickElementByJSE(sf.gbjIBLC.unlistedNumber_checkBox);
			reportStatusPass(methodName + " Clicked on Unlisted Number Checkbox ", true, false);
			sf.seleU.wait(5000);

			// Update the Cart post adding Additional Installation and Account Features
			sf.seleU.clickElementByJSE(sf.gbjIBLC.lineQty_UpdateCart_Btn);
			reportStatusPass(methodName + " Clicked on Update Cart on Additional Installation and Account Features",
					true, false);
			sf.seleU.wait(8000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Editing a Line in IBLC in GBJ", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Select IBLC Number Type Ported/Native
	 * 
	 *                     Choose Voice feature Group
	 * 
	 *                     Update the cart post adding voice features group and
	 *                     Click on Next Button
	 * 
	 *                     Add Long Distance Plans (Optional) and Click on Next
	 *                     Button
	 * 
	 *                     Select Distinctive Ring (Optional) and Click on Next
	 *                     Button
	 * 
	 *                     Select Distinctive Ring Type Ported/Native
	 * 
	 *                     Update the cart post adding Distinctive Ring and Click on
	 *                     Next Button
	 * 
	 *                     Add special and blocking features
	 * 
	 *                     Add Administrative features Group
	 * 
	 *                     Update the cart post adding Admin and Block Features and
	 *                     Click on Next Button
	 * 
	 */
	public void editBusinessPhoneLineFeatures(Hashtable<String, String> dataTable) throws IOException {
		try {

			// Select IBLC Number Type Ported/Native
			if (dataTable.get("IBLC_Number Type").equals(sf.dataInput.iblcNumberTypePorted)) {
				sf.seleU.clickElementByJSE(sf.gbjIBLC.ported_RadioBtn.get(0));
			} else {
				sf.seleU.clickElementByJSE(sf.gbjIBLC.native_RadioBtn.get(0));
			}
			reportStatusPass(methodName + " Selected IBLC Numer Type As : " + dataTable.get("IBLC_Number Type"), true,
					false);

			// Choose Voice feature Group
			int vfgCount = 0;
			int vfgQty = Integer.parseInt(dataTable.get("VFG_Qty"));

			for (int i = 0; i < sf.gbjIBLC.voiceFeatureGroup_allCheckBox.size(); i++) {

				if (sf.gbjIBLC.voiceFeatureGroup_allChkBoxInput.get(i).isEnabled()) {

					System.out.println(sf.gbjIBLC.voiceFeatureGroup_allChkBoxInput.get(i).isEnabled());

					sf.seleU.clickElementByJSE(sf.gbjIBLC.voiceFeatureGroup_allCheckBox.get(i));
					sf.seleU.wait(2000);

					vfgCount++;
				}

				if (vfgCount == vfgQty && sf.seleU.isElementDisplayed(sf.gbjIBLC.updateCartButton)
						&& sf.gbjIBLC.updateCartButton.isEnabled()) {
					reportStatusPass(methodName + " Added " + vfgQty + " Voice features group", true, false);
					break;
				}

				sf.seleU.clickElementByJSE(sf.gbjIBLC.iblc_rogersBusinessPhone_Text);
				sf.seleU.enterText(sf.gbjIBLC.iblc_rogersBusinessPhone_Text, Keys.TAB);
				sf.seleU.wait(5000);
			}

			// Update the cart post adding voice features group
			sf.seleU.clickElementByJSE(sf.gbjIBLC.updateCartButton);
			reportStatusPass(methodName + " Clicked on Update cart button voice features group update", true, false);
			sf.seleU.wait(8000);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked on Next button voice features group update", true, false);
			sf.seleU.wait(8000);

			// Add Long Distance Plans (Optional)
			for (int j = 0; j < sf.gbjIBLC.longDistancePlan_AllRadioBtns.size(); j++) {
				if (sf.seleU.getTextFromWebElement(sf.gbjIBLC.longDistancePlan_AllRadioBtns.get(j))
						.equals(dataTable.get("Long Distance Plan"))) {
					sf.seleU.clickElementByJSE(sf.gbjIBLC.longDistancePlan_AllRadioBtns.get(j));
					reportStatusPass(
							methodName + " Selected Long Distance Plan as " + dataTable.get("Long Distance Plan"), true,
							false);
					break;
				}
			}

			// Update the cart post adding Long Distance Plans (Optional)
			if (sf.seleU.isElementDisplayed(sf.gbjIBLC.updateCartButton)) {
				sf.seleU.clickElementByJSE(sf.gbjIBLC.updateCartButton);
				reportStatusPass(methodName + " Clicked on Update cart button voice features group update", true,
						false);
				sf.seleU.wait(8000);
			}
			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked on Next button post adding Long Distance Plans (Optional)", true,
					false);
			sf.seleU.wait(8000);

			// Select Distinctive Ring (Optional)
			int dr_Qty = Integer.parseInt(dataTable.get("DR_Qty"));

			for (int k = 0; k < dr_Qty; k++) {
				sf.seleU.clickElementByJSE(sf.gbjIBLC.distinctiveRing_AllCheckBoxes.get(k));
				reportStatusPass(methodName + "Selected Distinctive Ring :" + (k + 1), true, false);
				sf.seleU.wait(15000);

				// Select Distinctive Ring Type Ported/Native
				if (dataTable.get("IBLC_Number Type").equals(sf.dataInput.iblcNumberTypePorted)) {
					sf.seleU.clickElementByJSE(sf.gbjIBLC.ported_RadioBtn.get(k));
				} else {
					sf.seleU.clickElementByJSE(sf.gbjIBLC.native_RadioBtn.get(k));
				}
				reportStatusPass(methodName + " Selected Distinctive Ring" + (k + 1) + " As : "
						+ dataTable.get("IBLC_Number Type"), true, false);
				sf.seleU.wait(5000);
			}

			// Update the cart post adding Distinctive Ring
			sf.seleU.clickElementByJSE(sf.gbjIBLC.updateCartButton);
			reportStatusPass(methodName + " Clicked on Update cart button post adding Distinctive Ring", true, false);
			sf.seleU.wait(8000);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked on Next button post adding Distinctive Ring", true, false);
			sf.seleU.wait(8000);

			// Add special and blocking features
			sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(0));
			reportStatusPass(methodName + " Clicked on Add to cart button for 'Special and Blocking Features Group'",
					true, false);
			sf.seleU.wait(8000);

			int sbf_Qty = Integer.parseInt(dataTable.get("SBFG_Qty"));

			// Itearate and add special and blocking features Options
			for (int l = 0; l < sbf_Qty; l++) {
				sf.seleU.clickElementByJSE(sf.gbjIBLC.specialBlockFeatures_AllCheckBoxes.get(l));
				reportStatusPass(
						methodName + "Selected special and blocking features :"
								+ sf.seleU.getTextFromWebElement(sf.gbjIBLC.specialBlockFeatures_AllCheckBoxes.get(l)),
						true, false);
				sf.seleU.wait(2000);
			}

			// Add Administrative features Group
			sf.seleU.clickElementByJSE(sf.gbj.addToCartAllButtons.get(0));
			reportStatusPass(methodName + " Clicked on Add to cart button for 'Administrative features Group'", true,
					false);
			sf.seleU.wait(8000);

			int afg_Qty = Integer.parseInt(dataTable.get("AFG_Qty"));

			// Itearate and add Administrative features Group Options
			for (int m = 0; m < afg_Qty; m++) {
				sf.seleU.clickElementByJSE(sf.gbjIBLC.admFeaturesGrp_AllCheckBoxes.get(m));
				reportStatusPass(
						methodName + "Selected 'Administrative features Group Options'  :"
								+ sf.seleU.getTextFromWebElement(sf.gbjIBLC.admFeaturesGrp_AllCheckBoxes.get(m)),
						true, false);
				sf.seleU.wait(2000);
			}
			sf.seleU.wait(15000);

			// Update the cart post adding Admin and Block Features
			sf.seleU.clickElementByJSE(sf.gbjIBLC.updateCartButton);
			reportStatusPass(methodName + " Clicked on Update cart button on Admin and Block Features", true, false);
			sf.seleU.wait(8000);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.gbj.nextButton);
			reportStatusPass(methodName + " Clicked on Next button on Admin and Block Features", true, false);
			sf.seleU.wait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Editing Business Phone Line Features  in GBJ", e);
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
	public void validateIBLCPrice(String fieldName, WebElement ele, String expectedValue) throws Exception {

		String actualValue = sf.seleU.getTextFromWebElement(ele);
		if (expectedValue.contains(actualValue)) {
			reportStatusPass(" Validated product " + fieldName + " as  " + expectedValue, true, true);
		} else {
			reportStatusFail(" Expected value for product " + fieldName + " is " + expectedValue
					+ " Actual value for Product " + fieldName + " is " + actualValue, true);
		}
	}

}
