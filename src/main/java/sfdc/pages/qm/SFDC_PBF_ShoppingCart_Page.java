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

/**
 * @author Anukriti.Chawla, Date:11 Aug 2021
 *
 *         SFDC Persona Based Buy Flow Page
 */
public class SFDC_PBF_ShoppingCart_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_PBF_ShoppingCart_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_Shopping Cart@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Waive installation fees
	 *                     
	 *                     Verify changes in prices after waiving off fees
	 * 
	 */
	public void waiveOffInstallationFees() throws IOException {
		try {
			
			
			sf.seleU.clickElementByJSE(sf.shopCartPBF.waiveOffInstallationButton);
			reportStatusPass(methodName +" Clicked on Waive Off intsallation fees button", false, true);
			sf.seleU.wait(15000);
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
			verifyFieldValue("Unit Price for One time fees after waive off", sf.shopCartPBF.afterWaiveOffInstallationUnitPrice, "0.00");
			verifyFieldValue("Line Total for One time fees after waive off", sf.shopCartPBF.afterWaiveOffInstallationLineTotalPrice, "0.00");
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, "0.00");
			} else {
				verifyFieldValue("Unit Price for One time fees after waive off", sf.shopCartPBF.afterWaiveOffInstallationUnitPrice, "0,00");
				verifyFieldValue("Line Total for One time fees after waive off", sf.shopCartPBF.afterWaiveOffInstallationLineTotalPrice, "0,00");
				verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, "0,00");
			}
			
			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue) +"."
					 + sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesSubValue);
			if (totalOneTimeFees.equalsIgnoreCase("0.00") || totalOneTimeFees.equalsIgnoreCase("0.0"))
				reportStatusPass(methodName + " Total Ome Time Fees for Product is correct and waived OFF - 0.00", true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  0.00, "
						+ " Actual : " + totalOneTimeFees , true);
			sf.seleU.wait(15000);
			SFDC_PBF_CablePlan_Selection_Page.verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, Double.parseDouble("0.00"));
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying waive off installation is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                      Add Installation fees and verify price refreshes back to normal.
	 * 
	 */
	public void addInstallationFees() throws IOException {
		try {
			
			sf.seleU.wait(5000);
		
			sf.seleU.clickElementByJSE(sf.shopCartPBF.addInstallationFeeButton);
			reportStatusPass(methodName +" Clicked on Add intsallation fees button", false, true);
			sf.seleU.wait(15000);
			
			verifyFieldNotDisplayed("Cut - Off Unit Price", sf.shopCartPBF.afterWaiveOffInstallationUnitPrice);
			verifyFieldNotDisplayed("Cut - Off Line Total Price", sf.shopCartPBF.afterWaiveOffInstallationLineTotalPrice);
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
			verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFees);
			} else {
				verifyFieldValue("One Time fees for Product", sf.comPBFShopCart.addedProductOneTimeFeesSubtotal, InputData_Communities.commPBFOneTimeFeesFr);
			}
			
			//Validate one time fees subtotal
			String totalOneTimeFees = sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesValue) +"."
					 + sf.seleU.getTextFromWebElement(sf.comPBFShopCart.addedProductTotalOneTimeFeesSubValue);
			if (totalOneTimeFees.contains(InputData_Communities.commPBFOneTimeFees))
				reportStatusPass(methodName + " Total Ome Time Fees for Product is correct " + InputData_Communities.commPBFOneTimeFees, true, false);
			else
				reportStatusFail(methodName + " Total One Time Fees for Product is incorrect, Expected :  " + InputData_Communities.commPBFOneTimeFees
						+ ", Actual : " + totalOneTimeFees , true);
			
			SFDC_PBF_CablePlan_Selection_Page.verifyTCV("ONE TIME TCV", sf.planSelPBF.oneTimeTCV, Double.parseDouble(InputData_Communities.commPBFOneTimeFees));
			
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Business internet icon in serviceability column is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                      Edit Ethernet Plan to E1000/E100
	 * 
	 */
	public void editEthernetPlanBackToOriginal() throws IOException {
		try {
			
			sf.seleU.wait(5000);
		
			sf.seleU.clickElementByJSE(sf.shopCartPBF.editEthernetPlanButton);
			reportStatusPass(methodName +" Clicked on Edit Ethernet Network Access Plan button", false, true);
			sf.seleU.wait(15000);
			
			verifyFieldDisplayed("Ethernet Access options Page Header", sf.planSelPBF.fibreAccessPageHeader);
			
			if(InputData_Communities.commPBFEditEthernetPlan.equalsIgnoreCase("E100")) 
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE1000Option);
			else
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE100Option);
			
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.shopCartPBF.updateCartButton);
			reportStatusPass(methodName +" Clicked on Update Cart", false, true);
			sf.seleU.wait(30000);
						
			// Proceed to Checkout
			sf.seleU.clickOnElement(sf.comPBFCablePlan.proceedToShoppingCartButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Proceed to checkout Button", true, true);
		
			verifyFieldDisplayed("Shopping Cart Header", sf.comPBFShopCart.shoppingCartHeader);

			if(InputData_Communities.commPBFEditEthernetPlan.equalsIgnoreCase("E1000"))  {
				InputData_Communities.commPBFCurrentEthernetPlan = "E100";
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE100Cost;
			}
			else {
				InputData_Communities.commPBFCurrentEthernetPlan = "E1000";
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE1000Cost;
			}
			//Validate Line Total
			verifyFieldValue("Line Total Price for Ethernet Access", sf.comPBFShopCart.ethernetAccessLineTotal, InputData_Communities.commPBFCurrentEthernetPlanCost);
		
			
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Editing Ethernet Plan is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * @author Bhoomi.Kishor
	 * 
	 *                      Edit Ethernet Plan to E1000/E100 when discount is applied
	 * 
	 */
	public void editEthernetPlanBackToOriginalWithDiscountUpdate() throws IOException {
		try {
			
			sf.seleU.wait(5000);
		
			sf.seleU.clickElementByJSE(sf.shopCartPBF.editEthernetPlanButton);
			reportStatusPass(methodName +" Clicked on Edit Ethernet Network Access Plan button", false, true);
			sf.seleU.wait(15000);
			
			verifyFieldDisplayed("Ethernet Access options Page Header", sf.planSelPBF.fibreAccessPageHeader);
			
			if(InputData_Communities.commPBFEditEthernetPlan.equalsIgnoreCase("E100")) 
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE1000Option);
			else
				sf.seleU.clickElementByJSE(sf.planSelPBF.ethernetE100Option);
			
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.shopCartPBF.updateCartButton);
			reportStatusPass(methodName +" Clicked on Update Cart", false, true);
			sf.seleU.wait(30000);
						
			// Proceed to Checkout
			sf.seleU.clickOnElement(sf.comPBFCablePlan.proceedToShoppingCartButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Clicked on Proceed to checkout Button", true, true);
		
			verifyFieldDisplayed("Shopping Cart Header", sf.comPBFShopCart.shoppingCartHeader);

			if(InputData_Communities.commPBFEditEthernetPlan.equalsIgnoreCase("E1000"))  {
				InputData_Communities.commPBFCurrentEthernetPlan = "E100";
				if (Integer.parseInt(InputData_Communities.discount) > 0) {
					InputData_Communities.commPBFCurrentEthernetPlanCost = String.valueOf(Double.parseDouble(InputData_Communities.commPBFEthernetPlanE100Cost)-
							((Double.parseDouble(InputData_Communities.commPBFEthernetPlanE100Cost)* Double.parseDouble(InputData_Communities.discount))/100)); 
				}
				else {
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE100Cost;}
			}
			else {
				InputData_Communities.commPBFCurrentEthernetPlan = "E1000";
				if (Integer.parseInt(InputData_Communities.discount) > 0) {
					InputData_Communities.commPBFCurrentEthernetPlanCost = String.valueOf(Double.parseDouble(InputData_Communities.commPBFEthernetPlanE1000Cost)-
							((Double.parseDouble(InputData_Communities.commPBFEthernetPlanE1000Cost)* Double.parseDouble(InputData_Communities.discount))/100));
				}
				else {
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE1000Cost;
				}
				
			}
			//Validate Line Total
			verifyFieldValue("Line Total Price for Ethernet Access", sf.comPBFShopCart.ethernetAccessLineTotal, InputData_Communities.commPBFCurrentEthernetPlanCost);
		
			
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Editing Ethernet Plan is unscuccesfull", e);
			e.printStackTrace();
		}
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
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify field is not displayed
	 */
	public static void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {

		try {
			// Verify field is not displayed
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Verified " + fieldName + " is not displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is displayed, It should not be displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
