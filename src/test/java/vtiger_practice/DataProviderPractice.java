package vtiger_practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	@Test(dataProvider= "getData" )
	public void sampleDataTest(String name, String model ,int qty)
	{
		System.out.println(name + "<-->" + model + "<-->" + qty );
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[4][3];
		data[0][0] = "Samsung";
		data[0][1] = "A80";
		data[0][2] = 10;
		
		data[1][0] = "Iphone";
		data[1][1] = "14";
		data[1][2] = 15;
		
		data[2][0] = "Nokia";
		data[2][1] = "1100";
		data[2][2] = 20;
		
		data[3][0] = "Vivo";
		data[3][1] = "V20";
		data[3][2] = 30;
		
		return data;
		
	}

}
