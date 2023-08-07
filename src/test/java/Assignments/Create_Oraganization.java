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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Create_Oraganization {

	public static void main(String[] args) throws InterruptedException, IOException {
		Random r = new Random();
		int randomNumber = r.nextInt(1000);
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		// Read data from excel
		FileInputStream fie = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fie);
		Row row1 = wb.getSheet("organization").getRow(1);
		String orgName = row1.getCell(2).getStringCellValue()+randomNumber;
		System.out.println("orgName " +orgName);
		Row row2 = wb.getSheet("organization").getRow(5);
		String industryType = row2.getCell(3).getStringCellValue();
		System.out.println("industryType " +industryType);
		Row row3 = wb.getSheet("organization").getRow(9);
		String ratingValue = row3.getCell(4).getStringCellValue();
		System.out.println("rating " +ratingValue);
		Row row4 = wb.getSheet("organization").getRow(13);
		String accountTypeValue = row4.getCell(5).getStringCellValue();
		System.out.println("accountType " +accountTypeValue);

		Row r5 = wb.getSheet("organization").getRow(17);
		String description = r5.getCell(6).getStringCellValue();
		System.out.println("description " +description);
		
		//Step1 Launch Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		driver.get(URL);
		
		//Step2 Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME,Keys.TAB,PASSWORD,Keys.ENTER);
		
		//Step3 Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step4 Click on Create Organization look Up Image 
		driver.findElement(By.xpath("//img[contains(@alt,'Create Organization')]")).click();
		
		//Step5 Create Organization with Mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(orgName,Keys.TAB,"",Keys.TAB,"www.exioms.com");
        WebElement industriesList = driver.findElement(By.name("industry"));	
        Select selIndustry = new Select(industriesList);
        selIndustry.selectByValue(industryType);
        WebElement ratingList = driver.findElement(By.name("rating"));
        Select selRating = new Select(ratingList);
        selRating.selectByValue(ratingValue);
        WebElement accountTypeList = driver.findElement(By.name("accounttype"));
        Select selAccountType = new Select(accountTypeList);
        selAccountType.selectByValue(accountTypeValue);
        driver.findElement(By.name("emailoptout")).click();
        driver.findElement(By.xpath("//input[@value='U']")).click();
        driver.findElement(By.name("description")).sendKeys(description);
        
        //Step6 Save
        driver.findElement(By.xpath("(//input[contains(@value,'Save')])[1]")).click();
        Thread.sleep(3000);
        
        //Step7 Verify
        String verificationData = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
        if (verificationData.contains(orgName)) {
			System.out.println("script is pass");
		}
       
        //Step8 Logout
        WebElement useImge = driver.findElement(By.xpath("//span[.=' Administrator']/ancestor::tr[2]/descendant::img[3]"));
        Actions act = new Actions(driver);
        act.moveToElement(useImge).perform();
        driver.findElement(By.linkText("Sign Out")).click();
        
	}

}
