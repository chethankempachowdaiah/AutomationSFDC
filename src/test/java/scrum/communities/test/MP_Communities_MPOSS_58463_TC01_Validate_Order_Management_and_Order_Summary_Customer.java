package scrum.communities.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.lib_pages.SFDC_AllPages;

public class MP_Communities_MPOSS_58463_TC01_Validate_Order_Management_and_Order_Summary_Customer extends Base {

	@Test
	public void validateOrderManagementAndOrderSummary() throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		sfdc.comLogin.loginToCommunities();
		if (sf.dataInput.frenchEnabled.equalsIgnoreCase("Yes")) {
			sfdc.comHome.changeLanguage();
		}
		sfdc.comHome.validateOrderSummarycommunities();
		sfdc.comHome.validatePagination();
		sfdc.comHome.validateSearchResults();
		sfdc.comHome.validateSorting();
		sfdc.comHome.validateMasterOrder();
		sfdc.comHome.validateChildOrders();
		sfdc.comHome.validateChildOrderDetails();
	}

}
