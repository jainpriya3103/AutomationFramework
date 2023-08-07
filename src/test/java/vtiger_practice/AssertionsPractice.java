/*
 * @author priya
 */

package vtiger_practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsPractice {
	
	@Test
	public void test()
	{
		System.out.println("step11S");
		System.out.println("step2");
		System.out.println("step3");
		System.out.println("step4");
		Assert.assertTrue(false);
		System.out.println("step5");
		System.out.println("step6");
		Assert.assertEquals(false, true);
		System.out.println("step7");
		System.out.println("step8");
		
		
	}
	
	@Test
	public void test1()
	{
		SoftAssert sa = new SoftAssert();
		System.out.println("step1");
		System.out.println("step2");
		System.out.println("step3");
		System.out.println("step4");
		sa.assertTrue(false);
		System.out.println("step5");
		System.out.println("step6");
		sa.assertEquals(false, true);
		System.out.println("step7");
		System.out.println("step8");
		sa.assertAll();
		
		
	}
	
	

}
