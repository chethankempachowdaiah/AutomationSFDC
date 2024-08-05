package sfdc.pages.macd;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_ChangeWirelessPlans_Page extends MyListener {

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_ChangeWirelessPlans_Page() {
		sf = new SFDC_AllPageObjects();
	}
	
	/**
	 * Method developed on @Date: 11.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to click change wireless plan button.
	 * @throws Exception 
	 * 
	 */
	public void clickChangeWrlsPlanBtn() throws Exception {
		try {
			methodName = "Review price plan Page@ :";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.changeWirelessPlanBtn);
			sf.seleU.wait(2000);
			sf.seleU.waitTillLoading();
			assertTrue(sf.macdChangeWirelessPlansObj.voiceAndData.isDisplayed());
			reportStatusPass(methodName+"sucessfully clicked on change wireless plan button", true, true);
		}catch (Exception e) {
			reportStatusFail(methodName+"failed to click on change wireless plan button", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 11.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to check default plan selection tab.
	 * @throws Exception 
	 * 
	 */
	public void checkVoiceAndDataDefaultSelected() throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			if(sf.seleU.getElementAttribute(sf.macdChangeWirelessPlansObj.voiceAndData, "aria-selected").equalsIgnoreCase("true")) {
				reportStatusPass(methodName+"By default Voice and data tab is selected", true, true);
			}else {
				reportStatusFail(methodName+"By default voice and data tab is not selected", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in checking default plan selection tab", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 11.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to check current plan tab is shown selected
	 * @throws Exception 
	 * 
	 */
	public void checkCurrentPlanTab(String prevPlanSize) throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			//WebElement ele = driver.findElement(By.xpath("//div[@class='nds-p-bottom_medium']//button[contains(@data-id,'"+prevPlanSize+"')]"));
			WebElement currentPlantext = driver.findElement(By.xpath("//div[contains(@class,'offer-bucketsize') and contains(text(),'"+prevPlanSize+"')]/parent::div/following-sibling::div//span[contains(text(),'current plan')]"));
			if(currentPlantext.isDisplayed()) {
				reportStatusPass(methodName+"Current plan is not selectable", true, true);
			}else {
				reportStatusFail(methodName+"current plan is selectable or current plan text not present", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in checking current plan selection tab", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 12.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to check default pooled is selected and can switch to standalone.
	 * @throws Exception 
	 * 
	 */
	public void validatePooledAndStandalone() throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			for(int i=0; i<sf.macdChangeWirelessPlansObj.pooledRadio.size(); i++) {
				if(sf.seleU.isElementSelected(sf.macdChangeWirelessPlansObj.pooledRadio.get(i)) && 
						sf.seleU.isElementPresent(sf.macdChangeWirelessPlansObj.standaloneRadio.get(i))) {
					reportStatusPass(methodName+"By default pooled is selected and can switch to standalone", true, true);
				}else {
					reportStatusFail(methodName+"By default pooled is not selected or standalone not present", true);
				}
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in validating pooled and standalone", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 12.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to select new wireless plan.
	 * @throws Exception 
	 * 
	 */
	public void selectNewWirelessPlan_VoiceAndData(String newPlanSize) throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			WebElement selectPlan = driver.findElement(By.xpath("//button[text()='Select plan' and contains(@data-id,'RGR_WRLS_VD_"+newPlanSize+"')]"));
			sf.seleU.clickElementByJSE(selectPlan);
			sf.seleU.hardwait(2000);
			WebElement planSleected = driver.findElement(By.xpath("//div[contains(@class,'offer-bucketsize') and contains(text(),'"+newPlanSize+"')]/parent::div/following-sibling::div//span[contains(@class,'offer-tick_mark')]//span[contains(text(),'Plan selected')]"));
			if(planSleected.isDisplayed()) {
				reportStatusPass(methodName+newPlanSize+" plan is selected", true, true);
			}else {
				reportStatusFail(methodName+"plan not selected", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in selecting new plan", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 12.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to validate unable to select multiple plans.
	 * @throws Exception 
	 * 
	 */
	public void validate_UnableToSelectMultiPlans() throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			for(int i=0; i<sf.macdChangeWirelessPlansObj.selectPlan.size(); i++) {
				sf.seleU.clickElementByJSE(sf.macdChangeWirelessPlansObj.selectPlan.get(i));
				sf.seleU.hardwait(1000);
			}
			if(sf.macdChangeWirelessPlansObj.planSelectedText.size()==1) {
				reportStatusPass(methodName+"user can select only one plan at a time", true, true);
			}else {
				reportStatusFail(methodName+"multi plans is selectable and not expected", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"validation failed on checking unable to multi plan selection", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 14.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to click on data only tab.
	 * @throws Exception 
	 * 
	 */
	public void clickOnDataOnlyTab() throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.macdChangeWirelessPlansObj.dataOnly);
			sf.seleU.hardwait(2000);
			if(sf.seleU.getElementAttribute(sf.macdChangeWirelessPlansObj.dataOnly, "aria-selected").equalsIgnoreCase("true")) {
				reportStatusPass(methodName+"Data only tab is selected", true, true);
			}else {
				reportStatusFail(methodName+"data only tab is not selected", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in selecting data only tab", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 14.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to select new wireless plan.
	 * @throws Exception 
	 * 
	 */
	public void selectNewWirelessPlan_DataOnly(String newPlanSize) throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			WebElement selectPlan = driver.findElement(By.xpath("//lightning-tab[@aria-labelledby='WRLS_DATA__item']//button[text()='Select plan' and contains(@data-id,'DATA_ONLY')]"));
			sf.seleU.clickElementByJSE(selectPlan);
			sf.seleU.hardwait(2000);
			WebElement planSleected = driver.findElement(By.xpath("//lightning-tab[@aria-labelledby='WRLS_DATA__item']//div[contains(@class,'offer-bucketsize') and contains(text(),'"+newPlanSize+"')]"
					+ "/parent::div/following-sibling::div//span[contains(@class,'offer-tick_mark')]//span[contains(text(),'Plan selected')]"));
			if(planSleected.isDisplayed()) {
				reportStatusPass(methodName+newPlanSize+" plan is selected", true, true);
			}else {
				reportStatusFail(methodName+"plan not selected", true);
			}
		}catch (Exception e) {
			reportStatusFail(methodName+"error in selecting new plan", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 18.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to click continue to date selection button
	 * @throws Exception 
	 * 
	 */
	public void click_continueToDateSelection() throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdChangeWirelessPlansObj.contToDateSelection)) {
				sf.seleU.clickElementByJSE(sf.macdChangeWirelessPlansObj.contToDateSelection);
			}
			sf.seleU.hardwait(4000);
			sf.seleU.waitTillLoading();
			sf.seleU.wait(5000);
			reportStatusPass(methodName+"successfully clicked on continue to date selection button", true, true);
		}catch (Exception e) {
			reportStatusFail(methodName+"error in clicking continue to date selection button", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 18.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to check added new plan is present.
	 * @throws Exception 
	 * 
	 */
	public void validate_newAddedFeatures(String newPlan) throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			String str = sf.seleU.getTextFromWebElement(sf.macdChangeWirelessPlansObj.newPlanFeature).replaceAll("\\s", "");
			if(str.contains(newPlan)) {
				reportStatusPass(methodName+"newly added plan "+newPlan+" is present", true, true);
			}else {
				reportStatusFail(methodName+"newly added plan "+newPlan+" is not present", true);
			}
			
		}catch (Exception e) {
			reportStatusFail(methodName+"error in selecting new plan", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 19.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to validate effective date options.
	 * @throws Exception 
	 * 
	 */
	public void validate_selectEffectiveDate_Options(String[] optionList) throws Exception {
		try {
			methodName = "Change Wireless Plans Step 2 Of 2 Page@: ";
			sf.seleU.hardwait(2000);
			for(int i=0;i<optionList.length;i++) {	
				String str= sf.seleU.getTextFromWebElement(sf.macdChangeWirelessPlansObj.dateRadioTextList.get(i));
				if(str.contains(optionList[i])) {
					reportStatusPass(methodName + str +" has present under Select Effective Date option. ", true, true);
					if(str.contains("Next billing cycle:") 
							&& sf.seleU.isElementSelected(sf.macdChangeWirelessPlansObj.dateradioBtnList.get(i)))
						reportStatusPass(methodName + str +" has default selected. ", true, true);
				}
			}
			
		}catch(Exception e) {
			reportStatusFail("Error is getting while validate the select Effective date for change wireess plan ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 19.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to select effective date from the options.
	 * @throws Exception 
	 * 
	 */
	public void selectEffectiveDate(String effectiveFrom, String date) throws Exception {
		try {
			methodName = "Change Wireless Plans Step 2 Of 2 Page@: ";
			
			
			for(WebElement ele: sf.macdChangeWirelessPlansObj.dateradioBtnList) {
				String str= sf.seleU.getElementAttribute(ele, "data-id");
				if(str.contains(effectiveFrom)) {
					sf.seleU.clickElementByJSE(ele);
					if(str.contains("Choose a date")) {
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
	 * Method developed on @Date: 25.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to check success message at the cart is displayed or not.
	 * @throws Exception 
	 * 
	 */
	public void check_CartSuccessMsg() throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdChangeWirelessPlansObj.successCartMsg)) {
				reportStatusPass(methodName+"successful cart message displayed", true, true);
			}else {
				reportStatusFail(methodName+"successful cart message is not displayed", true);
			}
			
		}catch (Exception e) {
			reportStatusFail(methodName+"error in checking cart successful msg", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 25.04.2022 by @author Viswas.Reddy
	 * 
	 * Method to check conflict discount section and click on proceed or cancel.
	 * @throws Exception 
	 * 
	 */
	public void check_ConflictingDiscountAndClickProceedOrChangePlan(String action) throws Exception {
		try {
			methodName = "Change Wireless Plans Page@ :";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdChangeWirelessPlansObj.conflictDiscountHeader) 
					&& sf.seleU.isElementDisplayed(sf.macdChangeWirelessPlansObj.conflictDiscountcontainer)) {
				reportStatusPass(methodName+"Plan has conflict discount ", true, true);
				if(action=="Remove & Proceed") {
					sf.seleU.clickElementByJSE(sf.macdChangeWirelessPlansObj.removeDiscountAndProceed);
					reportStatusPass(methodName+"clicked on remove and proceed button", true, true);
					sf.seleU.hardwait(2000);
					sf.seleU.waitTillLoading();
				}
				if(action=="Cancel") {
					sf.seleU.clickElementByJSE(sf.macdChangeWirelessPlansObj.cancelWirelessPlanChange);
					sf.seleU.hardwait(2000);
					sf.seleU.waitTillLoading();
					reportStatusPass(methodName+"clicked on cancel wireless plan change button", true, true);
				}
			}
			
		}catch (Exception e) {
			reportStatusFail(methodName+"error in checking conflict discount section", true);
			e.printStackTrace();
		}
	}
	
}
