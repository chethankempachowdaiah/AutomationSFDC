package sfdc.pages.om;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 03/FEB/2020
 * 
 *         SFDC Manual Queue>Account Provisioning Queue Details
 *
 */
public class SFDC_ManualQueueDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	int currentWindowCounter = 1;

	public SFDC_ManualQueueDetails_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Account Provisioning Queue Details@: ";
	}

	/**
	 * @throws Exception
	 * 
	 *                   Filter the order in the queue
	 */
	public void filterOrder() throws Exception {

		sf.seleU.hardwait(2000);
		if (sf.seleU.isElementDisplayed(sf.manQue.orderNumberInput)) {
			sf.seleU.wait(1000);
			sf.seleU.clearAndEnterText(sf.manQue.orderNumberInput, sf.dataInput.orderNumber);
			sf.seleU.wait(1000);
			sf.seleU.clickElementByJSE(sf.manQue.searchButton);

		} else {
			sf.seleU.wait(2000);
			sf.seleU.clickOnElement(sf.manQue.filterButton);		
			sf.seleU.clearAndEnterText(sf.manQue.orderNumberInput, sf.dataInput.orderNumber);
			sf.seleU.wait(2000);
			sf.seleU.clickOnElement(sf.manQue.searchButton);
			
			
		
		}
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Click on pick up link in Queue by filtering order number
	 *                     and task name
	 */
	public void pickUpOrderInQueueItems(String taskName) throws IOException {
		try {
			String methodName = "SFDC_Select pick up link in queue@: ";
			int index = 0;
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);
			sf.seleU.wait(2000);
			filterOrder();
			sf.seleU.wait(4000);

			
			// Click on pick up link in Queue
			boolean pickUpSelected = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
//				System.out.println((sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentAllNameRequests.get(i)).trim()));
//				System.out.println(taskName.trim());
				sf.seleU.wait(3000);
				if (sf.dataInput.orderNumber
						.equals(sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i)).trim())
						&& taskName.trim().equals(sf.seleU
								.getTextFromWebElement(sf.manQue.orderFulfilmentAllNameRequests.get(i)).trim())) {
				
					sf.seleU.clickElementByJSE(sf.manQue.actionPickUpAllLinks.get(i));
					// sf.seleU.enterText(sf.manQue.actionPickUpAllLinks.get(i), Keys.ENTER);
					sf.seleU.wait(4000);
					pickUpSelected = true;
					index = i;
					break;
				}
			}

			if (pickUpSelected) {
				reportStatusPass(methodName + " Clicked on Pick up Link for " + taskName, true, false);
			} else {
				reportStatusFail(methodName + " No Pick up in Queue", true);
			}
			sf.seleU.wait(8000);
			// Iterating from switching to 11 different window for pick up link in manual
			// queue task items
			System.out.println(currentWindowCounter);
			if (currentWindowCounter < 12) {
				currentWindowCounter++;
				sf.seleU.switchWindow(currentWindowCounter);
			}
			sf.seleU.wait(10000);
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for selecting task items in filter search", e);
			e.printStackTrace();
		}

	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Click on pick up link in Queue by filtering order number
	 *                     and task name
	 */
	public void pickUpOrderInQueueItemsForCableFlow(String taskName) throws IOException {
		try {
			String methodName = "SFDC_Select pick up link in queue@: ";
			int index = 0;
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);
			sf.seleU.wait(2000);
			filterOrder();
			sf.seleU.wait(5000);
	//		sf.seleU.waitElementToBeVisible(sf.manQue.orderFulfilmentRequestAllLinks.get(1));

			// Click on pick up link in Queue
			boolean pickUpSelected = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				sf.seleU.wait(2000);
				if (sf.dataInput.orderNumber
						.equals(sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i)).trim())
						&& taskName.trim().equals(sf.seleU
								.getTextFromWebElement(sf.manQue.orderFulfilmentAllNameRequests.get(i)).trim())) {
					sf.seleU.wait(2000);
					sf.seleU.clickElementByJSE(sf.manQue.actionPickUpAllLinks.get(i));
					// sf.seleU.enterText(sf.manQue.actionPickUpAllLinks.get(i), Keys.ENTER);
					sf.seleU.wait(2000);
					pickUpSelected = true;
					index = i;
					break;
				}
			}

			if (pickUpSelected) {
				reportStatusPass(methodName + " Clicked on Pick up Link for " + taskName, true, false);
			} else {
				reportStatusFail(methodName + " No Pick up in Queue", true);
			}
			sf.seleU.wait(2000);
			sf.seleU.switchWindow(2);

			sf.seleU.wait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for selecting task items in filter search", e);
			e.printStackTrace();
		}

	}


	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Click on pick up link in Queue(Account Provisioning
	 *                     Queue/Service Delivery Queue)
	 */
	public void pickUpOrderInQueue() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Click on pick up link in Queue
			boolean pickUpSelected = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				if (sf.dataInput.orderNumber
						.equals(sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i)))) {
					sf.seleU.clickElementByJSE(sf.manQue.actionPickUpAllLinks.get(i));
					// sf.seleU.enterText(sf.manQue.actionPickUpAllLinks.get(i), Keys.ENTER);
					sf.seleU.wait(3000);
					pickUpSelected = true;
					break;
				}

			}

			if (pickUpSelected) {
				reportStatusPass(methodName + " Clicked on Pick up Link ", true, false);
			} else {
				reportStatusFail(methodName + " No Pick up in Queue", true);
			}

			sf.seleU.hardwait(5000);

			sf.seleU.switchWindow(2);
			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Manual Queues Object", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify order in Queue
	 */
	public void verifyOrderInQueue() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Verify order in Queue
			boolean pickUpSelected = false;
			for (int i = 0; i < sf.manQue.orderFulfilmentRequestAllLinks.size(); i++) {
				if (sf.dataInput.orderNumber
						.equals(sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i)))) {
					pickUpSelected = true;
					break;
				}
			}

			if (pickUpSelected) {
				reportStatusPass(methodName + " Verified order : " + sf.dataInput.orderNumber + " in Queue", true,
						false);
			} else {
				reportStatusFail(methodName + " No order in Queue", true);
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for order in Manual Queues Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is not there queue
	 */
	public void verifyOrderNotInQueue() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Iterate and Verify Order is not there queue
			boolean isOrderInQueue = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i))
						.equals(sf.dataInput.orderNumber)) {
					isOrderInQueue = true;
				}
			}

			if (isOrderInQueue) {
				reportStatusFail(" Verification failure for order not to be In Queue, Order " + sf.dataInput.orderNumber
						+ " is still there in Queue", true);
			} else {
				reportStatusPass(methodName + " Verified Order " + sf.dataInput.orderNumber + " no more exist in queue",
						true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification failure for order not to be In Queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is Assigned and Running
	 */
	public void verifyOrderRunningAndAssigned() throws IOException {
		try {
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Iterate and Verify Order is Assigned and Running
			boolean isOrderRunning = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i))
						.equals(sf.dataInput.orderNumber)
						&& sf.seleU.getTextFromWebElement(sf.manQue.orderStateAllLinks.get(i))
						.equals(sf.dataInput.taskStatusRunning)
						&& sf.seleU.getTextFromWebElement(sf.manQue.assignedToUserAllLinks.get(i))
						.contains(sf.dataInput.user)) {
					isOrderRunning = true;
					break;
				}
			}

			if (isOrderRunning) {
				reportStatusPass(methodName + " Verified Order " + sf.dataInput.orderNumber
						+ " is assigned to the user and Running", true, true);
			} else {
				reportStatusFail(" Verification failure for order state" + sf.dataInput.orderNumber
						+ " is not assigned and Running", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification failure for Order Status ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is Running
	 */
	public void verifyOrderRunning() throws IOException {
		try {
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Iterate and Verify Order is Assigned and Running
			boolean isOrderRunning = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i))
						.equals(sf.dataInput.orderNumber)
						&& sf.seleU.getTextFromWebElement(sf.manQue.orderStateAllLinks.get(i))
						.equals(sf.dataInput.taskStatusRunning)) {
					isOrderRunning = true;
					break;
				}
			}

			if (isOrderRunning) {
				reportStatusPass(methodName + " Verified Order " + sf.dataInput.orderNumber
						+ " is assigned to the user and Running", true, true);
			} else {
				reportStatusFail(" Verification failure for order state" + sf.dataInput.orderNumber
						+ " is not assigned and Running", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification failure for Order Status ", e);
			e.printStackTrace();
		}
	}
	
	




	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is Ready and Not Assigned
	 */
	public void verifyOrderReadyAndNotAssigned() throws IOException {
		try {

			sf.seleU.wait(7000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Iterate and Verify Order is Ready and Not Assigned
			boolean isOrderReady = false;
			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i))
						.equals(sf.dataInput.orderNumber)
						&& sf.seleU.getTextFromWebElement(sf.manQue.orderStateAllLinks.get(i))
						.equals(sf.dataInput.taskStatusReady)) {
					isOrderReady = true;
					break;
				}
			}

			if (isOrderReady) {
				reportStatusPass(
						methodName + " Verified Order " + sf.dataInput.orderNumber + " is Ready and not  assigned ",
						true, true);
			} else {
				reportStatusFail(" Verification failure for order state 'not assigned and Ready' of order number "
						+ sf.dataInput.orderNumber, true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification failure for Order Status ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is Failed
	 */
	public void verifyOrderStatusFailed() throws IOException {
		try {
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.manQue.actionPickUpAllLinks);

			filterOrder();

			// Iterate and Verify Order is Failed

			for (int i = 0; i < sf.manQue.actionPickUpAllLinks.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.manQue.orderFulfilmentRequestAllLinks.get(i))
						.equals(sf.dataInput.orderNumber)
						&& sf.seleU.getTextFromWebElement(sf.manQue.orderStateAllLinks.get(i))
						.equals(sf.dataInput.orderStateFailed)) {
					sf.dataInput.isOrderFailed = true;
					break;
				}
			}

			if (sf.dataInput.isOrderFailed) {
				reportStatusPass(methodName + " Verified Order " + sf.dataInput.orderNumber + " is failed", true, true);
			} else {
				reportStatusFail(" Verification failure for order state" + sf.dataInput.orderNumber + " is not failed",
						true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification failure for Order Status Failed", e);
			e.printStackTrace();
		}
	}

}
