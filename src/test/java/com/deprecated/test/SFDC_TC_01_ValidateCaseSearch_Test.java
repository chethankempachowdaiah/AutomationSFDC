package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author priyanka.acharya
 * 
 *         validate the filter criteria for case search
 *
 */
public class SFDC_TC_01_ValidateCaseSearch_Test extends Base {

	SFDC_AllPages sfdc = new SFDC_AllPages();

	/**
	 * 1- Login to application SFDC
	 * 
	 * 2- Close any open account/opportunity tab
	 * 
	 * 3- select account and validate the defined account is getting opened
	 * 
	 * 4- validate the filter criteria for case search
	 * 
	 * 5- Close any open account/opportunity tab
	 * 
	 * @throws IOException
	 * 
	 * 
	 */
	@Test
	public void test_validateCaseSearch() throws IOException {
		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.closeTabIfOpen();
		sfdc.acc.selectAccount();
		sfdc.acc.searchCase();
		sfdc.home.closeTabIfOpen();
		softassert.assertAll();
	}
}
