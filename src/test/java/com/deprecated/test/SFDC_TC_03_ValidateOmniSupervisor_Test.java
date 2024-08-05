package com.deprecated.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.framework.base.Base;
import com.sfdc.data.InputData;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author priyanka.acharya
 * 
 *         validate the Omni supervisor Page
 *
 */
public class SFDC_TC_03_ValidateOmniSupervisor_Test extends Base {

	SFDC_AllPages sfdc = new SFDC_AllPages();

	/**
	 * 1- Login to application SFDC
	 * 
	 * 2- Close any open account/opportunity tab
	 * 
	 * 3- validate the Omni supervisor Page
	 * 
	 * 4- Close any open account/opportunity tab
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void test_validateOmniSupervisor() throws IOException {

		sfdc.login.loginToSFDC(InputData.userid_ae);
		sfdc.home.closeTabIfOpen();
		sfdc.omni.selectOmniSupervisor();
		sfdc.home.closeTabIfOpen();
		softassert.assertAll();

	}
}
