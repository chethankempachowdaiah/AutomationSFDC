package com.sfdc.lib_pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.data.InputData_Service;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date: 26/Sept/2019
 * 
 *         login Page for SFDC Application
 *
 */
public class SFDC_Login_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_Login_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_login@:";
	}

	/**
	 * login to SFDC Application
	 * 
	 * @throws IOException
	 */
	public void loginToSFDC(String profile) throws IOException {

		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();

			if (sf.seleU.isElementDisplayed(sf.login.userNameInput)) {
				userLogin(profile);
			}

			else if (sf.seleU.isElementPresent(By.xpath("//span[@class='uiImage']/img[@title='User']"))
					|| sf.seleU.isElementPresent(By.xpath("//a[@id='globalHeaderNameMink']"))) {
				reportStatusPass(methodName
						+ " User is already logged into the application.. Logging out to login again as " + profile,
						true, true);
				sfdc.home.logout();
				userLogin(profile);
			}
			Thread.sleep(3000);

		} catch (Throwable e) {
			reportStatusFail("login to SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	public void loginToSFDCForB2B() throws IOException 
	{
		try 
		{
			SFDC_AllPages sfdc = new SFDC_AllPages();
			
			driver.get(InputData_Service.urlb2b);
			sf.seleU.hardwait(3000);
			
			if (sf.seleU.isElementPresent(By.xpath("//span[@class='uiImage']/img[@title='User']")))
//					|| sf.seleU.isElementPresent(By.xpath("//a[@id='globalHeaderNameMink']"))) 
			{
				reportStatusPass(methodName + " User is already logged into the application.. Logging out to login again ", true, true);
				sfdc.home.logout();
				sf.seleU.hardwait(3000);
				
	            //enter username and password
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Service.userid_b2b);
				sf.seleU.hardwait(3000);
				String decodedPassword = decodeBytes(InputData_Service.password_b2b);
				sf.seleU.clearAndEnterText(sf.login.passwordInput, decodedPassword);
				sf.seleU.hardwait(3000);
				
				// Click on login To SandBox Button
				sf.seleU.clickElementByJSE(sf.login.loginToSandBoxButton);
				reportStatusPass(methodName + " Entered user Id and password: ", true, false);
				sf.seleU.hardwait(3000);
			}			
			else
			{
				if (sf.seleU.isElementDisplayed(sf.login.userNameInput))
				{
		            //enter username and password
					sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Service.userid_b2b);
					sf.seleU.hardwait(3000);
					String decodedPassword = decodeBytes(InputData_Service.password_b2b);
					sf.seleU.clearAndEnterText(sf.login.passwordInput, decodedPassword);
					sf.seleU.hardwait(3000);
					
					// Click on login To SandBox Button
					sf.seleU.clickElementByJSE(sf.login.loginToSandBoxButton);
					reportStatusPass(methodName + " Entered user Id and password: ", true, false);
					sf.seleU.hardwait(3000);
				}
			}
		} 
		catch (Throwable e) 
		{
			reportStatusFail("login to SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	public void loginToSFDCForWebToCase(String profile) throws IOException 
	{
		try 
		{
			SFDC_AllPages sfdc = new SFDC_AllPages();
			
			driver.get(InputData.url);

			if (sf.seleU.isElementDisplayed(sf.login.userNameInput)) {
				userLogin(profile);
			}

			else if (sf.seleU.isElementPresent(By.xpath("//span[@class='uiImage']/img[@title='User']"))
					|| sf.seleU.isElementPresent(By.xpath("//a[@id='globalHeaderNameMink']"))) {
				reportStatusPass(methodName
						+ " User is already logged into the application.. Logging out to login again as " + profile,
						true, true);
				sfdc.home.logout();
				userLogin(profile);
			}
			Thread.sleep(3000);

		} catch (Throwable e) {
			reportStatusFail("login to SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * login to SFDC Application
	 * 
	 * @throws IOException
	 */
	public void loginToSFDCInNewTab(String profile) throws IOException {

		try {

			sf.seleU.openNewTab();
			sf.seleU.switchWindow(2);
			navigateURL();
			reportStatusPass(methodName + "Logging in to SF in new Tab", true, true);
			loginToSFDC(profile);

		} catch (Throwable e) {
			reportStatusFail("login to SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param profile
	 * @throws IOException
	 * 
	 *             Enter the username (Based on the profile) and password
	 * 
	 *             Click on login To SandBox Button
	 */
	public void userLogin(String profile) throws IOException {

		try {

			if (!sf.seleU.isElementPresent(sf.login.userNameInput)) {
				driver.get(InputData.url);
				sf.seleU.wait(5000);
			}
			// Enter the username (Based on the profile) and password
			if (profile.equals("AE")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_ae);
			} else if (profile.equals("Delivery")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_delivery);
			} else if (profile.equals("Ops Manager")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_opsmanager);
			} else if (profile.equals("Sales Manager")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_salesmanager);
			} else if (profile.equals("Service")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_service);
			} else if (profile.equals("Data Governance")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_datagov);
			} else if (profile.contains("itfull")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.oldOrg_username);
			} else if (profile.contains("Business Admin")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_businessAdmin);
			} else if (profile.contains("Contract Support")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_contractSupport);
			} else if (profile.contains("Contract Manager")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_contractManager);
			} else if (profile.contains("Third Party")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_thirdParty);
			} else if (profile.contains("Fraud Ops")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_fraudOps);
			} else if (profile.contains("Credit Ops")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_creditOps);
			} else if (profile.contains("NIS")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_NIS);
			} else if (profile.contains("SBEUnassigned")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_SBEUnassigned);
			} else if (profile.contains("UnassignedUser")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedUser);
			} else if (profile.contains("UnassignedWholesale")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedWholesale);
			} else if (profile.contains("PendingUnassigned")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_PendingUnassigned);
			} else if (profile.contains("UnassignedABMW")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedABMW);
			} else if (profile.contains("UnassignedAT")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedAT);
			} else if (profile.contains("UnassignedON")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedON);
			} else if (profile.contains("UnassignedGTA")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedGTA);
			} else if (profile.contains("UnassignedQC")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedQC);
			} else if (profile.contains("UnassignedBC")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_UnassignedBC);
			} else if (profile.contains("salesqa")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_salesqa);
			} else if (profile.contains("MarketingUser")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_MarketingUser);
			}else if (profile.contains("SalesExecutive")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_SalesExecutive);
			} else if (profile.contains("fraudops")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_fraudOps);
			} else if (profile.contains("creditops")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_creditOps);
			} else if (profile.contains("DealerChamp")) {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData.userid_DealerChamp);
			}
			else if(profile.contains("System Admin")) 
			{
				sf.seleU.clearAndEnterText(sf.login.userNameInput,InputData.userid_systemAdmin);
			}			
			else {
				sf.seleU.clearAndEnterText(sf.login.userNameInput, InputData_Sales.userid_ae);
			}
			String decodedPassword = "";
			if (profile.contains("itfull")) {
				decodedPassword = decodeBytes(InputData.oldOrg_password);
			} else {
				decodedPassword = decodeBytes(InputData.password);
			}
			sf.seleU.clearAndEnterText(sf.login.passwordInput, decodedPassword);
			ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, methodName + "   URL: " + InputData.url
					+ DateTimeUtilities.currentSystemDate("yyyy/MM/dd HH:mm:ss"));

			// Click on login To SandBox Button
			sf.seleU.clickElementByJSE(sf.login.loginToSandBoxButton);
			reportStatusPass(methodName + " Entered user Id and password for profile: " + profile, true, false);

			sf.seleU.hardwait(3000);

			// user enters Verification code if asked

			// if (sf.seleU.isElementPresent(sf.login.verifyButton)) {
			// if (sf.seleU.dataInput.userAvailable.equals("Yes")) {
			// Scanner scan = new Scanner(System.in);
			// System.out.print("Enter Verification Code: ");
			// String verificationCode = scan.nextLine();
			//
			// sf.seleU.clearAndEnterText(sf.login.verificationCodeInput, verificationCode);
			// sf.seleU.clickElementByJSE(sf.login.verifyButton);
			// sf.seleU.hardwait(6000);
			// reportStatusPass(methodName + " Successfully Entered verification code for
			// profile :" + profile,
			// true, false);
			// } else {
			// reportStatusFail(
			// "login to SFDC Application demands Verification Code, no user/GUI is availble
			// to enter the Code",
			// true);
			// }
			// }


			// temporary code to click on devops release notification banner
			//clickOnFinishButton();


			// verify if login successful
			if (sf.seleU.isElementPresent(By.xpath("//span[@class='uiImage']/img[@title='User']"))
					|| sf.seleU.isElementPresent(By.xpath("//a[@id='globalHeaderNameMink']/span"))) {
				reportStatusPass(methodName + " Login to SFDC Application is Successful as " + profile, true, true);
			} else {
				// reportStatusFail("login to SFDC Application is Unsuccessful", true);
			}

			sf.seleU.refreshPage();
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail("login to SFDC Application is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	//////////// Temporary code added to click on Release devops notification



	//	/**
	//	 * @param profile
	//	 * @throws IOException
	//	 * 
	//	 *             Click on Finish button for devops release Banner
	//	 *             
	//	 */
	//	public void clickOnFinishButton() throws IOException {
	//
	//		try {
	//			
	//			sf.seleU.hardwait(3000);
	//		//	WebElement finishButton = driver.findElement(By.xpath("//button[normalize-space()=\"Finish\"]"));
	//			Boolean status = sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//button[normalize-space()=\"Finish\"]")));
	//			if (status.equals(true)) {
	//				reportStatusPass(methodName +" Devops release Notification is present with Finish button ", true, true);
	//				sf.seleU.clickElementByJSE(driver.findElement(By.xpath("//button[normalize-space()=\"Finish\"]")));
	//				reportStatusPass(methodName +" Finish button is clicked ", true, true);
	//				sf.seleU.hardwait(3000);
	//			} else {
	//				reportStatusPass("User is on home page ", true, true);
	//			}
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//	}

}
