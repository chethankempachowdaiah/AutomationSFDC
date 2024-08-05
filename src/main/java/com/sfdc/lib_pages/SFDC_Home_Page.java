package com.sfdc.lib_pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date: 26/Sept/2019
 * 
 *         Home Page for SFDC Application
 *
 */
public class SFDC_Home_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Home_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * It will verify whether correct user is logged into the application
	 * 
	 * @throws IOException
	 */
	public void verifyLogin() throws IOException {

		try {

			String methodName = "SFDC_Home@: ";

			// Verify correct user is logged in to application
			Thread.sleep(5000);
			sf.seleU.mouseOverAndClickOnElement(sf.home.userImg);

			Thread.sleep(5000);
			// sf.seleU.FluentWaitElement(sf.home.profileNameText, 5);
			String userNameText = sf.seleU.getTextFromWebElement(sf.home.profileNameText);

			// Validating the logged in user
			if (userNameText.equals(InputData.username)) {
				reportStatusPass(methodName + " User " + userNameText + " has successfully logged in to Application",
						true, true);
			} else {
				reportStatusFail(methodName + " Incorrect user", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" login to SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * logout from the application
	 * 
	 * @throws IOException
	 */
	public void logout() throws IOException {
		try {

			String methodName = "SFDC_Home@: ";

			// Click on logout
			sf.seleU.hardwait(5000);
			if (sf.seleU.isElementPresent(sf.home.logoutMenu) && sf.seleU.isElementDisplayed(sf.home.logoutMenu)) {
				sf.seleU.clickElementByJSE(sf.home.logoutMenu);
			} else if (sf.seleU.isElementDisplayed(sf.home.userImg)) {
				sf.seleU.clickElementByJSE(sf.home.userImg);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.home.logoutMenu);
				
			} 
			
			else {
				sf.seleU.navigateBack();
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.home.userImg);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.home.logoutMenu);

			}

			if (sf.seleU.isElementPresent(sf.login.userNameInput)) {
				reportStatusPass(methodName + " Successfully logged out from the application", true, true);
			} else {
				reportStatusFail(methodName + " logout Unsuccessful", true);
			}
		} catch (

		Throwable e) {
			reportStatusFail("logout from SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * logout from the application
	 * 
	 * @throws IOException
	 */
	public void logout_FromB2B() throws IOException {
		try {

			String methodName = "SFDC_Home@: ";

			// Click on logout
			sf.seleU.hardwait(5000);
			if (sf.seleU.isElementDisplayed(sf.home.userImg_B2B)) {
				sf.seleU.clickElementByJSE(sf.home.userImg_B2B);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.home.logoutMenu);
			}

			if (sf.seleU.isElementPresent(sf.login.userNameInput)) {
				reportStatusPass(methodName + " Successfully logged out from the B2B application", true, true);
			} else {
				reportStatusFail(methodName + " logout Unsuccessful", true);
			}
			
		} catch (

		Throwable e) {
			reportStatusFail("logout from B2B Application is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Close already opened tab
	 * 
	 * @throws IOException
	 */
	public void closeTabIfOpen() throws IOException {

		try {

			sf.seleU.refreshPage();
			sf.seleU.hardwait(2000);

			int closeTabIconsCount = sf.home.closeTab.size();
			if (closeTabIconsCount > 0) {

				while (sf.home.closeTab.size() > 0) {
					sf.seleU.clickElementByJSE(sf.home.closeTab.get(sf.home.closeTab.size() - 1));
					sf.seleU.hardwait(2000);

				}
			}
//			sf.seleU.refreshPage();
			
		} catch (Throwable e) {
			reportStatusFail("Error in closing pre-opened  tab", e);
			e.printStackTrace();
		}

	}

	/**
	 * Close already opened tab
	 * 
	 * @throws IOException
	 */
	public void closeTabIfOpenWithRefresh() throws IOException {

		try {

			sf.seleU.wait(7000);

			int closeTabIconsCount = sf.home.closeTab.size();
			if (closeTabIconsCount > 0) {

				while (sf.home.closeTab.size() > 0) {
					sf.seleU.clickElementByJSE(sf.home.closeTab.get(sf.home.closeTab.size() - 1));
					sf.seleU.wait(7000);

				}
			}
			sf.seleU.hardwait(5000);
			sf.seleU.refreshPage();
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail("Error in closing pre-opened  tab", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Close the Recently Opened Tab
	 */
	public void closeLastOpenedTab() throws IOException {
		try {

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.closeTab.get(sf.home.closeTab.size() - 1));
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail("Error in closing pre-opened  tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Close the Recently Opened Tab
	 */
	public void closeLastOpenedTabs(int closeNum) throws IOException {
		try {
			sf.seleU.wait(2000);
			for (int i = 0; i < closeNum; i++) {
				sf.seleU.wait(3000);
				sf.seleU.clickElementByJSE(sf.home.closeTab.get(sf.home.closeTab.size() - 1));
			}

			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail("Error in closing pre-opened tabs", e);
			e.printStackTrace();
		}
	}

	/**
	 * Date : 08/Jan/2019
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select respective Console
	 * 
	 * 3- Validate Console title
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	public void openR4BSalesConsole() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for Respective console
			sf.seleU.clickElementByJSE(sf.home.applauncher);
//			sf.seleU.clickElementByJSE(sf.home.viewAllButton);
//			reportStatusPass(methodName + " Clicked on App launcher ", true, false);
//			sf.seleU.enterText(sf.home.searchAppPlaceHolderInput, Global.dataInput.console);
//			sf.seleU.hardwait(2000);
//
//			// Select the correct one as per the input data
//			for (int i = 0; i < sf.home.appTileAllLinks.size(); i++) {
//				if (sf.home.appTileAllLinks.get(i).getText().equals(Global.dataInput.console)) {
//					sf.seleU.clickElementByJSE(sf.home.appTileAllLinks.get(i));
//					break;
//				}
//			}
//			sf.seleU.hardwait(4000);

			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, Global.dataInput.console);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(Global.dataInput.console)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.wait(4000);

			// verify correct app console is selected
			if (sf.seleU.getTextFromWebElement(sf.home.consoleTitleText).equals(Global.dataInput.console)) {
				reportStatusPass(methodName + " Validated that " + Global.dataInput.console + " is selected", true,
						true);
			} else {
				reportStatusFail(methodName + " Selecting " + Global.dataInput.console + " is not successful", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
	}
	
	public void openServiceConsole() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for Respective console
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, InputData.serviceConsole);
			for (int i = 0; i < sf.home.searchedApp.size(); i++) 
			{
				if (sf.home.searchedApp.get(i).getText().equals(InputData.serviceConsole)) 
				{
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.wait(4000);

			// verify correct app console is selected
			if (sf.seleU.getTextFromWebElement(sf.home.consoleTitleText).equals(InputData.serviceConsole)) 
			{
				reportStatusPass(methodName + " Validated that Service Console is selected", true, true);
			} 
			else 
			{
				reportStatusFail(methodName + " Selecting Service Console is not successful", true);
			}
			sf.seleU.hardwait(4000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
	}
	
	public void openSurveyResponses() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for Respective console
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, InputData.surveyResponsesConsole);
			for (int i = 0; i < sf.home.searchedAppNew.size(); i++) 
			{
				if (sf.home.searchedAppNew.get(i).getText().equals(InputData.surveyResponsesConsole)) 
				{
					sf.seleU.clickElementByJSE(sf.home.searchedAppNew.get(i));
					break;
				}
			}
			sf.seleU.wait(4000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Date : 24/June/2021
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select R4B Quote Approval
	 *
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	public void openR4BQuoteApproval() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for R4B Quote Approval
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.home.searchAppItemInput, Global.dataInput.r4BQuoteApproval);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(Global.dataInput.r4BQuoteApproval)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
		
	}
	 
	/**
	 * Date : 14/Oct/2021
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select Campaign
	 *
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	public void openCampaign() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for R4B Quote Approval
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.home.searchAppItemInput, Global.salesData.campaigns);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(Global.salesData.campaigns)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the Campaigns Console ", e);
			e.printStackTrace();
		}
		
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select + Icon on top right corner
	 * 
	 *                     Select 'Switch to B2B Org Option'
	 */
	public void switchToB2BOrg() throws IOException {
		try {

			String methodName = "SFDC_Home@: ";

			// Select + Icon on top right corner
			sf.seleU.clickElementByJSE(sf.home.plusIcon);
			reportStatusPass(methodName + " Clicked on '+' Plus Icon on top right of the page ", true, false);
			sf.seleU.hardwait(4000);

			// Select 'Switch to B2B Org Option'
			sf.seleU.clickElementByJSE(sf.home.switchToB2BOrg);
			reportStatusPass(methodName + "   Select 'Switch to B2B Org Option' ", true, false);
			sf.seleU.hardwait(15000);

			// Verify B2B/ITFULL
			if (sf.seleU.driver.getCurrentUrl().trim().contains(sf.dataInput.oldOrg_url.trim())) {
				reportStatusPass(methodName + " Successful Switcing from R4B to B2B ", true, true);
			} else {
				reportStatusFail("Error in switching to B2B Org", true);
			}

		} catch (Throwable e) {
			reportStatusFail("Error in switching to B2B Org", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select Navigation Menu
	 * 
	 *                     2.Select Home
	 */
	public void selectHome() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {
			// Select Navigation Menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.HomeMenu);
			reportStatusPass(methodName + " Selected Home from menu", true, false);
			sf.seleU.hardwait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not open Home tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify active/default tab when Home Page is opened from
	 *                     navigation menu
	 * 
	 */
	public void verifyActiveTabUnderHome() throws IOException {

		String methodName = "SFDC_Home@: ";
		String defaulttabName = sf.dataInput.defaultTabUnderHome;
		try {
			sf.seleU.hardwait(10000);
			// Verify value in tabName is active/default tab when Home page is opened
			if (sf.seleU.getTextFromWebElement(sf.home.activeTabInHome).equalsIgnoreCase(defaulttabName)) {
				reportStatusPass(
						methodName + defaulttabName + " is the active tab opened by default when Home page is selected",
						true, true);
			} else {
				reportStatusFail(methodName + defaulttabName
						+ " is not the active tab opened by default when Home page is selected", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verification Failure for Default tab under Home page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify tabNames are present when Home is opened from
	 *                     navigation menu
	 * 
	 */
	public void verifyTabsUnderHome() throws IOException {

		String methodName = "SFDC_Home@: ";

		try {
			sf.dataInput.setTabsUnderHome();
			List<String> expectedTabsUnderHome = sf.dataInput.tabsUnderHome;
			List<String> actualTabsUnderHome = new ArrayList<String>();

			// Extract all actual tabs name present on page and store in a list
			for (int i = 0; i < sf.home.tabItemsInHome.size(); i++) {
				actualTabsUnderHome.add(sf.seleU.getTextFromWebElement(sf.home.tabItemsInHome.get(i)));
			}

			// sort lists for comparison
			Collections.sort(expectedTabsUnderHome);
			Collections.sort(actualTabsUnderHome);

			// Verify expectedTabs list is equal to actualTabs List

			if (expectedTabsUnderHome.equals(actualTabsUnderHome)) {
				reportStatusPass(methodName + " All expected tabs are present under Home page : "
						+ AdditionalUtilities.getAsString(actualTabsUnderHome), true, true);
			} else {
				reportStatusFail(methodName + " All expected tabs are not present :: Expected Tabs--> "
						+ AdditionalUtilities.getAsString(expectedTabsUnderHome) + "  Actual Tabs--> "
						+ AdditionalUtilities.getAsString(actualTabsUnderHome), true);
			}
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verification Failure for tabs under Home page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Open Task Section is present under My Activities
	 *                     in Home Page
	 * 
	 */
	public void verifyOpenTaskSection() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {

			// Go to My Activities tab
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.home.myActivitiesTab);
			sf.seleU.hardwait(2000);

			// Verify Open Task section present or not
			if (sf.seleU.isElementPresent(sf.home.openTasksSection)) {
				reportStatusPass(methodName + " Open Task Section is present under My Activities in Home page", true,
						true);
			} else {
				reportStatusFail(methodName + " Open Task Section is not present under My Activities in Home page",
						true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Verification Failure for Open task Section under My Activities in Home page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Open Task Section table number of rows under My
	 *                     Activities in Home Page
	 * 
	 */
	public void verifyOpenTaskSectionTableSize() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {

			// Verify OpenTask table contains 1 more row than before
			sf.seleU.hardwait(4000);
			if (sf.home.openTasksTableRows.size() == (sf.dataInput.openTaskTableNoOfRows + 1)) {
				reportStatusPass(methodName + " Open Task Section contains the newly created task", true, true);
			} else {
				reportStatusFail(methodName + " Open Task Section does not contains the newly created task", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName
							+ " Verification Failure for finding number of open tasks under My Activities in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Open Task Section has the recently created task in
	 *                     Home Page
	 * 
	 */
	public void verifyNewTaskInOpenTaskSection() throws IOException {

		String methodName = "SFDC_Home@: ";

		try {
			String expectedValue = sf.dataInput.lastModifiedActualValueInTaskCreation;
			String actualValue1 = sf.seleU.getTextFromWebElement(sf.home.lastModifiedColumnValueTaskSection);
			reportStatusPass(
					methodName
							+ "  Extracted actualvalue from last modified column in Open Task section for verification",
					false, false);

			// Sort and extract value again if the newly created task appends at the end of
			// list
			sf.seleU.hardwait(3000);
			//sf.seleU.clickOnElement(sf.home.lastModifiedColumnTaskSection);
			sf.seleU.hardwait(5000);
			String actualValue2 = sf.seleU.getTextFromWebElement(sf.home.lastModifiedColumnValueTaskSection);
			reportStatusPass(methodName
					+ " Sorted Column 'Last Modified Date/Time and extracted actualvalue again if newly created task was appended at the end of the list",
					false, false);

			// Verify expected value matches any of the actual values or not
			sf.seleU.hardwait(4000);

			if ((expectedValue.contains(actualValue1)) || (expectedValue.contains(actualValue2))) {
				reportStatusPass(
						methodName + " Open Task Section contains the newly created task :: Expected Value --> "
								+ expectedValue + " Actual Values --> " + actualValue1 + ", " + actualValue2,
						true, true);
			} else {
				reportStatusFail(
						methodName + " Open Task Section does not contains the newly created task:: Expected Value --> "
								+ expectedValue + " Actual Values --> " + actualValue1 + ", " + actualValue2,
						true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName
							+ " Verification Failure for finding newly created task under My Activities in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Extract number of rows in Open Task Section table under
	 *                     My Activities in Home Page
	 * 
	 */
	public void getNumberofRowsInOpenTaskSection() throws IOException {

		String methodName = "SFDC_Home@: ";

		try {
			// Extract No. of Tasks present in Open Tasks section under Home Page
			sf.seleU.hardwait(4000);
			sf.dataInput.openTaskTableNoOfRows = sf.home.openTasksTableRows.size();
			reportStatusPass(
					methodName + " No. of tasks in Open Task section are " + sf.dataInput.openTaskTableNoOfRows, false,
					false);

		} catch (Throwable e) {
			reportStatusFail(
					methodName
							+ " Cannot extract number of rows in open tasks section under My Activities in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Create new task under Open Tasks Section in Home Page(My
	 *                     Activities)
	 * 
	 *                     Fill form for New Task and save
	 * 
	 */
	public void createNewTask() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {

			// Click on new task
			sf.seleU.clickElementByJSE(sf.home.newTaskButton);

			// Click on Log a Sales call Radio Button
			sf.seleU.clickElementByJSE(sf.home.newTaskLogSalesRadio);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.newTaskNextButton);
			reportStatusPass(methodName + " Creating a New Task by selecting Log a Sales Task option", false, false);

			// Fill Form
			sf.seleU.clickElementByJSE(sf.home.newTaskTypeDropDown);
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.home.newTaskTypeBase))
				sf.seleU.clickElementByJSE(sf.home.newTaskTypeBase);
			else
				sf.seleU.clickElementByJSE(sf.home.newTaskTypeNewCustomer);
			sf.seleU.clearAndEnterText(sf.home.newTaskSubjectTextBox, sf.dataInput.typeOfTask);
			sf.seleU.hardwait(2000);
			sf.seleU.scrollByCoOrdinates(2);
			//sf.seleU.clickElementByJSE(sf.home.newTaskDueDateIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.inputDate, Global.salesData.currentDate);
			//sf.seleU.enterText(sf.home.newTaskDueDateInput, Global.salesData.currentDate);
			sf.seleU.hardwait(3000);
			//sf.seleU.clickElementByJSE(sf.home.newTaskDueDateToday);
			
			sf.seleU.enterText(sf.home.newTaskSubjectTextBox, Keys.TAB);
			
			//sf.seleU.enterText(sf.home.newTaskDueDateInput, AdditionalUtilities.getTodaysDate());
			reportStatusPass(methodName
					+ " Selected Type as Reporting, Status as Open, Due Date as Today and Subject as Follow Up Meeting for the new task",
					false, false);

			// Click on Save to create the task
			sf.seleU.clickElementByJSE(sf.home.newTaskSaveButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Created New Task", true, false);

			// Extract Last modified DateTime to verify on Home Page
			sf.seleU.wait(12000);
			sf.dataInput.lastModifiedActualValueInTaskCreation = sf.seleU
					.getTextFromWebElement(sf.home.lastModifiedActualValueInTaskCreation);
			reportStatusPass(methodName + " Saved Last modified Date/Time for newly created task :: "
					+ sf.dataInput.lastModifiedActualValueInTaskCreation, true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating new task", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Accounts Section is present under My Accounts in
	 *                     Home Page
	 * 
	 */
	public void verifyAccountsSection() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {
			// Click on My Accounts Tab
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.home.myAccountsTab);
			sf.seleU.hardwait(2000);

			// Verify Accounts section is present under My Accounts tab
			if (sf.seleU.isElementPresent(sf.home.myAccountsSection)) {
				reportStatusPass(methodName + "Accounts Section is present under My Accounts in Home page", true, true);
			} else {
				reportStatusFail(methodName + "Accounts Section is not present under My Accounts in Home page", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verification Failure for Accounts Section under My Accounts in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Accounts Section is present under My Accounts in
	 *                     Home Page
	 * 
	 */
	public void verifyAccountCasesSection() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {
			// Click on My Accounts Tab
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.home.myAccountsTab);
			sf.seleU.hardwait(2000);

			// Verify Accounts section is present under My Accounts tab
			if (sf.seleU.isElementPresent(sf.home.myAccountCasesSection)) {
				reportStatusPass(methodName + "My Account Cases Section is present under My Accounts in Home page",
						true, true);
			} else {
				reportStatusFail(methodName + "My Account Cases Section is not present under My Accounts in Home page",
						true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Verification Failure for My Account Cases Section under My Accounts in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Account Section table has some data
	 * 
	 */
	public void verifyAccountSectionTableData() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {
			// Verify Account Section table has some data
			sf.seleU.hardwait(4000);
			if (sf.home.accountsTableRows.size() > 0) {
				reportStatusPass(methodName + "Accounts Section contains" + sf.home.accountsTableRows.size()
						+ " business/billing/service accounts", true, true);
			} else {
				reportStatusFail(methodName + "Accounts Section does not contain any accounts", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Verification Failure for finding number of accounts under My Accounts in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Create new account under Accounts Section in Home Page(My
	 *                     Accounts)
	 * 
	 *                     Fill form for New business account and save
	 * 
	 */
	public void createNewBuissnessAccountFromMyAccounts() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {

			// Click on new from Accounts section and select Business account
			sf.seleU.clickElementByJSE(sf.home.newAccountButton);
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.acc.businessAccountRadio);
			sf.seleU.clickElementByJSE(sf.acc.businessAccountRadio.get(0));
			sf.seleU.clickElementByJSE(sf.acc.selectAccountType_nextBtn);
			reportStatusPass(methodName + " Selected account as Business and clicked on Next", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in creating new task", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Account Section table has the newly created
	 *                     account
	 * 
	 */
	public void verifyNewAccountinMyAccountsTable() throws IOException {

		String methodName = "SFDC_Home@: ";
		try {
			// Verify Account Section table has the newly created account searching by its
			// name
			sf.seleU.hardwait(4000);
			sf.seleU.clearAndEnterText(sf.home.searchInput, Global.dataInput.businessAccountName);
			sf.seleU.wait(4000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.wait(5000);

			reportStatusPass(methodName + " Searching for newly created account in the list", false, false);
			if (sf.acc.accountsAllRecords.size() > 0) {
				reportStatusPass(methodName + "Accounts Section contains " + Global.dataInput.businessAccountName					+ " business/billing/service account", true, true);
			} else {
				reportStatusFail(methodName + "Accounts Section does not contain the newly created account", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName
							+ " Verification Failure for finding newly created account under My Accounts in Home page",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Opportunities Section is present under My Accounts
	 *                     in Home Page
	 * 
	 */
	public void verifyOpportunitySection() throws IOException {

		String methodName = "SFDC_Home@: ";

		try {
			// Click on My Accounts tab
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.home.myAccountsTab);
			sf.seleU.hardwait(2000);

			// Verify Opportunities section present
			if (sf.seleU.isElementPresent(sf.home.opportunitiesSection)) {
				reportStatusPass(methodName + " Opportunities Section is present under My Accounts in Home page", true,
						true);
			} else {
				reportStatusFail(methodName + " Opportunities Section is not present under My Accounts in Home page",
						true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Verification Failure for Opportunities Section under My Accounts in Home page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Opportunities Section table number of rows under
	 *                     My Accounts in Home Page
	 * 
	 */
	public void verifyOpportunitySectionTableData() throws IOException {

		String methodName = "SFDC_Home@: ";

		try {
			// Verify Opportunities Section table has some data
			sf.seleU.hardwait(4000);
			if (sf.home.opportunitiesTableRows.size() >= 1) {
				reportStatusPass(methodName + "Opportunities Section contains " + sf.home.opportunitiesTableRows.size()
						+ " opportunities", true, true);
			} else {
				reportStatusFail(methodName + "Opportunities Section does not contain any data", true);
			}

			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Verification Failure for finding number of Opportunities under My Accounts in Home page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Click on Home tab button to navigate to My Performance Tab
	 * 
	 * @throws IOException
	 */

	
	public void myPerformanceTabClick() throws IOException {

		try {

			String methodName = "SFDC_My Performance Button Click@: ";

			// 1-Clicking My Performance tab button
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.home.myPerformance);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Clicked on the My Performance tab button", true, true);
			sf.seleU.hardwait(3000);
		

		} catch (Throwable e) {
			reportStatusFail("Selecting on the My Performance tab button is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
						
	
	/**
	 * Click on Home tab button to navigate to R4B Sales Opportunities Dash Board
	 * 
	 * @throws IOException
	 */
	
	public void validateR4BSalesOpportunitiesDashboard() throws IOException {

		try {
			// Validating R4B Sales Opportunities dash board is present or not
			
			String methodName = "SFDC_R4B Sales Opportunities Dashboard @: ";

			String strSalesOpp = "R4B Sales opportunities";
			
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.home.salesOpportunities);
			sf.seleU.ScrolltoElement(sf.home.salesOpportunities.get(0));
			
			
			if(strSalesOpp.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.salesOpportunities.get(0))))
			reportStatusPass(methodName + "Validation Successful- R4B Sales Opportunities dashbaord is present for AE on Homepage under  Performance tab : "+sf.seleU.getTextFromWebElement(sf.home.salesOpportunities.get(0)) ,true, true);
			 else
		    reportStatusFail("Validation Unsuccessful-R4B Sales Opportunities Dash Board is not Present", true);
			

		} catch (Throwable e) {
			reportStatusFail("Selecting R4B Sales Opportunities Dash Board on the Home Page is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Click on Home tab button to navigate to R4B Sales Opportunities Dash Board
	 * 
	 * @throws IOException
	 */
	
	public void validateR4BSalesOpportunitiesDashboardWithFilters() throws IOException {

		try {
			// Validating R4B Sales Opportunities dash board is present or not
			
			String methodName = "SFDC_R4B Sales Opportunities Dashboard with Filters @: ";

			 List<String> list=new ArrayList<String>();  
			
			 list.add("Opportunity Stage");  
			 list.add("Opportunity Close Date");
			 list.add("Quote Status");
			 list.add("Order Status");
			 list.add("Order Created Date");
			 list.add("Channel");
			 list.add("Sales Segment");
			 list.add("Order Close Date");
			 
			sf.seleU.hardwait(4000);
			sf.seleU.switchToElementFrame(sf.home.salesOpportunities);
			sf.seleU.ScrolltoElement(sf.home.salesOpportunities.get(0));
			
			
			if(list.get(0).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.opportunityStage.get(0))) && list.get(1).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.opportunityCloseDate.get(0))) && list.get(2).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.quoteStatus.get(0))) && list.get(3).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.orderStatus.get(0))) && list.get(4).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.orderCreatedDate.get(0))) && list.get(5).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.channel.get(0))) && list.get(6).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.salesSegment.get(0))) && list.get(7).equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.home.orderCloseDate.get(0)))) 
			reportStatusPass(methodName + "Validation Successful- R4B Sales Opportunities dashbaord filters present are as required. : "+sf.seleU.getTextFromWebElement(sf.home.salesOpportunities.get(0)) ,true, true);
			 else
		    reportStatusFail("Validation Unsuccessful-R4B Sales Opportunities dashbaord filters not present are as required.", true);
			

		} catch (Throwable e) {
			reportStatusFail("Selecting R4B Sales Opportunities Dash Board with filters on the Home Page is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Voice of R4B links are present on Home Page
	 * 
	 *                     Verify if link is open in new tab
	 */
	public void verifyVoiceOfLinksPresentAtHome() throws IOException {
		try {
			String methodName = "SFDC_Account@: ";

			// Select Links from Accounts page
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.acc.links);
			sf.seleU.hardwait(2000);

			//Verify Verify Voice of R4B links are present on Account Page
			String str= "Voice of R4B";
			if(str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.acc.voiceOfLinks)))
			reportStatusPass(methodName +" Verifed Voice of R4B links are present on Account Page" , true, true);
			
			else
				reportStatusFail(" Verification Failed- Verifed Voice of R4B links are not present on Account Page ", true);
			
			// Select account
			sf.seleU.clickElementByJSE(sf.acc.voiceOfLinks);
			reportStatusPass(methodName + " Clicked on Voice of R4B Links from Accounts", true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.switchWindow(3);
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
	/**
	 * Date : 08/Sep/2021
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select Guided Selling By ringDNA
	 * @throws IOException
	 * 
	 */
	public void openGuidedSellingByRingDNA() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for R4B Quote Approval
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.home.searchAppItemInput, Global.dataInput.gSBRingDNA);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(Global.dataInput.gSBRingDNA)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Date : 01/Dec/2021
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select tasks
	 * @throws IOException
	 * 
	 */
	public void openTasks() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for R4B Quote Approval
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.wait(5000); 
			sf.seleU.enterText(sf.home.searchAppItemInput, Global.dataInput.tasks);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(Global.dataInput.tasks)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.wait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Date : 13/Sep/2021
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select Guided Selling By ringDNA Should not able to open for AE Profile 
	 * @throws IOException
	 * 
	 */
	public void openGuidedSellingByRingDNAAE() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for R4B Quote Approval
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.wait(5000);
			sf.seleU.enterText(sf.home.searchAppItemInput, Global.dataInput.gSBRingDNA);
			if (sf.home.noResults.get(0).getText().equals(Global.dataInput.noResult)) {
				reportStatusPass(" Verified  Guided Selling By ringDNA should not be able to open for AE Profile" , true, true);
			}
			else
				reportStatusFail(" Verification Failed- Guided Selling By ringDNA Should able to open for AE Profile ", true);
		
			sf.seleU.wait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 1- Click on App launcher
	 * 
	 * 2- Select respective Console
	 * 
	 * 3- Validate Console title
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	public void openReports() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";

			// Click on app launcher and look for Respective console
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, InputData_Sales.rports);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(InputData_Sales.rports)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.wait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the respective Console ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *  Validate reports
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	public void validateReports() throws IOException {
		try {
			String methodName = "SFDC_Home@: ";
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.lead.newReport);
			sf.seleU.wait(2000);
			sf.seleU.switchToFrame(sf.lead.iframeReportBuilder);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.lead.reportLead);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.lead.reptcontinue);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.lead.runReport);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.lead.searchReportButton);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.lead.searchReportInput, InputData_Sales.leadBusAccountName);
			sf.seleU.clickEnter();
			sf.seleU.wait(2000);
			if (sf.seleU.getTextFromWebElement(sf.lead.match).equals(InputData_Sales.match)) {
				reportStatusPass(methodName + "Verification Pass- Validated, Converted Lead should be reportable", true,
						true);
			} else {
				reportStatusFail(methodName + " Verification Fail- Validated, Converted Lead should not be reportable", true);
			}

			sf.seleU.hardwait(4000);
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail("Unable to reports ", e);
			e.printStackTrace();
		}
	}
	

}
