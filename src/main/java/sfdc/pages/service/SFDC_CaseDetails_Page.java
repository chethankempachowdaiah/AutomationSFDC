package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.DateTimeUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_EmailToCase_Lib;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 20/FEB/2020
 * 
 *         Case Details Page(Email to case> Accpet case from omni channel> case
 *         details page)
 *
 */
public class SFDC_CaseDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_CaseDetails_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Case Details@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details
	 */
	public void validateEmailToCaseDetails(boolean acceptedViaOmniChannel) throws IOException {
		try {

			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, SFDC_EmailToCase_Lib.subjectLine);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText,
					InputData.emailToCaseServiceUserName);

			SFDC_EmailToCase_Lib.caseID = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			if (acceptedViaOmniChannel) {
				verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			} else {
				verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusNew);
			}

			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginEmail);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText,
					InputData.caseRecordType);
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, SFDC_EmailToCase_Lib.subjectLine);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					SFDC_EmailToCase_Lib.mailBody);
			verifyFieldValue("Case  Web Email", sf.caseDetails.webEmailFieldValueText, SFDC_EmailToCase_Lib.username);
			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText,
					InputData.emailToCaseServiceUserName);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for Proactively Created case
	 */
	public void validate_ProactivelyCreatedCaseDetails() throws IOException {
		try {

			// Verify Case details
			sf.seleU.hardwait(4000);
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.emailToCaseServiceUserName);
			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Case  Priority", sf.caseDetails.priorityFieldValueText);
			sf.seleU.hardwait(3000);
//			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityLow);
			/*
			 * verifyFieldValue("Case  Type", sf.caseDetails.TypeFieldValueText,
			 * sf.dataInput.caseTypeServiceRequest); verifyFieldValue("Case  Sub Type",
			 * sf.caseDetails.SubTypeFieldValueText,
			 * sf.dataInput.caseSubTypeAddWiFiCalling);
			 */
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseRecordType);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);
			sf.seleU.hardwait(3000);
//			verifyFieldValue("Case  Reason", sf.caseDetails.caseReasonFieldValueText,
//					InputData.caseReasonBillingInquiry);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.emailToCaseServiceUserName);

			// Verifying Tellioffer for case created
//			verifyFieldValue("Case  TelliOfferStatus", sf.caseDetails.noAvaiableOffers,
//					InputData.noAvailableOffer);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	public void validate_ProactivelyCreatedCaseDetailsForSubscription(boolean recordTypeValueWireline) throws IOException 
	{
		try 
		{
			// Verify Case details
			sf.seleU.hardwait(4000);
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.emailToCaseServiceUserName);
			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Case  Priority", sf.caseDetails.priorityFieldValueText);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseRecordType);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.emailToCaseServiceUserName);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.validateCaseDetailsButton);
			sf.seleU.hardwait(3000);
			if(recordTypeValueWireline)
			{
				sf.seleU.enterText(sf.caseDetails.subscriptionInputBox.get(1), InputData_Service.subscriptionValue);
			}
			if(!recordTypeValueWireline)
			{
				sf.seleU.enterText(sf.caseDetails.subscriptionInputBox.get(1), InputData_Service.subscriptionValueWireless);
			}			
			sf.seleU.hardwait(2000);			
			sf.seleU.enterText(sf.caseDetails.subscriptionInputBox.get(1), Keys.ARROW_DOWN);
			sf.seleU.enterText(sf.caseDetails.subscriptionInputBox.get(1), Keys.ENTER);
			sf.seleU.enterText(sf.caseDetails.subscriptionInputBox.get(1), Keys.TAB);
			sf.seleU.clickElementByJSE(sf.caseDetails.updateButton);
			sf.seleU.hardwait(3000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	public void validateNextBestAction(boolean recordTypeValueWireline) throws IOException
	{
		try
		{
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.subscriptionLink);
			sf.seleU.hardwait(3000);
			if(recordTypeValueWireline)
			{
				softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.caseDetails.recordTypeValue), InputData.wirelineSubscription, "Error in validating record value");
			}
			if(!recordTypeValueWireline)
			{
				softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.caseDetails.recordTypeValue), InputData.wirelessSubscription, "Error in validating record value");
			}
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.caseDetails.statusValue), Global.dataInput.status_Active, "Error in validating record value");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.subscriptionCloseButton);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseDetails.nextBestActionField), "Error in validating next best action");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.customerInterestButton);
			sf.seleU.hardwait(3000);
			String mainWinHandel = driver.getWindowHandle();

			sf.seleU.clickElementByJSE(sf.caseDetails.cableRenewalLink);
			sf.seleU.hardwait(3000);

			Set<String> windowList = driver.getWindowHandles();
			Iterator<String> it = windowList.iterator();

			while (it.hasNext()) {
				String childWinHandel = it.next();
				if (!mainWinHandel.equals(childWinHandel)) {
					driver.switchTo().window(childWinHandel);
				}
			}
			
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseDetails.accessDeniedField), "Error in validating access denied field");
			sf.seleU.hardwait(3000);			
			driver.close();
			sf.seleU.hardwait(3000);
			driver.switchTo().window(mainWinHandel);
			
			sf.seleU.clickElementByJSE(sf.caseDetails.finishButton);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseDetails.acceptedField), "Error in validating next best action");
			sf.seleU.hardwait(3000);
		}
		catch (Throwable e)
		{
			reportStatusFail(" Error in validating next best action ", e);
		}
	}

	public void validateProactivelyCreatedSNOWCaseDetails() throws IOException {
		try {
            //change case record type
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.changeRecordTypeButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.snowIntegrationRadioButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseRecordTypeSNOW);
			sf.seleU.hardwait(3000);
			
			// Verify Case details
			sf.seleU.hardwait(4000);
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.emailToCaseServiceUserName);
			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Case  Priority", sf.caseDetails.priorityFieldValueText);
			sf.seleU.hardwait(3000);
//			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityLow);
			/*
			 * verifyFieldValue("Case  Type", sf.caseDetails.TypeFieldValueText,
			 * sf.dataInput.caseTypeServiceRequest); verifyFieldValue("Case  Sub Type",
			 * sf.caseDetails.SubTypeFieldValueText,
			 * sf.dataInput.caseSubTypeAddWiFiCalling);
			 */
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);
			sf.seleU.hardwait(3000);
//			verifyFieldValue("Case  Reason", sf.caseDetails.caseReasonFieldValueText,
//					InputData.caseReasonBillingInquiry);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.emailToCaseServiceUserName);

			// Verifying Tellioffer for case created
//			verifyFieldValue("Case  TelliOfferStatus", sf.caseDetails.noAvaiableOffers,
//					InputData.noAvailableOffer);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	public void validateETMStatusAndBellNotification(boolean isServiceCase) throws IOException
	{
		try
		{
			if(isServiceCase)
			{
			   //validate case status closed
			   verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusClosed);
			   sf.seleU.hardwait(3000);
			   
			   //validate case status change message is appearing in bell notifications
			   sf.seleU.hardwait(4000);
			   sf.seleU.clickElementByJSE(sf.caseDetails.notificationsButton);
			   sf.seleU.hardwait(3000);
			   verifyFieldValue("Case  status has changed", sf.caseDetails.notificationsStatusText, InputData.caseStatusClosed);		   
			   reportStatusPass(methodName + " Case Status Message is Appearing in Bell Notification ", true, true);			   
			}
			else
			{
				//validate case status resolved
				verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusResolved);
				
				//validate case status change message is appearing in bell notifications
				sf.seleU.hardwait(4000);
				sf.seleU.clickElementByJSE(sf.caseDetails.notificationsButton);
				sf.seleU.hardwait(3000);
				verifyFieldValue("Case  status has changed", sf.caseDetails.notificationsStatusText, InputData.caseStatusResolved);		   
				reportStatusPass(methodName + " Case Status Message is Appearing in Bell Notification ", true, true);
			}
			sf.seleU.hardwait(3000);
						
			//close the dialog box
			sf.seleU.clickElementByJSE(sf.caseDetails.closeNotificationsIcon);
			sf.seleU.hardwait(4000);
		}
		catch (Throwable e)
		{
			reportStatusFail(" Error in validating case status ", e);
			e.printStackTrace();
		}
	}
	
	public void validateErrorMessage() throws IOException 
	{
		try 
		{            
			//validate new contact is displayed MPOSS-56305 story validation
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseDetails.newContactField), " Error in displaying the new contact ");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.newContactField);
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.cc.FirstNameInput), " Error in displaying the first name input ");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cc.createContactClose);
			sf.seleU.hardwait(3000);
						
			//validate contact name is empty
			softassert.assertTrue((sf.caseDetails.contactNameValueText.getText()).isEmpty(), " Error in validating contact name field is empty ");
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseDetails.manageContactButton), " Error in validating manage contact is displayed ");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.manageContactButton);		
			sf.seleU.hardwait(3000);
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseDetails.addContactMessage), " Error in validating contact message displayed ");
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.closeButton);
			sf.seleU.hardwait(4000);
						
			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.emailToCaseServiceUserName);
			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			verifyFieldHasValue("Case  Priority", sf.caseDetails.priorityFieldValueText);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseRecordType);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.emailToCaseServiceUserName);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 * click on New Contact Button
	 * 
	 * 
	 */
	public void validateNewContactButton() throws IOException 
	{
		try 
		{
			sf.seleU.isElementDisplayed(sf.caseDetails.newContactField);
			reportStatusPass(methodName + "New Contact Button is displayed", true, true);
			sf.seleU.clickOnElement(sf.caseDetails.newContactField);
			reportStatusPass(methodName + "Clicked on New Contact Button", true, true);
			
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful  Validation", e);
			e.printStackTrace();
		}
	}
	
	public void validateHoverFunctionDetails() throws IOException 
	{
		try 
		{
			//validate hovering over various fields shows relevant information
			sf.seleU.hardwait(4000);
			sf.seleU.mouseOverOnElement(sf.caseDetails.contactNameValueText);
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//a[@title='" + InputData_Service.contactForInternalGuidedCase + "']"))))
//			if(sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//a[@title='" + Global.dataInput.contactName + "']"))))
			{
				 reportStatusPass(methodName + "Validated contact details are present", true, true);
			}
			else
			{
				reportStatusFail(methodName + "Error in validating contact details", true);
			}
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.closeButton);
			sf.seleU.hardwait(3000);
//			sf.seleU.mouseOverOnElement(sf.caseDetails.accountNameValueText);
//			sf.seleU.hardwait(2000);
//			if(sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//a[@title='" + InputData_Service.businessAccountForInternalGuidedCase + "']"))))
//			if(sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//a[@title='" + Global.dataInput.businessAccountName + "']"))))
//			{
//				 reportStatusPass(methodName + "Validated Business Account details are present", true, true);
//			}
//			else
//			{
//				reportStatusFail(methodName + "Error in validating Business Account details", true);
//			}
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickElementByJSE(sf.caseDetails.closeButton);
//			sf.seleU.hardwait(3000);
//			sf.seleU.mouseOverOnElement(sf.caseDetails.billingAccountNameValueText);
//			sf.seleU.hardwait(2000);
//			if(sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//a[@title='" + Global.dataInput.billingAccountName + "']"))))
//			{
//				 reportStatusPass(methodName + "Validated Billing Account details are present", true, true);
//			}
//			else
//			{
//				reportStatusFail(methodName + "Error in validating Billing Account details", true);
//			}
//			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseRecordType);
			sf.seleU.hardwait(2000);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);
			sf.seleU.hardwait(2000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for Proactively Created case for technical support type
	 */
	public void validate_ProactivelyCreatedTechnicalSupportCaseDetails() throws IOException 
	{
		try {
			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.emailToCaseServiceUserName);

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityLow);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseTypeTechnicalSupport);

			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.emailToCaseServiceUserName);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	public void validate_ProactivelyCreatedTechnicalSupportCaseDetailsAEProfile() throws IOException 
	{
		try {
			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.AEUserName);

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityLow);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseTypeTechnicalSupport);

			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.AEUserName);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	public void validate_ProactivelyCreatedTechnicalSupportCaseDetailsFraudOpsProfile() throws IOException 
	{
		try {
			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, InputData.FraudOpsUserName);

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityLow);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseTypeTechnicalSupport);

			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText, InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText, InputData.FraudOpsUserName);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}
	
	public void validateErrorResponseFromSnowInComments() throws IOException
	{
		try
		{
			// Select Change Owner Button and enter case owner
			sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
			reportStatusPass(methodName + "Clicked On Change Owner", true, false);
			sf.seleU.wait(4000);

			sf.seleU.clickElementByJSE(sf.caseDetails.selectChangeUser);
			reportStatusPass(methodName + "Select change User", true, false);
			sf.seleU.wait(3000);
			
			sf.seleU.clickElementByJSE(sf.caseDetails.queuesOptionForOwner);
			reportStatusPass(methodName + "Searched Default Queue in Options", true, false);
			sf.seleU.wait(3000);

			sf.seleU.enterText(sf.caseDetails.searchQueuesInput, InputData.caseOwnerTier2WirelineQueue);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.caseDetails.searchQueuesInput, Keys.ENTER);
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.caseDetails.wirelineQueueNew);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.commentsTab);
			sf.seleU.hardwait(5000);
			softassert.assertTrue(sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.caseCommentsText).contains("404"), "Error in validating status code");
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in validating error response from snow in comments ", e);
			e.printStackTrace();
		}		
	}
			
	public void changeCasePriorityAndValidateMilestone() throws IOException
	{
		try
		{
			//enter account name
//			sf.seleU.clickElementByJSE(sf.caseDetails.editAccountNameButton);
//			sf.seleU.hardwait(3000);
//			sf.seleU.clearAndEnterText(sf.caseDetails.accountNameInput, Global.dataInput.businessAccountName);
//			sf.seleU.hardwait(5000);
//			sf.seleU.enterText(sf.caseDetails.accountNameInput, Keys.ARROW_DOWN);
//			sf.seleU.hardwait(3000);
//			sf.seleU.enterText(sf.caseDetails.accountNameInput, Keys.ENTER);
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
//			sf.seleU.hardwait(5000);
			
			//change priority to critical
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionCritical);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.hardwait(5000);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityCritical);
			sf.seleU.hardwait(4000);
			
			//validate milestone time
			if(sf.seleU.getTextFromWebElement(sf.caseDetails.resolutionTime).contains("3 hr"))
			{
				reportStatusPass(methodName + " Validated Resolution Time for Case Priority Critical ", true, true);			
			}
			else
			{
				reportStatusFail(methodName + " Error in validating Resolution Time ", true);
			}	
			sf.seleU.hardwait(4000);
		
		   //change priority to high
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionHigh);
		   sf.seleU.hardwait(3000);
	       sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
		   sf.seleU.hardwait(5000);
		   verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityHigh);
		   sf.seleU.hardwait(4000);
			
		   //validate milestone time
		   if(sf.seleU.getTextFromWebElement(sf.caseDetails.resolutionTime).contains("7 hr"))
		   {
			reportStatusPass(methodName + " Validated Resolution Time for Case Priority High ", true, true);			
		   }
		   else
		   {
			  reportStatusFail(methodName + " Error in validating Resolution Time ", true);
		   }
		   sf.seleU.hardwait(4000);
		   
		   //change priority to medium
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionMedium);
		   sf.seleU.hardwait(3000);
	       sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
		   sf.seleU.hardwait(5000);
		   verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);
		   sf.seleU.hardwait(4000);
			
		   //validate milestone time
		   if(sf.seleU.getTextFromWebElement(sf.caseDetails.resolutionTime).contains("23 hr"))
		   {
			reportStatusPass(methodName + " Validated Resolution Time for Case Priority Medium ", true, true);
		   }
		   else
		   {
			  reportStatusFail(methodName + " Error in validating Resolution Time ", true);
		   }
		   sf.seleU.hardwait(4000);
		   
		   //change priority to low
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionLow);
		   sf.seleU.hardwait(3000);
	       sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
		   sf.seleU.hardwait(5000);
		   verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityLow);
		   sf.seleU.hardwait(4000);
			
		   //validate milestone time
		   if(sf.seleU.getTextFromWebElement(sf.caseDetails.resolutionTime).contains("1 day 23 hr"))
		   {
			reportStatusPass(methodName + " Validated Resolution Time for Case Priority Low ", true, true);
		   }
		   else
		   {
			  reportStatusFail(methodName + " Error in validating Resolution Time ", true);
		   }
		   sf.seleU.hardwait(4000);
		   
		   //change priority to planning
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
		   sf.seleU.hardwait(3000);
		   sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionPlanning);
		   sf.seleU.hardwait(3000);
	       sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
		   sf.seleU.hardwait(5000);
		   verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityPlanning);
		   sf.seleU.hardwait(4000);
			
		   //validate milestone time
		   if(sf.seleU.getTextFromWebElement(sf.caseDetails.resolutionTime).contains("3 days 7 hr"))
		   {
			reportStatusPass(methodName + " Validated Resolution Time for Case Priority Planning ", true, true);
		   }
		   else
		   {
			  reportStatusFail(methodName + " Error in validating Resolution Time ", true);
		   }
		   sf.seleU.hardwait(4000);
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in changing case priority and validating milestone ", e);
			e.printStackTrace();
		}
	}

	public void changeCaseStatusAndValidateMilestone() throws IOException
	{
		try
		{			
			// Verify user is allowed to edit Case category
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCategoryButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCategoryField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseCategoryApplication);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case category ", true, true);
			
			// Verify user is allowed to edit Case Class
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseClassField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseRogersBusiness);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case class ", true, true);
			
			// Verify user is allowed to edit Case SubClass
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseSubClassField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseSubClassAccess);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case subclass ", true, true);
					
			// Verify user is allowed to edit Case Reason
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseReasonDeviceandHardwareOption);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case reason ", true, true);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.save);
			
			// Change Status to Resolved
			sf.seleU.wait(16000);
			sf.seleU.clickElementByJSE(sf.caseDetails.resolvedTab);
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			sf.seleU.wait(6000);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusResolved);
			
			//validate Milestone
			verifyFieldValue("Milestones", sf.caseDetails.milestoneMessage, "You completed all the milestones.");
			sf.seleU.hardwait(5000);
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in changing case status and validating milestone ", e);
			e.printStackTrace();
		}
	}
	
	public void createInternalGuidedCase() throws IOException{
		try {
			//click on internal case
			sf.seleU.hardwait(8000);
			sf.seleU.clickElementByJSE(sf.caseDetails.internalCase);
			reportStatusPass(methodName + " Clicked internal case ", true, false);
			sf.seleU.wait(4000);   			
		    }
		
		catch(Throwable e) {
			reportStatusFail(" Error in clicking internal case button ", e);
			e.printStackTrace();
		}
	}
	
	public void createExternalTicket() throws IOException
	{
		try
		{
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(5000);
			
			//click on new			
			sf.seleU.mouseOverOnElement(sf.caseRelatedDetails.externalTicketsIcon);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsNew);
			sf.seleU.hardwait(3000);
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in creating external ticket ", e);
			e.printStackTrace();
		}
	}
	
	public void enterETMCaseDetails() throws IOException
	{
		try
		{
			//click on ETM
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.buttonETM);
			reportStatusPass(methodName + " Selected case type as ETM ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L1
			sf.seleU.clickOnElement(sf.caseDetails.etmL1InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL1Options.get(2));
			reportStatusPass(methodName + " Selected ETM L1 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L2
			sf.seleU.clickOnElement(sf.caseDetails.etmL2InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL2Options.get(5));
			reportStatusPass(methodName + " Selected ETM L2 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L3
			sf.seleU.clickOnElement(sf.caseDetails.etmL3InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL3Options.get(1));
			reportStatusPass(methodName + " Selected ETM L3 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L4
			sf.seleU.clickOnElement(sf.caseDetails.etmL4InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL4Options.get(9));
			reportStatusPass(methodName + " Selected ETM L4 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L5
			sf.seleU.clickOnElement(sf.caseDetails.etmL5InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL5Options.get(1));
			reportStatusPass(methodName + " Selected ETM L5 ", true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);
			
			//select status
			sf.seleU.clickOnElement(sf.caseDetails.statusInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.statusOptions.get(1));
			reportStatusPass(methodName + " Selected Status ", true, false);
			sf.seleU.hardwait(3000);
			
			//select product line
			sf.seleU.clickOnElement(sf.caseDetails.productLineInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.productLineOptions.get(1));
			reportStatusPass(methodName + " Selected Product Line ", true, false);
			sf.seleU.hardwait(3000);
			
			//select contact's preferred communication method
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationMethodInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationMethodOptions.get(1));
			reportStatusPass(methodName + " Selected contact's preferred communication method ", true, false);
			sf.seleU.hardwait(3000);
			
			//select contact's preferred communication time
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationTimeInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationTimeOptions.get(1));
			reportStatusPass(methodName + " Selected contact's preferred communication time ", true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(3000);
						
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			
			//enter date of payment			
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.dateOfPaymentEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.dateOfPaymentEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, DateTimeUtilities.currentSystemDate("dd-MM-yyyy"));
			sf.seleU.hardwait(2000);
						
			//enter amount of payment
			sf.seleU.clickElementByJSE(sf.caseDetails.amountOfPaymentEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.amountOfPaymentEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "200");
			sf.seleU.hardwait(2000);
			
			//enter location of payment			
			sf.seleU.clickElementByJSE(sf.caseDetails.paymentMadeEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.paymentMadeEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "Rogers Store");
			sf.seleU.hardwait(2000);
						
			//enter location street number			
			sf.seleU.clickElementByJSE(sf.caseDetails.streetNumberEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.streetNumberEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "8200");
			sf.seleU.hardwait(2000);
						
			//enter location street name			
			sf.seleU.clickElementByJSE(sf.caseDetails.streetNameEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.streetNameEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "Dixie Road");
			sf.seleU.hardwait(2000);
			
			//enter location city			
			sf.seleU.clickElementByJSE(sf.caseDetails.cityEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.cityEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "Brampton");
			sf.seleU.hardwait(2000);
			
			//enter location postal code			
			sf.seleU.clickElementByJSE(sf.caseDetails.postalCodeEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.postalCodeEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "L6T 4B8");
			sf.seleU.hardwait(2000);
						
			//enter location province			
			sf.seleU.clickElementByJSE(sf.caseDetails.provinceEditor);
			sf.seleU.hardwait(2000);
//			sf.seleU.clearTextWithoutHighlight(sf.caseDetails.accountNumberInputBox);
			sf.seleU.clearTextByMouseAction(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.provinceEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterTextWithoutHighlight(sf.caseDetails.accountNumberInputBox, "ON");
			sf.seleU.hardwait(2000);
						
			reportStatusPass(methodName + " Entered Answers to all questions ", true, true);
			
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.hardwait(3000);			
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(3000);
			
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.errorMessageAPI), "Error in Displaying error Message API");
			sf.seleU.hardwait(3000);
			
//			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.buttonClose.get(2));
            sf.seleU.clickElementByJSE(sf.caseRelatedDetails.closeButton);
			sf.seleU.hardwait(4000);
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in filling ETM case details ", e);
			e.printStackTrace();
		}
	}
	
	public void enterETMCaseDetailsL5Truck() throws IOException
	{
		try
		{
			//click on ETM
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.buttonETM);
			reportStatusPass(methodName + " Selected case type as ETM ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L1
			sf.seleU.clickOnElement(sf.caseDetails.etmL1InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL1Options.get(4));
			reportStatusPass(methodName + " Selected ETM L1 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L2
			sf.seleU.clickOnElement(sf.caseDetails.etmL2InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL2Options.get(2));
			reportStatusPass(methodName + " Selected ETM L2 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L3
			sf.seleU.clickOnElement(sf.caseDetails.etmL3InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL3Options.get(1));
			reportStatusPass(methodName + " Selected ETM L3 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L4
			sf.seleU.clickOnElement(sf.caseDetails.etmL4InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL4Options.get(1));
			reportStatusPass(methodName + " Selected ETM L4 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L5
			sf.seleU.clickOnElement(sf.caseDetails.etmL5InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL5Options.get(2));
			reportStatusPass(methodName + " Selected ETM L5 ", true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);
			
			//select status
			sf.seleU.clickOnElement(sf.caseDetails.statusInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.statusOptions.get(1));
			reportStatusPass(methodName + " Selected Status ", true, false);
			sf.seleU.hardwait(3000);
			
			//select product line
			sf.seleU.clickOnElement(sf.caseDetails.productLineInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.productLineOptions.get(1));
			reportStatusPass(methodName + " Selected Product Line ", true, false);
			sf.seleU.hardwait(3000);
			
			//select contact's preferred communication method
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationMethodInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationMethodOptions.get(1));
			reportStatusPass(methodName + " Selected contact's preferred communication method ", true, false);
			sf.seleU.hardwait(3000);
			
			//select contact's preferred communication time
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationTimeInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationTimeOptions.get(1));
			reportStatusPass(methodName + " Selected contact's preferred communication time ", true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(3000);
						
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(3000);
			
//			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.buttonClose.get(2));
            sf.seleU.clickElementByJSE(sf.caseRelatedDetails.closeButton);
			sf.seleU.hardwait(4000);
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in filling ETM case details ", e);
			e.printStackTrace();
		}
	}

	
	public void enterETMCaseDetails(String ETML1, String ETML2, String ETML3, String ETML4, String communicationMethod) throws IOException
	{
		try
		{
			//click on ETM
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.buttonETM);
			reportStatusPass(methodName + " Selected case type as ETM ", true, false);
			sf.seleU.hardwait(3000);
			
			System.out.println(ETML1 + ETML2 + ETML3 + ETML4 + communicationMethod);
			
			//select ETM L1			
			sf.seleU.clickOnElement(sf.caseDetails.etmL1InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L1']/following::ul//li//span//span[text()='" + ETML1 + "']")));
//			sf.seleU.clickOnElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L1']/following::ul//li//span//span[text()='" + ETML1 + "']"));
			reportStatusPass(methodName + " Selected ETM L1 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L2
			sf.seleU.clickOnElement(sf.caseDetails.etmL2InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L2']/following::ul//li//span//span[text()='" + ETML2 + "']")));
//			sf.seleU.clickOnElement(By.xpath("//span[text()='ETM']/following::span[text()='ETML2']/following::ul//li//span//span[text()='" + ETML2 + "']"));
			reportStatusPass(methodName + " Selected ETM L2 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L3
			sf.seleU.clickOnElement(sf.caseDetails.etmL3InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L3']/following::ul//li//span//span[text()='" + ETML3 + "']")));
//			sf.seleU.clickOnElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L3']/following::ul//li//span//span[text()='" + ETML3 + "']"));
			reportStatusPass(methodName + " Selected ETM L3 ", true, false);
			sf.seleU.hardwait(3000);

			//select ETM L4
			sf.seleU.clickOnElement(sf.caseDetails.etmL4InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L4']/following::ul//li//span//span[text()='" + ETML4 + "']")));
//			sf.seleU.clickOnElement(By.xpath("//span[text()='ETM']/following::span[text()='ETM L4']/following::ul//li//span//span[text()='" + ETML4 + "']"));
			reportStatusPass(methodName + " Selected ETM L4 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select ETM L5
			sf.seleU.clickOnElement(sf.caseDetails.etmL5InputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.etmL5Options.get(1));
			reportStatusPass(methodName + " Selected ETM L5 ", true, false);
			sf.seleU.hardwait(3000);
			
			//select status
			sf.seleU.clickOnElement(sf.caseDetails.statusInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.statusOptions.get(1));
			reportStatusPass(methodName + " Selected Status ", true, false);
			sf.seleU.hardwait(3000);
			
			//select contact's preferred communication method
			sf.seleU.clickOnElement(sf.caseDetails.preferredCommunicationMethodInputBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(By.xpath("//span[text()='ETM']/following::span[contains(text(),'Preferred Communication')]/following::ul//li//span//span[text()='" + communicationMethod + "']" ));
			reportStatusPass(methodName + " Selected contact's preferred communication method ", true, false);
			sf.seleU.hardwait(3000);
			
			sf.seleU.scrollByCoOrdinates(2);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			
			//check value can be entered in the input box
			if(ETML1.equals("Account Issues (Rogers)") && ETML2.equals("Care Issues") && ETML3.equals("Account Not In ICM") && ETML4.equals("ICM/Maestro") && communicationMethod.equals("Email"))		
			{	
			sf.seleU.clickElementByJSE(sf.caseDetails.accountNumberEditor);
			sf.seleU.hardwait(4000);
//			sf.seleU.clearAndEnterText(sf.caseDetails.accountNumberInputBox, Global.dataInput.billingAccountName);
			sf.seleU.clearText(sf.caseDetails.accountNumberInputBox);			
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.accountNumberEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.caseDetails.accountNumberInputBox, InputData_Service.billingAccountForETMCase);
//			sf.seleU.clearAndEnterText(sf.caseDetails.accountNumberInputBoxNew, InputData_Service.billingAccountForETMCase);
			sf.seleU.hardwait(4000);			
			sf.seleU.enterText(sf.caseDetails.accountNumberInputBox, Keys.TAB);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.hardwait(3000);
			}
			
			//check value can be selected from pick list
			if(ETML1.equals("Account Issues (Rogers)") && ETML2.equals("Care Issues") && ETML3.equals("Cancellation") && ETML4.equals("N/A") && communicationMethod.equals("Email"))		
			{
			sf.seleU.clickElementByJSE(sf.caseDetails.accountDisconnectEditor);
			sf.seleU.hardwait(4000);
			sf.seleU.clearText(sf.caseDetails.accountNumberInputBox);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.accountDisconnectEditor);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.caseDetails.accountNumberInputBox,"True");
//			sf.seleU.clearAndEnterText(sf.caseDetails.accountNumberInputBox,"True");
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.caseDetails.accountNumberInputBox, Keys.TAB);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.hardwait(3000);
			}
			
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			
            sf.seleU.hardwait(4000);
//			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.buttonClose.get(2));
            sf.seleU.clickElementByJSE(sf.caseRelatedDetails.closeButton);
			sf.seleU.hardwait(4000);		
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in filling ETM case details ", e);
			e.printStackTrace();
		}
	}
	
	public void enterCaseDetails() throws IOException
	{
		try 
		{			
			//check case type fraud risk		
			if (sf.seleU.isElementSelected(sf.caseDetails.buttonFraudRiskType.get(0)));
			{
				reportStatusPass(methodName + " Selected case type as fraud risk ", true, false);
			}
			sf.seleU.wait(4000);			
            boolean isCaseOriginInternal= sf.seleU.isElementPresent(By.xpath("//input[@data-value='Internal']"));
			
			if(isCaseOriginInternal)
			{
				reportStatusPass(methodName + " Selected Case Origin ", true, false);	
			}
			else
			{
				reportStatusFail(methodName + " Error in selecting case origin", true);
			}						
			sf.seleU.wait(4000);
			
			//check product family selected by default			
            boolean isProductFamilyWireless= sf.seleU.isElementPresent(By.xpath("//input[@data-value='Wireless']"));			
			if(isProductFamilyWireless)
			{
				reportStatusPass(methodName + " Selected Product Family ", true, false);
				sf.seleU.wait(3000);
			}
			else
			{
			sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));			
			InputData_Service.setDataForProductFamilyOptions();
									
			//verify product families are displayed for internal guided case			
			boolean isProductFamiliesDisplayed = true;
			for (int i=1; i<= (sf.caseDetails.productFamilyOptions.size()-5); i++) 
			{				
				if(InputData_Service.productFamilyOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.productFamilyOptions.get(i))))
				{
					isProductFamiliesDisplayed= true;
				}
				else 
				{
					isProductFamiliesDisplayed= false;
					break;
				}				
			}			
//			ArrayList<String> actualProductFamilyOptions = new ArrayList<String>();
//			
//			for (int i=1; i<=(sf.caseDetails.productFamilyOptions.size()-5); i++)
//			{
//				actualProductFamilyOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.productFamilyOptions.get(i)));
//				sf.seleU.hardwait(2000);
//			}
//			
//			Collections.sort(InputData_Service.productFamilyOptions);
//			Collections.sort(actualProductFamilyOptions);
//		
//			if(isProductFamiliesDisplayed && InputData_Service.productFamilyOptions.equals(actualProductFamilyOptions))
//			{
//			  reportStatusPass(methodName + " Verified product families options displayed", true, true);
//			}			
//			else
//			{
//			  reportStatusFail(methodName + "product families options not displayed", true);				
//			}
			
			sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
			reportStatusPass(methodName + " Selected Product Family ", true, false);
			sf.seleU.hardwait(4000);
			}
			
			//select category		
			sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
			InputData_Service.setDataForCategoryOptions();
			
			boolean isCategoryOptionsDisplayed= true;			
			for(int i=1; i<=(sf.caseDetails.categoryOptions.size()-2); i++)
			{				
				if(InputData_Service.categoryOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.categoryOptions.get(i))))
				{
					isCategoryOptionsDisplayed= true;
				}
				else 
				{
				    isCategoryOptionsDisplayed= false;
				    break;
				}
			}
			
//			ArrayList<String> actualCategoryOptions = new ArrayList<String>();
//			
//			for (int i=1; i<=(sf.caseDetails.categoryOptions.size()-2); i++)
//			{
//				actualCategoryOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.categoryOptions.get(i)));
//				sf.seleU.hardwait(2000);
//			}
//			
//			Collections.sort(InputData_Service.categoryOptions);
//			Collections.sort(actualCategoryOptions);
//
//			if(isCategoryOptionsDisplayed && InputData_Service.categoryOptions.equals(actualCategoryOptions)) 
//			{			
//			  reportStatusPass(methodName + " Verified Category options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "Category options not displayed", true);	
//			}
			
			sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
			reportStatusPass(methodName + " Selected Category ", true, false);
			sf.seleU.hardwait(4000);
			
			//select Sub Category
			sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));	
			InputData_Service.setDataForSubCategoryOptions();
			
			boolean isSubCategoryOptionsDisplayed= true;			
			for(int i=1; i<=(sf.caseDetails.subCategoryOptions.size()-1); i++)
			{				
				if(InputData_Service.subCategoryOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.subCategoryOptions.get(i))))
				{
					isSubCategoryOptionsDisplayed= true;
				}
				else 
				{
				    isSubCategoryOptionsDisplayed= false;
				    break;
				}
			}
			
//			ArrayList<String> actualSubCategoryOptions = new ArrayList<String>();
//			
//			for (int i=1; i<=(sf.caseDetails.subCategoryOptions.size()-1); i++)
//			{
//				actualSubCategoryOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.subCategoryOptions.get(i)));
//				sf.seleU.hardwait(2000);
//			}
//			
//			Collections.sort(InputData_Service.subCategoryOptions);
//			Collections.sort(actualSubCategoryOptions);
//
//			if(isSubCategoryOptionsDisplayed && InputData_Service.subCategoryOptions.equals(actualSubCategoryOptions))
//			{
//			  reportStatusPass(methodName + " Verified Sub Category options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "Sub Category options not displayed", true);	
//			}
			
			sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
			reportStatusPass(methodName + " Selected Sub Category ", true, false);
			sf.seleU.hardwait(4000);			
								
			if (sf.seleU.isElementDisplayed(sf.caseDetails.toolTip.get(0)))
			{
				reportStatusPass(methodName + " Selected Sub Category Tooltip is displayed ", true, false);
			}
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);			
		}
		catch(Throwable e) {
			reportStatusFail(" Error in filling case details ", e);
			e.printStackTrace();
		}
	}
	
	public void enterCaseDetailsFraudOpsProfile() throws IOException
	{
		try 
		{			
			//check case type fraud risk		
			if (sf.seleU.isElementSelected(sf.caseDetails.buttonFraudRiskType.get(0)));
			{
				reportStatusPass(methodName + " Selected case type as fraud risk ", true, false);
			}
			sf.seleU.wait(4000);			
            boolean isCaseOriginInternal= sf.seleU.isElementPresent(By.xpath("//input[@data-value='Internal']"));
			
			if(isCaseOriginInternal)
			{
				reportStatusPass(methodName + " Selected Case Origin ", true, false);	
			}
			else
			{
				reportStatusFail(methodName + " Error in selecting case origin", true);
			}						
			sf.seleU.wait(4000);
			
			//check product family selected by default			
            boolean isProductFamilyWireless= sf.seleU.isElementPresent(By.xpath("//input[@data-value='Wireless']"));			
			if(isProductFamilyWireless)
			{
				reportStatusPass(methodName + " Selected Product Family ", true, false);
				sf.seleU.wait(3000);
			}
			else
			{
			sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));			
			InputData_Service.setDataForProductFamilyOptions();
									
			//verify product families are displayed for internal guided case			
			boolean isProductFamiliesDisplayed = true;
			for (int i=1; i<= (sf.caseDetails.productFamilyOptions.size()-5); i++) 
			{				
				if(InputData_Service.productFamilyOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.productFamilyOptions.get(i))))
				{
					isProductFamiliesDisplayed= true;
				}
				else 
				{
					isProductFamiliesDisplayed= false;
					break;
				}				
			}			
//			ArrayList<String> actualProductFamilyOptions = new ArrayList<String>();
//			
//			for (int i=1; i<=(sf.caseDetails.productFamilyOptions.size()-5); i++)
//			{
//				actualProductFamilyOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.productFamilyOptions.get(i)));
//				sf.seleU.hardwait(2000);
//			}
//			
//			Collections.sort(InputData_Service.productFamilyOptions);
//			Collections.sort(actualProductFamilyOptions);
//		
//			if(isProductFamiliesDisplayed && InputData_Service.productFamilyOptions.equals(actualProductFamilyOptions))
//			{
//			  reportStatusPass(methodName + " Verified product families options displayed", true, true);
//			}			
//			else
//			{
//			  reportStatusFail(methodName + "product families options not displayed", true);				
//			}
			
			sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
			reportStatusPass(methodName + " Selected Product Family ", true, false);
			sf.seleU.hardwait(4000);
			}
			
			//select category		
			sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
			InputData_Service.setDataForCategoryOptions();
			
			boolean isCategoryOptionsDisplayed= true;			
			for(int i=1; i<=(sf.caseDetails.categoryOptions.size()-53); i++)
			{				
				if(InputData_Service.categoryOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.categoryOptions.get(i))))
				{
					isCategoryOptionsDisplayed= true;
				}
				else 
				{
				    isCategoryOptionsDisplayed= false;
				    break;
				}
			}
			
//			ArrayList<String> actualCategoryOptions = new ArrayList<String>();
//			
//			for (int i=1; i<=(sf.caseDetails.categoryOptions.size()-2); i++)
//			{
//				actualCategoryOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.categoryOptions.get(i)));
//				sf.seleU.hardwait(2000);
//			}
//			
//			Collections.sort(InputData_Service.categoryOptions);
//			Collections.sort(actualCategoryOptions);
//
//			if(isCategoryOptionsDisplayed && InputData_Service.categoryOptions.equals(actualCategoryOptions)) 
//			{			
//			  reportStatusPass(methodName + " Verified Category options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "Category options not displayed", true);	
//			}
			
			sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
			reportStatusPass(methodName + " Selected Category ", true, false);
			sf.seleU.hardwait(4000);
			
			//select Sub Category
			sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));	
			InputData_Service.setDataForSubCategoryOptions();
			
			boolean isSubCategoryOptionsDisplayed= true;			
			for(int i=1; i<=(sf.caseDetails.subCategoryOptions.size()-52); i++)
			{				
				if(InputData_Service.subCategoryOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.subCategoryOptions.get(i))))
				{
					isSubCategoryOptionsDisplayed= true;
				}
				else 
				{
				    isSubCategoryOptionsDisplayed= false;
				    break;
				}
			}
			
//			ArrayList<String> actualSubCategoryOptions = new ArrayList<String>();
//			
//			for (int i=1; i<=(sf.caseDetails.subCategoryOptions.size()-1); i++)
//			{
//				actualSubCategoryOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.subCategoryOptions.get(i)));
//				sf.seleU.hardwait(2000);
//			}
//			
//			Collections.sort(InputData_Service.subCategoryOptions);
//			Collections.sort(actualSubCategoryOptions);
//
//			if(isSubCategoryOptionsDisplayed && InputData_Service.subCategoryOptions.equals(actualSubCategoryOptions))
//			{
//			  reportStatusPass(methodName + " Verified Sub Category options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "Sub Category options not displayed", true);	
//			}
			
			sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
			reportStatusPass(methodName + " Selected Sub Category ", true, false);
			sf.seleU.hardwait(4000);			
								
			if (sf.seleU.isElementDisplayed(sf.caseDetails.toolTip.get(0)))
			{
				reportStatusPass(methodName + " Selected Sub Category Tooltip is displayed ", true, false);
			}
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);			
		}
		catch(Throwable e) {
			reportStatusFail(" Error in filling case details ", e);
			e.printStackTrace();
		}
	}

	
	public void enterTicketDetails() throws IOException
	{
		try
		{
			sf.seleU.clickElementByJSE(sf.caseDetails.buttonExternalTicket);
			sf.seleU.hardwait(3000);
			
			//enter external ticket number
			sf.seleU.enterText(sf.caseDetails.externalTicketNumberTextBox, AdditionalUtilities.generateRandomDigits(5));			
			reportStatusPass(methodName + " Entered external Ticket Number", true, true);
			sf.seleU.hardwait(3000);
			
			//select source
			sf.seleU.clickOnElement(sf.caseDetails.sourceInputBox);
			sf.seleU.hardwait(3000);
			InputData_Service.setDataForSourceOptions();
			
			ArrayList<String> actualSourceOptions = new ArrayList<String>();
			
			for (int i=1; i<=(sf.caseDetails.sourceOptions.size()-6); i++)
			{
				actualSourceOptions.add(sf.seleU.getTextFromWebElement(sf.caseDetails.sourceOptions.get(i)));
				sf.seleU.hardwait(2000);
			}			
			Collections.sort(InputData_Service.expectedSourceOptions);
			sf.seleU.hardwait(3000);			
			Collections.sort(actualSourceOptions);
			sf.seleU.hardwait(3000);
		
			if(InputData_Service.expectedSourceOptions.equals(actualSourceOptions))
			{
			  reportStatusPass(methodName + " Verified Source options displayed", true, true);
			}			
			else
			{
			  reportStatusFail(methodName + "Source options not displayed", true);				
			}
			sf.seleU.hardwait(3000);
			
			sf.seleU.clickOnElement(sf.caseDetails.sourceOptions.get(1));
			reportStatusPass(methodName + " Selected Source Option", true, true);
			sf.seleU.hardwait(3000);
			
			//select status
			sf.seleU.clickOnElement(sf.caseDetails.statusValueBox);
			sf.seleU.hardwait(3000);
			sf.seleU.clickOnElement(sf.caseDetails.statusOptionsExternalType.get(1));
			reportStatusPass(methodName + " Selected Status ", true, false);
			sf.seleU.hardwait(3000);
						
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);	
			sf.seleU.hardwait(3000);
			
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.closeButton);
			sf.seleU.hardwait(3000);
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in filling ticket details ", e);
			e.printStackTrace();
		}
	}
	
	public void validateTicketDetails() throws IOException
	{
		try
		{
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(5000);
			
			//click on external tickets
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIcon);
			sf.seleU.hardwait(4000);
			
			//click on external ticket number
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketNumber);
			sf.seleU.hardwait(8000);
			
			//validate different fields
			verifyFieldValue("Case", sf.caseRelatedDetails.parentCaseNumber, InputData.caseNumber);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Status", sf.caseRelatedDetails.status, "In Progress");
			sf.seleU.hardwait(4000);			
		}
		catch(Throwable e) 
		{
			reportStatusFail(" Error in validating ticket details ", e);
			e.printStackTrace();
		}
	}
	
	public void enterCaseDetailsForDataDrivenTest(String ProductFamily, String Category, String SubCategory) throws IOException{
		try {
			
			//check case type fraud risk
		
			if (sf.seleU.isElementSelected(sf.caseDetails.buttonFraudRiskType.get(0)));
			{
				reportStatusPass(methodName + " Selected case type as fraud risk ", true, false);
			}
			sf.seleU.wait(4000);
					
			//check case origin selected by default
//			sf.seleU.clickOnElement(sf.caseDetails.categoryDropdown.get(21));
			
			
            boolean isCaseOriginInternal= sf.seleU.isElementPresent(By.xpath("//input[@data-value='Internal']"));
			
			if(isCaseOriginInternal)
			{
				reportStatusPass(methodName + " Selected Case Origin ", true, false);	
			}
			else
			{
				reportStatusFail(methodName + " Error in selecting case origin", true);
			}
						
			sf.seleU.wait(4000);
						
			System.out.println(ProductFamily + Category + SubCategory);
			
			if(ProductFamily.equals("Data Centre") && Category.equals("Escalation - Fraud") && SubCategory.equals("High Risk Order"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Data Centre") && Category.equals("Escalation - Fraud") && SubCategory.equals("PIN/Password Reset"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(2));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Data Centre") && Category.equals("Fraud Investigation") && SubCategory.equals("Report Risk"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Internet and Advanced Networks") && Category.equals("Escalation - Fraud") && SubCategory.equals("High Risk Order"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Internet and Advanced Networks") && Category.equals("Escalation - Fraud") && SubCategory.equals("PIN/Password Reset"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(2));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Internet and Advanced Networks") && Category.equals("Fraud Investigation") && SubCategory.equals("Report Risk"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Internet of Things") && Category.equals("Escalation - Fraud") && SubCategory.equals("High Risk Order"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(3));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Internet of Things") && Category.equals("Escalation - Fraud") && SubCategory.equals("PIN/Password Reset"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(3));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(2));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Internet of Things") && Category.equals("Fraud Investigation") && SubCategory.equals("Report Risk"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(3));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Voice and Collaboration") && Category.equals("Escalation - Fraud") && SubCategory.equals("High Risk Order"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(4));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Voice and Collaboration") && Category.equals("Escalation - Fraud") && SubCategory.equals("PIN/Password Reset"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(4));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(2));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Voice and Collaboration") && Category.equals("Fraud Investigation") && SubCategory.equals("Report Risk"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(4));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Wireless") && Category.equals("Escalation - Fraud") && SubCategory.equals("Multiple HUP"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Wireless") && Category.equals("Escalation - Fraud") && SubCategory.equals("High Risk Order"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(2));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Wireless") && Category.equals("Escalation - Fraud") && SubCategory.equals("PIN/Password Reset"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(3));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Wireless") && Category.equals("Escalation - Fraud") && SubCategory.equals("High Risk SIM Swap"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(4));
				sf.seleU.hardwait(3000);				
			}
			
			if(ProductFamily.equals("Wireless") && Category.equals("Fraud Investigation") && SubCategory.equals("Report Risk"))
			{
				// select product family
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
				sf.seleU.hardwait(3000);
				
				// select category
				sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(2));
				sf.seleU.hardwait(3000);
				
				// select sub category
				sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
				sf.seleU.hardwait(3000);				
			}
				
			
//            boolean isProductFamilyWireless= sf.seleU.isElementPresent(By.xpath("//input[@data-value='Wireless']"));
//			
//			if(isProductFamilyWireless)
//			{
//				reportStatusPass(methodName + " Selected Product Family ", true, false);
//				sf.seleU.wait(3000);
//			}
//			else
//			{
//			sf.seleU.clickOnElement(sf.caseDetails.productFamilyInputBox.get(1));	
//						
//			//verify product families are displayed for internal guided case			
//			boolean isProductFamiliesDisplayed = true;
//			for (int i=1; i<= (sf.caseDetails.productFamilyOptions.size()-5); i++) 
//			{				
//				if(InputData_Service.productFamilyOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.productFamilyOptions.get(i))))
//				{
//					isProductFamiliesDisplayed= true;
//				}
//				else 
//				{
//					isProductFamiliesDisplayed= false;
//					break;
//				}				
//			}
//		
//			if(isProductFamiliesDisplayed) 
//			{
//			  reportStatusPass(methodName + " Verified product families options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "product families options not displayed", true);				
//			}
//			
//			sf.seleU.clickOnElement(sf.caseDetails.productFamilyOptions.get(5));
//			reportStatusPass(methodName + " Selected Product Family ", true, false);
//			sf.seleU.hardwait(4000);
//			}
//			
//			//select category		
//			sf.seleU.clickOnElement(sf.caseDetails.categoryInputBox.get(1));			
//			boolean isCategoryOptionsDisplayed= true;			
//			for(int i=1; i<=(sf.caseDetails.categoryOptions.size()-2); i++)
//			{				
//				if(InputData_Service.categoryOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.categoryOptions.get(i))))
//				{
//					isCategoryOptionsDisplayed= true;
//				}
//				else 
//				{
//				    isCategoryOptionsDisplayed= false;
//				    break;
//				}
//			}
//
//			if(isCategoryOptionsDisplayed) 
//			{			
//			  reportStatusPass(methodName + " Verified Category options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "Category options not displayed", true);	
//			}
//			
//			sf.seleU.clickOnElement(sf.caseDetails.categoryOptions.get(1));
//			reportStatusPass(methodName + " Selected Category ", true, false);
//			sf.seleU.hardwait(4000);
//			
//			//select Sub Category
//			sf.seleU.clickOnElement(sf.caseDetails.subcategoryInputBox.get(1));			
//			boolean isSubCategoryOptionsDisplayed= true;			
//			for(int i=1; i<=(sf.caseDetails.subCategoryOptions.size()-1); i++)
//			{				
//				if(InputData_Service.subCategoryOptions.contains(sf.seleU.getTextFromWebElement(sf.caseDetails.subCategoryOptions.get(i))))
//				{
//					isSubCategoryOptionsDisplayed= true;
//				}
//				else 
//				{
//				    isSubCategoryOptionsDisplayed= false;
//				    break;
//				}
//			}
//
//			if(isSubCategoryOptionsDisplayed)
//			{
//			  reportStatusPass(methodName + " Verified Sub Category options displayed", true, true);
//			}
//			
//			else
//			{
//			  reportStatusFail(methodName + "Sub Category options not displayed", true);	
//			}
//			
//			sf.seleU.clickOnElement(sf.caseDetails.subCategoryOptions.get(1));
//			reportStatusPass(methodName + " Selected Sub Category ", true, false);
//			sf.seleU.hardwait(4000);			
								
			if (sf.seleU.isElementDisplayed(sf.caseDetails.toolTip.get(0)))
			{
				reportStatusPass(methodName + " Selected Sub Category Tooltip is displayed ", true, false);
			}
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);			
		}
		catch(Throwable e) {
			reportStatusFail(" Error in filling case details ", e);
			e.printStackTrace();
		}
	}

						
	public void enterCaseRelatedDetails(boolean isBusinessAccountPopulated, boolean isBillingAccountPopulated, boolean isContactPopulated) throws IOException{
	  try {		
			//enter business account
		    if(!isBusinessAccountPopulated)
		    {
		    sf.seleU.hardwait(4000);
//		    sf.seleU.enterText(sf.caseRelatedDetails.accountSearch.get(0), Global.dataInput.businessAccountName);
		    sf.seleU.enterText(sf.caseRelatedDetails.accountSearch.get(0), InputData_Service.businessAccountForInternalGuidedCase);
		    sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.caseRelatedDetails.accountSearch.get(0), Keys.ARROW_DOWN);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.caseRelatedDetails.accountSearch.get(0), Keys.ENTER);
			sf.seleU.hardwait(5000);
//			reportStatusPass(methodName + " Entered business account as: " + Global.dataInput.businessAccountName, true, false);
			reportStatusPass(methodName + " Entered business account as: " + InputData_Service.businessAccountForInternalGuidedCase, true, false);
			sf.seleU.wait(4000);
		    }
			
			//enter billing account only if created from customer case
			if(!isBillingAccountPopulated)
			{	
//			sf.seleU.enterText(sf.caseRelatedDetails.searchBillingAccount.get(0), Global.dataInput.billingAccountName);
			sf.seleU.enterText(sf.caseRelatedDetails.searchBillingAccount.get(0), InputData_Service.billingAccountForInternalGuidedCase);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.caseRelatedDetails.searchBillingAccount.get(0), Keys.ARROW_DOWN);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.caseRelatedDetails.searchBillingAccount.get(0), Keys.ENTER);
			sf.seleU.hardwait(5000);
//			reportStatusPass(methodName + " Entered billing account as: " + Global.dataInput.billingAccountName, true, false);
			reportStatusPass(methodName + " Entered billing account as: " + InputData_Service.billingAccountForInternalGuidedCase, true, false);
			sf.seleU.hardwait(4000);
			}
			
			//enter contact
			if(!isContactPopulated)
			{
//			sf.seleU.enterText(sf.caseRelatedDetails.searchContact.get(0), (Global.dataInput.contactFirstName + " " + Global.dataInput.contactLastName));
			sf.seleU.enterText(sf.caseRelatedDetails.searchContact.get(0), InputData_Service.contactForInternalGuidedCase);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.caseRelatedDetails.searchContact.get(0), Keys.ARROW_DOWN);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.caseRelatedDetails.searchContact.get(0), Keys.ENTER);
			sf.seleU.hardwait(5000);
//			reportStatusPass(methodName + " Entered contact as: " + (Global.dataInput.contactFirstName + " " + Global.dataInput.contactLastName), true, false);
			reportStatusPass(methodName + " Entered contact as: " + InputData_Service.contactForInternalGuidedCase, true, false);
			sf.seleU.hardwait(4000);
			}
			
			//click on next button
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(5000);
			
			// Verify Internal case is created
			sf.seleU.implicitlyWait(7);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.closeButton);
			reportStatusPass(methodName + " Verified Internal case is created ", true, true);
			sf.seleU.hardwait(5000);
		}
		catch(Throwable e) {
			reportStatusFail(methodName + " Error in filling case related details ", e);
			e.printStackTrace();
		}
	}
		
	public void validateInternalGuidedCase() throws IOException{
		try {
			
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(10000);
			sf.seleU.ScrolltoElement(sf.caseRelatedDetails.emailsIcon.get(1));
			sf.seleU.hardwait(10000);			
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.relatedCasesIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.fraudCaseNumberLink);
			sf.seleU.hardwait(3000);
			
			//MPOSS-61106 validate flex card is displayed on fraud case
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.flexCardValues), "Error in validating flex card values");
			sf.seleU.hardwait(3000);			
			InputData.fraudCaseNumber=sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.caseNumberField);
			verifyFieldValue("Case Owner", sf.caseRelatedDetails.fraudRiskCaseOwner, InputData.fraudRiskSelection);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Status", sf.caseRelatedDetails.caseStatus, InputData.caseStatusNew);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Priority", sf.caseRelatedDetails.priorityField, InputData.casePriorityMedium);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Parent Case", sf.caseRelatedDetails.parentCaseField, InputData.caseNumber);
			sf.seleU.hardwait(3000);
		}			
			catch(Throwable e) {
				reportStatusFail(" Error validating internal guided case ", e);
				e.printStackTrace();
			}
		}
	
	public void validateUserAbleToSeeFlexCardDetails() throws IOException{
		try {
			
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(10000);
			sf.seleU.ScrolltoElement(sf.caseRelatedDetails.emailsIcon.get(1));
			sf.seleU.hardwait(10000);			
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.relatedCasesIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.fraudCaseNumberLink);
			sf.seleU.hardwait(3000);
			
			//MPOSS-61106 validate flex card is displayed on fraud case
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.flexCardValues), "Error in validating flex card values");
			sf.seleU.hardwait(3000);			
		}			
			catch(Throwable e) {
				reportStatusFail(" Error validating flex card details ", e);
				e.printStackTrace();
			}
		}
	
	public void validateETMCaseDetails(String ETML1, String ETML2, String ETML3, String ETML4, String communicationMethod) throws IOException
	{
		try
		{
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(5000);
			
			//click on external tickets
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIcon);
			sf.seleU.hardwait(4000);
			
			//click on external ticket number
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketNumber);
			sf.seleU.hardwait(4000);
			
			//validate parent case number & ticket status
			verifyFieldValue("Case", sf.caseRelatedDetails.parentCaseNumber, InputData.caseNumber);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Status", sf.caseRelatedDetails.status, "New");
			sf.seleU.hardwait(4000);
			
			//validate Flex Attributes field displayed
			if((ETML1.equals("Account Issues (Rogers)") && ETML2.equals("Care Issues") && ETML3.equals("Cancellation") && ETML4.equals("N/A") && communicationMethod.equals("Email"))||
			   (ETML1.equals("Account Issues (Rogers)") && ETML2.equals("Care Issues") && ETML3.equals("Account Not In ICM") && ETML4.equals("ICM/Maestro") && communicationMethod.equals("Email")))				
			{
				if(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.flexAttributesField))
				{
					reportStatusPass(methodName + " Verified ETM case is created ", true, true);
				}
				else
				{
					reportStatusFail(methodName + "Error in validating ETM Case", true);
				}
			}				
		}
		catch(Throwable e)
		{
			reportStatusFail(" Error validating ETM case ", e);
			e.printStackTrace();
		}		
	}
	
	public void validateETMCaseDetails() throws IOException
	{
		try
		{
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(5000);
			
			//click on external tickets
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIcon);
			sf.seleU.hardwait(4000);
			
			//click on external ticket number
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketNumber);
			sf.seleU.hardwait(8000);
			
			//capture ETM number
			InputData.etmCaseNumber = sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.etmCaseNumber);
			
			//validate different fields
			verifyFieldValue("Case", sf.caseRelatedDetails.parentCaseNumber, InputData.caseNumber);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Status", sf.caseRelatedDetails.status, "In Progress");
			sf.seleU.hardwait(4000);
			verifyFieldHasValue("Type 1", sf.caseRelatedDetails.type1Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 2", sf.caseRelatedDetails.type2Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 3", sf.caseRelatedDetails.type3Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 4", sf.caseRelatedDetails.type4Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 5", sf.caseRelatedDetails.type5Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Flex Attributes", sf.caseRelatedDetails.flexAttributesField);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Record Type", sf.caseRelatedDetails.recordTypeField, "ETM");
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Preferred Communication Method", sf.caseRelatedDetails.communicationMethodField);
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Preferred Communication Channel", sf.caseRelatedDetails.communicationChannelField);
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Product Line", sf.caseRelatedDetails.productLineField);
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Preferred Communication Time", sf.caseRelatedDetails.communicationTimeField);
            sf.seleU.hardwait(3000);
            sf.seleU.scrollUpByCoOrdinates();
            sf.seleU.hardwait(4000);
		}
		catch(Throwable e)
		{
			reportStatusFail(" Error validating ETM case ", e);
			e.printStackTrace();
		}
	}
	
	public void validateETMCaseDetailsL5Truck() throws IOException
	{
		try
		{
			//click on related tab
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
			sf.seleU.hardwait(5000);
			
			//click on external tickets
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIconNew);
			sf.seleU.hardwait(4000);
			
			//click on external ticket number
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketNumber);
			sf.seleU.hardwait(8000);
			
			//capture ETM number
			InputData.etmCaseNumber = sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.etmCaseNumber);
			
			//validate different fields
			verifyFieldValue("Case", sf.caseRelatedDetails.parentCaseNumber, InputData.caseNumber);
			sf.seleU.hardwait(4000);
			verifyFieldValue("Status", sf.caseRelatedDetails.status, "In Progress");
			sf.seleU.hardwait(4000);
			verifyFieldHasValue("Type 1", sf.caseRelatedDetails.type1Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 2", sf.caseRelatedDetails.type2Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 3", sf.caseRelatedDetails.type3Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 4", sf.caseRelatedDetails.type4Field);
			sf.seleU.hardwait(3000);
			verifyFieldHasValue("Type 5", sf.caseRelatedDetails.type5Field);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Record Type", sf.caseRelatedDetails.recordTypeField, "ETM");
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Preferred Communication Method", sf.caseRelatedDetails.communicationMethodField);
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Preferred Communication Channel", sf.caseRelatedDetails.communicationChannelField);
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Product Line", sf.caseRelatedDetails.productLineField);
            sf.seleU.hardwait(3000);
            verifyFieldHasValue("Preferred Communication Time", sf.caseRelatedDetails.communicationTimeField);
            sf.seleU.hardwait(3000);
            sf.seleU.scrollUpByCoOrdinates();
            sf.seleU.hardwait(4000);
		}
		catch(Throwable e)
		{
			reportStatusFail(" Error validating ETM case ", e);
			e.printStackTrace();
		}
	}

	
	public void validateETMCaseCanBeUpdated() throws IOException
	{
		try
		{
			//click on update Tab and enter Additional Notes
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.updateButton);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.additionalNotesRadioButton);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseRelatedDetails.additionalNotesInputBox, InputData.etmCaseAdditionalNotesText);			
			sf.seleU.enterText(sf.caseRelatedDetails.additionalNotesInputBox, Keys.TAB);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + " Entered Additional Notes ", true, false);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.ticketUpdateMessageConfirmation.get(0)), "Additional Notes has not been updated");
			
//			if(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.ticketUpdateMessageConfirmation.get(0)))
//			{
//				reportStatusPass(methodName + " Additional Notes has been updated ", true, true);
//			}
//			else
//			{
//				reportStatusFail(methodName + " Error in updating additional notes ", true);
//			}
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.buttonClose.get(4));
			sf.seleU.hardwait(3000);
			
			//validate additional notes can be checked under case comments tab on parent case
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.parentCaseNumber);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.commentsTab);
			sf.seleU.hardwait(4000);
			if(sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.caseCommentsText).contains(InputData.etmCaseNumber)
			&& sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.caseCommentsText).contains(InputData.etmCaseAdditionalNotesText))
			{
				reportStatusPass(methodName + " ETM Case Number and Additional Notes are present under Case Comments Tab ", true, true);
			}
			else
			{
				reportStatusFail(methodName + " Error in validating ETM Case can be updated ", true);
			}
			
			sf.seleU.hardwait(4000);
		}			
		catch(Throwable e)
		{
			reportStatusFail(" Error in validating ETM Case can be updated ", e);
			e.printStackTrace();
		}
	}
	
	public void validateETMCaseCanBeCanceled() throws IOException
	{
		try
		{
			//click on external tickets
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIcon);
			sf.seleU.hardwait(4000);
			
			//click on external ticket number
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketNumber);
			sf.seleU.hardwait(4000);

			//click on update Tab and validate case can be canceled
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.updateButton);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.cancelTicketRadioButton);
			sf.seleU.hardwait(3000);
			if(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.cancelTicketConfirmMessage))
			{
				reportStatusPass(methodName + "Cancel confirmation message with Yes & No options are displayed", true, true);
			}
			else
			{
				reportStatusFail(methodName + "Cancel confirmation message is not displayed", true);
			}
			sf.seleU.hardwait(3000);
			
			//validate whether after clicking no radio button, it comes to pop up
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.noRadioButton);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			if(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.updateButton))
			{
				reportStatusPass(methodName + "Selecting No option bringing back to Case details page", true, true);
			}
			else
			{
				reportStatusFail(methodName + "No button selection is not working as expected", true);
			}
			sf.seleU.hardwait(3000);
			
			//validate whether after clicking yes radio button, case can be canceled
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.updateButton);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.cancelTicketRadioButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.yesRadioButton);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.nextButton);
			sf.seleU.hardwait(4000);
			
			softassert.assertTrue(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.ticketUpdateMessageConfirmation.get(1)), "Error in Canceling the Ticket");
			
//			if(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.ticketUpdateMessageConfirmation.get(1)))
//			{
//				reportStatusPass(methodName + " Additional Notes has been updated ", true, true);
//			}
//			else
//			{
//				reportStatusFail(methodName + " Error in updating additional notes ", true);
//			}
//			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.buttonClose.get(4));
			sf.seleU.hardwait(3000);
			verifyFieldValue("Status", sf.caseRelatedDetails.status, "Cancelled");
			sf.seleU.hardwait(4000);
			
			//validate whether when trying to cancel a canceled ticket, displaying the message that ticket is already canceled
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.updateButton);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.cancelTicketRadioButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.yesRadioButton);
			sf.seleU.hardwait(2000);
            if(sf.seleU.isElementDisplayed(sf.caseRelatedDetails.alreadyCanceledMessage))
            {
               reportStatusPass(methodName + "Mesaage Displayed that ticket is already canceled ", true, true);
            }
			else
			{
				reportStatusFail(methodName + "Error in displaying message that ticket is already canceled", true);
			}
			sf.seleU.hardwait(3000);
		}			
		catch(Throwable e)
		{
			reportStatusFail(" Error in validating ETM Case can be updated ", e);
			e.printStackTrace();
		}
	}
	
	public void editCommentsField() throws IOException
	{
		try
		{
			//add comments
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.commentsTab);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.newTab);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseRelatedDetails.inputBox, AdditionalUtilities.generateRandomCharacters(5));
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseRelatedDetails.inputBox, Keys.TAB);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.saveButton);
			sf.seleU.hardwait(3000);
			InputData_Service.commentsText = sf.seleU.getTextFromWebElement(sf.caseRelatedDetails.caseCommentsText);
            sf.seleU.hardwait(3000);
		}
		catch (Throwable e) 
		{
			reportStatusFail(" Error in editing Comments", e);
			e.printStackTrace();
		}
	}
	
	public void validateCaseOwnerNotified() throws IOException
	{
		try
		{
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.notificationsButton);
			sf.seleU.hardwait(3000);
			softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.caseDetails.notificationsCommentsText), InputData_Service.commentsText, "Error in validating comments");
			sf.seleU.hardwait(4000);
			
			//close the dialog box
			sf.seleU.clickElementByJSE(sf.caseDetails.closeNotificationsIcon);
			sf.seleU.hardwait(4000);			
		}
		catch (Throwable e) 
		{
			reportStatusFail(" Error in validation", e);
			e.printStackTrace();
		}
	}
			
		public void searchCaseFromGlobalSearch(String guidedCase) throws IOException{
			try {
					
			//select Navigation menu
			sf.seleU.hardwait(5000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			
			// Select Cases
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.casesMenu);
			reportStatusPass(methodName + " Selected Cases from menu", true, false);
			sf.seleU.hardwait(4000);
			
			//enter case to be searched in global search
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(4000);
			
			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, guidedCase);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
			}

			else { // after click if search more .. is displayed then perform else part
				sf.seleU.hardwait(4000);
				sf.seleU.enterText(sf.cases.globalCasesAndMoreSearch, guidedCase);
				sf.seleU.hardwait(5000);
				sf.seleU.enterText(sf.cases.globalCasesAndMoreSearch, Keys.ENTER);
			}
			
			sf.seleU.hardwait(5000);
			sf.seleU.scrollByCoOrdinates(2);
									
			boolean isCaseFound = false;		
			if (sf.cases.caseLinkGlobalSearchResult.size() == 1) {
			sf.seleU.clickElementByJSE(sf.cases.caseLinkGlobalSearchResult.get(0));
			reportStatusPass(methodName + " Found and Clicked on Case " + guidedCase, true, false);
			sf.seleU.wait(4000);
			isCaseFound = true;
			}
			if (!isCaseFound) {
				reportStatusFail(methodName + " No such Case found as:  " + guidedCase, true);
			}
			}
			catch (Throwable e) {
				reportStatusFail(" Selecting a case is Unsuccessful", e);
				e.printStackTrace();
			}
		}
		
		public void validateETMTicketDisplayed() throws IOException
		{
			try
			{
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTabNew);
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIconNew);				
				sf.seleU.hardwait(4000);
				softassert.assertEquals(sf.seleU.getElementAttribute(sf.caseRelatedDetails.externalTicketNumber, "title"), InputData.etmCaseNumber, "Error in displaying ETM Ticket");
                sf.seleU.hardwait(3000);			
			}
			catch(Throwable e)
			{
				reportStatusFail("Error in Displaying ETM Ticket", e);
				e.printStackTrace();
			}
		}
		
		public void validateCaseOriginWeb() throws IOException
		{
			try
			{
				String methodName = "SFDC_New Cases@: ";
				
				softassert.assertEquals(sf.seleU.getTextFromWebElement(sf.caseDetails.caseOriginFieldValueText), InputData.caseOriginWeb, "Error in validating case origin");
				reportStatusPass(methodName + " Validated Case Origin as " + InputData.caseOriginWeb, true, false);
				sf.seleU.hardwait(3000);
				softassert.assertEquals(sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseRecordType, "Error in validating case record type");
				reportStatusPass(methodName + " Validated Case record type as " + InputData.caseRecordType, true, false);
				sf.seleU.hardwait(3000);
			}
			catch (Throwable e) 
			{
				reportStatusFail(" Error in Validating Case Origin as Web ", e);
				e.printStackTrace();
			}
		}
		
		public void changeETMStatus() throws IOException
		{
			try
			{
				//click on related tab
				sf.seleU.hardwait(5000);
				sf.seleU.clickElementByJSE(sf.caseRelated.caseRelatedTab);
				sf.seleU.hardwait(5000);
				
				//click on external tickets
				sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketsIconNew);				
				sf.seleU.hardwait(4000);
				
				//click on external ticket number
				sf.seleU.clickElementByJSE(sf.caseRelatedDetails.externalTicketNumber);
				sf.seleU.hardwait(8000);
				
				//change status to closed
				sf.seleU.clickElementByJSE(sf.caseRelatedDetails.statusDropdownBox);
				sf.seleU.hardwait(3000);				
				sf.seleU.clickElementByJSE(sf.caseRelatedDetails.statusDropdown);
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseRelatedDetails.closedStatus);
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
				sf.seleU.hardwait(3000);	
			}
			catch (Throwable e) 
			{
				reportStatusFail(" Error in changing case status ", e);
				e.printStackTrace();
			}
		}
		
		public void validateRelationshipScore() throws IOException
		{
			try {
				//validate Relationship Score is displayed
				sf.seleU.clickElementByJSE(sf.caseDetails.businessAccountLink);
				sf.seleU.hardwait(3000);
				if(sf.seleU.isElementDisplayed(sf.caseDetails.relationshipScoreField)
				&& sf.seleU.isElementDisplayed(sf.caseDetails.relationshipScoreValue))
				{
				   reportStatusPass(methodName + " Relationship Score is Displayed ", true, true);	
				}
				else
				{
					reportStatusFail(methodName + " Error in validating Relationship Score ", true);
				}
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseDetails.closeButton);
				sf.seleU.hardwait(2000);
			}
			catch (Throwable e) 
			{
				reportStatusFail(" Error in validating Relationship Score ", e);
				e.printStackTrace();
			}
		}
		
		public void validateRedirectionOfBillingAccount() throws IOException
		{
			try {
				
				InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
				sf.seleU.clickElementByJSE(sf.caseDetails.billingAccountLink);
				sf.seleU.hardwait(3000);
								
				if(sf.seleU.isElementDisplayed(sf.caseDetails.billingAccountField)
				&& sf.seleU.getTextFromWebElement(sf.caseDetails.billingAccountField).equals(InputData_Service.billingAccountForInternalGuidedCase))
				{
				   reportStatusPass(methodName + " Account is Displayed ", true, true);	
				}
				else
				{
					reportStatusFail(methodName + " Error in validating account displayed ", true);
				}
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseDetails.billingAccountField);
				sf.seleU.hardwait(3000);
				softassert.assertTrue(sf.seleU.getTextFromWebElement(sf.ad.accountNameValueText).equals(InputData_Service.billingAccountForInternalGuidedCase), " Error in validating billing account name ");
				sf.seleU.hardwait(3000);				
			}
			catch (Throwable e) 
			{
				reportStatusFail(" Error in validating Relationship Score ", e);
				e.printStackTrace();
			}
		}
		
		public void validateRedirectionOfContact() throws IOException
		{
			try {
				
				sf.seleU.clickElementByJSE(sf.caseDetails.contactLink);
				sf.seleU.hardwait(3000);
								
				if(sf.seleU.isElementDisplayed(sf.caseDetails.contactField)
				&& sf.seleU.getTextFromWebElement(sf.caseDetails.contactField).equals(InputData_Service.contactForInternalGuidedCase))
				{
				   reportStatusPass(methodName + " Contact is Displayed ", true, true);	
				}
				else
				{
					reportStatusFail(methodName + " Error in validating contact displayed ", true);
				}
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseDetails.contactField);
				sf.seleU.hardwait(3000);
				softassert.assertTrue(sf.seleU.getTextFromWebElement(sf.cc.contactName).equals(InputData_Service.contactForInternalGuidedCase), " Error in validating contact name ");
				sf.seleU.hardwait(3000);				
			}
			catch (Throwable e) 
			{
				reportStatusFail(" Error in validating Relationship Score ", e);
				e.printStackTrace();
			}
		}
								
	public void validate_GuidedCasewithBusinessphone() throws IOException {
		try {
			
             sf.seleU.hardwait(3000);
			 sf.seleU.switchToDefaultContent();
			 
			// Verify Case details
			verifyFieldValue("Case", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText,
					InputData.emailToCaseServiceUserName);

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);

			verifyFieldHasValue("Case Priority", sf.caseDetails.priorityFieldValueText);

			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText,
					InputData.caseRecordTypeSupport);

			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);

			verifyFieldValue("Product Family", sf.caseDetails.productFamilyFieldValueText, InputData.caseOriginPhone);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText,
					InputData.emailToCaseServiceUserName);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	// verify the TelliOffer status as NoAvaialbleOffers in only New Service Case
	// created

	public void validate_TelliOfferStatus() throws IOException {
		try {

			verifyFieldValue("Case  TelliOfferStatus", sf.caseDetails.noAvaiableOffers, InputData.noAvailableOffer);
		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	public void validate_MileStoneStatus() throws IOException {
		try {

			verifyFieldValue("Case MileStoneStatus", sf.caseDetails.MileStoneTimer, InputData.noMileStones);
		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for Proactively Created Tier 1 Tech
	 *                     Supportcase
	 */
	public void validate_Tier1ProactiveCaseDetails() throws IOException {
		try {

			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusNew);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText,
					InputData.caseTypeTechnicalSupport);

			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginPhone);
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText,
					InputData.emailToCaseServiceUserName);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Case Details while case created by communities (
	 *                     Web to Case)
	 */
	public void validateWebToCaseDetails(Hashtable<String, String> dataTable) throws IOException {
		try {

			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  Owner", sf.caseDetails.caseOwnerFieldValueText, dataTable.get("Case_Owner"));

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusNew);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);

			verifyFieldValue("Case  Contact Name", sf.caseDetails.contactNameValueText, dataTable.get("Case_Contact"));
			verifyFieldValue("Case  Contact Email", sf.caseDetails.contactEmailValueText,
					dataTable.get("Com_Login_Email_Id"));
			verifyFieldValue("Case  Account ", sf.caseDetails.accountNameValueText, dataTable.get("Account_Name"));

			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, dataTable.get("Case_Origin"));
			verifyFieldValue("Case  Record Type", sf.caseDetails.caseRecordTypeFieldValueText,
					dataTable.get("Case_RecordType"));
			verifyFieldValue("Case  Reason", sf.caseDetails.caseReasonFieldValueText, dataTable.get("Case_Reason"));

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Last Modified By", sf.caseDetails.lastModifiedByFieldValueText,
					dataTable.get("Case_Contact"));
			verifyFieldValue("Created By", sf.caseDetails.createdByFieldValueText, dataTable.get("Case_Contact"));

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Select Change Owner Button and Select owner as Default
	 *                     Queue
	 * 
	 *                     Verify updated case owner
	 */
	public void changeCaseOwnerDefaultQueue() throws IOException {
		try {

			// Select Change Owner Button and enter case owner
			sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
			reportStatusPass(methodName + "Clicked On Change Owner", true, false);
			sf.seleU.wait(4000);

			sf.seleU.clickElementByJSE(sf.caseDetails.selectChangeUser);
			reportStatusPass(methodName + "Select change User", true, false);
			sf.seleU.wait(3000);
			
			sf.seleU.clickElementByJSE(sf.caseDetails.queuesOptionForOwner);
			reportStatusPass(methodName + "Searched Default Queue in Options", true, false);
			sf.seleU.wait(3000);

			sf.seleU.enterText(sf.caseDetails.searchQueuesInput, InputData.caseOwnerDefaultQueue);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.caseDetails.searchQueuesInput, Keys.ENTER);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.resultDefaultQueue);
			
//			sf.seleU.clickElementByJSE(sf.caseDetails.searchResultDefaultQueue);
			sf.seleU.wait(3000);

			// sf.seleU.clickElementByJSE(sf.caseDetails.searchedOwnerIcon);
			reportStatusPass(methodName + "Selected the searched owner : " + InputData.caseOwnerDefaultQueue, true,
					false);			
			verifyFieldValue("Case  Owner", sf.caseDetails.updatedCaseOwnerValue, InputData.caseOwnerDefaultQueue);
			sf.seleU.wait(4000);
			
			// Submit and verify updated case owner
//			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
			verifyFieldValue("Case  Owner Updated Msg", sf.caseDetails.ownerUpdatedMsg,
					InputData.caseOwnerDefaultQueue);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Owner to Default Queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify External Ticket Count in Case generated
	 * 
	 *                     if count is true then check ticket count as 1 and verify
	 *                     values in Ticket
	 * 
	 */
	public void validate_ExternalTicket(boolean count) throws IOException {
		try {
			sf.seleU.clickElementByJSE(sf.caseDetails.moreTab);
			reportStatusPass(methodName + "Clicked On moretab", true, false);

			sf.seleU.clickElementByJSE(sf.caseDetails.externalTickets);
			reportStatusPass(methodName + "Clicked On Change Owner", true, false);

			if (!count)
				verifyFieldValue("External Ticket count  ", sf.caseDetails.tcktCount0, "0");
			else {
				verifyFieldValue("External Ticket count  ", sf.caseDetails.tcktCount, "1");
				reportStatusPass(methodName + "External Ticket is generated", true, false);
				verifyFieldValue("Service Now ", sf.caseDetails.servNow, "ServiceNow");
				reportStatusPass(methodName + "Ticket Source is ServiceNow", true, false);
				// extracting the incedent Id of Ticket
				InputData.incidentID = sf.seleU.getTextFromWebElement(sf.caseDetails.incedentNum);
				reportStatusPass(methodName + "Incedent ID " + InputData.incidentID + " is generated", true, false);

			}

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Select Change Owner Button and Select owner as Tier 2
	 *                     Wireline
	 * 
	 *                     Verify updated case owner
	 */
	public void changeCaseOwnerTier2Wireline() throws IOException {
		try {

			// Select Change Owner Button and enter case owner
			sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
			reportStatusPass(methodName + "Clicked On Change Owner", true, false);

			sf.seleU.clickElementByJSE(sf.caseDetails.selectChangeUser);
			reportStatusPass(methodName + "Select change User", true, false);

			sf.seleU.clickElementByJSE(sf.caseDetails.queuesOptionForOwner);
			reportStatusPass(methodName + "Searched Tier2Wireline Queue in Options", true, false);

			sf.seleU.enterText(sf.caseDetails.searchQueuesInput, InputData.caseOwnerTier2WirelineQueue);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.tier2WireLineDD);

			// sf.seleU.clickElementByJSE(sf.caseDetails.searchedOwnerIcon);
			reportStatusPass(methodName + "Selected the searched owner : " + InputData.caseOwnerTier2WirelineQueue,
					true, false);
			sf.seleU.wait(2000);
			verifyFieldValue("Case  Owner", sf.caseDetails.updatedCaseOwnerValue,
					InputData.caseOwnerTier2WirelineQueue);

			// Submit and verify updated case owner
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
			verifyFieldValue("Case  Owner Updated Msg", sf.caseDetails.ownerUpdatedMsg,
					InputData.caseOwnerTier2WirelineQueue);
			sf.seleU.refreshPage();

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Owner to Tier2Wireline Queue", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify user is not allowed to edit case status , priority
	 *                     and Origin, Status, priority, Type,Case Origin,Case
	 *                     Reason, Subject
	 * 
	 *                     Verify user is not allowed to 'mark status as complete '
	 *                     and change status
	 * 
	 *                     Verify user is not allowed send an email
	 * 
	 */
	public void verifyUserIsNotAllowedToEditCaseDetails(String caseOrigin) throws IOException {
		try {

			// Verify case status , priority and Origin
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, caseOrigin);

			// Verify user is not allowed to edit Status
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editStatusButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.statusEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.statusEditOptionClosed);
			sf.seleU.hardwait(3000);
			verifyPleaseTakeOwnerShipMsg("Status");

			// Verify user is not allowed to edit Priority
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionLow);
			sf.seleU.hardwait(3000);
			verifyPleaseTakeOwnerShipMsg("Priority");
			
			// Verify user is not allowed to edit Type
			// sf.seleU.clickElementByJSE(sf.caseDetails.typeEditDropdown);
			// sf.seleU.clickElementByJSE(sf.caseDetails.typeEditOptionProblem);
			// verifyPleaseTakeOwnerShipMsg("Type");

			// Verify user is not allowed to edit Case Origin
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseOriginButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseOriginEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseOriginEditOptionPhone);
			sf.seleU.hardwait(3000);
			verifyPleaseTakeOwnerShipMsg("Case Origin");

			// Verify user is not allowed to edit Case Reason
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseReasonButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditOptionBillingInquiry);
			sf.seleU.hardwait(3000);
			verifyPleaseTakeOwnerShipMsg("Case Reason");

			// Verify user is not allowed to edit Subject
//			sf.seleU.hardwait(3000);
//			sf.seleU.clickElementByJSE(sf.caseDetails.editSubjectButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.caseDetails.subjectEditInput, InputData.caseStatusNew);
			sf.seleU.hardwait(3000);
			verifyPleaseTakeOwnerShipMsg("Subject");
			sf.seleU.clickElementByJSE(sf.caseDetails.cancelButton);
			sf.seleU.wait(2000);
			
			// Verify user is not allowed to 'mark status as complete ' and change status
			sf.seleU.clickElementByJSE(sf.caseDetails.markStatusAsCompleteButton);
			verifyPleaseTakeOwnerShipMsg();
			sf.seleU.clickElementByJSE(sf.caseDetails.newTab);
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			verifyPleaseTakeOwnerShipMsg();

			// Verify user is not allowed send an email
			sf.seleU.clickElementByJSE(sf.caseDetails.emailTab);
			sf.seleU.clickElementByJSE(sf.caseDetails.composeEmailButton);
			sf.seleU.switchToFrame(sf.caseRelated.emailEditorFrame);
			sf.seleU.switchToFrame(sf.caseRelated.emailBodyFrame);
			sf.seleU.clickElementByJSE(sf.caseRelated.emailBodyArea);
			sf.seleU.clearAndEnterText(sf.caseRelated.emailBodyArea, InputData.replyEmailBody);
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.caseRelated.emailSendButton);
			sf.seleU.hardwait(2000);

			// Verify 'Please take case ownership before sending email to the customer'
			if (sf.seleU.isElementDisplayed(sf.caseDetails.emailSendReviewErrorMsg)) {
				sf.seleU.ScrolltoElement(sf.caseDetails.emailSendReviewErrorMsg);
				reportStatusPass(methodName
						+ "Verified Error Msg as ''Please take case ownership before sending email to the customer''  for sending email without ownership",
						true, true);
			} else {
				reportStatusFail(methodName + " Error in verifying user sending email without ownership", true);
			}
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Verification for fields values not allowed for Edit", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Select Change Owner Button and enter case owner , Submit
	 *                     and verify updated case owner
	 */
	public void changeCaseOwner() throws IOException {
		try {

			// Select Change Owner Button and enter case owner
			sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
			sf.seleU.enterText(sf.caseDetails.searchPeopleInput, InputData.emailToCaseServiceUserName);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseDetails.searchPeopleInput, Keys.ARROW_DOWN);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseDetails.searchPeopleInput, Keys.ENTER);
			sf.seleU.hardwait(3000);
//			if (sf.seleU.isElementDisplayed(sf.caseDetails.searchedOwnerName)) {
//				sf.seleU.clickElementByJSE(sf.caseDetails.searchedOwnerName);
//			}
//			sf.seleU.clickOnElement(By.xpath("//div[@title='" + InputData.emailToCaseServiceUserName + "']//ancestor::a"));
//			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Owner", sf.caseDetails.updatedCaseOwnerValue, InputData.emailToCaseServiceUserName);

			// Submit and verify updated case owner
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Owner Updated Msg", sf.caseDetails.ownerUpdatedMsg,
					InputData.emailToCaseServiceUserName);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Owner", e);
			e.printStackTrace();
		}
	}
	
	public void changeCaseOwner(String internalGuidedCaseUsername) throws IOException {
		try {

			// Select Change Owner Button and enter case owner
			sf.seleU.hardwait(4000);						
			sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseDetails.searchPeopleInput, internalGuidedCaseUsername);
			sf.seleU.hardwait(4000);
			sf.seleU.enterText(sf.caseDetails.searchPeopleInput, Keys.ARROW_DOWN);
			sf.seleU.enterText(sf.caseDetails.searchPeopleInput, Keys.ENTER);
			sf.seleU.hardwait(4000);
			if (sf.seleU.isElementDisplayed(sf.caseDetails.searchedOwnerName)) {
				sf.seleU.clickElementByJSE(sf.caseDetails.searchedOwnerName);
			}
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Owner", sf.caseDetails.updatedCaseOwnerValue, internalGuidedCaseUsername);

			// Submit and verify updated case owner
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
			sf.seleU.hardwait(2000);
			verifyFieldValue("Case  Owner Updated Msg", sf.caseDetails.ownerUpdatedMsg,
					internalGuidedCaseUsername);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Owner", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Select Change Owner Button and enter case owner , Submit
	 *                     and verify updated case owner
	 */
	public void changeCaseOwnerToUser() throws IOException {
		try {

			// Select Change Owner Button and enter case owner
			sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Clicked On Change Owner", true, false);

			sf.seleU.clickElementByJSE(sf.caseDetails.selectChangeUser);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Selected change User", true, false);

			sf.seleU.clickElementByJSE(sf.caseDetails.searcResultUsersOption);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.caseDetails.searchUsersInput, InputData.emailToCaseServiceUserName);
			sf.seleU.wait(2000);
			sf.seleU.enterText(sf.caseDetails.searchUsersInput, Keys.ARROW_DOWN);
			sf.seleU.enterText(sf.caseDetails.searchUsersInput, Keys.ENTER);
			// sf.seleU.clickElementByJSE(sf.caseDetails.searchedOwnerIcon);
			sf.seleU.wait(2000);
			verifyFieldValue("Case  Owner", sf.caseDetails.updatedCaseOwnerValue, InputData.emailToCaseServiceUserName);

			// Submit and verify updated case owner
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
			verifyFieldValue("Case  Owner Updated Msg", sf.caseDetails.ownerUpdatedMsg,
					InputData.emailToCaseServiceUserName);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Owner", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify user is allowed to edit case status , priority and
	 *                     Origin, Status, priority, Type,Case Origin,Case Reason,
	 *                     Subject, Description
	 * 
	 *                     Verify user is allowed to 'mark status as complete ' and
	 *                     change status
	 * 
	 *                     Verify user is allowed send an email
	 */
	public void verifyUserIsAllowedToEditCaseDetails() throws IOException {
		try {

			// Verify user is allowed to edit Status
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editStatusButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.statusEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.statusEditOptionInProgress);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);

			// Verify user is allowed to edit Priority
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionMedium);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);

			// Verify user is allowed to edit Type
			// sf.seleU.clickElementByJSE(sf.caseDetails.editTypeButton);
			// sf.seleU.clickElementByJSE(sf.caseDetails.typeEditDropdown);
			// sf.seleU.clickElementByJSE(sf.caseDetails.typeEditOptionProblem);
			// sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			// verifyFieldValue("Case Type", sf.caseDetails.TypeFieldValueText,
			// sf.dataInput.caseTypeProblem);

			// Verify user is allowed to edit Case Origin
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseOriginButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseOriginEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseOriginEditOptionEmail);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Origin", sf.caseDetails.caseOriginFieldValueText, InputData.caseOriginEmail);

			// Verify user is allowed to edit Case Reason
			sf.seleU.hardwait(3000);
			if(sf.seleU.isElementDisplayed(sf.caseDetails.editCaseReasonButton)) 
			{
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseReasonButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditOptionBillingInquiry);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Reason", sf.caseDetails.caseReasonFieldValueText,
					InputData_Communities.caseReasonBillingInquiry); 
			}

			// Verify user is allowed to edit Subject
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editSubjectButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.caseDetails.subjectEditInput, InputData_Communities.caseReasonBillingInquiry);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseDetails.subjectEditInput, Keys.TAB);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText,
					InputData_Communities.caseReasonBillingInquiry);

			// Verify user is allowed to edit Description
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editDescriptionButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clearAndEnterText(sf.caseDetails.descriptionEditInput, InputData_Communities.caseReasonBillingInquiry);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.caseDetails.descriptionEditInput, Keys.TAB);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData_Communities.caseReasonBillingInquiry);

			// Verify user is allowed to 'mark status as complete ' and change status
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.inProgressTab);
			sf.seleU.clickElementByJSE(sf.caseDetails.markStatusAsCompleteButton);
			verifyFieldDisplayed("Case Status Changed Successfully", sf.caseDetails.statusChangedSuccessfullyMsg);
			sf.seleU.clickElementByJSE(sf.caseDetails.newTab);
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			verifyFieldDisplayed("Case Status Changed Successfully", sf.caseDetails.statusChangedSuccessfullyMsg);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusNew);

			// Verify user is allowed send an email
			sf.seleU.clickElementByJSE(sf.caseDetails.emailTab);
			sf.seleU.clickElementByJSE(sf.caseDetails.composeEmailButton);
			sf.seleU.switchToFrame(sf.caseRelated.emailEditorFrame);
			sf.seleU.switchToFrame(sf.caseRelated.emailBodyFrame);
			sf.seleU.clickElementByJSE(sf.caseRelated.emailBodyArea);
			sf.seleU.clearAndEnterText(sf.caseRelated.emailBodyArea, InputData.replyEmailBody);
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.caseRelated.emailSendButton);
			verifyFieldDisplayed("Email Sent Msg", sf.caseRelated.emailSentMessage);

			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Verification for fields values  allowed for Edit", e);
			e.printStackTrace();
		}
	}
	
	public void verifyUserIsAllowedToEditInternalGuidedCaseDetails() throws IOException {
		try {
            			
			// Verify user is allowed to edit Status
			sf.seleU.clickElementByJSE(sf.caseDetails.editStatusButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.statusEditDropdown);
			sf.seleU.clickElementByJSE(sf.caseDetails.statusEditOptionInProgress);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusInProgress);

			// Verify user is allowed to edit Priority
			sf.seleU.clickElementByJSE(sf.caseDetails.editPriorityButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditDropdown);
			sf.seleU.clickElementByJSE(sf.caseDetails.priorityEditOptionMedium);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			verifyFieldValue("Case  Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);

			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Verification for fields values  allowed for Edit", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on compose email and select email body area
	 * 
	 *                     Attach Quick Text in Email body
	 * 
	 *                     Verify quick text content in email body
	 */
	public void EmailUsingQuickText() throws IOException {
		try {

			InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);

			// Click on compose email and select email body area
			sf.seleU.clickElementByJSE(sf.caseDetails.emailTab);
			sf.seleU.clickElementByJSE(sf.caseDetails.composeEmailButton);
			sf.seleU.switchToFrame(sf.caseRelated.emailEditorFrame);
			sf.seleU.switchToFrame(sf.caseRelated.emailBodyFrame);
			sf.seleU.clickElementByJSE(sf.caseRelated.emailBodyArea);

			// Attach Quick Text in Email body
			sf.seleU.clearAndEnterText(sf.caseRelated.emailBodyArea, (Keys.chord(Keys.CONTROL, ".")));
			sf.seleU.hardwait(2000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.caseRelated.allQuickTextNames);
			sf.seleU.enterText(sf.caseRelated.searchQuickTextInput, InputData.quickTextName);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.caseRelated.allQuickTextNames.get(0));

			// Verify quick text content in email body
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.caseRelated.emailEditorFrame);
			sf.seleU.switchToFrame(sf.caseRelated.emailBodyFrame);

			verifyFieldValue("Quick Text Msg", sf.caseRelated.emailBodyArea,
					InputData.quickTextName + " " + InputData.caseNumber);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in emailing using Quick Text", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param fieldName
	 * @throws IOException
	 * 
	 *                     Click on Save Button
	 * 
	 *                     Verify 'Please take ownership message'
	 * 
	 *                     Click on undo button
	 * 
	 */
	public void verifyPleaseTakeOwnerShipMsg(String fieldName) throws IOException {
		try {
			// Click on Save Button
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);

			sf.seleU.wait(5000);

			// Verify 'Please take ownership message'
			if (sf.seleU.isElementDisplayed(sf.caseDetails.pleaseTakeOwnershipMsg)) {
				reportStatusPass(methodName + "Validated Error message 'Please take ownership' while editing the field "
						+ fieldName + " without ownership", true, true);

				sf.seleU.clickElementByJSE(sf.caseDetails.closeErrorDialogueButton);

				// Click on undo button
				sf.seleU.wait(3000);
				// sf.seleU.clickElementByJSE(sf.caseDetails.undoButton);

			} else {
				reportStatusFail(methodName + " Error in Verification for 'Please take ownership message' for field "
						+ fieldName, true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Verification for 'Please take ownership message'", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify 'Please take ownership message' while marking case
	 *                     status without ownership
	 */
	public void verifyPleaseTakeOwnerShipMsg() throws IOException {
		try {

			// Verify 'Please take ownership message' while marking case status without
			// ownership
			sf.seleU.wait(3000);
			if (sf.seleU.isElementDisplayed(sf.caseDetails.pleaseTakeOwnershipMsgInStatus)) {
				reportStatusPass(methodName
						+ "Validated Error message 'Please take ownership' while marking case status  without ownership",
						true, true);

				sf.seleU.clickElementByJSE(sf.caseDetails.closeErrorMsgButton);
			} else {
				reportStatusFail(methodName
						+ " Error in Verification for 'Please take ownership message' for field while marking case status  without ownership",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Verification for 'Please take ownership message'", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case customer reason
	 */
	public void verifyCaseCustomerReason() throws IOException {
		try {

			// Verify Case Reason
			verifyFieldValue("Case Customer Reason", sf.caseDetails.caseCustomerReason,
					InputData_Communities.commCaseReason);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case reason verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case customer reason
	 */
	public void verifyCaseCategory() throws IOException {
		try {

			// Verify Case Reason
			verifyFieldValue("Case Category", sf.caseDetails.caseCategory, InputData_Communities.commCaseSFCategory);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case reason verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Closed status of case
	 * 
	 *                     Select Closed Status
	 * 
	 *                     Verify status is set to close
	 * 
	 */
	public void closeCase() throws IOException {
		try {
			sf.seleU.wait(10000);
			// Change Case Reason to Others before closing a case as it is a prerequisite to
			// close a case that the case Reason should not be empty
			sf.seleU.clickElementByJSE(sf.caseDetails.detailsTab);
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseReasonButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditDropdown);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditOptionBillingInquiry);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.hardwait(3000);
			if (sf.seleU.isElementDisplayed(sf.caseDetails.pleaseTakeOwnershipMsg)) {
				
				//Take ownership to edit the case
				sf.seleU.clickElementByJSE(sf.caseDetails.changeOwnerButton);
				sf.seleU.enterText(sf.caseDetails.searchPeopleInput, InputData.emailToCaseServiceUserName);
				sf.seleU.hardwait(4000);
				sf.seleU.clickOnElement(By.xpath("//div[@title='" + InputData.emailToCaseServiceUserName + "']//ancestor::a"));
				sf.seleU.hardwait(3000);
				verifyFieldValue("Case  Owner", sf.caseDetails.updatedCaseOwnerValue, InputData.emailToCaseServiceUserName);

				// Submit and verify updated case owner
				sf.seleU.hardwait(3000);
				sf.seleU.clickElementByJSE(sf.caseDetails.submitChangeCaseOwnereButton);
				sf.seleU.hardwait(3000);
				verifyFieldValue("Case  Owner Updated Msg", sf.caseDetails.ownerUpdatedMsg,
						InputData.emailToCaseServiceUserName);

				
				sf.seleU.clickElementByJSE(sf.caseDetails.closeErrorDialogueButton);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
				sf.seleU.wait(2000);
				
			}
			verifyFieldValue("Case  Reason", sf.caseDetails.caseReasonFieldValueText,
					InputData_Communities.caseReasonBillingInquiry);

			// Click on close case button
			sf.seleU.clickElementByJSE(sf.caseDetails.closedButton);
			sf.seleU.wait(4000);

			// Select closed status
			sf.seleU.clickElementByJSE(sf.caseDetails.selectClosedStatusButton);
			sf.seleU.wait(4000);
			sf.seleU.selectTextFromDropDown(sf.caseDetails.selectClosedStatusDropdown, InputData.caseStatusClosed);
			sf.seleU.clickElementByJSE(sf.caseDetails.saveButton);
			sf.seleU.wait(10000);

			// Verify 'Case closed successfully'
			if (sf.seleU.getTextFromWebElement(sf.caseDetails.statusFieldValueText)
					.equalsIgnoreCase(InputData.caseStatusClosed)) {
				reportStatusPass(methodName + " Case " + InputData.caseNumber + " status is changed to "
						+ InputData.caseStatusClosed + " successfully", true, true);
			} else {
				reportStatusFail(methodName + " Case " + InputData.caseNumber + " could not be closed successfully",
						true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in closing case", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public boolean verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		boolean verified = false;
		try {
			sf.seleU.wait(3000);
			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + "Validated " + fieldName + " is " + expectedText, true, true);
				verified = true;
			} else {
				reportStatusFail(methodName + "Actual Value for " + fieldName + " is " + element.getText()
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
		return verified;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for Created case in communities
	 */
	public void validate_CommunitiesCaseDetails() throws IOException {
		try {

			// Verify Case details
			verifyFieldValue("Case  ", sf.caseDetails.casePrimaryFieldValueText, InputData.caseStatusNew + addOn_1);

			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusNew);

			verifyFieldValue("Case  Reason", sf.caseDetails.caseCustomerReason, InputData_Communities.recentCaseReason);

			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldDisplayed("Case  description", sf.caseDetails.descriptionFieldValueText);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for customer case.
	 * 
	 *                     New contact is populated on the case.
	 * 
	 *                     Case account, subscription, record type, product family,
	 *                     subject, description are populated on the case
	 */
	public void validate_ServiceAccountCustomerCaseDetails() throws IOException {
		try {

			sf.seleU.wait(6000);

			// Verify Contact details
			verifyFieldHasValue("Contact Name", sf.caseDetails.contactNameValueText);

			// Verify Account Name
//			if (InputData.chosenAccountType == Global.dataInput.acc_RecordType_Billing)
//				verifyFieldValue("Account Name", sf.caseDetails.billingAccountNameValueText,
//						InputData.chosenAccountNameForCustomerCase);
//			else
			verifyFieldHasValue("Account Name", sf.caseDetails.accountNameValueText);

			// Verify Case subscription has some value
			verifyFieldHasValue("Subscription Name", sf.caseDetails.subscriptionFieldValueText);

			// Verify Record Type
			verifyFieldValue("Case Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.recentCaseType);

			// Verify Product Family
			verifyFieldValue("Case Product", sf.caseDetails.caseProductFieldValueText,
					InputData.caseProductInternetAndAdvancedNetwork);

			// Verify subject and description
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for customer case.
	 * 
	 *                     New contact is populated on the case.
	 * 
	 *                     Case account, subscription, record type, product family,
	 *                     subject, description are populated on the case
	 */

	public void validate_BillingOrBusAccountCustomerCaseDetails() throws IOException {
		try {

			sf.seleU.wait(6000);
            sf.seleU.switchToDefaultContent();
            
			// Verify Contact details
//			verifyFieldHasValue("Contact Name", sf.caseDetails.contactNameValueText);

			// Verify Case account
			if (InputData.chosenAccountType == Global.dataInput.acc_RecordType_Billing)
				verifyFieldValue("Case Account Name", sf.caseDetails.billingAccountNameValueText,
						InputData.chosenAccountNameForCustomerCase);
			else
				verifyFieldHasValue("Case Account Name", sf.caseDetails.accountNameValueText);

			// Verify Case subscription has some value
			verifyFieldHasValue("Subscription Name", sf.caseDetails.subscriptionFieldValueText);

			// Verify Record Type
			verifyFieldValue("Case Record Type", sf.caseDetails.caseRecordTypeFieldValueText, 
					InputData.caseTypeTechnicalSupport);

			// Verify Product Family
			verifyFieldValue("Case Product", sf.caseDetails.caseProductFieldValueText,
					InputData_Communities.recentCaseProduct);

			// Verify subject and description
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for customer case through service
	 *                     contact.
	 * 
	 *                     Case contact, billing account, subscription, record type,
	 *                     product family, category, subject, description are
	 *                     populated on the case page
	 * 
	 */
	public void validate_ServiceContactCustomerCaseDetails() throws IOException {
		try {

			sf.seleU.wait(6000);
			// Verify Contact details
			verifyFieldValue("Contact Name", sf.caseDetails.contactNameValueText, InputData.contactFullName);

			// Verify billing account is populated
			verifyFieldHasValue("Case Billing Account Name", sf.caseDetails.billingAccountNameValueText);

			// Verify Case subscription has some value
			verifyFieldHasValue("Subscription Name", sf.caseDetails.subscriptionFieldValueText);

			// Verify Record Type
			verifyFieldValue("Case Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseTypeTechnicalSupport);

			// Verify Product Family
			verifyFieldValue("Case Product", sf.caseDetails.caseProductFieldValueText,
					InputData_Communities.recentCaseProduct);

			// Verify subject and description
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation for service account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Case details for customer case through
	 *                     subscription.
	 * 
	 *                     Case contact, account, subscription, record type, product
	 *                     family, subject, description are populated on the case
	 *                     page
	 * 
	 */
	public void validate_SubscriptionCustomerCaseDetails() throws IOException {
		try {

			sf.seleU.wait(6000);
			sf.seleU.switchToDefaultContent();
			
			// Verify Contact details
//			verifyFieldHasValue("Contact Name", sf.caseDetails.contactNameValueText);

			// Verify account name is populated
//			verifyFieldHasValue("Case Account Name", sf.caseDetails.accountNameValueText);

			// Verify Case subscription has some value
			verifyFieldHasValue("Subscription Name", sf.caseDetails.subscriptionFieldValueText);

			// Verify Record Type
			verifyFieldValue("Case Record Type", sf.caseDetails.caseRecordTypeFieldValueText, InputData.caseTypeTechnicalSupport);

			// Verify Product Family
			verifyFieldValue("Case Product", sf.caseDetails.caseProductFieldValueText,InputData_Communities.recentCaseProduct);

			// Verify subject and description
			verifyFieldValue("Case  Subject", sf.caseDetails.subjectFieldValueText, InputData.caseStatusNew + addOn_1);
			verifyFieldValue("Case  description", sf.caseDetails.descriptionFieldValueText,
					InputData.caseStatusNew + addOn_1);

		} catch (Throwable e) {
			reportStatusFail(" Unsuccessful case Validation for service account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Change case status to New and Verify error message
	 */

	public void verifyCaseStatusCannotBeChangedToNew() throws IOException {
		try {

			// Change Case Status to New
			sf.seleU.clickElementByJSE(sf.caseDetails.newTab);
			sf.seleU.wait(5000);
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			sf.seleU.wait(2000);

			verifyFieldDisplayed("Case Edit(Status Change) Error Message", sf.caseDetails.caseEditErrorMessage);
			verifyFieldDisplayed("Case Edit(Status Change) Sub Error Message",
					sf.caseDetails.notAllowedToChangeStatusText);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying case status change to New is not allowed", e);
			e.printStackTrace();
		}
	}

	/**
	 * Change case status to Active Third Party
	 * 
	 * @throws IOException
	 */
	public void changeCaseStatusToAwaitingThirdParty() throws IOException {

		try {
			sf.seleU.clickElementByJSE(sf.caseDetails.awaitingThirdPartyTab);
			sf.seleU.wait(5000);
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying case status change to Awaiting Third Party", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Change case status to Awaiting Customer Response
	 * 
	 * @throws IOException
	 */
	public void changeCaseStatusToAwaitingCustomerResponse() throws IOException 
	{
		try 
		{
			sf.seleU.clickElementByJSE(sf.caseDetails.awaitingCustomerResponseTab);
			sf.seleU.wait(5000);
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + " Case Status Changed to Awaiting Customer Response ", true, false);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(methodName + " Error in changing case status to Awaiting Customer Response ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the Active tab for status is 'AwaitingThirdParty'
	 */
	public void verifyActiveThirdPartyStatus() throws IOException {
		try {

			sf.seleU.wait(5000);

			if (sf.seleU.isElementDisplayed(sf.caseDetails.activeThirdPartyTab))
				reportStatusPass(methodName + " Active tab is found :"
						+ sf.seleU.getTextFromWebElement(sf.caseDetails.activeThirdPartyTab), true, true);
			if (sf.seleU.getTextFromWebElement(sf.caseDetails.activeThirdPartyTab)
					.equalsIgnoreCase(InputData.caseStatusAwaitingThirdParty)) {

				reportStatusPass(methodName + " Active Tab is as expected "
						+ sf.seleU.getTextFromWebElement(sf.caseDetails.activeThirdPartyTab), true, true);
			} else {
				reportStatusFail(methodName + " Active Tab is " + ""
						+ sf.seleU.getTextFromWebElement(sf.caseDetails.activeThirdPartyTab) + " But expected was "
						+ InputData.caseStatusAwaitingThirdParty, true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error in active status verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify the Active tab for status is 'AwaitingCustomerResponse'
	 */
	public void verifyAwaitingCustomerResponseStatus() throws IOException 
	{
		try {
			sf.seleU.wait(5000);			
			if (sf.seleU.isElementDisplayed(sf.caseDetails.activeAwaitingCustomerResponseTab))
			{
				reportStatusPass(methodName + " Active tab is found :"
						+ sf.seleU.getElementAttribute(sf.caseDetails.activeAwaitingCustomerResponseTab, "title"), true, true);
			}
			if (sf.seleU.getElementAttribute(sf.caseDetails.activeAwaitingCustomerResponseTab, "title")
					.equalsIgnoreCase(InputData.caseStatusAwaitingCustomerResponse)) 
			{
				reportStatusPass(methodName + " Active Tab is as expected "
						+ sf.seleU.getElementAttribute(sf.caseDetails.activeAwaitingCustomerResponseTab, "title"), true, true);
			} 
			else 
			{
				reportStatusFail(methodName + " Active Tab is " + ""
						+ sf.seleU.getElementAttribute(sf.caseDetails.activeAwaitingCustomerResponseTab, "title") + " But expected was "
						+ InputData.caseStatusAwaitingCustomerResponse, true);
			}
			
			//validate case status change message is appearing in bell notifications
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.caseDetails.notificationsButton);
			verifyFieldValue("Case  status has changed", sf.caseDetails.notificationsText, InputData.caseStatusAwaitingCustomerResponse);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + " Case Status Message is Appearing in Bell Notification ", true, true);
			
			//close the dialog box
			sf.seleU.clickElementByJSE(sf.caseDetails.closeNotificationsIcon);
			sf.seleU.hardwait(4000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Error in active status verification and bell notification ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Change case status to resolved
	 * 
	 *                     Verify the message : Error message is present: Please
	 *                     update case categorization and reason fields before
	 *                     setting the case status to resolved.
	 * 
	 *                     Verify error message Case status is not changed to
	 *                     resolved
	 */

	public void verifyCaseStatusCannotBeSetToResolved() throws IOException {
		try {

			// Select Change Owner Button and enter case owner
			sf.seleU.clickElementByJSE(sf.caseDetails.resolvedTab);
			sf.seleU.wait(5000);
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			sf.seleU.wait(2000);
			verifyFieldHasValue("Case Edit(Status Change) Error Message", sf.caseDetails.caseEditErrorMessage);
//			verifyFieldHasValue("Case Edit(Status Change) Sub Error Message",
//					sf.caseDetails.notAllowedToChangeStatusText);

			// Close error message
			sf.seleU.wait(5000);
			sf.seleU.scrollToTop();
			sf.seleU.clickElementByJSE(sf.caseDetails.errorCloseButton);
			reportStatusPass(methodName + ": Error message is closed", true, true);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Status to Resolved", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Fill out the necessary details
	 * 
	 *                     Mark case status as Resolved
	 * 
	 *                     Verify the success message
	 * 
	 *                     Verify the status of the case
	 */

	public void verifyCaseStatusSetToResolvedWithMandatoryFieldUpdate() throws IOException {
		try {

			sf.seleU.wait(5000);

			// Verify user is allowed to edit Case ProductFamily
			sf.seleU.clickElementByJSE(sf.caseDetails.editProductFamilyButton);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseProductFamilyField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseProductFamilyBusinessPhoneOption);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated product family ", true, true);

			// Verify user is allowed to edit Case category
//			sf.seleU.clickElementByJSE(sf.caseDetails.editCategoryButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCategoryField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseCategoryLongDistanceOption);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case category ", true, true);

			// Verify user is allowed to edit Case Class
//			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseClassButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseClassField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseClassWireOption);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case class ", true, true);

			// Verify user is allowed to edit Case SubClass
//			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseSubClassButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseSubClassField);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseSubClassSeviceFailureOption);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case subclass ", true, true);

			// Verify user is allowed to edit Subject
//			sf.seleU.clickElementByJSE(sf.caseDetails.editSubjectButton);
//			sf.seleU.clearAndEnterText(sf.caseDetails.subjectEditInput, InputData.caseReasonBillingInquiry);
//			sf.seleU.enterText(sf.caseDetails.subjectEditInput, Keys.TAB);
//			reportStatusPass(methodName + "Updated the case subject ", true, true);

			// Verify user is allowed to edit Description
//			sf.seleU.clearAndEnterText(sf.caseDetails.descriptionEditInput, InputData.caseReasonBillingInquiry);
//			sf.seleU.enterText(sf.caseDetails.descriptionEditInput, Keys.TAB);
//			reportStatusPass(methodName + "Updated the case description ", true, true);

			// Verify user is allowed to edit Case Reason
//			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseReasonButton);
			sf.seleU.clickElementByJSE(sf.caseDetails.caseReasonEditDropdown);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.caseDetails.editCaseReasonDeviceandHardwareOption);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Updated the case reason ", true, true);

			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(sf.caseDetails.save);

			// Change Status to Resolved
			sf.seleU.wait(16000);
			sf.seleU.clickElementByJSE(sf.caseDetails.resolvedTab);
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.caseDetails.markAsCurrentStatusButton);
			sf.seleU.wait(6000);
			verifyFieldValue("Case  Status", sf.caseDetails.statusFieldValueText, InputData.caseStatusResolved);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Updating Case Status to Resolved", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + "Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field has value/text
	 */
	public void verifyFieldHasValue(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field has some value/non-empty
			if (!sf.seleU.getTextFromWebElement(element).isEmpty())
				reportStatusPass(
						methodName + " " + fieldName + " field has value : " + sf.seleU.getTextFromWebElement(element),
						true, true);
			else
				reportStatusFail(methodName + " " + fieldName + " field value is not populated and is empty", true);

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
