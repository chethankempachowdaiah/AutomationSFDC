package sfdc.pages.sales;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;

import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.framework.utilities.ScreenDocs;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date : 14/04/2021
 *
 *         Site Selection page
 */
public class SFDC_SiteSelection_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_SiteSelection_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_SiteSlection@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open URL for Site Selection Page
	 * 
	 *                     Enter Credentials for internal users
	 * 
	 *                     Click on sign-In Button
	 * 
	 *                     Verify landing page
	 * 
	 */
	public void loginToSFDCSiteSelectionPage(List<String> credentials) throws IOException {

		try {

			driver.get(sf.commData.siteSelectionUrlForInternalUser);
			sf.seleU.wait(5000);

			if (sf.seleU.isElementPresent(By.xpath("//span[@class='uiImage']/img[@title='User']"))
					|| sf.seleU.isElementPresent(By.xpath("//a[@id='globalHeaderNameMink']"))) {
				reportStatusFail(methodName + " User is already logged into the application..", true);
			}
			Thread.sleep(3000);
			enterCredentialsSiteSelection(credentials.get(0), credentials.get(1));

		} catch (Throwable e) {
			reportStatusFail(methodName + "Login to site Selection page from SFDC Unsuccessful", e);
			e.printStackTrace();
		}
	}

	private void enterCredentialsSiteSelection(String userName, String pwd) throws IOException {
		try {
			sf.seleU.clearAndEnterText(sf.login.userNameInput, userName);
			String decodedPassword = "";
			decodedPassword = decodeBytes(pwd);
			sf.seleU.clearAndEnterText(sf.login.passwordInput, decodedPassword);
			ScreenDocs.captureScreenShot(docxGeneric, runGeneric, outGeneric, methodName + "   URL: " + InputData.url
					+ DateTimeUtilities.currentSystemDate("yyyy/MM/dd HH:mm:ss"));

			// Click on login To SandBox Button
			sf.seleU.clickElementByJSE(sf.login.loginToSandBoxButton);
			reportStatusPass(methodName + " Login credentials entered", true, false);

			sf.seleU.hardwait(3000);

			// verify if login successful
			if (sf.seleU.isElementPresent(By.xpath("//span[@class='uiImage']/img[@title='User']"))
					|| sf.seleU.isElementPresent(By.xpath("//a[@id='globalHeaderNameMink']/span"))) {
				reportStatusPass(methodName + " Login to SFDC Application is Successful", true, true);
			} else {
				reportStatusFail("login to SFDC Application is Unsuccessful", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Login to site Selection page from SFDC Unsuccessful", e);
			e.printStackTrace();
		}
	}

}
