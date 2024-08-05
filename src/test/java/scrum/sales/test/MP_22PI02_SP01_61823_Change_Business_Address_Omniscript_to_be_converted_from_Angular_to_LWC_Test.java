package scrum.sales.test;

import com.framework.base.Base;
import org.testng.annotations.Test;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Nandan.More, Date 11/Apr/2022
 * 
 *          "Change Business Address" Omniscript to be converted from Angular to LWC.
 * 
 */

public class MP_22PI02_SP01_61823_Change_Business_Address_Omniscript_to_be_converted_from_Angular_to_LWC_Test extends Base {
	
	/**
	 * @throws Exception
	 * 
	 *                  
	 */
	@Test
	public void test_ValidateChangebusinessLWC() throws Exception , InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.selectAccountTab();
		sfdc.acc.selectChangeBusinessAddress();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}



