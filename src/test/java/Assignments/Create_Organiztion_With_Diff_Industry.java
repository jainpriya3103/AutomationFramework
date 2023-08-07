package Assignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Create_Organiztion_With_Diff_Industry {

	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pobj = new Properties();
		pobj.load(fip);
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//Random class
		Random r = new Random();
		int randomNumber = r.nextInt(1000);
		
		//Read data from excel file
		FileInputStream fie = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fie);
		Row rw1 = wb.getSheet("organization").getRow(1);
		String orgName = rw1.getCell(2).getStringCellValue()+randomNumber;
		//Get industry type 
		Row rw2 = wb.getSheet("organization").getRow(5);
		String industryType = rw2.getCell(3).getStringCellValue();
		//Get website address
		String websiteAddress = "www." + orgName +".com" ;
		System.out.println(websiteAddress);

		WebDriver driver = null;	
        //Step1 Launch Browser
	    if (BROWSER.equalsIgnoreCase("chrome")) {
	    	WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
			System.out.println(BROWSER + "---launched");
		}
	    else if(BROWSER.equalsIgnoreCase("firefox"))
		{
	    	WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
			System.out.println(BROWSER + "---launched");
		}
	    else 
	    {
	    	System.out.println("Invalid browser");
	    }
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		driver.get(URL);
		
		//Step2 Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//Step3 Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step4 Click on Create Organization look Up Image
		driver.findElement(By.xpath("//img[contains(@alt,'Create Organization')]")).click();
		
		//Step5 create Organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(orgName,Keys.TAB,"",Keys.TAB,websiteAddress);
        
	    //Step6 Select "Chemicals" in the industry drop down
		WebElement industriesList = driver.findElement(By.name("industry"));	
        Select selIndustry = new Select(industriesList);
        selIndustry.selectByValue(industryType);
        
        //Step7 Save
        driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
        
        //Step8 Verify
        String verifyOrg = driver.findElement(By.cssSelector(".dvHeaderText")).getText();
        if (verifyOrg.contains(orgName)) {
			System.out.println("Script is pass");
		}
        
        //Step9 Logout
        WebElement useImge = driver.findElement(By.xpath("//span[.=' Administrator']/ancestor::tr[2]/descendant::img[3]"));
        Actions act = new Actions(driver);
        act.moveToElement(useImge).perform();
        driver.findElement(By.linkText("Sign Out")).click();
	}

}
