package sfdc.pages.om;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Manual Queues page objects
 */
public class SFDC_ManualQueues_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_ManualQueues_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Manual Queues@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on app launcher and search for manual queues
	 * 
	 *                     2.Select manual queues
	 * 
	 *                     3.Click on list view dropdown and select all Orders
	 * 
	 *                     4.Verify Manual Queues( Account Provisioning
	 *                     Queue,Service Delivery Queue)are displayed
	 */
	public void verifyManualQueuesObject() throws IOException {
		try {

			// Click on app launcher and search for manual queues
			sf.seleU.clickElementByJSE(sf.home.applauncher);

			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, sf.dataInput.manualQueuesObject);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(sf.dataInput.manualQueuesObject)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}

			reportStatusPass(methodName + " Clicked on Manual Queue Object ", true, false);
			sf.seleU.wait(4000);

			// Click on list view dropdown and select all in manual queue
			sf.seleU.clickElementByJSE(sf.home.manualQueueListViewIcon);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.wait(4000);
			sf.seleU.refreshPage();
			sf.seleU.wait(15000);

			// Verify Manual Queues( Account Provisioning Queue,Service Delivery Queue)are
			// displayed

			boolean isAccprovQuFound = false;
			boolean isServiceDlvryQuFound = false;

			for (int i = 0; i < sf.mques.manualQueuesAllRecords.size(); i++) {

				// Check if Account Provisioning Queue Found
				if (sf.seleU.getTextFromWebElement(sf.mques.manualQueuesAllRecords.get(i))
						.equals(sf.dataInput.accountProvisQueObject)) {
					isAccprovQuFound = true;
				}

				// Check if Service Delivery Queue Found
				if (sf.seleU.getTextFromWebElement(sf.mques.manualQueuesAllRecords.get(i))
						.equals(sf.dataInput.serviceDeliveryQueueObject)) {
					isServiceDlvryQuFound = true;
				}

			}

			if (isAccprovQuFound && isServiceDlvryQuFound) {
				reportStatusPass(
						methodName
								+ " Manual Queues Objects (Account Provisioning Queue,Service Delivery Queue) verified",
						true, true);
			} else {
				reportStatusFail(methodName + " Invalid Manual Queues Object", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Manual Queues Object", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException  //PA PIiSprint2
	 * 
	 *                     1.Click on app launcher and search for manual queues
	 * 
	 *                     2.Select manual queues
	 * 
	 *                     3.Click on list view dropdown and select all Orders
	 * 
	 *                     4.Verify Manual Queues( Account Provisioning
	 *                     Queue,Service Delivery Queue)are displayed
	 */
	public void clickOnManualQueuesObject() throws IOException {
		try {

			// Click on app launcher and search for manual queues
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, sf.dataInput.manualQueuesObject);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(sf.dataInput.manualQueuesObject)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}

			reportStatusPass(methodName + " Clicked on Manual Queue Object ", true, false);
			sf.seleU.wait(2000);

			// Click on list view dropdown and select all in manual queue
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.wait(2000);
			sf.seleU.refreshPage();
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Manual Queues Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on app launcher and search for manual queues
	 * 
	 *                     Verify no access for 'manual queue' object
	 */
	public void verifyNoAccessToManualQueues() throws IOException {
		try {

			// Click on app launcher and search for manual queues
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.clickElementByJSE(sf.home.viewAllButton);
			reportStatusPass(methodName + " Clicked on App launcher ", true, false);
			sf.seleU.enterText(sf.home.searchAppPlaceHolderInput, sf.dataInput.manualQueuesObject);
			sf.seleU.hardwait(2000);

			// verify no access for 'manual queue' objecterify no access for 'manual queue'
			// objecterify no access for 'manual queue' object
			if (sf.home.appTextAllLinks.size() == 0) {
				reportStatusPass(methodName + " Verified that, there is no object as 'manual queue' ", true, false);
			} else {
				reportStatusFail(methodName + " Verification Failure for Manual Queues Object", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Manual Queues Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on app launcher and search for manual queues
	 * 
	 *                     Select manual queues
	 * 
	 *                     Click on list view dropdown and select all Orders
	 * 
	 *                     Click on 'Account Provisioning Queue'
	 */
	public void selectManualQueueAccProvQue() throws IOException {

		try {
			// Click on app launcher and search for manual queues
			sf.seleU.clickElementByJSE(sf.home.applauncher);
//			sf.seleU.hardwait(2000);
//			sf.seleU.clickElementByJSE(sf.home.viewAllButton);
//			reportStatusPass(methodName + " Clicked on App launcher ", true, false);
//			sf.seleU.enterText(sf.home.searchAppPlaceHolderInput, sf.dataInput.manualQueuesObject);
//			sf.seleU.hardwait(2000);
//
//			// Select manual queues
//			for (int i = 0; i < sf.home.appTextAllLinks.size(); i++) {
//				if (sf.home.appTextAllLinks.get(i).getText().equals(sf.dataInput.manualQueuesObject)) {
//					sf.seleU.clickElementByJSE(sf.home.appTextAllLinks.get(i));
//					break;
//				}
//			}
//			sf.seleU.hardwait(8000);

			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, sf.dataInput.manualQueuesObject);
			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(sf.dataInput.manualQueuesObject)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			reportStatusPass(methodName + " Clicked on Manual Queue Object ", true, false);
			sf.seleU.wait(4000);

			// Click on list view dropdown and select all Orders
			sf.seleU.clickElementByJSE(sf.home.manualQueueListViewIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.hardwait(9000);

			if (sf.seleU.isElementPresent(sf.home.closeServiceDeliveryQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeServiceDeliveryQueue);
			}

			if (sf.seleU.isElementPresent(sf.home.closeOffice365)) {
				sf.seleU.clickElementByJSE(sf.home.closeOffice365);
			}

			if (sf.seleU.isElementPresent(sf.home.closeAccountProvisioningQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeAccountProvisioningQueue);
			}

			// Click on 'Account Provisioning Queue'

			for (int i = 0; i < sf.mques.manualQueuesAllRecords.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.mques.manualQueuesAllRecords.get(i))
						.equals("Account Provisioning Queue")) {
					sf.seleU.clickElementByJSE(sf.mques.manualQueuesAllRecords.get(i));
					reportStatusPass(methodName + " Clicked on 'Account Provisioning Queue'", true, false);
					break;
				}

			}
			sf.seleU.hardwait(8000);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Manual Queues Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on 'Account Provisioning Queue'
	 */
	public void selectAccountProvisioningQueue() throws IOException {
		try {
			sf.seleU.clickElementByJSE(sf.home.manualQueueListViewIcon);
			sf.seleU.hardwait(3000);

			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.hardwait(4000);

			if (sf.seleU.isElementPresent(sf.home.closeServiceDeliveryQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeServiceDeliveryQueue);
			}

			if (sf.seleU.isElementPresent(sf.home.closeOffice365)) {
				sf.seleU.clickElementByJSE(sf.home.closeOffice365);
			}

			if (sf.seleU.isElementPresent(sf.home.closeAccountProvisioningQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeAccountProvisioningQueue);
			}

			// Click on 'Account Provisioning Queue'

			for (int i = 0; i < sf.mques.manualQueuesAllRecords.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.mques.manualQueuesAllRecords.get(i))
						.equals("Account Provisioning Queue")) {
					sf.seleU.clickElementByJSE(sf.mques.manualQueuesAllRecords.get(i));
					reportStatusPass(methodName + " Clicked on 'Account Provisioning Queue'", true, false);
					break;
				}

			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking Account provisioning queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on 'Service Delivery Queue'
	 */
	public void selectServiceDeliveryQueue() throws IOException {

		try {

			if (sf.seleU.isElementPresent(sf.home.closeAccountProvisioningQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeAccountProvisioningQueue);
			}

			if (sf.seleU.isElementPresent(sf.home.closeOffice365)) {
				sf.seleU.clickElementByJSE(sf.home.closeOffice365);
			}

			if (sf.seleU.isElementPresent(sf.home.closeServiceDeliveryQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeServiceDeliveryQueue);
			}

			sf.seleU.clickElementByJSE(sf.home.manualQueueListViewIcon);
			sf.seleU.hardwait(3000);

			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.hardwait(4000);

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.mques.manualQueuesAllRecords);

			// Click on 'Service Delivery Queue'

			for (int i = 0; i < sf.mques.manualQueuesAllRecords.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.mques.manualQueuesAllRecords.get(i))
						.equals("Service Delivery Queue")) {
					sf.seleU.clickElementByJSE(sf.mques.manualQueuesAllRecords.get(i));
					reportStatusPass(methodName + " Clicked on 'Service Delivery Queue'", true, false);
					break;
				}
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking Service Delivery Queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Office 365
	 */
	public void selectOffice365() throws IOException {
		try {

			if (sf.seleU.isElementPresent(sf.home.closeAccountProvisioningQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeAccountProvisioningQueue);
			}

			if (sf.seleU.isElementPresent(sf.home.closeServiceDeliveryQueue)) {
				sf.seleU.clickElementByJSE(sf.home.closeServiceDeliveryQueue);
			}

			if (sf.seleU.isElementPresent(sf.home.closeOffice365)) {
				sf.seleU.clickElementByJSE(sf.home.closeOffice365);
			}

			sf.seleU.clickElementByJSE(sf.home.manualQueueListViewIcon);
			sf.seleU.hardwait(3000);

			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.hardwait(4000);

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.mques.manualQueuesAllRecords);

			// Click on 'Office 365'

			System.out.println(sf.mques.manualQueuesAllRecords.size());

			for (int i = 0; i < sf.mques.manualQueuesAllRecords.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.mques.manualQueuesAllRecords.get(i)).equals("Office 365")) {
					sf.seleU.clickElementByJSE(sf.mques.manualQueuesAllRecords.get(i));
					reportStatusPass(methodName + " Clicked on 'Office 365'", true, false);
					break;
				}
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting Office 365", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select View All Option
	 */
	public void selectViewAllOption() throws IOException {
		try {
			String methodName = "SFDC_Select view all option from drop down@: ";
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);

			sf.seleU.clickElementByJSE(sf.mques.allOption);
			reportStatusPass(methodName + " Selected All Manual Queues Option", true, false);
			sf.seleU.hardwait(2000);

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.mques.manualQueuesAllRecords);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting View All Option In Dropdown", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on delivery specialist queue
	 */
	public void selectDeliverySpecialistQueue() throws IOException {
		try {
			String methodName = "SFDC_Select Delivery Specialist queue@: ";
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.mques.deliverySpecialistQueue);
			reportStatusPass(methodName + " Selected delivery specialist queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting delivery specialist queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on service designer queue
	 */
	public void selectServiceDesignerQueue() throws IOException {
		try {
			String methodName = "SFDC_Select service designer queue@: ";
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.mques.serviceDesignerQueue);
			reportStatusPass(methodName + " Selected service designer queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (

		Throwable e) {
			reportStatusFail(" Error in selecting service designer queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Tech Implementation queue
	 */
	public void selectTechImpSpecialistQueue() throws IOException {
		try {
			String methodName = "SFDC_Select Tech Implementation Queue@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mques.techImpSpecQueue);
			reportStatusPass(methodName + " Selected Tech Implementation queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Tech Imp queue", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on TechM Queue
	 */
	public void selectTechMQueue() throws IOException {
		try {
			String methodName = "SFDC_Select_TechM_Queue@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mques.techMQueue);
			reportStatusPass(methodName + " Selected TechM queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting TechM queue", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Cable Order Provisioning queue
	 */
	public void selectCableOrderProvisioningQueue() throws IOException {
		try {
			String methodName = "SFDC_Select Cable Order Provisioning Queue@: ";
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.mques.cableOrderQueue);
			reportStatusPass(methodName + " Selected Cable Order Provisioning queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Cable Order queue", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on Cable Error Handling
	 */
	public void selectCableErrorHandlingQueue() throws IOException {
		try {
			String methodName = "SFDC_Select Cable Error Handling Queue@: ";
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.mques.cableErrorHandlingOrderQueue);
			reportStatusPass(methodName + " Selected Cable Order Provisioning queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Cable Handling queue", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on office 365 task
	 */
	public void selectOffice365TaskQueue() throws IOException {
		try {
			String methodName = "SFDC_Select_Office_365_Queue@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.mques.office365OrderQueue);
			reportStatusPass(methodName + " Selected Office 365 manual queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting Cable Order queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on salesforce prod support queue
	 */
	public void selectSalesforceProdSupport() throws IOException {
		try {
			String methodName = "SFDC_Select Salesforce Prod support Queue@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(sf.mques.salesforceProdSupportcQueue);
			reportStatusPass(methodName + " Selected prod support queue", true, false);
			sf.seleU.hardwait(4000);

		} catch (

		Throwable e) {
			reportStatusFail(" Error in selecting salesforce prod support", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on pending order button
	 */
	public void selectPendingOrder() throws IOException {
		try {

			sf.seleU.clickElementByJSE(sf.home.pendingOrderButton);
			reportStatusPass(methodName + " Selected Pending Order Button", true, false);
			sf.seleU.hardwait(4000);

		} catch (

		Throwable e) {
			reportStatusFail(" Error in selecting Pending Order", e);
			e.printStackTrace();
		}
	}
}
