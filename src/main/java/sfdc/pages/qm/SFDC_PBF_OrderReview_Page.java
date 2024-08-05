package sfdc.pages.qm;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:13 Aug 2021
 *
 *         SFDC Persona Based Buy Flow Page
 * 
 */
public class SFDC_PBF_OrderReview_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_PBF_OrderReview_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_PBF_OrderReview@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify E-Signature Section
	 * 
	 */
	public void verifyESigSection() throws IOException {
		try {

			sf.seleU.wait(5000);

			verifyFieldDisplayed("E-Signature Section", sf.orderRevPBF.eSignatureSection);

			verifyFieldDisplayed("Require E-Signature Option", sf.orderRevPBF.needESignatureRadio);

			verifyFieldDisplayed("E-Signature not required Option", sf.orderRevPBF.noESignatureRadio);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying E-Signature section is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on E-Sig required radio button Send for Approval
	 * 
	 */
	public void sendQuoteForESigApproval() throws IOException {
		try {

			sf.seleU.wait(6000);

			sf.seleU.clickElementByJSE(sf.orderRevPBF.needESignatureRadio);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("E-Signature required Message", sf.orderRevPBF.eSignatureRequiredMessage);
			sf.seleU.clickElementByJSE(sf.orderRevPBF.sendForApprovalButton);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + "Clicked on Send for Approval after selecting e-sig required", true, true);
		} catch (Throwable e) {
			reportStatusFail(methodName + "Selecting Yes from E-Signature section is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify SSite COntact
	 * 
	 */
	public static void verifySiteContact() throws IOException {
		try {

			sf.seleU.wait(5000);

			verifyFieldValue("Site Contact Name", sf.orderRevPBF.siteContactName,
					InputData_Communities.commPBFContactFirstName + " " + InputData_Communities.commPBFContactLastName);

			// verifyFieldValue("Site Contact Phone Number",
			// sf.orderRevPBF.siteContactPhone,
			// InputData_Communities.commPBFContactPhoneNumber);

			verifyFieldValue("Site Contact Email Address", sf.orderRevPBF.siteContactEmail,
					InputData_Communities.commPBFContactEmailID);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Site COntactm section is unscuccesfull", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
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
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
