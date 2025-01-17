package com.sfdc.page_objects;

import com.framework.base.MyListener;
import com.framework.utilities.SeleniumUtilities;

import sfdc.page_objects.communities.Communities_CompleteOrderFlow_Objects;
import sfdc.page_objects.communities.Communities_ContactUs_Objects;
import sfdc.page_objects.communities.Communities_Home_Objects;
import sfdc.page_objects.communities.Communities_Login_Objects;
import sfdc.page_objects.communities.Communities_MyBusinessCaseDetails_Objects;
import sfdc.page_objects.communities.Communities_MyBusinessCases_Objects;
import sfdc.page_objects.communities.Communities_MyBusinessOrdersDetails_Objects;
import sfdc.page_objects.communities.Communities_PBF_AddNewSite_Objects;
import sfdc.page_objects.communities.Communities_PBF_CablePlanSelection_Objects;
import sfdc.page_objects.communities.Communities_PBF_Objects;
import sfdc.page_objects.communities.Communities_PBF_OrderConfirmationScreen_Objects;
import sfdc.page_objects.communities.Communities_PBF_OrderReview_Objects;
import sfdc.page_objects.communities.Communities_PBF_ShoppingCart_Objects;
import sfdc.page_objects.communities.Communities_ReviewSpecSheet_Objects;
import sfdc.page_objects.macd.MACD_AccountManagement_Objects;
import sfdc.page_objects.macd.MACD_BlockService_Object;
import sfdc.page_objects.macd.MACD_ChangeWirelessPlans_Objects;
import sfdc.page_objects.macd.MACD_RemoveAddOn_Objects;
import sfdc.page_objects.macd.MACD_ReviewPlaceOrder_Objects;
import sfdc.page_objects.macd.MACD_ReviewSummaryScreen_Objects;
import sfdc.page_objects.macd.MACD_ReviewWirelessLine_Objects;
import sfdc.page_objects.macd.MACD_SelectAddOn_Objects;
import sfdc.page_objects.macd.MACD_TelephNumberChange_Objects;
import sfdc.page_objects.om.SFDC_CableOrder_ManualQueueTask_Objects;
import sfdc.page_objects.om.SFDC_CompleteVlocityOrder_Objects;
import sfdc.page_objects.om.SFDC_CreateSuperSystemOrder_Objects;
import sfdc.page_objects.om.SFDC_ManualQueueQueueDetails_Objects;
import sfdc.page_objects.om.SFDC_ManualQueues_Objects;
import sfdc.page_objects.om.SFDC_OrchestrationItem_Objects;
import sfdc.page_objects.om.SFDC_OrchestrationPlan_Objects;
import sfdc.page_objects.om.SFDC_OrderDecomposition_Objects;
import sfdc.page_objects.om.SFDC_OrderDetails_Objects;
import sfdc.page_objects.om.SFDC_OrderFailure_Objects;
import sfdc.page_objects.om.SFDC_OrderRelated_Objects;
import sfdc.page_objects.om.SFDC_OrderWithPendingTask_Objects;
import sfdc.page_objects.om.SFDC_Orders_Objects;
import sfdc.page_objects.om.SFDC_ReviewOrderDetailsComplete_Objects;
import sfdc.page_objects.partnercommunities.PartnerCommunities_AccountDetails_Objects;
import sfdc.page_objects.partnercommunities.PartnerCommunities_Home_Objects;
import sfdc.page_objects.partnercommunities.PartnerCommunities_Login_Objects;
import sfdc.page_objects.partnercommunities.PartnerCommunities_OpportunityDetails_Objects;
import sfdc.page_objects.qm.SFDC_CPQHome_Objects;
import sfdc.page_objects.qm.SFDC_CPQ_QuoteRecord_Objects;
import sfdc.page_objects.qm.SFDC_CreateQuote_Objects;
import sfdc.page_objects.qm.SFDC_GuidedByJourney_IBLC_Objects;
import sfdc.page_objects.qm.SFDC_GuidedByJourney_IBLC_Provisioning_Details_Objects;
import sfdc.page_objects.qm.SFDC_GuidedByJourney_Internet_TV_Objects;
import sfdc.page_objects.qm.SFDC_GuidedByJourney_Objects;
import sfdc.page_objects.qm.SFDC_GuidedByJourney_RDI_Objects;
import sfdc.page_objects.qm.SFDC_HybridCart_IBLC_Objects;
import sfdc.page_objects.qm.SFDC_PBF_CablePlan_Selection_Objects;
import sfdc.page_objects.qm.SFDC_PBF_MultiSiteSelection_Objects;
import sfdc.page_objects.qm.SFDC_PBF_MultiSite_ShoppingCart_Objects;
import sfdc.page_objects.qm.SFDC_PBF_Multisite_OrderReview_Objects;
import sfdc.page_objects.qm.SFDC_PBF_Office365AddOns_Selection_Objects;
import sfdc.page_objects.qm.SFDC_PBF_OrderConfirmationScreen_Objects;
import sfdc.page_objects.qm.SFDC_PBF_OrderReview_Objects;
import sfdc.page_objects.qm.SFDC_PBF_ShoppingCart_Objects;
import sfdc.page_objects.qm.SFDC_PBF_SiteContact_Objects;
import sfdc.page_objects.qm.SFDC_PBF_SiteSelection_Objects;
import sfdc.page_objects.qm.SFDC_PBF_TVAddOns_Selection_Objects;
import sfdc.page_objects.qm.SFDC_PBF_TVPlanSelection_Objects;
import sfdc.page_objects.qm.SFDC_PBF_TV_AddOns_Selection_Objects;
import sfdc.page_objects.qm.SFDC_QuoteDetails_Objects;
import sfdc.page_objects.qm.SFDC_QuoteRelated_Objects;
import sfdc.page_objects.qm.SFDC_QuoteReview_Objects;
import sfdc.page_objects.qm.SFDC_Quote_GenerateDocument_Objects;
import sfdc.page_objects.qm.SFDC_Quote_SelectSiteContact_Objects;
import sfdc.page_objects.qm.SFDC_Quotes_Objects;
import sfdc.page_objects.sales.SFDC_Campaigns_Objects;
import sfdc.page_objects.sales.SFDC_CreateOpportunity_Objects;
import sfdc.page_objects.sales.SFDC_GuidedSellingByRingDNA_Objects;
import sfdc.page_objects.sales.SFDC_Leads_Objects;
import sfdc.page_objects.sales.SFDC_Opportunities_Objects;
import sfdc.page_objects.sales.SFDC_OpportunityCloseInfo_Objects;
import sfdc.page_objects.sales.SFDC_OpportunityDetails_Objects;
import sfdc.page_objects.sales.SFDC_OpportunityRelated_Objects;
import sfdc.page_objects.sales.SFDC_R4B_Quote_Approval_Page_Layout_Objects;
import sfdc.page_objects.sales.SFDC_Setup_Objects;
import sfdc.page_objects.sales.SFDC_TaskCreation_Objects;
import sfdc.page_objects.sales.SFDC_TaskDetails_Objects;
import sfdc.page_objects.sales.SFDC_TaskRelated_Objects;
import sfdc.page_objects.sales.SFDC_b2b_Account_Objects;
import sfdc.page_objects.sales.SFDC_b2b_Contact_Objects;
import sfdc.page_objects.service.Email_Mailinator_Objects;
import sfdc.page_objects.service.SFDC_AccountDetails_Objects;
import sfdc.page_objects.service.SFDC_AccountHierarchy_Objects;
import sfdc.page_objects.service.SFDC_AccountRelated_Objects;
import sfdc.page_objects.service.SFDC_Accounts_LinkMasterAccount_Objects;
import sfdc.page_objects.service.SFDC_Accounts_Objects;
import sfdc.page_objects.service.SFDC_CaseDetails_Objects;
import sfdc.page_objects.service.SFDC_CaseKnowledge_Objects;
import sfdc.page_objects.service.SFDC_CaseRelatedDetails_Objects;
import sfdc.page_objects.service.SFDC_CaseRelated_Objects;
import sfdc.page_objects.service.SFDC_Cases_Objects;
import sfdc.page_objects.service.SFDC_ChangeLegalName_Objects;
import sfdc.page_objects.service.SFDC_ChangePrimaryContact_Objects;
import sfdc.page_objects.service.SFDC_ContactDetails_Objects;
import sfdc.page_objects.service.SFDC_Contacts_Objects;
import sfdc.page_objects.service.SFDC_CreateBillingAccount_Objects;
import sfdc.page_objects.service.SFDC_CreateBusinessAccount_Objects;
import sfdc.page_objects.service.SFDC_CreateContact_Objects;
import sfdc.page_objects.service.SFDC_CreateServiceAccount_Objects;
import sfdc.page_objects.service.SFDC_Customer_Case_Objects;
import sfdc.page_objects.service.SFDC_EmailToCase_OmniChannel_Objects;
import sfdc.page_objects.service.SFDC_EnableCommunityUser_Objects;
import sfdc.page_objects.service.SFDC_Knowledge_Details_Objects;
import sfdc.page_objects.service.SFDC_Knowledge_Objects;
import sfdc.page_objects.service.SFDC_OmniSupervisor_Objects;
import sfdc.page_objects.service.SFDC_PartyRelationships_Objects;
import sfdc.page_objects.service.SFDC_QuickText_Objects;
import sfdc.page_objects.service.SFDC_ReviewChangeInEmployeeSize_Objects;
import sfdc.page_objects.service.SFDC_ServiceAccountPremisesDetails_Objects;
import sfdc.page_objects.service.SFDC_ServiceAccountRelated_Objects;
import sfdc.page_objects.service.SFDC_Subscription_Objects;
import sfdc.page_objects.service.SNOW_Case_ValidationPage;
import sfdc.page_objects.wacc.WACC_AccessoriesDetails_Objects;
import sfdc.page_objects.wacc.WACC_AddOns_Selection_Objects;
import sfdc.page_objects.wacc.WACC_BrowseAccessories_Objects;
import sfdc.page_objects.wacc.WACC_GenerateDocument_ESign_Objects;
import sfdc.page_objects.wacc.WACC_Product_Selection_Objects;
import sfdc.page_objects.wacc.WACC_ReviewOrder_Objects;
import sfdc.page_objects.wacc.WACC_ShopWirelessDevices_Objects;
import sfdc.page_objects.wacc.WACC_ShoppingCart_Objects;

/**
 * @author Priyanka.Acharya, @Date 26/Sept/2019
 *
 *         This Class represents all the pages of the SFDC application,
 *         Basically all the page object classess are initialized here
 */
public class SFDC_AllPageObjects extends MyListener {

	public SFDC_Login_Objects login;
	public SFDC_Home_Objects home;
	public SFDC_Opportunities_Objects opp;
	public SFDC_Accounts_Objects acc;
	public SFDC_Contacts_Objects contacts;
	public SFDC_Quotes_Objects quotes;
	public SFDC_Orders_Objects orders;
	public SFDC_ManualQueues_Objects mques;
	public SFDC_OmniSupervisor_Objects omni;
	public SFDC_CreateBusinessAccount_Objects cba;
	public SFDC_CreateContact_Objects cc;
	public SFDC_ChangePrimaryContact_Objects cpc;
	public SFDC_CreateServiceAccount_Objects csa;
	public SFDC_CreateBillingAccount_Objects cbia;
	public SFDC_AccountDetails_Objects ad;
	public SFDC_ServiceAccountRelated_Objects saccRelated;
	public SFDC_ReviewChangeInEmployeeSize_Objects es;
	public SFDC_ChangeLegalName_Objects cln;
	public SFDC_AccountRelated_Objects ar;
	public SFDC_ContactDetails_Objects cd;
	public SFDC_CreateOpportunity_Objects co;
	public SFDC_CreateQuote_Objects cq;
	public SFDC_OpportunityDetails_Objects od;
	public SFDC_OpportunityRelated_Objects or;
	public SFDC_OpportunityCloseInfo_Objects ocInfo;
	public SFDC_CPQHome_Objects cpqHome;
	public SFDC_HybridCart_IBLC_Objects iblcCart;
	public SFDC_CPQ_QuoteRecord_Objects cpqQuoteRec;
	public SFDC_ServiceAccountPremisesDetails_Objects premises;
	public SFDC_Quote_GenerateDocument_Objects gdPdf;
	public SFDC_QuoteReview_Objects quoteReview;
	public SFDC_Quote_SelectSiteContact_Objects siteCon;
	public SFDC_QuoteRelated_Objects qr;
	public SFDC_QuoteDetails_Objects qd;
	public SFDC_OrderDetails_Objects orDetails;
	public SFDC_OrderRelated_Objects orRelated;
	public SFDC_OrderDecomposition_Objects orDecompose;
	public SFDC_OrderFailure_Objects orFailure;
	public SFDC_OrchestrationPlan_Objects orchPlan;
	public SFDC_ManualQueueQueueDetails_Objects manQue;
	public SFDC_CreateSuperSystemOrder_Objects crSSOdr;
	public SFDC_OrchestrationItem_Objects orchItem;
	public SFDC_OrderWithPendingTask_Objects pendingTask;
	public SFDC_CompleteVlocityOrder_Objects compVlctOdr;
	public SFDC_EmailToCase_OmniChannel_Objects omniChannel;
	public SFDC_CaseDetails_Objects caseDetails;
	public SFDC_CaseRelated_Objects caseRelated;
	public SFDC_CaseRelatedDetails_Objects caseRelatedDetails;
	public SFDC_Cases_Objects cases;
	public SFDC_QuickText_Objects qt;
	public SFDC_Accounts_LinkMasterAccount_Objects liMAcc;
	public SFDC_AccountHierarchy_Objects accHi;
	public SFDC_PartyRelationships_Objects pr;
	public SFDC_GuidedByJourney_Objects gbj;
	public SFDC_GuidedByJourney_Internet_TV_Objects gbjCart;
	public SFDC_GuidedByJourney_IBLC_Objects gbjIBLC;
	public SFDC_GuidedByJourney_IBLC_Provisioning_Details_Objects gbjProv;
	public SFDC_ReviewOrderDetailsComplete_Objects rODComplete;
	public SFDC_CableOrder_ManualQueueTask_Objects cableTaskItems;
	public SFDC_GuidedByJourney_RDI_Objects gbjRDI;
	public SFDC_EnableCommunityUser_Objects ecu;
	public SFDC_Subscription_Objects subs;
	public SFDC_Files_Objects files;
	public SFDC_Customer_Case_Objects customerCase;
	public SFDC_Setup_Objects setup;

	public SFDC_TaskCreation_Objects task;
	public SFDC_TaskDetails_Objects taskDetails;
	public SFDC_Knowledge_Objects knowledge;
	public SFDC_Knowledge_Details_Objects knowledgeDetails;
	public SFDC_CaseKnowledge_Objects caseKnowledge;
	public SFDC_R4B_Quote_Approval_Page_Layout_Objects r4Bquote;
	public SFDC_GuidedSellingByRingDNA_Objects gSBRingDNA; 
	public SFDC_Campaigns_Objects campaign; 

	public Email_Mailinator_Objects mailinator;

	public Communities_Login_Objects comLogin;
	public Communities_ContactUs_Objects comContactUs;
	public Communities_Home_Objects comHome;
	public Communities_MyBusinessCases_Objects comMBC;
	public Communities_MyBusinessCaseDetails_Objects comCaseDetails;
	public Communities_MyBusinessOrdersDetails_Objects comOrderDetails;
	public Communities_ReviewSpecSheet_Objects comReviewSpecSheet;
	public Communities_PBF_Objects comPBF;
	public Communities_PBF_ShoppingCart_Objects comPBFShopCart;
	public Communities_PBF_CablePlanSelection_Objects comPBFCablePlan;
	public Communities_PBF_OrderReview_Objects comPBFOrderReview;
	public Communities_PBF_OrderConfirmationScreen_Objects comPBFOrderConfirm;
	public Communities_PBF_AddNewSite_Objects comPBFAddSite;
	public SFDC_PBF_TVPlanSelection_Objects comPBFTVPlan;
	public SFDC_PBF_TV_AddOns_Selection_Objects comPBFTVAddons;
	
	public SFDC_PBF_SiteSelection_Objects siteSelPBF;
	public SFDC_PBF_CablePlan_Selection_Objects planSelPBF;
	public SFDC_PBF_ShoppingCart_Objects shopCartPBF;
	public SFDC_PBF_OrderReview_Objects orderRevPBF;
	public SFDC_PBF_OrderConfirmationScreen_Objects orderConfPBF;
	public SFDC_PBF_SiteContact_Objects siteConPBF;
	public SFDC_PBF_TVAddOns_Selection_Objects tvAddOnsPBF;
	public SFDC_PBF_Office365AddOns_Selection_Objects offAddOnsPBF;
	
	public SFDC_PBF_MultiSiteSelection_Objects mulSiteSel;
	public SFDC_PBF_MultiSite_ShoppingCart_Objects mulSiteShopCart;
	public SFDC_PBF_Multisite_OrderReview_Objects mulSiteOrderReview;
	
	public SFDC_b2b_Account_Objects b2bAccountObj;
	public SFDC_b2b_Contact_Objects b2bContactObj;
	public SFDC_UserProfile_Objects userProf;
	public SFDC_TaskRelated_Objects taskRelated;
	public SFDC_Leads_Objects lead;

	public PartnerCommunities_Home_Objects partnerCommHome;
	public PartnerCommunities_Login_Objects partnerCommLogin;
	public PartnerCommunities_AccountDetails_Objects partnerCommAccDetails;
	public PartnerCommunities_OpportunityDetails_Objects partnerCommOppDetails;

	public SNOW_Case_ValidationPage snowCasePage;

	public SeleniumUtilities seleU;
	
	public WACC_Product_Selection_Objects proSel;
	public WACC_AddOns_Selection_Objects addOnSel;
	public WACC_ShoppingCart_Objects shopCartObj;
	public WACC_ReviewOrder_Objects revOrderObj;
	public WACC_GenerateDocument_ESign_Objects genDocObj;
	
	public WACC_ShopWirelessDevices_Objects shopWADevobj;
	public  WACC_BrowseAccessories_Objects bAccessoriesObj;
	public WACC_AccessoriesDetails_Objects accessoryDetailObj;
	public MACD_AccountManagement_Objects accManagementObj;
	public MACD_ReviewWirelessLine_Objects reviewWALineObj;
	public MACD_SelectAddOn_Objects macdSelAddonObj;
	public MACD_RemoveAddOn_Objects macdRemoveAddOnObj;
	public MACD_ReviewPlaceOrder_Objects macdReviewPlaceOrdObj;
	public MACD_ChangeWirelessPlans_Objects macdChangeWirelessPlansObj;
    public MACD_BlockService_Object macdBlkServiceObj;
	public MACD_ReviewSummaryScreen_Objects macdReviewSumScreenObj;
    public MACD_TelephNumberChange_Objects macdTelephNumChangeObj;
	
	
public Communities_CompleteOrderFlow_Objects comOrderFlowObj;
	public SFDC_AllPageObjects() {

		login = new SFDC_Login_Objects(driver);
		home = new SFDC_Home_Objects(driver);
		opp = new SFDC_Opportunities_Objects(driver);
		acc = new SFDC_Accounts_Objects(driver);
		contacts = new SFDC_Contacts_Objects(driver);
		quotes = new SFDC_Quotes_Objects(driver);
		orders = new SFDC_Orders_Objects(driver);
		mques = new SFDC_ManualQueues_Objects(driver);
		omni = new SFDC_OmniSupervisor_Objects(driver);
		cba = new SFDC_CreateBusinessAccount_Objects(driver);
		cc = new SFDC_CreateContact_Objects(driver);
		cpc = new SFDC_ChangePrimaryContact_Objects(driver);
		csa = new SFDC_CreateServiceAccount_Objects(driver);
		cbia = new SFDC_CreateBillingAccount_Objects(driver);
		ad = new SFDC_AccountDetails_Objects(driver);
		ar = new SFDC_AccountRelated_Objects(driver);
		saccRelated = new SFDC_ServiceAccountRelated_Objects(driver);
		cd = new SFDC_ContactDetails_Objects(driver);
		es = new SFDC_ReviewChangeInEmployeeSize_Objects(driver);
		cln = new SFDC_ChangeLegalName_Objects(driver);
		co = new SFDC_CreateOpportunity_Objects(driver);
		cq = new SFDC_CreateQuote_Objects(driver);
		od = new SFDC_OpportunityDetails_Objects(driver);
		or = new SFDC_OpportunityRelated_Objects(driver);
		orDecompose = new SFDC_OrderDecomposition_Objects(driver);
		ocInfo = new SFDC_OpportunityCloseInfo_Objects(driver);
		cpqHome = new SFDC_CPQHome_Objects(driver);
		iblcCart = new SFDC_HybridCart_IBLC_Objects(driver);
		cpqQuoteRec = new SFDC_CPQ_QuoteRecord_Objects(driver);
		premises = new SFDC_ServiceAccountPremisesDetails_Objects(driver);
		gdPdf = new SFDC_Quote_GenerateDocument_Objects(driver);
		quoteReview = new SFDC_QuoteReview_Objects(driver);
		siteCon = new SFDC_Quote_SelectSiteContact_Objects(driver);
		qr = new SFDC_QuoteRelated_Objects(driver);
		qd = new SFDC_QuoteDetails_Objects(driver);
		orDetails = new SFDC_OrderDetails_Objects(driver);
		orRelated = new SFDC_OrderRelated_Objects(driver);
		orFailure = new SFDC_OrderFailure_Objects(driver);
		orchPlan = new SFDC_OrchestrationPlan_Objects(driver);
		manQue = new SFDC_ManualQueueQueueDetails_Objects(driver);
		crSSOdr = new SFDC_CreateSuperSystemOrder_Objects(driver);
		orchItem = new SFDC_OrchestrationItem_Objects(driver);
		pendingTask = new SFDC_OrderWithPendingTask_Objects(driver);
		compVlctOdr = new SFDC_CompleteVlocityOrder_Objects(driver);
		omniChannel = new SFDC_EmailToCase_OmniChannel_Objects(driver);
		caseDetails = new SFDC_CaseDetails_Objects(driver);
		caseRelated = new SFDC_CaseRelated_Objects(driver);
		caseRelatedDetails = new SFDC_CaseRelatedDetails_Objects(driver);
		cases = new SFDC_Cases_Objects(driver);
		qt = new SFDC_QuickText_Objects(driver);
		liMAcc = new SFDC_Accounts_LinkMasterAccount_Objects(driver);
		accHi = new SFDC_AccountHierarchy_Objects(driver);
		pr = new SFDC_PartyRelationships_Objects(driver);
		gbj = new SFDC_GuidedByJourney_Objects(driver);
		gbjCart = new SFDC_GuidedByJourney_Internet_TV_Objects(driver);
		gbjIBLC = new SFDC_GuidedByJourney_IBLC_Objects(driver);
		gbjProv = new SFDC_GuidedByJourney_IBLC_Provisioning_Details_Objects(driver);
		rODComplete = new SFDC_ReviewOrderDetailsComplete_Objects(driver);
		cableTaskItems = new SFDC_CableOrder_ManualQueueTask_Objects(driver);
		gbjRDI = new SFDC_GuidedByJourney_RDI_Objects(driver);
		ecu = new SFDC_EnableCommunityUser_Objects(driver);
		subs = new SFDC_Subscription_Objects(driver);
		files = new SFDC_Files_Objects(driver);
		customerCase = new SFDC_Customer_Case_Objects(driver);
		setup = new SFDC_Setup_Objects(driver);

		task = new SFDC_TaskCreation_Objects(driver);
		taskDetails = new SFDC_TaskDetails_Objects(driver);
		knowledge = new SFDC_Knowledge_Objects(driver);
		knowledgeDetails = new SFDC_Knowledge_Details_Objects(driver);
		caseKnowledge = new SFDC_CaseKnowledge_Objects(driver);

		mailinator = new Email_Mailinator_Objects(driver);

		comLogin = new Communities_Login_Objects(driver);
		comContactUs = new Communities_ContactUs_Objects(driver);
		comHome = new Communities_Home_Objects(driver);
		comMBC = new Communities_MyBusinessCases_Objects(driver);
		comCaseDetails = new Communities_MyBusinessCaseDetails_Objects(driver);
		comOrderDetails = new Communities_MyBusinessOrdersDetails_Objects(driver);
		comReviewSpecSheet = new Communities_ReviewSpecSheet_Objects(driver);
		comPBF = new Communities_PBF_Objects(driver);
		comPBFShopCart = new Communities_PBF_ShoppingCart_Objects(driver);
		comPBFCablePlan = new Communities_PBF_CablePlanSelection_Objects(driver);
		comPBFOrderReview = new Communities_PBF_OrderReview_Objects(driver);
		comPBFOrderConfirm = new Communities_PBF_OrderConfirmationScreen_Objects(driver);
		comPBFAddSite = new Communities_PBF_AddNewSite_Objects(driver);
		comPBFTVPlan = new SFDC_PBF_TVPlanSelection_Objects(driver);
		siteSelPBF = new SFDC_PBF_SiteSelection_Objects(driver);
		planSelPBF = new SFDC_PBF_CablePlan_Selection_Objects(driver);
		shopCartPBF = new SFDC_PBF_ShoppingCart_Objects(driver);
		orderRevPBF = new SFDC_PBF_OrderReview_Objects(driver);
		orderConfPBF = new SFDC_PBF_OrderConfirmationScreen_Objects(driver);
		siteConPBF = new SFDC_PBF_SiteContact_Objects(driver);
		tvAddOnsPBF = new SFDC_PBF_TVAddOns_Selection_Objects(driver);
		offAddOnsPBF = new SFDC_PBF_Office365AddOns_Selection_Objects(driver);
		comPBFTVAddons = new SFDC_PBF_TV_AddOns_Selection_Objects(driver);
		mulSiteSel = new SFDC_PBF_MultiSiteSelection_Objects(driver);
		mulSiteShopCart = new SFDC_PBF_MultiSite_ShoppingCart_Objects(driver);
		mulSiteOrderReview = new SFDC_PBF_Multisite_OrderReview_Objects(driver);
		
		b2bAccountObj = new SFDC_b2b_Account_Objects(driver);
		b2bContactObj = new SFDC_b2b_Contact_Objects(driver);

		userProf = new SFDC_UserProfile_Objects(driver);
		seleU = new SeleniumUtilities();
		
		taskRelated = new SFDC_TaskRelated_Objects(driver);
		lead = new SFDC_Leads_Objects(driver);

		partnerCommHome = new PartnerCommunities_Home_Objects(driver);
		partnerCommLogin = new PartnerCommunities_Login_Objects(driver);
		partnerCommAccDetails = new PartnerCommunities_AccountDetails_Objects(driver);
		partnerCommOppDetails = new PartnerCommunities_OpportunityDetails_Objects(driver);

		snowCasePage = new SNOW_Case_ValidationPage(driver);

		proSel = new WACC_Product_Selection_Objects(driver);
		addOnSel = new WACC_AddOns_Selection_Objects(driver);
		shopCartObj = new WACC_ShoppingCart_Objects(driver);
		revOrderObj = new WACC_ReviewOrder_Objects(driver);
		r4Bquote = new SFDC_R4B_Quote_Approval_Page_Layout_Objects(driver);
		gSBRingDNA = new SFDC_GuidedSellingByRingDNA_Objects(driver); 
		campaign = new SFDC_Campaigns_Objects(driver); 

		genDocObj = new WACC_GenerateDocument_ESign_Objects(driver);
		
		comOrderFlowObj = new Communities_CompleteOrderFlow_Objects(driver);
		
		shopWADevobj = new WACC_ShopWirelessDevices_Objects(driver);
		bAccessoriesObj= new WACC_BrowseAccessories_Objects(driver);
		accessoryDetailObj=new WACC_AccessoriesDetails_Objects(driver) ;
		accManagementObj = new  MACD_AccountManagement_Objects (driver);
		reviewWALineObj = new MACD_ReviewWirelessLine_Objects(driver);
		macdSelAddonObj = new MACD_SelectAddOn_Objects(driver);
		macdRemoveAddOnObj = new MACD_RemoveAddOn_Objects(driver);
		macdReviewPlaceOrdObj  = new MACD_ReviewPlaceOrder_Objects(driver);
		macdChangeWirelessPlansObj = new MACD_ChangeWirelessPlans_Objects(driver);
		macdBlkServiceObj = new MACD_BlockService_Object(driver);
		macdReviewSumScreenObj = new MACD_ReviewSummaryScreen_Objects(driver);
		macdTelephNumChangeObj = new MACD_TelephNumberChange_Objects(driver);
	}

}
