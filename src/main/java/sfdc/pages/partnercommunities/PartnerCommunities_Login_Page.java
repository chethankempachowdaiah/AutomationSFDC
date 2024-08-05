package sfdc.pages.partnercommunities;

import java.io.IOException;
import java.util.Hashtable;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:14/05/2021
 *
 *         Partner Communities login page
 */
public class PartnerCommunities_Login_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public PartnerCommunities_Login_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "PartnerCommunities_login@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                    Enter Credentials for partner communities Login
	 * 
	 *                     Click on sign-In Button
	 * 
	 */
	public void loginToPartnerCommunities(String userid) throws IOException {
		try {

			driver.get(sf.partnerCommData.partnerCommunities_url);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(sf.partnerCommHome.profileIcon)) {
				reportStatusPass(methodName + " User Already Logged in ", true, true);
			} else {
				if (userid.contains("dealer"))
					enterCredentialsPartnerCommunities(userid, sf.partnerCommData.partnerCommunities_pwd_dealer);
				else
					enterCredentialsPartnerCommunities(userid, sf.partnerCommData.partnerCommunities_pwd_var);
				
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Login to partner communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

	
	/**
	 * @param userID
	 * @param pwd
	 * @throws IOException
	 * 
	 *                     Enter Credentials for partner communities Login
	 * par
	 *                     Click on Log-In Button
	 * 
	 *                     Verify Login success
	 */
	public void enterCredentialsPartnerCommunities(String userID, String pwd) throws IOException {
		try {

			// Enter Credentials for partner communities Login
			sf.seleU.clearAndEnterText(sf.partnerCommLogin.userNameInput, userID);
			sf.seleU.clearAndEnterText(sf.partnerCommLogin.passwordInput, decodeBytes(pwd));
			reportStatusPass(methodName + " Entered credentials for partner communities Login", true, false);

			sf.seleU.clickOnElement(sf.partnerCommLogin.loginButton);
			reportStatusPass(methodName + " Clicked on Log In Button", true, false);
			
			sf.seleU.wait(10000);
			// Verify Login success
			if (sf.seleU.isElementPresent(sf.partnerCommHome.profileIcon )) {
				reportStatusPass(methodName + " Login to partner communities successful", true, false);
			} else {
				reportStatusFail(methodName + " Unsuccessful login to partner communities", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Login to partner communities Unsuccessful", e);
			e.printStackTrace();
		}
	}

}
