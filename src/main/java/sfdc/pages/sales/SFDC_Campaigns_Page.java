package sfdc.pages.sales;

import java.io.IOException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Nandan.More, Date : 14/Oct/2021
 * 
 *         SFDC Campaign page
 */
public class SFDC_Campaigns_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Campaign@: ";

	public SFDC_Campaigns_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 * 				Verify Page lands on Campaign page
	 * 
	 *  			Create New Campaign
	 *  
	 *  			Enter Mandatory Fields and Hit Save 
	 *  
	 *   			Campaign Details page will be appeared 
	 *                  
	 * 
	 */
	public void validateActiveCheckbox() throws IOException {
		try {
			String str="Checkbox";
			// 1-Selecting Campaign Header from page
			sf.seleU.hardwait(5000);
			sf.seleU.getTextFromWebElement(sf.campaign.campaignPageHeader);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Selected  Campaign ", true, true);
			//creating new campaign
			sf.seleU.clickElementByJSE(sf.campaign.newCampaignButton);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.campaign.campaignNameTextBox , Global.salesData.campaignName);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.startDateIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.buttonDateToday);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.endDateIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.campaign.endDateRead , Global.salesData.endDate);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.campaign.saveCampaign);
			sf.seleU.hardwait(5000);
			sf.seleU.refreshPage();
			sf.seleU.wait(10000);
			sf.seleU.refreshPage();
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.detailsTab);	
			sf.seleU.hardwait(2000);
			// Clicking on edit button as we are checking check box value
			sf.seleU.clickElementByJSE(sf.campaign.editForm);
			sf.seleU.hardwait(2000);			
			// Verification - Active check box on Campaigns is automatically checked
			verifyCheckboxCheckedOrNot(str,sf.campaign.activeCheckbox);
			sf.seleU.hardwait(2000);
			// Clicking on cancel button as we are not modifying anything
			sf.seleU.clickElementByJSE(sf.campaign.cancelButton);
			sf.seleU.hardwait(2000);
			
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Validating Campaigns verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value is displayed
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
	
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify Check box is checked or not
	 *                     
	 **/
	public void verifyCheckboxCheckedOrNot(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.wait(3000);

			// Verify Field displayed
			if (sf.campaign.activeCheckbox.isSelected()) {
				reportStatusPass("Validated " + fieldName + " is automatically checked when current date falls between Campaign Start and End Date", true, true);
			} else {
				reportStatusFail("Verification Failed "+fieldName + " is not automatically checked", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Checkbox Field verification", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 * 				Verify Page lands on Campaign page
	 * 
	 *  			Open Any Campaign
	 *  
	 *  			Verify Current Dtae, Start Date and End Date
	 *  
	 *   			Verify Active Check Box  (Negative Scenario)
	 *                  
	 * 
	 */
	public void validateActiveCheckboxNotChecked() throws IOException {
		try {

			// 1-Selecting Campaign Header from page
			sf.seleU.hardwait(5000);
			sf.seleU.getTextFromWebElement(sf.campaign.campaignPageHeader);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + " Selected  Campaign ", true, true);
			//creating new campaign
			sf.seleU.clickElementByJSE(sf.campaign.newCampaignButton);
			sf.seleU.hardwait(2000);
			sf.seleU.enterText(sf.campaign.campaignNameTextBox , Global.salesData.campaignName);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.startDateIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.campaign.startDateRead , Global.salesData.startDate);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.endDateIcon);
			sf.seleU.hardwait(3000);
			sf.seleU.enterText(sf.campaign.endDateRead , Global.salesData.endDate2);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.saveCampaign);
			sf.seleU.hardwait(5000);
			sf.seleU.refreshPage();
			sf.seleU.wait(10000);
			sf.seleU.refreshPage();
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.detailsTab);			
			sf.seleU.hardwait(5000);
			// Clicking on edit button as we are checking check box value
			sf.seleU.clickElementByJSE(sf.campaign.editForm);
			sf.seleU.hardwait(2000);			
			// Verification - Active check box on Campaigns is not automatically checked
			// Verify Field checked or not
			if (sf.campaign.activeCheckbox.isSelected()) {
				reportStatusFail("Verification Failed-  Check box is automatically checked when current date not falls between Campaign Start and End Date",  true);
			} else {
				reportStatusPass("Negative Scenario Verification Passed-   Check box is not automatically checked when current date not falls between Campaign Start and End Date", true,true);
			}
			sf.seleU.hardwait(2000);
			
			// Clicking on cancel button as we are not modifying anything
			sf.seleU.clickElementByJSE(sf.campaign.cancelButton);
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Validating Campaigns verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Navigate Up to Contact Campaign layout
	 *                     
	 **/
	public void contactLayout() throws IOException {
		try {
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.relatedTab);	
			sf.seleU.wait(2000);
			sf.seleU.switchToElementFrame(sf.campaign.campaignHistory);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.campaignHistory.get(0));	
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error in navigating up to contact campaign layout", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify Campaign fields 
	 *                     
	 **/
	public void verifyCampaignFields() throws IOException {
		try {
			
			sf.seleU.wait(2000);
			verifyFieldDisplayed("Campaign Name ", sf.campaign.campaignName);
			verifyFieldDisplayed("Start Date ", sf.campaign.sDate);
			verifyFieldDisplayed("Type ", sf.campaign.type);	
			verifyFieldDisplayed("Created Date by Descending/Ascending Order ", sf.campaign.createdDate);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Campaign Field verification", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Navigate Up to Lead Campaign layout
	 *                     
	 **/
	public void leadLayout() throws IOException {
		try {
		
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.camHistory);	
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error in navigating up to Lead campaign layout", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Navigate Up to Account Campaign layout
	 *                     
	 **/
	public void accountLayout() throws IOException {
		try {
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.camHistory);	
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error in navigating up to account campaign layout", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Navigate Up to Lead Campaign layout
	 *                     
	 *                     open any one record 
	 *                     
	 *                     validate campaign record type based on profile
	 *                     
	 **/
	public void validateCampaignRecordType() throws IOException {
		try {
		
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.listViewIcon);	
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.campaign.allActiveCampaignsOption);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.campaign.campaignAllRecords.get(0));
			sf.seleU.wait(4000);
			sf.seleU.clickElementByJSE(sf.campaign.detailsTab);	
			sf.seleU.hardwait(5000);
			String str1 ="Standard";
			if (str1.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.campaign.standard)))
				reportStatusPass(methodName + " Verifed Campaign record type = Standard ", true, true);

			else
				reportStatusFail(" Verification Failed- Campaign record type = not Standard e ",
						true);
		} catch (Throwable e) {
			reportStatusFail(" Error in validating Campaign Record type", e);
			e.printStackTrace();
		}
	}
	
	

}