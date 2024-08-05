package scrum.wa.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.base.Base;
import com.framework.base.Constants;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

public class WA_TC_71_OM_Validate_OrchestrationTasks_Test extends Base {

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              1. RDI END TO END FLOW FROM ORDER CREATION TO
	 *                              MANUAL QUEUE TASK COMPLETION, Order Activation,
	 *                              Service Assets
	 * 
	 */ // 1. Many steps are commented out due to new changes been introduced (Like
	// community login Url).
	// Also, executing the regression suite scenario test cases we need to comment
	// out few steps

	@Test(dataProvider = "getE2EFlowTestData")
	public void test_Validate_GBJEndToEndFlow_Promotions(Hashtable<String, String> dataTable)
			throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();	
		//	 *********Login with AE profile******************
		sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		sfdc.home.closeTabIfOpen();
		sf.dataInput.orderNumber = "00085812" ; 
		sfdc.orders.selectOrder();	
		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		sfdc.waccOrchestration.verifyBANTasksInOrchestrationPlan();
		sf.waData.wacc_BaANTask_StatusFailed = true;
		if(sf.waData.wacc_BaANTask_StatusFailed) {
//			*****************************Click on manual queue object***//
			sfdc.mques.clickOnManualQueuesObject();
//			sfdc.mques.selectViewAllOption();
			sf.dataInput.items_InQueue();
			sfdc.mques.selectTechMQueue();
		//	1. ******Click on TechM queue and Pickup the BAN Task*********//			
			sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(17));
			sfdc.waccOrchestration.BANCreation_ManualTasks();
			sfdc.home.closeTabIfOpen();
			sf.seleU.closeRecentlyOpenedWindow();
		}
		sfdc.waccOrchestration.verifyTasksInOrchesnPlanBeforeValidateSubmitOrder();

		// 2. *****************************Click on delivery specialist queue and select
		// Create verify billing account task*****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
	//	sfdc.mques.selectViewAllOption();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(1));
		sfdc.rODComplete.createVerifyBilling_Complete_Page();
		sf.seleU.closeRecentlyOpenedWindow();
		//	sfdc.home.closeLastOpenedTab();

		// 3. *****************************Click on delivery specialist queue and select
		// Review spec sheet task****//
		//		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
	//	sfdc.mques.selectViewAllOption();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(2));
		sfdc.rODComplete.verifyReviewSpecSheet_Complete_Page(dataTable, false);
		sf.seleU.closeRecentlyOpenedWindow();
		//	sfdc.home.closeLastOpenedTab();

		// 5. *****************************Click on service designer queue andpickUpOrderInQueueItemsForCableFlow select
		// complete circuit design task and IP assignment task****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectViewAllOption();
		sfdc.mques.selectServiceDesignerQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(7));
		sfdc.rODComplete.verifyCompleteCircutDesign_IPAssignment_Complete_Page(dataTable);
		sf.seleU.closeRecentlyOpenedWindow();
		//		sfdc.home.closeLastOpenedTab();

		// *****************************Click on delivery specialist queue and select
		// Send order to P&I task****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(4));
		sfdc.rODComplete.verifySendOrderToPI_Complete_Page(dataTable);
		sf.seleU.closeRecentlyOpenedWindow();
		//	sfdc.home.closeLastOpenedTab();

		//6.*****************************Click on delivery specialist queue and
		//	 select monitor Rpats fibre build task****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectDeliverySpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(6));
		sfdc.rODComplete.verifyMonitorRpats_Build_Complete_Page(dataTable);
		sf.seleU.closeRecentlyOpenedWindow();
		//	sfdc.home.closeLastOpenedTab();

		//		//5. ********Verify FOC letter Email **************
		//		sfdc.cableTaskItems.verifyEmailAtMailinatorForOrder(sf.dataInput.contactEmailAddress, "FOC_Letter");
		//		sfdc.rODComplete.verifyFieldValuesInEmail_FOCLetter(dataTable);
		//		driver.quit();
		//
		//		 8.*****************************Click on delivery specialist queue and select
		//		 Schedule TIS for TTC task****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectDeliverySpecialistQueue();
		System.out.println(sf.dataInput.queueItemsName.get(10));
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(10));
		sfdc.rODComplete.verifyScheduleTis_Complete_Page(dataTable);
		sf.seleU.closeRecentlyOpenedWindow();
		//	sfdc.home.closeLastOpenedTab();

		// 10.*****************************Click on Technical Implementation queue and
		// select Complete Access & Service Provisioning task****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectTechImpSpecialistQueue();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(11));
		sfdc.rODComplete.verifyComplete_Accessand_Service_Provisioning_Complete_Page(dataTable);
		sf.seleU.closeRecentlyOpenedWindow();
		//		sfdc.home.closeLastOpenedTab();

		// 11.*****************************Click on Salesforce Prod Support queue and
		// select Billing Activation Failure task****//
		sfdc.home.closeTabIfOpen();
		sfdc.mques.clickOnManualQueuesObject();
		sf.dataInput.items_InQueue();
		sfdc.mques.selectViewAllOption();
		sfdc.mques.selectSalesforceProdSupport();
		sfdc.manQue.pickUpOrderInQueueItemsForCableFlow(sf.dataInput.queueItemsName.get(12));
		sfdc.rODComplete.verifyBillingActivationFailure_Complete_Page(dataTable);
		sf.seleU.closeRecentlyOpenedWindow();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		//	sf.dataInput.orderNumber = "00109475";
		// ***************LOGIN AS AE***********************//
		//	sf.dataInput.tempBusinessAccountName = "BusinessPankaj03";
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		//	sfdc.home.openR4BSalesConsole();
		//		sf.dataInput.orderNumber = "00011165";
		sfdc.home.closeTabIfOpen();  

		// *****verify order status is activated****************************
		//		sf.dataInput.orderNumber = "00008410";
		sfdc.orders.selectOrder();
		//sfdc.orders.globalSearchOrders(sf.dataInput.orderNumber);
		//		sfdc.orders.verifyCommitedInServiceDate();
		//		//		//
		//		//		//		// Verify the orchestration plan for RDI 
		//		//		//		sfdc.orderRelated.verifyOrchestrationPlanInOrder();
		//		//		//		sfdc.orchPlan.verifyOrchestrationPlanForRDIGlobalAfterCompleteTask();	
		//		//		//		sfdc.home.closeLastOpenedTab();
		//		//		//
		  //sfdc.orderDetails.verifyActivatedOrderInOrderDetails();
		//		sfdc.home.closeLastOpenedTab();  
		sfdc.orderDecomPose.extractAndVerifyRDIAttributesInDecomposition(dataTable);

		// 13. ********Verify Billing Account CAN Details******//
		sfdc.orderDetails.verifyBillingAccountNumber(sf.dataInput.billingCAN, "RDI");
		sfdc.saccRelated.verifyAssetDetailsForRDI(dataTable, "billing");
		sfdc.home.closeLastOpenedTabs(2);

		//15. ***Verify Service Account CAN NO And Asset details***********//
		sfdc.orderDetails.verifyCANNOInServiceAccountDetails("RDI");
		sfdc.saccRelated.clickOnDedicatedInternetProductInServiceAsset(dataTable);
		sfdc.saccRelated.verifyRDI_AttributeValue(dataTable);
		//		//	sfdc.orders.cancel_Order();

		// **********Close Tabs and Log out********//
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		//	sfdc.home.closeBrowser();
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
	public Object[][] getE2EFlowTestData() throws IOException {
		return getDataSetsRunMode(Constants.MP_TESTDATA_FILE, Constants.GBJ_RDI_RESUME_SHEET);
	}

}


