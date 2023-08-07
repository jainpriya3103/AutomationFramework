package genericUtilities;

import java.util.Date;
import java.util.Random;

/**
 * This class contains all java related utilities
 * @author priya
 *
 */
public class JavaUtility {
	
	public int getRandomNumber()
	{
		Random r = new Random();
		int num = r.nextInt(1000);
		return num;
		
	}
	
	public String getSystemDate()
	{
		Date dt = new Date();
		return dt.toString();
	}
	
	public String getFormattedDate()
	{
		Date d = new Date();
		String dateArr[] = d.toString().split(" ");
		String date = dateArr[2];
		String month = dateArr[1];
		String year = dateArr[5];
		String time = dateArr[3].replace(":", "-");
		return date + " " + month +" " + year + " " + time;
		
	}
	

}
