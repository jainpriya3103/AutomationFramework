package vtiger_practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNgPriority {
	//by default the priority will be 0 and lowest priority will execute first
	//when ever we want to run a script multiple times we use invocation count
	@Test(invocationCount=3,priority = 2)
	public void create()
	{
		System.out.println("created");
	}
	
	//if u dont want to run a script
	@Test(enabled = false)
	public void modify()
	{
		System.out.println("modified");
	}
	@Test
	public void delete()
	{
		System.out.println("deleted");
	}
	
	@Test
	public void create1()
	{
		Assert.fail();
		System.out.println("created");
	}
	
	@Test(dependsOnMethods="create1")
	//modify1 depends on create1 if create1 got fail modify1 & delete 1 both will skip
	public void modify1( )
	{
		Assert.fail();
		System.out.println("modified");
	}
	
	@Test(dependsOnMethods= {"create1","modify1"})
	//here delete1 depends on two methods create1 & modify1 if any one method will get fail delete will be skip
	public void delete1()
	{
		System.out.println("deleted");
	}
	
	@Test(invocationCount = 0)
	public void demo()
	{
		System.out.println("demo");
	}
	
	

}
