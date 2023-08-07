package vtiger_practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTestCaseRunTime {
	
	@Test 
	public void method1()
	{
		Assert.fail();
	}
    
	@Test
	public void method2()
	{
		Assert.fail();
	}
	
	@Test
	public void method3()
	{
		Assert.assertEquals(false, false);
	}
}
