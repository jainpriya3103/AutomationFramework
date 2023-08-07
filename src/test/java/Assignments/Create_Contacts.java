package Assignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Create_Contacts {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		JavaUtility jUtil = new JavaUtility();
		int r = jUtil.getRandomNumber();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		WebDriverUtility wdUtil = new WebDriverUtility();
		wdUtil.waitForPageLoad(driver);
		PropertyFileUtility pUtil  = new PropertyFileUtility();
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD	 = pUtil.readDataFromPropertyFile("password");
		ExcelFileUtility eUtil = new ExcelFileUtility();
		//Read data from excel
		String contactName = eUtil.readDataFromExcel("contacts", 5, 2)+r;
		String leadSource = eUtil.readDataFromExcel("contacts", 5, 4);
		
		//Step 1 Launch Browser
		driver.get(URL);
		
		
		//Step 2 Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//Step 3 Navigate to Contacts link
	    WebElement contactLink = driver.findElement(By.linkText("Contacts"));
	    contactLink.click();
	   
	    //Step 4 Click on Create contact look Up Image
	    driver.findElement(By.xpath("//img[contains(@alt,'Create Contact')]")).click();
	    
	    //Step 5 Create Contact with Mandatory fields
	    WebElement salutationDropdown = driver.findElement(By.name("salutationtype"));
	    wdUtil.HandleDropDown(salutationDropdown, "Mrs.");
	   
	    driver.findElement(By.name("firstname")).sendKeys(contactName,Keys.TAB,"",Keys.TAB,"Tondon");
	    WebElement leadSourceDropDown = driver.findElement(By.name("leadsource"));
	    wdUtil.HandleDropDown(leadSourceDropDown,leadSource);
	    driver.findElement(By.cssSelector("#jscal_trigger_birthday")).click();
	    driver.findElement(By.xpath("//td[.='May, 2023']/ancestor::div[@class='calendar']/descendant::td[.='6']")).click();
	    driver.findElement(By.xpath("//input[@value='T']")).click();
        WebElement assignedToDropDown = driver.findElement(By.name("assigned_group_id"));
        wdUtil.HandleDropDown("Support Group", assignedToDropDown);
        
        
        driver.findElement(By.name("notify_owner")).click();
        driver.findElement(By.name("mailingstreet")).sendKeys("Welcome to Vtiger application");
        
        //Step 6 Save and Verify
        driver.findElement(By.xpath("(//input[contains(@value,'Save')])[2]")).click();
        // if I use contactLink.click() here I will get Stale element reference exception the stale element reference 
        //error is a WebDriver error that occurs because the referenced web element is no longer attached to the DOM.
        //driver.findElement(By.linkText("Contacts")).click();
        String contactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if (contactHeader.contains(contactName)) {
			System.out.println("script is pass");
		}
        
        //Step 7 Logout
        Thread.sleep(2000);
        WebElement adminOptions = driver.findElement(By.xpath("//span[@class='userName']/ancestor::td[@class='small']/descendant::td[2]"));
        wdUtil.MouseHoverAction(driver, adminOptions);
        driver.findElement(By.xpath("//a[.='Sign Out']")).click();
        System.out.println("logout successful");
        
        Thread.sleep(2000);
        driver.close();
      

	}

}
