package sfdc.pages.sales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Nandan.More, Date : 24/June/2021
 * 
 *         SFDC R4B Quote page
 */
public class SFDC_R4B_Quote_Approval_Page_Layout_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	static String methodName = "SFDC_R4BQuoteApproval@: ";

	Boolean result=true,result2=true;

	public SFDC_R4B_Quote_Approval_Page_Layout_Page() {
		sf = new SFDC_AllPageObjects();

	}
	/**
	 * @throws IOException
	 * 
	 * 				Select the R4B Approval Field Text
	 * 
	 *  			Find the all web elements from Quote Details and Account Details quick layout
	 *  
	 *  			Store all web elements text values in List
	 *  
	 *  			Check on the  approval page shouldn't show Quote Name under Quote Detail section and Account Name under Account section 
	 * 
	 *                  
	 * 
	 */
	public void seletAndOpendR4BQuoteApproval() throws IOException {
		try {
			//Click on Drop down list
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.selectListViewDropdown);
			sf.seleU.wait(5000);

			//Click on All
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.allQuotes);
			sf.seleU.wait(5000);

			//Select One/Open one record/ hit first record 
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.quoteNumbersAllRows);
			sf.seleU.wait(5000);


			// Verify currently we at R4B Approval Page or not 

			String strR4BApproval = "R4B Approval";
			if(strR4BApproval.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.r4Bquote.r4BHeading)))
				reportStatusPass(methodName +" Heading selected as : " +  sf.seleU.getTextFromWebElement(sf.r4Bquote.r4BHeading) ,
						true, true);

			else
				reportStatusFail("R4B Approval Page is not opened ", true);

			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating R4B Quote Approval fields", e);
			e.printStackTrace();
		}

	}

	/**@author Pankaj Agarwal 20 Jan 2022
	 * @throws IOException
	 * 				  
	 * Approve the Credit/Fraud check 			 
	 * 
	 */
	public void approveR4BQuote() throws IOException {
		try {

			if(!sf.seleU.isElementPresent(sf.r4Bquote.approveButton)) {
				sf.seleU.scrollByCoOrdinates(3);
				sf.seleU.clickElementByJSE(sf.qr.quoteApprovalNameLink);
				reportStatusPass(methodName + " Clicked on Quote Approval Name Link", true, false);
				sf.seleU.hardwait(4000);

				sf.seleU.clickElementByJSE(sf.qr.r4BApprovalRelatedTab);
				sf.seleU.hardwait(4000);
			}
			sf.seleU.clickElementByJSE(sf.r4Bquote.approveButton);
			sf.seleU.switchToActiveElement();
			sf.seleU.clickElementByJSE(sf.r4Bquote.finalApproveButton);
			reportStatusPass(methodName + " Clicked on Final Approve Button", true, false);
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in approving Credit/Fraud checks", e);
			e.printStackTrace();
		}
	}

	/**@author Pankaj Agarwal 20 Jan 2022
	 * @throws IOException
	 * 				  
	 * Verify R4b quote before record type, approval type, approval status, approval time			 
	 * 
	 */
	public static void verifyR4BQuoteDetails_BeforeApproval(String reviewType) throws IOException {
		try {
			sf.seleU.wait(2000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.r4bApprovalDeatils);
			sf.seleU.wait(3000);
			verifyFieldValue("Approval Type", sf.r4Bquote.r4bApprovalType, reviewType);
			verifyFieldValue("Approval Status", sf.r4Bquote.r4bApprovalStatus, sf.dataInput.orderStatusInProgress);
			// if monthly product price is greater is between 500 and less then 1999 
			// then approval time of next 2 hours shld be present else it should not be present
			if(sf.dataInput.fraudCheckStatus.equals("Yes") ) {
				if (sf.seleU.getTextFromWebElement(sf.r4Bquote.r4bApprovalTime) != null){
					reportStatusPass(methodName + " Approval Time for monthly product price > 500 and less the <2000 "
							+ "is present as " + sf.seleU.getTextFromWebElement(sf.r4Bquote.r4bApprovalTime), true, true);
				} else {
					reportStatusFail(methodName + " Approval time is not present ", false);
				} 
			} 
			else if (sf.dataInput.fraudCheckStatus_PriceAbove_2000.equals("Yes")) {
				if (sf.seleU.getTextFromWebElement(sf.r4Bquote.r4bApprovalTime) == null){
					reportStatusPass(methodName + " Approval Time is present as " + sf.seleU.getTextFromWebElement(sf.r4Bquote.r4bApprovalTime), true, true);
				} else {
					reportStatusFail(methodName + " Approval time is present ", false);
				} 
			} 
			else {
				reportStatusFail(methodName + " Not satisfying any condition for fraud check ", false);
			}
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in reviewing r4bquote approval details section", e);
			e.printStackTrace();
		}
	}


	/**@author Chethan K
	 * @throws IOException
	 * 				  
	 * Reject the Credit/Fraud check 			 
	 * 
	 */
	public void rejectR4BQuote() throws IOException {
		try {

			sf.seleU.clickElementByJSE(sf.r4Bquote.rejectButton);
			sf.seleU.switchToActiveElement();
			sf.seleU.clickElementByJSE(sf.r4Bquote.finalRejectButton);
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in Rejecting Credit/Fraud checks", e);
			e.printStackTrace();
		}
	}



	/**@author Chethan K
	 * @throws IOException
	 * 				  
	 * Pass the NFDB check 			 
	 * 
	 */
	public void passNFDB() throws IOException {
		try {
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.NFDBPassButton);
			sf.seleU.hardwait(3000);
			sf.seleU.switchToActiveElement();
			sf.seleU.clickElementByJSE(sf.r4Bquote.confirmNFDBButton);
			sf.seleU.hardwait(3000);
			sf.seleU.refreshPage();
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in Passing the NFDB check", e);
			e.printStackTrace();
		}
	}

	/**@author Chethan K
	 * @throws IOException
	 * 				  
	 * Reject the NFDB check 			 
	 * 
	 */
	public void rejectNFDB() throws IOException {
		try {
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.NFDBRejectButton);
			sf.seleU.hardwait(3000);
			sf.seleU.switchToActiveElement();
			sf.seleU.clickElementByJSE(sf.r4Bquote.confirmNFDBButton);
			sf.seleU.hardwait(3000);
			sf.seleU.refreshPage();
		}
		catch (Throwable e) {
			reportStatusFail(methodName + " Error in Rejecting NFDB check", e);
			e.printStackTrace();
		}
	}


	public void verifyQuoteNameAccName() throws IOException {

		try {

			//Store All web elements text values in List under quote details layout
			sf.seleU.wait(5000);

			String strQuoteName = sf.seleU.getTextFromWebElement(sf.r4Bquote.qd);

			List<String> list = new ArrayList<>();
			list.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.qdAccName));
			list.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.qdOneTimeMargin));
			list.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.qdOneTimeTotal));
			list.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.qdRecurringMargin));
			list.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.qdRTotal));

			//compare each and every value to Quote Name under Quote Details section 
			for(int i=0;i<list.size();i++)
			{
				result =strQuoteName.equalsIgnoreCase(list.get(i));
			}  

			if (result==false) {

				reportStatusPass(methodName + " No Quote Name field is present under Quote Details Section : " +  sf.seleU.getTextFromWebElement(sf.r4Bquote.qd), true, true);
			}
			else
				reportStatusFail("Quotet Name field is present under Quote Details Sectiont ", true);

			sf.seleU.wait(5000);
			//Store All web elements text values in List under quote details layout
			String strAccName = sf.seleU.getTextFromWebElement(sf.r4Bquote.ad);

			List<String> list2 = new ArrayList<>();
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adAutoR1DUNS));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adContact));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adCreditLimit));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adCreditReview));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adDUNS));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adIndustry));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adLastCredit));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adLegalName));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adParentAccount));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adPhone));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adR1DUNS));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adSalesSegment));
			list2.add(sf.seleU.getTextFromWebElement(sf.r4Bquote.adSubIndustry));

			//compare each and every value to Account Name under Account Details section 
			for(int j=0;j<list2.size();j++)
			{
				result2 =strAccName.equalsIgnoreCase(list2.get(j));
			}  

			if (result2==false) {

				reportStatusPass(methodName + " No Account Name field is present under Account Details Section : " +  sf.seleU.getTextFromWebElement(sf.r4Bquote.ad), true, true);
			}
			else
				reportStatusFail("Account Name field is present under Account Details Section ", true);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating R4B Quote Approval field i.e. Account Name and Quote Name Under Quick Layout", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 * 				Select Related Quote Field under details tab
	 * 
	 *  			Click on Edit button around Related Quote Field
	 *  
	 *  			Pop Up window opens
	 *  
	 *  			Verify Cancel and Save Filed in Pop up and Hit Cancel 
	 * 
	 *              Capture Pop up window  
	 * 
	 */
	public void verifyRelatedQuoteField() throws IOException {
		try {

			reportStatusPass(methodName +" Before Hitting the Related Quote Field Button "  ,true, true);
			//Click on Edit button around Related Quote Field
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.editRelatedQuote);
			sf.seleU.wait(5000);

			//Verify Cancel and Save Filed in Pop up and Hit Cancel 
			String strCancel = "Cancel";
			if(strCancel.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.r4Bquote.cancelEdit)))
				reportStatusPass(methodName +" The Related Quote Field is Editable" , true, true);

			else
				reportStatusFail("The Related Quote Field is Not Editable ", true);

			sf.seleU.wait(5000);
			//Hit Cancel as we are not modifying anything  
			sf.seleU.clickElementByJSE(sf.r4Bquote.cancelEdit);
			sf.seleU.wait(5000);


		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating Related Quote field", e);
			e.printStackTrace();
		}

	}


	/**
	 * @throws IOException
	 * 
	 * 				Select the R4B Approval Field Text
	 * 
	 *  			Find the all web elements from Quote Details and Account Details quick layout
	 *  
	 *  			Store all web elements text values in List
	 *  
	 *  			Check on the  approval page shouldn't show Quote Name under Quote Detail section and Account Name under Account section 
	 * 
	 *                  
	 * 
	 */
	public void seletAndOpendR4BQuoteApprovalCurrentQuote() throws IOException {
		try {

			//Click on Drop down list
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.selectListViewDropdown);
			sf.seleU.wait(5000);

			//Click on All
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.allQuotes);
			sf.seleU.wait(5000);

			//Select One/Open one record/ hit first record 
			sf.seleU.wait(5000);
			sf.seleU.clickElementByJSE(sf.r4Bquote.quoteNumbersAllRows);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in validating R4B Quote Approval fields", e);
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 1- Enter Quote Number to be Searched
	 * 
	 * @throws IOException
	 */
	public void searchQuote(String quoteNumber) throws IOException {
		try {
			String methodName = "SFDC_SearchQuote@: ";

			// 1- Enter Quote Number to be Searched
			sf.seleU.clearAndEnterText(sf.home.searchInput, quoteNumber);
			sf.seleU.wait(4000);
			sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
			sf.seleU.wait(5000);
			if (sf.acc.accountsAllRecords.size() > 1 && sf.seleU
					.getTextFromWebElement(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1))
					.equals(quoteNumber)) {
				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(sf.acc.accountsAllRecords.size() - 1));
			}

			else if (sf.acc.accountsAllRecords.size() == 0) {

				// 2- Select My Accounts
				sf.seleU.clickElementByJSE(sf.home.listViewIcon);
				sf.seleU.hardwait(2000);
				sf.seleU.clickElementByJSE(sf.acc.myAccountsOption);
				reportStatusPass(methodName + " Selected My Accounts Option", true, false);

				// 3- Enter Account to be Searched
				sf.seleU.clearAndEnterText(sf.home.searchInput, quoteNumber);
				sf.seleU.hardwait(4000);
				sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
				sf.seleU.hardwait(5000);

				if (sf.acc.accountsAllRecords.size() == 0) {
					// 2- Select My Accounts
					sf.seleU.clickElementByJSE(sf.home.listViewIcon);
					sf.seleU.hardwait(2000);
					sf.seleU.clickElementByJSE(sf.acc.recentlyViewedAccountOption);
					reportStatusPass(methodName + " Selected Recently Viewed Accounts Option", true, false);

					// 3- Enter Account to be Searched
					sf.seleU.clearAndEnterText(sf.home.searchInput, quoteNumber);
					sf.seleU.hardwait(4000);
					sf.seleU.enterText(sf.home.searchInput, Keys.ENTER);
					sf.seleU.hardwait(5000);
				}

				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));

			}

			else {

				sf.seleU.clickElementByJSE(sf.acc.accountsAllRecords.get(0));
			}
			reportStatusPass(methodName + " Found and Clicked on  Quote " + quoteNumber, true, false);

			sf.seleU.wait(9000);
		} catch (

				Throwable e) {
			reportStatusFail(" Searching an quote is Unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**Pankaj Agarwal 20 Jan 222
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {
			// Verify Field value matches the expected result
			sf.seleU.wait(2000);
			if (sf.seleU.getTextFromWebElement(element).trim().contains(expectedText.trim())) {
				reportStatusPass("Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail("Actual Value for " + fieldName + " is " + sf.seleU.getTextFromWebElement(element) + " And Expected One is "
						+ expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}




}