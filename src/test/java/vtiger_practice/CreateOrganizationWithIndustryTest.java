package vtiger_practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

public class CreateOrganizationWithIndustryTest {
	ExcelFileUtility eUtil = new ExcelFileUtility();
	@Test(dataProvider = "getData")
	public void createOrganizationWithIndustryTest(String ORG, String INDUSTRY) throws IOException
	{
		PropertyFileUtility pUtil = new PropertyFileUtility();
	//	ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverUtility wdUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		int r = jUtil.getRandomNumber();
		String orgName = ORG +r;
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		WebDriver driver = null;
		// Step 1: launch the browser - Run Time Polymorphism
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		LoginPage lp = new LoginPage(driver);
		wdUtil.waitForPageLoad(driver);
		driver.get(URL);
		// Step 2: Login to Application
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("login successful");
		
		// Step 3: Click on Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganization();
		System.out.println("link clicked");
		
		// Step 4: Click on Create Org Look Up Image
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgLookUp();
		
		// Step 5: Create Organization with mandatory fields
		CreateNewOrganization co = new CreateNewOrganization(driver);
		co.crateOrganization(orgName, INDUSTRY);
		
		// Step 8: Validate for Organization
		OrganizationInformationPage cip = new OrganizationInformationPage(driver);
		String header = cip.getHeader();
		if (header.contains(orgName)) {
			System.out.println("script is pass");
		}
	
		else
		{
			System.out.println("script is fail");
		}
		
		//Step 9: Logout of App
		hp.clickonSignOut(driver);
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		return eUtil.readMultipleData("DataProviderOrg");
	}
}
