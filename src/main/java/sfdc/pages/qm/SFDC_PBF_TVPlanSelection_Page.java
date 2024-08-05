package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_PBF_TVPlanSelection_Page extends MyListener{

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_PBF_TVPlanSelection_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_TVPlanSelection@:";
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
				sf.seleU.ScrolltoElementPageCenter(element);
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Validate Name of Products
	 * 
	 * @throws IOException
	 */
	public void validateBusinessTVProductsNames() throws IOException {

		try {
			//sf.seleU.wait(5000);
			//In Case of Bundled - 3 Year Bus Int + TV - Tv would also be for 3 Year else it would be always monthly
			if (!InputData_Communities.commPBFBundledFirstProd.equals("TV"))
				verifyFieldDisplayed("Yearly Term Radio", sf.comPBFTVPlan.yearlyTermRadio);
			else
				verifyFieldDisplayed("Monthly Term Radio", sf.comPBFTVPlan.monthlyTermRadio);
			
			List<String> expectedBusinessProducts = InputData_Communities.commPBFTVProducts;
			List<String> actualBusinessProducts = new ArrayList<String>();

			// Loop over all business TV names to be verified
			for (int i = 0; i < sf.comPBFTVPlan.plansTV.size(); i++) {
				actualBusinessProducts.add(sf.seleU.getTextFromWebElement(sf.comPBFTVPlan.plansTV.get(i)));
			}
			reportStatusPass(methodName + " Extracted Product names to compare with expected list", false, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessProducts);
			Collections.sort(actualBusinessProducts);

			// Verify expected Business TV list is equal to actualBusiness Plans List

			if (expectedBusinessProducts.equals(actualBusinessProducts)) {
				reportStatusPass(methodName + " All expected Products are present on TV Selection Page: "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected Products are not present on TV Selection Page: :: Expected Products--> "
						+ AdditionalUtilities.getAsString(expectedBusinessProducts) + "  Actual Products--> "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Business Products Names", e);
			e.printStackTrace();
		}

	}

	public List<WebElement> getTVProductLists() {
		List<WebElement> listItems= driver.findElements(By.xpath("//*[contains(@class,'offer-container')]//*[@class='catalog-name']"));
		return listItems;
	}

	/**
	 * 
	 * Validate Name of subProducts
	 * 
	 * @throws IOException
	 */
	public void validateBusinessTVSubProductsNames() throws IOException {

		try {
			List<String> expectedBusinessProducts = InputData_Communities.commPBFTVSubProducts;
			List<String> actualBusinessProducts = new ArrayList<String>();

			//Loop over sub plan section
			for(int j=0; j < sf.comPBFTVPlan.subPlansSection.size(); j++) {
				sf.seleU.ScrolltoElementPageCenter(sf.comPBFTVPlan.subPlansSection.get(j));
				sf.seleU.clickOnElement(sf.comPBFTVPlan.subPlansSection.get(j));
				
				// Collecting Business TV sub Products
				List<WebElement> subPlansTV = sf.comPBFTVPlan.subPlansSection.get(j).findElements(By.xpath("./following-sibling::div//ul/li//span/span"));
				
				//Loop over all business sub plan names to be verified
				for (int i = 0; i < subPlansTV.size(); i++) {
					actualBusinessProducts.add(sf.seleU.getTextFromWebElement(subPlansTV.get(i)));
				}
				sf.seleU.clickOnElement(sf.comPBFTVPlan.subPlansSection.get(j));
			}
			reportStatusPass(methodName + " Extracted Sub Products names to compare with expected list", true, false);

			// sort lists for comparison
			Collections.sort(expectedBusinessProducts);
			Collections.sort(actualBusinessProducts);

			// Verify expectedBusiness plan list is equal to actualBusiness Plans List

			if (expectedBusinessProducts.equals(actualBusinessProducts)) {
				reportStatusPass(methodName + " All expected Sub Products are present on TV Selection Page: "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true, true);
			} else {
				reportStatusFail(methodName
						+ " All expected Sub Products are not present on TV Selection Page: :: Expected Sub Products--> "
						+ AdditionalUtilities.getAsString(expectedBusinessProducts) + "  Actual Sub Products--> "
						+ AdditionalUtilities.getAsString(actualBusinessProducts), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Sub Products Names", e);
			e.printStackTrace();
		}

	}


	public void selectTVProduct() throws Exception {

		try {

			// Loop over the TV Product Details to be verified
			for(int i=0; i < sf.comPBFTVPlan.viewMoreDetails.size(); i++ ) {
				sf.seleU.ScrolltoElementPageCenter(sf.comPBFTVPlan.viewMoreDetails.get(i));
				sf.seleU.clickOnElement(sf.comPBFTVPlan.viewMoreDetails.get(i));
				sf.seleU.wait(4000);
				verifyFieldDisplayed(" Product Details Header ", sf.comPBFTVPlan.productDetailsHeader);
				sf.seleU.clickOnElement(sf.comPBFTVPlan.viewMoreDetails.get(i));
			}

			// Loop over the sub plan section
			outerLoop:
			for(int j=0; j < sf.comPBFTVPlan.subPlansSection.size(); j++) {
				sf.seleU.ScrolltoElementPageCenter(sf.comPBFTVPlan.subPlansSection.get(j));
				sf.seleU.clickOnElement(sf.comPBFTVPlan.subPlansSection.get(j));
				// Collecting Business TV sub Products
				List<WebElement> subPlansTV = (sf.comPBFTVPlan.subPlansSection.get(j)).findElements(By.xpath("./following-sibling::div//ul/li//span/span"));
				// Loop over the TV Sub Product Items and Select the Sub Product
				for (int i = 0; i < subPlansTV.size(); i++) {
					if(sf.seleU.getTextFromWebElement(subPlansTV.get(i)).trim().equalsIgnoreCase(InputData_Communities.commPBFAddTVProductName)) {
						sf.seleU.clickOnElement(subPlansTV.get(i));						
						reportStatusPass(methodName + " Selected on TV Sub Product: " + InputData_Communities.commPBFAddTVProductName, true, false);
						sf.seleU.clickElementByJSE(sf.comPBFTVPlan.addToCartList.get(j));
						reportStatusPass(methodName + " Clicked on Add to Cart Button", true, true);
						break outerLoop;
					}
				}
			}
			sf.seleU.wait(4000);
			//Validation of Remove From Cart Button
			verifyFieldDisplayed(" Remove From Cart Button", sf.comPBFTVPlan.removeCart);
			// Click on Next button
			sf.seleU.ScrolltoElementPageCenter(sf.comPBFTVPlan.nextbtn);
			sf.seleU.clickElementByJSE(sf.comPBFTVPlan.nextbtn);
			reportStatusPass(methodName + " Clicked on Next button", false, false);
	
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Selecting the TV Product", e);
			e.printStackTrace();
		}
	}


}
