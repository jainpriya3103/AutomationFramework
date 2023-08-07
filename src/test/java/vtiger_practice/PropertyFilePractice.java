package vtiger_practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilePractice {
public static void main (String[] args) throws IOException
{
	//Step 1 open the file in java readable format
	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
	
	//Step 2 Create object of peroperties class from java.util
	Properties pobj = new Properties();
	
	//Step 3 load the file into properties
	pobj.load(fis);
	
	//step 4 give the key and read the value
	String browserName = pobj.getProperty("browser");
	System.out.println(browserName);
	}
}
