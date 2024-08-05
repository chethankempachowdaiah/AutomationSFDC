package sfdc.pages.qm;

import java.io.IOException;
import java.util.Hashtable;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_Home_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.om.SFDC_OrderDetails_Page;
import sfdc.pages.om.SFDC_Orders_Page;
import sfdc.pages.sales.SFDC_R4B_Quote_Approval_Page_Layout_Page;

/**
 * @author Priyanka.Acharya, Date: 30/JAN/2020
 *
 *         SFDC Quote Related Tab
 */
public class SFDC_QuoteRelated_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	boolean quoteRecordFound = false;
	int totalPrice = 0;

	public SFDC_QuoteRelated_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Related Tab
	 * 
	 *             Verify Email PDF Attachment is populated for the quote
	 * 
	 *             Click on Quote Email PDF Attachment
	 * 
	 *             Verify Email PDF Attachment is getting displayed for the quote
	 * 
	 */
	public void verifyQuotePdfAttachment() throws IOException {
		try {
			String methodName = "SFDC_Related@: ";

			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			// Verify Email PDF Attachment is populated for the quote
			if (sf.seleU.isElementDisplayed(sf.qr.filesSubTab) && sf.qr.filesAttachmentAllLinks.size() > 0) {
				reportStatusPass(
						methodName + " Email PDF Attachment is populated for the quote " + InputData.quoteNumber, true,
						true);
			} else {
				reportStatusFail(methodName + " Quote Email PDF Attachment is not present", true);

			}

			// Click on Quote Email PDF Attachment
			sf.seleU.clickElementByJSE(sf.qr.filesAttachmentAllLinks.get(0));
			reportStatusPass(methodName + " Clicked on  Quote Email PDF Attachment", true, false);

			sf.seleU.hardwait(3000);

			// Verify Email PDF Attachment is getting displayed for the quote
			if (sf.seleU.isElementDisplayed(sf.qr.loadedPagePDFAttachment)) {
				reportStatusPass(methodName + " Email PDF Attachment is getting displayed for the quote "
						+ InputData.quoteNumber, true, true);
				sf.seleU.clickElementByJSE(sf.qr.closePDFButton);
			} else {
				reportStatusFail(methodName + " Quote Email PDF Attachment is not Displayed", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Quote Email PDF Attchement", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on Related Tab
	 * 
	 *             Verify the product items in Quote Line items
	 * 
	 * 
	 */
	public void verifyProductsInQuoteLineItem() throws IOException {
		try {
			String methodName = "SFDC_verify Product Price InQuote Line Items@: ";

			sf.seleU.wait(2000);
			// Click on Related Ta
			sf.seleU.clickElementByJSE(sf.qr.quoteRelatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			sf.seleU.hardwait(2000);
			// Click on Quote line items view all
			sf.seleU.clickElementByJSE(sf.qr.quoteLineItemsViewAllClick.get(0));
			reportStatusPass(methodName + " Clicked on Quote line items view all", true, false);

			if (sf.qr.quoteLineItemsProductName.size() > 0) {

				for (int i = 0; i < sf.qr.quoteLineItemsProductName.size(); i++) {
					sf.seleU.wait(1000);
					// Add Quote Line Items Product Name With the product which was added during the
					// intial stage
					// on rogers business solution page

					sf.dataInput.quoteLineItemsProductName
					.add(sf.seleU.getTextFromWebElement(sf.qr.quoteLineItemsProductName.get(i)).trim());

					reportStatusPass(
							methodName + "Product is found in the Quote Line items list "
									+ sf.seleU.getTextFromWebElement(sf.qr.quoteLineItemsProductName.get(i)),
									true, false);
				}
			} else {
				sf.seleU.hardwait(2000);
				reportStatusFail(methodName + "Product is not found in the Quote Line items list ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Product price items", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj agarwal PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on Related Tab
	 * 
	 *             Click on view all in product items
	 * 
	 *             Verify the RDI products and extract the product speed
	 * 
	 * 
	 */
	public void extractProductDetailsInOrderProductItems(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Product details in order related tab@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Order Related Tab", true, false);

			sf.seleU.hardwait(2000);
			// Click on Quote line items view all
			sf.seleU.clickElementByJSE(sf.qr.orderProductItemsViewAllClick.get(0));
			reportStatusPass(methodName + " Clicked on Product items view all", true, false);
			sf.seleU.wait(4000);

			for (int i = 0; i < sf.qr.orderProductItemsName.size(); i++) {
				sf.seleU.wait(3000);

				// Matching with RDI Product Name With the product which was added during the
				// intial stage
				// on rogers business solution page

				if (sf.seleU.getTextFromWebElement(sf.qr.orderProductItemsName.get(i)).replaceAll(" ", "")
						.contains(dataTable.get("Dedicated_Internet_Product").replaceAll(" ", ""))) {

					sf.dataInput.quoteProductName = sf.seleU.getTextFromWebElement(sf.qr.orderProductItemsName.get(i))
							.trim();

					// Split it to extract the speed from the product details
					String Speed[] = sf.seleU.getTextFromWebElement(sf.qr.orderProductItemsName.get(i)).trim()
							.split(" ");
					sf.dataInput.quoteInternetUploadSpeed = Speed[3] + " " + Speed[4];

					sf.seleU.hardwait(2000);
					reportStatusPass(
							methodName + "Product is found in the Product items list " + sf.dataInput.quoteProductName
							+ " " + "With spped as " + sf.dataInput.quoteInternetUploadSpeed,
							true, false);
					quoteRecordFound = true;
					break;
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Verify Product in Order Product List", e);
			e.printStackTrace();
		}
	}

	/**
	 * Pankaj Agarwal
	 * 
	 * @throws IOException
	 * 
	 *             Click on Related Tab
	 * 
	 *             Verify Email PDF Attachment is populated for the quote
	 * 
	 *             Click on Quote Email PDF Attachment
	 * 
	 *             Verify Email PDF Attachment is getting displayed for the quote
	 * 
	 */
	public void verifyPriceItems(Hashtable<String, String> dataTable) throws IOException {
		try {
			String methodName = "SFDC_Verify Price Items In QuoteLine Items@: ";

			// Click on Related Tab
			if (quoteRecordFound) {
				sf.seleU.wait(2000);

				// If the product is found verify the total charges of the product in the list
				// from excel sheet

				String strTotalPrice = String.valueOf(totalPrice);
				if (strTotalPrice.equals(dataTable.get("MONTHLY TOTAL"))) {
					reportStatusPass(
							methodName + "Total Charge for the product is " + dataTable.get("Recurring Cost Internet"),
							true, false);
				} else {
					reportStatusFail(methodName + "Total Price is not equal", true);
				}
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Price Items In QuoteLine Items", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Related tab
	 * 
	 *             Verify Orchestartion Plan is displayed
	 * 
	 *             Click on orchestration plan number
	 * 
	 */
	public void verifyR4BQuoteNameApproval() throws IOException {
		try {
			String methodName = "SFDC_Verify R4B QuoteName Approval@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.hardwait(4000);
			// Verify quote link is displayed
			if (sf.seleU.isElementDisplayed(sf.qr.quoteApprovalNameLink)
					&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalNameLink).length() > 0) {

				sf.dataInput.quoteR4BApprovalName = sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalNameLink);

				reportStatusPass(methodName + " Verified R4b quote approval name in quote Relted Tab", true, true);

			} else {
				reportStatusFail(" Error in Viewing r4b quote approval name in related tab", true);
			}
			// sf.seleU.hardwait(3000);

			// Click on orchestration plan number
			// sf.seleU.clickElementByJSE(sf.qr.quoteApprovalNameLink);
			// reportStatusPass(methodName + " Clicked on r4b quote approval name link",
			// true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing quote approval name in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI04 - SP04
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Related tab
	 * 
	 *             Verify Order number is displayed
	 * 
	 *             Click on order number
	 * 
	 */
	public void verifyMultisite_OrderNumberInQuoteRelatedAfterApproved(String subOrderStatus) throws IOException {
		try {
			String methodName = "SFDC_Verify Multisite Order Number In Quote related@: ";
			SFDC_OrderDetails_Page orderDetails = new SFDC_OrderDetails_Page();
			SFDC_Orders_Page orders = new SFDC_Orders_Page();
			SFDC_Home_Page home = new SFDC_Home_Page();
			sf.seleU.wait(4000);
			int subOrderCount = 0;
			
			// Click on Related Tab
			sf.seleU.ScrolltoElementPageCenter(sf.qr.relatedTab);
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.hardwait(2000);
			for(int i = 0; i<= InputData_Communities.mulPBFNumOfSites; i++) {
				sf.seleU.wait(3000);
				sf.seleU.ScrolltoElementPageCenter(sf.qr.ordersHeader);
				// Verify order number is displayed
				sf.seleU.wait(5000);
				if (sf.seleU.isElementDisplayed(sf.qr.orderNameLinkInQuoteRelated.get(i))
						&& sf.seleU.getTextFromWebElement(sf.qr.orderNameLinkInQuoteRelated.get(i)).length() > 0) {
					sf.seleU.hardwait(2000);
					
					// Click on order number
					sf.seleU.ScrolltoElementPageCenter(sf.qr.orderNameLinkInQuoteRelated.get(i));
					sf.seleU.clickElementByJSE(sf.qr.orderNameLinkInQuoteRelated.get(i));
					reportStatusPass(methodName + " Order number is present and clicked on order Number In Quote related",
							true, false);
					if(i==0) {
						// Only Master order will be In Progress state.
						orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
					} else {
						// Verfying the suborder status and update the site contact
						orderDetails.verifyCreatedOrderInOrderDetailsTab(subOrderStatus);
						
						// update the site contact from the order page if the order is ready to submit state
						if(subOrderStatus.equals(sf.dataInput.orderStatusReadyToSubmit)){
							orders.updatePBFMultisiteOrderWithSiteContact();
							orderDetails.verifyCreatedOrderInOrderDetailsTab(sf.dataInput.orderStatusInProgress);
						}
											
						subOrderCount++;
					}
					home.closeLastOpenedTab();
					sf.seleU.hardwait(5000);

				} else {
					reportStatusFail(" Error in Viewing order numbe in quote related", true);
				}
			}
			if(subOrderCount == InputData_Communities.mulPBFNumOfSites) {
				reportStatusPass(methodName + " All the suborders for the multisites are verified",true, false);
			} else {
				reportStatusFail(" Error in verfying the no of suborder created", true);
			}
					
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing order in quote in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Related tab
	 * 
	 *             Verify Order number is displayed
	 * 
	 *             Click on order number
	 * 
	 */
	public void verifyOrderNumberInQuoteRelatedAfterApproved() throws IOException {
		try {
			String methodName = "SFDC_Verify Order Number In Quote related@: ";

			sf.seleU.wait(4000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.hardwait(2000);
			sf.seleU.scrollByCoOrdinates(3);
			// Verify order number is displayed
			if (sf.seleU.isElementDisplayed(sf.qr.orderNameLinkInInQuoteRelated)
					&& sf.seleU.getTextFromWebElement(sf.qr.orderNameLinkInInQuoteRelated).length() > 0) {
				sf.seleU.hardwait(7000);
				// Click on order number
				sf.seleU.clickElementByJSE(sf.qr.orderNameLinkInQuoteRelated.get(1));
				reportStatusPass(methodName + " Order number is present and clicked on order Number In Quote related",
						true, false);

			} else {
				reportStatusFail(" Error in Viewing order numbet in quote related", true);
			}
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing order in quote in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Related tab
	 * 
	 *             Verify Order number is displayed
	 * 
	 *             Click on order number
	 * 
	 */
	public void verifyOrderNotCreatedInQuoteRelatedAfterApproved() throws IOException {
		try {
			String methodName = "SFDC_Verify Order Number In Quote related@: ";

			sf.seleU.wait(4000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.hardwait(2000);
			// Verify order number is displayed
			if (!sf.seleU.isElementDisplayed(sf.qr.orderNameLinkInInQuoteRelated))
				reportStatusPass(" No Order is created in quote related tab", true, true);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing order in quote in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on Order Related tab
	 * 
	 *             Verify Order number is displayed
	 * 
	 *             Click on Sub order number
	 * 
	 */
	public void clickOnOrderNoFromQuote() throws IOException {
		try {
			String methodName = "SFDC_Verify Order Number In Quote related@: ";
			if (InputData_WA.env.equals("WADEVQA")) {
				sf.seleU.refreshPage();
			}
			sf.seleU.wait(4000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.hardwait(2000);
			// Verify order number is displayed
			if (sf.seleU.isElementDisplayed(sf.qr.orderNameLinkInInQuoteRelated1)
					&& sf.seleU.getTextFromWebElement(sf.qr.orderNameLinkInInQuoteRelated1).length() > 0) {
				sf.seleU.hardwait(4000);
				// Click on order number
				sf.seleU.clickElementByJSE(sf.qr.orderNameLinkInInQuoteRelated1);
				reportStatusPass(methodName + " Order number is present and clicked on order Number In Quote related",
						true, false);

			} else {
				reportStatusFail(" Error in Viewing order numbet in quote related", true);
			}
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing order in quote in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA PI02 - SP01
	 * 
	 * @throws IOException
	 * 
	 *             Click on QUote Related tab
	 * 
	 *             Approve it for credit check
	 * 
	 */
	public void approveR4BQuoteForCreditCheck() throws IOException {
		try {
			String methodName = "SFDC_Verify_Quote_Approve_For_Credit_Check@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			sf.seleU.hardwait(4000);
			sf.seleU.scrollByCoOrdinates(3);
			// Verify quote link is displayed
			if (sf.seleU.isElementDisplayed(sf.qr.quoteApprovalNameLink)
					&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalNameLink).length() > 0) {

				// Approve the quote
				sf.seleU.clickElementByJSE(sf.qr.quoteApprovalNameLink);
				reportStatusPass(methodName + " Clicked on Quote Approval Name Link", true, false);
				sf.seleU.hardwait(4000);

				sf.seleU.clickElementByJSE(sf.qr.r4BApprovalRelatedTab);
				sf.seleU.hardwait(4000);

				// sf.seleU.ScrolltoElement(sf.qr.quoteApprovalClick);
				// sf.seleU.hardwait(1000);
				// sf.seleU.clickElementByJSE(sf.qr.quoteApprovalClick);
				// reportStatusPass(methodName + " Clicked on Quote Approval Status", true,
				// false);
				// sf.seleU.hardwait(2000);
				//
				// sf.seleU.clickElementByJSE(sf.qr.quoteApprovalDropdown);
				// reportStatusPass(methodName + " Clicked on Quote deopdown", true, false);
				//
				// sf.seleU.clickElementByJSE(sf.qr.quoteApprovalDropdownValueApproved);
				// reportStatusPass(methodName + " Clicked on Quote dropdown approved value",
				// true, false);
				// sf.seleU.hardwait(3000);
				//
				// sf.seleU.clickElementByJSE(sf.qr.quoteApprovalSaveButton);
				// reportStatusPass(methodName + " Clicked on Quote Save Button", true, false);
				// sf.seleU.wait(15000);
				sf.seleU.ScrolltoElement(sf.qr.approveQouteButton);
				sf.seleU.clickElementByJSE(sf.qr.approveQouteButton);
				sf.seleU.hardwait(1000);

				sf.seleU.clickElementByJSE(sf.qr.saveApprove);
				reportStatusPass(methodName + " Clicked on Approval Save Button", true, false);
				sf.seleU.wait(15000);

			} else {
				reportStatusFail(" Error in Viewing r4b quote approval name in related tab", true);
			}
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing quote approval name in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA 22PI02 - SP01 jan 2022
	 * 
	 * @throws IOException
	 * 
	 *             Click on QUote Related tab
	 * 
	 *             Approve it for credit check
	 * 
	 */
	public void pickApproveR4BQuoteForCreditOrFraudCheck_Approve(String checkType) throws IOException {
		try {
			String methodName = "SFDC_PickApprove_R4BQuoteFor_CreditOrFraudCheck@: ";
			int fraudIndex = 0, creditIndex = 0;
			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);

			sf.seleU.wait(5000);
			sf.seleU.ScrolltoElement(sf.qr.r4BQuoteApprovalText);
			sf.seleU.scrollUpByCoOrdinates();
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			// For both credit fraud in progress then credit index is 0 and fraud index is 1
			// else it will be 0 if only
			// is check is present either credit or fraud.
			if (sf.qr.quoteApprovalNameLink_URL.size() > 1) {
				creditIndex = 0;
				fraudIndex = 1;
			} else {
				creditIndex = 0;
				fraudIndex = 0;
			}
			// for credit check
			if (checkType.equals("Credit Check")) {
				if (sf.seleU.isElementDisplayed(sf.qr.quoteApprovalNameLink_URL.get(creditIndex))
						&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalType.get(creditIndex)).trim()
						.equals("Credit Check")
						//&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalStatusType.get(creditIndex)).trim()
						//.equals(sf.dataInput.orderStatusInProgress)
						) {

					// Approve the quote
					sf.seleU.clickElementByJSE(sf.qr.quoteApprovalNameLink_URL.get(creditIndex));
					reportStatusPass(methodName + " Clicked on Quote Approval Name Link for credit check", true, false);
					sf.seleU.hardwait(2000);

					sf.seleU.clickElementByJSE(sf.qr.r4BApprovalRelatedTab);
					sf.seleU.hardwait(4000);

					// approve the quote
					sf.seleU.ScrolltoElement(sf.qr.approveQouteButton);
					sf.seleU.clickElementByJSE(sf.qr.approveQouteButton);
					sf.seleU.hardwait(1000);

					sf.seleU.clickElementByJSE(sf.qr.saveApprove);
					reportStatusPass(methodName + " Clicked on final Approvalo ok button", true, false);
					sf.seleU.wait(2500);
				}
			}
			sf.seleU.wait(3000);

			// for fraud check
			if (checkType.equals("Fraud Review"))
				if (sf.seleU.isElementDisplayed(sf.qr.quoteApprovalNameLink_URL.get(fraudIndex))
						&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalType.get(fraudIndex)).trim()
						.equals("Fraud Review")
						//&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalStatusType.get(fraudIndex)).trim()
						//.equals(sf.dataInput.orderStatusInProgress)
						) {

					approveR4BFraudQuote(fraudIndex);
				}

				else {
					reportStatusFail(" Error in Viewing r4b quote approval name in related tab", true);
				}
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing quote approval name in related tab", e);
			e.printStackTrace();
		}
	}
	
	public void checkR4BFieldHistory_awaitingesign() throws IOException {
		try {
			String methodName = "checkR4BFieldHistory_awaitingesign@: ";
			//Boolean checkFound = false;
			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);

			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.qr.r4BApprovalHistoryText);
			sf.seleU.scrollUpByCoOrdinates();
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			
				if (!(sf.qr.r4bHistoryQuoteLink_URL.size() > 0)) {
					reportStatusPass(methodName + " Successfully validated r4b history is not present when quote is in awaiting signature", true, true);
							
							sf.seleU.wait(1000);
							}	
				else {
					reportStatusFail("R4B History is present and should not be present",true);
				}
					
				
		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing R4B History", e);
			e.printStackTrace();
		}
	}

	/**
	 * PA 22PI02 - SP02 Feb 2022
	 * 
	 * @throws IOException
	 * 
	 *             Check the R4b Field history after quote approval 
	 *           
	 * 
	 */
	public void checkStatusIn_R4BFieldHistory(String checkType) throws IOException {
		try {
			String methodName = "SFDC_CheckStatusIn_R4BFieldHistory@: ";
			Boolean checkFound = false;
			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);

			sf.seleU.hardwait(2000);
			sf.seleU.ScrolltoElement(sf.qr.r4BApprovalHistoryText);
			sf.seleU.scrollUpByCoOrdinates();
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			outerloop:
				if (sf.qr.r4bHistoryQuoteLink_URL.size() > 1) {
					for(int i = 0; i < sf.qr.r4bHistoryQuoteLink_URL.size(); i++ ) {
						sf.seleU.ScrolltoElementPageCenter(sf.qr.r4bHistoryQuoteApprovalType.get(i));
						// for credit check or raud check in r4b history after the quote is approve
						if(sf.seleU.getTextFromWebElement(sf.qr.r4bHistoryQuoteApprovalType.get(i)).trim()							.equals(checkType)
								&& sf.seleU.getTextFromWebElement(sf.qr.r4bHistoryQuoteApprovalValueType.get(i)).trim()
								.equals(sf.dataInput.quoteStatusApproved) 
								&& sf.seleU.getTextFromWebElement(sf.qr.r4bHistoryQuoteFinalStatus.get(i)).trim()
								.equals(sf.dataInput.finalisedStatus)) {

							reportStatusPass(methodName + " Successfully validated the quote status in r4b history"
									+ "for approval type as " + checkType + "with status as " + sf.dataInput.quoteStatusApproved +
									"quote final status as " + sf.dataInput.finalisedStatus, true, true);
							checkFound = true;
							sf.seleU.wait(1000);
							break outerloop;
						}				
					}
				}
			if(!checkFound)
				reportStatusFail(" Error in Viewing r4bHistory quote approval name in related tab", true);

			sf.seleU.hardwait(1000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing R4B History", e);
			e.printStackTrace();
		}
	}

	/**
	 * @author Pankaj Agarwal 20Jan/2022
	 * @throws IOException
	 * 
	 *             Approve the Credit/Fraud check
	 * 
	 */
	public void approveR4BFraudQuote(int index) throws IOException {
		try {
			// SFDC_AllPages sfdc = new SFDC_AllPages();
			String methodName = "SFDC_Approve R4B Quote for fraud check@: ";
			if (!sf.seleU.isElementPresent(sf.r4Bquote.approveButton)) {

				sf.seleU.clickElementByJSE(sf.qr.quoteApprovalNameLink_URL.get(index));
				reportStatusPass(methodName + " Clicked on Quote Approval Name Link for fraud review", true, false);
				sf.seleU.hardwait(4000);

				// Verify R4b quote before record type, approval type, approval status, approval
				// time
				if (sf.dataInput.fraudCheckStatus!=null || sf.dataInput.fraudCheckStatus_PriceAbove_2000!=null)
					SFDC_R4B_Quote_Approval_Page_Layout_Page.verifyR4BQuoteDetails_BeforeApproval("Fraud Review");

				// Approve the quote
				sf.seleU.clickElementByJSE(sf.qr.r4BApprovalRelatedTab);
				sf.seleU.hardwait(4000);
			}
			sf.seleU.ScrolltoElement(sf.r4Bquote.approveButton);
			sf.seleU.scrollUpByCoOrdinates();
			sf.seleU.clickElementByJSE(sf.r4Bquote.approveButton);
			sf.seleU.switchToActiveElement();
			sf.seleU.clickElementByJSE(sf.r4Bquote.finalApproveButton);
			reportStatusPass(methodName + " Clicked on Final Approve Button", true, false);
		} catch (Throwable e) {
			reportStatusFail(" Error in approving Credit/Fraud checks", e);
			e.printStackTrace();
		}
	}

	/**
	 * @CreatedBy Sakshi Sakshi 17.08.2021
	 *
	 * @throws IOException
	 * 
	 *             Click on Quote Related tab
	 * 
	 *             Verify Order number is displayed
	 * 
	 *             Get the order number
	 *             
	 *             
	 * 
	 */
	public void getOrderNumberFromQuoteRelatedTab(String orderNumber) throws IOException {
		try {
			String methodName = "SFDC_Verify Order Number In Quote related@: ";

			sf.seleU.waitForLoading();
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.waitForLoading();
			sf.seleU.ScrolltoElementPageCenter(sf.qr.ordersHeader);
			// Verify order number is displayed
			if(sf.qr.orderNamesLinkInInQuoteRelated1.size()<=1) {
				sf.seleU.clickElementByJSE(sf.qr.orderViewAll);
			}
			for (int i = 1; i < sf.qr.orderNamesLinkInInQuoteRelated1.size(); i++) {
				if (sf.seleU.isElementDisplayed(sf.qr.orderNamesLinkInInQuoteRelated1.get(i))
						&& sf.seleU.getTextFromWebElement(sf.qr.orderNamesLinkInInQuoteRelated1.get(i)).length() > 0) {
					sf.seleU.waitForLoading();
					// Click on order number
					orderNumber = sf.seleU.getTextFromWebElement(sf.qr.orderNamesLinkInInQuoteRelated1.get(i));
					Global.dataInput.orderNumber = orderNumber;
					// System.out.println("got order number is : " +sf.dataInput.orderNumber);
					reportStatusPass(methodName + " Order number is present and fetched In Quote related : "
							+ sf.dataInput.orderNumber, true, false);
					sf.seleU.waitForLoading();
					sf.seleU.clickElementByJSE(sf.qr.orderNamesLinkInInQuoteRelated1.get(i));
					reportStatusPass(
							methodName + " Order number is present and clicked on order Number In Quote related", true,
							false);

				} else {
					reportStatusFail(" Error in fetching Sub order number in quote related", true);
				}
				sf.seleU.hardwait(4000);

			}
		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing order in quote in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Click on Order Related tab
	 * 
	 *             Verify Order numbers are displayed
	 * 
	 *             Click on order number (Sub Order)
	 * 
	 */
	public void clickOnSubOrderNoFromQuoteWACC() throws IOException {
		try {
			String methodName = "SFDC_Verify Order Number In Quote related@: ";
			if (InputData_WA.env.equals("WADEVQA")) {
				sf.seleU.refreshPage();
			}
			sf.seleU.wait(4000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);
			sf.seleU.hardwait(2000);
			// Verify order number is displayed
			if (sf.seleU.isElementDisplayed(sf.qr.orderNameLinkInInQuoteRelated1)
					&& sf.seleU.getTextFromWebElement(sf.qr.orderNameLinkInInQuoteRelated1).length() > 0) {
				sf.seleU.hardwait(4000);
				// Click on order number
				sf.seleU.clickElementByJSE(sf.qr.orderNameLinkInInQuoteRelated1);
				reportStatusPass(methodName + " Order number is present and clicked on order Number In Quote related",
						true, false);

			} else {
				reportStatusFail(" Error in Viewing order numbet in quote related", true);
			}
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing order in quote in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @author Satish.Doraiswamy
	 * 
	 * @throws IOException
	 * 
	 *             Click on QUote Related tab
	 * 
	 *             Click on App Link
	 * 
	 */
	public void clickOnAppR4BQuoteForCreditCheck() throws IOException {
		try {
			String methodName = "SFDC_Verify_Quote_Approve_For_Credit_Check@: ";

			sf.seleU.wait(2000);
			// Click on Related Tab
			sf.seleU.clickElementByJSE(sf.qr.relatedTab);
			reportStatusPass(methodName + " Clicked on Quote Related Tab", true, false);

			sf.seleU.hardwait(4000);
			sf.seleU.scrollByCoOrdinates(3);
			// Verify quote link is displayed
			if (sf.seleU.isElementDisplayed(sf.qr.quoteApprovalNameLink)
					&& sf.seleU.getTextFromWebElement(sf.qr.quoteApprovalNameLink).length() > 0) {

				// Approve the quote
				sf.seleU.clickElementByJSE(sf.qr.quoteApprovalNameLink);
				reportStatusPass(methodName + " Clicked on Quote Approval Name Link", true, false);
				sf.seleU.hardwait(4000);

			} else {
				reportStatusFail(" Error in Viewing r4b quote approval name in related tab", true);
			}
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing quote approval name in related tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @author Satish.Doraiswamy
	 * @throws IOException
	 * 
	 *             Click on QUote Related tab
	 * 
	 *             Approve it for credit check
	 * 
	 */
	public void approveQuoteForCreditCheck() throws IOException {
		try {
			String methodName = "SFDC_Verify_Quote_Approve_For_Credit_Check@: ";

				sf.seleU.clickElementByJSE(sf.qr.r4BApprovalRelatedTab);
				sf.seleU.hardwait(4000);
				
				sf.seleU.ScrolltoElement(sf.qr.approveQouteButton);
				sf.seleU.clickElementByJSE(sf.qr.approveQouteButton);
				sf.seleU.hardwait(1000);

				sf.seleU.clickElementByJSE(sf.qr.saveApprove);
				reportStatusPass(methodName + " Clicked on Approval Save Button", true, false);
				sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing quote approval name in related tab", e);
			e.printStackTrace();
		}
	}
}
