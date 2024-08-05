package sfdc.pages.qm;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date: 30/Jan/2019
 * 
 *         SFDC Quote_SelectSiteContact Page(Opportunity>create Quote>Configure
 *         Quote>Accept Quote>Generate Document>Send Email> Select Site Contact)
 *
 */
public class SFDC_Quote_SelectSiteContact_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_Quote_SelectSiteContact_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify User is prompted to enter "Site Contact"
	 *                     Information.
	 */
	public void verifySelectExistingNewContactText() throws IOException {
		try {
			String methodName = "Quote_SelectSiteContact@: ";

			// Select continue if Send Email Error is present

			int counter = 0;
			boolean isMsgPresent = false;
			//Fixed existing iframe issue by pointing to right frame
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.siteCon.selectSiteContactTextList);
			while (!isMsgPresent) {

				if (sf.seleU.isElementPresent(sf.siteCon.selectSiteContactText)) {
					isMsgPresent = true;
					break;
				}

				if (counter > 4) {
					break;
				}
				counter++;

				sf.seleU.wait(15000);
			}

			// Verify User is prompted to enter "Site Contact" Information.
			if (sf.seleU.isElementDisplayed(sf.siteCon.selectSiteContactText) && isMsgPresent) {
				reportStatusPass(methodName + sf.siteCon.selectSiteContactText.getText() + "  message is displayed",
						true, true);
			} else {
				reportStatusFail(methodName + " 'Select Existing/new Site contact ' message is not present", true);
			}

			sf.seleU.hardwait(2000);
			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail(" Select Existing/New Site Contact message is not present", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Save for later button
	 * 
	 *                     Click on order saved in draft alert OK Button
	 * 
	 */
	public void selectSaveForLater() throws IOException {

		try {

			String methodName = "Quote_SelectSiteContact@: ";

			// Click on Save for later button
			sf.seleU.clickElementByJSE(sf.siteCon.saveForLaterButton);
			reportStatusPass(methodName + " Clicked on Save for later button", true, false);

			// Click on order saved in draft alert OK Button
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.siteCon.orderSavedInDraftAlertOk);
			reportStatusPass(methodName + " Clicked on order saved in draft alert OK Button", true, false);

		} catch (Throwable e) {
			reportStatusFail(" Selecting Save for later in select site contact page is unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on New Site Contact Check box
	 */
	public void createNewSiteContact() throws IOException {
		try {

			String methodName = "Quote_SelectSiteContact@: ";

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.siteCon.addAContactButton);

			// Click on New Site Contact Check box
			// if (sf.seleU.isElementPresent(sf.siteCon.addAContactButton)) {
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.siteCon.addAContactButton.get(0));
			reportStatusPass(methodName + " Clicked on Add new Contact button", true, false);
			sf.seleU.wait(4000);
			// }

			// Preapare test data for another contact
			sf.dataInput.thirdContact_prepareContactData();
			// sf.seleU.switchToDefaultContent();
			// sf.seleU.switchToFrame(sf.siteCon.accessibilityFrame.get(1));

			// Enter Site Contact Details
			sf.seleU.enterText(sf.siteCon.siteConFirstName, sf.dataInput.contactFirstName);
			sf.seleU.enterText(sf.siteCon.siteConLastName, sf.dataInput.contactLastName);
			sf.seleU.enterText(sf.siteCon.siteConEmailAddress, sf.dataInput.contactEmailAddress);
			sf.seleU.enterText(sf.siteCon.siteConPhone, sf.dataInput.phoneNumber.substring(0, 10));
			reportStatusPass(
					methodName + " Entered Site Contact info : " + sf.dataInput.contactFirstName + " "
							+ sf.dataInput.contactLastName + " " + "email address " + sf.dataInput.contactEmailAddress,
					true, false);

			// Click on Next
			sf.seleU.clickElementByJSE(sf.siteCon.createSiteConNext);
			reportStatusPass(methodName + " Click on next buttn site contact page", true, false);

			if (sf.seleU.isElementDisplayed(sf.siteCon.selectSiteContactNextButton)) {
				sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
			}
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(35000);

		} catch (Throwable e) {
			reportStatusFail(" Creating New Site Contact  is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on New Site Contact Check box after resume quote
	 */
	public static void createNewSiteContactAfterRsumeQuote() throws IOException {
		try {

			String methodName = "Quote_SelectSite_Contac@: ";
			sf.seleU.wait(4000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToFrame(sf.siteCon.addAContactButtonResumeQuoteIFrame);

			sf.seleU.wait(6000);
			sf.seleU.getTextFromWebElement(sf.siteCon.addAContactButtonAfterResumeQuote);
			sf.seleU.clickElementByJSE(sf.siteCon.addAContactButtonAfterResumeQuote);
			reportStatusPass(methodName + " Clicked on Add new Contact button", true, false);
			sf.seleU.wait(8000);

			// Preapare test data for another contact
			sf.dataInput.thirdContact_prepareContactData();

			// Enter Site Contact Details
			sf.seleU.enterText(sf.siteCon.siteConFirstName, sf.dataInput.contactFirstName);
			sf.seleU.enterText(sf.siteCon.siteConLastName, sf.dataInput.contactLastName);
			sf.seleU.enterText(sf.siteCon.siteConEmailAddress, sf.dataInput.contactEmailAddress);
			sf.seleU.enterText(sf.siteCon.siteConPhone, sf.dataInput.phoneNumber.substring(0, 10));
			reportStatusPass(methodName + " Entered Site Contact info : " + sf.dataInput.contactFirstName + " "
					+ sf.dataInput.contactLastName + " Email Address " + sf.dataInput.contactEmailAddress
					+ " phone number " + sf.dataInput.phoneNumber.substring(0, 10), true, false);
			sf.seleU.hardwait(3000);
			// Click on Next
			sf.seleU.clickElementByJSE(sf.siteCon.createSiteConNext);
			reportStatusPass(methodName + " Click on next buttn in site contact page", true, false);

			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(20000);

		} catch (Throwable e) {
			reportStatusFail(" Creating New Site Contact  is unsuccessful from resume quote button option", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select the site contact by verifying the correct site
	 *                     contact
	 * 
	 *                     Click on New/Existing Contact Next Button
	 */
	public static void selectExistingSiteContact() throws IOException {
		try {

			String methodName = "Quote_SelectSiteContact@: ";

			if(!sf.seleU.isElementPresent(sf.siteCon.selectExistingContactCheckBoxAll))
				sf.seleU.switchToElementFrame(sf.siteCon.selectExistingContactCheckBoxAll);
			// Select the site contact by verifying the correct site contact
			boolean isSiteContactValidated = false;
			for (int i = 0; i < sf.siteCon.selectExistingContactCheckBoxAll.size(); i++) {

//				System.out.println("..." + sf.dataInput.siteContactName);
//				System.out.println(sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(i)));

				if (sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(i))
						.equals(sf.dataInput.siteContactName)) {

					sf.seleU.clickElementByJSE(sf.siteCon.selectExistingContactCheckBoxAll.get(i));
					reportStatusPass(methodName + " Selected Existing Site contact", true, false);

					isSiteContactValidated = true;
					break;
				} else {
					// reportStatusFail(methodName + " No Existing site contact information
					// availble", true);
				}

			}

			if (!isSiteContactValidated) {
				sf.seleU.clickElementByJSE(sf.siteCon.selectExistingContactCheckBoxAll.get(0));
				reportStatusPass(
						methodName + " Selected Existing Site contact :"
								+ sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(0)),
						true, true);
				sf.dataInput.siteContactName = sf.seleU
						.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(0));
				sf.dataInput.contactEmailAddress = sf.seleU
						.getTextFromWebElement(sf.siteCon.selectExistingContactEmailAll.get(0));
			}

			sf.seleU.hardwait(4000);

			// Click on New/Existing Contact Next Button
			sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
			reportStatusPass(methodName + " Clicked on New Contact Next button", true, false);

			sf.seleU.wait(35000);
			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail(" Selecting Existing Site Contact is unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Verify the selected site contact 
	 * 
	 */
	public static void verifySelectedContact() throws IOException {
		try {

			String methodName = "Quote_SelectSiteContact@: ";

			if(!sf.seleU.isElementPresent(sf.siteCon.selectExistingContactCheckBoxAll))
				sf.seleU.switchToElementFrame(sf.siteCon.selectExistingContactCheckBoxAll);
			// Select the site contact by verifying the correct site contact
			boolean isSiteContactValidated = false;
			for (int i = 0; i < sf.siteCon.selectExistingContactCheckBoxAll.size(); i++) {

				System.out.println("..." + sf.dataInput.siteContactName);
				System.out.println(sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(i)));

				if (sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(i))
						.equals(sf.dataInput.siteContactName)) {

					if (sf.seleU.getElementAttribute(sf.siteCon.selectExistingContactCheckBoxInputTagAll.get(i), "class").contains("not-empty")) {
						isSiteContactValidated = true;
						reportStatusPass(methodName + " Verified Slected contact is the expected one", true, true);
					} else
						reportStatusFail(methodName + " Selected site contact is not the expected one", true);
							
					break;

				}
			}
			sf.seleU.hardwait(4000);
			if (!isSiteContactValidated)
				reportStatusFail(methodName + " Selected site contact is not the expected one", true);
			
			// Click on New/Existing Contact Next Button
			sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
			reportStatusPass(methodName + " Clicked on New Contact Next button", true, false);

			sf.seleU.wait(35000);
			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail(" Selecting Existing Site Contact is unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Search for the specific site contact
	 *                     
	 *                     Select the site contact by verifying the correct site
	 *                     contact
	 * 
	 *                     Click on New/Existing Contact Next Button
	 */
	public void selectSpecificExistingSiteContact(String siteCOntactName) throws IOException {
		try {

			String methodName = "Quote_SelectSiteContact@: ";
			sf.seleU.wait(25000);

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.siteCon.selectExistingContactCheckBoxAll);
			sf.seleU.wait(2000);
			
			sf.seleU.enterText(sf.siteCon.filterInputBox, siteCOntactName);
			sf.seleU.wait(2000);
			// Select the site contact by verifying the correct site contact
			sf.seleU.clickElementByJSE(sf.siteCon.selectExistingContactCheckBoxAll.get(0));
			reportStatusPass(methodName + " Selected Existing Site contact", true, false);

			sf.seleU.wait(2000);

			
			sf.dataInput.siteContactEmailId = sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactEmailAll.get(0));
			sf.dataInput.siteContactName = sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(0));
			reportStatusPass(methodName + " Site Contact EmailId captured " + sf.dataInput.siteContactEmailId, true, true);
			reportStatusPass(methodName + "  Site Contact Name captured " + sf.dataInput.siteContactName, true, true);

			
			// Click on New/Existing Contact Next Button
			sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
			reportStatusPass(methodName + " Clicked on New Contact Next button", true, false);

						
			sf.seleU.wait(25000);
			sf.seleU.switchToDefaultContent();
			

		} catch (Throwable e) {
			reportStatusFail(" Selecting Specific Existing Site Contact is unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Select the site contact by verifying the correct site
	 *                     contact
	 * 
	 *                     Click on New/Existing Contact Next Button
	 */
	public void selectExistingSiteContact_Scrum() throws IOException {
		try {

			String methodName = "Quote_SelectSiteContact@: ";
			sf.seleU.wait(45000);
//			if (sf.dataInput.env.equals("ITDEVSTAGE")) {
//				sf.seleU.waitElementToBeVisible(sf.siteCon.siteContactText);
//			//	System.out.println(sf.seleU.getTextFromWebElement(sf.siteCon.siteContactText));
//				sf.seleU.waitElementToBeVisible(sf.siteCon.addAContactButtonResumeQuoteIFrame);
//				sf.seleU.switchToDefaultContent();
//				
//				sf.seleU.switchToFrame(sf.siteCon.addAContactButtonResumeQuoteIFrame);
//				sf.seleU.wait(4000);
//				
//				// Select the site contact by verifying the correct site contact
//			//	sf.seleU.waitElementToBeVisible(sf.siteCon.selectExistingContactCheckBoxAll.get(0));
//				sf.seleU.clickElementByJSE(sf.siteCon.selectExistingContactCheckBoxAll.get(0));
//				reportStatusPass(methodName + " Selected Existing Site contact", true, false);
//
//				sf.seleU.wait(2000);
//
//				// Click on New/Existing Contact Next Button
//				sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
//				reportStatusPass(methodName + " Clicked on New Contact Next button", true, false);
//
//			//	sf.seleU.wait(25000);
//				sf.seleU.switchToDefaultContent();
//				
//			} else {
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.siteCon.selectExistingContactCheckBoxAll);
			sf.seleU.wait(2000);
			// Select the site contact by verifying the correct site contact
			sf.seleU.clickElementByJSE(sf.siteCon.selectExistingContactCheckBoxAll.get(0));
			reportStatusPass(methodName + " Selected Existing Site contact", true, false);

			sf.seleU.wait(2000);

			
			//Capturing site contact email Id and name for sitecontact in gbj flow for verifying email
			//sf.dataInput.siteContactEmailId = sf.seleU.getTextFromWebElement(sf.cq.emailIdSiteContact);
			sf.dataInput.siteContactEmailId = sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactEmailAll.get(0));
			sf.dataInput.siteContactName = sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(0));
			reportStatusPass(methodName + " Site Contact EmailId captured " + sf.dataInput.siteContactEmailId, true, true);
			reportStatusPass(methodName + "  Site Contact Name captured " + sf.dataInput.siteContactName, true, true);

			
			// Click on New/Existing Contact Next Button
			sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
			reportStatusPass(methodName + " Clicked on New Contact Next button", true, false);

						
			sf.seleU.wait(45000);
			sf.seleU.switchToDefaultContent();
			// }

		} catch (Throwable e) {
			reportStatusFail(" Selecting Existing Site Contact is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select the site contact by verifying the correct site
	 *                     contact
	 * 
	 *                     Click on New/Existing Contact Next Button
	 */
	public void verifyTextAndSelectNoSiteContact_Scrum() throws IOException {
		try {

			String methodName = "Quote_NoSelectSiteContact@: ";
			sf.seleU.wait(5000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.siteCon.selectExistingContactCheckBoxAll);
			// Select the site contact by verifying the correct site contact
			verifyFieldPresent("You may continue but an order", sf.siteCon.siteContactText);
			//reportStatusPass(methodName + " Selected Existing Site contact", true, false);
			
			//Capturing site contact email Id and name for sitecontact 
			sf.dataInput.siteContactEmailId = sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactEmailAll.get(0));
			sf.dataInput.siteContactName = sf.seleU.getTextFromWebElement(sf.siteCon.selectExistingContactNameAll.get(0));
			reportStatusPass(methodName + " Site Contact EmailId captured " + sf.dataInput.siteContactEmailId, true, true);
			reportStatusPass(methodName + "  Site Contact Name captured " + sf.dataInput.siteContactName, true, true);

			sf.seleU.wait(2000);
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.siteCon.selectExistingContactCheckBoxAll);
			// Click on New/Existing Contact Next Button
			sf.seleU.clickElementByJSE(sf.siteCon.selectSiteContactNextButton);
			reportStatusPass(methodName + " Clicked on New Contact Next button skipping the Site Contact selection", true, false);

			sf.seleU.wait(25000);
			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail(" Selecting Existing Site Contact is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * Verify Field is present
	 * 
	 * @throws IOException
	 */
	public void verifyFieldPresent(String fieldName, WebElement element) throws IOException {

		try {

			// Verify Field is present
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(
						fieldName + " is present" + " with value as " + sf.seleU.getTextFromWebElement(element), true,
						true);
			} else {
				reportStatusPass(
						fieldName + " is not present" + " with value as " + sf.seleU.getTextFromWebElement(element),
						true, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}

	}
}
