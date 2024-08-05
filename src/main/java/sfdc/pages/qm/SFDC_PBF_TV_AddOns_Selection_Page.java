package sfdc.pages.qm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_PBF_TV_AddOns_Selection_Page extends MyListener {
	
	// Creating all the pages Object to interact with pages
		public static SFDC_AllPageObjects sf;
		public static String methodName;

		public SFDC_PBF_TV_AddOns_Selection_Page() {
			sf = new SFDC_AllPageObjects();
			methodName = "SFDC_PBF_TV_AddOns_Selection@:";
		}
		
		public void validateTVAddOnsProductNames() throws IOException {

			try {
				List<String> expectedBusinessAddons = InputData_Communities.commPBFTVAddOnsList;
				List<String> actualBusinessAddons = new ArrayList<String>();

				// Loop over all business TV Addons names to be verified
				for (int i = 0; i < sf.comPBFTVAddons.addOnHeaderList.size(); i++) {
					actualBusinessAddons.add(sf.seleU.getTextFromWebElement( sf.comPBFTVAddons.addOnHeaderList.get(i)).trim());
				}
				reportStatusPass(methodName + " Extracted TV AddOns names to compare with expected list", false, false);

				// sort lists for comparison
				Collections.sort(expectedBusinessAddons);
				Collections.sort(actualBusinessAddons);

				// Verify expectedBusiness plan list is equal to actualBusiness Plans List
				if (expectedBusinessAddons.equals(actualBusinessAddons)) {
					reportStatusPass(methodName + " All expected TV AddOns are present : "
							+ AdditionalUtilities.getAsString(actualBusinessAddons), true, true);
				} else {
					reportStatusFail(methodName
							+ " All expected TV AddOns are not present : :: Expected Plans--> "
							+ AdditionalUtilities.getAsString(expectedBusinessAddons) + "  Actual Plans--> "
							+ AdditionalUtilities.getAsString(actualBusinessAddons), true);
				}

			} catch (Throwable e) {
				reportStatusFail(methodName + " Error in validating TV AddOns Names", e);
				e.printStackTrace();
			}

		}
		
		public void addTVAddon() throws IOException {
			
			try {
				String addonsTV = InputData_Communities.commPBFTVAddOnName;
				sf.seleU.wait(6000);
				// Loop over all business TV Addons names
				for (int i = 0; i < sf.comPBFTVAddons.addOnHeaderList.size(); i++) {
					if(sf.seleU.getTextFromWebElement(sf.comPBFTVAddons.addOnHeaderList.get(i)).trim().equalsIgnoreCase(addonsTV)) {
						for(int j=0; j < InputData_Communities.commPBFTVAddOnQty; j++) {
							sf.seleU.clickElementByJSE(sf.comPBFTVAddons.incrementAddOnButton.get(i));
						}
						reportStatusPass(methodName + ": "+ addonsTV +" Addon Added to TV Product", true, false);
						
					}
				}
				sf.seleU.wait(7000);
				sf.seleU.ScrolltoElementPageCenter(sf.comPBFTVAddons.updateCartButton);
				
				//Click on Update Cart Button
				sf.seleU.clickElementByJSE(sf.comPBFTVAddons.updateCartButton);
				sf.seleU.wait(10000);
				sf.seleU.waitForLoading();
				reportStatusPass(methodName + " Clicked on Update Cart Button", true, false);
				
				
			} catch (Throwable e) {
				reportStatusFail(methodName + " Error in validating TV AddOns Names", e);
				e.printStackTrace();
			}
		}
		
		public void validateEditAddonsTV() throws IOException {

			try {
				String addonsTV = InputData_Communities.commPBFTVAddOnName;
				String editAddonsTV = InputData_Communities.commPBFTVEditAddOnName;

				// Loop over all business TV Addons names
				for (int i = 0; i < sf.comPBFTVAddons.addOnHeaderList.size(); i++) {
					if(sf.seleU.getTextFromWebElement(sf.comPBFTVAddons.addOnHeaderList.get(i)).trim().equalsIgnoreCase(addonsTV)) {
						for(int j=0; j < InputData_Communities.commPBFTVAddOnQty; j++) {
						sf.seleU.clickElementByJSE(sf.comPBFTVAddons.decrementAddOnButton.get(i));
						}
						reportStatusPass(methodName+":"+ addonsTV +" Addon removed from TV Product", true, false);
					}
					if(sf.seleU.getTextFromWebElement(sf.comPBFTVAddons.addOnHeaderList.get(i)).trim().equalsIgnoreCase(editAddonsTV)) {
						sf.seleU.clickElementByJSE(sf.comPBFTVAddons.incrementAddOnButton.get(i));
						reportStatusPass(methodName+":"+ editAddonsTV +" Addon Modified to TV Product", true, false);
					}
				}

				//Click on Update Cart Button
				sf.seleU.clickElementByJSE(sf.comPBFTVAddons.updateCartButton);
				sf.seleU.wait(10000);
				reportStatusPass(methodName + " Clicked on Update Cart Button", false, false);

				//Click on shopping Cart Button
				sf.seleU.clickElementByJSE(sf.comPBFTVAddons.shoppingCartButton);
				sf.seleU.wait(10000);
				reportStatusPass(methodName + " Clicked on shopping Cart Button", false, false);

			} catch (Throwable e) {
				reportStatusFail(methodName + " Error in validating Edit TV AddOns Names", e);
				e.printStackTrace();
			}
		}

}
