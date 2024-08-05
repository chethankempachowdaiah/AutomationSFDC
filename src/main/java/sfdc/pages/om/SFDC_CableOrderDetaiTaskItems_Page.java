package sfdc.pages.om;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_CableOrderDetaiTaskItems_Page extends MyListener{

	/**
	 * @author Pankaj Agarwal, Date 04/April/2021
	 * 
	 *         Cable L2 Flow Task items
	 *
	 */

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public int count, foundCount = 0; 
	List<String> excelProductQuantity;
	String supersystemCANNO, v21BANNO = "";

	public SFDC_CableOrderDetaiTaskItems_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_CableOrderDetaiTaskItems_Page@: ";

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in review order details page fro cable order and
	 *                     select complete
	 */
	public void reviewOrderDetailsComplete_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_field_value_review_order_details@: ";


			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();
			//	1. Verify General Information Field*******************************
			verifyGeneralInformationField();

			// 2. Verify Additional Information Field************************
			verifyFieldPresent("Additional Information", sf.cableTaskItems.additionalInformationText);
			// verifyFieldValue("Billing Account", sf.rODComplete.additionalInformationText,
			// sf.dataInput.billingAccountName);
			verifyFieldValue("Trade Name", sf.rODComplete.tradeNameText, sf.dataInput.businessAccountLegalName);
			verifyFieldValue("Sales Segment", sf.rODComplete.salesSegmentText, sf.dataInput.salesSegment);
			verifyFieldPresent("Order type ", sf.rODComplete.orderTypeText);
			//		verifyFieldPresent("credit Limit Approved", sf.rODComplete.creditLimitApprovedValue);
			//	verifyFieldPresent("Hours Of Operation", sf.rODComplete.hoursOfOperationValue);
			sf.seleU.wait(1000);

			// Verify Site Contact
			verifyFieldValue("Site Contact Name Text", sf.rODComplete.siteContactNameText,
					sf.dataInput.siteContact);
			verifyFieldValue("Site Contact Email", sf.rODComplete.siteContactEmailText,
					sf.dataInput.emailIdValue);
			sf.seleU.wait(1000);		
			verifyFieldValue("Site Contact Phone",sf.rODComplete.siteContactPhoneText,
					sf.dataInput.contactPhoneNumber);
			sf.seleU.wait(1000);
			verifyFieldValue("Site Contact Language Pref", sf.rODComplete.siteContactLanguageText,
					sf.dataInput.langEnglish);
			sf.seleU.wait(1000);

			// Validate Authorized User Signing Authority Information
			sf.seleU.ScrolltoElement(sf.rODComplete.authorizedUserNameText);
			verifyFieldValue("Authorized User Name", sf.rODComplete.authorizedUserNameText,
					sf.dataInput.primaryContact);
			verifyPhoneNoFieldValueByFormat("Authorized User Phone", sf.rODComplete.authorizedContactText, sf.dataInput.phoneNumber);
			verifyFieldValue("Authorized User Email", sf.rODComplete.authorizedUserEmaiText,
					sf.dataInput.contactEmailAddress);
			sf.seleU.wait(1000);
			verifyFieldValue("Authorized User Email", sf.rODComplete.authorizedUserLanguageText,
					sf.dataInput.langEnglish);

			// Validate Sales Agent Information
			try {
				sf.seleU.ScrolltoElement(sf.rODComplete.salesContactNameText);
				verifyFieldValue("Sales Contact Name", sf.rODComplete.salesContactNameText, sf.dataInput.userProfileAe);
				verifyFieldPresent("Sales Contact Email", sf.rODComplete.salesContactEmailText);
			} catch (Exception e) {
				System.out.println("Sales information is not present");
				e.printStackTrace();
			}
			// verifyFieldPresent("Sales Contact Phone",
			// sf.rODComplete.salesContactPhoneValue);

			sf.seleU.scrollByCoOrdinates(1);

			//	1. validate IBLC Information ::

			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {

				if(sf.seleU.isElementDisplayed(sf.rODComplete.iblcProductInformationText)) {
					sf.seleU.ScrolltoElement(sf.rODComplete.iblcProductInformationText);
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("IBLC Product_1"), dataTable.get("IBLC_Lines"));
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Long Distance Plan"), dataTable.get("Long Distance Quantity"));
					compareQuantityProduct(sf.cableTaskItems.phoneInstallationDetails, dataTable.get("Installation_Detail_Product_Name"), dataTable.get("Line_And_Jack_Qty"));
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Equipment_Details_Name"), dataTable.get("Equipment_Details_Quantity"));
				}
			}

			// ****** 2. Validate Internet Product Information********************

			// compare speed which got extracted from order decomposition
			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {

				sf.seleU.ScrolltoElement(sf.rODComplete.productSpeedDetailsText);
				//			verifyFieldValue("Product Speed", sf.rODComplete.productSpeedDetailsText, sf.dataInput.quoteInternetUploadSpeed); //- check speed
				sf.seleU.hardwait(1000);

				if(sf.seleU.isElementPresent(sf.cableTaskItems.internetProductHeaderText)) {
					sf.seleU.ScrolltoElement(sf.cableTaskItems.internetProductHeaderText);
					sf.seleU.scrollUpByCoOrdinates();
					verifyFieldPresent("Internet Product Information", sf.cableTaskItems.internetProductHeaderText);

					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Internet Product Name"), dataTable.get("Internet Product Quantity"));				
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Internet_Installation_Details"), dataTable.get("Internet Product Quantity"));
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Internet Equipment Details"), dataTable.get("Internet Product Quantity"));
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Internet_wifi_Service").trim().split(",")[1], dataTable.get("Internet Product Quantity"));
					verifyFieldValue("TErm Details ", sf.cableTaskItems.termDetailsText, dataTable.get("Contract_Term_InMonths"));
					verifyFieldValue("Promo Code ", sf.cableTaskItems.promoCodeValue, sf.dataInput.promo_Code);

				}		
			}

			//3.  validate TV product information**************************
			if(!dataTable.get("TV Product Name").trim().equals("NA")) {

				if(sf.seleU.isElementPresent(sf.cableTaskItems.tvProductHeaderText)) {
					sf.seleU.ScrolltoElement(sf.cableTaskItems.tvProductHeaderText);

					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("TV Product Name"), dataTable.get("TV Product Name Quantity"));
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("TV_Installation_Details"), dataTable.get("TV Product Name Quantity"));
					compareQuantityProduct(sf.cableTaskItems.productNameValue, dataTable.get("Rogers TV AddOn Product"), dataTable.get("Rogers TV AddOn Quantity"));
				}
			}

			//4.  validate office 365 information***************************

			if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {
				if(sf.seleU.isElementPresent(sf.cableTaskItems.office365ProductHeaderText)) {
					sf.seleU.ScrolltoElement(sf.cableTaskItems.office365ProductHeaderText);
					compareQuantityProduct(sf.cableTaskItems.office365ProductNameValue, dataTable.get("Office 365 AddOn"), dataTable.get("Office 365 AddOn Quantity"));
				}
			}

			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
			}

			// verify success message text
			verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton);
			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false); 


		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in review order details page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the Fail task functionality
	 */
	public void validateFailTaskButton(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Validate_Fail_TaskButton@: "; int k=  0;

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.scrollByCoOrdinates(1);
			//		sf.seleU.ScrolltoElement(sf.rODComplete.vaidateOrderStatusCheckBox);

			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
			}

			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.failTaskButton)) {

				while (k <= 1) {
					sf.seleU.wait(6000);
					sf.seleU.clickElementByJSE(sf.cableTaskItems.failTaskButton);
					reportStatusPass(" Clicked on fail task button", true, false);

					// verify Cancel Fail Text
					verifyFieldPresent("Verify Cancel Fail Text", sf.cableTaskItems.cancelFailureText);

					sf.seleU.enterText(sf.cableTaskItems.enterTextArea, "failed");
					reportStatusPass(" Entered the failure reason as failed", true, false);


					sf.seleU.enterText(sf.cableTaskItems.enterTextArea, Keys.TAB);
					k++;

					if(k == 1) {
						// if Clicked on the failure text button by mistake then click again to remove the text area
						sf.seleU.wait(2000);
						sf.seleU.clickElementByJSE(sf.cableTaskItems.cancelFaiTaskFailureButton);
						reportStatusPass(" Clicked on fail task button again if it was clicked by mistake", true, false);
						sf.seleU.wait(4000);
						if(!sf.seleU.isElementDisplayed(sf.cableTaskItems.enterTextArea)) {
							reportStatusPass(" Enter Text Area disappeared as failed task button was clicked by mistake", true, false);
						}

					}
				}

			} else {
				reportStatusFail(" Fail task button not displayed", true);
			}


			// verify success message text
			verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);

			sf.seleU.wait(10000);
			sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton);
			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false);
			sf.seleU.wait(10000);
		}catch (Throwable e) {
			reportStatusFail(" Verify the Directory listing Information Section", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Entering the CAN NO in the create account in SS failure manual task
	 */
	public void clickOnCANNoRadioInCreateAccountManualTask(Hashtable<String, String> dataTable) throws IOException {

		try { 
			String methodName = "SFDC_click_On_CANNoRadio_InCreate_Account_ManualTask@: ";
			//			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();
			// 1. Verify Create Account In SS task Failure header text
			verifyFieldPresent("Create Account In SS task Failure header tex", 
					sf.cableTaskItems.createAccountInSSFailureHeaderText);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.createCanManuallyClick.get(0));
			reportStatusPass(" Clicked on create can manually task radio button", true, false);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.nextButtonForCreateCanManually);
			reportStatusPass(" Clicked on Next button", true, false);	

			sf.seleU.wait(4000);

			sf.seleU.ScrolltoElement(sf.cableTaskItems.enterSuperSystemCAN);

		} catch (Throwable e) {
			reportStatusFail(" Error in clicking the CAN No radio button", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Entering the CAN NO in the create account in SS failure manual task
	 */
	public void enterCANNO_InCreateAccountInSSTaskFailureManualTask(Hashtable<String, String> dataTable) throws IOException {

		try { 
			String methodName = "SFDC_Verify field value in Office 365 Task@: ";
			//			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();
			// 1. Verify Create Account In SS task Failure header text
			verifyFieldPresent("Create Account In SS task Failure header tex", 
					sf.cableTaskItems.createAccountInSSFailureHeaderText);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.createCanManuallyClick.get(0));
			reportStatusPass(" Clicked on create can manually task radio button", true, false);

			sf.seleU.wait(4000);
			
			sf.seleU.clickOnElement(sf.cableTaskItems.nextButtonForCreateCanManually);
			reportStatusPass(" Clicked on Next button", true, false);	
			//
			sf.seleU.ScrolltoElement(sf.cableTaskItems.enterSuperSystemCAN);
			sf.seleU.clickElementByJSE(sf.cableTaskItems.enterSuperSystemCAN);
			sf.seleU.wait(4000);
			sf.seleU.clearAndEnterText(sf.cableTaskItems.enterSuperSystemCAN, sf.omData.canNOInCreateAccount);
			reportStatusPass(" Enter the CAN NO as " + sf.omData.canNOInCreateAccount, true, true);

			//			// Clicked On the Complete Button after entering CANNO
			//			sf.seleU.wait(8000);
			//			sf.seleU.clickElementByJSE(sf.cableTaskItems.clickOnCompleteButtonAfterCANNo);
			//			reportStatusPass(" Clicked on complete button In Create CAN Manually page", true, false);

			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.ScrolltoElement(sf.rODComplete.vaidateOrderStatusCheckBox);
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
			}

			// verify success message text
			verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);
			sf.seleU.wait(10000);
			sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton_ForRPATasks);
			reportStatusPass(methodName + " Clicked on 'Review Order details Page' Complete Button", true, false); 
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in entering the value for Create CAN No", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                    Verify Order Cancel Meesage for fail tasks
	 */
	public void validateFailTaskButton_ForCanceledOrder(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_Order_Cancel-Mesage_for_fail tasks@:";

			sf.seleU.wait(6000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.ScrolltoElement(sf.rODComplete.vaidateOrderStatusCheckBox);

			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
				verifyFieldValue("Order Canceled Text mesage ", sf.cableTaskItems.orderCancelledText,
						sf.omData.orderCanCelMessageTextInManualTasks);
			}

			if(!sf.seleU.isElementDisplayed(sf.cableTaskItems.failTaskButton)) {	
				reportStatusPass(" Fail Task button is not displayed as expected for canceled order ", true, false);
			} else {
				reportStatusFail(" Fail task button is displayed", true);
			}

		}catch (Throwable e) {
			reportStatusFail(" Verify Order Cancel Meesage for fail tasks", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 * Validate Create Cable Order Task:
	 *                     1. Verify General And Additional Information Section 
	 *                     2.Verify the field details for IBLC, INternet And TV Products 
	 */
	public void createCableOrderTask_Page(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_field_Create_Cable_Order_Task@: "; int k=  0; int j=1; 

			sf.seleU.wait(8000);
			sf.seleU.switchToDefaultContent();
			// Initialize all the variables
			List<WebElement> iblcPhoneLineDetailsName; List<WebElement> iblcPhoneLineName; 
			List<WebElement> iblcPhoneLineDistinctRingFeature; WebElement iblcElementLineName;
			List<WebElement> iblcPhoneLineVoiceFeature; List<WebElement> iblcPhoneLineSpecialBlockingFeature; List<WebElement> iblcPhoneLineAdminFeature;
			WebElement tvProductDetailsName; WebElement internetProductDetailsName; List<WebElement> allPromoDetailsName;

			//	 1. Verify General Information Field
			verifyGeneralInformationField();

			//         2. Verify Billing Account CAN No
			verifyFieldValue("Billing Account CAN NO", sf.cableTaskItems.billingAccountCANNo,
					sf.omData.canNOInCreateAccount);

			// 2. Verify Additional Information Field
			verifyFieldPresent("Additional Information", sf.cableTaskItems.additionalInformationText);
			// verifyFieldValue("Billing Account", sf.rODComplete.additionalInformationText,
			// sf.dataInput.billingAccountName);
			verifyFieldValue("Trade Name", sf.rODComplete.tradeNameText, sf.dataInput.businessAccountLegalName);
			verifyFieldValue("Sales Segment", sf.rODComplete.salesSegmentText, sf.dataInput.salesSegment);
			verifyFieldPresent("Order type ", sf.rODComplete.orderTypeText);
			//		verifyFieldPresent("credit Limit Approved", sf.rODComplete.creditLimitApprovedValue);
			//	verifyFieldPresent("Hours Of Operation", sf.rODComplete.hoursOfOperationValue);
			sf.seleU.wait(1000);

			// Validate Site Contact information
			sf.seleU.ScrolltoElement(sf.rODComplete.siteNameText);
			verifyFieldValue("Site Name", sf.rODComplete.siteNameText, sf.dataInput.serviceAccountName);
			verifyFieldValue("Site Contact Name", sf.rODComplete.siteContactNameText, sf.dataInput.siteContact);
			verifyPhoneNoFieldValueByFormat("Site Contact Phone", sf.rODComplete.siteContactPhoneText, sf.dataInput.contactPhoneNumber);
			sf.seleU.wait(1000);
			verifyFieldValue("Site Contact Email", sf.rODComplete.siteContactEmailText, sf.dataInput.emailIdValue);
			verifyFieldValue("Site Contact Language Preference", sf.rODComplete.siteContactLanguageText,
					sf.dataInput.langEnglish);

			// Validate Authorized User Signing Authority Information
			sf.seleU.ScrolltoElement(sf.rODComplete.authorizedUserNameText);
			verifyFieldValue("Authorized User Name", sf.rODComplete.authorizedUserNameText,
					sf.dataInput.primaryContact);
			verifyPhoneNoFieldValueByFormat("Authorized User Phone", sf.rODComplete.authorizedContactText, sf.dataInput.phoneNumber);
			verifyFieldValue("Authorized User Email", sf.rODComplete.authorizedUserEmaiText,
					sf.dataInput.contactEmailAddress);
			sf.seleU.wait(1000);
			verifyFieldValue("Authorized User Email", sf.rODComplete.authorizedUserLanguageText,
					sf.dataInput.langEnglish);

			// Validate Sales Agent Information
			sf.seleU.ScrolltoElement(sf.rODComplete.salesContactNameText);
			verifyFieldValue("Sales Contact Name", sf.rODComplete.salesContactNameText, sf.dataInput.userProfileAe);
			verifyFieldPresent("Sales Contact Email", sf.rODComplete.salesContactEmailText);
			// verifyFieldPresent("Sales Contact Phone",
			// sf.rODComplete.salesContactPhoneValue);
			sf.seleU.scrollByCoOrdinates(1);


			/* validate Internet Product Information Field Values */
			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {
				sf.seleU.ScrolltoElement(sf.cableTaskItems.internetProductDetailsInformationText);
				sf.seleU.wait(2000);
				sf.seleU.scrollUpByCoOrdinates();
				ArrayList<String> arrProduct = new ArrayList<String>();
				for (int i = 1; i <= sf.cableTaskItems.internetProductDetailsName.size(); i++) {	
					/* This locator will have the splitted data i.e all clm data for a row */
					internetProductDetailsName = driver.findElement(By.xpath("(//span[contains(text(),'Product Details')]/../../../following-sibling::div/slot/div["+i+"]/span[2])"));					
					arrProduct.add(sf.seleU.getTextFromWebElement(internetProductDetailsName).trim());
				}
				// compare the excel internet product info with the UI data
				compareFiedlValues_Internet_TV("Inetrnet Product Details ", arrProduct, dataTable.get("V21_Internet_Product_information").trim());

				// verify the promo code
				if(!dataTable.get("Internet Product Name").trim().equals("NA") && dataTable.get("IBLC Product_1").trim().equals("NA")) {
					verifyFieldPresent("Promo Name for Internet product" , sf.cableTaskItems.promoName.get(0));
					verifyFieldPresent("Promo Details for Internet product" , sf.cableTaskItems.promoDetails.get(0));
				}
			} 
			//			/* validate Tv Product Information Field Values */

			if(!dataTable.get("TV Product Name").trim().equals("NA")) {
				sf.seleU.ScrolltoElement(sf.cableTaskItems.tvProductDetailsInformationText);
				sf.seleU.scrollUpByCoOrdinates();
				ArrayList<String> arrProduct = new ArrayList<String>();
				for (int i = 1; i <= sf.cableTaskItems.tvProductDetailsName.size(); i++) {			
					// This locator will have the splitted data i.e all clm data
					tvProductDetailsName = driver.findElement(By.xpath("//*[text()='TV Product Information']//following::span[contains(text(),'Product Details')]/../../../following-sibling::div/slot/div["+i+"]/span[2]"));
					arrProduct.add(sf.seleU.getTextFromWebElement(tvProductDetailsName).trim());					
				}
				compareFiedlValues_Internet_TV("TV Product Details ", arrProduct, dataTable.get("V21_TV_Product_information").trim());
			}



			/* Verify IBLC Product Information */
			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {

				//				for (int i = 1; i < sf.cableTaskItems.internetAndIBLCPromoDetailsName.size() - 1; i++) {	
				//
				//					// This locator will have the splitted data i.e all clm data for a row
				//					allPromoDetailsName = driver.findElements(By.xpath("(//span[contains(text(),'Promo Details')]/../../../following-sibling::div/slot/div["+i+"]/span)"));
				//
				//					verifyFieldValuesForDistinctRings("Internet And IBLC Promo Details Name ", allPromoDetailsName);
				//					//	compareFieldlValuesWitFourIndexInRow("TV Product Name ", internetProductDetailsName, sf.cableTaskItems.tvProductDetailsName, dataTable.get("Rogers TV AddOn Product").trim().replace(" ", ""));
				//				}


				// validate IBLC Information Field Values ::
				if(sf.seleU.isElementDisplayed(sf.cableTaskItems.iblcProductHeaderText)) {
					sf.seleU.ScrolltoElement(sf.cableTaskItems.iblcProductHeaderText);

					// verify equipment and installation details field values are populating
					for (int i = 0; i < sf.cableTaskItems.iblcEquip_InstallDetailsName.size(); i=i+2) {				 
						verifyFieldPresentWithCode("Equipment And Installtion Details For IBLC ", sf.cableTaskItems.iblcEquip_InstallDetailsName.get(i), 
								sf.cableTaskItems.iblcEquip_InstallDetailsName.get(i+1));	
					}

					//	 expand all the task items if it's not expanded
					for (int i = 0; i < sf.cableTaskItems.iblcPhoneLineExpandArrow.size(); i++) {	
						if(sf.seleU.getElementAttribute(sf.cableTaskItems.iblcPhoneLineExpandArrow.get(i), "aria-expanded").trim().contains("false")) {
							sf.seleU.clickElementByJSE(sf.cableTaskItems.iblcPhoneLineExpandArrow.get(i));
							reportStatusPass("Succesfully expanded the task items which were closed ", false, false);
						}
					}

					//	verify all Phone Line values and product details are populating
					for (int i = 1; i <= 4; i++) {	

						iblcElementLineName = driver.findElement(By.xpath("//span[text() = 'Phone Line "+i+"']"));
						iblcPhoneLineDetailsName = driver.findElements(By.xpath("(//span[contains(text(),'Phone Line Details')])["+i+"]/../../../following-sibling::div/slot/div/span"));
						iblcPhoneLineName = driver.findElements(By.xpath("(//span[text() = 'Phone Line "+i+"'])[1]/../../../following-sibling::div/slot/div/span"));
						iblcPhoneLineDistinctRingFeature = driver.findElements(By.xpath("(//span[contains(text(),'Distinctive Ring Details')])["+i+"]/../../parent::div/following-sibling::div/slot/div/lightning-accordion-section/section/div/following-sibling::div/slot/div/span"));
						sf.seleU.ScrolltoElement(iblcElementLineName);


						verifyFieldPresentWithCode("Equipment And Installtion Details For IBLC ", sf.cableTaskItems.iblcPhoneLine.get(i-1), sf.cableTaskItems.iblcPhoneLine.get(i-1));	
						verifyFieldPresent("Phone Line Name is ", iblcElementLineName);
						verifyFieldAllValuesPresent("IBLC Phone Line Product Name ", iblcPhoneLineName);
						verifyFieldValuesForDistinctRings("IBLC Phone Line Details Product Name ", iblcPhoneLineDetailsName);

						// Verify Voice Feature
						if(!dataTable.get("Voice Features_ForBasic").equals("NA")) {
							iblcPhoneLineVoiceFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Voice Features')])["+i+"]/../../../following-sibling::div/slot/div/span"));
							verifyFieldValuesForDistinctRings("IBLC Phone Line Voice Feature ", iblcPhoneLineVoiceFeature);
							compareFiedlValues("IBLC Phone Line Voice Feature ", iblcPhoneLineVoiceFeature, dataTable.get("Voice Features_ForBasic").trim().replace(" ", ""));
						}

						// verify special blocking feature
						if(!dataTable.get("Special Blocking Feature_ForBasic").equals("NA")) {
							iblcPhoneLineSpecialBlockingFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Special and Blocking Features')])["+i+"]/../../../following-sibling::div/slot/div/span"));
							verifyFieldValuesForDistinctRings("IBLC Phone Line Special blocking feature", iblcPhoneLineSpecialBlockingFeature);
							compareFiedlValues("IBLC Phone Line Special blocking feature ", iblcPhoneLineSpecialBlockingFeature, dataTable.get("Special Blocking Feature_ForBasic").trim().replace(" ", ""));
						}

						// verify Admin feature
						if(!dataTable.get("Admin Feature ForBasic").equals("NA")) {
							iblcPhoneLineAdminFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Admin Features')])["+i+"]/../../../following-sibling::div/slot/div/span"));
							verifyFieldValuesForDistinctRings("IBLC Phone Line Adminfeature ", iblcPhoneLineAdminFeature);
							compareFiedlValues("IBLC Phone Line Admin Feature ", iblcPhoneLineAdminFeature, dataTable.get("Admin Feature ForBasic").trim().replace(" ", ""));
						} 
						System.out.println(i);
						verifyFieldValuesForDistinctRings("IBLC Phone Line Details Distintive Rings ", iblcPhoneLineDistinctRingFeature);
					}

					// verify all Phone Line values and product details are populating
					//					for (int i = 5; i <= 8; i++) {	
					//
					//						iblcElementLineName = driver.findElement(By.xpath("//span[text() = 'Phone Line "+i+"']"));
					//						iblcPhoneLineDetailsName = driver.findElements(By.xpath("(//span[contains(text(),'Phone Line Details')])["+i+"]/../../../following-sibling::div/slot/div/span"));
					//						iblcPhoneLineName = driver.findElements(By.xpath("(//span[text() = 'Phone Line "+i+"'])[1]/../../../following-sibling::div/slot/div/span"));
					//						iblcPhoneLineDistinctRingFeature = driver.findElements(By.xpath("(//span[contains(text(),'Distinctive Ring Details')])["+i+"]/../../parent::div/following-sibling::div/slot/div/lightning-accordion-section/section/div/following-sibling::div/slot/div/span"));
					//
					//						sf.seleU.ScrolltoElement(iblcElementLineName);
					//
					//						verifyFieldPresent("Phone Line Name is ", iblcElementLineName);
					//						verifyFieldAllValuesPresent("IBLC Phone Line Product Name ", iblcPhoneLineName);
					//						verifyFieldValuesForDistinctRings("IBLC Standard Phone Line Details Product Name ", iblcPhoneLineDetailsName);
					//
					//						// Verify Voice Feature
					//						if(!dataTable.get("Voice Features_ForStandard").equals("NA")) {
					//
					//							iblcPhoneLineVoiceFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Voice Features')])["+i+"]/../../../following-sibling::div/slot/div/span"));
					//							verifyFieldValuesForDistinctRings("IBLC Standard Phone Line Voice Feature ", iblcPhoneLineVoiceFeature);
					//							compareFiedlValues("IBLC Standard Phone Line Voice Feature ", iblcPhoneLineVoiceFeature, dataTable.get("Voice Features_ForStandard").trim().replace(" ", ""));
					//						}
					//
					//						if(!dataTable.get("Special Blocking Feature_ForStandard").equals("NA")) {
					//
					//							iblcPhoneLineSpecialBlockingFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Special and Blocking Features')])["+i+"]/../../../following-sibling::div/slot/div/span"));
					//							verifyFieldValuesForDistinctRings("IBLC Standard Phone Line Special blocking feature", iblcPhoneLineSpecialBlockingFeature);
					//							compareFiedlValues("IBLC Standard Phone Line special blocking Feature ", iblcPhoneLineSpecialBlockingFeature, dataTable.get("Special Blocking Feature_ForStandard").trim().replace(" ", ""));
					//						}
					//
					//						if(!dataTable.get("Admin Feature For Standard").equals("NA") && dataTable.get("Admin Feature ForBasic").equals("NA")) {
					//
					//							// As the index for admin feature will start again from 0 if admin feature is present in Basic
					//							iblcPhoneLineAdminFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Admin Features')])["+j+"]/../../../following-sibling::div/slot/div/span"));
					//							verifyFieldValuesForDistinctRings("IBLC Standard Phone Line Admin feature", iblcPhoneLineAdminFeature);
					//							compareFiedlValues("IBLC Standard Phone Line Admin Feature ", iblcPhoneLineAdminFeature, dataTable.get("Admin Feature For Standard").trim().replace(" ", ""));
					//
					//							j++;
					//
					//						} else if(!dataTable.get("Admin Feature For Standard").equals("NA") && !dataTable.get("Admin Feature ForBasic").equals("NA")) {
					//
					//							iblcPhoneLineAdminFeature = driver.findElements(By.xpath("(//span[contains(text(), 'Admin Features')])["+i+"]/../../../following-sibling::div/slot/div/span"));
					//							verifyFieldValuesForDistinctRings("IBLC Standard Phone Line Admin feature", iblcPhoneLineAdminFeature);
					//							compareFiedlValues("IBLC Standard Phone Line Admin Feature ", iblcPhoneLineAdminFeature, dataTable.get("Admin Feature For Standard").trim().replace(" ", ""));
					//
					//						}
					//						// verify distinctive rings
					//						verifyFieldValuesForDistinctRings("IBLC Standard Phone Line Details Distintive Rings ", iblcPhoneLineDistinctRingFeature);
					//					} 
				}


				// for monthly promo code will not appear
				if(!dataTable.get("Contract_Term").trim().equals("Monthly")) {
					verifyFieldPresent("Promo Code Value for IBLC Product ", sf.cableTaskItems.iblcPromoCodeValue.get(0));
					verifyFieldPresent("Promo Code NAme for IBLC Product ", sf.cableTaskItems.iblcPromoCodeValue.get(1));

				}
			}


		} catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Create Cable Order TAsks page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in IBLC Provisioning Section
	 */
	public void iblcProvisioiningItems(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_Field_IBLC_Provisioning@: "; int count =  0;

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.scrollByCoOrdinates(1);
			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.iblcEmergencyAndOSPInvoiceAttach.get(0))) {
				verifyFieldPresent("OSP Invoices Attchment ", sf.cableTaskItems.iblcEmergencyAndOSPInvoiceAttach.get(0));
			}

			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.iblcEmergencyAndOSPInvoiceAttach.get(0))) {
				verifyFieldPresent("POSP Onvoice Attachment ", sf.cableTaskItems.iblcEmergencyAndOSPInvoiceAttach.get(1));
			}
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.iblcProvisioningHearderText);
			verifyFieldPresent("IBLC Provisioning Header ", sf.cableTaskItems.iblcProvisioningHearderText);
			verifyFieldPresent("Port Request Information LSR Table ", sf.cableTaskItems.portRequestInformationText);

			// validate IBLC Information Field Values ::
			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.portRequestInformationText)) {
				sf.seleU.ScrolltoElement(sf.cableTaskItems.portRequestInformationText);

				List<String> ar = new ArrayList<String>();
				// verify all Phone Line values and product details are populating
				for (int i = 1; i <= sf.cableTaskItems.portRequestInformationListRow.size(); i++) {	
					count++;
					for(int j =1; j <=3 ; j++) {
						WebElement portRequestList = driver.findElement(By.xpath("//div[@title='Phone Number']/parent::th/parent::tr/parent::thead/following-sibling::tbody/tr["+i+"]/td["+j+"]/div"));
						ar.add(sf.seleU.getTextFromWebElement(portRequestList));	
					}
					reportStatusPass("Port Request Information Table Data is Displayed" + " is displayed as " + 
							AdditionalUtilities.getAsString(ar),true, false);
					ar.clear();
				}		


				System.out.println(Integer.parseInt(dataTable.get("Port Request Information LSR Count").trim()));

				if(Integer.parseInt(dataTable.get("Port Request Information LSR Count").trim()) == count) {
					reportStatusPass("Total No of Phone Number request for port is matched with IBLC specsheet",true, true);
				}  else {
					reportStatusFail(" Total No of Phone Number request for port is not matched with IBLC specsheet", true);
				}
			}

			// Validate directory list table 
			directoryListInfo(dataTable);


		} catch (Throwable e) {
			reportStatusFail(" Verify the field details in IBLC Provisioning Section", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the Directory listing Information Section
	 */
	public void directoryListInfo(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_Directory_Listing@: "; int k=  0;

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.ScrolltoElement(sf.cableTaskItems.directoryListingInformationText);
			verifyFieldPresent("Directory List Information Header ", sf.cableTaskItems.directoryListingInformationText);

			// validate Directory List Information Field Values ::

			verifyFieldValue("Service Account Name", sf.cableTaskItems.directoryListingInformationList.get(0), sf.dataInput.serviceAccountName);

			verifyFieldValue("List Number", sf.cableTaskItems.directoryListingInformationList.get(1), 
					"123456");

			verifyFieldValue("Sublist Number", sf.cableTaskItems.directoryListingInformationList.get(2), 
					"123456");

		}catch (Throwable e) {
			reportStatusFail(" Verify the Directory listing Information Section", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify and Enter all the field information for V21 Account Source Block
	 */
	public void v21AccountAndSSInfo(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_V21_Account_Source_Block@: "; int k=  0;

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();

			sf.seleU.scrollUpByCoOrdinates();
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.v21AccountSSOHeaderText);
			verifyFieldPresent("V21 Account and SS Order Information header text ", sf.cableTaskItems.v21AccountSSOHeaderText);

			if(!dataTable.get("Region").equals("AL")) {
				
				//1. Verify v21 ban and enter the no
				verifyFieldPresent("V21 Consolidated BAN Information header text ", sf.cableTaskItems.v21ConsolidatedBanHeaderText);

				sf.seleU.clearAndEnterText(sf.cableTaskItems.v21BanNumberEnter, sf.omData.v21BanNumberEnter);
				reportStatusPass(" Entered V21 Ban No Enter is " + sf.omData.v21BanNumberEnter, true, false);

				
				//3. Enter Supersystem Install Order Information:
				verifyFieldPresent("Supersystem Install Order Information header text ", sf.cableTaskItems.supersystemInstallOrderInfoText);
				// Vlidate min length 7 and max length as 7
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), "1234");
				reportStatusPass(" Entered supersystem INS OrderNo Enter is " + "1234", true, false);

				sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), Keys.TAB);

				if(sf.seleU.isElementDisplayed(sf.cableTaskItems.supersystemINSOrderNoMinLengtText)) {
					reportStatusPass(" Min Length should 7digit " + sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemINSOrderNoMinLengtText), true, false);
					// Entered the correct supersystem order
					sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), "1234567");
					reportStatusPass(" Entered the correct supersystem Install OrderNo Enter is " + "1234567", true, false);
				}

				// Enter the supersystem install appointemt date
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), sf.omData.enterPostAppointmentDate);
				reportStatusPass(" Entered the supersystem install appoitment date as " + sf.omData.enterPostAppointmentDate, true, false);
			    sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), Keys.TAB);

				// Enter the supersystem install appointemt time
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), sf.omData.pre_SiteInspection_In_Time);
				reportStatusPass(" Entered the supersystem install appointment start Time is " + sf.omData.pre_SiteInspection_In_Time, true, false);

				sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), Keys.TAB);

				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), sf.omData.pre_SiteInspection_Out_Time);
				reportStatusPass(" Entered the supersystem install appointment end Time is " + sf.omData.pre_SiteInspection_Out_Time, true, false);

				sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), Keys.TAB);

			}
			
			if(!dataTable.get("Region").equals("ON"))
			{
			//1. Verify v21 ban and enter the no
			verifyFieldPresent("V21 Consolidated BAN Information header text ", sf.cableTaskItems.v21ConsolidatedBanHeaderText);

			sf.seleU.clearAndEnterText(sf.cableTaskItems.v21BanNumberEnter, sf.omData.v21BanNumberEnter);
			reportStatusPass(" Entered V21 Ban No Enter is " + sf.omData.v21BanNumberEnter, true, false);

			//2. Verify supersystem Pre-Inspection and order information
			verifyFieldPresent("Supersystem Pre Information header text ", sf.cableTaskItems.supersystemPreInspectionOrderHeaderText);

			// Vlidate min length 7 and max length as 7
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0), "1234");
			reportStatusPass(" Entered supersystem Pre-inspection Enter is " + "1234", true, false);

			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0), Keys.TAB);

			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.supersystemINSOrderNoMinLengtText)) {
				reportStatusPass(" Min Length should 7" + sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemINSOrderNoMinLengtText), true, false);
				// Entered the correct supersystem order
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0), "1234567");
				reportStatusPass(" Entered the correct supersystem Pre-inspection OrderNo Enter is " + "1234567", true, false);
			}

			// enter the appointment date
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(0), sf.omData.enterAppointmentDate);
			reportStatusPass(" Entered the appoitment date as " + sf.omData.enterAppointmentDate, true, false);
			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(0), Keys.TAB);

			// Enter the appointment start and end time
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(0), sf.omData.pre_SiteInspection_In_Time);
			reportStatusPass(" Entered the correct supersystem Pre-inspection Appointemnt Start Time is " + sf.omData.pre_SiteInspection_In_Time, true, false);

			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(1), sf.omData.pre_SiteInspection_Out_Time);
			reportStatusPass(" Entered the correct supersystem Pre-inspection Appointmnt End Time is " + sf.omData.pre_SiteInspection_Out_Time, true, false);

			//3. Enter Supersystem Install Order Information:
			verifyFieldPresent("Supersystem Install Order Information header text ", sf.cableTaskItems.supersystemPreInspectionOrderHeaderText);
			// Vlidate min length 7 and max length as 7
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), "1234");
			reportStatusPass(" Entered supersystem INS OrderNo Enter is " + "1234", true, false);

			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), Keys.TAB);

			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.supersystemINSOrderNoMinLengtText)) {
				reportStatusPass(" Min Length should 7digit " + sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemINSOrderNoMinLengtText), true, false);
				// Entered the correct supersystem order
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), "1234567");
				reportStatusPass(" Entered the correct supersystem Install OrderNo Enter is " + "1234567", true, false);
			}

			// Enter the supersystem install appointemt date
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), sf.omData.enterPostAppointmentDate);
			reportStatusPass(" Entered the supersystem install appoitment date as " + sf.omData.enterPostAppointmentDate, true, false);
		    sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), Keys.TAB);

			// Enter the supersystem install appointemt time
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), sf.omData.pre_SiteInspection_In_Time);
			reportStatusPass(" Entered the supersystem install appointment start Time is " + sf.omData.pre_SiteInspection_In_Time, true, false);

			sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), Keys.TAB);

			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), sf.omData.pre_SiteInspection_Out_Time);
			reportStatusPass(" Entered the supersystem install appointment end Time is " + sf.omData.pre_SiteInspection_Out_Time, true, false);

			sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), Keys.TAB);
			}

			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
			}

			// verify success message text
			verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);

			sf.seleU.wait(5000);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.createCableCompleteButton);
			reportStatusPass(methodName + " Clicked on 'create cable order Page' Complete Button", true, false);

			// verify success message text
			//	verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);

			sf.seleU.wait(10000);

		}catch (Throwable e) {
			reportStatusFail("  Verify and Enter all the field information for V21 Account Source Block", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Enter supersystem info for work order
	 */
	public void superSystemInfoForWorkOrder(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify_SuperSystem_Info_For_WorkOrder@: "; int k=  0;

			sf.seleU.wait(20000);
			sf.seleU.switchToDefaultContent();
			// Initialize all the variables		
			WebElement tvProductDetailsName; WebElement internetProductDetailsName;

			// 1. Verify Create Account In SS task Failure header text
			verifyFieldPresent("Create Work order In SS task Failure header tex", 
					sf.cableTaskItems.createWorkOrderInSSFailureHeaderText);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.createWorkOrderManuallyClick.get(0));
			reportStatusPass(" Clicked on create work Order manually task radio button", true, false);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.nextButtonForCreateCanManually);
			//reportStatusPass(" Clicked on Next button", true, false);

			//			//	 1. Verify General Information Field
			//			verifyGeneralInformationField();
			//
			//			//         2. Verify Billing Account CAN No
			//			verifyFieldValue("Billing Account CAN NO", sf.cableTaskItems.billingAccountCANNo,
			//					sf.omData.canNOInCreateAccount);
			//
			//			// 2. Verify Additional Information Field
			//			verifyFieldPresent("Additional Information", sf.cableTaskItems.additionalInformationText);
			//			// verifyFieldValue("Billing Account", sf.rODComplete.additionalInformationText,
			//			// sf.dataInput.billingAccountName);
			//			verifyFieldValue("Trade Name", sf.rODComplete.tradeNameText, sf.dataInput.businessAccountLegalName);
			//			verifyFieldValue("Sales Segment", sf.rODComplete.salesSegmentText, sf.dataInput.salesSegment);
			//			verifyFieldPresent("Order type ", sf.rODComplete.orderTypeText);
			//			//		verifyFieldPresent("credit Limit Approved", sf.rODComplete.creditLimitApprovedValue);
			//			//	verifyFieldPresent("Hours Of Operation", sf.rODComplete.hoursOfOperationValue);
			//			sf.seleU.wait(1000);
			//
			//			// Validate Site Contact information
			//			sf.seleU.ScrolltoElement(sf.rODComplete.siteNameText);
			//			verifyFieldValue("Site Name", sf.rODComplete.siteNameText, sf.dataInput.serviceAccountName);
			//			verifyFieldValue("Site Contact Name", sf.rODComplete.siteContactNameText, sf.dataInput.siteContact);
			//			verifyPhoneNoFieldValueByFormat("Site Contact Phone", sf.rODComplete.siteContactPhoneText, sf.dataInput.contactPhoneNumber);
			//			sf.seleU.wait(1000);
			//			verifyFieldValue("Site Contact Email", sf.rODComplete.siteContactEmailText, sf.dataInput.emailIdValue);
			//			verifyFieldValue("Site Contact Language Preference", sf.rODComplete.siteContactLanguageText,
			//					sf.dataInput.langEnglish);
			//
			//			// Validate Authorized User Signing Authority Information
			//			sf.seleU.ScrolltoElement(sf.rODComplete.authorizedUserNameText);
			//			verifyFieldValue("Authorized User Name", sf.rODComplete.authorizedUserNameText,
			//					sf.dataInput.primaryContact);
			//			verifyPhoneNoFieldValueByFormat("Authorized User Phone", sf.rODComplete.authorizedContactText, sf.dataInput.phoneNumber);
			//			verifyFieldValue("Authorized User Email", sf.rODComplete.authorizedUserEmaiText,
			//					sf.dataInput.contactEmailAddress);
			//			sf.seleU.wait(1000);
			//			verifyFieldValue("Authorized User Email", sf.rODComplete.authorizedUserLanguageText,
			//					sf.dataInput.langEnglish);
			//
			//			// Validate Sales Agent Information
			//			sf.seleU.ScrolltoElement(sf.rODComplete.salesContactNameText);
			//			verifyFieldValue("Sales Contact Name", sf.rODComplete.salesContactNameText, sf.dataInput.userProfileAe);
			//			verifyFieldPresent("Sales Contact Email", sf.rODComplete.salesContactEmailText);
			//			// verifyFieldPresent("Sales Contact Phone",
			//			// sf.rODComplete.salesContactPhoneValue);
			//			sf.seleU.scrollByCoOrdinates(1);

			/* validate Internet Product Information Field Values */
			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {
				sf.seleU.ScrolltoElement(sf.cableTaskItems.internetProductDetailsInformationText);
				sf.seleU.wait(2000);
				sf.seleU.scrollUpByCoOrdinates();
				ArrayList<String> arrProduct = new ArrayList<String>();
				for (int i = 1; i <= sf.cableTaskItems.internetProductDetailsName.size(); i++) {	
					/* This locator will have the splitted data i.e all clm data for a row */
					internetProductDetailsName = driver.findElement(By.xpath("(//span[contains(text(),'Product Details')]/../../../following-sibling::div/slot/div["+i+"]/span[2])"));					
					arrProduct.add(sf.seleU.getTextFromWebElement(internetProductDetailsName).trim());
				}
				// compare the excel internet product info with the UI data
				compareFiedlValues_Internet_TV("Inetrnet Product Details ", arrProduct, dataTable.get("V21_Internet_Product_information").trim());

				// verify the promo code
				if(!dataTable.get("Internet Product Name").trim().equals("NA") && dataTable.get("IBLC Product_1").trim().equals("NA")) {
					verifyFieldPresent("Promo Name for Internet product" , sf.cableTaskItems.promoName.get(0));
					verifyFieldPresent("Promo Details for Internet product" , sf.cableTaskItems.promoDetails.get(0));
				}
			} 
			//			/* validate Tv Product Information Field Values */

			if(!dataTable.get("TV Product Name").trim().equals("NA")) {
				sf.seleU.ScrolltoElement(sf.cableTaskItems.tvProductDetailsInformationText);
				sf.seleU.scrollUpByCoOrdinates();
				ArrayList<String> arrProduct = new ArrayList<String>();
				for (int i = 1; i <= sf.cableTaskItems.tvProductDetailsName.size(); i++) {			
					// This locator will have the splitted data i.e all clm data
					tvProductDetailsName = driver.findElement(By.xpath("//*[text()='TV Product Information']//following::span[contains(text(),'Product Details')]/../../../following-sibling::div/slot/div["+i+"]/span[2]"));
					arrProduct.add(sf.seleU.getTextFromWebElement(tvProductDetailsName).trim());					
				}
				compareFiedlValues_Internet_TV("TV Product Details ", arrProduct, dataTable.get("V21_TV_Product_information").trim());
			}

      
			if(!dataTable.get("Region").trim().equals("AL")) {
				
				//3. Enter Supersystem Install Order Information:******************

				verifyFieldPresent("Supersystem Install Order Information header text ", sf.cableTaskItems.supersystemInstallOrderInfoText);
				// Vlidate min length 7 and max length as 7
				sf.seleU.clearText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1));
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), "1234");
				reportStatusPass(" Entered supersystem INS OrderNo Enter is " + "1234", true, false);

				sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), Keys.TAB);

				if(sf.seleU.isElementDisplayed(sf.cableTaskItems.supersystemINSOrderNoMinLengtText)) {
					reportStatusPass(" Min Length should 7digit " + sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemINSOrderNoMinLengtText), true, false);
					// Entered the correct supersystem order
					sf.seleU.clearText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1));
					sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), sf.omData.installation_WorkOrderNo);
					reportStatusPass(" Entered the correct supersystem Install OrderNo Enter is " + sf.omData.installation_WorkOrderNo, true, false);
				}

				// Enter the supersystem install appointemt date
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), sf.omData.enterPostAppointmentDate);
				reportStatusPass(" Entered the supersystem install appoitment date as " + sf.omData.enterPostAppointmentDate, true, false);
				sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), Keys.TAB);

				// Enter the supersystem install appointemt time
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), sf.omData.pre_SiteInspection_In_Time);
				reportStatusPass(" Entered the supersystem install appointment start Time is " + sf.omData.pre_SiteInspection_In_Time, true, false);
				sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), Keys.TAB);

				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), sf.omData.pre_SiteInspection_Out_Time);
				reportStatusPass(" Entered the supersystem install appointment end Time is " + sf.omData.pre_SiteInspection_Out_Time, true, false);
				sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), Keys.TAB);
			}
			
			if(!dataTable.get("Region").trim().equals("ON"))
				
			{
			//2. Verify supersystem Pre-Inspection and order information ***************
			verifyFieldPresent("Supersystem Pre Information header text ", sf.cableTaskItems.supersystemPreInspectionOrderHeaderText);

			// Vlidate min length 7 and max length as 7 for work order no
			sf.seleU.clearText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0));
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0), "1234");
			reportStatusPass(" Entered supersystem Pre-inspection Enter is " + "1234", true, false);

			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0), Keys.TAB);

			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.supersystemINSOrderNoMinLengtText)) {
				reportStatusPass(" Min Length should 7" + sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemINSOrderNoMinLengtText), true, false);
				// Entered the correct supersystem order
				sf.seleU.clearText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0));
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(0), sf.omData.preSiteInspection_WorkOrderNo);
				reportStatusPass(" Entered the correct supersystem Pre-inspection OrderNo Enter is " + sf.omData.preSiteInspection_WorkOrderNo, true, false);
			}

			// enter the appointment date
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(0), sf.omData.enterAppointmentDate);
			reportStatusPass(" Entered the appoitment date as " + sf.omData.enterAppointmentDate, true, false);
			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(0), Keys.TAB);

			// Enter the appointment start and end time
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(0), sf.omData.pre_SiteInspection_In_Time);
			reportStatusPass(" Entered the correct supersystem Pre-inspection Appointemnt Start Time is " + sf.omData.pre_SiteInspection_In_Time, true, false);

			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(1), sf.omData.pre_SiteInspection_Out_Time);
			reportStatusPass(" Entered the correct supersystem Pre-inspection Appointmnt End Time is " + sf.omData.pre_SiteInspection_Out_Time, true, false);

			//3. Enter Supersystem Install Order Information:******************

			verifyFieldPresent("Supersystem Install Order Information header text ", sf.cableTaskItems.supersystemPreInspectionOrderHeaderText);
			// Vlidate min length 7 and max length as 7
			sf.seleU.clearText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1));
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), "1234");
			reportStatusPass(" Entered supersystem INS OrderNo Enter is " + "1234", true, false);

			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), Keys.TAB);

			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.supersystemINSOrderNoMinLengtText)) {
				reportStatusPass(" Min Length should 7digit " + sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemINSOrderNoMinLengtText), true, false);
				// Entered the correct supersystem order
				sf.seleU.clearText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1));
				sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderNoEnter.get(1), sf.omData.installation_WorkOrderNo);
				reportStatusPass(" Entered the correct supersystem Install OrderNo Enter is " + sf.omData.installation_WorkOrderNo, true, false);
			}

			// Enter the supersystem install appointemt date
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), sf.omData.enterPostAppointmentDate);
			reportStatusPass(" Entered the supersystem install appoitment date as " + sf.omData.enterPostAppointmentDate, true, false);
			sf.seleU.enterText(sf.cableTaskItems.supersystemINSOrderAppointmentDateEnter.get(1), Keys.TAB);

			// Enter the supersystem install appointemt time
			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), sf.omData.pre_SiteInspection_In_Time);
			reportStatusPass(" Entered the supersystem install appointment start Time is " + sf.omData.pre_SiteInspection_In_Time, true, false);
			sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(2), Keys.TAB);

			sf.seleU.clearAndEnterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), sf.omData.pre_SiteInspection_Out_Time);
			reportStatusPass(" Entered the supersystem install appointment end Time is " + sf.omData.pre_SiteInspection_Out_Time, true, false);
			sf.seleU.enterText(sf.cableTaskItems.supersystemINSEnterOderTime.get(3), Keys.TAB);
			}
			// Validation for with out status check box click 
			if(sf.seleU.isElementDisplayed(sf.rODComplete.vaidateOrderStatusCheckBox)) {
				sf.seleU.clickElementByJSE(sf.rODComplete.vaidateOrderStatusCheckBox);
				reportStatusPass(methodName + " Clicked on 'Validate Order Status Check Box", true, false);
				//verify success message text
				verifyFieldPresent("Order is valid. Proceed with completing the text", sf.cableTaskItems.orderCompletedMessageText);
			}

			sf.seleU.wait(5000);
			sf.seleU.ScrolltoElement(sf.cableTaskItems.completeButton_ForRPATasks);
			sf.seleU.wait(10000);
			sf.seleU.hardwait(5000);

			sf.seleU.clickElementByJSE(sf.cableTaskItems.completeButton_ForRPATasks);
			reportStatusPass(methodName + " Clicked on 'create work order Page' Complete Button", true, false);



			sf.seleU.wait(10000);

		}catch (Throwable e) {
			reportStatusFail("entering the Super System Info for create work order is failed ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify general information fields
	 */
	public void verifyGeneralInformationField() throws IOException {

		try {
			String methodName = "SFDC_Verify field value in general information@: ";
			sf.seleU.wait(2000);
			// 1. Verify General Information Field

			verifyFieldValue("Order Number", sf.rODComplete.orderDetailsText, sf.dataInput.orderNumber);
			verifyFieldValue("Order State", sf.rODComplete.orderStatusText, sf.dataInput.orderStatusInProgress);
			sf.seleU.wait(1000);
			verifyFieldValue("Order BusinessName", sf.rODComplete.businessNameText,
					sf.dataInput.tempBusinessAccountName);

			verifyFieldValueByFomrat("Order ServiceAddress", sf.rODComplete.serviceAddressText, sf.dataInput.serviceAddress);

			verifyFieldValue("Order Delivery Specialist",
					sf.rODComplete.deliverySpecialistText, sf.dataInput.userProfileDelivery);

			//			verifyFieldValue("Order Account Executive", sf.rODComplete.accountExecutiveText,
			//					sf.dataInput.userProfileAe);

			//			verifyFieldValueWithFormat("Address Contract Type ", sf.rODComplete.addressContractTypeText,
			//					sf.dataInput.accessTypeNet);

			sf.seleU.wait(1000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying general Informa"
					+ "tion fields", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws InterruptedException Opens a new tab, and Navigate to
	 *                              "Mailinator.com" URL click on search box enter
	 *                              the contact email id click on go button lands on
	 *                              email inbox page and verify it click on the
	 *                              first email verifies the subject value with the
	 *                              order No.
	 * 
	 */

	public void verifyEmailAtMailinatorForOrder(String emailID, String letterChoose) throws IOException {
		try {
			WebElement emailOrderNumber; WebElement orderNumberHeader;
			//	emailID = "contactcommunity@mailinator.com";
			//	emailID = "pankajagarwal03@mailinator.com";
			String contactEmailId = emailID.split("@")[0];

			sf.seleU.openNewTab();
			sf.seleU.hardwait(2000);
			sf.seleU.switchWindow(2);
			sf.seleU.hardwait(2000);

			// open mailinator.com
			sf.seleU.openURL(sf.commData.mailinatorURL);
			reportStatusPass(methodName + "User has opened mailinator in another tab", true, true);
			sf.seleU.clickOnElement(sf.mailinator.searchFieldMailinator);
			sf.seleU.wait(4000);
			//	contactEmailId = "omtest_contactpankaj";
			sf.seleU.enterTextByJSE(sf.mailinator.searchFieldMailinator, contactEmailId);
			sf.seleU.hardwait(6000);
			sf.seleU.clickElementByJSE(sf.mailinator.gOMailinator);

			if (sf.seleU.getCurrentUrl().contains(contactEmailId)) {
				reportStatusPass(methodName + "Verified : mailinator contact order inbox", true, true);
			} else {

				reportStatusFail(methodName, true);
			}

			sf.seleU.wait(6000);
			if(letterChoose.equals("Appointment Letter")) {
				emailOrderNumber = driver.findElement(By.xpath("//td[contains(text(),'Sandbox: Your Rogers Order "+sf.dataInput.orderNumber+" - Installation and Activation')]"));
			} else if(letterChoose.equals("welcomeLetter")) {
				emailOrderNumber = driver.findElement(By.xpath("//td[contains(text(),'Sandbox: Your Rogers Order "+sf.dataInput.orderNumber+" - Getting Started')]"));
			} else if(letterChoose.equals("FOC_Letter")) {
				emailOrderNumber = driver.findElement(By.xpath("//td[contains(text(),'Sandbox: Order "+sf.dataInput.orderNumber+" - Installation and Activation')]"));
			}
			else {
				emailOrderNumber = driver.findElement(By.xpath("//td[contains(text(),'Sandbox: Your Rogers Order "+sf.dataInput.orderNumber+" - Thank you')]"));
			}

			sf.seleU.clickOnElement(emailOrderNumber);
			sf.seleU.wait(4000);
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.toReceiverID);
			verifyFieldValue("ReceiverEmaiID To ", sf.cableTaskItems.toReceiverID,
					contactEmailId);

			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.toReceiverID);
			verifyFieldValue("FromAdressID ", sf.cableTaskItems.fromAddressID,
					"noreplybusiness@rci.rogers.com");
			sf.seleU.wait(2000);
			if(letterChoose.equals("Appointment Letter")) {
				orderNumberHeader = driver.findElement(By.xpath("//div[contains(text(),'Sandbox: Your Rogers Order "+sf.dataInput.orderNumber+" - Installation and Activation')]"));
			} else if(letterChoose.equals("welcomeLetter")) {
				orderNumberHeader = driver.findElement(By.xpath("//div[contains(text(),'Sandbox: Your Rogers Order "+sf.dataInput.orderNumber+" - Getting Started')]"));
			} else if(letterChoose.equals("FOC_Letter")) {
				orderNumberHeader = driver.findElement(By.xpath("//div[contains(text(),'Sandbox: Order "+sf.dataInput.orderNumber+" - Installation and Activation')]"));
			} 
			else {
				orderNumberHeader = driver.findElement(By.xpath("//div[contains(text(),'Sandbox: Your Rogers Order "+sf.dataInput.orderNumber+" - Thank you')]"));
			}

			sf.seleU.ScrolltoElement(orderNumberHeader);
			verifyFieldPresent("Contact Name Authorized ", orderNumberHeader);

			sf.seleU.hardwait(5000);
			sf.seleU.switchToFrame(sf.cableTaskItems.frameEmailBody.get(0));

		} catch (Exception e) {
			reportStatusFail(methodName + " Error in verifying the Email Attributes", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in Appointment letter email of signing authority email
	 */
	public void verifyFieldValuesInAppointmentEmail(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in Appointment Letter@: "; int k=  0;

			sf.seleU.wait(5000);

			
			driver.switchTo().frame("emailuiFrame");


			WebElement contactName = driver.findElement(By.xpath("//td[contains(text(),'"+sf.dataInput.sf.dataInput.primaryContact+"')]"));
			verifyFieldValueByFomrat("Contact Name Authorized ", contactName, sf.dataInput.primaryContact);	

			sf.seleU.ScrolltoElement(sf.cableTaskItems.preInspectionDateAndTime);

			verifyFieldPresent("Pre Site Inspection Date And Time ", sf.cableTaskItems.preInspectionDateAndTime);

			sf.seleU.ScrolltoElement(sf.cableTaskItems.installationActivationDateAndTime);
			verifyFieldPresent("Installation And Activation Date and Time ", sf.cableTaskItems.installationActivationDateAndTime);

			sf.seleU.ScrolltoElement(sf.cableTaskItems.servicesToBeInstalled);
			verifyFieldPresent("Services To be Installed header Text", sf.cableTaskItems.servicesAddressHeaderText);

			// Verify TV Product product 

			if(!dataTable.get("TV Product Name").trim().equals("NA")) {

				List<WebElement> eleTV = driver.findElements(By.xpath("//li[contains(text(),'"+dataTable.get("TV Product Name").trim()+"')]/following-sibling::ul/li"));
				compareQuantityProductWithSplit(eleTV, dataTable.get("Rogers TV AddOn Product"), dataTable.get("Rogers TV AddOn Quantity"));

			}

			// Verify Internet Product product 

			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {

				List<WebElement> eleInternet = driver.findElements(By.xpath("//li[contains(text(),'"+dataTable.get("Internet Product Name").trim()+"')]/following-sibling::ul/li"));
				compareQuantityProduct(eleInternet, dataTable.get("Internet Equipment Details"), dataTable.get("Internet Product Quantity"));
				if(!dataTable.get("Internet Product Name").trim().equals("NA")) {
					compareQuantityProduct(eleInternet, dataTable.get("Internet_wifi_Service").trim().split(",")[1], dataTable.get("Internet Product Quantity"));
				}
			}

			// Verify IBLC Product 

			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {
				String mergeProductName = dataTable.get("IBLC Product_1") + "," + dataTable.get("Installation_Detail_Product_Name") + "," + dataTable.get("Equipment_Details_Name");
				String mergeQuantity = dataTable.get("IBLC_Lines") + "," + dataTable.get("Line_And_Jack_Qty") + "," + dataTable.get("Equipment_Details_Quantity");

				List<WebElement> elePhone = driver.findElements(By.xpath("//li[contains(text(),'Business Phone')]/following-sibling::ul/li"));
				compareQuantityProductWithSplit(elePhone, mergeProductName, mergeQuantity);
			}

			// Verify service address
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.servicesAddressHeaderText);
			sf.seleU.ScrolltoElement(sf.cableTaskItems.servicesAddressHeaderText);
			verifyFieldPresent("Service Address Hearder Text ", sf.cableTaskItems.servicesAddressHeaderText);

			//			verifyFieldValueByFomrat("Service Address Value ", sf.cableTaskItems.servicesAddressText.get(1),
			//					sf.dataInput.serviceAddress);

			verifyFieldPresent("Service Address Value ", sf.cableTaskItems.servicesAddressText.get(1));

			// Verify Delivery specialist
			sf.seleU.ScrolltoElement(sf.cableTaskItems.deliverySpecialistName);

			verifyFieldValueByFomrat("Delivery Specialist ", sf.cableTaskItems.deliverySpecialistName,
					sf.dataInput.userProfileDelivery);

			//verifyFieldValueByFomrat("Delivery Specialist ", sf.cableTaskItems.deliverySpecialistEmailIdName,
					//sf.omData.deliverySpecialistEmail);

			verifyFieldValueByFomrat("Year in the footer ", sf.rODComplete.yearverificationWelcomeletter,
					sf.omData.RDIWelcomeLetter_Message22);
			
		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Appointment Letter", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in Welcome letter email of signing authority email
	 */
	public void verifyFieldValuesInEmail_ForWelcomeLetter(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in Welcome Letter@: "; int k=  0;

			sf.seleU.wait(5000);

			
			
			driver.switchTo().frame("emailuiFrame");

			//			sf.dataInput.sf.dataInput.primaryContact = "ContactPankajSign ContactPankajSign";
			//			sf.dataInput.userProfileDelivery = "Pankaj Agarwal Delivery";


			WebElement contactName = driver.findElement(By.xpath("//td[contains(text(),'"+sf.dataInput.primaryContact+"')]"));
			verifyFieldValueByFomrat("Contact Name Authorized ", contactName, sf.dataInput.primaryContact);	
			sf.seleU.ScrolltoElement(contactName);
			System.out.println(contactName.getText());

			/*if(contactName.getText().trim().contains(sf.dataInput.userProfileDelivery) && 
					contactName.getText().trim().contains(sf.dataInput.orderNumber)) {

				reportStatusPass(sf.dataInput.userProfileDelivery + " delivery profile is present in the message " +
						"And order no is also present " + sf.dataInput.orderNumber, true, true);
			}*/

			sf.seleU.ScrolltoElement(sf.cableTaskItems.servicesToBeInstalled);
			verifyFieldPresent("Services To be Installed header Text", sf.cableTaskItems.servicesAddressHeaderText);

			// Verify TV Product 

			if(!dataTable.get("TV Product Name").trim().equals("NA")) {

				List<WebElement> eleTV = driver.findElements(By.xpath("//li[contains(text(),'"+dataTable.get("TV Product Name").trim()+"')]/following-sibling::ul/li"));
				compareQuantityProductWithSplit(eleTV, dataTable.get("Rogers TV AddOn Product"), dataTable.get("Rogers TV AddOn Quantity"));

			}

			// Verify Internet Product 

			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {

				List<WebElement> eleInternet = driver.findElements(By.xpath("//li[contains(text(),'"+dataTable.get("Internet Product Name").trim()+"')]/following-sibling::ul/li"));
				compareQuantityProduct(eleInternet, dataTable.get("Internet Equipment Details"), dataTable.get("Internet Product Quantity"));
				if(!dataTable.get("Internet_wifi_Service").trim().equals("NA")) {
					compareQuantityProduct(eleInternet, dataTable.get("Internet_wifi_Service").trim().split(",")[1], dataTable.get("Internet Product Quantity"));
				}
			}

			// Verify office 365 product

			if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {

				List<WebElement> eleOffice365 = driver.findElements(By.xpath("//li[contains(text(),'Microsoft 365')]/following-sibling::ul/li"));
				String mergeProductName = dataTable.get("Office 365 AddOn");
				String mergeQuantity = dataTable.get("Office 365 AddOn Quantity");

				compareQuantityProductWithSplit(eleOffice365, mergeProductName, mergeQuantity);

			}

			// Verify IBLC Product 

			if(!dataTable.get("IBLC Product_1").trim().equals("NA")) {
				String mergeProductName = dataTable.get("IBLC Product_1") + "," + dataTable.get("Installation_Detail_Product_Name") + "," + dataTable.get("Equipment_Details_Name");
				String mergeQuantity = dataTable.get("IBLC_Lines") + "," + dataTable.get("Line_And_Jack_Qty") + "," + dataTable.get("Equipment_Details_Quantity");
				System.out.println(mergeProductName);
				System.out.println(mergeQuantity);
				List<WebElement> elePhone = driver.findElements(By.xpath("//li[contains(text(),'Business Phone')]/following-sibling::ul/li"));
				compareQuantityProductWithSplit(elePhone, mergeProductName, mergeQuantity);
			}

			// Verify service address
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.servicesAddressHeaderText);
			sf.seleU.ScrolltoElement(sf.cableTaskItems.servicesAddressHeaderText);
			verifyFieldPresent("Service Address Hearder Text ", sf.cableTaskItems.servicesAddressHeaderText);

			//			verifyFieldValueByFomrat("Service Address Value ", sf.cableTaskItems.servicesAddressText.get(1),
			//					sf.dataInput.serviceAddress);
			verifyFieldPresent("Service Address Value ", sf.cableTaskItems.servicesAddressText.get(1));

			WebElement message1 = driver.findElement(By.xpath("//div[contains(text(),'"+sf.omData.welcomeLetter_Message1+"')]"));
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(message1);
			verifyFieldPresent("Message dispalyed as ", message1);

			WebElement message2 = driver.findElement(By.xpath("//div[contains(text(),'"+sf.omData.welcomeLetter_Message2+"')]"));
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(message2);
			verifyFieldPresent("Message dispalyed as ", message2);

			WebElement message3 = driver.findElement(By.xpath("//div[contains(text(),'"+sf.omData.welcomeLetter_Message3+"')]"));
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(message3);
			verifyFieldPresent("Message dispalyed as ", message3);

			// Verify Delivery specialist
			sf.seleU.ScrolltoElement(sf.cableTaskItems.deliverySpecialistName);

			verifyFieldValueByFomrat("Delivery Specialist ", sf.cableTaskItems.deliverySpecialistName,
					sf.dataInput.userProfileDelivery);

			//verifyFieldValueByFomrat("Delivery Specialist ", sf.cableTaskItems.deliverySpecialistEmailIdName,
				//	sf.omData.deliverySpecialistEmail);
			
			verifyFieldValueByFomrat("Year in the footer ", sf.rODComplete.yearverificationWelcomeletter,
					sf.omData.RDIWelcomeLetter_Message22);

			// Switch to parent window to close the mailinator
			//			sf.seleU.switchWindow(1);
			//			sf.seleU.closeRecentlyOpenedWindow();

		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Welcome Letter", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in closure letter email of signing authority email
	 *                     
	 *                     1. Verify that Correct account is presented in the Closure Letter:
                                        - CAN No will come  for the Internet Only, TV and Internet Only + TV order
                                        - BAN will come for IBLc, Internet + O365 or Internet + TV + IBLc orders
	 */
	public void verifyFieldValuesInEmail_ForClosureLetter(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in Closure Letter@: "; int k=  0;

sf.seleU.wait(5000);

			
			driver.switchTo().frame("emailuiFrame");

			//						sf.dataInput.sf.dataInput.primaryContact = "contactPankaj7May contactPankaj7May";
			//						sf.dataInput.userProfileDelivery = "Prasanna.delivery VS";

			//1. Verify Hello Message information
			WebElement contactName_HelloMsg = driver.findElement(By.xpath("//td[contains(text(),' Hello "+sf.dataInput.primaryContact+",')]"));
			sf.seleU.wait(2000);
			sf.seleU.ScrolltoElement(contactName_HelloMsg);
			verifyFieldPresent("Hello Message with Contact Name Authorized ", contactName_HelloMsg);	

			// Verify I am please to inform message
			if(contactName_HelloMsg.getText().trim().contains(sf.omData.welcomeLetter_Message1)) {
				reportStatusPass("Thank you Message Is Displayed As " + sf.omData.welcomeLetter_Message1, true, true);
			}

			//2. Verify Account Number

			verifyFieldPresent("Account Number Text ", sf.cableTaskItems.accountNumberText);

			if(!dataTable.get("Office 365 AddOn").trim().equals("NA") || !dataTable.get("IBLC Product_1").trim().equals("NA")) {
				verifyFieldValue("Account Number is ", sf.cableTaskItems.accountNumber.get(0), sf.omData.v21BanNumberEnter);
			} else{
				verifyFieldValue("Account Number is ", sf.cableTaskItems.accountNumber.get(0), sf.omData.canNOInCreateAccount);
			}

			//3. Verify service address
			sf.seleU.waitElementToBeVisible(sf.cableTaskItems.servicesAddressHeaderText);
			sf.seleU.ScrolltoElement(sf.cableTaskItems.servicesAddressHeaderText);
			verifyFieldPresent("Service Address Hearder Text ", sf.cableTaskItems.servicesAddressHeaderText);

			//			verifyFieldValueByFomrat("Service Address Value ", sf.cableTaskItems.servicesAddressText.get(1),
			//					sf.dataInput.serviceAddress);
			verifyFieldPresent("Service Address Value ", sf.cableTaskItems.closureLetterServicesAddressText);

			//		System.out.println(contactName.getText());

			//4. Verify Thank You Message
			WebElement message2 = driver.findElement(By.xpath("//div[contains(text(),'"+sf.omData.closureLetter_Message2+"')]"));
			sf.seleU.wait(1000);
			sf.seleU.ScrolltoElement(message2);
			verifyFieldPresent("Message dispalyed as ", message2);

			sf.seleU.wait(1000);
			if(sf.seleU.getTextFromWebElement(message2).trim().contains(sf.omData.closureLetter_Message3) && 
					sf.seleU.getTextFromWebElement(message2).trim().contains(sf.omData.closureLetter_Message3)) {

				reportStatusPass("Thank you Message Is Displayed As " + sf.seleU.getTextFromWebElement(message2), true, true);
			}

			//5. Verify Delivery specialist
			sf.seleU.ScrolltoElement(sf.cableTaskItems.deliverySpecialistName);

			verifyFieldValueByFomrat("Delivery Specialist ", sf.cableTaskItems.deliverySpecialistName,
					sf.dataInput.userProfileDelivery);

			//verifyFieldValueByFomrat("Delivery Specialist ", sf.cableTaskItems.deliverySpecialistEmailIdName,
					//sf.omData.deliverySpecialistEmail);

			verifyFieldValueByFomrat("Year in the footer ", sf.rODComplete.yearverificationWelcomeletter,
					sf.omData.RDIWelcomeLetter_Message22);
			
			sf.seleU.closeRecentlyOpenedWindow();

		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Email page for Order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the field details in closure letter email of signing authority email
	 *                     
	 *                     1. Verify that Correct account is presented in the Closure Letter:
                                        - CAN No will come  for the Internet Only, TV and Internet Only + TV order
                                        - BAN will come for IBLc, Internet + O365 or Internet + TV + IBLc orders
	 */
	public void verifyFieldValuesInEmail_ForMultisite_ClosureLetter(Hashtable<String, String> dataTable) throws IOException {

		try {
			String methodName = "SFDC_Verify field value in Closure Letter@: "; int k=  0;

			sf.seleU.wait(2000);
			// Verify TV Product 

			if(!dataTable.get("TV Product Name").trim().equals("NA")) {
				for(int i = 2; i<8; i= i+2) {
					List<WebElement> eleTV = driver.findElements(By.xpath("//tbody//tr["+i+"]//div[contains(text(),'Services installed')]/following-sibling::ul/li"));	
				}

		//		compareQuantityProductWithSplit(eleTV, dataTable.get("Rogers TV AddOn Product"), dataTable.get("Rogers TV AddOn Quantity"));

			}

			// Verify Internet Product 

			if(!dataTable.get("Internet Product Name").trim().equals("NA")) {

				List<WebElement> eleInternet = driver.findElements(By.xpath("//li[contains(text(),'"+dataTable.get("Internet Product Name").trim()+"')]/following-sibling::ul/li"));
				compareQuantityProduct(eleInternet, dataTable.get("Internet Equipment Details"), dataTable.get("Internet Product Quantity"));
				if(!dataTable.get("Internet_wifi_Service").trim().equals("NA")) {
					compareQuantityProduct(eleInternet, dataTable.get("Internet_wifi_Service").trim().split(",")[1], dataTable.get("Internet Product Quantity"));
				}
			}

			// Verify office 365 product

			if(!dataTable.get("Office 365 AddOn").trim().equals("NA")) {

				List<WebElement> eleOffice365 = driver.findElements(By.xpath("//li[contains(text(),'Microsoft 365')]/following-sibling::ul/li"));
				String mergeProductName = dataTable.get("Office 365 AddOn");
				String mergeQuantity = dataTable.get("Office 365 AddOn Quantity");

				compareQuantityProductWithSplit(eleOffice365, mergeProductName, mergeQuantity);

			}


		}catch (Throwable e) {
			reportStatusFail(" Error in verifying filed values in Email page for Order", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Office 365 5 task field values
	 */
	public void verifyOffice365TaskItems(Hashtable<String, String> dataTable) throws IOException {

		try { 
			String methodName = "SFDC_Verify field value in Office 365 Task@: ";
			sf.seleU.wait(2000);
			//	 1. Verify office 365 field itemss
			verifyFieldValue("order No is ", sf.cableTaskItems.office365TaskOrderNumber, sf.dataInput.orderNumber);
			verifyFieldValue("order Status ", sf.cableTaskItems.office365TaskOrderStatus, sf.dataInput.orderStatusInProgress);

			// verify sales agent details
			sf.seleU.ScrolltoElementPageCenter(sf.cableTaskItems.salesContactName.get(1));
			verifyFieldValueByFomrat("Sales Agent Name ", sf.cableTaskItems.salesContactName.get(0), sf.dataInput.userProfileAe);
			verifyFieldValueByFomrat("Sales Agent Email ID ", sf.cableTaskItems.salesContactEmaiID.get(0), sf.omData.AEProfileEmail);

			// verify signing authority
			verifyFieldValueByFomrat("Signing Authority Contact Name", sf.cableTaskItems.salesContactName.get(1), sf.dataInput.primaryContact);
			verifyFieldValueByFomrat("Signing Authority Email Id", sf.cableTaskItems.salesContactEmaiID.get(1), sf.dataInput.contactEmailAddress);

			//		sf.seleU.ScrolltoElement(sf.cableTaskItems.office365ProductHeaderTextInTask);
			verifyFieldPresent("Business Name", sf.cableTaskItems.office365TaskBusinessName);
			verifyFieldPresent("Service Address Name", sf.cableTaskItems.office365TaskServiceAddressName);

			sf.seleU.wait(2000);
			// Verify CAN And BAN No
			sf.seleU.ScrolltoElementPageCenter(sf.cableTaskItems.supersystemCANNO);
			supersystemCANNO = sf.seleU.getTextFromWebElement(sf.cableTaskItems.supersystemCANNO).split(":")[1].replaceAll(" ", "");
			System.out.println(supersystemCANNO);
			compareFieldValue("supersystemCANNO", supersystemCANNO, sf.omData.canNOInCreateAccount);

			v21BANNO = sf.seleU.getTextFromWebElement(sf.cableTaskItems.v21BANNO).split(":")[1].replaceAll(" ", "");
			System.out.println(v21BANNO);
			compareFieldValue("v21BANNO", v21BANNO, sf.omData.v21BanNumberEnter);

			System.out.println(sf.seleU.getTextFromWebElement(sf.cableTaskItems.office365TermDuration));
			// Verifying the term duration in the office 365 task, as single xpath will have the entire list with term duration 
			// and product so split it abd then match
			VerifyingFiedlValues1("Term Duration of the office 365 product is ", sf.cableTaskItems.office365TermDuration, dataTable.get("Contract_Term_InMonths").toLowerCase());
			VerifyingFiedlValues1("Office Product is  ", sf.cableTaskItems.office365TermDuration, dataTable.get("Office 365 AddOn"));

			verifyFieldPresent("Task Assigned To User", sf.cableTaskItems.taskAssigedToUser);
			verifyFieldPresent("Task Assigned State", sf.cableTaskItems.taskAssigedState);


			if(sf.seleU.isElementDisplayed(sf.cableTaskItems.assignToMeCheckBoxClick)) {
				sf.seleU.clickElementByJSE(sf.cableTaskItems.assignToMeCheckBoxClick);
				reportStatusPass(" Clicked On Assign To Me check box click", true, false);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.cableTaskItems.confirmButton);
				reportStatusPass(" Clicked On confirm button", true, false);
			}

			sf.seleU.clickElementByJSE(sf.cableTaskItems.nextButton);
			reportStatusPass(" Clicked On the Next Button In office 365 task", true, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying field value in Office 365 Task", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     Creating the object of file upload page to call upload
	 *                     method
	 * 
	 */
	private void upload_File() throws IOException {
		// Auto-generated method stub
		try {
			SFDC_Files_Page fileUploadPage = new SFDC_Files_Page();

			fileUploadPage.uploadFilesWithOutVerify();

		} catch (Throwable e) {
			reportStatusFail("upload file failed", e);
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
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValueByFomrat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().replaceAll(" ", "")
					.contains(expectedText.trim().replaceAll(" ", ""))) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void compareFieldValue(String fieldName, String screenText, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (screenText.replaceAll(" ", "").trim().equals(expectedText.replaceAll(" ", ""))) {
				reportStatusPass(" Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(" Actual Value for " + fieldName + " is " + screenText
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
	 *                     Verify Field value matches the expected result
	 */
	public void verifyPhoneNoFieldValueByFormat(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).trim().replaceAll("[^0-9]", "")
					.contains(expectedText.trim().replaceAll("[^0-9]", ""))) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText.trim().replaceAll("[^0-9]",""),true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + 
						sf.seleU.getTextFromWebElement(element).trim().replaceAll("[^0-9]", "") + " And Expected One is "
						+ expectedText.trim().replaceAll("[^0-9]",""), true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValueWithFormat(String fieldName, WebElement element, String expectedText)
			throws IOException {
		try {

			// Verify Field value matches the expected result

			if (expectedText.trim().contains(sf.seleU.getTextFromWebElement(element).trim().toLowerCase().replaceAll("[^A-Za-z0-9]", ""))) {

				reportStatusPass("Validated " + fieldName + " is " + element.getText(), true, true);

			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
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
			if (sf.seleU.isElementDisplayedWithYellowHighlight(element)) {
				reportStatusPass(
						fieldName + " is present" + " with value as " + sf.seleU.getTextFromWebElementWithYellowHighlight(element), true,
						false);
			} else {
				reportStatusFail(
						fieldName + " is not present" ,
						true);
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
	public void verifyFieldPresentWithCode(String fieldName, WebElement element, WebElement code) throws IOException {

		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayedWithYellowHighlight(element)) {
				reportStatusPass(
						fieldName + " is present" + " with value as " + sf.seleU.getTextFromWebElementWithYellowHighlight(element) +
						"  With code value as " + sf.seleU.getTextFromWebElementWithYellowHighlight(code), true,
						false);
			} else {
				reportStatusFail(
						fieldName + " is not present" ,
						true);
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
	public void verifyFieldAllValuesPresent(String fieldName, List<WebElement> productName) throws IOException {

		try {

			// Verify Field is present
			for(int i=0; i < productName.size(); i=i+3) {


				if (sf.seleU.isElementDisplayedWithYellowHighlight(productName.get(i))) {
					sf.seleU.ScrolltoElement(productName.get(i));
					reportStatusPass(
							fieldName + " is present" + " with value as " + sf.seleU.getTextFromWebElementWithYellowHighlight(productName.get(i+1)) +
							"  With code value as " + sf.seleU.getTextFromWebElementWithYellowHighlight(productName.get(i+2)), true,
							false);
				} else {
					reportStatusFail(
							fieldName + " is not present" , true);
				}
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
	public void verifyFieldValuesForDistinctRings(String fieldName, List<WebElement> productName) throws IOException {

		try {
			List <String> arr  = new ArrayList<String>();

			// Verify Field is present
			// All individual cell of a single row will be validated
			sf.seleU.wait(2000);
			for(int i=0; i < productName.size(); i++) {
				sf.seleU.wait(1000);
				if (sf.seleU.isElementDisplayedWithYellowHighlight(productName.get(i))) {
					sf.seleU.ScrolltoElement(productName.get(i));
					arr.add(sf.seleU.getTextFromWebElementWithYellowHighlight(productName.get(i)).trim());

				} 
				else {
					reportStatusFail(
							fieldName + " is not present" , true);
				}
			}

			reportStatusPass(fieldName + " " + " is present as "
					+ AdditionalUtilities.getAsString(arr), true, false);


		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}

	/** Please change this method based on the below method compareFiedlValues_Internet_TV
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void compareFiedlValues(String fieldName, List<WebElement> productName, String excelFeatureName) throws IOException {

		try {

			List <String> arr_UI_Data  = new ArrayList<String>();

			String arr1[] = excelFeatureName.split(",");
			List <String> arrList_Excel  = new ArrayList<String>(Arrays.asList(arr1));

			// Compare the UI data value with the datasheet values
			for(int i=0; i < productName.size(); i=i+3) {
				if (sf.seleU.isElementDisplayedWithYellowHighlight(productName.get(i))) {
					sf.seleU.ScrolltoElement(productName.get(i));
					arr_UI_Data.add(sf.seleU.getTextFromWebElementWithYellowHighlight(productName.get(i+1)).trim().replace(" ", ""));
				} 
			}

			Collections.sort(arr_UI_Data);
			Collections.sort(arrList_Excel);

			if(arr_UI_Data.equals(arrList_Excel)){
				reportStatusPass(fieldName + "Application Displayed Data " +  
						AdditionalUtilities.getAsString(arr_UI_Data) + " is matched with Excel input data " +
						AdditionalUtilities.getAsString(arrList_Excel), true, true);
			} else {
				reportStatusFail(fieldName + "Application Displayed Data " +  
						AdditionalUtilities.getAsString(arr_UI_Data) + " is not matched with Excel input data " +
						AdditionalUtilities.getAsString(arrList_Excel), true);
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
	public void compareFiedlValues_Internet_TV(String fieldName, List <String> arr_UI_Data, String excelFeatureName) throws IOException {

		try {

			List <String> arrList_Excel  = new ArrayList<String>();
			// Add excel data to the list
			String arr1[] = excelFeatureName.split(",");
			arrList_Excel  = new ArrayList<String>(Arrays.asList(arr1));		

			//		Compare the UI data value with the datasheet values
			Collections.sort(arr_UI_Data);
			Collections.sort(arrList_Excel);

			if(arrList_Excel.equals(arr_UI_Data)){
				reportStatusPass(fieldName + "Application Displayed Data " +  
						AdditionalUtilities.getAsString(arr_UI_Data) + " is matched with Excel input data " +
						AdditionalUtilities.getAsString(arrList_Excel), true, true);
			} else {
				reportStatusFail(fieldName + "Application Displayed Data " +  
						AdditionalUtilities.getAsString(arr_UI_Data) + " is not matched with Excel input data " +
						AdditionalUtilities.getAsString(arrList_Excel), true);
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
	public void VerifyingFiedlValues(String fieldName, WebElement productName, String excelFeatureName) throws IOException {

		try {
			// splitting the excel data and adding it list for comparisions
			String excelValue = excelFeatureName.replaceAll(" ", "");
			List <String> arrList  = new ArrayList<String>(Arrays.asList(excelValue));

			List <String> arrTermList  = new ArrayList<String>();
			arrTermList.add(sf.seleU.getTextFromWebElement(productName).replaceAll(" ", ""));
			System.out.println(arrTermList);

			for(int i = 0; i<arrTermList.size(); i++) {

				for(int j = 0; j<arrList.size(); j++) {

					if(arrTermList.get(i).trim().contains(arrList.get(j).replaceAll(" ", ""))) {
						reportStatusPass(fieldName + "is matched and displayed as " +
								arrList.get(j), true, true);

					} else {
						reportStatusFail(" It's not matched for " + arrList.get(j), true);
					}
				}	
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Verification of Field Value Items", e);
			e.printStackTrace();
		}
	}


	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void VerifyingFiedlValues1(String fieldName, WebElement productName, String excelFeatureName) throws IOException {

		try {
			// splitting the excel data and adding it list for comparisions
			String excelValue[] = excelFeatureName.split(",");
			List <String> arrList  = new ArrayList<String>(Arrays.asList(excelValue));
			System.out.println(arrList);

			System.out.println(sf.seleU.getTextFromWebElement(productName).trim().replaceAll(" ", ""));

			// Verifying the UI data from the excel data
			for(int i = 0; i<arrList.size(); i++) {
				if(sf.seleU.getTextFromWebElement(productName).trim().replaceAll(" ", "").
						contains(arrList.get(i).replaceAll(" ", ""))) {
					System.out.println("found");
					reportStatusPass(fieldName + "is matched and displayed as " +
							arrList.get(i), true, true);
				} else {
					reportStatusFail(" It's not matched for " + arrList.get(i), true);
				}
			}
		}

		catch (Throwable e) {
			reportStatusFail(" Error in Verification of Field Value Items", e);
			e.printStackTrace();
		}
	}
	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void compareFieldlValuesWitFourIndexInRow(String fieldName, List<WebElement> eachProductNameInRow, List<WebElement> totalRow, String excelFeatureName) throws IOException {

		try {
			List <String> arr2  = new ArrayList<String>(); 

			String arr1[] = excelFeatureName.split(",");
			List <String> arrList  = new ArrayList<String>(Arrays.asList(arr1));

			// It will add the middle product from the row i.e second column data
			for(int i=1; i < eachProductNameInRow.size(); i=i+4) {
				if (sf.seleU.isElementDisplayedWithYellowHighlight(eachProductNameInRow.get(i))) {
					arr2.add(sf.seleU.getTextFromWebElementWithYellowHighlight(eachProductNameInRow.get(i)).trim().replace(" ", ""));
				} 
			}

			// Compare the UI data which we extracted in the previous step value with the input data sheet values data
			Collections.sort(arr2);
			Collections.sort(arrList);

			for(int i  = 0; i < arr2.size(); i++) {
				count++;

				for(int j = 0; j<arrList.size(); j++) {

					if(arr2.get(i).trim().contains(arrList.get(j).replaceAll(" ", ""))) {
						reportStatusPass(fieldName + "Application Displayed Data " +  
								arr2 + " is matched with Excel input data " +
								arrList.get(j), true, true);
						foundCount++;
						break;
					}
				}	

				// validate the for the failed condition if the count is matching with the test data
				// first compare total rows with the count then compare input data count with the matched data
				if(count == totalRow.size()) {
					if(foundCount == arrList.size())  {
						reportStatusPass(fieldName + " is matched for all the values " , true, false);
					} else {
						reportStatusFail(fieldName + " is not matched", true);
					} 
				}
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
	public void verifyFieldPresentFromDropDown(String fieldName, WebElement element) throws IOException {

		try {
			// Verify Field is present
			if (!sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element).isEmpty()) {
				reportStatusPass("Validated " + fieldName + " is present" + " with value as "
						+ sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element), true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field Present in dropdown", e);
			e.printStackTrace();
		}

	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldValueFromDropDown(String fieldName, WebElement element, String expectedText) throws IOException {

		try {
			// Verify Field is present
			if (!sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element).isEmpty()) {

				if (sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element).trim().contains(expectedText.trim())) {
					reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
				} else {
					reportStatusFail("Actual Value for " + fieldName + " is " + sf.seleU.getSelectedTextFromDropDownWithYellowHightlight(element) + " And Expected One is "
							+ expectedText, true);
				}
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification in dropdown", e);
			e.printStackTrace();
		}

	}


	public int  countNoOfDigits(int num) throws IOException {
		int count = 0;
		while (num != 0) {
			// num = num/10
			num /= 10;
			++count;
		}
		System.out.println("Number of digits: " + count);
		return count;
	}

	public int  countNoOfCharacters(String string) throws IOException {
		int count = 0;              
		//Counts each character except space    
		for(int i = 0; i < string.length(); i++) {    
			if(string.charAt(i) != ' ')    
				count++;    
		}              
		//Displays the total number of characters present in the given string    
		System.out.println("Total number of characters in a string: " + count);    
		return count;
	}

	/**
	 * @throws IOException
	 *                     PA-OMPI2Sp1
	 *                     Fetch Product from a Table -  compare multiple product and quantity both at the same time.
	 *                     Note ::
	 *                     If the product name is matched then it will verify the quantity, for sme products quantity
	 *                     are in the same row with the product and for some it's in different row or index.
	 * 
	 */
	public void compareQuantityProduct(List<WebElement> actualProductUI, String expProductExcel, String expProductQuantity)
			throws IOException {
		try {
			// compare
			String methodName = "SFDC_Fetch Product Name"; boolean flag = false; 

			String[] expProductArray = expProductExcel.split(",");
			List<String> expPrductName = new ArrayList<String>(Arrays.asList(expProductArray));
			System.out.println(expPrductName);

			if(!expProductQuantity.equals("NA")) {
				String[] expProductQuantityArray = expProductQuantity.split(",");
				excelProductQuantity = new ArrayList<String>(Arrays.asList(expProductQuantityArray));
				//		System.out.println(excelProductQuantity);
			}

			for (int i = 0; i < actualProductUI.size(); i++) {
				String uiProductName = sf.seleU.getTextFromWebElement(actualProductUI.get(i));	
				//		System.out.println(uiProductName);
				count ++;

				for (int j = 0; j < expPrductName.size(); j++) {

					//		int index = uiProductName.indexOf("(");
					//		String uiQuantity = uiProductName.substring(index).replaceAll("[^0-9]","").trim();
					String quantityUI = uiProductName.replaceAll("[^0-9]","").trim();
					String excelProductName = expPrductName.get(j);
					//		System.out.println(excelProductName);

					if(uiProductName.trim().contains(expPrductName.get(j).trim())) {
						flag = true;
						foundCount ++;
						if(!expProductQuantity.equals("NA")) {

							if (quantityUI.replaceAll("[^0-9]","").trim().equals(excelProductQuantity.get(j).trim())) {

								reportStatusPass(methodName + " Validated And Matched Product Name is " + expPrductName.get(j) + " With quantity as "
										+ excelProductQuantity.get(j) , true, true);

								//								if(j == expPrductName.size() -1) {
								//									i=actualProduct.size();
								//								}
								break;
							} // if the product name is matched, now need to match the quantity of the UI and excel, take the next data from the UI
							else if(flag==true){
								i = i+1;
								if(sf.seleU.getTextFromWebElement(actualProductUI.get(i)).replaceAll("[^0-9]","").trim().equals(excelProductQuantity.get(j).trim())) {
									reportStatusPass(methodName + " Validated And Matched Product Name is " + expPrductName.get(j) + " With quantity as "
											+ excelProductQuantity.get(j) , true, true);
									break;
								}
								// if the quantity is not matched in the same or in the next index then it's a failed condition
								else {
									reportStatusFail(" Quantiy is not matched for " + expPrductName.get(j) + " " +excelProductQuantity.get(j), true);
								}
							}

						}  else {  // Only product name is matched and quantity is NA
							reportStatusPass(methodName + " Validated And Matched Product Name is " + expPrductName.get(j) , true, true);	
							break;
						}
					}
				}
			}

			// validate the for the failed condition if the count is matching with the test data
			if(flag == true) {
				reportStatusPass(" is matched for the values " + expProductExcel , true, false);
			} else {			
				reportStatusFail(" is not matched for " + expProductExcel, true);
			} 


		} catch (Throwable e) {
			reportStatusFail(" Invalid verification of quantity in the review order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 *                     PA-OMPI2Sp1
	 *                     Fetch Product from a Table -  compare multiple product and quantity both at the same time.
	 *                     Note ::
	 *                     If the product name is matched then it will verify the quantity, for sme products quantity
	 *                     are in the same row with the product and for some it's in different row or index.
	 * 
	 */
	public void compareQuantityProductWithSplit(List<WebElement> actualProduct, String expProductName, String expProductQuantity)
			throws IOException {
		try {
			// compare
			String methodName = "SFDC_Fetch Product Name"; boolean flag = false; 

			String[] expProductArray = expProductName.split(",");
			List<String> expPrductName = new ArrayList<String>(Arrays.asList(expProductArray));
			System.out.println(expPrductName);

			if(!expProductQuantity.equals("NA")) {
				String[] expProductQuantityArray = expProductQuantity.split(",");
				excelProductQuantity = new ArrayList<String>(Arrays.asList(expProductQuantityArray));
				System.out.println(excelProductQuantity);
			}

			for (int i = 0; i < actualProduct.size(); i++) {
				String uiProductName = sf.seleU.getTextFromWebElement(actualProduct.get(i));	
				System.out.println(uiProductName);
				count ++;

				outerloop: 
					for (int j = 0; j < expPrductName.size(); j++) {
						// for eg: Microsoft office 365 Business Standard(3) +> need to find out the index from where quantity starts
						int index = uiProductName.indexOf("(");
						String uiQuantity = uiProductName.substring(index).replaceAll("[^0-9]","").trim();
						//	String quantity = uiProductName.replaceAll("[^0-9]","").trim();
						String excelProductName = expPrductName.get(j);

						if(uiProductName.trim().contains(expPrductName.get(j).trim())) {
							flag = true;
							foundCount ++;
							if(!excelProductQuantity.get(j).equals("NA")) {

								if (uiQuantity.replaceAll("[^0-9]","").trim().equals(excelProductQuantity.get(j).trim())) {

									reportStatusPass(methodName + " Validated And Matched Product Name is " + expPrductName.get(j) + " With quantity as "
											+ excelProductQuantity.get(j) , true, true);

									break outerloop;
								} // if the product name is matched but the quantity is not matched  then the below condition will be executed
								else if(flag==true){
									i = i+1;
									if(sf.seleU.getTextFromWebElement(actualProduct.get(i)).replaceAll("[^0-9]","").trim().equals(excelProductQuantity.get(j).trim())) {
										reportStatusPass(methodName + " Validated And Matched Product Name is " + expPrductName.get(j) + " With quantity as "
												+ excelProductQuantity.get(j) , true, true);
										break outerloop;
									}
									// if the quantity is not matched in the same or in the next index then it's a failed condition
									else {
										reportStatusFail(" Quantiy is not matched for " + expPrductName.get(j) + " " +excelProductQuantity.get(j), true);
									}
								}

							}  else {  // block for if the quantity is not equal to NA
								reportStatusPass(methodName + " Validated And Matched Product Name is " + expPrductName.get(j) , true, true);	
								break outerloop;
							}
						}
					}
			}

			// validate the for the failed condition if the count is matching with the test data
			if(flag == true) {
				reportStatusPass(" is matched for the values " + expProductName , true, false);
			} else {			
				reportStatusFail(" is not matched for " + expProductName, true);
			} 


		} catch (Throwable e) {
			reportStatusFail(" Invalid verification of quantity in the review order details", e);
			e.printStackTrace();
		}
	}

	//	/**
	//	 * @throws IOException
	//	 *                     PA-OMPI2Sp1
	//	 *                     Fetch Product from a Table - compare multiple product at the same time - in which product and 
	//	 *                     quantity are in the same index
	//	 * 


	//	 */
	//	public void compareQuantityProductInSameIndex(List<WebElement> actualProduct, String expProductName, String expProductQuantity)
	//			throws IOException {
	//		try {
	//			// compare
	//			String methodName = "SFDC_Fetch Product Name"; boolean flag = false; 
	//
	//			String[] expProductArray = expProductName.split(",");
	//			List<String> arPrductName = new ArrayList<String>(Arrays.asList(expProductArray));
	//			System.out.println(arPrductName);
	//
	//
	//			String[] expProductQuantityArray = expProductQuantity.split(",");
	//			List<String> arProductQuantity = new ArrayList<String>(Arrays.asList(expProductQuantityArray));
	//			System.out.println(arProductQuantity);
	//
	//			for (int i = 0; i < actualProduct.size(); i++) {
	//				String uiProductName = sf.seleU.getTextFromWebElement(actualProduct.get(i));	
	//				System.out.println(uiProductName);
	//
	//				for (int j = 0; j < arPrductName.size(); j++) {
	//
	//					String quantity = uiProductName.replaceAll("[^0-9]","").trim();
	//					String excelProductName = arPrductName.get(j);
	//
	//					if(uiProductName.trim().contains(arPrductName.get(j).trim())) {		
	//						flag = true;
	//						if (quantity.trim().equals(arProductQuantity.get(j).trim())) {
	//
	//							reportStatusPass(methodName + " Validated And Matched Product Name is " + arPrductName.get(j) + " With quantity as "
	//									+ arProductQuantity.get(j) , true, true);
	//
	//							break;
	//						} // if the product name is matched but the quantity is not matched  then the below condition will be executed
	//
	//					} // block for if the quantity is not equal to NA
	//				}
	//			}
	//
	//
	//		} catch (Throwable e) {
	//			reportStatusFail(" Invalid verification of quantity in the review order details", e);
	//			e.printStackTrace();
	//		}
	//	}
	//
	//	/**
	//	 * @throws IOException
	//	 *                     PA-OMPI2Sp1
	//	 *                     Fetch Product from a Table - compare only 1 product name at a time
	//	 * 
	//	 */
	//	public void compareProductName(List<WebElement> actualProduct, String expProductName)
	//			throws IOException {
	//		try {
	//			// compare
	//			String methodName = "SFDC_Fetch Product Name"; boolean flag = false; 
	//
	//			String[] expProductArray = expProductName.split(",");
	//			List<String> arPrductName = new ArrayList<String>(Arrays.asList(expProductArray));
	//
	//			for (int i = 0; i < actualProduct.size(); i++) {
	//				String uiProductName = sf.seleU.getTextFromWebElement(actualProduct.get(i));	
	//				//		System.out.println(uiProductName);
	//
	//				for (int j = 0; j < arPrductName.size(); j++) {
	//
	//					String quantity = uiProductName.replaceAll("[^0-9]","").trim();
	//					String excelProductName = arPrductName.get(j);
	//
	//					if(uiProductName.trim().contains(arPrductName.get(j).trim())) {
	//
	//						reportStatusPass(methodName + " Validated And Matched Product Name is " + arPrductName.get(j),
	//								true, true);
	//
	//						if(j == arPrductName.size() -1) {
	//							i=actualProduct.size();
	//						}
	//						break;
	//					}
	//
	//				}
	//			}
	//
	//		} catch (Throwable e) {
	//			reportStatusFail(" Invalid verification of quantity in the review order details", e);
	//			e.printStackTrace();
	//		}
	//	}
}
