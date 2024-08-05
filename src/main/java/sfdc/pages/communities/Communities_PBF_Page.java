package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:02/04/2021
 *
 *         Communities Persona Based Buy Flow Page
 */
public class Communities_PBF_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_PBF_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_PBF@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open URL for Communities PBF page
	 * 
	 *                     Enter Credentials for communities PBF Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToCommunitiesPBF(List<String> credentials) throws IOException {

		try {

			driver.get(InputData_Communities.communities_PBFurl);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(sf.comPBF.rogersLogo)) {
				reportStatusPass(methodName + "User Already Logged in ", true, true);
			} else {

				enterCredentialsPBFCommunities(credentials.get(0), credentials.get(1));

			}
			sf.seleU.wait(20000);
		} catch (Throwable e) {
			reportStatusFail(methodName + "login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param userID
	 * @param pwd
	 * @throws IOException
	 * 
	 *                     Enter Credentials for communities PBF Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 *                     Verify Login success
	 */
	public void enterCredentialsPBFCommunities(String userID, String pwd) throws IOException {
		try {

			// Enter Credentials for communities Login
			sf.seleU.clearAndEnterText(sf.comPBF.userNameInputBox, userID);
			String decodedPassword = decodeBytes(pwd);
			sf.seleU.clearAndEnterText(sf.comPBF.passwordInputBox, decodedPassword);
			reportStatusPass(methodName + " Entered credentials for communities PBF Login", true, false);

			// Click on sign-In Button
			sf.seleU.clickElementByJSE(sf.comPBF.loginButton);
			reportStatusPass(methodName + " Clicked on Log In Button", true, false);
			sf.seleU.wait(10000);

			driver.get(InputData_Communities.communities_PBFurl);
			sf.seleU.wait(5000);

			// Verify Login success
			if (sf.seleU.isElementPresent(sf.comPBF.rogersLogo)) {
				reportStatusPass(methodName + "Login to communities PBF successful", true, true);
			} else {
				reportStatusFail(methodName + " Unsuccessful login to PBF communities", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + "Login to PBF communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Primary Account is set as default value in
	 *                     business account dropdown
	 * 
	 */
	public void verifyBusAccDefaultValue() throws IOException {
		try {
			verifyFieldValue("Default Value set in Business Account Dropdown", sf.comPBF.businessAccountDefaultValue,
					InputData_Communities.primaryAccountForCommUser);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying PBF landing page is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify PBF landing Page
	 * 
	 */
	public void verifyPBFLandingPage() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.scrollByCoOrdinates(2);
			if (sf.seleU.isElementDisplayed(sf.comPBFShopCart.buyBusinessProductsButton))
				sf.seleU.clickElementByJSE(sf.comPBFShopCart.buyBusinessProductsButton);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Communities PBF Page - Where Would you like your services Header", sf.comPBF.letsGetStartedHeader);
			verifyFieldDisplayed("Communities PBF Page - Select Address Text", sf.comPBF.selectAddressFlowText);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying PBF landing page is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify PBF landing Page logo
	 * 
	 */
	public void verifyPBFLandingPageRogersLogo() throws IOException {
		try {
			sf.seleU.wait(30000);
			verifyFieldDisplayed("Communities PBF Page - Rogers Logo on Top", sf.comPBF.rogersLogo);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying PBF landing page is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Business Account dropdown is present to select the
	 *                     Business Acc (In case of multiple business acc)
	 * 
	 */
	public void verifyBusinessAccDropdownPresent() throws IOException {
		try {
			verifyFieldDisplayed("Business Account Dropdown(Case of Multiple Accounts)", sf.comPBF.businessAccDropdown);
			verifyFieldDisplayed("Business Account Dropdown(Heading - 'Select a business account to begin')",
					sf.comPBF.headingForBusAccDropdown);
			verifyFieldDisplayed(
					"Business Account Dropdown(SubHeading -'You will need to create one order per business account')",
					sf.comPBF.subHeadingForBusAccDropdown);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying presence of business acc dropdown is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Business Account dropdown is not present to select
	 *                     the Business Acc (In case of single business acc)
	 * 
	 */
	public void verifyBusinessAccDropdownNotPresent() throws IOException {
		try {

			verifyFieldNotDisplayed("Business Account Dropdown(Case of Single Accounts)",
					sf.comPBF.businessAccDropdown);
			verifyFieldNotDisplayed("Business Account Dropdown(Heading - 'Select a business account to begin')",
					sf.comPBF.headingForBusAccDropdown);
			verifyFieldNotDisplayed(
					"Business Account Dropdown(SubHeading -'You will need to create one order per business account')",
					sf.comPBF.subHeadingForBusAccDropdown);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying absence of business acc dropdown is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Business Accounts in Dropdown
	 * 
	 */
	public void verifyBusinessAccountValues() throws IOException {

		try {

			reportStatusPass(methodName + " Default value for Business Account Dropdown is "
					+ sf.seleU.getTextFromWebElement(sf.comPBF.businessAccountDefaultValue), true, true);

			// Click on dropdown to open it
			sf.seleU.clickOnElement(sf.comPBF.businessAccountDefaultValue);
			reportStatusPass(methodName + " Clicked on Business Account Dropdown", true, false);
			sf.seleU.wait(2000);

			// Verify expected values of business account with actual values
			List<String> actualValuesForBusinessAcc = new ArrayList<>();
			for (int i = 0; i < sf.comPBF.businessAccountValues.size(); i++) {
				actualValuesForBusinessAcc.add(sf.seleU.getTextFromWebElement(sf.comPBF.businessAccountValues.get(i)));
				reportStatusPass(methodName + " Value " + i + " in Business Account Dropdown: "
						+ sf.seleU.getTextFromWebElement(sf.comPBF.businessAccountValues.get(i)), true, true);
			}

			// sort lists for comparison
			Collections.sort(InputData_Communities.communitiesBusinessAccounts);
			Collections.sort(actualValuesForBusinessAcc);

			// Verify expected value list is equal to actual values List

			if (InputData_Communities.communitiesBusinessAccounts.equals(actualValuesForBusinessAcc)) {
				reportStatusPass(methodName + " All expected Business Accounts are present in the dropdown : "
						+ AdditionalUtilities.getAsString(actualValuesForBusinessAcc), true, true);
			} else {
				reportStatusFail(
						methodName + " All expected Business Accounts are not present :: Expected Business Accounts--> "
								+ AdditionalUtilities.getAsString(InputData_Communities.communitiesBusinessAccounts)
								+ "  Actual Business Accounts--> "
								+ AdditionalUtilities.getAsString(actualValuesForBusinessAcc),
						true);
			}

			// Click on dropdown to close it
			sf.seleU.clickOnElement(sf.comPBF.businessAccountDefaultValue);
			reportStatusPass(methodName + " Clicked on Business Account Dropdown to close it", false, false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Invalid communities tab", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify corresponding service addresses to single business
	 *                     account related to community user
	 * 
	 */
	public void verifyServiceAddressesForSingleBusAcc() throws IOException {

		try {

			// Verify expected values of service addresses with actual values
			List<String> actualServiceAddresses = new ArrayList<>();
			List<String> expectedServiceAddresses = new ArrayList<>();

			expectedServiceAddresses = (List<String>) Global.dataInput.servicePremiseAddress
					.get(InputData_Communities.primaryAccountForCommUser);

			sf.seleU.scrollByCoOrdinates(1);
			// Remove all null values from list like Remove "null-" from "null-Brampton"
			expectedServiceAddresses = replaceNullValues(expectedServiceAddresses);

			for (int j = 0; j < sf.comPBF.serviceAddressValues.size(); j++) {
				actualServiceAddresses.add(sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)));
				reportStatusPass(methodName + " Value " + j + " in Serivce Addresses List: "
						+ sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)), true, true);
			}

			// sort lists for comparison
			Collections.sort(expectedServiceAddresses);
			Collections.sort(actualServiceAddresses);

			// Verify expected value list is equal to actual values List

			if (expectedServiceAddresses.equals(actualServiceAddresses)) {
				reportStatusPass(methodName + " All expected service addresses for Business Account "
						+ " are present in the list : " + AdditionalUtilities.getAsString(actualServiceAddresses), true,
						true);
			} else {
				reportStatusFail(methodName + "  All expected service addresses for Business Account "
						+ " are not present in the list :: Expected Service Addresses--> "
						+ AdditionalUtilities.getAsString(expectedServiceAddresses) + "  Actual Service Addresses--> "
						+ AdditionalUtilities.getAsString(actualServiceAddresses), true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Verifying corresponding service addresses to business account related to community user unsuccessfull",
					e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify corresponding service addresses to business
	 *                     account related to community user - With Pagination
	 * 
	 */
	public void verifyServiceAddresses() throws IOException {

		try {

			// Verify expected values of service addresses with actual values
			List<String> actualServiceAddresses = new ArrayList<>();
			List<String> expectedServiceAddresses = new ArrayList<>();

			expectedServiceAddresses = InputData_Communities.commPBFServiceAddresses;

			sf.seleU.scrollByCoOrdinates(3);
			// Remove all null values from list like Remove "null-" from "null-Brampton"
			expectedServiceAddresses = replaceNullValues(expectedServiceAddresses);

			do {
				for (int j = 0; j < sf.comPBF.serviceAddressValues.size(); j++) {
					actualServiceAddresses.add(sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)));
					reportStatusPass(methodName + " Value " + j + " in Serivce Addresses List: "
							+ sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)), true, true);
				}
				
				sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
				sf.seleU.wait(3000);
			} while(isFieldEnabled(" Next Page Button", sf.comPBF.nextPageButton)); 
			
			//Extract service sites from last page
			for (int j = 0; j < sf.comPBF.serviceAddressValues.size(); j++) {
				actualServiceAddresses.add(sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)));
				reportStatusPass(methodName + " Value " + j + " in Serivce Addresses List: "
						+ sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)), true, true);
			}
			//Remove duplicates
			Set<String> set = new HashSet<>(actualServiceAddresses);
			actualServiceAddresses.clear();
			actualServiceAddresses.addAll(set);
			
			// sort lists for comparison
			Collections.sort(expectedServiceAddresses);
			Collections.sort(actualServiceAddresses);

			// Verify expected value list is equal to actual values List

			if (expectedServiceAddresses.equals(actualServiceAddresses)) {
				reportStatusPass(methodName + " All expected service addresses for Business Account "
						+ " are present in the list : " + AdditionalUtilities.getAsString(actualServiceAddresses), true,
						true);
			} else {
				reportStatusFail(methodName + "  All expected service addresses for Business Account "
						+ " are not present in the list :: Expected Service Addresses--> "
						+ AdditionalUtilities.getAsString(expectedServiceAddresses) + "  Actual Service Addresses--> "
						+ AdditionalUtilities.getAsString(actualServiceAddresses), true);
			}

			//Set the table back to Page 1
			while(isFieldEnabled("Previous Page Button", sf.comPBF.previousPageButton)) {
				sf.seleU.clickOnElement(sf.comPBF.previousPageButtonParent);
				sf.seleU.wait(3000);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Verifying corresponding service addresses to business account related to community user unsuccessfull",
					e);
			e.printStackTrace();
		}
	}
	
	/*
	 * @throws IOException
	 * 
	 *                     Verify Filter, sorting, pagination nd Total
	 * 
	 */
	public void verifyServiceAddressesTableLayout() throws IOException {
		try {
				verifySortingOnTable();
				verifyPaginationOnTable();
				verifyTotalServiceAddressCount();
				searchRandomAddress();
				verifyNoSitesFoundMessage();
				
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Valiating Service Address Table layout is unsuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Replace null values for Premises addresses
	 * 
	 */
	private List<String> replaceNullValues(List<String> expectedServiceAddresses) throws IOException {

		try {
			// TODO Auto-generated method stub
			String oldValue = "";
			for (int i = 0; i < expectedServiceAddresses.size(); i++) {
				if (expectedServiceAddresses.get(i).contains("null-")) {
					oldValue = expectedServiceAddresses.get(i).replace("null-", "");
					expectedServiceAddresses.set(i, oldValue);
				}
				if (expectedServiceAddresses.get(i).contains("-null")) {
					oldValue = expectedServiceAddresses.get(i).replace("-null", "");
					expectedServiceAddresses.set(i, oldValue);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Replaceing null values for premises adresses unsuccessfull", e);
			e.printStackTrace();
		}
		return expectedServiceAddresses;

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify corresponding service addresses to multiple
	 *                     business account related to community user
	 * 
	 */
	public void verifyServiceAddressesForAllBusAccounts() throws IOException {

		try {

			// Click on dropdown to open it
			sf.seleU.clickOnElement(sf.comPBF.businessAccountDefaultValue);
			reportStatusPass(methodName + " Clicked on Business Account Dropdown to open it", true, false);
			sf.seleU.wait(4000);

			// Select values from dropdown one by one and verify their corresponding service
			// addresses
			for (int i = 0; i < sf.comPBF.businessAccountValues.size(); i++) {

				String businessAcc = sf.seleU.getTextFromWebElement(sf.comPBF.businessAccountValues.get(i));
				reportStatusPass(methodName + "Selecting Value " + i + " in Business Account Dropdown: " + businessAcc,
						true, true);
				sf.seleU.clickOnElement(sf.comPBF.businessAccountValuesToSelect.get(i));
				sf.seleU.wait(4000);
				sf.seleU.scrollByCoOrdinates(1);
				// Verify expected values of service addresses with actual values
				List<String> actualServiceAddresses = new ArrayList<>();
				List<String> expectedServiceAddresses = Global.dataInput.servicePremiseAddress.get(businessAcc);

				// Remove all null values from list like Remove "null-" from "null-Brampton"
				expectedServiceAddresses = replaceNullValues(expectedServiceAddresses);

				for (int j = 0; j < sf.comPBF.serviceAddressValues.size(); j++) {
					actualServiceAddresses.add(sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)));
					reportStatusPass(
							methodName + " Value " + j + " in Serivce Addresses List: "
									+ sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(j)),
							true, true);
				}

				// sort lists for comparison
				Collections.sort(expectedServiceAddresses);
				Collections.sort(actualServiceAddresses);

				// Verify expected value list is equal to actual values List

				if (expectedServiceAddresses.equals(actualServiceAddresses)) {
					reportStatusPass(methodName + " All expected service addresses for Business Account " + businessAcc
							+ " are present in the list : " + AdditionalUtilities.getAsString(actualServiceAddresses),
							true, true);
				} else {
					reportStatusFail(methodName + "  All expected service addresses for Business Account " + businessAcc
							+ " are not present in the list :: Expected Service Addresses--> "
							+ AdditionalUtilities.getAsString(expectedServiceAddresses)
							+ "  Actual Service Addresses--> "
							+ AdditionalUtilities.getAsString(actualServiceAddresses), true);
				}
				sf.seleU.clickOnElement(sf.comPBF.businessAccountDefaultValue);
				reportStatusPass(methodName + " Clicked on Business Account Dropdown to open it", true, false);
				sf.seleU.wait(2000);

			}
		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Verifying corresponding service addresses to business accounts related to community user unsuccessfull",
					e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Next button is present on Page and has value as
	 *                     'Next'
	 * 
	 */
	public void verifyNextButtonPresenceAndText() throws IOException {
		try {
			verifyFieldDisplayed("Next Button", sf.comPBF.nextButton);
			if (InputData.userLanguage.equalsIgnoreCase(InputData.userLanguageEnglish))
				verifyFieldValue("Text Value of Next Button", sf.comPBF.nextButton,
						InputData_Communities.nextButtonEnglish);
			if (InputData.userLanguage.equalsIgnoreCase(InputData.userLanguageFrench))
				verifyFieldValue("Text Value of Next Button in French", sf.comPBF.nextButton,
						InputData_Communities.nextButtonFrench);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying presence of next button is unsuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Next button Click is landing to Shopping Cart Page
	 * 
	 */
	public void verifyNextButtonNavigation() throws IOException {
		try {

			sf.seleU.clickOnElement(sf.comPBF.nextButton);
			sf.seleU.wait(10000);
			verifyFieldDisplayed("Shopping Cart Page", sf.comPBF.shoppingCartLabel);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying next button navigation to shopping cart page is unsuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open site selection page
	 * 
	 */
	public void navigateToSiteSelectionPage() throws IOException {
		try {
			sf.seleU.openURL(InputData_Communities.siteSelectionUrlForInternalUser);
			reportStatusPass(methodName + " Navigated to Site Selection PBF page : "
					+ InputData_Communities.siteSelectionUrlForInternalUser, true, true);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Navigation to site selection page is unsuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Next button is enabled *
	 */
	public void verifyNextButtonEnabled() throws IOException {
		try {
			verifyFieldEnabled("Next Button", sf.comPBF.nextButton);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying state of next button is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Next button is disabled
	 * 
	 */
	public void verifyNextButtonDisabled() throws IOException {
		try {
			verifyFieldDisabled("Next Button", sf.comPBF.nextButton);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying state of next button is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Next button is has attribute to float right
	 * 
	 */
	public void verifyNextButtonRightAlligned() throws IOException {
		try {

			if (sf.seleU.getElementAttribute(sf.comPBF.nextButton, Global.dataInput.attributeNameClass)
					.contains(InputData_Communities.floatRightAttributeValue))
				reportStatusPass(methodName + " Next Button is right aligned as expected", true, true);
			else
				reportStatusFail(methodName
						+ " Next button is not right aligned as its class doesn't has the attribute value as "
						+ InputData_Communities.floatRightAttributeValue, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying attribute of next button is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select First address from list
	 * 
	 */
	public static void selectAddress() throws IOException {
		try {
			
			if (InputData_Communities.commPBFSiteAddress!=null) {
				sf.seleU.wait(5000);
				sf.seleU.clickElementByJSE(sf.comPBF.filterInputBox);
				sf.seleU.wait(5000);
				sf.seleU.enterText(sf.comPBF.filterInputBox, InputData_Communities.commPBFSiteAddress);
				reportStatusPass(methodName + " Searched " + InputData_Communities.commPBFSiteAddress + " in filter by keywords Box", true, true);
			}
			sf.seleU.wait(5000);
			InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.comPBF.serviceAddressValues.get(0));
			
			if (sf.dataInput.env.equalsIgnoreCase("QMDEVPRO") )
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtonSquare.get(0));
			else
				sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));

			sf.seleU.wait(5000);
			reportStatusPass(methodName + " Selected first address from list : "
					+ InputData_Communities.commPBFselectedAddress, true, true);
			
			sf.seleU.clickElementByJSE(sf.comPBF.nextButton);
			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting service address from list is unsuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Business Account from LIST
	 * 
	 */
	public void selectBusinessAccount() throws IOException {
		try {

			//sf.seleU.refreshPage();
			sf.seleU.wait(5000);
			sf.seleU.scrollByCoOrdinates(2);
			if (sf.seleU.isElementDisplayed(sf.comPBFShopCart.buyBusinessProductsButton))
				sf.seleU.clickElementByJSE(sf.comPBFShopCart.buyBusinessProductsButton);
			
			// Click on dropdown to open it
			sf.seleU.clickOnElement(sf.comPBF.businessAccountDefaultValue);
			reportStatusPass(methodName + " Clicked on Business Account Dropdown", true, false);
			sf.seleU.wait(5000);
			
			boolean foundAcc = false;
			//Select business account
			if (sf.comPBF.businessAccountValues.size() >= 1) {
				for (int i = 0; i < sf.comPBF.businessAccountValues.size(); i++) {
					if (sf.seleU.getTextFromWebElement(sf.comPBF.businessAccountValues.get(i)).equalsIgnoreCase(sf.dataInput.businessAccountName)) {
						sf.seleU.clickOnElement(sf.comPBF.businessAccountValues.get(i));
						foundAcc = true;
						reportStatusPass(methodName + " Value " + i + " in Business Account Dropdown is selected: "
								+ sf.dataInput.businessAccountName, true, true);
						break;
					}
				}
			}

			if (!foundAcc)
				reportStatusFail(methodName + " No such account found in dropdown to select : " +  sf.dataInput.businessAccountName, true);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Selecting service address from list is unsuccesfull", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Fill Random string in Filter by keywords input box
	 * 
	 */
	public void searchRandomAddress() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.comPBF.filterInputBox);
			sf.seleU.wait(5000);
			sf.seleU.enterText(sf.comPBF.filterInputBox, AdditionalUtilities.generateRandomCharacters(5));
			reportStatusPass(methodName + " Searched random string in filter by keywords Box", true, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Searching random string in filter by keywords is unsuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify no sites found message
	 * 
	 */
	@SuppressWarnings("static-access")
	public void verifyNoSitesFoundMessage() throws IOException {
		try {
			if (sf.seleU.isElementDisplayed(sf.comPBF.noSiteFoundMessage)) {
				String actualMessage = sf.seleU.getTextFromWebElement(sf.comPBF.noSiteFoundMessage);
				if (InputData.userLanguage.equalsIgnoreCase(InputData.userLanguageEnglish)) {

					if (actualMessage.equals(InputData_Communities.noSiteFoundMessageEnglish))
						reportStatusPass(methodName + " No Sites found messgae is displayed properly :"
								+ InputData_Communities.noSiteFoundMessageEnglish, true, true);
					else
						reportStatusFail(methodName
								+ "No Sites found Message is displayed but text is not as expected, Expected Message : "
								+ InputData_Communities.noSiteFoundMessageEnglish + ", Actual Message : "
								+ actualMessage, true);

				}
				if (InputData.userLanguage.equalsIgnoreCase(InputData.userLanguageFrench))

					if (actualMessage.equals(InputData_Communities.noSiteFoundMessageFrench))
						reportStatusPass(methodName + " No Sites found messgae is displayed properly :"
								+ InputData_Communities.noSiteFoundMessageFrench, true, true);
					else
						reportStatusFail(methodName
								+ "No Sites found Message is displayed but text is not as expected, Expected Message : "
								+ InputData_Communities.noSiteFoundMessageFrench + ", Actual Message : "
								+ actualMessage, true);
			} else {
				reportStatusFail(methodName + "No Sites found Message is not displayed", true);
			}
			//Clear search box
			//sf.seleU.clearText(sf.comPBF.filterInputBox);
			for (int i=0; i <= AdditionalUtilities.generateRandomCharacters(5).length() ; i++) {
			sf.comPBF.filterInputBox.sendKeys(Keys.BACK_SPACE);
			}
			sf.seleU.wait(5000);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying no site found message is unsuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify count for service addresses
	 * 
	 */
	
	public void verifyTotalServiceAddressCount() throws IOException {
		try {
			
			//verifyFieldValue("Total Service Addresses", sf.comPBF.totalServiceAddresses , InputData_Communities.commPBFTotalServiceAddresses + " Total");
			String totalServices = sf.seleU.getTextFromWebElement(sf.comPBF.totalServiceAddresses);
				reportStatusPass(methodName + "Total Service Addresses: " + totalServices, true, true);
			
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Number of records(Total) in Table is unsuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Table Sorting
	 */
	
	public static void verifySortingOnTable() throws IOException {
		try {
			
			if ((sf.seleU.getElementAttribute(sf.comPBF.streetAddressHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by Street Address is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by Street Address is not working", true);
			

			if ((sf.seleU.getElementAttribute(sf.comPBF.cityHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by City is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by City is not working", true);
			

			if ((sf.seleU.getElementAttribute(sf.comPBF.provinceHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by Province is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by Province is not working", true);
			

			if ((sf.seleU.getElementAttribute(sf.comPBF.postalCodeHeaderButton, InputData_Communities.dataSortableAttributeName)).equalsIgnoreCase("true"))
				reportStatusPass(methodName + "Sorting by Postal Code is doable", true, true);
			else
				reportStatusFail(methodName + "Sorting by Postal Code is not working", true);
						
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Sorting of Table is unsuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Table Pagination
	 * 
	 */
	
	public static void verifyPaginationOnTable() throws IOException {
		try {
			int i=2;
			if (InputData_Communities.commPBFTotalServiceAddresses>5
					|| InputData_Communities.commPBFTotalServiceAddresses==0 
					&& sf.seleU.isElementPresent(sf.comPBF.nextPageButton)) {
				while (isFieldEnabled("Next Page Button", sf.comPBF.nextPageButton)) {
					sf.seleU.clickOnElement(sf.comPBF.nextPageButtonParent);
					reportStatusPass(methodName + "Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
					i++;
				}
				while (isFieldEnabled("Previous Page Button", sf.comPBF.previousPageButton)) {
					sf.seleU.clickOnElement(sf.comPBF.previousPageButtonParent);
					reportStatusPass(methodName + "Clicked on Previous page in service Addresses Table, Current Page : " + (i-1) , true, true);
					i--;
				}
			}
			else {
				reportStatusPass(methodName + "Pagination is not present service Addresses Table as The total addresses present are less than or equla to 5", true, true);
				
			}
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Pagination of Table is unsuccesfull", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Change User language to French
	 * 
	 * @throws IOException
	 */
	public void changeLanguageToFrench() throws IOException {

		try {

			// Open User Settings
			sf.seleU.clickElementByJSE(sf.comPBF.frenchLangButton);
			sf.seleU.hardwait(2000);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Changed language to French", true, true);

			InputData.userLanguage = InputData.userLanguageFrench;

		} catch (Throwable e) {
			reportStatusFail("Error in changing user language to French", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 		Click on Add new Site Page
	 * 
	 * @throws IOException
	 */
	public static void addNewSite() throws IOException {

		try {

			// Open User Settings
			sf.seleU.clickElementByJSE(sf.comPBF.addNewSiteButton);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Clicked on Add new SIte button", true, true);

		} catch (Throwable e) {
			reportStatusFail("Error in clicking on Add New SIte", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Change User language to English
	 * 
	 * @throws IOException
	 */
	public void changeLanguageToEnglish() throws IOException {

		try {

			// Open User Settings
			sf.seleU.clickElementByJSE(sf.comPBF.englishLangButton);
			sf.seleU.hardwait(2000);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Changed language to English", true, true);

			InputData.userLanguage = InputData.userLanguageEnglish;

		} catch (Throwable e) {
			reportStatusFail("Error in changing user language to English", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is disabled
	 */
	public void verifyFieldDisabled(String fieldName, WebElement element) throws IOException {
		try {

			if (!element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is disabled and not displayed", true,
						true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + " is not a disabled field, It should be a disabled", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is enabled
	 */
	public void verifyFieldEnabled(String fieldName, WebElement element) throws IOException {
		
		try {

			if (element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is enabled and displayed", true,
						true);
			} else {
				reportStatusFail(methodName + " Field " + fieldName + " is not enabled field, It should be a enabled",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Return Field is enabled/not
	 */
	public static boolean isFieldEnabled(String fieldName, WebElement element) throws IOException {
		
		boolean enabled = false;
		
		try {

			if (element.isEnabled()) {
				enabled = true;
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
		return enabled;
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
