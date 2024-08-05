package scrum.sales.test;

import com.framework.base.Base;
import com.framework.base.Global;
import java.io.IOException;
import org.testng.annotations.Test;
import com.sfdc.data.InputData;
import org.testng.annotations.Test;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * 
 *          PI22_01_SP04_MPOSS_55253_Validate_Permission_Sets_Test
 *         
 *          MPOSS-55253_TC_01 Verify permission set "Create New Lead" added.
 */

public class PI22_01_SP04_MPOSS_55253_Validate_Permission_Sets_Test extends Base {
	
	/**
	 * @throws Exception
	 * 
	 *                  
	 */
	@Test
	public void test_ValidatePermissionSets() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_BusinessAdmin);
		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.setupPage.verifyCreateNewLeadPermission();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}

}


