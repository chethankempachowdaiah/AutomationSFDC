package sfdc.pages.sales;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date 17/Jan/2020
 * 
 *         Opportunity Details Page
 * 
 * 
 *
 */
public class SFDC_OpportunityDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;

	public SFDC_OpportunityDetails_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Validate Opportunity Details with data input Details
	 */

	public void validateOpportunityDetails(String accountName) throws IOException {
		try {

			sf.seleU.switchToDefaultContent();

			// Search Account and Click on Details page
			sf.seleU.hardwait(2000);

			// Verify Opportunity details Fields value with data sheet

			// 1. Verify Opportunity Name with data sheet
			verifyFieldValue("Opportunity Name", sf.od.opportunityDetailsNameValueText,
					accountName + " " + Global.dataInput.opportunityType + " " + Global.dataInput.opportunitySite + " "
							+ Global.dataInput.opportunityProductType + " " + Global.dataInput.opportunityProductAcr);

			// 2. Verify Opportunity Account Name with data sheet

			verifyFieldValue("Opportunity Account Name", sf.od.opportunityDetailsAccountNameValueText, accountName);

			// 3. Verify Opportunity Type with data sheet
			verifyFieldValue("Opportunity Type", sf.od.opportunityDetailsTypeValueText,
					Global.dataInput.opportunityType);

			// 3. Verify Opportunity Close Date with data
			verifyFieldValue("Opportunity Close Date", sf.od.opportunityDetailsCloseDateValueText,
					Global.dataInput.opportunityCloseDateFormatted);

			// 4. Verify Opportunity Stage with data
			verifyFieldValue("Opportunity Stage", sf.od.opportunityDetailsStageValueText,
					Global.dataInput.opportunityStage);

			// 5. Verify Opportunity Additional Information Next Step with data
			// verifyFieldValue("Opportunity Additional Information Next Step",
			// sf.od.opportunityDetailsNextStepValueText,
			// sf.dataInput.opportunityAdditionalInfo);

			// 6. Verify Opportunity Owner with User Name
			verifyFieldValue("Opportunity Owner", sf.od.opportunityDetailsOpportunityOwnerValueText,
					InputData.username);

			// 7. Verify Opportunity Amount with data
			verifyFieldValue("Opportunity Amount", sf.od.opportunityDetailsAmountValueText,
					"$" + Global.dataInput.opportunityAmount);

			// 8a. Verify help text for Opportunity Score  is present and displayed

			if (sf.seleU.isElementDisplayed(sf.od.scoreLabel) && sf.seleU.isElementDisplayed(sf.od.scoreInfoIcon)) {
				reportStatusPass("Validated Opportunity Score is present", true, true);

				// 8b. verify opportunity score information message
				sf.seleU.mouseOverOnElement(sf.od.scoreInfoIcon);
				// verifyFieldValue("Opportunity Score Information Message",
				// sf.od.scoreInformationText,
				// sf.dataInput.opportunityScoreMsg);

				// 9. Verify Forecast Category dropdown with 5 values

			} else {
				reportStatusFail(" No Field Present as Opportunity Score", true);
			}
			sf.seleU.clickElementByJSE(sf.od.editForcastCategoryButton);
			Global.dataInput.forcastCategory_PrepareOptions();
			sf.seleU.clickElementByJSE(sf.od.forcastCategoryEditDropdownLink);
			sf.seleU.wait(3000);
			verifyOptions("Forcast Category Options", sf.od.forcastCategoryAllLinks,
					Global.dataInput.forecastCategoryOptions);
			sf.seleU.clickElementByJSE(sf.od.forcastCategoryAllLinks.get(0));
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.od.cancelButton);
			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Incorrect Opportunity deatils in Opportunity Details Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.verify Closed Lost primary reason in opportunity
	 *                     details
	 * 
	 *                     2.verify Closed Lost secondary reason in opportunity
	 *                     details
	 * 
	 *                     3.verify Closed Lost Competitor Lost to in opportunity
	 *                     details
	 */
	public void verifyClosedLostOpportunityDetails() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();

			// verify Closed Lost primary reason in opportunity details
			verifyFieldValue("Closed Lost Primary Reason ", sf.od.closedLostPrimaryReasonValueText,
					Global.dataInput.closedLostPrimaryReason);

			// verify Closed Lost secondary reason in opportunity details
			verifyFieldValue("Closed Lost Secondary Reason ", sf.od.closedLostSecondaryReasonValueText,
					Global.dataInput.closedLostSecondaryReason);

			// verify Closed Lost Competitor Lost to in opportunity details
			verifyFieldValue("Closed Lost Competitor Lost to ", sf.od.closedLostCompetitorToValueText,
					Global.dataInput.cosedLostCompetitor);

			sf.seleU.hardwait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Incorrect Opportunity deatils in Opportunity Details Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Opportunity is Closed Own
	 */
	public void verifyOpportunityIsClosedOwn() throws IOException {
		try {

			// Verify Opportunity is Closed Own
			verifyFieldValue("Opportunity Record Type", sf.od.opportunityRecordTypeValueText, InputData.closedWonOpp);

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying Closed Own Opportunity", e);
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

	/**
	 * @param DropdownName
	 * @param dropdown
	 * @param dropdownOptionsData
	 * @throws IOException
	 * 
	 * 
	 *                     Verify the options present in the given dropdown list
	 */
	public void verifyOptions(String elelemtListName, List<WebElement> eles, List<String> listOptionsData)
			throws IOException {
		try {

			String methodName = "SFDC_Opportunity Details: ";

			boolean listValidated = false;

			for (int i = 0; i < eles.size(); i++) {

				for (int j = 0; j < listOptionsData.size(); j++) {
					if (sf.seleU.getTextFromWebElement(eles.get(i)).equals(listOptionsData.get(j))) {
						listValidated = true;
						break;
					}

				}

				if (listValidated) {
					reportStatusPass(methodName + " Validated Option Values for " + elelemtListName + " is "
							+ sf.seleU.getTextFromWebElement(eles.get(i)), true, true);
				} else {
					reportStatusFail(methodName + " Invalid Option Value for " + elelemtListName, true);
				}

				listValidated = false;
			}

		} catch (Throwable e) {
			reportStatusFail(" Invalid Info in Options", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Log An Activity button
	 */
	public void logAnActivity() throws IOException {

		String methodName = "SFDC_Opportunity Details: ";

		try {

			sf.seleU.clickElementByJSE(sf.od.showMoreActionsButton);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.cd.logASalesActivityButton);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Clicked on Log An Activity button from opportunity ", true, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on Log An Activity", e);
			e.printStackTrace();
		}
	}

}
