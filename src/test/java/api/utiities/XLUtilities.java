package api.utiities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtilities {
	
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	
	public XLUtilities(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		int rowcount= sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	

	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		 row= sheet.getRow(rownum);
		 int cellcount= row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName, int rownum, int column) throws IOException
	{
		
		fi= new FileInputStream(path);
		workbook= new XSSFWorkbook(fi);
		sheet= workbook.getSheet(sheetName);
		 row= sheet.getRow(rownum);
		 cell=row.getCell(column);
		 
		 DataFormatter formator= new DataFormatter();
		 String data;
		 
		 try
		 {
			 data= formator.formatCellValue(cell);
		 }
		 catch(Exception e)
		 {
			 data="";
		 }
			workbook.close();
			fi.close();
			return data;
	}
	
	
	public void setCellData(String sheetName, int rownum, int column, String data) throws IOException
	{
		File xlfile= new File(path);
		if(!xlfile.exists())
		{
			workbook= new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
		}
		
		//finish later
	}
}
