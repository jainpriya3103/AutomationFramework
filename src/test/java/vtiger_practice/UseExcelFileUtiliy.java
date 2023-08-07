package vtiger_practice;

import java.io.IOException;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;

public class UseExcelFileUtiliy {

	public static void main(String[] args) throws InterruptedException, IOException {
		ExcelFileUtility excelUtility = new ExcelFileUtility();
		String valueFromCalledMethod = excelUtility.readDataFromExcel("organization",1, 2);
        System.out.println("value --" + valueFromCalledMethod);
       // excelUtility.writeDataIntoExcel("Test", 3, 5, "Priya");
        //System.out.println("data added");
        JavaUtility javaUtil = new JavaUtility();
       System.out.println(javaUtil.getRandomNumber());
       System.out.println(javaUtil.getSystemDate());
       System.out.println(javaUtil.getFormattedDate());
	}

}
