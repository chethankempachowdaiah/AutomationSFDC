package com.framework.base;

import com.framework.utilities.DateTimeUtilities;
import com.sfdc.data.InputData;

/**
 * @author priyanka.acharya, Date : 09/12/2019
 *
 *         Constants Variables are declared as public static, so that they can
 *         be called in any other methods without instantiation.
 * 
 *         Constant Variables are declared a final, so that they cannot be
 *         changed during the execution
 * 
 */
public class Constants {

	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String USER_HOME = System.getProperty("user.home");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String RESOURCES_DIR = USER_DIR + "\\src\\test\\resources\\";
	public static final String USER_PROFILE_DIR = "C:\\Users\\" + System.getProperty("user.name");
	public static final String REPORTS_DIR = USER_DIR + "\\reports_and_logs\\";
	public static final String DOWNLOADS_DIR = "Downloaded File";
	public static final String REPORT_TITLE = "Test Automation Execution Summary Report";

	public static final String EXECUTION_SUMMARY_REPORT = "/reports_and_logs/Automation_Run_" +InputData.env.toString()
			+ DateTimeUtilities.currentSystemDate("_MM_dd_HHmmss") + ".html";

	public static final String CHROME_USERDATA_DIR = USER_PROFILE_DIR
			+ "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\";

	public static final String CHROMEDRIVER = RESOURCES_DIR + "\\drivers\\chromedriver.exe";
	public static final String GECKODRIVER = RESOURCES_DIR + "\\drivers\\geckodriver.exe";
	public static final String IEDRIVER = RESOURCES_DIR + "\\drivers\\IEDriverServer.exe";

	public static final String JENKINS_DIR_TEMP = "E:\\Automation_workspace\\sfdc_Automation_config\\config.properties";

	public static final String GUIDEDCASE_TESTDATA_FILE = Constants.RESOURCES_DIR + "\\dataFiles\\GuidedCaseTestData.xlsx";
	public static final String MP_TESTDATA_FILE = Constants.RESOURCES_DIR + "\\dataFiles\\MP_TestData.xlsx";
	public static final String WA_TESTDATA_FILE = Constants.RESOURCES_DIR + "\\dataFiles\\WA_TestData.xlsx";
	public static final String SAMPLE_UPLOAD_FILE = Constants.RESOURCES_DIR + "\\dataFiles\\Sample_Evidence.docx";
	public static final String BUSINESS_CARD = USER_DIR + "\\src\\test\\resources\\dataFiles\\Sample_Upload_File.txt";
	public static final String COMPANY_LETTER = USER_DIR + "\\src\\test\\resources\\dataFiles\\Sample_Business_Card.docx";
	public static final String SAMPLE_DOCUMENT_PATH = Constants.RESOURCES_DIR + "\\dataFiles\\";
	public static final String FILE_TEXT_BOX = USER_DIR + "\\src\\test\\resources\\sikulifiles\\file_Text_Box.png";
	public static final String OPEN_BUTTON = USER_DIR + "\\src\\test\\resources\\sikulifiles\\open_Button.png";
	public static final String SIKULI_PATH = Constants.RESOURCES_DIR + "\\sikulifiles\\";
	public static final String SAMPLE_FILE_FOR_FILES_UPLOAD_PAGE = Constants.RESOURCES_DIR
			+ "dataFiles\\Sample_Upload_File.txt";
	public static final String PRICE_COST_FILE = Constants.RESOURCES_DIR + "\\dataFiles\\Quote_Pricing_Cost.xlsx";
	public static final String CPQ_PRICE_PLAN_FILE = Constants.RESOURCES_DIR + "\\dataFiles\\CPQ_PricePlan.xlsx";

	public static final String RECURRING_PRICE_COST_SCRUM_FILE = Constants.RESOURCES_DIR
			+ "\\dataFiles\\Recurring_Cost_price CPQ.xlsx";

	public static final String TESTDATA_COMM_FILE = Constants.RESOURCES_DIR
			+ "\\dataFiles\\PBF_Test_Data.xlsx";
	
	public static final String TESTDATA_COMM_PRICING_VALIDATION_FILE = Constants.RESOURCES_DIR
			+ "\\dataFiles\\Communities_Test_Data_PriceValidation.xlsx";
	
	public static final String PBF_CUSTOMER_SHEET = "PBF_CUSTOMER";
	public static final String PBF_CUSTOMER_SHEET_ALLENV = "PBF_CUSTOMER_COMPACT";
	public static final String PBF_CUSTOMER_LOP_SHEET = "PBF_CUSTOMER_LOST_OPP";
	public static final String PBF_AGENT_SHEET = "PBF_AGENT";
	public static final String PBF_AGENT_SHEET_ALLENV_BI = "PBF_AGENT_COMPACT_BI";
	public static final String PBF_AGENT_SHEET_ALLENV_RDI = "PBF_AGENT_COMPACT_RDI";
	public static final String PBF_AGENT_SHEET_RDI_PRICE_VALIDATION_ON_NET = "PBF_AGENT_COMPACT_RDI_ON-NET";
	public static final String PBF_AGENT_SHEET_RDI_PRICE_VALIDATION_ABA_ON = "PBF_AGENT_COMPACT_RDI_ABA-ON";
	public static final String PBF_AGENT_SHEET_RDI_PRICE_VALIDATION_ABA_EAST = "PBF_AGENT_COMPACT_RDI_ABA-East";
	public static final String PBF_AGENT_SHEET_RDI_SPECSHEET = "PBF_RDI_SPECSHEET_54365";

	public static final String PBF_AGENT_SHEET_ALLENV_BITVOFF = "PBF_AGENT_COMPACT_BusIntTVOff";
	public static final String PBF_AGENT_SHEET_ALLENV_TV = "PBF_AGENT_COMPACT_TV";
	public static final String PBF_AGENT_SHEET_ALLENV_MULTISITE = "PBF_AGENT_COMPACT_MULTISITE";
	public static final String PBF_ADDRESSES = "Addresses";

	public static final String PBF_AGENT_SHEET_BITVOFF_FRAUD_ESIGN = "PBF_AGENT_IntOffc_TV_Fraud_Esig";


	public static final String CONTRACT_CODE_SHEET = "Contract_Code";
	public static final String CASE_ROUTING_SHEET = "Case_Routing";
	public static final String RECURRING_PRICE_COST_SHEET = "Recurring Sheet";
	public static final String GBJ_SHEET = "GBJ";
	public static final String GBJ_IBLC_SHEET = "GBJ_IBLC";
	public static final String EPC_SHEET = "EPC_Changes";
	public static final String INT_TV_E2E_SHEET = "INT_TV_E2E";
	public static final String INT_TV_E2E_DEV_SHEET = "INT_TV_E2E_Dev";
	public static final String IBLC_E2E_SHEET = "IBLC_E2E";
	public static final String IBLC_E2E_OM_SHEET = "IBLC_E2E_OMScrum";
	public static final String GBJ_RDI_SHEET = "GBJ_RDI";
	public static final String GBJ_RDI_RESUME_SHEET = "RDI";
	public static final String GBJ_RDI_ORDER_CANCEL_SHEET = "RDI_ORDER_CANCEL";
	public static final String GBJ_TASKFAIL_ORDERCANCEL_ATL_SHEET = "GBJ_IBLC_ORDERCANCEL_ATL";
	public static final String GBJ_TASKFAIL_ORDERCANCEL_ON_SHEET = "GBJ_IBLC_ORDERCANCEL_ON";

	public static final String PRICE_COST_SHEET = "price_cost";
	public static final String PRICE_COST_IBLC_SHEET = "price_cost_IBLC";

	public static final String W2C_SERVICE_SHEET = "W2C_Service";
	public static final String W2C_TECH_SUPPORT_SHEET = "W2C_Tech_Support";
	
	public static final String Proactive_Case_SUPPORT_SHEET ="SFDC_Proactive_Case";

	public static final String WA_PVT = "WA_PVT";

	public static final String CASE_MANAGEMENT_CATEGORIES_FILE = Constants.RESOURCES_DIR
			+ "\\dataFiles\\Case management categories for Tech Support and Service V18.xlsx";

	public static final String CASE_MANAGEMENT_CATEGORIES_FILE_FRENCH = Constants.RESOURCES_DIR
			+ "\\dataFiles\\Case management categories for Tech Support and Service V18_French.xlsx";
	public static final String TECH_SUPPORT_SHEET = "Tech support record type";

	public static final String SERVICE_TYPE_SHEET = "Service Type";
	
	public static final String DELIMITER_COMMA = ",";

	public static final String LOGGER_ROLLINGFILE = "rollingFileLogger";

	public static final String DOWNLOADS_LOCATION = Constants.REPORTS_DIR + DOWNLOADS_DIR + "\\";

	public static final String ACCEPTED_PDF = "Accepted.pdf";
	public static final String PROPOSAL_PDF = "Proposal.pdf";
	public static final String CONTRACT_PDF = "Contract EV1.pdf";
	public static final String AGREEMENT_PDF = "Agreement.pdf";
	
	public static final String EMAIL_ACCEPT_TEXT = "You will find attached a service agreement based on your acceptance of our pricing proposal.";
	public static final String EMAIL_PROPOSAL_TEXT = "You will find attached a pricing proposal based on our recent discussions.";
	public static final String EMAIL_SITECONTACT_TEXT1 = "You've been identified by ";
	public static final String EMAIL_SITECONTACT_TEXT2 = "as someone who can help coordinate the delivery of Rogers service(s) to ";
	public static final String EMAIL_SITECONTACT_TEXT3= ". Please see the details below. A Rogers representative will be in touch soon to discuss the details.";
	public static final String WIRELESS_PLANS_SELECTION_SHEET = "Wireless_Plans_Selection";
	public static final String WIRELESS_MACD_ADD_ADDONS = "MACD_Add_AddOn";
	public static final String WIRELESS_MACD_REMOVE_ADDONS = "MACD_Remove_AddOns";
	public static final String WIRELESS_PLANS_SELECTION_SHEET_R4BPreFIT = "R4BPreFIT";
	public static final String WIRELESS_PLANS_SELECTION_SHEET_PreProd = "WACC_Production";
	public static final String TERMSANDCONDITIONS_INTERNET_IBLC = "https://www.rogers.com/cms/pdf/en/business-fixed-network-terms-en.pdf";
	public static final String TERMSANDCONDITIONS_TV ="https://www.rogers.com/cms/pdf/en/business-productivity-terms-en.pdf";
	public static final String Task_Subject = "Quote Finalized – Select/Add Site Contact from the order page via 'Update Order Details' / Devis finalisé – Sélectionner/Ajouter un contact sur place à partir de la page de commande en cliquant sur 'Mettre à jour les détails de la commande'";
	
	public static final String MP_FrenchTestDataSheetName= "SFDC_French_TestData";
	public static final String MP_EnglishTestDataSheetName= "SFDC TestData";
}
