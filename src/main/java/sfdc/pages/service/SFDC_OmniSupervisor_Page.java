package sfdc.pages.service;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author priyanka.acharya
 * 
 *         Omni Supervisor page
 *
 */
public class SFDC_OmniSupervisor_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_OmniSupervisor_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Select Account from navigation menu
	 * 
	 * @throws IOException
	 */
	public void selectOmniSupervisor() throws IOException {

		try {

			String methodName = "SFDC_OmniSupervisor@: ";

			// Selecting Omni Supervisor from menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.home.omniSupervisorMenu);
			reportStatusPass(methodName + " Selected Omni Supervisor from menu", true, false);
			sf.seleU.hardwait(5000);

			// Verifying Agents tab
			if (sf.seleU.getTextFromWebElement(sf.omni.agentsTabText).equals(sf.dataInput.agentsTab)) {
				reportStatusPass(methodName + " Found and Validated Tab: " + sf.dataInput.agentsTab, true, true);
			} else {
				reportStatusFail(methodName + " Missing Tab in Omni supervisor: " + sf.dataInput.agentsTab, true);
			}

			// Verifying Queues Backlog tab
			if (sf.seleU.getTextFromWebElement(sf.omni.queuesBacklogTabText).equals(sf.dataInput.queuesBacklogTab)) {
				reportStatusPass(methodName + " Found and Validated  Tab: " + sf.dataInput.queuesBacklogTab, true,
						true);
			} else {
				reportStatusFail(methodName + " Missing Tab in Omni supervisor: " + sf.dataInput.queuesBacklogTab,
						true);
			}

			// Verifying Assigned Work tab
			if (sf.seleU.getTextFromWebElement(sf.omni.assignedWorkTabText).equals(sf.dataInput.assignedWorkTab)) {
				reportStatusPass(methodName + " Found and Validated Tab: " + sf.dataInput.assignedWorkTab, true, true);
			} else {
				reportStatusFail(methodName + " Missing Tab in Omni supervisor: " + sf.dataInput.assignedWorkTab, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Validating Omni Supervisor is Unsuccessful", e);
			e.printStackTrace();
		}
	}

}
