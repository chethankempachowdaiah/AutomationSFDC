package scrum.communities.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;

import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 */
public class MP_Communities_TC_04_VerifyOrderDetails_Test extends Base {

	/**
	 * @throws Exception 
	 */
	@Test
	public void test_VerifyMBH_Order_Details() throws Exception {

       //intializeChrome(true);

		SFDC_AllPages sfdc = new SFDC_AllPages();

		// ****LOGIN To Communitities and Verify Community Badge*******//
		sfdc.comLogin.loginToCommunities();
		//sfdc.comHome.verifyCommunityBadge();

		// ****Verify My Business Orders table and Order details according to
		// status*******//
		// sfdc.comHome.openCommunityCases();
		sfdc.comOrderDetails.myBusinessOrdersVerfifyOrderDetails();		sfdc.comHome.validateOrderSummarycommunities();
		sfdc.comHome.validatePagination();
		sfdc.comHome.validateSearchResults();
		sfdc.comHome.validateSorting();
		sfdc.comHome.validateMasterOrder();
		sfdc.comHome.validateChildOrders();
		sfdc.comHome.validateChildOrderDetails();
		
		// softassert.assertAll();/

	}

}
