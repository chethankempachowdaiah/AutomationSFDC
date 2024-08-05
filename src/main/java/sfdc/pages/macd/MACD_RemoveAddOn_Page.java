package sfdc.pages.macd;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_RemoveAddOn_Page extends MyListener {
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_RemoveAddOn_Page() {
		sf = new SFDC_AllPageObjects();
	}
	/**
	 * Method developed on @Date: 24.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Add-on on Remove Wireless add-ons. 
	 * @throws Exception 
	 * 
	 */
	public void validateAddOnPresent(String addon, String price) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(3000);
			for(int i=0; i<sf.macdRemoveAddOnObj.removeAddonList.size();i++) {
				if(sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.removeAddonList.get(i)).equalsIgnoreCase(addon) && 
						sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.removeAddonCheckBox.get(i)) &&
						sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.removeAddonPrice.get(i)).replaceAll("[^\\d.]", "").trim().equals(price)) {
					reportStatusPass(methodName+ addon+" AddOn , checkBox and price is present. ",true, true);
					break;
				}
			}
		}catch(Exception e) {
			reportStatusFail("Error in validating AddOn on Remove Wireless add-ons Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 24.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Select Add-on and Click Remove add-on and Continue button on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void selectAddOnAndClickRemove(String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			for(int i=0; i<sf.macdRemoveAddOnObj.removeAddonList.size();i++) {
				if(sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.removeAddonList.get(i)).equalsIgnoreCase(addon)) { 
					sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.removeAddonCheckBox.get(i));	
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.removeAndContinueBtn);
					reportStatusPass(methodName+ addon+" AddOn has selected. ",true, true);					
					break;
				}
			}			
		}catch(Exception e) {
			reportStatusFail("Error in select addon and click Remove add-on and Continue button on Remove Wireless add-ons Page. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 24.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Apple Care AddOn on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void validateAppleCarePresent(String header, String subHeader) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			System.out.println(sf.macdRemoveAddOnObj.appleCareBlock.getCssValue("background").contains("rgb(241, 253, 255)"));
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.appleCareHeader) && sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.appleCareHeader).equals(header)
					&& sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.appleCareSubHeader) && sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.appleCareSubHeader).equals(subHeader)
					&& sf.macdRemoveAddOnObj.appleCareBlock.getCssValue("background").contains("rgb(241, 253, 255)")) {
				reportStatusPass(methodName+ "AppleCare is in Blue color & Header and SubHeader has matched correctly.",true, true);
			}
		}catch(Exception e) {
			reportStatusFail("Error in validating AppleCare on Remove Wireless add-ons Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 24.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Remove add-on and Continue button on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void validate_RemoveAddOnAndContineBtnDisabled() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(!sf.seleU.isElementEnabled(sf.macdRemoveAddOnObj.removeAndContinueBtn))	
				reportStatusPass(methodName+ "Remove add-on and Continue button is disabled.",true, true);
			else
				reportStatusFail("Remove add-on and Continue button is enabled on Remove Wireless add-ons Page. ", true);
		}catch(Exception e) {
			reportStatusFail("Error in validating Remove add-on and Continue button on Remove Wireless add-ons Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 24.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Device Protection section not present on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void validate_DPsectionNotPresent() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(3000);
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.dpSection))	
				reportStatusFail(methodName+ "Device Protection section is present.",true);
		}catch(Exception e) {				
			reportStatusPass(methodName+ "Device Protection section is not present.", true, true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 24.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Device Protection section present on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void validate_DPsectionPresent() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(3000);
			if (sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.dpSection))
				reportStatusPass(methodName + "Device Protection section is present.", true, true);
		} catch (Exception e) {
			reportStatusFail(methodName + "Device Protection section is present.", true);
			e.printStackTrace();
		}
	}	
	/**
	 * Method developed on @Date: 31.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Effective Date page is displayed.
	 * @throws Exception 
	 * 
	 */
	public void validate_EffectiveDatePagePresent() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.selectEffectiveDate))
				reportStatusPass(methodName + "Effective Date page is displayed.", true, true);
		} catch (Exception e) {
			reportStatusFail(methodName + "Error in getting Effective Date page is displayed.", true);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 06.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to Validate Confirm Effective Date Button
	 * @throws Exception 
	 * 
	 */
	public void select_ConfirmEffectiveDateButton() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Confirm Effective Date Button@: ";
			sf.seleU.hardwait(4000);
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.confirmEffectiveDateButton))
				sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.confirmEffectiveDateButton);
			reportStatusPass(methodName + "Effective Date page is displayed.", true, true);
		} catch (Exception e) {
			reportStatusFail(methodName + "Error in selecting Confirm Effective Date Button.", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 31.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate remove VoiceMail Warning Message.
	 * @throws Exception 
	 * 
	 */
	public void validate_VoiceMailWarningMsg(String text, String subText) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "VoiceMail warning message Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.warningMsgSubHeader).equals("Please see the details below about the changes to your service.")
					&& sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.warningMessage).equals("Wireless Voicemail reset") 
					&& sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.warningMessageText).equals(text)
					&& sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.warningMessageSubText).equals(subText)) {
				sf.seleU.hardwait(2000);
				reportStatusPass(methodName + "VoiceMail Warning message has validated successfully.", true, true);
			}else
				reportStatusFail(methodName + "VoiceMail Warning message has not validated successfully.", true);	
		} catch (Exception e) {
			reportStatusFail(methodName + "Error in validating Voicemail Warning Message.", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 31.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Confirm & proceed button on VoiceMail Warning Message.
	 * @throws Exception 
	 * 
	 */
	public void select_ConfirmAndProceedBtn() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove VoiceMail warning message Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.confirmAndProceedBtn))
				sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.confirmAndProceedBtn);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName + "Confirm & proceed button has clicked successfully.", true, true);				
		} catch (Exception e) {
			reportStatusFail(methodName + "Error in clicking on Confirm & proceed button.", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 31.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Cancel button on VoiceMail Warning Message.
	 * @throws Exception 
	 * 
	 */
	public void select_CancelBtn() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove VoiceMail warning message Page@: ";
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.cancelBtn))
				sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.cancelBtn);
			sf.seleU.hardwait(3000);
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.removeHeader))
				reportStatusPass(methodName + "Confirm & proceed button has clicked successfully and Remove Wireless Add-On page is displayed", true, true);	
		} catch (Exception e) {
			reportStatusFail(methodName + "Error in clicking on Cancel button.", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 01.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Select multiple Add-on and Click Remove add-on and Continue button on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void selectMultipleAddOnAndClickRemove(String[] addonList) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			for (String str : addonList) {
				for (int i = 0; i < sf.macdRemoveAddOnObj.removeAddonList.size(); i++) {
					if (sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.removeAddonList.get(i)).equalsIgnoreCase(str)) {
						sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.removeAddonCheckBox.get(i));
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName + str + " AddOn has selected. ", true, true);
						break;
					}
				}
			}
			sf.seleU.hardwait(4000);
			sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.removeAndContinueBtn);
			reportStatusPass(methodName + " Remove add-on and Continue button has clicked. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in select addon and click Remove add-on and Continue button on Remove Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 01.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to review removable addon on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void reviewSelectedAddontoRemove(String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.getTextFromWebElement(sf.macdRemoveAddOnObj.reviewRemovableAddon).contains(addon)) {
				reportStatusPass(methodName + " removable addon has present. ", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in review removable addon has present on Remove Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 01.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to review removable DP on Remove Wireless add-ons Step 1 of 2.
	 * @throws Exception 
	 * 
	 */
	public void reviewSelectedDPtoRemove() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.reviewRemovableDP)) {
				reportStatusPass(methodName + " removable DP has present. ", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in review removable DP has present on Remove Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}


	/**
	 * Method developed on @Date: 05.04.2022 by @author viswas reddy
	 * 
	 * Method to click on edit button in wireless remove add-on's page 2.
	 * @throws Exception 
	 * 
	 */
	public void clickOnEdit() throws Exception {
		try {
			methodName = "Remove Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.macdRemoveAddOnObj.step2of2.isDisplayed()) {
				reportStatusPass(methodName+"user is on step 2 of remove add on page", true, true);
				sf.seleU.clickElementByJSE(sf.macdRemoveAddOnObj.edit);
				sf.seleU.hardwait(2000);
				if(!sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.step2of2) && 
						sf.macdRemoveAddOnObj.removeAndContinueBtn.isDisplayed()) {
					reportStatusPass(methodName+"successfully clicked edit and "
							+ "user is on step 1 of remove add on page", true, true);
				}
			}
		}catch(Exception e) {
			reportStatusFail(methodName+"error in clicking edit button", true);
			e.printStackTrace();
		}
	}

}
