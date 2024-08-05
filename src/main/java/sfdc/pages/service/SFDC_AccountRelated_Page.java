package sfdc.pages.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Service;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,Date 14/jan/2020
 * 
 *         SFDC Account Related page
 *
 */
public class SFDC_AccountRelated_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public String methodName;

	public SFDC_AccountRelated_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Account_Related@: ";
	}

	/**
	 * Date: 14/Jan/2019
	 * 
	 * @throws IOException
	 * 
	 *                     1. Click on Related/Overview tab on account
	 * 
	 *                     2. Click New New Opportunity
	 * 
	 * 
	 */
	public void createOpportunity() throws IOException {
		try {

			boolean oppSectionFound = false;

//			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
//				sf.seleU.clickElementByJSE(sf.ad.closeButton);
//			}
//			sf.seleU.clickElementByJSE(sf.ar.relatedTab);
//			reportStatusPass(methodName + "Clicked ON Related Tab to create Opportunity", true, true);
//			sf.seleU.hardwait(4000);

			if (sf.seleU.isElementPresent(sf.ar.newOpportunityButton)) {
				oppSectionFound = true;
			} else {
				sf.seleU.clickElementByJSE(sf.ad.overviewTab);
				sf.seleU.hardwait(4000);
				for (int i = 0; i < 100; i++) {
					sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
					if (sf.seleU.isElementPresent(sf.ar.newOpportunityButton)) {
						oppSectionFound = true;
						break;
					}
					sf.seleU.hardwait(2000);
				}
			}
			// click on New Opportunity Button

			if (oppSectionFound) {
				sf.seleU.ScrolltoElement(sf.ar.newOpportunityButton);
				sf.seleU.clickElementByJSE(sf.ar.newOpportunityButton);
				reportStatusPass(methodName + " Clicked on New Opportunity Button", true, false);
				sf.seleU.hardwait(3000);
			} else {
				reportStatusFail(methodName + "Selecting New Opportunity is Unsuccessful", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Selecting New Opportunity is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void validateRelationshipContactBillingServiceAccountForACRPermission(boolean signingAuthority) throws IOException
	{
		String methodName = "SFDC_Verify Contact@: ";
		try 
		{									
			//click on view all under Related Accounts
 		    sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.cd.viewAllRelatedAccount);
			sf.seleU.hardwait(3000);
											
			//validate ACR Permissions value
			if(signingAuthority)
			{
			verifyFieldValue("Account Record Type", sf.ar.accountRecordTypeValue.get(0), "Business");
			sf.seleU.hardwait(3000);			
			String[] ACRPermissionValues = sf.seleU.getTextFromWebElement(sf.ar.contactAcrPermissionText.get(0)).split(";");				
			List<String> actualACRPermissionValues = new ArrayList<String>(Arrays.asList(ACRPermissionValues));				
			InputData_Service.setDataForACRPermissionValues();				
			List<String> expectedACRPermissionValues = InputData_Service.ACRPermissionValues;				
			Collections.sort(actualACRPermissionValues);				
			Collections.sort(expectedACRPermissionValues);
			
			if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(0), "alt").equals("True")		
			   && actualACRPermissionValues.equals(expectedACRPermissionValues))
			{
				reportStatusPass(methodName + " Verified relationship Type  is Direct & ACR permission is selected ", true, true);
				sf.seleU.hardwait(4000);
			}
			}
			
			if(!signingAuthority)
			{
			verifyFieldValue("Account Record Type", sf.ar.accountRecordTypeValue.get(0), "Business");
			sf.seleU.hardwait(3000);
			if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(0), "alt").equals("True")		
			   && sf.seleU.getTextFromWebElement(sf.ar.contactAcrPermissionText.get(0)).equals("Administrator"))
			{
				reportStatusPass(methodName + " Verified relationship Type  is Direct & no ACR permission is selected ", true, true);
				sf.seleU.hardwait(4000);
			}
			}
			
			verifyFieldValue("Account Record Type", sf.ar.accountRecordTypeValue.get(1), "Service");
			sf.seleU.hardwait(3000);			
			if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(1), "alt").equals("False")	
			    && sf.seleU.getTextFromWebElement(sf.ar.contactAcrPermissionText.get(1)).equals("Site Contact"))
			{
				reportStatusPass(methodName + " Verified relationship Type  is InDirect & ACR permission is selected ", true, true);
				sf.seleU.hardwait(4000);
			}
			
//			verifyFieldValue("Account Record Type", sf.ar.accountRecordTypeValue.get(0), "Billing");
//			sf.seleU.hardwait(3000);
//			String[] ACRPermissionValues = sf.seleU.getTextFromWebElement(sf.ar.contactAcrPermissionText.get(0)).split(";");				
//			List<String> actualACRPermissionValues = new ArrayList<String>(Arrays.asList(ACRPermissionValues));				
//			InputData_Service.setDataForACRPermissionValuesBillingAccount();				
//			List<String> expectedACRPermissionValues = InputData_Service.ACRPermissionValuesBillingAccount;				
//			Collections.sort(actualACRPermissionValues);				
//			Collections.sort(expectedACRPermissionValues);

//			if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(0), "alt").equals("False")	
//		       && actualACRPermissionValues.equals(expectedACRPermissionValues))
//			{
//				reportStatusPass(methodName + " Verified relationship Type  is InDirect & ACR permission is selected ", true, true);
//				sf.seleU.hardwait(4000);
//			}

			else 
			{
			    reportStatusFail(methodName + " Error in Verifying Relationship Types and ACR Permission value ", true);
			}						
	}
			catch (Throwable e) 
		{
				reportStatusFail(methodName + " Error in validating Relationship Contact Billing Service Account For ACR Permission ", e);
				e.printStackTrace();
		}			
   }

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Related tab under account view
	 * 
	 *                     2.Click on Entitlements
	 * 
	 *                     3.Validate Entitlements present
	 */
	
	public void validateEntitlementAssosciated() throws IOException 
	{
		try 
		{
			//click on related tab
			sf.seleU.switchToDefaultContent();
			sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			sf.seleU.hardwait(4000);
			
			//click on Entitlements
//			sf.seleU.ScrolltoElement(sf.ar.relatedContactsViewAll);
//   		sf.seleU.hardwait(3000);
			sf.seleU.ScrolltoElement(sf.ar.accountHistorySubTabLink);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.ar.entitlements);
			sf.seleU.hardwait(4000);
			
			//validate Entitlements
			if(sf.seleU.isElementDisplayed(sf.ar.standardServiceSupport) &&
			   sf.seleU.isElementDisplayed(sf.ar.standardTechnicalSupport))
			{
				reportStatusPass(methodName + " Entitlements validated ", true, true);
			}
			else
			{
				reportStatusFail(methodName + " Error in validating Entitlements ", true);
			}
		}
			catch (Throwable e) 
			{
				reportStatusFail(" Error in validating Entitlements ", e);
				e.printStackTrace();
			}		
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Related tab under account view
	 * 
	 *                     2.Verify orders are present in Account>related>orders
	 * 
	 *                     3.Click on Order Link in Account>related>orders
	 */
	public void viewOrdersInAccountRelatedTab() throws IOException {
		try {

			// Click on Related tab under account view
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			reportStatusPass(methodName + " Clicked on Overview tab in Accounts Page", true, false);

			sf.seleU.hardwait(4000);
			for (int i = 0; i < 100; i++) {
				sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
				if (sf.seleU.isElementPresent(sf.ar.ordersSubTabLink)) {
					break;
				}
			}

			// Verify orders are present in Account>related>orders
			sf.seleU.ScrolltoElement(sf.ar.ordersSubTabLink);
			if (sf.ar.ordersAllLinks.size() > 0) {
				reportStatusPass(methodName + "Validated orders present in Account>Overview>orders", true, true);
			} else {
				reportStatusFail(" No orders present in account>Overview>orders", true);
			}

			// Click on Order Link in Account>related>orders
			sf.seleU.clickElementByJSE(sf.ar.ordersAllLinks.get(0));
			reportStatusPass(methodName + " Clicked on first order link in Account>related>orders", true, false);

			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing Orders in Account Overview Tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related tab under account view
	 * 
	 *                     Verify Data Governance approval Present in
	 *                     Account>related>Approval History
	 * 
	 *                     Click on Approve Button
	 * 
	 *                     Enter Approval Mesaage
	 * 
	 *                     Click on Approve Button
	 * 
	 *                     Verify Account is Approved
	 * 
	 */
	public void approveAsDataGovernance() throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			
			// Click on Details tab under account view

			if (sf.seleU.isElementDisplayed(sf.ar.detailsTab))
			sf.seleU.clickElementByJSE(sf.ar.detailsTab);
			else{
				 sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.detailsTab);

				}
			sf.seleU.hardwait(3000);
			sfdc.accDetails.addSalesSegmentInAccountDetails();
			
			//Below code will only execute if , we want to skip equifax Check. 
			if (sf.dataInput.FNF.equals("No"))
			{
			sfdc.accDetails.checkCreditFraudExempt();
			}
			// Click on Related tab under account view

			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			else{
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			}
			sf.seleU.hardwait(3000);
//			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
//				sf.seleU.clickElementByJSE(sf.ad.closeButton);
//			}
//			
			

			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.wait(6000);

			// Verify Data Governance approval Present in Account>related>Approval History

			int i = 0;
			while (true) {
				sf.seleU.scrollByCoOrdinates(1);
				sf.seleU.wait(3000);

				if (sf.seleU.isElementDisplayed(sf.ar.dataGovernanceApproveButton) || i == 5) {
					break;
				}
				i++;
			}

			sf.seleU.ScrolltoElement(sf.ar.approvalHistorySubTabLink);
			if (sf.ar.approvalAllLinks.size() > 0 && sf.seleU.isElementDisplayed(sf.ar.dataGovernanceApproveButton)) {
				reportStatusPass(
						methodName + "Validated Data Governance approval present in Account>related>Approval History",
						true, true);
			} else {
				reportStatusFail(" No Data Governance approval present in account>realted>Approval History", true);
			}
			sf.seleU.wait(6000);
			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.dataGovernanceApproveButton);
			reportStatusPass(methodName + " Clicked on Data Governance Approve Button", true, false);
			sf.seleU.hardwait(3000);

			// Enter Approval Mesaage
			sf.seleU.enterText(sf.ar.commentTextArea, Global.dataInput.accountApprovalMsg);
			reportStatusPass(
					methodName + " Entered Data Governanace Approval Mesaage as " + Global.dataInput.accountApprovalMsg,
					true, false);

			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.approveAccountButton);
			reportStatusPass(methodName + " Clicked on Approve Button", true, false);
			sf.seleU.wait(2000);

			// Verify Account is Approved
			if (sf.seleU.isElementDisplayed(sf.ar.accountWasApprovedMsg)) {
				reportStatusPass(methodName + " Verified Account Approval mesaage ", true, true);
			} else {
				 //reportStatusFail(" Error in Approving Account", true);
			}

			Global.dataInput.businessAccountStatus = Global.dataInput.status_Active;
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Approving Account as Data Governance", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related tab under account view
	 * 
	 *                     Verify Data Governance approval Present in
	 *                     Account>related>Approval History
	 * 
	 *                     Click on Approve Button
	 * 
	 *                     Enter Approval Mesaage
	 * 
	 *                     Click on Approve Button
	 * 
	 *                     Verify Account is Approved
	 * 
	 * 
	 * 
	 */
	public void approveAsDataGovernanceWithOutcreditCheckExempt(Hashtable<String, String> dataTable)
			throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			sfdc.accDetails.addSalesSegmentInAccountDetails();

			// If it doesn't requires credit check validation then click credit check exempt
			if (dataTable.get("Credit_Check_Value_Enter").equals("NA")) {
				System.out.println("clicked on crdit frad check exempt");
				sfdc.accDetails.checkCreditFraudExempt();
			}

			// Click on Related tab under account view
			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			}
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.hardwait(3000);

			// Verify Data Governance approval Present in Account>related>Approval History
			sf.seleU.ScrolltoElement(sf.ar.approvalHistorySubTabLink);
			if (sf.ar.approvalAllLinks.size() > 0 && sf.seleU.isElementDisplayed(sf.ar.dataGovernanceApproveButton)) {
				reportStatusPass(
						methodName + "Validated Data Governance approval present in Account>related>Approval History",
						true, true);
			} else {
				reportStatusFail(" No Data Governance approval present in account>realted>Approval History", true);
			}

			sf.seleU.hardwait(3000);
			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.dataGovernanceApproveButton);
			reportStatusPass(methodName + " Clicked on Data Governance Approve Button", true, false);
			sf.seleU.hardwait(3000);

			// Enter Approval Mesaage
			sf.seleU.enterText(sf.ar.commentTextArea, Global.dataInput.accountApprovalMsg);
			reportStatusPass(
					methodName + " Entered Data Governanace Approval Mesaage as " + Global.dataInput.accountApprovalMsg,
					true, false);

			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.approveAccountButton);
			reportStatusPass(methodName + " Clicked on Approve Button", true, false);
			sf.seleU.wait(2000);

			// Verify Account is Approved
			if (sf.seleU.isElementDisplayed(sf.ar.accountWasApprovedMsg)) {
				reportStatusPass(methodName + " Verified Account Approval mesaage ", true, true);
			} else {
				// reportStatusFail(" Error in Approving Account", true);
			}

			Global.dataInput.businessAccountStatus = Global.dataInput.status_Active;
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Approving Account as Data Governance", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related tab under account view
	 * 
	 *                     Verify Data Governance approval Present in
	 *                     Account>related>Approval History
	 * 
	 *                     Click on Reject Button
	 * 
	 *                     Enter Rejection Mesaage
	 * 
	 *                     Click on Reject Button
	 * 
	 *                     Verify Account is Rejected
	 * 
	 */
	public void rejectAsDataGovernance() throws IOException {
		try {
			// Click on Related tab under account view
			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			}
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.hardwait(3000);

			// Verify Data Governance approval Present in Account>related>Approval History
			sf.seleU.ScrolltoElement(sf.ar.approvalHistorySubTabLink);
			if (sf.ar.approvalAllLinks.size() > 0 && sf.seleU.isElementDisplayed(sf.ar.dataGovernanceRejectButton)) {
				reportStatusPass(methodName
						+ "Validated Data Governance approval/rejection present in Account>related>Approval History",
						true, true);
			} else {
				reportStatusFail(" No Data Governance approval/rejection present in account>realted>Approval History",
						true);
			}

			// Click on Reject Button
			sf.seleU.clickElementByJSE(sf.ar.dataGovernanceRejectButton);
			reportStatusPass(methodName + " Clicked on Data Governance Reject Button", true, false);
			sf.seleU.hardwait(3000);

			// Enter Rejection Mesaage
			sf.seleU.enterText(sf.ar.commentTextArea, Global.dataInput.accountRejectionMsg);
			reportStatusPass(methodName + " Entered Data Governanace Rejection Mesaage as "
					+ Global.dataInput.accountRejectionMsg, true, false);

			// Click on Reject Button
			sf.seleU.clickElementByJSE(sf.ar.rejectAccountButton);
			reportStatusPass(methodName + " Clicked on Reject Button", true, false);
			sf.seleU.hardwait(2000);

			// Verify Account is Rejected
			if (sf.seleU.isElementDisplayed(sf.ar.accountWasRejectedMsg)) {
				reportStatusPass(methodName + " Verified Account Rejection mesaage ", true, true);
			} else {
				reportStatusFail(" Error in Rejecting Account", true);
			}

			sf.seleU.hardwait(3000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Approving Account as Data Governance", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Account Name in contact
	 * 
	 *                     Click on Related tab under account view
	 * 
	 *                     Verify Service Group Present in Account>related>Service
	 *                     Groups
	 * 
	 *                     Verify Service Group Details
	 * 
	 * 
	 */
	public void verifyServiceGroupsAddedToAccount() throws IOException {
		try {

			// Click on Account Name in contact
			sf.seleU.clickElementByJSE(sf.cd.accountNameText);
			reportStatusPass(methodName + " Clicked on Account Link in Contact Details Page", true, false);
			sf.seleU.hardwait(8000);

			// Click on Related tab under account view
			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			else {
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			}
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.wait(5000);

			// Verify Service Group Present in Account>related>Service Groups
			sf.seleU.ScrolltoElement(sf.ar.serviceGroupTab);
			sf.seleU.wait(20000);
			if (sf.ar.serviceGroupNameAllValues.size() > 0) {
				reportStatusPass(methodName + "Validated service group is present in Account>related>Service Groups",
						true, true);
			} else {
				reportStatusFail(" No Service Group is present", true);
			}

			// Verify Service Group Details
			boolean sgFound = false;
			for (int i = 0; i < sf.ar.serviceGroupNameAllValues.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.ar.serviceGroupNameAllValues.get(i))
						.equals(Global.dataInput.supportGroupName)) {

					verifyFieldValue("Service Group Name", sf.ar.serviceGroupNameAllValues.get(i),
							Global.dataInput.supportGroupName);
					verifyFieldValue("Service Group Email", sf.ar.serviceGroupEmailAllValues.get(i),
							Global.dataInput.supportGroupEmail);
					verifyFieldValue("Service Group Phone ", sf.ar.serviceGroupPhoneAllValues.get(i),
							Global.dataInput.supportGroupPhone);
					verifyFieldValue("Service Group Product ", sf.ar.serviceGroupsProductsSupportedAllValues.get(i),
							Global.dataInput.suuportGroupProductsSupported);

					sgFound = true;

				}
			}

			// Delete Service Group
			if (sgFound) {
				sf.seleU.clickElementByJSE(sf.ar.serviceGroupOptionsImg);
				sf.seleU.clickElementByJSE(sf.ar.deleteOptionInServiceGroup);
				sf.seleU.clickElementByJSE(sf.ar.serviceGroupDeleteButton);
				sf.seleU.wait(2000);
				// verifyFieldDisplayed("Service Group Deleted msg",
				// sf.ar.serviceGroupDeletedMsg);
			} else {
				reportStatusFail("Error in Verifying Service Groups", true);
			}

		} catch (Throwable e) {
			reportStatusFail("Error in Verifying Service Groups", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Related tab under account view
	 * 
	 *                     Verify orders are present in Account>related>assets
	 * 
	 * 
	 */
	public void verifyAssetsCreated() throws IOException {

		try {

			verifyFieldValue("Service Account Record Type", sf.ad.accountRecordTypeValueText,
					Global.dataInput.acc_RecordType_Service);
			verifyFieldValue("Service Account Parent Account", sf.ad.parentAccountValueText,
					Global.dataInput.businessAccountName);
			verifyFieldValue("Service Account Number", sf.ad.accountNumberFieldValue.get(0), InputData.superSystemCAN);

			sf.seleU.clickElementByJSE(sf.ar.allRelatedTab.get(1));
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.hardwait(5000);

			// Verify orders are present in Account>related>assets
			sf.seleU.ScrolltoElement(sf.ar.assetsSubTabLink);
			if (sf.ar.assetsAllLinks.size() > 0) {
				reportStatusPass(methodName + "Validated assets present in Account>related>assets", true, true);
			} else {
				reportStatusFail(methodName + " No assets present in account>realted>assets", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing Assets in Account Related Tab", true);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Assets Created for each of the Product/promotion
	 */
	public void verifyAssetsDetails(Hashtable<String, String> dataTable) throws IOException {
		try {

			boolean internetFound = false;
			boolean tvFound = false;
			boolean office365Found = false;

			sf.seleU.clickElementByJSE(sf.ar.assetsSubTabLink);
			sf.seleU.wait(5000);

			// Verify Assets Created for each of the Product/promotion
			for (int i = 0; i < sf.ar.assetsAllLinksInServiceAssets.size(); i++) {

				if (sf.seleU.getTextFromWebElement(sf.ar.assetsAllLinksInServiceAssets.get(i))
						.equalsIgnoreCase(Global.dataInput.quoteProductName)) {
					internetFound = true;
				}

				if (sf.seleU.getTextFromWebElement(sf.ar.assetsAllLinksInServiceAssets.get(i))
						.equalsIgnoreCase(Global.dataInput.office365ProductName)) {
					office365Found = true;
				}

				if (sf.seleU.getTextFromWebElement(sf.ar.assetsAllLinksInServiceAssets.get(i))
						.equalsIgnoreCase(Global.dataInput.tvProductName)) {
					tvFound = true;
				}

			}

			if (internetFound && tvFound && office365Found) {
				reportStatusPass(methodName + "Validated Internet , TV and office 365 Assets Created Successfully",
						true, true);
			} else {
				if (dataTable.get("Product _Type").contains(InputData.tvProduct) && tvFound && (!internetFound)
						&& (!office365Found)) {
					reportStatusPass(methodName + "Validated  TV  Assets Created Successfully", true, true);
				} else {
					reportStatusFail(methodName + " Assets Missing in Assets Tab ", true);
				}
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Assets Details ", true);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Related tab under account view
	 * 
	 *                     2.Verify orders are present in Account>related>orders
	 * 
	 *                     3.Click on Order Link in Account>related>orders
	 */
	public void clickOnOpportunity() throws IOException {
		try {

			// Click on Related tab under account view
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ad.overviewTab);
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.wait(3000);

			// click on New Opportunity Button
			sf.seleU.ScrolltoElement(sf.ar.opportunitiesSectionText);

			sf.seleU.hardwait(3000);

			// Verify opportunity are present in Account>related>opportunity
			sf.seleU.ScrolltoElement(sf.ar.opportunitySubTabLink);
			if (sf.ar.opportunityNameLink.size() > 0) {
				reportStatusPass(methodName + "Validated Opportunity is present in Account>related>Opportunity", true,
						true);
			} else {
				reportStatusFail(" No opportunity is present in account>realted>opportunity", true);
			}

			// Click on Order Link in Account>related>orders
			sf.seleU.clickElementByJSE(sf.ar.opportunityNameLink.get(0));
			reportStatusPass(methodName + " Clicked on first order link in Account>related>opportunity", true, false);

			sf.seleU.wait(4000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Viewing opportunity in Account Related Tab", e);
			e.printStackTrace();
		}
	}

	/**
	 * 1- Verify Relationship is direct or indirect 2. Verifying ACR permission for
	 * Business, Service and Billing account 3. Verifying ACR permission for
	 * Business account in related contact list in the account related page 4.
	 * Remove relationship for service and billing account after verifying Acr
	 * value.
	 *
	 * @throws IOException
	 */
	public void validateAcrPermissionInAccountRelated(String contactName, String acrValue[], String rolesValue[],
			boolean removeRelationship) throws IOException {

		try {

			String methodName = "SFDC_Verify Relationship and ACR@: ";
			boolean isFound = false;
			int index = 0;

			// Clicked on the contact tab in Account page
			sf.seleU.wait(1000);

//			sf.seleU.waitElementToBeVisible(sf.ar.relatedTab);
//			sf.seleU.clickElementByJSE(sf.ar.relatedTab);
//			reportStatusPass(methodName + "Clicked on the account Related tab", true, false);
//			sf.seleU.wait(2000);

			sf.seleU.waitElementToBeVisible(sf.ar.contactTab);
			sf.seleU.clickElementByJSE(sf.ar.contactTab);
			reportStatusPass(methodName + "Clicked on the contact tab in the business account page", true, false);
			sf.seleU.wait(2000);

			// Click on view all of related contact
			sf.seleU.ScrolltoElement(sf.ar.relatedContactViewAll);
			sf.seleU.clickElementByJSE(sf.ar.relatedContactViewAll);
			reportStatusPass(methodName + "Clicked on the related contact view all", true, false);
			sf.seleU.wait(1000);

			// Click on quick filter option in related account page list
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterClick);
			reportStatusPass(methodName + "Clicked on related contact Quick Filter button", true, false);
			sf.seleU.wait(2000);

			// Enter the account name to be filtered
			sf.seleU.clearAndEnterText(sf.ar.quickFilterEnterContactName, contactName);
			reportStatusPass(methodName + "Enter the Contact name as " + contactName, true, false);
			sf.seleU.hardwait(3000);

			// Click on apply button after entering the account name
			sf.seleU.clickElementByJSE(sf.cd.contactQuickFilterApplyButton);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Clicked on quick filter apply button ", true, false);

			// Click on Quick filter hide button
			sf.seleU.clickElementByJSE(sf.cd.contactHideFilterClick);

			for (int i = 0; i < sf.ar.contactNameText.size(); i++) {
				sf.seleU.wait(2000);
				// Matching with Record type and Account number in the related account list
				// after filtering the account

				if (sf.seleU.getTextFromWebElement(sf.ar.contactNameText.get(i)).trim().contains(contactName.trim())) {

					// If the direct check box is checked then the relationship is direct between
					// the contact and account
					if (sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(i), "class").contains("checked")
							&& sf.seleU.getElementAttribute(sf.ar.contactAcrDirectCheckBox.get(i), "alt")
									.contains("True")) {
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName + "Record is found in the related account list and contact "
								+ sf.seleU.getTextFromWebElement(sf.ar.contactNameText.get(i))
								+ "Relationship is Direct /n with the account ", true, false);
						isFound = true;
						index = i;
						break;

					} else {
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName
								+ "Record is found in the related account list and Relationship is InDirect /n with the account "
								+ sf.seleU.getTextFromWebElement(sf.ar.contactNameText.get(i)) + " ", true, false);
						isFound = true;
						index = i;
						break;
					}
				}
			}

			// If the Record is found in the Related Account List fetch ACR value and verify
			if (isFound) {
				sf.seleU.wait(2000);

				sf.seleU.hardwait(3000);
				verifyFieldValueWithCollections("ACR field value is displayed",
						sf.ar.contactAcrPermissionText.get(index), acrValue);

				verifyFieldValueWithCollections("Role value is displayed", sf.ar.contactRoleTypeText.get(index),
						rolesValue);

			}

			// No Account found in the Related account list in the contact related page
			else {
				reportStatusFail(methodName + " No record found for the account in the related account list", true);
			}

			sf.seleU.wait(2000);
		} catch (Throwable e) {
			reportStatusFail(" Verifying relationship and ACR value for the account and contact is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on View All contacts from Contacts Tab
	 */
	public void viewAllContactsFromContactsTab() throws IOException {

		String methodName = "SFDC_Account Details@: ";

		try {
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ar.contactTab);
			reportStatusPass(methodName + "Clicked on the contact tab in the business account page", true, false);
			sf.seleU.wait(2000);

			// Click on view all of related contact
			sf.seleU.ScrolltoElement(sf.ar.relatedContactViewAll);
			sf.seleU.clickElementByJSE(sf.ar.relatedContactViewAll);
			reportStatusPass(methodName + "Clicked on the related contact view all", true, false);
			sf.seleU.wait(1000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not view all related contacts", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *                     Click on new contact from Contacts Tab
	 */
	public void clickNewContact() throws IOException {

		String methodName = "SFDC_Contacts@: ";

		try {
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ar.contactTab);
			reportStatusPass(methodName + "Clicked on the contact tab in the business account page", true, false);
			sf.seleU.wait(2000);

			// Click on new contact
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ar.newContact);
			reportStatusPass(methodName + "Clicked on the New Contact", true, false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Could not click on new contact", e);
			e.printStackTrace();
		}
	}
	
	/* Verify new contact created
	 * 
	 */
	public void validateNewCreateContact() throws IOException{
		String methodName = "SFDC_Verify Contact@: ";
		try {
			
			viewAllContactsFromContactsTab();
			
			sf.seleU.wait(5000);
		
			//verify contact created
			boolean isContactFound = false;
			// click on newly created contact
			for(int i=0; i<=sf.ar.allContacts.size(); i++)
			{
				sf.seleU.wait(3000);
				sf.seleU.ScrolltoElement(sf.ar.allContacts.get(i));
				if (sf.seleU.getTextFromWebElement(sf.ar.allContacts.get(i))
				.equals((Global.dataInput.contactFirstName).concat(" ").concat(Global.dataInput.contactLastName))) {				
					sf.seleU.clickElementByJSE(sf.ar.allContacts.get(i));
					sf.seleU.wait(5000);
					reportStatusPass(methodName + " Found and Clicked on  Contact ", true, true);
					isContactFound = true;
					break;
				}
			}
									
			if (!isContactFound) {
				reportStatusFail(methodName + " No such contact found as  " + Global.dataInput.contactFirstName, true);
			}		
			// Verify Contact details
			if (sf.seleU.getTextFromWebElement(sf.cc.contactName).contains(Global.dataInput.contactFirstName)
					&& sf.seleU.getTextFromWebElement(sf.cc.contactName)
							.contains(Global.dataInput.contactLastName)) {
				reportStatusPass(methodName + "Verifying contact creation is successful",
						true, true);
				sf.seleU.wait(5000);
			}
			 else {
			 reportStatusFail(methodName + " Verifying contact creation is Unsuccessful ",
			 true);
			 }
			 //verify contact role as Admin			
			 sf.seleU.hardwait(5000);
			 sf.seleU.clickElementByJSE(sf.cd.relatedTab.get(0));						
			 reportStatusPass(methodName + " Clicked on related tab ", true, false);
			 sf.seleU.hardwait(4000);
			 verifyFieldValue("Roles ", sf.cd.rolesAdmin, "Administrator");		  
		}
			catch (Throwable e) {
				reportStatusFail(methodName + " Verifying contact creation is Unsuccessful", e);
				e.printStackTrace();
			}			
		}
		
	/**
	 * @throws IOException
	 * 
	 *                     1.Click on Related tab under account view
	 * 
	 *                     2.Verify Industry subIndustry approval Request
	 */
	public void verifyApprovalRequestForIndSubIndChange() throws IOException {
		try {

			// Click on Related tab under account view

			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
			sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			else{
			sf.seleU.clickElementByJSE(sf.ar.moreTabs);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			}

			// Click on View All button under Approval Section
			sf.seleU.wait(3000);
			//sf.seleU.switchToFrame(sf.ar.viewAllApprovalHistory);
			//sf.seleU.clickElementByJSE(sf.ar.viewAllApprovalHistory);
			//sf.seleU.switchToElementFrame(sf.ar.viewAllApprovalHistory);
			//sf.seleU.ScrolltoElement(sf.ar.viewAllApprovalHistory);
			//sf.seleU.wait(4000);
			//if (sf.seleU.isElementPresent(sf.ar.viewAllApprovalHistory)) 
			//sf.seleU.clickElementByJSE(sf.ar.viewAllApprovalHistory);
			sf.seleU.scrollByCoOrdinates(5);
			sf.seleU.switchToElementFrame(sf.ar.viewAllApprovalHistory);
			sf.seleU.wait(3000);
			sf.seleU.ScrolltoElement(sf.ar.viewAllApprovalHistory.get(0));
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.ar.viewAllApprovalHistory.get(0));
			sf.seleU.wait(3000);

			// Validate landing page and Click on first/recent approval request
			sf.seleU.wait(8000);
			verifyFieldDisplayed("Approval History Page", sf.ar.approvalHistoryDetailsPageHeader);
			sf.seleU.clickElementByJSE(sf.ar.approvalDetailsPageAllLinks.get(0));
			sf.seleU.wait(8000);
			reportStatusPass(methodName + " Opened first/recent approval request from table", true, true);

			// Validate Approval request is related to change in Industry/SubIndustry
			verifyFieldDisplayed("Industry Change", sf.ar.industryForApproval);
			verifyFieldDisplayed("Industry Change", sf.ar.subIndustryForApproval);

			// Approve Request
			sf.seleU.clickElementByJSE(sf.ar.singleRequestApproveButton);
			sf.seleU.wait(3000);
			reportStatusPass(methodName + " Approved request from request history", true, true);

			// Enter Approval Mesaage
			sf.seleU.enterText(sf.ar.commentTextArea, Global.dataInput.accountApprovalMsg);
			reportStatusPass(
					methodName + " Entered Data Governanace Approval Mesaage as " + Global.dataInput.accountApprovalMsg,
					true, false);

			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.approveAccountButton);
			reportStatusPass(methodName + " Clicked on Approve Button", true, false);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Validating approve request for Industry-SubIndustry change", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param DirectMsg
	 * @param acc
	 * @param array
	 * @throws IOException
	 * 
	 *                     Verifying and matching field values in the
	 *                     list/collection by passing WebElement
	 */
	public void verifyFieldValueWithCollections(String DirectMsg, WebElement acc, String array[]) throws IOException {
		try {
			sf.seleU.wait(2000);
			String expectedArrValue[] = sf.seleU.getTextFromWebElement(acc).trim().split(";");

			List<String> expectedValueList = new ArrayList<>(Arrays.asList(expectedArrValue));
			List<String> actualValueList = new ArrayList<String>(Arrays.asList(array));

			// sort lists for comparison
			Collections.sort(expectedValueList);
			Collections.sort(actualValueList);

			if (expectedValueList.equals(actualValueList)) {
				reportStatusPass(DirectMsg + " " + "and ts validated and matched with the expected values "
						+ AdditionalUtilities.getAsString(expectedValueList), true, true);

			} else {
				reportStatusFail(" All expected field value are not present :: " + "Expected field value is "
						+ "Applicaton displayed data is " + AdditionalUtilities.getAsString(expectedValueList)
						+ "  Actual Value--> " + AdditionalUtilities.getAsString(actualValueList), true);
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

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).equals(expectedText)) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + element.getText() + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is Displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementDisplayed(element)) {
				reportStatusPass("Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail("Field " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	
	public void validateInternalGuidedCaseForAccounts() throws IOException{
		try {
			
			//click on related tab
			sf.seleU.hardwait(4000);
//			sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			sf.seleU.clickElementByJSE(sf.ar.cases);
			sf.seleU.hardwait(5000);
			
			//click on new created fraud case
			sf.seleU.clickElementByJSE(sf.caseRelatedDetails.fraudCaseNumberLink);
			sf.seleU.hardwait(5000);
			InputData.fraudCaseNumber=sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);	
			sf.seleU.hardwait(4000);			
			verifyFieldValue("Case Owner", sf.caseRelatedDetails.fraudRiskCaseOwner, InputData.fraudRiskSelection);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Case  Status", sf.caseRelatedDetails.caseStatus, InputData.caseStatusNew);
			sf.seleU.hardwait(3000);
			verifyFieldValue("Priority", sf.caseDetails.priorityFieldValueText, InputData.casePriorityMedium);
			sf.seleU.hardwait(3000);
			}
				
		catch(Throwable e) {
			reportStatusFail(" Error validating internal guided case ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Date: 09/Dec/2021
	 * @throws IOException
	 *                     1. Click on Opportunities tab on account
	 *                     2. Click New Opportunity
	 */
	public void createOpportunityThrDGProfile() throws IOException {
		try {
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.ad.OppTabDG);
			sf.seleU.hardwait(2000);
			// click on New Opportunity Button
			sf.seleU.clickElementByJSE(sf.ar.newOpportunityButton);
			reportStatusPass(methodName + " Clicked on New Opportunity Button", true, false);
			sf.seleU.hardwait(3000);
		} catch (Throwable e) {
			reportStatusFail(" Selecting New Opportunity is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Date: 31/Dec/2021
	 * 
	 * @throws IOException
	 * 
	 *                     1. Click on Related/Overview tab on account
	 * 
	 *                     2. Click Cases and Open Any Case
	 *                     
	 *                     3. Click Add New Service (New Opportunity)
	 */
	public void createOpportunityThrCase() throws IOException {
		try {

			boolean caseSectionFound = false;

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}
			
				sf.seleU.clickElementByJSE(sf.ad.overviewTab);
				sf.seleU.hardwait(4000);
				for (int i = 0; i < 150; i++) {
					sf.seleU.enterText(sf.ad.overviewTab, Keys.PAGE_DOWN);
					if (sf.seleU.isElementPresent(sf.ar.cases)) {
						caseSectionFound = true;
						break;
					}
					sf.seleU.hardwait(2000);
				}
			
			// click on Cases and Open any case and Hit Add New Service Button

			if (caseSectionFound) {
				sf.seleU.ScrolltoElement(sf.ar.cases);
				reportStatusPass(methodName + " Case Found ", true, false);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.cases);
				sf.seleU.hardwait(2000);
				sf.seleU.refreshPage();
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.caseAllRecords.get(0));
				reportStatusPass(methodName + " Clicked on First Case", true, false);
				sf.seleU.hardwait(2000);
				if(sf.ar.addNewServiceOnCase.isDisplayed())
				{
					sf.seleU.clickElementByJSE(sf.ar.addNewServiceOnCase);
					sf.seleU.hardwait(4000);
				}
				else
				{
				sf.seleU.clickElementByJSE(sf.ar.onCaseDropDownButton);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.addNewServiceOnCase);
				sf.seleU.hardwait(2000);
				}
				reportStatusPass(methodName + " Clicked on Add New Service Button", true, false);
				sf.seleU.hardwait(5000);
			} else {
				reportStatusFail(methodName + "Selecting Add New Service Button is Unsuccessful", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Selecting New Transactional Opportunity is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws IOException
	 * 
	 *   TAG- Approved Account Ownership Transfer
	 */
	public void approveAsManager() throws IOException {
		try {

			SFDC_AllPages sfdc = new SFDC_AllPages();
			

			if (sf.seleU.isElementDisplayed(sf.ar.relatedTab))
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);
			else{
				sf.seleU.clickElementByJSE(sf.ar.moreTabs);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.ar.relatedTab);

			}
			sf.seleU.hardwait(3000);
		
			reportStatusPass(methodName + " Clicked on related tab in Accounts Page", true, false);
			sf.seleU.wait(6000);

			// Verify TAG approval Present in Account>related>Approval History

			int i = 0;
			while (true) {
				sf.seleU.scrollByCoOrdinates(1);
				sf.seleU.wait(3000);

				if (sf.seleU.isElementDisplayed(sf.ar.dataGovernanceApproveButton) || i == 5) {
					break;
				}
				i++;
			}

			sf.seleU.ScrolltoElement(sf.ar.approvalHistorySubTabLink);
			if (sf.ar.approvalAllLinks.size() > 0 && sf.seleU.isElementDisplayed(sf.ar.dataGovernanceApproveButton)) {
				reportStatusPass(
						methodName + "Validated TAG approval present in Manager Account>related>Approval History",
						true, true);
			} else {
				reportStatusFail(" No TAG approval present in Manager account>realted>Approval History", true);
			}
			sf.seleU.wait(6000);
			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.dataGovernanceApproveButton);
			reportStatusPass(methodName + " Clicked on Data Governance Approve Button", true, false);
			sf.seleU.hardwait(3000);

			// Enter Approval Mesaage
			sf.seleU.enterText(sf.ar.commentTextArea, Global.dataInput.accountApprovalMsg);
			reportStatusPass(
					methodName + " Entered TAG Approval Mesaage as " + Global.dataInput.accountApprovalMsg,
					true, false);

			// Click on Approve Button
			sf.seleU.clickElementByJSE(sf.ar.approveAccountButton);
			reportStatusPass(methodName + " Clicked on Approve Button", true, false);
			sf.seleU.wait(2000);

			// Verify Account is Approved
			if (sf.seleU.isElementDisplayed(sf.ar.accountWasApprovedMsg)) {
				reportStatusPass(methodName + " Verified Account Approval mesaage ", true, true);
			} 

			//Global.dataInput.businessAccountStatus = Global.dataInput.status_Active;
			sf.seleU.refreshPage();
			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Error in Approving Account TAG as Manager", e);
			e.printStackTrace();
		}
	}
}
