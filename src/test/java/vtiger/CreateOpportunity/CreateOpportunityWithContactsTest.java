package vtiger.CreateOpportunity;

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
import pomClasses.ContactInformationPage;
import pomClasses.ContactsPage;
import pomClasses.CreateNewContacts;
import pomClasses.CreateNewOpportunity;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.OpportunityPage;
import pomClasses.OrganizationInformationPage;

public class CreateOpportunityWithContactsTest {
@Test
	public  void createOpportunityWithContactsTest() throws IOException, InterruptedException {
		//Create objects for utilities
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		int r = jUtil.getRandomNumber();
		String BROWSER = pUtil.readDataFromPropertyFile("browser"); 
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String opportunityName = eUtil.readDataFromExcel("opportunity", 1, 2);
		String relatedToValue = eUtil.readDataFromExcel("opportunity", 1, 3);
		String contactName = eUtil.readDataFromExcel("contacts", 1, 3);
		String firstName = "priya" + r;
		
		//Step 1 Launch the browser
		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) 
		{
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
		
		//Step 2 launch the url
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3 Login to app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4 click on create contact from home page
		HomePage hp = new HomePage(driver);
		hp.clickOnContact();
		
		//Step 5 click on create contact look up img
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUp();
		
		//Step 6 Fill the mandatory Fields
		CreateNewContacts cnc = new CreateNewContacts(driver);
		cnc.createContact(firstName, contactName);
		
		//Step 7 verify the contact
		ContactInformationPage cip = new ContactInformationPage(driver);
		String text = cip.getHeader();
		if (text.contains(contactName)) {
			System.out.println("Script is pass");
		}
		
		//Step 8 Click on opportunity link from home page
		hp.clickOnOppotunity();
		
		//Step 9 Click on create opportunity look up image
		OpportunityPage op = new OpportunityPage(driver);
		op.clickOnOpportunityLookUp();
		
		//Step 10 Fill all mandatory fields
		String searchContact = firstName +" "+ contactName;
		CreateNewOpportunity cno = new CreateNewOpportunity(driver);
		cno.createOpportunity(driver, opportunityName, relatedToValue, searchContact, null);
		
		//Step 11 Verify created opportunity
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String verifyText = oip.getHeader();
		if (verifyText.contains(opportunityName))
		{
			System.out.println("script is pass opportunity created");
		}
		
		//Step 12 logout 
		hp.clickonSignOut(driver);

	}

}
