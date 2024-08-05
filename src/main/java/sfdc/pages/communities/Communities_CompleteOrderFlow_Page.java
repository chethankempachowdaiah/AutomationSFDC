package sfdc.pages.communities;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_Files_Page;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Sakshi.Lnu
 * @date 26.08.2021
 * 
 *       Community Portal complete (Submit) Order
 */

public class Communities_CompleteOrderFlow_Page extends MyListener {

	// Creating all the pages Object to interact with pages

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static SFDC_Files_Page filesPage;

	public Communities_CompleteOrderFlow_Page() {
		sf = new SFDC_AllPageObjects();
		filesPage = new SFDC_Files_Page();
		methodName = "Communities_Order Completion Flow@:";
	}

	/**
	 * @throws IOException
	 * 
	 *             Compete the incomplete Order
	 * 
	 * 
	 */
	public void selectRequestToPortOrNewNumber(String request) throws IOException {
		try {
			sf.seleU.wait(5000);
			// Select Case and Open Case Details
			verifyFieldDisplayed("Customer Info Form Header", sf.comOrderFlowObj.customerInforFormHeading);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " User lands on Customer Info Page ", true, true);
			verifyFieldValue("Order Number", sf.comOrderFlowObj.orderNumberField, dataInput.orderNumber);
			verifyFieldDisplayed("Step 1 to complete customer Info form", sf.comOrderFlowObj.step1EligibilityCheck);
			sf.seleU.scrollToBottom();
			verifyFieldDisplayed("Select Request", sf.comOrderFlowObj.selectRequestHeader);
			sf.seleU.wait(10000);
			System.out.println("Request demanded:::" + request);
			switch (request) {
			case "New number":
				if (sf.seleU.isElementDisplayed(sf.comOrderFlowObj.requestNewNumberRadio)) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.requestNewNumberRadio);
					reportStatusPass(methodName + "Clicked on New number radio", true, true);
					sf.seleU.hardwait(5000);
					sf.seleU.clickOnElement(sf.comOrderFlowObj.checkEligibiltyButton);
					sf.seleU.hardwait(5000);
				} else {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.editButton);
					reportStatusPass(methodName + "Clicked on edit Button", true, true);
					if (!sf.comOrderFlowObj.requestNewNumberRadio.isSelected()) {
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.requestNewNumberRadio);
						reportStatusPass(methodName + "Clicked on New number radio", true, true);
						sf.seleU.hardwait(5000);
						sf.seleU.clickOnElement(sf.comOrderFlowObj.checkEligibiltyButton);
						sf.seleU.hardwait(5000);
					} else {
						sf.seleU.hardwait(5000);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.reviewEligibilityButton);
					}
				}

				Boolean counter = sf.seleU.isElementDisplayed(sf.comOrderFlowObj.eligibiltyCheck);
				if (counter) {
					verifyFieldValue("Eligibilty Check Number Details", sf.comOrderFlowObj.numberDetails,
							"New number from Rogers");
					sf.seleU.clickOnElementNumberoftimes(sf.comOrderFlowObj.provideInfoButton, 1);
				}
				break;

			case "Transfer":
				if (sf.seleU.isElementDisplayed(sf.comOrderFlowObj.transferNumberRadio)) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
					reportStatusPass(methodName + "User requested to transfer number", true, true);

//					sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, InputData_WA.WACC_TransferPhoneNumber);

//					sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, InputData_WA.WACC_TransferPhoneNumber);
					String constructedMobileNumber=InputData_WA.WACC_TransferPhoneNumber.substring(0,6).concat(AdditionalUtilities.generateRandomDigit(4));
					sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, constructedMobileNumber);
					sf.seleU.hardwait(3000);
					sf.comOrderFlowObj.portNumberInputField.sendKeys(Keys.TAB);
//					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.selectRequestHeader);

					sf.seleU.hardwait(5000);
					reportStatusPass(
							methodName + "User requested to transfer number for : " + sf.seleU
							.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
							true, true);
					sf.seleU.hardwait(5000);

				//	sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
				//	sf.seleU.hardwait(5000);
					sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[text()='Transfer number']")));

					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
					sf.seleU.hardwait(5000);

				} else {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.editButton);
					reportStatusPass(methodName + "Clicked on edit Button", true, true);
					if (!sf.comOrderFlowObj.transferNumberRadio.isSelected()) {
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
						reportStatusPass(methodName + "Clicked on Transfer number radio", true, true);
						sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, InputData_WA.WACC_TransferPhoneNumber);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.selectRequestHeader);
						sf.seleU.hardwait(5000);
						reportStatusPass(methodName + "User requested to transfer number for : " + sf.seleU
								.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
								true, true);
						sf.seleU.hardwait(5000);
						sf.seleU.robotPressKey(1, KeyEvent.VK_ENTER);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
						sf.seleU.hardwait(5000);
					} else {
						reportStatusPass(methodName + "User requested to transfer number for : " + sf.seleU
								.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
								true, true);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.reviewEligibilityButton);
					}
				}

				Boolean status = sf.seleU.isElementDisplayed(sf.comOrderFlowObj.eligibiltyCheck);
				if (status) {
					verifyFieldValue("Eligibilty Check Number Details", sf.comOrderFlowObj.numberDetails, "Wireless");
					sf.seleU.clickOnElementNumberoftimes(sf.comOrderFlowObj.provideInfoButton, 1);

				} else {
					verifyFieldValue("Eligibilty Check Number Details", sf.comOrderFlowObj.numberDetails,
							"This number is not eligible for port in to Rogers");
					verifyFieldDisplayed("Disabled More Info Button", sf.comOrderFlowObj.disabledProvideInfoButton);
				}
				break;

			default:
				sf.seleU.clickElementByJSE(sf.comOrderFlowObj.requestNewNumberRadio);
				reportStatusPass(methodName + "User Clicked on Request new number", true, true);
				sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
				sf.seleU.hardwait(5000);
				Boolean status1 = sf.seleU.isElementDisplayed(sf.comOrderFlowObj.eligibiltyCheck);
				if (status1) {
					verifyFieldValue("Eligibilty Check Number Details", sf.comOrderFlowObj.numberDetails,
							"New number from Rogers");
					sf.seleU.clickOnElementNumberoftimes(sf.comOrderFlowObj.provideInfoButton, 1);

				}
				break;
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Viewing Case Details is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify field is displayed on page or not
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying field is displayed", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *             Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			sf.seleU.waitTillLoading();
			// Verify Field value matches the expected result
			sf.seleU.waitElementToBeVisible(element);
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)
					|| expectedText.contains(sf.seleU.getTextFromWebElement(element))) {
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
	 *             Verify Field has value/text
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
	 *             Provide customer information
	 * 
	 */
	public void provideCustomerInformationAndSubmitOrder(String requestType, String deviceType) throws IOException {
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitTillLoading();
			verifyFieldDisplayed("Step 2", sf.comOrderFlowObj.step2EligibilityCheck);
			reportStatusPass(methodName + " User is on provide information page", true, true);
			sf.seleU.hardwait(2000);
			verifyFieldValue("Requested Type", sf.comOrderFlowObj.requestedType, requestType);
			if (sf.seleU.isElementDisplayed(sf.comOrderFlowObj.editButton)) {
				sf.seleU.clickElementByJSE(sf.comOrderFlowObj.editButton);
				reportStatusPass(methodName + " Edit Button is clicked", true, true);
			} 
			
			if (InputData.env.contains("WA")||InputData.env.contains("ITQATEST") || InputData.env.contains("PREPROD")) {
				sf.seleU.clearTextByMouseAction(sf.comOrderFlowObj.assignedFirstNameInput);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.comOrderFlowObj.assignedFirstNameInput, "TestAutomationWACC");
				reportStatusPass(methodName + "Assigned user's first name entered", true, true);
				sf.seleU.clearTextByMouseAction(sf.comOrderFlowObj.assignedLastNameInput);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.comOrderFlowObj.assignedLastNameInput, "AutoValue");
				reportStatusPass(methodName + "Assigned user's last name entered", true, true);

			}	 	
			
			else  {
				sf.seleU.clearTextByMouseAction(sf.comOrderFlowObj.assignedUserInput);
				sf.seleU.enterText(sf.comOrderFlowObj.assignedUserInput, "TestAutomationWACC");
			}
			reportStatusPass(methodName + "Assigned User entered as TestAutomationWACC", true, true);
			switch (requestType) {
			case "New number":
				sf.seleU.ScrolltoElement(sf.comOrderFlowObj.proviceDropdown);
				sf.seleU.hardwait(2000);
				sf.seleU.ScrolltoElement(sf.comOrderFlowObj.proviceDropdown);
				sf.seleU.clickOnElement(sf.comOrderFlowObj.proviceDropdown);
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.comOrderFlowObj.provinceDropDownOptions.get(8));
				sf.seleU.hardwait(3000);
				sf.seleU.clickOnElement(sf.comOrderFlowObj.cityDropdown);
				sf.seleU.hardwait(3000);
				System.out.println(sf.seleU.getTextFromWebElement(sf.comOrderFlowObj.cityDropDownOptions.get(83)));
				sf.seleU.hardwait(3000);

				String path="//li//div[@role='option' and contains(@data-value,'TORONTO')]";
				WebElement element=driver.findElement(By.xpath(path));
				sf.seleU.waitElementToBeClickable(element);
				sf.seleU.clickOnElement(element);
				
				/*for(int i=0;i<sf.comOrderFlowObj.cityDropDownOptions.size();i++) {
					if(sf.seleU.getTextFromWebElement(sf.comOrderFlowObj.cityDropDownOptions.get(i)).equalsIgnoreCase("TORONTO")) {
						sf.seleU.clickOnElement(sf.comOrderFlowObj.cityDropDownOptions.get(i));
					break;
					}
				}*/
				//sf.seleU.clickOnElement(sf.comOrderFlowObj.cityDropDownOptions.get(597));
				sf.seleU.clickOnElement(sf.comOrderFlowObj.cityDropDownOptions.get(83));
				break;

			case "Transfer":
				//sf.seleU.clearTextByMouseAction(sf.comOrderFlowObj.accountNumberInput);
				String accNum = AdditionalUtilities.generateRandomDigit(9).toString() + AdditionalUtilities.generateRandomDigit(5).toString();
				sf.seleU.enterText(sf.comOrderFlowObj.accountNumberInput,accNum);
				reportStatusPass(methodName + "Account number input is: "+ accNum, true, true);
				if (deviceType.equalsIgnoreCase("IMEI")) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.imeiDeviceTypeRadio);
					reportStatusPass(methodName + deviceType + " is selected", true, true);
					sf.seleU.clearTextByMouseAction(sf.comOrderFlowObj.imeiInput);
					sf.seleU.enterText(sf.comOrderFlowObj.imeiInput, accNum+ "1");
					reportStatusPass(methodName + deviceType + " input  is: "+ accNum+"1", true, true);

				} else if (deviceType.equalsIgnoreCase("ESN")) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.esnDeviceTypeRadio);
					reportStatusPass(methodName + deviceType + " is selected", true, true);
					sf.seleU.clearTextByMouseAction(sf.comOrderFlowObj.esnInput);
					sf.seleU.enterText(sf.comOrderFlowObj.esnInput, accNum);
					reportStatusPass(methodName + deviceType + " input is: "+accNum, true, true);
				} else {
					reportStatusFail(methodName + "No valid device type is selected : " + deviceType, true);
				}
				break;
			default:
				reportStatusFail(methodName + "no valid request is selected", true);
				break;
			}
//			verifyFieldDisplayed("Disabled Submit Button", sf.comOrderFlowObj.disabledSubmitButton);
			sf.seleU.hardwait(7000);
			sf.seleU.clickOnElement(sf.comOrderFlowObj.saveDetailsButton);
			reportStatusPass(methodName + "Save details button  is clicked", true, true);
			sf.seleU.hardwait(7000);
			sf.seleU.clickElementByJSE(sf.comOrderFlowObj.submitRequestButton);
			reportStatusPass(methodName + "Submit order button is clicked", true, true);
			//validate 911 Limitation page and click submit
			validate911LimitationAndSubmit();
			
			sf.seleU.hardwait(35000);
			sf.seleU.waitElementToBeVisible(sf.comOrderFlowObj.thankTitleText);
			verifyFieldDisplayed("Thank you for Submit order", sf.comOrderFlowObj.thankTitleText);
			sf.seleU.hardwait(5000);
			verifyFieldValue("Order number on Thank you screen", sf.comOrderFlowObj.orderNumberConfirmation,
					Global.dataInput.orderNumber);
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.comOrderFlowObj.goToOrderLink);
			sf.seleU.hardwait(4000);
			reportStatusPass(methodName + "Go to Orders button  is clicked", true, true);
			sf.seleU.hardwait(4000);
		} catch (Throwable e) {
			reportStatusFail(" Error in Filling  Customer Information Form", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Author : Satish
	 * Method is used for Enter the Port or Transfer Number and New Number 
	 * flows, not included any validation, hence as per your requirement 
	 * validate it in outside of the method.
	 * 
	 * @throws IOException
	 * 
	 * Compete the incomplete Order
	 * 
	 * 
	 */
	public void selectRequestToPortOrNewNumberWithOutAnyVal(String request) throws IOException {
		Boolean status =false;
		try {
			sf.seleU.wait(5000);
			// Select Case and Open Case Details
			verifyFieldDisplayed("Customer Info Form Header", sf.comOrderFlowObj.customerInforFormHeading);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " User lands on Customer Info Page ", true, true);
			verifyFieldValue("Order Number", sf.comOrderFlowObj.orderNumberField, dataInput.orderNumber);
			verifyFieldDisplayed("Step 1 to complete customer Info form", sf.comOrderFlowObj.step1EligibilityCheck);
			sf.seleU.scrollToBottom();
			verifyFieldDisplayed("Select Request", sf.comOrderFlowObj.selectRequestHeader);
			sf.seleU.wait(5000);
			System.out.println("Request demanded:::" + request);
			switch (request) {
			case "New number":
				if (sf.seleU.isElementDisplayed(sf.comOrderFlowObj.requestNewNumberRadio)) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.requestNewNumberRadio);
					reportStatusPass(methodName + "Clicked on New number radio", true, true);
					sf.seleU.hardwait(5000);
					sf.seleU.clickOnElement(sf.comOrderFlowObj.checkEligibiltyButton);
					sf.seleU.hardwait(5000);
				} else {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.editButton);
					reportStatusPass(methodName + "Clicked on edit Button", true, true);
					if (!sf.comOrderFlowObj.requestNewNumberRadio.isSelected()) {
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.requestNewNumberRadio);
						reportStatusPass(methodName + "Clicked on New number radio", true, true);
						sf.seleU.hardwait(5000);
						sf.seleU.clickOnElement(sf.comOrderFlowObj.checkEligibiltyButton);
						sf.seleU.hardwait(5000);
					} else {
						sf.seleU.hardwait(5000);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.reviewEligibilityButton);
					}
				}
				break;

			case "Transfer":
				if (sf.seleU.isElementDisplayed(sf.comOrderFlowObj.transferNumberRadio)) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
					reportStatusPass(methodName + "User requested to transfer number", true, true);
					String constructedMobileNumber=InputData_WA.WACC_TransferPhoneNumber.substring(0,6).concat(AdditionalUtilities.generateRandomDigit(4));
					sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, constructedMobileNumber);
					sf.seleU.hardwait(3000);
					sf.comOrderFlowObj.portNumberInputField.sendKeys(Keys.TAB);

					sf.seleU.hardwait(5000);
					reportStatusPass(
							methodName + "User requested to transfer number for : " + sf.seleU
							.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
							true, true);
					sf.seleU.hardwait(5000);
//					sf.seleU.clickOnElement(driver.findElement(By.xpath("//span[text()='Transfer number']")));
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
					sf.seleU.hardwait(5000);

				} else {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.editButton);
					reportStatusPass(methodName + "Clicked on edit Button", true, true);
					if (!sf.comOrderFlowObj.transferNumberRadio.isSelected()) {
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
						reportStatusPass(methodName + "Clicked on Transfer number radio", true, true);
						sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, InputData_WA.WACC_TransferPhoneNumber);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.selectRequestHeader);
						sf.seleU.hardwait(5000);
						reportStatusPass(methodName + "User requested to transfer number for : " + sf.seleU
								.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
								true, true);
						sf.seleU.hardwait(5000);
						sf.seleU.robotPressKey(1, KeyEvent.VK_ENTER);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
						sf.seleU.hardwait(5000);
					} else {
						reportStatusPass(methodName + "User requested to transfer number for : " + sf.seleU
								.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
								true, true);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.reviewEligibilityButton);
					}
				}
				break;

			default:
				sf.seleU.clickElementByJSE(sf.comOrderFlowObj.requestNewNumberRadio);
				reportStatusPass(methodName + "User Clicked on Request new number", true, true);
				sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
				sf.seleU.hardwait(5000);
				Boolean status1 = sf.seleU.isElementDisplayed(sf.comOrderFlowObj.eligibiltyCheck);
				if (status1) {
					verifyFieldValue("Eligibilty Check Number Details", sf.comOrderFlowObj.numberDetails,
							"New number from Rogers");
					sf.seleU.clickOnElementNumberoftimes(sf.comOrderFlowObj.provideInfoButton, 1);

				}
				break;
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + "Viewing Case Details is unsuccessful", e);
			e.printStackTrace();
		}
	}

	
	/**
	 * Author : Satish
	 * Method is used for Enter the Port or Transfer Number and New Number 
	 * flows, Read Port number from Excel sheet.
	 * 
	 * @throws IOException
	 * 
	 * Compete the incomplete Order
	 * 
	 * 
	 */
	public void selectRequestToPortNumber(String request) throws IOException {
		Boolean status =false;
		try {
			sf.seleU.wait(5000);
			// Select Case and Open Case Details
			verifyFieldDisplayed("Customer Info Form Header", sf.comOrderFlowObj.customerInforFormHeading);
			sf.seleU.wait(5000);
			reportStatusPass(methodName + " User lands on Customer Info Page ", true, true);
			verifyFieldValue("Order Number", sf.comOrderFlowObj.orderNumberField, dataInput.orderNumber);
			verifyFieldDisplayed("Step 1 to complete customer Info form", sf.comOrderFlowObj.step1EligibilityCheck);
			sf.seleU.scrollToBottom();
			verifyFieldDisplayed("Select Request", sf.comOrderFlowObj.selectRequestHeader);
			sf.seleU.wait(5000);
			System.out.println("Request demanded:::" + request);
			
				if (sf.seleU.isElementDisplayed(sf.comOrderFlowObj.transferNumberRadio)) {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
					reportStatusPass(methodName + "User requested to transfer number", true, true);
					String constructedMobileNumber=InputData_WA.WACC_TransferPhoneNumber;
					sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, constructedMobileNumber);
					sf.seleU.hardwait(3000);
					sf.comOrderFlowObj.portNumberInputField.sendKeys(Keys.TAB);

					sf.seleU.hardwait(5000);
					reportStatusPass(
							methodName + "User requested to transfer number for : " + sf.seleU
							.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
							true, true);
					sf.seleU.hardwait(5000);
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
					sf.seleU.hardwait(5000);

				} else {
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.editButton);
					reportStatusPass(methodName + "Clicked on edit Button", true, true);
					if (!sf.comOrderFlowObj.transferNumberRadio.isSelected()) {
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
						reportStatusPass(methodName + "Clicked on Transfer number radio", true, true);
						sf.seleU.enterText(sf.comOrderFlowObj.portNumberInputField, InputData_WA.WACC_TransferPhoneNumber);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.selectRequestHeader);
						sf.seleU.hardwait(5000);
						reportStatusPass(methodName + "User requested to transfer number for : " + sf.seleU
								.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
								true, true);
						sf.seleU.hardwait(5000);
						sf.seleU.robotPressKey(1, KeyEvent.VK_ENTER);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.transferNumberRadio);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.checkEligibiltyButton);
						sf.seleU.hardwait(5000);
					} else {
						reportStatusPass(methodName + "User requested to transfer number for : " + sf.seleU
								.getTextFromWebElementWithYellowHighlight(sf.comOrderFlowObj.portNumberInputField),
								true, true);
						sf.seleU.clickElementByJSE(sf.comOrderFlowObj.reviewEligibilityButton);
					}
				}
				
		} catch (Throwable e) {
			reportStatusFail(methodName + "Viewing Case Details is unsuccessful", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 09.05.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate 911Limitation Page and Submit the order.
	 * @throws Exception
	 */
	public void validate911LimitationAndSubmit() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName="911 Limitation Page@: ";
			sf.seleU.hardwait(4000);
			sf.seleU.waitTillLoading();
			if(sf.seleU.isElementDisplayed(sf.comOrderFlowObj.limitationHeader) && !sf.seleU.isElementEnabled(sf.comOrderFlowObj.limitationSubmitBtn)) {
				sf.seleU.clickElementByJSE(sf.comOrderFlowObj.agreeChkBox);
				if(sf.seleU.isElementEnabled(sf.comOrderFlowObj.limitationSubmitBtn))
					sf.seleU.clickElementByJSE(sf.comOrderFlowObj.limitationSubmitBtn);
				reportStatusPass(methodName + " 911 Limitation Agreement checkbox has selected and Submit button has clicked successfully. ",true, true);
			}
			else
				reportStatusFail(methodName + "911 Limitation page has not displayed/Submit button has enabled. ", true);
		} catch (Throwable e) {
			reportStatusFail(methodName + "Error in Accepet the 911 Limitation Agreement.", e);
			e.printStackTrace();
		}
		
	}
}
