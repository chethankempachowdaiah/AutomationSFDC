package sfdc.pages.communities;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Pankaj Agarwal, Date 20/03/2021
 * 
 *         This page fills the RDI spec sheet for order submission
 *
 */
public class Communities_ReviewSpecSheet_Page extends MyListener {

	// Creating all the pages Object to interact with pages

	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_ReviewSpecSheet_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_My Business Order Details@:";
	}
	
	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Select Yes for billing Address and select a billing address
	 *
	 */
	public void selectBillingAddress() throws IOException {
		try {
			
			if (sf.seleU.isElementPresent(sf.orDetails.editTechSpecsSheet))
				sf.seleU.clickElementByJSE(sf.orDetails.editTechSpecsSheet);
			sf.seleU.wait(30000);
			if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer")
					|| InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))
				sf.seleU.switchWindow(4);
			
			if (sf.seleU.isElementPresent(sf.orDetails.overRideSheetDataButton))
				sf.seleU.clickElementByJSE(sf.orDetails.overRideSheetDataButton);
			
			sf.seleU.hardwait(6000);
			sf.seleU.hardwait(6000);
			verifyFieldDisplayed("Billing Information Section", sf.comReviewSpecSheet.billingInfoSection);
			verifyFieldDisplayed("Billing Info Question Banner ", sf.comReviewSpecSheet.billingQueBanner);
			verifyFieldDisplayed("Billing Info Option1 " + sf.seleU.getTextFromWebElement(sf.comReviewSpecSheet.billingQueOption1), sf.comReviewSpecSheet.billingInfoSection);
			verifyFieldDisplayed("Billing Info Option2 " + sf.seleU.getTextFromWebElement(sf.comReviewSpecSheet.billingQueOption2), sf.comReviewSpecSheet.billingQueBanner);

			sf.seleU.hardwait(1000);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(1000);
			sf.seleU.clickElementByJSE(sf.comReviewSpecSheet.billingQueRadioYes);
			sf.seleU.hardwait(3000);
			
			reportStatusPass(methodName + " Selected Yes to choose billing address", true,true);

			sf.seleU.hardwait(1000);
			verifyFieldDisplayed("Billing Information Table Column - Account Name", sf.comReviewSpecSheet.billingAccontDetailsColumnAccName);
			verifyFieldDisplayed("Billing Information Table Column - Account Number", sf.comReviewSpecSheet.billingAccontDetailsColumnAccNumber);
			verifyFieldDisplayed("Billing Information Table Column - Account Source" , sf.comReviewSpecSheet.billingAccontDetailsColumnAccSource);
			verifyFieldDisplayed("Billing Information Table Column - Billing Address ", sf.comReviewSpecSheet.billingAccontDetailsColumnAccBillingAddress);

			if (sf.seleU.isElementDisplayed(sf.comReviewSpecSheet.billingRowRadioButton)) {
				sf.seleU.clickOnElement(sf.comReviewSpecSheet.billingRowRadioButton);
				sf.seleU.hardwait(1000);
				reportStatusPass(methodName + " Selected first row for Billing Account", true,true);
	
				sf.dataInput.billingAccountName = sf.seleU.getTextFromWebElement(sf.comReviewSpecSheet.billingAccountName);
				sf.dataInput.billingAccountNumber = sf.seleU.getTextFromWebElement(sf.comReviewSpecSheet.billingAccountNumber);
				verifyFieldValue("Billing Account Source", sf.comReviewSpecSheet.billigAccountSource, "iSeries");
				reportStatusPass(methodName + " Extracted Billing Account Name : " + sf.dataInput.billingAccountName + " , Billing Account Number : " + sf.dataInput.billingAccountNumber, true,true);
			}
			else {
				sf.dataInput.billingAccountName = null;
				verifyFieldDisplayed("No Records to Display Message", sf.comReviewSpecSheet.noRecordsToDisplayMessage);
				reportStatusPass(methodName + " Verified that no billing account is associated "
						+ "with the account and No records to display message is diaplayed", true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Selecting billing address on SpecSheet Page is unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Select No for billing Address 
	 *
	 */
	public void selectNoBillingAddress() throws IOException {
		try {
			
			if (sf.seleU.isElementPresent(sf.orDetails.editTechSpecsSheet))
				sf.seleU.clickElementByJSE(sf.orDetails.editTechSpecsSheet);
			
			sf.seleU.wait(30000);
			if (InputData_Communities.commPBFUser.equalsIgnoreCase("Dealer")
					|| InputData_Communities.commPBFUser.equalsIgnoreCase("Var"))
				sf.seleU.switchWindow(4);
			
			if (sf.seleU.isElementPresent(sf.orDetails.overRideSheetDataButton))
				sf.seleU.clickElementByJSE(sf.orDetails.overRideSheetDataButton);
			
			sf.seleU.hardwait(6000);
			verifyFieldDisplayed("Billing Information Section", sf.comReviewSpecSheet.billingInfoSection);
			verifyFieldDisplayed("Billing Info Question Banner ", sf.comReviewSpecSheet.billingQueBanner);
			verifyFieldDisplayed("Billing Info Option1 " + sf.seleU.getTextFromWebElement(sf.comReviewSpecSheet.billingQueOption1), sf.comReviewSpecSheet.billingInfoSection);
			verifyFieldDisplayed("Billing Info Option2 " + sf.seleU.getTextFromWebElement(sf.comReviewSpecSheet.billingQueOption2), sf.comReviewSpecSheet.billingQueBanner);

			sf.seleU.hardwait(1000);

			sf.seleU.clickOnElement(sf.comReviewSpecSheet.billingQueRadioNo);
			sf.seleU.hardwait(3000);
			
			reportStatusPass(methodName + " Selected No to choose billing address", true,true);

			sf.seleU.hardwait(1000);
			verifyFieldNotDisplayed("Billing Information Table Column - Account Name", sf.comReviewSpecSheet.billingAccontDetailsColumnAccName);
			verifyFieldNotDisplayed("Billing Information Table Column - Account Number", sf.comReviewSpecSheet.billingAccontDetailsColumnAccNumber);
			verifyFieldNotDisplayed("Billing Information Table Column - Account Source" , sf.comReviewSpecSheet.billingAccontDetailsColumnAccSource);
			verifyFieldNotDisplayed("Billing Information Table Column - Billing Address ", sf.comReviewSpecSheet.billingAccontDetailsColumnAccBillingAddress);


			
		} catch (Throwable e) {
			reportStatusFail(methodName + "Selecting Order is unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Verify Order Details: a.Order# b. Account Name: Should be
	 *                     Business Account Name c.Order Start Date d.Status
	 *                     e.Service Account#: Should be blank if order is not yet
	 *                     activated f.Activated Date: Should be blank if order is
	 *                     not yet activated g.Monthly Charges: Exactly same as on
	 *                     the Order h. One time charge: Exactly same as on the
	 *                     Order i.Customer Name: First Name and Last Name
	 *                     j.Products: Main Products and Sub-Products in Bullets" {
	 *
	 */
	public void selectOrderForReviewSpecSheetInDevStage() throws IOException {
		try {

			sf.seleU.wait(5000);
			sf.seleU.waitElementToBeVisible(sf.comOrderDetails.readyToSubmitOrderDetailsButton);
			sf.seleU.clickElementByJSE(sf.comOrderDetails.readyToSubmitOrderDetailsButton);
			reportStatusPass(methodName + "Succesfully clicked on Ready To Submit Button", true, true);
			sf.seleU.wait(5000);

			for (int i = 0; i < sf.comOrderDetails.readyToSubmitorderProductList.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.comOrderDetails.readyToSubmitorderProductList.get(i)).trim()
						.equals(sf.dataInput.orderNumber)) {

					reportStatusPass(
							methodName + "Order Name Selected is " + sf.seleU
									.getTextFromWebElement(sf.comOrderDetails.readyToSubmitorderProductList.get(0)),
							true, false);
					sf.seleU.clickElementByJSE(sf.comOrderDetails.readyToSubmitorderProductList.get(0));

					reportStatusPass(methodName + "Order Name Selected is " + i, true, false);
					break;
				}
			}
			sf.seleU.wait(7000);
			if (sf.seleU.isElementDisplayed(sf.comOrderDetails.orderReviewSpecSheetCompleteButton)) {
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.comOrderDetails.orderReviewSpecSheetCompleteButton);
				sf.seleU.wait(6000);
				sf.seleU.scrollByCoOrdinates(1);
				// sf.seleU.ScrolltoElement(sf.comOrderDetails.orderReviewSpecSheetNextButtonInDevStage);
				sf.seleU.wait(2000);
				sf.seleU.waitElementToBeVisible(sf.comOrderDetails.orderReviewSpecSheetNextButton);
				sf.seleU.clickElementByJSE(sf.comOrderDetails.orderReviewSpecSheetNextButton);
			}
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(methodName + "Selecting Order is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Verify Order Details: a.Order# b. Account Name: Should be
	 *                     Business Account Name c.Order Start Date d.Status
	 *                     e.Service Account#: Should be blank if order is not yet
	 *                     activated f.Activated Date: Should be blank if order is
	 *                     not yet activated g.Monthly Charges: Exactly same as on
	 *                     the Order h. One time charge: Exactly same as on the
	 *                     Order i.Customer Name: First Name and Last Name
	 *                     j.Products: Main Products and Sub-Products in Bullets" {
	 *
	 */
	public void enterSpecSheetValues(boolean newPortableIpAddress, Hashtable<String, String> dataTable)
			throws IOException {
		try {
			
			if (sf.seleU.isElementPresent(sf.orDetails.editTechSpecsSheet))
				sf.seleU.clickElementByJSE(sf.orDetails.editTechSpecsSheet);
			
			if (sf.seleU.isElementPresent(sf.orDetails.overRideSheetDataButton))
				sf.seleU.clickElementByJSE(sf.orDetails.overRideSheetDataButton);
			
			sf.seleU.hardwait(6000);
			sf.seleU.hardwait(6000);
			verifyFieldDisplayed("Technical Specification Sheet is ", sf.comReviewSpecSheet.reviewSpecSheet_PageTitle);
			verifyFieldDisplayed("Product Summary is ", sf.comReviewSpecSheet.reviewSpecSheet_ProductSummary);

			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_EnterDemarcationLocation);

			sf.seleU.enterText(sf.comReviewSpecSheet.reviewSpecSheet_EnterDemarcationLocation,
					sf.dataInput.addressState);
			reportStatusPass(methodName + "Selected value for Demarcation Location is " + sf.dataInput.addressState,
					true, false);

			sf.seleU.hardwait(1000);

			sf.seleU.clickOnElement(sf.comReviewSpecSheet.reviewSpecSheet_EncapsulationTypeDropDown);
		//	sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_EncapsulationTypeDropDown);
			sf.seleU.hardwait(3000);
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.comReviewSpecSheet.reviewSpecSheet_EncapsulationTypeDropDownDevStageValues,
					dataTable.get("encapsulationType").trim());

			reportStatusPass(methodName + "Selected value for encapsulation is " + dataTable.get("encapsulationType"),
					true, false);

			sf.seleU.hardwait(1000);
			sf.seleU.clickOnElement(sf.comReviewSpecSheet.reviewSpecSheet_HandOffInterfaceDropDown);
			//sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_HandOffInterfaceDropDown);
			sf.seleU.hardwait(3000);
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.comReviewSpecSheet.reviewSpecSheet_HandOffInterfaceDropDownDevStageValues,
					dataTable.get("handOffInterface"));
			reportStatusPass(
					methodName + "Selected value for Hand-Off interfacr is " + dataTable.get("handOffInterface"), true,
					false);
			sf.seleU.scrollByCoOrdinates(1);
			sf.seleU.hardwait(1000);
			sf.seleU.clickOnElement(sf.comReviewSpecSheet.reviewSpecSheet_DNSRequiredDropDown);
			//sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_DNSRequiredDropDown);
			sf.seleU.hardwait(3000);
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.comReviewSpecSheet.reviewSpecSheet_DNSRequiredDropDownDevStageValues, "No");
			reportStatusPass(methodName + "Selected value for Hand-Off interfacr is " + "No", true, false);

			sf.seleU.hardwait(1000);
			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_vLanId);
			sf.seleU.enterText(sf.comReviewSpecSheet.reviewSpecSheet_vLanId, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Selected value for VLand ID is " + sf.dataInput.oracleNumber, true, false);

			// Click on Ip version radio Yes option
			sf.seleU.hardwait(1000);
			if (newPortableIpAddress == true) {
				sf.seleU.clickElementByJSE(sf.comReviewSpecSheet.newIpAddressYesRadioOption);
				reportStatusPass(methodName + "clicked on Yes radion button for new portable IP address radio", true,
						false);
				// choose from the ip version dropdown option
				if (sf.seleU.isElementDisplayed(sf.comReviewSpecSheet.ipVersionDropDown)) {
					sf.seleU.clickOnElement(sf.comReviewSpecSheet.ipVersionDropDown);

					sf.dataInput.sf.dataInput.completeIpAssignment = true;
					//sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.ipVersionDropDown);
					sf.seleU.hardwait(3000);
					sf.seleU.withOutSelectClassDropDownOptions(sf.comReviewSpecSheet.ipVersionDropDownOptionValues,
							dataTable.get("iPversion"));
					reportStatusPass(methodName + " Selected value for IP-Version is is " + dataTable.get("iPversion"),
							true, false);
					sf.seleU.hardwait(1000);

					// choose from IPv4 lan block option
					if (sf.seleU.isElementDisplayed(sf.comReviewSpecSheet.ipV4LanBlockDropDown)) {
						sf.seleU.clickOnElement(sf.comReviewSpecSheet.ipV4LanBlockDropDown);
						//sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.ipV4LanBlockDropDown);
						sf.seleU.hardwait(3000);
						reportStatusPass(methodName + " Clicked on Ipv4 LanBlock dropdown", true, false);
						sf.seleU.hardwait(2000);
						sf.seleU.withOutSelectClassDropDownOptions(
								sf.comReviewSpecSheet.ipV4LanBlockDropDownOptionValues,
								dataTable.get("ipV4LAnBlockSpecSheet"));
						reportStatusPass(methodName + "Selected value for IP-Version is is "
								+ dataTable.get("ipV4LAnBlockSpecSheet"), true, false);
					}
				}
			}
			sf.seleU.hardwait(2000);
			if (sf.seleU.isElementDisplayed(sf.comReviewSpecSheet.reviewSpecSheet_SubmitButton)) {
				sf.seleU.hardwait(1000);
				// sf.seleU.clickElementByJSE(sf.comReviewSpecSheet.reviewSpecSheet_SubmitButtonDevStage);
				sf.seleU.clickOnElement(sf.comReviewSpecSheet.reviewSpecSheet_SubmitButton);
				reportStatusPass(methodName + "Clicked on review spec sheet submit button", true, false);
			}

			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Selecting Order is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Verify Order Details: a.Order# b. Account Name: Should be
	 *                     Business Account Name c.Order Start Date d.Status
	 *                     e.Service Account#: Should be blank if order is not yet
	 *                     activated f.Activated Date: Should be blank if order is
	 *                     not yet activated g.Monthly Charges: Exactly same as on
	 *                     the Order h. One time charge: Exactly same as on the
	 *                     Order i.Customer Name: First Name and Last Name
	 *                     j.Products: Main Products and Sub-Products in Bullets" {
	 *
	 */
	public void verifyAfterSubmitSpecsheet() throws IOException {
		try {

			sf.seleU.hardwait(6000);
			verifyFieldDisplayed("Technical Specification Sheet is ", sf.comReviewSpecSheet.reviewSpecSheet_PageTitle);
			verifyFieldDisplayed("Product Summary is ", sf.comReviewSpecSheet.reviewSpecSheet_ProductSummary);

			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_EnterDemarcationLocation);

			sf.seleU.enterText(sf.comReviewSpecSheet.reviewSpecSheet_EnterDemarcationLocation, "Canada");
			reportStatusPass(methodName + "Selected value for Demarcation Location is " + "Canada", true, false);

			sf.seleU.hardwait(1000);

			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_EncapsulationTypeDropDown);
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.comReviewSpecSheet.reviewSpecSheet_EncapsulationTypeDropDownDevStageValues, "Access");

			reportStatusPass(methodName + "Selected value for encapsulation is " + "Access", true, false);

			sf.seleU.hardwait(1000);
			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_HandOffInterfaceDropDown);
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.comReviewSpecSheet.reviewSpecSheet_HandOffInterfaceDropDownDevStageValues, "RJ45");
			reportStatusPass(methodName + "Selected value for Hand-Off interfacr is " + "RJ45", true, false);

			sf.seleU.hardwait(1000);
			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_DNSRequiredDropDown);
			sf.seleU.withOutSelectClassDropDownOptions(
					sf.comReviewSpecSheet.reviewSpecSheet_DNSRequiredDropDownDevStageValues, "No");
			reportStatusPass(methodName + "Selected value for Hand-Off interfacr is " + "No", true, false);

			sf.seleU.hardwait(1000);
			sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_vLanId);
			sf.seleU.enterText(sf.comReviewSpecSheet.reviewSpecSheet_vLanId, sf.dataInput.oracleNumber);
			reportStatusPass(methodName + "Selected value for VLand ID is " + sf.dataInput.oracleNumber, true, false);

			if (sf.seleU.isElementDisplayed(sf.comReviewSpecSheet.reviewSpecSheet_SubmitButton)) {
				sf.seleU.hardwait(1000);
				// sf.seleU.clickElementByJSE(sf.comReviewSpecSheet.reviewSpecSheet_SubmitButtonDevStage);
				sf.seleU.moveToThenSlowClickElement(sf.comReviewSpecSheet.reviewSpecSheet_SubmitButton);
				reportStatusPass(methodName + "Clicked on review spec sheet submit button", true, false);
			}

			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Selecting Order is unsuccessful", e);
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
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			sf.seleU.hardwait(2000);
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
	 *                     Verify element is present
	 */
	public boolean verifyElementPresent(List<WebElement> element) throws IOException {

		boolean presence = false;
		try {
			sf.seleU.hardwait(2000);
			if (element.size() > 0)
				presence = true;
			else
				presence = false;

		} catch (Throwable e) {
			reportStatusFail(" Error in verifying element present", e);
			e.printStackTrace();
		}
		return presence;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {
			sf.seleU.hardwait(2000);
			// Verify field is displayed on page or not
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}
			sf.seleU.hardwait(2000);
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying field is displayed", e);
			e.printStackTrace();
		}
	}

}
