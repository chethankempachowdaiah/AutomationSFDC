package sfdc.pages.macd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_BlockService_Page extends MyListener{

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;
	
	public MACD_BlockService_Page() {
		sf = new SFDC_AllPageObjects();
	}
	
	/**
	 * Method developed on @Date: 25.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to see Available block Type that is Block CTN with a checkbox and a short description. 
	 * @throws Exception 
	 * 
	 */
	public void verify_BlockCTN_Checkbox_Des() throws Exception {
		try {
			if(sf.seleU.getTextFromWebElement(sf.macdBlkServiceObj.blockctn).contains("Block Telephone Number")) {
				reportStatusPass("Available Block Type that is Block Telephone Number is present",true,true);
			}
			else {
				reportStatusFail("Unable to see available block type",true);
			}
			if(sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.checkbox)) {
				reportStatusPass("User can see checkbox in Block Type",true,true);
			}
			else {
				reportStatusFail("User not able to see checkbox in block type",true);
			}
			if(sf.seleU.getTextFromWebElement(sf.macdBlkServiceObj.description).contains("Blocks all wireless services and add-ons from being used on this wireless line and does not allow incoming or outgoing calls.")) {
				reportStatusPass("User can see short description in Block Type",true,true);
			}
			else {
				reportStatusFail("User not able to see short Description in Block Type",true);
			}
		}catch(Exception e) {
			reportStatusFail("Error is getting while seeing Available Block Type", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 25.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Continue Button in Block Service Page
	 * @throws Exception 
	 * 
	 */

	public void click_Continue() throws Exception {

		try {

			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.continueBtn));
			sf.seleU.clickElementByJSE(sf.macdBlkServiceObj.continueBtn);
			sf.seleU.wait(2000);
			reportStatusPass("Continue Button is clicked", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 27.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to Select Block reason and click Continue Button in Block Service Page
	 * @throws Exception 
	 * 
	 */
	public void validate_BlockReason(String blkreason)throws Exception {
		try {
            sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.selectBlkReason));
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.selectBlkReasonDes));
			sf.seleU.hardwait(3000);
			WebElement ele = driver.findElement(By.xpath("//span[normalize-space()='" + blkreason + "']//span[@class='nds-radio_faux']"));
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.wait(2000);
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.continueBtn));
			sf.seleU.clickElementByJSE(sf.macdBlkServiceObj.continueBtn);
			sf.seleU.wait(2000);
			reportStatusPass("Block reason " +blkreason+ " is Selected and Continue Button is clicked", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 28.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to Select Block Date and click Continue Button in Block Service Page
	 * @throws Exception 
	 * 
	 */
	public void selectBlockEffectiveDate(String effectiveFrom) throws Exception {
		try {
			methodName = "Block Service Page@: ";
			List<WebElement> dateRadioBtnList = driver.findElements(By.xpath("//span[contains(@class,'radio-options_text')]"));
			
			for(WebElement ele: dateRadioBtnList) {
				String str= sf.seleU.getElementAttribute(ele, "Today:");
				if(str.contains(effectiveFrom)) {
					sf.seleU.clickElementByJSE(ele);
					if(str.contains("Choose a date")) {
						// it will take the next 7 days
						String date = DateTimeUtilities.currentSystemDatePlus(7,"yyyy/MM/dd").trim();
						sf.seleU.clearAndEnterText(sf.macdSelAddonObj.dateInput, date);
						sf.macdSelAddonObj.dateInput.sendKeys(Keys.TAB);
					}
					reportStatusPass(methodName + str +" has selected. ", true, true);
				}
			}
		}catch(Exception e) {
			reportStatusFail("Error is getting while select the Block date ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 05.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to select Effective date from Calendar
	 * Note: variable date contains "Date Month" only(eg."5 March")
	 * @throws Exception 
	 * 
	 */
	public void selectEffectiveDateFromCalendar(String date) throws Exception {
		try {
			
			String[] dateArr = date.split(" ");

			WebElement chooseDate = driver.findElement(By.xpath("//span[normalize-space()='Choose a date (up to next 45 days)']//span[@class='nds-radio_faux']"));
			sf.seleU.clickElementByJSE(chooseDate);
			sf.seleU.hardwait(3000);
			if (sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.dateInput)) {
				WebElement calendarIcon = driver.findElement(By.xpath("//button[@data-id='datePickerBtn']//*[local-name()='use']"));
				//sf.seleU.clickElementByJSE(calendarIcon);
				sf.seleU.clickElementByJSE(sf.macdSelAddonObj.dateInput);;
				while (true) {
					if (sf.seleU.getTextFromWebElement(sf.macdBlkServiceObj.calendarMonth).equalsIgnoreCase(dateArr[1])) {
						sf.seleU.clickElementByJSE(sf.macdBlkServiceObj.calendarDaysList.get(Integer.parseInt(dateArr[0])));
						sf.seleU.hardwait(2000);
						reportStatusPass(methodName + date + " has selected as Effective Date. ", true, true);
						break;
					} else {
						sf.seleU.clickElementByJSE(sf.macdBlkServiceObj.calendarNextMonthIcon);
						sf.seleU.hardwait(2000);
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error is getting while select the Effective date ", e);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method developed on @Date: 05.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Confirm Block Date Button in Block Service Page
	 * @throws Exception 
	 * 
	 */

	public void click_ConfirmBlockDate() throws Exception {

		try {

			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdBlkServiceObj.confirmBlockDate));
			sf.seleU.clickElementByJSE(sf.macdBlkServiceObj.confirmBlockDate);
			sf.seleU.wait(2000);
			reportStatusPass("Confirm Block Date Button is clicked", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 18.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Edit On Block Selection .
	 * @throws Exception 
	 * 
	 */
	public void clickEditOnBlockSelection(String text) throws Exception {
		try {
			sf.seleU.hardwait(3000);
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(),' " +text+ "')]/../following-sibling::div//div[contains(@class,'text-edit')]"));
			sf.seleU.clickElementByJSE(ele);
			sf.seleU.hardwait(3000);
			reportStatusPass(methodName +" Click Edit Successfully.." +text, true, true);
		}
		catch (Exception e) {
			reportStatusFail("Unable to edit Block Selection.. ", e);
			e.printStackTrace();
		}

	}
}
