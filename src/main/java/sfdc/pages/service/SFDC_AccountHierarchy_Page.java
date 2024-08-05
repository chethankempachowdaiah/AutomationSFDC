package sfdc.pages.service;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 18/JUNE/2020
 * 
 *         Select Account> Account Overview> Account Hierarchy
 *
 */
public class SFDC_AccountHierarchy_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_AccountHierarchy_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Account Overview Tab
	 * 
	 *                     Click on Account Hierarchy
	 * 
	 *                     Verify Account Hierarchy for Master and Taregt Account
	 */
	public void verifyAccountHierarchyAdded() throws IOException {
		try {

			String methodName = "SFDC_Account Hierarchy@: ";

			// Click on Account Overview Tab
			sf.seleU.switchToDefaultContent();
//			sf.seleU.switchToElementFrame(sf.accHi.overview);
//			sf.seleU.clickElementByJSE(sf.accHi.overview.get(0));
			sf.seleU.clickElementByJSE(sf.accHi.overviewTab);
			reportStatusPass(methodName + " Clicked on Account Overview Tab", true, false);

			// Click on Account Hierarchy
			sf.seleU.clickElementByJSE(sf.accHi.accountHierarchyButton);
			reportStatusPass(methodName + " Clicked on Account Hierarchy", true, false);

			// Verify Account Hierarchy for Master and Taregt Account
			boolean isTargetAccountFound = false;
			for (int i = 1; i < sf.accHi.accountNameAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.accHi.accountNameAllRows.get(i))
						.equals(sf.dataInput.parentAccountName)) {
					isTargetAccountFound = true;
					break;
				}
			}

			if (isTargetAccountFound
					&& sf.seleU.getTextFromWebElement(sf.accHi.accountNameAllRows.get(0))
							.equals(sf.dataInput.businessAccountName)
					&& sf.seleU.getTextFromWebElement(sf.accHi.accountNameCurrent.get(0)).equals("current")) {

				reportStatusPass(methodName + " Verified Account Hierarchy for Master and Taget Account", true, true);

			} else {
				reportStatusFail(methodName + "Invalid Account Hierarchy for Master and Taget Account", true);

			}

		} catch (Throwable e){
			reportStatusFail("  Error in Verifying Account Hierarchy Added", e);			
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Account Overview Tab
	 * 
	 *                     Click on Account Hierarchy
	 * 
	 *                     Verify Account Hierarchy for Master and Taregt Account
	 *                     Removed
	 */
	public void verifyAccountHierarchyRemoved() throws IOException {
		try {

			String methodName = "SFDC_Account Hierarchy@: ";

			// Click on Account Overview Tab
			sf.seleU.clickElementByJSE(sf.accHi.overviewTab);
			reportStatusPass(methodName + " Clicked on Account Overview Tab", true, false);

			// Click on Account Hierarchy
			sf.seleU.clickElementByJSE(sf.accHi.accountHierarchyButton);
			reportStatusPass(methodName + " Clicked on Account Hierarchy", true, false);

			// Verify Account Hierarchy for Master and Taregt Account Removed
			boolean isTargetAccountFound = false;
			for (int i = 1; i < sf.accHi.accountNameAllRows.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.accHi.accountNameAllRows.get(i))
						.equals(sf.dataInput.parentAccountName)) {
					isTargetAccountFound = true;
					break;
				}
			}

			if ((!isTargetAccountFound)
					&& sf.seleU.getTextFromWebElement(sf.accHi.accountNameAllRows.get(0))
							.equals(sf.dataInput.businessAccountName)
					&& sf.seleU.getTextFromWebElement(sf.accHi.accountNameCurrent.get(0)).equals("current")) {

				reportStatusPass(methodName + " Verified Account Hierarchy for Master and Taget Account Removed", true,
						true);

			} else {
				reportStatusFail(methodName + "Account Hierarchy for Master and Taget Account not removed", true);

			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Account Hierarchy Removed", e);
			e.printStackTrace();
		}
	}
	
	/**Anukriti Chawla
	 * @throws IOException
	 * 
	 *                    Click on Account hierarchy and extract all service accounts
	 */
	public void extractAllRelatedServiceAccForBusAcc() throws IOException {
		try {
			String methodName = "SFDC_Account Hierarchy@: ";
			// Click on Account Overview Tab
			sf.seleU.clickElementByJSE(sf.accHi.overviewTab);
			reportStatusPass(methodName + " Clicked on Account Overview Tab", true, false);
			// Click on Account Hierarchy
			sf.seleU.clickElementByJSE(sf.accHi.accountHierarchyButton);
			reportStatusPass(methodName + " Clicked on Account Hierarchy", true, false);
			if (sf.seleU.isElementDisplayed(sf.accHi.expandCurrentAcc))
				sf.seleU.clickElementByJSE(sf.accHi.expandCurrentAcc);
			
			// Extract Service Accounts
			for(int i=0; i<sf.accHi.accountType.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.accHi.accountType.get(i)).equalsIgnoreCase(sf.dataInput.acc_RecordType_Service)) {
					sf.dataInput.serviceAccountList.add(sf.seleU.getTextFromWebElement(sf.accHi.currentAccSubAccs.get(i)));
					reportStatusPass(methodName + " Added service account to list for later use : " + sf.seleU.getTextFromWebElement(sf.accHi.currentAccSubAccs.get(i)), true, true);
				}
			}
			
		} catch (Throwable e) {
			reportStatusFail(" Error in Extracting Service Accounts from Account Hierarchy Page for Business Acc", e);
			e.printStackTrace();
		}
	}
	
	/**Anukriti Chawla
	 * @throws IOException
	 * 
	 *                    Extract number of related service accounts
	 */
	public void extractNoOfRelatedServiceAcc() throws IOException {
		try {
			String methodName = "SFDC_Account Hierarchy@: ";
			if (sf.seleU.isElementDisplayed(sf.accHi.expandCurrentAcc))
				sf.seleU.clickElementByJSE(sf.accHi.expandCurrentAcc);
			
			// Extract Service Accounts
			for(int i=0; i<sf.accHi.accountType.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.accHi.accountType.get(i)).equalsIgnoreCase(sf.dataInput.acc_RecordType_Service)) {
					sf.dataInput.numberOfServiceAccountRelated = sf.dataInput.numberOfServiceAccountRelated +1;
				}
			}
			reportStatusPass(methodName + " No. of service account related to Business Account : " + sf.dataInput.numberOfServiceAccountRelated, true, true);
			
		} catch (Throwable e) {
			reportStatusFail(" Error in Extracting number of Service Accounts from Account Hierarchy Page for Business Acc", e);
			e.printStackTrace();
		}
	}
	
	/**Anukriti Chawla
	 * @throws IOException
	 * 
	 *                    Extract number of related service accounts
	 */
	public void verifyNoOfRelatedServiceAccIncreased() throws IOException {
		try {
			String methodName = "SFDC_Account Hierarchy@: ";
			sf.seleU.refreshPage();
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.accHi.expandCurrentAcc))
				sf.seleU.clickElementByJSE(sf.accHi.expandCurrentAcc);
			sf.dataInput.serviceAccountList.clear();
			// Extract Service Accounts
			for(int i=0; i<sf.accHi.accountType.size(); i++) {
				if (sf.seleU.getTextFromWebElement(sf.accHi.accountType.get(i)).equalsIgnoreCase(sf.dataInput.acc_RecordType_Service)) {
					sf.dataInput.serviceAccountList.add(sf.seleU.getTextFromWebElement(sf.accHi.currentAccSubAccs.get(i)));
					reportStatusPass(methodName + " Added service account to list for later use : " + sf.seleU.getTextFromWebElement(sf.accHi.currentAccSubAccs.get(i)), true, true);
				}
			}
			if (sf.dataInput.serviceAccountList.size()==(sf.dataInput.numberOfServiceAccountRelated + 1))
				reportStatusPass(methodName + " Newly related service account has been added to the related list of Business Account : " + sf.dataInput.numberOfServiceAccountRelated, true, true);
			else
				reportStatusFail(" Service Accounts added is not reflecting in related acc list of Business account in Account Hierarchy Page", true);
			
		} catch (Throwable e) {
			reportStatusFail(" Error in verifying number of Service Accounts from Account Hierarchy Page for Business Acc", e);
			e.printStackTrace();
		}
	}
	
}

