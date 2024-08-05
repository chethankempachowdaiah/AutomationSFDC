package scrum.communities.test;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.util.Hashtable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.framework.base.Base;
import com.framework.base.BaseDataProvider;
import com.framework.base.Constants;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_Communities;
import com.sfdc.lib_pages.SFDC_AllPages;

/**
 * @author Anukriti.Chawla
 * 
 *      
 *         PBF_L1_E2E_Validate lost opportunity is created when the existing address is not serviceable
 *         
 *         PBF_L1_E2E_Validate lost opportunity is created when the newly added address is not serviceable 
 * 
 */
public class MP_Communities_TC_09_PBF_Customer_LostOpportunity_Test extends BaseDataProvider {

	/**
	 * @throws Exception 
	 */
	@Test(dataProvider = "PBFCustomerData")
	public void test_validate_PBF_SiteSelection(Hashtable<String, String> dataTable) throws Exception {

		SFDC_AllPages sfdc = new SFDC_AllPages();

		InputData_Communities.setDataForPBFE2E(dataTable);

		// ********************PBF_L1_E2E_Validate SA is able to place an order successfully_No Credit_No Fraud*************/
		// Login to PBF and verify landing page
		sfdc.commPBF.loginToCommunitiesPBF(sf.commData.communitiesPBFCredentialsForMultiAcc);
		sfdc.commPBF.verifyPBFLandingPage();

		// Verify Business Account Dropdown Presence
		sfdc.commPBF.verifyBusinessAccDropdownPresent();

		//Select Business Account
		sfdc.commPBF.selectBusinessAccount();

		if (InputData_Communities.commPBFAddNewSite.equals("Yes")) {
			
			sfdc.commPBF.addNewSite();
			sfdc.commPBFAddSite.fillSiteInfo();
			sfdc.commPBFAddSite.verifyServiceAddressesTableLayout();
			sfdc.commPBFAddSite.selectIDontSeeMyAddress();
			
			sfdc.commPBFAddSite.verifyNonServiceablityErrorMessage();
			
		} else {
			
			//Verify Pagination,sorting,Filter and Total
			sfdc.commPBF.verifyServiceAddressesTableLayout();
			sfdc.commPBF.selectBusinessAccount();
			sfdc.commPBF.selectAddress();
			
			//Validate shopping Cart Page and Proceed
			sfdc.commPBFShopCart.validateShoppingCartPageForNonServiceability();
			
			
		}
		
			
		//Login with AE and validate lost opportunity
		sfdc.login.navigateURL();
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpenWithRefresh();
		sfdc.opp.verifyNumberOfOpportunityWithSameName(sf.dataInput.businessAccountName + "-New", InputData_Communities.commPBFNumberOfClosedLostOpp);
		sfdc.home.logout();
			
		
	}
	
	/**
	 * @return
	 * @throws IOException
	 * 
	 *                     Data Provider to fetch multiple set of data and assign
	 *                     them to 2D Object Array
	 */
	@DataProvider
	public Object[][] PBFCustomerData() throws IOException {
		return getDataSetsRunMode(Constants.TESTDATA_COMM_FILE, Constants.PBF_CUSTOMER_LOP_SHEET);
	}
}
