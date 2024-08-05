package scrum.om.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.BaseBrowser;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_OM_Cable_CancelOrder_Test extends BaseBrowser{

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                         - Cable only order with no running tasks - order cancellation before PONR
							   - Review Order Details task is in Ready status when the order is cancelled
							   - Original order goes in status Cancelled
							   - Supplemental order goes in status Activated
							   - Orchestration plan is unlinked from original order and linked to Supplemental order
							   - All tasks are "Discarded"
							   - Notifications are sent to AE for cable order
	 * 
	 */ 


	@Test(dataProvider = "getRDIOrderCancel")
	public void test_Validate_Cable_Cancel_Order_BeforePONR(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);
		//		System.out.println(dataTable.get("Dedicated_Internet_Product"));
		//
		sf.dataInput.orderNumber = "00111071";
		//		sf.omData.canceledOrderNumber = "00239716";
		//		sf.omData.supersededOrderNumber  = "00013382";00239716

		// **************Login with AE profile*****************
		// sfdc.login.loginToSFDC(InputData.Profile_AE);
		// // sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		// // // Thread.sleep(8000);
		// // ****Search the business account****
		// // sfdc.accDetails.searchAccount(Global.dataInput.tempBusinessAccountName);
		// sfdc.accDetails.searchBusAccGlobalSearch("BAAPankaj2");
		//
		// // *****Extract the credit check value entered for validation************
		// // if(dataTable.get("Credit_Check_Value_Enter").equals("Yes")) {
		// // sfdc.accDetails.extractCreditCheckRequiredValidationValues();
		// // }
		//
		// /****************************************************************/
		//// sfdc.login.loginToSFDC(InputData.userid_ae);
		//// sfdc.home.closeTabIfOpen();
		////
		//// sfdc.accDetails.searchAccount(sf.dataInput.account_ATL);
		//
		//
		// // ****Create Opportunity and select existing contact in
		// // opportunity***************//
		// sfdc.accRelated.createOpportunity();
		// sfdc.cOpp.enterOpportunityDetails();
		// sfdc.cOpp.selecetExistingContactInOpportunity("ContactPankajSign
		// ContactPankajSign");
		//
		// // *******Create Quote through GBJ*********//
		//  sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
		// sfdc.cQuote.createQuote_SelectBillingAccountNext();
		//
		// // ****Create Service Account with different access type group as per the
		// test
		// // data*****//
		//		 sfdc.cQuote.createQuote_SelectNewServiceAccount();
		//		 sfdc.csa.enterServiceAccountInfo(1);
		//		 sfdc.csa.verifyServiceAccountCreated();
		//		 sfdc.csa.checkServicability_AddServiceLocations(dataTable);
		//		 sfdc.csa.clickOnNextInCheckServicabilityInGbj();
		//		 sfdc.cQuote.createQuote_SelectCreatedNewServiceAccount();
		//		
		// // for existing service account use the below one
		// // sfdc.csa.createQuote_SelectCreatedServiceAccount(dataTable);
		//
		// // ***Add RDI Product in the cart based on the test data***********//
		// sfdc.gbjRDI.addRogersBusinessSolutions_RDI(dataTable, false);
		//
		// // If the test data does't have the second addon product based on the
		// dedicated
		// // internet speed then it will have the
		// // Quote review
		// if (dataTable.get("SecondAdds_On").equals("NA")) {
		// sfdc.quoteReview.verifyDetailsinQuoteReviewForRDI(dataTable, false);
		// }
		//
		// // *****Acccept Quote and create new site contact
		// sfdc.cpqHome.acceptQuote();
		// sfdc.gdPdf.sendQuoteEmail();
		//
		// // sfdc.siteCon.createNewSiteContact();
		//
		// sfdc.siteCon.selectExistingSiteContact_Scrum();


		//		//	 *********Login with AE profile*******************
		//		intializeChrome(false);
		//		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//		// //Thread.sleep(7000);
		//		//		sfdc.home.openR4BSalesConsole();
		//		sfdc.home.closeTabIfOpen();
		//
		//		// ***************Extract the order no from excel sheet***********************//
		//		// sf.dataInput.orderNumber = dataTable.get("Order_Number");
		//		sf.dataInput.orderNumber = "00011165";
		//		System.out.println(sf.dataInput.orderNumber);
		//		sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
		//
		//		// ***************Extract Order details from details
		//		// tab***********************//
		//		sfdc.orderDetails.extractOrderDetails();
		//		sfdc.home.closeTabIfOpen();
		//		sfdc.home.logout();

		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();  

		// *****verify order status is activated****************************	
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyAllTaskItemsStaus("Ready");
		sfdc.home.closeLastOpenedTab();

		//****Cable cancel Order********
		sfdc.orders.cancel_Order();
		sfdc.orderDetails.verifyCancelledOrderInOrderDetaisTab();

		// For cable cancel order AE will get the notification, not the SD
		sfdc.orders.verifyNotificationInIcon();
		sfdc.orders.verifyOpenTaskStatus_ForCancel_FromAE();
		sfdc.home.closeTabIfOpen();

		// ******Verify order is canceled and superseded order is generated which is activated **** 
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);

		// Verify No orchestration plan for RDI in original order
		sfdc.orderRelated.verifyNoOrchestrationPlanInOrder();

		// Verify Superseded order generated
		sfdc.orderRelated.verifySupersededOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.orders.globalSearchOrderFromHomeMenu(sf.omData.supersededOrderNumber);
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();

		// Verify All ready tasks are discarded
		sfdc.orchPlan.verifyAllTaskItemsStaus("Discarded");
		sfdc.home.closeLastOpenedTabs(1);
		sfdc.orderDetails.verifyOrderIsActivated();	

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		driver.quit();	
	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		// Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
		Global.dataInput.office365ProductName = dataTable.get("Office 365 AddOn");
		Global.dataInput.tvProductName = dataTable.get("TV Product Name");

		Global.dataInput.address = dataTable.get("Address");
		Global.dataInput.addressStreet = dataTable.get("Address Street");
		Global.dataInput.addressCity = dataTable.get("Address City");
		Global.dataInput.addressState = dataTable.get("Address State");
		Global.dataInput.addressCountry = dataTable.get("Address Country");
		Global.dataInput.serviceAddress = dataTable.get("CSA_Service Address");

		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}

	@DataProvider
	public Object[][] getRDIOrderCancel() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}
}


