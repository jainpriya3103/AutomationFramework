package Assignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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

public class Create_Contact_With_Organization {

	public static void main(String[] args) throws InterruptedException, IOException {
        FileInputStream fip = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
        Properties pobj = new Properties ();
        pobj.load(fip);
        String URL = pobj.getProperty("url");
        String PASSWORD = pobj.getProperty("password");
        String USERNAME = pobj.getProperty("username");
        
        FileInputStream fie = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
        Workbook wb = WorkbookFactory.create(fie);
        Row rw1 = wb.getSheet("contacts").getRow(1);
        String firstName = rw1.getCell(2).getStringCellValue();
        Row rw2 = wb.getSheet("contacts").getRow(9);
        String lastName = rw2.getCell(3).getStringCellValue();
        Row rw3 = wb.getSheet("contacts").getRow(9);
        String salutationType = rw3.getCell(4).getStringCellValue();
		// Step1 Launch Browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);

		// Step2 Login to application with valid credentials
		driver.findElement(By.name("user_name")).sendKeys(USERNAME, Keys.TAB, PASSWORD, Keys.ENTER);

		// Step3 Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step4 Click on Create contact look up image
		driver.findElement(By.xpath("//img[contains(@alt,'Create Contact')]")).click();

		// Step5 Create contact with manadatory fields
		WebElement salutationtypeList = driver.findElement(By.name("salutationtype"));
		Select selsalutationtype = new Select(salutationtypeList);
		selsalutationtype.selectByValue(salutationType);
		driver.findElement(By.name("firstname")).sendKeys(firstName, Keys.TAB, "", Keys.TAB, lastName);
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();

		// Step6 Select the Organization from organization look up image
		Set<String> windowsList = driver.getWindowHandles();
		for (String childWindow : windowsList) {
			driver.switchTo().window(childWindow);
			System.out.println(driver.getTitle());
			if (!driver.getTitle().contains("Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM")) {
				break;
			}

		}
		driver.findElement(By.xpath("//a[.='exioms']")).click();
		for (String parentWindow : windowsList) {
			driver.switchTo().window(parentWindow);
			if (driver.getTitle().contains("Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM")) {
				break;
			}
		}

		// Step7 Save and verify
		driver.findElement(By.xpath("//input[contains(@value,'Save')]")).click();

		// Step8 Logout
		WebElement useImge = driver
				.findElement(By.xpath("//span[.=' Administrator']/ancestor::tr[2]/descendant::img[3]"));
		Actions act = new Actions(driver);
		act.moveToElement(useImge).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		//Thread.sleep(1000);
	//	driver.close();

	}

}
