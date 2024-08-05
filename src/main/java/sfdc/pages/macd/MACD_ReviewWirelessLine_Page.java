package sfdc.pages.macd;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.framework.base.MyListener;
import com.framework.utilities.DateTimeUtilities;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class MACD_ReviewWirelessLine_Page extends MyListener {
	public static SFDC_AllPageObjects sf;
	public static String methodName;
	public static int quntityCount;
	public static SFDC_AllPages sfdc;

	public MACD_ReviewWirelessLine_Page() {
		sf = new SFDC_AllPageObjects();
	}
	/**
	 * Method developed on @Date: 15.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Add add-ons button. 
	 * @throws Exception 
	 * 
	 */
	public void select_Add_addonsBtn() throws Exception {
		try {
			methodName = "Rogers Wireless line Page@: ";
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.Add_addOnBtn);
			sf.seleU.waitForLoading();
			sf.seleU.hardwait(2000);
			sf.seleU.waitForLoading();
			reportStatusPass(methodName+ " Add add-ons button has clicked. ",true, true);
			sf.seleU.waitForLoading();
		}catch(Exception e) {
			reportStatusFail("Error in clicking on Add add-ons button on Rogers Wireless line Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 15.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on Remove add-ons button. 
	 * @throws Exception 
	 * 
	 */
	public void select_Remove_addonsBtn() throws Exception {
		try {
			methodName = "Rogers Wireless line Page@: ";
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.removeExistingAddOn);
			sf.seleU.waitTillLoading();
			sf.seleU.hardwait(4000);
			Assert.assertTrue(sf.seleU.isElementDisplayed(sf.macdRemoveAddOnObj.removeHeader));
			reportStatusPass(methodName+ " Remove add-ons button has clicked. ",true, true);
		}catch(Exception e) {
			reportStatusFail("Error in clicking on Remove add-ons button on Rogers Wireless line Page. ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Method developed on @Date: 23.03.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to validate product on Rogers Wireless Line Page. 
	 * @throws Exception 
	 * 
	 */
	public void validate_Product(String product) throws Exception {
		try {
			methodName = "Rogers Wireless line Page@: ";
			for(WebElement ele: sf.reviewWALineObj.productList) {
				String str = sf.seleU.getTextFromWebElement(ele);
				if(str!=null && str.contains(product)) {
					reportStatusPass(methodName+ product+" Product is present. ",true, true);
					break;
				}
			}
		}catch(Exception e) {
			reportStatusFail("Error in validating product on Rogers Wireless line Page. ", e);
			e.printStackTrace();
		}
	}	

	/**
	 * Method developed on @Date: 07.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate device protection on Rogers Wireless Line Page. 
	 * @throws Exception 
	 * 
	 */
	public void validate_DeviceProduct_InWirelessAddOnSection(String product) throws Exception {
		try {
			methodName = "Device Product In Wireless Add On Section@: ";

			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.deviceProtection_InWirelssAddOn) && 
					sf.seleU.getTextFromWebElement(sf.reviewWALineObj.deviceProtection_InWirelssAddOn).contains(product)) {
				reportStatusPass(methodName+ product+" Product is present in the wireless add on section, not in seperate section ",true, true);				
			}

		}catch(Exception e) {
			reportStatusFail("Error in validating device protection product on Rogers Wireless line Page. ", e);
			e.printStackTrace();
		}
	}	


	/**
	 * Method developed on @Date: 06.04.2022 by @author Priyanka Tawade
	 *
	 * Method to verify message bar on the top of Addons summary page that changes have been made successfully 
	 * @throws Exception
	 *
	 */
	public void verify_addonsMessageBar() throws Exception{
		try {
			methodName = "Rogers Wireless line Page@: ";

			//WebElement msg = driver.findElement(By.xpath("//span[normalize-space()='You have successfully made changes to your add-ons']"));
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.msgBarOnAddonsSummary)) {
				sf.seleU.getTextFromWebElement(sf.reviewWALineObj.msgBarOnAddonsSummary);

				reportStatusPass("User can able to see msg Bar that You have successfully made changes to your Addons" ,true, true);
			}
			else {
				reportStatusFail("User not able to see the msg bar",false);
			}

		}catch (Exception e) {
			reportStatusFail("Error in validating.. ", e);
			e.printStackTrace();

		}
	}

	/**
	 * Method developed on @Date: 06.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate Proceed to order summary button
	 * @throws Exception 
	 * 
	 */
	public void validateSuccessfullAddOnChangesMessage() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Successfull AddOn Changes Message@: ";
			sf.seleU.hardwait(3000);
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.addOnChangesMessage) && 
					sf.seleU.isElementDisplayed(sf.reviewWALineObj.addOnChangesSubMessage)) {			
				reportStatusPass(methodName + " Successfully Validated Quick Add On Message Changes at the Review Wireless Page ", true, true);
			}
			else
				reportStatusFail(methodName + " Not able to validate addon message at the Review Wireline Page after adding the Add On  ", true);
		} catch (Exception e) {
			reportStatusFail("Error in Validating the Quick Add On Message Check ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 06.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate "Changes to your Wireless add-ons table" by product, action and Date, Price
	 * @throws Exception 
	 * 
	 */
	public void validate_ChangesToWirelessAddOns(String[] product, String[] action, String[] Price, String cancelChanges) throws Exception {
		try {

			boolean found = false; int count =0;
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.wireLessAddOnChangesText)) {
				sf.seleU.ScrolltoElementPageCenter(sf.reviewWALineObj.wireLessAddOnChangesText);

				outerloop:
					for(int i = 0; i< sf.reviewWALineObj.wireLessAddOnChangesProduct.size(); i++) {
						for(int k=0; k < product.length; k++) {
							
							//i. Validate the Product name, Price , date
							if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProduct.get(i)).replaceAll("[-+^]*", "").equals(product[k]) &&  
									sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductAction.get(i)).equals(action[k]) &&
									sf.seleU.isElementDisplayed(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i)) &&
									sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductPrice.get(i)).replaceAll("/mo", "").replaceAll("\\$", "").replaceAll("[a-zA-Z]", "").trim().equals(Price[k])) {	

								System.out.println(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i)));
								methodName = "Changes to your Wireless add-ons section@: ";
								reportStatusPass(methodName+ product[k]+" Product is present in the Changes to Wireless AddOns"
										+ " with Action as " + action[k] + " And Effective Date " + 
										sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i)) +
										" with price as " + sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductPrice.get(i)),true, true);

								found = true;
								count++;

								//ii. Validate the Cancel Scenario, if the user wants to cancel


								if(!cancelChanges.equals("")) {

									if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductCancelChanges.get(i)).trim().equals(cancelChanges))  {
										sf.seleU.clickElementByJSE(sf.reviewWALineObj.wireLessAddOnChangesProductCancelChanges.get(i));	
										reportStatusPass(methodName+ product+" Successfuly clicked on the cancel change button", true, true);
										// Validation after clicking on the cancel changes, product shouldn't be on the list 
										sf.seleU.wait(4000);
										if(sf.reviewWALineObj.wireLessAddOnChangesProductCancelChanges.size() >= 1) {
											System.out.println("List is having more then 1 product");
											for(int j = 0; j< sf.reviewWALineObj.wireLessAddOnChangesProduct.size(); j++) {
												if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProduct.get(j)).equals(product)) {
													reportStatusFail(methodName+ product+" Successfuly not clicked on the cancel change button and product"
															+ "disappeared ", true);
												} 
											}
										} else {
											reportStatusPass(methodName+ product+" Product got successfully removed", true, true);
										}
									}
								}
								break; // This break is from the outerfor loop
							}					
						}					
					}
				if(found && count == product.length) {
					reportStatusPass("All the Product is found in the list with total product in the list as " + count, true, false);
				} else {
					reportStatusFail("All Product is not found in the list,", true);
				}
			} else {
				reportStatusFail("Changes to your Wireless add-ons: text is not displayed,", true);
			}
		}catch(Exception e) {
			reportStatusFail("Error in validating product on Rogers Wireless line Page. ", e);
			e.printStackTrace();
		}
	}	

	/**
	 * Method developed on @Date: 20.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to effective date should be matched as selected from the previous options
	 * @throws Exception 
	 * 
	 */
	public void validateEffectiveDateInChangeToWirelessSection(String product[]) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Validate_EffectiveDateInChange_ToWirelessSection@: ";
			sf.seleU.hardwait(3000);
			String dateOption[] = waData.WACC_Select_Effective_DateOption;
			String expectedDate =""; String expectedDateDayFormat = ""; String[] expectedDateFormat = null;

			for(int k=0; k < product.length; k++) {
				for(int i = 0; i< sf.reviewWALineObj.wireLessAddOnChangesProduct.size(); i++) {

					if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProduct.get(i)).replaceAll("[-+^]*", "").equals(product[k])) {
						String actualDate = sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i));

						if(dateOption[k].contains("Today:")) {
							expectedDate =	DateTimeUtilities.currentSystemDate("dd-MMM-yyyy");
							// adding the date with the product name to validate the effective date in PDF
							waData.WACC_Expected_Effective_DateOption.put(product[k],DateTimeUtilities.currentSystemDate("dd/MM/yyyy"));
						} else if(dateOption[k].contains("Choose a date")) {
							expectedDate = DateTimeUtilities.currentSystemDatePlus(7,"dd-MMM-yyyy");
							waData.WACC_Expected_Effective_DateOption.put(product[k],DateTimeUtilities.currentSystemDatePlus(7,"dd/MM/yyyy"));
						}
						System.out.println(waData.WACC_Expected_Effective_DateOption);

						// Format the day, remove 0 from 05 if it's 5th May
						expectedDateFormat = expectedDate.split("-");
						expectedDateDayFormat = expectedDate.split("-")[0].trim();
						if(expectedDateDayFormat.contains("0")){
							// zeros from a string
							String regex = "^0+(?!$)";					 
							// Replaces the matched
							expectedDateDayFormat = expectedDateDayFormat.replaceAll(regex, "");
							expectedDate = expectedDateDayFormat + "-" + expectedDateFormat[1] + "-" + expectedDateFormat[2];
							System.out.println(expectedDate);			
						}
						if(actualDate.trim().equals(expectedDate.trim())) 			
							reportStatusPass(methodName + "For Product " + product[k]+ " Date is matched with expected date as " + expectedDate, true, true);
						else
							reportStatusFail(methodName + "For product " + product[k] + " Date is not matched as expected date is " + expectedDate + " and actual date is " +actualDate, true);
						break;
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error in validating the effective date ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 21.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to change the effective date from the changes to the wireless section
	 * @throws Exception 
	 * 
	 */
	public void changeEffectiveDateInChangeToWirelessSection(String product[]) throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Validate_EffectiveDateInChange_ToWirelessSection@: ";
			sf.seleU.hardwait(3000);
			String dateOption[] = waData.WACC_Change_Effective_DateOption;
			String expectedDate ="";

			for(int k=0; k < product.length; k++) {
				for(int i = 0; i< sf.reviewWALineObj.wireLessAddOnChangesProduct.size(); i++) {

					if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProduct.get(i)).replaceAll("[-+^]*", "").equals(product[k])) {
						String actualDate = sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i));

						if(dateOption[k].contains("Today:")) {
							expectedDate =	DateTimeUtilities.currentSystemDate("dd-MMM-yyyy");
							// adding the date with the product name to validate the effective date in PDF
							waData.WACC_Expected_Effective_DateOption.put(product[k],DateTimeUtilities.currentSystemDate("dd/MM/yyyy"));
						} else if(dateOption[k].contains("Choose a date")) {
							expectedDate = DateTimeUtilities.currentSystemDatePlus(7,"dd-MMM-yyyy");
							waData.WACC_Expected_Effective_DateOption.put(product[k],DateTimeUtilities.currentSystemDatePlus(7,"dd/MM/yyyy"));
						}
						sf.seleU.hardwait(2000);

						// After changing the effective date again validating with the expected date
						sf.seleU.clearAndEnterText(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i), expectedDate);
						actualDate = sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductEffectiveDate.get(i));

						if(actualDate.trim().equals(expectedDate)) 			
							reportStatusPass(methodName + "For Product " + product[k]+ " Date got changed and matched with expected date as " + expectedDate, true, true);
						else
							reportStatusFail(methodName + "For product " + product[k] + " Date didn't got changed and is not matched as expected date is " + expectedDate + " and actual date is " +actualDate, true);
						break;
					}
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error in validating the effective date ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 06.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate "Changes to your Wireless add-ons table" by product, action and Date, Price
	 * @throws Exception 
	 * 
	 */
	public void validate_ChangesToWirelessAddOns_ForDPAndAppleCareTogether(String[] product, String[] action, String[] Price, String cancelChanges[]) throws Exception {
		try {
			methodName = "Changes to your Wireless add-ons section@: ";

			boolean found = false; int count =0; int exist = 0;int index = 0;
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.wireLessAddOnChangesText)) {
				sf.seleU.ScrolltoElementPageCenter(sf.reviewWALineObj.wireLessAddOnChangesText);
				index = sf.reviewWALineObj.wireLessAddOnChangesProductPrice.size()-1;
				for(int i = 0; i< sf.reviewWALineObj.wireLessAddOnChangesBothDPProductTogether.size(); i++) {
					innerloop:
						for(int k=0; k < product.length; k++) {
							if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesBothDPProductTogether.get(i)).replaceAll("[-+^]*", "").equals(product[k]) &&  
									sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesBothDPActionTogether.get(i)).equals(action[k]) &&
									sf.seleU.isElementDisplayed(sf.reviewWALineObj.wireLessAddOnChangesEffectiveDateTogether.get(0)) &&
									sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductPrice.get(index)).replaceAll("/mo", "").replaceAll("\\$", "").replaceAll("[a-zA-Z]", "").trim().equals(Price[0])) {	


								reportStatusPass(methodName+ product[k]+" Product is present in the Changes to Wireless AddOns"
										+ " with Action as " + action[k] + " And Effective Date " + 
										sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesEffectiveDateTogether.get(0)),true, true);

								found = true;
								count++;

								//ii. Validate the Cancel Scenario 
								if(!cancelChanges[k].equals("")) {

									if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesProductCancelChanges.get(index)).trim().equals(cancelChanges[k]))  {
										sf.seleU.clickElementByJSE(sf.reviewWALineObj.wireLessAddOnChangesProductCancelChanges.get(index));	
										reportStatusPass(product[k]+" Successfuly clicked on the cancel change button", true, true);
										// Validation after clicking on the cancel changes, product shouldn't be on the list 
										sf.seleU.wait(4000);
										if(sf.reviewWALineObj.wireLessAddOnChangesProductCancelChanges.size() >= 1) {
											System.out.println("List is having more then 1 product");
											for(int j = 0; j< sf.reviewWALineObj.wireLessAddOnChangesBothDPProductTogether.size(); j++) {
												if(sf.seleU.getTextFromWebElement(sf.reviewWALineObj.wireLessAddOnChangesBothDPProductTogether.get(j)).equals(product[k])) {
													reportStatusFail(methodName+ product+" Successfuly not clicked on the cancel change button and product"
															+ "disappeared ", true);
												} 
											}
										} else {
											reportStatusPass(methodName+ product[k]+" Product got successfully removed", true, true);
										}
									}
								}
								break innerloop; // This break is from inner for loop
							}					
						}					
				}
				if(found && count == product.length) {
					reportStatusPass("All the Product is found in the list with total product in the list as " + count, true, false);
				} else {
					reportStatusFail("All Product is not found in the list,", true);
				}
			} else {
				reportStatusFail("Changes to your Wireless add-ons: text is not displayed,", true);
			}
		}catch(Exception e) {
			reportStatusFail("Error in validating product on Rogers Wireless line Page. ", e);
			e.printStackTrace();
		}
	}	


	/**
	 * Method developed on @Date: 06.04.2022 by @author Pankaj Agarwal
	 * 
	 * Method to validate Proceed to order summary button
	 * @throws Exception 
	 * 
	 */
	public void validateAndClickProccedToOrderSummary() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Proceed to Order Summary button@: ";
			sf.seleU.hardwait(2000);
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.proceedToOrderSummaryButton) && sf.seleU.isElementEnabled(sf.reviewWALineObj.proceedToOrderSummaryButton)) {
				sf.seleU.clickElementByJSE(sf.reviewWALineObj.proceedToOrderSummaryButton);
				reportStatusPass(methodName + " Clicked on the proceed To Order Summary Button ", true, true);
			}
			else
				reportStatusFail(methodName + " Not able to Click on the proceed To Order Summary Button ", true);
		} catch (Exception e) {
			reportStatusFail("Error in Continue to Date Selection button on Add Wireless add-ons Page. ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 11.04.2022 by @author viswas reddy
	 * 
	 * Method to check change wireless plan button is present in review price plan page.
	 * @throws Exception 
	 * 
	 */
	public void checkChangeWirelessPlanBtnPresent() throws Exception {
		try {
			methodName = "Review Price Plan change@: ";
			sf.seleU.hardwait(2000);
			if(sf.reviewWALineObj.changeWirelessPlanBtn.isDisplayed()) {
				reportStatusPass(methodName+"Change wireless plan button is displayed", true, true);
			}
		}catch (Exception e) {
			reportStatusFail("Error in Checking change wireless paln button present", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 11.04.2022 by @author viswas reddy
	 * 
	 * Method to click previous button.
	 * @throws Exception 
	 * 
	 */
	public void clickPreviousButton() throws Exception {
		try {
			methodName = "Review wireless line@: ";
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.previousBtn);
			sf.seleU.hardwait(2000);
			reportStatusPass("Successfully clicked on previous button", true, true);
		}catch (Exception e) {
			reportStatusFail("Error in Clicking previous button", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 11.04.2022 by @author viswas reddy
	 * 
	 * Method to check CTN is selected after clicking previous button.
	 * @throws Exception 
	 * 
	 */
	public void checkCTNselectedAfterClickPrevious(String phnNum) throws Exception {
		try {
			methodName = "Check CTN selected after clicking previous@: ";
			sf.seleU.hardwait(2000);
			for(int i=0; i<sf.accManagementObj.phoneNumberList.size(); i++) {
				if(phnNum.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.accManagementObj.phoneNumberList.get(i)))) {
					sf.seleU.isElementSelected(sf.accManagementObj.selectNumberRadioBtn.get(i));
					reportStatusPass(methodName+"CTN is selected", true, true);
				}
			}
		}catch (Exception e) {
			reportStatusFail("Error in Checking CTN selected after clicking previous", e);
			e.printStackTrace();
		}
	}



	/**
	 * Method developed on @Date: 12.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Remove Current Addons Button
	 * @throws Exception 
	 * 
	 */

	public void click_RemoveCurrentAddOns() throws Exception {

		try {
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.reviewWALineObj.removeCurrentAddOnsBtn));
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.removeCurrentAddOnsBtn);
			sf.seleU.wait(2000);
			reportStatusPass(methodName + "Remove current AddOns button is clicked and User can start Remove addons flow", true, true);
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 12.04.2022 by @author Priyanka Tawade
	 * 
	 * Method to click Discard Changes.
	 * @throws Exception 
	 * 
	 */

	public void click_discardChanges() throws Exception {

		try {

			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.reviewWALineObj.discardChangesBtn));
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.discardChangesBtn);
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.discardChangesHeader)) {
				sf.seleU.clickElementByJSE(sf.reviewWALineObj.discardChangesBtnOnPopUp);
				sf.seleU.wait(2000);
				reportStatusPass(methodName + "Discard Changes Button is clicked", true, true);
			}
		} catch (Exception e) {
			reportStatusFail("Error is validating ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 20.04.2022 by @author Viswas Reddy
	 * 
	 * Method to click Discard Changes and cancel it.
	 * @throws Exception 
	 * 
	 */

	public void click_discardChangesAndCancel() throws Exception {

		try {
			sf.seleU.hardwait(2000);
			sf.seleU.waitTillLoading();
			Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.reviewWALineObj.discardChangesBtn));
			sf.seleU.clickElementByJSE(sf.reviewWALineObj.discardChangesBtn);
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.discardChangesHeader)) {
				sf.seleU.clickElementByJSE(sf.reviewWALineObj.discardChangesCancel);
				sf.seleU.wait(2000);
				if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.discardChangesBtn)) {
					reportStatusPass(methodName + "Discard Changes Button is clicked and canceled it", true, true);
				}
			}
		} catch (Exception e) {
			reportStatusFail("Error in clicking discard and cacelling it", e);
			e.printStackTrace();
		}
	}

	/**
	 * Method developed on @Date: 16.05.2022 by @author Priyanka Tawade
	 * 
	 * Method to see Initial CTN block Summary (Block,Block Reason,Block Date)
	 * @throws Exception 
	 * 
	 */
	public void verify_InitialCTNBlockSummary_RemoveBlock(String blk,String reason,String date) throws Exception {
		try {
			
			methodName = "Review Wireless Line Page@: ";
			sf.seleU.waitTillLoading();	
		WebElement blkCtn = driver.findElement(By.xpath("//span[contains(text(),'" +blk+ "')]"));
		WebElement blkReason = driver.findElement(By.xpath("//span[contains(text(),'" +reason+ "')]"));
		WebElement blkDate = driver.findElement(By.xpath("//span[contains(text(),'" +date+ "')]"));
		sf.seleU.hardwait(2000);
		sf.seleU.waitTillLoading();
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.reviewWALineObj.ctnBlockHeader));
		
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.block).equalsIgnoreCase("Block Type") && sf.seleU.getTextFromWebElement(blkCtn).equalsIgnoreCase(blk)) {
			reportStatusPass(methodName+ " User is able to see the Block Type"+ blk+" on Wireless Line Page" ,true,true);
		}
		else {
			reportStatusFail("User is unable to see the Block on Wireless Line Page",false);
		}
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.blockReason).equalsIgnoreCase("Block Reason") && sf.seleU.getTextFromWebElement(blkReason).equalsIgnoreCase(reason)) {
			reportStatusPass(methodName+ " User is able to see the Block Reason "+ reason+ " on Wireless Line Page",true, true);
		}
		else {
			reportStatusFail("User is unable to see the Block Reason on Wireless Line Page",false);
		}
		if(sf.seleU.getTextFromWebElement(sf.macdReviewSumScreenObj.blockDate).equalsIgnoreCase("Block Date") && sf.seleU.getTextFromWebElement(blkDate).equalsIgnoreCase(date)) {
			reportStatusPass(methodName+ " User is able to see the Block Date "+ date+ " on Wireless Line Page",true,true);
		}
		else {
			reportStatusFail("User is unable to see the Block Date on Wireless Line Page",false);
		}
		Assert.assertEquals(true, sf.seleU.isElementDisplayed(sf.reviewWALineObj.removeBlk));
		reportStatusPass(methodName+" User is able to see Remove Block button",true,true);
		
		}catch(Exception e) {
			reportStatusFail("Error is getting while seeing Initial CTN Block Summary", e);
			e.printStackTrace();
		}
		}
	/**
	 * Method developed on @Date: 23.05.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to click on MACD button on Billing account page. 
	 * @throws Exception 
	 * 
	 */
	public void validateEffectiveDateCalendarIcon_AppleCare() throws Exception {
		// TODO Auto-generated method stub
		try {
			methodName = "Validate_EffectiveDateInChange_ToWirelessSection@: ";
			sf.seleU.waitForLoading();
			if(sf.seleU.isElementDisplayed(sf.reviewWALineObj.appleCareEffectiveDateCalendarIcon) && !sf.seleU.isClickable(sf.reviewWALineObj.appleCareEffectiveDateCalendarIcon)) {
				reportStatusPass(methodName +"Effective date Calendar has disabled and not clickable.", true, true);
			}
			else
				reportStatusFail("Effective date Calendar icon is not present/disabled on Review Order.", true);
		}catch(Exception e) {
			reportStatusFail("Error in validating Effective date Calendar icon is disabled & not clickable on Review Order Page.", e);
			e.printStackTrace();
		}
	}
}


