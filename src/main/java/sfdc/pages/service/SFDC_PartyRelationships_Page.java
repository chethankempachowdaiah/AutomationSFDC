package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 18/JUNE/2020
 * 
 *         Select Party Relationships>Details
 *
 */
public class SFDC_PartyRelationships_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	String methodName;

	public SFDC_PartyRelationships_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Party Relationships@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Selecting Party Relationships from menu
	 * 
	 *                     Verify relationship is created and click on the
	 *                     relationship
	 * 
	 *                     Verify relationship details
	 */
	public void verifyPartyRelationshipCreated() throws IOException {
		try {

			// Select Party Relationships from menu
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.partyRelationshipsMenu);
			reportStatusPass(methodName + " Selected Party Relationship from menu", true, false);
			sf.seleU.hardwait(2000);

			// Verify relationship is created and click on the relationship
			boolean isRelationshipFound = false;
			for (int i = 0; i < sf.pr.relationshipNameAllRows.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.pr.sourcePartyIdAllRows.get(i))
						.equals(sf.dataInput.businessAccountName)
						&& sf.seleU.getTextFromWebElement(sf.pr.targetPartyIdAllRows.get(i))
								.equals(sf.dataInput.parentAccountName)) {
					isRelationshipFound = true;
					sf.dataInput.relationshipName = sf.seleU
							.getTextFromWebElement(sf.pr.relationshipNameAllRows.get(i));
					sf.seleU.clickElementByJSE(sf.pr.relationshipNameAllRows.get(i));

					break;
				}
			}

			// Verify relationship details
			if (isRelationshipFound) {
				reportStatusPass(methodName + " Verified Party Relationship is created", true, true);

//				sf.seleU.clickElementByJSE(sf.pr.detailsTab);
				
				sf.seleU.clickElementByJSE(sf.pr.details);

				verifyFieldValue("Relationship Name", sf.pr.relationshipNameValue, sf.dataInput.relationshipName);
				verifyFieldValue("Target Party Id", sf.pr.targetPartyIdValue, sf.dataInput.parentAccountName);
				verifyFieldValue("Source Party Id", sf.pr.sourcePartyIdValue, sf.dataInput.businessAccountName);
				verifyFieldValue("Primary Role", sf.pr.primaryRoleValue, sf.dataInput.acc_RecordType_Business);
				verifyFieldValue("Target Role", sf.pr.targetRoleValue, sf.dataInput.relationshipTypeSelected);

			} else {
				reportStatusFail(methodName + "Error in verifying party relationship", true);

			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Error in verifying party relationship", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Party Relationships from menu
	 * 
	 *                     Verify relationship(that was created) is removed
	 */
	public void verifyPartyRelationshipRemoved() throws IOException {
		try {

			// Select Party Relationships from menu
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.partyRelationshipsMenu);
			reportStatusPass(methodName + " Selected Party Relationship from menu", true, false);
			sf.seleU.hardwait(2000);

			// Verify relationship is removed
			boolean isRelationshipFound = false;
			for (int i = 0; i < sf.pr.relationshipNameAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.pr.relationshipNameAllRows.get(i))
						.equals(sf.dataInput.relationshipName)) {
					isRelationshipFound = true;
					break;
				}
			}

			if (!isRelationshipFound) {
				reportStatusPass(methodName + " Verified Party Relationship is Removed", true, true);
			} else {
				reportStatusFail(methodName + "Error in removing party relationship", true);

			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "party relationship is not removed", e);
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
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
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
