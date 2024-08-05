package scrum.sales.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla, Date 15/Apr/2021
 * 
 *         Validate Labels for vertical group and vertical changed to Industry-Sub-Industry for create a bunsiness Account
 *
 * 
 */
public class Pl02_SP1_MPOSS_36764_Validate_Industry_SubIndustry_Label_On_Create_BusinessAccount_Page_Test extends Base {

	/**
	 * @throws IOException
	 * 
	 *                              1. AE log in and select account object
	 * 
	 *                              2. Click New 
	 * 
	 *                              4. Select Account Record Type: Business
	 * 
	 *                              5. Verify labels for Industry- Sub Industry
	 *                              
	 * @throws InterruptedException
	 * 
	 */
	@Test
	public void test_validateLabelsIndSubIndBusinessAccount() throws IOException, InterruptedException {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.login.loginToSFDC(InputData.Profile_AE);

		sfdc.home.openR4BSalesConsole();
		sfdc.home.closeTabIfOpen();
		sfdc.acc.launchCreateNewBusinessAccount();
		sfdc.cba.verifyIndustrySubIndustryLabels();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();

		softassert.assertAll();

	}

}
