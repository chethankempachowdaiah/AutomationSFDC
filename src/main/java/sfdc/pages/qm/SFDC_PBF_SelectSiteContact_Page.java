package sfdc.pages.qm;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla,Date: 17/08/2021
 * 
 *        PBF > Select Site Contact-- > Add new COntact
 *
 */
public class SFDC_PBF_SelectSiteContact_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	String methodName = "SFDC_PBF_SiteContact@:";
	
	public SFDC_PBF_SelectSiteContact_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * 			Validate user is able to click on the Add contact CTA
	 * 
	 * 			User selects from an existing contact and proceed to land back on Order Review Page
	 * 
	 * @throws IOException
	 */
	public void addSiteContact() throws IOException {

		try {
				
			if (sf.seleU.isElementDisplayed(sf.comPBFOrderReview.addContactButton)) {
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.addContactButton);
				sf.seleU.wait(3000);
				reportStatusPass(methodName + " Moved to Site Contact Page", true, true);
				
				if (InputData_Communities.commPBFAddNewContact!=null && InputData_Communities.commPBFAddNewContact.equals("Yes")) {
					createNewSiteContact();
				} else {
					//Extract Contact Name from Site Contact 
					sf.dataInput.siteContactName =  sf.seleU.getTextFromWebElement(sf.comPBF.siteContactName);
					sf.seleU.clickElementByJSE(sf.comPBF.serviceAddressRadioButtons.get(0));
					sf.seleU.wait(3000);
					sf.seleU.clickElementByJSE(sf.siteConPBF.continueButton);
					sf.seleU.wait(3000);
				}
				
				reportStatusPass(methodName + " Site Contact added to Order", true, true);
				
			} 
			else {
				reportStatusPass(methodName + " Site Contact is already added to Order", true, false);
			}
			
			//Scroll down
			sf.seleU.scrollByCoOrdinates(2);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in adding Site Contact on Order Review Page", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * @throws IOException
	 * 
	 *                    Create New Site Contact
	 */
	public void createNewSiteContact() throws IOException {
		try {
			// Click on Add new contact
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.siteConPBF.addAContactButton);
			reportStatusPass(methodName + " Clicked on Add new Contact button", true, false);
			sf.seleU.wait(5000);
			
			// Enter Site Contact Details
			sf.seleU.enterText(sf.siteConPBF.siteConFirstName, "New" + sf.dataInput.addOn_1);
			sf.seleU.enterText(sf.siteConPBF.siteConLastName, "New" + sf.dataInput.addOn_2);
			sf.seleU.enterText(sf.siteConPBF.siteConEmailAddress, "New" + sf.dataInput.addOn_1 + "_"
					+ "New" + sf.dataInput.addOn_2 + "@mailinator.com");
			sf.seleU.enterText(sf.siteConPBF.siteConEmailAddress, Keys.TAB);
			sf.seleU.clickElementByJSE(sf.siteConPBF.siteConPhone);
			sf.seleU.wait(2000);
			int[] possibleKeys = new int[]{
				    KeyEvent.VK_1, 
				    KeyEvent.VK_0,
				    KeyEvent.VK_2,
				    KeyEvent.VK_3,
				    KeyEvent.VK_4, 
				    KeyEvent.VK_5,
				    KeyEvent.VK_6,
				    KeyEvent.VK_7,
				    KeyEvent.VK_8, 
				    KeyEvent.VK_9,
			};
			
			for (int i=0; i<10;i++)
				sf.seleU.robotPressKey(1,possibleKeys[new Random().nextInt(possibleKeys.length)]);
			//sf.seleU.robotPressKey(10, Integer.parseInt(AdditionalUtilities.generateRandomNumbers(1)));
			sf.seleU.robotPressKey(1,KeyEvent.VK_TAB);
			reportStatusPass(
					methodName + " Entered Site Contact info : " + "New" + sf.dataInput.addOn_1 + " "
							+ "New" + sf.dataInput.addOn_2,	true, true);
			sf.seleU.wait(2000);
			// Click on Next
			sf.seleU.clickElementByJSE(sf.siteConPBF.continueButton);
			reportStatusPass(methodName + " Click on continue button site contact page", true, false);


		} catch (Throwable e) {
			reportStatusFail(" Creating New Site Contact  is unsuccessful", e);
			e.printStackTrace();
		}
	}

}
