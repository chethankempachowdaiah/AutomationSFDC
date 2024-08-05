package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date 01/05/2020
 * 
 *         Change Legal Name(Account>Change Legal Name)
 *
 */
public class SFDC_ChangeLegalName_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;

	public SFDC_ChangeLegalName_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Fields in Change legal name Page
	 * 
	 *                     update legal name and upload evidence
	 * 
	 *                     Click on next
	 * 
	 *                     Verify Legal Name change success
	 */
	public void changeLegalName() throws IOException {
		try {

			String methodName = "SFDC_Change Legal Name@: ";

			String phoneInExpectedFormat = "(" + sf.dataInput.phoneNumber.substring(0, 3) + ")" + " "
					+ sf.dataInput.phoneNumber.substring(3, 6) + "-" + sf.dataInput.phoneNumber.substring(6, 10);

			// Verify Fields in Change legal name Page
			verifyFieldValue("Business Account Name", sf.cln.accountNameValue, sf.dataInput.businessAccountName);
			verifyFieldValue("Business Account Legal Name", sf.cln.legalNameInput.get(0),
					sf.dataInput.businessAccountLegalName);

			verifyFieldValue("Business Account Owner", sf.cln.accountOwnerValue, InputData.username);			
			verifyFieldValue("Business Account Phone", sf.cln.phoneValue, phoneInExpectedFormat);
			verifyFieldValue("Business Account Street", sf.cln.streetValue, sf.dataInput.addressStreet.split(" ")[0]);
			verifyFieldValue("Business Account City", sf.cln.cityValue, sf.dataInput.addressCity);
			verifyFieldValue("Business Account Province", sf.cln.provinceValue, sf.dataInput.addressState);
			verifyFieldValue("Business Account Country", sf.cln.countryValue, sf.dataInput.addressCountry);
			verifyFieldValue("Business Account PostalCode", sf.cln.postalCodeValue, sf.dataInput.addressPostalCode);

			// update legal name and upload evidence
			sf.seleU.clearAndEnterText(sf.cln.legalNameInput.get(0), sf.dataInput.updatedBusinessAccountLegalName);
			sf.seleU.enterText(sf.cln.uploadLetterOfAssignmentInput, Constants.SAMPLE_UPLOAD_FILE);
			reportStatusPass(methodName + "Changed legal name and Uploaded letter of assignment for legal name change",
					true, false);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.cln.legalNameChangeNext);
			reportStatusPass(methodName + "Clicked on legal name change  Next Button", true, false);
			sf.seleU.hardwait(5000);

			if (sf.seleU.isElementPresent(sf.cln.proceedWithLegalNameChangeRadio)) {
				sf.seleU.clickElementByJSE(sf.cln.proceedWithLegalNameChangeRadio);
				sf.seleU.clickElementByJSE(sf.cln.legalNameDuplicateListNextButton);
				reportStatusPass(
						methodName + " Selected Duplicate validation option 'Proceed with the legal name change.'",
						true, false);
				sf.seleU.hardwait(5000);
			}

			// Verify Legal Name change success
			sf.seleU.switchToElementFrame(sf.cln.legalNameChangeSubmittedSuccess);
			sf.seleU.wait(4000);
			verifyFieldValue("Legal Name change success Msg", sf.cln.legalNameChangeSubmittedSuccess.get(0),
					sf.dataInput.businessAccountName);
			sf.seleU.clickElementByJSE(sf.cln.reviewLegalNameChangeNext);
			reportStatusPass(methodName + "Clicked on legal name change Review Next Button", true, false);
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying and Changing the legal name", e);
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
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)
					|| expectedText.contains(sf.seleU.getTextFromWebElement(element))) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + sf.seleU.getTextFromWebElement(element)
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
