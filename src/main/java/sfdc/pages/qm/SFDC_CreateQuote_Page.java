package sfdc.pages.qm;

import java.io.IOException;

import org.openqa.selenium.Keys;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka Acharya, Date 14/Jan/2020
 * 
 *         SFDC Create Quote (Opportunity>create Quote)
 *
 */
public class SFDC_CreateQuote_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;

	public SFDC_CreateQuote_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * Date 17/Jan/2020
	 * 
	 * 1. Select 'Create Quote' button.
	 * 
	 * 2. Select contact having contact role as Signing Authority.
	 * 
	 * 3. Click on next button in Select Primary Signing Authority
	 * 
	 * 
	 * 
	 * @throws IOException
	 */

	public void createQuote_SelectContatRole_SigningAuthority() throws Exception {

		try {
			String methodName = "SFDC_Select Primary Signing Authority(Contact)@: ";
			sf.seleU.refreshPage();
			sf.seleU.hardwait(3000);

			// Select Create Quote button on opportunity Detail page

			if (dataInput.env.equals("ITDEVSTAGE"))
				sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuoteButtons.get(0));
			else
				sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuoteByGuidedJourneyButton);

			sf.seleU.hardwait(3000);
			sf.seleU.switchToElementFrame(sf.cq.opportunityContactNameList);

			// Iterate on Contact list table and find out the correct contact and select
			int contactListSize = sf.cq.opportunityContactNameList.size();
			boolean isContactSelected = false;
			for (int i = 0; i < contactListSize; i++) {

				if (sf.seleU.getTextFromWebElement(sf.cq.opportunityContactNameList.get(i))
						.equals(sf.dataInput.accountContact)
						&& sf.seleU.getTextFromWebElement(sf.cq.opportunityContactRoleTextList.get(i))
								.contains(sf.dataInput.opportunityContactRole)) {

					// check on contactlist checkbox
					sf.seleU.clickElementByJSE(sf.cq.opportunityContactListCheckbox.get(i));
					isContactSelected = true;
					break;
				}

			}

			if (isContactSelected) {
				reportStatusPass(methodName + " Selected Contact for Opportunity as " + sf.dataInput.accountContact,
						true, true);
			} else {

				// check on contactlist checkbox
				sf.seleU.clickElementByJSE(sf.cq.opportunityContactListCheckbox.get(0));

				reportStatusFail(methodName + " No Contact Found for Opportunity as " + sf.dataInput.accountContact
						+ ", Therefore selected the first contact present", true);
			}

			sf.seleU.hardwait(3000);

			// Click on next button in Select Primary Signing Authority
			sf.seleU.clickElementByJSE(sf.cq.selectPrimarySigningAuthoritynextButton);
			reportStatusPass(methodName + " Clicked on  next button in Select Primary Signing Authority", true, false);

			// sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting Primary Signing Authority for contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Create Quote button on opportunity Detail page
	 * 
	 *                     check on contactlist checkbox
	 * 
	 *                     Click on next button in Select Primary Signing Authority
	 */
	public void createQuote_SelectExistingSigningAuthority() throws IOException {
		try {
			String methodName = "SFDC_Select Primary Signing Authority(Contact)@: ";

			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(5000);
			
			if (sf.dataInput.env.equals("ITDEVSTAGE")) {
				if (sf.cq.editDropdown.size() > 1) {
					sf.seleU.clickElementByJSE(sf.cq.editDropdown.get(1));
				} else {
					sf.seleU.clickElementByJSE(sf.cq.editDropdown.get(0));
				}
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.cq.CreateQuoteButtoninDropDown);
				reportStatusPass(methodName + " Clicked on Create Quote by HybridCart ", true, true);
			}
			else {
			// Select Create Quote button on opportunity Detail page
			sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuoteButtons.get(0));
			}
			sf.seleU.hardwait(5000);
			sf.seleU.switchToElementFrame(sf.cq.opportunityContactNameList);

			// check on contactlist checkbox
			sf.seleU.clickElementByJSE(sf.cq.opportunityContactListCheckbox.get(0));
			reportStatusPass(methodName + " Selected Contact for Opportunity ", true, true);

			// Capturing signing authority email Id for hybrid cart flow verifying email
			// later
			sf.dataInput.signingAuthEmailIdValue = sf.seleU.getTextFromWebElement(sf.cq.emailId);
			sf.dataInput.emailIdValue = sf.dataInput.signingAuthEmailIdValue;
			reportStatusPass(methodName + "Signing Authority EmailId is " + sf.dataInput.signingAuthEmailIdValue, true,
					true);

			sf.dataInput.signingAuthName = sf.seleU.getTextFromWebElement(sf.cq.nameSignAuth);
			reportStatusPass(methodName + " Signing Authority Name is " + sf.dataInput.signingAuthName, true, true);
			sf.seleU.hardwait(3000);

			// Click on next button in Select Primary Signing Authority
			sf.seleU.clickElementByJSE(sf.cq.selectPrimarySigningAuthoritynextButton);
			reportStatusPass(methodName + " Clicked on  next button in Select Primary Signing Authority", true, false);

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting Primary Signing Authority for contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Select Create Quote Guided By Journey button on
	 *                     opportunity Detail page
	 * 
	 *                     check on contactlist checkbox
	 * 
	 *                     Click on next button in Select Primary Signing Authority
	 */
	public void createQuoteGuidedByJourney_SelectExistingSigningAuthority() throws IOException {
		try {
			String methodName = "SFDC_Select Primary Signing Authority(Contact)@: ";

			sf.seleU.switchToDefaultContent();

			// Select Create Quote Guided By Journey button on opportunity Detail page
			if (sf.dataInput.env.equals("PROD") || sf.dataInput.env.equals("R4BPREPROD")
					|| sf.dataInput.env.equals("WADEVQA") || sf.dataInput.env.equals("Summer21")) {
				sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuoteButtons.get(0));
			}

//			else if (sf.dataInput.env.equals("ITDEVSTAGE")) {
//
//				if (sf.cq.editDropdown.size() > 1) {
//					sf.seleU.clickElementByJSE(sf.cq.editDropdown.get(1));
//				} else {
//					sf.seleU.clickElementByJSE(sf.cq.editDropdown.get(0));
//				}
//
//				sf.seleU.clickElementByJSE(sf.cq.createQuoteByGbj);}
		 else {
				sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuoteByGuidedJourneyButton);
			}

			reportStatusPass(methodName + "Clicked on guided by journey ", true, false);

			sf.seleU.hardwait(6000);
			if (sf.seleU.getCurrentUrl().contains("partner")) {
				sf.seleU.switchWindow(3);
			}
			if (sf.dataInput.env.equals("ITDEVSTAGE")) {
				sf.seleU.switchToFrame(sf.cq.opportunityContactFrame);
			} else {
				sf.seleU.switchToElementFrame(sf.cq.opportunityContactNameList);
			}
			sf.seleU.hardwait(2000);

			// Capturing signing authority email Id for gbj flow verifying email later
			sf.dataInput.emailIdValue = sf.seleU.getTextFromWebElement(sf.cq.emailId);
			reportStatusPass(methodName + "GBJ Signing Auth EmailId captured " + sf.dataInput.emailIdValue, true, true);

			// check on contactlist checkbox
			sf.seleU.clickElementByJSE(sf.cq.opportunityContactListCheckbox.get(0));
			reportStatusPass(methodName + " Selected Contact for Opportunity ", true, true);

			sf.seleU.hardwait(3000);

			// Click on next button in Select Primary Signing Authority
			sf.seleU.clickElementByJSE(sf.cq.selectPrimarySigningAuthoritynextButton);
			reportStatusPass(methodName + " Clicked on  next button in Select Primary Signing Authority", true, false);

			sf.seleU.wait(15000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Selecting Primary Signing Authority for contact", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Select service account (Existing One)
	 * 
	 *                     2. Verify Serviceability
	 * 
	 *                     3. Click Next
	 */
	public void createQuote_SelectExistingServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";

			if (!sf.seleU.isElementDisplayed(sf.cq.createQuoteGetServiceAccountStep_nextBtn)) {
				reportStatusPass(methodName + " Verified if no ON-Net is Selected, Next button will not be displayed. ",
						true, true);
			} else {
				reportStatusFail(methodName + " Next Button is displayed , even if no on-net selected", true);
			}

			for (int i = 0; i < sf.cq.createQuoteServiceAbilityListCable.size(); i++) {
				// Verify Serviceability
				if (!(sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityListCable.get(i)) == null)) {
					if ((sf.dataInput.createQuoteServiceability.length()) > 0

							&& (sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityListCable.get(i))
									.contains(sf.dataInput.createQuoteServiceability))
							|| (sf.seleU.getElementAttribute(sf.cq.createQuoteServiceAbilityListCableHoverText.get(i),
									"title").contains(sf.dataInput.createQuoteServiceability))
							|| (sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityListCable.get(i))
									.contains(sf.dataInput.acc_RecordType_Business))
							|| (sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityListCable.get(i))
									.contains(sf.qmData.cableONNetResidential))) {

						// Select service account (Existing One)
						sf.seleU.clickElementByJSE(sf.cq.createQuoteSelectServiceAccountCheckBoxList.get(i));

						reportStatusPass(methodName + " Selected Servicability for Service Account for create Quote "
								+ Global.dataInput.createQuoteServiceability, true, true);

						break;
					}
				}

			}

			sf.seleU.wait(3000);
			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on next button in Select Service Account Page", true, false);

			sf.seleU.wait(10000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting service account for Quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1. Select first service account (Existing One)
	 * 
	 *                     2. Verify Serviceability
	 * 
	 *                     3. Click Next
	 */
	public void createQuote_SelectFirstServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";

			if (!sf.seleU.isElementDisplayed(sf.cq.createQuoteGetServiceAccountStep_nextBtn)) {
				reportStatusPass(methodName + " Verified if no ON-Net is Selected, Next button will not be displayed. ",
						true, true);
			} else {
				reportStatusFail(methodName + " Next Button is displayed , even if no on-net selected", true);
			}

			// Select service account (Existing One)
			sf.seleU.clickElementByJSE(sf.cq.createQuoteSelectServiceAccountCheckBoxList.get(0));

			reportStatusPass(methodName + " Selected Servicability for Service Account for create Quote ", true, true);

			sf.seleU.wait(3000);

			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on next button in Select Service Account Page", true, false);

			sf.seleU.wait(14000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting service account for Quote", e);
			e.printStackTrace();
		}
	}

	public void createQuote_SelectExistingServiceAccountDebug3() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";

			// Select On net
			sf.seleU.selectTextFromDropDown(sf.cq.selectONNetDebug3, "On Net");
			reportStatusPass(methodName + " Selected Sub Product as " + sf.dataInput.opportunityProductType, true,
					false);

			// Select service account (Existing One)
			sf.seleU.clickElementByJSE(sf.cq.createQuoteSelectServiceAccountCheckBoxList.get(0));

			sf.seleU.wait(3000);

			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on next button in Select Service Account Page", true, false);

			sf.seleU.wait(14000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting service account for Quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on create new service account checkbox
	 * 
	 *                     2.Click on next button in Select Service account
	 */
	public void createQuote_SelectNewServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";

			// Click on create new service account checkbox
			sf.seleU.clickElementByJSE(sf.cq.createServiceAccountCheckbox);
			reportStatusPass(methodName + " Selected Checkbox for creating new service account  ", true, true);

			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Select Service Account Page", true, false);

			// Preapare data for another service account
			sf.dataInput.multipleServiceAcc_prepareSAData();

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting New service account checkbox", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Add new site for new Service account
	 */
	public void createQuote_SelectAddNewSite() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";

			// Click on create new service account checkbox
			sf.seleU.clickElementByJSE(sf.cq.addNewSiteButton);
			reportStatusPass(methodName + " Selected add new site button", true, true);

			// Preapare data for another service account
			// sf.dataInput.multipleServiceAcc_prepareSAData();

			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting New service account checkbox", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     1.Iterate on service account list table and find out the
	 *                     correct service account and select
	 * 
	 *                     2.Check on Service account list checkbox
	 * 
	 *                     3.Select Serviceability
	 * 
	 *                     4.Click on next button in Select Service account
	 * 
	 */
	public void createQuote_SelectCreatedServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select Service Account@: ";

			// Iterate on service account list table and find out the correct service
			// account and select
			sf.seleU.switchToDefaultContent();

			sf.seleU.switchToElementFrame(sf.cq.createQuoteSelectServiceAccountNameList);
			search_CreatedServiceAccount();
			int serviceAccountListSize = sf.cq.createQuoteSelectServiceAccountNameList.size();
			boolean isSASelected = false;
			for (int i = 0; i < serviceAccountListSize; i++) {

				if (sf.seleU.getTextFromWebElement(sf.cq.createQuoteSelectServiceAccountNameList.get(i))
						.equals(sf.dataInput.serviceAccountName)) {

					// Verify Serviceability
					if (sf.dataInput.createQuoteServiceability.length() > 0
							&& (sf.seleU.getTextFromWebElement(sf.cq.createQuoteServiceAbilityListCable.get(i))
									.equals(sf.dataInput.createQuoteServiceability))
							|| (sf.seleU.getElementAttribute(sf.cq.createQuoteServiceAbilityListCableHoverText.get(i),
									"title").equals(sf.dataInput.createQuoteServiceability))) {
						reportStatusPass(methodName + " Selected Servicability for Service Account for create Quote "
								+ sf.dataInput.createQuoteServiceability, true, true);
					} else {
						reportStatusFail(methodName + " No Servicability for Service Account for create Quote "
								+ sf.dataInput.createQuoteServiceability, true);
					}

					// check on Service account list checkbox
					sf.seleU.clickElementByJSE(sf.cq.createQuoteSelectServiceAccountCheckBoxList.get(i));

					isSASelected = true;
					break;
				}

			}

			if (isSASelected) {
				reportStatusPass(
						methodName + " Selected Service Account for Quote as " + sf.dataInput.serviceAccountName, true,
						true);
			} else {
				reportStatusFail(methodName + " Unable to select Service Account for Quote  as "
						+ sf.dataInput.serviceAccountName, true);
			}

			sf.seleU.hardwait(3000);

			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Select Service Account Page", true, false);

			// sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(10000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting service account for Quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     1.Iterate on service account list table and find out the
	 *                     correct service account and select
	 * 
	 *                     2.Check on Service account list checkbox
	 * 
	 *                     3.Select Serviceability
	 * 
	 *                     4.Click on next button in Select Service account
	 * 
	 */
	public void search_CreatedServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Search created Quote Select Service Account@: ";

			sf.seleU.clearAndEnterText(sf.cq.searchAccountInFilter, sf.dataInput.serviceAccountName);
			sf.seleU.enterText(sf.cq.searchAccountInFilter, Keys.ENTER);
			sf.seleU.hardwait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in searching service account for Quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Use this method if you don't want to check the
	 *                     servicability and just want to match with with service
	 *                     account, after creating the account
	 * 
	 *                     1.Iterate on service account list table and find out the
	 *                     correct service account and select
	 * 
	 *                     2.Check on Service account list checkbox
	 * 
	 *                     3.Click on next button in Select Service account
	 * 
	 */
	public void createQuote_SelectCreatedNewServiceAccount() throws IOException {
		try {
			String methodName = "SFDC_Create Quote Select newly created Service Account@: ";
			sf.seleU.hardwait(3000);
			// Iterate on service account list table and find out the correct service
			// account and select
			sf.seleU.switchToDefaultContent();

			if (sf.dataInput.env.equals("ITDEVSTAGE")) {
				sf.seleU.switchToFrame(sf.cq.createServiceAccountFrame);
			} else {
				sf.seleU.switchToElementFrame(sf.cq.createQuoteSelectServiceAccountNameList);
			}

			search_CreatedServiceAccount();

			int serviceAccountListSize = sf.cq.createQuoteSelectServiceAccountNameList.size();
			boolean isSASelected = false;
			sf.seleU.wait(3000);
			for (int i = 0; i < serviceAccountListSize; i++) {
				sf.seleU.wait(2000);
				if (sf.seleU.getTextFromWebElement(sf.cq.createQuoteSelectServiceAccountNameList.get(i))
						.equals(sf.dataInput.serviceAccountName)) {

					reportStatusPass(methodName + " Selected Servicability for Service Account for create Quote "
							+ sf.dataInput.createQuoteServiceability, true, true);

					// check on Service account list checkbox
					sf.seleU.clickElementByJSE(sf.cq.createQuoteSelectServiceAccountCheckBoxList.get(i));

					isSASelected = true;
					break;
				}

			}

			if (isSASelected) {
				reportStatusPass(
						methodName + " Selected Service Account for Quote as " + sf.dataInput.serviceAccountName, true,
						true);
			} else {
				reportStatusFail(methodName + " Unable to select Service Account for Quote  as "
						+ sf.dataInput.serviceAccountName, true);
			}

			sf.seleU.hardwait(3000);

			// Click on next button in Select Service account
			sf.seleU.clickElementByJSE(sf.cq.createQuoteGetServiceAccountStep_nextBtn);
			reportStatusPass(methodName + " Clicked on  next button in Select Service Account Page", true, false);

			// sf.seleU.switchToDefaultContent();
			sf.seleU.hardwait(10000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting service account for Quote", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on next in billing account page
	 */
	public void createQuote_SelectBillingAccountNext() throws IOException {
		try {
			String methodName = "SFDC_Select Billing Accout Next Button@: ";

			sf.seleU.wait(3000);

			// Click on next button in Select Service account
			if (sf.seleU.isElementDisplayed(sf.cq.createQuoteGetBillingAccountStep_nextBtn)) {
				sf.seleU.clickElementByJSE(sf.cq.createQuoteGetBillingAccountStep_nextBtn);
				reportStatusPass(methodName + " Clicked on next button in Select Billing Account Page", true, false);
			}
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in selecting next in billing account page", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify if 'create quote by PBF' button is present or not
	 *                     in opportunity details page
	 */
	public void verifyCreateQuotePBF_NotPresent() throws IOException {

		String methodName = "SFDC_Opportunity Details Quote Creation @: ";
		try {
			sf.seleU.switchToDefaultContent();
			sf.seleU.wait(5000);

			// Verify if 'create quote by PBF' button is present or not

			if (!sf.seleU.isElementDisplayed(sf.cq.opportunityDetailCreateQuotePBFButton)) {
				reportStatusPass(methodName + " Verified ' Create Quote by PBF' button is not present", true, true);
			} else {
				reportStatusFail(" Error : ' Create Quote by PBF' button is present", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Create Quote By PBF not present", e);
			e.printStackTrace();
		}
	}

	/**
	 * Date 29/April/2021
	 * 
	 * Click 'Create Quote by PBF' button.
	 * 
	 * For wireless products
	 * 
	 * @throws IOException
	 */

	public void clickCreateQuotePbfButton() throws Exception {
		//sf.seleU.refreshPage();
		try {
			String methodName = "SFDC_CreateQuote@: ";
			sf.seleU.hardwait(3000);
			// Select Create Quote button on opportunity Detail page
			if (sf.seleU.isElementPresent(sf.cq.opportunityDetailCreateQuotePBFButton)) {
				sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuotePBFButton);
				sf.seleU.hardwait(3000);
				reportStatusPass(methodName + "Create Quote PBF button is clicked", true, true);
			}
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting create Quote by PBF button", e);
			e.printStackTrace();
		}
	}
	

	/**Pankaj Agarwal
	 * Date 8th/Feb/2022
	 * 
	 * Click 'Create Quote by PBF' button.
	 * 
	 * For wireless products
	 * 
	 * @throws IOException
	 */

	public void clickCreateQuotePbfButton_New() throws Exception {
	//	sf.seleU.refreshPage();
		try {
			String methodName = "SFDC_CreateQuoteThruPBF@: ";
			sf.seleU.hardwait(3000);
			// Select Create Quote button on opportunity Detail page
			if (sf.seleU.isElementPresent(sf.cq.createQuoteThruPBFButton)) {
				sf.seleU.clickElementByJSE(sf.cq.createQuoteThruPBFButton);
				sf.seleU.hardwait(3000);
				reportStatusPass(methodName + "Create Quote PBF button is clicked", true, true);
			}
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting create Quote by PBF button", e);
			e.printStackTrace();
		}
	}
  
  /*
	 * 
	 *Created for FIT Env
	 */
	public void clickCreateQuotePbfButton_FIT() throws Exception {
		sf.seleU.refreshPage();
		try {
			String methodName = "SFDC_CreateQuote@: ";
			sf.seleU.hardwait(3000);
			// Select Create Quote button on opportunity Detail page
			if (sf.seleU.isElementPresent(sf.cq.opportunityDetailCreateQuoteButton_FIT)) {
				sf.seleU.clickElementByJSE(sf.cq.opportunityDetailCreateQuoteButton_FIT);
				sf.seleU.hardwait(3000);
				reportStatusPass(methodName + "Create Quote PBF button is clicked", true, true);
			}
			sf.seleU.hardwait(5000);
		} catch (Throwable e) {
			reportStatusFail(" Error in selecting create Quote by PBF button", e);
			e.printStackTrace();
		}
	}
	
	/*
	 *Driver will wait till Create Quote button load and Display 
	 */
	public void wait_Till_Load_CreateQuote_Button() throws Exception {
		sf.seleU.waitTillLoading();
		try {
			sf.seleU.waitElementToBeVisible(sf.cq.opportunityDetailCreateQuoteButton_FIT);
		} catch (Throwable e) {
			reportStatusFail(" create Quote button is not loaded or visibled !!", e);
			e.printStackTrace();
		}
	}
}