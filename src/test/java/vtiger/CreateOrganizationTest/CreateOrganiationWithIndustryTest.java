package vtiger.CreateOrganizationTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.CreateNewOrganization;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.OrganizationInformationPage;
import pomClasses.OrganizationPage;

@Listeners(genericUtilities.ListenerImplementation.class)
public class CreateOrganiationWithIndustryTest extends BaseClass {
@Test(groups="SmokeSuite")
	public void createOrganiationWithIndustryTest() throws IOException, InterruptedException {
		
		//JavaUtility jUtil = new JavaUtility();
		//int r = jUtil.getRandomNumber();
		//ExcelFileUtility eUtil = new ExcelFileUtility();
		
		String orgName = eUtil.readDataFromExcel("organization", 5, 2) +jUtil.getRandomNumber();
		String industryType = eUtil.readDataFromExcel("organization", 5, 3);
		//WebDriver driver = null;
	
		// Step 3: Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganization();
		System.out.println("link clicked");
		Reporter.log("Click on Organizations link",true);// if we pass true it will print in report as well as in console
		
		
		// Step 4: Click on Create Org Look Up Image
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgLookUp();
		Reporter.log("Click on Create Org Look Up Image",true);
		
		//Assert.fail();
		// Step 5: Create Organization with mandatory fields
		CreateNewOrganization co = new CreateNewOrganization(driver);
		co.crateOrganization(orgName, industryType);
		Reporter.log("organization created");
		
		// Step 8: Validate for Organization
		OrganizationInformationPage cip = new OrganizationInformationPage(driver);
		String header = cip.getHeader();
		Assert.assertTrue(header.contains(orgName));
		
	}

@Test
public void demo()
{
	System.out.println("demo for mvn cmd");
	}

}
