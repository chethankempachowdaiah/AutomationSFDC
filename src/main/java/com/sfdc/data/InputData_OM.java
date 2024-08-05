package com.sfdc.data;

import java.util.Hashtable;

import com.framework.utilities.AdditionalUtilities;
import com.framework.utilities.DateTimeUtilities;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class InputData_OM extends InputData {

	// Create Account In SS Task For CAN No
	public String canNOInCreateAccount;
	public String enterAppointmentDate; 
	public String enterPostAppointmentDate; 
	public String preSiteInspection_WorkOrderNo;
	public String installation_WorkOrderNo;

	//	public static String contactEmailId = getProperty("contactEmailId");
	public String deliverySpecialistEmail;
	public String deliverySpecialistPhone;
	
	public String AEProfileEmail;

	// Create Cable Order Task
	public String pre_SiteInspection_In_Time;
	public String pre_SiteInspection_Out_Time;
	public String v21BanNumberEnter;

	// Welcome Letter message for cable
	public String welcomeLetter_Message1;
	public String welcomeLetter_Message2;
	public String welcomeLetter_Message3;
	public String welcomeLetter_Message4;
	public String welcomeLetter_Message5;
	public String welcomeLetter_Message6;
	public String welcomeLetter_Message7;
	public String welcomeLetter_Message8;
	public String welcomeLetter_Message9;

	// Closure Letter Message for cable
	public String closureLetter_Message1;
	public String closureLetter_Message2;
	public String closureLetter_Message3;
	public String closureLetter_Message4;
	
	
	//Welcome Letter for RDI
		public String RDIWelcomeLetter_Message1;
		public String RDIWelcomeLetter_Message2;
		public String RDIWelcomeLetter_Message3;
		public String RDIWelcomeLetter_Message4;
		public String RDIWelcomeLetter_Message5;
		public String RDIWelcomeLetter_Message6;
		public String RDIWelcomeLetter_Message7;
		public String RDIWelcomeLetter_Message8;
		public String RDIWelcomeLetter_Message9;
		public String RDIWelcomeLetter_Message10;
		public String RDIWelcomeLetter_Message11;
		public String RDIWelcomeLetter_Message12;
		public String RDIWelcomeLetter_Message13;
		public String RDIWelcomeLetter_Message14;
		public String RDIWelcomeLetter_Message15;
		public String RDIWelcomeLetter_Message16;
		public String RDIWelcomeLetter_Message17;
		public String RDIWelcomeLetter_Message18;
		public String RDIWelcomeLetter_Message19;
		public String RDIWelcomeLetter_Message20;
		public String RDIWelcomeLetter_Message21;
		public String RDIWelcomeLetter_Message22;
		
		//Closure Letter for RDI
				public String RDIClosureLetter_Message1;
				public String RDIClosureLetter_Message2;
				public String RDIClosureLetter_Message3;
				public String RDIClosureLetter_Message4;
				public String RDIClosureLetter_Message5;
				public String RDIClosureLetter_Message6;
				public String RDIClosureLetter_Message7;
				public String RDIClosureLetter_Message8;
				public String RDIClosureLetter_Message9;
				public String RDIClosureLetter_Message10;
				public String RDIClosureLetter_Message11;
				public String RDIClosureLetter_Message12;
				public String RDIClosureLetter_Message13;
				public String RDIClosureLetter_Message14;
				public String RDIClosureLetter_Message15;


	// FOC Letter message for rdi
	public String focLetter_Message1;
	public String focLetter_Message2;
	public String focLetter_Message3;
	public String focLetter_Message4;
	public String focLetter_Message5;
	public String focLetter_Message6;
	public String focLetter_Message7;
	public String focLetter_Message8;
	public String focLetter_Message9;



	// Inservice date for RDI
	public String projectNumber;
	public String pHUBValue;
	public String iNServivceDate;

	// Flag for cable email auto task
	public boolean welcomeEmailTaskStatus_CompletedManually = false;
	public boolean apointmentEmailTaskStatus_CompletedManually = false;
	public boolean closureEmailTaskStatus_CompletedManually = false;

	//
	public String orderCanCelMessageTextInManualTasks;
	//
	public String supersededOrderNumber;
	public String canceledOrderNumber;

	//
	public String openTaskNotification_ReinsateMessage;
	public String openTaskNotification_ReinsateComments;
	public String openTaskNotification_ReinsateHeaderText;

	//
	public String openTaskNotification_CancelMessage;
	public String openTaskNotification_CancelComments;

	//
	public String openTaskNotification_CancellationMessage;
	public String openTaskNotification_CancellationComments;
	public String notificationDueDate;

	// Cancel Message for RDI and Cable Order
	public String taskStatusOrderCancellationMessage;
	public String taskStatusOrderFrozenMessage;
	public String taskStatusOrderCanceledMessage;	
	public String taskStatusOrderCanceledMessage_ForCable;
	public String orderRequestCancellation_Message_Enter;

	//RDI Fields
	public static String dedicatedInternetProduct;
	public static String advancedNetworks;
	public static String contractTerms;
	public static String handOffInterface;
	public static String encapsulationType;
	public static String iPversion;
	public static String ipV4WAnBlockSpecSheet;
	public static String ipV4LAnBlockSpecSheet;
	public static String ipV6WAnBlockSpecSheet;
	public static String ipV6LAnBlockSpecSheet;
	public static String demarcationLOC;
	public static String vLANID;
	public static String dnsRequired;
	public static String accessProvider;
	public static String existingPortableIP;
	public static String accountSource;
	

	public InputData_OM () {
		enterAppointmentDate = DateTimeUtilities.currentSystemDate("dd-MMM-yyyy");
		enterPostAppointmentDate = DateTimeUtilities.currentSystemDatePlus(7, "dd-MMM-yyyy");
		pre_SiteInspection_In_Time = "10:00 am";
		pre_SiteInspection_Out_Time = "11:00 am";

		// work order no
		preSiteInspection_WorkOrderNo = AdditionalUtilities.generateRandomDigits(7);
		installation_WorkOrderNo = AdditionalUtilities.generateRandomDigits(7);
		v21BanNumberEnter = AdditionalUtilities.generateRandomDigits(9);

		welcomeLetter_Message1 = "Here is a breakdown of your installation";
		welcomeLetter_Message2 = "Step 1: Installation Appointment";
		welcomeLetter_Message3 = "We will be scheduling 2 appointments for your installation:";
		welcomeLetter_Message4 = "The first appointment is for the site inspection to ensure the site is ready for installation.";
		welcomeLetter_Message5 = "On the second appointment the technician will complete the installation of the services.";
		welcomeLetter_Message6 = "If the technician is able to install the services during the first appointment they will. A confirmation will be sent once both appointments have been scheduled.";

		closureLetter_Message1 = "I am pleased to confirm your successful activation!";
		closureLetter_Message2 = "If you have any questions or concerns going forward please feel free to reach out to a Rogers Business Specialist at";
		closureLetter_Message3 = "877-274-3375";
		closureLetter_Message4 = "Thank you,";
		
		//RDI Welcome Letter Message
		RDIWelcomeLetter_Message1 = " and I am a Service Delivery Specialist from the Service Delivery Team. I'd like to welcome you to Rogers and say thank you for choosing us. I will be working closely with you to coordinate the implementation activities for your upcoming services.  ";
		RDIWelcomeLetter_Message2 = " Here is a breakdown of your installation. ";
		RDIWelcomeLetter_Message3 = " Getting Started ";
		RDIWelcomeLetter_Message4 = " Following is an overview of what your can expect.";
		RDIWelcomeLetter_Message5 = " Step 1: Confirm Billing Information";
		RDIWelcomeLetter_Message6 = " To ensure we are communicating with the appropriate party, please confirm name, email address and number of your billing contact by replying to this email.";
		RDIWelcomeLetter_Message7 = " Step 2: Site Survey";
		RDIWelcomeLetter_Message8 = " Within the next 5 business days, you will be contacted to schedule a site visit.";
		RDIWelcomeLetter_Message9 = " On the day of the site survey visit:";
		RDIWelcomeLetter_Message10 = " A site contact is required to be available and aware of the demarcation point (where the equipment is to be installed).";
		RDIWelcomeLetter_Message11 = " Access to the main building telephone/electrical rooms, building risers, cross connect terminals and phone closets.";
		RDIWelcomeLetter_Message12 = " Step 3: Installation Details ";
		RDIWelcomeLetter_Message13 = " After the site survey is complete, within 5 business days, I will send you an email to confirm the estimated installation date and requirements for installation.";
		RDIWelcomeLetter_Message14 = " Please review the confirmed installation date and the site readiness requirements.";
		RDIWelcomeLetter_Message15 = " Step 4: Activation";
		RDIWelcomeLetter_Message16 = " On the day of activation:";
		RDIWelcomeLetter_Message17 = " A technical contact is required to be available on-site to assist";
		RDIWelcomeLetter_Message18 = " Step 5: Billing and Support";
		RDIWelcomeLetter_Message19 = " Within 1 business day of successful service activation, you will receive an email and your billing will begin.";
		RDIWelcomeLetter_Message20 = " Once you receive this email: ";
		RDIWelcomeLetter_Message21 = " Please retain the letter for reference as it will include important details as well as contact information for support. ";
		RDIWelcomeLetter_Message22 = " © 2022 Rogers Communications";
		
		//RDI Closure Letter Message
				RDIClosureLetter_Message1 = " Your activation has been successfully confirmed. ";
				RDIClosureLetter_Message2 = " Thank You ";
				RDIClosureLetter_Message3 = " Enterprise Web Portal Instructional Embedded Video Here ";
				RDIClosureLetter_Message4 = "  As a valued customer,\r\n"
						+ "our dedicated support teams are here for you:";
				RDIClosureLetter_Message5 = " 1-800-496-4401";
				RDIClosureLetter_Message6 = " Technical Support";
				RDIClosureLetter_Message7 = " Business Inquiries";
				RDIClosureLetter_Message8 = " 8:00AM to 7:00PM EST";
				RDIClosureLetter_Message9 = " Please ensure you have your Service ID when calling in for billing or technical support. This can be found above as well as on your invoice.";
				RDIClosureLetter_Message10 = "  You can also get 24/7 support through our";
				RDIClosureLetter_Message11 = " View Invoices & Payment History";
				RDIClosureLetter_Message12 = "  https://eportal.rogers.com ";
				RDIClosureLetter_Message13 = " I personally want to thank you for choosing Rogers";
				RDIClosureLetter_Message14 = " After Hours: Please leave a message and we will return your call the next business day.";
				RDIClosureLetter_Message15 = "self-serve option";

		// RDI FOC Letter Message
		focLetter_Message1 = " Great news! The site-survey requirements have been gathered and the details of your upcoming installation and activation have been confirmed.";
		focLetter_Message2 = " Here is confirmation of your installation and activation. ";
		focLetter_Message3 = " Please ensure the following is completed before installation: ";
		focLetter_Message4 = " Space is available within the rack or a 24\"x 24\" area on the wall";
		focLetter_Message5 = " Access to the main building telephone/electrical rooms, building";
		//	focLetter_Message5 = "risers, cross connect terminals and phone closets.";
		focLetter_Message6 = " A surge-protected power source (with 4-115V power outlets) is";
		focLetter_Message7 = " Once your services are installed, we will complete the activation and ensure everything is working properly. Your billing will start as of this date. ";
		focLetter_Message8 = " If you have any questions you can reach me at ";
		focLetter_Message9 = " © 2022 Rogers Communications Inc.";

		// Create Account In SS Task Failure
		canNOInCreateAccount = AdditionalUtilities.generateRandomDigits(10) + AdditionalUtilities.generateRandomDigits(2);

		// order cancel message
		orderCanCelMessageTextInManualTasks = "Order is cancelled. Please close this task by clicking on complete. For more details please click on the Order number.";

		// Reinstate Task Notification Message To Delivery Specialist	
		openTaskNotification_ReinsateMessage = "is being reinstated to an In Progress State";	
		openTaskNotification_ReinsateComments = "is being reinstated to an In Progress state. Please ensure all the associated tasks are completed in time.";
		openTaskNotification_ReinsateHeaderText = "is being reinstated to an In Progress State";

		// Order request cancellation message text enter
		orderRequestCancellation_Message_Enter = "Testing for freezing";

		// Due date in the notification should be 2 plus current days
		notificationDueDate = DateTimeUtilities.currentSystemDatePlus(2, "dd/MM/yyyy");

		// Cancel Task Notification Message To Delivery Specialist	
		openTaskNotification_CancelMessage = "is cancelled";
		openTaskNotification_CancelComments = "is cancelled";

		// Cancel Task Notification Message To AE
		openTaskNotification_CancellationMessage = "Notification for Cancellation";
		openTaskNotification_CancellationComments = "is notifying you for cancellation of Order";

		// Verify task status order cancellation message
		taskStatusOrderCancellationMessage = "Order cancellation is in progress. Please click on complete and close the task. Please click on the Order number for more details";
		taskStatusOrderFrozenMessage = "Order is Frozen. Please do not complete the task yet. Click on Cancel and close the task. Please click on the Order number for more details";
		taskStatusOrderCanceledMessage = "Order is now cancelled. Click on Cancel and close the task. Please click on the Order number for more details ";		
		taskStatusOrderCanceledMessage_ForCable = "Order is cancelled. Please close this task by clicking on complete. For more details please click on the Order number";

	}
	// Function to set Datatable data to variables for Digital Scrum Regression
	// Suite
	public static void setDataForCreateCase_OM(Hashtable<String, String> dataTable) {
		try {
			
			dedicatedInternetProduct = readDataTableValues(dataTable, "Dedicated_Internet_Product");
			advancedNetworks = readDataTableValues(dataTable, "Advanced_Networks");
			contractTerms = readDataTableValues(dataTable, "Contract_Term");
			handOffInterface = readDataTableValues(dataTable, "handOffInterface");
			encapsulationType = readDataTableValues(dataTable, "encapsulationType");
			demarcationLOC = readDataTableValues(dataTable, "demarcationLOC");
			accessProvider = readDataTableValues(dataTable, "accessProvider");
			iPversion = readDataTableValues(dataTable, "iPversion");
			ipV4WAnBlockSpecSheet = readDataTableValues(dataTable, "ipV4WAnBlockSpecSheet");
			ipV4LAnBlockSpecSheet = readDataTableValues(dataTable, "ipV4LAnBlockSpecSheet");
			ipV6WAnBlockSpecSheet = readDataTableValues(dataTable, "ipV6WAnBlockSpecSheet");
			ipV6LAnBlockSpecSheet = readDataTableValues(dataTable, "ipV6LAnBlockSpecSheet");
			vLANID = readDataTableValues(dataTable, "vLanID");
			dnsRequired = readDataTableValues(dataTable, "dnsRequired");
			accessProvider = readDataTableValues(dataTable, "accessProvider");
			existingPortableIP = readDataTableValues(dataTable, "existingPortableIP");
			accountSource = "iSeries";

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public static String readDataTableValues(Hashtable<String, String> dataTable, String key) {
		String value =""; 
		try {
			if(dataTable.get(key)!=null)
				value = dataTable.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return value;
	}

}

