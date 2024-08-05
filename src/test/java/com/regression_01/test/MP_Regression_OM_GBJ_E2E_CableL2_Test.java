
package com.regression_01.test;

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

public class MP_Regression_OM_GBJ_E2E_CableL2_Test extends BaseBrowser {

	/*
	 * @param dataTable
	 * 
	 * @throws IOException
	 * 
	 * @throws InterruptedException
	 * 
	 * Create Account (Business, Service , Billing ) and Contacts
	 * 
	 * Create Opportunity and Create Quote BY GBJ
	 * 
	 * Add Different combinations of IBLC, Internt and TV Products/Promotions in GBJ
	 * 
	 * Verify the Order Creation and Activation
	 */
	@Test(dataProvider = "getTestData_IBLC_E2E")
	public void test_GuidedByJourney_IBLC_INT_TV_E2E(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		intializeChrome(false);
		SFDC_AllPages sfdc = new SFDC_AllPages();
		setInputData(dataTable);
		System.out.println(Global.dataInput.addressStreet);

		// Global.dataInput.tempBusinessAccountName = "OM_RegressionTest";
		// Global.dataInput.tempBusinessAccountName = "BAAPankaj2";
		Global.dataInput.tempBusinessAccountName = "BusinessPankaj03";
		// Global.dataInput.tempBusinessAccountName = "Auto_QA_BA_ 210705022701";
		Global.dataInput.businessAccountName = Global.dataInput.tempBusinessAccountName;
		// sf.dataInput.serviceAccountName = "ASQAAuto210703029336";
		// sf.dataInput.serviceAccountName = "OM_TestCable_9July_02";
		// sf.dataInput.orderNumber = "00012454";

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		// sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();

		// sfdc.acc.createNewBusinessAccount();
		// sfdc.cba.enterBusinessAccountInfo(false);
		// sfdc.cba.verifyBusinessAccountCreated();
		//
		// sfdc.cc.enterCreateContactInfo(false);
		// sfdc.cc.verifyMarkPrimaryContactReadOnly();
		// sfdc.cc.verifyContactCreated();
		// sfdc.cc.clickOnNextInCreateContact();
		//
		// sfdc.home.closeTabIfOpen();
		// sfdc.home.logout();
		//
		// // *********Login as Data Governance and Approve business Account******//
		// // intializeChrome(false);
		// sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		// sfdc.home.closeTabIfOpen();
		// // sfdc.accDetails.searchAccount(sf.dataInput.businessAccountName);
		//
		// sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.tempBusinessAccountName);
		// sfdc.accRelated.approveAsDataGovernance();
		// sfdc.home.closeTabIfOpen();
		//
		// sfdc.login.loginToSFDC(InputData.userid_ae);
		// // sfdc.home.openR4BSalesConsole();
		// sfdc.home.closeTabIfOpen();
		//
//		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
//		sfdc.accRelated.createOpportunity();
//		sfdc.cOpp.enterOpportunityDetails();
//		sfdc.cOpp.selecetExistingContactInOpportunity();
//
//		// *******Create Quote through GBJ*********//
//		sfdc.cQuote.createQuoteGuidedByJourney_SelectExistingSigningAuthority();
//		sfdc.cQuote.createQuote_SelectBillingAccountNext();
//
//		sfdc.cQuote.createQuote_SelectAddNewSite();
//	//	sfdc.csa.enterServiceAccountInfoWithParameter(1, Global.dataInput.tempBusinessAccountName, "GBJ");
//		sfdc.csa.enterServiceAccountInfo(1);
//		sfdc.csa.clickOnNextInCreateServiceAccount();
//		sfdc.cQuote.createQuote_SelectCreatedServiceAccount();
//
//		if (!dataTable.get("Internet Product Name").equals("NA")) {
//			sfdc.gbjCart.addRogersBusinessSolutions_Internet(dataTable, false, false);
//
//			if (!dataTable.get("Office 365 AddOn").equals("NA")) {
//				sfdc.gbjCart.addRogersOffice365AddOns(dataTable, false);
//			}
//			sfdc.gbjCart.hitNext();
//		}
//
//		if (dataTable.get("Product _Type").contains(InputData.tvProduct)) {
//			sfdc.gbjCart.addRogersBusinessSolutions_TV(dataTable, false);
//			sfdc.gbjCart.addRogersTVAddOns(dataTable, false);
//		}
//
//		sfdc.gbjCart.hitCheckOut();
//
//		// *****Acccept Quote and create new site contact
//		sfdc.gbjCart.acceptQuoteInGBJ();
//		sfdc.gdPdf.sendQuoteNextButton();
//		sfdc.siteCon.selectExistingSiteContact_Scrum();
//
//		// verify Order created
//		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();

//		//	 ***************LOGIN AS Delivery OM Part***********************//
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//		//		sf.dataInput.orderNumber = dataTable.get("Order_No").trim();
//		//		sf.dataInput.orderNumber = "00017396";
//		//		//	Global.dataInput.businessAccountName = "BAAPankaj2";
//		//		Global.dataInput.businessAccountName = "OM_RegressionTest";
//
//		// *************Verify and complete Review order Details task from the manual queue****
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//
//		// 1. ***********Click on delivery specialist queue and pick Review order details task*********//
//		sfdc.mques.selectCableOrderProvisioningQueue();
//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//		driver.quit();
//
//		// **************Login with AE Profile**********************************
//		intializeChrome(false);
//		sfdc.login.loginToSFDC(InputData.Profile_AE);
//		sfdc.home.closeTabIfOpen();
//
//		// *******Extract Order details from details tab And AE profile Information***********//
//		//	sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderDetails.extractOrderDetails();
//		sfdc.home.closeLastOpenedTab();
//
//		sfdc.orderDetails.extractAeProfileInformation();
//		sfdc.home.closeLastOpenedTab();
//
//		sfdc.orderDetails.extractContactDetailsFromOrderPage();
//		sfdc.home.closeLastOpenedTab();
//
//		// ***************Extract Order details from details
//		// tab***********************//
//		// 	sfdc.orderRelated.extractCableProductDetailsInOrderRelated(dataTable);
//		//	sfdc.orderRelated.extractCableTVProductDetailsInOrderRelated(dataTable);
//		//					sfdc.home.closeLastOpenedTab();
//		sfdc.orderDecomPose.extractAttributeValueInOrder(dataTable);
//
//		// ***************Extract Order details from business account
//		// details***********************//
//		sfdc.orderDetails.verifyOrderNumberInOrderDetails();
//		sfdc.orderDetails.extractBusinessAccountDetailsForManualQueue();
//		sfdc.home.closeLastOpenedTab();
//
//		// ***************Extract order details from service account details******//
//		sfdc.orderDetails.extractServiceAccountDetailsForManualQueue();
//		sfdc.home.closeLastOpenedTabs(2);
//
//		// ***************Extract order details from site contact details******//
//		sfdc.orderDetails.extractSiteContactDetails();
//		sfdc.home.closeLastOpenedTab();
//
//		// ***************Extract service address details from orchestration plan page
//		// in related tab******//
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		sfdc.orderDetails.extractServiceAdreesFromOrchestrationPlanPage();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//
//		// ***************LOGIN AS Delivery***********************//
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//
//
//		// *************Verify and complete Review order Details task from the manual queue****
//		sfdc.mques.clickOnManualQueuesObject();
//		sf.dataInput.items_InQueue();
//
//		// 1. *****************************Click on delivery specialist queue and select
//		// Review order details task*********//
//		sfdc.mques.selectCableOrderProvisioningQueue();
//		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(0));
//
//		sfdc.cableTaskItems.reviewOrderDetailsComplete_Page(dataTable);
//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		sfdc.orchPlan.verifyOrchestrationPlanForWelcomeLetterAfterCompleteTask();
//		sfdc.home.logout();
//
//		//	2. ********Verify Welcome Letter Email**************
//		//	sf.dataInput.contactEmailAddress = "omtest_contactpankaj@mailinator.com";
//		if(sf.omData.welcomeEmailTaskStatus_CompletedManually != true) {
//			sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "welcomeLetter");
//			sfdc.cableTaskItems.verifyFieldValuesInEmail_ForWelcomeLetter(dataTable);
//		}	
//		driver.quit();
//
//		//3. ***************Click on Create Account In SS task failure From Orchastration Plan*******//
//		intializeChrome(false);
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		//		// sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		//		sfdc.orchPlan.verifyAllTaskItemsOrchPlanForCableOrder();
//
//		//******Change the state of the task and complete the task**********//
//		sfdc.orchPlan.changeCreateAccountInSSTaskFailureState();
//		sfdc.home.closeLastOpenedTab();
//		sfdc.orchPlan.clickOnCreateAccountInSSTaskFailureTask();
//		sfdc.cableTaskItems.enterCANNO_InCreateAccountInSSTaskFailureManualTask(dataTable);
//		sfdc.home.closeTabIfOpen();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		// sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//
//		// ****Verify Update Account CAN And CAN creation auto task is completed*******//
//		sfdc.orchPlan.verifyUpdateAccountCreation_AndCANCreationCompleteTask();
//		sfdc.home.closeTabIfOpenWithRefresh();
//
//		//		// Verify Billing Account CAN No IN Order Details page***************************************************
//		//		//	sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//		//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		//		sfdc.orderDetails.verifyBillingAccountNumber(sf.dataInput.billingCAN);
//		//		sfdc.home.closeTabIfOpen();
//
//		// 4. ***************Verify Create Cable Order Task from the manual queue*******//
//		// This will not be skipped for Office 365 or IBLC product if included in the product
//
//		if(!dataTable.get("Office 365 AddOn").trim().equals("NA") || !dataTable.get("IBLC Product_1").trim().equals("NA") ) {	
//
//			sfdc.mques.clickOnManualQueuesObject();
//			sf.dataInput.items_InQueue();
//			sfdc.mques.selectCableOrderProvisioningQueue();
//			sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(14));
//			sfdc.cableTaskItems.createCableOrderTask_Page(dataTable);
//
//			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {
//				sfdc.cableTaskItems.iblcProvisioiningItems(dataTable);
//			}
//			sfdc.cableTaskItems.v21AccountAndSSInfo(dataTable);
//			sfdc.home.closeTabIfOpenWithRefresh();
//
//		} else {
//
//			// for orders which is not having office 365 or IBLC orders
//			sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//			// sfdc.orders.globalSearchOrdersWithOutAppLauncher(sf.dataInput.orderNumber);
//			sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//			//		sfdc.orchPlan.verifyAllTaskItemsOrchPlanForCableOrder();
//
//			//******Change the state of the work order task and complete the task**********//
//			sfdc.orchPlan.changeWorkOrderInSSTaskFailureState();
//			sfdc.home.closeLastOpenedTab();
//			sfdc.orchPlan.clickOnCreateWorkOrderInSSTaskFailureTask();
//			sfdc.cableTaskItems.superSystemInfoForWorkOrder(dataTable);
//			sfdc.home.closeTabIfOpenWithRefresh();
//		}
//
//		// ***************Verify Create cable order and Appointment Letter task is completed***********************//
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//
//		if(!dataTable.get("Office 365 AddOn").trim().equals("NA") || !dataTable.get("IBLC Product_1").trim().equals("NA") ) {	
//			sfdc.orchPlan.verifyOrchestrationPlanForCableOrderAfterCompleteTask();
//		}
//
//		sfdc.orchPlan.verifyOrchestrationPlanForAppointmentLetterAfterCompleteTask();
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//
//		//5. ********Verify Appointment letter Email **************
//		if(sf.omData.apointmentEmailTaskStatus_CompletedManually != true) {
//			sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "Appointment Letter");
//			sfdc.cableTaskItems.verifyFieldValuesInEmail(dataTable);
//		}
//		driver.quit();
//
//		//6. ***************Click on Account Activation Task From Orchestration Plan*******//
//		//***************LOGIN AS Delivery***********************//
//		intializeChrome(false);
//		sfdc.login.loginToSFDC(InputData.Profile_Delivery);
//		sfdc.home.closeTabIfOpen();
//
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//		//		sfdc.orchPlan.verifyAllTaskItemsOrchPlanForCableOrder();
//
//		sfdc.orchPlan.clickOnAccountActivationTask();
//		sfdc.home.closeLastOpenedTab();
//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//
//		//****Verify PONR task is completed**********//
//		sfdc.orchPlan.verifyPONRTaskIsCompleted();
//		sfdc.home.closeTabIfOpen();
//
//		// 7. ***************Complete the office 365 task from the manual queue*******//
//		if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
//
//			sfdc.mques.clickOnManualQueuesObject();
//			sf.dataInput.items_InQueue();
//
//			// *****************************Click on Office 365 queue and select
//			// // pick the order from the queue *********//
//			sfdc.mques.selectOffice365TaskQueue();
//			sfdc.manQue.pickUpOrderInQueueItems(sf.dataInput.queueItemsName.get(13));
//
//			// ********Verify the field values from the office 365 task *****//
//			sfdc.cableTaskItems.verifyOffice365TaskItems(dataTable);
//			sfdc.home.closeTabIfOpenWithRefresh();
//
//			// ********Verify Order is activated after office 365 task is completed *****//
//			sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//			sfdc.orderDetails.verifyOrderIsActivated();
//
//			// ********Verify Orchestration plan and check office 365 task is completed *****//
//			sfdc.orderRelated.verifyOrchestrationPlanInOrder();
//			sfdc.orchPlan.verifyOrchestrationPlanForOffice365AfterCompleteTask();
//
//			//****complete closure letter task manually********//			
//			sfdc.orchPlan.completeSendClosureLetterTaskManually();
//
//		} else {
//			// ********Verify Closure Letter task is completed *****//
//			sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//			//****complete closure letter task manually if not completed automatically********//	
//			sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.orchPlan.verifyOrchestrationPlanForClosureLetterAfterCompleteTask();
//
//		}
//
//		//8. ********Verify Billing Account CAN Details And Order is activated******//
//		//	sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.orders.globalSearchOrderFromHomeMenu(sf.dataInput.orderNumber);
//		sfdc.orderDetails.verifyOrderIsActivated();		
//		sfdc.orderDetails.verifyBillingAccountNumber(sf.dataInput.billingCAN);
//
//		if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
//
//			//************************Verify Invoice section**********************
//			sfdc.orderDetails.verifyBillingAccountInvoiceLink(sf.dataInput.billingBAN);
//			sfdc.orderDetails.clickOnBillingAssetForOffice365Product(dataTable);
//			sfdc.home.closeLastOpenedTabs(2);
//
//		}
//		sfdc.saccRelated.verifyAssetDetailsForCable(dataTable, "billing");
//		//		sfdc.home.closeTabIfOpenWithRefresh();
//		sfdc.home.closeLastOpenedTabs(2);
//
//		// ***Verify Service Account CAN NO And Asset details***********//
//		sfdc.orderDetails.verifyCANNOInServiceAccountDetails();
//		sfdc.saccRelated.verifyAssetDetailsForCable(dataTable, "Service");
//		sfdc.home.closeTabIfOpen();
//		sfdc.home.logout();
//
//		//9. ********Verify Closure letter task Item**************
//		if(sf.omData.closureEmailTaskStatus_CompletedManually != true) {
//			sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "closureLetter");
//			sfdc.cableTaskItems.verifyFieldValuesInEmail_ForClosureLetter(dataTable);
//		}
		driver.quit();
		// sfdc.home.closeBrowser();
		softassert.assertAll();

	}

	/**
	 * @param dataTable
	 * 
	 *                  Read Input data from datasheet and assign it to input
	 *                  variables
	 */
	public void setInputData(Hashtable<String, String> dataTable) {

		// Global.dataInput.quoteProductName = dataTable.get("Internet Product Name");
		// Global.dataInput.office365ProductName = dataTable.get("Office 365 AddOn");
		// Global.dataInput.tvProductName = dataTable.get("TV Product Name");

		Global.dataInput.address = dataTable.get("Address");
		Global.dataInput.addressStreet = dataTable.get("Address Street");
		Global.dataInput.addressCity = dataTable.get("Address City");
		Global.dataInput.addressState = dataTable.get("Address State");
		Global.dataInput.addressCountry = dataTable.get("Address Country");
		Global.dataInput.createQuoteServiceability = dataTable.get("Quote_Serviceability");
		// Global.dataInput.serviceAddress = dataTable.get("CSA_Service Address");

		test.log(Status.PASS, MarkupHelper.createLabel(dataTable.toString(), ExtentColor.GREEN));
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] getTestData_IBLC_E2E() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.IBLC_E2E_OM_SHEET);
	}
}
