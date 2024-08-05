package scrum.sales.test;


import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Sales;
import com.sfdc.lib_pages.SFDC_AllPages;
/**
 * @author Nandan.More, Date 24/June/2021
 * 
 *         
 *         
 *         MPOSS-41504_TC01_Validate that on Quote Approval page layout -  Quote Name and Account Name fields have been removed from the Quick Action layout
 *         
 *         MPOSS-41504_TC02_Validate that on Quote Approval page layout -  Quote Name and Account Name fields have been removed from the Quick Action layout
 *         
 *         MPOSS-41504_TC03_Validate that on Quote Approval page layout -  Quote Name and Account Name fields have been removed from the Quick Action layout
 *         
 *         MPOSS-41504_TC04_Validate that on Quote Approval page layout -  The Quote Name is still visible on the Approval Record page and is editable
 *         
 *         MPOSS-41504_TC05_Validate that on Quote Approval page layout -  The Quote Name is still visible on the Approval Record page and is editable
 *         
 *         MPOSS-41504_TC06_Validate that on Quote Approval page layout -  The Quote Name is still visible on the Approval Record page and is editable
 *         
 *         MPOSS-41504_TC07_Validate that on Quote Approval page layout -  The Account Name is shown under Quote Detail section and is not editable
 *         
 *         MPOSS-41504_TC08_Validate that on Quote Approval page layout -  The Account Name is shown under Quote Detail section and is not editable
 *         
 *         MPOSS-41504_TC09_Validate that on Quote Approval page layout -  The Account Name is shown under Quote Detail section and is not editable
 * 
 */

public class PI02_SP6_MPOSS_41504_TC_01_Validate_on_Quote_Approval_page_R4B_Quote_Approval extends Base {
	
	/**
	 * @throws IOException
	 * 
	 *                                               
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_R4B_Quote_Approval() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		
		sfdc.home.openR4BQuoteApproval();
		
				
		//Open any quote from R4B Quotes 
	     sfdc.r4Bquoteapp.seletAndOpendR4BQuoteApproval();
	     
	   //Verify Quote Name and Account Name fields have been removed from the Quick Action layout
	     sfdc.r4Bquoteapp.verifyQuoteNameAccName();
	     
	   //Verify Related Quote Filed -Editable under details tab
	  //   sfdc.r4Bquoteapp.verifyRelatedQuoteField();  ...//descoped as per new USER story

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//Login as Contract Manager
		sfdc.login.loginToSFDC(InputData.Profile_CreditOps);
		//sfdc.home.navigateURL();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BQuoteApproval();
		
		
		//Open any quote from R4B Quotes 
	     sfdc.r4Bquoteapp.seletAndOpendR4BQuoteApproval();
	     
	   //Quote Name and Account Name fields have been removed from the Quick Action layout
	     sfdc.r4Bquoteapp.verifyQuoteNameAccName();
	     
	   //Verify Related Quote Filed -Editable under details tab
	     sfdc.r4Bquoteapp.verifyRelatedQuoteField();
	     
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		
		//Login as Fraud Support
		sfdc.login.loginToSFDC(InputData.Profile_FraudOps);
		//sfdc.home.navigateURL();
		sfdc.home.closeTabIfOpen();
		sfdc.home.openR4BQuoteApproval();
		
		
		//Open any quote from R4B Quotes 
	     sfdc.r4Bquoteapp.seletAndOpendR4BQuoteApproval();
	     
	   //Quote Name and Account Name fields have been removed from the Quick Action layout
	     sfdc.r4Bquoteapp.verifyQuoteNameAccName();
	     
	   //Verify Related Quote Filed -Editable under details tab
	     sfdc.r4Bquoteapp.verifyRelatedQuoteField();

		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		softassert.assertAll();

	}

}
