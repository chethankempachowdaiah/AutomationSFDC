package com.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.framework.base.Constants;

public class GetExcelData {

	/**
	 * @param flowName
	 * @return
	 * 
	 *         Load Data File from the repository
	 * 
	 *         Load workbook
	 * 
	 *         Load sheet
	 */
	
	public HashMap getCompleteFlowData(String testdatasheet, String flowName) {
		String cellDataString = "";
		int rowNo = 1;
		HashMap<String, String> hmTestData = new HashMap<String, String>();
		try {
			InputStream dataFile = new FileInputStream(testdatasheet);
			XSSFWorkbook wb = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = wb.getSheetAt(0);
			for (int row = 0; row <= sheet.getLastRowNum(); row++) {
				if (sheet.getRow(row).getCell(0).toString().equals(flowName)) {
					rowNo = row;
					break;
				}
			}
			for (int col = 0; col < sheet.getRow(rowNo).getLastCellNum(); col++) {
				hmTestData.put(sheet.getRow(0).getCell(col).toString(), sheet.getRow(rowNo).getCell(col).toString());
			}
			dataFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return hmTestData;
	}
	/**
	 * @param rowNo
	 * @param colNo
	 * @return
	 * 
	 *         Load Data File from the repository
	 * 
	 *         Load workbook
	 * 
	 *         Load sheet
	 */
	public String getCellDataString(String testdatasheet, int rowNo, int colNo) {
		String cellDataString = "";
		try {

			InputStream dataFile = new FileInputStream(testdatasheet);
			XSSFWorkbook wb = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = wb.getSheetAt(0);

			cellDataString = sheet.getRow(rowNo).getCell(colNo).getStringCellValue();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellDataString;
	}
	
	public static ArrayList<Object[]> getDataFromGuidedCaseExcel() throws IOException
	{	
		ArrayList<Object[]> myData = new ArrayList<Object[]>();		
		XlsAction reader = new XlsAction(Constants.GUIDEDCASE_TESTDATA_FILE);
	    
		try
		{				
		for (int rowNum = 2; rowNum<= reader.getRowCount("GuidedCaseTestData"); rowNum++)
		{			
			String ProductFamily = reader.getCellData("GuidedCaseTestData", "ProductFamily", rowNum);
			String Category = reader.getCellData("GuidedCaseTestData", "Category", rowNum);
			String SubCategory = reader.getCellData("GuidedCaseTestData", "SubCategory", rowNum);
			
			Object ob[] = {ProductFamily, Category, SubCategory};
			myData.add(ob);		
		}
	    }		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return myData;
	}
	
	public static ArrayList<Object[]> getDataFromETMCaseExcel() throws IOException
	{	
		ArrayList<Object[]> myData = new ArrayList<Object[]>();		
		XlsAction reader = new XlsAction(Constants.GUIDEDCASE_TESTDATA_FILE);	    
		try
		{				
		for (int rowNum = 2; rowNum<= reader.getRowCount("ETMCaseDataAccountIssuesEmail")-1; rowNum++)
		{			
			String ETML1 = reader.getCellData("ETMCaseDataAccountIssuesEmail", "ETM L1", rowNum);
			String ETML2 = reader.getCellData("ETMCaseDataAccountIssuesEmail", "ETM L2", rowNum);
			String ETML3 = reader.getCellData("ETMCaseDataAccountIssuesEmail", "ETM L3", rowNum);
			String ETML4 = reader.getCellData("ETMCaseDataAccountIssuesEmail", "ETM L4", rowNum);
			String communicationMethod = reader.getCellData("ETMCaseDataAccountIssuesEmail", "Contact's preferred communication method", rowNum);			
			Object ob[] = {ETML1, ETML2, ETML3, ETML4, communicationMethod};
			myData.add(ob);		
		}
	    }		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return myData;
	}
	
	public HashMap getCompleteFlowData(String testdatasheet,String sheetName, String flowName) {
		String cellDataString = "";
		int rowNo = 1;
		HashMap<String, String> hmTestData = new HashMap<String, String>();
		try {
			InputStream dataFile = new FileInputStream(testdatasheet);
			XSSFWorkbook wb = new XSSFWorkbook(dataFile);
			XSSFSheet sheet = wb.getSheet(sheetName);
			for (int row = 0; row <= sheet.getLastRowNum(); row++) {
				if (sheet.getRow(row).getCell(0).toString().equals(flowName)) {
					rowNo = row;
					break;
				}
			}
			for (int col = 0; col < sheet.getRow(rowNo).getLastCellNum(); col++) {
				hmTestData.put(sheet.getRow(0).getCell(col).toString(), sheet.getRow(rowNo).getCell(col).toString());
			}
			dataFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return hmTestData;
	}
}


