package sfdc.pages.om;

import java.io.IOException;

import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya, Date: 03/02/2020
 *
 *         SFDC Orchestration Item Page
 */
public class SFDC_OrchestrationItem_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public SFDC_OrchestrationItem_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Orchestration_Item@: ";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify orchestartion task for orchestartion item( 'Create
	 *                     Service Account and Work Order in Super system', Vlocity
	 *                     Order completion) state
	 * 
	 */
	public void verifyOrchestrationState() throws IOException {
		try {

			/*
			 * Verify orchestartion task for orchestartion item( 'Create Service Account and
			 * Work Order in Super system', Vlocity Order completion) state
			 */
			String orderState;
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.orchItem.orchestrationStateValueText);

			if (sf.dataInput.isOrderFailed) {
				orderState = sf.dataInput.orderStateFailed;
			} else {
				orderState = sf.dataInput.orchestrationStateCompleted;
			}
			if (sf.seleU.getTextFromWebElement(sf.orchItem.orchestrationStateValueText.get(0)).equals(orderState)) {

				reportStatusPass(methodName + " Verified orchestartion task  status as " + orderState, true, true);
			} else {
				reportStatusFail(" Verification failure for Orchestration State, Actual: "
						+ sf.seleU.getTextFromWebElement(sf.orchItem.orchestrationStateValueText.get(0))
						+ " Expected is " + orderState, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Verification failure for Orchestration State", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify orchestartion task for orchestartion item state is
	 *                     Pending
	 * 
	 *                     Change the state to Running
	 * 
	 *                     Verify State is changed to Running
	 * 
	 */
	public void changeOrchestrationStatePendingToRunning() throws IOException {
		try {

			/*
			 * Verify orchestartion task for orchestartion item state is Pending
			 */
			String orderState;
			sf.seleU.switchToDefaultContent();
			sf.seleU.switchToElementFrame(sf.orchItem.orchestrationStateValueText);

			if (sf.seleU.getTextFromWebElement(sf.orchItem.orchestrationStateValueText.get(0))
					.equals(sf.dataInput.taskStatusPending)) {

				reportStatusPass(
						methodName + " Verified orchestartion task  status as " + sf.dataInput.taskStatusPending, true,
						true);
			} else {
				reportStatusFail(" Verification failure for Orchestration State, Actual: "
						+ sf.seleU.getTextFromWebElement(sf.orchItem.orchestrationStateValueText.get(0))
						+ " Expected is " + sf.dataInput.taskStatusPending, true);
			}

			// Change the state to Running
			sf.seleU.clickElementByJSE(sf.orchItem.orchestrationStateEditButton);
			sf.seleU.clickElementByJSE(sf.orchItem.selectStateInput);
			sf.seleU.hardwait(3000);
			sf.seleU.clickElementByJSE(sf.orchItem.stateOptionRunning);
			sf.seleU.hardwait(2000);
			sf.seleU.clickElementByJSE(sf.orchItem.saveButton);
			reportStatusPass(methodName + " Changed state to Running ", true, false);
			sf.seleU.hardwait(5000);

			// Verify State is changed to Running
			if (sf.seleU.getTextFromWebElement(sf.orchItem.orchestrationStateValueText.get(0))
					.equals(sf.dataInput.taskStatusRunning)) {

				reportStatusPass(
						methodName + " Verified orchestartion task  status as " + sf.dataInput.taskStatusRunning, true,
						true);
			} else {
				reportStatusFail(" Verification failure for Orchestration State, Actual: "
						+ sf.seleU.getTextFromWebElement(sf.orchItem.orchestrationStateValueText.get(0))
						+ " Expected is " + sf.dataInput.taskStatusRunning, true);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification failure for Orchestration State", e);
			e.printStackTrace();
		}
	}
}
