package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
		
	}
	
	public String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
		data = formatter.formatCellValue(cell); //Returns the formatted value of cell as a string
		}
		catch(Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;		
	}
	
	public void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		File xlFile = new File(path);
		if(!xlFile.exists()) // If file not exists create new file
		{    
		workbook = new XSSFWorkbook();
		fo = new FileOutputStream(path);
		workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName) ==-1) // If workbook not exists create new workbook
		{
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
		}
		
		if(sheet.getRow(rownum)==null) // If row not exists create new row
		{
			sheet.createRow(rownum);
			row = sheet.getRow(rownum);
		}
		row = sheet.getRow(rownum);
		cell = row.createCell(cellnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillColour(String sheetName, int rownum ,int cellnum, String cellColour) throws Throwable
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		style = workbook.createCellStyle();
		IndexedColors color;
		try {
        color = IndexedColors.valueOf(cellColour.toUpperCase());
		} catch( Exception e) {
			throw new Exception("Invalid color: "+ cellColour);
		}
		style.setFillBackgroundColor(color.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
}
