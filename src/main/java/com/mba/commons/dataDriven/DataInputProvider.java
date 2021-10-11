package com.mba.commons.dataDriven;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider {
	
	private static DataInputProvider obj;

	private DataInputProvider() {
	}

	public static DataInputProvider getInstance() {
		if (obj == null)
			obj = new DataInputProvider();
		return obj;
	}

	@SuppressWarnings("deprecation")
	public String[][] getSheet(String name, String excelPath) {
		XSSFRow row;
		XSSFCell cell;
		XSSFWorkbook workbook;
		String[][] value = null;
		double[][] nums = null;
		try {
			System.out.println(System.getProperty("user.dir")+excelPath);
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+excelPath);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(name);
			int rowCount = sheet.getLastRowNum();
			rowCount = sheet.getLastRowNum()+1;
			int columnCount = sheet.getRow(0).getLastCellNum();
			value = new String[rowCount][columnCount];
			int rows = sheet.getPhysicalNumberOfRows();
			for (int r = 0; r < rowCount; r++) {
				//if(r!=0) {
				row = sheet.getRow(r); 
				if (row != null) {
					for (int c = 0; c < columnCount; c++) {
						cell = row.getCell(c);
						nums = new double[rows][columnCount];
						if (cell != null) {
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								value[r][c] = cell.getCellFormula();
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								value[r][c] = "" + cell.getNumericCellValue();
								break;
							case XSSFCell.CELL_TYPE_STRING:
								value[r][c] = "" + cell.getStringCellValue();
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value[r][c] = "[BLANK]";
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								value[r][c] = "" + cell.getErrorCellValue();
								break;
							default:
							}				
						} else {
						}
					}
				}
			}
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}