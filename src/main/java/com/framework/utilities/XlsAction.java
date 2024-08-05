package com.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsAction {

	private static String excelFilePath;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	private static XSSFSheet sheet;
	private static XSSFWorkbook workBook;
	private static FormulaEvaluator evaluator;
	private static DataFormatter formatter;
	private static XSSFCell cell;
	private static XSSFRow row;

	public XlsAction(String excelFilePath, String sheetName) {
		XlsAction.excelFilePath = excelFilePath;
		File excelFile = new File(excelFilePath);
		if (!excelFile.exists()) {
			try {
				excelFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			fis = new FileInputStream(excelFile);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			workBook = new XSSFWorkbook(fis);
			evaluator = workBook.getCreationHelper().createFormulaEvaluator();
			formatter = new DataFormatter();
		} catch (IOException e) {

			e.printStackTrace();
		}
		sheet = workBook.getSheet(sheetName);
		if (sheet == null) {
			sheet = workBook.createSheet(sheetName);
		}
	}
	
	public XlsAction(String excelFilePath) 
	{
		this.excelFilePath = excelFilePath;
		try
		{
			fis = new FileInputStream(excelFilePath);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheetAt(0);
			fis.close();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workBook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workBook.getSheetAt(index);
			return sheet.getLastRowNum() + 1;
		}

	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		int index = workBook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(0);
		if (row == null)
			return 0;
		return row.getLastCellNum();
	}

	// returns data present in a perticular cell
	public String getCellData(String sheetName, int rowNum, String columnName) throws IOException {
		if (rowNum <= 0)
			return "";
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(0);
		int col_Num = -1;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName))
				col_Num = i;
		}
		if (col_Num == -1)
			return "";
		if (row == null)
			return "";
		row = sheet.getRow(rowNum - 1);
		if (row == null)
			return "";
		cell = row.getCell(col_Num);
		if (cell == null)
			return "";
		// String cellData = cell.getStringCellValue();
		String cellData = formatter.formatCellValue(cell, evaluator);
		fis.close();
		return cellData;
	}

	// returns data present in a perticular cell
	public String getCellData(String sheetName, int rowNum, int columnNum) throws IOException {
		if (rowNum <= 0)
			return "";
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(0);
		if (columnNum == -1)
			return "";
		if (row == null)
			return "";
		row = sheet.getRow(rowNum - 1);
		if (row == null)
			return "";
		cell = row.getCell(columnNum);
		if (cell == null)
			return "";
		// String cellData = cell.getStringCellValue();
		String cellData = formatter.formatCellValue(cell, evaluator);
		fis.close();
		return cellData;
	}
	
	public String getCellData(String sheetName, String columnName, int rowNum)
	{
		try {
			if (rowNum <= 0)		
				return "";
			
			int index = workBook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)		
				return "";
			
			sheet = workBook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(columnName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workBook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			
			String cellData = cell.getStringCellValue();
			return cellData;

			//System.out.println(cell.getCellType().name());
			//
//			if (cell.getCellType().name().equals("STRING"))
//				return cell.getStringCellValue();
//			String cellData = cell.getStringCellValue();
			

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + columnName + " does not exist in xls";
		}
	}
	
	// Write data in the respective cell
	public void setCellData(String data, String sheetName, int rowNum, String columnName) throws IOException {
		sheet = workBook.getSheet(sheetName);
		row = sheet.getRow(0);

		int col_Num = -1;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName)) {
				col_Num = i;
				break;
			}

		}
		row = sheet.getRow(rowNum - 1);
		if (row == null)
			row = sheet.createRow(rowNum - 1);
		cell = row.getCell(col_Num);
		if (cell == null)
			cell = row.createCell(col_Num);
		cell.setCellValue(data);
		fos = new FileOutputStream(excelFilePath);
		workBook.write(fos);
		fos.flush();
		fos.close();
	}

}
