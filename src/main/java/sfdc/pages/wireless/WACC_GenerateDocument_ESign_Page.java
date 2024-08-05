package sfdc.pages.wireless;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sakshi Lnu, Date : 28/07/2021
 * 
 *       Validate Quote send for E-signature
 *
 */
public class WACC_GenerateDocument_ESign_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public WACC_GenerateDocument_ESign_Page() {
		sf = new SFDC_AllPageObjects();

	}

	/**
	 * @throws Exception
	 * 
	 *          Verify Send quote by Docu sign when user accepts the quote by e-signature
	 * 
	 */
	public void generateDocument(String type) throws Exception {
		try {
			methodName = "E-Signature Quote Document Details Validation@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.genDocObj.generateDocumentHeader));
			reportStatusPass(methodName + " lands on generate document page", true, true);
			if (type.equalsIgnoreCase("word")) {
				sf.seleU.waitElementToBeClickable(sf.genDocObj.downloadWord);
				sf.seleU.clickOnElementNumberoftimes(sf.genDocObj.downloadWord, 1);
				sf.seleU.waitForLoading();
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + "word doc is downloaded", true, true);
			} else if (type.equalsIgnoreCase("pdf")) {
				sf.seleU.waitElementToBeClickable(sf.genDocObj.downloadPdf);
				sf.seleU.clickOnElementNumberoftimes(sf.genDocObj.downloadPdf, 1);
				reportStatusPass(methodName + "pdf doc is downloaded", true, true);
				sf.seleU.hardwait(3000);
			}  else {
				reportStatusFail(methodName, true);
			}
			sf.seleU.clickElementByJSE(sf.genDocObj.nextButton);
			sf.seleU.waitForLoading();
			// Dont use this in future
			//	sf.seleU.clickElementByJSE(sf.genDocObj.continueButton);
			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Error on validating Generate Doc Page", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws Exception
	 * 
	 *          Verify Quote summary generated in PDF
	 * 
	 */
	public void generatePdfToAcceptQuoteVerbally(String quoteNumber) throws Exception {
		try {
			methodName = "verbally accepted Quote Document Details Validation@: ";

			sf.seleU.hardwait(3000);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.genDocObj.orderSummaryHeader));
			reportStatusPass(methodName + " lands on Order Summary page", true, true);
			sf.seleU.hardwait(3000);
			sf.seleU.isElementDisplayed(sf.genDocObj.document);
			sf.seleU.hardwait(2000);
				sf.seleU.clickOnElementNumberoftimes(sf.genDocObj.downloadOrderSummaryPdf, 1);
				sf.seleU.hardwait(3000);
				reportStatusPass(methodName + "Order summary is downloaded", true, true);
			sf.seleU.clickElementByJSE(sf.genDocObj.confirmOrder);
			sf.seleU.waitForLoading();
			quoteNumber = sf.seleU.getTextFromWebElement(sf.genDocObj.quoteNumberText);
			InputData.quoteNumber = quoteNumber.replace("#", "");
			System.out.println("Quote Number got is " +InputData.quoteNumber);
				sf.seleU.clickElementByJSE(sf.genDocObj.doneButton);
				sf.seleU.waitForLoading();
		} catch (Throwable e) {
			reportStatusFail(" Error on validating Generate Doc Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Accept Quote Options a. e-signaure b. Verbal acceptance of quote
	 * 
	 * 
	 */
	public void generateDocuement(String quoteNumber) throws Exception {
		try {
			methodName = "WACC Review Order Details Validation@: ";
			sf.seleU.waitForLoading();
			// click options to accept the quote
			if (InputData_WA.WACC_eSignature.equals("Yes")) {
				sf.seleU.waitElementToBeClickable(sf.genDocObj.downloadPdf);
				reportStatusPass(methodName + sf.seleU.isElementDisplayed(sf.genDocObj.generateDocumentHeader)  +" User lands on generate document page", true, true);
				sf.seleU.switchToFrame(driver.findElement(By.xpath("//iframe[@class='ng-scope']")));
				sf.seleU.clickOnElementNumberoftimes(sf.genDocObj.downloadPdf, 1);
				//sf.seleU.clickOnElementNumberoftimes(driver.findElement(By.xpath("//body[@class='pdf']//button[@id='open-button']")), 1);
				sf.seleU.waitForLoading();
				sf.seleU.scrollToBottom();
				driver.switchTo().defaultContent();
				reportStatusPass(methodName + "PDF doc is downloaded", true, true);
				sf.seleU.clickElementByJSE(sf.genDocObj.nextButton);
				sf.seleU.waitForLoading();
			} else {
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + sf.seleU.isElementDisplayed(sf.genDocObj.orderSummaryHeader) + " user lands on Order Summary page", true, true);
				sf.seleU.waitForLoading();
				sf.seleU.isElementDisplayed(sf.genDocObj.document);
				sf.seleU.waitForLoading();
					sf.seleU.clickOnElementNumberoftimes(sf.genDocObj.downloadOrderSummaryPdf, 1);
					sf.seleU.waitForLoading();
					sf.seleU.scrollToBottom();
					reportStatusPass(methodName + "Order summary is downloaded", true, true);
					sf.seleU.waitForLoading();
				sf.seleU.clickElementByJSE(sf.genDocObj.confirmOrder);
				sf.seleU.waitForLoading();
				quoteNumber = sf.seleU.getTextFromWebElement(sf.genDocObj.quoteNumberText);
				InputData.quoteNumber = quoteNumber.replace("#", "");
				reportStatusPass(methodName + "Quote number is generated : " + InputData.quoteNumber, true, true);
					sf.seleU.clickElementByJSE(sf.genDocObj.doneButton);
					sf.seleU.waitForLoading();				
			}			
			
		} catch (Throwable e) {
			reportStatusFail(" Error on Quote Acceptance option", e);
			e.printStackTrace();
		}
	}

}
