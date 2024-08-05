package sfdc.pages.sales;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * 		   Date 08/Sep/2021
 * 
 *         Guided Selling By ringDNA Page
 * 
 * 
 *
 */
public class SFDC_GuidedSellingByRingDNA_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;

	public SFDC_GuidedSellingByRingDNA_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Guided Selling Fields under Contact details page.
	 */

	public void validateGuidedSellingFieldsUnderContact() throws IOException {
		try {

			String methodName = "Guided Selling by ringDNA: ";
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.details);
			sf.seleU.wait(2000);
			//Verify Contact layout page defaults to tab "Details" not Activities 
			String str= "Details";
			if(str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.gSBRingDNA.details.get(0))))
			reportStatusPass(methodName +" Verifed Contact layout page defaults to tab Details not Activities " , true, true);
			else
				reportStatusFail(methodName + " Verification Fail- Contact layout page not defaults to tab Details",true);
			sf.seleU.wait(2000);
			
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(2000);
			//Verify Guided Selling Fields are present on Contact Page
			String str1= "Guided Selling Fields";
			sf.seleU.ScrolltoElement(sf.gSBRingDNA.noDefered);	
			verifyFieldDisplayed("Number Of Deferred Sequence Actions", sf.gSBRingDNA.noDefered);
			verifyFieldDisplayed("Sequence ID", sf.gSBRingDNA.sequenceId);
			verifyFieldDisplayed("Number Of Performed Sequence Actions", sf.gSBRingDNA.noPferformedSeq);
			verifyFieldDisplayed("isActivated", sf.gSBRingDNA.isActivated);
			verifyFieldDisplayed("Number Of Sequence Emails to Reply", sf.gSBRingDNA.noSeqEmail);
			verifyFieldDisplayed("EntranceCriteria Matched Date", sf.gSBRingDNA.entrance);
			verifyFieldDisplayed("Number Of Sequence Emails to Opened", sf.gSBRingDNA.noSeqOpn);
			verifyFieldDisplayed("Opened Sequence Email'", sf.gSBRingDNA.openSeq);
			verifyFieldDisplayed("Number Of Sequence Emails Sent", sf.gSBRingDNA.noSeqSent);
			verifyFieldDisplayed("SequencePerformed", sf.gSBRingDNA.seqPer);
			verifyFieldDisplayed("Replied to Sequence Email", sf.gSBRingDNA.repSeq);
			verifyFieldDisplayed("Sequence Type", sf.gSBRingDNA.seqType);
			verifyFieldDisplayed("Priority", sf.gSBRingDNA.priority); 
			ringDNACallDetailRecord();
			sf.seleU.wait(2000);
			if(str1.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.gSBRingDNA.guidedSellingFields.get(0))))
			reportStatusPass(methodName +" Verifed RingDNA Call Detail Record and Guided Selling Fields are present on Contact Page" , true, true);
			else
				reportStatusFail(methodName + " Verification Fail- RingDNA Call Detail Record and Guided Selling Fields are not present on Contact Page",true);
		} catch (Throwable e) {
			reportStatusFail(" Error in validating Guided Selling Fields under Contact details page. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * Validate ringDNA Call Detail Record Fields under Contact and lead layout page.
	 */

	public void ringDNACallDetailRecord()  throws IOException{
		try 
		{
		sf.seleU.ScrolltoElement(sf.gSBRingDNA.ringDNACallDetailRecord);
		verifyFieldDisplayed("SMS Opt Out", sf.gSBRingDNA.sMSOptOut);
		verifyFieldDisplayed("Call Attempts", sf.gSBRingDNA.callAttempts);
		verifyFieldDisplayed("Days Since Last Attempt (Call or Email)", sf.gSBRingDNA.daysSinceLastAttempt);
		verifyFieldDisplayed("Email Attempts", sf.gSBRingDNA.emailAttempts);
		verifyFieldDisplayed("First Inbound Call", sf.gSBRingDNA.firstInboundCall);
		verifyFieldDisplayed("First Inbound Message", sf.gSBRingDNA.firstInboundMessage);
		verifyFieldDisplayed("First Outbound Call", sf.gSBRingDNA.firstOutboundCall);
		verifyFieldDisplayed("First Outbound Message", sf.gSBRingDNA.firstOutboundMessage);
		verifyFieldDisplayed("Last Email Attempt", sf.gSBRingDNA.lastEmailAttempt);
		verifyFieldDisplayed("Last Inbound Call", sf.gSBRingDNA.lastInboundCall);
		verifyFieldDisplayed("Last Inbound Message", sf.gSBRingDNA.lastInboundMessage);
		verifyFieldDisplayed("Last Outbound Call", sf.gSBRingDNA.lastOutboundCall);
		verifyFieldDisplayed("Last Outbound Message", sf.gSBRingDNA.lastOutboundMessage);
		verifyFieldDisplayed("Message Attempts", sf.gSBRingDNA.messageAttempts);
		verifyFieldDisplayed("Response Type", sf.gSBRingDNA.responseType);
		verifyFieldDisplayed("RingDNA Context", sf.gSBRingDNA.ringDNAContext);
		verifyFieldDisplayed("Time Since Last Call Attempt (Days)", sf.gSBRingDNA.timeSinceLastCallAttemptDays);
		verifyFieldDisplayed("Time Since Last Call Attempt (Minutes)", sf.gSBRingDNA.timeSinceLastCallAttemptMinutes);
		verifyFieldDisplayed("Time Since Last Email Attempt (Days)", sf.gSBRingDNA.timeSinceLastEmailAttemptDays);
		verifyFieldDisplayed("Time Since Last Email Attempt (Minutes)", sf.gSBRingDNA.timeSinceLastEmailAttemptMinutes);
		verifyFieldDisplayed("Time Since Last Message Attempt (Days)", sf.gSBRingDNA.timeSinceLastMessageAttemptDays);
		verifyFieldDisplayed("Time Since Last Message Attempt (Mins)", sf.gSBRingDNA.timeSinceLastMessageAttemptMins);
		verifyFieldDisplayed("Time to First Dial (Minutes)", sf.gSBRingDNA.timetoFirstDialMinutes);
		verifyFieldDisplayed("Time to First Response (Minutes)", sf.gSBRingDNA.timetoFirstResponseMinutes);
		}catch (Throwable e) {
			reportStatusFail(" Error in validating Guided Selling Fields under Contact details page. ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Validate Guided Selling Fields under Contact details page.
	 */

	public void validateGuidedSellingFieldsUnderContactDet() throws IOException {
		try {

			String methodName = "Guided Selling by ringDNA: ";
			sf.seleU.wait(2000);
				
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(2000);
			//Verify Guided Selling Fields are present on Contact Page
			String str1= "Guided Selling Fields";
			sf.seleU.ScrolltoElement(sf.gSBRingDNA.noDefered);
			verifyFieldDisplayed("Number Of Deferred Sequence Actions", sf.gSBRingDNA.noDefered);
			verifyFieldDisplayed("Sequence ID", sf.gSBRingDNA.sequenceId);
			verifyFieldDisplayed("Number Of Performed Sequence Actions", sf.gSBRingDNA.noPferformedSeq);
			verifyFieldDisplayed("isActivated", sf.gSBRingDNA.isActivated);
			verifyFieldDisplayed("Number Of Sequence Emails to Reply", sf.gSBRingDNA.noSeqEmail);
			verifyFieldDisplayed("EntranceCriteria Matched Date", sf.gSBRingDNA.entrance);
			verifyFieldDisplayed("Number Of Sequence Emails to Opened", sf.gSBRingDNA.noSeqOpn);
			verifyFieldDisplayed("Opened Sequence Email'", sf.gSBRingDNA.openSeq);
			verifyFieldDisplayed("Number Of Sequence Emails Sent", sf.gSBRingDNA.noSeqSent);
			verifyFieldDisplayed("SequencePerformed", sf.gSBRingDNA.seqPer);
			verifyFieldDisplayed("Replied to Sequence Email", sf.gSBRingDNA.repSeq);
			verifyFieldDisplayed("Sequence Type", sf.gSBRingDNA.seqType);
			verifyFieldDisplayed("Priority", sf.gSBRingDNA.priority);
			
			sf.seleU.wait(2000);
			if(str1.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.gSBRingDNA.guidedSellingFields.get(0))))
			reportStatusPass(methodName +" Verifed Guided Selling Fields are present on Contact Page" , true, true);
			else
				reportStatusFail(methodName + " Verification Fail- Guided Selling Fields are not present on Contact Page",true);
		} catch (Throwable e) {
			reportStatusFail(" Error in validating Guided Selling Fields under Contact details page. ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Validate Guided Selling Fields under Lead details page.
	 */

	public void validateGuidedSellingFieldsUnderLead() throws IOException {
		try {

			String methodName = "Guided Selling by ringDNA: ";
			sf.seleU.wait(2000);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.details);
			sf.seleU.wait(2000);
			//Verify Lead layout page defaults to tab "Details" not Activities 
			String str= "Details";
			if(str.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.gSBRingDNA.details.get(0))))
			reportStatusPass(methodName +" Verifed Lead layout page defaults to tab Details not Activities " , true, true);
			else
				reportStatusFail(methodName + " Verification Fail- Lead layout page not defaults to tab Details",true);
			
			sf.seleU.wait(2000);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.gsbFrame);
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Number Of Deferred Sequence Actions", sf.gSBRingDNA.noDefered);
			verifyFieldDisplayed("Sequence ID", sf.gSBRingDNA.sequenceId);
			verifyFieldDisplayed("Number Of Performed Sequence Actions", sf.gSBRingDNA.noPferformedSeq);
			verifyFieldDisplayed("isActivated", sf.gSBRingDNA.isActivated);
			verifyFieldDisplayed("Number Of Sequence Emails to Reply", sf.gSBRingDNA.noSeqEmail);
			verifyFieldDisplayed("EntranceCriteria Matched Date", sf.gSBRingDNA.entrance);
			verifyFieldDisplayed("Number Of Sequence Emails to Opened", sf.gSBRingDNA.noSeqOpn);
			verifyFieldDisplayed("Opened Sequence Email'", sf.gSBRingDNA.openSeq);
			verifyFieldDisplayed("Number Of Sequence Emails Sent", sf.gSBRingDNA.noSeqSent);
			verifyFieldDisplayed("SequencePerformed", sf.gSBRingDNA.seqPer);
			verifyFieldDisplayed("SequenceAssignmentError", sf.gSBRingDNA.seqErr);
			verifyFieldDisplayed("Replied to Sequence Email", sf.gSBRingDNA.repSeq);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.guidedSellingFields);
			ringDNACallDetailRecord();
			
			sf.seleU.wait(2000);
			//Verify Guided Selling Fields are present on Lead Page
			String str1= "Guided Selling Fields";
			if(str1.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.gSBRingDNA.guidedSellingFields.get(0))))
			reportStatusPass(methodName +" Verifed RingDNA Call Detail Record and Guided Selling Fields are present on Lead Page" , true, true);
			else
				reportStatusFail(methodName + " Verification Fail- RingDNA Call Detail Record and Guided Selling Fields are not present on Lead Page",true);
		} catch (Throwable e) {
			reportStatusFail(" Error in validating Guided Selling Fields under Lead details page.", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select Contact Tab
	 */
	public void selectContactTab() throws IOException {
		try {
				String methodName = "Guided Selling by ringDNA: ";
				// Select Navigation Menu
				sf.seleU.hardwait(5000);
				sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);

				// Select Contact
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.home.contactsMenu);
				reportStatusPass(methodName + " Selected Contacts from menu", true, false);
				sf.seleU.hardwait(2000);
	     	}
				catch (Throwable e) {
					reportStatusFail(" Error in Selecting Contact ", e);
					e.printStackTrace();
				}
		}
		
	
	/**
	 * @throws IOException
	 * 
	 *                     Select Lead Tab
	 */

	public void selectLeadTab() throws IOException {
		try {
				String methodName = "Guided Selling by ringDNA: ";
				// Select Navigation Menu
				// 1-Selecting Leads from menu
				sf.seleU.hardwait(5000);
				sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
				sf.seleU.hardwait(2000);

				sf.seleU.clickElementByJSE(sf.home.leadMenu);
				reportStatusPass(methodName + " Selected Leads from menu", true, true);
				sf.seleU.hardwait(2000);
	     	}
				catch (Throwable e) {
					reportStatusFail(" Error in Selecting Lead ", e);
					e.printStackTrace();
				}
			}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select and Open Contact for Guided Selling by ringDNA
	 */

	public void selectContactGS() throws IOException {
		try {

			String methodName = "Guided Selling by ringDNA: ";

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.contacts);
			sf.seleU.wait(2000);
			//Validate Add to Sequence and Remove From Sequence Button
			verifyFieldDisplayed("Add To Sequence", sf.gSBRingDNA.addToSequenceButton);
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Remove From Sequence", sf.gSBRingDNA.removeFromSequenceButton);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.listViewIcon);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.allContactsOption);
			sf.seleU.wait(2000);

			// Verify at least one contact is displayed and click on it
			if (sf.gSBRingDNA.contactsAllRecords.size() > 0) {
			reportStatusPass(methodName + " My Contact is present", true, false);
			reportStatusPass(methodName + " Selecting Contact : "
			+ sf.seleU.getTextFromWebElement(sf.gSBRingDNA.firstContactInList), false, true);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.firstContactInList);
			reportStatusPass(methodName + " Clicked on first Contact from the list", true, true);

			} 
			else 
				reportStatusFail(methodName + " No records found in My Contact ", true);			
		}
		catch (Throwable e) {
			reportStatusFail(" Error in Selecting Contact for Guided Selling by ringDNA ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select and Open Contact for Guided Selling by ringDNA
	 */

	public void selectContactGSforService() throws IOException {
		try {

			String methodName = "Guided Selling by ringDNA: ";

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.contacts);
			sf.seleU.wait(2000);
			//Validate Add to Sequence and Remove From Sequence Button
			verifyFieldDisplayed("Add To Sequence", sf.gSBRingDNA.addToSequenceButton);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.listViewIcon);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.allContactsOption);
			sf.seleU.wait(2000);

			// Verify at least one contact is displayed and click on it
			if (sf.gSBRingDNA.contactsAllRecords.size() > 0) {
			reportStatusPass(methodName + " My Contact is present", true, false);
			reportStatusPass(methodName + " Selecting Contact : "
			+ sf.seleU.getTextFromWebElement(sf.gSBRingDNA.firstContactInList), false, true);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.firstContactInList);
			reportStatusPass(methodName + " Clicked on first Contact from the list", true, true);

			} 
			else 
				reportStatusFail(methodName + " No records found in My Contact ", true);			
		}
		catch (Throwable e) {
			reportStatusFail(" Error in Selecting Contact for Guided Selling by ringDNA ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select and Open Lead for Guided Selling by ringDNA
	 */

	public void selectLeadGS() throws IOException {
		try {

			String methodName = "Guided Selling by ringDNA: ";

			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.leads);
			sf.seleU.wait(2000);
			//Validate Add to Sequence and Remove From Sequence Button
			verifyFieldDisplayed("Add To Sequence", sf.gSBRingDNA.addToSequenceButton);
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Remove From Sequence", sf.gSBRingDNA.removeFromSequenceButton);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.listViewIcon);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.allLeadsOption);
			sf.seleU.wait(2000);
			
			// Verify at least one Lead is displayed and click on it
			if (sf.gSBRingDNA.contactsAllRecords.size() > 0) {
			reportStatusPass(methodName + " My Lead is present", true, false);
			reportStatusPass(methodName + " Selecting Lead : "
			+ sf.seleU.getTextFromWebElement(sf.gSBRingDNA.firstLeadInList), false, true);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.firstLeadInList);
			reportStatusPass(methodName + " Clicked on first Lead from the list", true, true);

			} 
			else 
				reportStatusFail(methodName + " No records found in My Lead", true);			
		}
		catch (Throwable e) {
			reportStatusFail(" Error in Selecting Lead for Guided Selling by ringDNA ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 * 
	 */
	public void openServiceConsole() throws IOException {
		try {
			String methodName = "Guided Selling by ringDNA: ";

			// Click on app launcher and look for Service Console
			sf.seleU.clickElementByJSE(sf.home.applauncher);
			sf.seleU.hardwait(5000);
			String str1="Service Console";
			sf.seleU.enterText(sf.home.searchAppItemInput, str1);

			for (int i = 0; i < sf.home.searchedApp.size(); i++) {
				if (sf.home.searchedApp.get(i).getText().equals(str1)) {
					sf.seleU.clickElementByJSE(sf.home.searchedApp.get(i));
					break;
				}
			}
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to Select the Service Console ", e);
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @throws IOException
	 * 
	 */
	public void verifyParticipantActions() throws IOException {
		try {
			String methodName = "Guided Selling by ringDNA: ";
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.actions);
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.allActions);
			verifyFieldDisplayed("All Actions", sf.gSBRingDNA.allActions.get(0));
		
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to validate Participant Actions ", e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @throws IOException //validation of comment field under Task->New task->Log a sales call
	 * 
	 */
	public void tasksCommnentsValidation() throws IOException {
		try {
			String methodName = "Guided Selling by ringDNA: ";
//			sf.seleU.clickElementByJSE(sf.gSBRingDNA.showMoreActions);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.newTask);
			sf.seleU.hardwait(2000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.next);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.next.get(0));
			sf.seleU.ScrolltoElement(sf.gSBRingDNA.comments.get(0));
			sf.seleU.hardwait(4000);
			String str1="Comments";
			if(str1.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.gSBRingDNA.comments.get(0))))
				reportStatusPass(methodName +" Verifed Comments section in pagelayout for ringdna users is present on Log A Sales activity Page" , true, true);
				else
					reportStatusFail(methodName + " Verification Fail- RiComments section in pagelayout for ringdna users is not present on Log A Sales activity Page",true);
			
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.closeLogSalesWindow);
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to validate Participant Actions ", e);
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @throws IOException //validation of status drop down values 
	 * under Tasks->New task->LLog a sales call->Status dropdown
	 * 
	 */
	public void tasksLogSalesActivity() throws IOException {
		try {
			String methodName = "Guided Selling by ringDNA: ";
//			sf.seleU.clickElementByJSE(sf.gSBRingDNA.showMoreActions);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.newTask);
			sf.seleU.hardwait(2000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.next);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.next.get(0));
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.dropDownStatus);
			sf.seleU.hardwait(4000);
			
			verifyFieldDisplayed("Delivered under status dropdown", sf.gSBRingDNA.delivered);
			verifyFieldDisplayed("Sent under status dropdown", sf.gSBRingDNA.sent);
			verifyFieldDisplayed("Received under status dropdown", sf.gSBRingDNA.received);
			verifyFieldDisplayed("Undelivered under status dropdown", sf.gSBRingDNA.undelivered);
			verifyFieldDisplayed("Queued under status dropdown", sf.gSBRingDNA.queued); 
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.closeLogSalesWindow);
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to validate Participant Actions ", e);
			e.printStackTrace();
		}
		
	}	
	/**
	 * 
	 * @throws IOException
	 * 
	 */
	public void verifyListofGuidedSellingUsers () throws IOException {
		try {
			String methodName = "Guided Selling by ringDNA: ";
			sf.seleU.clickElementByJSE(sf.gSBRingDNA.seqSetting);
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.guidedSellingSet);
			verifyFieldDisplayed("Guided Selling Setting", sf.gSBRingDNA.guidedSellingSet.get(0));
			sf.seleU.hardwait(2000);
			sf.seleU.switchToElementFrame(sf.gSBRingDNA.grantAllPer);
			verifyFieldDisplayed("Grant All Permission", sf.gSBRingDNA.grantAllPer.get(0));
			
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail("Unable to validate Participant Actions ", e);
			e.printStackTrace();
		}
		
	}
	
	
	 
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
