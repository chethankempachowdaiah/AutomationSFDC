package sfdc.pages.qm;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date: 22/Feb/2021
 *
 *         SFDC Guided By Journey Provisioning Details Page
 */
public class SFDC_GuidedByJourney_IBLC_Provisioning_Details_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_GuidedByJourney_IBLC_Provisioning_Details_Page() {
		methodName = "SFDC_CPQ_Guided_By_Journey_Provisioning@: ";
		sf = new SFDC_AllPageObjects();

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify Provisioning Details Page
	 */
	public void verifyProvisioningDetailsPage() throws IOException {

		try {

			sf.seleU.robotPressKey(2, KeyEvent.VK_TAB);
			sf.seleU.wait(10000);

			// Verify Provisioning page is correctly Displayed
			verifyFieldDisplayed("Provisioning Details Page Header", sf.gbjProv.provisioningDetailsHeader);
			verifyFieldDisplayed("Provisioning Details Page SubHeader", sf.gbjProv.provisioningDetailsSubHeader);
			verifyFieldDisplayed("Provisioning Details - Business Phone Header", sf.gbjProv.businessPhoneLineHeader);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error verifying Provisioning Details page in IBLC in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Fill Provisioning Details for Order
	 */
	@SuppressWarnings("static-access")
	public void fillProvisioningLineAndPortDetails(Hashtable<String, String> dataTable) throws IOException {
		try {

			int completionImageIcon = 0;
			String tagClass = null;

			sf.seleU.robotClick(sf.home.applauncher);

			// Expand all Business Phone Lines Sections
			for (int i = 0; i < sf.gbjProv.businessPhoneLinesExpand.size(); i++) {

				sf.seleU.clickElementByJSE(sf.gbjProv.businessPhoneLinesExpand.get(i));
				sf.seleU.wait(3000);
			}

			// Check how many business phone lines are there to edit and fill provisioning
			// details if present
			if (sf.seleU.isElementDisplayed(sf.gbjProv.editPhoneLineButton.get(0))) {

				reportStatusPass(methodName + " There are " + sf.gbjProv.editPhoneLineButton.size()
						+ " Business Phone Lines to edit.", true, false);
				for (int i = 0; i < sf.gbjProv.editPhoneLineButton.size(); i++) {

					sf.seleU.clickElementByJSE(sf.gbjProv.editPhoneLineButton.get(i));
					reportStatusPass(methodName + " Clicked on Edit button for Business Line " + i, true, false);
					sf.seleU.wait(3000);
					verifyFieldDisplayed("Edit Business Phone Line Dialog Box", sf.gbjProv.dialogBox);
					verifyFieldDisplayed("Edit Business Phone Line Dialog Header",
							sf.gbjProv.editBusinessPhoneDialogHeader);
					verifyFieldDisplayed("Edit Business Phone Line Dialog - Line Details SubHeader",
							sf.gbjProv.lineDetailsHeader);

					sf.seleU.robotPressKey(2, KeyEvent.VK_TAB);
					sf.seleU.wait(10000);

					if (dataTable.get("IBLC_Number Type").equalsIgnoreCase(sf.dataInput.iblcNumberTypePorted)) {

						// sf.seleU.moveToThenSlowClickElement(sf.gbjProv.phoneInputBox.get(0));
						sf.seleU.clickOnElement(sf.gbjProv.phoneInputBox.get(0));
						sf.seleU.wait(3000);
						// sf.seleU.clickElementByJSE(sf.gbjProv.phoneInputBox.get(0));
						// sf.seleU.wait(3000);
						sf.seleU.robotPressKey(10, KeyEvent.VK_3);

						sf.seleU.wait(5000);
						reportStatusPass(methodName + "Entered Phone Number as IBLC Number Type is "
								+ sf.dataInput.iblcNumberTypePorted, true, false);
						for (int j = 1; j < sf.gbjProv.numberTypeDropdown.size(); j++) {
							sf.seleU.ScrolltoElement(sf.gbjProv.numberTypeDropdown.get(j));
							sf.seleU.wait(2000);
							sf.seleU.clickOnElement(sf.gbjProv.numberTypeDropdown.get(j));
							sf.seleU.wait(2000);
							sf.seleU.clickOnElement(sf.gbjProv.numberTypeValueNative.get(j));
							reportStatusPass(methodName + "Choose Distinctive Line Number type as "
									+ sf.dataInput.iblcNumberTypeNative, true, false);
						}

					} else {
						for (int j = 1; j < sf.gbjProv.numberTypeDropdown.size(); j++) {

							sf.seleU.ScrolltoElement(sf.gbjProv.numberTypeDropdown.get(j));
							sf.seleU.clickOnElement(sf.gbjProv.numberTypeDropdown.get(j));
							sf.seleU.wait(2000);
							sf.seleU.clickOnElement(sf.gbjProv.numberTypeValuePorted.get(j));
							sf.seleU.wait(2000);
							// sf.seleU.moveToThenSlowClickElement(sf.gbjProv.phoneInputBox.get(j - 1));
							sf.seleU.clickOnElement(sf.gbjProv.phoneInputBox.get(j - 1));
							sf.seleU.wait(3000);
							// sf.seleU.clickElementByJSE(sf.gbjProv.phoneInputBox.get(j - 1));
							// sf.seleU.wait(3000);
							sf.seleU.robotPressKey(10, KeyEvent.VK_3);

							sf.seleU.wait(5000);
							reportStatusPass(methodName + " Entered Phone Number for Distinctive Line, Number Type is "
									+ sf.dataInput.iblcNumberTypePorted, true, false);
						}
					}
					if (sf.seleU.isElementDisplayed(sf.gbjProv.linePurposeDropdown)) {
						sf.seleU.ScrolltoElement(sf.gbjProv.linePurposeDropdown);
						sf.seleU.clickOnElement(sf.gbjProv.linePurposeDropdown);
						sf.seleU.wait(2000);
						chooseLinePurpose(dataTable.get("Line_Purpose_Value"));
						sf.seleU.clearAndEnterText(sf.gbjProv.callerIDNameInput,
								AdditionalUtilities.generateRandomCharacters(5));
						reportStatusPass(methodName + " Entered Caller ID Name ", true, false);

					}

					sf.seleU.wait(2000);
					sf.seleU.ScrolltoElement(sf.gbjProv.saveButtonOnDialogBox);
					sf.seleU.clickOnElement(sf.gbjProv.saveButtonOnDialogBox);
					reportStatusPass(methodName + " Saved Line Details", true, false);

					sf.seleU.wait(3000);
					verifyFieldDisplayed("Edit Completion for Business Line - Image Icon",
							sf.gbjProv.dataCompletionInlineImage, i);
					completionImageIcon++;

				}

				// Edit Lines for Porting Requests
				if (sf.seleU.isElementDisplayed(sf.gbjProv.portingRequestLinesExpand)) {
					sf.seleU.clickElementByJSE(sf.gbjProv.portingRequestLinesExpand);
					reportStatusPass(methodName + " There are " + sf.gbjProv.editPortingRequestButton.size()
							+ " Port Request Lines to edit.", true, false);
					for (int i = 0; i < sf.gbjProv.editPortingRequestButton.size(); i++) {

						sf.seleU.clickElementByJSE(sf.gbjProv.editPortingRequestButton.get(i));
						reportStatusPass(methodName + "Clicked on Edit button for Port Request Line " + i, true, false);
						sf.seleU.wait(4000);
						verifyFieldDisplayed("Edit Port Request Line Dialog Box", sf.gbjProv.dialogBox);
						verifyFieldDisplayed("Edit Port Request Line Dialog Header",
								sf.gbjProv.editPortingRequestDialogHeader);
						sf.seleU.clickOnElement(sf.gbjProv.dslDropdown);
						sf.seleU.wait(2000);
						if (dataTable.get("DSL_Value").equalsIgnoreCase(sf.dataInput.iblcDSLValueYes))
							sf.seleU.clickOnElement(sf.gbjProv.dslValueYes);
						else
							sf.seleU.clickOnElement(sf.gbjProv.dslValueNo);
						sf.seleU.wait(2000);
						reportStatusPass(methodName + "Choosen DSL Type as : " + dataTable.get("DSL_Value"), true,
								false);
						sf.seleU.clearAndEnterText(sf.gbjProv.serviceProviderInputBox,
								dataTable.get("Service_Provider"));
						reportStatusPass(methodName + "Entered Service Provider " + dataTable.get("Service_Provider"),
								true, false);

						sf.seleU.wait(3000);
						sf.seleU.ScrolltoElement(sf.gbjProv.saveButtonOnDialogBox);
						sf.seleU.clickElementByJSE(sf.gbjProv.saveButtonOnDialogBox);
						reportStatusPass(methodName + "Saved Line Details", true, false);

						sf.seleU.wait(2000);
						verifyFieldDisplayed("Edit Completion for Business Line - Image Icon",
								sf.gbjProv.dataCompletionInlineImage, i + completionImageIcon);
					}

				}
				verifyFieldNotDisplayed("Line Incomplete Warning Image Icon", sf.gbjProv.warningForIncompleteDataImage,
						0);

			} else {
				reportStatusPass(methodName + " There are no Business Phones Lines to edit", true, false);
			}

		} catch (Throwable e) {
			reportStatusFail(
					methodName + " Error While filling line and port info on Provisioning Details in IBLC in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Fill Provisioning Details -Upload Invoice
	 */
	public void uploadInvoiceOnProvisioningPage() throws IOException {
		try {
			// Upload File in Invoice Attachments Section if exists
			if (sf.seleU.isElementDisplayed(sf.gbjProv.invoiceAtachmentsSection)) {
				reportStatusPass(methodName + " Upload File to Invoice Attachments Section", true, false);
				sf.seleU.ScrolltoElement(sf.gbjProv.invoiceAttachmentUploadButton);
				sf.seleU.scrollUpByCoOrdinates();
				uploadFiles(sf.gbjProv.invoiceAttachmentUploadButton);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While uploading invoice on Provisioning Details Page in IBLC in GBJ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Fill Provisioning Details -Upload Emergency Disclaimer
	 *                     Document
	 */
	public void uploadEmergencyDisclaimerOnProvisioningPage() throws IOException {
		try {
			// Upload File in Emergency Disclaimer Attachments Section if exists
			if (sf.seleU.isElementDisplayed(sf.gbjProv.emergencyDisclaimerAttachmentsSection)) {
				reportStatusPass(methodName + " Upload File to Emergency Disclaimer Attachments Section", true, false);
				sf.seleU.ScrolltoElement(sf.gbjProv.emergencyDisclaimerAttachmentsNote);
				uploadFiles(sf.gbjProv.emergencyDisclaimerUploadButton);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName
					+ " Error While uploading emergency disclaimer document on Provisioning Details in IBLC in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Fill Provisioning Details -Directory Details
	 */
	public void fillDirectoryDetailsOnProvisioningPage() throws IOException {

		try {

			// Fill Directory Details if Directory Listing Section exists
			if (sf.seleU.isElementDisplayed(sf.gbjProv.directoryListingSection)) {
				reportStatusPass(methodName + " Fill Details under Directory Listing Section", false, false);
				reportStatusPass(methodName + " Listing Name textBox has value : "
						+ sf.seleU.getTextFromWebElement(sf.gbjProv.listingNameInputBox), true, false);
				sf.seleU.clearAndEnterText(sf.gbjProv.listingNumberInputBox,
						AdditionalUtilities.generateRandomDigits(5));
				sf.seleU.clearAndEnterText(sf.gbjProv.subListingNumberInputBox,
						AdditionalUtilities.generateRandomDigits(5));
				reportStatusPass(methodName + " Entered Listing and SubListing Number.", true, false);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While filling directory info on Provisioning Details in IBLC in GBJ",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @param linePurposeValue
	 * @throws IOException
	 * 
	 *                     Choose Line Purpose Dropdown Value
	 */
	public void chooseLinePurpose(String linePurposeValue) throws IOException {
		try {

			// Choose Line Purpose Value From DropdDown
			if (linePurposeValue.equals(sf.dataInput.iblcLinePurposeValueSecurity))
				sf.seleU.clickOnElement(sf.gbjProv.linePurposeValueSecurity);

			if (linePurposeValue.equals(sf.dataInput.iblcLinePurposeValueElevator))
				sf.seleU.clickOnElement(sf.gbjProv.linePurposeValueElevator);

			if (linePurposeValue.equals(sf.dataInput.iblcLinePurposeValueAlarm))
				sf.seleU.clickOnElement(sf.gbjProv.linePurposeValueAlarm);

			reportStatusPass(methodName + "Choosen Line Purpose Value from Dropdown : " + linePurposeValue, true,
					false);
			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error While Choosing a Line Purpose Value in IBLC in GBJ", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public static void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
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

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public static void verifyFieldDisplayed(String fieldName, List<WebElement> element, int i) throws IOException {
		try {
			try {
				if (sf.seleU.isElementDisplayed(element.get(i))) {
					reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
				} else {
					reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
				}
			} catch (IndexOutOfBoundsException ex) {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is not displayed
	 */
	public static void verifyFieldNotDisplayed(String fieldName, List<WebElement> element, int i) throws IOException {
		try {
			try {
				if (sf.seleU.isElementDisplayed(element.get(i))) {
					reportStatusFail(methodName + " " + fieldName + " is displayed, It should not be present", true);

				} else {
					reportStatusPass(methodName + " Validated " + fieldName + " is not displayed", true, true);
				}
			} catch (IndexOutOfBoundsException ex) {
				reportStatusPass(methodName + " Validated " + fieldName + " is not displayed", true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Uploads Files from particular section
	 * 
	 */
	public void uploadFiles(WebElement filesUploadButton) throws IOException {

		try {

			// Click on Upload Files
			sf.seleU.clickOnElement(filesUploadButton);
			reportStatusPass(methodName + " Uploading new file", false, false);
			System.out.println("2");
			sf.seleU.hardwait(6000);

			Runtime.getRuntime().exec(
					Constants.RESOURCES_DIR + "\\fileSelector.exe" + " " + Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE);
			sf.seleU.hardwait(16000);

			reportStatusPass(
					methodName + " Trying to upload "
							+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE),
					false, false);

			sf.seleU.waitElementToBeClickable(sf.files.filesUploadDoneButton);

			// Verify File was uploaded successfully and Click done once uploaded
			if (sf.seleU.getTextFromWebElement(sf.files.filesUploadedtext).equalsIgnoreCase("1 of 1 file uploaded")) {
				reportStatusPass(methodName + " File "
						+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE)
						+ " uploaded successfully", true, true);
			} else {
				reportStatusFail(methodName + " Could not upload file "
						+ AdditionalUtilities.getFileName(Constants.SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE), true);
			}
			sf.seleU.waitElementToBeClickable(sf.files.filesUploadDoneButton);
			sf.seleU.clickElementByJSE(sf.files.filesUploadDoneButton);
			reportStatusPass(methodName + " Clicked on Done", false, false);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in uploading file", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify field is not displayed
	 */
	public void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {

		try {
			// Verify field is not displayed
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Verified " + fieldName + " is not displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is displayed, It should not be displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

}
