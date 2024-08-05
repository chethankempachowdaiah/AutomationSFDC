package com.sfdc.data;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * @author Anukriti.Chawla
 */
public class InputData_Communities extends InputData {

	// Communities
	public static String communities_admin_url = getProperty(env + "_" + "Communities_adminURL");
	public static String communities_admin_userid = getProperty(env + "_" + "Communities_adminUserid");
	public static String communities_admin_password = getProperty(env + "_" + "Communities_adminPassword");

	public static String communities_url = getProperty(env + "_" + "Communities_appURL");
	public static String communitiesCases_url = getProperty(env + "_" + "Communities_casesURL");
	public static String communities_userid = getProperty(env + "_" + "Communities_userid");
	public static String communities_password = getProperty(env + "_" + "Communities_password_encoded");
	public static String communities_account = getProperty(env + "_" + "Communities_account");
	public static String communities_contact = getProperty(env + "_" + "Communities_contact");

	public static String communities_PBFurl = getProperty(env + "_" + "Communities_PBF_url");

	public String communities_CaseReason;
	public String communities_CaseProduct;

	public static String mailinatorURL = getProperty("mailinatorURL");

	public static String communities_rogers_home = "https://www.rogers.com";
	public static String communities = "communities";


	public static HashMap<String, List<String>> communities_CaseProduct_Reason_Mapping = new HashMap<>();
	public static HashMap<String, List<String>> communities_CaseProd_Reason_MandatoryField_Validations = new HashMap<>();

	public static List<String> communities_Case_ReportTypes;
	public static String communities_Case_Product_Wireless;
	public static String communities_Case_Reason_Bill_Inquiry;
	public static String communities_Case_IMEI_Number;
	public static String imeiPleaseNoteMessage;
	public static String communities_Case_Reason_Bill_Copy;
	public static String communities_Case_Reason_Unlock_Device;
	public static int noOfFilesToBeUploadedInCases;
	public static String accountNumberLabelText;
	public static String invoiceMonthLabel;
	public static List<String> billMonthValues;
	public static List<String> communitiesBusinessAccounts;
	public static String recentCaseProduct;
	public static String recentCaseReason;
	public static String caseReasonBillingInquiry;
	public static String multipleBusAccountCommUser;
	public static String multipleBusAccountCommUserEmail;
	public static String singleBusAccountCommUserEmail;
	public static List<String> communitiesPBFCredentialsForMultiAcc;
	public static List<String> communitiesPBFCredentialsForSingleAcc;
	public static String primaryAccountForCommUser;
	public static String nextButtonEnglish;
	public static String nextButtonFrench;
	public static List<String> noServiceAddressCommUser;

	public static List<String> externalUserPBF;
	public static String floatRightAttributeValue;
	public static String siteSelectionUrlForInternalUser;
	public static String noSiteFoundMessageEnglish;
	public static String noSiteFoundMessageFrench;

	// **************New variables for Digital Regression Scrum Suite *******Remove
	// above once everything in place//

	public static int commCaseSlNo = -1;
	public static String commCaseServiceGroup = "Wireless";
	public static String commCaseRecordType = "Service";
	public static String commCaseProduct = "Business Phone";
	public static String commCaseBilling_TechSupport = "Billing and account management";
	public static String commCaseCategory;
	public static String commCaseReason = "Bill Copy";
	public static String commCaseAccPhoneNumber = getProperty(env + "_" + "commCaseAccPhoneNumber");
	public static String commCaseInvoiceMonth = "Last month";
	public static String commCaseChargeType="NA";
	public static String commCaseIMEINumber = "NA";
	public static String commCaseReportType = "NA";
	public static String commCaseTransferOfResponsibility ="NA";
	public static String commCaseAddAttachment = "Yes";
	public static String commCaseAccountName = getProperty(env + "_" + "commCaseAccountName");
	public static String commCaseNumberColumn;
	public static String commCaseDoFormValidations = "No";
	public static String commCaseDoSalesForceValidations = "No";
	public static String commCaseDoDetailsValidations = "Yes";
	public static String reportTypeTipMessage;
	public static String caseProductBusinessPhone = "Business Phone";
	public static String caseProductDataCentre = "Data Centre & Cloud";
	public static String caseReason = "Other";
	public static String caseCategoryBusinessInternet = "Business Internet (Cable Internet)";
	public static String caseCategoryDedicatedInternet = "Dedicated Internet (Fibre Internet)";
	public static String caseCategoryDarkFibre = "Dark Fibre";
	public static String commCaseSFCategory = "NA";
	public static String commCaseSiteAddress = "NA";
	public static String commCaseSiteContact = "NA";
	public static String caseDesc255Chars = "Description validation - If the input text is greater than 255 characters,"
			+ " truncate the text in the subject field. Truncate at the last full word and add an ellipses (...)"
			+ " at the end. Go to Case details page and validate that subject has been truncated successfully.";
	public static String commCaseIMEINumberForSpecificCase = getProperty("commCaseIMEINumber");
	public static String commCaseServiceID = "NA";
	public static String commCaseCircuitID = "NA";
	// **************//////
	
	//***************PBF Variables ********************///
	
	public static String commPBFUser;
	public static List<String> commPBFServiceAddresses;
	public static int commPBFTotalServiceAddresses;
	public static String dataSortableAttributeName = "data-sortable";
	public static String commPBFselectedAddress;
	public static String commPBFProductSpeedType;
	public static String commPBFAddProductName;
	public static String commPBFAddProductNameFr;
	public static List<String> commPBFBusinessProducts;
	public static List<String> commPBFBusinessProductsFr;
	public static List<String> commPBFBusinessSubProducts;
	public static List<String> commPBFBusinessSubProductsFr;
	public static List<String> commPBFProductSpeeds;
	public static String commPBFadvantageWiFiToolTip;
	public static String commPBFadvantageWiFiToolTipFr;
	public static String commPBFadvantageWiFiLTEBackupToolTip;
	public static String commPBFadvantageWiFiLTEBackupToolTipFr;
	public static String commPBFbusinessInternetToolTip;
	public static String commPBFbusinessInternetToolTipFr;
	public static String commPBFbusinessInternetLTEBackupToolTip;
	public static String commPBFbusinessInternetLTEBackupToolTipFr;
	public static String commPBFGigaBitPlanDesc;
	public static String commPBFGigaBitPlanDescFr;
	public static String commPBF500uPlanDesc;
	public static String commPBF500uPlanDescFr;
	public static String commPBF150uPlanDesc;
	public static String commPBF150uPlanDescFr;
	public static String commPBF30uPlanDesc;
	public static String commPBF30uPlanDescFr;
	public static List<String> commPBFAllPlansOffers;
	public static String commPBFProductPrice;
	public static String commPBFProductPriceWithDiscount;
	public static String discount;
	public static String commPBFInstallationFees;
	public static String commPBFInstallationFeesFr;
	public static String commPBFOneTimeFees;
	public static String commPBFOneTimeFeesFr;
	public static String commPBFReccuringCost;
	public static String commPBFTypeOfAddress;
	public static String commPBFnewsiteaddress;
	public static String commPBFProductTerm;
	public static String commPBFIncludedFeatures;
	public static String commPBFIncludedFeaturesFr;
	public static String commPBFAddSiteContact;
	public static String commPBFPromo;
	public static String commPBFContactFullName;
	public static String commPBFContactPhoneNumber;
	public static String commPBFContactEmailID;
	public static String commPBFSAFullName;
	public static String commPBFSAPhoneNumber;
	public static String commPBFSAEmailID;
	public static String commPBFContactBillingAddress;
	public static String commPBFBusinessGeneralTermURL = "rogers.com/cms/pdf/en/master-business-general-terms-en.pdf";
	public static String commPBFBusinessInternetTermURL = "rogers.com/cms/pdf/en/business-fixed-network-terms-en.pdf";
	public static String commPBFBusinessTVTermURL = "rogers.com/cms/pdf/en/business-productivity-terms-en.pdf";
	public static String commPBFDedicatedInternetTermURL = "rogers.com/cms/pdf/en/business-fixed-network-terms-en.pdf";
	public static String commPBFOff365TermURL = "rogers.com/cms/pdf/en/business-productivity-terms-en.pdf";
	public static String commPBFCreditCheckRequired;
	public static String commPBFFraudCheckRequired;
	public static String commPBFSiteAddress;
	public static String commPBFAddNewSite;
	public static String commPBFSiteAddressStreetNo;
	public static String commPBFSiteAddressStreetName;
	public static String commPBFSiteAddressStreetType;
	public static String commPBFSiteAddressCity;
	public static String commPBFSiteAddressPostalCode;
	public static String commPBFSiteAddressProvince;
	public static int commPBFNumberOfClosedLostOpp;
	public static String commPBFAddNewContact;
	
	public static String commPBFContactFirstName;
	public static String commPBFContactLastName;
	public static String commPBFESignature;
	public static String commPBFEditEthernetPlan;
	public static String commPBFEthernetPlanE100Cost;
	public static String commPBFEthernetPlanE1000Cost;
	public static String commPBFCurrentEthernetPlanCost;
	public static String commPBFCurrentEthernetPlan;
	public static String commPBFTcName;
	public static List<String> commPBFOff365AdOnsList;
	public static String commPBFOffAddOnName;
	public static String commPBFOffAddOnPrice = "0";
	public static List<String> commPBFTVAddOnsList;
	public static String commPBFTVAddOnName;
	public static String commPBFTVEditAddOnName;
	public static String commPBFTVAddOnPrice;
	public static int commPBFTVAddOnQty = 0;
	public static String commPBFTVAddOnInstallationPrice;
	public static String discountProduct;
	public static String discountProductFr;
	public static String discountPrice;
	
	public static List<String> ontarioRegionProvinces = new ArrayList<>();
	public static List<String> atlanticRegionProvinces = new ArrayList<>();
	
	public static String commPBFBundledFirstProd;

	public static int commPBFOffAddOnQty = 0;
	public static String commPBFCostToUseOfficeAddOn;
	public static List<String> commPBFTVProducts;
	public static List<String> commPBFTVSubProducts;
	public static String commPBFAddTVProductName;
	public static String commPBFTVOneTimeFees;
	public static String commPBFTVProductPrice;
	public static String commPBFCostToUseTVAddOn;
	public static String commPBFCostToUseTV;
	
	public static String commPBFRDISpecSheetBillAddressSelect;

	/////***********Multisite-PBF Variables********///////
	public static String mulPBFUser;
	public static String mulPBFCreditCheckRequired;
	public static String mulPBFFraudCheckRequired;
	public static String mulPBFAddNewSite;
	public static String mulPBFESignature;
	public static String mulPBFTcName;
	public static int mulPBFNumOfSites = 1;
	public static String mulPBFAddSiteContact;
	
	//New Site Details
	public static String mulPBFSiteAddressStreetNo;
	public static String mulPBFSiteAddressStreetName;
	public static String mulPBFSiteAddressStreetType;
	public static String mulPBFSiteAddressCity;
	public static String mulPBFSiteAddressPostalCode;
	public static String mulPBFSiteAddressProvince;
	
	//Site Product Details
	public static HashMap<String, HashMap<String,String>> sitesData = new HashMap<>();
	public static HashMap<String, String> selectedAddress = new HashMap<>();
	public static String mulPBFSiteTypeOfAddress;
	public static String mulPBFSiteProductName;
	public static String mulPBFSiteProductTerm;
	public static String mulPBFSiteProductPlanName;
	public static String mulPBFSiteProductAddOnName;
	public static String mulPBFSiteProductAddOnQty;
	public static String mulPBFSiteProductIncludedFeatures;
	
	

	public InputData_Communities() {

		communities_CaseReason = "Product Inquiry";
		communities_CaseProduct = "Internet of Things";

		communities_Case_ReportTypes = new ArrayList<String>();
		communities_Case_Product_Wireless = "Wireless";
		communities_Case_Reason_Bill_Inquiry = "Bill Inquiry";
		communities_Case_Reason_Bill_Copy = "Bill Copy";
		communities_Case_Reason_Unlock_Device = "Unlock Device";
		communities_Case_IMEI_Number = getProperty("communities_Case_IMEI_Number");
		imeiPleaseNoteMessage = "(If the $50 unlocking fee applies to your account, you will receive a notification prior to completing this request.)";
		noOfFilesToBeUploadedInCases = 2;
		accountNumberLabelText = "Account Number";
		invoiceMonthLabel = "Bill Month";
		billMonthValues = new ArrayList<String>();

		if(getProperty(env + "_communitiesBusinessAccounts")!=null)
			communitiesBusinessAccounts = new ArrayList<>(
				Arrays.asList(getProperty(env + "_communitiesBusinessAccounts").split(",")));

		caseReasonBillingInquiry = "Billing Inquiry";
		multipleBusAccountCommUserEmail = getProperty("multipleBusAccountCommUserEmail");

		
		if (getProperty(env + "_" +"multipleBusAccountCommUser")	!=null)
			communitiesPBFCredentialsForMultiAcc = new ArrayList<>(
				Arrays.asList( getProperty(env + "_" +"multipleBusAccountCommUser").split(",")));
		

		singleBusAccountCommUserEmail = getProperty("singleBusAccountCommUserEmail");
		noServiceAddressCommUser = new ArrayList<>(Arrays.asList(getProperty("noServiceAddressCommUser").split(",")));
		if(getProperty("singleBusAccountCommUser")!=null)	
			communitiesPBFCredentialsForSingleAcc = new ArrayList<>(
				Arrays.asList(getProperty("singleBusAccountCommUser").split(",")));
		nextButtonEnglish = "Next";
		nextButtonFrench = "Suivant";
		externalUserPBF = new ArrayList<>(Arrays.asList(getProperty("externalUser").split(",")));

		floatRightAttributeValue = "float--right";
		siteSelectionUrlForInternalUser = getProperty("siteSelectionUrlForInternalUser");
		noSiteFoundMessageEnglish = "No sites found\n" + "Select \"Add new site\" above to add a location";
		noSiteFoundMessageFrench = "Aucun emplacement trouvé\n"
				+ "Sélectionnez « Ajouter un nouvel emplacement » ci-dessus pour ajouter un emplacement";
		reportTypeTipMessage = "(More information on report types can be found in the Rogers Business Self-Serve app at https://bss.rogers.com)";
	}

	public List<String> setComm_Case_ReportTypes() {

		communities_Case_ReportTypes.add("Upgrade Eligibility Report");
		communities_Case_ReportTypes.add("Usage Report");
		communities_Case_ReportTypes.add("Cancellation Fee Report");
		return communities_Case_ReportTypes;

	}

	public HashMap setComm_CaseProduct_Reason_MappingService() {

		communities_CaseProduct_Reason_Mapping.put("Wireless",
				new ArrayList<>(Arrays.asList("Manage WLS Services", "Bill Copy", "Bill Inquiry", "Unlock Device",
						"Reports", "Account Update", "Contract Inquiry", "Manage Equipment", "Manage Paging Features",
						"Manage WLS Features", "Online Portal", "Payment", "Product Inquiry")));
		communities_CaseProduct_Reason_Mapping.put("Internet",
				new ArrayList<>(Arrays.asList("Bill Copy", "Bill Inquiry", "Reports", "Other")));
		communities_CaseProduct_Reason_Mapping.put("Business Phone",
				new ArrayList<>(Arrays.asList("Bill Copy", "Bill Inquiry", "Reports", "Other")));
		communities_CaseProduct_Reason_Mapping.put("Data Centre & Cloud", new ArrayList<>(Arrays.asList("Other")));
		communities_CaseProduct_Reason_Mapping.put("Internet of Things",
				new ArrayList<>(Arrays.asList("Bill Copy", "Bill Inquiry", "Account Update", "Online Portal",
						"Manage Services", "Contract Inquiry", "Manage Access Features", "Product Inquiry")));
		communities_CaseProduct_Reason_Mapping.put("SaaS",
				new ArrayList<>(Arrays.asList("Bill Copy", "Bill Inquiry", "Manage Services", "Contract Inquiry",
						"Manage G Suite Features", "Product Inquiry", "Manage GoDaddy Features", "Manage O365 Features",
						"Online Portal")));
		communities_CaseProduct_Reason_Mapping.put("Enterprise Mobility Management",
				new ArrayList<>(Arrays.asList("Bill Copy", "Bill Inquiry", "Online Portal", "Manage Services",
						"Contract Inquiry", "Manage EMM Features", "Product Inquiry")));
		return communities_CaseProduct_Reason_Mapping;

	}

	public HashMap setComm_CaseProduct_Reason_MappingTechnicalSupport() {

		communities_CaseProduct_Reason_Mapping.put("Wireless",
				new ArrayList<>(Arrays.asList("Call Completion", "Data Connectivity", "Degraded Services",
						"Dropped Calls", "Other Wireless Support", "SMS/MMS – Send Or Receive Messages")));
		communities_CaseProduct_Reason_Mapping.put("Internet", new ArrayList<>(Arrays.asList("Configuration Request",
				"Degraded Services", "Other Internet Support", "Other Network Support", "Out Of Service")));
		communities_CaseProduct_Reason_Mapping.put("Business Phone", new ArrayList<>(Arrays
				.asList("Configuration Request", "Degraded Services", "Other Technical Support", "Out Of Service")));
		return communities_CaseProduct_Reason_Mapping;

	}

	public HashMap setMandatoryField_Validation_List() {

		communities_CaseProd_Reason_MandatoryField_Validations.put("Wireless",
				new ArrayList<>(Arrays.asList("Unlock Device", "Reports")));
		communities_CaseProd_Reason_MandatoryField_Validations.put("All",
				new ArrayList<>(Arrays.asList("Bill Copy", "Bill Inquiry")));

		return communities_CaseProd_Reason_MandatoryField_Validations;

	}

	public List<String> setBillMonthList() {

		billMonthValues.add("Last month");
		billMonthValues.add("Last 3 months");
		billMonthValues.add("Last 6 Months");
		billMonthValues.add("Last 12 Months");
		billMonthValues.add("Last 18 months");

		return billMonthValues;
	}
	
	public static List<String> setOntarioProvinces() {

		ontarioRegionProvinces.add("ON");
		ontarioRegionProvinces.add("BC");
		ontarioRegionProvinces.add("QC");
		ontarioRegionProvinces.add("AB");

		return ontarioRegionProvinces;
	}
	
	public static List<String> setAtlanticProvinces() {

		atlanticRegionProvinces.add("NB");
		atlanticRegionProvinces.add("PE");
		atlanticRegionProvinces.add("NS");
		atlanticRegionProvinces.add("NL");

		return billMonthValues;
	}

	// Function to set Datatable data to variables for Digital Scrum Regression
	// Suite
	public static void setDataForCreateCase(Hashtable<String, String> dataTable) {
		try {

			commCaseSlNo = Integer.parseInt(dataTable.get("Serial No"));
			commCaseServiceGroup = dataTable.get("Service Group");
			commCaseRecordType = dataTable.get("Record Type");
			commCaseAccountName = dataTable.get("Account Name" + "_" + env);
			commCaseProduct = dataTable.get("Product");
			commCaseBilling_TechSupport = dataTable.get("Billing_TechSupport");
			commCaseCategory = dataTable.get("Category");
			commCaseReason = dataTable.get("Reason");
			commCaseAccPhoneNumber = dataTable.get("Acc_Phone_Number" + "_" + env);
			commCaseInvoiceMonth = dataTable.get("Invoice Month");
			commCaseIMEINumber = dataTable.get("IMEI Number");
			commCaseReportType = dataTable.get("Report Type");
			commCaseTransferOfResponsibility = dataTable.get("TOR Value");
			commCaseChargeType = dataTable.get("Charge Type");
			commCaseAddAttachment = dataTable.get("Add Attachment");
			commCaseNumberColumn = "Case Number";
			commCaseDoFormValidations = dataTable.get("Field Validations");
			commCaseDoSalesForceValidations = dataTable.get("Salesforce Valdations");
			commCaseDoDetailsValidations = dataTable.get("Case Details Verification");
			commCaseSFCategory = dataTable.get("Category_SF");
			commCaseSiteAddress = dataTable.get("Site Address");
			commCaseSiteContact = dataTable.get("Site Contact");
			commCaseServiceID = dataTable.get("Service ID");
			commCaseCircuitID = dataTable.get("Circuit ID");

		} catch (Exception e) {
			throw e;
		}

	}
	
	// Function to set Datatable data to variables for Digital Scrum Regression
		// Suite
		public static void setDataForPBFE2E(Hashtable<String, String> dataTable) throws Exception {
			try {

				sf.dataInput.businessAccountName = readDataTable(dataTable, env + "_BusinessAccount");
				commPBFUser = readDataTable(dataTable, "User");
				
				if (!readDataTable(dataTable, "Service Addresses").isEmpty())
					commPBFServiceAddresses = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Service Addresses").split(","))) ;
				if (!readDataTable(dataTable, "Total Service Addresses").isEmpty())
				commPBFTotalServiceAddresses = Integer.parseInt(readDataTable(dataTable, "Total Service Addresses"));
					
				commPBFProductSpeedType = readDataTable(dataTable, "Product Speed Type");
				commPBFAddProductName = readDataTable(dataTable, "Add Product Name");
				commPBFAddProductNameFr = readDataTable(dataTable, "Add Product Name Fr");
				if (!readDataTable(dataTable, "Business Products").isEmpty())
					commPBFBusinessProducts = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Business Products").split(","))) ;
				if (!readDataTable(dataTable, "Business Products Fr").isEmpty())
					commPBFBusinessProductsFr = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Business Products Fr").split(","))) ;
				if (!readDataTable(dataTable, "Product List").isEmpty())
					commPBFBusinessSubProducts = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Product List").split(","))) ;
				if (!readDataTable(dataTable, "Product List Fr").isEmpty())
					commPBFBusinessSubProductsFr = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Product List Fr").split(","))) ;
				if (!readDataTable(dataTable, "Speed Of Products").isEmpty())
					commPBFProductSpeeds = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Speed Of Products").split(","))) ;
				commPBFadvantageWiFiToolTip = readDataTable(dataTable, "Advantage WiFi ToolTip");
				commPBFadvantageWiFiToolTipFr = readDataTable(dataTable, "Advantage WiFi ToolTip Fr");
				commPBFadvantageWiFiLTEBackupToolTip = readDataTable(dataTable, "Advantage WiFi LTE backup ToolTip");
				commPBFadvantageWiFiLTEBackupToolTipFr = readDataTable(dataTable, "Advantage WiFi LTE backup ToolTip Fr");
				commPBFbusinessInternetToolTip = readDataTable(dataTable, "Business Internet ToolTip");
				commPBFbusinessInternetToolTipFr = readDataTable(dataTable, "Business Internet ToolTip Fr");
				commPBFbusinessInternetLTEBackupToolTip = readDataTable(dataTable, "Business Internet LTE Backup ToolTip");
				commPBFbusinessInternetLTEBackupToolTipFr = readDataTable(dataTable, "Business Internet LTE Backup ToolTip Fr");
				commPBFGigaBitPlanDesc = readDataTable(dataTable, "GigaBit Plan Description");
				commPBFGigaBitPlanDescFr = readDataTable(dataTable, "GigaBit Plan Description Fr");
				commPBF500uPlanDesc = readDataTable(dataTable, "500u Plan Description");
				commPBF500uPlanDescFr = readDataTable(dataTable, "500u Plan Description Fr");
				commPBF150uPlanDesc = readDataTable(dataTable, "150u Plan Description");
				commPBF150uPlanDescFr = readDataTable(dataTable, "150u Plan Description Fr");
				commPBF30uPlanDesc = readDataTable(dataTable, "30u Plan Description");
				commPBF30uPlanDescFr = readDataTable(dataTable, "30u Plan Description Fr");
				if (!readDataTable(dataTable, "All Plans Offers").isEmpty())	
					commPBFAllPlansOffers = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "All Plans Offers").split(","))) ;
				commPBFProductPrice = readDataTable(dataTable, "Product Price");
				commPBFProductPriceWithDiscount= readDataTable(dataTable, "Discounted Product Price");
				commPBFInstallationFees = readDataTable(dataTable, "Installation Fees");
				commPBFInstallationFeesFr = readDataTable(dataTable, "Installation Fees Fr");
				commPBFOneTimeFees =  readDataTable(dataTable, "One Time Fees");
				commPBFOneTimeFeesFr =  readDataTable(dataTable, "One Time Fees Fr");
				commPBFReccuringCost = readDataTable(dataTable, "Cost To Use");
				commPBFTypeOfAddress = readDataTable(dataTable, "Type of Address");
				commPBFProductTerm =  readDataTable(dataTable, "Product Term");
				commPBFPromo = readDataTable(dataTable, "Promo");
				commPBFIncludedFeatures = readDataTable(dataTable, "Included Features");
				commPBFIncludedFeaturesFr = readDataTable(dataTable, "Included Features Fr");
				commPBFAddSiteContact = readDataTable(dataTable, "Add Site Contact");
				commPBFContactFullName = readDataTable(dataTable, "Contact Full Name");
				commPBFContactPhoneNumber = readDataTable(dataTable, "Contact Phone Number");
				commPBFContactEmailID = readDataTable(dataTable, "Contact EmailID");
				commPBFSAFullName = readDataTable(dataTable, "Signing Authority Full Name");
				commPBFSAPhoneNumber = readDataTable(dataTable, "Signing Authority Phone Number");
				commPBFSAEmailID = readDataTable(dataTable, "Signing Authority EmailID");
				commPBFContactBillingAddress = readDataTable(dataTable, "Contact Billing Address");
				commPBFCreditCheckRequired = readDataTable(dataTable, "Credit Check");
				commPBFFraudCheckRequired = readDataTable(dataTable, "Fraud Check");
				commPBFSiteAddress = readDataTable(dataTable, "Site Address");
				commPBFAddNewSite = readDataTable(dataTable, "Add New Site");
				commPBFSiteAddressStreetNo = readDataTable(dataTable, "Street No");
				commPBFSiteAddressStreetName = readDataTable(dataTable, "Street Name");
				commPBFSiteAddressStreetType = readDataTable(dataTable, "Street Type");
				commPBFSiteAddressCity = readDataTable(dataTable, "City");
				commPBFSiteAddressPostalCode = readDataTable(dataTable, "Postal Code");
				commPBFSiteAddressProvince = readDataTable(dataTable, "Province");
				
				commPBFnewsiteaddress=commPBFSiteAddressStreetNo+" "+commPBFSiteAddressStreetName+" "+commPBFSiteAddressStreetType+" "+commPBFSiteAddressCity+" "+commPBFSiteAddressPostalCode;
				
				if (!readDataTable(dataTable, "No of Closed Lost Opp").isEmpty())
					commPBFNumberOfClosedLostOpp = Integer.parseInt(readDataTable(dataTable, "No of Closed Lost Opp"));
				commPBFAddNewContact = readDataTable(dataTable, "Add New Contact");
				commPBFContactFirstName = readDataTable(dataTable, "Contact First Name");
				commPBFContactLastName = readDataTable(dataTable, "Contact Last Name");
				commPBFESignature = readDataTable(dataTable, "E-Signature");
				commPBFEditEthernetPlan = readDataTable(dataTable, "Edit Ethernet Plan");
				commPBFEthernetPlanE100Cost = readDataTable(dataTable, "E100 Access Cost");
				commPBFEthernetPlanE1000Cost = readDataTable(dataTable, "E1000 Access Cost");
				commPBFCurrentEthernetPlan = readDataTable(dataTable, "Current Ethernet Plan");
				
				if (commPBFCurrentEthernetPlan.equalsIgnoreCase("E100"))
					commPBFCurrentEthernetPlanCost = commPBFEthernetPlanE100Cost;
				else
					commPBFCurrentEthernetPlanCost = commPBFEthernetPlanE1000Cost;
				
				commPBFTcName = readDataTable(dataTable, "TCName");
				
				if(commPBFTcName!=null)
					com.framework.base.MyListener.reportStatusPass("Info@:"+ commPBFTcName, true, true);
				
				if (!readDataTable(dataTable, "Office365 AddOn List").isEmpty())
					commPBFOff365AdOnsList = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "Office365 AddOn List").split(","))) ;
				
				if (!readDataTable(dataTable, "TV Product List").isEmpty())
				commPBFTVProducts = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "TV Product List").split(","))) ;
				
				if (!readDataTable(dataTable, "TV SubProduct List").isEmpty())
				commPBFTVSubProducts = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "TV SubProduct List").split(","))) ;

				commPBFTVAddOnsList = new ArrayList<>(
						Arrays.asList(readDataTable(dataTable, "TV Addons List").split(","))) ;
				
				commPBFTVAddOnName = readDataTable(dataTable, "TV Addon Add");
				commPBFTVEditAddOnName = readDataTable(dataTable, "Edit TV Addon");

				
				commPBFOffAddOnName = readDataTable(dataTable, "Office365 AddOn");
				
				if (!readDataTable(dataTable, "Office365 AddOn Price").isEmpty())
					commPBFOffAddOnPrice = readDataTable(dataTable, "Office365 AddOn Price");
				
				if (!readDataTable(dataTable, "Office365 AddOn Qty").isEmpty())
					commPBFOffAddOnQty = Integer.parseInt(readDataTable(dataTable, "Office365 AddOn Qty"));
				
				commPBFCostToUseOfficeAddOn = readDataTable(dataTable, "Cost To Use Office AddOn");
				commPBFAddTVProductName = readDataTable(dataTable, "Add TV Product");
				commPBFTVOneTimeFees = readDataTable(dataTable, "One Time Fees TV");
				commPBFTVProductPrice = readDataTable(dataTable, "TV Product Price");
				commPBFTVAddOnPrice = readDataTable(dataTable, "TV AddOn Price");
				
				commPBFCostToUseTVAddOn = readDataTable(dataTable, "Cost to Use TV AddOn");
				commPBFCostToUseTV = readDataTable(dataTable, "Cost To Use TV");
				
				if (!readDataTable(dataTable, "TV AddOn Qty").isEmpty())
					commPBFTVAddOnQty = Integer.parseInt(readDataTable(dataTable, "TV AddOn Qty"));
				
				commPBFTVAddOnInstallationPrice = readDataTable(dataTable, "TV AddOn Installation Price");
				
				discountProduct = readDataTable(dataTable, "Discount Product");
				discountProductFr = readDataTable(dataTable, "Discount Product Fr");
				
				discountPrice = readDataTable(dataTable, "DiscountPrice");
				
				commPBFBundledFirstProd = readDataTable(dataTable, "First Bundled Product");
				discount = readDataTable(dataTable, "Discount");
				commPBFRDISpecSheetBillAddressSelect = readDataTable(dataTable, "Select Billing Address On SpecSheet Page");
				
				setOntarioProvinces();
				setAtlanticProvinces();
			
			} catch (Exception e) {
				throw e;
			}

		}
		///****** By Anukriti - 19-Jan-2022  *****////
		// Function to set Datatable data to variables for Digital Scrum Regression Suite
		public static void setDataForMultisitePBFE2E(Hashtable<String, String> dataTable) throws Exception {
			try {
				
				sf.dataInput.businessAccountName = readDataTable(dataTable, env + "_BusinessAccount");
				mulPBFUser = readDataTable(dataTable, "User");
				mulPBFAddSiteContact = readDataTable(dataTable, "Add Site Contact");
				mulPBFCreditCheckRequired = readDataTable(dataTable, "Credit Check");
				mulPBFFraudCheckRequired = readDataTable(dataTable, "Fraud Check");
				mulPBFAddNewSite = readDataTable(dataTable, "Add New Site");
				mulPBFESignature = readDataTable(dataTable, "E-Signature");
				mulPBFTcName = readDataTable(dataTable, "TCName");
				mulPBFNumOfSites = Integer.parseInt(readDataTable(dataTable, "No. Of Sites"));
						
				//Read multiple sites data into a hashmap
				for (int i=1; i<=mulPBFNumOfSites; i++) {
					
					HashMap<String, String> siteData = new HashMap<>();
					
					siteData.put("Type Of Address", readDataTable(dataTable, "Site" + i + "_TypeOfAddress"));
					siteData.put("Product Name", readDataTable(dataTable, "Site" + i + "_ProductName"));
					siteData.put("Product Term", readDataTable(dataTable, "Site" + i + "_ProductTerm"));
					siteData.put("Product Plan Name", readDataTable(dataTable, "Site" + i + "_ProductPlanName"));
					siteData.put("Product AddOn Name", readDataTable(dataTable, "Site" + i + "_ProductAddOnName"));
					siteData.put("Product AddOn Qty", readDataTable(dataTable, "Site" + i + "_ProductAddOnQty"));
					siteData.put("Product Included Features", readDataTable(dataTable, "Site" + i + "_ProductIncludedFeatures"));
					sitesData.put("Site" + i, siteData);
				}
				setOntarioProvinces();
				setAtlanticProvinces();
			
				commPBFESignature = readDataTable(dataTable, "E-Signature");
				commPBFFraudCheckRequired = readDataTable(dataTable, "Fraud Check");
				commPBFCreditCheckRequired = readDataTable(dataTable, "Credit Check");
			} catch (Exception e) {
				throw e;
			}

		}
	
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
}

