package vtiger_practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class LoginToApp {

	public static void main(String[] args) throws IOException {
		WebDriverUtility wdUtil = new WebDriverUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		String URl = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		wdUtil.waitForPageLoad(driver);
		driver.get(URl);
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		HomePage hp = new HomePage(driver);
        hp.clickOnOrganization();
	}

}

