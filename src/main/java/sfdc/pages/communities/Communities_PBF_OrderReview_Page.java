package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla, Date:13/07/2021
 *
 *         Communities Persona Based Buy Flow Page - Order Review
 */
public class Communities_PBF_OrderReview_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_PBF_OrderReview_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_PBF_OrderReview@:";
	}
			
				
	/**
	 * 			Validate the Order Review Page for the account info section:
	 * 
	 * 				1.Order contact: This should display the details of the Order Owner(Full name, Phone Number, E-mail address)
	 * 
	 * 				2.Signing Authority: This should display the details of the Signing Authority (Full name, Phone Number, E-mail address)
	 * 
	 * 				3.Billing Address: This should display the details of the address associated to the Business Account"
	 * 
	 *  		Validate the Site Panel on the Order Review Page:
	 *  
	 *  			1.Service Address displayed should be the one selected on site selection screen.
	 *  
	 * @throws IOException
	 */
	public void validateOrderReviewPage() throws IOException {

		try {
				verifyFieldDisplayed("Order Review Page Header", sf.comPBFOrderReview.orderReviewPageHeader);
				verifyFieldDisplayed("Order Contact Block", sf.comPBFOrderReview.orderContactLabel);
				verifyFieldDisplayed("Signing Authority Block", sf.comPBFOrderReview.signingAuthorityLabel);
				verifyFieldDisplayed("Billing Address Block", sf.comPBFOrderReview.orderContactBillingAddressLabel);
				
				//Verify Value of Contact, Signing Authority and Billing Address
				verifyFieldValue("Order Contact Full Name", sf.comPBFOrderReview.orderContactName, InputData_Communities.commPBFContactFullName);
				verifyFieldValue("Order Contact Phone Number", sf.comPBFOrderReview.orderContactPhoneNumber, InputData_Communities.commPBFContactPhoneNumber);
				verifyFieldValue("Order Contact Email ID", sf.comPBFOrderReview.orderContactEmailID, InputData_Communities.commPBFContactEmailID);
				verifyFieldValue("Order Signing Authority Full Name", sf.comPBFOrderReview.signingAuthorityName, InputData_Communities.commPBFSAFullName);
				verifyFieldValue("Order Signing Authority Phone Number", sf.comPBFOrderReview.signingAuthorityPhoneNumber, InputData_Communities.commPBFSAPhoneNumber);
				verifyFieldValue("Order Signing Authority Email ID", sf.comPBFOrderReview.signingAuthorityEmailID, InputData_Communities.commPBFSAEmailID);
				verifyFieldValue("Order Contact Billing Address", sf.comPBFOrderReview.orderContactBillingAddressValue, InputData_Communities.commPBFContactBillingAddress);
				
				//Verify Value of Selected Address
				verifyFieldValue("Selected Service Address", sf.comPBFShopCart.serviceAddressText, InputData_Communities.commPBFselectedAddress);
				
				//Verify Place Order Button is disabled for customer
				if(InputData_Communities.commPBFUser==null)
					verifyFieldDisabled("Place Order", sf.comPBFOrderReview.placeOrderButton);
				
				//Scroll down
				sf.seleU.scrollByCoOrdinates(2);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Review Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			Validate the Order Review Page for the account info section:
	 * 
	 * 				1.Order contact: Verify has values for Order Owner(Full name, Phone Number, E-mail address)
	 * 
	 * 				2.Signing Authority: Verify has values for Signing Authority (Full name, Phone Number, E-mail address)
	 * 
	 * 				3.Billing Address: Verify has values for address associated to the Business Account"
	 * 
	 *  		Validate the Site Panel on the Order Review Page:
	 *  
	 *  			1.Service Address displayed should be the one selected on site selection screen.
	 *  
	 * @throws IOException
	 */
	public void validateOrderReviewPageWithoutValues() throws IOException {

		try {
				verifyFieldDisplayed("Order Review Page Header", sf.comPBFOrderReview.orderReviewPageHeader);
				verifyFieldDisplayed("Order Contact Block", sf.comPBFOrderReview.orderContactLabel);
				verifyFieldDisplayed("Signing Authority Block", sf.comPBFOrderReview.signingAuthorityLabel);
				verifyFieldDisplayed("Billing Address Block", sf.comPBFOrderReview.orderContactBillingAddressLabel);
				
				//Verify Value of Contact, Signing Authority and Billing Address
				verifyFieldHasValue("Order Contact Full Name", sf.comPBFOrderReview.orderContactName);
				
				if(sf.dataInput.env.equalsIgnoreCase("ITDEVSTAGE")) {
					verifyFieldHasValue("Order Signing Authority Phone Number", sf.comPBFOrderReview.signingAuthorityPhoneNumber);
					verifyFieldHasValue("Order Contact Phone Number", sf.comPBFOrderReview.orderContactPhoneNumber);
				} 
				verifyFieldHasValue("Order Contact Email ID", sf.comPBFOrderReview.orderContactEmailID);
				verifyFieldHasValue("Order Signing Authority Full Name", sf.comPBFOrderReview.signingAuthorityName);
				verifyFieldHasValue("Order Signing Authority Email ID", sf.comPBFOrderReview.signingAuthorityEmailID);
				verifyFieldHasValue("Order Contact Billing Address", sf.comPBFOrderReview.orderContactBillingAddressValue);
				
				sf.dataInput.siteContactName = sf.seleU.getTextFromWebElement(sf.comPBFOrderReview.signingAuthorityName);
				sf.dataInput.siteContactEmailId = sf.seleU.getTextFromWebElement(sf.comPBFOrderReview.signingAuthorityEmailID);
				
				//Verify Value of Selected Address
				verifyFieldValue("Selected Service Address", sf.comPBFShopCart.serviceAddressText, InputData_Communities.commPBFselectedAddress);
				
				//Verify Place Order Button is disabled for customer
				if(InputData_Communities.commPBFUser==null)
					verifyFieldDisabled("Place Order", sf.comPBFOrderReview.placeOrderButton);
				
				//Scroll down
				sf.seleU.scrollByCoOrdinates(2);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Review Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * 			Click on "Open PDF copy of Order Summary" CTA
	 * 
	 * 			Validate on the PDF Preview page,User is able to click on the Download Order Summary CTA
	 * 
	 * 			Validate on the PDF Preview page,User is able to click on the Email Order Summary CTA
	 * 
	 * 			Click on Back to review and place order link
	 * 
	 * @throws IOException
	 */
	public void verifyOrderSummaryPDF() throws IOException {

		try {
					
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.openPDFCopyOrderSummaryButton);
				sf.seleU.wait(15000);
				reportStatusPass(methodName + " Opened Order Summary PDF", true, true);
				
				//Validate Order Summary Page
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.downloadOrderSummaryButton);
				reportStatusPass(methodName + " Downloaded Order Summary PDF", true, true);
				sf.seleU.wait(15000);
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.emailOrderSummary);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Emailed Order Summary PDF", false, true);
				verifyFieldDisplayed("Email Sent Success Message", sf.comPBFOrderReview.emailSentMessage);
				
				//Move Back to Order Review Page
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.backToOrderReviewPageButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Moved Back to Order Review Page", true, true);
				
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Order Summary PDF", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 			Validate the Terms and Conditions section should be displayed
	 * 			with 2 CTAs which should open the Business Agreeement PDFs
	 * 
	 *  		Validate user checks in the Terms and Conditions checkbox
	 *  
	 * @throws IOException
	 */
	public void verifyTermsAndCondtionsAndAccept() throws IOException {

		try {
				
				//Validate Terms and conditions Block and Body
				verifyFieldDisplayed("Terms And Conditions Header", sf.comPBFOrderReview.termAndConditionsSection);
				
				//Click on show details for agent
				if (InputData_Communities.commPBFUser.equalsIgnoreCase("Agent") ||
						InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer") ||
						InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.showDetails);
					
				sf.seleU.wait(2000);
				verifyFieldDisplayed("Terms And Conditions Text", sf.comPBFOrderReview.termAndConditionsText);
				
				//Validate 2 Agreements Link Buttons are present
				verifyFieldDisplayed("Terms And Conditions Open Rogers Business Agreement Button", sf.comPBFOrderReview.rogersBusinessAgreementButton);
				verifyFieldDisplayed("Terms And Conditions Productt Agreement Button", sf.comPBFOrderReview.rogersBusinessInternetTermsButton);
				
				if (!(InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer") ||
						InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))) {
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.rogersBusinessAgreementButton);
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Clicked on Rogers Business Agreement Button", true, false);
					sf.seleU.switchWindow(2);
					if (sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFBusinessGeneralTermURL)) {
						
						reportStatusPass(methodName + " Rogers Business Agreement Opened Correctly", false, true);
						
					} else {
						reportStatusFail(methodName + " Business Agreement Failed to Open", true);
					}
					sf.seleU.switchWindow(1);
					sf.seleU.closeRecentlyOpenedWindow();
					
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.rogersBusinessInternetTermsButton);
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Cicked on Rogers Business Internet Terms Button", true, false);
					sf.seleU.switchWindow(2);
					if (sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFBusinessInternetTermURL)
							|| sf.seleU.getCurrentUrl().toString().contains(InputData_Communities.commPBFBusinessTVTermURL)) {
						
						reportStatusPass(methodName + " Rogers Business Internet Terms Opened Correctly", false, true);
						
					} else {
						reportStatusFail(methodName + " Business Internet Terms Failed to Open", true);
					}
					sf.seleU.switchWindow(1);
					sf.seleU.closeRecentlyOpenedWindow();
				}	
				//Accept Terms and Conditions
				sf.seleU.wait(5000);
				sf.seleU.scrollByCoOrdinates(3);
				
				//Checkbox not there for Agent
				if (!(InputData_Communities.commPBFUser.equalsIgnoreCase("Agent") ||
						InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer") ||
						InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))) {
					sf.seleU.clickElementByJSE(sf.comPBFOrderReview.termsAndConditionsCheckbox);
					reportStatusPass(methodName + " Accepted Termes and conditions", true, false);
				}
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating terms and condintions on Order Review Page", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 			Validate user is presented with two options on the page:
	 * 
	 * 				1.Cancel and Back should take the user back to the Shopping cart Page.
	 * 
	 * @throws IOException
	 */
	public void verifyCancelAndBackRedirection() throws IOException {

		try {
			
				//Click on Cancel And Back Button
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.cancelAndBackButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Cicked on Cancel And Back Button", true, true);
				
				//Validate Shopping Cart Page os displayed
				verifyFieldDisplayed("Shopping Cart Page", sf.comPBFShopCart.shoppingCartHeader);
				
				//Go Back to Order Review Page

				Communities_PBF_ShoppingCart_Page.proceedToCheckout();
				
				//Validate Order Review Page is displayed
				verifyFieldDisplayed("Order Review Page", sf.comPBFOrderReview.orderReviewPageHeader);
				
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating redirection to shopping cart page by Cancel and Back Button on Order Review Page", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * 			Validate the Credit and Fraud Static Message bar is displayed
	 *           Credit check required message banner will only display for credit check not for fraud check
	 *           Banner will always be displayed
	 * 
	 * @throws IOException
	 */
	public void validateCreditAndFraudCheckMessage() throws IOException {

		try {
				if(InputData_Communities.commPBFCreditCheckRequired.equalsIgnoreCase("Yes")) {
					verifyFieldDisplayed("Credit check Required Message", sf.comPBFOrderReview.creditCheckReqMessage);
				     verifyFieldDisplayed("The services offered under this agreement will not be provided until the customer onboarding process is complete. This process includes a credit and/or fraud check.", sf.comPBFOrderReview.creditCheckMessagedescription);
				}
				else 
					verifyFieldDisplayed("Credit check not required Message", sf.comPBFOrderReview.creditCheckNotReqMessage);
					verifyFieldDisplayed("This Agreement will be automatically approved and does not require a credit check", sf.comPBFOrderReview.creditCheckMessagedescription);
		}	
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Credit And Fraud Check Message on Order Review Page", e);
			e.printStackTrace();
		}

	}
	

	
	/**
	 * 			Validate user clicks on Place Order
	 * 
	 * 			Accepted PDF Preview is dsiplayed with all relevant details on the PDF
	 * 
	 *			Click on Confirm Order	
	 *
	 *			Validate the following:
	 *
	 *				1.An email is trigerred to the Signing Authority with the Accepted PDF.
	 *
	 *				2.An email is trigerred to the Site Contact.
	 *
	 *				3.Order Confirmation Page is displayed
	 *
	 *
	 * @throws IOException
	 */
	public void confirmAndPlaceOrder() throws IOException {

		try {
				//Click on Place Order
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.placeOrderButton);
				sf.seleU.wait(5000);
				reportStatusPass(methodName + " Cicked on Place Order Button", true, true);
				
				
				sf.seleU.wait(7000);
				//Vaidate Order can be downloaded
				verifyFieldDisplayed("Download Order Summary PDF",sf.comPBFOrderReview.downloadOrderSummaryButton);
				
				//Click on Confirm Order
				sf.seleU.clickElementByJSE(sf.comPBFOrderReview.confirmOrderButton);
				sf.seleU.wait(7000);
				reportStatusPass(methodName + " Cicked on Confirm Order Button", true, true);
				sf.seleU.wait(25000);
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in placing the order", e);
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

	/**
	 * @throws IOException
	 * 
	 *                     Verify field is not displayed
	 */
	public static void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {

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
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field has value/text
	 */
	public void verifyFieldHasValue(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field has some value/non-empty
			if (!sf.seleU.getTextFromWebElement(element).isEmpty())
				reportStatusPass(
						methodName + " " + fieldName + " field has value : " + sf.seleU.getTextFromWebElement(element),
						true, true);
			else
				reportStatusFail(methodName + " " + fieldName + " field value is not populated and is empty", true);

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is disabled
	 */
	public void verifyFieldDisabled(String fieldName, WebElement element) throws IOException {
		try {

			if (!element.isEnabled()) {
				reportStatusPass(methodName + " Validated " + fieldName + " field is disabled and not displayed", true,
						true);
			} else {
				reportStatusFail(
						methodName + " Field " + fieldName + " is not a disabled field, It should be a disabled", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
}
