package vtiger.CreateContactTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.ContactsPage;
import pomClasses.CreateNewContacts;
import pomClasses.CreateNewOrganization;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.OrganizationInformationPage;
import pomClasses.OrganizationPage;

public class CreateContactTest {
 @Test
	public  void createContactTest() throws IOException, InterruptedException
	{
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		int r = jUtil.getRandomNumber();
		WebDriverUtility wUtil = new WebDriverUtility();
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String firstName  = eUtil.readDataFromExcel("contacts", 9, 2);
		String lastName  = eUtil.readDataFromExcel("contacts", 9, 3);
		String salutationType = eUtil.readDataFromExcel("contacts", 9, 4);
		String orgName = eUtil.readDataFromExcel("contacts", 12, 4)+r;
		
		//Step1 Launch browser
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(BROWSER.equalsIgnoreCase("firefox")) 
		{
          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser");
		}
		
		//Step 2 Launch the url.
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3 login with valid credentials
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		
		HomePage hp = new HomePage(driver);
		//Step 4 Click on Create organization link
		hp.clickOnOrganization();
		
		//Step 5 Click on create organization look up image
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrgLookUp();
		
		//Step 6 fill all required fields
		CreateNewOrganization cno = new CreateNewOrganization(driver);
		cno.crateOrganization(orgName);
		
		//Step 7 verify the created organization 
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String createdOrg = oip.getHeader();
		if(createdOrg.contains(orgName))
		{
			System.out.println("script pass");
		}
		
		//Step 8 Click on Create contacts link
		hp.clickOnContact();
		
		//Step 9 Click on Create Contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUp();
		
		//Step  fill all the required Fields
		CreateNewContacts cnc = new CreateNewContacts(driver);
		//cnc.createContact(firstName, lastName, salutationType);
		cnc.createContact(lastName, driver, orgName);
		
		
		//
	}
}
