package com.sfdc.lib_pages;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Anukriti.Chawla 15/Apr/2021
 * 
 *         Home Page for SFDC Application
 *
 */
public class SFDC_UserProfile_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	String methodName;

	public SFDC_UserProfile_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_UserSettings@:";
	}

	/**
	 * Change User language to French
	 * 
	 * @throws IOException
	 */
	public void changeLanguageToFrench() throws IOException {

		try {

			// Open User Settings
			sf.seleU.clickElementByJSE(sf.home.userImg);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.userSettingsLink);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Opened User Settings", true, true);

			// Change Locale and Langugae Settings

			sf.seleU.clickElementByJSE(sf.userProf.languageAndTimeSettingLink);
			sf.seleU.hardwait(2000);
			sf.seleU.switchToElementFrame(sf.userProf.languageSelectBox);
			sf.seleU.selectTextFromDropDown(sf.userProf.localeSelectBox, InputData.userLocaleFrench);
			sf.seleU.selectTextFromDropDown(sf.userProf.languageSelectBox.get(0), InputData.userLanguageFrench);
			sf.seleU.clickElementByJSE(sf.userProf.saveButton);
			reportStatusPass(methodName + " Changed User Language and locale to " + InputData.userLocaleFrench, true,
					true);
			InputData.userLanguage = InputData.userLanguageFrench;

			sf.seleU.switchToDefaultContent();
			

		} catch (Throwable e) {
			reportStatusFail("Error in changing user language to French", e);
			e.printStackTrace();
		}

	}

	/**
	 * Change User language to English
	 * 
	 * @throws IOException
	 */
	public void changeLanguageToEnglish() throws IOException {

		try {

			// Open User Settings
			sf.seleU.clickElementByJSE(sf.home.userImg);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.home.userSettingsLink);
			sf.seleU.hardwait(5000);
			reportStatusPass(methodName + " Opened User Settings", true, true);

			// Change Locale and Langugae Settings
			sf.seleU.clickElementByJSE(sf.userProf.languageAndTimeSettingLink);
			sf.seleU.hardwait(2000);
			sf.seleU.switchToElementFrame(sf.userProf.languageSelectBox);

			sf.seleU.selectTextFromDropDown(sf.userProf.localeSelectBox, InputData.userLocaleEnglish);
			sf.seleU.selectTextFromDropDown(sf.userProf.languageSelectBox.get(0), InputData.userLanguageEnglish);
			sf.seleU.clickElementByJSE(sf.userProf.saveButton);
			reportStatusPass(methodName + " Changed User Language and locale to " + InputData.userLanguageEnglish, true,
					true);
			InputData.userLanguage = InputData.userLanguageEnglish;
			sf.seleU.switchToDefaultContent();

		} catch (Throwable e) {
			reportStatusFail("Error in changing user language to English", e);
			e.printStackTrace();
		}
	}
	
	public void changeToFrenchForCustomer() throws IOException {
		try {
			sf.seleU.clickElementByJSE(sf.userProf.languageToggle);
			sf.seleU.hardwait(30000);
		} catch (Throwable e) {
			reportStatusFail("Error in changing user language to English", e);
			e.printStackTrace();
		}
	}

}
