package sfdc.pages.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import com.sfdc.data.InputData;
import com.framework.base.Global;
import com.framework.base.MyListener;

import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 09/jan/2020
 * 
 *         SFDC Create Business Account Page
 *
 */
public class SFDC_CreateBusinessAccount_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_CreateBusinessAccount_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * 1- Enter Account name
	 * 
	 * 2- Enter Legal Name
	 * 
	 * 3- Enter Phone and Number of Employees
	 * 
	 * 4- Enter parent Account
	 * 
	 * 5- Enter Billing Address
	 * 
	 * 6- Select No, I would like to create a separate service address
	 * 
	 * 7- Click on Next Button
	 * 
	 * @throws IOException
	 */
	public void enterBusinessAccountInfo(boolean sameServiceAddress) throws IOException {
		try {

			String methodName = "SFDC_Create Business Account@: ";

			// 1- Enter Account name
			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.parentAccountName);
				reportStatusPass(methodName + " Entered Business Account name as " + Global.dataInput.parentAccountName,
						true, false);
			} else {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.businessAccountName);
				reportStatusPass(
						methodName + " Entered Business Account name as " + Global.dataInput.businessAccountName, true,
						false);
			}
			// During contact creation, account name of the contact is changed which will
			// have direct
			// relationship with the contact, so to validate indirect relationship with the
			// contact we need to store
			// this current business account.
			Global.dataInput.tempBusinessAccountName = Global.dataInput.businessAccountName;
			
			// For service account creation if its not been created from business account
			// sf.dataInput.parentAccountName = sf.dataInput.businessAccountName;

			// 2- Enter Legal Name
			sf.seleU.enterText(sf.cba.legalNameInput, Global.dataInput.businessAccountLegalName);
			reportStatusPass(
					methodName + " Entered Business Account legal name as " + Global.dataInput.businessAccountLegalName,
					true, false);

			if (sf.seleU.isElementPresent(sf.cba.verticalGroupSelect)) {

				// Enter Vertical Group and Vertical
				sf.seleU.selectValueFromDropDown(sf.cba.verticalGroupSelect, Global.dataInput.busAccVerticalGroup);
				reportStatusPass(methodName + " Selected Vertical Group  as " + Global.dataInput.busAccVerticalGroup,
						true, false);
				sf.seleU.wait(3000);

				// Select Vertical
				sf.seleU.selectValueFromDropDown(sf.cba.verticalSelect, Global.dataInput.busAccVertical);
				reportStatusPass(methodName + " Selected Vertical   as " + Global.dataInput.busAccVertical, true,
						false);
				sf.seleU.wait(3000);
			}

			// 3- Enter Phone
			sf.seleU.enterText(sf.cba.enterPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered Business Account Phone Number as " + Global.dataInput.phoneNumber,
					true, false);

			// Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);
			reportStatusPass(methodName + " Entered Business Account Number of Employees as "
					+ Global.dataInput.numberOfEmployees, true, false);

			// Select Country
			// sf.seleU.clickElementByJSE(sf.cba.countrySelect);
			// Sales Scenario - When user creates new Business Account ,
			// in "Country" drop down user can see "United states" instead of "US"

//			String unitedStates = "United States";
//			String us = "US";
//			if (unitedStates.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates)))
//				reportStatusPass(methodName + " In Country drop down user can see United states instead of US as : "
//						+ sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates), true, true);
//			else if (us.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates)))
//				reportStatusFail(methodName + " In Country drop down user can see US instead of United states  as : "
//						+ sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates), true);
//
//			else
//				reportStatusFail("In Country drop down user can see different country name  ", true);
//
//			sf.seleU.wait(5000);
//
//			System.out.println(Global.dataInput.addressCountry);
			sf.seleU.selectValueFromDropDown(sf.cba.countrySelect, Global.dataInput.addressCountry);
			reportStatusPass(methodName + " Selected Country  as " + Global.dataInput.addressCountry, true, false);
			sf.seleU.wait(3000);

			// 4- Enter parent Account
			/*
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput,
			 * sf.dataInput.parentAccountName); sf.seleU.hardwait(1000);
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput, Keys.ARROW_DOWN);
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput, Keys.ENTER);
			 * reportStatusPass( methodName +
			 * " Entered Parent Account for Business Account as " +
			 * sf.dataInput.parentAccountName, true, false);
			 */

			// 5- Enter Billing Address

			if (Global.dataInput.selectCanNotFindBillingAddress.equals("Yes")) {
				createBusinessAddress();
			} else {
				sf.seleU.enterText(sf.cba.billingAddressInput, Global.dataInput.address);
				sf.seleU.wait(2000);
				sf.seleU.enterText(sf.cba.billingAddressInput, Keys.ENTER);
				sf.seleU.wait(3000);
			}
//				try {
//					if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
//						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
//					} else {
//						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
//					}
//				} catch (StaleElementReferenceException e) {
//				}
//				sf.seleU.wait(3000);
//				if (sf.seleU.isElementPresent(sf.cba.billingAddressTypeAheadOption)) {
//					try {
//						if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
//							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
//						} else {
//							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
//						}
//					} catch (StaleElementReferenceException e) {
//					}
//					sf.seleU.wait(3000);
//				}
			
				reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);

//			}
		

			// 6- Select Yes or No option, whether to create a separate service address or
			// not
			if (sameServiceAddress) {
				reportStatusPass(methodName + " Selected radio as default Yes it will be same service address' ", true,
						false);
			} else {
				sf.seleU.clickElementByJSE(sf.cba.separateServiceAddressradio);
				reportStatusPass(
						methodName + " Selected radio for 'No, I would like to create a separate service address' ",
						true, false);

			}

			// 7- Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.captureBusinessAccountDetails_nextBtn);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);

			InputData.currentAccount = Global.dataInput.businessAccountName;
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterBusinessAccountInfoRevised() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Business Account@: ";

			// Enter Account name
			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) 
			{
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.parentAccountName);
				reportStatusPass(methodName + " Entered Business Account name as " + Global.dataInput.parentAccountName, true, false);
			} 
			else 
			{
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.businessAccountName);
				reportStatusPass(methodName + " Entered Business Account name as " + Global.dataInput.businessAccountName, true, false);
			}
			sf.seleU.hardwait(3000);
			// During contact creation, account name of the contact is changed which will
			// have direct
			// relationship with the contact, so to validate indirect relationship with the
			// contact we need to store
			// this current business account.
			Global.dataInput.tempBusinessAccountName = Global.dataInput.businessAccountName;
			
			// Enter Legal Name
			sf.seleU.enterText(sf.cba.legalNameInput, Global.dataInput.businessAccountLegalName);
			reportStatusPass(methodName + " Entered Business Account legal name as " + Global.dataInput.businessAccountLegalName, true, false);
			sf.seleU.hardwait(3000);
		
			//select industry
			sf.seleU.clickOnElement(sf.cba.verticalGroupSelect);
			sf.seleU.hardwait(4000);
			sf.seleU.clickOnElement(sf.cba.financialServicesOption);
			reportStatusPass(methodName + " Selected Industry as " + Global.dataInput.busAccVerticalGroup, true, false);
			sf.seleU.hardwait(3000);
			
			//select subindustry
			sf.seleU.clickOnElement(sf.cba.verticalSelect);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.cba.bankingOption);
			reportStatusPass(methodName + " Selected Sub-Industry as " + Global.dataInput.busAccVertical, true, false);
			sf.seleU.hardwait(3000);
			
			// Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);			
			reportStatusPass(methodName + " Entered Business Account Number of Employees as " + Global.dataInput.numberOfEmployees, true, false);
			sf.seleU.hardwait(3000);
			
			// Enter Phone
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3) 
			{
			  sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			  reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			  sf.seleU.hardwait(3000);
			  sf.seleU.enterText(sf.cc.phoneInput, Keys.TAB);
			  sf.seleU.hardwait(2000);
			  j = j + 3;
			}
						
			//select country
			sf.seleU.clickOnElement(sf.cba.countrySelect);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.cba.countryCanadaOption);
			reportStatusPass(methodName + " Selected Country  as " + Global.dataInput.addressCountry, true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.enterText(sf.cba.billingAddressInput, Global.dataInput.address);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.cba.businessAddress);
			sf.seleU.hardwait(3000);
			
			// Select 'Can not find Business address checkbox'
//			sf.seleU.clickElementByJSE(sf.cba.canotFindAddressCheckbox);
//			reportStatusPass(methodName + "Selected 'Can not find business address checkbox", true, false);
//			sf.seleU.hardwait(3000);
			
			// Enter Fields for Business Address			
//			sf.seleU.enterText(sf.cba.billingAddressStreetNumber, "100");
//			sf.seleU.hardwait(3000);
//			sf.seleU.enterText(sf.cba.billingAddressStreetName, "Jamieson");
//			sf.seleU.hardwait(3000);
//			sf.seleU.enterText(sf.cba.billingAddressStreetType, "Pky");
//			sf.seleU.hardwait(3000);
//			sf.seleU.enterText(sf.cba.billingAddressCity, Global.dataInput.addressCity);
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickOnElement(sf.cba.billingAddressProvince);
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickOnElement(sf.cba.billingProvinceON);
//			sf.seleU.hardwait(3000);
//			sf.seleU.enterText(sf.cba.billingAddressProvince, Keys.TAB);
//			sf.seleU.hardwait(3000);
			
//			for(int i=0; i<=7; i++)
//			{
//				sf.seleU.enterText(sf.cba.billingAddressPostalCodeRevised, Keys.ARROW_LEFT);
//				sf.seleU.hardwait(2000);
//			}
//			sf.seleU.enterTextByJSE(sf.cba.billingAddressPostalCodeRevised, Global.dataInput.addressPostalCode);
//			sf.cba.billingAddressPostalCodeRevised.sendKeys(Global.dataInput.addressPostalCode);
//      		sf.seleU.enterText(sf.cba.billingAddressPostalCodeRevised, Global.dataInput.addressPostalCode);
//      	sf.seleU.enterText(sf.cba.billingAddressPostalCodeRevised, Global.dataInput.addressPostalCode);
//			reportStatusPass(methodName + " Entered Postal Code as : " + Global.dataInput.addressPostalCode, true, false);
//			sf.seleU.hardwait(3000);
//			reportStatusPass(methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address, true, false);
//			sf.seleU.hardwait(3000);
//			sf.seleU.enterText(sf.cba.billingAddressPostalCodeRevised, Keys.TAB);
//			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
			sf.seleU.hardwait(3000);											
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 1- Enter Account name
	 * 
	 * 2- Enter Legal Name
	 * 
	 * 3- Enter Phone and Number of Employees
	 * 
	 * 4- Click on Next Button
	 * 
	 * @throws IOException
	 */
	public void enterBusinessAccountInfoLWC() throws IOException {
		try {

			String methodName = "SFDC_Create Business AccountLWC@: ";

			// 1- Enter Account name
			sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.businessAccountName);
				reportStatusPass(
						methodName + " Entered Business Account name as " + Global.dataInput.businessAccountName, true,
						false);
			
			// 2- Enter Legal Name
//			sf.seleU.enterText(sf.cba.legalNameInput, Global.dataInput.businessAccountLegalName);
//			reportStatusPass(
//					methodName + " Entered Business Account legal name as " + Global.dataInput.businessAccountLegalName,
//					true, false);

			//3- Select Industry
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.industryClick);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.selectFinancialSer);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Selected Industry as " + "Financial Services", true, false);
			
			//4- Select Sub- Industry
			sf.seleU.clickOnElement(sf.cba.subIndustryClick);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.selectBanking);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Selected Sub-Industry as " + "Banking", true, false);
			
			// 5- Enter Phone
			
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3)
			{
			sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.cc.phoneInput, Keys.TAB);
			// sf.seleU.clickElementByJSE(sf.cba.websiteBox);
			sf.seleU.hardwait(2000);
			// sf.seleU.scrollUpByCoOrdinates();
			// sf.seleU.hardwait(2000);
			j = j + 3;
			}
						
			// 6- Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Entered Business Account Number of Employees as "
								+ Global.dataInput.numberOfEmployees, true, false);
			
			//7- By default country selected as Canada
			reportStatusPass(methodName + " Selected Country by default as " + "Canada", true, false);
			sf.seleU.wait(3000);

			// 8- Billing Address for Business Account
			sf.seleU.clearAndEnterText(sf.cba.busAddressInput, Global.dataInput.address);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.busAddressInputclick);
			sf.seleU.wait(3000);
			reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);

			// 9- Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);

			InputData.currentAccount = Global.dataInput.businessAccountName;
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 1- Enter Account name
	 * 
	 * 2- Enter Legal Name
	 * 
	 * 3- Enter Phone and Number of Employees
	 * 
	 * 4- Enter parent Account
	 * 
	 * 5- Enter Billing Address
	 * 
	 * 6- Select No, I would like to create a separate service address
	 * 
	 * 7- Click on Next Button
	 * 
	 * @throws IOException
	 */
	public void enterNewBusinessAccountInfo(boolean sameServiceAddress) throws IOException {
		try {

			String methodName = "SFDC_Create Business Account@: ";

			// 1- Enter Account name
			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.parentAccountName);
				reportStatusPass(methodName + " Entered Business Account name as " + Global.dataInput.parentAccountName,
						true, false);
			} else {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.businessAccountName);
				reportStatusPass(
						methodName + " Entered Business Account name as " + Global.dataInput.businessAccountName, true,
						false);
			}
			// During contact creation, account name of the contact is changed which will
			// have direct
			// relationship with the contact, so to validate indirect relationship with the
			// contact we need to store
			// this current business account.
			Global.dataInput.tempBusinessAccountName = Global.dataInput.businessAccountName;
			
			// For service account creation if its not been created from business account
			// sf.dataInput.parentAccountName = sf.dataInput.businessAccountName;

			// 2- Enter Legal Name
			sf.seleU.enterText(sf.cba.legalNameInput, Global.dataInput.businessAccountLegalName);
			reportStatusPass(
					methodName + " Entered Business Account legal name as " + Global.dataInput.businessAccountLegalName,
					true, false);

			if (sf.seleU.isElementPresent(sf.cba.verticalGroupSelect)) {

				// Enter Vertical Group and Vertical
				sf.seleU.selectValueFromDropDown(sf.cba.verticalGroupSelect, Global.dataInput.busAccVerticalGroup);
				reportStatusPass(methodName + " Selected Vertical Group  as " + Global.dataInput.busAccVerticalGroup,
						true, false);
				sf.seleU.wait(3000);

				// Select Vertical
				sf.seleU.selectValueFromDropDown(sf.cba.verticalSelect, Global.dataInput.busAccVertical);
				reportStatusPass(methodName + " Selected Vertical   as " + Global.dataInput.busAccVertical, true,
						false);
				sf.seleU.wait(3000);
			}

			// 3- Enter Phone
			sf.seleU.enterText(sf.cba.enterPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered Business Account Phone Number as " + Global.dataInput.phoneNumber,
					true, false);

			// Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);
			reportStatusPass(methodName + " Entered Business Account Number of Employees as "
					+ Global.dataInput.numberOfEmployees, true, false);

			// Select Country
			// sf.seleU.clickElementByJSE(sf.cba.countrySelect);
			// Sales Scenario - When user creates new Business Account ,
			// in "Country" drop down user can see "United states" instead of "US"

//			String unitedStates = "United States";
//			String us = "US";
//			if (unitedStates.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates)))
//				reportStatusPass(methodName + " In Country drop down user can see United states instead of US as : "
//						+ sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates), true, true);
//			else if (us.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates)))
//				reportStatusFail(methodName + " In Country drop down user can see US instead of United states  as : "
//						+ sf.seleU.getTextFromWebElement(sf.cba.countrySelectUnitedStates), true);
//
//			else
//				reportStatusFail("In Country drop down user can see different country name  ", true);
//
//			sf.seleU.wait(5000);
//
//			System.out.println(Global.dataInput.addressCountry);
			sf.seleU.selectValueFromDropDown(sf.cba.countrySelect, Global.dataInput.addressCountry);
			reportStatusPass(methodName + " Selected Country  as " + Global.dataInput.addressCountry, true, false);
			sf.seleU.wait(3000);

			// 4- Enter parent Account
			/*
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput,
			 * sf.dataInput.parentAccountName); sf.seleU.hardwait(1000);
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput, Keys.ARROW_DOWN);
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput, Keys.ENTER);
			 * reportStatusPass( methodName +
			 * " Entered Parent Account for Business Account as " +
			 * sf.dataInput.parentAccountName, true, false);
			 */

			// 5- Enter Billing Address

			if (Global.dataInput.selectCanNotFindBillingAddress.equals("Yes")) {
				createBusinessAddressForChangingAddress();
			} else {
				sf.seleU.enterText(sf.cba.billingAddressInput, Global.dataInput.address);
				sf.seleU.wait(2000);
				sf.seleU.enterText(sf.cba.billingAddressInput, Keys.ENTER);
				sf.seleU.wait(3000);
			}
//				try {
//					if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
//						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
//					} else {
//						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
//					}
//				} catch (StaleElementReferenceException e) {
//				}
//				sf.seleU.wait(3000);
//				if (sf.seleU.isElementPresent(sf.cba.billingAddressTypeAheadOption)) {
//					try {
//						if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
//							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
//						} else {
//							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
//						}
//					} catch (StaleElementReferenceException e) {
//					}
//					sf.seleU.wait(3000);
//				}
			
				reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);

//			}
		

			// 6- Select Yes or No option, whether to create a separate service address or
			// not
			if (sameServiceAddress) {
				reportStatusPass(methodName + " Selected radio as default Yes it will be same service address' ", true,
						false);
			} else {
				sf.seleU.clickElementByJSE(sf.cba.separateServiceAddressradio);
				reportStatusPass(
						methodName + " Selected radio for 'No, I would like to create a separate service address' ",
						true, false);

			}

			// 7- Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.captureBusinessAccountDetails_nextBtn);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);

			InputData.currentAccount = Global.dataInput.businessAccountName;
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * 1- Enter Account name
	 * 
	 * 2- Enter Legal Name
	 * 
	 * 3- Enter Phone and Number of Employees
	 * 
	 * 4- Enter parent Account
	 * 
	 * 5- Enter Billing Address
	 * 
	 * 6- Select No, I would like to create a separate service address
	 * 
	 * 7- Click on Next Button
	 * 
	 * @throws IOException
	 */
	public void enterSecondBusinessAccountInfo(boolean sameServiceAddress) throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// 1- Enter Account name
			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.parentAccountName);
				reportStatusPass(methodName + " Entered Business Account name as " + Global.dataInput.parentAccountName,
						true, false);
			} else {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.secondBusinessAccountName);
				reportStatusPass(
						methodName + " Entered Business Account name as " + Global.dataInput.secondBusinessAccountName,
						true, false);
			}
			// During contact creation, account name of the contact is changed which will
			// have direct
			// relationship with the contact, so to validate indirect relationship with the
			// contact we need to store
			// this current business account.
			Global.dataInput.tempBusinessAccountName = Global.dataInput.secondBusinessAccountName;

			// For service account creation if its not been created from business account
			// sf.dataInput.parentAccountName = sf.dataInput.businessAccountName;

			// 2- Enter Legal Name
			sf.seleU.enterText(sf.cba.legalNameInput, Global.dataInput.businessAccountLegalName);
			reportStatusPass(
					methodName + " Entered Business Account legal name as " + Global.dataInput.businessAccountLegalName,
					true, false);

			// Enter Vertical Group and Vertical
			// if (sf.dataInput.env.equals("ITDEVSTAGE")) {
			sf.seleU.selectValueFromDropDown(sf.cba.verticalGroupSelect, Global.dataInput.busAccVerticalGroup);
			reportStatusPass(methodName + " Selected Vertical Group  as " + Global.dataInput.busAccVerticalGroup, true,
					false);
			sf.seleU.wait(3000);

			// Select Vertical
			sf.seleU.selectValueFromDropDown(sf.cba.verticalSelect, Global.dataInput.busAccVertical);
			reportStatusPass(methodName + " Selected Vertical   as " + Global.dataInput.busAccVertical, true, false);
			sf.seleU.wait(3000);
			// }

			// 3- Enter Phone
			sf.seleU.enterText(sf.cba.enterPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered Business Account Phone Number as " + Global.dataInput.phoneNumber,
					true, false);

			// Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);
			reportStatusPass(methodName + " Entered Business Account Number of Employees as "
					+ Global.dataInput.numberOfEmployees, true, false);

			// Select Country

			sf.seleU.selectValueFromDropDown(sf.cba.countrySelect, Global.dataInput.addressCountry);
			reportStatusPass(methodName + " Selected Country  as " + Global.dataInput.addressCountry, true, false);
			sf.seleU.wait(3000);

			// 4- Enter parent Account
			/*
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput,
			 * sf.dataInput.parentAccountName); sf.seleU.hardwait(1000);
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput, Keys.ARROW_DOWN);
			 * sf.seleU.enterText(sf.cba.chooseParentAccountInput, Keys.ENTER);
			 * reportStatusPass( methodName +
			 * " Entered Parent Account for Business Account as " +
			 * sf.dataInput.parentAccountName, true, false);
			 */

			// 5- Enter Billing Address

			if (Global.dataInput.selectCanNotFindBillingAddress.equals("Yes")) {
				createBusinessAddress();
			} else {
				sf.seleU.enterText(sf.cba.billingAddressInput, Global.dataInput.address);
				sf.seleU.wait(3000);
				try {
					if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
					} else {
						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
					}
				} catch (StaleElementReferenceException e) {
				}
				sf.seleU.wait(3000);
				if (sf.seleU.isElementPresent(sf.cba.billingAddressTypeAheadOption)) {
					try {
						if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
						} else {
							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
						}
					} catch (StaleElementReferenceException e) {
					}
					sf.seleU.wait(3000);
				}

				reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);

			}

			// 6- Select Yes or No option, whether to create a separate service address or
			// not
			if (sameServiceAddress) {
				reportStatusPass(methodName + " Selected radio as default Yes it will be same service address' ", true,
						false);
			} else {
				sf.seleU.clickElementByJSE(sf.cba.separateServiceAddressradio);
				reportStatusPass(
						methodName + " Selected radio for 'No, I would like to create a separate service address' ",
						true, false);

			}

			// 7- Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.captureBusinessAccountDetails_nextBtn);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);

			InputData.currentAccount = Global.dataInput.businessAccountName;
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select 'Can not find Business address checkbox'
	 * 
	 *                     Verify street, province, city and postal code mandatory
	 *                     for BusinessAddress
	 * 
	 *                     Enter Fields for Business Address
	 */
	public void createBusinessAddress() throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// Select 'Can not find Business address checkbox'
			sf.seleU.clickElementByJSE(sf.cba.canotFindAddressCheckbox);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Selected 'Can not find business address checkbox", true, false);

			// Verify street, province, city and postal code mandatory for BusinessAddress			
			verifyFieldIsRequired("Billing Street", sf.cba.billingAddressstreet);
			verifyFieldIsRequired("Billing Province", sf.cba.billingAddressProvince);
			verifyFieldIsRequired("Billing City", sf.cba.billingAddressCity);
			verifyFieldIsRequired("Billing Postal Code", sf.cba.billingAddressPostalCode);

			// Enter Fields for Business Address
			sf.seleU.enterText(sf.cba.billingAddressstreet, Global.dataInput.addressStreet);
			sf.seleU.selectValueFromDropDown(sf.cba.billingAddressProvince, Global.dataInput.addressState);
			sf.seleU.enterText(sf.cba.billingAddressCity, Global.dataInput.addressCity);
			sf.seleU.enterText(sf.cba.billingAddressPostalCode, Global.dataInput.addressPostalCode);
			// sf.seleU.clearAndEnterText(sf.cba.billingAddressCountry,
			// sf.dataInput.addressCountry);
			reportStatusPass(
					methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address, true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Address is Unsuccessful", e);
			e.printStackTrace();
		}
	}
		
	/**
	 * @throws IOException
	 * 
	 *                     Select 'Can not find Business address checkbox'
	 * 
	 *                     Verify street, province, city and postal code mandatory
	 *                     for BusinessAddress
	 * 
	 *                     Enter Fields for Business Address
	 */
	public void createBusinessAddressForChangingAddress() throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// Select 'Can not find Business address checkbox'
			sf.seleU.clickElementByJSE(sf.cba.canotFindAddressCheckbox);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + "Selected 'Can not find business address checkbox", true, false);

			// Verify street, province, city and postal code mandatory for BusinessAddress
			verifyFieldIsRequired("Billing Street", sf.cba.billingAddressstreet);
			verifyFieldIsRequired("Billing Province", sf.cba.billingAddressProvince);
			verifyFieldIsRequired("Billing City", sf.cba.billingAddressCity);
			verifyFieldIsRequired("Billing Postal Code", sf.cba.billingAddressPostalCode);

			// Enter Fields for Business Address
			sf.seleU.enterText(sf.cba.billingAddressstreet, InputData.street);
			sf.seleU.selectValueFromDropDown(sf.cba.billingAddressProvince, Global.dataInput.addressState);
			sf.seleU.enterText(sf.cba.billingAddressCity, InputData.city);
			sf.seleU.enterText(sf.cba.billingAddressPostalCode, InputData.postalcode);
			// sf.seleU.clearAndEnterText(sf.cba.billingAddressCountry,
			// sf.dataInput.addressCountry);
			reportStatusPass(
					methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address, true,
					false);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Address is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * 1- Verify No Duplicate Accounts Message, If duplicate found, Slect the option
	 * 'None the options above match what I am looking for, I would like to continue
	 * creating a new account'
	 * 
	 * 2- Click on next Button
	 * 
	 * @throws IOException
	 */
	public void verifyBusinessAccountCreated() throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// 1- Verify No Duplicate Accounts Message

			if (sf.seleU.isElementPresent(sf.cba.noDuplicateAccountMsg)) {

				if (sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
						.contains(Global.dataInput.businessAccountName)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
								.contains(Global.dataInput.cbaNoDuplicatemsg)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(1))
								.contains(Global.dataInput.cbaClickNextToCreateNewAccMsg)) {

					reportStatusPass(methodName
							+ "Buniess Account Creation Duplicate validation message has been validated successfully",
							true, true);
				} else {
					reportStatusFail("Invalid Buniess Account Creation Duplicate validation message", true);
				}
		
				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.noDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on No Duplicate Account for create buniess account page",
						true, false);

			}
			// If duplicate found, Select'None the options above match what I am looking
			// for, I would like to continue creating a new account'

			else if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg)) {

				sf.seleU.clickElementByJSE(sf.cba.selectNextActionCreateNewAccountRadio);
				reportStatusPass(
						methodName + sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)) + ",,, "
								+ sf.seleU.getTextFromWebElement(sf.cba.selectNextActionCreateNewAccountRadio),
						true, true);

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.accountDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on Duplicate Accounts for create buniess account page",
						true, false);
			}
			if (sf.seleU.isElementDisplayed(sf.cba.eSC_NoMatchesFound_nextBtn)) {
				sf.seleU.clickElementByJSE(sf.cba.eSC_NoMatchesFound_nextBtn);
				reportStatusPass(methodName + "ESC No Matches Found and Hit Next", true, true);
				sf.seleU.wait(9000);
			}
			Global.dataInput.businessAccountStatus = Global.dataInput.status_PendingApproval;
			sf.seleU.wait(15000);

			if (sf.seleU.isElementPresent(sf.cba.reviewServiceAddress_nextBtn)) {
				reviewServiceAddress();
				sf.seleU.clickElementByJSE(sf.cba.reviewServiceAddress_nextBtn);
				reportStatusPass(methodName + "Reviewed Service Address and Hit Next", true, true);
				sf.seleU.wait(9000);
			}
			
			

		} catch (Throwable e) {
			reportStatusFail(" Verifying Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	

	
	/**
	 * 1- Verify No Duplicate Accounts Message, If duplicate found, Select the option
	 * 'None the options above match what I am looking for, I would like to continue
	 * creating a new account'
	 * 
	 * 2- Click on next Button
	 * 
	 * @throws IOException
	 */
	public void verifyBusinessAccountCreatedLWC() throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// 1- Verify No Duplicate Accounts Message

			if (sf.seleU.isElementPresent(sf.cba.noDuplicateAccountMsg)) {

				if (sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
								.contains(Global.dataInput.cbaNoDuplicatemsg)) {

					reportStatusPass(methodName
							+ "Buniess Account Creation Duplicate validation message has been validated successfully",
							true, true);
				} else {
					reportStatusFail("Invalid Buniess Account Creation Duplicate validation message", true);
				}
		
				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				reportStatusPass(
						methodName + " Clicked on Next Button on No Duplicate Account for create buniess account page",
						true, false);

			}
			// If duplicate found, Select'None the options above match what I am looking
			// for, I would like to continue creating a new account'

			else if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg)) {

				sf.seleU.clickElementByJSE(sf.cba.selectNextActionCreateNewAccountRadio);
				reportStatusPass(
						methodName + sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)) + ",,, "
								+ sf.seleU.getTextFromWebElement(sf.cba.selectNextActionCreateNewAccountRadio),
						true, true);

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				reportStatusPass(
						methodName + " Clicked on Next Button on Duplicate Accounts for create buniess account page",
						true, false);
			}
			sf.seleU.wait(4000);
			if (sf.seleU.isElementDisplayed(sf.cba.escMsg)) {
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				reportStatusPass(methodName + "ESC No Matches Found and Hit Next", true, true);
				sf.seleU.wait(4000);
			}
			//ESC Validation
			if (sf.seleU.isElementDisplayed(sf.cba.externalValidation)) {
				reportStatusPass(
						"ESC- Legal Name Matches(greater than 75% ) " + sf.seleU.getTextFromWebElement(sf.cba.externalValidation) + ",,, "
								+ sf.seleU.getTextFromWebElement(sf.cba.legalNameMsg),
						true, true);
				sf.seleU.scrollByCoOrdinates(1);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.cba.escOptions);
				sf.seleU.wait(3000);
				verifyFieldValue("Legal Name", sf.cba.legalName,Global.salesData.esclegalName);
				sf.seleU.wait(3000);
				verifyFieldValue("Type", sf.cba.escType,Global.salesData.escType);
				sf.seleU.wait(3000);
				verifyFieldValue("Status", sf.cba.escStatus,Global.salesData.escStatus);
				sf.seleU.wait(3000);
				verifyFieldValue("Score", sf.cba.escScore,Global.salesData.escScrore);
				sf.seleU.wait(3000);
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				sf.seleU.wait(3000);
				reportStatusPass(methodName + "ESC Matches Found- Verification Successful ,Selected one legal name and Hit Next", true, true);
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				sf.seleU.wait(3000);
				verifyFieldValue("Legal Name Value", sf.cba.legalNameValue,Global.salesData.legalNameValue);
				sf.seleU.wait(3000);
//				verifyFieldValue("Account Status ", sf.cba.accStatus ,Global.salesData.accStatus);
//				sf.seleU.wait(3000);
			}
			Global.dataInput.businessAccountStatus = Global.dataInput.status_PendingApproval;
			sf.seleU.wait(15000);	
			

		} catch (Throwable e) 
		{

			reportStatusFail(" Verifying Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	public void verifyBusinessAccountCreatedRevised() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Buniess Account@: ";

			// Verify No Duplicate Accounts Message

			if (sf.seleU.isElementPresent(sf.cba.noDuplicateAccountMsg)) 
			{
				if (sf.seleU.getTextFromWebElement(sf.cba.businessAccountValue).contains(Global.dataInput.businessAccountName)
				&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0)).contains(Global.dataInput.cbaNoDuplicatemsg)) 
				{
					reportStatusPass(methodName + "Buniess Account Creation Duplicate validation message has been validated successfully", true, true);
				} 
				else 
				{
					reportStatusFail("Invalid Buniess Account Creation Duplicate validation message", true);
				}
				sf.seleU.hardwait(3000);
		
				// Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				sf.seleU.hardwait(3000);
			}
			// If duplicate found, Select'None the options above match what I am looking for, I would like to continue creating a new account'

			else if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg))
			{
                sf.seleU.scrollByCoOrdinates(2);
                sf.seleU.hardwait(3000);
			    sf.seleU.clickElementByJSE(sf.cba.selectNextActionCreateNewAccountRadio);
			    sf.seleU.hardwait(3000);
				
				// Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
				sf.seleU.hardwait(5000);
			}
//			if (sf.seleU.isElementDisplayed(sf.cba.eSC_NoMatchesFound_nextBtn)) 
//			{
                if(sf.seleU.isElementDisplayed(sf.cba.continueToNextStep))
              {
                sf.seleU.hardwait(2000);
			    sf.seleU.clickOnElement(sf.cba.continueToNextStep);
				sf.seleU.hardwait(4000);
              }
//			}
		} 
		catch (Throwable e)
		{

			reportStatusFail(" Verifying Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void verifyNewBusinessAccountCreated() throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// 1- Verify No Duplicate Accounts Message

			if (sf.seleU.isElementPresent(sf.cba.noDuplicateAccountMsg)) {

				if (sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
						.contains(Global.dataInput.businessAccountName)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
								.contains(Global.dataInput.cbaNoDuplicatemsg)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(1))
								.contains(Global.dataInput.cbaClickNextToCreateNewAccMsg)) {

					reportStatusPass(methodName
							+ "Buniess Account Creation Duplicate validation message has been validated successfully",
							true, true);
				} else {
					reportStatusFail("Invalid Buniess Account Creation Duplicate validation message", true);
				}

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.noDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on No Duplicate Account for create buniess account page",
						true, false);

			}

			// If duplicate found, Select'None the options above match what I am looking
			// for, I would like to continue creating a new account'

			else if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg)) {

				sf.seleU.clickElementByJSE(sf.cba.selectNextActionCreateNewAccountRadio);
				reportStatusPass(
						methodName + sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)) + ",,, "
								+ sf.seleU.getTextFromWebElement(sf.cba.selectNextActionCreateNewAccountRadio),
						true, true);

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.accountDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on Duplicate Accounts for create buniess account page",
						true, false);
			}

			Global.dataInput.businessAccountStatus = Global.dataInput.status_PendingApproval;
			sf.seleU.wait(15000);

			if (sf.seleU.isElementPresent(sf.cba.reviewServiceAddress_nextBtn)) {
				reviewNewServiceAddress();
				sf.seleU.clickElementByJSE(sf.cba.reviewServiceAddress_nextBtn);
				reportStatusPass(methodName + "Reviewed Service Address and Hit Next", true, true);
				sf.seleU.wait(9000);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verifying Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	public void verifyBusinessAccountCreatedWithoutReview() throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";
   //////div[@id='CBA_AccountDuplicate_nextBtn']
			// 1- Verify No Duplicate Accounts Message

			if (sf.seleU.isElementPresent(sf.cba.noDuplicateAccountMsg)) {

				if (sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
						.contains(Global.dataInput.businessAccountName)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
								.contains(Global.dataInput.cbaNoDuplicatemsg)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(1))
								.contains(Global.dataInput.cbaClickNextToCreateNewAccMsg)) {

					reportStatusPass(methodName
							+ "Buniess Account Creation Duplicate validation message has been validated successfully",
							true, true);
				} else {
					reportStatusFail("Invalid Buniess Account Creation Duplicate validation message", true);
				}

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.noDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on No Duplicate Account for create buniess account page",
						true, false);

			}

			// If duplicate found, Select'None the options above match what I am looking
			// for, I would like to continue creating a new account'

			else if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg)) {

				sf.seleU.clickElementByJSE(sf.cba.selectNextActionCreateNewAccountRadio);
				reportStatusPass(
						methodName + sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)) + ",,, "
								+ sf.seleU.getTextFromWebElement(sf.cba.selectNextActionCreateNewAccountRadio),
						true, true);

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.accountDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on Duplicate Accounts for create buniess account page",
						true, false);
			}

			Global.dataInput.businessAccountStatus = Global.dataInput.status_PendingApproval;
			sf.seleU.wait(15000);

			if (sf.seleU.isElementPresent(sf.cba.reviewServiceAddress_nextBtn)) {
				// reviewServiceAddress();
				sf.seleU.clickElementByJSE(sf.cba.reviewServiceAddress_nextBtn);
				// reportStatusPass(methodName + "Reviewed Service Address and Hit Next", true,
				// true);
				sf.seleU.wait(9000);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verifying Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * For second business account 1- Verify No Duplicate Accounts Message, If
	 * duplicate found, Slect the option 'None the options above match what I am
	 * looking for, I would like to continue creating a new account'
	 * 
	 * 2- Click on next Button
	 * 
	 * @throws IOException
	 */
	public void verifyBusinessAccountCreatedWithParameter(String account) throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			// 1- Verify No Duplicate Accounts Message

			if (sf.seleU.isElementPresent(sf.cba.noDuplicateAccountMsg)) {

				if (sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0)).contains(account)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(0))
								.contains(Global.dataInput.cbaNoDuplicatemsg)
						&& sf.seleU.getTextFromWebElement(sf.cba.noDuplicateAccountMsg.get(1))
								.contains(Global.dataInput.cbaClickNextToCreateNewAccMsg)) {

					reportStatusPass(methodName
							+ "Buniess Account Creation Duplicate validation message has been validated successfully",
							true, true);
				} else {
					reportStatusFail("Invalid Buniess Account Creation Duplicate validation message", true);
				}

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.noDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on No Duplicate Account for create buniess account page",
						true, false);

			}

			// If duplicate found, Select'None the options above match what I am looking
			// for, I would like to continue creating a new account'

			else if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg)) {

				sf.seleU.clickElementByJSE(sf.cba.selectNextActionCreateNewAccountRadio);
				reportStatusPass(
						methodName + sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)) + ",,, "
								+ sf.seleU.getTextFromWebElement(sf.cba.selectNextActionCreateNewAccountRadio),
						true, true);

				// 2- Click on next Button
				sf.seleU.clickElementByJSE(sf.cba.accountDuplicates_nextBtn);
				reportStatusPass(
						methodName + " Clicked on Next Button on Duplicate Accounts for create buniess account page",
						true, false);
			}

			Global.dataInput.businessAccountStatus = Global.dataInput.status_PendingApproval;
			sf.seleU.wait(15000);

			if (sf.seleU.isElementPresent(sf.cba.reviewServiceAddress_nextBtn)) {
				reviewServiceAddress();
				sf.seleU.clickElementByJSE(sf.cba.reviewServiceAddress_nextBtn);
				reportStatusPass(methodName + "Reviewed Service Address and Hit Next", true, true);
				sf.seleU.wait(9000);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verifying Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Dupliacte Account Message
	 * 
	 *                     Verify And Select Account
	 * 
	 * 
	 *                     Select Option "No, but I would like to create a new
	 *                     subsidiary or child within the selected account"
	 * 
	 *                     Click on next Button
	 */
	public void validateAndSelectChildAccount() throws IOException {
		try {

			String methodName = "SFDC_Create Child Business Account@: ";

			// Verify Dupliacte Account Message
			if (sf.seleU.isElementPresent(sf.cba.duplicateAccountMsg)
					&& sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0))
							.contains(Global.dataInput.parentAccountName)) {
				reportStatusPass(methodName + "Validated Duplicate Account Message "
						+ sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)), true, true);
			} else {
				reportStatusFail(methodName + "Invalid Duplicate Account message"
						+ sf.seleU.getTextFromWebElement(sf.cba.duplicateAccountMsg.get(0)), true);
			}

			// Verify And Select Account
			boolean selectAccount = false;
			for (int i = 0; i < sf.cba.duplicateTableAccountNameAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.cba.duplicateTableAccountNameAllRows.get(i))
						.equals(Global.dataInput.parentAccountName)) {
					sf.seleU.clickElementByJSE(sf.cba.duplicateTableAccountSelectCheckboxAllRows.get(i));
					selectAccount = true;
					break;
				}
			}
			if (selectAccount) {
				reportStatusPass(methodName + "Found and Selected Business Account as Parent Account : "
						+ Global.dataInput.parentAccountName, true, true);
			} else {
				sf.seleU.clickElementByJSE(sf.cba.duplicateTableAccountSelectCheckboxAllRows.get(0));
				Global.dataInput.parentAccountName = sf.seleU
						.getTextFromWebElement(sf.cba.duplicateTableAccountNameAllRows.get(0));
				reportStatusPass(methodName
						+ "No such account found to be selected as parent account. Selected the first one i.e."
						+ Global.dataInput.parentAccountName, true, true);
			}

			// Select Option "No, but I would like to create a new subsidiary or child
			// within the selected account"
			sf.seleU.clickElementByJSE(sf.cba.selectNextActionChildWithinAccountRadio);
			reportStatusPass(methodName
					+ "Selected option 'No, but I would like to create a new subsidiary or child within the selected account'",
					true, false);

			// Click on next Button
			sf.seleU.clickElementByJSE(sf.cba.accountDuplicates_nextBtn);
			reportStatusPass(
					methodName + " Clicked on Next Button on Duplicate Accounts for create buniess account page", true,
					false);

			Global.dataInput.businessAccountStatus = Global.dataInput.bA_Status_Prospect;
			sf.seleU.wait(9000);

			sf.seleU.clickElementByJSE(sf.cba.nextBtn);

		} catch (Throwable e) {
			reportStatusFail(" Verifying Child Business Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select 'Can not find Business address checkbox'
	 * 
	 *                     Verify street, province, city and postal code mandatory
	 *                     for BusinessAddress
	 * 
	 *                     Enter Fields for Business Address
	 */
	public void reviewServiceAddress() throws IOException {
		try {

			String methodName = "SFDC_Review Service Address@: ";

			// Verify street, province, city and postal code mandatory for BusinessAddress
			// verifyFieldIsRequired("Review Service Address Header",
			// sf.cba.reviewServiceAddressHeaderText);
			sf.seleU.wait(2000);

			// Enter Shipping Address Province (if not autopopulated )

			// Enter Shipping Address City (if not autopopulated ) and if it's populating
			// then verify the street with the actual data
//			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_StreetText).length() == 0) {
//				sf.seleU.clearAndEnterText((sf.cba.reviewServiceAddress_StreetText), Global.dataInput.addressStreet);
//			} else {
//				verifyFieldValue("Street", sf.cba.reviewServiceAddress_StreetText, Global.dataInput.addressStreet);
//			}
			sf.seleU.hardwait(2000);

			// Enter Province City (if not autopopulated ) and if it's populating then
			// verify the street with the actual data
			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_ProvinceCity).length() == 0) {
				sf.seleU.enterText((sf.cba.reviewServiceAddress_ProvinceCity), Global.dataInput.addressCity);

			} else {
				verifyFieldValue("City", sf.cba.reviewServiceAddress_ProvinceCity, Global.dataInput.addressCity);
			}
			sf.seleU.hardwait(2000);

			// Enter Postal Code (if not autopopulated ) and if it's populating then verify
			// the street with the actual data
			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_PostalCode).length() == 0) {
				sf.seleU.enterText((sf.cba.reviewServiceAddress_PostalCode), Global.dataInput.addressPostalCode);
			} else {
				verifyFieldValue("Postal Code", sf.cba.reviewServiceAddress_PostalCode,
						Global.dataInput.addressPostalCode);
			}
			sf.seleU.hardwait(2000);

			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_Country).length() == 0) {
				sf.seleU.enterText((sf.cba.reviewServiceAddress_Country), Global.dataInput.addressCountry);
			} else {
				verifyFieldValue("Country", sf.cba.reviewServiceAddress_Country, Global.dataInput.addressCountry);
			}

			// Enter Shipping Address Province (if not autopopulated )
			sf.seleU.hardwait(2000);
			if (sf.seleU.getSelectedTextFromDropDown(sf.cba.reviewServiceAddress_ProvinceText).length() == 0) {
				sf.seleU.selectTextFromDropDown(sf.cba.reviewServiceAddress_ProvinceText,
						Global.dataInput.addressState);
			} else {
				verifyFieldValue("Province", sf.cba.reviewServiceAddress_ProvinceText, Global.dataInput.addressState);
			}

			reportStatusPass(methodName + " Reviewed Service Address Information As : ", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Address is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void reviewNewServiceAddress() throws IOException {
		try {

			String methodName = "SFDC_Review Service Address@: ";

			// Verify street, province, city and postal code mandatory for BusinessAddress
			// verifyFieldIsRequired("Review Service Address Header",
			// sf.cba.reviewServiceAddressHeaderText);
			sf.seleU.wait(2000);

			// Enter Shipping Address Province (if not autopopulated )

			// Enter Shipping Address City (if not autopopulated ) and if it's populating
			// then verify the street with the actual data
//			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_StreetText).length() == 0) {
//				sf.seleU.clearAndEnterText((sf.cba.reviewServiceAddress_StreetText), Global.dataInput.addressStreet);
//			} else {
//				verifyFieldValue("Street", sf.cba.reviewServiceAddress_StreetText, Global.dataInput.addressStreet);
//			}
			sf.seleU.hardwait(2000);

			// Enter Province City (if not autopopulated ) and if it's populating then
			// verify the street with the actual data
			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_ProvinceCity).length() == 0) {
				sf.seleU.enterText((sf.cba.reviewServiceAddress_ProvinceCity), Global.dataInput.addressCity);

			} else {
				verifyFieldValue("City", sf.cba.reviewServiceAddress_ProvinceCity, InputData.city);
			}
			sf.seleU.hardwait(2000);

			// Enter Postal Code (if not autopopulated ) and if it's populating then verify
			// the street with the actual data
			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_PostalCode).length() == 0) {
				sf.seleU.enterText((sf.cba.reviewServiceAddress_PostalCode), Global.dataInput.addressPostalCode);
			} else {
				verifyFieldValue("Postal Code", sf.cba.reviewServiceAddress_PostalCode,
						InputData.postalcode);
			}
			sf.seleU.hardwait(2000);

			if (sf.seleU.getTextFromWebElement(sf.cba.reviewServiceAddress_Country).length() == 0) {
				sf.seleU.enterText((sf.cba.reviewServiceAddress_Country), Global.dataInput.addressCountry);
			} else {
				verifyFieldValue("Country", sf.cba.reviewServiceAddress_Country, Global.dataInput.addressCountry);
			}

			// Enter Shipping Address Province (if not autopopulated )
			sf.seleU.hardwait(2000);
			if (sf.seleU.getSelectedTextFromDropDown(sf.cba.reviewServiceAddress_ProvinceText).length() == 0) {
				sf.seleU.selectTextFromDropDown(sf.cba.reviewServiceAddress_ProvinceText,
						Global.dataInput.addressState);
			} else {
				verifyFieldValue("Province", sf.cba.reviewServiceAddress_ProvinceText, Global.dataInput.addressState);
			}

			reportStatusPass(methodName + " Reviewed Service Address Information As : ", true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Address is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * Validate Industry - Sub-Industry labels on Enter business Info page
	 * 
	 * @throws IOException
	 */
	public void verifyIndustrySubIndustryLabels() throws IOException {
		try {

			verifyFieldValue("Industry Label", sf.cba.industryLabel, Global.dataInput.industryLabel);
			verifyFieldValue("Sub-Industry Label", sf.cba.subIndustryLabel, Global.dataInput.subIndustryLabel);
			sf.seleU.hardwait(2000);

			sf.seleU.switchWindow(1);

		} catch (Throwable e) {
			reportStatusFail(" Validating labels for Industry-Sub-Industry is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * Validate Industry is required - Sub-Industry is optional on Enter business
	 * Info page
	 * 
	 * @throws IOException
	 */
	public void verifyIndustrySubIndustryFieldType() throws IOException {
		try {

			verifyFieldIsRequired("Industry Field", sf.cba.verticalGroupSelect);
			verifyFieldIsRequired("Sub-Industry Field", sf.cba.verticalSelect);

		} catch (Throwable e) {
			reportStatusFail(" Validating labels for Industry-Sub-Industry is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Enter Business Account info , leave subindustry empty
	 * 
	 * @throws IOException
	 */
	public void enterBusinessAccountInfoWithoutSubIndustry(boolean sameServiceAddress) throws IOException {
		try {

			String methodName = "SFDC_Create Business Account@: ";

			// 1- Enter Account name
			if (Global.dataInput.isChildBusinessAccount.equals("Yes")) {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.parentAccountName);
				reportStatusPass(methodName + " Entered Business Account name as " + Global.dataInput.parentAccountName,
						true, false);
			} else {
				sf.seleU.enterText(sf.cba.accountNameInput, Global.dataInput.businessAccountName);
				reportStatusPass(
						methodName + " Entered Business Account name as " + Global.dataInput.businessAccountName, true,
						false);
			}
			Global.dataInput.tempBusinessAccountName = Global.dataInput.businessAccountName;

			// 2- Enter Legal Name
			sf.seleU.enterText(sf.cba.legalNameInput, Global.dataInput.businessAccountLegalName);
			reportStatusPass(
					methodName + " Entered Business Account legal name as " + Global.dataInput.businessAccountLegalName,
					true, false);

			// Enter Vertical Group
			sf.seleU.selectValueFromDropDown(sf.cba.verticalGroupSelect, Global.dataInput.busAccVerticalGroup);
			reportStatusPass(methodName + " Selected Vertical Group  as " + Global.dataInput.busAccVerticalGroup, true,
					false);
			sf.seleU.wait(3000);

			// 3- Enter Phone
			sf.seleU.enterText(sf.cba.enterPhoneInput, Global.dataInput.phoneNumber);
			reportStatusPass(methodName + " Entered Business Account Phone Number as " + Global.dataInput.phoneNumber,
					true, false);

			// Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);
			reportStatusPass(methodName + " Entered Business Account Number of Employees as "
					+ Global.dataInput.numberOfEmployees, true, false);

			// Select Country

			sf.seleU.selectValueFromDropDown(sf.cba.countrySelect, Global.dataInput.addressCountry);
			reportStatusPass(methodName + " Selected Country  as " + Global.dataInput.addressCountry, true, false);
			sf.seleU.wait(3000);

			// 5- Enter Billing Address

			if (Global.dataInput.selectCanNotFindBillingAddress.equals("Yes")) {
				createBusinessAddress();
			} else {
				sf.seleU.enterText(sf.cba.billingAddressInput, Global.dataInput.address);
				sf.seleU.wait(3000);
				try {
					if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
					} else {
						sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
					}
				} catch (StaleElementReferenceException e) {
				}
				sf.seleU.wait(3000);
				if (sf.seleU.isElementPresent(sf.cba.billingAddressTypeAheadOption)) {
					try {
						if (sf.cba.billingAddressTypeAheadOption.size() > 1) {
							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(1));
						} else {
							sf.seleU.clickElementByJSE(sf.cba.billingAddressTypeAheadOption.get(0));
						}
					} catch (StaleElementReferenceException e) {
					}
					sf.seleU.wait(3000);
				}

				reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);
			}

			// 6- Select Yes or No option, whether to create a separate service address or
			// not
			if (sameServiceAddress) {
				reportStatusPass(methodName + " Selected radio as default Yes it will be same service address' ", true,
						false);
			} else {
				sf.seleU.clickElementByJSE(sf.cba.separateServiceAddressradio);
				reportStatusPass(
						methodName + " Selected radio for 'No, I would like to create a separate service address' ",
						true, false);

			}

			// 7- Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.captureBusinessAccountDetails_nextBtn);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);

			InputData.currentAccount = Global.dataInput.businessAccountName;
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param fieldName
	 * @param element
	 * @param expectedText
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
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
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is mandatory fields
	 */
	public void verifyFieldIsRequired(String fieldName, WebElement field) throws IOException {
		try {

			String methodName = "SFDC_Create Buniess Account@: ";

			if (field.getAttribute("required").equals("true")) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is required field", true, true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + "is not a mandatory field, It should be a mandatory field",
						true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Error in validating required field", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is optional field
	 */
	public void verifyFieldIsOptional(String fieldName, WebElement field) throws IOException {
		try {

			String methodName = "SFDC_Create Business Account@: ";
			String result = field.getAttribute("required");
			if (!(result == null))
				reportStatusFail(
						methodName + " Field " + fieldName + "is a mandatory field, It should not be a mandatory field",
						true);
			else
				reportStatusPass(methodName + " Validated " + fieldName + " field is optional field", true, true);

		} catch (Throwable e) {
			reportStatusFail(" Error in validating optional field", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Business Account Information for ESC  
	 * 
	 * @throws IOException
	 */
	public void enterBusinessAccountInfoESC() throws IOException {
		try {

			String methodName = "SFDC_Create Business AccountLWC@: ";

			// 1- Enter Account name
			sf.seleU.enterText(sf.cba.accountNameInput, Global.salesData.busAccName);
				reportStatusPass(
						methodName + " Entered Business Account name as " + Global.salesData.busAccName, true,
						false);
			
			// 2- Enter Legal Name
			sf.seleU.enterText(sf.cba.legalNameInput, Global.salesData.busAccLegalName);
			reportStatusPass(
					methodName + " Entered Business Account legal name as " + Global.salesData.busAccLegalName,
					true, false);

			//3- Select Industry
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.industryClick);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.selectManufactResources);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Selected Industry as " + "Manufacturing & Resources", true, false);
			
			//4- Select Sub- Industry
			sf.seleU.clickOnElement(sf.cba.subIndustryClick);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.selectManufacturing);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Selected Sub-Industry as " + "Manufacturing", true, false);
			
			// 5- Enter Phone
			
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber, true, false);
			int j = 3;
			for (int i = 0; i <= 12; i = i + 3)
			{
			sf.seleU.enterText(sf.cc.phoneInput, Global.dataInput.phoneNumber.substring(i, j));
			reportStatusPass(methodName + " Entered contact Phone Number as : " + Global.dataInput.phoneNumber.substring(i, j), true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.cc.phoneInput, Keys.TAB);
			sf.seleU.hardwait(2000);
			j = j + 3;
			}
						
			// 6- Enter Number of Employees
			sf.seleU.enterText(sf.cba.employeeSizeInput, Global.dataInput.numberOfEmployees);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Entered Business Account Number of Employees as "
								+ Global.dataInput.numberOfEmployees, true, false);
			
			//7- By default country selected as Canada
			reportStatusPass(methodName + " Selected Country by default as " + "Canada", true, false);
			sf.seleU.wait(3000);

			// 8- Billing Address for Business Account
			sf.seleU.clearAndEnterText(sf.cba.busAddressInput, Global.dataInput.address);
			sf.seleU.wait(3000);
			sf.seleU.clickOnElement(sf.cba.busAddressInputclick);
			sf.seleU.wait(3000);
			reportStatusPass(
						methodName + " Entered Billing Address for Business Account as " + Global.dataInput.address,
						true, false);

			// 9- Click on Next Button
			sf.seleU.clickElementByJSE(sf.cba.continueToNextStep);
			reportStatusPass(methodName + " Click on Next Button on create buniess account page", true, false);

			InputData.currentAccount = Global.dataInput.businessAccountName;
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Entering details for Buniess Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
}
