package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_Service;
import com.sfdc.page_objects.SFDC_AllPageObjects;

import sfdc.pages.communities.Communities_MyBusinessCaseDetails_Page;

public class SFDC_SnowPortal_Case_Page extends MyListener {
	
	// Creating all the pages Object to interact with pages
		public static SFDC_AllPageObjects sf;
		public static String methodName;
		
		public SFDC_SnowPortal_Case_Page() {
			sf=new SFDC_AllPageObjects();
			methodName="SNOWPortal_CasePage@";
		}
	/**
	 * @param value
	 * @throws Exception
	 * 
	 *                    Launching SNOW PORTAL
	 * 
	 *                   Validate values from salesforce case in Snow Portal 
	 */
	
    public void launchSnowPortal() throws Exception {
		
		//InputData.caseNumber = sf.seleU.getTextFromWebElement(sf.caseDetails.caseNumberFieldValueText);
		sf.seleU.hardwait(3000);
		driver.get(InputData_Service.snowPortalURL);
		sf.seleU.hardwait(4000);
		if (sf.seleU.isElementPresent(sf.snowCasePage.snowGlobalSearch)){
			sf.seleU.enterText(sf.snowCasePage.snowGlobalSearch, dataInput.caseNumber);
			sf.seleU.enterText(sf.snowCasePage.snowGlobalSearch, Keys.ENTER);
			reportStatusPass(methodName + " Entered the case ID " , true, true);			
		}
		sf.seleU.switchToFrame(sf.snowCasePage.incidentFrame);
		sf.seleU.clickOnElement(sf.snowCasePage.incidentLink);
		sf.seleU.hardwait(4000);
		
		InputData_Service.snowIncidentID=sf.seleU.getElementAttribute(sf.snowCasePage.incidentID, "value");
		reportStatusPass(methodName + "Incident ID " +InputData_Service.snowIncidentID, true, true);
		sf.seleU.hardwait(2000);
		InputData_Service.snowPortfolio=sf.seleU.getTextFromWebElement(sf.snowCasePage.portfolioOption);  //getElementAttribute(sf.login.portfolioOption, "value");
		reportStatusPass(methodName + " Case Portfolio " +InputData_Service.snowPortfolio, true, true);
		InputData_Service.snowStatus=sf.seleU.getTextFromWebElement(sf.snowCasePage.statusOption);
		reportStatusPass(methodName + " Case Status " +InputData_Service.snowStatus, true, true);
		InputData_Service.snowCaseID=sf.seleU.getElementAttribute(sf.snowCasePage.caseId, "value");
		reportStatusPass(methodName + " Case ID " +InputData_Service.snowCaseID, true, true);
		InputData_Service.snowAssignmentgrp=sf.seleU.getElementAttribute(sf.snowCasePage.snowAssignmentGrp, "value");
		reportStatusPass(methodName + " Case AssignmentGroup in Snow " +InputData_Service.snowAssignmentgrp, true, true);
		InputData_Service.snowPriority=sf.seleU.getElementAttribute(sf.snowCasePage.priorityOption, "value");
		reportStatusPass(methodName + "Getting Priority " +InputData_Service.snowPriority, true, true);
		sf.seleU.hardwait(2000);
		InputData_Service.snowDescription=sf.seleU.getElementAttribute(sf.snowCasePage.descriptionField, "value");
		reportStatusPass(methodName + " case description" +InputData_Service.snowDescription, true, true);
		InputData_Service.snowSubject=sf.seleU.getElementAttribute(sf.snowCasePage.shortDescriptionField, "value");
		reportStatusPass(methodName + " case Subject" +InputData_Service.snowSubject, true, true);
		sf.seleU.hardwait(2000);
		if(sf.snowCasePage.snowReason.isDisplayed()) {
		InputData_Service.snowReason=sf.seleU.getTextFromWebElement(sf.snowCasePage.snowReason);
		}
		else {
			sf.seleU.clickOnElement(sf.snowCasePage.snowClosureInfoTab);
			InputData_Service.snowReason=sf.seleU.getTextFromWebElement(sf.snowCasePage.snowReason);
		}
		
		//Verifying Fields captured with the Salesforce entered case values
		verifyFieldValue("Incident Id", sf.snowCasePage.incidentID, InputData.incidentID);
		verifyFieldValue("Case Id",sf.snowCasePage.caseId, InputData.caseNumber);
		//verifyFieldValue("Case Assignment Group",sf.snowCasePage.snowAssignmentGrp, InputData.caseNumber); //create the Assignmnet Group Variable
		verifyFieldValue("Case Status",sf.snowCasePage.statusOption, InputData_Service.verifyValue.get(InputData_Service.sfdcCaseStatus));
		verifyFieldValue("Case priority",sf.snowCasePage.priorityOption, InputData_Service.verifyValue.get(InputData_Service.sfdcCasePriority));
		verifyFieldValue("Case subject",sf.snowCasePage.shortDescriptionField, InputData.caseStatusNew + addOn_1);
		verifyFieldValue("Case description",sf.snowCasePage.descriptionField, InputData.caseStatusNew + addOn_1);
		verifyFieldValue("Case productFamily , portfolio",sf.snowCasePage.portfolioOption, InputData_Service.verifyValue.get(InputData_Service.sfdcCaseProduct));
		//verifyFieldValue("Case Reason",sf.snowCasePage.snowReason, InputData_Service.verifyValue.get(InputData_Service.sfdcCaseReason));

	}
    
    /**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public static void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).contains(expectedText)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is " + expectedText, true, true);
			} else {
				reportStatusFail(methodName + " Actual Value for " + fieldName + " is " + element.getText()
						+ " And Expected One is " + expectedText, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
	}
	
	public void snowlogout() {
		sf.seleU.clickElementByJSE(sf.snowCasePage.snowUserInfo);
		sf.seleU.clickElementByJSE(sf.snowCasePage.snowLogout);
	}
}
