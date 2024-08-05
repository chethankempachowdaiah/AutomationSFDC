package com.sfdc.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import com.framework.base.Constants;
import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.DateTimeUtilities;

public class InputData_Sales extends InputData {

	public static String accForSearch = getProperty("accForSearch");
	public static String businessAccBySelf = getProperty("businessAccBySelf");
	public static String industryHelpText = "Search ”Industry” under Knowledge Articles for a list of definitions";
	//Following URLSused for verification of actual and expected URL under links
	public static String createNewSalesforceCaseURL = "https://rogersb2b.my.salesforce.com/500/e?nooverride=1&RecordType=012i0000000AxLI";
	public static String createNewCompensationCaseURL = "https://can01.safelinks.protection.outlook.com/?url=https%3A%2F%2Frogersb2b.my."
			+ "salesforce.com%2Fa4f%2Fo&data=04%7C01%7CMike.Maiorino%40rci.rogers.com%7Ce76d6a1f12a0426aaa7508d929beef15%7C0ab4cbb"
			+ "f4bc74826b52ca14fed5286b9%7C0%7C0%7C637586722292895901%7CUnknown%7CTWFpbGZsb3d8eyJWIjoiMC4wLjAwMDAiLCJQIjoiV2luMzIiLC"
			+ "JBTiI6Ik1haWwiLCJXVCI6Mn0%3D%7C1000&sdata=tMqW8VbQQnUTJIvIxfYvkk7VDRzoSlmfDicyELoNKIw%3D&reserved=0";
	public static String salesforceClassicIssuessAWURL = "https://r4b.lightning.force.com/lightning/r/Knowledge__kav/ka05o000000093iAAA/view";
	public static String salesforceLightingSalesCGURL = "https://r4b.lightning.force.com/lightning/page/chatter/record/0F95o00000000J8CAI";
	public static String wirelessOfferGridURL = "https://r4b.lightning.force.com/lightning/r/ContentDocument/0695o000000DqoYAAS/view";
	public static String wirelineOfferGrideURL = "https://r4b.lightning.force.com/lightning/r/ContentDocument/0695o000000HQmyAAG/view";
	public static String missionPossibleHubURL = "https://rogersaspire.com/program/missionpossible/";
	
	//Below Variables were used for Accounts Creation for Territory Management 
	public static final String accCreationSheet = "Acc_Creation";
	public static final String sbeUnassignedSheet = "SBEUnassigned";
	public static final String unassignedUserSheet = "UnassignedUser";
	public static final String unassignedWholesaleSheet = "UnassignedWholesale";
	public static final String pendingUnassignedSheet = "PendingUnassigned";
	public static final String unassignedBCSheet = "UnassignedBC";
	public static final String unassignedABMWSheet = "UnassignedABMW2";
	public static final String unassignedATSheet = "UnassignedAT2";
	public static final String unassignedONSheet = "UnassignedON";
	public static final String unassignedGTASheet = "UnassignedGTA";
	public static final String unassignedQCSheet = "UnassignedQC";
	public static final String smbData = "SMBDATA";
	public static final String resultsSheet = "Results";
	public static String salesSegment;
	public static String url;
	public static String oppUrl;
	public static String user;
	
	public static void SMBData(Hashtable<String, String> dataTable) throws Exception {
		try {

			sf.dataInput.businessAccountName = readDataTable(dataTable, env + "_BusinessAccount");
			user = readDataTable(dataTable, "User");		
			salesSegment = readDataTable(dataTable,"Sales_Segment");
			url = readDataTable(dataTable,"URL");
			oppUrl = readDataTable(dataTable,"Opp_URL");
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
	
	public static String userid_NIS = getProperty(env + "_" + "userid_NIS");
	public static String userid_SBEUnassigned = getProperty(env + "_" + "userid_SBEUnassigned");
	public static String userid_UnassignedUser = getProperty(env + "_" + "userid_UnassignedUser");
	public static String userid_UnassignedWholesale = getProperty(env + "_" + "userid_UnassignedWholesale");
	public static String userid_PendingUnassigned = getProperty(env + "_" + "userid_PendingUnassigned");
	public static String userid_UnassignedABMW = getProperty(env + "_" + "userid_UnassignedABMW");
	public static String userid_UnassignedAT = getProperty(env + "_" + "userid_UnassignedAT");
	public static String userid_UnassignedON = getProperty(env + "_" + "userid_UnassignedON");
	public static String userid_UnassignedGTA = getProperty(env + "_" + "userid_UnassignedGTA");
	public static String userid_UnassignedQC = getProperty(env + "_" + "userid_UnassignedQC");
	public static String userid_UnassignedBC = getProperty(env + "_" + "userid_UnassignedBC");
	public static String userid_salesqa = getProperty(env + "_" + "userid_salesqa");
	public static String userid_MarketingUser = getProperty(env + "_" + "userid_MarketingUser");
	public static String userid_SalesExecutive = getProperty(env + "_" + "userid_SalesExecutive");

	public static String Profile_NIS = "NIS";
	public static String leadBusAccountName;
	public static String noResultsLead = "No items to display.";
	public static String Profile_SBEUnassigned= "SBEUnassigned";
	public static String Profile_UnassignedUser= "UnassignedUser";
	public static String Profile_UnassignedWholesale= "UnassignedWholesale";
	public static String Profile_PendingUnassigned= "PendingUnassigned";
	public static String Profile_UnassignedBC= "UnassignedBC";
	public static String Profile_UnassignedABMW= "UnassignedABMW";
	public static String Profile_UnassignedAT= "UnassignedAT";
	public static String Profile_UnassignedON= "UnassignedON";
	public static String Profile_UnassignedGTA= "UnassignedGTA2";
	public static String Profile_UnassignedQC= "UnassignedQC";
	public static String Profile_salesqa= "salesqa";
	public static String Profile_MarketingUser= "MarketingUser";
	public static String Profile_SalesExecutive= "SalesExecutive";
	
	public String campaigns = "Campaigns";
	public String campaignName = AdditionalUtilities.generateRandomDigit(6);
	public String endDate = DateTimeUtilities.currentSystemDatePlus(7, "dd/MM/yyyy");	
	public String startDate = DateTimeUtilities.currentSystemDateMinus(10, "dd/MM/yyyy");	
	public String endDate2 = DateTimeUtilities.currentSystemDateMinus(3, "dd/MM/yyyy");	
	public String currentDate = DateTimeUtilities.currentSystemDateMinus(0,"dd/MM/yyyy");
	public String createNewLeadPermission = "Create New Lead";
	public static String rports = "Reports";
	public static String match = "1/2";
	public static String rogersBusID;
	
	public static String taskNewEventSubjectForActivity;
	
	public static int salesAccNo = -1;
	public static final String SMB_Rules_Test_Data = Constants.RESOURCES_DIR + "\\dataFiles\\SMB_Rules_Test_Data.xlsx";
	
	public static String messageFNF = ": File Not Found - We will add $1000 to Account's Available Credit Limit and Total Assigned Credit Limit";
	
	public static int credit_Assigned_FNF = 1000;
	public static int credit_Available_FNF = 1000;
	
	// Function to set Datatable data to variables for Accounts Creation for Territory Management 
	// 
	public static void setDataForCreateAccounts(Hashtable<String, String> dataTable) {
		try {

			salesAccNo = Integer.parseInt(dataTable.get("Serial_No"));
			
		} catch (Exception e) {
			throw e;
		}

	}	
	
}
