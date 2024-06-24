package api.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row; 
	public XSSFCell cell;
	public FileInputStream input;
	
	

	public int countRow() throws IOException {
		File file =new File(".//UsersData.xlsx");
		input=new FileInputStream(file);
		workbook=new XSSFWorkbook(input);
		sheet=workbook.getSheetAt(0);
		row=sheet.getRow(0);
		cell=sheet.getRow(0).getCell(0);
		
		int RowCount=sheet.getLastRowNum();
		
		workbook.close();
		input.close();
		
		return RowCount;
	}
	
	public int countCell() throws Throwable {
		File file =new File(".//UsersData.xlsx");
		input=new FileInputStream(file);
		workbook=new XSSFWorkbook(input);
		sheet=workbook.getSheetAt(0);
		row=sheet.getRow(0);
		
		int cellCount=row.getLastCellNum();
		
		workbook.close();
		input.close();
		
		return cellCount;
	}
	
	public String readData(int rownum,int cellnum) throws Throwable {
		File file =new File(".//UsersData.xlsx");
		input=new FileInputStream(file);
		workbook=new XSSFWorkbook(input);
		sheet=workbook.getSheetAt(0);
		
		row=sheet.getRow(rownum);
		cell=sheet.getRow(rownum).getCell(cellnum);
		
		DataFormatter format=new DataFormatter();
		String data = null;
		try {
			data=format.formatCellValue(cell);
			
		} catch (Exception e) {
			data=" ";
		}

		
		return data;
		
		
		
		
	}
	
	

}
