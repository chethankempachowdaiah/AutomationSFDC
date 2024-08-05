package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date : 13/jan/2020
 * 
 *         SFDC Create Billing Account Page
 *
 */
public class SFDC_CreateBillingAccount_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;

	public SFDC_CreateBillingAccount_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 *                     1-Enter Parent Account if needed
	 * 
	 *                     2- Enter Billing Account Name
	 * 
	 *                     3- Enter Billing Account Number
	 * 
	 *                     4- Enter Billing Account Source
	 * 
	 *                     5- Click on Next Button
	 * 
	 * 
	 */
	public void enterBillingAccountInfo() throws IOException {
		try {
			String methodName = "SFDC_Create Billing Account@: ";

			// Enter Parent Account if needed
			if (!sf.seleU.isElementPresent(By.xpath("//strong[contains(.,'Parent Account')]/parent::p"))) {

				sf.seleU.enterText(sf.cbia.billing_ChooseParentAccountInput, Global.dataInput.parentAccountName);
				sf.seleU.hardwait(1000);
				sf.seleU.enterText(sf.cbia.billing_ChooseParentAccountInput, Keys.ARROW_DOWN);
				sf.seleU.enterText(sf.cbia.billing_ChooseParentAccountInput, Keys.ENTER);

				reportStatusPass(methodName + " Entered Parent Account for Billing Account  as "
						+ Global.dataInput.parentAccountName, true, false);
			}

			// Enter Billing Account Name
			sf.seleU.enterText(sf.cbia.billingAccountNameInput, Global.dataInput.billingAccountName);
			reportStatusPass(methodName + " Entered Billing Account name as " + Global.dataInput.billingAccountName, true, false);
			Global.dataInput.tempbillingAccountName = Global.dataInput.billingAccountName;
			
			// Enter Billing Account Number
			sf.seleU.enterText(sf.cbia.billingAccountNumberInput, Global.dataInput.billingAccountNumber);
			reportStatusPass(methodName + " Entered Billing Account Number as " + Global.dataInput.billingAccountNumber,
					true, false);

			// Enter Billing Account Source
			sf.seleU.selectTextFromDropDown(sf.cbia.billingAccountSourceInput, Global.dataInput.billingAccountSource);
			reportStatusPass(methodName + " Entered Billing Account Source as " + Global.dataInput.billingAccountSource,
					true, false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cbia.cBiA_CreateBillingAccount_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create Billing Account page", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Billing Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}
	
	public void enterNewBillingAccountInfo() throws IOException {
		try {
			String methodName = "SFDC_Create Billing Account@: ";

			// Enter Parent Account if needed
			if (!sf.seleU.isElementPresent(By.xpath("//strong[contains(.,'Parent Account')]/parent::p"))) {

				sf.seleU.enterText(sf.cbia.billing_ChooseParentAccountInput, Global.dataInput.businessAccountName);
				sf.seleU.hardwait(1000);
				sf.seleU.enterText(sf.cbia.billing_ChooseParentAccountInput, Keys.ARROW_DOWN);
				sf.seleU.enterText(sf.cbia.billing_ChooseParentAccountInput, Keys.ENTER);

				reportStatusPass(methodName + " Entered Parent Account for Billing Account  as "
						+ Global.dataInput.businessAccountName, true, false);
			}

			// Enter Billing Account Name
			sf.seleU.enterText(sf.cbia.billingAccountNameInput, Global.dataInput.billingAccountName);
			reportStatusPass(methodName + " Entered Billing Account name as " + Global.dataInput.billingAccountName, true,
					false);
			
			// Enter Billing Account Number
			sf.seleU.enterText(sf.cbia.billingAccountNumberInput, Global.dataInput.billingAccountNumber);
			reportStatusPass(methodName + " Entered Billing Account Number as " + Global.dataInput.billingAccountNumber,
					true, false);

			// Enter Billing Account Source
			sf.seleU.selectTextFromDropDown(sf.cbia.billingAccountSourceInput, Global.dataInput.billingAccountSource);
			reportStatusPass(methodName + " Entered Billing Account Source as " + Global.dataInput.billingAccountSource,
					true, false);

			// Click on Next Button
			sf.seleU.clickElementByJSE(sf.cbia.cBiA_CreateBillingAccount_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on create Billing Account page", true, false);

			sf.seleU.hardwait(5000);

		} catch (Throwable e) {
			reportStatusFail(" Entering details for Billing Account Creation is Unsuccessful", e);
			e.printStackTrace();
		}
	}


	/**
	 * @throws IOException
	 * 
	 *                     1- Verify Billing Account Created Successfully
	 * 
	 *                     2- Click on next button
	 */
	public void verifyBillingAccountCreated() throws IOException 
	{
		try 
		{
			String methodName = "SFDC_Create Billing Account@:  ";
			// 1- Verify Billing Account Created Successfully
			if (sf.seleU.getTextFromWebElement(sf.cbia.billingAccountCreatedText).contains("Billing Account " + Global.dataInput.billingAccountName + " created.")) 
			{
				reportStatusPass(methodName + " Billing Account :" + Global.dataInput.billingAccountName + "created successfully", true, true);
			} 
			else 
			{
				reportStatusFail(" Creating Biling Account is Unsuccessful ", true);
			}

			// 2- Click on next button
			sf.seleU.clickElementByJSE(sf.cbia.cBiA_BillingAccountConfirmation_nextBtn);
			reportStatusPass(methodName + " Clicked on Next Button on Billing Account Confirmation page", true, false);
			sf.seleU.hardwait(6000);
		} 
		catch (Throwable e) 
		{
			reportStatusFail(" Creating Billing Account is Unsuccessful", e);
			e.printStackTrace();
		}
	}	
}
