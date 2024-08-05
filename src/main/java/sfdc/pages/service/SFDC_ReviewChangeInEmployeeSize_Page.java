package sfdc.pages.service;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 30/04/2020
 *
 *         SFDC Account > Change Employee Size> Cutsomer Story> Task>Please
 *         review the change in employee size for the account referenced
 */
public class SFDC_ReviewChangeInEmployeeSize_Page extends MyListener {
	// Creating all the pages Object to interact with pages
	public SFDC_AllPageObjects sf;
	String methodName;

	public SFDC_ReviewChangeInEmployeeSize_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Employee Change Customer Story Task@: ";

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Employee Size Change Csutomer Story Task field
	 *                     values
	 */
	public void verifyEmployeeSizeChangeTaskFields() throws IOException {
		try {

			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.es.relatedToAccount);

			// Verify Employee Size Change Csutomer Story Task field values
			verifyFieldValue("Business Account Related To", sf.es.relatedToAccount.get(0),
					sf.dataInput.businessAccountName);
			verifyFieldValue("Task Subject", sf.es.subjectValue, sf.dataInput.customerStoryEmployeeSizeChange);
			verifyFieldValue("Employee Size Change Assigned To", sf.es.assinedToValue,
					sf.dataInput.employeeSizeChangeAssignedTo);
			verifyFieldValue("Employee Size Change Comments", sf.es.commentsValue,
					sf.dataInput.employeeSizeChangeComments);
			verifyFieldValue("Employee Size Change Priority", sf.es.priorityValue,
					sf.dataInput.employeeSizeChangePriority);
			verifyFieldValue("Employee Size Change Status", sf.es.statusValue, sf.dataInput.employeeSizeChangeStatus);

		} catch (Throwable e) {
			reportStatusFail(" Error in Verifying Employee Size Change Story", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field value matches the expected result
	 */
	public void verifyFieldValue(String fieldName, WebElement element, String expectedText) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.getTextFromWebElement(element).equals(expectedText)) {
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

}
