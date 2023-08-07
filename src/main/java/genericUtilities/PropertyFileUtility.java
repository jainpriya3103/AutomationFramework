package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consists of generic methods related to property file
 * @author priya jain
 * @throws IOException 
 */
public class PropertyFileUtility {
/**
 * This method will read data from property file and return value to caller
 * @param key
 * @return
 * @throws IOException
 */
	
public String readDataFromPropertyFile(String key) throws IOException
{
	FileInputStream fip = new FileInputStream(IConstantUtility.propertyFilePath);
	Properties pobj = new Properties();
	pobj.load(fip);
	String value = pobj.getProperty(key);
	return value;
	}
	

}
