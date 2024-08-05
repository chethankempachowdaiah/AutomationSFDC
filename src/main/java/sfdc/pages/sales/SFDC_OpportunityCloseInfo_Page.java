package sfdc.pages.sales;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date 20/Jan/2020
 * 
 *         Opportunity Close Information Page(Create Opportunity>create Quote>
 *         Servicability : OffNet>Opportunity Close Information)
 * 
 * 
 */
public class SFDC_OpportunityCloseInfo_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;
	String methodName;

	public SFDC_OpportunityCloseInfo_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Opportunity Close Information@: ";

	}

	/**
	 * Validate that below 3 fields are the required fields :
	 * 
	 * -Closed Lost Primary
	 * 
	 * ..................Reason: a. Serviceability b. Promotion Pricing
	 * 
	 * -Closed Lost Secondary
	 * 
	 * ..................Reason: a. No cable Coverage b. Promotion Pricing
	 * 
	 * -Competitor Lost To
	 * 
	 * ......Reason: a. Bell b. Telus c. Shaw d. Cogeco e.Zayo/Allstream
	 * 
	 * Select Closed Lost Primary
	 * 
	 * Select Closed Lost Secondary
	 * 
	 * Select Competitor Lost To
	 * 
	 * Click on Next Button
	 * 
	 * @throws IOException
	 * 
	 */
	public void validateClosedLostOpportunity() throws IOException {

		try {

			sf.dataInput.closedLost_prepareReasons();

			// Verify Closed Lost Dropdown options
			verifyDropdownOptions("Closed Lost Primary Reason", sf.ocInfo.closedLostPrimaryReasonDropdown,
					sf.dataInput.closedLostPrimaryReasons);
			verifyDropdownOptions("Closed Lost Secondary Reason", sf.ocInfo.closedLostSecondaryReasonDropdown,
					sf.dataInput.closedLostSecondaryReasons);
			verifyDropdownOptions("Closed Lost Competitor Lost To", sf.ocInfo.closedLostCompetitorLostToDropdown,
					sf.dataInput.closedLostCompetitors);

			// Verify Fields are required fields
			verifyFieldIsRequired("Closed Lost Primary Reason", sf.ocInfo.closedLostPrimaryReasonDropdown);
			verifyFieldIsRequired("Closed Lost Secondary Reason", sf.ocInfo.closedLostSecondaryReasonDropdown);
			verifyFieldIsRequired("Closed Lost Competitor Lost To", sf.ocInfo.closedLostCompetitorLostToDropdown);

			// Select Closed Lost Primary
			sf.seleU.selectTextFromDropDown(sf.ocInfo.closedLostPrimaryReasonDropdown,
					sf.dataInput.closedLostPrimaryReason);
			reportStatusPass(methodName + " Selected Closed Lost Primary as " + sf.dataInput.closedLostPrimaryReason,
					true, false);

			// Select Closed Lost Secondary
			sf.seleU.selectTextFromDropDown(sf.ocInfo.closedLostSecondaryReasonDropdown,
					sf.dataInput.closedLostSecondaryReason);
			reportStatusPass(
					methodName + " Selected Closed Lost Secondary as " + sf.dataInput.closedLostSecondaryReason, true,
					false);

			// Select Competitor Lost To
			sf.seleU.selectTextFromDropDown(sf.ocInfo.closedLostCompetitorLostToDropdown,
					sf.dataInput.cosedLostCompetitor);
			reportStatusPass(
					methodName + " Selected Closed Lost Competitor Lost To as " + sf.dataInput.cosedLostCompetitor,
					true, false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.ocInfo.closedLostCloseInformation_nextBtn);
			reportStatusPass(methodName + " Clicked On Next Button on Opportunity Close Information Page" + " ", true,
					false);

			sf.seleU.wait(9000);
		} catch (Throwable e) {
			reportStatusFail(" Invalid Info in Opportunity Close Information Page", e);
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
	public void verifyDropdownOptions(String DropdownName, WebElement dropdown, List<String> dropdownOptionsData)
			throws IOException {
		try {

			boolean dropdownValidated = false;

			for (int i = 1; i < sf.seleU.getDropDownOptions(dropdown).size(); i++) {

				for (int j = 0; j < dropdownOptionsData.size(); j++) {

					if (sf.seleU.getDropDownOptions(dropdown).get(i).equals(dropdownOptionsData.get(j))) {
						dropdownValidated = true;
						break;
					}

				}

				if (dropdownValidated) {
					reportStatusPass(methodName + " Validated Drop down Values for " + DropdownName + " is "
							+ sf.seleU.getDropDownOptions(dropdown).get(i), true, true);
				} else {
					reportStatusFail(methodName + " Invalid Drop down Values for " + DropdownName, true);
				}
				dropdownValidated = false;
			}

		} catch (Throwable e) {
			reportStatusFail(" Invalid Info in Opportunity Close Information Page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param field
	 * @throws IOException
	 * 
	 *                     verify given field is mandatory fields
	 */
	public void verifyFieldIsRequired(String fieldName, WebElement field) throws IOException {
		try {
			if (field.getAttribute("required").equals("true")) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is required field", true, true);
			} else {
				reportStatusFail(methodName + " Field " + fieldName + "is not a mandatory field", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Error in validating required field", e);
			e.printStackTrace();
		}
	}

}
