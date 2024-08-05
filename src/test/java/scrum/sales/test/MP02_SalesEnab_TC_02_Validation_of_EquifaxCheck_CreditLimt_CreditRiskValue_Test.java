package scrum.sales.test;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 14/Feb/2022
 * 
  *         Test Case 1- User creates new Opportunity on Business Account which has NO 
 *         Billing Account tagged AND NO Credit Limit Assigned AND Equifax Returns with 
 *         MULTIPLE matches found AND ZERO account with above CL8 
 *         
 *         
 *         Test Case-2
 *         Verification of "Credit Limit Available" and "Credit Check Status =Not Required"
 *         values when credit value remains/goes into positive after creating order (PBF Flow).
 *
 *			        
 *        [Assigned Credit Limit =  Credit limit Assigned - ((new MRR + new NRC)  + 
 *        (unbilled + A/R))  -- 4000
 *        
 *         unbilled= MRR + NRC ,A/R = ATB 3 + ATB4 + ATB5
 *         MRR= Monthly Recurring Charge and NRC = One Time TCV
 *         = -Negative - Credit Review Required
 *         = +Positive - Not credit review is not required , subtract the Values from the
 *          Credit Limit Available]
 * 
 */
public class MP02_SalesEnab_TC_02_Validation_of_EquifaxCheck_CreditLimt_CreditRiskValue_Test extends Base {
	
	/**
	 * @throws Exception
	 * 
	 *            
	 */
	@Test
	public void test_validation_of_EquifaxCheck_CreditLimt_CreditRiskValue() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
	
		/*Test Case -1  TC02 Validation of equifax message and $1000 has been credited or not 
		 to Credit Limit Available and Total Credit Limit Assigned respectively at account details*/
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.createNewBusinessAccountLWC();
		sfdc.cba.enterBusinessAccountInfoLWC(); 
		sfdc.cba.verifyBusinessAccountCreatedLWC();
		sfdc.cc.enterNewContactInfoLWC(true);
		sfdc.home.selectHome();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_DataGovernance);
		sf.dataInput.FNF = "Yes";
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.approveAsDataGovernance();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sf.seleU.refreshPage();
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.extractCreditCheckRequiredValidationValues();
		sfdc.accDetails.validateCreditValuesAfterEquifaxCheck();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		/*Test Case -2 MPOSS-37777 TC01 Validate as AE If the user gets Equifax response 
		 as "File Not Found" then  Automatically "Credit Risk Value" will be marked as "High Risk FNF*/
		
		sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchBusAccGlobalSearch(Global.dataInput.businessAccountName);
		sfdc.accDetails.validateFnFValueAfterEquifax();
		sfdc.home.logout();
		softassert.assertAll();
	}

}
