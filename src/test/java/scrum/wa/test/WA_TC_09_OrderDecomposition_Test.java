package scrum.wa.test;

import org.testng.annotations.Test;
import com.framework.base.Base;
import com.framework.base.Global;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 *@author Sakshi.Lnu
 * 
 *       Accept Shop wireless devices
 *
 */
public class WA_TC_09_OrderDecomposition_Test extends Base {

	/**
	 * 
	 * @throws IOException
	 * @throws InterruptedException                       
	 *                             
	 */

	SFDC_AllPages sfdc = new SFDC_AllPages();
	@Test()
	public void verifyShopWirlessDevicesPage_Test() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();
		// **************Login with AE profile*************************
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		Global.dataInput.orderNumber = "00057804";
		sfdc.orders.globalSearchOrderFromHomeMenu(Global.dataInput.orderNumber);
		sfdc.orderDecomPose.clickDecompositionInOrder();
		sfdc.home.closeTabIfOpen();
		sfdc.home.logout();
		softassert.assertAll();
	}
}
