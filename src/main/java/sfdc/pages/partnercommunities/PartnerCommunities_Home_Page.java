package sfdc.pages.partnercommunities;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:14/05/2021
 * 
 *         Partner Communities>Home
 *
 */
public class PartnerCommunities_Home_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public PartnerCommunities_Home_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "PartnerCommunities_Home@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Sales Menu
	 * 
	 *                     Select Account Menu
	 *                     
	 *                     Click on New
	 *                     
	 *                     Switch to Window 2
	 */
	public void launchCreateNewBusinessAccount() throws IOException {

		try {
			// Click on Sales Menu
			sf.seleU.clickElementByJSE(sf.partnerCommHome.salesMenuButton);
			reportStatusPass(methodName + " Clicked on Salest Menu", true, true);
			sf.seleU.wait(2000);

			// Click on Accounts Menu
			sf.seleU.clickElementByJSE(sf.partnerCommHome.accountOptionLink);
			reportStatusPass(methodName + " Clicked on Accounts from Sales Menu", true, true);
			sf.seleU.wait(2000);

			//Click on new from accounts layout
			sf.seleU.clickOnElement(sf.acc.newAccountButton);
			reportStatusPass(methodName + " Clicked on New Account button", true, true);
			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Launching new account omniscript is unsuccessful", e);
			e.printStackTrace();
		}

	}
	/**
	 * @throws IOException
	 * 
	 *                     Click on Sales Menu
	 * 
	 *                     Select Account Menu
	 *                     
	 *                     Verify Billing accounts are not viewable
	 *                     
	 */
	public void verifyBillingAccNotListed() throws IOException {

		try {
			sf.seleU.wait(10000);
			// Click on Sales Menu
			sf.seleU.clickElementByJSE(sf.partnerCommHome.salesMenuButton);
			reportStatusPass(methodName + " Clicked on Salest Menu", true, true);
			sf.seleU.wait(3000);

			// Click on Accounts Menu
			sf.seleU.clickElementByJSE(sf.partnerCommHome.accountOptionLink);
			reportStatusPass(methodName + " Clicked on Accounts from Sales Menu", true, true);
			sf.seleU.wait(5000);
			
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.partnerCommHome.billingAccountsListOption);
			reportStatusPass(methodName + " Clicked on Billing Account List views", true, true);
			sf.seleU.wait(5000);
			//Verify Billing Accounts Not vissible
			verifyFieldNotDisplayed("Billing Account list data", sf.partnerCommHome.accountsTabledata);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Validating billing acc list view absence is unsuccessful", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Logout from partner Communities
	 *                     
	 */
	public void logOut() throws IOException {

		try {
			//Click on Profile name
			sf.seleU.clickElementByJSE(sf.partnerCommHome.profileNameLink);
			
			//Click on Logout
			sf.seleU.clickElementByJSE(sf.home.logoutMenu);
			reportStatusPass("Logged out from partner communities", true, true);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Logout from partner communities is unsuccessful", e);
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
	/**
	 * @throws IOException
	 * 
	 *                    Switch to Window 2
	 *                    
	 */
	public void switchToWindowTwo() throws IOException {

		try {
			sf.seleU.switchWindow(2);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Switching to second window is unsuccessful", e);
			e.printStackTrace();
		}

	}
	
}
