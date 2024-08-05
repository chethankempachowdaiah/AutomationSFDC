package sfdc.pages.om;

import java.io.IOException;

import org.openqa.selenium.StaleElementReferenceException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 23/04/2020
 * 
 *         SFDC Pending Order Report(Manual Queue > Pending Report)
 * 
 *         Report: Orchestration Item with Order Order with Pending Task
 *
 */
public class SFDC_OrderWithPendingTask_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	String methodName;

	public SFDC_OrderWithPendingTask_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Order with Pending Task@: ";

	}

	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is Pending list
	 */
	public void verifyOrderInPendingList() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.pendingTask.ordersAllRows);

			// Iterate and Verify Order Present in the list
			boolean isOrderInList = false;
			for (int i = 0; i < sf.pendingTask.ordersAllRows.size(); i++) {
				try {
					if (sf.seleU.getTextFromWebElement(sf.pendingTask.ordersAllRows.get(i))
							.equals(sf.dataInput.orderNumber)) {
						sf.seleU.clickElementByJSE(sf.pendingTask.orchestrationItemAllRows.get(i));
					}
				} catch (StaleElementReferenceException e) {
				}
				isOrderInList = true;

			}

			if (isOrderInList) {

				reportStatusPass(
						methodName + " Verified Order " + sf.dataInput.orderNumber + " is added to the Pending Report",
						true, true);
			} else {
				reportStatusFail(methodName + " Verified Order " + sf.dataInput.orderNumber
						+ " is not added to the Pending Report", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification failure for order In Pending Order List", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Iterate and Verify Order is removed from pending list
	 */
	public void verifyOrderNotInPendingList() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.pendingTask.ordersAllRows);

			// Iterate and Verify Order is not Present in the list
			boolean isOrderInList = false;
			for (int i = 0; i < sf.pendingTask.ordersAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.pendingTask.ordersAllRows.get(i))
						.equals(sf.dataInput.orderNumber)) {
					sf.seleU.clickElementByJSE(sf.pendingTask.orchestrationItemAllRows.get(i));
					isOrderInList = true;
				}
			}

			if (isOrderInList) {
				reportStatusFail(" Verification failure for order not to be In pending list, Order "
						+ sf.dataInput.orderNumber + " is still there in Pending list", true);
			} else {
				reportStatusPass(
						methodName + " Verified Order " + sf.dataInput.orderNumber + " removed from pending list", true,
						true);
			}

		} catch (Throwable e) {
			reportStatusFail("Verification failure for order not to be In pending list", e);
			e.printStackTrace();
		}
	}
}
