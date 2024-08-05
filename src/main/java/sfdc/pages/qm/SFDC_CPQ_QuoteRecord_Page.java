package sfdc.pages.qm;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 20/jan/2020
 * 
 *         CPQ Review Quote record(Opportunity> Create Quote> Select Contact>
 *         Select service account>Review Quote record)
 *
 */
public class SFDC_CPQ_QuoteRecord_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_CPQ_QuoteRecord_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Quote Record@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Verify Quote Number in Quote Record Page
	 * 
	 * 
	 */
	public void verifyQuoteInQuoteRecord() throws IOException {

		try {

			// 1- Verify Quote Number in Quote Record Page
			// sf.seleU.switchWindow(2);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.cpqQuoteRec.quoteNumberText);
			if (sf.seleU.getTextFromWebElement(sf.cpqQuoteRec.quoteNumberText.get(0)).length() > 0) {
				reportStatusPass(methodName + " Quote Number Verified as :"
						+ sf.seleU.getTextFromWebElement(sf.cpqQuoteRec.quoteNumberText.get(0)), true, true);
			} else {
				reportStatusFail(methodName + " Invalid Quote Number", true);
			}

			sf.seleU.hardwait(10000);
		} catch (Throwable e) {
			reportStatusFail(" Invalid Quote in Quote Record Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Hconfigure button
	 */
	public void clickOnHConfigure() throws IOException {
		try {

			// Click on Hconfigure button
			sf.seleU.clickElementByJSE(sf.cpqQuoteRec.hConfigureButton);
			reportStatusPass("Clicked on Hconfigure button", true, true);
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting HConfigure Button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Opportunity Link
	 */
	public void clickOnOpportunityLink() throws IOException {
		try {

			// Click on Opportunity Link
			sf.seleU.clickElementByJSE(sf.cpqQuoteRec.opportunityNameLink);
			reportStatusPass(methodName + " Clicked on Opportunity Link", true, false);

			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking opportunity link in quote record page", e);
			e.printStackTrace();
		}
	}
}
