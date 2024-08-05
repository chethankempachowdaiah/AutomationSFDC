//package scrum.qm.test;
package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.framework.base.MyListener;
import com.sfdc.data.InputData_Communities;
import com.sfdc.data.InputData_WA;
import com.sfdc.data.InputData_WA.AgreementOrderDetails;
import com.sfdc.data.InputData_WA.AgreementOrderSummaryTableColumnDetails;
import com.sfdc.data.InputData_WA.AgreementOrderTermAndConditionAndURL;
import com.sfdc.data.InputData_WA.AgreementTotalForAllLinesTableColumnDetails;
import com.sfdc.data.InputData_WA.RecurringFeesTableColumnPDFDocs;

/**
 * @author Chethan.Kempachowdaiah, Date 03/May/2021
 * 
 *         PDF Helper class using pdfbox to read the PDF data and verify the
 *         content as required by the test case
 */

public class PDFHelper extends MyListener {

	/***
	 * @param pdfLocation
	 * @throws IOException
	 * 
	 *                     Gets the PDF location
	 * 
	 *                     Extracts the text from pdf and returns it to the calling
	 *                     function
	 */
	public static String ExtractTextFromPDF(String pdfLocation) throws IOException {
		File file = null;
		FileInputStream fis = null;
		PDDocument pdfDocument = null;
		PDFTextStripper pdfTextStripper;
		String pdfText = null;
		try {
			file = new File(pdfLocation);
			fis = new FileInputStream(file);
			// Loads file into PDFDocment
			pdfDocument = PDDocument.load(fis);
			pdfTextStripper = new PDFTextStripper();
			// Set pages to read entire PDF
			pdfTextStripper.setStartPage(1); // comment this line if you want to read the entire document
			//pdfTextStripper.setEndPage(pdfDocument.getPages().getCount());
			pdfTextStripper.setEndPage(2);
			pdfText = pdfTextStripper.getText(pdfDocument);
		} catch (Throwable e) {
			reportStatusFail(" Error in reading PDF", e);
			e.printStackTrace();
		} finally {
			// Close the pdf, file and also delete the pdf from the downloads folder
			pdfDocument.close();
			fis.close();
			// file.delete();
		}
		return pdfText;
	}

	/*** Pankaj Agarwal 26 Feb 2022
	 * @param productName, pdfText
	 * 
	 *                     Gets the prices, Qty for the input product from PDF text
	 * 
	 */
	public static String[] GetPricesForProductFromPDF(String productName, String pdfText) {
		String extractedText;
		String[] lines;
		String[] prices = null;
		int lineSplitIndex = 2;

		if(productName.contains("Ignite Internet with")) {
			String internetProductName[] = productName.split(" ");
			String internetProduct = internetProductName[0] + " " +internetProductName[1] + " " +internetProductName[2] + " "+
					internetProductName[3];
			System.out.println(internetProduct);
			System.out.println(internetProductName[4] + internetProductName[5]);
			if (pdfText.toUpperCase().contains(internetProduct.toUpperCase()) && 
					pdfText.toUpperCase().contains(internetProductName[4] + " " +internetProductName[5].toUpperCase())){

				productName = internetProduct;
				lineSplitIndex = 3;
			}	
		}

		if(productName.contains("Business Internet with LTE Backup")) {
			String internetProductName[] = productName.split(" ");
			String internetProduct = internetProductName[0] + " " +internetProductName[1] + " " +internetProductName[2];
			System.out.println(internetProduct);
			System.out.println(internetProductName[3] + internetProductName[4]);
			String internetProductRemaining = internetProductName[3] + " " +internetProductName[4]
					+ " "+ internetProductName[5];
			if (pdfText.toUpperCase().contains(internetProduct.toUpperCase()) && 
					pdfText.toUpperCase().contains(internetProductRemaining.toUpperCase())){

				productName = internetProduct;
				lineSplitIndex = 3;
			}	
		}

		// As the bigger product Names appear in two different lines of PDF so splitting it 
		if(productName.contains("Advantage WiFi with LTE backup")) {
			String internetProductName[] = productName.split(" ");
			String internetProduct = internetProductName[0] + " " +internetProductName[1] + " " +internetProductName[2];
			if (pdfText.toUpperCase().contains(internetProduct.toUpperCase()) && 
					pdfText.toUpperCase().contains(internetProductName[4].toUpperCase())){

				productName = internetProduct;
				lineSplitIndex = 3;
			}		
		}
		//	only for office 365 product validate first 3 word 
		if(InputData_Communities.commPBFOffAddOnName.equals(productName)) {

			String offcProd[] = InputData_Communities.commPBFOffAddOnName.split(" ");
			String microsoft365Business = offcProd[0] + " " +offcProd[1] + " " +offcProd[2];
			if (pdfText.toUpperCase().contains(offcProd[3].toUpperCase())){
				productName = microsoft365Business;
			}			
		}

		if(productName.equals("Internet Installation") || productName.equals(InputData_Communities.commPBFTVAddOnName) ||
				productName.equals("Installation")) {
			lineSplitIndex = 0;
		}	

		if(!InputData_Communities.commPBFBusinessSubProducts.get(0).equals("NA")) {
			for(int i = 0;i < InputData_Communities.commPBFBusinessSubProducts.size(); i++) {
				if(productName.equals(InputData_Communities.commPBFBusinessSubProducts.get(i))) {
					lineSplitIndex = 0;
				}
			}
		}

		System.out.println("lineSplitIndex is " + lineSplitIndex );

		if (pdfText.toUpperCase().contains(productName.toUpperCase())) {
			System.out.println(pdfText);
			// Split the string to get the products prices and quantity
			extractedText = pdfText.toUpperCase().substring(pdfText.toUpperCase().indexOf(productName.toUpperCase()));
			// split based on line
			lines = extractedText.split("\n");
			System.out.println(lines[0]);
			System.out.println(lines[0]);
			System.out.println(lines[0]);
			System.out.println(lines[0]);
			// extract the exact line for the product price
			prices = lines[lineSplitIndex].split(" ");
			System.out.println(prices[0]);
		}
		return prices;
	}

	/***
	 * @param productName, quantity, actual price, final price
	 * 
	 *                     Calculates the prices for the input product as per the
	 *                     quantity and compares it with the expected value
	 */
	public static void ValidatePricesOnQuantity(String productName, String Qty, String actualPrice, String finalPrice)
			throws IOException, InterruptedException {
		double CalculatedValue = Integer.valueOf(Qty) * Double.valueOf(actualPrice);
		String expectedValue = String.format("%.2f", CalculatedValue);
		// Check if final price from the pdf and actual price are same
		if (finalPrice.equals(expectedValue)) {
			reportStatusPass(" Prices are mtched for the " + productName + " in PDF as " + finalPrice, true,
					true);
		} else {
			reportStatusFail("Actual Value in PDF for " + productName + "for" + Qty +" is " + finalPrice + " And Expected One is "
					+ expectedValue, true);
		}
	}

	/***
	 * @param pdfText, text
	 * 
	 *                    Verifies if the text is present in the PDF
	 */
	public static void IsTextPresentInPDF(String pdfText, String text) throws IOException, InterruptedException {
		if (pdfText.contains(text)) {
			reportStatusPass(" Validated text: " + text + " in PDF ", true, true);
		} else {
			reportStatusFail(text + " not present in PDF ", true);
		}
	}
	/**
	 * Method developed on @Date: 28.02.2022 by @author Jigyasa Dwivedi
	 * 
	 * Method to delete existing file on given location
	 * @throws Exception 
	 * 
	 */
	public static void deletefile_IfExist(String pdfLocation) throws Exception {
		// TODO Auto-generated method stub
		File file = null;
		try {
			file = new File(pdfLocation);
			file.delete();			
		} catch (Throwable e) {
			reportStatusFail(" Error in delete file",true);
			//e.printStackTrace();
		}
	}

	/**
	 * @author Satish.Doraiswamy
	 *Mar. 3, 2022
	 * @throws IOException 
	 *
	 */

	public  static String readPDFFileLineByLine(String filePath) throws IOException {
		String finalValue=null;
		PDDocument document = null; 
		try{
			document = PDDocument.load(new File(filePath));
			document.getClass();
			if( !document.isEncrypted() ){
				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition( true );
				PDFTextStripper Tstripper = new PDFTextStripper();
				finalValue = Tstripper.getText(document);
				reportStatusPass(" Readed PDF Document Data is  " + finalValue, true,true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			// Close the pdf connection
			document.close();
			// file.delete();
		}
		return finalValue;
	}
	
	/**
	 * @author Satish.Doraiswamy
	 * @throws InterruptedException 
	 * @throws IOException 
	 * Validating Prepared For Data from PDF docs
	 */
	public static void verifyPreparedForData(String documentData,String value) throws IOException, InterruptedException {
		sf.seleU.hardwait(2000);
	String tableColumnValue=null;
		String[] lineValues = documentData.split("\\r?\\n");
		   for(int i=0;i< lineValues.length ;i++  ) {
			   if(lineValues[i].equalsIgnoreCase(value)) {
				   tableColumnValue=lineValues[i+1]; 
					   if(tableColumnValue.contains(InputData_WA.account_Business_R4B)) {
						   reportStatusPass(" Prepared For  Value is  " + tableColumnValue, true,true);
					   }else {
						   reportStatusFail("Prepared For Value is failed and actual value is" + tableColumnValue, true);
					   }
				   break;
			   }
		   }
	}
	
	/**
	 * @author Satish.Doraiswamy
	 * @throws InterruptedException 
	 * @throws IOException 
	 *  Validating Attention Data from PDF docs
	 */
	public static void verifyAttentionData(String documentData,String value) throws IOException, InterruptedException {
	sf.seleU.hardwait(2000);	
	String tableColumnValue=null;
		String[] lineValues = documentData.split("\\r?\\n");
		   for(int i=0;i< lineValues.length ;i++  ) {
			   if(lineValues[i].equalsIgnoreCase(value)) {
				   tableColumnValue=lineValues[i+1]; 
					   if(tableColumnValue.contains(InputData_WA.contact_Business_R4B)) {
						   reportStatusPass(" Prepared For  Value is  " + tableColumnValue, true,true);
					   }else {
						   reportStatusFail("Prepared For Value is failed and actual value is" + tableColumnValue, true);
					   }
				   break;
			   }
		   }
	}

	/**
	 * @author Satish.Doraiswamy
	 * @throws InterruptedException 
	 * @throws IOException 
	 * Validating Order Summary Table Column Data from PDF docs
	 */
	public static void verifyAgreementOrderSummaryTableColumnData(String documentData,String value) throws IOException, InterruptedException {
		sf.seleU.hardwait(2000);
		String tableColumnValue=null;
		String[] lineValues = documentData.split("\\r?\\n");
		   for(int i=0;i< lineValues.length ;i++  ) {
			   if(lineValues[i].equalsIgnoreCase(value)) {
				   tableColumnValue=lineValues[i+1]; 
				   for(AgreementOrderSummaryTableColumnDetails agreementOrderSummaryTableColumnDetailsVal: AgreementOrderSummaryTableColumnDetails.values()) {
					   if(tableColumnValue.contains(agreementOrderSummaryTableColumnDetailsVal.getAgreementOrderSummaryTableColumnDetails())) {
						   reportStatusPass(" Recurring Fees Table Column Name is " + tableColumnValue, true,true);
						   break;
					   }else {
						   reportStatusFail("Actual  Recurring Fees Table Column Name is " + tableColumnValue +  " And Expected One is "
									+ agreementOrderSummaryTableColumnDetailsVal.getAgreementOrderSummaryTableColumnDetails(), true);
					   }
				   }
				   break;
			   }
		   }
		   for(AgreementOrderSummaryTableColumnDetails agreementOrderSummaryTableColumnDetailsVal: AgreementOrderSummaryTableColumnDetails.values()) {
			   if(documentData.contains(agreementOrderSummaryTableColumnDetailsVal.getAgreementOrderSummaryTableColumnDetails())) {
				   reportStatusPass(" Recurring Fees Table Column Name is " + agreementOrderSummaryTableColumnDetailsVal.getAgreementOrderSummaryTableColumnDetails(), true,true);
			   }else {
				   reportStatusFail("Actual  Recurring Fees Table Column Name is " + tableColumnValue +  " And Expected One is "
							+ agreementOrderSummaryTableColumnDetailsVal.getAgreementOrderSummaryTableColumnDetails(), true);
			   }
		   }
	}
	
	/**
	 * @author Satish.Doraiswamy
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 * Validating Total For All Line Table Column Data from PDF docs
	 */
	public static void verifyAgreementTotalForAllLineTableColumnData(String documentData,String value) throws IOException, InterruptedException {
	String tableColumnValue=null;
		String[] lineValues = documentData.split("\\r?\\n");
		   for(int i=0;i< lineValues.length ;i++  ) {
			   if(lineValues[i].equalsIgnoreCase(value)) {
				   tableColumnValue=lineValues[i+1]; 
				   for(AgreementTotalForAllLinesTableColumnDetails agreementTotalForAllLinesTableColumnDetails: AgreementTotalForAllLinesTableColumnDetails.values()) {
					   if(tableColumnValue.contains(agreementTotalForAllLinesTableColumnDetails.getAgreementTotalForAllLinesTableColumnDetails())) {
						   reportStatusPass(" Recurring Fees Table Column Name is " + tableColumnValue, true,true);
						   break;
					   }else {
						   reportStatusFail("Actual  Recurring Fees Table Column Name is " + tableColumnValue +  " And Expected One is "
									+ agreementTotalForAllLinesTableColumnDetails.getAgreementTotalForAllLinesTableColumnDetails(), true);
					   }
				   }
				   break;
			   }
		   }
		   for(AgreementTotalForAllLinesTableColumnDetails agreementTotalForAllLinesTableColumnDetails: AgreementTotalForAllLinesTableColumnDetails.values()) {
			   if(documentData.contains(agreementTotalForAllLinesTableColumnDetails.getAgreementTotalForAllLinesTableColumnDetails())) {
				   reportStatusPass(" Recurring Fees Table Column Name is " + agreementTotalForAllLinesTableColumnDetails.getAgreementTotalForAllLinesTableColumnDetails(), true,true);
			   }else {
				   reportStatusFail("Actual  Recurring Fees Table Column Name is " + documentData +  " And Expected One is "
							+ agreementTotalForAllLinesTableColumnDetails.getAgreementTotalForAllLinesTableColumnDetails(), true);
			   }
		   }
	}
	
	/**
	 * @author Satish.Doraiswamy
	 * @throws InterruptedException 
	 * @throws IOException 
	 *  Validating Terms and Condition Text and URL
	 */
	public static void verifyTermsAndConditionURLAndText(String documentData) throws IOException, InterruptedException {
		sf.seleU.hardwait(2000);
		 for(AgreementOrderTermAndConditionAndURL agreementOrderTermAndConditionAndURL: AgreementOrderTermAndConditionAndURL.values()) {
			   if(documentData.contains(agreementOrderTermAndConditionAndURL.getAgreementOrderTermAndConditionAndURL())) {
				   reportStatusPass(" Recurring Fees Table Column Name is " + agreementOrderTermAndConditionAndURL.getAgreementOrderTermAndConditionAndURL(), true,true);
			   }else {
				   reportStatusFail("Actual  Recurring Fees Table Column Name is " + documentData +  " And Expected One is "
							+ agreementOrderTermAndConditionAndURL.getAgreementOrderTermAndConditionAndURL(), true);
			   }
		   }
	}

}