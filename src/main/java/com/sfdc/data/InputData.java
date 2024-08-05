package com.sfdc.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.DateTimeUtilities;
import com.framework.utilities.FetchTestData;

public class InputData extends FetchTestData {

	public static String env = getProperty("env");
	public static String oldOrg = getProperty("oldorg");
	public static String operatingSystem = getProperty("operatingSystem");
	public static String automationTool = getProperty("automationTool");
	public static String applicationName = getProperty("applicationName");
	public static String dataFile = getProperty("dataFilePath");
	
	public static String sauceLabsUserName = getProperty("SauceLabs_userName");
	public static String sauceLabsAccessKey = getProperty("SauceLabs_accessKey");
	public static String sauceLabsURL = "https://" + sauceLabsUserName + ":" + sauceLabsAccessKey + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	 
	public static String browser = getProperty("browser");
	public static String testType = getProperty("testType");
	public static String project = getProperty("project");
	public static String release = getProperty("release");
	public static String userAvailable = getProperty("userAvailable");
	public static String mailableReport = getProperty("mailableReport");
	public static String insertRecordToDB = getProperty("insertResultToDB");
	public static String frenchEnabled = getProperty("frenchEnabled");

	public static String url = getProperty(env + "_" + "appURL");
	public static String b2BUrl = getProperty(oldOrg + "_" + "appURL");
	public static String userid_ae = getProperty(env + "_" + "userid_AE");
	public static String userid_delivery = getProperty(env + "_" + "userid_Delivery");
	public static String userid_opsmanager = getProperty(env + "_" + "userid_OpsManager");
	public static String userid_salesmanager = getProperty(env + "_" + "userid_SalesManager");
	public static String userid_service = getProperty(env + "_" + "userid_Service");
	public static String userid_datagov = getProperty(env + "_" + "userid_DataGovernance");
	public static String userid_businessAdmin = getProperty(env + "_" + "userid_BusinessAdmin");
	public static String userid_contractSupport = getProperty(env + "_" + "userid_ContractSupport");
	public static String userid_contractManager = getProperty(env + "_" + "userid_ContractManager");
	public static String userid_fraudOps = getProperty(env + "_" + "userid_FraudOps");
	public static String userid_creditOps = getProperty(env + "_" + "userid_CreditOps");
	public static String userid_thirdParty = getProperty(env + "_" + "userid_thirdParty");
	public static String userid_systemAdmin = getProperty(env + "_" + "userid_SystemAdmin");
	public static String userid_DealerChamp = getProperty(env + "_" + "userid_DealerChamp");
	
	public static String username = getProperty(env + "_" + "username");
	public static String user = getProperty(env + "_" + "user");
	public static String password = getProperty(env + "_" + "password_encoded");

	public static String oldOrg_url = getProperty(oldOrg + "_" + "appURL");
	public static String oldOrg_username = getProperty(oldOrg + "_" + "userid_AE");
	public static String oldOrg_password = getProperty(oldOrg + "_" + "password_encoded");

	public static String Profile_AE = "AE";
	public static String Profile_Delivery = "Delivery";
	public static String Profile_OpsManager = "Ops Manager";
	public static String Profile_SalesManager = "Sales Manager";
	public static String Profile_Service = "Service";
	public static String Profile_DataGovernance = "Data Governance";
	public static String Profile_BusinessAdmin = "Business Admin";
	public static String Profile_ContractSupport = "Contract Support";
	public static String Profile_ContractManager = "Contract Manager";
	public static String Profile_CreditOps = "Credit Ops";
	public static String Profile_FraudOps = "Fraud Ops";
	public static String Profile_ThirdParty = "Third Party";
	public static String Profile_SystemAdmin = "System Admin";
	public static String Profile_DealerChamp = "DealerChamp";
	
	public static String roles = "Endorser";
	
	// Email to case

	public static String emailToCaseCustomerID = getProperty("EmailToCase_QA_Customer_EmailID");
	public static String emailToCaseCustomerPassword = getProperty("EmailToCase_QA_Customer_Password");
	public static String emailToCaseCustomerHost = getProperty("EmailToCase_QA_Customer_Host");
	public static String emailToCaseCustomerRecipientID = getProperty("EmailToCase_QA_Customer_RecipientID_" + env);
	public static String emailToCaseServiceUserName = getProperty("EmailToCase_QA_Service_username");
	public static String AEUserName = getProperty("AE_username");
	public static String FraudOpsUserName = getProperty("FraudOps_username");
	public static String emailToCaseDGUserName = getProperty("EmailToCase_QA_DG_username");
	
	//Change Business Address
	public static String street = getProperty("Street");
	public static String city = getProperty("City");
	public static String postalcode = getProperty("PostalCode");
	
	//Internal Guided Case
	public static String internalGuidedCaseUserName = getProperty("InternalGuidedCase_username");

	// TelliOffer
	public static String noAvailableOffer = "No Available offers!";

	// MileStone
	public static String noMileStones = "No milestones to show.";

	// Snow Validation
	public static String productFamily_InternetOfThings = "Internet of Things";
	public static String productFamily_BusinessPhone = "Business Phone";
	
	//Internal Guided Case
	public static String fraudRiskSelection = "Fraud Risk";
	public static String fraudCaseNumber;

	//UserID Value For Prospect Account
	public static String prospect_UsrValue = "";
	
	// Account
	public static String prospect_Acc = getProperty(env + "_" + "Prospect_Account");
	public static String account_ON = getProperty(env + "_" + "ON_Account");
	public static String account_CBA = getProperty(env + "_" + "CBA_Account");
	public static String account_ATL = getProperty(env + "_" + "ATL_Account");
	public static String account_ATL_NF = getProperty(env + "_" + "NF_Account");
	public static String account_ATL_NB = getProperty(env + "_" + "NB_Account");
	
	public static String busAccNameContactRelationship= getProperty(env + "_" + "ON_AccountContactRelationship");

	// Report Storage DB Details

	public static String db_driver = getProperty("DB_Driver");
	public static String db_url = getProperty("DB_Url");
	public static String db_oracleThin = getProperty("DB_ThinDriver");
	public static String db_port = getProperty("DB_Port");
	public static String db_servicename = getProperty("DB_ServiceName");
	public static String db_userid = getProperty("DB_UserID");
	public static String db_password = getProperty("DB_Password");
	public static String db_table = getProperty("DB_Table");
	public static String db_timeFormat = getProperty("DB_TimeFormat");

	public static String caseNumber;
	public static String etmCaseNumber;
	public static String etmCaseAdditionalNotesText;
	public static String caseStatusInProgress;
	public static String caseStatusNew;
	public static String caseStatusClosed;
	public static String caseStatusResolved;
	public static String caseStatusAwaitingCustomerResponse;
	public static String caseStatusAwaitingProblemTicket;
	public static String caseStatusAwaitingMonitoring;
	public static String caseStatusAwaitingThirdParty;
	public static String casePriorityHigh;
	public static String casePriorityMedium;
	public static String casePriorityCritical;
	public static String casePriorityLow;
	public static String casePriorityPlanning;
	public static String caseTypeProblem;
	public static String caseTypeServiceRequest;
	public static String caseSubTypeAddWiFiCalling;;
	public static String caseOriginEmail;
	public static String caseOriginPhone;
	public static String caseOriginWeb;
	public static String caseOriginMonitoring;
	public static String caseOriginProactive;
	public static String caseReasonBillingInquiry;
	public static String caseRecordType;
	public static String caseRecordTypeSupport;
	public static String caseRecordTypeSNOW;
	public static String replyEmailBody;
	public static String caseTypeTechnicalSupport;
	public static String recentCaseType;
	public static String chosenAccountNameForCustomerCase;
	public static String chosenAccountType;
	public static String relatedBusinessAccountForCusCase;
	public static String serviceAccountNumberForCusCase;
	public static String caseOwnerDefaultQueue;
	public static String caseOwnerTier2WirelineQueue;
	public static String caseProductInternetAndAdvancedNetwork;
	public static String subscriptionForContact;
	public static String relatedBusinessAccountForCusCaseServiceContact;
	public static String relatedBillingAccForCusCase;
	public static String relatedContactForCusCaseBusAcc;
	public static String incidentID;

	// Quick Text
	public static String quickTextName;
	public static String quickTextCategory;
	public static String quickTextChannel;

	// Account Status
	public String bA_Status_Prospect;
	public String businessAccountStatus;
	public String status_Active;
	public String status_PendingApproval;
	public String field_RecordLocked;
	public String field_RecordUnlocked;

	// Account Record Type
	public static String acc_RecordType_Business;
	public static String acc_RecordType_Business_Fr;
	public String acc_RecordType_Service;
	public String acc_RecordType_Billing;

	// Object Names
	public String ordersObject;
	public String manualQueuesObject;
	public String accountProvisQueObject;
	public String serviceDeliveryQueueObject;

	public String quickTextObject;

	public String category;
	public String console;
	public static String serviceConsole;
	public static String surveyResponsesConsole;
	public static String surveyAccounts;
	public String userProfile;
	public String userProfileAe;
	public String userProfileDelivery;
	public static String userLocaleFrench;
	public static String userLocaleEnglish;
	public static String userLanguageFrench;
	public static String userLanguageEnglish;
	public static String userLanguage;

	public String busAccAbbr;
	public String businessAccName;
	public String businessAccountName;
	public String bussinessAccountForCase;
	public String businessAccountLegalName;
	public String busAccVerticalGroup;
	public String busAccVertical;
	public String updatedBusinessAccountLegalName;
	public String numberOfEmployees;
	public String updatedEmployeeSize;
	public String phoneNumber;
	public String phoneNumberWithoutCode;
	public String address;
	public String busAddress;
	public String selectCanNotFindBillingAddress;
	public String isChildBusinessAccount;
	public String industryLabel;
	public String subIndustryLabel;
	public String aEProfileNameForAccOwnerTransfer;
	public String baseRevenueQuotaTransfer;
	public String acquisitionMarginTCVQuotaTransfer;
	public String changeOwer;
	public String territories;

	public String secondBusinessAccountName;
	public String tempBusinessAccountName;
	public String secondUpdatedEmployeeSize;
	
	public String customerStoryEmployeeSizeChange;
	public String employeeSizeChangeAssignedTo;
	public String employeeSizeChangeComments;
	public String employeeSizeChangePriority;
	public String employeeSizeChangeStatus;

	public String cbaNoDuplicatemsg;
	public String cbaClickNextToCreateNewAccMsg;
	public String accountApprovalMsg;
	public String accountRejectionMsg;

	// Create Contact Inputs
	public String contactSalutation;
	public String contactTitle;
	public String contactFirstName;
	public String contactLastName;
	public String contactName;
	public String contactEmailAddress;
	public String selectCanNotFindMailingAddress;

	public String contactPhoneNumber;
	public String updatedNewPhoneNumber;
	public String emailIdValue;
	public String signingAuthEmailIdValue;
	public String signingAuthName;

	public String duplicateEmailAddress;
	public String contactType;
	public String formatField;

	public String contactRelationshipType;
	public String contactLanguage;

	public String addressStreet;
	public String addressCity;
	public String addressState;
	public String addressCountry;
	public String addressPostalCode;
	public String suiteFloorNumber;
	public String serviceLocationAccessType;
	
	public String ServiceAccaddressStreetNo;
	public String ServiceAccaddressStreetName; 
	public String ServiceAccaddressStreetType;
	public String ServiceAccaddressCity;
	public String ServiceAccaddressProvince;
	public String ServiceAccaddressPostalCode;
	
	
	
	public String actualContact_Address;
	public String fullContactName;
	public String tempContactName;

	
	public String primaryContact;
	public String accountContact;
	public String siteContactName;
	public String siteContactEmailId;
	public String serviceLocation;
	public String selectedProduct;
	public String aeName;
	public String accountName;
	public String aeEmailId;
	public String aeTelNum;
	public String serviceStreetAddress;
	public String serviceCity;
	public String serviceState;
	public String servicePostalCode;
	public String serviceCountry;
	
	
	public String port;
	public String accessspeed;
	

	public static String langEnglish;
	public static String langFrench;

	public static String contactFullName;
	//
	public static String siteContactRole = "General";

	// Create Service Account Input
	public String serviceAccountName;
	public String serviceAccountNumber;
	public String serviceAccountSource;
	public String serviceAddress;
	public String servicePremiseType;
	public String servicePremiseStatus;
	public String servicePremiseFloorLocation;
	public HashMap<String, List<String>> servicePremiseAddress;
	public List<String> serviceAccountList;
	public static int numberOfServiceAccountRelated = 0;

	// Create Billing Account Input
	public String billingAccountName;
	public String tempbillingAccountName;
	public String billingAccountNumber;
	public String billingAccountSource;
	public static String billingCAN;
	public static String billingBAN;

	// Create Opportunity
	public static String salesSegment;
	public String opportunitySite;
	public String none;
	public String yes;
	public String no;
	public String opportunityProductType;
	public String opportunityProduct;
	public String opportunityR4BProduct;
	public String opportunitySubProduct;
	public String opportunityProductAcr;

	public List<String> oldOrgOpportunityProductFamily;
	public List<String> newOrgOpportunityProduct;

	public static String opportunityCloseDate;
	public static String creditCheckReviewDateThirtyDaysBefore;
	public static String creditCheckReviewDateTenDaysBefore;
	public String opportunityCloseDateFormatted;
	public String creditCheckLastReviewDateFormattedThirtyDaysBefore;

	public String creditCheckLastReviewDateFormattedTenDaysBefore;
	public String opportunityStage;
	public String opportunityType;
	public String opportunityAmount;
	public String opportunityAdditionalInfo;
	public String opportunityContactRole;

	public String opportunityName;
	public String parentAccountName;
	// public String parentAccount;
	public String stage;
	public String closeDate;
	public String statusNew;
	
	public String opportunityScoreMsg;

	public static String currentAccount;

	// Create Quote
	public String createQuoteServiceability;
	public String accessTypeNet;
	public static String accessTypeFibre = "Fibre";
	public String quoteProductName;
	public String office365ProductName;
	public String tvProductName;
	public String quoteR4BApprovalName;
	public String quoteName;
	public String creditCheckStatus;
	public String fraudCheckStatus;
	public String fraudCheckStatus_PriceAbove_2000;
	public String validationCheckStatus;

	public String r4BQuoteApproval = "R4B Quotes Approval";
	public String gSBRingDNA = "Guided Selling by ringDNA";
	public String tasks = "Tasks";
	public String FNF = "No";
	public String busAccName = "TARGET";
	public String busAccLegalName = "TARGET";
	public String esclegalName = "Legal Name";
	public String escType = "Type";
	public String escStatus = "Status";
	public String escScrore = "Confidence Score";
	public String accStatus = "Active";
	public String legalNameValue = "TARGET";
	

	public String noResult = "No results";

	public String createNewSalesforceCase = "Create New Salesforce Case";
	public String createNewCompensationCase = "Create New Compensation Case";
	public String salesforceClassicIssuessAW = "Salesforce Classic Issues and Workarounds";
	public String salesforceLightingSalesCG = "Salesforce Lightning Sales Support Chatter Group";
	public String wirelessOfferGrid = "Wireless Offer Grid";
	public String wirelineOfferGrid = "Wireline Offer Grid";
	public String missionPossibleHub = "Mission Possible Hub";

	public String iblcLongDistancePlans;
	public String iblcLDP_BusinessPhoneLongDistancePlan;
	public String iblcLDP_UnlimitedNorthAmericanLD;
	public String iblcAdministrativeFeaturesGroup;
	public String iblcVoiceFeaturesGroup;
	public String iblcDistinctiveRing;
	public String iblcSpecialandBlockingFeaturesGroup;
	public String iblc911EmergencyAccess;
	public String iblcMessageRelayService;
	public String iblcTelephonyModem;
	public String iblcUnlistedNumber;
	public String iblcDirectoryListing;
	public String iblcTechnicalInstallPhoneLines;
	public String iblcTechnicalInstallJacks;
	public String iblcStandard;
	public String iblcBasic;
	public String iblcPro;

	public String iblcNumberTypePorted;
	public String iblcNumberTypeNative;
	public String iblcLinePurposeValueSecurity;
	public String iblcLinePurposeValueElevator;
	public String iblcLinePurposeValueAlarm;
	public String iblcDSLValueYes;
	public String attributeNameClass;
	public String phoneNumberFieldClassValue;

	public String recurringCharge;
	public String recurringCost;
	public String recurringChargeTotal;
	public String recurringCostTotal;
	public String oneTimeCharge;
	public String oneTimeCost;
	public String oneTimeChargeTotal;
	public String oneTimeCostTotal;
	public double totalPrice;
	public boolean creditCheck_ValidationFlag;
	public boolean quoteReviewcreditCheckFlag;
	public int credit_Available;
	public int credit_Assigned;

	public String quotetempProduct;

	public static String installationDiscounts;

	public static String quoteNumber;

	public static String internetProduct;
	public static String tvProduct;

	public static String contractTermMonthly;
	public static String contractTerm36Months;
	public static String contractTerm60Months;

	public List<String> queueItemsName = new ArrayList<String>();
	public List<String> quoteLineItemsProductName = new ArrayList<String>();

	// Close Opportunity Information
	public String closedLostPrimaryReason;
	public String closedLostSecondaryReason;
	public String cosedLostCompetitor;

	public static String closedWonOpp;

	public List<String> closedLostPrimaryReasons;
	public List<String> closedLostSecondaryReasons;
	public List<String> closedLostCompetitors;
	public List<String> contactRoleOptions;
	public List<String> forecastCategoryOptions;

	// Quote Configuration

	public String quoteContractTerm;
	public String quoteServiceLocation;
	public String quoteInternetDownloadSpeed;
	public String quoteInternetUploadSpeed;
	public String quoteStaticIp;

	public String quotePDFAlertText;

	// Quote Status
	public String quoteStatusAccepted;
	public String quoteStatusFinalized;
	public String quoteStatusFinalizedFr;
	public String quoteStatusApproved;
	public String quoteStatusApprovedFr;
	public String quoteStatusRejected;

	// Guided By Jouney TCV
	public static double monthylyTotal_num;
	public static double recurringTCV_num;
	public static double onetimeTCV_num;
	public static double totalTCV_num;
	public static double totalCost_num;
	public static double tcvMargin_num;
	public static double tcvMarginPercentage_num;

	// Order Status
	public String orderStatusDraft;
	public String orderStatusDraftFr;
	public String orderStatusReadyToSubmit;
	public String orderStatusReadyToSubmitFr;
	public String orderStatusInProgress;
	public String orderStatusInProgressFr;
	public String orderStatusActivated;
	public String orderStatusActivatedFr;
	public String orderStatusCancelled;
	public String orderStatusCancelledRequested;
	public String orderStatusFrozen;
	public String awaitingSign;
	public String awaitingSignFr;
	public String signedStatus;
	public String signedStatusFr;
	public String finalisedStatus;
	public String finalisedStatusFr;
	public String notReqStatus;
	public String notApplicable;
	public String requiredStatus;
	public String passStatus;
	public String reqStatus;

	public String declinedStatus;
	public String declinedStatusFr;
	public String deniedStatus;
	public String deniedStatusFr;

	public String orderNumber;
	public boolean isOrderFailed;
	public String orderStateFailed;
	public String orderFailureSubject;
	public String orderFailureStatusOpen;
	public String orderFailureStatusCompleted;
	public String orderFailureComment;
	public String tempVarForModifiedDate;

	// Order Related
	public List<String> orderRelatedProductItemsName = new ArrayList<String>();

	// Orchestration
	public String orchestrationPlanName;
	public String orchestrationStateInProgress;
	public String orchestrationStateCompleted;

	// Orchestartion task Type
	public String orchestrationTaskTypeMilestone;
	public String orchestrationTaskTypeManualTaskIn;
	public String orchestrationTaskTypeAccountProvisioningQueue;
	public String orchestrationTaskTypeServiceDeliveryQueue;
	public String orchestrationTaskTypeAutoTask;

	// Orchestartion Item Actions
	public String orchestrationItemActionOpenRecord;
	public String orchestrationItemActionCancelTask;
	public String orchestrationItemActionPendTask;
	public String orchestrationItemActionAssignToMe;
	public String orchestrationItemActionCompleteTask;
	public String orchestrationItemActionFailTask;

	public String orchestrationItemActionTaskBusinessName;
	public String orchestrationItemActionTaskSiteContact;
	public String orchestrationItemActionTaskBillingAddress;
	public String orchestrationItemActionTaskServiceAddress;
	public String orchestrationItemActionTaskSalesAgentContact;
	public String orchestrationItemActionTaskSiteName;
	public String orchestrationItemActionTaskSignAuthContact;
	public String orchestrationItemActionTaskCanNumber;

	// Order Cancel
	public String businessAccNameForOrderCancelATL;

	// Task Status
	public String taskStatusRunning;
	public String taskStatusReady;
	public String taskStatusPending;

	// Persist Supersystem CAN
	public static String superSystemCAN;
	public static String v21BAN;
	public static String superSystemWorkOrderNumber1;
	public static String superSystemWorkOrderNumber2;
	public static String v21DealerCode;
	public static String sgiDealerCode;

	public String taskAssignedToYouText;

	// Site Contact
	public String siteContact;
	public String contactRecordType;

	public String agentsTab;
	public String queuesBacklogTab;
	public String assignedWorkTab;

	// Faile task
	public String businessFailureText;

	// Contact Account Relationship Options
	public String viewRelationshipOption;
	public String editRelationshipOption;
	public String removeRelationshipOption;

	// Relation Type Options List
	public static List<String> relationshipTypesOptions;
	public static String relationshipTypeSelected;
	public static String relationshipName;

	public List<String> relationshipType_Contact = new ArrayList<String>();
	public List<String> directRoleOptions = new ArrayList<String>();

	// Support Group
	public String supportGroupName;
	public String supportGroupEmail;
	public String supportGroupPhone;
	public String suuportGroupProductsSupported;

	// Case category
	public static String caseIdentifier;

	public static HashMap<String, ArrayList<String>> prodct_map = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<String>> category_map = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<String>> type_map = new HashMap<String, ArrayList<String>>();

	public static ArrayList<String> category_list;
	public static ArrayList<String> type_list;
	public static ArrayList<String> subType_list;

	// Subscriptions
	public static List<String> subscription_titles;
	public static List<String> sysInfo_titles;
	public static String wirelessSubscription;
	public static String wirelineSubscription;
	public static String billingAccountWithSubscription;
	public static String subscriptionName;

	// AE Home Page
	public static String defaultTabUnderHome;
	public static List<String> tabsUnderHome;
	public static int openTaskTableNoOfRows = 0;
	public static String typeOfTask;
	public static String itemName;
	public static String lastModifiedActualValueInTaskCreation;

	// Files
	public static int filesTableNoOfRows = 0;
	public static String libraryName;
	public static String folderName;
	public static String ownedByMeFilesSection;
	public static String recentFilesSection;
	public static String followingFilesSection;
	public static String sharedWithMeFilesSection;
	public static String librariesSection;
	public static int noOfFiles;

	// Task (Log An activity)
	public static String taskTypeForActivity;
	public static String interactionTypeOfTask;
	public static String taskSubject;
	public static String taskAccountName;
	public static String taskSolutionDiscussed;
	public static String serviceAccountNameForLogAnActivity;
	public static String billingAccountNameForLogAnActivity;
	public static List<String> taskTypes;
	public static List<String> taskTypeHelpText;
	public static List<String> taskInteractionTypes;
	public static List<String> taskSubjectList;
	public static List<String> taskSolutionDiscussedList;
	public static String logActivityLabel;

	// Create Customer Case for service account with subscriptions
	public static String serviceAccountWithSusbscriptions;

	// Knowledge (Article)
	public static String articleNameForVersionValidation;
	public static String articleNameForDetailValidation;
	public static String articleRecordType;
	public static List<String> articleListViews;
	public static List<String> articleCategories;
	public static String articleName;
	public static String publicationStatusDraft;
	public static String articleVersionZero;
	public static String publicationStatusPublished;

	// Manual Queue - Send Order To P&I
	public String oracleNumber;
	public String portConfigNo;
	public String deviceID;
	public String customerPortNo;
	public String oracleErrorCheckNo;
	public String cLFIValue;
	public String cLFIValueErrorCheckNoWithLessDigits;
	public String cLFIValueErrorCheckNoWithMoreDigits;
	public String evcID;
	public boolean completeIpAssignment = false;

	// Manual Queue Review Spec Sheet
//	public String handOffInterface;
//	public String encapsulationType;
//	public String iPversion;
//	public String ipV4WAnBlockSpecSheet;
//	public String ipV4LAnBlockSpecSheet;

	// B2B Account Details

	public String b2b_accountRecordType;
	public String b2b_accountStatus;
	public String b2b_franchiseLocation;
	public String b2b_franchiseOwner;
	public String b2b_status_Prospect;
	public String b2b_status_Inactive;
	public String b2b_malID;
	public String b2b_secondMalID;
	public String b2b_accountDetailTag;

	// B2B Contact Details
	public String b2b_contType;
	public String b2b_currentCompyName;
	public String b2b_updatedPhoneNumber;

	// ACR Permission Field Value Details
	public List<String> contact_AcrValue = new ArrayList<String>();

	// Order Management Decomposition
	public String promo_Code;

	public InputData() {

		// Account Status
		bA_Status_Prospect = "Prospect";
		status_Active = "Active";
		status_PendingApproval = "Pending Approval";
		field_RecordLocked = "Record locked.";
		field_RecordUnlocked = "Record unlocked.";

		// Account Record Tyep
		acc_RecordType_Business = "Business";
		acc_RecordType_Business_Fr = "Affaires";
		acc_RecordType_Service = "Service";
		acc_RecordType_Billing = "Billing";
		
		//ETM Case
		etmCaseAdditionalNotesText = "Testing Purpose";
		
		// Object Names
		ordersObject = "Orders";
		manualQueuesObject = "Manual Queues";
		accountProvisQueObject = "Account Provisioning Queue";
		serviceDeliveryQueueObject = "Service Delivery Queue";

		quickTextObject = "Quick Text";

		category = getMPTestData("Category");
		console = getMPTestData("Console");
		serviceConsole = "Service Console";
		surveyResponsesConsole = "SurveyResponses";
		surveyAccounts = "Survey Accounts";
		userProfile = getMPTestData("Profile");
		userLocaleFrench = "French (Canada)";
		userLocaleEnglish = "anglais (Canada)";
		userLanguageFrench = "Français";
		userLanguageEnglish = "English";
		userLanguage = "English";

		busAccAbbr = getMPTestData("CBA_Name");
		businessAccountName = getMPTestData("CBA_Name") + addOn_1;
		secondBusinessAccountName = getMPTestData("CBA_Name") + addOn_2;
		businessAccountLegalName = getMPTestData("CBA_Legal Name") + addOn_1;
		bussinessAccountForCase = "MP27NoBilling";

		busAccVerticalGroup = getMPTestData("CBA_Vertical Group");
		busAccVertical = getMPTestData("CBA_Vertical");

		phoneNumberWithoutCode = AdditionalUtilities.generateRandomDigits(10);
		updatedBusinessAccountLegalName = getMPTestData("CBA_Legal Name") + addOn_2;
		phoneNumber = AdditionalUtilities.generateRandomDigits(9) + AdditionalUtilities.generateRandomDigits(9);
		b2b_updatedPhoneNumber = getMPTestData("Phone Number") + addOn_2;
		numberOfEmployees = getMPTestData("CBA_Number of Employees");
		updatedEmployeeSize = getMPTestData("Updated_Employee Size");
		secondUpdatedEmployeeSize = getMPTestData("Second_UpdatedEmpSize");
		address = getMPTestData("Address");
		busAddress = getProperty(env + "_" + "bussAddress");
		selectCanNotFindBillingAddress = getMPTestData("CBA_Enter Manual Address");
		isChildBusinessAccount = getMPTestData("CBA_ Is Child BA");
		industryLabel = "Industry";
		subIndustryLabel = "Sub-Industry";
		aEProfileNameForAccOwnerTransfer = "Nandan NIS";
		baseRevenueQuotaTransfer = "50%";
		acquisitionMarginTCVQuotaTransfer = "1111";

		customerStoryEmployeeSizeChange = "Please review the change in employee size for the account referenced";
		employeeSizeChangeAssignedTo = "Data Governance queue";
		employeeSizeChangeComments = "Employee size Old Value:" + numberOfEmployees + " New Value:"
				+ updatedEmployeeSize;
		employeeSizeChangePriority = "Normal";
		employeeSizeChangeStatus = "Open";

		cbaNoDuplicatemsg = "No potential duplicates found for the account";
		cbaClickNextToCreateNewAccMsg = "Please click Next.";
		accountApprovalMsg = getMPTestData("CBA_Approval Msg");
		accountRejectionMsg = getMPTestData("CBA_Rejection Msg");
		changeOwer = getMPTestData("Owner_Id");

		// Create Contact Inputs
		contactSalutation = getMPTestData("CC_Salutaion");
		contactTitle = getMPTestData("CC_Title");
		firstContact_prepareContactData();
		contactRelationshipType = getMPTestData("CC_Relationship Type");
		contactLanguage = getMPTestData("CC_Language");

		addressStreet = getMPTestData("Address Street");
		addressCity = getMPTestData("Address City");
		addressState = getMPTestData("Address State");
		addressCountry = getMPTestData("Address Country");
		addressPostalCode = getMPTestData("Address Postal Code");
		suiteFloorNumber = getMPTestData("Address Suite/Floor");
		serviceLocationAccessType = getMPTestData("Service Location Access Type");
		territories = getMPTestData("Territories");
		
		ServiceAccaddressStreetNo = getProperty(env + "_" + "ServiceAccountStreetNo");
		ServiceAccaddressStreetName = getProperty(env + "_" + "ServiceAccountStreetName");
		ServiceAccaddressStreetType = getProperty(env + "_" + "ServiceAccountStreetType");
		ServiceAccaddressCity = getProperty(env + "_" + "ServiceAccountCity");
		ServiceAccaddressProvince = getProperty(env + "_" + "ServiceAccountProvince");
		ServiceAccaddressPostalCode = getProperty(env + "_" + "ServiceAccountPostalCode");
		
		
		
		langEnglish = "English";
		langFrench = "French";

		contactFullName = getProperty(env + "_" + "contactFullName");

		selectCanNotFindMailingAddress = getMPTestData("CC_Enter Manual Address");

		// Create Service Account Input
		serviceAccountName = getMPTestData("CSA_Service Account Name") + addOn_1;
		serviceAccountNumber = getMPTestData("CSA_Account Number") + addOn_1;
		serviceAccountSource = getMPTestData("CSA_Account Source");
		serviceAddress = getMPTestData("CSA_Service Address");
		servicePremiseType = getMPTestData("CSA_Premise Type");
		servicePremiseStatus = getMPTestData("Premise Status");
		servicePremiseAddress = new HashMap<>();
		serviceAccountList = new ArrayList<String>();

		// Create Billing Account Input
		billingAccountName = getMPTestData("CBiA_Billing Account Name") + addOn_1;
		billingAccountNumber = getMPTestData("CBiA_Billing Account Number") + addOn_1;
		billingAccountSource = getMPTestData("CBiA_Billing Account Source");

		billingCAN = "CAN Billing";
		billingBAN = "BAN Billing";

		// Create Opportunity
		salesSegment = "Mid-Market";
		none = "None";
		yes = "Yes";
		no = "No";
		opportunitySite = getMPTestData("Opp_Type_Of_Site");
		opportunityProductType = getMPTestData("Opp_Product_Type");
		opportunityProduct = getMPTestData("Opp_Product");
		opportunityR4BProduct = getMPTestData("Opp_R4B_Product");
		opportunitySubProduct = getMPTestData("Opp_Sub Product");
		opportunityProductAcr = getMPTestData("Product_Acronym");

		oldOrgOpportunityProductFamily = new ArrayList<String>();
		newOrgOpportunityProduct = new ArrayList<String>();

		opportunityCloseDate = DateTimeUtilities.currentSystemDatePlus(7, "MM-dd-yyyy");
		creditCheckReviewDateThirtyDaysBefore = DateTimeUtilities.currentSystemDateMinus(30, "MM-dd-yyyy");
		creditCheckReviewDateTenDaysBefore = DateTimeUtilities.currentSystemDateMinus(10, "MM-dd-yyyy");

		try {
			opportunityCloseDateFormatted = DateTimeUtilities.getFormattedStringDate("MM-dd-yyyy", "dd/MM/yyyy",
					opportunityCloseDate);
		} catch (ParseException e) {
		}

		try {
			creditCheckLastReviewDateFormattedThirtyDaysBefore = DateTimeUtilities.getFormattedStringDate("MM-dd-yyyy",
					"dd/MM/yyyy", creditCheckReviewDateThirtyDaysBefore);
		} catch (ParseException e) {
		}

		try {
			creditCheckLastReviewDateFormattedTenDaysBefore = DateTimeUtilities.getFormattedStringDate("MM-dd-yyyy",
					"dd/MM/yyyy", creditCheckReviewDateTenDaysBefore);
		} catch (ParseException e) {
		}

		opportunityStage = getMPTestData("Opp_Stage");
		opportunityType = getMPTestData("Opp_Type");
		opportunityAmount = getMPTestData("Opp_Amount");
		opportunityAdditionalInfo = "QA Automation Test";
		opportunityContactRole = getMPTestData("Opp_Contact Role");

		opportunityName = getMPTestData("Opportunity Name") + addOn_1;

		parentAccountName = account_ON;
		// parentAccount = account_CBA;
		stage = getMPTestData("Stage");
		closeDate = DateTimeUtilities.currentSystemDatePlus(3, "dd/MM/yyyy");
		statusNew = getMPTestData("Status New");

		opportunityScoreMsg = "The Opportunity Score uses data science and machine learning to score your opportunities so that you can prioritize them based on deals you are more likely to win.";

		// Create Quote
		createQuoteServiceability = getMPTestData("Quote_Serviceability");
		quoteProductName = getMPTestData("Quote_Product Name");

		iblcLongDistancePlans = "Long Distance Plans";
		iblcLDP_BusinessPhoneLongDistancePlan = "Business Phone Long Distance Plan";
		iblcLDP_UnlimitedNorthAmericanLD = "Unlimited North American LD";
		iblcAdministrativeFeaturesGroup = "Administrative Features Group";
		iblcVoiceFeaturesGroup = "Voice Features Group";
		iblcDistinctiveRing = "Distinctive Ring";
		iblcSpecialandBlockingFeaturesGroup = "Special and Blocking Features Group";
		iblc911EmergencyAccess = "9-1-1 Emergency Access Fee";
		iblcMessageRelayService = "Message Relay Service";
		iblcTelephonyModem = "Telephony Modem";
		iblcUnlistedNumber = "Unlisted Number";
		iblcDirectoryListing = "Directory Listing";
		iblcTechnicalInstallPhoneLines = "Technician Install - Phone Lines";
		iblcTechnicalInstallJacks = "Technician Install - Jacks";
		iblcStandard = "Business Phone Standard";
		iblcBasic = "Business Phone Basic";
		iblcPro = "Business Phone Pro";
		

		iblcNumberTypePorted = "Ported";
		iblcNumberTypeNative = "Native";
		iblcLinePurposeValueSecurity = "Security";
		iblcLinePurposeValueElevator = "Elevator";
		iblcLinePurposeValueAlarm = "Alarm";
		iblcDSLValueYes = "Yes";
		attributeNameClass = "class";
		phoneNumberFieldClassValue = " nds-not-empty nds-is-dirty";

		recurringCharge = "Recurring Charge";
		recurringCost = "Recurring Cost";
		recurringChargeTotal = "Recurring Total";
		recurringCostTotal = "Recurring Cost Total";
		oneTimeCharge = "One Time Charge";
		oneTimeCost = "One Time Cost";
		oneTimeChargeTotal = "One Time Total";
		oneTimeCostTotal = "One Time Cost Total";

		quotetempProduct = getMPTestData("Quote_Product_Name_Temp");

		installationDiscounts = "INSTALLATION DISCOUNT";

		quoteNumber = "";
		contractTermMonthly = "Monthly";
		contractTerm36Months = "3 Year Term";
		contractTerm60Months = "5 Year Term";

		internetProduct = "Internet";
		tvProduct = "TV";

		// Order Cancel
		businessAccNameForOrderCancelATL = getProperty("businessAccNameForOrderCancelATL");

		// Close Opportunity Information
		closedLostPrimaryReason = getMPTestData("Closed Lost Primary Reason");
		closedLostSecondaryReason = getMPTestData("Closed Lost Secondary Reason");
		cosedLostCompetitor = getMPTestData("Competitor Lost To");

		closedWonOpp = "Closed Won Opportunities";

		closedLostPrimaryReasons = new ArrayList<String>();
		closedLostSecondaryReasons = new ArrayList<String>();
		closedLostCompetitors = new ArrayList<String>();
		contactRoleOptions = new ArrayList<String>();
		forecastCategoryOptions = new ArrayList<String>();

		// Quote Configuration

		quoteContractTerm = getMPTestData("Quote_Contract Term");
		quoteServiceLocation = getMPTestData("Quote_Service Location");
		quoteInternetDownloadSpeed = getMPTestData("Quote_Internet Download Speed");
		quoteInternetUploadSpeed = getMPTestData("Quote_Internet Upload Speed");
		quoteStaticIp = getMPTestData("Quote_Static Ip");

		quotePDFAlertText = "PDFGenerationComplete!";

		// Quote Status
		quoteStatusAccepted = "Accepted";
		quoteStatusFinalized = "Finalized";
		quoteStatusFinalizedFr = "Terminé";
		quoteStatusApproved = "Approved";
		quoteStatusApprovedFr = "Approuvée";
		quoteStatusRejected = "Rejected";

		// Order Status
		orderStatusDraft = "Draft";
		orderStatusDraftFr= "Ébauche";
		orderStatusReadyToSubmit = "Ready To Submit";
		orderStatusReadyToSubmitFr="Prêt à l’envoi";
		orderStatusInProgress = "In Progress";
		orderStatusInProgressFr = "En cours";
		orderStatusActivated = "Activated";
		orderStatusActivatedFr = "Activé";
		orderStatusCancelled = "Cancelled";
		orderStatusCancelledRequested = "Cancel Requested";
		orderStatusFrozen = "Frozen";
		awaitingSign = "Awaiting Signature";
		awaitingSignFr = "En attente de la signature";
		signedStatus = "Signed";
		signedStatusFr = "Signé";
		finalisedStatus = "Finalized";
		finalisedStatusFr = "Terminé";
		notReqStatus = "Not Required";
		notApplicable = "Not Applicable";
		requiredStatus = "Required";
		passStatus = "Pass";
		reqStatus = "In Progress";
		declinedStatus = "Signature Declined";
		declinedStatusFr = "Signature refusée";
		deniedStatus = "Denied";
		deniedStatusFr = "Refusé";

		orderNumber = "";
		isOrderFailed = false;
		orderStateFailed = "Failed";
		orderFailureSubject = "Order Failure";
		orderFailureStatusOpen = "Open";
		orderFailureStatusCompleted = "Completed";
		orderFailureComment = "Automation Script:Adding comment to failed task order";

		// Orchestration
		orchestrationPlanName = "";
		orchestrationStateInProgress = "In Progress";
		orchestrationStateCompleted = "Completed";

		// Orchestartion task Type
		orchestrationTaskTypeMilestone = "Milestone";
		orchestrationTaskTypeManualTaskIn = "Manual Task in";
		orchestrationTaskTypeAccountProvisioningQueue = "Account Provisioning Queue";
		orchestrationTaskTypeServiceDeliveryQueue = "Service Delivery Queue";
		orchestrationTaskTypeAutoTask = "Auto Task";

		// Orchestartion Item Actions
		orchestrationItemActionOpenRecord = "Open Record";
		orchestrationItemActionCancelTask = "Cancel Task";
		orchestrationItemActionPendTask = "Pend Task";
		orchestrationItemActionAssignToMe = "Assign to Me";
		orchestrationItemActionCompleteTask = "Complete Task";
		orchestrationItemActionFailTask = "Fail Task";

		orchestrationItemActionTaskBusinessName = "Business Name";
		orchestrationItemActionTaskSiteContact = "Site Contact";
		orchestrationItemActionTaskBillingAddress = "Billing Address";
		orchestrationItemActionTaskServiceAddress = "Service Address";
		orchestrationItemActionTaskSalesAgentContact = "Sales Agent Contact";
		orchestrationItemActionTaskSiteName = "Site Name";
		orchestrationItemActionTaskSignAuthContact = "Signing Authority Contact";
		orchestrationItemActionTaskCanNumber = "CAN Number";

		// Task Status
		taskStatusRunning = "Running";
		taskStatusReady = "Ready";
		taskStatusPending = "Pending";

		// Task(Log An Activity)
		serviceAccountNameForLogAnActivity = getProperty(env + "_" + "serviceAccountNameForLogAnActivity");
		billingAccountNameForLogAnActivity = getProperty(env + "_" + "billingAccountNameForLogAnActivity");

		taskTypes = new ArrayList<>(Arrays.asList("New Customer", "Existing Customer"));
		taskTypeHelpText = new ArrayList<>(
				Arrays.asList("Existing customer includes new opportunities with existing customers"
						+ "Prospect Customer is any customer who has no active billing account with Rogers"));
		taskInteractionTypes = new ArrayList<>(Arrays.asList("1-1 Call", "Conference Call",
				"Customer Visit- On Premise", "Customer Visit- Off Premise"));
		taskSubjectList = new ArrayList<>(Arrays.asList("Follow-Up Meeting", "Retention", "Service Renewal",
				"Cross Sell - Upsell", "Quarterly Business Review (QBR)", "Customer Initiated", "Qualifying Meeting",
				"Solution Negotiation", "Contract Negotiation", "Executive Engagement", "Executive Brief",
				"Event Entertainment / Relationship Building"));
		taskSolutionDiscussedList = new ArrayList<>(Arrays.asList("Fixed Network", "Data Center & Cloud", "Security",
				"Wireless", "Wireless Unison", "Fixed Network Unison", "Business Collaboration", "IoT M2M",
				"IoT Solutions", "General Business Discussion", "Product chosen in Competitive Insight"));

		logActivityLabel = "Log a Sales Activity";

		// Create Customer Case for service account with subscriptions

		serviceAccountWithSusbscriptions = getProperty(env + "_" + "serviceAccountWithSusbscriptions");

		// Persist Supersystem CAN
		superSystemCAN = addOn_1;
		v21BAN = AdditionalUtilities.generateRandomDigits(9);
		superSystemWorkOrderNumber1 = AdditionalUtilities.generateRandomDigits(7);
		superSystemWorkOrderNumber2 = AdditionalUtilities.generateRandomDigits(7);
		v21DealerCode = getMPTestData("V21 Dealer Code");
		sgiDealerCode = getMPTestData("SGI Dealer Code");

		taskAssignedToYouText = "Task Is Assigned To You";

		// Site Contact
		siteContact = "Site Contact";
		contactRecordType = "Business Contacts";

		// Email to case
		caseStatusInProgress = "In Progress";
		caseStatusNew = "New";
		caseStatusClosed = "Closed";
		caseStatusResolved = "Resolved";
		caseStatusAwaitingCustomerResponse = "Awaiting Customer Response";
		caseStatusAwaitingProblemTicket = "Awaiting Problem Ticket";
		caseStatusAwaitingMonitoring = "Awaiting Monitoring";
		caseStatusAwaitingThirdParty = "Awaiting Third party";
		casePriorityCritical = "Critical";
		casePriorityHigh = "High";
		casePriorityMedium = "Medium";
		casePriorityLow = "Low";
		casePriorityPlanning = "Planning";
		caseTypeProblem = "Problem";
		caseTypeServiceRequest = "Service Request";
		caseSubTypeAddWiFiCalling = "Add WiFi Calling";
		caseOriginEmail = "Email";
		caseOriginPhone = "Phone";
		caseOriginWeb = "Web";
		caseOriginMonitoring = "Monitoring";
		caseOriginProactive = "Proactive";
		caseReasonBillingInquiry = "Billing Inquiry";
		caseRecordType = "Service";
		caseRecordTypeSupport = "Technical Support";
		caseRecordTypeSNOW = "SNOW Integration";
		replyEmailBody = " Replying to Customer via Automation Script";
		caseTypeTechnicalSupport = "Technical Support";
		relatedBusinessAccountForCusCase = getProperty(env + "_" + "relatedBusinessAccountForCusCase");
		serviceAccountNumberForCusCase = getProperty(env + "_" + "serviceAccountNumberForCusCase");
		relatedBillingAccForCusCase = getProperty(env + "_" + "relatedBillingAccForCusCase");
		subscriptionForContact = getProperty("subscriptionForContact");
		caseOwnerDefaultQueue = "Default Queue";
		caseOwnerTier2WirelineQueue = "Tier 2 - Wireline";
		relatedContactForCusCaseBusAcc = getProperty(env + "_" + "relatedContactForCusCaseBusAcc");

		// GuidedCase
		caseProductInternetAndAdvancedNetwork = "Internet and Advanced Network";
		relatedBusinessAccountForCusCaseServiceContact = getProperty(env + "_" + "relatedBusinessAccountForCusCaseServiceContact");

		// Quick text
		quickTextName = "QT_" + addOn_1;
		quickTextCategory = "Greetings";
		quickTextChannel = "Email";

		agentsTab = getMPTestData("Agents Tab");
		queuesBacklogTab = getMPTestData("Queues BackLog Tab");
		assignedWorkTab = getMPTestData("Assigned Work Tab");

		// Failed task
		businessFailureText = "Testing QA Automation: Failed task";

		// Conntact Account Relationship Types
		viewRelationshipOption = "View Relationship";
		editRelationshipOption = "Edit Relationship";
		removeRelationshipOption = "Remove Relationship";

		// Relation Type Options List
		relationshipTypesOptions = new ArrayList<String>();

		// Support Group
		supportGroupName = "SG_" + addOn_1;
		supportGroupEmail = "sgtest" + getMPTestData("CC_Email Domain");
		supportGroupPhone = phoneNumber;
		suuportGroupProductsSupported = "Wireless Solutions, Tools & Unison";

		// Subscriptions
		subscription_titles = new ArrayList<String>();
		sysInfo_titles = new ArrayList<String>();
		wirelessSubscription = "Wireless";
		wirelineSubscription = "wireline";
		subscriptionName = getProperty(env + "_" + "subscriptionName");
		billingAccountWithSubscription = getProperty(env + "_" + "billingAccountWithSubscription");

		// AE Home Page

		typeOfTask = "Follow-Up Meeting";
		itemName = "Subscriptions";
		tabsUnderHome = new ArrayList<String>();
		defaultTabUnderHome = "My Accounts";

		// Files
		libraryName = "lib" + AdditionalUtilities.generateRandomCharacters(3);
		folderName = "folder" + AdditionalUtilities.generateRandomCharacters(3);
		ownedByMeFilesSection = "Owned By Me";
		recentFilesSection = "Recent";
		followingFilesSection = "Following";
		sharedWithMeFilesSection = "Shared With Me";
		librariesSection = "Libraries";
		noOfFiles = 0;

		// Knowledge(Article)
		articleNameForVersionValidation = getProperty("articleNameForVersionValidation");
		articleNameForDetailValidation = getProperty("articleNameForDetailValidation");
		articleRecordType = "General Article";
		articleListViews = new ArrayList<String>();
		articleCategories = new ArrayList<String>();
		articleName = "AutoMation" + addOn_1;
		publicationStatusDraft = "Draft";
		articleVersionZero = "0";
		publicationStatusPublished = "Published";

		// Contact details page
		contactType = "Phone";

		// Manual Queue- Send Order To P&I And verify Billing Account
		oracleNumber = AdditionalUtilities.generateRandomDigits(6);
		portConfigNo = AdditionalUtilities.generateRandomDigits(6);
		deviceID = AdditionalUtilities.generateRandomDigits(6);
		customerPortNo = AdditionalUtilities.generateRandomDigits(6);
		oracleErrorCheckNo = AdditionalUtilities.generateRandomDigits(5);
		cLFIValueErrorCheckNoWithLessDigits = AdditionalUtilities.generateRandomDigits(4);
		cLFIValue = AdditionalUtilities.generateRandomDigits(8);
		cLFIValueErrorCheckNoWithMoreDigits = AdditionalUtilities.generateRandomDigits(10)
				+ AdditionalUtilities.generateRandomDigits(10) + AdditionalUtilities.generateRandomDigits(10);
		evcID = AdditionalUtilities.generateRandomDigits(10) + AdditionalUtilities.generateRandomDigits(10)
				+ AdditionalUtilities.generateRandomDigits(2);

		// B2B Account Creation

		b2b_accountRecordType = "Submitted Account";
		b2b_accountStatus = "Assigned";
		b2b_status_Inactive = "Inactive";
		b2b_franchiseLocation = "None";
		b2b_franchiseOwner = "None";
		b2b_status_Prospect = "Prospect";
		b2b_malID = addOn_1;
		b2b_secondMalID = addOn_1;
		b2b_accountDetailTag = "Account Detail";

		// B2B Contact Creation

		b2b_contType = "User";

	}
	
	public void prepareSecondBillingAccountData()
	{
		billingAccountName = getMPTestData("CBiA_Billing Account Name") + addOn_2;
		billingAccountNumber = getMPTestData("CBiA_Billing Account Number") + addOn_2;
	}

	public void firstContact_prepareContactData() {

		/*
		 * contactFirstName = getMPTestData("CC_First Name") + addOn_1; contactLastName
		 * = getMPTestData("CC_Last Name") + addOn_1; contactEmailAddress =
		 * AdditionalUtilities.generateRandomCharacters(8).toLowerCase() +
		 * getMPTestData("CC_Email Domain");
		 */
		// To avoid duplicate contact error
		contactFirstName = AdditionalUtilities.generateRandomCharacters(8).toLowerCase();
		contactLastName = AdditionalUtilities.generateRandomCharacters(8).toLowerCase();
		contactEmailAddress = AdditionalUtilities.generateRandomCharacters(8).toLowerCase()
				+ getMPTestData("CC_Email Domain");

		primaryContact = contactFirstName + " " + contactLastName;
		fullContactName = contactSalutation + " " + contactFirstName + " " + contactLastName;
	}

	public void secondContact_prepareContactData() {

		/*
		 * contactFirstName = getMPTestData("CC_First Name") + addOn_2; contactLastName
		 * = getMPTestData("CC_Last Name") + addOn_2; contactEmailAddress =
		 * AdditionalUtilities.generateRandomCharacters(8).toLowerCase() +
		 * getMPTestData("CC_Email Domain");
		 */
		contactFirstName = AdditionalUtilities.generateRandomCharacters(8).toLowerCase();
		contactLastName = AdditionalUtilities.generateRandomCharacters(8).toLowerCase();
		contactEmailAddress = AdditionalUtilities.generateRandomCharacters(8).toLowerCase() + getMPTestData("CC_Email Domain");
		contactPhoneNumber = getMPTestData("Phone Number") + addOn_2;
		accountContact = contactFirstName + " " + contactLastName;
	}

	public void thirdContact_prepareContactData() {

		/*
		 * contactFirstName = getMPTestData("CC_First Name") + addOn_3; contactLastName
		 * = getMPTestData("CC_Last Name") + addOn_3; contactEmailAddress =
		 * AdditionalUtilities.generateRandomCharacters(8).toLowerCase() +
		 * getMPTestData("CC_Email Domain");
		 */
		contactFirstName = AdditionalUtilities.generateRandomCharacters(8).toLowerCase();
		contactLastName = AdditionalUtilities.generateRandomCharacters(8).toLowerCase();
		contactEmailAddress = AdditionalUtilities.generateRandomCharacters(8).toLowerCase()
				+ getMPTestData("CC_Email Domain");

		contactPhoneNumber = getMPTestData("Phone Number") + addOn_3;
		siteContactName = contactFirstName + " " + contactLastName;
	}

	public void multipleServiceAcc_prepareSAData() {
		serviceAccountName = getMPTestData("CSA_Service Account Name") + addOn_2;
		serviceAccountNumber = getMPTestData("CSA_Account Number") + addOn_2;
	}

	public void closedLost_prepareReasons() {

		closedLostPrimaryReasons.add("Serviceability");
		closedLostPrimaryReasons.add("Promotion Pricing");

		closedLostSecondaryReasons.add("No Cable Coverage");
		closedLostSecondaryReasons.add("Promotion Pricing");

		closedLostCompetitors.add("Bell");
		closedLostCompetitors.add("Telus");
		closedLostCompetitors.add("Shaw");
		closedLostCompetitors.add("Cogeco");
		closedLostCompetitors.add("Zayo/Allstream");

	}

	public void contactRole_PrepareOptions() {

		contactRoleOptions.add("Signing Authority");
		contactRoleOptions.add("Executive Sponsor");
		contactRoleOptions.add("Endorser");
		contactRoleOptions.add("Decider");
		contactRoleOptions.add("User");

	}

	public void forcastCategory_PrepareOptions() {

		forecastCategoryOptions.add("--None--");
		forecastCategoryOptions.add("Upside");
		forecastCategoryOptions.add("Pipeline");
		forecastCategoryOptions.add("Best Case");
		forecastCategoryOptions.add("Commit");
		forecastCategoryOptions.add("Closed");
	}

	public void oldOrgNewOrgSubProducts_PrepareOptions() {

		oldOrgOpportunityProductFamily.add("Data Center");
		oldOrgOpportunityProductFamily.add("Internet of Things");
		// oldOrgOpportunityProductFamily.add("Wireless");

		newOrgOpportunityProduct.add("Business TV");
		newOrgOpportunityProduct.add("Business Internet");

	}

	public void relationshipType_PrepareOptions() {

		relationshipTypesOptions.add("Affiliate");
		relationshipTypesOptions.add("Branch");
		relationshipTypesOptions.add("Division");

		relationshipTypesOptions.add("Federated Franchise");
		relationshipTypesOptions.add("Franchise");
		relationshipTypesOptions.add("Government Subsidiary");

		relationshipTypesOptions.add("Joint Venture");
		relationshipTypesOptions.add("Limited Partnership");
		relationshipTypesOptions.add("Subsidiary");

	}

	public List<String> setSubscriptionTitles() {

		subscription_titles.add("Name");
		subscription_titles.add("Product Type");
		subscription_titles.add("Record Type");
		subscription_titles.add("Status");
		subscription_titles.add("Billing Account");
		subscription_titles.add("Phone");
		subscription_titles.add("Subscription Start Date");
		subscription_titles.add("Subscription End Date");
		return subscription_titles;

	}

	public List<String> setSysInfoTitles() {

		// sysInfo_titles.add("Subscription Number");
		sysInfo_titles.add("Created By");
		sysInfo_titles.add("Last Modified By");
		return sysInfo_titles;

	}

	public List<String> setTabsUnderHome() {

		tabsUnderHome.add("My Performance");
		tabsUnderHome.add("My Activities");
		tabsUnderHome.add("My Accounts");
		tabsUnderHome.add("PowerBi Reports");
		return tabsUnderHome;

	}

	public List<String> setArticleListViews() {

		articleListViews.add("Archived Articles");
		articleListViews.add("Draft Articles");
		articleListViews.add("Published English Articles");
		articleListViews.add("Published French Articles");
		articleListViews.add("Recently Viewed");

		return articleListViews;
	}

	public List<String> setArticleCategoriesList() {

		articleCategories.add("Function");
		articleCategories.add("Product");
		articleCategories.add("Content Type");

		return articleCategories;
	}

	public void setCaseNumberToSearch() {

		caseNumber = getProperty("caseNumber");

	}

	public String setContact_Mailing_Address() {

		addressStreet = getMPTestData("Address Street");
		addressCity = getMPTestData("Address City");
		addressState = getMPTestData("Address State");
		addressCountry = getMPTestData("Address Country");
		addressPostalCode = getMPTestData("Address Postal Code").replace(" ", "");

		actualContact_Address = addressStreet + " " + addressCity + " " + addressState + " " + addressPostalCode + " "
				+ addressCountry;

		return actualContact_Address;
	}

	public void acrPermission_FiedValue() {

		contact_AcrValue.add("Account");
		contact_AcrValue.add("Billing");
		contact_AcrValue.add("General");
		contact_AcrValue.add("Security & Contact");
		contact_AcrValue.add("Service Change");
		contact_AcrValue.add("Signing Authority");
		contact_AcrValue.add("View All Cases");
		contact_AcrValue.add("Technical");
		contact_AcrValue.add("Usage");
		contact_AcrValue.add("Site Contact"); // 9
	}

	public void directRoles_Field() {

		directRoleOptions.add("Administrator");
		directRoleOptions.add("Decider");
		directRoleOptions.add("Coach/Influencer");
		directRoleOptions.add("Signing Authority");
		directRoleOptions.add("Assessor");
		directRoleOptions.add("Endorser");
		directRoleOptions.add("General");
		directRoleOptions.add("Technical");
		directRoleOptions.add("Site Contact");
	}

	public void items_InQueue() {

		queueItemsName.add("Review Order Details");
		queueItemsName.add("Create/Verify Billing Account");
		queueItemsName.add("Review Spec Sheet");
		queueItemsName.add("Initiate Circuit Design");
		queueItemsName.add("Send Order to P&I");
		queueItemsName.add("Capture PHUB data");
		queueItemsName.add("Monitor RPATS Fibre Build");
		queueItemsName.add("Complete Circuit Design And IP Assignment");
		queueItemsName.add("Schedule TIS for IP Assignment");
		queueItemsName.add("Complete IP Assignment");
		queueItemsName.add("Schedule TIS for TTC");
		queueItemsName.add("Complete Access & Service Provisioning");
		queueItemsName.add("Billing Activation Failure");
		queueItemsName.add("Office 365 Order Creation");
		queueItemsName.add("Create Cable Order");
		queueItemsName.add("Create Account in SS Task Failure");
		queueItemsName.add("Create Work Order in SS Task Failure");
		queueItemsName.add("BAN Creation Wireless Fallout Task"); // 17 no

	}

	public Object[][] getMultipleUserProfiles() {

		userProfile = getMPTestData("Profile");
		String[] profiles = userProfile.split("#");
		Object profileNames[][] = new Object[profiles.length][1];

		for (int i = 0; i < profiles.length; i++) {
			System.out.println(".............." + profiles[i]);
			profileNames[i][0] = profiles[i];
		}
		return profileNames;

	}

	/**
	 * @throws ClassNotFoundException
	 * 
	 *                                Add Execution Summary to database
	 */
	public void addTestResultToDB() throws ClassNotFoundException {
		executionTime = DateTimeUtilities.timeDuration(testStartTime, testEndTime, db_timeFormat);

		String viewRecords = "SELECT * FROM " + db_table + " WHERE GROUP_ID ='" + project + "'";

		String insertRecords = "INSERT INTO " + db_table
				+ "(TEST_SCENARIO, STATUS, FAILED_DESCRIPTION,KEY_PARAMETERS, TIME_STAMP, GROUP_ID, TOOL, START_TIME, END_TIME, APPLICATION, RELEASE)"
				+ "VALUES ('" + classname + "','" + testStatus + "','" + failReason + "','" + testType + "','"
				+ executionTime + "','" + project + "','" + automationTool + "','" + testStartTime + "','" + testEndTime
				+ "','" + applicationName + "','" + release + "')";

		System.out.println(insertRecords);
		System.out.println(viewRecords);

		String connectionUrl = db_oracleThin + ":" + db_url + ":" + db_port + ":" + db_servicename;

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {

			// Creating the connection to database and executing the given query
			Class.forName(db_driver);
			con = DriverManager.getConnection(connectionUrl, db_userid, db_password);
			stmt = con.prepareStatement(insertRecords);
			int action = stmt.executeUpdate();

			// Verify Record Inserted Successfully
			if (action > 0) {
				System.out.println("Record inserted to DB Successfully");
			} else {
				System.out.println("Failure in inserting records to DB");
			}

			// View Inserted Records
			stmt = con.prepareStatement(viewRecords);
			rs = stmt.executeQuery(viewRecords);

			ResultSetMetaData rsmd = rs.getMetaData();

			System.out.println("_______________________________DB DATA______________________________");
			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(rsmd.getColumnName(i) + " = " + columnValue);

				}
				System.out.println("");
			}

			// Closing the db connection
			closeDBConnection(con, stmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// Closing the db connection
			closeDBConnection(con, stmt, rs);
		}

	}

	/**
	 * @param con
	 * @param stmt
	 * @param rs
	 * 
	 *             Close DB Connection
	 */
	public static void closeDBConnection(Connection con, Statement stmt, ResultSet rs) {

		// closing DB Connection
		if ((rs != null) || (stmt != null) || (con != null)) {
			try {
				rs.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * @throws IOException
	 * 
	 *                     Refresh addOn value
	 * 
	 */
	public void refreshAddOnValue() throws IOException {
		// TODO Auto-generated method stub
		try {
			addOn_1 = AdditionalUtilities.currentSystemTime("yyMMddhh") + AdditionalUtilities.generateRandomDigits(4);

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}



