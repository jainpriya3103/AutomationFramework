package vtiger.CreateContactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

	@Test(groups="RegressionSuite")
	public void createContactWithOrganizationTest() throws IOException, InterruptedException {
		//Create the objects for all utilities
		JavaUtility jUtil = new JavaUtility();
		int r = jUtil.getRandomNumber();
		WebDriverUtility wdUtil = new WebDriverUtility();
		PropertyFileUtility pUtil  = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		wdUtil.waitForPageLoad(driver);
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD	 = pUtil.readDataFromPropertyFile("password");
		String contactName = eUtil.readDataFromExcel("contacts", 12, 2)+r;
		String lastName = eUtil.readDataFromExcel("contacts", 12, 3);
		String orgName = eUtil.readDataFromExcel("contacts", 12, 4)+r;
		//Step 1 Launch Browser
				driver.get(URL);
		//Step 2 Login to application with valid credentials
				driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
				System.out.println("Login successful");
		//Step 3: Click on Organizations link		
				driver.findElement(By.linkText("Organizations")).click();
		//Step 4: Click on Organization look up image
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		//Step 5: create organization with mandatory fields
				driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		//Step 6: click on save button
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//Step 7: Validate the organization
				String createdOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (createdOrgName.contains(orgName)) {
					System.out.println("Organization created");
				}
				else
				{
					System.out.println("Organization not created");
				}
	  //Step 8: Click on Contacts link
				driver.findElement(By.linkText("Contacts")).click();
	  //Step 9: Click on Create Contact look up image
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	 //Step 10: Fill all the mandatory fields
				driver.findElement(By.name("firstname")).sendKeys(contactName);
			    driver.findElement(By.name("lastname")).sendKeys(lastName);
	 //Step 11: Click on Organization name look up image
			    driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@alt='Select']")).click();
     //Step 12: Switch to child window
			    wdUtil.switchToWindow(driver, "Accounts");
	 //Step 13: Search for an organization
			    driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgName,Keys.ENTER);
	 //Step 14: Select the Organization from the list
			    driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
	 // Step 15: switch the control back to main window
				wdUtil.switchToWindow(driver, "Contacts");
	// Step 16: Save the contact - No such Element Exception
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	// Step 17: Validate for Organization
				String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (ContactHeader.contains(lastName)) {
					System.out.println(ContactHeader);
					System.out.println("PASS");
				} else {
					System.out.println("FAIL");
				}
				
	//Step 18: Logout of App
				WebElement adImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wdUtil.MouseHoverAction(driver, adImg);
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("Logout is scuccessful");
			    
	}

}
