package sfdc.pages.om;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 31/JAN/2020
 *
 *         SFDC Order Details Tab
 */
public class SFDC_OrderDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_OrderDetails_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Order_Details@: ";
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Draft
	 * 
	 *             3.Verify order status is Ready To Submit
	 * 
	 *             4.Verify 'select site contact' button is displayed and enabled
	 */
	public void verifyCreatedOrderInOrderDetails() throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.clickOnElementNumberoftimes(sf.orDetails.orderDetailsTab, 1);

			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusDraft);
			verifyOrderStatusInOrderDetails(sf.dataInput.orderStatusReadyToSubmit);
			verifySelectSiteContactEnabled();
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal
	 * 
	 * @throws IOException
	 * 
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Draft
	 * 
	 *             3.Verify order status is Ready To Submit
	 * 
	 *             Created this method as we don't need site contact
	 */
	public void verifyCreatedOrderInOrderDetailsTab(String orderStatus) throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTabs);

			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			String language=InputData_WA.language;
			if(language==null || language.equals("") || language.equalsIgnoreCase("English")) {
				verifyStatusInOrderDetails(sf.dataInput.orderStatusDraft);
			}
			else if(language.equalsIgnoreCase("French")){
				verifyStatusInOrderDetails(sf.dataInput.orderStatusDraftFr);		
			}			
			sf.seleU.refreshPage();
			// Click on Order Details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTabs);
			verifyOrderStatusInOrderDetails(orderStatus);
			sf.seleU.waitForLoading();

		} catch (Throwable e) {
			reportStatusFail(methodName + " Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Product details
	 */
	public void verifyProductDetails() throws IOException {

		try {
			// Click on Order Details tab
			sf.seleU.refreshPage();
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.orRelated.orderRelatedTab);

			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);
			boolean productFound = false;

			for (int i = 0; i < sf.orDetails.orderProductValueText.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.orDetails.orderProductValueText.get(i)).contains(InputData_Communities.commPBFAddProductName) || 
						sf.seleU.getTextFromWebElement(sf.orDetails.orderProductValueText.get(i)).contains(InputData_Communities.commPBFAddProductNameFr)) {
					reportStatusPass(methodName + " Found expected product in order details : "
							+ InputData_Communities.commPBFAddProductName, true, true);
					productFound = true;
					break;
				}
			}

			if (!productFound)
				reportStatusFail(methodName + " Could not find exepcted product in order details", true);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Billing Address
	 */
	public void verifyBillingAddress() throws IOException {

		try {
			sf.seleU.hardwait(6000);
			if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer")
					|| InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))
				sf.seleU.switchWindow(3);

			// Click on Order Details tab
			sf.seleU.refreshPage();
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.orDetails.orderDetailsTabs);

			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			if (sf.dataInput.billingAccountName == null)
				verifyFieldValue("Billing Address Value", sf.orDetails.billingAccountLink, "");
			else
				verifyFieldValue("Billing Address Value", sf.orDetails.billingAccountLink,
						sf.dataInput.billingAccountName);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Invalid billing details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Billing Address
	 */
	public void verifyNoBillingAddress() throws IOException {

		try {
			sf.seleU.hardwait(6000);

			// Click on Order Details tab
			if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer")
					|| InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))
				sf.seleU.switchWindow(3);

			sf.seleU.refreshPage();
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.orDetails.orderDetailsTab);

			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			if (sf.seleU.getTextFromWebElement(sf.orDetails.billingAccountLink).isEmpty())
				// ||
				// !sf.seleU.getTextFromWebElement(sf.orDetails.billingAccountLink).equalsIgnoreCase(sf.dataInput.billingAccountName))
				reportStatusPass(methodName + " Verified Billing Address is not updated/empty", true, true);
			else
				reportStatusFail(methodName + " Billing Address Changed!!", true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Invalid billing details", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *             Verify Product details
	 */
	public void verifyProductDetailsForMultisite() throws IOException {

		try {
			// Click on Order Details tab
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);

			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);
			boolean productFound = false, tvProductFound = false;

			sf.seleU.clickElementByJSE(sf.orRelated.orderProductViewALLClick);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			List<String> expectedProducts = new ArrayList<>();
			
			//Add Products for multiple sites one by one
			for (int i=0; i< InputData_Communities.mulPBFNumOfSites ; i++) {
				
				HashMap<String, String> siteWiseData = InputData_Communities.sitesData.get("Site" + (i+1));
				InputData_Communities.mulPBFSiteProductPlanName = siteWiseData.get("Product Plan Name");
				InputData_Communities.mulPBFSiteProductAddOnName = siteWiseData.get("Product AddOn Name");
				
				//Add Product Names to list
				if(InputData_Communities.mulPBFSiteProductPlanName.contains(",")) {
					expectedProducts.add(InputData_Communities.mulPBFSiteProductPlanName.split(",")[0]);
					expectedProducts.add(InputData_Communities.mulPBFSiteProductPlanName.split(",")[1]);
				} else {
					expectedProducts.add(InputData_Communities.mulPBFSiteProductPlanName);
				}
				//Add Addon
				if (!InputData_Communities.mulPBFSiteProductAddOnName.equals("NA")) {
					if(InputData_Communities.mulPBFSiteProductAddOnName.contains(",")) {
						expectedProducts.add(InputData_Communities.mulPBFSiteProductAddOnName.split(",")[0]);
						expectedProducts.add(InputData_Communities.mulPBFSiteProductAddOnName.split(",")[1]);
					} else {
						expectedProducts.add(InputData_Communities.mulPBFSiteProductAddOnName);
					}
				}
			}
				for (int j = 0;j<expectedProducts.size();j++) {
					
					for (int i = 0; i < sf.orDetails.orderProductValuesViewAllList.size(); i++) {
						if (sf.seleU.getTextFromWebElement(sf.orDetails.orderProductValuesViewAllList.get(i))
								.contains(expectedProducts.get(j))) {
							reportStatusPass(methodName + " Found expected product in order details : "
									+ expectedProducts.get(j), true, true);
							productFound = true;
							break;
						}
					}
					
					if (!productFound)
						reportStatusFail(methodName + " Could not find exepcted product in order details : " + expectedProducts.get(j),
								true);

			}
	

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot verify order details", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *             Verify Product details
	 */
	public void verifyProductDetailsForTvBusInt() throws IOException {

		try {
			// Click on Order Details tab
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.orRelated.orderRelatedTab);

			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);
			boolean productFound = false, tvProductFound = false;

			sf.seleU.clickElementByJSE(sf.orRelated.orderProductViewALLClick);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			for (int i = 0; i < sf.orDetails.orderProductValuesViewAllList.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.orDetails.orderProductValuesViewAllList.get(i))
						.contains(InputData_Communities.commPBFAddProductName)) {
					reportStatusPass(methodName + " Found expected Business Internet product in order details : "
							+ InputData_Communities.commPBFAddProductName, true, true);
					productFound = true;
					break;
				}
			}
			for (int i = 0; i < sf.orDetails.orderProductValuesViewAllList.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.orDetails.orderProductValuesViewAllList.get(i))
						.contains(InputData_Communities.commPBFAddTVProductName)) {
					reportStatusPass(methodName + " Found expected TV product in order details : "
							+ InputData_Communities.commPBFAddTVProductName, true, true);
					tvProductFound = true;
					break;
				}
			}

			if (!productFound)
				reportStatusFail(methodName + " Could not find exepcted Business Internet product in order details",
						true);

			if (!tvProductFound)
				reportStatusFail(methodName + " Could not find exepcted TV product in order details", true);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Draft
	 * 
	 *             3.Verify order status is In Progress
	 * 
	 *             4.Verify 'select site contact' button is displayed and disabled
	 */
	public void verifySubmittedOrderInOrderDetails() throws IOException {
		try {

			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusDraft);
			verifyOrderStatusInOrderDetails(sf.dataInput.orderStatusInProgress);
			// verifyCancelOrderPresent();
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Activated
	 * 
	 *             3.Verify order status is Activated
	 * 
	 * 
	 */
	public void verifyActivatedOrderInOrderDetails() throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusActivated);
			verifyOrderStatusInOrderDetails(sf.dataInput.orderStatusActivated);
			sf.seleU.hardwait(3000);

			// Click on Order Details tab
			sf.seleU.clickElementByJSE(sf.orDetails.serviceAccountValueLink);
			reportStatusPass(methodName + " Clicked on Service Account", true, false);

			sf.seleU.wait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Activated
	 * 
	 *             3.Verify order status is Activated
	 * 
	 * 
	 */
	public void verifyOrderIsActivated() throws IOException {
		try {

			// Click on Order Details tab

			// sf.seleU.refreshPage();
			sf.seleU.wait(8000);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTabs);
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusActivated);
			verifyOrderStatusInOrderDetails(sf.dataInput.orderStatusActivated);
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Activated
	 * 
	 *             3.Verify order status is Cancelled
	 * 
	 *             4.Verify 'cancel order' button is not present
	 * 
	 * 
	 */
	public void verifyCancelledOrderInOrderDetais() throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.orDetails.statusFieldValueText);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusActivated);
			verifyOrderStatusInOrderDetails(sf.dataInput.orderStatusCancelled);
			verifyCancelOrderIsNotPresent();
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Verify order number is present
	 * 
	 *             2.Verify status is Activated
	 * 
	 *             3.Verify order status is Cancelled
	 * 
	 *             4.Verify 'cancel order' button is not present
	 * 
	 * 
	 */
	public void verifyCancelledOrderInOrderDetaisTab() throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(8000);
			/// sf.seleU.switchToElementFrame(sf.orDetails.statusFieldValueText);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusActivated);
			verifyOrderStatusInOrderDetails(sf.dataInput.orderStatusCancelled);
			// verifyCancelOrderIsNotPresent();
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             verify cancel requested status
	 * 
	 * 
	 */
	public void verifyCancelledRequestedStatusInOrderDetais(String Status) throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.scrollToTop();
			sf.seleU.switchToElementFrame(sf.orDetails.statusFieldValueText);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			sf.seleU.scrollToTop();
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);
			sf.seleU.wait(4000);
			// verifyOrderNumberInOrderDetails();
			verifyStatusInOrderDetails(sf.dataInput.orderStatusDraft);
			verifyOrderStatusInOrderDetails(Status);
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid order details status for canceled requested", e);
			e.printStackTrace();
		}
	}

	/**
	 * Chethan
	 * 
	 * @throws IOException
	 * 
	 *             Verify "ship to contact" field value is present
	 */
	public void verifyShipToContactInOrder() throws IOException {

		try {

			sf.seleU.waitElementToBeVisible(sf.orDetails.orderNumberInQuoteRelated);
			sf.seleU.wait(8000);
			sf.seleU.clickElementByJSE(sf.orDetails.orderNumberInQuoteRelated);
			// sf.seleU.clickOnElementNumberoftimes(sf.orDetails.orderDetailsTab, 1);
			reportStatusPass(methodName + " Clicked on Order number", true, false);
			sf.seleU.wait(5000);
			if (sf.seleU.getTextFromWebElement(sf.orDetails.shipToContactFieldValueText).length() > 0) {
				reportStatusPass(methodName + " ShipToContact is verified as "
						+ sf.seleU.getTextFromWebElement(sf.orDetails.shipToContactFieldValueText), true, true);
				// sf.dataInput.orderNumber =
				// sf.seleU.getTextFromWebElement(sf.orDetails.shipToContactFieldValueText);

			} else {
				reportStatusFail(methodName + " ShipToContact value is missing", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in finding shiptocontact field value", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify order number is present
	 */
	public void verifyOrderNumberInOrderDetails() throws IOException {

		try {

			// Verify order number is present

			sf.seleU.waitElementToBeVisible(sf.orDetails.orderDetailsTabs);
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTabs);
			// sf.seleU.clickOnElementNumberoftimes(sf.orDetails.orderDetailsTab, 1);
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);
			sf.seleU.waitForLoading();
			if (sf.seleU.getTextFromWebElement(sf.orDetails.orderNumberFieldValueText).length() > 0) {
				reportStatusPass(methodName + " Order number is verified as "
						+ sf.seleU.getTextFromWebElement(sf.orDetails.orderNumberFieldValueText), true, true);
				sf.dataInput.orderNumber = sf.seleU.getTextFromWebElement(sf.orDetails.orderNumberFieldValueText);

			} else {
				reportStatusFail(methodName + " Invalid order Number", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Invalid Order Number In order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Verify Status in order details
	 */
	public void verifyStatusInOrderDetails(String status) throws IOException {

		try {
			// Verify Status in order details
			WebElement orderStatus;
			if (sf.orDetails.statusFieldValueText.size() == 1) {
				orderStatus = sf.orDetails.statusFieldValueText.get(0);
			} else {
				orderStatus = sf.orDetails.statusFieldValueText.get(1);
			}
			sf.seleU.waitForLoading();
			if (sf.seleU.getTextFromWebElement(orderStatus).trim().equals(status)) {
				reportStatusPass(methodName + " Verified Status in order details as "
						+ sf.seleU.getTextFromWebElement(orderStatus), true, true);
			} else {
				reportStatusFail(
						methodName + " Invalid Status in order details :" + sf.seleU.getTextFromWebElement(orderStatus),
						true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Invalid Status In order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * Chethan K
	 * 
	 * @param status
	 * @throws IOException
	 * 
	 *             Verify credit and fraud Status in details tab
	 */
	public static void verifyCreditAndFraudChecks(String creditStatus, String fraudStatus) throws IOException {

		try {

			// Verify Status in order details
			WebElement creditStatusElement;
			WebElement fraudStatusElement;

			creditStatusElement = sf.orDetails.creditStatusText.get(0);
			fraudStatusElement = sf.orDetails.fraudStatusText.get(0);

			if (!creditStatus.equals("")) {
				if (sf.seleU.getTextFromWebElement(creditStatusElement).equals(creditStatus)) {
					reportStatusPass(methodName + " Verified Credit Status as "
							+ sf.seleU.getTextFromWebElement(creditStatusElement), true, true);
				} else {
					reportStatusFail(methodName + " Invalid Credit Status :"
							+ sf.seleU.getTextFromWebElement(creditStatusElement), true);
				}
			}
			if (!fraudStatus.equals("")) {
				if (sf.seleU.getTextFromWebElement(fraudStatusElement).equals(fraudStatus)) {
					reportStatusPass(methodName + " Verified Fraud Status in as "
							+ sf.seleU.getTextFromWebElement(fraudStatusElement), true, true);
				} else {
					reportStatusFail(
							methodName + " Invalid Fraud status :" + sf.seleU.getTextFromWebElement(fraudStatusElement),
							true);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(" Invalid Credit or Fraud Status ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Chethan K
	 * 
	 * @param status
	 * @throws IOException
	 * 
	 *             Verify Status in contract and order details
	 */
	public void verifyContractAndQuoteDetails(String orderStatus, String contractStatus, String creditStatus,
			String fraudStatus) throws IOException {

		try {

			// Verify Status in order details
			WebElement contractStatusElement;
			// sf.seleU.clickElementByJSE(sf.qr.quoteLineItemsViewAllClick.get(0));

			sf.seleU.clickElementByJSE(sf.orDetails.orderRelatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.wait(3000);
			sf.seleU.scrollByCoOrdinates(2);
			if (sf.orDetails.contractStatusFieldValueText.size() == 1) {
				contractStatusElement = sf.orDetails.contractStatusFieldValueText.get(0);
			} else {
				contractStatusElement = sf.orDetails.contractStatusFieldValueText
						.get(sf.orDetails.contractStatusFieldValueText.size() - 1);
			}

			if (sf.seleU.getTextFromWebElement(contractStatusElement).trim().equals(contractStatus)) {
				reportStatusPass(methodName + " Verified Contract Status in order details as "
						+ sf.seleU.getTextFromWebElement(contractStatusElement), true, true);
			} else {
				reportStatusFail(methodName + " Invalid Contract Status in order details :"
						+ sf.seleU.getTextFromWebElement(contractStatusElement), true);
			}

			// Click on Order Details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on Quote Details Tab", true, false);
			verifyStatusInOrderDetails(orderStatus);
			verifyCreditAndFraudChecks(creditStatus, fraudStatus);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Status In order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * Anukriti Chawla
	 * 
	 * @param status
	 * @throws IOException
	 * 
	 *             Verify Status in contract and order details
	 */
	public void verifyContractAndQuoteDetailsWithTimeSpanCheck(String orderStatus, String contractStatus)
			throws IOException {

		try {
			int maxtimeInterval = 15;
			boolean updated = false;
			// Verify Status in order details
			WebElement contractStatusElement;
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);

			for (int i = 1; i <= maxtimeInterval; i++) {
				sf.seleU.scrollByCoOrdinates(1);
				if (sf.orDetails.contractStatusFieldValueText.size() == 1) {
					contractStatusElement = sf.orDetails.contractStatusFieldValueText.get(0);
				} else {
					contractStatusElement = sf.orDetails.contractStatusFieldValueText
							.get(sf.orDetails.contractStatusFieldValueText.size() - 1);
				}
				if (sf.seleU.getTextFromWebElement(contractStatusElement).trim().equals(contractStatus)) {
					reportStatusPass(methodName + " Verified Contract Status in order details as "
							+ sf.seleU.getTextFromWebElement(contractStatusElement), true, true);
					updated = true;
					break;
				} else {
					reportStatusPass(methodName + " Refreshing page as details not updated yet, will wait for total "
							+ maxtimeInterval + " minutes", false, false);
					sf.seleU.refreshPage();
					sf.seleU.waitForLoading();
					sf.seleU.clickOnElementNumberoftimes(sf.qr.relatedTab, 1);
					sf.seleU.waitForLoading();
					sf.seleU.refreshPage();
					// sf.seleU.clickElementByJSE(sf.qr.relatedTab);
					continue;

				}
			}

			if (!updated)
				reportStatusFail(methodName + " Invalid Contract Status in order details", true);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Status In order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             1. Extract Monthly Charge, User Profile, contact details from
	 *             order details page
	 */
	public void extractOrderDetails(String product) throws IOException {
		try {
			String methodName = "SFDC_Verify Order Details: ";
			String monthlyTotal = "";

			sf.seleU.wait(8000);

			// Click on order details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on order details tab button " + monthlyTotal, true, false);
			sf.seleU.hardwait(2000);

			// Verify Order number
            verifyFieldValue("Order Number is ", sf.orDetails.orderNumberFieldValueText, sf.dataInput.orderNumber);
            
         // 1. Extracting monthly price from order details page
         			monthlyTotal = sf.seleU.getTextFromWebElement(sf.orDetails.effectiveMonthCharge).trim().replace("$", "")
         					.replace(",", "").replace(" ", "");
         			reportStatusPass(methodName + " Extracted the monthly price value in order details page as " + monthlyTotal,
         					true, false);

         			sf.seleU.wait(2000);

         			sf.dataInput.totalPrice = Double.parseDouble(monthlyTotal);
         			sf.seleU.wait(2000);

         			// 2. Extracting created by and last modified values from order details
         			sf.dataInput.userProfileAe = sf.seleU.getTextFromWebElement(sf.orDetails.createdByValueText);
			
			//Verifying if the billing account link exists or not for cable or rdi
                if (product.equals("RDI")) {
			    if(!sf.seleU.isElementPresent(sf.orDetails.billingAccountLink)) {
					//selected no for the create new billing account in the specsheet manually
					reportStatusPass("Billing Account link is not visible as expected", true, false);
				}
				else reportStatusFail("Billing Account Link is displayed and it should not be displayed ", true);
			}
				
			
			
			if (product.equals("RDI")) {
				
				sf.dataInput.userProfileDelivery = sf.seleU.getTextFromWebElement(sf.orDetails.companyAuthorValueText);
				
				// Ectract delivery specilaist details
				sf.dataInput.userProfileAe = sf.seleU.getTextFromWebElement(sf.orDetails.createdByValueText);
				
				sf.seleU.clickElementByJSE(sf.orDetails.companyAuthorValueText);
				reportStatusPass(methodName + "Clicked on delivery profile agent", true, false);

				sf.omData.deliverySpecialistEmail = sf.seleU.getTextFromWebElement(sf.orDetails.companyAuthorEmailId)
						.trim();
				reportStatusPass(methodName + "Extracted from order details User Profile as " + sf.dataInput.userProfileAe
						+ " And User Delivery as " + sf.dataInput.userProfileDelivery + " Email id as "
						+ sf.omData.deliverySpecialistEmail, true, false);
				}
				
			
			
				if (product.equals("Cable")) {
					if(!sf.seleU.isElementPresent(sf.orDetails.billingAccountLink)) {
						
						reportStatusPass("Billing Account link is not visible as expected", true, false);
					}
					else reportStatusFail(methodName + "Billing Account Link is visible for Cable and it should not be visible ", true);
				}	
		}
			
			catch (Throwable e) {
			reportStatusFail("Invalid Extarction from order details page", e);
			e.printStackTrace();
		}
}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             1. Extract Monthly Charge, User Profile, contact details from
	 *             order details page
	 */
	public void verifyQuoteLinkInOrderDetails() throws IOException {
		try {
			// Click on order details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on order details tab button ", true, false);
			sf.seleU.hardwait(2000);

			reportStatusPass(methodName + "Order Status is "
					+ sf.seleU.getTextFromWebElement(sf.orDetails.orderStatusFieldValueText), true, false);

			if (sf.seleU.isElementDisplayed(sf.orDetails.QuoteLink)) {
				reportStatusPass(
						methodName + "Quote Link/ID is" + sf.seleU.getTextFromWebElement(sf.orDetails.QuoteLink), true,
						false);
				sf.seleU.clickElementByJSE(sf.orDetails.QuoteLink);
				reportStatusPass(methodName + "Clicked on Quote Link successfully", true, false);

			} else {
				reportStatusFail("Quote ID/Link is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail("Invalid Extarction from order details page for Quote Link", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             1. Extract Monthly Charge, User Profile, contact details from
	 *             order details page
	 */
	public void extractAeProfileInformation() throws IOException {
		try {
			// Click on order details tab
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on order details tab button ", true, false);
			sf.seleU.hardwait(2000);
			// 2. Extracting AE Profile Information
			sf.dataInput.userProfileAe = sf.seleU.getTextFromWebElement(sf.orDetails.createdByValueText);

			// Ectract delivery specilaist details
			sf.seleU.clickElementByJSE(sf.orDetails.createdByValueText);
			reportStatusPass(methodName + "Clicked on AE Profile agent", true, false);

			sf.seleU.ScrolltoElement(sf.orDetails.companyAuthorEmailId);
			sf.omData.AEProfileEmail = sf.seleU.getTextFromWebElement(sf.orDetails.companyAuthorEmailId).trim();
			reportStatusPass(methodName + "Extracted User Profile and email id for AE Profile as "
					+ sf.dataInput.userProfileAe + " " + sf.omData.AEProfileEmail, true, false);

		} catch (Throwable e) {
			reportStatusFail("Invalid Extarction from order details page for AE Profile", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Click on business account link and extract legal name, no of emp
	 *             and sales segment values
	 */
	public void extractContactDetailsFromOrderPage() throws IOException {

		try {
			String methodName = "SFDC_Extract business Account Details@: ";
			// 3. Extracting field values from contact details
			sf.seleU.wait(4000);
			sf.dataInput.primaryContact = sf.seleU.getTextFromWebElement(sf.orDetails.customerAuthorValueText);
			reportStatusPass(methodName + " Extracting the customer contact name " + sf.dataInput.primaryContact, true,
					false);
			sf.seleU.hardwait(2000);

			// Click on contact link in order details page
			sf.seleU.clickElementByJSE(sf.orDetails.customerAuthorValueText);
			reportStatusPass(methodName + " Clicked on customer contact link", true, false);
			sf.seleU.wait(2000);

			sf.dataInput.phoneNumber = sf.seleU.getTextFromWebElement(sf.cd.contactPhoneText);
			sf.dataInput.contactEmailAddress = sf.seleU.getTextFromWebElement(sf.cd.contactEmailText);
			reportStatusPass(methodName + " Extracted the fied values values from the customer contact details Page "
					+ sf.dataInput.phoneNumber + " " + sf.dataInput.contactEmailAddress, true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Extraction from Contact Details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Click on business account link and extract legal name, no of emp
	 *             and sales segment values
	 */
	public void extractBusinessAccountDetailsForManualQueue() throws IOException {

		try {
			String methodName = "SFDC_Extract Contact Details@: ";
			sf.seleU.hardwait(4000);
			sf.seleU.switchToDefaultContent();
			sf.dataInput.tempBusinessAccountName = sf.seleU
					.getTextFromWebElement(sf.orDetails.businessAccountValueText);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.orDetails.businessAccountValueText);
			reportStatusPass(methodName + " Clicked on business account link to extract values "
					+ sf.dataInput.tempBusinessAccountName, true, false);
			sf.seleU.wait(4000);

			// Click on business account details tab and extract legal name, no of emp and
			// sales segment values
			// sf.seleU.clickElementByJSE(sf.ad.detailsTab);
			// sf.seleU.wait(4000);
			sf.dataInput.businessAccountLegalName = sf.seleU.getTextFromWebElement(sf.ad.newLegalNameValueText);
			sf.dataInput.numberOfEmployees = sf.seleU.getTextFromWebElement(sf.ad.newEmployeesValueText);

			// reportStatusPass(methodName + " Clicked on business account overview page
			// tab", true, false);
			// sf.dataInput.salesSegment =
			// sf.seleU.getTextFromWebElement(sf.ad.salesSegementValueInput);
			reportStatusPass(methodName + "Extracted the field values as " + sf.dataInput.salesSegment + " No of Emp "
					+ sf.dataInput.numberOfEmployees + " Legal name is  " + sf.dataInput.businessAccountLegalName, true,
					false);

			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Extraction from business account details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Click on business account link and extract legal name, no of emp
	 *             and sales segment values
	 */
	public void clickOnBusinessAccForQuoteFromOrder() throws IOException {

		try {
			String methodName = "SFDC_Extract business Account Details@: ";
			sf.seleU.hardwait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.orDetails.businessAccountValueText);
			reportStatusPass(methodName + " Clicked on business account link to extract values ", true, false);
			sf.seleU.wait(4000);

			// Click on business account details tab and extract legal name, no of emp and
			// sales segment values
			sf.seleU.clickElementByJSE(sf.ad.detailsTab);

			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Extraction from business account details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Click on service account link and extract access type net and
	 *             premise type
	 */
	public void extractServiceAccountDetailsForManualQueue() throws IOException {

		try {
			String methodName = "SFDC_Extract Service Account Details@: ";
			sf.seleU.hardwait(2000);

			// Click on service account link in order details
			sf.dataInput.serviceAccountName = sf.seleU.getTextFromWebElement(sf.orDetails.serviceAccountValueLink);
			sf.seleU.clickElementByJSE(sf.orDetails.serviceAccountValueLink);
			sf.seleU.wait(4000);

			// Extract access net type value service details page
			String premiseDetails = sf.seleU.getTextFromWebElement(sf.csa.premiseServiceServiceabilityOutputText).trim()
					.split(",")[0];
			sf.dataInput.accessTypeNet = premiseDetails.split(":")[1].trim().replaceAll("[^A-Za-z0-9]", "")
					.toLowerCase();
			reportStatusPass(methodName + " Extracted servicability type " + sf.dataInput.accessTypeNet, true, false);

			// removed from the UI now
			// // Click on service premise link to extract premise type
			// sf.seleU.clickElementByJSE(sf.csa.premiseServiceLink);
			// reportStatusPass(methodName + " Clicked on Premise Link", true, false);
			// sf.seleU.hardwait(1000);
			//
			// sf.dataInput.servicePremiseType =
			// sf.seleU.getTextFromWebElement(sf.csa.premisesType).split(" ")[0]
			// .toLowerCase();
			// reportStatusPass(methodName + " Extracted Premise Type Value " +
			// sf.dataInput.servicePremiseType, true,
			// false);

			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Extraction from service account details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Click on site contact link and extract email id value
	 */
	public void extractSiteContactDetails() throws IOException {
		try {
			String methodName = "SFDC_Extract site contact Details@: ";
			sf.seleU.hardwait(2000);

			sf.dataInput.siteContact = sf.seleU.getTextFromWebElement(sf.orDetails.siteContactValueText);

			sf.seleU.clickElementByJSE(sf.orDetails.siteContactValueText);
			reportStatusPass(methodName + " Clicked on site contact link " + sf.dataInput.siteContact, true, false);
			sf.seleU.wait(2000);

			sf.dataInput.contactPhoneNumber = sf.seleU.getTextFromWebElement(sf.cd.contactPhoneText);
			sf.dataInput.emailIdValue = sf.seleU.getTextFromWebElement(sf.cd.contactEmailText);
			reportStatusPass(
					methodName + " Extracted the fied values values from the ship to site contact details Page "
							+ sf.dataInput.emailIdValue,
					true, false);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid extraction from site contact details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Extract service address from orchestration plan page
	 */
	public void extractServiceAdreesFromOrchestrationPlanPage() throws IOException {

		String methodName = "SFDC_Orchestratio Plan for service address in order related tab@: ";
		try {
			sf.seleU.hardwait(5000);
			// sf.dataInput.serviceAddress = sf.seleU
			// .getTextFromWebElement(sf.orchPlan.orchesItemPlanSreviceAddressText.get(0)).trim();

			sf.dataInput.serviceAddress = sf.seleU
					.getTextFromWebElement(sf.orchPlan.orchesItemPlanSreviceAddressText.get(0)).split(":")[1].trim();

			reportStatusPass(methodName + " Extracted the Service Address from Orchestration Plan Page "
					+ sf.dataInput.serviceAddress, true, false);

			System.out.println(sf.dataInput.serviceAddress);
			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Extraction service address is failed from orchestration plan", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param orderStatus
	 * @throws IOException
	 * 
	 *             Verify order status in order details
	 */
	public void verifyOrderStatusInOrderDetails(String orderStatus) throws IOException {
		try {
			// Verify order status in order details
			if (sf.seleU.getTextFromWebElement(sf.orDetails.orderStatusFieldValueText).equals(orderStatus)) {
				reportStatusPass(methodName + " Verified Order Status in order details as "
						+ sf.seleU.getTextFromWebElement(sf.orDetails.orderStatusFieldValueText), true, true);
			} else {
				reportStatusFail(methodName + " Invalid Order Status in order details :"
						+ sf.seleU.getTextFromWebElement(sf.orDetails.orderStatusFieldValueText), true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Invalid Order Status In order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify 'select site contact' button is displayed and enabled
	 */
	public void verifySelectSiteContactEnabled() throws IOException {
		try {
			// Verify 'select site contact' button is displayed and enabled
			if (sf.seleU.isElementDisplayed(sf.orDetails.selectSiteContactButton)
					&& sf.orDetails.selectSiteContactButton.isEnabled()) {
				reportStatusPass(methodName + " Verified That 'select site contact' button is displyed and enabled",
						true, true);
			} else {
				reportStatusFail(methodName
						+ " Error in viewing 'select site contact' button, It should be enabled, but it is disabled",
						true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in viewing 'select site contact' button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify 'select site contact' button is displayed and disabled
	 */
	public void verifySelectSiteContactDisabled() throws IOException {
		try {
			// Verify 'select site contact' button is displayed and disabled
			if (sf.seleU.isElementDisplayed(sf.orDetails.selectSiteContactButton)
					&& (!sf.orDetails.selectSiteContactButton.isEnabled())) {
				reportStatusPass(methodName + " Verified That 'select site contact' button is displyed and disabled",
						true, true);
			} else {
				reportStatusFail(methodName
						+ " Error in viewing 'select site contact' button, It should be disabled, but it is enabled",
						true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in viewing 'select site contact' button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify 'cancel order' button is displayed
	 */
	public void verifyCancelOrderPresent() throws IOException {
		try {

			// Verify 'cancel order' button is displayed
			if (sf.seleU.isElementDisplayed(sf.orDetails.cancelOrderButton)
					&& (sf.orDetails.cancelOrderButton.isEnabled())) {
				reportStatusPass(methodName + " Verified That 'Cancel Order' button is displyed ", true, true);
			} else {
				reportStatusFail(methodName + " Error in viewing 'Cancel Order'  button", true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in viewing 'Cancel Order' button", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify 'cancel order' button is not displayed
	 */
	public void verifyCancelOrderIsNotPresent() throws IOException {
		try {

			// Verify 'cancel order' button is not displayed
			if (!sf.seleU.isElementDisplayed(sf.orDetails.cancelOrderButton)) {
				reportStatusPass(methodName + " Verified That 'Cancel Order' button is not displyed ", true, true);
			} else {
				reportStatusFail(methodName + " Error in verifying 'Cancel Order' button not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying 'Cancel Order' button not present", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click 'cancel order' button
	 * 
	 *             Click 'Cancel order checkbox' and then hit next
	 * 
	 *             Verify order cancelled successfully
	 * 
	 *             Click on next button
	 */
	public void cancelOrder() throws IOException {
		try {

			// Click 'cancel order' button
			sf.seleU.clickElementByJSE(sf.orDetails.cancelOrderButton);
			reportStatusPass(methodName + " Clicked on 'cancel order' Button ", true, false);
			sf.seleU.wait(20000);

			// Verify order cancelled successfully
			if (sf.seleU.isElementDisplayed(sf.orDetails.orderCancelledMsg)) {
				reportStatusPass(methodName + " Verified That 'Order is cancelled successfully' message is displyed ",
						true, true);
			} else {
				reportStatusFail(methodName + " Error in Cancelling the Order , no 'order cancelled msg'", true);
			}

			// Click on next button
			sf.seleU.clickElementByJSE(sf.orDetails.cancelInfoMsgNextButton);
			reportStatusPass(methodName + " Clicked on cancel Info Message next Button ", true, false);
			sf.seleU.hardwait(6000);
			sf.seleU.refreshPage();
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Cancelling the order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify order failure link is displayed
	 */
	public void verifyTaskFailureActivity() throws IOException {
		try {

			sf.seleU.clickElementByJSE(sf.orDetails.showAllActivityButton);

			// Verify order failure link is displayed
			if (sf.seleU.isElementDisplayed(sf.orDetails.orderFailureLink)) {
				reportStatusPass(methodName + " Verified That 'order failure' link  is displyed in order activity ",
						true, true);
				sf.seleU.clickElementByJSE(sf.orDetails.orderFailureLink);

			} else {
				reportStatusFail(methodName + " Error in viewing 'order failure' link in order activity", true);
			}

			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in viewing 'failure task Activity ' in order", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Click on the service account link
	 * 
	 *             2.Store the last changed time stamp value in the service account
	 *             details page
	 * 
	 */
	public void verifyBillingAccountNumber(String billingType, String product) throws IOException {
		try {

			String methodName = "SFDC_Verify Billing Account Link In Order Details Page@: ";
			String billingAccount;
			// Click on Order Details tab

			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on order details tab", true, false);
			sf.seleU.wait(2000);
			Global.dataInput.businessAccountName = sf.dataInput.tempBusinessAccountName;
			if (sf.seleU.isElementPresent(sf.orDetails.billingAccountLink)) {
				if (product.equals("RDI")) {
					billingAccount = Global.dataInput.businessAccountName;
				} else {
					billingAccount = Global.dataInput.businessAccountName + " " + billingType;
				}
				// if(sf.seleU.getTextFromWebElement(sf.orDetails.billingAccountLink).trim().
				// contains(billingAccount).trim()) {
				// reportStatusPass(methodName + billingType + "Billing Account Created : " +
				// billingAccount, true, false);
				// } else {
				// reportStatusFail(
				// methodName + "Not Displaying the correct billing account ",true);
				// }
				verifyFieldValue("Billing Account Number ", sf.orDetails.billingAccountLink, billingAccount);
				sf.seleU.clickElementByJSE(sf.orDetails.billingAccountLink);
				sf.seleU.wait(4000);
				reportStatusPass(methodName + " Clicked on Billing Account Link", true, false);

			} else {
				reportStatusFail(methodName + "Billing Account Link is not displayed ", true);
			}

			// sf.seleU.clickElementByJSE(sf.orDetails.serviceAccountValueLink);
			// reportStatusPass(methodName + " Clicked on Service Account", true, false);
			// sf.seleU.wait(4000);
			if (product.equals("RDI")) {
				verifyBillingAccountDetails(sf.dataInput.oracleNumber, product);
			} else {
				verifyBillingAccountDetails(sf.omData.canNOInCreateAccount, product);
			}

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Verify Billing Account Details With CAN Number", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal
	 * 
	 * @throws IOException
	 * 
	 *             Verify Billing Account Details 1. Number, status
	 * 
	 */
	public void verifyBillingAccountDetails(String billingAccountNumber, String product) throws IOException {
		try {

			String methodName = "SFDC_Verify Billing Account Link In Order Details Page@: ";
			// Click on Order Details tab
			sf.seleU.switchToDefaultContent();

			verifyFieldValue("Billing Account Number ", sf.orDetails.billingAccountNumber, billingAccountNumber);

			verifyFieldValue("Billing Account Status ", sf.orDetails.billingAccountStatus, sf.dataInput.status_Active);
			verifyFieldValue("Account Record Type ", sf.orDetails.billingAccountRecordType,
					sf.dataInput.acc_RecordType_Billing);

			if (product.equals("RDI")) {
				verifyFieldValue("Account Source for RDI", sf.orDetails.billingAccountSource, sf.omData.accountSource);
			} else {
				verifyFieldValue("Account Source for Cable", sf.orDetails.billingAccountSource, "SS");
			}

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Verify Billing Account Details With CAN Number", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal
	 * 
	 * @throws IOException
	 * 
	 *             Verify Billing Account Details 1. Number, status
	 * 
	 */
	public void verifyBillingAccountInvoiceLink(String billingType) throws IOException {
		try {

			String methodName = "SFDC_Verify Billing Account Invoice Link In Billing Details Page@: ";
			if (sf.seleU.isElementPresent(sf.orDetails.billingAccountInvoicedLink)) {

				String billingAccount = Global.dataInput.businessAccountName + " " + billingType;

				// if(sf.seleU.getTextFromWebElement(sf.orDetails.billingAccountLink).trim().
				// contains(billingAccount).trim()) {
				// reportStatusPass(methodName + billingType + "Billing Account Created : " +
				// billingAccount, true, false);
				// } else {
				// reportStatusFail(
				// methodName + "Not Displaying the correct billing account ",true);
				// }

				verifyFieldValue("Billing Account Link ", sf.orDetails.billingAccountInvoicedLink, billingAccount);

				sf.seleU.clickElementByJSE(sf.orDetails.billingAccountInvoicedLink);
				reportStatusPass(methodName + " Clicked on Billing Account Invoiced Link", true, false);
				sf.seleU.wait(2000);

			} else {
				reportStatusFail(methodName + "Billing Account Invoiced Link is not displayed ", true);
			}

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Verify Billing Account Details With CAN Number", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws IOException
	 * 
	 *             1. Click on Service Account In Order Details TAb
	 * 
	 *             2. Click on Service Account Related button
	 * 
	 *             3. Click on dedicated Internet Product in service assets
	 * 
	 * 
	 */
	public void clickOnBillingAssetForOffice365Product(Hashtable<String, String> dataTable) throws IOException {
		try {
			int office365BillingAsset = 0;
			int count = 0;
			// Click on Related tab under account view
			sf.seleU.hardwait(1000);

			sf.seleU.clickElementByJSE(sf.orDetails.billingAccountRelatedTab.get(1));
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Clicked on Related Tab In the Billing Account invoice link", true, false);
			sf.seleU.wait(2000);

			// click on View All In Billing Assets for office 365
			sf.seleU.clickElementByJSE(sf.saccRelated.billing_AssetViewAll);
			reportStatusPass(methodName + " Clicked on Invoice Billing Asset Link", true, false);
			sf.seleU.hardwait(1000);

			office365BillingAsset = sf.saccRelated.productItemsNameInServiceAssetList.size();
			// verify whether billing asset for office 365 is present or not.
			for (int i = 0; i < office365BillingAsset; i++) {
				sf.seleU.wait(1000);
				if (sf.seleU.getTextFromWebElement(sf.saccRelated.productItemsNameInServiceAssetList.get(i))
						.length() > 0) {
					reportStatusPass(
							methodName + "Product is found in the Billing Asset items list " + sf.seleU
									.getTextFromWebElement(sf.saccRelated.productItemsNameInServiceAssetList.get(i)),
							true, true);
				} else {
					reportStatusFail("Product is not displayed for office 365 as expected", true);

				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in billing asset for office 365 product", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param status
	 * @throws IOException
	 * 
	 *             Verify the CAN NO in the account number section of service
	 *             account details
	 */
	public void verifyCANNOInServiceAccountDetails(String product) throws IOException {
		try {
			String methodName = "SFDC_Verify Account No In Service ACcount DetailsPage@: ";
			sf.seleU.hardwait(4000);
			// Click on Order Details tab
			sf.seleU.switchToDefaultContent();

			// Click on service account link in order details
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			verifyFieldValue("Serice Account name", sf.orDetails.serviceAccountValueLink,
					sf.dataInput.serviceAccountName);
			// sf.dataInput.serviceAccountName =
			// sf.seleU.getTextFromWebElement(sf.orDetails.serviceAccountValueLink);
			sf.seleU.clickElementByJSE(sf.orDetails.serviceAccountValueLink);
			reportStatusPass(methodName + " Clicked on Service Account link", true, false);
			sf.seleU.wait(4000);

			if (product.equals("Cable")) {
				verifyFieldValue("Service Account Number ", sf.orDetails.billingAccountNumber,
						sf.omData.canNOInCreateAccount);
			} else {
				verifyFieldPresent("Service Account Number ", sf.orDetails.billingAccountNumber);
			}
			verifyFieldValue("Account Record Type ", sf.orDetails.serviceAccountRecordType,
					sf.dataInput.acc_RecordType_Service);

			if (product.equals("RDI")) {
				verifyFieldValue("Account Source for RDI", sf.orDetails.billingAccountSource, sf.omData.accountSource);
			} else {
				verifyFieldValue("Account Source for Cable", sf.orDetails.billingAccountSource, "SS");
			}

			verifyFieldValue("Service Account Status ", sf.orDetails.billingAccountStatus, sf.dataInput.status_Active);

			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Invalid extraction from site contact details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Click on the service account link
	 * 
	 *             2.Store the last changed time stamp value in the service account
	 *             details page
	 * 
	 */
	public void verifydModifiedTimeInServiceBeforeUpdate() throws IOException {
		try {

			String methodName = "SFDC_Verify Modified Time In Service Before Update@: ";
			// Click on Order Details tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			reportStatusPass(methodName + " Clicked on order details tab", true, false);
			sf.seleU.wait(2000);

			sf.seleU.clickElementByJSE(sf.orDetails.serviceAccountValueLink);
			reportStatusPass(methodName + " Clicked on Service Account", true, false);
			sf.seleU.wait(4000);

			// Storing timestamp to compare
			sf.dataInput.tempVarForModifiedDate = sf.seleU
					.getTextFromWebElement(sf.csa.premiseDetailsServiceLastModified).trim();
			reportStatusPass(
					methodName + "Last Modied time stamp before update CAN No " + sf.dataInput.tempVarForModifiedDate,
					true, false);
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Verify Modified time before update is failed", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Details tab
	 * 
	 *             1.Click on the service account link in the order details page
	 * 
	 *             2.Verify the last modified field timestamp value and user
	 *             modified
	 */
	public void verifyCanNoAndModifiedTimeInServiceAfterUpdate() throws IOException {
		try {

			// Click on Order Details tab
			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.clickElementByJSE(sf.orDetails.orderDetailsTab);
			sf.seleU.scrollToTop();
			reportStatusPass(methodName + " Clicked on Order Details Tab", true, false);

			sf.dataInput.tempBusinessAccountName = sf.seleU.getTextFromWebElement(sf.orDetails.businessAccountValueText)
					.trim();

			// Click on the service account
			sf.seleU.clickElementByJSE(sf.orDetails.serviceAccountValueLink);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Clicked on Service Account", true, false);
			sf.seleU.wait(4000);

			// verifying last modified timestamp: If the new stamp is not equals to old
			// timestamp then it's updated
			if (!sf.dataInput.tempVarForModifiedDate.trim()
					.equals(sf.seleU.getTextFromWebElement(sf.csa.premiseDetailsServiceLastModified).trim())) {

				reportStatusPass(
						methodName
								+ "Can No successfully got updated, last Modied time stamp before update CAN No is=> "
								+ sf.dataInput.tempVarForModifiedDate + " After update Can No is=> "
								+ sf.seleU.getTextFromWebElement(sf.csa.premiseDetailsServiceLastModified).trim(),
						true, true);

			}

			// Verify service account source is SS
			verifyFieldValue("Account Source is ", sf.csa.premiseDetailsAccountSource,
					sf.dataInput.billingAccountSource);

			// Verify CanNo is same which was updated in the orchestration plan dashboard
			// after selecting complete task
			verifyFieldValue("Account Number is ", sf.csa.premiseDetailsAccountNumber, sf.dataInput.superSystemCAN);
			sf.seleU.wait(7000);

			// Click on the user img tag button to fetch profile user Id
			sf.seleU.clickElementByJSE(sf.home.userImg);

			// Verify Last modified by value with the current user
			verifyFieldValue("Last Modified By ", sf.csa.premiseDetailsServiceLastModifiedBy,
					sf.seleU.getTextFromWebElement(sf.login.userProfileLoginName).trim());

		} catch (Throwable e) {
			reportStatusFail(" Verify CanNo in the service account detais failed", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on business account Details tab
	 * 
	 *             1.Verify updated CanNO with the updated business account name.
	 * 
	 */
	public void verifyCanNoWithBusinessAccountName() throws IOException {
		try {

			sf.seleU.hardwait(2000);
			// Click on Account Details tab
			sf.seleU.clickElementByJSE(sf.ad.detailsTab);
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();

			// Verify updated CanNo populates in the account name text
			if (!sf.seleU.getTextFromWebElement(sf.ad.accountNameValueText).trim()
					.equals(sf.dataInput.superSystemCAN)) {
				reportStatusPass(methodName + "Business Account Name is "
						+ sf.seleU.getTextFromWebElement(sf.ad.accountNameValueText)
						+ " Not same as Can No after being updated" + sf.dataInput.superSystemCAN, true, false);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verify CanNo with business account number got failed", e);
			e.printStackTrace();
		}
	}
	
	public void validateOrderDetails() throws IOException {
		try {
			// Master order Status 
			verifyFieldValue("Master Order Status is ", sf.orDetails.masterOrderStatusFieldText, sf.dataInput.orderStatusInProgress);
			
			// Child order Status 
			verifyFieldValue("Child Order Status is ", sf.orDetails.childOrderStatusFieldText, sf.dataInput.orderStatusReadyToSubmit);
			
			// Click on Order Decomposition
			//sf.seleU.clickOnElement(sf.orDetails.orderDecomposition);
			
			//verifyFieldPresent(" Master Order Product ", sf.orDetails.orderDecompositionMasterOrderProduct);
			
			// Click on Related Tab
			sf.seleU.clickOnElement(sf.orDetails.orderRelated.get(0));
			
			// Click on Child 
			sf.seleU.clickOnElement(sf.orDetails.childOrderLink);
			
			// Child Order detail tab
			sf.seleU.clickOnElement(sf.orDetails.childOrderDetails.get(1));
			
			// Validating the Master order number
			String actualOrderNumber = sf.seleU.getTextFromWebElement(sf.orDetails.masterOrderFieldText);
			String masterOrderNumber= sf.seleU.getAttributeInnerText(sf.orDetails.masterOrderNumber.get(0));
			if(actualOrderNumber.equalsIgnoreCase(masterOrderNumber)) {
				reportStatusPass(methodName + actualOrderNumber + " Actual orderNumber equals " + masterOrderNumber, true, false);
			} else {
				reportStatusFail(methodName + actualOrderNumber + " Actual orderNumber Not equals " + masterOrderNumber, true);
			}
			
			// validate Ship To Contact Filed
			String shipToContact = sf.seleU.getAttributeInnerText(sf.orDetails.shipToContactFieldText);
			reportStatusPass(methodName + " Validated Ship To Contact: " + shipToContact , true, false);
			
			// validate Billing Account Field
			String billingAccount = sf.seleU.getAttributeInnerText(sf.orDetails.billingAccount);
			reportStatusPass(methodName + " Validated Billing Account: " + billingAccount , true, false);
			
			
		} catch (Throwable e) {
			reportStatusFail(" Verify order details failed", e);
			e.printStackTrace();
		}
	}
	
	public void validateOrderDetailsAfterApproval() throws IOException {
		try {
			// Click on Related tab 
			sf.seleU.clickElementByJSE(sf.orDetails.orderRelated.get(0));
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.scrollByCoOrdinates(3);
			// Click on Master Order Link
			sf.seleU.clickElementByJSE(sf.orDetails.orders.get(0));
			sf.seleU.wait(4000);
			if (sf.dataInput.frenchEnabled.equalsIgnoreCase("No")) {
			// Master order Status 
			verifyFieldValue("Master Order Status is ", sf.orDetails.masterOrderStatusFieldText, sf.dataInput.orderStatusInProgress);
			
			// Child order Status 
			verifyFieldValue("Child Order Status is ", sf.orDetails.childOrderStatusFieldText, sf.dataInput.orderStatusReadyToSubmit);
			} else {
				verifyFieldValue("Master Order Status is ", sf.orDetails.masterOrderStatusFieldText, sf.dataInput.orderStatusInProgressFr);
				
				// Child order Status 
				verifyFieldValue("Child Order Status is ", sf.orDetails.childOrderStatusFieldText, sf.dataInput.orderStatusReadyToSubmit);
			}
			// Click on Order Decomposition
			//sf.seleU.clickOnElement(sf.orDetails.orderDecomposition);
			
			//verifyFieldPresent(" Master Order Product ", sf.orDetails.orderDecompositionMasterOrderProduct);
			
			// Click on Related Tab
			sf.seleU.clickOnElement(sf.orDetails.orderRelated.get(1));
			reportStatusPass(methodName + " Clicked on Master Order Related Tab", true, false);
			
			// Click on Child 
			sf.seleU.clickOnElement(sf.orDetails.childOrderLink);
			reportStatusPass(methodName + " Clicked on Child Order Link Tab", true, false);
			
			// Child Order detail tab
			sf.seleU.clickOnElement(sf.orDetails.childOrderDetails.get(2));
			reportStatusPass(methodName + " Clicked on Child Detail Link Tab", true, false);
			
			// Validating the Master order number
			String actualOrderNumber = sf.seleU.getTextFromWebElement(sf.orDetails.masterOrderFieldText);
			String masterOrderNumber= sf.seleU.getAttributeInnerText(sf.orDetails.masterOrderNumber.get(1));
			if(masterOrderNumber.contains(actualOrderNumber)) {
				reportStatusPass(methodName + actualOrderNumber + " orderNumber equals Master Order" + masterOrderNumber, true, false);
			} else {
				reportStatusFail(methodName + actualOrderNumber + " orderNumber Not equals Master Order " + masterOrderNumber, true);
			}
			
			// validate Ship To Contact Filed
			String shipToContact = sf.seleU.getAttributeInnerText(sf.orDetails.shipToContactFieldText);
			reportStatusPass(methodName + " Validated Ship To Contact: " + shipToContact , true, false);
			
			// validate Billing Account Field
			String billingAccount = sf.seleU.getAttributeInnerText(sf.orDetails.billingAccount);
			reportStatusPass(methodName + " Validated Billing Account: " + billingAccount , true, false);
			
			
		} catch (Throwable e) {
			reportStatusFail(" Verify order details failed", e);
			e.printStackTrace();
		}
	}
	
	public void validateOrderDetailsForRDIAfterApproval() throws IOException {
		try {
			// Click on Related tab 
			sf.seleU.clickOnElement(sf.orDetails.orderRelated.get(0));
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			// Click on Master Order Link
			sf.seleU.clickOnElement(sf.orDetails.orders.get(0));
			// Master order Status 
			verifyFieldValue("Master Order Status is ", sf.orDetails.masterOrderStatusFieldText, sf.dataInput.orderStatusActivated);
			
			// Child order Status 
			verifyFieldValue("Child Order Status is ", sf.orDetails.childOrderStatusFieldText, sf.dataInput.orderStatusReadyToSubmit);
			
			// Click on Order Decomposition
			sf.seleU.clickOnElement(sf.orDetails.orderDecomposition);
			
			verifyFieldPresent(" Master Order Product ", sf.orDetails.orderDecompositionMasterOrderProduct);
			
			// Click on Related Tab
			sf.seleU.clickOnElement(sf.orDetails.orderRelated.get(1));
			reportStatusPass(methodName + " Clicked on Master Order Related Tab", true, false);
			
			// Click on Child 
			sf.seleU.clickOnElement(sf.orDetails.childOrderLink);
			reportStatusPass(methodName + " Clicked on Child Order Link Tab", true, false);
			
			// Child Order detail tab
			sf.seleU.clickOnElement(sf.orDetails.childOrderDetails.get(2));
			reportStatusPass(methodName + " Clicked on Child Detail Link Tab", true, false);
			
			// Validating the Master order number
			String actualOrderNumber = sf.seleU.getTextFromWebElement(sf.orDetails.masterOrderFieldText);
			String masterOrderNumber= sf.seleU.getAttributeInnerText(sf.orDetails.masterOrderNumber.get(1));
			if(actualOrderNumber.equalsIgnoreCase(masterOrderNumber)) {
				reportStatusPass(methodName + actualOrderNumber + " orderNumber equals Master Order" + masterOrderNumber, true, false);
			} else {
				reportStatusFail(methodName + actualOrderNumber + " orderNumber Not equals Master Order " + masterOrderNumber, true);
			}
			
			// validate Ship To Contact Filed
			String shipToContact = sf.seleU.getAttributeInnerText(sf.orDetails.shipToContactFieldText);
			reportStatusPass(methodName + " Validated Ship To Contact: " + shipToContact , true, false);
			
			// validate Billing Account Field
			String billingAccount = sf.seleU.getAttributeInnerText(sf.orDetails.billingAccount);
			reportStatusPass(methodName + " Validated Billing Account: " + billingAccount , true, false);
			
			
		} catch (Throwable e) {
			reportStatusFail(" Verify order details failed", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().equals(expectedText)) {
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
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (isElementDisplayed(element)) {
				reportStatusPass(fieldName + " is present" + " with value as " + getTextFromWebElement(element), true,
						false);
			} else {
				reportStatusFail(fieldName + " is not present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);

			e.printStackTrace();
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			sf.seleU.waitElementToBeVisible(element);
			if (element.isDisplayed()) {
				sf.seleU.highLightElementYellow(element);
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public String getTextFromWebElement(WebElement element) throws Exception {
		String gettext = null;
		try {
			sf.seleU.waitElementToBeVisible(element);
			gettext = element.getText();
			sf.seleU.highLightElementYellow(element);
			if (gettext.length() == 0) {
				gettext = element.getAttribute("value");
			}
			logger.info("Element text is: " + gettext);

		} catch (NoSuchElementException e) {
			logger.info("No element with name " + element + "was found."); 
		}
		return gettext;
	}
	
	
	/**
	 * Satish D
	 * 
	 * @param status
	 * @throws IOException
	 * 
	 *             Verify the Quote Status of the E-Sign Orders with 15min, additionally provided 5min Extra grace period. 
	 */
	public void verifyQuoteStatusForESignOrdersWithGracePeriod(String orderStatus, String contractStatus)
			throws IOException {

		try {
			int maxtimeInterval = 15;
			int maxTimeWithGraceInterval=20;
			boolean updated = false;
			// Verify Status in order details
			WebElement contractStatusElement;
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusInfo(methodName + " Order Status Check Start Time  :  "
					+  DateTimeUtilities.currentSystemDate("MM_dd_HHmmss") + "", false);
			for (int i = 1; i <= maxTimeWithGraceInterval; i++) {
				sf.seleU.refreshPage();
				sf.seleU.scrollByCoOrdinates(1);
				if (sf.orDetails.contractStatusFieldValueText.size() == 1) {
					contractStatusElement = sf.orDetails.contractStatusFieldValueText.get(0);
				} else {
					contractStatusElement = sf.orDetails.contractStatusFieldValueText
							.get(sf.orDetails.contractStatusFieldValueText.size() - 1);
				}
				if (sf.seleU.getTextFromWebElement(contractStatusElement).trim().equals(contractStatus)) {
					reportStatusPass(methodName + " Verified Contract Status in order details as "
							+ sf.seleU.getTextFromWebElement(contractStatusElement), true, true);
					updated = true;
					reportStatusInfo(methodName + " Order Status Check End Time  :  "
							+  DateTimeUtilities.currentSystemDate("MM_dd_HHmmss") + "", false);
					break;
				} else {
					reportStatusPass(methodName + " Refreshing page as details not updated yet, will wait for total "
							+ maxtimeInterval + " minutes", false, false);
					sf.seleU.refreshPage();
					sf.seleU.wait(4000);
					sf.seleU.clickOnElementNumberoftimes(sf.qr.relatedTab, 1);
					sf.seleU.wait(60000);
					sf.seleU.refreshPage();
					// sf.seleU.clickElementByJSE(sf.qr.relatedTab);
					/*if(i==maxtimeInterval) {
						reportStatusInfo(methodName + " Order Status Check End Time is : and Running 5min Grace Period  :  "
								+  DateTimeUtilities.currentSystemDate("MM_dd_HHmmss") + "and Running Extra 5min Grace Period", false);
					}*/
					continue;
				}
			}

			if (!updated)
				reportStatusInfo(methodName + " Order Status Check End Time  :  "
						+  DateTimeUtilities.currentSystemDate("MM_dd_HHmmss") + "", false);
				reportStatusFail(methodName + " Invalid Contract Status in order details", true);

		} catch (Throwable e) {
			reportStatusFail(" Invalid Status In order details", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 10.05.2022 by @author Viswas reddy
	 * 
	 * Method to validate 3 year SOC code mobile price plan service. 
	 * @throws Exception 
	 * 
	 */
	public void verify_threeYearSOCcodeMobilePricePlan(String SOC) throws Exception {
		try {
			methodName = "Order details page@ ";
			sf.seleU.waitForLoading();
			// Click on Order Decomposition
			sf.seleU.ScrolltoElement(sf.orDetails.orderDecomposition);
			sf.seleU.clickElementByJSE(sf.orDetails.orderDecomposition);
			sf.seleU.ScrolltoElement(sf.orDetails.mobPricePlanSOC);
			String actualSOC = sf.seleU.getTextFromWebElement(sf.orDetails.mobPricePlanSOC);
			if(actualSOC.contains(SOC)) {
				reportStatusPass(methodName+"SOC code for 36 month term price plan is as expected", true, true);
			}else {
				reportStatusFail(methodName+"expected SOC code is "+SOC+" and actual SOC code is "+actualSOC+" both not matching", true);
			}
		}catch(Exception e) {
			reportStatusFail(methodName+"error in validating 3 year SOC mobile price plan", true);
		}
	}
	
}
