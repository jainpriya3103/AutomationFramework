package vtiger_practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPracticeTest {
	@Test(retryAnalyzer = genericUtilities.RetryAnalyserImplementation.class)
	
	public void method1()
	{
	Assert.fail();
	System.out.println("pass");
	}
	
	@Test
	public void method2()
	{
		Assert.fail();
	}
	
	

}
