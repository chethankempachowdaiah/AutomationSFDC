package sfdc.pages.macd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_ReviewSummaryScreen_Page extends MyListener{

	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;
	
	public MACD_ReviewSummaryScreen_Page() {
		sf = new SFDC_AllPageObjects();
	}
	
	/**
	 * Method developed on @Date: 06.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to see Telephone Number and User-name on Summary page
	 * @throws Exception 
	 * 
	 */
	public void verify_UserName_CTN(String name,String ctn) throws Exception {
		try {
			
			methodName = "Review Order Summary Page@: ";
		//WebElement user = driver.findElement(By.xpath("//label[normalize-space()='" + name+ "']"));
		//WebElement phone = driver.findElement(By.xpath("//span[normalize-space()='" +ctn+ "']"));
		
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.wirelessLineHeader));
		
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.username).equalsIgnoreCase(name) && sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.phone).equalsIgnoreCase(ctn)) {
			reportStatusPass(methodName+ " User is able to see the username "+ name+ " And Telephone Number " +ctn+ " on Review Summary Screen",true, true);
		}
		else {
			reportStatusFail("User is unable to see the Username and Telephone Number on Review Summary Screen",false);
		}
		}catch(Exception e) {
			reportStatusFail("Error is getting while seeing Telephone no and Username", e);
			e.printStackTrace();
		}
		}

	/**
	 * Method developed on @Date: 06.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to see Pending Blocks On this Line (Block,Block Reason,Block Date)
	 * @throws Exception 
	 * 
	 */
	public void verify_pendingBlksOnThisLine(String blk,String reason,String date) throws Exception {
		try {
			
			methodName = "Review Order Summary Page@: ";
		WebElement blkCtn = driver.findElement(By.xpath("//span[contains(text(),'" +blk+ "')]"));
		WebElement blkReason = driver.findElement(By.xpath("//span[contains(text(),'" +reason+ "')]"));
		WebElement blkDate = driver.findElement(By.xpath("//span[contains(text(),'" +date+ "')]"));
		
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.pendingBlks));
		
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.block).equalsIgnoreCase("Block") && sf.seleU.getTextFromWebElement(blkCtn).equalsIgnoreCase(blk)) {
			reportStatusPass(methodName+ " User is able to see the Block "+ blk+ " on Review Summary Screen",true,true);
		}
		else {
			reportStatusFail("User is unable to see the Block on Review Summary Screen",false);
		}
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.blockReason).equalsIgnoreCase("Block Reason") && sf.seleU.getTextFromWebElement(blkReason).equalsIgnoreCase(reason)) {
			reportStatusPass(methodName+ " User is able to see the Block Reason "+ reason+ " on Review Summary Screen",true, true);
		}
		else {
			reportStatusFail("User is unable to see the Block Reason on Review Summary Screen",false);
		}
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.blockDate).equalsIgnoreCase("Block Date") && sf.seleU.getTextFromWebElement(blkDate).equalsIgnoreCase(date)) {
			reportStatusPass(methodName+ " User is able to see the Block Date "+ date+ " on Review Summary Screen",true,true);
		}
		else {
			reportStatusFail("User is unable to see the Block Date on Review Summary Screen",false);
		}
		}catch(Exception e) {
			reportStatusFail("Error is getting while seeing Pending Blocks On this Line", e);
			e.printStackTrace();
		}
		}

	/**
	 * Method developed on @Date: 05.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Proceed Button in Review Order Summary Page
	 * @throws Exception 
	 * 
	 */

	public void click_Proceed() throws Exception {

		try {
			methodName = "Review Order Summary Page@: ";
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.proceed));
			sf.seleU.clickElementByJSE(sf.macdReviewSumScreenObj.proceed);
			sf.seleU.wait(2000);
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.title));
			reportStatusPass(methodName+ "Proceed Button is clicked and User lands on Order Confirmation Screen", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 09.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Edit Button On Pending Blocks on this Line in Review Order Summary Page
	 * @throws Exception 
	 * 
	 */

	public void click_EditOnPendingBlksOnThisLine() throws Exception {

		try {
			methodName = "Review Order Summary Page@: ";
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.clickEdit));
			sf.seleU.clickElementByJSE(sf.macdReviewSumScreenObj.clickEdit);
			sf.seleU.wait(2000);
			reportStatusPass(methodName+ "Edit Button is clicked and User Lands on Step 1 of 3 for Block Type Selection", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Method developed on @Date: 10.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to verify Order Confirmation Screen, Choose another number button and Go to manage account button
	 * @throws Exception 
	 * 
	 */
	public void verify_OrderConfirmationScreenWithTwoButtons(String btn1,String btn2) throws Exception {
		try {
			methodName = " Order confirmation Screen@: ";
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.title));
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.detail));
			sf.seleU.wait(2000);
			reportStatusPass(methodName+ "User is able to see the Order Confirmation Screen", true, true);
			if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.chooseAnotherNo).equalsIgnoreCase("Choose another number") && sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.goToMngAcc).equalsIgnoreCase("Go to manage account")) {
				reportStatusPass(methodName+ " User is able to see the  "+ btn1+ " and " + btn2 +" on Order Confirmation Screen",true,true);
			}
			else {
				reportStatusFail(methodName+"User is not able to see buttons on Order Confirmation Screen",false);
			}	
			
		} catch (Exception e) {
			reportStatusFail("Error is validating on Order Confirmation Screen", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 10.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Go To Manage Account Button On Order Confirmation Screen
	 * @throws Exception 
	 * 
	 */
     public void click_GoToManageAccBtn() throws Exception {
      try {
			methodName = "Order confirmation Screen@:  ";
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.goToMngAcc));
			sf.seleU.clickElementByJSE(sf.macdReviewSumScreenObj.goToMngAcc);
			sf.seleU.wait(2000);
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.accManagementObj.accManagementHeader));
			reportStatusPass(methodName+ "Go To Manage Account Button is clicked and User Lands on Account Management Screen", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating on Order Confirmation Screen", e);
			e.printStackTrace();
		}
	}
     
     /**
 	 * Method developed on @Date: 10.05.2022 by @author Priyanka Tawade
 	 * 
 	 * Method to click Choose Another Number Button On Order Confirmation Screen
 	 * @throws Exception 
 	 * 
 	 */
      public void click_ChooseAnotherNoBtn() throws Exception {
       try {
 			methodName = "Order confirmation Screen@:  ";
 			sf.seleU.waitTillLoading();
 			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.macdReviewSumScreenObj.chooseAnotherNo));
 			sf.seleU.clickElementByJSE(sf.macdReviewSumScreenObj.chooseAnotherNo);
 			sf.seleU.wait(2000);
 			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.accManagementObj.selectPhoneNumberHeader));
 			reportStatusPass(methodName+ "Choose Another Number Button is clicked and User Lands on CTN Selection Screen", true, true);
 		} catch (Exception e) {
 			reportStatusFail("Error is validating on Order Confirmation Screen", e);
 			e.printStackTrace();
 		}
 	}
}
