package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 09/jan/2020
 * 
 *         SFDC Create Business Account Page
 *
 */
public class SFDC_CreateServiceAccount_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_CreateServiceAccount_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * 
	 * 1- Enter Parent Account if needed
	 * 
	 * 2- Enter Service Account name
	 * 
	 * 3- Enter Service Account Number
	 * 
	 * 4- Enter Service Account Source
	 * 
	 * 5- Enter Service Address
	 * 
	 * 6- Enter Premise Type(Verify Premise Type pre-selected as expected)
	 * 
	 * 7- Enter Shipping Address Street
	 * 
	 * 8- Enter Shipping Address Province (if not autopopulated )
	 * 
	 * 9- Enter Shipping Address City (if not autopopulated )
	 * 
	 * 10-Enter Shipping Address Country (if not autopopulated )
	 * 
	 * 11-Enter Shipping Address Postal Code (if not autopopulated )
	 * 
	 * 
	 * 
	 * @throws IOException
	 */

	public void enterServiceAccountInfo(int saCount) throws IOException {
		try {
			String methodName = "SFDC_Create Service Account@: ";
			int saIter = saCount - 1;

			// Enter Parent Account if needed
			if (!sf.seleU.isElementDisplayed(sf.csa.parentAccount)) {

				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, sf.dataInput.parentAccountName);
				sf.seleU.hardwait(5000);
				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, Keys.ARROW_DOWN);
				sf.seleU.hardwait(4000);
				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, Keys.ENTER);
				sf.seleU.hardwait(3000);
				reportStatusPass(methodName + " Entered Parent Account for Service Account  as "
						+ sf.dataInput.parentAccountName, true, false);
			}

			// Enter Service Account name
			sf.seleU.enterText(sf.csa.serviceAccountNameInput.get(saIter), sf.dataInput.serviceAccountName);
			reportStatusPass(methodName + " Entered Service Account name as " + sf.dataInput.serviceAccountName, true, false);
			sf.seleU.hardwait(4000);

			// Enter Service Account Number
			sf.seleU.enterText(sf.csa.serviceAccountNumberInput.get(saIter), sf.dataInput.serviceAccountNumber);
			reportStatusPass(methodName + " Entered Service Account Number as " + sf.dataInput.serviceAccountNumber, true, false);
			sf.seleU.hardwait(4000);
			
			// Enter Service Account Source
			sf.seleU.enterText(sf.csa.serviceAccountSourceInput.get(saIter), sf.dataInput.serviceAccountSource);
			reportStatusPass(methodName + " Entered Service Account Source as " + sf.dataInput.serviceAccountName, true, false);
			sf.seleU.hardwait(4000);
			
			// Enter Service Address			
//			 sf.seleU.clickOnElement(sf.csa.serviceAddressInput.get(saIter));
//			 sf.seleU.hardwait(2000);
//			 sf.seleU.enterText(sf.csa.serviceAddressInput.get(saIter),
//			 sf.dataInput.serviceAddress); sf.seleU.hardwait(1000);
//			 sf.seleU.enterText(sf.csa.serviceAddressInput.get(saIter), Keys.ARROW_DOWN);
//			 sf.seleU.enterText(sf.csa.serviceAddressInput.get(saIter), Keys.ENTER);
//			 reportStatusPass( methodName + " Entered Service Address Service Account Source as " + sf.dataInput.serviceAddress, true, false);
			
			//Enter Country
			sf.seleU.selectValueFromDropDown(sf.csa.serviceCountrySelect.get(saIter), sf.dataInput.addressCountry);
			reportStatusPass(methodName + " Entered Service Address Country as " + sf.dataInput.addressCountry, true, false);
			sf.seleU.hardwait(3000);			
			sf.seleU.clickElementByJSE(sf.cba.canotFindAddressCheckbox);
			sf.seleU.hardwait(3000);
			
			// Enter Premise Type(Verify Premise Type pre-selected as expected)
			if (sf.seleU.getSelectedOptionValue(sf.csa.servicePremiseTypeInput.get(saIter)).equals(sf.dataInput.servicePremiseType)) 
			{
			reportStatusPass(methodName + " Verified that  Premise Type pre-selected as " + sf.dataInput.servicePremiseType, true, true);
			} 
			else 
			{
				reportStatusFail(" Premise-Type is not selected as expected : " + sf.dataInput.servicePremiseType, true);
				sf.seleU.selectValueFromDropDown(sf.csa.servicePremiseTypeInput.get(saIter), sf.dataInput.servicePremiseType);
				reportStatusPass(methodName + " Entered Premise Type as " + sf.dataInput.servicePremiseType, true, false);
			}
			
			// Enter Shipping Address Street
			sf.seleU.clickOnElement(sf.csa.serviceStreetInput.get(saIter));
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.csa.serviceStreetInput.get(saIter), sf.dataInput.addressStreet);
			sf.seleU.hardwait(3000);
			
			// Enter Shipping Address Province (if not autopopulated )
			if (sf.seleU.getSelectedTextFromDropDown(sf.csa.serviceProvinceInput.get(saIter)).length() == 0) 
			{
				sf.seleU.selectTextFromDropDown(sf.csa.serviceProvinceInput.get(saIter), sf.dataInput.addressState);
			}
			sf.seleU.hardwait(3000);
			
			// Enter Shipping Address City (if not autopopulated )
			if (sf.seleU.getTextFromWebElement(sf.csa.serviceCityInput.get(saIter)).length() == 0) 
			{
				sf.seleU.enterText(sf.csa.serviceCityInput.get(saIter), sf.dataInput.addressCity);
			}
			sf.seleU.hardwait(3000);
			
			// Enter Shipping Address Postal Code (if not autopopulated )
			if (sf.seleU.getTextFromWebElement(sf.csa.servicePostalCodeInput.get(saIter)).length() == 0) 
			{
				sf.seleU.enterText(sf.csa.servicePostalCodeInput.get(saIter), Keys.TAB);
				sf.seleU.hardwait(3000);
				sf.seleU.enterText(sf.csa.servicePostalCodeInput.get(saIter), sf.dataInput.addressPostalCode);
				sf.seleU.hardwait(3000);
				sf.seleU.enterText(sf.csa.servicePostalCodeInput.get(saIter), Keys.TAB);
				sf.seleU.hardwait(2000);
			}
			reportStatusPass(methodName + " Enter Shipping Address Information As : " + sf.dataInput.addressStreet, true, false);
		    sf.seleU.hardwait(4000);
		    sf.seleU.clickElementByJSE(sf.csa.cSR_CreateServiceAccount_nextBtn);
		    reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);
		    sf.seleU.hardwait(3000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterServiceAccountInfoRevised() throws IOException
	{
		String methodName = "SFDC_Create Service Account@: ";
		try
		{
			//enter site address
			sf.seleU.enterText(sf.cba.billingAddressInput, Global.dataInput.busAddress);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.cba.businessAddress);
			sf.seleU.hardwait(3000);
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.csa.searchSiteButton);
			sf.seleU.hardwait(3000);
			
			//select access type cable
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(4000);
//			sf.seleU.clickOnElement(sf.csa.accessTypeFibreNew);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeFibreNew);
			sf.seleU.hardwait(4000);
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.clickElementByJSE(sf.csa.confirmButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.csa.saveAndAddSiteButton);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "  Service Account created", true, false);
		}
		catch (Throwable e)
		{
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 1- Enter Parent Account if needed
	 * 
	 * 2- Enter Service Account name
	 * 
	 * 3- Enter Service Account Number
	 * 
	 * 4- Enter Service Account Source
	 * 
	 * 5- Enter Service Address
	 * 
	 * 6- Enter Premise Type(Verify Premise Type pre-selected as expected)
	 * 
	 * 7- Enter Shipping Address Street
	 * 
	 * 8- Enter Shipping Address Province (if not autopopulated )
	 * 
	 * 9- Enter Shipping Address City (if not autopopulated )
	 * 
	 * 10-Enter Shipping Address Country (if not autopopulated )
	 * 
	 * 11-Enter Shipping Address Postal Code (if not autopopulated )
	 * 
	 * 
	 * 
	 * @throws IOException
	 */

	public void enterServiceAccountInfoWithParameter(int saCount, String parentAccount, String serviceAccountFromGBJ)
			throws IOException {
		try {
			String methodName = "SFDC_Create Service Account@: ";
			int saIter = saCount - 1;

			// Enter Parent Account if needed
			if (!sf.seleU.isElementDisplayed(sf.csa.parentAccount)) {

				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, parentAccount);
				sf.seleU.wait(1000);
				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, Keys.ARROW_DOWN);
				sf.seleU.wait(1000);
				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, Keys.ENTER);
				sf.seleU.wait(1000);
				reportStatusPass(methodName + " Entered Parent Account for Service Account  as "
						+ sf.dataInput.parentAccountName, true, false);
			}

			sf.seleU.hardwait(2000);
			sf.seleU.wait(10000);

			if (serviceAccountFromGBJ.equals("GBJ")) {
				sf.seleU.clickElementByJSE(sf.csa.copyBusinessAccountAddressButtonDuringGBJ);
				reportStatusPass(methodName + " Copied the Service Address from Business Address During GBJ", true,
						false);
			} else {
				sf.seleU.clickElementByJSE(sf.csa.copyBusinessAccountAddressButton);
				reportStatusPass(methodName + " Copied the Service Address from Business Address", true, false);
			}
			sf.seleU.wait(10000);
			// Enter Service Account name
			sf.seleU.enterText(sf.csa.serviceAccountNameInput.get(saIter), sf.dataInput.serviceAccountName);
			reportStatusPass(methodName + " Entered Service Account name as " + sf.dataInput.serviceAccountName, true,
					false);

			// Enter Service Account Source
			sf.seleU.enterText(sf.csa.serviceAccountSourceInput.get(saIter), sf.dataInput.serviceAccountSource);
			reportStatusPass(methodName + " Entered Service Account Source as " + sf.dataInput.serviceAccountName, true,
					false);

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button
	 * 
	 */
	public void clickOnNextInCreateServiceAccount() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Service Account@: ";
			
			checkServicability_AddServiceLocations();

			// Click On Next Button
			sf.seleU.clickElementByJSE(sf.csa.checkServicabilityNextButton);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);
			sf.seleU.wait(5000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Service Location
	 * 
	 *                     Add Service Location
	 */
	public void checkServicability_AddServiceLocations() throws IOException {
		try {

			String methodName = "SFDC_Add Service Locations@: ";

			// Search Service Location
			sf.seleU.switchToFrame(sf.csa.checkServicabilityFrame);
			
//			sf.seleU.enterText(sf.csa.serviceLocationCity, sf.dataInput.addressCity);
//			reportStatusPass(methodName + "Entered the city as " + sf.dataInput.addressCity, true, false);
//			sf.seleU.enterText(sf.csa.serviceLocationCity, Keys.TAB);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeGroup);
			sf.seleU.hardwait(3000);
			
			if (!sf.dataInput.suiteFloorNumber.equalsIgnoreCase("NA"))
			{
				sf.seleU.enterText(sf.csa.serviceLocationSuiteFloor, sf.dataInput.suiteFloorNumber);
			}	
			if (sf.dataInput.serviceLocationAccessType.equalsIgnoreCase(sf.dataInput.accessTypeFibre))
			{
				sf.seleU.clickElementByJSE(sf.csa.accessTypeFibre);
				reportStatusPass(methodName + "Clicked on access Type group Fibre", true, false);
			} 
			else 
			{
				sf.seleU.clickElementByJSE(sf.csa.accessTypeCable);
			}

			// sf.seleU.clickElementByJSE(sf.csa.accessTypeFibre); // changing with cable
			reportStatusPass(methodName + " Searched and Found Service Location", true, false);

			// Add Service Location
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.csa.serviceLocationCheckBoxAll.get(0));
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.csa.addServiceLocationButton);
			reportStatusPass(methodName + " Added Service Location", true, false);

			sf.seleU.switchToDefaultContent();
//			sf.seleU.switchToFrame(sf.csa.accessbilityFrame);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Adding Service Locations for Service Account  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button in check servicability page
	 * 
	 */
	public void clickOnNextInCheckServicability() throws IOException {
		try {

			String methodName = "SFDC_Click on next button in check servicability@: ";

			// Click On Next Button
			sf.seleU.clickElementByJSE(sf.csa.checkServicabilityNextButton);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);

			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Click on next button in check servicability is Unsuccessful", e);
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button in check servicability page
	 * 
	 */
	public void clickOnNextInCheckServicabilityInGbj() throws IOException {
		try {

			String methodName = "SFDC_Click on next button in check servicability@: ";

			sf.seleU.wait(4000);
			sf.seleU.switchToFrame(sf.cq.createServiceAccountFrame);
			sf.seleU.wait(4000);
			// Click On Next Button
			sf.seleU.clickElementByJSE(sf.csa.checkServicabilityNextButton);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);

			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Click on next button in check servicability is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Verify Service Account Created Successfully
	 * 
	 *                     2- Click on next button
	 */
	public void verifyServiceAccountCreated() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Service Account@: ";

			// 1- Verify Service Account Created Successfully
			if (sf.seleU.isElementDisplayed(sf.csa.serviceAccountCreatedMsg)) 
			{
				reportStatusPass(methodName + " Service Account :" + sf.dataInput.serviceAccountName + "created successfully", true, true);
			} 
			else 
			{
				reportStatusFail(" Creating Service Account is Unsuccessful ", true);
			}
			sf.seleU.hardwait(7000);

			// 2- Click on next button
			sf.seleU.clickElementByJSE(sf.csa.cSR_AccountCreationsConfirmation_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on Service Account Confirmation page", true, false);
			sf.seleU.hardwait(5000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Creating Service Account is Unsuccessful", e);
		}
	}
	
	public void validateServiceAccountCreatedRevised() throws IOException
	{
		try
		{
			sf.seleU.hardwait(3000);
			verifyFieldValue("Parent Account", sf.ad.parentAccountValueText, Global.dataInput.businessAccountName);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Service Account Owner", sf.ad.accountOwnerValueText, InputData.username);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Service Account Status", sf.ad.statusValueTextNew, Global.dataInput.status_Active);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Service Account Record Type", sf.ad.accountRecordTypeValueTextNew, Global.dataInput.acc_RecordType_Service);
			sf.seleU.hardwait(3000);
			if (sf.seleU.getTextFromWebElement(sf.ad.premisesValueInput).length() > 0) 
			{
				reportStatusPass(" Validated Premises field value as " + sf.ad.premisesValueInput.getText()
				+ " in  service account details page", true, true);			
				sf.seleU.clickElementByJSE(sf.ad.premisesValueInputLink);
			} 
			else 
			{
				reportStatusFail(" Invalid value in premises field in service account details page", true);
			}			
		}
		catch (Throwable e)
		{
			reportStatusFail(" Validating Service Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     1-Click on Add Buttoon
	 * 
	 *                     2-Preapare data for another service account
	 */
	public void selectAddAnotherServiceAccount() throws IOException {
		try {

			String methodName = "SFDC_Create Service Account@: ";

			// Click on Add Buttoon
			sf.seleU.clickElementByJSE(sf.csa.serviceAccountAddButton);
			reportStatusPass(methodName + " Clicked on Add Button on Service Account  page", true, false);

			// Preapare data for another service account
			sf.dataInput.multipleServiceAcc_prepareSAData();

		} catch (Throwable e) {
			reportStatusFail(" Creating Another Service Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Check the checkbox Create billing account
	 * 
	 *                     2- Click on Next Button
	 */
	public void checkCreateBillingAccount() throws IOException {
		try {

			String methodName = "SFDC_Create Billing Account@: ";

			// 1- Check the checkbox Create billing account
			sf.seleU.clickElementByJSE(sf.csa.cA_CreateBillingAccountCheck);
			reportStatusPass(methodName + " Checked the checkbox Create billing account", true, true);

			// 2- Click on Next Button
			sf.seleU.clickElementByJSE(sf.csa.cA_ConfirmatioinStepOfSA_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on 'Create Billing Account ?' page ", true, true);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Checking the check box 'Create Billing Account' is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1- Verify Billing acc creation form/option is not
	 *                     diaplayed
	 */
	public void validateCreateBillingAccountAbsence() throws IOException {
		try {

			verifyFieldNotDisplayed("Billing account creation checkbox", sf.csa.cA_CreateBillingAccountCheck);
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(" Checking the check box 'Create Billing Account' is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button in Billing Account Creation
	 */
	public void noBillingAccountClickOnNext() throws IOException {
		try {

			String methodName = "SFDC_Create Billing Account@: ";

			// 2- Click on Next Button
			sf.seleU.clickElementByJSE(sf.csa.cA_ConfirmatioinStepOfSA_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on 'Create Billing Account ?' page ", true, true);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting next in billing account creation page is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Premises link to verify shipping address in the
	 *                     service account premise If we select same service address
	 *                     during business account creation then the premise
	 *                     shipping address should match with the business account
	 *                     address.
	 */
	public void clickOnPremisesLink() throws IOException {
		try {

			String methodName = "SFDC_Click On Premise Link@: ";

			sf.seleU.hardwait(2000);

			// Click On premise link
			if (sf.seleU.isElementPresent(sf.csa.premiseServiceLink)) {
				if (sf.seleU.getTextFromWebElement(sf.csa.premiseServiceLink)
						.contains(sf.dataInput.serviceAccountName)) {
					sf.seleU.clickElementByJSE(sf.csa.premiseServiceLink);
					reportStatusPass(methodName + " Clicked on Premise Link", true, false);
					validateShippingAddressOnPremise();
				}
			}

			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Clicking on the premise link is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Shipping address after clicking on the premise
	 *                     in the service account details
	 */
	public void validateShippingAddressOnPremise() throws IOException {
		String methodName = "SFDC_Validate Shipping Address On Premise@: ";

		try {

			sf.seleU.hardwait(3000);

			verifyFieldValue("Country", sf.csa.premiseCountryText, sf.dataInput.addressCountry);
			verifyFieldValue("State", sf.csa.premiseStateText, sf.dataInput.addressState);
			verifyFieldValue("City", sf.csa.premiseCityText, sf.dataInput.addressCity);
			verifyFieldValue("PostalCode", sf.csa.premisePostalCodeText, sf.dataInput.addressPostalCode);
			verifyFieldValue("Street", sf.csa.premiseStreetAddressText, sf.dataInput.addressStreet);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying shipping address on premise is unsucessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Service Location
	 * 
	 *                     Add multiple Service Location Verify the added service
	 *                     location in service account details
	 */
	public void checkServicability_AddMultipleServiceLocations() throws IOException {
		try {

			String methodName = "SFDC_Add Service Locations@: ";
			String accessType = "";
			String addedServiceLocation = "";
			String relatedServiceLocation = "";
			String addedSerLoc = "";
			String relatedSerLoc = "";

			List<String> addedServiceLocList = new ArrayList<String>();

			// Search Service Location
			sf.seleU.switchToFrame(sf.csa.checkServicabilityFrame);

			sf.seleU.enterText(sf.csa.serviceLocationCity, sf.dataInput.addressCity);
			sf.seleU.enterText(sf.csa.serviceLocationCity, Keys.TAB);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeGroup);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeCable);
			reportStatusPass(methodName + " Searched and Found Service Location", true, false);

			// Add multiple Service Location
			sf.seleU.wait(3000);
			for (int i = 0; i < 3; i++) {
				sf.seleU.clickElementByJSE(sf.csa.serviceLocationCheckBoxAll.get(i));
				sf.seleU.wait(2000);
				String[] accessTypeGroup1 = sf.seleU.getTextFromWebElement(sf.csa.premiseServiceAccessTypeGroup.get(i))
						.trim().split(";");
				accessType = sf.seleU.getTextFromWebElement(sf.csa.premiseServiceAccessType.get(i)).trim();
				for (int k = 0; k < accessTypeGroup1.length; k++) {
					addedServiceLocList.add(accessType);
					addedServiceLocList.add(accessTypeGroup1[k]);
				}
			}

			addedSerLoc = Arrays.toString(addedServiceLocList.toArray());
			addedServiceLocation = Arrays.toString(addedServiceLocList.toArray()).replaceAll("[^A-Za-z0-9]", "");

			// click on add service location
			sf.seleU.clickElementByJSE(sf.csa.addServiceLocationButton);
			reportStatusPass(methodName + " Added Service Location", true, false);

			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.csa.accessbilityFrame);

			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.csa.checkServicabilityNextButton);
			reportStatusPass(methodName + "Clicked on Next Button on check servicability in service account creation",
					true, false);
			sf.seleU.switchToDefaultContent();

			// Fetching the service location from service details which was added in the
			// check servicability
			sf.seleU.wait(2000);
			sf.seleU.waitElementToBeVisible(sf.csa.premiseServiceServiceabilityOutputText);
			relatedSerLoc = sf.seleU.getTextFromWebElement(sf.csa.premiseServiceServiceabilityOutputText);
			relatedServiceLocation = sf.seleU.getTextFromWebElement(sf.csa.premiseServiceServiceabilityOutputText)
					.trim().replaceAll("[^A-Za-z0-9]", "");

			// Sort added service location and related service location to compare
			sortStringAndCompare("Service Location is ", addedServiceLocation, relatedServiceLocation, addedSerLoc,
					relatedSerLoc);

			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Adding Service Locations for Service Account  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Service Location Add Service Location in premise
	 *                     details Verify after adding same service location
	 *                     populates in the servicability window
	 */
	public void checkServicability_AndAddMultipleServiceLocationsInPremise() throws IOException {
		try {

			String methodName = "SFDC_Add Service Locations in premise and verify with check servicability option@: ";
			// sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.csa.premiseServiceCheckServicabilityButton);
			reportStatusPass(methodName + "Clicked on check servicability location button in premise", true, false);

			sf.seleU.enterText(sf.csa.serviceLocationCity, sf.dataInput.addressCity);
			sf.seleU.enterText(sf.csa.serviceLocationCity, Keys.TAB);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeGroup);
			sf.seleU.wait(1000);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeCable);
			reportStatusPass(methodName + " Searched and Found Service Location", true, false);
			sf.seleU.wait(35000);

			// Store premise service location floor for matching
			sf.dataInput.servicePremiseFloorLocation = sf.seleU
					.getTextFromWebElement(sf.csa.premiseServiceLocationFloor.get(1)).trim();

			// Adding service location in check servicability
			for (int i = 0; i < 3; i++) {
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.csa.serviceLocationCheckBoxAll.get(i));
			}

			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.csa.addServiceLocationButton);
			reportStatusPass(methodName + " Added Service Location", true, false);

			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.csa.addServiceLocationCloseButton);
			sf.seleU.refreshPage();
			sf.seleU.hardwait(1000);

			// Click on premise check servicability button again to verify the service loc
			// floor
			sf.seleU.clickElementByJSE(sf.csa.premiseServiceCheckServicabilityButton);
			reportStatusPass(methodName + " Premise Service CheckServicabilityButton Again", true, false);
			sf.seleU.wait(35000);

			// Verify the service location floor which was added is matching after opening
			// the check servicability window
			for (int i = 0; i < sf.csa.premiseServiceLocationFloor.size(); i++) {
				if (!sf.seleU.getTextFromWebElement(sf.csa.premiseServiceLocationFloor.get(i)).trim()
						.equals(sf.dataInput.servicePremiseFloorLocation)) {

					reportStatusPass(
							methodName + "Added location is not present in the table and the current location floor is "
									+ sf.seleU.getTextFromWebElement(sf.csa.premiseServiceLocationFloor.get(i))
									+ " Previous added location floor is " + sf.dataInput.servicePremiseFloorLocation,
							true, true);
					break;
				}
			}
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.csa.addServiceLocationCloseButton);

			reportStatusPass(methodName + " close add serviceLocation window ", true, false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Adding Service Locations in premise details for Service Account  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Search Service Location
	 * 
	 *                     Add Service Location depending on the access type grouo
	 *                     ABA ON, ABA East, fibre On net
	 */
	public void checkServicability_AddServiceLocations(Hashtable<String, String> dataTable) throws IOException {
		try {

			String methodName = "SFDC_Add Service Locations based On Access Type Group@: ";

			// Search Service Location
			sf.seleU.switchToFrame(sf.csa.checkServicabilityFrame);
			sf.seleU.enterText(sf.csa.serviceLocationCity, sf.dataInput.addressCity);
			sf.seleU.enterText(sf.csa.serviceLocationCity, Keys.TAB);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.csa.accessTypeGroup);
			sf.seleU.wait(1000);

			// sf.seleU.clickElementByJSE(sf.csa.accessTypeCable);
			if (dataTable.get("ABA Promo").equals("ABA-On")) {
				sf.seleU.clickElementByJSE(sf.csa.accessTypeABA_On);
				reportStatusPass(methodName + "Clicked on access Type group ABA On", true, false);
			}

			if (dataTable.get("ABA Promo").equals("ABA-East")) {
				sf.seleU.clearText(sf.csa.serviceLocationCity);
				sf.seleU.enterText(sf.csa.serviceLocationCity, Keys.TAB);
				sf.seleU.clickElementByJSE(sf.csa.accessTypeGroup);
				sf.seleU.clickElementByJSE(sf.csa.accessTypeABA_East);
				reportStatusPass(methodName + "Clicked on access Type group ABA East", true, false);
			}

			if (dataTable.get("ABA Promo").equals("Near-Net") || dataTable.get("ABA Promo").equals("On-Net")) {
				sf.seleU.clickElementByJSE(sf.csa.accessTypeFibre);
				reportStatusPass(methodName + "Clicked on access Type group Fibre", true, false);
			}

			// Add Service Location
			sf.seleU.wait(30000);

			// for ABA-East we need to add access type as On-Net from servicability to
			// select RDI product
			if (dataTable.get("ABA Promo").equals("ABA-East") || dataTable.get("ABA Promo").equals("On-Net")) {
				int i = 0;
				while (true) {

					sf.seleU.wait(1000);
					if (sf.seleU.getTextFromWebElement(sf.csa.premiseServiceAccessType.get(i)).trim()
							.equals("ON NET")) {
						sf.seleU.clickElementByJSE(sf.csa.serviceLocationCheckBoxAll.get(i));
						reportStatusPass(methodName + "Clicked on service location check box with "
								+ "access type On Net with access type group as On Net", true, false);
						break;
					} else if (i < sf.csa.premiseServiceAccessTypeGroup.size() - 1) {
						i++;

					} else {
						sf.seleU.clickElementByJSE(sf.csa.premiseServiceAccessNextButton);
						sf.seleU.wait(2000);
						i = 0;
					}

				}
			} else {
				sf.seleU.clickElementByJSE(sf.csa.serviceLocationCheckBoxAll.get(0));
				reportStatusPass(methodName + "Clicked on service location check box", true, false);
			}

			sf.seleU.clickElementByJSE(sf.csa.addServiceLocationButton);
			reportStatusPass(methodName + " Added Service Location", true, false);

			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Adding Service Locations for Service Account  is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Use this method only for existing service account with
	 *                     conditions on access type group 1.Iterate on service
	 *                     account list table and find out the correct service
	 *                     account based on access type group (ABA On, ABA East,
	 *                     fibre on net)
	 * 
	 *                     2.Check on Service account list checkbox
	 * 
	 *                     3.Select Serviceability
	 * 
	 *                     4.Click on next button in Select Service account
	 * 
	 */
	public void createQuote_SelectCreatedServiceAccount(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";
			sf.seleU.hardwait(3000);
			// Iterate on service account list table and find out the correct service
			// account and select
			sf.seleU.switchToDefaultContent();
			// sf.seleU.switchToElementFrame(sf.cq.createQuoteSelectServiceAccountNameList);
			sf.seleU.switchToFrame(sf.cq.createServiceAccountFrame);

			int serviceAccountListSize = sf.cq.createQuoteSelectServiceAccountNameList.size();
			boolean isSASelected = false;
			int index = 0;

			// Select service account depending on the servicability
			// fetch service account index with ABA -On servicability from
			if (dataTable.get("ABA Promo").length() > 0 && dataTable.get("ABA Promo").equals("ABA-On")) {

				for (int i = 0; i < serviceAccountListSize; i++) {
					sf.seleU.hardwait(1000);
					System.out
							.println(sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityPromoABA_On.get(i)));
					if (sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityPromoABA_On.get(i)) != null) {

						reportStatusPass(methodName + " Selected Servicability for Service Account for create Quote "
								+ dataTable.get("ABA Promo"), true, true);

						index = i;
						isSASelected = true;
						break;
					}
				}
			}

			// fetch service account index with ABA -EAST servicability
			if (dataTable.get("ABA Promo").length() > 0 && dataTable.get("ABA Promo").equals("ABA-East")) {

				for (int i = 0; i < serviceAccountListSize; i++) {
					if (sf.seleU.getTextFromWebElement(sf.cq.createQuoteSelectServiceAccountNameList.get(i))
							.equals(sf.dataInput.serviceAccountName)) {

						sf.seleU.hardwait(1000);
						System.out.println(
								sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityPromoABA_East.get(i)));
						if (sf.seleU
								.getTextFromWebElement(sf.cq.createQuoteServiceAbilityPromoABA_East.get(i)) != null) {

							reportStatusPass(
									methodName + " Selected Servicability for Service Account for create Quote "
											+ dataTable.get("ABA Promo"),
									true, true);

							index = i;
							isSASelected = true;
							break;
						}
					}
				}
			}
			// fetch service account index with fibre on near net
			if (dataTable.get("Serviceability").length() > 0) {
				if (dataTable.get("ABA Promo").equals("Near-Net") || dataTable.get("ABA Promo").equals("On-Net")) {

					for (int i = 0; i < serviceAccountListSize; i++) {
						sf.seleU.hardwait(1000);
						System.out.println(
								sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityFibre_OnNearNet.get(i)));
						if (sf.seleU
								.getTextFromWebElement(sf.cq.createQuoteServiceAbilityFibre_OnNearNet.get(i)) != null
								&& sf.seleU
										.getTextFromWebElement(sf.cq.createQuoteServiceAbilityListCable.get(i)) == null
								&& sf.seleU.getTextFromWebElement(
										sf.cq.createQuoteServiceAbilityPromoABA_East.get(i)) == null
								&& sf.seleU.getTextFromWebElement(
										sf.cq.createQuoteServiceAbilityPromoABA_On.get(i)) == null) {

							reportStatusPass(
									methodName + " Selected Servicability for Service Account for create Quote "
											+ dataTable.get("ABA Promo"),
									true, true);

							index = i;
							isSASelected = true;
							break;
						}
					}
				}
			}

			// check on Service account list checkbox depending on the promo
			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.cq.createQuoteSelectServiceAccountCheckBoxList.get(index));

			if (isSASelected) {
				reportStatusPass(methodName + " Selected Service Account for Quote as " + dataTable.get("ABA Promo"),
						true, true);
			} else {
				reportStatusFail(
						methodName + " Unable to select Service Account for Quote  as " + dataTable.get("ABA Promo"),
						true);
			}

			sf.seleU.hardwait(3000);

			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Select Service Account Page", true, false);

			// sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(8000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting service account for Quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Sort the string and compare the same
	 */
	public void sortStringAndCompare(String fieldValue, String inputText, String outPutText, String inputSerLoc,
			String outputSerLoc) throws IOException {
		try {

			String methodName = "SFDC_Sort String@: ";
			// convert to character array
			char tempArrayForInput[] = inputText.toCharArray();
			char tempArrayForOutput[] = inputText.toCharArray();

			// sort tempArray
			Arrays.sort(tempArrayForInput);
			Arrays.sort(tempArrayForOutput);

			// Convert array to string
			String inputSortedString = String.valueOf(tempArrayForInput);
			String outputSortedString = String.valueOf(tempArrayForOutput);

			if (inputSortedString.trim().equals(outputSortedString.trim())) {
				// Verify Field value matches the expected result
				reportStatusPass(methodName + "Validated " + fieldValue + " is matched with actual value as"
						+ inputSerLoc + " expected value as " + outputSerLoc, true, true);
			} else {
				reportStatusFail(methodName + "Not matched", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in sort string method", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is not displayed as expected", true, true);
			} else {
				reportStatusFail(fieldName + " is displayed, It should be absent", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			if (!expectedText.equals("NA")) {
				// Verify Field value matches the expected result
				if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is " + element.getText()
							+ " And Expected One is " + expectedText, true);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button
	 * 
	 *                     Validate the details on Check serviceability Page
	 * 
	 * 
	 */
	public void clickOnNextInCreateServiceAccountValidateServiceabilityPageDetails() throws IOException {
		try {

			String methodName = "SFDC_Create Service Account@: ";

			// Click On Next Button
			sf.seleU.clickElementByJSE(sf.csa.cSR_CreateServiceAccount_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);
			sf.seleU.wait(15000);

			if (sf.seleU.isElementPresent(sf.csa.cSR_ConfirmedCreateServiceAccount_nextBtn))
				sf.seleU.clickElementByJSE(sf.csa.cSR_ConfirmedCreateServiceAccount_nextBtn);

			sf.seleU.wait(6000);
			reportStatusPass(methodName + " Clicked on Next Button which will redirect to Serviceability  page", true,
					false);

			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button
	 * 
	 *                     Check serviceability and Add location
	 * 
	 *                     Hit next.
	 * 
	 */
	public void checkServicabilityAddServiceLocationsClickNext() throws IOException {
		try {

			String methodName = "SFDC_Create Service Account@: ";
			sf.seleU.wait(2000);
			//checkServicability_AddServiceLocations();
			// Click On Next Button
			checkServicability_AddServiceLocations();
			sf.seleU.clickElementByJSE(sf.csa.checkServicabilityNextButton);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);
			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Add Serviceable Location button does exist
	 * 
	 */
	public void verifyAddServiceableLocationButtonExist() throws IOException {
		try {
			sf.seleU.switchToFrame(sf.csa.checkServicabilityFrame);
			verifyFieldDisplayed("Add Serviceable Location", sf.csa.addServiceLocationButton);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Add Serviceable Location presence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Previous button does exist
	 * 
	 */
	public void verifPaginationPreviousButtonExist() throws IOException {
		try {
			//sf.seleU.ScrolltoElement(sf.csa.premiseServiceAccessPreviousButton);
			sf.seleU.wait(3000);
			sf.seleU.scrollByCoOrdinates(300);
			verifyFieldDisplayed("Pagination Previous Button", sf.csa.premiseServiceAccessPreviousButton);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Pagination Previous Button presence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Next Button does exist
	 * 
	 */
	public void verifyPaginationNextButtonExist() throws IOException {
		try {

			verifyFieldDisplayed("PaginationNext Button", sf.csa.premiseServiceAccessNextButton);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Pagination Next Button presence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Pagination Previous button does exist
	 * 
	 */
	public void verifyPreviousButtonExist() throws IOException {
		try {
			sf.seleU.switchToDefaultContent();
			//sf.seleU.switchToFrame(sf.csa.accessbilityFrame);
			verifyFieldDisplayed("Previous Button", sf.csa.checkServicabilityPreviousButton);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Previous Button presence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Pagination Next Button does exist
	 * 
	 */
	public void verifyNextButtonExist() throws IOException {
		try {

			verifyFieldDisplayed("Next Button", sf.csa.checkServicabilityNextButton);
			sf.seleU.switchToFrame(sf.csa.checkServicabilityFrame);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Next Button presence was unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Verify Access type field
	 * 
	 */
	public void verifyAccessTypeExist() throws IOException {
		try {
			verifyFieldDisplayed("Access Type", sf.csa.accessType);

		} catch (Throwable e) {
			reportStatusFail(" Verifyin gAccess Type presence was unsuccessful", e);
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
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Next Button without checking Servicability 
	 * 
	 */
	public void clickOnNextInCreateServiceAccountWithoutChkServicibility() throws IOException {
		try {

			String methodName = "SFDC_Create Service Account@: ";

			// Click On Next Button
			sf.seleU.clickElementByJSE(sf.csa.cSR_CreateServiceAccount_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);
			sf.seleU.wait(15000);

			if(sf.seleU.isElementPresent(sf.csa.cSR_ConfirmedCreateServiceAccount_nextBtn))
				sf.seleU.clickElementByJSE(sf.csa.cSR_ConfirmedCreateServiceAccount_nextBtn);

			// Click On Next Button
			sf.seleU.clickElementByJSE(sf.csa.checkServicabilityNextButton);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);

			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void newEnterServiceAccInfo(int saCount) throws IOException {
		try {
			String methodName = "SFDC_Create Service Account@: ";
			int saIter = saCount - 1;

			// Enter Parent Account if needed
			if (!sf.seleU.isElementDisplayed(sf.csa.parentAccount)) {

				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, sf.dataInput.parentAccountName);
				sf.seleU.wait(4000);
				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, Keys.ARROW_DOWN);
				sf.seleU.wait(1000);
				sf.seleU.enterText(sf.csa.service_ChooseParentAccountInput, Keys.ENTER);
				sf.seleU.wait(1000);
				reportStatusPass(methodName + " Entered Parent Account for Service Account  as "
						+ sf.dataInput.parentAccountName, true, false);
			}

			// Enter Service Account name
			sf.seleU.enterText(sf.csa.serviceAccountNameInput.get(saIter), sf.dataInput.serviceAccountName);
			reportStatusPass(methodName + " Entered Service Account name as " + sf.dataInput.serviceAccountName, true,
					false);
			// Enter Service Account Number
			// sf.seleU.enterText(sf.csa.serviceAccountNumberInput.get(saIter),
			// sf.dataInput.serviceAccountNumber);
			// reportStatusPass(methodName + " Entered Service Account Number as " +
			// sf.dataInput.serviceAccountNumber,
			// true, false);

			// Enter Service Account Source
			sf.seleU.enterText(sf.csa.serviceAccountSourceInput.get(saIter), sf.dataInput.serviceAccountSource);
			reportStatusPass(methodName + " Entered Service Account Source as " + sf.dataInput.serviceAccountName, true,
					false);
			if (sf.seleU.isElementPresent(sf.csa.serviceCountrySelect)) {
				if (sf.seleU.getSelectedTextFromDropDown(
						sf.csa.serviceCountrySelect.get(saIter)) != sf.dataInput.addressCountry) {
					sf.seleU.selectTextFromDropDown(sf.csa.serviceCountrySelect.get(saIter),
							sf.dataInput.addressCountry);
				}
			}
				sf.seleU.enterText(sf.csa.selectSerAddress, sf.dataInput.address);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.csa.clickAdd);
				sf.seleU.wait(4000);
				reportStatusPass(methodName + " Enter Shipping Address Information As : "
						+ sf.dataInput.address, true, false);
			sf.seleU.wait(7000);
			sf.seleU.clickElementByJSE(sf.csa.cSR_CreateServiceAccount_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create Service Account page", true, false);
			sf.seleU.wait(3000);
			if(sf.seleU.isElementPresent(sf.csa.cSR_AccountCreationsConfirmation_nextBtn))
				sf.seleU.clickElementByJSE(sf.csa.cSR_AccountCreationsConfirmation_nextBtn);
			sf.seleU.wait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Service Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Add New Service Account Information
	 * 
	 */
	
	public void enterServiceAccInfoLWC() throws IOException {
		
		try {
			String methodName = "SFDC_Create Service Account@: ";	
				verifyFieldDisplayed("Add New Site Address", sf.comPBFAddSite.addNewSiteHeader);
				sf.seleU.scrollByCoOrdinates(1);
				sf.seleU.wait(2000);
				sf.seleU.clearAndEnterText(sf.csa.inputSerAddress, Global.dataInput.address);
				sf.seleU.wait(3000);
				sf.seleU.clickElementByJSE(sf.cba.busAddressInputclick);
				sf.seleU.wait(3000);
				reportStatusPass(
							methodName + " Entered Service Address for Business Account as " + Global.dataInput.address,
							true, false);
				
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.searchMySiteButton);
				sf.seleU.wait(4000);
				
		} catch (Throwable e) {
			reportStatusFail(" Filling Service Account address is unscuccesful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select one address displayed      
	 * 
	 */
	public void selectAddress() throws IOException {
		try {
			String methodName = "SFDC_Create Service Account@: ";
				//Verify Step1 collapsed and Step2 is expanded
			if (sf.seleU.isElementDisplayed(sf.csa.noResults))
			{
				sf.seleU.wait(2000);
				reportStatusPass(methodName + " Selected Service Address not have servicible location", true, true);
			}
			else
			{
				verifyFieldNotDisplayed("Step1 Details", sf.comPBFAddSite.searchAddressOpenDIV);
				verifyFieldDisplayed("Step2 Details", sf.comPBFAddSite.selectAddressOpenDIV);
				
				if (InputData_Communities.commPBFAddProductName.contains("bps")) {
//					sf.seleU.clickElementByJSE(sf.comPBF.filterInputBoxFibre);
//					sf.seleU.wait(5000);
//					sf.seleU.enterText(sf.comPBF.filterInputBoxFibre, InputData_Communities.commPBFSiteAddressStreetName);
//					reportStatusPass(methodName + " Searched " + InputData_Communities.commPBFSiteAddressStreetName + " in filter by keywords Box", true, true);
//					
//					sf.seleU.wait(5000);
//					InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.comPBFAddSite.serviceAddressValuesFibre.get(0));
					sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressFibreRadioButtons.get(1));
	
				} else {
//					sf.seleU.clickElementByJSE(sf.comPBF.filterInputBox);
//					sf.seleU.wait(5000);
//					sf.seleU.enterText(sf.comPBF.filterInputBox, InputData_Communities.commPBFSiteAddressStreetName);
//					reportStatusPass(methodName + " Searched " + InputData_Communities.commPBFSiteAddressStreetName + " in filter by keywords Box", true, true);
//					
//					sf.seleU.wait(5000);
//					InputData_Communities.commPBFselectedAddress = sf.seleU.getTextFromWebElement(sf.comPBFAddSite.serviceAddressValues.get(0));
					sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(1));
	
				}
				
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Selected first address from list : "
						+ InputData_Communities.commPBFselectedAddress, true, true);
				
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.confirmAddressButton);
				sf.seleU.wait(3000);
				sf.seleU.clickElementByJSE(sf.comPBFAddSite.saveSiteButton);
				sf.seleU.wait(2000);
			}
				
		} catch (Throwable e) {
			reportStatusFail(" Selecting Service address is unscuccesfull", e);
			e.printStackTrace();
		}
	}
	
}
