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

public class Create_Org_With_Diff_UserType {

	public static void main(String[] args) throws InterruptedException, IOException {
Random r = new Random();
int randomNumber = r.nextInt(1000);
FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
Properties pobj = new Properties();
pobj.load(fip);
String BROWSER = pobj.getProperty("browser");
String URL = pobj.getProperty("url");
String USERNAME = pobj.getProperty("username");
String PASSWORD = pobj.getProperty("password");
//Read excel data
FileInputStream fie = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
Workbook wb = WorkbookFactory.create(fie);
Row rw1 = wb.getSheet("organization").getRow(17);
String orgName = rw1.getCell(2).getStringCellValue() + randomNumber;
Row rw2 = wb.getSheet("organization").getRow(17);
String industryType = rw2.getCell(3).getStringCellValue();
Row rw3 = wb.getSheet("organization").getRow(17);
String accountType = rw3.getCell(5).getStringCellValue();
String website = "www."+ orgName + ".com";

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
driver.findElement(By.name("accountname")).sendKeys(orgName,Keys.TAB,"",Keys.TAB,website);
     
//Step6 Select "Energy" in the industry drop down
WebElement industriesList = driver.findElement(By.name("industry"));	
Select selIndustry = new Select(industriesList);
selIndustry.selectByValue(industryType);

//Step7 Select "Customer" in the Type Drop down 
WebElement accountTypeList = driver.findElement(By.name("accounttype"));
Select selAccountType = new Select(accountTypeList);
selAccountType.selectByValue(accountType);
        
//Step8 Save
driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

//Step9 Verify
String verifyOrg = driver.findElement(By.cssSelector(".dvHeaderText")).getText();
if (verifyOrg.contains(orgName)) {
	System.out.println("verifyOrg");
}
//Step10 Logout
WebElement useImge = driver.findElement(By.xpath("//span[.=' Administrator']/ancestor::tr[2]/descendant::img[3]"));
Actions act = new Actions(driver);
act.moveToElement(useImge).perform();
driver.findElement(By.linkText("Sign Out")).click();

	}

}
