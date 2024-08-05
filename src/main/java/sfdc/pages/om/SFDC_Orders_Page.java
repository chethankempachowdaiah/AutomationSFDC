package sfdc.pages.om;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.qm.SFDC_Quote_SelectSiteContact_Page;

/**
 * @author Priyanka.Acharya, Date: 25/Jan/2019
 *
 *         SFDC Order page objects
 */
public class SFDC_Orders_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Orders_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on app launcher and search for order
	 * 
	 *                     2.Select Order
	 * 
	 *                     3.Click on list view dropdown and select all Orders
	 * 
	 *                     4.Verify Orders are displayed
	 */
	public void verifyOrdersObject() throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";

			// Click on app launcher and search for order
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			//			sf.seleU.clickElementByJSE(sf.home.viewAllButton);
			//			reportStatusPass(methodName + " Clicked on App launcher ", true, false);
			//			sf.seleU.enterText(sf.home.searchAppPlaceHolderInput, sf.dataInput.ordersObject);
			//			sf.seleU.hardwait(2000);
			//
			//			// Select Order
			//			for (int i = 0; i < sf.home.appTextAllLinks.size(); i++) {
			//				if (sf.home.appTextAllLinks.get(i).getText().equals(sf.dataInput.ordersObject)) {
			//					sf.seleU.clickElementByJSE(sf.home.appTextAllLinks.get(i));
			//					break;
			//				}
			//			}
			//			sf.seleU.hardwait(4000);

			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.home.searchAppItemInput, sf.dataInput.ordersObject);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(sf.dataInput.ordersObject)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}

			reportStatusPass(methodName + " Clicked on Orders Object ", true, false);
			sf.seleU.wait(4000);

			// All order option is not available in the list view icon
			// Click on list view dropdown and select all Orders
			//			sf.seleU.clickElementByJSE(sf.home.ordersListViewIcon);
			//			sf.seleU.hardwait(2000);
			//sf.seleU.clickElementByJSE(sf.orders.allOrdersOption);
			reportStatusPass(methodName + " Selected All order Option", true, false);
			sf.seleU.hardwait(2000);

			// Verify Orders are displayed
			if (sf.orders.ordersAllRecords.size() > 0) {
				reportStatusPass(methodName + " Orders Objects verified", true, true);
			} else {
				reportStatusFail(methodName + " Invalid Orders Object", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Orders Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on app launcher and search for order
	 * 
	 *                     2.Select Order
	 * 
	 *                     3.Click on list view dropdown and select all Orders
	 * 
	 *                     4.Verify Orders are displayed
	 */
	public void globalSearchOrders(String orderNumber) throws IOException {
		try {
			String methodName = "SFDC_Global Search Orders@: ";
			sf.seleU.wait(4000);
			// Click on app launcher and search for order
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.clickElementByJSE(sf.home.viewAllButton);
			reportStatusPass(methodName + " Clicked on App launcher ", true, false);
			sf.seleU.enterText(sf.home.searchAppPlaceHolderInput, sf.dataInput.ordersObject);
			sf.seleU.hardwait(2000);

			// Select Order
			for (int i = 0; i < sf.home.appTextAllLinks.size(); i++) {
				sf.seleU.wait(5000);
				if (sf.home.appTextAllLinks.get(i).getText().equals(sf.dataInput.ordersObject)) {
					sf.seleU.clickElementByJSE(sf.home.appTextAllLinks.get(i));
					break;
				}
			}
			sf.seleU.wait(2000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.enterText(sf.orders.ordersGlobalSearch, orderNumber);
			sf.seleU.hardwait(1000);
			sf.seleU.enterText(sf.orders.ordersGlobalSearch, Keys.ENTER);
			sf.seleU.hardwait(1000);

			// // Iterate and Find the order and click on order
			boolean isOrderFound = false;
			for (int i = 0; i < sf.orders.ordersAllRecords.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.orders.globalSerachOrdersAllRecords.get(i)).trim()
						.equals(sf.dataInput.orderNumber)) {
					sf.seleU.clickElementByJSE(sf.orders.globalSerachOrdersAllRecords.get(i));
					isOrderFound = true;
					break;
				}
			}

			if (isOrderFound) {
				reportStatusPass(methodName + " Clicked on order " + sf.dataInput.orderNumber, true, true);
			} else {
				reportStatusFail(methodName + " No order found as " + sf.dataInput.orderNumber, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Orders Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on app launcher and search for order
	 * 
	 *                     2.Select Order
	 * 
	 *                     3.Click on list view dropdown and select all Orders
	 * 
	 *                     4.Verify Orders are displayed
	 */
	public void selectOrderFromMenuItems() throws IOException {
		try {
			String methodName = "SFDC_select order@: ";
			int index = 0;
			boolean isOrderFound = false;
			// Click on app launcher and search for order

			sf.seleU.hardwait(2000);

			// Select Order
			for (int i = 0; i < sf.orders.ordersAllRecords.size(); i++) {
				System.out.println(sf.seleU.getTextFromWebElement(sf.orders.ordersAllRecordsStatusActive.get(i)));
				if (sf.seleU.getTextFromWebElement(sf.orders.ordersAllRecordsStatusActive.get(i)).trim().equals("Activated")) {
					sf.seleU.clickElementByJSE(sf.orders.ordersAllRecords.get(i));
					isOrderFound = true;
					index = i;
					break;
				}
			}
			sf.seleU.hardwait(4000);

			// Click on list view dropdown and select all Orders
			if (isOrderFound) {
				reportStatusPass(
						methodName + " Clicked on order "
								+ sf.seleU.getTextFromWebElement(sf.orders.ordersAllRecordsStatusActive.get(index)),
								true, true);
			} else {
				reportStatusFail(methodName + " No order found as ", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting order", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 *                    Select Order from Menu Drop down
	 * 
	 *                     Enter order to be Searched in the sub list
	 * 
	 *                     Iterate and Find the order and click on order
	 * 
	 */
	public void selectOrder() throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";
			if (!InputData.env.equals("WADEVQA")) {
				sf.seleU.switchToDefaultContent();
			}
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.homeMenuOrders);
			reportStatusPass(methodName + " Selected Orders from Home Menu", true, false);
			//	sf.seleU.refreshPage();
			sf.seleU.wait(2000);

			// 2- Select Recently View Order option
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.acc.recentlyViewedAccountOption);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Selected recently viewed Option", true, false);

			// Enter order to be Searched
			sf.seleU.enterText(sf.orders.searchOrderInput, sf.dataInput.orderNumber);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.orders.searchOrderInput, Keys.ENTER);
			sf.seleU.hardwait(3000);

			// Iterate and Find the order and click on order
			boolean isOrderFound = false;
			for (int i = 0; i < sf.orders.ordersAllRecords.size(); i++) {
				if (sf.orders.ordersAllRecords.get(i).getText().equals(sf.dataInput.orderNumber)) {
					sf.seleU.clickElementByJSE(sf.orders.ordersAllRecords.get(i));
					isOrderFound = true;
					break;
				}
			}

			if (isOrderFound) {
				reportStatusPass(methodName + " Clicked on order " + sf.dataInput.orderNumber, true, true);
			} else {
				reportStatusFail(methodName + " No order found as " + sf.dataInput.orderNumber, true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting order object", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 	 *                    Search the order directly from the global search
	 */
	public void globalSearchOrdersWithOutAppLauncher(String orderNumber) throws IOException {
		try {
			String methodName = "SFDC_Global"
					+ " Search Orders@: ";
			System.out.println(orderNumber);
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(8000);
			//			// Click on app launcher and search for order
			//			sf.seleU.clickElementByJSE(sf.home.applauncher);
			//			sf.seleU.clickElementByJSE(sf.home.viewAllButton);
			//			reportStatusPass(methodName + " Clicked on App launcher ", true, false);
			//			sf.seleU.enterText(sf.home.searchAppPlaceHolderInput, sf.dataInput.ordersObject);
			//			sf.seleU.hardwait(2000);
			//
			//			// Select Order
			//			for (int i = 0; i < sf.home.appTextAllLinks.size(); i++) {
			//				if (sf.home.appTextAllLinks.get(i).getText().equals(sf.dataInput.ordersObject)) {
			//					sf.seleU.clickElementByJSE(sf.home.appTextAllLinks.get(i));
			//					break;
			//				}
			//			}
			//			sf.seleU.wait(2000);

			// 2- Enter Account to be Searched in global search
			sf.seleU.clearAndEnterText(sf.orders.globalSearch, orderNumber);
			reportStatusPass(methodName + " Entered the order number as " + sf.dataInput.orderNumber, true, true);
			sf.seleU.hardwait(1000);
			sf.seleU.enterText(sf.orders.globalSearch, Keys.ENTER);

			sf.seleU.wait(5000);
			// // Iterate and Find the order and click on order
			boolean isOrderFound = false;
			for (int i = 0; i < sf.orders.ordersAllRecords.size(); i++) {
				sf.seleU.wait(8000);
				if (sf.seleU.getTextFromWebElement(sf.orders.globalSerachOrdersAllRecords.get(i)).trim()
						.equals(sf.dataInput.orderNumber)) {
					sf.seleU.clickElementByJSE(sf.orders.globalSerachOrdersAllRecords.get(i));
					isOrderFound = true;
					break;
				}
			}

			if (isOrderFound) {
				reportStatusPass(methodName + " Clicked on order " + sf.dataInput.orderNumber, true, true);
			} else {
				reportStatusFail(methodName + " No order found as " + sf.dataInput.orderNumber, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Orders Object", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 	 *                    Search the order from HOme Tab Menu
	 */
	public void globalSearchOrderFromHomeMenu(String orderNumber) throws IOException {
		try {
			String methodName = "SFDC_Global"
					+ " Search Orders_From_DropDownMenu@: ";
			System.out.println(orderNumber);
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.homeMenuOrders);
			reportStatusPass(methodName + " Selected Orders from Home Menu", true, false);
			sf.seleU.refreshPage();
			sf.seleU.wait(4000);

			// Click on app launcher and search for order
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);

			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, orderNumber);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if sercah more .. is displayed then perform else part
				sf.seleU.hardwait(6000);
				sf.seleU.enterText(sf.orders.ordersGlobalSearch, orderNumber);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.orders.ordersGlobalSearch, Keys.ENTER);
			}

			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);

			//			// 2- Enter Account to be Searched in global searched the
			//			sf.seleU.clearAndEnterText(sf.orders.ordersGlobalSearch, orderNumber);
			//			reportStatusPass(methodName + " Entered the order number as " + orderNumber, true, true);
			//			sf.seleU.wait(4000);
			//			sf.seleU.enterText(sf.orders.ordersGlobalSearch, Keys.ENTER);

			sf.seleU.wait(2000);
			// // Iterate and Find the order and click on order
			boolean isOrderFound = false;
			for (int i = 0; i < sf.orders.ordersAllRecordList.size(); i++) {
				sf.seleU.wait(6000);
				if (sf.seleU.getElementAttribute(sf.orders.ordersAllRecordList.get(i), "title").trim()
						.equals(orderNumber)) {
					sf.seleU.wait(4000);
					sf.seleU.clickElementByJSE(sf.orders.ordersAllRecordList.get(i));
					isOrderFound = true;
					break;
				}
			}

			if (isOrderFound) {
				reportStatusPass(methodName + " Clicked on order " + orderNumber, true, true);
			} else {
				reportStatusFail(methodName + " No order found as " + orderNumber, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Orders Object", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 *                    Select Order from Menu Drop down
	 * 
	 *                     Enter order to be Searched in the sub list
	 * 
	 *                     Iterate and Find the order and click on order
	 * 
	 */
	public void validateOrderListViewIcon() throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";
			if (!InputData.env.equals("WADEVQA")) {
				sf.seleU.switchToDefaultContent();
			}
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			// Select Home
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.homeMenuOrders);
			reportStatusPass(methodName + " Selected Orders from Home Menu", true, false);
			//	sf.seleU.refreshPage();
			sf.seleU.wait(4000);

			// select all Master Order view option	
			// 2- Select Recently View Order option
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.orders.allMasterOrderIconOption);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected all Master Order Option", true, false);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.orders.quickFilterButton);
			reportStatusPass(methodName + "Clicked on quick Filter apply button", true, false);
			sf.seleU.wait(1000);

			// validate whether the order record type is Master Order 
			if(sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeMasterOrder).trim().equals("Master Order"))
				reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.orders.orderRecordType) + " " + 
						sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeEquals) + " " +
						sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeMasterOrder), true, false);
			else
				reportStatusFail(methodName + sf.seleU.getTextFromWebElement(sf.orders.orderRecordType) + " " + 
						"Not equals" + " " +sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeMasterOrder), true);

			sf.seleU.clickElementByJSE(sf.orders.quickFilterButton);
			reportStatusPass(methodName + "Clicked on quick Filter apply button again to close it", true, false);

			// select all Draft Order view option	
			// 2- Select Draft Order Option
			sf.seleU.clickElementByJSE(sf.home.listViewIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.orders.allDraftOrdersListIcon);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Selected all Draft Order Option", true, false);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.orders.quickFilterButton);
			reportStatusPass(methodName + "Clicked on quick Filter apply button", true, false);
			sf.seleU.wait(1000);

			// validate whether the order record type is Master Order 
			if(sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeNotEquals).trim().equals("not equal to"))
				reportStatusPass(methodName + sf.seleU.getTextFromWebElement(sf.orders.orderRecordType) + " " + 
						sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeNotEquals) + " " +
						sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeMasterOrder), true, false);
			else
				reportStatusFail(methodName + sf.seleU.getTextFromWebElement(sf.orders.orderRecordType) + " " + 
						"equals" + " " +sf.seleU.getTextFromWebElement(sf.orders.orderRecordTypeMasterOrder), true);

			sf.seleU.clickElementByJSE(sf.orders.quickFilterButton);
			reportStatusPass(methodName + "Clicked on quick Filter apply button again to close it", true, false);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.orders.quickFilterButton);
			reportStatusPass(methodName + "Clicked on quick Filter apply button again to close it", true, false);
			sf.seleU.wait(1000);

		} catch (Throwable e) {
			reportStatusFail(" Error in list view icon", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 * 
	 * 
	 */
	public void verifyUpcomingTasksDueAndComplete() throws IOException {
		try {
			String methodName = "SFDC_OrdersUpcoming_Task@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			if (sf.seleU.isElementDisplayed(sf.orDetails.upcomingTaskNotification)) {
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.orDetails.upcomingTaskNotification);
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " Selected Upcoming Task notofication Tab", true, false);
			}

			sf.seleU.hardwait(2000);

			// Verify Subject in Task Details
			verifyFieldDisplayed("Task Priority", sf.orDetails.taskPriority);

			// Verify Subject in Task Details
			verifyFieldDisplayed("Task Due Date", sf.orDetails.taskDueDate);
			sf.seleU.hardwait(2000);
			// Verify Subject in Task Details
			verifyFieldDisplayed("Task SUbject", sf.orDetails.taskSubject);

			// Verify Subject in Task Details
			verifyFieldDisplayed("Order No Realted to", sf.orDetails.taskOrderRealtedTo);
			sf.seleU.hardwait(3000);

			if (sf.seleU.isElementDisplayed(sf.orDetails.markCompleteButton)) {
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.orDetails.markCompleteButton);
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + " Selected Complete Task Button", true, false);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for upcoming tasks in order page", e);
			e.printStackTrace();
		}
	}



	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 * 
	 * 
	 */
	public void verifyNotificationIconStatus_ForTaskFailure() throws IOException {
		try {
			String methodName = "SFDC_Verify_Failure_Notification_Icon_Msg@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			if (sf.seleU.isElementDisplayed(sf.orders.notificationiconButton)) {
				sf.seleU.hardwait(1000);
				sf.seleU.clickElementByJSE(sf.orders.notificationiconButton);
				reportStatusPass(methodName + " cicked on the notification icon button", true, false);
				sf.seleU.hardwait(1000);
				verifyFieldDisplayed("Task Failure notification ", sf.orders.notificationiconPopUpOrderFailureMsg.get(0));
				reportStatusPass(methodName + " cicked on the notification icon message", true, false);
			} else {
				reportStatusFail(" Notification icon is not displayed ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for upcoming tasks in order page", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2. Verify RPA task failed notification in delivery profile
	 * 
	 * 
	 */
	public void verifyRPATaskFailNotificationIconStatus(String failureTaskName) throws IOException {
		try {
			String methodName = "SFDC_Verify_RPA_TaskFailure_Notification_Icon_Msg@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			if (sf.seleU.isElementDisplayed(sf.orders.notificationiconButton)) {
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.orders.notificationiconButton);
				sf.seleU.hardwait(2000);


				if(failureTaskName.equals("createAccount")) {
					List<WebElement> rpaCreateAccountTaskfailed = driver.findElements(By.xpath("//*[contains(text(),'RPA Task Failed - "+sf.dataInput.orderNumber+"')]"));
					verifyFieldDisplayed("Task notification ", rpaCreateAccountTaskfailed.get(0));
					verifyFieldDisplayed("create account task notification Message ", sf.orders.createAccountNotificationIconMessage);
					sf.seleU.clickElementByJSE(rpaCreateAccountTaskfailed.get(0));

				} else {

					List<WebElement> rpaCreateWorkOrderTaskfailed = driver.findElements(By.xpath("//*[contains(text(),'RPA Task Failed- "+sf.dataInput.orderNumber+"')]"));
					verifyFieldDisplayed("Task notification ", rpaCreateWorkOrderTaskfailed.get(0));
					verifyFieldDisplayed("create work order task notification Message ", sf.orders.workOrderNotificationIconMessage);
					sf.seleU.clickElementByJSE(rpaCreateWorkOrderTaskfailed.get(0));
				}

				// after clicking the notification it should point to manual queue cable error handling page			
				sf.seleU.hardwait(2000);
				verifyFieldDisplayed("Task notification ", sf.mques.cableErrorHandlingOrderQueueTextHeader);

				sf.seleU.hardwait(5000);
				reportStatusPass(methodName + " clicked on the notification icon", true, false);
			} else {
				reportStatusFail(" Notification icon is not displayed ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for upcoming tasks in order page", e);
			e.printStackTrace();
		}
	}


	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 *
	 */
	public void verifyNotificationInIcon() throws IOException {
		try {
			String methodName = "SFDC_Verify_Cancel_Notification_Icon_Msg@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			sf.seleU.refreshPage();
			sf.seleU.refreshPage();
			sf.seleU.wait(6000);
			if (sf.seleU.isElementDisplayed(sf.orders.notificationiconButton)) {

				sf.seleU.hardwait(4000);
				sf.seleU.clickElementByJSE(sf.orders.notificationiconButton);
				sf.seleU.wait(6000);
				List<WebElement> notificationiconPopUpOrderCancelMsg = driver.findElements(By.xpath("//span[contains(text(),'Order "+sf.omData.canceledOrderNumber+" is')][@class='notification-text-title uiOutputText']"));
				verifyFieldDisplayed("Task notification ", notificationiconPopUpOrderCancelMsg.get(0));
				sf.seleU.clickElementByJSE(notificationiconPopUpOrderCancelMsg.get(0));
				reportStatusPass(methodName + " cicked on the notification icon", true, false);

			} else {
				reportStatusFail(" Notification icon is not displayed ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for upcoming tasks in order page", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 *
	 */
	public void verifyNotificationInIconToAE() throws IOException {
		try {
			String methodName = "SFDC_Verify_Notification_To_AE_Msg@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			if (sf.seleU.isElementDisplayed(sf.orders.notificationiconButton)) {

				sf.seleU.hardwait(1000);
				sf.seleU.clickElementByJSE(sf.orders.notificationiconButton);
				sf.seleU.hardwait(1000);
				List<WebElement> notificationiconPopUpOrderCancelMsg = driver.findElements(By.xpath("//*[contains(text(),'Notification for Cancellation')]"));
				verifyFieldDisplayed("Task notification ", notificationiconPopUpOrderCancelMsg.get(0));
				sf.seleU.clickElementByJSE(notificationiconPopUpOrderCancelMsg.get(0));
				reportStatusPass(methodName + " cicked on the notification icon", true, false);

			} else {
				reportStatusFail(" Notification icon is not displayed ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for upcoming tasks in order page", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 * 
	 * 
	 */
	public void verifyOpenTaskStatus_ForReinstate() throws IOException {
		try {
			String methodName = "SFDC_Orders_Task_Status@: ";

			// Verify header reinstate message
			verifyFieldValue("Task Reinstate Header Text", sf.orDetails.taskNotificationHeaderText, sf.omData.openTaskNotification_ReinsateHeaderText );

			// Verify Priority in Task Details
			verifyFieldDisplayed("Task Priority", sf.orDetails.taskPriority);

			// Verify Status in Task Details
			verifyFieldDisplayed("Task Status", sf.orDetails.taskStatus);

			// Verify DueDate in Task Details
			verifyFieldValue("Task Due Date", sf.orDetails.taskDueDate, sf.omData.notificationDueDate);
			sf.seleU.hardwait(2000);

			// Verify Subject in Task Details
			verifyFieldValue("Task SUbject", sf.orDetails.taskSubject, sf.omData.openTaskNotification_ReinsateMessage);

			// Verify Comments in Task Details
			verifyFieldValue("Comments", sf.orDetails.taskComments, sf.omData.openTaskNotification_ReinsateComments);

			// Verify Order No in Task Details
			verifyFieldValue("Order No Realted to", sf.orDetails.taskOrderRealtedTo, sf.dataInput.orderNumber);
			sf.seleU.hardwait(3000);

			//  Verify Task assigned to SD
			verifyFieldDisplayed("Task Assigned To", sf.orDetails.taskAssignedTo);

			//			if (sf.seleU.isElementDisplayed(sf.orDetails.markCompleteButton)) {
			//				sf.seleU.hardwait(2000);
			//				sf.seleU.clickElementByJSE(sf.orDetails.markCompleteButton);
			//				sf.seleU.hardwait(2000);
			//				reportStatusPass(methodName + " Selected Complete Task Button", true, false);
			//			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Open Tasks Notification", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 * 
	 * 
	 */
	public void verifyOpenTaskStatus_ForCancel_FromAE() throws IOException {
		try {
			String methodName = "SFDC_Orders_Task_Status@: ";

			sf.seleU.wait(6000);
			// Verify Priority in Task Details
			verifyFieldDisplayed("Task Priority", sf.orDetails.taskPriority);

			sf.seleU.ScrolltoElement(sf.orDetails.taskStatus);
			// Verify Status in Task Details
			verifyFieldDisplayed("Task Status", sf.orDetails.taskStatus);

			// Verify DueDate in Task Details
			verifyFieldValue("Task Due Date is current date plus 2 days", sf.orDetails.taskDueDate, sf.omData.notificationDueDate);
			sf.seleU.hardwait(2000);

			// Verify Subject in Task Details
			verifyFieldValue_WithFormat("Task SUbject", sf.orDetails.taskSubject, sf.omData.openTaskNotification_CancelMessage);

			// Verify Comments in Task Details
			verifyFieldValue_WithFormat("Comments", sf.orDetails.taskComments, sf.omData.openTaskNotification_CancelComments);

			// Verify Order No in Task Details
			verifyFieldValue("Order No Realted to", sf.orDetails.taskOrderRealtedTo, sf.omData.canceledOrderNumber);

			//	verifyFieldDisplayed("Order No Realted to", sf.orDetails.taskOrderRealtedTo);

			// Task Assigned To
			//	verifyFieldValue("Task Assigned To", sf.orDetails.taskAssignedTo, sf.dataInput.userProfileDelivery);

			verifyFieldDisplayed("Task Assigned To", sf.orDetails.taskAssignedTo);
			sf.seleU.hardwait(3000);


		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Open Tasks Notification from AE", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 * 
	 * 
	 */
	public void verifyOpenTaskStatus_ForRequestCancelFromSD() throws IOException {
		try {
			String methodName = "SFDC_VerifyOpenTaskStatus_ForRequestCancelFromSD @: ";


			// Verify Priority in Task Details
			verifyFieldDisplayed("Task Priority", sf.orDetails.taskPriority);

			sf.seleU.ScrolltoElement(sf.orDetails.taskStatus);
			// Verify Status in Task Details
			verifyFieldDisplayed("Task Status", sf.orDetails.taskStatus);

			// Verify DueDate in Task Details
			verifyFieldValue("Task Due Date is current date plus 2 days", sf.orDetails.taskDueDate, sf.omData.notificationDueDate);
			sf.seleU.hardwait(2000);

			// Verify Subject in Task Details
			verifyFieldValue_WithFormat("Task SUbject", sf.orDetails.taskSubject, sf.omData.openTaskNotification_CancellationMessage);

			// Verify Comments in Task Details
			verifyFieldValue_WithFormat("Comments", sf.orDetails.taskComments, sf.omData.openTaskNotification_CancellationComments);

			// Verify SD entered Comments in Task Details
			verifyFieldValue_WithFormat("Comments", sf.orDetails.taskComments, sf.omData.orderRequestCancellation_Message_Enter);

			// Verify Order No in Task Details
			verifyFieldValue("Order No Realted to", sf.orDetails.taskOrderRealtedTo, sf.omData.canceledOrderNumber);

			//	verifyFieldDisplayed("Order No Realted to", sf.orDetails.taskOrderRealtedTo);

			// Task Assigned To
			verifyFieldDisplayed("Task Assigned To", sf.orDetails.taskAssignedTo);
			sf.seleU.hardwait(3000);


		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Open Tasks Notification from SD", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 *                     1.Add SIte COntact to order if no site was added during PBF order creation
	 * 
	 * 
	 */
	public void updatePBFOrderWithSiteContact() throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";
			if (InputData_Communities.commPBFAddSiteContact.equals("No")) {
				updateOrderDetails();
				sf.dataInput.siteContactName = InputData_Communities.commPBFContactFullName;
				SFDC_Quote_SelectSiteContact_Page.selectExistingSiteContact();
			}
		} catch (Throwable e) {
			reportStatusFail(" Could not add site contact to order on order page", e);
			e.printStackTrace();
		}
	}
	
	/**Pankaj Agarwal 1st March 2022
	 * @throws IOException
	 * 
	 *                     1.Add SIte COntact to multisite order if no site was added during PBF order creation
	 * 
	 * 
	 */
	public void updatePBFMultisiteOrderWithSiteContact() throws IOException {
		try {
			String methodName = "SFDC_Multisite Orders@: ";
		//	if (InputData_Communities.commPBFAddSiteContact.equals("No") && InputData_Communities.commPBFESignature.equalsIgnoreCase("No")) {
				updateOrderDetails();
				sf.dataInput.siteContactName = InputData_Communities.commPBFContactFullName;
				SFDC_Quote_SelectSiteContact_Page.selectExistingSiteContact();
		//	}
		} catch (Throwable e) {
			reportStatusFail(" Could not add site contact to order on order page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Select update order details option from order details
	 *                     page
	 * 
	 * 
	 */
	public void updateOrderDetails() throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			if (!sf.seleU.isElementDisplayed(sf.orDetails.updateOrderDetails)) {
				sf.seleU.clickElementByJSE(sf.orDetails.showMoreActions);
				sf.seleU.hardwait(2000);
			}
			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElementPageCenter(sf.orDetails.updateOrderDetails);
			sf.seleU.clickElementByJSE(sf.orDetails.updateOrderDetails);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Selected Updated Order Details Tab", true, false);


			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for clicking updated order details tab in order page", e);
			e.printStackTrace();
		}
	}

	/**PA PI02-SP02
	 * @throws IOException
	 * 
	 *                     Cancel order in order page from AE Profile
	 * 
	 * 
	 */
	public void cancel_Order() throws IOException {
		try {
			String methodName = "SFDC_CancelOrder@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			sf.seleU.scrollToTop();
			sf.omData.canceledOrderNumber = sf.dataInput.orderNumber;
			//
			sf.seleU.wait(8000);
			if (sf.seleU.isElementDisplayed(sf.orders.cancelOrderButton)) {	
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.orders.cancelOrderButton);
				sf.seleU.hardwait(4000);
				reportStatusPass(methodName + " Clicked on Cancel Order Button", true, false);
			}

			sf.seleU.wait(10000);
			sf.seleU.waitElementToBeVisible(sf.orders.iframeCancelOrder);
			sf.seleU.switchToFrame(sf.orders.iframeCancelOrder);
			// Confirm cancellation

			verifyFieldDisplayed("Confirm Cancellation ", sf.orders.orderCancelationTextText.get(0));
			verifyFieldDisplayed("Do you want to cancel Text ", sf.orders.orderDoYouWantCancelText);
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(sf.orders.conformationNextButton);
			sf.seleU.clickElementByJSE(sf.orders.conformationNextButton);
			reportStatusPass(methodName + " Clicked on confomation next Button after clicking cancel button", true, false);
			sf.seleU.wait(30000);

			//	verifyFieldDisplayed("Order Submission Results Text", sf.orders.orderSubmissionResultText.get(1));
			//			verifyFieldDisplayed("Waiting for the plan to be frozen text ", sf.orders.orderWaitingForPlanFrozenText);
			sf.seleU.wait(6000);

			// It will go in if condition only PONR is reached and it should reflect that " Your order has crossed Point of No Return"
			if(sf.seleU.isElementDisplayed(sf.orders.orderCancelMsgText)) {

				verifyFieldDisplayed("Your order has crossed Point of No Return", sf.orders.orderCancelMsgText);
				sf.seleU.hardwait(4000);
				sf.seleU.ScrolltoElement(sf.orders.ponrReachedCancelNextButton);
				sf.seleU.clickElementByJSE(sf.orders.ponrReachedCancelNextButton);
				reportStatusPass(methodName + " Clicked on next Button after clicking cancel button", true, false);

			} else {

				sf.seleU.hardwait(4000);
				sf.seleU.ScrolltoElement(sf.orders.nextButton);
				sf.seleU.clickElementByJSE(sf.orders.nextButton);
				reportStatusPass(methodName + " Clicked on next Button after clicking cancel button", true, false);
			}

			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(8000);

		} catch (Throwable e) {
			reportStatusFail(" Falied to click on the cancel order button", e);
			e.printStackTrace();
		}
	}

	/**PA PI03-SP01
	 * @throws IOException
	 * 
	 *                     Request Order Cancellation from Service Delivery profile
	 * 
	 * 
	 */
	public void request_OrderCancellation() throws IOException {
		try {
			String methodName = "SFDC_RequestCancelOrder@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			sf.seleU.scrollToTop();
			sf.omData.canceledOrderNumber = sf.dataInput.orderNumber;
			//
			if (sf.seleU.isElementDisplayed(sf.orders.requestCancelationOrderButton)) {	
				sf.seleU.hardwait(1000);
				sf.seleU.clickElementByJSE(sf.orders.requestCancelationOrderButton);
				sf.seleU.hardwait(4000);
				reportStatusPass(methodName + " Clicked on Request Cancelation Order Button", true, false);
			}

			sf.seleU.wait(10000);
			sf.seleU.waitElementToBeVisible(sf.orders.iframeCancelOrder);
			sf.seleU.switchToFrame(sf.orders.iframeCancelOrder);
			verifyFieldDisplayed("Capture issue Details ", sf.orders.orderRequestCancellationResultText);
			sf.seleU.clearAndEnterText(sf.orders.issueEnterText, sf.omData.orderRequestCancellation_Message_Enter);
			reportStatusPass(methodName + " Enter text is " + sf.omData.orderRequestCancellation_Message_Enter, true, false);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.orders.issueEnterText, Keys.TAB);
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(sf.orders.proceedButton);
			sf.seleU.clickElementByJSE(sf.orders.proceedButton);
			reportStatusPass(methodName + " Clicked on proceed Button after clicking cancel button", true, false);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Falied to click on the request cancellation button", e);
			e.printStackTrace();
		}
	}

	/**PA PI03-SP01
	 * @throws IOException
	 * 
	 *                     Reinstate Order in freezed status from AE profile 
	 * 
	 * 
	 */
	public void reinstate_OrderInFrozenStatus() throws IOException {
		try {
			String methodName = "SFDC_Reinstate_OrderIn_FrozenStatus@: ";

			// Click on app launcher and search for order
			sf.seleU.hardwait(4000);
			sf.seleU.scrollToTop();
			//		sf.omData.canceledOrderNumber = sf.dataInput.orderNumber;
			//
			if (sf.seleU.isElementDisplayed(sf.orders.reinstateOrderButton)) {	
				sf.seleU.hardwait(1000);
				sf.seleU.clickElementByJSE(sf.orders.reinstateOrderButton);
				sf.seleU.hardwait(4000);
				reportStatusPass(methodName + " Clicked on Reinstate Order Button", true, false);
			}

			sf.seleU.wait(10000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Falied to click on the reinstate order button", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     1.Click on task notification pending link
	 * 
	 *                     2.Verify the fields for the existing order.
	 * 
	 *                     3.Click on complete button and the task status will
	 *                     change from In progress to complete
	 * 
	 * 
	 */
	public void verifyCommitedInServiceDate() throws IOException {
		try {
			String methodName = "SFDC_Verify_InService_Date@: ";

			// Click on app launcher and search for order
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(4000);
			if (sf.seleU.isElementDisplayed(sf.orders.commitedInserviceDateForRDI)) {
				sf.seleU.hardwait(1000);

				verifyFieldDisplayed("Commited In Service Date is ", sf.orders.commitedInserviceDateForRDI );
				verifyFieldDisplayed("Commited In Service Date Refresh Button ", sf.orders.commitedInserviceDatRefreshClick );
				//	reportStatusPass(methodName + " Commited Inservice date is present in the flex card", true, false);

			} else {
				reportStatusFail(" Service Date is not displayed ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verified Commited Inservice date is present in the flex card", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed " + sf.seleU.getTextFromWebElement(element),
						true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/*
	 * * @throws IOException
	 * 
	 * Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";
			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/*
	 * * @throws IOException
	 * 
	 * Verify Field value matches the expected result
	 */
	public void verifyFieldValue_WithFormat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			String methodName = "SFDC_Orders@: ";
			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).toLowerCase().contains(expectedText.toLowerCase())) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + sf.seleU.getTextFromWebElement(element), true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
				+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/** @CreatedBy Sakshi Sakshi 17.08.2021
	 * @throws IOException
	 * 
	 *                     Submit order in order page from BA Profile
	 * 
	 * 
	 */
	public void submit_Order() throws IOException {
		try {
			String methodName = "SFDC_CancelOrder@: ";

			// Go to top of page
			sf.seleU.hardwait(4000);
			sf.seleU.scrollToTop();
			//submit order by clicking on more action item at right corner of the page
			sf.seleU.wait(8000);
			sf.seleU.clickElementByJSE(sf.orders.moreActionButton);
			Boolean status = sf.seleU.isElementDisplayed(sf.orders.submitOrderButton);
			if (status) {	
				sf.seleU.wait(4000);
				sf.seleU.clickElementByJSE(sf.orders.submitOrderButton);
				sf.seleU.hardwait(4000);
				reportStatusPass(methodName + " Clicked on Submit Order Button", true, false);
				sf.seleU.hardwait(4000);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

}
