package sfdc.pages.communities;

import java.io.IOException;
import java.util.Hashtable;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Service;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date:09/06/2020
 *
 *         Communities login page objects
 */
public class Communities_Login_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_Login_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_login@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on sign-In Link in Communities page
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToCommunities(Hashtable<String, String> dataTable) throws IOException {
		try {

			generateTokenCommunitiesAdmin();

			driver.get(InputData_Communities.communities_url);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(sf.comHome.profileImage)) {
				reportStatusPass(methodName + "User Already Logged in ", true, true);
			} else {

				// Click on sign-In Link in Communities page
				if (!sf.seleU.isElementPresent(sf.comLogin.userIdInput)) {
					sf.seleU.clickElementByJSE(sf.comLogin.signInLink);
					reportStatusPass(methodName + " Clicked on Sign On Link in Communities Landing Page", true, false);
				}

				enterCredentialsCommunities(dataTable.get("Com_Login_Email_Id"), dataTable.get("Com_pwd_Encoded"));

			}
		} catch (Throwable e) {
			reportStatusFail(methodName + "login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on sign-In Link in Communities page
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToCommunities() throws IOException {
		try {

			// generateTokenCommunitiesAdmin();

			driver.get(InputData_Communities.communities_url);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(sf.comHome.profileImage)) {
				reportStatusPass(methodName + "User Already Logged in ", true, true);
				logoutFromCommunities();
			}

			// Click on sign-In Link in Communities page

			if (!InputData.env.equals("ITDEVSTAGE")) {
				if (!sf.seleU.isElementPresent(sf.comLogin.userIdInput)) {
					sf.seleU.clickElementByJSE(sf.comLogin.signInLink);
					reportStatusPass(methodName + " Clicked on Sign On Link in Communities Landing Page", true, false);
				}

				enterCredentialsCommunities(InputData_Communities.communities_userid,
						InputData_Communities.communities_password);
			} else {
				if (sf.seleU.isElementDisplayed(sf.login.userNameInput)) {
					enterCredentialsCommunities(InputData_Communities.communities_userid,
							InputData_Communities.communities_password);
				} else
					reportStatusFail(methodName + " Cannot Login into the application.. ", true);
			}

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on sign-In Link in Communities page
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToCommunitiesForFraudCaseSearch() throws IOException {
		try {

			// generateTokenCommunitiesAdmin();

			driver.get(InputData_Service.communities_url);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(sf.comHome.profileImage)) {
				reportStatusPass(methodName + "User Already Logged in ", true, true);
				logoutFromCommunities();
			}

			// Click on sign-In Link in Communities page

			if (!InputData.env.equals("ITDEVSTAGE")) {
				if (!sf.seleU.isElementPresent(sf.comLogin.userIdInput)) {
					sf.seleU.clickElementByJSE(sf.comLogin.signInLink);
					reportStatusPass(methodName + " Clicked on Sign On Link in Communities Landing Page", true, false);
				}
				
				enterCredentialsCommunities(InputData_Service.communities_userid,
						InputData_Service.communities_password);
			} else {
				if (sf.seleU.isElementDisplayed(sf.login.userNameInput)) {
					enterCredentialsCommunities(InputData_Service.communities_userid,
							InputData_Service.communities_password);
				} else
					reportStatusFail(methodName + " Cannot Login into the application.. ", true);
			}

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Login as admin and generate token to skip captcha while communities login
	 */
	public void generateTokenCommunitiesAdmin() {
		driver.get(InputData_Communities.communities_admin_url);
		sf.seleU.enterText(sf.comLogin.adminEmailAddressInput, InputData_Communities.communities_admin_userid);
		sf.seleU.enterText(sf.comLogin.adminPasswordInput, InputData_Communities.communities_admin_password);
		sf.seleU.clickElementByJSE(sf.comLogin.generateTokenButton);
		sf.seleU.wait(5000);
	}

	/**
	 * @param userID
	 * @param pwd
	 * @throws IOException
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 *                     Verify Login success
	 */
	public void enterCredentialsCommunities(String userID, String pwd) throws IOException {
		try {

			// Enter Credentials for communities Login
			sf.seleU.clearAndEnterText(sf.login.userNameInput, userID);

			if (sf.seleU.isElementDisplayed(sf.comLogin.continueButton)) {
				sf.seleU.clickElementByJSE(sf.comLogin.continueButton);
			}
			sf.seleU.clearAndEnterText(sf.login.passwordInput, decodeBytes(pwd));
			reportStatusPass(methodName + " Entered credentials for communities Login", true, false);

			if (!InputData.env.equals("ITDEVSTAGE") && !InputData.env.equals("WADEVQA")) {

				// Click on sign-In Button
				sf.seleU.clickElementByJSE(sf.comLogin.signInButton);
				sf.seleU.wait(10000);
			} else {
				sf.seleU.clickElementByJSE(sf.login.loginToSandBoxButton);
				reportStatusPass(methodName + " Clicked on Log In Button", true, false);
			}
			sf.seleU.wait(5000);

			// for prod captcha comes up, User needs to manually enter captcha and confirm

			if (InputData.env.equals("PROD")) {

				sf.seleU.wait(80000);
				sf.seleU.wait(60000);

				//				Scanner scan = new Scanner(System.in);
				//				System.out.print("Entered Captcha: ");
				//				String captchaDone = scan.nextLine();
				//
				//				sf.seleU.hardwait(6000);
				//				if (captchaDone.length() > 0) {
				//					reportStatusPass(methodName + " Successfully Entered Recaptcha", true, true);
				//				} else {
				//					reportStatusFail(methodName + " Error While Entering Recaptcha", true);
				//				}
			}

			//	driver.get(InputData_Communities.communities_url);
			sf.seleU.wait(5000);

			// Verify Login success
			if (sf.seleU.isElementPresent(sf.comHome.profileImage)) {
				reportStatusPass(methodName + "Login to communities successful", true, false);
			} else {
				reportStatusFail(methodName + " Unsuccessful login to communities", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + "login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Profile Image and Click on logout Link
	 * 
	 *                     Verify Logout success
	 */
	public void logoutFromCommunities() throws IOException {
		try {

			if (sf.seleU.isElementDisplayed(sf.comHome.profileImage)) {

				// Click on Profile Image and Click on logout Link
				sf.seleU.clickElementByJSE(sf.comHome.profileImage);
				sf.seleU.clickElementByJSE(sf.comHome.signoutLink);
				reportStatusPass(methodName + "Clicked on Signout Link", true, false);
				sf.seleU.wait(10000);

				//				// Verify Logout success
				//				if (sf.seleU.isElementPresent(sf.comLogin.signInLink)) {
				//					reportStatusPass(methodName + "Logout from communities successful", true, true);
				//				} else {
				//					reportStatusFail(methodName + "Logout from communities unsuccessful", true);
				//				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + "Logout from communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException // For ITdevstage Click on sign-In Link in Communities
	 *                     page
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToCommunities_ITDevStage(Hashtable<String, String> dataTable) throws IOException {
		try {
			sf.seleU.wait(4000);

			driver.get(InputData_Communities.communities_url);
			sf.seleU.wait(6000);

			if (sf.seleU.isElementPresent(sf.comHome.profileImage)) {
				reportStatusPass(methodName + "User Already Logged in ", true, true);
				logoutFromCommunities();
			} else {
				enterCredentialsForCommunityDevStage();
			}

			sf.seleU.wait(15000);
			if (sf.seleU.isElementPresent(sf.comHome.myOrderAndCasesText)) {
				reportStatusPass(methodName + "User Logged in succesfully", true, true);
			} else {
				reportStatusFail(methodName + "LogIn failed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Login as admin and generate token to skip captcha while communities login
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void enterCredentialsForCommunityDevStage() throws IOException, InterruptedException {

		sf.seleU.hardwait(5000);
		sf.seleU.enterText(sf.comLogin.userNameInputDevStage, InputData_Communities.communities_userid);
		sf.seleU.wait(1000);
		sf.seleU.enterText(sf.comLogin.passwordInputDevStage, InputData_Communities.communities_password);
		sf.seleU.wait(2000);
		sf.seleU.clickElementByJSE(sf.comLogin.loginButtonDevStage);
		sf.seleU.wait(4000);

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Profile Image and Click on logout Link
	 * 
	 *                     Verify Logout success
	 */
	public void logoutFromCommunitiesDevStage() throws IOException {
		try {
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.comHome.profileImage)) {

				// Click on Profile Image and Click on logout Link
				sf.seleU.clickElementByJSE(sf.comHome.profileImage);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.comHome.signoutLink);
				reportStatusPass(methodName + "Clicked on Signout Link", true, false);
				sf.seleU.wait(5000);

				// Verify Logout success
				if (sf.seleU.isElementPresent(sf.comLogin.signInLinkDevStage.get(0))) {
					reportStatusPass(methodName + "Logout from communities successful", true, true);
				} else {
					reportStatusFail(methodName + "Logout from communities unsuccessful", true);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + "Logout from communities Unsuccessful", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Click on sign-In Link in Communities page
	 * 
	 *                     Enter Credentials for communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToWaccCommunities() throws IOException {
		try {

			// generateTokenCommunitiesAdmin();

			driver.get(InputData_Communities.communities_url);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(sf.comHome.profileImage)) {
				reportStatusPass(methodName + "User Already Logged in ", true, true);
				logoutFromCommunities();
			}

			// Click on sign-In Link in Communities page


			if (sf.seleU.isElementDisplayed(sf.login.userNameInput)) {
				enterCredentialsCommunities(InputData_Communities.communities_userid,
						InputData_Communities.communities_password);
			} else
				reportStatusFail(methodName + " Cannot Login into the application.. ", true);

		} catch (

				Throwable e) {
			reportStatusFail(methodName + " Login to communities Unsuccessful", e);
			e.printStackTrace();
		}
	}
}
