package com.sfdc.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

import com.framework.base.Constants;
import com.framework.utilities.XlsAction;

public class InputData_QM extends InputData {

	// QM
	public static String cableONNetResidential;
	public static String runMode;

	public InputData_QM () {
		cableONNetResidential = "Residential";
	}

	// Function to extract data to variables for QM Scrum Regression for ON-NET, ABA-ON, ABA-Promo for RDI products
	public static void setDataFor_QM_RDI(String sheetName, int rowNum, XlsAction reader) {
		try {
			//	XlsAction reader = new XlsAction(excelWorkBook);  	
			InputData_Communities.commPBFTcName = reader.getCellData(sheetName, "SrNo.", rowNum);	
			if(InputData_Communities.commPBFTcName!=null)
				com.framework.base.MyListener.reportStatusPass("Row No is: "+ InputData_Communities.commPBFTcName, true, true);
			
			String testcase = reader.getCellData(sheetName, "TCName", rowNum);
			System.out.println(testcase);
			runMode = reader.getCellData(sheetName, "RunMode", rowNum);
			InputData_Communities.commPBFTypeOfAddress = reader.getCellData(sheetName, "Type of Address", rowNum);
			InputData_Communities.commPBFProductTerm = reader.getCellData(sheetName, "Product Term", rowNum);
			InputData_Communities.commPBFBusinessProducts = new ArrayList<>(
					Arrays.asList(reader.getCellData(sheetName, "Business Products", rowNum).split(","))) ;
			InputData_Communities.commPBFAddProductName = reader.getCellData(sheetName, "Add Product Name", rowNum);
			InputData_Communities.commPBFProductSpeeds = new ArrayList<>(
					Arrays.asList(reader.getCellData(sheetName, "Speed Of Products", rowNum).split(","))) ;
			InputData_Communities.commPBFEditEthernetPlan = reader.getCellData(sheetName, "Edit Ethernet Plan", rowNum);
			InputData_Communities.commPBFCurrentEthernetPlan = reader.getCellData(sheetName, "Current Ethernet Plan", rowNum);

			// All Prices
			InputData_Communities.commPBFProductPrice = reader.getCellData(sheetName, "Product Price", rowNum);
			InputData_Communities.discount = reader.getCellData(sheetName, "Discount", rowNum);
			InputData_Communities.commPBFOneTimeFees = reader.getCellData(sheetName, "One Time Fees", rowNum);
			InputData_Communities.commPBFReccuringCost = reader.getCellData(sheetName, "Cost To Use", rowNum);
			InputData_Communities.commPBFEthernetPlanE100Cost = reader.getCellData(sheetName, "E100 Access Cost", rowNum);
			InputData_Communities.commPBFEthernetPlanE1000Cost = reader.getCellData(sheetName, "E1000 Access Cost", rowNum);
			InputData_Communities.commPBFInstallationFees = reader.getCellData(sheetName, "Installation Fees", rowNum);

			// validate the ethernet plan for the cost
			if (InputData_Communities.commPBFCurrentEthernetPlan.equalsIgnoreCase("E100"))
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE100Cost;
			else
				InputData_Communities.commPBFCurrentEthernetPlanCost = InputData_Communities.commPBFEthernetPlanE1000Cost;

		}  catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// Function to extract data to variables for QM Scrum Regression for Cable products like Business Internet, TV and office 365
	public static void setDataFor_QM_Internet_Offic365_TV(String sheetName, int rowNum, XlsAction reader) {
		try {
			//	XlsAction reader = new XlsAction(excelWorkBook);  	
			
			InputData_Communities.commPBFTcName = reader.getCellData(sheetName, "SrNo.", rowNum);
			
			if(InputData_Communities.commPBFTcName!=null)
				com.framework.base.MyListener.reportStatusPass("Sr No is: "+ InputData_Communities.commPBFTcName, true, true);
			
			runMode = reader.getCellData(sheetName, "RunMode", rowNum);
			InputData_Communities.commPBFProductSpeedType = reader.getCellData(sheetName, "Product Speed Type", rowNum);
			InputData_Communities.commPBFAddProductName = reader.getCellData(sheetName, "Add Product Name", rowNum);
			InputData_Communities.commPBFIncludedFeatures = reader.getCellData(sheetName, "Included Features", rowNum);
			InputData_Communities.commPBFProductPrice = reader.getCellData(sheetName, "Product Price", rowNum);
			InputData_Communities.commPBFProductTerm = reader.getCellData(sheetName, "Product Term", rowNum);
			InputData_Communities.commPBFPromo  = reader.getCellData(sheetName, "Promo", rowNum);
			InputData_Communities.discountProduct  = reader.getCellData(sheetName, "Discount Product", rowNum);
			InputData_Communities.discountPrice  = reader.getCellData(sheetName, "DiscountPrice", rowNum);
			InputData_Communities.commPBFOffAddOnName = reader.getCellData(sheetName, "Office365 AddOn", rowNum);
			InputData_Communities.commPBFOffAddOnPrice = reader.getCellData(sheetName, "Office365 AddOn Price", rowNum);
			String qunatity =  reader.getCellData(sheetName, "Office365 AddOn Qty", rowNum);
			InputData_Communities.commPBFOffAddOnQty = Integer.parseInt(qunatity);
			InputData_Communities.commPBFOneTimeFees = reader.getCellData(sheetName, "One Time Fees", rowNum);
			InputData_Communities.commPBFCostToUseOfficeAddOn = reader.getCellData(sheetName, "Cost To Use Office AddOn", rowNum);
			InputData_Communities.commPBFReccuringCost = reader.getCellData(sheetName, "Cost To Use", rowNum);
			InputData_Communities.commPBFCostToUseTV = reader.getCellData(sheetName, "Cost To Use TV", rowNum);
			InputData_Communities.commPBFCostToUseTVAddOn = reader.getCellData(sheetName, "Cost to Use TV AddOn", rowNum);
			InputData_Communities.commPBFInstallationFees = reader.getCellData(sheetName, "Installation Fees", rowNum);
			InputData_Communities.commPBFBundledFirstProd = reader.getCellData(sheetName, "First Bundled Product", rowNum);
			InputData_Communities.commPBFAddTVProductName = reader.getCellData(sheetName, "Add TV Product", rowNum);
			InputData_Communities.commPBFTVAddOnName = reader.getCellData(sheetName, "TV Addon Add", rowNum);
			InputData_Communities.commPBFTVProductPrice = reader.getCellData(sheetName, "TV Product Price", rowNum);
			InputData_Communities.commPBFTVAddOnPrice = reader.getCellData(sheetName, "TV AddOn Price", rowNum);
			InputData_Communities.commPBFTVAddOnQty = Integer.parseInt(reader.getCellData(sheetName, "TV AddOn Qty", rowNum));
		}  catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// Function to extract data to variables for QM Scrum Regression for Cable products like Business Internet, TV and office 365
	public static void setDataFor_QM_Internet_Standalone(String sheetName, int rowNum, XlsAction reader) {
		try {
			//	XlsAction reader = new XlsAction(excelWorkBook);  			
			InputData_Communities.commPBFTcName = reader.getCellData(sheetName, "SrNo.", rowNum);	
			if(InputData_Communities.commPBFTcName!=null)
				com.framework.base.MyListener.reportStatusPass("Row No is: "+ InputData_Communities.commPBFTcName, true, true);
		
			runMode = reader.getCellData(sheetName, "RunMode", rowNum);
			InputData_Communities.commPBFProductSpeedType = reader.getCellData(sheetName, "Product Speed Type", rowNum);
			InputData_Communities.commPBFAddProductName = reader.getCellData(sheetName, "Add Product Name", rowNum);
			InputData_Communities.commPBFProductPrice = reader.getCellData(sheetName, "Product Price", rowNum);
			InputData_Communities.commPBFProductTerm = reader.getCellData(sheetName, "Product Term", rowNum);
			InputData_Communities.commPBFPromo  = reader.getCellData(sheetName, "Promo", rowNum);
			InputData_Communities.discountProduct  = reader.getCellData(sheetName, "Discount Product", rowNum);
			InputData_Communities.discountPrice  = reader.getCellData(sheetName, "DiscountPrice", rowNum);
			InputData_Communities.commPBFOneTimeFees = reader.getCellData(sheetName, "One Time Fees", rowNum);
			InputData_Communities.commPBFReccuringCost = reader.getCellData(sheetName, "Cost To Use", rowNum);
			InputData_Communities.commPBFInstallationFees = reader.getCellData(sheetName, "Installation Fees", rowNum);
			InputData_Communities.commPBFIncludedFeatures = reader.getCellData(sheetName, "Included Features", rowNum);
			InputData_Communities.commPBFProductSpeeds = new ArrayList<>(
					Arrays.asList(reader.getCellData(sheetName, "Speed Of Products", rowNum).split(","))) ;
		}  catch (Throwable e) {
			e.printStackTrace();
		}
	}


}
