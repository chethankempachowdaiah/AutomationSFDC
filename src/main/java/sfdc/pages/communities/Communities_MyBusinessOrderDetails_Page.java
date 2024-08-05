package sfdc.pages.communities;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class Communities_MyBusinessOrderDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages

	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_MyBusinessOrderDetails_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_My Business Order Details@:";
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Verify Order Details: a.Order# b. Account Name: Should be
	 *                     Business Account Name c.Order Start Date d.Status
	 *                     e.Service Account#: Should be blank if order is not yet
	 *                     activated f.Activated Date: Should be blank if order is
	 *                     not yet activated g.Monthly Charges: Exactly same as on
	 *                     the Order h. One time charge: Exactly same as on the
	 *                     Order i.Customer Name: First Name and Last Name
	 *                     j.Products: Main Products and Sub-Products in Bullets"
	 *
	 */
	public void verifyPBFOrderDetails() throws IOException {
		try {

			reportStatusPass(methodName + " Selected Order : " + sf.dataInput.orderNumber, true, true);
			sf.seleU.wait(5000);

			verifyFieldValue("Order Number", sf.comOrderDetails.orderNumber, sf.dataInput.orderNumber);
			verifyFieldValue("Account Name Text value", sf.comOrderDetails.accountName, sf.dataInput.businessAccountName);

			verifyFieldValue("Order Status", sf.comOrderDetails.orderStatus, sf.dataInput.orderStatusReadyToSubmit);
			verifyFieldDisplayed("Order Start Date", sf.comOrderDetails.orderStartDate);
			reportStatusPass(methodName + " Order Start date on order is "
					+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderStartDate), false, false);

			verifyFieldDisplayed("Service account Name", sf.comOrderDetails.serviceAccountName);
			verifyFieldDisplayed("Activated Date", sf.comOrderDetails.orderActivatedDate);

			verifyFieldValue("Monthly Charge", sf.comOrderDetails.orderMonthlyCharges, InputData_Communities.commPBFProductPrice);
			verifyFieldValue("One Time Charge", sf.comOrderDetails.orderOneTimeCharges, InputData_Communities.commPBFOneTimeFees);
			verifyFieldValue("Customer Name", sf.comOrderDetails.customerName, InputData_Communities.commPBFContactFullName);

			verifyFieldValue("Main Line Product", sf.comOrderDetails.orderMainProduct, InputData_Communities.commPBFAddProductName);
			for (int i = 0; i < sf.comOrderDetails.orderProductList.size(); i++) {
				verifyFieldDisplayed("Products", sf.comOrderDetails.orderProductList.get(i));
				reportStatusPass(
						methodName + " Product Name " + i + " on order is "
								+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderProductList.get(i)),
								false, false);
				for (int j = 0; j < sf.comOrderDetails.orderProductSubList.size(); j++) {
					verifyFieldDisplayed("SubList for product "
							+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderProductList.get(i))
							+ " is present", sf.comOrderDetails.orderProductSubList.get(j));
					reportStatusPass(
							methodName + " Sublist : " + j + ") "
									+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderProductSubList.get(j)),
									false, false);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying PBF Order Details is unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Verify Order Details: a.Order# b. Account Name: Should be
	 *                     Business Account Name c.Order Start Date d.Status
	 *                     e.Service Account#: Should be blank if order is not yet
	 *                     activated f.Activated Date: Should be blank if order is
	 *                     not yet activated g.Monthly Charges: Exactly same as on
	 *                     the Order h. One time charge: Exactly same as on the
	 *                     Order i.Customer Name: First Name and Last Name
	 *                     j.Products: Main Products and Sub-Products in Bullets"
	 *
	 */
	public void myBusinessOrdersVerfifyOrderDetails(String orderStatus) throws IOException {
		try {

			reportStatusPass(methodName + " Selected Order : " + Global.dataInput.orderNumber, true, true);
			sf.seleU.wait(5000);

			if(sf.seleU.isElementDisplayed(sf.comMBC.orderNumberLinkForSubOrder)) {
				reportStatusPass(methodName + " Clicking suborder in multisite Order : " + Global.dataInput.orderNumber, true, true);
				Global.dataInput.orderNumber = sf.seleU.getTextFromWebElement(sf.comMBC.orderNumberLinkForSubOrder);
				sf.seleU.clickOnElement(sf.comMBC.orderNumberLinkForSubOrder);
				reportStatusPass(methodName + " Selected subOrder : " + Global.dataInput.orderNumber, true, true);
				sf.seleU.wait(5000);

			}
			verifyFieldValue("Order Number", sf.comOrderDetails.orderNumber, Global.dataInput.orderNumber);
			verifyFieldDisplayed("Account Name Text value", sf.comOrderDetails.accountName);
			reportStatusPass(methodName + " Account Name on order is "
					+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.accountName), true, false);

			verifyFieldValue("Order Status", sf.comOrderDetails.orderStatus, orderStatus);
			verifyFieldDisplayed("Order Start Date", sf.comOrderDetails.orderStartDate);
			reportStatusPass(methodName + " Order Start date on order is "
					+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderStartDate), false, false);

			verifyFieldDisplayed("Service account Name", sf.comOrderDetails.serviceAccountName);
			verifyFieldDisplayed("Activated Date", sf.comOrderDetails.orderActivatedDate);

			verifyFieldDisplayed("Monthly Charge", sf.comOrderDetails.orderMonthlyCharges);
			reportStatusPass(methodName + " Monthly Charges on order is "
					+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderMonthlyCharges), false, false);

			verifyFieldDisplayed("One Time Charge", sf.comOrderDetails.orderOneTimeCharges);
			reportStatusPass(methodName + " One Time Charge on order is "
					+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderOneTimeCharges), false, false);

			verifyFieldDisplayed("Customer Name", sf.comOrderDetails.customerName);
			reportStatusPass(methodName + " Customer Name on order is "
					+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.customerName), false, false);

			for (int i = 0; i < sf.comOrderDetails.orderProductList.size(); i++) {
				verifyFieldDisplayed("Products", sf.comOrderDetails.orderProductList.get(i));
				reportStatusPass(
						methodName + " Product Name " + i + " on order is "
								+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderProductList.get(i)),
								false, false);
				for (int j = 0; j < sf.comOrderDetails.orderProductSubList.size(); j++) {
					verifyFieldDisplayed("SubList for product "
							+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderProductList.get(i))
							+ " is present", sf.comOrderDetails.orderProductSubList.get(j));
					reportStatusPass(
							methodName + " Sublist : " + j + ") "
									+ sf.seleU.getTextFromWebElement(sf.comOrderDetails.orderProductSubList.get(j)),
									false, false);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Order Details is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Order of all status types one by one : Cancelled,
	 *                     Activated, In Progress and Submit
	 * 
	 *                     Verify Order Details: a.Order# b. Account Name: Should be
	 *                     Business Account Name c.Order Start Date d.Status
	 *                     e.Service Account#: Should be blank if order is not yet
	 *                     activated f.Activated Date: Should be blank if order is
	 *                     not yet activated g.Monthly Charges: Exactly same as on
	 *                     the Order h. One time charge: Exactly same as on the
	 *                     Order i.Customer Name: First Name and Last Name
	 *                     j.Products: Main Products and Sub-Products in Bullets"
	 *
	 */
	public void myBusinessOrdersVerfifyOrderDetails() throws IOException {
		try {

			verifyFieldDisplayed("Communities My Business Cases Header", sf.comMBC.myBusinessCasesHeader);
			verifyFieldDisplayed("Communities Order Summary Table Header", sf.comMBC.orderSummary_tableHeader);

			// Select Order status wise and verify details
			sf.seleU.clickElementByJSE(sf.comMBC.orderFilterInProgress);
			sf.seleU.wait(4000);
			if (verifyElementPresent(sf.comMBC.orderNumberLinkForInProgressOrder)) {
				Global.dataInput.orderNumber = sf.seleU
						.getTextFromWebElement(sf.comMBC.orderNumberLinkForInProgressOrder.get(0));
				sf.seleU.clickOnElement(sf.comMBC.orderNumberLinkForInProgressOrder.get(0));
				myBusinessOrdersVerfifyOrderDetails(Global.dataInput.orderStatusInProgress);
				sf.seleU.clickElementByJSE(sf.comMBC.orderSummaryLink);
			} else {
				reportStatusPass(methodName + "There is no order Listed of In-Progress Status", true, true);
				sf.seleU.refreshPage();
			}

			sf.seleU.wait(15000);
			sf.seleU.clickElementByJSE(sf.comMBC.orderFilterActivated);
			sf.seleU.wait(2000);
			if (verifyElementPresent(sf.comMBC.orderNumberLinkForActivatedOrder)) {
				Global.dataInput.orderNumber = sf.seleU
						.getTextFromWebElement(sf.comMBC.orderNumberLinkForActivatedOrder.get(0));

				sf.seleU.clickOnElement(sf.comMBC.orderNumberLinkForActivatedOrder.get(0));
				myBusinessOrdersVerfifyOrderDetails(Global.dataInput.orderStatusActivated);
				sf.seleU.clickElementByJSE(sf.comMBC.orderSummaryLink);
			} else {
				reportStatusPass(methodName + " There is no order Listed of Activated Status", true, true);
				sf.seleU.refreshPage();
			}
			sf.seleU.wait(15000);
			sf.seleU.clickElementByJSE(sf.comMBC.orderFilterCancelled);
			sf.seleU.wait(2000);
			if (verifyElementPresent(sf.comMBC.orderNumberLinkForCancelledOrder)) {
				Global.dataInput.orderNumber = sf.seleU
						.getTextFromWebElement(sf.comMBC.orderNumberLinkForCancelledOrder.get(0));

				sf.seleU.clickOnElement(sf.comMBC.orderNumberLinkForCancelledOrder.get(0));
				myBusinessOrdersVerfifyOrderDetails(Global.dataInput.orderStatusCancelled);
				sf.seleU.clickElementByJSE(sf.comMBC.orderSummaryLink);
			} else {
				reportStatusPass(methodName + "There is no order Listed of Cancelled Status", true, true);
				sf.seleU.refreshPage();
			}

			sf.seleU.wait(15000);
			sf.seleU.clickElementByJSE(sf.comMBC.orderFilterReadyToSubmit);
			sf.seleU.wait(2000);
			if (verifyElementPresent(sf.comMBC.orderNumberLinkForSubmitOrder)) {
				Global.dataInput.orderNumber = sf.seleU
						.getTextFromWebElement(sf.comMBC.orderNumberLinkForSubmitOrder.get(0));

				sf.seleU.clickOnElement(sf.comMBC.orderNumberLinkForSubmitOrder.get(0));
				myBusinessOrdersVerfifyOrderDetails(Global.dataInput.orderStatusReadyToSubmit);
				sf.seleU.clickElementByJSE(sf.comMBC.orderSummaryLink);
			} else {
				reportStatusPass(methodName + "There is no order Listed of Ready to Submit Status", true, true);
			}
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Viewing Order Details is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (expectedText.contains(sf.seleU.getTextFromWebElement(element))) {
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

	/**
	 * @throws IOException
	 * 
	 *                     Verify element is present
	 */
	public boolean verifyElementPresent(List<WebElement> element) throws IOException {

		boolean presence = false;
		try {

			if (element.size() > 0)
				presence = true;
			else
				presence = false;

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying element present", e);
			e.printStackTrace();
		}
		return presence;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify field is displayed on page or not
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying field is displayed", e);
			e.printStackTrace();
		}
	}



	/**
	 * @throws IOException
	 * 
	 *                     verify Order details and click on Complete to fill customer info form 
	 */
	public void clickOnIncompleteOrderNumber() throws Exception {
		try {
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.comMBC.orderFilterReadyToSubmit);
			sf.seleU.waitForLoading();
			verifyElementPresent(sf.comMBC.orderNumberLinkForSubmitOrder);
			verifyElementPresent(sf.comMBC.orderNumberLinkForIncomplete);
			for (int i = 0; i < sf.comMBC.orderNumberLinkForSubmitOrder.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.comMBC.orderNumberLinkForSubmitOrder.get(i)).contains(Global.dataInput.orderNumber)) {
					Global.dataInput.orderNumber = sf.seleU.getTextFromWebElement(sf.comMBC.orderNumberLinkForSubmitOrder.get(i));
					sf.seleU.clickElementByJSE(sf.comMBC.orderNumberLinkForSubmitOrder.get(i));
					//myBusinessOrdersVerfifyOrderDetails(Global.dataInput.orderStatusReadyToSubmit);
					sf.seleU.waitForLoading();
					sf.seleU.clickElementByJSE(sf.comMBC.completeFormLink);
					reportStatusPass(methodName + "clicked to complete order", true, true);
					if (sf.seleU.isElementDisplayed(sf.comMBC.continueFormUpdatedLink)) {
						sf.seleU.clickElementByJSE(sf.comMBC.continueFormUpdatedLink);
						sf.seleU.hardwait(3000); 
					}
					break;
				}
			}
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " There is no order Listed of Ready to Submit Status", e);
			e.printStackTrace();
		} 

	}
}