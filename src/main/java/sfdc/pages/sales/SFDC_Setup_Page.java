package sfdc.pages.sales;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Keys;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_Setup_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	SFDC_AllPageObjects sf;
	static String methodName = "SFDC_Setupd@: ";

	public SFDC_Setup_Page() {
		sf = new SFDC_AllPageObjects();
	}

	/**
	 * @throws IOException
	 * 
	 * 				Verify Create New Lead Permission              
	 * 
	 */
	public void verifyCreateNewLeadPermission() throws IOException {
		try {
			
			sf.seleU.wait(6000);
			sf.seleU.clickElementByJSE(sf.setup.setupIcon);
			sf.seleU.wait(3000);
			sf.seleU.switchToElementFrame(sf.setup.setupButton);
			sf.seleU.clickElementByJSE(sf.setup.setupButton.get(0));
			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.setup.searchSetup , Global.salesData.createNewLeadPermission);
			sf.seleU.wait(3000);
			sf.seleU.enterText(sf.setup.searchSetup, Keys.ENTER);
			sf.seleU.wait(3000);
			sf.seleU.clickElementByJSE(sf.setup.linkCreateNewLead);
			sf.seleU.wait(5000);
			
			sf.seleU.switchToElementFrame(sf.setup.createNewLead);
			if (Global.salesData.createNewLeadPermission.equalsIgnoreCase(sf.seleU.getTextFromWebElement(sf.setup.createNewLead.get(0))))
				reportStatusPass(methodName + " Verification Pass -Verifed Create New Lead Permission created ", true, true);

			else
				reportStatusFail(" Verification Failed- Create New Lead Permission not created ",
						true);
					
		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in verifying permission sets", e);
			e.printStackTrace();
		}
	}



}
