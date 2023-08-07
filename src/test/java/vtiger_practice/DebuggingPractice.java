package vtiger_practice;

import org.testng.annotations.Test;

public class DebuggingPractice {

	@Test
	public void debug()
	{
		System.out.println("Step 1");
		System.out.println("Step 2");
		int c = divide(5,0);
		System.out.println(c);
		 
		
	}
	
	public int divide(int a, int b)
	{
		return a/b;
	}
}
