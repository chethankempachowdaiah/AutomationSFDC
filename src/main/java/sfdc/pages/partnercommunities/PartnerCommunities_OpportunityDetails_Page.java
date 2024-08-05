package sfdc.pages.partnercommunities;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date 14/05/2021
 * 
 *        Partner Communities Opportunity Details Page
 * 
 * 
 *
 */
public class PartnerCommunities_OpportunityDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;
	public static String methodName; 

	public PartnerCommunities_OpportunityDetails_Page
	() {
		sf = new SFDC_AllPageObjects();
		methodName = "PartnerComm_OppDetails@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Opportunity Details with data input Details
	 */

	public void validateOpportunityDetails(String accountName) throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchWindow(1);
		 	sf.seleU.closeRecentlyOpenedWindow();
		 	
		 	
		 	// Click on Sales Menu
			sf.seleU.clickElementByJSE(sf.partnerCommHome.salesMenuButton);
			reportStatusPass(methodName + " Clicked on Salest Menu", true, true);
			sf.seleU.wait(2000);

			// Click on Accounts Menu
			//sf.seleU.clickElementByJSE(sf.partnerCommHome.opportunityOptionLink);
			sf.seleU.clickElementByJSE(sf.partnerCommHome.opportunityOptionLink);
			reportStatusPass(methodName + " Clicked on Opportunities from Sales Menu", true, true);
			sf.seleU.wait(2000);
			
			sf.seleU.clearAndEnterText(sf.cases.searchCaseInputBox, accountName + " " + sf.dataInput.opportunityType + " " + sf.dataInput.opportunitySite + " "
					+ sf.dataInput.opportunityProductType + " " + sf.dataInput.opportunityProductAcr);
			sf.seleU.enterText(sf.cases.searchCaseInputBox, Keys.ENTER);
			sf.seleU.wait(5000);
			sf.seleU.clickOnElement(sf.cases.caseNumberAllRows.get(0));
			
			reportStatusPass(methodName + " Opened Opportunity from list", true, true);
			
			// Search Account and Click on Details page
			sf.seleU.hardwait(3000);

			// Verify Opportunity details Fields value with data sheet

			// 1. Verify Opportunity Name with data sheet
			verifyFieldValue("Opportunity Name", sf.partnerCommOppDetails.opportunityDetailsNameValueText,
					accountName + " " + sf.dataInput.opportunityType + " " + sf.dataInput.opportunitySite + " "
							+ sf.dataInput.opportunityProductType + " " + sf.dataInput.opportunityProductAcr);

			// 2. Verify Opportunity Account Name with data sheet
			verifyFieldValue("Opportunity Account Name", sf.partnerCommOppDetails.opportunityDetailsAccountNameValueText, accountName);

			// 3. Verify Opportunity Type with data sheet
			verifyFieldValue("Opportunity Type", sf.partnerCommOppDetails.opportunityDetailsTypeValueText, sf.dataInput.opportunityType);

		} catch (Throwable e) {
			reportStatusFail(" Incorrect Opportunity deatils in Opportunity Details Page", e);
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
			if (sf.seleU.getTextFromWebElement(element).equalsIgnoreCase(expectedText)) {
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

}
