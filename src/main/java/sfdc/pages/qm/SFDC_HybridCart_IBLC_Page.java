package sfdc.pages.qm;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.framework.base.Global;
import com.framework.base.MyListener;
import com.sfdc.page_objects.SFDC_AllPageObjects;

public class SFDC_HybridCart_IBLC_Page extends MyListener {

	// Creating all the pages Object to interact with pages
	public static SFDC_AllPageObjects sf;
	public String methodName;

	public SFDC_HybridCart_IBLC_Page() {
		sf = new SFDC_AllPageObjects();
		methodName = "SFDC_Hybrid Cart IBLC@: ";
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws Exception
	 * 
	 *                     Verify IBLC Recurring Price/Cost, One time charge/cost
	 *                     etc as provided in dataTable for IBLC Offer( basic,
	 *                     standard, pro), Promo( 9 Promos-promo1, promo2, promo3)
	 *                     and IBLC Bundle ( 5 Internet product * 3 offer= 15 )
	 */
	public void verify_IBLC_Offer_Promo_Bundle_HybridCart(Hashtable<String, String> dataTable)
			throws IOException, Exception {

		verify_IBLC_Price_Cost_Total(dataTable);
		verify_TechnicianInstall_PhoneLines_Jacks(dataTable);
		sf.seleU.hardwait(6000);
		if (dataTable.get("Product_Type").equals("IBLC + Int Bundle")) {
			verify_InternetIBLCBundle_Price_Cost_OneTimeChargeCost(dataTable);
		}

		//verify_LongDistancePlans(dataTable);
		verify_AdministrativeFeaturesGroup(dataTable);

		if (dataTable.get("Product_Type").equals("Promo") && dataTable.get("Product_Type").equals("Offer")) {
			verify_VoiceFeaturesGroup(dataTable);
		}
		verify_DistinctiveRing(dataTable);
		verify_SpecialAndBlockingFeaturesGroup(dataTable);
		verify_911Emergency_MsgRelayService(dataTable);
		//verify_TelephonyModem_UnlistedNumber_DirectoryListing(dataTable);
		
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws Exception
	 * 
	 *                     Verify IBLC product price , cost and cost total
	 */
	public void verify_IBLC_Price_Cost_Total(Hashtable<String, String> dataTable) throws IOException, Exception {

		try {
			sf.seleU.hardwait(10000);	
			// Verify IBLC product price , cost and cost total
			verifyFieldValue(dataTable.get("IBLC_Product"),
					product_Attribute(dataTable.get("IBLC_Product"), Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("IBLC_Price"));

			verifyFieldValue(dataTable.get("IBLC_Product"),
					product_Attribute(dataTable.get("IBLC_Product"), Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("IBLC_Cost"));

			verifyFieldValue(dataTable.get("IBLC_Product"),
					product_Attribute(dataTable.get("IBLC_Product"), Global.dataInput.recurringCostTotal, 8),
					"$" + dataTable.get("IBLC_Cost Total"));
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for IBLC product price , cost and cost total", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * 
	 *                     Verify IBLC+Internet product price , cost
	 * 
	 *                     Verify IBLC+Internet product one time charge and one time
	 *                     cost
	 */
	public void verify_InternetIBLCBundle_Price_Cost_OneTimeChargeCost(Hashtable<String, String> dataTable)
			throws IOException {

		try {
			sf.seleU.hardwait(10000);	
			// Verify IBLC+Internet product price , cost
			verifyFieldValue(dataTable.get("Internet_Product"),
					product_Attribute(dataTable.get("Internet_Product"), Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("IBLC_Intenet_Price"));
			sf.seleU.hardwait(10000);
			verifyFieldValue(dataTable.get("Internet_Product"),
					product_Attribute(dataTable.get("Internet_Product"), Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("IBLC_Intenet_Cost"));
			// Verify IBLC+Internet product one time charge and one time cost
			verifyFieldValue(dataTable.get("Internet_Product"),
					product_Attribute(dataTable.get("Internet_Product"), Global.dataInput.oneTimeCharge, 6),
					"$" + dataTable.get("IBLC_Intenet_OT_Charge"));
			verifyFieldValue(dataTable.get("Internet_Product"),
					product_Attribute(dataTable.get("Internet_Product"), Global.dataInput.oneTimeCost, 10),
					"$" + dataTable.get("IBLC_Intenet_OT_Cost"));
			sf.seleU.hardwait(5000);	
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for IBLC product price , cost and cost total", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 *                   1. Verify for IBLC offer Default Long distance plan is
	 *                   "Business Phone Long Distance Plan" and for IBLC
	 *                   Promo/bundle Default Long Distance Plan is "Unlimited North
	 *                   American LD"
	 * 
	 *                   2. Add Long Distance Plan and Verify Price and cost for the
	 *                   same
	 * 
	 *
	 */
	public void verify_LongDistancePlans(Hashtable<String, String> dataTable) throws Exception {

		try {

			// Verify IBLC Default Long Distance Plan in Hybrid Cart
			if (dataTable.get("Product_Type").equals("Offer")) {

				verify_DefaultLongDistancePlan(Global.dataInput.iblcLDP_BusinessPhoneLongDistancePlan,
						dataTable.get("Product_Type"));

				// Add Long Distance Plan
				sf.seleU.clickElementByJSE(
						product_AddToCartButton(dataTable.get(Global.dataInput.iblcLongDistancePlans)));
				sf.seleU.hardwait(6000);

			} else {
				verify_DefaultLongDistancePlan(Global.dataInput.iblcLDP_UnlimitedNorthAmericanLD,
						dataTable.get("Product_Type"));
			}

			// Verify Long Distance Plan
			verifyFieldValue(dataTable.get(Global.dataInput.iblcLongDistancePlans),
					product_Attribute(dataTable.get(Global.dataInput.iblcLongDistancePlans),
							Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("LDP_Price"));
			verifyFieldValue(dataTable.get(Global.dataInput.iblcLongDistancePlans),
					product_Attribute(dataTable.get(Global.dataInput.iblcLongDistancePlans),
							Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("LDP_Cost"));

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Long Distance Plan in Hybrid Cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 *                   Add Administrative Features Group to Cart
	 * 
	 *                   Configure Administrative Features Group Attributes Call
	 *                   Block/Toll Block/Toll Block - International
	 * 
	 *                   Verify for each attribute 10.00$ is reflected in price and
	 *                   0.00$ in cost
	 */
	public void verify_AdministrativeFeaturesGroup(Hashtable<String, String> dataTable) throws Exception {

		try {

			// Add Administrative Features Group
			sf.seleU.clickElementByJSE(product_AddToCartButton(Global.dataInput.iblcAdministrativeFeaturesGroup));
			sf.seleU.hardwait(6000);

			// select show actions dropdown
			sf.seleU.clickElementByJSE(sf.iblcCart.administrativeFeaturesGroup_ShowActions_Button);
			reportStatusPass(methodName + " Clicked on Show Actions Dropdown for Administrative Features Group", true,
					false);

			// Configure the Product
			sf.seleU.clickElementByJSE(sf.iblcCart.administrativeFeaturesGroup_Configure_Button);
			sf.seleU.wait(15000);

			// Verify Attributes listed under Administrative Features Group Configuration
			if (sf.iblcCart.administrativeFeaturesGroup_IBLC_Attributes.size() == 3) {
				reportStatusPass(
						methodName + " Verified there are 3 attributes present for Administrative Features Group", true,
						false);
			} else {
				reportStatusFail(methodName + " Missing Attribute for Administrative Features Group Configuration",
						true);
			}

			// Verify and add Operator Call Block/Toll Block/Toll Block - International
			String[] afg_list = dataTable.get(Global.dataInput.iblcAdministrativeFeaturesGroup).trim().split("#");
			for (int i = 0; i < sf.iblcCart.administrativeFeaturesGroup_IBLC_Attributes.size(); i++) {
				for (int j = 0; j < afg_list.length; j++) {

					if (sf.seleU.getTextFromWebElement(sf.iblcCart.administrativeFeaturesGroup_IBLC_Attributes.get(i))
							.trim().equals(afg_list[j].trim())) {
						sf.seleU.wait(20000);
						sf.seleU.clickElementByJSE(sf.iblcCart.administrativeFeaturesGroup_IBLC_Attributes.get(i));
						sf.seleU.wait(20000);
						break;
					}
				}
			}
			sf.seleU.wait(20000);

			// Verify Administrative Features Group Price/Cost updated (10.00 $ for each)
			verifyFieldValue(Global.dataInput.iblcAdministrativeFeaturesGroup,
					added_Product_Attribute(Global.dataInput.iblcAdministrativeFeaturesGroup,
							Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("AFP_Price"));
			sf.seleU.wait(10000);
			verifyFieldValue(Global.dataInput.iblcAdministrativeFeaturesGroup,
					added_Product_Attribute(Global.dataInput.iblcAdministrativeFeaturesGroup,
							Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("AFP_Cost"));

			sf.seleU.clickElementByJSE(sf.iblcCart.iblcAttributes_Configuration_CloseButton);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Administrative Features Group Price/Cost in Hybrid Cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 *                   Configure Voice Features Group Attributes
	 * 
	 *                   Verify for each attribute 6.00$ is reflected in price and
	 *                   0.00$ in cost ( above the free voice features)
	 * 
	 */
	public void verify_VoiceFeaturesGroup(Hashtable<String, String> dataTable) throws Exception {

		try {

			// select show actions dropdown
			sf.seleU.clickElementByJSE(sf.iblcCart.voiceFeaturesGroup_ShowActions_Button);
			reportStatusPass(methodName + " Clicked on Show Actions Dropdown for Voice Features Group", true, false);

			// Configure the Product
			sf.seleU.clickElementByJSE(sf.iblcCart.voiceFeaturesGroup_Configure_Button);
			sf.seleU.hardwait(6000);

			// Verify Attributes listed under Voice Features Group Configuration
			if (sf.iblcCart.voiceFeaturesGroup_IBLC_Attributes.size() == 12) {
				reportStatusPass(methodName + " Verified there are 12 attributes present for Voice Features Group",
						true, false);
			} else {
				reportStatusFail(methodName + " Missing Attribute for Voice Features Group Configuration", true);
			}

			// Verify and add respective voice feature attribute
			int vfg_qty = Integer.parseInt(dataTable.get("Voice Features Group_Qty"));

			for (int j = 0; j < vfg_qty; j++) {
				sf.seleU.clickElementByJSE(sf.iblcCart.voiceFeaturesGroup_IBLC_Attributes.get(j));
			}
			sf.seleU.enterText(sf.iblcCart.voiceFeaturesGroup_NoOfSelectedAttr_Input,
					dataTable.get("Voice Features Group_Qty"));
			sf.seleU.enterText(sf.iblcCart.voiceFeaturesGroup_NoOfSelectedAttr_Input, Keys.TAB);
			sf.seleU.hardwait(10000);

			// Verify Voice Features Group Price/Cost updated (6.00 $ for each)
			verifyFieldValue(Global.dataInput.iblcVoiceFeaturesGroup,
					added_Product_Attribute(Global.dataInput.iblcVoiceFeaturesGroup, Global.dataInput.recurringCharge,
							3),
					"$" + dataTable.get("VFG_Price"));

			verifyFieldValue(Global.dataInput.iblcVoiceFeaturesGroup,
					added_Product_Attribute(Global.dataInput.iblcVoiceFeaturesGroup, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("VFG_Cost"));

			sf.seleU.clickElementByJSE(sf.iblcCart.iblcAttributes_Configuration_CloseButton);

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Voice Features Group Price/Cost in Hybrid Cart", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 *                   1. Add Distinctive Ring to cart
	 * 
	 *                   2.Verify Distinctive Ring Price/Cost updated (6.00 $ for
	 *                   each)
	 * 
	 *                   3. Update Distinctive Ring Qunatity(max 3 can be added)
	 * 
	 *                   4. Verify Distinctive Ring Price total updated (6.00 $ *
	 *                   Quantity)
	 */
	public void verify_DistinctiveRing(Hashtable<String, String> dataTable) throws Exception {

		try {

			// Add Distinctive Ring
			if (sf.seleU.isElementDisplayed(product_AddToCartButton(Global.dataInput.iblcDistinctiveRing))) {
				sf.seleU.clickElementByJSE(product_AddToCartButton(Global.dataInput.iblcDistinctiveRing));
				sf.seleU.hardwait(5000);
			}
			// Verify Distinctive Ring Price/Cost updated (6.00 $ for each)
			verifyFieldValue(Global.dataInput.iblcDistinctiveRing,
					product_Attribute(Global.dataInput.iblcDistinctiveRing, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("DR_Price"));

			verifyFieldValue(Global.dataInput.iblcDistinctiveRing,
					product_Attribute(Global.dataInput.iblcDistinctiveRing, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("DR_Cost"));

			// Update Distinctive Ring Quantity

			try {
				sf.seleU.clickElementByJSE(sf.iblcCart.distinctiveRingQtyInput);				
				sf.seleU.clearText(sf.iblcCart.distinctiveRingQtyInput);	
				sf.seleU.hardwait(10000);			
				sf.seleU.clearAndEnterText(sf.iblcCart.distinctiveRingQtyInput, dataTable.get("Distinctive Ring_Qty"));
				
				reportStatusPass(methodName + " Updated distinctiveRing quantity as "
						+ dataTable.get("Distinctive Ring_Qty"), true, false);
				sf.seleU.enterText(sf.iblcCart.distinctiveRingQtyInput, Keys.TAB);
			} catch (StaleElementReferenceException e) {
			}
//			try {
//				sf.seleU.enterText(sf.iblcCart.distinctiveRingQtyInput, dataTable.get("Distinctive Ring_Qty"));
//				sf.seleU.enterKeys();
//			} catch (StaleElementReferenceException e) {
//			}
			sf.seleU.wait(20000);

			// Verify Distinctive Ring Price/Cost updated (6.00 $ for each)
			verifyFieldValue(Global.dataInput.iblcDistinctiveRing,
					product_Attribute(Global.dataInput.iblcDistinctiveRing, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("DR_Price"));

			verifyFieldValue(Global.dataInput.iblcDistinctiveRing,
					product_Attribute(Global.dataInput.iblcDistinctiveRing, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("DR_Cost"));

			// Verify Distinctive Ring Price total updated (6.00 $ * Quantity)
			verifyFieldValue(Global.dataInput.iblcDistinctiveRing,
					product_Attribute(Global.dataInput.iblcDistinctiveRing, Global.dataInput.recurringChargeTotal, 4),
					"$" + dataTable.get("DR_Total Price"));

		} catch (

		Throwable e) {
			reportStatusFail(" Verification Failure for Distinctive Ring Price/Cost in Hybrid Cart", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws Exception
	 * 
	 *                     Verify Special and Blocking Features Group (Free
	 *                     Features.)
	 */
	public void verify_SpecialAndBlockingFeaturesGroup(Hashtable<String, String> dataTable) throws IOException {

		try {

			if (sf.seleU.isElementDisplayed(
					product_AddToCartButton(Global.dataInput.iblcSpecialandBlockingFeaturesGroup))) {
				sf.seleU.clickElementByJSE(
						product_AddToCartButton(Global.dataInput.iblcSpecialandBlockingFeaturesGroup));
				sf.seleU.hardwait(6000);
			}

			// Verify Special and Blocking Features Group (Free Features.)
			verifyFieldValue(Global.dataInput.iblcSpecialandBlockingFeaturesGroup,
					product_Attribute(Global.dataInput.iblcSpecialandBlockingFeaturesGroup,
							Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("Special and Blocking Features Group Price"));

			verifyFieldValue(Global.dataInput.iblcSpecialandBlockingFeaturesGroup,
					product_Attribute(Global.dataInput.iblcSpecialandBlockingFeaturesGroup,
							Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("Special and Blocking Features Group Cost"));

		} catch (Throwable e) {
			reportStatusFail(
					" Verification Failure for Special and Blocking Features Group (Free Features.) in Hybrid Cart", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws Exception
	 * 
	 *                     Verify 9-1-1 Emergency Access Recurring Charge/Cost
	 * 
	 *                     Verify Message Relay Service Recurring Charge/Cost
	 */
	public void verify_911Emergency_MsgRelayService(Hashtable<String, String> dataTable) throws IOException {

		try {

			// Verify 9-1-1 Emergency Access Recurring Charge
			verifyFieldValue(Global.dataInput.iblc911EmergencyAccess,
					product_Attribute(Global.dataInput.iblc911EmergencyAccess, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("9-1-1 Emergency Access Price"));

			// Verify 9-1-1 Emergency Access Recurring Cost
			verifyFieldValue(Global.dataInput.iblc911EmergencyAccess,
					product_Attribute(Global.dataInput.iblc911EmergencyAccess, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("9-1-1 Emergency Access Cost"));

			// Verify Message Relay Service Recurring Charge
			verifyFieldValue(Global.dataInput.iblcMessageRelayService,
					product_Attribute(Global.dataInput.iblcMessageRelayService, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("Message Relay Service_Price"));

			// Verify Message Relay Service Recurring Cost
			verifyFieldValue(Global.dataInput.iblcMessageRelayService,
					product_Attribute(Global.dataInput.iblcMessageRelayService, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("Message Relay Service_Cost"));

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for 9-1-1 Emergency Access/Message Relay Service in Hybrid Cart",
					e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws IOException
	 * @throws Exception
	 * 
	 *                     Verify Telephony Modem Recurring Charge and Recurring
	 *                     Cost(0.00 $)
	 * 
	 *                     Verify Unlisted Number Recurring Charge (2.00 $ ) and
	 *                     cost (0.00 $)
	 * 
	 *                     Verify Directory Listing Recurring Charge and Recurring
	 *                     Cost (0.00 $)
	 */
	public void verify_TelephonyModem_UnlistedNumber_DirectoryListing(Hashtable<String, String> dataTable)
			throws IOException {

		try {

			// Verify Telephony Modem Recurring Charge(0.00 $)
			verifyFieldValue(Global.dataInput.iblcTelephonyModem,
					product_Attribute(Global.dataInput.iblcTelephonyModem, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("Telephony Modem_Price"));

			// Verify Telephony Modem Recurring Cost (0.00 $)
			verifyFieldValue(Global.dataInput.iblcTelephonyModem,
					product_Attribute(Global.dataInput.iblcTelephonyModem, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("Telephony Modem_Cost"));

			// Verify Unlisted Number Recurring Charge (2.00 $ )
			verifyFieldValue(Global.dataInput.iblcUnlistedNumber,
					product_Attribute(Global.dataInput.iblcUnlistedNumber, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("Unlisted Number Price"));

			// Verify Unlisted Number Recurring Cost (0.00 $ )
			verifyFieldValue(Global.dataInput.iblcUnlistedNumber,
					product_Attribute(Global.dataInput.iblcUnlistedNumber, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("Unlisted Number Cost"));

			// Verify Directory Listing Recurring Charge(0.00 $)
			verifyFieldValue(Global.dataInput.iblcDirectoryListing,
					product_Attribute(Global.dataInput.iblcDirectoryListing, Global.dataInput.recurringCharge, 3),
					"$" + dataTable.get("Directory Listing Price"));

			// Verify Directory Listing Recurring Cost (0.00 $)
			verifyFieldValue(Global.dataInput.iblcDirectoryListing,
					product_Attribute(Global.dataInput.iblcDirectoryListing, Global.dataInput.recurringCost, 7),
					"$" + dataTable.get("Directory Listing Cost"));

		} catch (Throwable e) {
			reportStatusFail(
					" Verification Failure for Telephony Modem/Unlisted Number/Directory Listing in Hybrid Cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param dataTable
	 * @throws Exception
	 * 
	 *                   select show actions dropdown for Technician Install - Phone
	 *                   Lines and configure No. Of Lines
	 * 
	 *                   Verify Technician Install - Phone Lines Price/Cost updated
	 *                   based on no . of lines selected
	 * 
	 *                   Update Technician Install - Jacks Quantity
	 * 
	 *                   Verify Technician Install - Jacks Price/Cost updated (35.00
	 *                   $ for each)
	 * 
	 *                   Verify Technician Install - Jacks Price total updated
	 *                   (35.00 $ * Quantity)
	 */
	public void verify_TechnicianInstall_PhoneLines_Jacks(Hashtable<String, String> dataTable) throws Exception {

		try {

			// select show actions dropdown for Technician Install - Phone Lines
			sf.seleU.clickElementByJSE(sf.iblcCart.technicalInstallPhoneLines_ShowActions_Button);
			reportStatusPass(methodName + " Clicked on Show Actions Dropdown for Technician Install - Phone Lines",
					true, false);

			// Configure the Product for Technician Install - Phone Lines
			sf.seleU.clickElementByJSE(sf.iblcCart.technicalInstallPhoneLines_Configure_Button);
			sf.seleU.hardwait(6000);

			// Select No of lines for Technician Install - Phone Lines
			sf.seleU.selectValueFromDropDown(sf.iblcCart.technicalInstallPhoneLines_NumberOfLines_Select,
					dataTable.get("Technician Install - Phone Lines_Qty"));
			reportStatusPass(methodName + " Confgired no of lines for Technician Install - Phone Lines as "
					+ dataTable.get("Technician Install - Phone Lines_Qty"), true, false);
			sf.seleU.hardwait(6000);
			sf.seleU.clickElementByJSE(sf.iblcCart.iblcAttributes_Configuration_CloseButton);

			sf.seleU.wait(6000);

			// Verify Technician Install - Phone Lines Price/Cost updated
			verifyFieldValue(Global.dataInput.iblcTechnicalInstallPhoneLines,
					product_Attribute(Global.dataInput.iblcTechnicalInstallPhoneLines, Global.dataInput.oneTimeCharge,
							5),
					"$" + dataTable.get("TI_PL_OT_Price"));

			verifyFieldValue(Global.dataInput.iblcTechnicalInstallPhoneLines,
					product_Attribute(Global.dataInput.iblcTechnicalInstallPhoneLines, Global.dataInput.oneTimeCost, 9),
					"$" + dataTable.get("TI_PL_OT_Cost"));

			// Update Technician Install - Jacks Quantity
			sf.seleU.clearAndEnterText(sf.iblcCart.technicalInstallJacks_QtyInput, dataTable.get("Jack_Qty"));
			reportStatusPass(methodName + " Updated jack quantity  for Technician Install -Jacks as "
					+ dataTable.get("Jack_Qty"), true, false);
			sf.seleU.enterText(sf.iblcCart.technicalInstallJacks_QtyInput, Keys.TAB);
			sf.seleU.clickElementByJSE(sf.iblcCart.monthlyRecurringTotalText);
			sf.seleU.hardwait(10000);

			// Verify Technician Install - Jacks Price/Cost updated (35.00 $ for each)
			verifyFieldValue(Global.dataInput.iblcTechnicalInstallJacks,
					product_Attribute(Global.dataInput.iblcTechnicalInstallJacks, Global.dataInput.oneTimeCharge, 5),
					"$" + dataTable.get("Technician Install - Jacks_OT_Charge"));

			verifyFieldValue(Global.dataInput.iblcTechnicalInstallJacks,
					product_Attribute(Global.dataInput.iblcTechnicalInstallJacks, Global.dataInput.oneTimeCost, 9),
					"$" + dataTable.get("Technician Install - Jacks_OT_Cost"));

			// Verify Technician Install - Jacks Price total updated (35.00 $ * Quantity)
			verifyFieldValue(Global.dataInput.iblcTechnicalInstallJacks,
					product_Attribute(Global.dataInput.iblcTechnicalInstallJacks, Global.dataInput.oneTimeChargeTotal,
							6),
					"$" + dataTable.get("Jack_Total_OT_Charge"));

			verifyFieldValue(Global.dataInput.iblcTechnicalInstallJacks,
					product_Attribute(Global.dataInput.iblcTechnicalInstallJacks, Global.dataInput.oneTimeCostTotal,
							10),
					"$" + dataTable.get("Jack_Total_OT_Cost"));

		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for TechnicianInstall Phone Lines/Jacks in Hybrid Cart", e);
			e.printStackTrace();
		}

	}

	/**
	 * @param defaultPlan
	 * @param productType
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 * 
	 *                              Verify Default Long Distance Plan for differnt
	 *                              IBLC offer, promo etc
	 * 
	 *                              Delete the default long distance plan
	 */
	public void verify_DefaultLongDistancePlan(String defaultPlan, String productType) throws IOException {

		try {

			if (product_Attribute(defaultPlan, Global.dataInput.recurringCharge, 3) != null) {
				reportStatusPass(methodName + " Verified that " + defaultPlan
						+ " is the default Long Distnace Plan for IBLC " + productType, true, true);
			} else {
				reportStatusFail(methodName + " Invalid Default Long Distance plan for IBLC " + productType, true);

			}

			if (defaultPlan.equals(Global.dataInput.iblcLDP_BusinessPhoneLongDistancePlan)) {

				// select show actions dropdown
				sf.seleU.clickElementByJSE(sf.iblcCart.businessPhoneLongDistancePlan_ShowActions_Button);
				reportStatusPass(
						methodName + " Clicked on Show Actions Dropdown for IBLC Business Phone Long Distance Plan",
						true, false);

				// Delete the Product
				sf.seleU.clickElementByJSE(sf.iblcCart.businessPhoneLongDistancePlan_Delete_Button);

				// Hit Delete button
				sf.seleU.wait(2000);
				sf.seleU.clickElementByJSE(sf.iblcCart.deleteButton);
				reportStatusPass(methodName + " Deleted IBLC Long Distance Default Plan from cart", true, false);
				sf.seleU.wait(15000);
			}
		} catch (Throwable e) {
			reportStatusFail(" Verification Failure for Default Long Distance Plan in Hybrid Cart", e);
			e.printStackTrace();
		}
	}

	/**
	 * @param product
	 * @param attributeColumn
	 * @return
	 * 
	 *         Get IBLC Hybrid cart Element based on the product and attribute
	 *         column
	 * @throws IOException
	 * 
	 * @throws Exception
	 */
	public WebElement product_Attribute(String product, String columnName, int attributeColumn) throws IOException {

		WebElement prod_attr_ele = null;
		String attr_locator = "//span[text()='" + product + "']//ancestor::div[@class='cpq-item-base-product']//div["
				+ attributeColumn + "]//span//span";
		
		try {
			prod_attr_ele = sf.seleU.findElement(By.xpath(attr_locator));

			if (prod_attr_ele != null) {

				sf.seleU.ScrolltoElement(prod_attr_ele);
				sf.seleU.highLightElement(prod_attr_ele);

				System.out.println(
						columnName + " for " + product + " is " + sf.seleU.getTextFromWebElement(prod_attr_ele));

			}
		} catch (Throwable e) {
			reportStatusFail(" Error in locating product with locator :" + attr_locator, e);
			e.printStackTrace();
		}
		return prod_attr_ele;
	}

	/**
	 * @param product
	 * @param attributeColumn
	 * @return
	 * 
	 *         Get IBLC Hybrid cart Element based on the product and attribute
	 *         column
	 * 
	 * @throws Exception
	 */
	public WebElement added_Product_Attribute(String product, String columnName, int attributeColumn) throws Exception {

		WebElement prod_attr_ele = null;
		String attr_locator = "//span[text()='" + product
				+ "']//ancestor::div[@class='cpq-item-base-product cpq-item-selected']//div[" + attributeColumn
				+ "]//span//span";

		try {
			prod_attr_ele = sf.seleU.findElement(By.xpath(attr_locator));

			if (prod_attr_ele != null) {

				sf.seleU.ScrolltoElement(prod_attr_ele);
				sf.seleU.highLightElement(prod_attr_ele);

				System.out.println(
						columnName + " for " + product + " is " + sf.seleU.getTextFromWebElement(prod_attr_ele));

			}
		} catch (Throwable e) {
			reportStatusFail(" Error in locating product with locator :" + attr_locator, e);
			e.printStackTrace();
		}
		return prod_attr_ele;

	}

	/**
	 * @param product
	 * @return
	 * @throws Exception
	 * 
	 *                   Return Add to Cart Button for the respective product
	 *                   Selected
	 */
	public WebElement product_AddToCartButton(String product) throws Exception {

		WebElement prod_attr_ele = null;
		String attr_locator = "//span[text()='" + product
				+ "']//ancestor::div[@class='cpq-item-base-product']//div[12]//button";

		try {
			prod_attr_ele = sf.seleU.findElement(By.xpath(attr_locator));

			if (prod_attr_ele != null) {

				sf.seleU.ScrolltoElement(prod_attr_ele);
				sf.seleU.highLightElement(prod_attr_ele);

			}
		} catch (Throwable e) {
			reportStatusFail(" Error in locating product with locator :" + attr_locator, e);
			e.printStackTrace();
		}
		return prod_attr_ele;

	}

	/**
	 * @param fieldName
	 * @param ele
	 * @param expectedValue
	 * @throws IOException
	 * 
	 *                     Verify Expected field value is matching actual field
	 *                     value
	 */
	public void verifyFieldValue(String fieldName, WebElement ele, String expectedValue) throws IOException {
		try {

			String actualValue = sf.seleU.getTextFromWebElement(ele);

			if (actualValue.equals(expectedValue)) {
				reportStatusPass(methodName + " Validated product " + fieldName + " as  " + expectedValue, true, true);
			} else {
				reportStatusFail(methodName + " Expected value for product " + fieldName + " is " + expectedValue
						+ " Actual value for Product " + fieldName + " is " + actualValue, true);
			}

		} catch (Throwable e) {
			reportStatusFail(" Error in Field value verification", e);
			e.printStackTrace();
		}
	}

}
