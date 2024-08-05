package sfdc.pages.macd;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_AccountManagement_Page extends MyListener {
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_AccountManagement_Page() {
		sf = new SFDC_AllPageObjects();
	}
	/**
	 * Method developed on @Date: 10.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to search and select the Billing account. 
	 * @throws Exception 
	 * 
	 */
	public void selectBillingAccount(String billingAccount) throws Exception {
		try {
			// 1-Selecting Accounts from menu
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, billingAccount);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
				sf.seleU.hardwait(4000);
				sf.seleU.refreshPage();
				reportStatusPass(billingAccount+" Search Billing Account successfully from global search. ", true, true);
			}

		}catch(Exception e) {
			reportStatusFail("Error is getting while search the Billing Account from global search. ", e);
			e.printStackTrace();
		}
	}


	/**
	 * Method developed on @Date: 12.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to search and select the Billing account. 
	 * @throws Exception 
	 * 
	 */
	public void selectBillingAccount_ByHomeNavigation(String billingAccount) throws Exception {
		try {
			// 1-Selecting Accounts from menu
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, billingAccount);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
				sf.seleU.hardwait(4000);
				sf.seleU.refreshPage();
				reportStatusPass(billingAccount+" Search Billing Account successfully from global search. ", true, true);
			}

		}catch(Exception e) {
			reportStatusFail("Error is getting while search the Billing Account from global search. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Select Account from global search
	 * 
	 * @throws IOException
	 */
	public void searchBillingAccountAccountGlobalSearch(String billingAccount) throws IOException {

		try {

			String methodName = "SFDC_Account@: ";
			boolean isAccountFound = false;
			// click on account menuv

			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.home.showNavigationMenu);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.home.accountsMenu);
			reportStatusPass(methodName + " Selected Accounts from menu", true, false);
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.acc.search);
			sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.acc.searchAccountGlobalAfterClick)) {
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, billingAccount);
				sf.seleU.hardwait(2000);
				sf.seleU.enterText(sf.acc.searchAccountGlobalAfterClick, Keys.ENTER);
				sf.seleU.hardwait(4000);
				sf.seleU.refreshPage();
				reportStatusPass(billingAccount+" Search Billing Account successfully from global search. ", true, true);
			}

			if (sf.acc.accountLinkGlobalSearchResult.size() == 1) {
				sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(0));
				reportStatusPass(methodName + " Found and Clicked on  Account " + billingAccount, true, false);
				isAccountFound = true;
			} else {
				for (int i = 0; i < sf.acc.accountLinkGlobalSearchResult.size(); i++) {

					if (sf.seleU.getElementAttribute(sf.acc.billingAccountNoGlobalSearchResult.get(i),"title").equalsIgnoreCase(billingAccount)
							&& (sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(sf.dataInput.acc_RecordType_Billing) || 
									sf.seleU.getElementAttribute(sf.acc.accountTypeGlobalSearchResult.get(i),"title").equalsIgnoreCase(InputData.acc_RecordType_Business_Fr))) {
						sf.seleU.clickElementByJSE(sf.acc.accountLinkGlobalSearchResult.get(i));
						reportStatusPass(methodName + " Found and Clicked on  Account " + billingAccount, true, false);
						isAccountFound = true;
						break;
					}
				}
			}
			if (!isAccountFound) {
				reportStatusFail(methodName + " No such account found as:  " + billingAccount, true);
			}

			if (sf.seleU.isElementPresent(sf.ad.closeButton)) {
				sf.seleU.clickElementByJSE(sf.ad.closeButton);
			}

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Selecting an account is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 10.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on MACD button on Billing account page. 
	 * @throws Exception 
	 * 
	 */
	public void select_ManageAccountButton(String caseCreationInput) throws Exception {
		try {
			methodName = "Billing Account Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.accManagementObj.manageAccountButton)) {
				sf.seleU.clickElementByJSE(sf.accManagementObj.manageAccountButton);
				sf.seleU.hardwait(4000);				
				select_CaseCreation(caseCreationInput);
				reportStatusPass(methodName + "Manage Account button has clicked. ", true, true);
			}
			else
				reportStatusFail("Manage Account button is not displayed on the Billing Account Page. ", true);
		}catch(Exception e) {
			reportStatusFail("Error is clicking on MACD button on the Billing Account Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 10.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select option on Account Management page. 
	 * @throws Exception 
	 * 
	 */
	public void select_AccManagementOption(String option) throws Exception {
		try {
			methodName = "Account Management Page@: ";
			sf.seleU.hardwait(2000);
			WebElement ele = driver.findElement(By.xpath("//a[@data-target-id='"+option+"']"));			
			if(sf.seleU.isElementDisplayed(ele)) {
				sf.seleU.clickElementByJSE(ele);
				sf.seleU.hardwait(2000);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.accManagementObj.selectPhoneNumberHeader));
				reportStatusPass(methodName + option + " option has clicked and Search and Select telephone numbers Page is displayed. ", true, true);
			}
			else
				reportStatusFail(option +" option is not displayed on the Account Management Page. ", true);
		}catch(Exception e) {
			reportStatusFail("Error is clicking on option "+option+" on the Account Management Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 10.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select Phone Number and click on Select Number button on Search and Select telephone numbers page. 
	 * @throws Exception 
	 * 
	 */
	public void click_SelectNumberButton(String phoneNum) throws Exception {
		try {
			methodName = "Search and Select telephone numbers Page@: ";
			sf.seleU.hardwait(2000);

			//phoneNum="1010004119";

			if(sf.accManagementObj.selectNameRadioBtn.size()>1) {
				sf.seleU.enterText(sf.accManagementObj.searchTelephone, phoneNum);
				sf.seleU.hardwait(5000);
				System.out.println(sf.accManagementObj.selectNameRadioBtn.get(0).isSelected());
				if(!sf.accManagementObj.selectNameRadioBtn.get(0).isSelected())
					sf.seleU.clickElementByJSE(sf.accManagementObj.selectNameRadioBtn.get(0));
			}
			sf.seleU.clickElementByJSE(sf.accManagementObj.selectNumberBtn);
			//Assert.assertTrue(sf.seleU.isElementDisplayed(sf.reviewWALineObj.wirelessLineHeader));
			reportStatusPass(methodName + phoneNum+ " PhoneNumber has selected and click on Select Number button on Search and Select telephone numbers Page. ",
					true, true);
		} catch (Exception e) {
			reportStatusFail("Error is selecting " + phoneNum + " on the Search and Select telephone numbers Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 10.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select Confirm and Proceed Button on Changes to your Service page. 
	 * @throws Exception 
	 * 
	 */
	public void select_ConfirmAndProceedButton() throws Exception {
		try {
			methodName = "Changes to your Service Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.accManagementObj.confirmAndProceedBtn)) {
				sf.seleU.clickElementByJSE(sf.accManagementObj.confirmAndProceedBtn);
				sf.seleU.hardwait(4000);
				//Assert.assertTrue(sf.seleU.isElementDisplayed(sf.accManagementObj.changesToYourServiceHeader));
				reportStatusPass(methodName + "Confirm and Proceed button has clicked. ", true, true);
			}
			else
				reportStatusFail(" Confirm and Proceed button is not displayed on Changes to your Service Page. ", true);				
		}catch(Exception e) {
			reportStatusFail("Error is clicking on Confirm and Proceed button on Changes to your Service Page. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 08.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to verify All MACD Flows present in Account Management page or not. 
	 * @throws Exception 
	 * 
	 */
	public void verify_MACDFlows() throws Exception {
		try {
			methodName = "Verify MACD Flows@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.pricePlanChangeTile).contains("Price Plan Change")) {
				reportStatusPass("Price Plan Change Tile is Present",true,true);
			}
			else {
				reportStatusFail("Price Plan Tile s not present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.addALineTile).contains("Add a Line")) {
				reportStatusPass("Add a Line Tile is Present",true,true);
			}
			else {
				reportStatusFail("Add a Line Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.addOnTile).contains("Add-On")) {
				reportStatusPass("Add On Tile is Present",true,true);
			}
			else {
				reportStatusFail("Add On Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.addOrRemoveSIMTile).contains("Add or Remove SIM")) {
				reportStatusPass("Add Or Remove SIM Tile is Present",true,true);
			}
			else {
				reportStatusFail("Add Or Remove SIM Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.swapSIMTile).contains("Swap SIM")) {
				reportStatusPass("Swap SIM Tile is Present",true,true);
			}
			else {
				reportStatusFail("Swap SIM Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.hardwareUpgradeTile).contains("Hardware Upgrade")) {
				reportStatusPass("Hardware Upgrade Tile is Present",true,true);
			}
			else {
				reportStatusFail("Hardware Upgrade Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.loremIpsumH1Tile).contains("Lorem Ipsum H1")) {
				reportStatusPass("Lorem Ipsum H1 Tile is Present",true,true);
			}
			else {
				reportStatusFail("Lorem Ipsum H1 Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.loremIpsumH2Tile).contains("Lorem Ipsum H2")) {
				reportStatusPass("Lorem Ipsum H2 Tile is Present",true,true);
			}
			else {
				reportStatusFail("Lorem Ipsum H2 Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.portInTile).contains("Port In")) {
				reportStatusPass("Port IN Tile is Present",true,true);
			}
			else {
				reportStatusFail("Port IN Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.portOutTile).contains("Port Out")) {
				reportStatusPass("Port Out Tile is Present",true,true);
			}
			else {
				reportStatusFail("Port Out Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.transferOfResponsibilityTile).contains("Transfer of Responsibility")) {
				reportStatusPass("Transfer of Responsibility Tile is Present",true,true);
			}
			else {
				reportStatusFail("Transfer of Responsibility Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.telephoneNumberChangeTile).contains("Telephone Number Change")) {
				reportStatusPass("Telephone Number Change Tile is Present",true,true);
			}
			else {
				reportStatusFail("Telephone Number Change Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.loremIpsumTransferTile).contains("Lorem Ipsum transfer")) {
				reportStatusPass("Lorem Ipsum transfer Tile is Present",true,true);
			}
			else {
				reportStatusFail("Lorem Ipsum transfer Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.displayNameTile).contains("Display Name")) {
				reportStatusPass("Display Name Tile is Present",true,true);
			}
			else {
				reportStatusFail("Display Name Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.changeVoicemailPasswordTile).contains("Change Voicemail Password")) {
				reportStatusPass("Change Voicemail Password Tile is Present",true,true);
			}
			else {
				reportStatusFail("Change Voicemail Password Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.languageChangeTile).contains("Language Change")) {
				reportStatusPass("Language Change Tile is Present",true,true);
			}
			else {
				reportStatusFail("Language Change Tile is not Present",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.serviceBlocksTile).contains("Service Blocks")) {
				reportStatusPass("Service Blocks Tile is Present",true,true);
			}
			else {
				reportStatusFail("Service Blocks Tile is not Present",true);
			}
			System.out.println("User is able to see All MACD Flows in Account Management Page");

		}
		catch(Exception e) {
			reportStatusFail("User is not able to see MACD Flows. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 19.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to verify Service Block MACD Flow present in Account Management page or not. 
	 * @throws Exception 
	 * 
	 */
	public void verify_ServiceBlock() throws Exception {
		try {
			methodName = "Verify Service Block@: ";
			sf.seleU.hardwait(2000);

			if(sf.seleU.getTextFromWebElement(sf.accManagementObj.serviceBlocksTile).contains("Service Blocks")) {
				reportStatusPass("User can start Service Block MACD Flow",true,true);
			}
			else {
				reportStatusFail("Service Blocks Tile is not Present",true);
			}
		}catch (Exception e) {
			// TODO: handle exception
			reportStatusFail("User is not able to start Service Block MACD Flow ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to search CTN By putting Last Three digit from global search. 
	 * @throws Exception 
	 * 
	 */
	public void searchCTNUsingLastThreeDigit(String ctn) throws Exception {
		try {
			// 1-Selecting Accounts from menu
			sf.seleU.wait(3000);
			//sf.seleU.clickElementByJSE(sf.acc.search);
			//sf.seleU.wait(2000);
			if (sf.seleU.isElementDisplayed(sf.accManagementObj.searchTelephoneNo)) {
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.accManagementObj.searchTelephoneNo);
				sf.seleU.enterText(sf.accManagementObj.searchTelephoneNo, ctn);
				reportStatusPass(ctn+"User can successfully apply filter by putting last three digit of CTN from global search. ", true, true);
			}

		}catch(Exception e) {
			reportStatusFail("Error is getting while search the aplying filter from global search. ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 16.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to click on Manage Account button on Billing account page. 
	 * @throws Exception 
	 * 
	 */
	public void select_ManageAccButton() throws Exception {
		try {
			methodName = "Billing Account Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.accManagementObj.manageAccBtn)) {
				sf.seleU.clickElementByJSE(sf.accManagementObj.manageAccBtn);
				sf.seleU.hardwait(4000);
				Assert.assertTrue(sf.seleU.isElementDisplayed(sf.accManagementObj.accManagementHeader));
				reportStatusPass(methodName + "Manage Account button has clicked and Account Management Page is displayed. ", true, true);
			}
			else
				reportStatusFail("Manage Account button is not displayed on the Billing Account Page. ", true);
		}catch(Exception e) {
			reportStatusFail("Error is clicking on Manage Account button on the Billing Account Page. ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 19.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to verify Cases pop up title message informing user that cases must be created.
	 * @throws Exception 
	 * 
	 */
	public void verify_CasesPopUpTitle() throws Exception {
		try {
			methodName = "Billing Account Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.accManagementObj.caseTitle)) {
				reportStatusPass(methodName + "User is able to see Cases Pop Up Title message ", true, true);
			}
			else
				reportStatusFail("User is unable to see cases pop up message ", true);
		}catch(Exception e) {
			reportStatusFail("Error is validated while verifying cases pop up. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 23.05.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Select Create case/ Skip Case Creation button on A case must be created for this transaction Page. 
	 * @throws Exception 
	 * 
	 */
	public void select_CaseCreation(String caseCreationInput) throws Exception {
		try {
			methodName = "A case must be created for this transaction Page@: ";
			sf.seleU.waitForLoading();
			
			if(sf.seleU.isElementDisplayed(sf.accManagementObj.createCase) && sf.seleU.isElementDisplayed(sf.accManagementObj.skipCaseCreation)) {
				if(caseCreationInput.equalsIgnoreCase("create"))
					sf.seleU.clickElementByJSE(sf.accManagementObj.createCase);				
				else if(caseCreationInput.equalsIgnoreCase("skip")) {
					sf.seleU.clickElementByJSE(sf.accManagementObj.skipCaseCreation);
					Assert.assertTrue(sf.seleU.isElementDisplayed(sf.accManagementObj.accManagementHeader));
				}
				reportStatusPass(methodName + caseCreationInput+" case button has clicked and Account Management Page is displayed. ", true, true);
			}
			else
				reportStatusFail("Create Case/Skip Case Creation button is not displayed on the A case must be created for this transaction Page. ", true);
		}catch(Exception e) {
			reportStatusFail("Error in clicking on Create Case/Skip Case Creation button on the A case must be created for this transaction Page. ", e);
			e.printStackTrace();
		}
	}
}
