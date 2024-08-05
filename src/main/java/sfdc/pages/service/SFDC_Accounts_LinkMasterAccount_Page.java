package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_Accounts_LinkMasterAccount_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Accounts_LinkMasterAccount_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Account As Master Account
	 * 
	 *                     Verify All input fields: Account Name, Account Legal
	 *                     Name, Account R1DUNS and Account DUNS for account search
	 *                     are present
	 * 
	 *                     Search Account and Select Account Name
	 * 
	 *                     Verify Relationship Type Field is required
	 * 
	 *                     Select Relationship Type
	 * 
	 *                     Verify Relation Type Options
	 * 
	 *                     Select Relationship Type
	 * 
	 *                     Click on Next Button and accept alert Ok
	 * 
	 */
	public void linkMasterAccount() throws IOException {
		try {

			String methodName = "SFDC_Link_MasterAccount@: ";

			// Select Account As Master Account
			for (int i = 0; i < sf.liMAcc.accountsAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.liMAcc.accountsAllRows.get(i))
						.equals(dataInput.businessAccountName)) {

					sf.seleU.clickElementByJSE(sf.liMAcc.addMasterAccountIconAllRows.get(i));
					sf.seleU.clickElementByJSE(sf.liMAcc.addSelectIconAllRows.get(i));
					reportStatusPass(
							methodName + " Selected Account as Master Account " + dataInput.businessAccountName,
							true, false);
				}
			}

			// Verify All input fields: Account Name, Account Legal Name, Account R1DUNS and
			// Account DUNS for account search are present

			if (sf.seleU.isElementPresent(sf.liMAcc.mergeAccountNameInput)
					&& sf.seleU.isElementPresent(sf.liMAcc.mergeAccountLegalNameInput)
					&& sf.seleU.isElementPresent(sf.liMAcc.mergeAccountR1DUNSInput)
					&& sf.seleU.isElementPresent(sf.liMAcc.mergeAccountDUNSInput)) {
				reportStatusPass(methodName
						+ " Verified All input fields: Account Name, Account Legal Name, Account R1DUNS and  Account DUNS for account search are present",
						true, true);
			} else {
				reportStatusFail(methodName + "Invalid Input Fields for Account search", true);
			}

			// Search Account and Select Account Name
			sf.seleU.clearAndEnterText(sf.liMAcc.mergeAccountNameInput, dataInput.parentAccountName);
			sf.seleU.clickElementByJSE(sf.liMAcc.selectMergeAccountInput);
			sf.seleU.clickElementByJSE(sf.liMAcc.mergeAccountOptionsAll.get(0));
			reportStatusPass(methodName + " Selected Target Account " + dataInput.parentAccountName, true, false);

			// Verify Relationship Type Field is required
			if (sf.seleU.getElementAttribute(sf.liMAcc.selectRelationshipType, "required").equals("true")) {
				reportStatusPass(methodName + " Verified Relationship Type field is a required field", true, true);
			} else {
				reportStatusFail(methodName + "Relationship Type field is not a required field", true);
			}

			// Select Relationship Type
			sf.seleU.clickElementByJSE(sf.liMAcc.selectRelationshipType);
			sf.dataInput.relationshipType_PrepareOptions();

			// Verify Relation Type Options
			boolean isRelationValid = true;
			for (int j = 0; j < sf.liMAcc.relationshipTypeOptionsAll.size(); j++) {

				if (sf.dataInput.relationshipTypesOptions.contains(sf.seleU
						.getTextFromWebElement(sf.liMAcc.relationshipTypeOptionsAll.get(j)).trim().split("/")[0])) {
					isRelationValid = true;
				} else {
					isRelationValid = false;
					break;
				}
			}

			if (isRelationValid
					&& sf.dataInput.relationshipTypesOptions.size() == sf.liMAcc.relationshipTypeOptionsAll.size()) {
				reportStatusPass(methodName + " Verified relationType Options are valid", true, true);

			} else {
				reportStatusFail(methodName + "Invalid Relationship Type Options", true);
			}

			// Select Relationship Type
			sf.dataInput.relationshipTypeSelected = sf.seleU
					.getTextFromWebElement(sf.liMAcc.relationshipTypeOptionsAll.get(0));
			sf.seleU.clickElementByJSE(sf.liMAcc.relationshipTypeOptionsAll.get(0));
			reportStatusPass(methodName + " Selected relationshipType as " + sf.dataInput.relationshipTypeSelected,
					true, true);

			// Click on Next Button and accept alert Okay
			sf.seleU.clickElementByJSE(sf.liMAcc.accountDuplicateNextButton);
			sf.seleU.clickElementByJSE(sf.liMAcc.alertOkButton);
			reportStatusPass(methodName + " Clicked on Next Button", true, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Linking Master Account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Master Account for delink
	 * 
	 *                     Click on Delink Account Next Button
	 */
	public void delinkAccount() throws IOException {
		try {

			String methodName = "SFDC_Link_MasterAccount@: ";

			// Select Master Account for delink
			sf.seleU.clickElementByJSE(sf.liMAcc.addSelectIconAllRows.get(0));
			reportStatusPass(methodName + "Selected Master Account for delink", true, false);

			// Click on Delink Account Next Button
			sf.seleU.clickElementByJSE(sf.liMAcc.delinkAccountNextButton);
			reportStatusPass(methodName + "Clicked on Delink Account Next Button", true, false);

			verifyFieldPresent(" Relationship Removed Msg", sf.liMAcc.relationshipRemovedMsg);

		} catch (Throwable e) {
			reportStatusFail(" Error in Delinking Account", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is present
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is Displayed ", true, true);
			} else {
				reportStatusFail("Validated " + fieldName + " is not Displayed ", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
