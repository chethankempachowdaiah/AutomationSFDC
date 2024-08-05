package sfdc.pages.macd;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_TelephNumberChange_Page extends MyListener {

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_TelephNumberChange_Page() {
		sf = new SFDC_AllPageObjects();
	}
	
	/**
	 * Method developed on @Date: 04.05.2022 by @author Viswas reddy
	 * 
	 * Method to select Phone Number and click on Select Number button on Search and Select telephone numbers page. 
	 * @throws Exception 
	 * 
	 */
	public void click_SelectNumberButtonForTNC(String phoneNum) throws Exception {
		try {
			methodName = "Search and Select telephone numbers Page@: ";
			sf.seleU.hardwait(2000);
			phoneNum="1010004119";
			if(sf.accManagementObj.selectNameRadioBtn.size()>1) {
				sf.seleU.enterText(sf.accManagementObj.searchTelephone, phoneNum);
				sf.seleU.hardwait(5000);
				System.out.println(sf.accManagementObj.selectNameRadioBtn.get(0).isSelected());
				if(!sf.accManagementObj.selectNameRadioBtn.get(0).isSelected())
					sf.seleU.clickElementByJSE(sf.accManagementObj.selectNameRadioBtn.get(0));
			}
			sf.seleU.clickElementByJSE(sf.accManagementObj.selectNumberBtn);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.macdTelephNumChangeObj.telePhNumChangeHeader));
			reportStatusPass(methodName + phoneNum+ " PhoneNumber has selected and click on Select Number button on Search and Select telephone numbers Page. ",
					true, true);
		} catch (Exception e) {
			reportStatusFail("Error is selecting " + phoneNum + " on the Search and Select telephone numbers Page. ",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 05.05.2022 by @author Viswas reddy
	 * 
	 * Method to choose a reason to change telephone number and click on proceed next. 
	 * @throws Exception 
	 * 
	 */
	public void choose_ReasonToChangeTelePhNum(String reason) throws Exception {
		try {
			methodName = "Choose a reason to change telephone number@: ";
			sf.seleU.hardwait(2000);
			WebElement ele = driver.findElement(By.xpath("//span[text()='"+reason+"']/parent::label[@class='nds-radio__label']"));
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.clickElementByJSE(sf.macdTelephNumChangeObj.proceedNext);
			reportStatusPass(methodName+"successfully choosed a reason to change telephone number", true, true);
			sf.seleU.hardwait(2000);
			sf.seleU.waitTillLoading();
		} catch (Exception e) {
			reportStatusFail("Error in choosing " + reason + " to change Telephone Number change Page. ",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 05.05.2022 by @author Viswas reddy
	 * 
	 * Method to select location to change telephone number. 
	 * @throws Exception 
	 * 
	 */
	public void select_NumChangeLocation(String province, String city) throws Exception {
		try {
			methodName = "Choose a location to change telephone number @: ";
			sf.seleU.hardwait(2000);
			WebElement ele = driver.findElement(By.xpath("//div[@data-value='"+province+"']"));
			sf.seleU.clickOnElement(sf.macdTelephNumChangeObj.provinceDropdown);
			sf.seleU.clickOnElement(ele);
			sf.seleU.hardwait(2000);
			sf.seleU.clearAndEnterText(sf.macdTelephNumChangeObj.city, city);
			sf.seleU.hardwait(2000);
			sf.macdTelephNumChangeObj.city.sendKeys(Keys.TAB);
			sf.seleU.clickOnElement(sf.macdTelephNumChangeObj.proceedNextSelectLoc);
			if(sf.seleU.isElementDisplayed(sf.macdTelephNumChangeObj.selectPhoneNumber)) {
				reportStatusPass(methodName+"successfully selected a location", true, true);
			}
			sf.seleU.waitTillLoading();
		} catch (Exception e) {
			reportStatusFail("Error in selecting Telephone Number change location ", true);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 05.05.2022 by @author Viswas reddy
	 * 
	 * Method to validate step 1 location collapsed and data entered is remembered. 
	 * @throws Exception 
	 * 
	 */
	public void validate_stepOneLocation(String province) throws Exception {
		try {
			methodName = "validate Step 1: location@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.macdTelephNumChangeObj.editStepOne)) {
				reportStatusPass(methodName+"Step 1: Location is collapsed after clicked next", true, true);
				sf.seleU.clickElementByJSE(sf.macdTelephNumChangeObj.editStepOne);
				WebElement ele = driver.findElement(By.xpath("//input[@data-value='"+province+"']"));
				WebElement eleCity = driver.findElement(By.xpath("//input[contains(@class,'nds-not-empty')]/following-sibling::div//label[text()='City']"));
				if(sf.seleU.isElementDisplayed(ele) && sf.seleU.isElementDisplayed(eleCity)) {
					reportStatusPass(methodName+"Step 1: Location previous data is remembered", true, true);
				}else {
					reportStatusFail(methodName+"Step 1: Location previous data is not remembered", true);
				}
			}else {
				reportStatusFail(methodName+"Step 1: location is not collapsed after clicked next", true);
			}
		} catch (Exception e) {
			reportStatusFail("Error in validating step 1 location ", true);
			e.printStackTrace();
		}
	}
}