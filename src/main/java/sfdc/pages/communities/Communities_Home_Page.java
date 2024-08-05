package sfdc.pages.communities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.base.MyListener;
import com.framework.utilities.AdditionalUtilities;
import com.sfdc.data.InputData_Communities;
import com.sfdc.page_objects.SFDC_AllPageObjects;

/**
 * @author Priyanka.Acharya,, Date:19/07/2020
 * 
 *         Communities>Home
 *
 */
public class Communities_Home_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public static String methodName;

	public Communities_Home_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "Communities_Home@:";
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Dashboard
	 * 
	 *                     Verify Community Badge and Click on View Cases
	 */
	public void verifyCommunityBadge() throws IOException {

		try {
			// Click on Dashboard
			sf.seleU.clickElementByJSE(sf.comHome.dashboardLink);
			reportStatusPass(methodName + " Clicked on Dashboard Link", false, false);
			sf.seleU.wait(15000);

			// Verify Community Badge and Click on View Cases
			sf.seleU.scrollByCoOrdinates(1);
			verifyFieldDisplayed("Community Badge", sf.comHome.communitiesBadge);
			sf.seleU.clickElementByJSE(sf.comHome.communitiesBadgeViewCases);
			reportStatusPass(methodName + " Clicked on Community Badge View Cases", false, false);
			sf.seleU.wait(10000);

			sf.seleU.switchWindow(2);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Community Badge is unsuccessful", e);
			e.printStackTrace();
		}

	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Rogers Logo
	 * 
	 *                     Verify Quick Links
	 * 
	 *                     Verify User Email Text and Profile(Preferences, Sign Out
	 *                     Link)
	 * 
	 *                     Verify MBH Nav Bar( My Business Hub,
	 *                     Dashboard,Preference)
	 */
	public void verifyHeader() throws IOException {
		try {

			// Verify Rogers Logo
			sf.seleU.wait(10000);
			verifyFieldDisplayed("Rogers logo in global-top-nav", sf.comHome.rogersLogoLink);
			sf.seleU.clickElementByJSE(sf.comHome.rogersLogoLink);
			sf.seleU.wait(5000);
			if (sf.seleU.getCurrentUrl().contains(sf.commData.communities_rogers_home)) {
				reportStatusPass(methodName + " Verified Navigation to Rogers Home Page", true, true);
			} else {
				reportStatusFail(methodName + " Verifying Navigation to Rogers Home Page is unsuccessful", true);
			}
			sf.seleU.navigateBack();
			sf.seleU.wait(5000);

			// Verify Quick Links
			verifyFieldDisplayed("Quick Links", sf.comHome.quickLinks);

			// Verify User Email Text and Profile(Preferences, Sign Out Link)
			verifyFieldDisplayed("User Email Text", sf.comHome.userEmailText);
			if (sf.seleU.getElementAttribute(sf.comHome.userEmailTitle, "title")
					.equals(sf.commData.communities_contact)) {
				reportStatusPass(
						methodName + " Verified User presented with their username (Upon Mouse Over on Profile) : "
								+ sf.commData.communities_contact,
								true, true);
			} else {
				reportStatusFail(methodName + "Verifying User Name (Upon Mouse Over on Profile) is unsuccessful", true);
			}
			sf.seleU.clickElementByJSE(sf.comHome.profileImage);
			verifyFieldDisplayed("Profile Preference", sf.comHome.profilePreference);
			verifyFieldDisplayed("Sign Out", sf.comHome.signoutLink);
			sf.seleU.clickElementByJSE(sf.comHome.profilePreference);
			sf.seleU.wait(15000);
			verifyFieldDisplayed("MyBusiness Hub Associated Products & Services Header",
					sf.comHome.myBusinessHubAssociatedProductsAndServices);
			verifyFieldDisplayed("MyBusiness Cases Toggle ", sf.comHome.myBusinessCasesToggleButton);
			sf.seleU.navigateBack();
			sf.seleU.wait(5000);

			// Verify MBH Nav Bar( My Business Hub, Dashboard,Preference)
			verifyFieldDisplayed("My Business Hub InHeader", sf.comHome.myBusinessHubInHeader);
			verifyFieldDisplayed("Dashboard Link", sf.comHome.dashboardLink);
			verifyFieldDisplayed("Preference Link", sf.comHome.preferencesLink);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Community Header is unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify and Click on Quick Link
	 * 
	 *                     Verify My Business Cases Link
	 * 
	 *                     Click on My Business Cases Link
	 * 
	 *                     Verify Quick Links navigates to My Business Cases Header
	 */
	public void verifyQuickLinks() throws IOException {
		try {

			// Verify and Click on Quick Link
			verifyFieldDisplayed("Quick Links", sf.comHome.quickLinks);
			sf.seleU.clickElementByJSE(sf.comHome.quickLinks);
			reportStatusPass(methodName + " Clicked on Quick Links", false, false);
			sf.seleU.wait(8000);

			// Verify My Business Hub Link
			verifyFieldDisplayed("My Business Hub Link", sf.comHome.myBusinessHubLink);

			// verifyFieldDisplayed("My Business Cases Link",
			// sf.comHome.communitiesBadgeViewCases);

			// Click on My Business Cases Link
			//			sf.seleU.clickElementByJSE(sf.comHome.communitiesBadgeViewCases);
			//			reportStatusPass(methodName + " Clicked on My Business Cases Link", false, false);
			//			sf.seleU.wait(15000);

			// Click on My Business Cases Link
			sf.seleU.clickElementByJSE(sf.comHome.myBusinessHubLink);
			sf.seleU.wait(15000);
			reportStatusPass(methodName + " Clicked on My Business Hub Link", true, true);

			sf.seleU.switchWindow(2);

			// Verify Quick Links navigates to My Business Cases Header
			//			verifyFieldDisplayed("Communities My Business Cases Header", sf.comMBC.myBusinessCasesHeader);
			//			verifyFieldDisplayed("Communities case Summary Table Header", sf.comMBC.caseSummary_caseSummaryTableHeader);

			// Verfy Clicking on My business Hub Link takes to authentication page
			verifyFieldDisplayed("My Business Cases Link", sf.comHome.communitiesBadgeViewCases);
			// verifyFieldDisplayed("Authentication Page for My Business Hub",
			// sf.comLogin.signInLink);
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();
		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Community Quick Links unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Preferences Link
	 * 
	 *                     Click on MyBusiness Cases Toggle Button
	 */
	public void selectAndClickPreferencesToggle() throws IOException {
		try {

			// Click on Preferences Link
			sf.seleU.clickElementByJSE(sf.comHome.preferencesLink);
			reportStatusPass(methodName + " Clicked on Preferences Link", false, false);
			sf.seleU.wait(15000);

			verifyFieldDisplayed("MyBusiness Hub Associated Products & Services Header",
					sf.comHome.myBusinessHubAssociatedProductsAndServices);
			verifyFieldDisplayed("MyBusiness Cases Toggle ", sf.comHome.myBusinessCasesToggleButton);

			// Click on MyBusiness Cases Toggle Button
			sf.seleU.clickElementByJSE(sf.comHome.myBusinessCasesToggleButton);
			reportStatusPass(methodName + " Clicked on MyBusiness Cases Toggle Button", false, false);
			sf.seleU.wait(5000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Preferences Toggle Links unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Verify Toggle is in display Mode
	 * 
	 *                              Verify Community Badge Displayed
	 */
	public void verifyCommunityBadgeDisplayed() throws IOException, InterruptedException {

		// Verify Toggle is in display Mode
		verifyFieldDisplayed("Toggle Display", sf.comHome.toggleDisplayText);

		// Click on Dashboard
		sf.seleU.clickElementByJSE(sf.comHome.dashboardLink);
		reportStatusPass(methodName + " Clicked on Dashboard Link", false, false);
		sf.seleU.wait(12000);

		// Verify Community Badge Displayed
		verifyFieldDisplayed("Community Badge", sf.comHome.communitiesBadge);
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Verify My Business hub link takes us to the
	 *                              dashboard page
	 * 
	 *                              Verify Community Badge Displayed
	 */
	public void verifyMyBusinessHubLink() throws IOException, InterruptedException {

		// Click on My business Hub
		sf.seleU.clickElementByJSE(sf.comHome.myBusinessHubLink);
		reportStatusPass(methodName + " Clicked on My business Hub Link", false, false);
		sf.seleU.wait(12000);

		// Verify Community Badge Displayed
		verifyFieldDisplayed("Community Badge", sf.comHome.communitiesBadge);
	}

	/**
	 * @throws IOException
	 * 
	 *                     Click on Add More Services Button
	 * 
	 *                     Verify that communities portal is NOT offered as a
	 *                     selectable portal in the Add additional services flow
	 */
	public void verifyAddMoreServices() throws IOException {
		try {

			// Click on Add More Services Button
			sf.seleU.clickElementByJSE(sf.comHome.addMoreServicesButton);
			reportStatusPass(methodName + " Clicked on Add More Services Button", false, false);
			sf.seleU.wait(2000);

			// Verify that communities portal is NOT offered as a selectable portal in the
			// Add additional services flow
			for (int i = 0; i < sf.comHome.addMoreServicesLinks.size(); i++) {

				sf.seleU.ScrolltoElement(sf.comHome.addMoreServicesLinks.get(i));
				if ((!sf.seleU.getTextFromWebElement(sf.comHome.addMoreServicesLinks.get(i))
						.contains(sf.commData.communities))
						&& (!sf.seleU.getTextFromWebElement(sf.comHome.addMoreServicesHeaders.get(i))
								.contains(sf.commData.communities))) {
					reportStatusPass(methodName
							+ " Verified that communities portal is NOT offered as a selectable portal in  the Add additional services flow ",
							true, true);
				} else {
					reportStatusFail( 
							methodName + "Error : Comunities is present in Add additional Service in Preferences",
							true);
				}
			}

			// Close the Pop-Up
			sf.seleU.clickElementByJSE(sf.comHome.cancelButton);
			sf.seleU.wait(2000);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Add more Services (Not containing Communities) is unsuccessful",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 *                              Verify Toggle Display "Hide"
	 * 
	 *                              Verify Community Badge Not Displayed
	 */
	public void verifyCommunityBadgeNotDisplayed() throws IOException, InterruptedException {

		// Verify Toggle Display "Hide"
		verifyFieldDisplayed("Toggle Hide", sf.comHome.toggleHideText);

		// Click on Dashboard
		sf.seleU.clickElementByJSE(sf.comHome.dashboardLink);
		reportStatusPass(methodName + " Clicked on Dashboard Link", false, false);
		sf.seleU.wait(12000);

		// Verify Community Badge Not Displayed
		verifyFieldNotDisplayed("Community Badge", sf.comHome.communitiesBadge);
	}

	/**
	 * @throws IOException
	 * 
	 * 
	 *                     Verify Footer 2020 Rogers Communication
	 * 
	 *                     Verify Privacy Policy Link
	 * 
	 *                     Verify Footer Terms and Conditions Link
	 * 
	 *                     Verify Contact Us Footer Link
	 * 
	 *                     Verify FAQ Link
	 * 
	 *                     Verify Footer Language Toggle
	 * 
	 *                     Verify Footer Line
	 */
	public void verifyCommunityFooter() throws IOException {
		try {

			sf.seleU.scrollToBottom();

			// Verify Footer 2020 Rogers Communication
			verifyFieldDisplayed("Footer 2022 Rogers Communication", sf.comHome.footer2022RogersCommunication);

			// Verify Privacy Policy Link
			verifyFieldDisplayed("Footer Privacy Policy Link", sf.comHome.footerPrivacyPolicyLink);
			sf.seleU.clickElementByJSE(sf.comHome.footerPrivacyPolicyLink);
			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(15000);
			verifyFieldDisplayed("Privacy CRTConsumer Codes Header", sf.comHome.privacyCRTConsumerCodesHeader);
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			// Verify Footer Terms and Conditions Link
			verifyFieldDisplayed("Footer Terms and Conditions Link", sf.comHome.footerTermsAndConditionsLink);
			sf.seleU.clickElementByJSE(sf.comHome.footerTermsAndConditionsLink);
			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Support Terms And Conditions Header", sf.comHome.supportTermsAndConditionsHeader);
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			// Verify Contact Us Footer Link
			verifyFieldDisplayed("Footer Contact Us Link", sf.comHome.footerContactUsLink);
			sf.seleU.clickElementByJSE(sf.comHome.footerContactUsLink);
			sf.seleU.wait(10000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("Contact Us Header", sf.comContactUs.contactUsTextHeader);
			// Page layout changed
			//			verifyFieldDisplayed("email Button", sf.comContactUs.emailButton);
			//			verifyFieldDisplayed("phone Button", sf.comContactUs.phoneButton);
			//			verifyFieldDisplayed("chat Button", sf.comContactUs.chatButton);
			//			
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			// Verify FAQ Link
			verifyFieldDisplayed("Footer FAQs", sf.comHome.footerFAQsLink);
			sf.seleU.clickElementByJSE(sf.comHome.footerFAQsLink);
			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("FAQs Header", sf.comHome.faqsHeader);
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

			// Verify Footer Language Toggle
			verifyFieldDisplayed("Footer Language Toggle", sf.comHome.footerLanguageToggle);

			// Verify Footer Line
			verifyFieldDisplayed("Footer Line", sf.comHome.footerLine);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Community Footer Links unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Contact Us Page
	 * 
	 */
	public void verifyCommunityContactUsPage() throws IOException {
		try {
			sf.seleU.wait(5000);
			sf.seleU.scrollToBottom();

			// Verify Contact Us Footer Link
			verifyFieldDisplayed("Footer Contact Us Link", sf.comHome.footerContactUsLink);
			sf.seleU.clickElementByJSE(sf.comHome.footerContactUsLink);
			sf.seleU.wait(5000);
			sf.seleU.switchWindow(2);
			sf.seleU.wait(5000);
			verifyFieldDisplayed("contact Us Header", sf.comContactUs.contactUsTextHeader);
			verifyFieldDisplayed("email Button", sf.comContactUs.emailButton);
			verifyFieldDisplayed("phone Button", sf.comContactUs.phoneButton);
			verifyFieldDisplayed("chat Button", sf.comContactUs.chatButton);

			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying Community Footer Links unsuccessful", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Open Cases Page in Community
	 * 
	 */
	public void openCommunityCases() throws IOException {

		String methodName = "SFDC_Home@: ";

		try {
			// Open cases URL
			sf.seleU.hardwait(4000);
			sf.seleU.switchWindow(1);
			sf.seleU.closeRecentlyOpenedWindow();
			//			sf.seleU.clickElementByJSE(sf.comContactUs.caseSubmissionCaseSummaryLink);
			//			reportStatusPass(methodName + " Clicked on Case Summary Link", true, false);
			//			
			sf.seleU.openURL(InputData_Communities.communitiesCases_url);
			sf.seleU.wait(10000);

		} catch (Throwable e) {
			reportStatusFail(methodName + " Cannot Open Communities Cases URL", e);
			e.printStackTrace();
		}
	}
	/**
	 * Change to Language French
	Mar. 3, 2022
	 * @throws IOException 
	 */
	
	public void changeLanguage() throws IOException {
		try {
			sf.seleU.clickOnElement(sf.comHome.footerLanguageToggle);
			sf.seleU.hardwait(20000);
			reportStatusPass(methodName + "Clicked on Language Toggle to Display Page in French Language " , true, true);
		} catch (Exception e) {
			reportStatusFail(methodName + " Unsuccessful Language Change", e);
			e.printStackTrace();
		}
	}

	/**
	 * validate the order summary data table
	Feb. 24, 2022
	 * @throws IOException
	 */
	public void validateOrderSummarycommunities() throws IOException {
		try {

			verifyFieldDisplayed("Communities Order Summary Header", sf.comHome.orderSummaryHeader);
			verifyFieldDisplayed("Order Summary Field Order Number", sf.comHome.orderNumberField);
			verifyFieldDisplayed("Order Summary Field Submission", sf.comHome.submissionDateField);
			verifyFieldDisplayed("Order Summary Field Status", sf.comHome.statusField);
			verifyFieldDisplayed("Order Summary Field Services Address", sf.comHome.serviceAddressDevicesField);
			verifyFieldDisplayed("Order Summary Field Services Products", sf.comHome.productsField);

		} catch (Throwable e) {
			reportStatusFail(methodName + "Verifying My Business Orders unsuccessful", e);
			e.printStackTrace();
		}
	}
	/**
	 * Validate pagination for order summary table
	Feb. 24, 2022
	 * @throws IOException
	 */
	public void validatePagination() throws IOException {
		try {
			int i=2;
			if (sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage)) {
				while (sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage)) {
					sf.seleU.clickOnElement(sf.comHome.nextPageButton);
					reportStatusPass(methodName + "Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
					i++;
				}
				i--;
				while (sf.seleU.isElementPresentWebDriverWait(sf.comHome.previousPage)) {
					sf.seleU.clickOnElement(sf.comHome.previousPageButton);
					reportStatusPass(methodName + "Clicked on Previous page in service Addresses Table, Current Page : " + (i-1) , true, true);
					i--;
				}
			}
			else {
				reportStatusPass(methodName + "Pagination is not present as The total addresses present are less than or equla to 5", true, true);

			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Verifying Pagination of Table is unsuccesfull", e);
			e.printStackTrace();
		}

	}
	
	/**
	 * Validate Search Results in Filter Box By Keywords
	Feb. 28, 2022
	 * @throws IOException
	 */

	public void validateSearchResults() throws IOException {
		 String OrderNumber;
		 List<WebElement> elementList = new ArrayList<>();
		try {
			elementList = sf.comHome.orderNumberList;
			OrderNumber = sf.seleU.getTextFromWebElement(elementList.get(0));
			sf.seleU.clickOnElement(sf.comHome.searchInputBox);
			sf.seleU.enterText(sf.comHome.searchInputBox, OrderNumber);
			reportStatusPass(methodName + " Searched random order in filter box by keywords ", true, true);
			for (int i=0; i < OrderNumber.length(); i++ ) {  
				sf.comHome.searchInputBox.sendKeys(Keys.BACK_SPACE);
			}

		} catch (Exception e) {
			reportStatusFail(methodName + " Searching on filter box failed", e);

		}

	}

	/**
	 *  Validate sorting 
	Feb. 28, 2022
	 * @throws IOException
	 */
	public void validateSorting() throws IOException {
		ArrayList<String> obtainedList = new ArrayList<>();
		List<WebElement> elementList = new ArrayList<>();

		String orderNumber,minimumOrderNumber,maximumOrderNumber;
		String min, max;
		int i=2;
		try {
			elementList = sf.comHome.orderNumberList;
			for(WebElement we:elementList){
				orderNumber = sf.seleU.getTextFromWebElement(we);
				//Double.parseDouble(orderNumber);
				obtainedList.add(orderNumber);
			}
			if (sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage)) {
				while (sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage)) {
					sf.seleU.clickOnElement(sf.comHome.nextPageButton);
					elementList = sf.comHome.orderNumberList;
					for(WebElement we:elementList){
						orderNumber = sf.seleU.getTextFromWebElement(we);
						//Double.parseDouble(orderNumber);
						obtainedList.add(orderNumber);
					}
					reportStatusPass(methodName + "Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
					i++;
				}
				i--;
				while (sf.seleU.isElementPresentWebDriverWait(sf.comHome.previousPage)) {
					sf.seleU.clickOnElement(sf.comHome.previousPageButton);
					reportStatusPass(methodName + "Clicked on Previous page in service Addresses Table, Current Page : " + (i-1) , true, true);
					i--;
				}
			}
			min = Collections.min(obtainedList);
			max = Collections.max(obtainedList);
			elementList = sf.comHome.orderNumberList;

			// Validating Maximum Order Number

			sf.seleU.clickOnElement(sf.comHome.orderNumberField);
			maximumOrderNumber = sf.seleU.getTextFromWebElement(elementList.get(0));
			if(max.equalsIgnoreCase(maximumOrderNumber)) {
				reportStatusPass(methodName + " Maximum order Number Extracted " +  maximumOrderNumber, true, true);
			} else  {
				reportStatusFail(methodName + " Wrong Maximum order Number Extracted " +  maximumOrderNumber, true);
			}

			// Validating minimum Order Number

			sf.seleU.clickOnElement(sf.comHome.orderNumberField);
			minimumOrderNumber = sf.seleU.getTextFromWebElement(elementList.get(0));
			if(min.equalsIgnoreCase(minimumOrderNumber)) {
				reportStatusPass(methodName + " Minimum order Number Extracted " +  minimumOrderNumber, true, true);
			} else  {
				reportStatusFail(methodName + " Wrong Minimum order Number Extracted " +  minimumOrderNumber, true);
			}

		} catch (Exception e) {
			reportStatusFail(methodName + " Sorting failed", e);			
		}
	}
	/**
	 * Find a multisite order from list and click on it
	 * By Veda - Feb2022
	 * modified logic to click on arrow for multisite order ---13 Apr 2022....Anukriti Chawla
	 * 
	 * @throws IOException
	 */
	public void validateMasterOrder() throws Exception {
		List<WebElement> elementList = new ArrayList<>();
		String serviceAddress="";
		int i=2;
		boolean found=false;
		try {
//			elementList = sf.comHome.serviceAddressList;
//			for(int k=0; k < elementList.size(); k++){			
//				serviceAddress = sf.seleU.getTextFromWebElement(elementList.get(k));
//				if(serviceAddress.contains("2") || serviceAddress.contains("3")) {
//					sf.seleU.clickOnElement(sf.comHome.orderNumberList.get(k));
//					reportStatusPass(methodName + " Clicked at Master Order on Having Service Address: "+serviceAddress , true, true);
//					found = true;
//					break;
//				}			
//			}
//			
//			if (sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage) || !found) {
//				while (sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage)) {
//					sf.seleU.clickOnElement(sf.comHome.nextPageButton);
//					elementList = sf.comHome.serviceAddressList;
//					for(WebElement we:elementList){
//						serviceAddress = sf.seleU.getTextFromWebElement(we);
//						if(serviceAddress.contains("2") || serviceAddress.contains("3")) {
//							sf.seleU.clickOnElement(sf.comHome.orderNumberList.get(0));
//							reportStatusPass(methodName + " Clicked at Master Order on Service Address: "+serviceAddress , true, true);
//							found = true;
//							break;
//						}					
//					}
//					reportStatusPass(methodName + "Clicked on Next page in service Addresses Table, Current Page : " + i , true, true);
//					i++;
//				}
//			}
			
			//Find Master order on page to click
			do {
				if (sf.seleU.isElementPresent(sf.comHome.orderNumberSubOrderLink)) {
					serviceAddress = sf.seleU.getTextFromWebElement(sf.comHome.orderNumberSubOrderCorresServiceAddress.get(0));
					sf.seleU.clickElementByJSE(sf.comHome.orderNumberSubOrderLink.get(0));
					reportStatusPass(methodName + " Clicked at Master Order on Service Address: "+ serviceAddress , true, true);
					found = true;
					break;
				}
				sf.seleU.clickOnElement(sf.comHome.nextPageButton);
				
			} while(sf.seleU.isElementPresentWebDriverWait(sf.comHome.nextPage));
			sf.seleU.wait(3000);
			if(!found)
				reportStatusFail(methodName + " Not able to find any multisite order", true);
			else {
				// Validate Master Order
				String sizeOfChildOrders =Integer.toString(sf.comMBC.orderNumberLinksListForSubOrder.size()-1);
				if(serviceAddress.contains(sizeOfChildOrders)) {
					reportStatusPass(methodName + " Service Address matched Child OrderProducts: "+sizeOfChildOrders , true, true);
				} else {
					reportStatusFail(methodName + " Service Address Not Matched OrderProducts: "+sizeOfChildOrders, true);
				}
			}
		} catch (Exception e) {
			reportStatusFail(methodName + " Validating Master Order Failed", e);
		}		
	}
	/**
	 * Validates Child Orders
	Mar. 7, 2022
	 * @throws Exception
	 */
	public void validateChildOrders() throws Exception {
		try {
			// Search the Filter Box
			sf.seleU.clickOnElement(sf.comHome.searchBoxChildOrder);
			String childOrder = sf.seleU.getTextFromWebElement(sf.comHome.childOrderNumber.get(1));
			sf.seleU.enterText(sf.comHome.searchBoxChildOrder, childOrder);
			reportStatusPass(methodName + " Searched Child order in filter box by keywords ", true, true);
			for (int j=0; j < childOrder.length(); j++ ) {  
				sf.comHome.searchBoxChildOrder.sendKeys(Keys.BACK_SPACE);
			}
			
			String serviceAddress =  sf.seleU.getTextFromWebElement(sf.comHome.serviceAddresschildOrder.get(0));
			sf.seleU.enterText(sf.comHome.searchBoxChildOrder, serviceAddress);
			reportStatusPass(methodName + " Searched Service Address in filter box by keywords ", true, true);
			for (int j=0; j < serviceAddress.length(); j++ ) {  
				sf.comHome.searchBoxChildOrder.sendKeys(Keys.BACK_SPACE);
			}
			sf.seleU.refreshPage();
			sf.seleU.hardwait(10000);
		} catch (Exception e) {
			reportStatusFail(methodName + " validating child orders Failed", e);
		}	

	}
	
	/**
	 * Validate Child Orders Details
	Mar. 7, 2022
	 * @throws IOException
	 */
	public void validateChildOrderDetails() throws IOException {
		try {
			for (int i=1; i < sf.comHome.childOrderNumber.size(); i++ ) {
				sf.seleU.clickOnElement(sf.comHome.childOrderNumber.get(i));
				sf.seleU.hardwait(10000);
				reportStatusPass(methodName + " Child Order Number: "+sf.seleU.getTextFromWebElement(sf.comHome.childOrder), true, true);
				reportStatusPass(methodName + " Child Account Name: "+sf.seleU.getTextFromWebElement(sf.comHome.accountName), true, true);
				reportStatusPass(methodName + " Order Start Date: "+sf.seleU.getTextFromWebElement(sf.comHome.orderStartDate), true, true);
				reportStatusPass(methodName + " Child Order Status: "+sf.seleU.getTextFromWebElement(sf.comHome.status), true, true);
				reportStatusPass(methodName + " Child Order Monthly charges: "+sf.seleU.getTextFromWebElement(sf.comHome.monthlyFees), true, true);
				reportStatusPass(methodName + " Child Order One Time Charges: "+sf.seleU.getTextFromWebElement(sf.comHome.oneTimeFees), true, true);
				reportStatusPass(methodName + " Child Order Customer Name: "+sf.seleU.getTextFromWebElement(sf.comHome.customerName), true, true);
				reportStatusPass(methodName + " Child Order Product Name: "+sf.seleU.getTextFromWebElement(sf.comHome.productName), true, true);
				sf.seleU.navigateBack();
				sf.seleU.hardwait(10000);
			}
			sf.seleU.refreshPage();
			sf.seleU.clickOnElement(sf.comHome.backToOrderSummary);
			reportStatusPass(methodName + " Navigated to Back Order Summary Table ", true, true);
			
		} catch (Exception e) {
			reportStatusFail(methodName + " validating child orders details Failed", e);
		}
	}
	/**
	 * @throws IOException
	 * 
	 *                     Return Field is enabled/not
	 */
	public static boolean isFieldEnabled(String fieldName, WebElement element) throws IOException {

		boolean enabled = false;

		try {

			if (element.isEnabled()) {
				enabled = true;
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field verification", e);
			e.printStackTrace();
		}
		return enabled;
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (sf.seleU.isElementPresentWebDriverWait(element)) {
				sf.seleU.ScrolltoElementPageCenter(element);
				reportStatusPass(methodName + " Validated " + fieldName + " is displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is not displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Field verification", e);
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Verify Field is displayed
	 */
	public void verifyFieldNotDisplayed(String fieldName, WebElement element) throws IOException {
		try {

			// Verify Field value matches the expected result
			if (!sf.seleU.isElementDisplayed(element)) {
				reportStatusPass(methodName + " Validated " + fieldName + " is Not displayed", true, true);
			} else {
				reportStatusFail(methodName + " " + fieldName + " is  displayed", true);
			}

		} catch (Throwable e) {
			reportStatusFail(methodName + " Error in Field verification", e);
			e.printStackTrace();
		}
	}
}

