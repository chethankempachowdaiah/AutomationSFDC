package sfdc.pages.qm;

import java.io.IOException;

import org.openqa.selenium.Keys;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Quotes page objects
 */
public class SFDC_Quotes_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Quotes_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Quotes
	 * 
	 *                     3.Click on list view dropdown and select all quotes
	 * 
	 *                     4.Verify Quotes are displayed
	 */
	public void verifyQuotesObject() throws IOException {
		try {
			String methodName = "SFDC_Quotes@: ";

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Quotes
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.quotesMenu);
			reportStatusPass(methodName + " Selected Quotes from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select all quotes
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.quotes.allQuotesOption);
			reportStatusPass(methodName + " Selected All Quotes Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify Quotes are displayed
			if (sf.quotes.quotesAllRecords.size() > 0) {
				reportStatusPass(methodName + " Quotes Objects verified", true, true);
			} else {
				reportStatusFail(methodName + " Invalid Quotes Object", true);

			}
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Quotes Object", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Quotes
	 * 
	 *                     3.Click on list view dropdown and select all quotes
	 * 
	 *                     4.Enter Quote to be Searched
	 * 
	 */
	public void searchQuote() throws IOException {
		try {

			String methodName = "SFDC_Quote@: ";

			// 1. Select Navigation Menu
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Quotes
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.quotesMenu);
			reportStatusPass(methodName + " Selected Quotes from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.enterText(sf.quotes.globalQuoteSearch, InputData.quoteNumber);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.quotes.globalQuoteSearch, Keys.ENTER);
			sf.seleU.hardwait(2000);

			boolean isQuoteFound = false;
			// select the quote from the list
			for (int i = 0; i < sf.quotes.quoteGlobalSearchResult.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.quotes.quoteGlobalSearchResult.get(i))
						.equals(InputData.quoteNumber)) {

					sf.seleU.clickElementByJSE(sf.quotes.quoteGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Quote " + InputData.quoteNumber, true, false);
					isQuoteFound = true;
					break;
				}
			}

			if (!isQuoteFound) {
				reportStatusFail(methodName + " No such Quote found as:  " + InputData.quoteNumber, true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting an quote is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/** Author - Chethan K
	 *  Capture quote name
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void CaptureQuoteName () throws IOException, InterruptedException {
		try {
			sf.seleU.clickElementByJSE(sf.qd.quoteDetailsTab.get(0));
			sf.dataInput.quoteName = sf.seleU.getTextFromWebElement(sf.qd.quoteNameText);
			reportStatusPass(" Quotename : " + sf.dataInput.quoteName, true, false);
			sf.seleU.clickElementByJSE(sf.qd.quoteRelatedTab.get(0));
		} catch (Exception e) {
			reportStatusPass(" Exception" + e, true, false);
		}
	}

	/**
	 * Select Quote from global search
	 * 
	 * @throws IOException
	 */
	public void searchQuoteGlobalSearch(String quoteName) throws IOException {

		try {

			String methodName = "SFDC_Quote@: ";

			// 1. Select Navigation Menu
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Quotes
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.quotesMenu);
			reportStatusPass(methodName + " Selected Quotes from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Enter Account to be Searched in global search
			if(!sf.seleU.isElementDisplayed(sf.quotes.globalQuoteSearch)) {
				sf.seleU.clickOnElement(sf.home.searchBoxButton);
				sf.seleU.hardwait(2000);
			}
			// 2- Enter Account to be Searched in global search
			sf.seleU.enterText(sf.quotes.globalQuoteSearch, quoteName);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.quotes.globalQuoteSearch, Keys.ENTER);
			sf.seleU.hardwait(2000);

			boolean isQuoteFound = false;
			// select the quote from the list
			for (int i = 0; i < sf.quotes.quoteGlobalSearchResult.size(); i++) {

				if (sf.seleU.getElementAttribute(sf.quotes.quoteGlobalSearchResult.get(i),"title").equalsIgnoreCase(quoteName)) {

					sf.seleU.clickElementByJSE(sf.quotes.quoteGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Quote " + quoteName, true, false);
					isQuoteFound = true;
					break;
				}
			}

			if (!isQuoteFound) {
				reportStatusFail(methodName + " No such Quote found as:  " + quoteName, true);
			}

			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting an quote is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**chethan.k
	 * Select Quote from global search
	 * 
	 * @throws IOException
	 */
	public void searchQuoteGlobalSearchInDevStage(String quoteName) throws IOException {

		try {
			
			String methodName = "SFDC_Quote@: ";

			// 1. Select Navigation Menu
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Quotes
			// 2- Enter Account to be Searched in global search
						sf.seleU.clickElementByJSE(sf.acc.search);
						sf.seleU.wait(4000);

						if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
							sf.seleU.hardwait(2000);
							sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, quoteName);
							sf.seleU.hardwait(2000);
							sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
						}

						else { // after click if sercah more .. is displayed then perform else part
							sf.seleU.hardwait(2000);
							sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, quoteName);
							sf.seleU.hardwait(2000);
							sf.seleU.enterText(sf.acc.globalAccountAndMoreSearch, Keys.ENTER);
						}
						sf.seleU.hardwait(5000);
						sf.seleU.scrollByCoOrdinates(2);


			boolean isQuoteFound = false;
			// select the quote from the list
			for (int i = 0; i < sf.quotes.quoteGlobalSearchResult.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.quotes.quoteGlobalSearchResult.get(i)).equals(quoteName)) {

					sf.seleU.clickElementByJSE(sf.quotes.quoteGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Quote " + quoteName, true, false);
					isQuoteFound = true;
					break;
				}
			}

			if (!isQuoteFound) {
				reportStatusFail(methodName + " No such Quote found as:  " + quoteName, true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting an quote is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Global Search for Fraud & Credit Check profiles
	 * 
	 * @throws IOException
	 */
	public void searchGlobalSearchWithFraudNCreditCheckPro(String quoteName) throws IOException {

		try {

			String methodName = "SFDC_Quote@: ";

			// 1. Select Navigation Menu
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Quotes
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.quotesMenu);
			reportStatusPass(methodName + " Selected Quotes from menu", true, false);
			sf.seleU.hardwait(2000);

			// 2- Enter Quote to be Searched in global search
			if(!sf.seleU.isElementDisplayed(sf.quotes.globalQuoteSearch)) {
				sf.seleU.hardwait(4000);
				sf.seleU.clickElementByJSE(sf.home.searchBoxButton);
				sf.seleU.hardwait(4000);
			}
			// 2- Enter Quote to be Searched in global search
			sf.seleU.enterText(sf.quotes.globalQuoteSearch, quoteName);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.quotes.globalQuoteSearch, Keys.ENTER);
			sf.seleU.hardwait(2000);

			boolean isQuoteFound = false;
			// select the quote from the list
			for (int i = 0; i < sf.quotes.quoteGlobalSearchResult.size(); i++) {

				if (sf.seleU.getElementAttribute(sf.quotes.quoteGlobalSearchResult.get(i),"title").equalsIgnoreCase(quoteName)) {

					sf.seleU.clickElementByJSE(sf.quotes.quoteGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Quote " + quoteName, true, false);
					isQuoteFound = true;
					break;
				}
			}

			if (!isQuoteFound) {
				reportStatusFail(methodName + " No such Quote found as:  " + quoteName, true);
			}

			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting an quote is Unsuccessful", e);
			e.printStackTrace();
		}
	}

}
