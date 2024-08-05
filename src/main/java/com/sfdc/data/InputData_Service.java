package com.sfdc.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class InputData_Service extends InputData {
	
	//Snow Portal
	public static String snowPortalURL=getProperty("snowPortalURL");
		
    //SnowPortal
	public static String snowIncidentID;
	public static String snowCaseID;
	public static String snowAssignmentgrp;
	public static String snowPriority;
	public static String snowPortfolio;
	public static String snowStatus;
	public static String snowReason;
	public static String snowSubject;	
	public static String snowDescription;
	public static String snowData;
	public static String sfdcCaseOrigin;
	public static String sfdcCasePriority;
	public static String sfdcCaseProduct;
	public static String sfdcCaseReason;
	public static String sfdcCaseStatus;
	
	public static String commCaseFirstName;
	public static String commCaseLastName;
	public static String commCaseEmail;
	public static String commCaseServiceID;
	public static HashMap<String, String> verifyValue;
	
	//Internal Guided Case
	public static List<String> productFamilyOptions;
    public static List<String> categoryOptions;
    public static List<String> subCategoryOptions;
    
    //External Ticket
    public static List<String> expectedSourceOptions;
    
    //ETM Case
    public static String billingAccountForETMCase = getProperty(env + "_" + "billingAccountForETMCase");
    public static String contactForETMCase = getProperty(env + "_" + "contactForETMCase");
    public static String contactForInternalGuidedCase;
    public static String billingAccountForInternalGuidedCase;
    public static String businessAccountForInternalGuidedCase;
    
    //subscription
    public static String contactForSubscription;
    public static String contactForSubscriptionWireless;
    public static String billingAccountForSubscription;
    public static String subscriptionName;
    public static String subscriptionValue;
    public static String subscriptionValueWireless;
    
    //Login to Communities for fraud case search
    public static String communities_url = getProperty(env + "_" + "Communities_appURL");
    public static String communities_userid = getProperty(env + "_" + "Communities_userid");
    public static String communities_password = getProperty(env + "_" + "Communities_password_encoded");
    
    //Survey Score Visualization on Case and Account
    public static String relationshipScoreBusinessAccount = getProperty(env + "_" + "RelationshipScoreBusinessAccount");
    public static String touchPointScoreCustomerCase = getProperty(env + "_" + "TouchPointScoreCustomerCase");
    public static String relationshipScoreCustomerCase = getProperty(env + "_" + "RelationshipScoreCustomerCase");
    
    //Product options for fraud case not shown by community user
    public static List<String> productOptions;
    
    //ACR Permission
    public static List<String> ACRPermissionValues;
    public static List<String> ACRPermissionValuesBillingAccount;
    public static String businessAccountACR;
    
    //Case Owner get notified for editing comments
    public static String commentsText;
    
    //b2b details
    public static String urlb2b = getProperty("appURLb2b");
    public static String userid_b2b = getProperty("userid_b2b");
    public static String password_b2b = getProperty("password_encoded_b2b");
    
	public InputData_Service() {
	//CommCase Address DATA
	 commCaseFirstName="Jhon";
	 commCaseLastName="Hopper";	
	 commCaseEmail="Jhon@mailnator.com";	
	 commCaseServiceID="1234567";
	 businessAccountACR=getProperty(env + "_" + "businessAccountACR");
	 
	 contactForInternalGuidedCase = getProperty(env + "_" + "contactForInternalGuidedCase");
	 billingAccountForInternalGuidedCase = getProperty(env + "_" + "billingAccountForInternalGuidedCase");
	 businessAccountForInternalGuidedCase = getProperty(env + "_" + "businessAccountForInternalGuidedCase");
	 billingAccountForSubscription = getProperty(env + "_" + "billingAccountForSubscription");
	 contactForSubscription = getProperty(env + "_" + "contactForSubscription");
	 contactForSubscriptionWireless = getProperty(env + "_" + "contactForSubscriptionWireless");
	 subscriptionValue = getProperty(env + "_" + "subscriptionValue");
	 subscriptionValueWireless = getProperty(env + "_" + "subscriptionValueWireless");
	}
	
	public static void setDataForProductFamilyOptions()
	{
		productFamilyOptions = new ArrayList<String>();
		productFamilyOptions.add("Data Centre");
	    productFamilyOptions.add("Internet and Advanced Networks");
	    productFamilyOptions.add("Internet of Things");
	    productFamilyOptions.add("Voice and Collaboration");
	    productFamilyOptions.add("Wireless");
	}
	
	public static void setDataForSourceOptions()
	{
		expectedSourceOptions = new ArrayList<String>();
		expectedSourceOptions.add("Canada Post Tracking Number");
		expectedSourceOptions.add("Data Center");
		expectedSourceOptions.add("ETM Ticket");
		expectedSourceOptions.add("Field Tech");
		expectedSourceOptions.add("Field Tech. Maintenance");
		expectedSourceOptions.add("iBSS");
		expectedSourceOptions.add("Purolator Tracking Number");
		expectedSourceOptions.add("UPS Tracking Number");
		expectedSourceOptions.add("SNOW Customer case");
		expectedSourceOptions.add("Ledcor");
	}
	
	public static void setDataForACRPermissionValues()
	{
		ACRPermissionValues = new ArrayList<String>();
		ACRPermissionValues.add("Account");
		ACRPermissionValues.add("Billing");
		ACRPermissionValues.add("General");
		ACRPermissionValues.add("Security & Contact");
		ACRPermissionValues.add("Service Change");
		ACRPermissionValues.add("Signing Authority");
		ACRPermissionValues.add("View All Cases");
	}
	
	public static void setDataForACRPermissionValuesBillingAccount()
	{
		ACRPermissionValuesBillingAccount = new ArrayList<String>();
		ACRPermissionValuesBillingAccount.add("General");
		ACRPermissionValuesBillingAccount.add("Service Change");
		ACRPermissionValuesBillingAccount.add("Technical");
		ACRPermissionValuesBillingAccount.add("Usage");
	}


	
	public static void setDataForCategoryOptions()
	{
		categoryOptions = new ArrayList<String>();
		categoryOptions.add("Escalation - Fraud");
		categoryOptions.add("Fraud Investigation");
	}
	
	public static void setDataForSubCategoryOptions()
	{
		subCategoryOptions = new ArrayList<String>();
		subCategoryOptions.add("Multiple HUP");
		subCategoryOptions.add("High Risk Order");
		subCategoryOptions.add("PIN/Password Reset");
		subCategoryOptions.add("High Risk SIM Swap");		
	}
	
	public static void setDataForProductsOptions()
	{
	    productOptions = new ArrayList<String>();
	    productOptions.add("Business Phone");
	    productOptions.add("Internet and Advanced Network");
	    productOptions.add("Wireless");
	    productOptions.add("Data Centre & Cloud");
	    productOptions.add("Internet of Things");
	    productOptions.add("Enterprise Mobility Management");
	    productOptions.add("SaaS");
	}
		
	//data to be set for case creation in Salesforce from Excel 
	public static void setDataForSFDCCase(Hashtable<String,String> dataTable) {
		sfdcCaseOrigin=dataTable.get("Case_Origin");
		sfdcCasePriority=dataTable.get("Case_Priority");
		sfdcCaseProduct=dataTable.get("Case_Product");
		sfdcCaseReason=dataTable.get("Case_Reason");
		sfdcCaseStatus=dataTable.get("Case_Status");
		
		verifyValue=new HashMap<String,String>();
		
		verifyValue.put("Critical","1 - Critical");
		verifyValue.put("High","2 - High");
		verifyValue.put("Medium","3 - Moderate");
		verifyValue.put("Low","4 - Low");
		verifyValue.put("Planning","5 - Planned");
		verifyValue.put("Business Phone","Voice");
		verifyValue.put("Enterprise Mobility Management","EMM");
		verifyValue.put("Internet and Advanced Network","Data");
		verifyValue.put("Internet of Things","IoT/M2M");
		verifyValue.put("SaaS","SaaS");
		verifyValue.put("Wireless","Wireless Device");
		verifyValue.put("Account management","");
		verifyValue.put("Customer Environment","Customer Environment");
		verifyValue.put("Customer provided wrong info","");
		verifyValue.put("Device and Hardware","Device/hardware");
		verifyValue.put("Duplicate Ticket","Duplicate");
		verifyValue.put("Monitoring Event Cleared","");
		verifyValue.put("Network - Capacity","Network");
		verifyValue.put("Network - Core Fault","Network");
		verifyValue.put("Network - Known Outage","Network");
		verifyValue.put("Network -Limitation","Network");
		verifyValue.put("Network - Planned Outage","Network");
		verifyValue.put("Network - Radio Network Fault","Network");
		verifyValue.put("No Response","No Response");
		verifyValue.put("No trouble found","No Trouble found");
		verifyValue.put("Problem Ticket","Problem Ticket");
		verifyValue.put("Provisioning","Provisioning");
		verifyValue.put("Rogers Vendors / Cloud platform","Service Platform");
		verifyValue.put("Software","Software");
		verifyValue.put("Third Party","Third Party");
		verifyValue.put("In Progress","Active");
		verifyValue.put("Awaiting Problem Ticket","Awaiting Problem Ticket");
		verifyValue.put("Awaiting Customer Response","Awaiting Customer Info");
		verifyValue.put("Awaiting Monitoring","Awaiting Monitoring");
		verifyValue.put("Awaiting Third Party","Awaiting Third Party");
		verifyValue.put("Resolved","Resolved Pending Customer Confirmation");
		
	}

}
