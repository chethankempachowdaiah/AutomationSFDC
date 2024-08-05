package sfdc.pages.service;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_EmailToCase_Lib;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 20/02/2020
 * 
 *         Omni Channel (Email to case)
 *
 */
public class SFDC_EmailToCase_OmniChannel_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_EmailToCase_OmniChannel_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Email To Case_Omni Channel ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Omni-Channel Tab
	 * 
	 *                     Click Status Button
	 * 
	 *                     Select Offline Status
	 * 
	 *                     Verify the status is changed to Offline
	 */
	public void changeStatusToOffline() throws IOException {
		try {

			// Click on Omni-Channel Tab
			sf.seleU.clickElementByJSE(sf.omniChannel.omniChannelTab);
			reportStatusPass(methodName + " Clicked on Omni Channel Tab ", true, false);
			sf.seleU.hardwait(2000);

			// Click Status Button
			sf.seleU.clickElementByJSE(sf.omniChannel.changeOmniChannelStatusButton);
			reportStatusPass(methodName + " Clicked on Status Button ", true, false);
			sf.seleU.hardwait(3000);

			// Select Offline Status
			sf.seleU.clickElementByJSE(sf.omniChannel.offlineEmailStatusOption);
			reportStatusPass(methodName + " Selected Offline Status ", true, false);
			sf.seleU.wait(20000);

			// Verify the status is changed to offline

			if (sf.seleU.isElementDisplayed(sf.omniChannel.offlineEmailCurrentStatus)) {
				reportStatusPass(methodName + " Successfully Changed the Status to Offline", true, false);
			} else {
				reportStatusFail(" Error in Changng the status to Offline", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Changng the status to Offline", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Omni-Channel Tab
	 * 
	 *                     Click Status Button
	 * 
	 *                     Select Availble Status
	 * 
	 *                     Verify the status is changed to avilable
	 * 
	 *                     Accept the required case
	 */
	public void changeStatusToAvailble() throws IOException {
		try {

			// Click on Omni-Channel Tab
			sf.seleU.clickElementByJSE(sf.omniChannel.omniChannelTab);
			reportStatusPass(methodName + " Clicked on Omni Channel Tab ", true, false);
			sf.seleU.hardwait(2000);

			// Click Status Button
			sf.seleU.clickElementByJSE(sf.omniChannel.changeOmniChannelStatusButton);
			reportStatusPass(methodName + " Clicked on Status Button ", true, false);
			sf.seleU.hardwait(3000);

			// Select Availble Status
			sf.seleU.clickElementByJSE(sf.omniChannel.availableEmailStatusOption);
			reportStatusPass(methodName + " Selected Availble Status ", true, false);
			sf.seleU.wait(20000);

			// Verify the status is changed to available

			if (sf.seleU.isElementDisplayed(sf.omniChannel.availableEmailCurrentStatus)) {
				reportStatusPass(methodName + " Successfully Changed the Status to Availble", true, false);
			} else {
				reportStatusFail(" Error in Changng the status to Availble", true);
			}

			// Accepting /Declining case from queue functionality is changed

			int i = 0;
			int maxAttempts = 45;
			while (true) {
				if (sf.seleU.getTextFromWebElement(sf.omniChannel.caseHeaderText)
						.equals(SFDC_EmailToCase_Lib.subjectLine)) {

					// Accept the case
					sf.seleU.clickElementByJSE(sf.omniChannel.acceptCaseButton);
					reportStatusPass(methodName + "Accepted the case "
							+ sf.seleU.getTextFromWebElement(sf.omniChannel.caseHeaderText), true, false);
					break;

				} else {

					// Decline the case
					sf.seleU.clickElementByJSE(sf.omniChannel.declineCaseButton);
					reportStatusPass(methodName + "Declined the case "
							+ sf.seleU.getTextFromWebElement(sf.omniChannel.caseHeaderText)
							+ " , As this is not the required case to proceed.", true, false);
					sf.seleU.wait(10000);
				}

				if (i == maxAttempts) {
					break;
				}
				i++;
			}

			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Changng the status to Availble", e);
			e.printStackTrace();
		}
	}
}
