package scrum.wa.test;

import org.testng.annotations.Test;

import com.framework.base.MyListener;
import com.sfdc.data.InputData;
import com.sfdc.data.InputData_WA;
import com.sfdc.lib_pages.SFDC_AllPages;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class WA_TC_15_SummaryPage_FinancedDevice extends MyListener{
	SFDC_AllPages sfdc;
	SFDC_AllPageObjects sf;
	String DeviceFinPriceDetail, FinanceTimeDetailPge;

	@Test(priority=1)
	public void validate_SummaryPage_FinancedDevice() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		reachTillProductsSelectnpage("Iphone XR",1);
		DeviceFinPriceDetail = sf.shopWADevobj.financedPrice.getText().replaceAll("[^0-9.]", "");
		FinanceTimeDetailPge = sf.shopWADevobj.financeTenure.getText().replaceAll("[^a-zA-Z]", "");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectAddOn.validateAccordionSeqDeviceFirst(InputData_WA.pricePlanArray);
		sfdc.selectAddOn.clickOnShopCart();
		sfdc.shopCart.fetchAddedDeviceDetails("Iphone XR", DeviceFinPriceDetail, FinanceTimeDetailPge,"FinanceFlow");
		sfdc.shopCart.hideDetailsOfDevice(InputData_WA.DevicelinkHidden);
		sfdc.shopCart.updateCartQtyPlans("1");
		sfdc.reOrder.UpdtngQtyinOrdrSumryPge();
		sfdc.home.logout();
	}

	@Test(priority=2)
	public void addPlanwithFullPriceofDevice() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		reachTillProductsSelectnpage("iPhone 12 Pro",1);
		sfdc.shopWADevcs.selectDevicePaywithFullPrice();
		DeviceFinPriceDetail = sf.shopWADevobj.fullPriceOfDevce.getText();
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectAddOn.validateAccordionSeqDeviceFirst(InputData_WA.pricePlanArray);
		sfdc.selectAddOn.clickOnShopCart();
		sfdc.shopCart.fetchAddedDeviceDetails("iPhone 12 Pro", DeviceFinPriceDetail, FinanceTimeDetailPge,"NA");
		sfdc.shopCart.updateCartQtyPlans("1");
		sfdc.home.logout();
	}

	@Test(priority=3)
	public void addPlanwithDiscountedPriceofDevice() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		reachTillProductsSelectnpage("iPhone 12 Mini",1);
		sfdc.shopWADevcs.changeFinanceTenure();
		DeviceFinPriceDetail = sf.shopWADevobj.financedPrice.getText().replaceAll("[^0-9.]", "");
		FinanceTimeDetailPge = sf.shopWADevobj.financeTenure.getText().replaceAll("[^a-zA-Z]", "");
		sfdc.shopWADevcs.clickAddToCartBtnDevListPage();
		sfdc.shopWADevcs.skipWithAddingDevceProtetcion();
		sfdc.selectAddOn.validateAccordionSeqDeviceFirst(InputData_WA.pricePlanArray);
		sfdc.selectAddOn.clickOnShopCart();
		sfdc.shopCart.fetchAddedDeviceDetails("iPhone 12 Mini", DeviceFinPriceDetail, FinanceTimeDetailPge,"FinanceFlow");
		sfdc.shopCart.verifyStrikedOutPriceCart();
	}

	@Test(priority=4)
	public void addBYodDevicewithDifferentaddoNs() throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		reachTillProductsSelectnpage("iPhone 12 Mini",2);
		//		sfdc.selectAddOn.expandAddOnsWindow();
		sfdc.selectAddOn.selectRoamingPlans();
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		//		sfdc.selectAddOn.expandAddOnsWindow();
		sfdc.selectAddOn.validateSMSPlans();
		sfdc.selectAddOn.clickOnAddToCartAddOns();
		sfdc.selectAddOn.navigateToBYOD();
		sfdc.selectAddOn.clickOnShopCart();
		sfdc.shopCart.checkonetimefeesInCart();
	}

	public void reachTillProductsSelectnpage(String DeviceModel, int count) throws Exception {
		sfdc = new SFDC_AllPages();
		sf = new SFDC_AllPageObjects();
		// ***************LOGIN AS AE***********************//
		sfdc.login.loginToSFDC(InputData.Profile_AE);
		sfdc.home.closeTabIfOpen();
		sfdc.accDetails.searchAccountFromGlobSearch(InputData_WA.account_Business_R4B, "Business");
		sfdc.accRelated.createOpportunity();
		sfdc.cOpp.enterOpportunityDetails();
		sfdc.cOpp.selecetExistingContactInOpportunity(InputData_WA.contact_Business_R4B);
		sfdc.cQuote.clickCreateQuotePbfButton();
		sfdc.selectPro.verifyWirelessProducts();
		sfdc.selectPro.selectDataPlanAndType("Voice and Data", "Pooled", "40GB");
		//sfdc.selectPro.clickOnPlansAddToCart();
		sfdc.selectPro.continueToAddOnsButton();
		if (count ==1) {
		sfdc.selectAddOn.validateLongDistancePlans("Long Distance", "US LD", "No Offer");
		sf.seleU.clickElementByJSE(sf.addOnSel.addToCart);
			sfdc.selectAddOn.clickOnContinueToDevice();
			sfdc.shopWADevcs.selectWirelessDevice("Apple", DeviceModel,"PostWirelessPlans");  
		}
	}
}