package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This utility is created to read data from excel
 * @author Priya Jain
 *
 */
public class ExcelFileUtility {
	/**
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */

	public String readDataFromExcel(String sheetName,int rowNum,int cellNum) throws InterruptedException, IOException
	{
		FileInputStream fie = new FileInputStream(IConstantUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fie);
		Row rw1 = wb.getSheet(sheetName).getRow(rowNum);
		String value = rw1.getCell(cellNum).getStringCellValue();
		return value;
	}
	
	/**
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public void writeDataIntoExcel(String sheetName,int rowNum,int cellNum,String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(IConstantUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet(sheetName).createRow(rowNum).createCell(cellNum).setCellValue(value);
		
		FileOutputStream fio = new FileOutputStream(IConstantUtility.excelFilePath);
		wb.write(fio);
		wb.close();
	}
	
	public Object[][] readMultipleData(String SheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fie = new FileInputStream(IConstantUtility.excelFilePath);
		Workbook wb = WorkbookFactory.create(fie);
		Sheet sh = wb.getSheet(SheetName);
		int lastRow = sh.getLastRowNum();
		int lastCel = sh.getRow(0).getLastCellNum();
		Object[][] data = new Object[lastRow][lastCel];
		for(int i = 1; i<lastRow; i++)
		{
			for(int j =0; j<lastCel ;j++)
			{
				data[i][j]= sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return data;
	}
}
