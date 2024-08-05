package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_Home_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date 20/Jan/2020
 * 
 *         Service Account Premises Details Page
 * 
 * 
 */
public class SFDC_ServiceAccountPremisesDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	SFDC_AccountDetails_Page accDetails;
	SFDC_AccountHierarchy_Page accHi;
	SFDC_Home_Page home;
	String methodName = "SFDC_ServiceAccount_Premises@: ";

	public SFDC_ServiceAccountPremisesDetails_Page() {
		sf = new SFDC_AllPageObjects();
		accDetails = new SFDC_AccountDetails_Page();
		accHi = new SFDC_AccountHierarchy_Page();
		home = new SFDC_Home_Page();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Verify Premise Name
	 * 
	 *                     2.Verify Premise Address, city, state, country, premise
	 *                     type, premise owner,premise Status
	 * 
	 *                     3.Verify Servicable Locations
	 */
	public void validatePremises() throws Exception 
	{
		try 
		{
			// Verify Premise Name

//			sf.seleU.clickElementByJSE(sf.premises.premisesDetailsTab);
			if (sf.seleU.getTextFromWebElement(sf.premises.premisesNameValueText).length() > 0) 
			{
				reportStatusPass(methodName + " Validated  Premise Name  is " 
			   + sf.seleU.getTextFromWebElement(sf.premises.premisesNameValueText), true, true);
			} 
			else 
			{
				reportStatusFail("Premise Name is not populated", true);
			}
			sf.seleU.hardwait(3000);

			// Verify Premise Address, city, state, country, premise type, premise owner, premise Status
			verifyFieldValue("Premises Street Address", sf.premises.premisesStreetAddressValueText, Global.dataInput.addressStreet);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises City", sf.premises.premisesCityValueText, Global.dataInput.addressCity);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises State", sf.premises.premisesStatAddressValueText, Global.dataInput.addressState);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises Postal Code", sf.premises.postalCodeValueText, Global.dataInput.addressPostalCode);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises Country", sf.premises.premisesCountryValueText, Global.dataInput.addressCountry);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises Type", sf.premises.premisesTypeValueText, Global.dataInput.servicePremiseType);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises Owner", sf.premises.premisesOwnerValueText, InputData.username);
			sf.seleU.hardwait(3000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Invalid value in premises field in service account details page", e);
			e.printStackTrace();
		}
	}
	
	public void validatePremisesRevised() throws Exception 
	{
		try 
		{
			// Verify Premise Name
			if (sf.seleU.getTextFromWebElement(sf.premises.premisesNameValueText).length() > 0) 
			{
				reportStatusPass(methodName + " Validated  Premise Name  is " 
			   + sf.seleU.getTextFromWebElement(sf.premises.premisesNameValueText), true, true);
			} 
			else 
			{
				reportStatusFail("Premise Name is not populated", true);
			}
			sf.seleU.hardwait(3000);

			// Verify Premise Address, city, state, country, premise type, premise owner, premise Status
			
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.premises.premisesStreetAddressValueText), "Street Name is not displayed");
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.premises.premisesCityValueText), "City is not displayed");
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.premises.premisesStatAddressValueText), "State is not displayed");
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.premises.postalCodeValueText), "Postal Code is not displayed");
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.premises.premisesCountryValueText), "Country is not displayed");
			sf.seleU.hardwait(3000);
			
			
//			verifyFieldValue("Premises Street Address", sf.premises.premisesStreetAddressValueText, Global.dataInput.addressStreet);
//			sf.seleU.hardwait(3000);
//			verifyFieldValue("Premises City", sf.premises.premisesCityValueText, Global.dataInput.addressCity);
//			sf.seleU.hardwait(3000);
//			verifyFieldValue("Premises State", sf.premises.premisesStatAddressValueText, Global.dataInput.addressState);
//			sf.seleU.hardwait(3000);
//			verifyFieldValue("Premises Postal Code", sf.premises.postalCodeValueText, Global.dataInput.addressPostalCode);
//			sf.seleU.hardwait(3000);
//			verifyFieldValue("Premises Country", sf.premises.premisesCountryValueText, Global.dataInput.addressCountry);
//			sf.seleU.hardwait(3000);
//			verifyFieldValue("Premises Type", sf.premises.premisesTypeValueText, Global.dataInput.servicePremiseType);
//			sf.seleU.hardwait(3000);
			verifyFieldValue("Premises Owner", sf.premises.premisesOwnerValueText, InputData.username);
			sf.seleU.hardwait(3000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Invalid value in premises field in service account details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Extract Premises details
	 */
	public void extractPremises(String busAccName) throws IOException {
		try {
			List<String> premisesForBusAcc = new ArrayList<String>();
			// Extract premise details and store in a list

			if (sf.seleU.getTextFromWebElement(sf.ad.premisesValueInput).length() > 0) {
				reportStatusPass(methodName + " Validated Premises field value as " + sf.ad.premisesValueInput.getText()
						+ " in  service account details page", true, true);

				sf.seleU.clickElementByJSE(sf.ad.premisesValueInput);

			} else {
				reportStatusFail(methodName + " Invalid value in premises field in service account details page", true);
			}

			sf.seleU.clickElementByJSE(sf.premises.premisesDetailsTab);
			if (sf.seleU.getTextFromWebElement(sf.premises.premisesNameValueText).length() > 0) {
				reportStatusPass(methodName + " Validated  Premise Name  is "
						+ sf.seleU.getTextFromWebElement(sf.premises.premisesNameValueText), true, true);
			} else {
				reportStatusFail("Premise Name is not populated", true);
			}

			if (Global.dataInput.servicePremiseAddress.containsKey(busAccName)) {
				premisesForBusAcc = Global.dataInput.servicePremiseAddress.get(busAccName);
			}

			premisesForBusAcc.add(sf.seleU.getTextFromWebElement(sf.premises.premisesApartmentValueText) + "-"
					+ sf.seleU.getTextFromWebElement(sf.premises.premisesStreetAddressValueText));

			Global.dataInput.servicePremiseAddress.put(busAccName, premisesForBusAcc);

			reportStatusPass(methodName + " Saved Premise Addresses for Business acc : " + busAccName + " are : "
					+ Global.dataInput.servicePremiseAddress.get(busAccName), true, false);
		} catch (Throwable e) {
			reportStatusFail(" Cannot extract Premise details", e);
			e.printStackTrace();
		}
	}

	/**
	 * Anukriti Chawla
	 * 
	 * @throws IOException
	 * 
	 *                     Extract Premises details for all service accounts under
	 *                     all stored business accounts
	 */
	public void extractRelatedPremisesForAllBusAcc() throws IOException {
		try {

			// Traverse through all business accounts one by one
			System.out.println("1");
			for (int i = 0; i < InputData_Communities.communitiesBusinessAccounts.size(); i++) {
				// Search the account globally
				accDetails.searchBusAccGlobalSearch(InputData_Communities.communitiesBusinessAccounts.get(i));
				Global.dataInput.serviceAccountList.clear();
				accHi.extractAllRelatedServiceAccForBusAcc();
				home.closeTabIfOpen();
				for (int j = 0; j < Global.dataInput.serviceAccountList.size(); j++) {
					accDetails.searchAccountFromGlobalSearch(Global.dataInput.serviceAccountList.get(j),
							Global.dataInput.acc_RecordType_Service);
					extractPremises(InputData_Communities.communitiesBusinessAccounts.get(i));
					home.closeTabIfOpen();
				}
			}
		} catch (Throwable e) {
			reportStatusFail(" Cannot extract Premise details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws Exception {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).replaceAll("\\s", "")
					.contains(expectedText.replaceAll("\\s", ""))) {
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
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " Validated " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
