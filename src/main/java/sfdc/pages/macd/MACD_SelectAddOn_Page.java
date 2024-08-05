package sfdc.pages.macd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_SelectAddOn_Page extends MyListener {
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_SelectAddOn_Page() {
		sf = new SFDC_AllPageObjects();
	}
	
	/**
	 * Method developed on @Date: 28.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate existing addOn/dp. 
	 * @throws Exception 
	 * 
	 */
	public void validate_ExistingAddOn(String addOn, String price) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			WebElement addOnName= driver.findElement(By.xpath("//div[normalize-space()='Your current Wireless add-ons:']/following-sibling::div//div[contains(normalize-space(),'"+addOn+"') and contains(text(),'$')]"));
			WebElement addOnPrice= driver.findElement(By.xpath("//div[normalize-space()='Your current Wireless add-ons:']/following-sibling::div//div[contains(normalize-space(),'"+addOn+"') and contains(text(),'$')]/span[text()='"+price+"']"));
			if(sf.seleU.isElementDisplayed(addOnName) && sf.seleU.isElementDisplayed(addOnPrice)) {
				reportStatusPass(methodName+ addOn+":"+price+" AddOn and price is present. ",true, true);
			}
		}catch(Exception e) {
			reportStatusFail("Error is getting while validate existing AddOn. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 28.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate available addon list on Add Wireless add-ons Page. 
	 * @throws Exception 
	 * 
	 */
	public void validate_AddOnPresent(String addOnOption, String[] addonList) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			// Iterate AddOn
			WebElement option = driver.findElement(By.xpath("//div[@class='summary-section']//div[contains(text(),'" + addOnOption + "')]"));

			if (sf.seleU.isElementDisplayed(option)) {
				reportStatusPass(methodName + addOnOption + " is present. ", true, true);
				WebElement optionExpand = driver.findElement(By.xpath("//div[@class='slds-grid']//div[contains(text(),'"
						+ addOnOption + "')]/following-sibling::div/*[@title='Edit']"));
				sf.seleU.clickElementByJSE(optionExpand);
				sf.seleU.hardwait(3000);
				/*
				 * List<WebElement> ListOfAddon = sf.macdSelAddonObj.addonList; if
				 * (addOnOption.equalsIgnoreCase("Voicemail")) { ListOfAddon =
				 * sf.macdSelAddonObj.voiceMailAddonList; }
				 */
				if(sf.macdSelAddonObj.addonList.size()==0)
					reportStatusPass(methodName + " No AddOn/Device present to select. ", true, true);
				
				for (int i = 0; i < sf.macdSelAddonObj.addonList.size(); i++) {
					for (String str : addonList) {
						if (sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addonList.get(i)).equalsIgnoreCase(str))
							reportStatusPass(methodName + str + " AddOn/DP is present. ", true, true);
					}
				}
			} else
				reportStatusFail(methodName + addOnOption + "is not present. ", true);

		} catch (Exception e) {
			reportStatusFail("Error is getting while validate AddOn. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 28.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate existing selected addon not available in addon list on Add Wireless add-ons Page. 
	 * @throws Exception 
	 * 
	 */
	public void validate_ExistingAddOnNotDisplayed(String addOnOption, String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
		methodName = "Add Wireless add-ons Page@: ";
		sf.seleU.hardwait(2000);
		
		WebElement option = driver.findElement(By.xpath("//div[@class='summary-section']//div[contains(text(),'" + addOnOption + "')]"));

		if (sf.seleU.isElementDisplayed(option)) {
			reportStatusPass(methodName + addOnOption + "is present. ", true, true);
			WebElement optionExpand = driver.findElement(By.xpath("//div[@class='slds-grid']//div[contains(text(),'"
					+ addOnOption + "')]/following-sibling::div/*[@title='Edit']"));
			sf.seleU.clickElementByJSE(optionExpand);
			sf.seleU.hardwait(3000);
			if(newAddonNotPresent(addon))
				reportStatusPass(methodName + addon + ": Existing AddOn/DP is not present in the addon list. ", true, true);			
		} else
			reportStatusFail(methodName + addOnOption + " is not present. ", true);

	} catch (Exception e) {
		reportStatusFail("Error is getting while validate AddOn. ", e);
		e.printStackTrace();
	}
	}
	public boolean newAddonNotPresent(String addon) {
		try {
			return sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//span[contains(text(),'"+addon+"')]")));
		}catch(Exception e){
			return true;
		}
	}
	/**
	 * Method developed on @Date: 28.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select addon on Add Wireless add-ons Page. 
	 * @throws Exception  
	 * 
	 */
	public void selectAddOn(String addOnOption, String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);			
			WebElement option = driver.findElement(By.xpath("//div[@class='summary-section']//div[contains(text(),'" + addOnOption + "')]"));
			
			if (sf.seleU.isElementDisplayed(option)) {
				sf.seleU.ScrolltoElementPageCenter(option);
				reportStatusPass(methodName + addOnOption + " is present. ", true, true);
				
				WebElement optionExpand = driver.findElement(By.xpath("//div[@class='slds-grid']//div[contains(text(),'"+ addOnOption + "')]/following-sibling::div/*[@title='Edit']"));
				sf.seleU.clickElementByJSE(optionExpand);
				sf.seleU.hardwait(3000);
				WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'"+addon+"')]/../preceding-sibling::span | //span[contains(text(),'"+addon+"')]/preceding-sibling::span"));				
				WebElement addonBox= driver.findElement(By.xpath("//div[@class='summary-section']//div[@class='custom-box']/div |//span[contains(text(),'"+addon+"')]/ancestor::div[contains(@class,'voice-box slds-p-around_large')]"));
				
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", ele);				
				System.out.println(sf.macdSelAddonObj.addOnBox.getAttribute("style"));
				if(sf.macdSelAddonObj.addOnBox.getAttribute("style").contains("border: 3px solid rgb(218, 41, 28)")) {
					reportStatusPass(methodName + addon + "selected and box around the selected addon turn Red. ", true, true);
				}
			} else
				reportStatusFail(methodName + addOnOption + " is not present. ", true);

		} catch (Exception e) {
			reportStatusFail("Error is getting while Select AddOn. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 28.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate existing addon not present. 
	 * @throws Exception 
	 * 
	 */
	public void validate_ExistingAddOnNotPresent(String addon) throws Exception {
		// TODO Auto-generated method stub
		methodName = "Add Wireless add-ons Page@: ";
		if (existingAddonNotPresent(addon))
			reportStatusPass(methodName + addon + " AddOn is not present under current wireless add-ons section. ",true, true);		
	}
	public boolean existingAddonNotPresent(String addon) {
		try {
			return sf.seleU.isElementDisplayed(driver.findElement(By.xpath("//div[normalize-space()='Your current Wireless add-ons:']/following-sibling::div//div[contains(normalize-space(),'"+addon+"') and contains(text(),'$')]")));
		}catch(Exception e){
			return true;
		}
	}
	
	/**
	 * Method developed on @Date: 06/05/2022 by @author Pankaj Agarwal
	 * 
	 * Method to check Conflicting add-ons message while adding the add ons
	 * @throws Exception 
	 * 
	 */
	public void validate_DeviceProtectionIsNotAvailableMsg() throws Exception {
		try {
			methodName = "Device Protection Not Avaiable@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.deviceProtectionNotMsg)) {
				reportStatusPass(methodName + "Device protection is not available as apple care+ is already selected", true, true);
			}	else {
				reportStatusFail(methodName+"v alidating the device protection message is not present ", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error validating the device protection ", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 06/05/2022 by @author Pankaj Agarwal
	 * 
	 * Method to check Conflicting add-ons message while adding the add ons
	 * @throws Exception 
	 * 
	 */
	public void check_ConflictPopUpMessaageAndProcced(String action) throws Exception {
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.conflictAddOnHeaderMessage) && 
					sf.seleU.isElementDisplayed(sf.macdSelAddonObj.conflictAddOnMessage)
					&& sf.seleU.isElementDisplayed(sf.macdSelAddonObj.replaceExistingAddOnButton)) {
				reportStatusPass(methodName + "Plan has the conflict while adding the add on", true, true);
				
				if(action=="Replace") {
					sf.seleU.clickElementByJSE(sf.macdSelAddonObj.replaceExistingAddOnButton);
					reportStatusPass(methodName+"clicked on replace existing addon", true, true);
					sf.seleU.hardwait(2000);
					sf.seleU.waitForLoading();
				}
				if(action=="Cancel") {
					sf.seleU.clickElementByJSE(sf.macdSelAddonObj.replaceExistingAddOnCancelButton);
					sf.seleU.hardwait(2000);
					sf.seleU.waitForLoading();
					reportStatusPass(methodName+"clicked on cancel wireless plan change button", true, true);
				}
			}
			
		}catch (Exception e) {
			reportStatusFail(methodName+"error in checking conflict AddONs Message", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date:11-04-2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate added extra addon under Add extra features to your plan.
	 * @throws Exception 
	 * 
	 */
	public void validate_No_VoiceMail_AddOn_RadioButton() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "NoVoiceMailAddOnText@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.noVoiceMailAddOnText) && 
					sf.seleU.isElementDisplayed(sf.macdSelAddonObj.noVoiceMailAddOnSubText)
					&& sf.seleU.isElementDisplayed(sf.macdSelAddonObj.updateAddOnButton)) {
				reportStatusPass(methodName + "No voicemail addon text is displayed", true, true);
				
				if(!sf.seleU.isElementSelected(sf.macdSelAddonObj.noVoiceMailAddOnRadioButton)) {
					reportStatusPass(methodName + "No voicemail addon radiobutton is selected as default", true, true);
				} else {
					reportStatusPass(methodName + "voicemail addon radiobutton is selected", true, true);
				}				
			}
			else
				reportStatusFail(methodName + "No Voicemail addon text is not displayed", true);
		} catch (Exception e) {
			reportStatusFail("Error in validating No voicemail addon text",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date:11-05-2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate No voice mail addon is not displayed just after selecting a addon
	 * @throws Exception 
	 * 
	 */
	public void validate_No_DP_AddOn_SelectedBy_Default() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Validate_No_DP_AddOn_SelectedBy_Default@: ";
			sf.seleU.hardwait(2000);
			if(!sf.seleU.isElementDisplayed(sf.macdSelAddonObj.noVoiceMailAddOnText) && 
					!sf.seleU.isElementDisplayed(sf.macdSelAddonObj.updateAddOnButton)) {
				reportStatusPass(methodName + "No voicemail addon text is not displayed as expected", true, true);							
			}			
			else
				reportStatusFail(methodName + "No Voicemail addon text is displayed", true);
			
		} catch (Exception e) {
			reportStatusFail("Error in validating No voicemail addon text",e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method developed on @Date:12-04-2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate No voice mail addon is not displayed just after selecting a addon
	 * @throws Exception 
	 * 
	 */
	public void validate_No_VoiceMail_AddOn_NotDisplayed() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "NoVoiceMailAddOnNotDisplayed@: ";
			sf.seleU.hardwait(2000);
			if(!sf.seleU.isElementDisplayed(sf.macdSelAddonObj.noVoiceMailAddOnText) && 
					!sf.seleU.isElementDisplayed(sf.macdSelAddonObj.updateAddOnButton)) {
				reportStatusPass(methodName + "No voicemail addon text is not displayed as expected", true, true);							
			}			
			else
				reportStatusFail(methodName + "No Voicemail addon text is displayed", true);
			
		} catch (Exception e) {
			reportStatusFail("Error in validating No voicemail addon text",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date:11-04-2022 by @author Pankaj Agarwal
	 * 
	 * Method to select No_VoiceMail_AddOn_RadioButton with update button
	 * @throws Exception 
	 * 
	 */
	public void select_No_VoiceMail_AddOn_RadioButton_AndUpdate() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "SelectNoVoiceMailAddOnText@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.noVoiceMailAddOnText) && 
					sf.seleU.isElementDisplayed(sf.macdSelAddonObj.noVoiceMailAddOnSubText)
					&& sf.seleU.isElementDisplayed(sf.macdSelAddonObj.updateAddOnButton)) {
						
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.noVoiceMailAddOnRadioButton);
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.updateAddOnButton);
					reportStatusPass(methodName + "no voicemail addon radiobutton is clicked with the update voice mail button", true, true);							
			}
			else
				reportStatusFail(methodName + "No Voicemail addon text is not displayed", true);
		} catch (Exception e) {
			reportStatusFail("Error in validating No voicemail addon text",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date:11-04-2022 by @author Pankaj Agarwal
	 * 
	 * Method to select No_VoiceMail_AddOn_RadioButton with update button
	 * @throws Exception 
	 * 
	 */
	public void select_UpdateAddOnButton() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "SelectUpdateAddOnButton@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.updateAddOnButton) && sf.seleU.isElementEnabled(sf.macdSelAddonObj.updateAddOnButton)) {
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.updateAddOnButton);
					reportStatusPass(methodName + "UPdate AddOn Button is clicked", true, true);							
			}
			else
				reportStatusFail(methodName + "update addon button is not displayed and clicked", true);
		} catch (Exception e) {
			reportStatusFail("Error in validating No voicemail addon text",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Continue to Date Selection button is displayed/enabled and able to click.
	 * @throws Exception 
	 * 
	 */
	public void validateAndClickContinueToDateSelection() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(4000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.continueToDateSel) && sf.seleU.isElementEnabled(sf.macdSelAddonObj.continueToDateSel)) {
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.continueToDateSel);
				reportStatusPass(methodName + " Continue to Date Selection button has enabled and clicked successfully. ", true, true);
			}
			else
				reportStatusPass(methodName + " Continue to Date Selection button is not displayed/enabled. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in Continue to Date Selection button on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 12.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate Continue to Date Selection button is disabled.
	 * @throws Exception 
	 * 
	 */
	public void validateContinueToDateSelectionNotEnabled() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "ValidateContinueToDateSelectionButtonDisabled@: ";
			sf.seleU.hardwait(2000);
			if(!sf.seleU.isElementEnabled(sf.macdSelAddonObj.continueToDateSel)) {
				reportStatusPass(methodName + " Continue to Date Selection button is disabled ", true, true);
			}
			else
				reportStatusPass(methodName + " Continue to Date Selection button is displayed/enabled. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in Continue to Date Selection button on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate added extra addon under Add extra features to your plan.
	 * @throws Exception 
	 * 
	 */
	public void validate_ExtraAddonInPlan(String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Step 2 of 2 Page@: ";
			sf.seleU.hardwait(4000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.addedExtraAddon) && sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addedExtraAddon).contains(addon)) {
				sf.seleU.ScrolltoElementPageCenter(sf.macdSelAddonObj.addedExtraAddon);
				reportStatusPass(methodName + addon +" added under Add extra features to your plan. ", true, true);
			}
			else
				reportStatusPass(methodName + addon +" not added under Add extra features to your plan. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in validating extra added addon under Add extra features to your plan section on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Add Add-Ons page is displayed.
	 * @throws Exception 
	 * 
	 */
	public void validate_Add_AddOnPagePresent() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Step 1 of 1 Page@: ";
			sf.seleU.hardwait(3000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.addedExtraAddon) && sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addedExtraAddon).contains("Add any features for your wireless plan by selecting them from below")) {
				reportStatusPass(methodName + " Select Add Addon Step 1 of 1 page is present. ", true, true);
			}
			else
				reportStatusPass(methodName + " Select Add Addon Step 1 of 1 page is not present. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in validating Add Add-ons page is displayed",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 05.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate added extra DP under Add extra features to your plan.
	 * @throws Exception 
	 * 
	 */
	public void validate_ExtraDPInPlan(String dp) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Step 2 of 2 Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.addedExtraDP) && sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addedExtraDP).equalsIgnoreCase(dp)
				&& sf.seleU.isElementDisplayed(sf.macdSelAddonObj.addedDPPrice)) {
				reportStatusPass(methodName + dp +" added under Add extra features to your plan. ", true, true);
				
				if(dp.equalsIgnoreCase("Apple Care") && !sf.seleU.isElementDisplayed(sf.macdSelAddonObj.addedDPFeeType)) {
					reportStatusPass(methodName + dp +" fee Type is One Time. ", true, true);
				}
				else if(dp.equalsIgnoreCase("Device Protection") && sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addedDPFeeType).equals("/mo")){
					reportStatusPass(methodName + dp +" fee Type is Monthly. ", true, true);
				}
			}
			else
				reportStatusPass(methodName + dp +" not added under Add extra features to your plan. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in validating extra added DP under Add extra features to your plan section on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 05.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate select Effective date option.
	 * @throws Exception 
	 * 
	 */
	public void validate_selectEffectiveDate_Options(String addOn, String[] optionList) throws Exception {
		try {
			methodName = "Add Wireless add-ons Step 2 Of 2 Page@: ";
			List<WebElement> dateRadioBtnList = driver.findElements(By.xpath("//span[normalize-space()='"+addOn+"']/../following-sibling::div//input"));
			List<WebElement> dateTextList = driver.findElements(By.xpath("//span[normalize-space()='"+addOn+"']/../following-sibling::div//span[contains(@class,'label radiotext')]"));
			if(addOn.equalsIgnoreCase("Apple Care")){
				if(sf.seleU.isElementSelected(dateRadioBtnList.get(0)) && sf.seleU.getTextFromWebElement(dateTextList.get(0)).contains("Today:"))
					reportStatusPass(methodName + addOn +" has default selected. ", true, true);
			}
			else {
			for(int i=0;i<optionList.length;i++) {	
				String str= sf.seleU.getTextFromWebElement(dateTextList.get(i));
				if(str.contains(optionList[i])) {
					reportStatusPass(methodName + str +" has present under Select Effective Date option. ", true, true);
					if(str.contains("Today:") && sf.seleU.isElementSelected(dateRadioBtnList.get(i)))
						reportStatusPass(methodName + str +" has default selected. ", true, true);
				}
			}
			}
		}catch(Exception e) {
			reportStatusFail("Error is getting while validate the select Effective date for Add Add-On ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 15.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select Effective date for Addon in yyyy/mm/dd 
	 * @throws Exception 
	 * 
	 */
	public void selectEffectiveDate(String addOn, String effectiveFrom, String date) throws Exception {
		try {
			methodName = "Add Wireless add-ons Step 2 Of 2 Page@: ";
			List<WebElement> dateRadioBtnList = driver.findElements(By.xpath("//span[normalize-space()='"+addOn+"']/../following-sibling::div//input"));
			
			for(WebElement ele: dateRadioBtnList) {
				String str= sf.seleU.getElementAttribute(ele, "data-id");
				if(str.contains(effectiveFrom)) {
					sf.seleU.clickElementByJSE(ele);
					if(str.contains("Choose a date")) {
						// it will take the next 7 days
						date = DateTimeUtilities.currentSystemDatePlus(7,"yyyy/MM/dd").trim();
						sf.seleU.clearAndEnterText(sf.macdSelAddonObj.dateInput, date);
						sf.macdSelAddonObj.dateInput.sendKeys(Keys.TAB);
					}
					reportStatusPass(methodName + str +" has selected. ", true, true);
				}
			}
		}catch(Exception e) {
			reportStatusFail("Error is getting while select the Effective date for Add Add-On ", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method developed on @Date: 15.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select Effective date for Addon from Calendar
	 * Note: variable date contains "Date Month" only(eg."5 March")
	 * @throws Exception 
	 * 
	 */
	public void selectEffectiveDateFromCalendar(String addOn, String date) throws Exception {
		try {
			methodName = "Add Wireless add-ons Step 2 Of 2 Page@: ";
			String[] dateArr = date.split(" ");

			WebElement chooseDate = driver.findElement(By.xpath("//span[normalize-space()='" + addOn+ "']/../following-sibling::div//span[contains(text(),'Choose a date')]"));
			sf.seleU.clickElementByJSE(chooseDate);
			sf.seleU.hardwait(3000);
			if (sf.seleU.isElementDisplayed(sf.macdSelAddonObj.dateInput)) {
				WebElement calendarIcon = driver.findElement(By.xpath("//span[normalize-space()='" + addOn+ "']/../following-sibling::div//button[@data-id='datePickerBtn']//*[local-name()='use']"));
				//sf.seleU.clickElementByJSE(calendarIcon);
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.dateInput);;
				while (true) {
					if (sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.calendarMonth).equalsIgnoreCase(dateArr[1])) {
						sf.seleU.clickElementByJSE(sf.macdSelAddonObj.calendarDaysList.get(Integer.parseInt(dateArr[0])));
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName + date + " has selected as Effective Date. ", true, true);
						break;
					} else {
						sf.seleU.clickElementByJSE(sf.macdSelAddonObj.calendarNextMonthIcon);
						sf.seleU.hardwait(2000);
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting while select the Effective date for Add Add-On ", e);
			e.printStackTrace();
		}
	}	
	
	/**
	 * Method developed on @Date: 06.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to Validate Apple Care AddOn on Remove Wireless add-ons.
	 * @throws Exception 
	 * 
	 */
	public void AddAppleCareTextOnSelectEffectivedate(String header, String subText1, String subText2) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			System.out.println(sf.macdSelAddonObj.addAppleCareBlock.getCssValue("background").contains("rgb(241, 253, 255)"));
			if(sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addAppleCareHeader).equals(header) && sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addAppleCareText1).equals(subText1)
					&& sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addAppleCareText2).equals(subText2) && sf.macdSelAddonObj.addAppleCareBlock.getCssValue("background").contains("rgb(241, 253, 255)")) {
				reportStatusPass(methodName+ "AppleCare is in Blue color & Header and SubHeader has matched correctly.",true, true);
			}
		}catch(Exception e) {
			reportStatusFail("Error in validating AppleCare on Remove Wireless add-ons Page. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 08.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate List of AddOn on Add Wireless AddOns Page.
	 * 
	 * @throws Exception
	 */
	public void verify_ListofAddOn(HashMap addOnPriceMap) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless AddOn Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();
			// Iterate AddOn
			for (int i = 0; i < sf.macdSelAddonObj.addOnHeadingList.size(); i++) {
				String addOnHeader = sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addOnHeadingList.get(i));
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.addOnHeadingList.get(i));
				// validate addOn
				sf.seleU.ScrolltoElement(sf.macdSelAddonObj.addOnHeadingList.get(i));
				if (addOnHeader.equalsIgnoreCase("Voicemail")) {
					// Iterate Addon under voice mail
					for (int j = 0; j < sf.macdSelAddonObj.voiceMailAddOnList.size(); j++) {
						String addOn = sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.voiceMailAddOnList.get(j));
						validate_AddonName_Price_Description(addOnPriceMap, addOn);					
					}
				} else {
					// Iterate Addon
					for (int j = 0; j < sf.macdSelAddonObj.addOnList.size(); j++) {
						String addOn = sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addOnList.get(j));
						validate_AddonName_Price_Description(addOnPriceMap, addOn);	
						if (addOnHeader.equalsIgnoreCase("Long Distance")) {
							validate_LongDistanceAddOn(addOn);
						}
					}					
				}
				validate_AddAddOnsButtonPresent(addOnHeader);
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating AddOn Name, price, description and Add AddOns button on Add Wireless AddOns Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 08.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Long Distance AddOn on Add Wireless AddOns Page.
	 * @throws Exception
	 */
	public void validate_LongDistanceAddOn(String addOn) throws Exception {
		try {
			methodName = "Add Wireless AddOn Page@: ";
			List<WebElement> offerRadioButton = driver.findElements(By.xpath("//*[contains(text(), '"+addOn+"')]/ancestor::div[contains(@class,'bottom_medium')]/div[2]//input"));
			WebElement NoOfferprice = driver.findElement(By.xpath("//*[contains(text(), '"+addOn+"')]/ancestor::div[contains(@class,'bottom_medium')]/div[3]//span"));			
			String price= addOn.equals("US LD")?"15" :"7";
			int count=0;
			for(WebElement ele: offerRadioButton) {
				String str= sf.seleU.getElementAttribute(ele, "value");
				sf.seleU.clickElementByJSE(ele);
				
				if(str.equalsIgnoreCase("No Offer") && sf.seleU.getTextFromWebElement(NoOfferprice).equals(price))
					reportStatusPass(methodName + str +" promo present.",true, true);
				else {
					WebElement addOnPrice = driver.findElement(By.xpath("//*[contains(text(), '"+addOn+"')]/ancestor::div[contains(@class,'bottom_medium')]/div[3]//div[contains(@class,'addon-item-promo_text')]"));
					if(str.equalsIgnoreCase(addOn) && sf.seleU.getTextFromWebElement(addOnPrice).contains("0.00"))				
					reportStatusPass(methodName + str +" promo present.",true, true);
				}
			}	
		}catch(Exception e) {
			reportStatusFail("Error is validating Long Distance AddOns on Add Wireless AddOns Page", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 08.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate Add AddOn Button is present on Add Wireless AddOns Page.
	 * @throws Exception
	 */
	public void validate_AddAddOnsButtonPresent(String addOnHeader) throws Exception {
		// TODO Auto-generated method stub
		try {
			if(!addOnHeader.equalsIgnoreCase("Device Protection") && sf.seleU.isElementDisplayed(sf.reviewWALineObj.Add_addOnBtn))
				reportStatusPass(methodName+ " Add add-ons button has Present. ",true, true);
			else if(addOnHeader.equalsIgnoreCase("Device Protection") && (sf.seleU.isElementDisplayed(sf.reviewWALineObj.Add_addOnBtn) || sf.seleU.isElementDisplayed(sf.macdSelAddonObj.continueBtn)))
				reportStatusPass(methodName+ " Add add-ons/continue button has Present. ",true, true);
			else
				reportStatusFail(methodName+ " Add add-ons button has not Present. ",true);
		}catch(Exception e) {
			reportStatusFail("Error is validating Add add-ons button on Add Wireless AddOns Page", e);
			e.printStackTrace();
			
		}
	}
	/**
	 * Method developed on @Date: 08.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate AddOn name, Description and price on Add Wireless AddOns Page.
	 * 
	 * @throws Exception
	 */
	public void validate_AddonName_Price_Description(HashMap addOnPriceMap, String addOn) throws Exception {
		try {
			methodName = "Select AddOn Page@: ";
			String selector="";
			WebElement addonSelector = driver.findElement(By.xpath("//*[contains(text(),'" +addOn+"')]/../preceding-sibling::span[contains(@class,'checkbox_faux')] | //*[contains(text(),'" +addOn+"')]/preceding-sibling::span[contains(@class,'radio_faux')]"));
			WebElement addOnPrice = driver.findElement(By.xpath("(//*[contains(text(),'" +addOn+"')]/ancestor::div[contains(@class,'bottom_medium')]/div[3]//span)[1]| (//*[contains(text(),'" +addOn+"')]/ancestor::div[contains(@class,'grid_vertical')]/div[2]//span)[1]"));
			WebElement addOnDesc = driver.findElement(By.xpath("(//*[contains(text(),'" +addOn+"')]/ancestor::div[contains(@class,'grid_vertical') or contains(@class,'bottom_medium')]//div/p)[1]"));
			// Validate addon is matched with hashmap key and validate if Checkbox and RadioButton
			
			if(addOn.contains("Voicemail"))
				selector="radio";
			else
				selector="checkbox";
			
			if (addOnPriceMap.containsKey(addOn) && sf.seleU.getElementAttribute(addonSelector, "class").contains(selector)) {
				int price = Integer.parseInt(sf.seleU.getTextFromWebElement(addOnPrice).split("\\.")[0].replaceAll("[^0-9]", ""));
				//validate addon price is matching with hashmap value
				String addOnString = (String) addOnPriceMap.get(addOn);
				String[] addOnArr=addOnString.split(":");
				String str = sf.seleU.getTextFromWebElement(addOnDesc);
				System.out.println(sf.seleU.getTextFromWebElement(addOnDesc).equalsIgnoreCase(addOnArr[1]));
				if (Integer.parseInt(addOnArr[0]) == price && sf.seleU.getTextFromWebElement(addOnDesc).equalsIgnoreCase(addOnArr[1])) {
					reportStatusPass(
							methodName + addOn + " : " + addOnArr[0] + " Addon name, Price and Description is matching with Requirement",true, true);
				} else {
					reportStatusFail("AddOn Price and description is not matching", true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating AddOn Name, Description and price on Add Wireless AddOns Page", e);
			e.printStackTrace();
		}

	}
	/**
	 * Method developed on @Date: 08.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Continue to Date Selection button is displayed.
	 * @throws Exception 
	 * 
	 */
	public void validate_ContinueToDateSelection() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.continueToDateSel) && sf.seleU.isElementEnabled(sf.macdSelAddonObj.continueToDateSel)) {
				reportStatusPass(methodName + " Continue to Date Selection button has displayed and Greyed out. ", true, true);
			}
			else
				reportStatusPass(methodName + " Continue to Date Selection button is not displayed/greyed Out. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in Continue to Date Selection button on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 08.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * To Validate List of AddOn on Add Wireless AddOns Page.
	 * 
	 * @throws Exception
	 */
	public void verify_RemoveListofAddOn(HashMap addOnPriceMap) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless AddOn Page@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.scrollToTop();
			// Iterate AddOn			
			for (int i = 0; i < sf.macdSelAddonObj.addOnList.size(); i++) {
				sf.seleU.ScrolltoElement(sf.macdSelAddonObj.addOnList.get(i));
				String addOn = sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addOnList.get(i));
				validate_AddonName_Price_Description(addOnPriceMap, addOn);
			}
		} catch (Exception e) {
			reportStatusFail(
					"Error is validating AddOn Name, price, description and Add AddOns button on Add Wireless AddOns Page",e);
		}
	}


	/**
	 * Method developed on @Date: 07.04.2022 by @author viswas reddy
	 * 
	 * Method to select addon's on long distance tab
	 * @throws Exception 
	 * 
	 */
	public void selectAddOn_LongDistance(String addOn, String offer) throws Exception {
		try {
			methodName = "Add Wireless add-ons Page@: Long Distance ";
			sf.seleU.hardwait(2000);
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + addOn
					+ "')]/..//preceding-sibling::span[@class='nds-checkbox_faux']"));
			sf.seleU.clickElementByJSE(ele);
			reportStatusPass(methodName + "clicked on checkbox " + addOn, true, true);
			if (offer.contains("promo")) {
				WebElement promoRadio = driver
						.findElement(By.xpath("//label[@class='nds-radio__label']//span[contains(text(),'" + addOn
								+ "')]//preceding-sibling::span[@class='nds-radio_faux']"));
				sf.seleU.clickElementByJSE(promoRadio);
				WebElement priceStrike = driver.findElement(By.xpath("//span[contains(text(),'" + addOn
						+ "')]/ancestor::div[contains(@class,'slds')]/following-sibling::div[contains(@class,'slds-align_absolute-center')]//div[contains(@class,'promoSelection')]"));
				WebElement offerPrice = driver.findElement(By.xpath("//span[contains(text(),'" + addOn
						+ "')]/ancestor::div[contains(@class,'slds')]/following-sibling::div[contains(@class,'slds-align_absolute-center')]//div[contains(@class,'addon-item-promo_text')][contains(text(),'$0.00/mo')]"));
				if (priceStrike.isDisplayed() && offerPrice.isDisplayed()) {
					if(priceStrike.getCssValue("text-decoration").contains("line-through") && offerPrice.getCssValue("color")
							.equals("rgba(218, 41, 28, 1)"))
					reportStatusPass(methodName + "successfully clicked on promo " + addOn + " price is striked out",
							true, true);
				}
			}
		} catch (Exception e) {
			reportStatusFail(methodName+"error in selecting addon", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 07.04.2022 by @author viswas reddy
	 * 
	 * Method to Validate default no offer is selected in long distance addon's tab
	 * @throws Exception 
	 * 
	 */
	public void validate_defaultNoOfferSelection(String[] addOn) throws Exception {
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			
			for (int i = 0; i <=addOn.length-1; i++) {
				WebElement noOffer = driver.findElement(By.xpath("//span[normalize-space()='"+addOn[i]+"']/ancestor::fieldset/preceding-sibling::fieldset//span[normalize-space()='No Offer']/parent::label/preceding-sibling::input[@type='radio']"));
				sf.seleU.ScrolltoElementPageCenter(noOffer);
				if (sf.seleU.isElementSelected(noOffer)) {
					reportStatusPass(methodName + "by default for "+addOn[i]+" no offer is selected", true, true);
				}else {
					reportStatusFail(methodName+"by default for "+addOn[i]+" no offer is not selected", true);
				}
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in validating default no offer", true);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 25.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Skip AddOn button is displayed/enabled and able to click and Category got collapsed.
	 * @throws Exception 
	 * 
	 */
	public void validateAndClickSkipAddOnsLink(String addOn) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			List<WebElement> selectAddOnList = driver.findElements(By.xpath("//div[contains(text(),'"+addOn+"')]/../following-sibling::div//span[contains(@class,'addon-item-text_large')] | //*[contains(text(),'Voicemail')]/../following-sibling::div//span[contains(@class,'radio-options_text')]"));
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.skipAddOns) && sf.seleU.isElementEnabled(sf.macdSelAddonObj.skipAddOns)) {
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.skipAddOns);
				sf.seleU.hardwait(3000);
				if(selectAddOnList.size()==0)
					reportStatusPass(methodName + " Skip AddOns link has enabled and clicked successfully. And AddOn category got collapsed. ", true, true);
			}
			else
				reportStatusPass(methodName + " Skip AddOns link is not displayed/enabled. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in Skip AddOns link on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 25.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate Back to Previous link is displayed/enabled and able to click.
	 * @throws Exception 
	 * 
	 */
	public void selectBackToPreviousLink() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Step 2 of 2 Page@: ";
			sf.seleU.hardwait(4000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.backToPrevious)) {
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.backToPrevious);
				reportStatusPass(methodName + " Back to Previous link has clicked successfully. ", true, true);
			}
			else
				reportStatusPass(methodName + " Back to Previous link is not displayed. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in Back to Previous link on Add Wireless add-ons Step 2 of 2 Page. ",e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 28.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to select addon on Add Wireless add-ons Page. 
	 * @throws Exception  
	 * 
	 */
	public void deSelectAddOn(String addOnOption, String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(4000);			
			WebElement option = driver.findElement(By.xpath("//div[@class='summary-section']//div[contains(text(),'" + addOnOption + "')]"));
			
			if (sf.seleU.isElementDisplayed(option)) {
				sf.seleU.ScrolltoElementPageCenter(option);
				reportStatusPass(methodName + addOnOption + " is present. ", true, true);
				
				WebElement optionExpand = driver.findElement(By.xpath("//div[@class='slds-grid']//div[contains(text(),'"+ addOnOption + "')]/following-sibling::div[contains(@class,'edit')]"));
				sf.seleU.clickElementByJSE(optionExpand);
				sf.seleU.hardwait(3000);
				WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'"+addon+"')]/../preceding-sibling::span | //span[contains(text(),'"+addon+"')]/preceding-sibling::span"));				
								
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", ele);				
				reportStatusPass(methodName + addon + " is deselected. ", true, true);
			} else
				reportStatusFail(methodName + addOnOption + " is not present. ", true);

		} catch (Exception e) {
			reportStatusFail("Error is getting while DeSelect AddOn. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 04.04.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate addon not present under Add extra features to your plan.
	 * @throws Exception 
	 * 
	 */
	public void validate_ExtraAddonInPlanNotAdded(String addon) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Add Wireless add-ons Step 2 of 2 Page@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.addedExtraAddon) && !sf.seleU.getTextFromWebElement(sf.macdSelAddonObj.addedExtraAddon).contains(addon)) {
				sf.seleU.ScrolltoElementPageCenter(sf.macdSelAddonObj.addedExtraAddon);
				reportStatusPass(methodName + addon +" not added under Add extra features to your plan. ", true, true);
			}
			else
				reportStatusPass(methodName + addon +" added under Add extra features to your plan. ", true, true);
		} catch (Exception e) {
			reportStatusFail("Error in validating not added extra addon under Add extra features to your plan section on Add Wireless add-ons Page. ",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 16.05.2022 by @author Viswas reddy
	 * 
	 * Method to validate addon for Dp not available after 60 days according to NAC business rule.
	 * @throws Exception 
	 * 
	 */
	public void validate_AddDP_NACbusinessRuleAFter60Days() throws Exception {
		try {
			methodName = "Add Wireless add-ons Page@: ";
			sf.seleU.hardwait(2000);
			WebElement option = driver.findElement(By.xpath("//div[@class='summary-section']//div[contains(text(),'Device Protection')]"));
			
			if (sf.seleU.isElementDisplayed(option)) {
				sf.seleU.ScrolltoElementPageCenter(option);
				reportStatusPass(methodName+ "Device protection tab is present. ", true, true);
				
				WebElement optionExpand = driver.findElement(By.xpath("//div[@class='slds-grid']//div[contains(text(),'Device Protection')]/following-sibling::div/*[@title='Edit']"));
				sf.seleU.clickElementByJSE(optionExpand);
				sf.seleU.hardwait(3000);
				if(sf.seleU.isElementDisplayed(sf.macdSelAddonObj.DPblockContent)) {
					reportStatusPass(methodName+"DP is not available after 60 days according to NAC business rule", true, true);
					String boldtext = "Device Protection is not available";
					String textbody = "Device protection is only available to activate within 60 days of activating your "
							+ "new line or upgrading your device.";
					WebElement boldText = driver.findElement(By.xpath("//span[contains(@class,'bold')]"));
					WebElement textBody = driver.findElement(By.xpath("//p[contains(@class,'text-body_regular')]"));
					if(sf.seleU.getTextFromWebElement(boldText).equalsIgnoreCase(boldtext) 
							&& sf.seleU.getTextFromWebElement(textBody).equalsIgnoreCase(textbody)) {
						reportStatusPass(methodName+"DP pop up message is as expected", true, true);						
					}else {
						reportStatusFail(methodName+"DP pop up message is not as expected", true);
					}
				}else {
					reportStatusFail(methodName+"DP block content is not showing", true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error in validating Add DP after 60 days of NAC business rule",e);
			e.printStackTrace();
		}
	}
}
