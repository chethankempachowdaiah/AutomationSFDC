package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_OM;
import com.sfdc.data.InputData_QM;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:16/08/2021
 *
 *         SFDC Persona Based Buy Flow Page - Order Confirmation
 */
public class SFDC_PBF_OrderConfirmation_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_PBF_OrderConfirmation_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_OrderConfirmation@:";
	}
		
	
	/**
	 * 			Validate the following on the Order Confirmation Screen:
	 * 
	 *				1.UI on the page is as per the PBF L1 Mock Up.
	 *
	 *				2.Order id is presented which when clicked should
	 *				 land to the Order Details page in communities.
	 * 
	 * @throws IOException
	 */
	public void validateOrderConfirmationScreenForRDI() throws IOException {

		try {
				sf.seleU.wait(20000);
			
					verifyFieldDisplayed("Order Confirmation Page Header", sf.orderConfPBF.orderAlmostCompleteMessage);
					verifyFieldDisplayed("Order Number", sf.orderConfPBF.orderNumberLink);
					sf.dataInput.orderNumber = sf.seleU.getTextFromWebElement(sf.orderConfPBF.orderNumberLink).replace("#", "");
				
				reportStatusPass(methodName + " Extracted Order Number for Future reference : " + sf.dataInput.orderNumber, false, true);
					
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order COnfirmation Screen", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			Validate the following on the Order Confirmation Screen:
	 * 
	 *				1.UI on the page is as per the PBF L1 Mock Up.
	 *
	 *				2.Order id is presented which when clicked should
	 *				 land to the Order Details page in communities.
	 * 
	 * @throws IOException
	 */
	public void validateOrderConfirmationScreen() throws IOException {

		try {
			
				if (InputData_Communities.commPBFAddSiteContact.equals("Yes"))	{
					verifyFieldDisplayed("Order Confirmation Page Header", sf.orderConfPBF.orderConfirmationPageHeader);
					verifyFieldDisplayed("Order Number", sf.orderConfPBF.qouteName);
					verifyFieldDisplayed("Done CTA", sf.orderConfPBF.doneButton);
					verifyFieldDisplayed("View Order Link", sf.orderConfPBF.viewOrderLink);
					sf.dataInput.orderNumber = sf.seleU.getTextFromWebElement(sf.orderConfPBF.qouteName).replace("#", "");
				
				} else {
					verifyFieldDisplayed("Order Confirmation Page Header", sf.orderConfPBF.orderAlmostCompleteMessage);
					verifyFieldDisplayed("Order Number", sf.orderConfPBF.orderNumberLink);
					if (sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE"))
						verifyFieldDisplayed("Done CTA", sf.orderConfPBF.doneButton);verifyFieldDisplayed("Site Contact Required Message", sf.orderConfPBF.siteContactSelectMessage);
					sf.dataInput.orderNumber = sf.seleU.getTextFromWebElement(sf.orderConfPBF.orderNumberLink).replace("#", "");
					sf.dataInput.orderNumber = sf.dataInput.orderNumber.split("\\(")[0];
				}
					reportStatusPass(methodName + " Extracted Order Number for Future reference : " + sf.dataInput.orderNumber, false, true);
					
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order COnfirmation Screen", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 			Validate the following on the Order Confirmation Screen:
	 * 
	 *				1.UI on the page is as per the PBF L1 Mock Up.
	 *
	 *				2.Quote id is presented which canotbe clicked 
	 * 
	 * @throws IOException
	 */
	public void validateOrderConfirmationScreenForCreditOrFraudCheckAcc() throws IOException {

		try {
				if (InputData_Communities.commPBFCreditCheckRequired.equals("Yes")
						||InputData_Communities.commPBFFraudCheckRequired.equals("Yes"))	
					verifyFieldDisplayed("Quote Confirmation Page Header", sf.orderConfPBF.quoteConfirmationPageHeader);
					
				else
					verifyFieldDisplayed("Quote Confirmation Page Header", sf.orderConfPBF.orderAlmostCompleteMessage);
				
				verifyFieldDisplayed("Quote Id", sf.orderConfPBF.orderNumberLink);
				sf.dataInput.quoteName = sf.dataInput.businessAccountName + "-" + sf.seleU
						.getTextFromWebElement(sf.orderConfPBF.orderNumberLink).replace("#", "");
				
				if(sf.dataInput.quoteName.contains("pend"))
					sf.dataInput.quoteName = sf.dataInput.quoteName.replace("(pending)", "");
				
				reportStatusPass(methodName + " Extracted Quote Name for Future reference : " + sf.dataInput.quoteName, false, true);
				sf.seleU.clickOnElement(sf.orderConfPBF.orderNumberLink);
				sf.seleU.wait(8000);
				verifyFieldDisplayed("Quote Details Page", sf.qd.quoteDetailsTab.get(0));
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Confirmation Screen", e);
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 			Click on Order Number
	 *  
	 * @throws IOException
	 */
	public void clickOnOrderNumber() throws IOException {

		try {
			
			//Customer
			if (InputData_Communities.commPBFUser==null) {
				sf.seleU.clickElementByJSE(sf.orderConfPBF.orderNumberLink);
			}
			//Agent
			else {
				sf.seleU.clickOnElement(sf.orderConfPBF.viewOrderLink);
			}
				sf.seleU.wait(10000);
				reportStatusPass(methodName + " Clicked on Order Link" , true, true);
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in opening order", e);
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
