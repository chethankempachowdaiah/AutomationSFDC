package sfdc.pages.communities;

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
 * @author Anukriti.Chawla, Date:14/07/2021
 *
 *         Communities Persona Based Buy Flow Page - Order Confirmation
 */
public class Communities_PBF_OrderConfirmation_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_PBF_OrderConfirmation_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_PBF_OrderConfirmation@:";
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
				
				verifyFieldDisplayed("Order Confirmation Page Header", sf.comPBFOrderConfirm.orderConfirmationPageHeader);
						
				verifyFieldDisplayed("Order Number", sf.comPBFOrderConfirm.orderNumberLink);
			
				sf.dataInput.orderNumber = sf.seleU
					.getTextFromWebElement(sf.comPBFOrderConfirm.orderNumberLink).replace("#", "");
				
				reportStatusPass(methodName + " Extracted Order Numver for Future reference : " + sf.dataInput.orderNumber, false, true);
					
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
				verifyFieldDisplayed("Quote Confirmation Page Header", sf.comPBFOrderConfirm.quoteConfirmationPageHeader);
				verifyFieldDisplayed("Quote Id", sf.comPBFOrderConfirm.qouteName);
				verifyFieldNotDisplayed("Order Number Link", sf.comPBFOrderConfirm.orderNumberLink);
				sf.dataInput.quoteName = sf.dataInput.businessAccountName + "-" + sf.seleU
						.getTextFromWebElement(sf.comPBFOrderConfirm.qouteName).replace("#", "");
				
				reportStatusPass(methodName + " Extracted Quote Name for Future reference : " + sf.dataInput.quoteName, false, true);
				
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
			
			
			if (sf.seleU.isElementPresent(sf.comPBFOrderConfirm.orderNumberLink)) {
				sf.seleU.wait(4000);
				sf.seleU.clickOnElement(sf.comPBFOrderConfirm.orderNumberLink);
			}
			else {
				sf.seleU.clickOnElement(sf.comPBFOrderConfirm.viewOrderLink);
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
	
	
	/** Chethan k
	 * 	Click on Order Number and validate NFDB
	 *  
	 * @throws IOException
	 */
	public void clickOnOrderNumberAndCheckNfdb() throws IOException {

		try {
			
			
			if (sf.seleU.isElementPresent(sf.comPBFOrderConfirm.viewOrderLink)) {
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.comPBFOrderConfirm.viewOrderLink);
			}
			else {
				sf.seleU.clickOnElement(sf.comPBFOrderConfirm.viewOrderLink);
			}
				sf.seleU.wait(10000);
				reportStatusPass(methodName + " Clicked on Order Link" , true, true);
				
				//Click on orderDetails 
				sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTabs);
				reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);
				sf.seleU.wait(4000);			
				
				//click on quoteLink
				if (sf.seleU.isElementPresent(sf.orDetails.QuoteLink)) {
					reportStatusPass(
							methodName + "Quote Link/ID is" + sf.seleU.getTextFromWebElement(sf.orDetails.QuoteLink), true,
							false);
					sf.seleU.wait(4000);
					sf.seleU.clickElementByJSE(sf.orDetails.QuoteLink);
					reportStatusPass(methodName + "Clicked on Quote Link successfully", true, false);
					sf.seleU.wait(4000);
									
				} else {
					reportStatusFail("Quote ID/Link is not present", true);
				}
				//click on quote details
//				sf.seleU.switchToDefaultContent();
//				sf.seleU.hardwait(4000);
//				sf.seleU.switchToElementFrame(sf.qd.quoteDetailsTab);
//				sf.seleU.hardwait(4000);
//				//sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
//				if (sf.qd.quoteDetailsTab.size() > 1)
//					sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(1));
//				else
//				sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
//				sf.seleU.hardwait(3000);
//				reportStatusPass(methodName + " Clicked on Quote Details Tab", true, true);

				
				
//				sf.seleU.wait(4000);
//				//Validation check
//				sf.seleU.getTextFromWebElement(sf.qd.quoteValidationCheckStatusText);
//				reportStatusPass(methodName + " Validation Check Status is " + sf.seleU.getTextFromWebElement(sf.qd.quoteValidationCheckStatusText) , true, true);
//				
//				sf.seleU.wait(4000);
//				sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText);
//				reportStatusPass(methodName + " Credit Check Status is " + sf.seleU.getTextFromWebElement(sf.qd.quoteCreditCheckStatusText) , true, true);
//				sf.seleU.wait(4000);
//				sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText);
//				reportStatusPass(methodName + " Fraud Check Status is " + sf.seleU.getTextFromWebElement(sf.qd.quoteFraudReviewStatusText) , true, true);
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in opening order and quote page", e);
			e.printStackTrace();
		}

	}

}
