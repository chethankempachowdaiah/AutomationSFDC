package sfdc.pages.sales;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date 20/Jan/2020
 * 
 *         Opportunity Related Tab Page
 * 
 * 
 */
public class SFDC_OpportunityRelated_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_OpportunityRelated_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * 
	 * 1- Click on Opportunity Related Tab
	 * 
	 * 2- Verify Quote Number Link in Opportunity Related Tab
	 * 
	 * 3- Click on Quote Number Link
	 * 
	 * @throws IOException
	 */

	public void verifyQuoteInOpportunityRelated() throws IOException {
		try {

			String methodName = "SFDC_Opportunity Related Tab@: ";

			// 1- Click on Opportunity Related Tab
			sf.seleU.clickElementByJSE(sf.or.opportunityRelatedTab);
			reportStatusPass(methodName + " Clicked on Opportunity Related Tab", true, false);

			// 2- Verify Quote Number Link in Opportunity Related Tab
			sf.seleU.wait(5000);
			sf.seleU.ScrolltoElement(sf.or.quotesQuoteNumberAllRows.get(0));
			if (sf.or.quotesQuoteNumberAllRows.size() > 0
					&& sf.seleU.getTextFromWebElement(sf.or.quotesQuoteNumberAllRows.get(0)).length() > 0) {
				reportStatusPass(methodName + " Quote Number Verified as :"
						+ sf.seleU.getTextFromWebElement(sf.or.quotesQuoteNumberAllRows.get(0)), true, true);
			} else {
				reportStatusFail(methodName + " Invalid Quote Number", true);
			}

			// 3-Click on Quote Number Link
			sf.seleU.clickElementByJSE(sf.or.quotesQuoteNumberAllRows.get(0));
			reportStatusPass(methodName + " Clicked on Quote Number Link", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Quote in in Opportunity Related Tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 1- Click on Opportunity Related Tab
	 * 
	 * 2- Verify Quote Number Link in Opportunity Related Tab
	 * 
	 * 3- Click on Quote Number Link
	 * 
	 * @throws IOException
	 */

	public void verifyContactCreatedInOpportunityRelated() throws IOException {
		try {

			String methodName = "SFDC_Opportunity Related Tab@: ";

			// 1- Click on Opportunity Related Tab
			sf.seleU.clickElementByJSE(sf.or.opportunityRelatedTab);
			reportStatusPass(methodName + " Clicked on Opportunity Related Tab", true, false);

			// 2- Verify Quote Number Link in Opportunity Related Tab
			sf.seleU.wait(5000);
			sf.seleU.ScrolltoElement(sf.or.contactRoleNumberAllRows.get(0));
			if (sf.or.contactRoleNumberAllRows.size() > 0
					&& sf.seleU.getTextFromWebElement(sf.or.contactRoleNumberAllRows.get(0)).length() > 0) {
				reportStatusPass(methodName + " Created contact name is Verified as :"
						+ sf.seleU.getTextFromWebElement(sf.or.contactRoleNumberAllRows.get(0)), true, true);
			} else {
				reportStatusFail(methodName + " Invalid Contact Role In Opportunity Details Page", true);
			}

			// 3-Click on Quote Number Link
			sf.seleU.clickElementByJSE(sf.or.quotesQuoteNumberAllRows.get(0));
			reportStatusPass(methodName + " Clicked on Quote Number to move to quote related page", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid contact role in Opportunity Related Tab", e);
			e.printStackTrace();
		}
	}
}
