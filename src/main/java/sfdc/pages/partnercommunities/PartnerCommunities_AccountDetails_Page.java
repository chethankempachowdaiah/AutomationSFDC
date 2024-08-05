package sfdc.pages.partnercommunities;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.Constants;
import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla,Date 14/05/2021
 * 
 *         Partner Communities Account Details page
 *
 */
public class PartnerCommunities_AccountDetails_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	public static String methodName;

	public PartnerCommunities_AccountDetails_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "PartnerCommunities_AccountDetails@:";
	}
	
	/**
	 * Select Account from global search
	 * 
	 * @throws IOException
	 */
	public void searchBusAccGlobalSearch(String businessAcc) throws IOException {

		try {

			sf.seleU.hardwait(5000);
			sf.seleU.clickOnElement(sf.home.searchBox);
			sf.seleU.hardwait(5000);
			sf.seleU.enterText(sf.home.searchBox, businessAcc);
			sf.seleU.enterText(sf.home.searchBox, Keys.ENTER);

			sf.seleU.hardwait(2000);
			//sf.seleU.scrollByCoOrdinates(2);

			boolean isAccountFound = false;
			// click on account menu
			for (int i = 0; i < sf.partnerCommAccDetails.accountLinkGlobalSearchResult.size(); i++) {

				if (sf.seleU.getElementAttribute(sf.partnerCommAccDetails.accountLinkGlobalSearchResult.get(i),"title").equalsIgnoreCase(businessAcc)
					 && (sf.seleU.getTextFromWebElement(sf.acc.accountTypeGlobalSearchResult.get(i)).trim()
							.equalsIgnoreCase(Global.dataInput.acc_RecordType_Business)
							|| sf.seleU.getTextFromWebElement(sf.acc.accountTypeGlobalSearchResultOption2.get(i)).trim()
							.equalsIgnoreCase(Global.dataInput.acc_RecordType_Business))) {
					sf.seleU.clickElementByJSE(sf.partnerCommAccDetails.accountLinkGlobalSearchResult.get(i));
					reportStatusPass(methodName + " Found and Clicked on  Account " + businessAcc, true, false);
					isAccountFound = true;
					break;
				}
				
			}

			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + businessAcc, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(9000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                    Switch to Window 1
	 *                    
	 *                    Search for newly created account
	 *                    
	 *                    OPen the account and validate few details
	 *                    
	 */
	public void validateBusinessAccount() throws IOException {

		try {
				sf.seleU.switchWindow(1);
			 	sf.seleU.closeRecentlyOpenedWindow();
//			 	
			 	// Click on Sales Menu
				sf.seleU.clickElementByJSE(sf.partnerCommHome.salesMenuButton);
				reportStatusPass(methodName + " Clicked on Salest Menu", true, true);
				sf.seleU.wait(2000);

				// Click on Accounts Menu
				sf.seleU.clickElementByJSE(sf.partnerCommHome.accountOptionLink);
				reportStatusPass(methodName + " Clicked on Accounts from Sales Menu", true, true);
				sf.seleU.wait(2000);
				
				sf.seleU.clickElementByJSE(sf.home.listViewIcon);
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.partnerCommHome.allAccountsListOption);
				reportStatusPass(methodName + " Clicked on All Account List views", true, true);
				sf.seleU.wait(5000);
				
				sf.seleU.clearAndEnterText(sf.cases.searchCaseInputBox, Global.dataInput.businessAccountName);
				sf.seleU.enterText(sf.cases.searchCaseInputBox, Keys.ENTER);
				sf.seleU.wait(5000);
				sf.seleU.clickOnElement(sf.cases.caseNumberAllRows.get(0));
				
				reportStatusPass(methodName + " Opened Account from list", true, true);
				
				verifyFieldValue("Business Account Name", sf.partnerCommAccDetails.accountNameValueText, Global.dataInput.businessAccountName);

				verifyFieldValue("Business Account Status", sf.partnerCommAccDetails.statusValueText, "Active");
				verifyFieldValue("Business Account Record Type", sf.partnerCommAccDetails.accountRecordTypeValueText.get(0),
						Global.dataInput.acc_RecordType_Business);

				sf.seleU.hardwait(5000);
				
		} catch (Throwable e) {
			reportStatusFail(methodName + " Validating business account details is unsuccessful", e);
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
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
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
}
