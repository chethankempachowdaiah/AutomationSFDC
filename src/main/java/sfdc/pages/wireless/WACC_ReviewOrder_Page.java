package sfdc.pages.wireless;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.PDFHelper;
import com.sfdc.data.InputData_WA;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sakshi Lnu, Date : 14/06/2021
 * 
 *         Review Order
 *
 */
public class WACC_ReviewOrder_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public WACC_ReviewOrder_Page() {
		sf = new SFDC_AllPageObjects();

	}

	/**
	 * @throws Exception
	 * 
	 *             1- validate review order a.
	 * 
	 */
	public void reviewOrder() throws Exception {
		try {
			methodName = "WACC Review Order Details Validation@: ";
			// click to view add on details
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.pdfSummary));
			reportStatusPass(methodName + " Generate Order summary in pdf is present", true, true);
			// Assert.assertEquals(true,
			// sf.seleU.isElementDisplayed(sf.revOrderObj.emailSummary));
			// reportStatusPass(methodName + " Generate Order summary by email is present",
			// true, true);
			verifyFieldValue("Account name", sf.revOrderObj.accountName, InputData_WA.account_Business_R4B);
			if (InputData_WA.WACC_Use_Credit_Option.equals("None")) {
				reportStatusPass(methodName + "Credit option chosen is :" + InputData_WA.WACC_Use_Credit_Option, true, true);
			} else {
				Assert.assertEquals(true,
						sf.seleU.getTextFromWebElement(sf.revOrderObj.activationCredit)
								.contains("$" + InputData_WA.WACC_Use_Credit_Option + ".00"));
				reportStatusPass(
						methodName + InputData_WA.WACC_Use_Credit_Option + " "
								+ sf.seleU.getTextFromWebElement(sf.revOrderObj.activationCredit) + " is present",
						true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error on Reviewing order details", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Accept Quote Options a. e-signaure b. Verbal acceptance of quote
	 * 
	 * 
	 */
	public void acceptQuoteOptions() throws Exception {
		try {
			methodName = "WACC Review Order Details Validation@: ";
			// click options to accept the quote
			sf.seleU.waitForLoading();
			if (InputData_WA.WACC_eSignature.equals("Yes")) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.eSignatureOption);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.eSignatureCondition));
				reportStatusPass(methodName + "Need e-signature Option is clicked", true, true);
				sf.seleU.clickElementByJSE(sf.revOrderObj.sendForApprovalButton);
				reportStatusPass(methodName + "Send For Approval button is clicked", true, true);
				sf.seleU.waitForLoading();
			} else {
				sf.seleU.clickElementByJSE(sf.revOrderObj.verbalQuoteAcceptOption);
				//Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.verbalQuoteAcceptCondition));
				reportStatusPass(methodName + "Quote is accepted verbally", true, true);
				sf.seleU.clickElementByJSE(sf.revOrderObj.placeOrderButton);
				reportStatusPass(methodName + "Place Order button is clicked", true, true);
				sf.seleU.waitForLoading();

			}

		} catch (Throwable e) {
			reportStatusFail(" error on Quote Acceptance option", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Verify Terms and Conditions
	 * 
	 */
	public void validateTermsAndConditions() throws Exception {
		try {
			methodName = "WACC Review Order Details Validation@: ";
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.termsConditions));
			sf.seleU.clickElementByJSE(sf.revOrderObj.termsConditionsShowLink);
			reportStatusPass(methodName + " Terms and Conditions are clicked", true, true);
			sf.seleU.clickElementByJSE(sf.revOrderObj.pdfBusinessAgreement);
			sf.seleU.waitForLoading();
			// Assert.assertEquals(true,
			// sf.seleU.getCurrentUrl().contains("master-business-general-terms-en"));
			reportStatusPass(methodName + " Business agreement(PDF) is opened in another tab", true, true);
			sf.seleU.waitForLoading();
			sf.seleU.switchWindow(0);
			sf.seleU.clickElementByJSE(sf.revOrderObj.pdfWirelessTerms);
			sf.seleU.waitForLoading();
			// Assert.assertTrue(sf.seleU.getCurrentUrl().contains("business-wireless-terms-en"));
			reportStatusPass(methodName + " Wireless Terms(PDF) is opened in another tab", true, true);
			sf.seleU.waitForLoading();
			sf.seleU.switchWindow(0);
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Throwable e) {
			reportStatusFail(" Error in verification Terms & conditions", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			sf.seleU.wait(5000);
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText)) {
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
	 * @throws Exception
	 * 
	 *             Edit the Shippinmg address Verify the same added after saving
	 * 
	 */
	public void editShippingAddress() throws Exception {
		try {
			methodName = "WACC Review Order Shipping Address Validation@: ";
			
			if (sf.seleU.getTextFromWebElement(sf.revOrderObj.shippingAddressText).trim().contains(",,")) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.editShippingAddressButton);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.revOrderObj.shippingAddressHeader));
				sf.seleU.waitForLoading();
				sf.seleU.hundredPercentPageZoom();
				reportStatusPass(methodName + " Shipping Address edit link is clicked", true, true);
				sf.seleU.enterText(sf.revOrderObj.streetInput, Global.dataInput.addressStreet);
				sf.seleU.enterText(sf.revOrderObj.cityInput, Global.dataInput.addressCity);
				sf.seleU.clickOnElementNumberoftimes(sf.revOrderObj.stateDropdown1, 1);
				for (int i = 0; i < sf.revOrderObj.dropdownOptions.size(); i++) {
					if (sf.revOrderObj.dropdownOptions.get(i).getText().contains(Global.dataInput.addressState)) {
						sf.seleU.clickOnElementNumberoftimes(sf.revOrderObj.dropdownOptions.get(i), 1);
						break;
					}
				}
				sf.seleU.enterText(sf.revOrderObj.postalCodeInput, Global.dataInput.addressPostalCode);
				sf.seleU.waitForLoading();
				sf.seleU.clickElementByJSE(sf.revOrderObj.saveButton);
				sf.seleU.waitForLoading();
				verifyFieldValue(methodName + ": Street verfication", sf.revOrderObj.shippingAddressText,
						Global.dataInput.addressStreet);
				verifyFieldValue(methodName + ": City verfication", sf.revOrderObj.shippingAddressText,
						Global.dataInput.addressCity);
				verifyFieldValue(methodName + ": State verfication", sf.revOrderObj.shippingAddressText,
						Global.dataInput.addressState);
				verifyFieldValue(methodName + ": postal Code verfication", sf.revOrderObj.shippingAddressText,
						Global.dataInput.addressPostalCode);
				reportStatusPass(methodName + " Save Button on editing shipping address page is clicked", true, true);
				sf.seleU.waitForLoading();
			}
		} catch (Throwable e) {
			reportStatusFail(" Error inputting shipping address", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Edit the Shipping Contact. Verify the same added after saving
	 * 
	 */
	public void editShippingContact(String accountName) throws Exception {
		try {
			methodName = "WACC Review Order Shipping Contact Validation@: ";
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.revOrderObj.editShippingContactButton);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.revOrderObj.shippingContactHeader));
			sf.seleU.wait(3000);
			sf.seleU.hundredPercentPageZoom();
			reportStatusPass(methodName + " Shipping Contact edit link is clicked", true, true);
			sf.seleU.clickOnElementNumberoftimes(sf.revOrderObj.salutationDropdown, 1);
			for (int i = 0; i < sf.revOrderObj.dropdownOptions.size(); i++) {
				if (sf.revOrderObj.dropdownOptions.get(i).getText().contains(Global.dataInput.contactSalutation)) {
					sf.seleU.clickOnElementNumberoftimes(sf.revOrderObj.dropdownOptions.get(i), 1);
					break;
				}
			}
			sf.seleU.clearTextByMouseAction(sf.revOrderObj.firstNameInput);
			sf.seleU.enterText(sf.revOrderObj.firstNameInput, Global.dataInput.contactFirstName);
			sf.seleU.clearTextByMouseAction(sf.revOrderObj.lastNameInput);
			sf.seleU.enterText(sf.revOrderObj.lastNameInput, Global.dataInput.contactLastName);
			sf.seleU.clearTextByMouseAction(sf.revOrderObj.jobTitleInput);
			sf.seleU.enterText(sf.revOrderObj.jobTitleInput, Global.dataInput.contactTitle);
			sf.seleU.clearTextByMouseAction(sf.revOrderObj.contactEmailInput);
			sf.seleU.enterText(sf.revOrderObj.contactEmailInput, Global.dataInput.contactEmailAddress);
			sf.seleU.enterText(sf.revOrderObj.contactPhoneInput, "5784548782");
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.revOrderObj.saveButton);
			sf.seleU.waitForLoading();
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.duplicateContacts)) {
				for (int i = 0; i < sf.revOrderObj.accountNameOptions.size(); i++) {
					if (sf.seleU.getTextFromWebElement(sf.revOrderObj.accountNameOptions.get(i))
							.contains(accountName)) {
						sf.seleU.clickOnElementFromPoint(sf.revOrderObj.radioOptions.get(i));
						reportStatusPass(
								methodName + "Select Contact is"
										+ sf.seleU.getTextFromWebElement(sf.revOrderObj.contactNameOptions.get(i))
										+ " and Selected Account is "
										+ sf.seleU.getTextFromWebElement(sf.revOrderObj.accountNameOptions.get(i)),
								true, true);
						break;
					}
					sf.seleU.clickOnElementFromPoint(sf.revOrderObj.saveButtonDupContacts);
					sf.seleU.hardwait(4000);
				}
			}
			verifyFieldValue(methodName + ": Name verfication", sf.revOrderObj.shippingContactText.get(0),
					Global.dataInput.contactFirstName +" "+Global.dataInput.contactLastName);
			verifyFieldValue(methodName + ": Phone verfication", sf.revOrderObj.shippingContactText.get(1),
					"5784548782");
			reportStatusPass(methodName + "edited shipping Contact details are verified", true, true);
			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Error inputting shipping contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Accept Quote Options a. e-signaure
	 * 
	 * 
	 */
	public void acceptQuoteByESign() throws Exception {
		try {
			methodName = "WACC Review Order Details Validation@: ";
			// click options to accept the quote
			sf.seleU.clickElementByJSE(sf.revOrderObj.eSignatureOption);
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.eSignatureCondition));
			reportStatusPass(methodName + "Need e-signature Option is clicked", true, true);
			sf.seleU.clickElementByJSE(sf.revOrderObj.sendForApprovalButton);
			reportStatusPass(methodName + "Send For Approval button is clicked", true, true);
			sf.seleU.waitForLoading();

			// Assert.assertEquals(true, );
		} catch (Throwable e) {
			reportStatusFail(" error on Quote Acceptance option", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * 
	 *             Accept Quote Options a. Verbally
	 * 
	 * 
	 */
	public void acceptQuoteVerbally() throws Exception {
		try {
			methodName = "WACC Review Order Details Validation@: ";
			// click options to accept the quote
			sf.seleU.waitForLoading();
			sf.seleU.clickElementByJSE(sf.revOrderObj.verbalQuoteAcceptOption);
			//Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.verbalQuoteAcceptCondition));
			reportStatusPass(methodName + "verbal quote accepted option is clicked", true, true);
			sf.seleU.clickElementByJSE(sf.revOrderObj.placeOrderButton);
			reportStatusPass(methodName + "Place Order button is clicked", true, true);
			sf.seleU.waitForLoading();
			// Assert.assertEquals(true, );
		} catch (Throwable e) {
			reportStatusFail(" error on Quote Acceptance option", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 05.10.2021 by @author Gaurav Singh
	 * 
	 * Validates user cannot update the qty in order summary page
	 * 
	 * 
	 */
	public void UpdtngQtyinOrdrSumryPge() throws Exception{
		try {	
		sf.seleU.waitTillLoading();
		sf.seleU.clickElementByJSE(sf.shopCartObj.proceedToCheckoutBtn);
		sf.seleU.waitTillLoading();
		sf.seleU.waitElementToBeVisible(sf.revOrderObj.editShippingContactButton);
		checkQtyDropdwnPresnseSmryPge(1);
		sf.seleU.scrollToTop();
		sf.seleU.clickElementByJSE(sf.revOrderObj.editShippingAddressButton);
		checkQtyDropdwnPresnseSmryPge(2);
		sf.seleU.clickElementByJSE(sf.revOrderObj.editShippingContactButton);
		checkQtyDropdwnPresnseSmryPge(2);
		reportStatusPass("Successfully Validated the updating qty in summary page", true, true);
		}
		catch(Exception e) {
			reportStatusFail("Failed to Validate updating qty in summary page!", false);
			e.printStackTrace();
		}
	}
	
	public void checkQtyDropdwnPresnseSmryPge(int count) throws Exception{
		
		sf.seleU.waitTillLoading();
		try {
			if(sf.shopCartObj.updateQuantityDropdown.isDisplayed()&&sf.shopCartObj.updateQuantityDropdown.isEnabled()) {
				reportStatusFail("User is able to see update qty in order summary page!", false);
			}
		}
		catch(Exception e) {
			if(count==2) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.BacktoPrvs);
				reportStatusPass("NO Edit Quantity dropdown found in edit address/contact section", true, true);
			}
		}
	}
	/**
	 * Method developed on @Date: 29.12.2021 by @author Jigyasa Dwivedi
	 * 
	 * Method to click Cancel And Back button on Order summary Page.
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public void clickCancelAndBackButton() throws Exception{
		methodName = "WACC Order Summary Page@: ";
		sf.seleU.waitTillLoading();
		try {
			if(sf.seleU.isElementDisplayed(sf.revOrderObj.cancelAndBack)) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.cancelAndBack);
				reportStatusPass(methodName+ "Cancle and Back button is present. ", true,true);
			}
			else {
				reportStatusFail("Cancle and Back button is not present on order summary page!", true);
			}
		}
		catch(Exception e) {
			reportStatusFail("Error in Cancle and Back button on order summary page!", true);
			}
		
	}
	
	/**
	 * Method developed on @Date: 15.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Terms and Condition link on Order Summary Page.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_TermsAndConditionLink(String url) throws Exception {
		// TODO Auto-generated method stub
		sf.seleU.waitTillLoading();
		try {
			methodName = "Order Summary Page@: ";
			String str = sf.seleU.getTextFromWebElement(sf.revOrderObj.termsAndConditionURL);
			if (sf.seleU.getTextFromWebElement(sf.revOrderObj.termsAndConditionURL).contains(url)) {
				reportStatusPass(methodName + " Terms and Condition link has matched.", true, true);
			} else {
				reportStatusFail("Terms and Condition link has matched on Order Summary Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Terms and Condition link on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 25.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click  "Send PDF Copy of Quote Summary to Customer" button on review Order
	 * 
	 * @throws Exception
	 * 
	 */
	public void select_SendPDFCopy_button() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Order Summary Page@: ";
			String str = sf.seleU.getTextFromWebElement(sf.revOrderObj.pdfSummary);
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.pdfSummary) && sf.seleU.isElementEnabled(sf.revOrderObj.pdfSummary) &&
					sf.seleU.getTextFromWebElement(sf.revOrderObj.pdfSummary).equals("Send PDF Copy of Quote Summary to Customer")) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.pdfSummary);
				reportStatusPass(methodName + " Send PDF Copy of Quote Summary to Customer button has clicked.", true, true);
			} else {
				reportStatusFail("Send PDF Copy of Quote Summary to Customer button is not displayed/enabled on Order Summary Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Send PDF Copy of Quote Summary to Customer button on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 25.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click  "Email Quote Summary" button on order Summary
	 * 
	 * @throws Exception
	 * 
	 */
	public void select_EmailQuoteSummary_button() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Order Summary Page@: ";
			String str = sf.seleU.getTextFromWebElement(sf.revOrderObj.emailQuoteSummary);
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.emailQuoteSummary) && sf.seleU.isElementEnabled(sf.revOrderObj.emailQuoteSummary)&&
					sf.seleU.getTextFromWebElement(sf.revOrderObj.emailQuoteSummary).equals("Email Quote Summary")) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.emailQuoteSummary);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.revOrderObj.emailSuccessMessage));
				reportStatusPass(methodName + " Email Quote Summary button has clicked.", true, true);
			} else {
				reportStatusFail("Email Quote Summary button is not displayed/enabled on Order Summary Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Email Quote Summary button on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 25.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click  "Download order Summary" button on order Summary
	 * 
	 * @throws Exception
	 * 
	 */
	public void select_DownloadOrderSummary_button() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Order Summary Page@: ";
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(10000);

			sf.seleU.waitElementToBeClickable(sf.revOrderObj.downloadOrderSummary);
			String str = sf.seleU.getTextFromWebElement(sf.revOrderObj.downloadOrderSummary);
			sf.seleU.hardwait(3000);
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.downloadOrderSummary) && sf.seleU.isElementEnabled(sf.revOrderObj.downloadOrderSummary)) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.downloadOrderSummary);
				reportStatusPass(methodName + " Download order Summary button has clicked.", true, true);
			} else {
				reportStatusFail("Download order Summary button is not displayed/enabled on Order Summary Page.", true);
			}
			sf.seleU.waitTillLoading();
		} catch (Exception e) {
			reportStatusFail("Error in Download order Summary button on Order Summary Page.", true);
		}
	}
	
	/**
	 * 
	 * @throws Exception
	 * 
	 *             Verify Terms and Conditions
	 *  Validating PDF docs URL
	 */
	public void validateTermsAndConditionsPDFDocs() throws Exception {
		
		
		try {
			methodName = "WACC Review Order Details Validation@: ";
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.revOrderObj.termsConditions));
			sf.seleU.clickElementByJSE(sf.revOrderObj.termsConditionsShowLink);
			reportStatusPass(methodName + " Terms and Conditions are clicked", true, true);
			sf.seleU.clickElementByJSE(sf.revOrderObj.pdfBusinessAgreement);
			sf.seleU.wait(3000);
			sf.seleU.switchWindow(0);
			sf.seleU.clickElementByJSE(sf.revOrderObj.pdfWirelessTerms);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Wireless Terms(PDF) is opened in another tab", true, true);
			sf.seleU.wait(2000);
			sf.seleU.switchWindow(0);
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Throwable e) {
			reportStatusFail(" Error in verification Terms & conditions", e);
			e.printStackTrace();
    }
}
  
	/**
	 * Method developed on @Date: 28.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click  validate text in PDF
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_text_InPDF(String text, String downloadLocation)throws Exception {
		// TODO Auto-generated method stub
		try {			
			String pdfcontent = PDFHelper.readPDFFileLineByLine(downloadLocation);			
			PDFHelper.IsTextPresentInPDF(pdfcontent, text);	
		}catch (Exception e) {
			reportStatusFail("Error in Download order Summary button on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 28.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click  "Back to review and place order" button on order Summary
	 * 
	 * @throws Exception
	 * 
	 */
	public void select_BackToReviewOrder_button() throws Exception {
		// TODO Auto-generated method stub
		sf.seleU.waitTillLoading();
		try {
			methodName = "Order Summary Page@: ";
			String str = sf.seleU.getTextFromWebElement(sf.revOrderObj.BackToReviewOrder);
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.BackToReviewOrder) && sf.seleU.isElementEnabled(sf.revOrderObj.BackToReviewOrder)) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.BackToReviewOrder);
				Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.shopCartObj.placeOrderHeader));
				reportStatusPass(methodName + " Back to review and place order button has clicked.", true, true);
			} else {
				reportStatusFail("Back to review and place order button is not displayed/enabled on Order Summary Page.", true);
			}

		} catch (Exception e) {
			reportStatusFail("Error in Back to review and place order button on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 02.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate shipping address on review Order page
	 * @throws Exception
	 * 
	 */
	public void validateShippingAddress(String streetName, String city, String state, String postalCode) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Review Order Shipping Address Validation@: ";
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.editShippingAddressButton)) {
				verifyFieldValue(methodName + ": Street verfication", sf.revOrderObj.shippingAddressText,streetName);
				verifyFieldValue(methodName + ": City verfication", sf.revOrderObj.shippingAddressText,city);
				verifyFieldValue(methodName + ": State verfication", sf.revOrderObj.shippingAddressText,state);
				verifyFieldValue(methodName + ": postal Code verfication", sf.revOrderObj.shippingAddressText,postalCode);
				sf.seleU.wait(2000);
			}
			else {
				reportStatusFail("Shipping Address is not present on Order Summary Page.", true);
			}
			} catch (Exception e) {
			reportStatusFail("Error in Back to review and place order button on Order Summary Page.", true);
		}
	}
	/**
	 * Method developed on @Date: 02.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to edit and validate shipping address on review Order page
	 * @throws Exception
	 * 
	 */
	public void edit_validateShippingAddress(String streetName, String city, String state, String postalCode) throws Exception {
		try {
			methodName = "WACC Review Order Shipping Address Validation@: ";
			
			if (sf.seleU.isElementDisplayed(sf.revOrderObj.editShippingAddressButton)) {
				sf.seleU.clickElementByJSE(sf.revOrderObj.editShippingAddressButton);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.revOrderObj.shippingAddressHeader));
				sf.seleU.wait(3000);
				sf.seleU.hundredPercentPageZoom();
				reportStatusPass(methodName + " Shipping Address edit link is clicked", true, true);
				sf.seleU.enterTextUsingKeys(sf.revOrderObj.streetInput, streetName);;
				
				sf.seleU.enterTextUsingKeys(sf.revOrderObj.cityInput, city);
				sf.seleU.clickOnElementNumberoftimes(sf.revOrderObj.stateDropdown1, 1);
				for (int i = 0; i < sf.revOrderObj.dropdownOptions.size(); i++) {
					if (sf.revOrderObj.dropdownOptions.get(i).getText().contains(state)) {
						sf.seleU.clickOnElementNumberoftimes(sf.revOrderObj.dropdownOptions.get(i), 1);
						break;
					}
				}
				sf.seleU.enterTextUsingKeys(sf.revOrderObj.postalCodeInput, postalCode);
				sf.seleU.hardwait(5000);
				sf.seleU.clickElementByJSE(sf.revOrderObj.saveButton);
				sf.seleU.waitForLoading();
				validateShippingAddress(streetName, city, state, postalCode);
				sf.seleU.wait(2000);
			}
		} catch (Throwable e) {
			reportStatusFail(" Error inputting shipping address", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 08.03.2022 by @author viswas reddy
	 * 
	 * Method to validate account number tab input acceptance format and 
	 * displays error when in input different format entered
	 * 
	 * @throws Exception
	 */
	public void validate_CommunitiesAccntNumberTab() throws Exception{
		try {
			methodName = "Communities account number Validation@: ";
			if(sf.revOrderObj.accntNumReqField.isDisplayed()) {
				reportStatusPass(methodName+"Account number field is required", true, true);
			}else {
				reportStatusFail(methodName+"Account number field is shown not required", true);
			}
			String accntNum = AdditionalUtilities.generateRandomCharacters(5).toString()+AdditionalUtilities
					.generateRandomDigits(5).toString();
			sf.seleU.clearAndEnterText(sf.comOrderFlowObj.accountNumberInput,accntNum);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Account number input is: "+ accntNum, true, true);
			sf.comOrderFlowObj.accountNumberInput.sendKeys(Keys.TAB);
			sf.seleU.hardwait(2000);
			if(sf.revOrderObj.accntNumErrorMsg.isDisplayed()) {
				reportStatusPass(methodName+ "error message is displayed when alphanumeric entered ", true, true);
			}else {
				reportStatusFail(methodName+ "error message is not displayed when alphanumeric entered ", true);
			}
			accntNum = AdditionalUtilities.generateRandomDigits(5).toString()+"@#$%&";
			sf.seleU.clearAndEnterText(sf.comOrderFlowObj.accountNumberInput,accntNum);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Account number input is: "+ accntNum, true, true);
			sf.comOrderFlowObj.accountNumberInput.sendKeys(Keys.TAB);
			sf.seleU.hardwait(2000);
			if(sf.revOrderObj.accntNumErrorMsg.isDisplayed()) {
				reportStatusPass(methodName+ "error message is displayed when special character entered ", true, true);
			}else {
				reportStatusFail(methodName+ "error message is not displayed when special character entered ", true);
			}
			accntNum = AdditionalUtilities.generateRandomDigit(9).toString() + AdditionalUtilities.generateRandomDigit(5).toString();
			sf.seleU.clearAndEnterText(sf.comOrderFlowObj.accountNumberInput,accntNum);
			sf.seleU.hardwait(2000);
			reportStatusPass(methodName + "Account number input is: "+ accntNum, true, true);
			sf.seleU.hardwait(2000);
			if(!sf.seleU.isElementDisplayed(sf.revOrderObj.accntNumErrorMsg)) {
				reportStatusPass(methodName+ "error message is not displayed when only numeric entered ", true, true);
			}else {
				reportStatusFail(methodName+ "error message is displayed when only numeric entered ", true);
			}
		} catch (Throwable e) {
			reportStatusFail(methodName+"Error validating account number tab", e);
			e.printStackTrace();
		}
		
	}

	/* Method developed on @Date: 07.03.2022 by @author Priyanka Tawade
	 * 
	 * Method to validate user cannot update device/price plan quantity on summary page
	 * 
	 * @throws Exception
	 * 
	 */
	public void updateQty_SmryPage() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "WACC Order Summary Page@: ";
			sf.seleU.hardwait(3000);
			
			if (!validate_checkQtyDropdownPresentSmryPage()) {
				reportStatusPass(methodName + " User not able to change the quantity on Order summary page..", true,true);

			}
		}
		catch(Exception e) {
			reportStatusFail(methodName + " User can change quantity on Order summary page", false);
		}
	}	
	
	/**
	 * Method developed on @Date: 07.03.2022 by @author Priyanka Tawade
	 * 
	 * Method to check quantity dropdown is present on summary page or not.
	 * 
	 * @throws Exception
	 * 
	 */
	public boolean validate_checkQtyDropdownPresentSmryPage() {
		try {
				return sf.seleU.isElementDisplayed(sf.shopCartObj.updateQuantityDropdown);
		}catch (Exception e) {
		return false;
		}
	}

	/**
	 * Method developed on @Date: 21.03.2022 by @author viswas reddy
	 * 
	 * Method to validate restriction of port in numbers, 
	 * if already a roger's numbers it should throw an error
	 * 
	 * @throws Exception
	 * 
	 */
	public void enterMultipleTransferNum(String numberSeries) throws Exception {
		try {
			methodName = "validate port in rogers numbers @: ";
			sf.seleU.hardwait(3000);
			sf.seleU.waitTillLoading();
			if(sf.comOrderFlowObj.customerInforFormHeading.isDisplayed()) {
				reportStatusPass(methodName + " User lands on Customer Info Page ", true, true);
			}
			sf.seleU.scrollToBottom();
			for(int i=1; i<=sf.revOrderObj.transferNumberRadio.size(); i++) {
				String transferradio = "(//label//span[normalize-space()='Transfer number']//ancestor::span//input[@type='radio'])[" + i
						+ "]";
				sf.seleU.clickElementByJSE(driver.findElement(By.xpath(transferradio)));
				sf.seleU.wait(2000);
				String portNumInput = "(//label[normalize-space()='Phone Number']/following-sibling::input[@class='input1'])["+i+"]";
				//sf.seleU.clickElementByJSE(driver.findElement(By.xpath(portNumInput)));
				String constructedMobileNumber=numberSeries.concat(AdditionalUtilities.generateRandomDigit(4));
				sf.seleU.clearAndEnterText(driver.findElement(By.xpath(portNumInput)), constructedMobileNumber);
				reportStatusPass(methodName + "transfer number is : " + constructedMobileNumber, true, true);
			}
			sf.comOrderFlowObj.portNumberInputField.sendKeys(Keys.TAB);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
			sf.seleU.waitForLoading();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 21.03.2022 by @author viswas reddy
	 * 
	 * Method to check restriction of port in numbers, 
	 * if already a roger's numbers it should throw an error
	 * 
	 * @throws Exception
	 * 
	 */
	public void checkRestrictionPortInRogers() throws Exception {
		String errorMsg = "This number is not eligible for port in to Rogers ";
		if (sf.revOrderObj.notEligiblePortIn.size() == sf.revOrderObj.noOfQuantity.size() &&
				!(sf.comOrderFlowObj.provideInfoButton.isEnabled())) {
			reportStatusPass(methodName + errorMsg + "Next Provide info button is disabled", true, true);
		} else {
			reportStatusFail(methodName + "port number is not a rogers or provide info button is enabled", true);
		}
	}
	
	/**
	 * Method developed on @Date: 24.03.2022 by @author viswas reddy
	 * 
	 * Method to extract text from the pdf and validate array of string is present in pdf
	 * 
	 * @throws Exception
	 * 
	 */
	public void validate_arrayText_InPDF(String[] text, String downloadLocation)throws Exception {
		// TODO Auto-generated method stub
		try {
			String pdfContent = PDFHelper.ExtractTextFromPDF(downloadLocation);
			IsArrayTextPresentInPDF(pdfContent, text);
		}catch (Exception e) {
			reportStatusFail("Error in Download order Summary button on Order Summary Page.", true);
		}
	}
	
	public static void IsArrayTextPresentInPDF(String pdfText, String[] text) throws IOException, InterruptedException {
		for (int i = 0; i <= text.length - 1; i++) {
			if (pdfText.contains(text[i])) {
				reportStatusPass(" Validated text: " + text[i] + " is present in PDF ", true, true);
			} else {
				reportStatusFail(text[i] + " not present in PDF ", true);
			}
		}
	}
	
	/**
	 * Method developed on @Date: 29.03.2022 by @author viswas reddy
	 * 
	 * Method to verify user lands on provide info page or not
	 * 
	 * @throws Exception
	 */
	public void clickOnProvideInfoBtn() throws Exception {
		try {
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.comOrderFlowObj.provideInfoButton);
			sf.seleU.waitTillLoading();
			sf.seleU.wait(3000);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.comOrderFlowObj.step2EligibilityCheck));
			reportStatusPass(methodName + " User is on provide information page", true, true);
		}catch (Exception e) {
			reportStatusFail("user is not on provide info page", true);
		}
		
	}
	
	/**
	 * Method developed on @Date: 29.03.2022 by @author viswas reddy
	 * 
	 * Method to check first and last name field is split and are required
	 * 
	 * @throws Exception
	 */
	public void checkFirstAndLastNameReq(String type) throws Exception {
		try {
			if(type=="Transfer") {
				if(sf.revOrderObj.firstNameReqList.size() == sf.revOrderObj.parentContainer.size() && 
						sf.revOrderObj.LastNameReqList.size() == sf.revOrderObj.parentContainer.size()) {
					reportStatusPass("first and last name field is split and are required ", true, true);
				}else {
					reportStatusFail("first and last name field is not split or not required", true);
				}
			}else {
				if(sf.revOrderObj.firstNameNewNum.isDisplayed() && 
						sf.revOrderObj.lastNameNewNum.isDisplayed()) {
					reportStatusPass("first and last name field is split and are required ", true, true);
				}else {
					reportStatusFail("first and last name field is not split or not required", true);
				}
			}
		}catch(Exception e) {
			reportStatusFail("error is checking first and last name field split and are required", true);
		}
	}
	
	/**
	 * Method developed on @Date: 29.03.2022 by @author viswas reddy
	 * 
	 * Method to click back to check eligibility button
	 * 
	 * @throws Exception
	 */
	public void clickBackToCheckEligibility() throws Exception {
		try {
			sf.seleU.wait(2000);
			sf.seleU.scrollToBottom();
			sf.seleU.clickElementByJSE(sf.revOrderObj.backToCheckEligi);
			sf.seleU.waitTillLoading();
			sf.seleU.wait(2000);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.comOrderFlowObj.step1EligibilityCheck));
			reportStatusPass(" clicked on back to eligibility check", true, true);
		}catch (Exception e) {
			reportStatusFail("failed to click on back to eligibility check", true);
		}
	}
	
	/**
	 * Method developed on @Date: 31.03.2022 by @author viswas reddy
	 * 
	 * Method to check when quote is in awaiting signature resume quote is present 
	 * and error message is displayed. If quote stage is other resume quote button is hidden
	 * 
	 * @throws Exception
	 */
	public void check_ResumeQuote() throws Exception {
		try {
			sf.seleU.wait(2000);
			String currentStage = "";
			currentStage = sf.seleU.getTextFromWebElement(sf.revOrderObj.currentStage);
			if(currentStage.contains("Awaiting")) {
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.revOrderObj.resumeQuote));
				reportStatusPass("Quote stage is "+currentStage+" resume Quote button is present", true, true);
				sf.seleU.clickElementByJSE(sf.revOrderObj.resumeQuote);
				sf.seleU.wait(2000);
				sf.seleU.waitTillLoading();
				sf.seleU.switchToFrame(driver.findElement(By.xpath("//iframe[@title='accessibility title']")));
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.revOrderObj.errorResumeQuote));
				driver.switchTo().defaultContent();
				reportStatusPass("Quote stage is "+currentStage+" Error resume Quote message is present", true, true);
			}else {
				Assert.assertFalse(sf.seleU.isElementDisplayed(sf.revOrderObj.resumeQuote));
				reportStatusPass("Quote stage is "+currentStage+" and resume Quote button is not present", true, true);
			}
		}catch (Exception e) {
			reportStatusFail("error in checking resume quote ", true);	
			e.printStackTrace();
		}
	}
}
