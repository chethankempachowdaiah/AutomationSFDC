package com.sfdc.data;

import java.util.HashMap;
import java.util.Hashtable;

import com.framework.utilities.FetchTestData;

public class InputData_WA extends FetchTestData {

	/**
	 * @author Sakshi.Lnu
	 * @Date 29.APRIL.2021 ###### This input data file encloses the all the string
	 *       values ###### Data table column names are also intialized here
	 */
	public static String env = getProperty("env");
	public static String account_Business_R4B = getProperty(env + "_ON_Account");
	public static String contact_Business_R4B = getProperty(env + "_ON_Contact");
	public static String siteContactEmailId = getProperty(env + "_Communities_userid");
	public static String contact_Business_R4B_ATL = getProperty(env + "_ATL_Contact");
	public static String contact_Business_R4B_FraudCheck = getProperty(env + "_fraudCheck_Account");
	public static String parentAccountName;
	// Wireless Products Selection
	public static String productsHeader;
	public static String productsSubHeader;
	public static String productsPlanHeader;
	public static String productsPlanSubHeader;
	public static String product_type;
	public static String vdPlan_size;
	public static String dataOnlyPlan_size;
	public static String voiceAndData;
	public static String dataOnly;
	public static String plan_typeStandalone;
	public static String plan_typePooled;


	// Wireless ADD ONs Type Selection
	public static String addOnType_LD;
	public static String addOnType_SMS;
	public static String addOnType_Roaming;
	public static String addOnType_Voicemail;

	// This WACC ADD On availability for Long Distance only
	public static String LD_NoOffer;
	public static String USLD_WirelessLD;
	public static String IntLD_WirelessIntLD;

	// Wireless Plans Selection Excel sheet Column Names are initialized here

	public static String addOnName_US_LD;
	public static String addOnName_International_LDSaver;
	public static String addOnName_SMS;
	public static String addOnName_Roaming;
	public static String addOnName_Premium_Voicemail;
	public static String addOnName_iPH_Voicemail;

	// Wireless Plan Selection Sheet Column names initialized in this section
	public static String WACC_CreateOpportunity;
	public static String WACC_Product_Type;
	public static String WACC_Data_Type;
	public static String WACC_Plan_Size;
	public static String WACC_Plan_Type;
	public static String WACC_Plan_Price;
	public static String WACC_AddOn_Type;
	public static String WACC_AddOn_Name;
	public static String WACC_AddOn_Availability;
	public static String WACC_AddOn_Price;
	public static String WACC_AddOn_TotalPrice;
	public static String WACC_Credit_Amount_Available;
	public static String WACC_Use_Credit_Option;
	public static String WACC_Plan_Cost;
	public static String WACC_ifCreditAvailable;
	public static String WACC_Total_Cost;
	public static String WACC_Final_TotalCosts;
	public static String WACC_Plan_Quantity;
	public static String WACC_eSignature;
	public static String WACC_CreateAccounts;
	public static String WACC_docType;
	public static String WACC_quoteStatus;
	public static String WACC_requestTypeForNumber;
	public static String WACC_DeviceType;
	public static String WACC_TransferPhoneNumber;
	public static String WACC_DeviceBrand;
	public static String WACC_DeviceName;
	public static String WACC_DeviceFinOption;
	public static String WACC_DeviceCost;
	public static String WACC_DeviceProtectionName;
	public static String WACC_DeviceProtectionCost;
	public static String WACC_AccessoryBrand;
	public static String WACC_AccessoryName;
	public static String WACC_AccessoryCost;
	public static String WACC_AddOn_Status;
	public static String WACC_CasesAndScreenProtectors_Text; 
	public static String WACC_PowerAndCables_Text;
	public static String WACC_DeviceProtectionCaption;
	public static String WACC_AppleCareDescription;
	public static String WACC_DeviceProtDescription;
	public static String WACC_Add_ExtaAddOns;
	public static String[] WACC_AddOnAction;
	public static String[] WACC_Multiple_AddOnPrice;
	public static String[] WACC_Multiple_AddOn;
	public static String[] WACC_ProductsPricesInPDFAfterTransactions;
	public static String[] WACC_ProductsInPDFAfterTransactions;
	public static String[] WACC_ProductsPricesInPDFBeforeTransactions;
	public static String[] WACC_ProductsInPDFBeforeTransactions;
	public static boolean WACC_AddOnAppleCareProductPresent = false;
	public static String[] WACC_Monthly_AddOnPrice_NoAppleCare;
	public static String[] WACC_Select_Effective_DateOption;
	public static String[] WACC_Change_Effective_DateOption;
	public static HashMap<String,String> WACC_Expected_Effective_DateOption = new HashMap<String,String>();
	public static String WACC_PlanName;
	// Footer Price elements
	public static String recurringTCV;
	public static String onetimeTCV;
	public static String totalTCV;
	public static String totalCosts;
	//Device Availability Status
	public static String availabilityInStock;
	public static String availabilityDelayed;
	//Price Plan sequence in AddOn page
	public static String[] pricePlanArray;
	//Device Plan sequence in AddOn page
	public static String[] DevicePlanArray;
	//hide link of added device on cart 
	public static String DevicelinkHidden;
	//plan and device sequesnce in the Shopping Cart
	public static String[] planFirstArray;
	public static String[] deviceFirstArray;
	public static String downloadLocation= getProperty(env + "_PDF_Location");
	
	// macd
	public boolean wacc_BaANTask_StatusFailed = false;
	public boolean wacc_VaidateSubmit_StatusFailed = false;
	public static String oneTimeFeesSubText_MACD;
	public static String recentlyMadeWirelessServiceText;
	public static String language = getProperty("language");
	public InputData_WA() {

		// *****************Wireless Products Selection****************
		productsHeader = "Products selection";
		productsSubHeader = "Browse through the categories of business products to see how our solutions can help.";
		productsPlanHeader = "Rogers Wireless Plans";
		productsPlanSubHeader = " Select your Rogers wireless plan";
		product_type = "Wireless";
		voiceAndData = "Voice and Data";
		vdPlan_size = "25GB";
		plan_typeStandalone = "Standalone";
		plan_typePooled = "Pooled";
		dataOnly = "Data Only";
		dataOnlyPlan_size = "1GB";

		// *************Wireless AddOns Selection**********************
		// Add on Type
		addOnType_LD = "Long Distance";
		addOnType_SMS = "SMS";
		addOnType_Roaming = "Roaming";
		addOnType_Voicemail = "Voicemail";

		// AddOn Names
		addOnName_US_LD = "US LD";
		addOnName_International_LDSaver = "International LD Saver";
		addOnName_SMS = "Unlimited Canada to US/Intl SMS/MMS";
		addOnName_Roaming = "Canada + US";
		addOnName_Premium_Voicemail = "Premium Voicemail to Text";
		addOnName_iPH_Voicemail = "iPhone Visual Voicemail";

		// AddOn Availability for LD Plans
		LD_NoOffer = "No Offer";
		USLD_WirelessLD = "Wireless US LD";
		IntLD_WirelessIntLD = "Wireless International LD Saver";

		// footer elements values
		recurringTCV = "Recurring TCV";
		onetimeTCV = "One Time TCV";
		totalCosts = "Total Costs";
		totalTCV = "Total TCV";

		//Device Availability Status Values
		availabilityInStock = "Available and in stock";
		availabilityDelayed = "Delayed for future shippingThis device is currently not available. You can still add it to your cart and it will be shipped to you in 1-2 weeks.";

		//Price Plan sequence in AddOn page
		pricePlanArray = new String[] {"Rogers wireless plan", "Wireless plan Add ons", "Wireless device","Device Protection","Purchase additional accessories for your device"};

		//Price Plan sequence in AddOn page
		DevicePlanArray = new String[] {"Wireless device", "Device Protection", "Rogers wireless plan","Add extra features to your plan","Purchase additional accessories for your device"};

		//hide link of added device on cart 
		DevicelinkHidden = "Show more details";
		// Getting the column names from excel sheet for Data Providers
		// Function to set Data table data to variables for WACC Regression to use the
		// Data providers

		// *************Shopping Cart**********************
		//Price Plan sequence in the Shopping Cart
		planFirstArray = new String[] {"Fin Business Global: 40GB","with US LD","iPhone 11"};
		//Price Plan sequence in the Shopping Cart
		deviceFirstArray = new String[] {"iPhone 11","Fin Business Global: 40GB","with US LD"};		
		oneTimeFeesSubText_MACD = "Total one-time fees, before tax, for all products and services to appear on your first monthly bill";
		recentlyMadeWirelessServiceText = "You’ll see the changes in fee(s) included on your bill beginning the day the changes in service(s) were made, along with your existing monthly service fee.";
	}
	
	public static String PORT_NUM_ELIGIBILITY_CHECK_DUPLICATE_MSG="PortIn Number already Exists";
	public static String DECLINE_SUCCESS_MSG="You Declined to Sign";
	

	public static void setDataForWACCProducts(Hashtable<String, String> dataTable) {
		try {
			WACC_CreateOpportunity = dataTable.get("Create_Opp");
			WACC_Product_Type = dataTable.get("Product_Type");
			WACC_Data_Type = dataTable.get("Data_Type");
			WACC_Plan_Size = dataTable.get("Plan_Size");
			WACC_Plan_Type = dataTable.get("Plan_Type");
			WACC_Plan_Price = dataTable.get("Plan_Price");
			WACC_AddOn_Type = dataTable.get("AddOn_Type");
			WACC_AddOn_Name = dataTable.get("AddOn_Name");
			WACC_AddOn_Availability = dataTable.get("AddOn_Availability");
			WACC_AddOn_Price = dataTable.get("AddOn_Price");
			WACC_AddOn_TotalPrice = dataTable.get("Total_TCV");
			WACC_Credit_Amount_Available = dataTable.get("Credit_amount_Available");
			WACC_Use_Credit_Option = dataTable.get("Use_CreditAmount");
			WACC_Plan_Cost = dataTable.get("Plan_Cost");
			WACC_ifCreditAvailable = dataTable.get("Credit_Available");
			WACC_Total_Cost = dataTable.get("Total_Cost");
			WACC_Final_TotalCosts = dataTable.get("Final_TotalCosts");
			WACC_Plan_Quantity = dataTable.get("Quantity");
			WACC_eSignature = dataTable.get("e-signature");
			WACC_CreateAccounts = dataTable.get("Create_Accounts");
			WACC_docType = dataTable.get("docType");
			WACC_quoteStatus = dataTable.get("Quote_Status");
			WACC_requestTypeForNumber = dataTable.get("Request_Type");
			WACC_DeviceType = dataTable.get("Device_Type");
			WACC_TransferPhoneNumber = dataTable.get("Phone Number");
			WACC_DeviceBrand = dataTable.get("DeviceBrand");
			WACC_DeviceName = dataTable.get("DeviceName");
			WACC_DeviceFinOption = dataTable.get("DeviceFinOption");
			WACC_DeviceCost = dataTable.get("DeviceCost");
			WACC_DeviceProtectionName = dataTable.get("DeviceProtectionName");
			WACC_DeviceProtectionCost = dataTable.get("DeviceProtectionCost");
			WACC_AccessoryBrand = dataTable.get("AccessoryBrand");
			WACC_AccessoryName = dataTable.get("AccessoryName");
			WACC_AccessoryCost = dataTable.get("AccessoryCost");
			WACC_AddOn_Status = dataTable.get("AddOn_Status");
			WACC_CasesAndScreenProtectors_Text = dataTable.get("CasesAndScreenProtectors_Text");
			WACC_PowerAndCables_Text = dataTable.get("PowerAndCables_Text");
			WACC_DeviceProtectionCaption = dataTable.get("DeviceProtectionCaption");
			WACC_AppleCareDescription = dataTable.get("AppleCareDescription");
			WACC_DeviceProtDescription = dataTable.get("DeviceProtDescription");
		}

		catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @author Pankaj Agarwal
	 *April. 19, 2022
	 *Set the data for the MACD flow wrt to test data
	 **/
	public static void setDataForWACCMacd(Hashtable<String, String> dataTable) {
		try {
			WACC_PlanName = readDataTable(dataTable, "Plan_Name");
			WACC_Plan_Price = readDataTable(dataTable, "Plan_Price");
			WACC_AddOnAction = readDataTable(dataTable, "AddOn_Action").split(",");
			WACC_Multiple_AddOnPrice = readDataTable(dataTable, "Multiple_AddOnPrice").split(",");
		    WACC_Multiple_AddOn  = readDataTable(dataTable, "Multiple_AddOn").split(",");
		    WACC_Monthly_AddOnPrice_NoAppleCare = readDataTable(dataTable, "Monthly_AddOnPrice_NoAppleCare").split(",");
		    WACC_Add_ExtaAddOns = readDataTable(dataTable, "Add_ExtraAddOns");
		    WACC_Select_Effective_DateOption = readDataTable(dataTable, "Select_Effective_DateOption").split(",");
		    WACC_Change_Effective_DateOption = readDataTable(dataTable, "Change_Effective_Date").split(",");
		    WACC_ProductsPricesInPDFAfterTransactions = readDataTable(dataTable, "After transaction_ProductPricesInPDF").split(",");
		    WACC_ProductsInPDFAfterTransactions = readDataTable(dataTable, "AfterTransactionProductsInPDF").split(",");
		    WACC_ProductsInPDFBeforeTransactions = readDataTable(dataTable, "BeforeTransactionProductsInPDF").split(",");
		    WACC_ProductsPricesInPDFBeforeTransactions =  readDataTable(dataTable, "BeforeTransaction_ProductPricesInPDF").split(",");
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @author Pankaj Agarwal
	 *April. 19, 2022
	 **/
	public static String readDataTable(Hashtable<String, String> dataTable , String key) {
		String value =""; 
		try {
			 if(dataTable.get(key)!=null)
				 value = dataTable.get(key);
		 } catch (Exception e) {
			 throw e;
		 }
		return value;
	}

	
	/**
	 * @author Satish.Doraiswamy
	 *Feb. 25, 2022
	 *Enum class declaration for Device Capacity, Device Colour & Payment mode
	 */
	public static enum DeviceCapacity{

		DEVICE128GBCAPACITY("128GB"),DEVICE256GBCAPACITY("256GB"),DEVICE512GBCAPACITY("512GB"),DEVICE1TBCAPACITY("1TB"),DEVICE512GBORMORECAPACITY("512GB or more");

		private String deviceCapacity;

		DeviceCapacity(String deviceCapacity) {
			this.deviceCapacity=deviceCapacity;
		}

		public String getDeviceCapacity() {
			return deviceCapacity;
		}

	}

	public static enum DeviceColour{

		DEVICESILVERCOLOUR("Silver"),DEVICEGOLDCOLOUR("Gold"),DEVICEBLUECOLOUR("Blue"),DEVICEGRAPHITECOLOUR("Graphite");

		private String deviceColour;

		DeviceColour(String deviceColour) {
			this.deviceColour=deviceColour;
		}

		public String getDeviceColour() {
			return deviceColour;
		}

	}

	public static enum DevicePaymentMode{

		DEVICE_PAY_WITH_FINANCING("Pay with financing"),DEVICE_PAY_FULL_PRICE("Pay full price");

		private String devicePaymentMode;

		DevicePaymentMode(String devicePaymentMode) {
			this.devicePaymentMode=devicePaymentMode;
		}

		public String getDevicePaymentMode() {
			return devicePaymentMode;
		}

	}
	
	public static enum RecurringFeesTableColumnPDFDocs{

		PRODUCT("Product"),UNIT_PRICE("Unit price"),QUANTITY("Quantity"),RECURRING_TOTAL("Recurring total")
		,DISCOUNT("Discount"),FINALCHARGES("Final charge");

		private String recurringFeesTableColumnPDFDocs;

		RecurringFeesTableColumnPDFDocs(String recurringFeesTableColumnPDFDocs) {
			this.recurringFeesTableColumnPDFDocs=recurringFeesTableColumnPDFDocs;
		}

		public String getRecurringFeesTableColumnValuesInPDFDocs() {
			return recurringFeesTableColumnPDFDocs;
		}

	}

	public static enum AgreementLineDetails{

		RECURRING_FEES("Recurring fees"),ONE_TIME_FEES("One-time fees"),HARDWARE_FINANCING_CHARGES("Hardware financing charges"),RECURRING_TOTAL("Recurring total")
		,DISCOUNT("Discount"),FINALCHARGES("Final charge");

		private String agreementLineDetails;

		AgreementLineDetails(String agreementLineDetails) {
			this.agreementLineDetails=agreementLineDetails;
		}

		public String getAgreementLineDetails() {
			return agreementLineDetails;
		}

	}
	
	public static enum AgreementOrderDetails{

		PREPARED_FOR("Prepared for:"),ATTENTION("Attention:"),ORDER_SUMMARY("Order summary"),TOTAL_FOR_ALL_LINES("Total for all lines"),PREPARED_BY("Prepared by:")
		,PROPOSAL("Proposal"),QUOTE_NUMBER("Quote number:"),PRESENT_DATE("Presented date:"),VALID_THROUGH("Valid through:");

		private String agreementOrderDetails;

		AgreementOrderDetails(String agreementOrderDetails) {
			this.agreementOrderDetails=agreementOrderDetails;
		}

		public String getAgreementOrderDetails() {
			return agreementOrderDetails;
		}

	}
	
	public static enum AgreementOrderSummaryTableColumnDetails{
// Total Credit & Monthly financing charge are in next line in the PDF dcos, hence using Total & Monthly Keywords for validation
		LINE("Line"),QUANTITY("Quantity"),RECURRING_CHARGE("Recurring charge"),MONTHLY_SAVING("Monthly savings"),ONETIME_CHARGE("One-time charge"),
		ONE_TIME_SAVING("One-time savings"),TOTAL_CREDIT("Total"),MONTHLY_FINANCING_CHARGE("Monthly");

		private String agreementOrderSummaryTableColumnDetails;

		AgreementOrderSummaryTableColumnDetails(String agreementOrderSummaryTableColumnDetails) {
			this.agreementOrderSummaryTableColumnDetails=agreementOrderSummaryTableColumnDetails;
		}

		public String getAgreementOrderSummaryTableColumnDetails() {
			return agreementOrderSummaryTableColumnDetails;
		}

	}
	
	public static enum AgreementTotalForAllLinesTableColumnDetails{
		// Total Credit & Monthly financing charge are in next line in the PDF dcos, hence using Total & Monthly Keywords for validation
		RECURRING_CHARGE("Recurring charge"),MONTHLY_SAVING("Monthly savings"),ONETIME_CHARGE("One-time charge"),
		ONE_TIME_SAVING("One-time savings"),TOTAL_CREDIT("Total"),HARDWARE_DOWN_PAYMENT("Hardware down Payment"),MONTHLY_FINANCING_CHARGE("Monthly");

		private String agreementTotalForAllLinesTableColumnDetails;

		AgreementTotalForAllLinesTableColumnDetails(String agreementTotalForAllLinesTableColumnDetails) {
			this.agreementTotalForAllLinesTableColumnDetails=agreementTotalForAllLinesTableColumnDetails;
		}

		public String getAgreementTotalForAllLinesTableColumnDetails() {
			return agreementTotalForAllLinesTableColumnDetails;
		}

	}
	
	public static enum AgreementOrderTermAndConditionAndURL{

		TERMS_AND_CONDITIONS("Terms & Conditions"),WELCOME_TO_ROGERS("Welcome to Rogers!"),PLEASE_RETAIN_A_COPY("Please retain a copy"),
		PROVISION_OF_THE_SERVICES("The provision of the Services"),TERMSANDCONDITION_URL("business-general-terms-en.pdf");

		private String agreementOrderTermAndConditionAndURL;

		AgreementOrderTermAndConditionAndURL(String agreementOrderTermAndConditionAndURL) {
			this.agreementOrderTermAndConditionAndURL=agreementOrderTermAndConditionAndURL;
		}

		public String getAgreementOrderTermAndConditionAndURL() {
			return agreementOrderTermAndConditionAndURL;
		}

	}
	
	public static enum CreditAndFraudRiskCreditOptios{

		NONE("None"),HIGHRISK_F("High Risk-F"),HIGHRISK_H("High Risk-H"),HIGHRISK_FNF("High Risk-FnF");
		private String creditAndFraudRiskCreditOptios;

		CreditAndFraudRiskCreditOptios(String creditAndFraudRiskCreditOptios) {
			this.creditAndFraudRiskCreditOptios=creditAndFraudRiskCreditOptios;
		}

		public String getCreditAndFraudRiskCreditOptios() {
			return creditAndFraudRiskCreditOptios;
		}

	}
	
	/**
	 * @author Priyanka.Tawade
	 * Mar 16, 2022
	 * Enum class declaration for Discount Device 
	*/
	public static enum DiscountDevice{
		
	DiscountDevicePriceForIphone11("$29.17/mo");

	private String discountDevicePrice;

	DiscountDevice(String discountDevicePrice) {
		this.discountDevicePrice=discountDevicePrice;
		}

		public String getDiscountDevicePrice() {
			return discountDevicePrice;
		}
	
	}
}
