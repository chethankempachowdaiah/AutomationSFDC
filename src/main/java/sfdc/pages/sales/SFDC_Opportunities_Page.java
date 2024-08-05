package sfdc.pages.sales;

import java.io.IOException;

import org.openqa.selenium.Keys;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 27/09/2019
 *
 *         Opportunity Home page
 */
public class SFDC_Opportunities_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Opportunities_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Select Navigation Menu
	 * 
	 *                     2- Select Opportunity
	 * 
	 *                     3- Click on list view dropdown and select all
	 *                     opportunities
	 * 
	 *                     4- Verify opportunities are displayed
	 * 
	 */
	public void verifyOpportunitiesObject() throws IOException {
		try {
			String methodName = "SFDC_Opportunities@: ";

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Opportunity
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.opportunitiesMenu);
			reportStatusPass(methodName + " Selected Opportunities from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select all opportunities
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.opp.myOpportunitiesOption);
			reportStatusPass(methodName + " Selected All Opportunities Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify opportunities are displayed
			if (sf.opp.opportunitiesAllRecords.size() > 0) {
				reportStatusPass(methodName + " Opportunities Objects verified", true, true);
			} else {
				reportStatusFail(methodName + " Invalid Opportunities Object", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Opportunities Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Select Navigation Menu
	 * 
	 *                     2- Select Opportunity
	 * 
	 *                     3- Click on list view dropdown and select all
	 *                     opportunities
	 * 
	 *                     4- Verify opportunities are displayed
	 * 
	 */
	public void selectCBFOpportunityOption() throws IOException {
		try {
			String methodName = "SFDC_Opportunities@: ";
			sf.seleU.hardwait(2000);
			// Click on list view dropdown and select all opportunities
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.opp.cbfOpportunitiesOption.get(0));
			reportStatusPass(methodName + " Selected CBF Opportunities Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify opportunities are displayed
			if (sf.opp.opportunitiesAllRecords.size() > 0) {
				reportStatusPass(methodName + " Opportunities Objects verified", true, true);
			} else {
				reportStatusFail(methodName + " Invalid Opportunities Object", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for CBF Opportunities Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select Opportunity
	 */
	public void selectOpportunityFromCBFRecordsList() throws IOException, InterruptedException {
		try {

			String methodName = "SFDC_Opportunities@: ";
			int index = 0;
			boolean selected = false;
			sf.seleU.hardwait(5000);

			for (int i = 0; i < sf.opp.opportunitiesAllRecords.size(); i++) {
				sf.seleU.hardwait(2000);
				if (!sf.seleU.getTextFromWebElement(sf.opp.opportunitiesAllRecords.get(i)).equals("-New")) {
					sf.seleU.clickElementByJSE(sf.opp.opportunitiesAllRecords.get(i));
					index = i;
					selected = true;
					break;
				}
			}
			sf.seleU.hardwait(3000);
			if (selected) {
				reportStatusPass(
						methodName + "Selected self service customer record type Opportunity from CBF list "
								+ sf.seleU.getTextFromWebElement(sf.opp.opportunitiesAllRecords.get(index)),
						true, false);
			} else {
				reportStatusFail(methodName + " No Pick up in Queue", true);
			}
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for CBF Opportunities Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select Opportunity
	 */
	public void selectOpportunity(int row) throws IOException, InterruptedException {

		String methodName = "SFDC_Opportunities@: ";

		sf.seleU.clickElementByJSE(sf.opp.opportunitiesAllRecords.get(row));
		reportStatusPass(methodName + "Selected Opportunity", true, false);

		sf.seleU.hardwait(5000);
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Select Opportunity
	 */
	public void selectOpportunity(String opportunity) throws IOException, InterruptedException {

		String methodName = "SFDC_Opportunities@: ";

		sf.seleU.enterText(sf.home.searchInput, opportunity);
		sf.seleU.hardwait(2000);
		sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
		sf.seleU.hardwait(5000);

		sf.seleU.clickElementByJSE(sf.opp.opportunitiesAllRecords.get(0));
		reportStatusPass(methodName + "Selected Opportunity", true, false);

		sf.seleU.hardwait(5000);
	}

	/**
	 * @param opportunity
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 * 
	 * 
	 *                              Search Opportunity from Global Search
	 * 
	 *                              Iterate and click on Opportunity
	 */
	public void searchOpportunityInGlobalSearch(String opportunity) throws IOException {

		String methodName = "SFDC_Opportunities@: ";

		try {

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Opportunity
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.opportunitiesMenu);
			reportStatusPass(methodName + " Selected Opportunities from menu", true, false);
			sf.seleU.hardwait(2000);

			// Enter Opportunity in Global Search
			sf.seleU.enterText(sf.acc.globalOpportunitySearch, opportunity);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.acc.globalOpportunitySearch, Keys.ENTER);
			sf.seleU.hardwait(5000);

			boolean isOppFound = false;

			// Iterate and click on Opportunity
			for (int i = 0; i < sf.acc.opportunitiesLinkGlobalSearchResult.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.acc.opportunitiesLinkGlobalSearchResult.get(i))
						.contains(opportunity)) {
					sf.seleU.clickElementByJSE(sf.acc.opportunitiesLinkGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Opportunity " + opportunity, true, true);
					isOppFound = true;
					break;
				}
			}

			// Verify Opportunity is found
			if (!isOppFound) {
				reportStatusFail(methodName + " No such Opportunity found as:  " + opportunity, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Opportunities Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Show Navigation Menu
	 * 
	 *                     2. Select opportunity list view
	 * 
	 *                     3. Select All opportunity
	 * 
	 *                     4. View first available opportunity
	 */
	public void viewAnyAvailableOpportunity() throws IOException {
		try {
			String methodName = "SFDC_Opportunities@: ";

			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Opportunity
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.opportunitiesMenu);
			reportStatusPass(methodName + " Selected Opportunities from menu", true, false);
			sf.seleU.hardwait(2000);

			// Click on list view dropdown and select all opportunities
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.opp.allOpportunitiesOption);
			reportStatusPass(methodName + " Selected All Opportunities Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify opportunities are displayed
			if (sf.opp.opportunitiesAllRecords.size() > 0) {
				reportStatusPass(methodName + " Opportunities Objects verified", true, true);
				sf.seleU.clickElementByJSE(sf.opp.opportunitiesAllRecords.get(0));
				reportStatusPass(methodName + "Selected first available Opportunity", true, false);
			} else {
				reportStatusFail(methodName + " Invalid Opportunities Object", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Opportunities Object", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify No new opportunity is created with same name for Closed Lost Opp
	 *                     As Closed Lost opp can be created only once per account
	 */
	public void verifyNumberOfOpportunityWithSameName(String oppName, int expectedOccurences) throws IOException {
		try {
			String methodName = "SFDC_Opportunities@: ";
			sf.seleU.wait(10000);
			int actualOccurrence = 0; 
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Opportunity
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.opportunitiesMenu);
			reportStatusPass(methodName + " Selected Opportunities from menu", true, false);
			sf.seleU.hardwait(2000);
			
			if(!sf.seleU.isElementDisplayed(sf.acc.globalOpportunitySearch)) {
				sf.seleU.clickOnElement(sf.home.searchBoxButton);
				sf.seleU.hardwait(5000);
			}
			// Enter Opportunity in Global Search
			sf.seleU.enterText(sf.acc.globalOpportunitySearch, oppName);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.acc.globalOpportunitySearch, Keys.ENTER);
			sf.seleU.hardwait(5000);

			boolean isOppFound = false;

			// Iterate and click on Opportunity
			for (int i = 0; i < sf.acc.opportunitiesLinkGlobalSearchResult.size(); i++) {

				if (sf.seleU.getElementAttribute(sf.acc.opportunitiesLinkGlobalSearchResult.get(i), "title")
						.equalsIgnoreCase(oppName)) {
					actualOccurrence = actualOccurrence + 1;
					reportStatusPass(methodName + " Found opportunity with name : " + oppName, true, true);
				}
			}

			// Verify opportunity with same name is not created again
			if (actualOccurrence <= expectedOccurences) {
				reportStatusPass(methodName + " No Duplicate Opportunity is created as expected", false, true);
			} else {
				reportStatusFail(methodName + " Duplicate Opportunity has been created", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Could notn verify duplicate opportunity creation", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Opportunity
	 */
	public void selectOpportunityTab() throws IOException {

		String methodName = "SFDC_Opportunities@: ";
		try {
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.opportunitiesMenu);
			reportStatusPass(methodName + " Selected Opportunity from menu", true, false);
			sf.seleU.hardwait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not open Opportunity tab", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Voice of R4B links are present on Opportunity Page
	 * 
	 *                     Verify if link is open in new tab
	 */
	public void verifyVoiceOfLinksPresentAtOpp() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Links from Accounts page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			//Verify Verify Voice of R4B links are present on Account Page
			String str= "Voice of R4B";
			if(str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.voiceOfLinks)))
			reportStatusPass(methodName +" Verifed Voice of R4B links are present on Opportunity Page" , true, true);
			
			else
				reportStatusFail(" Verification Failed- Verifed Voice of R4B links are not present on Opportunity Page ", true);
			
			// Select account
			sf.seleU.clickElementByJSE(sf.acc.voiceOfLinks);
			reportStatusPass(methodName + " Clicked on Voice of R4B Links from Opportunity", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.switchWindow(5);
			String str2 = sf.seleU.getCurrentUrl();
			reportStatusPass(str2 + " Verified Voice of R4B links are opened in new tab" , true, true);
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(1);
			sf.seleU.hardwait(2000);
			
		  } catch (Throwable e) {
				reportStatusFail(" Selecting Voice of R4B Link is Unsuccessful", e);
				e.printStackTrace();
			}
		
		}
	
	
	
}
 