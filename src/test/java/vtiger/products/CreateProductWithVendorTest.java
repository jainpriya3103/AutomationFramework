package vtiger.products;

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
import pomClasses.CreateNewProduct;
import pomClasses.CreateNewVendorPage;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.ProductInformationPage;
import pomClasses.ProductPage;
import pomClasses.VendorInformationPage;
import pomClasses.VendorPage;

public class CreateProductWithVendorTest {
@Test
	public void createProductWithVendorTest() throws IOException, InterruptedException {
		// create object for utilities
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		int randomNumber = jUtil.getRandomNumber();
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		String vendorName = eUtil.readDataFromExcel("vendor", 1, 2);
		String productName = eUtil.readDataFromExcel("product", 4, 2)+randomNumber;
		String glAccountType = eUtil.readDataFromExcel("product", 4, 3);
		
		WebDriver driver = null;
		//Launch the browser
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

		//Step 2 Launch the url
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 3 Login to application with valid credentials
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4 Click on vendor from home page
		HomePage hp = new HomePage(driver);
		hp.clickOnVendorLink(driver);
		
		//Step 5 Click on create vendor look up image
		VendorPage vp = new VendorPage(driver);
		vp.clickOnVendorLookUpImg();
		
		//Step 6 Fill all the mandatory fields 
		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.createVendor(vendorName);
		
 		//Step 7 Verify the Vendor information
		VendorInformationPage vip = new VendorInformationPage(driver);
		String header = vip.headerText();
		if (header.contains(vendorName)) {
			System.out.println("Script is passed vendor is created");
		}
		
		else
		{
			System.out.println("script is fail");
		}
		
		//Step 8 Click on Product link from Home page
		hp.clickOnProductLink();
		
		//Step 9 Click on Create product look up image
		ProductPage pp = new ProductPage(driver);
		pp.clickOnCreateProductLookUpImg();
		
		//Step 10 Fill all the mandatory fields along with vendor
		CreateNewProduct cnp = new CreateNewProduct(driver);
		cnp.createProduct(driver, productName, glAccountType, vendorName);
		
		//Step 11 Verify the Product
		ProductInformationPage pip = new ProductInformationPage(driver);
		String productHeader = pip.getHeaderText();
		if (productHeader.contains(productName)) {
			System.out.println("product created");
			
		}
		else
		{
			System.out.println("product not created");
		}
		
		//Step 12 logout the application
		hp.clickonSignOut(driver);
	}

}
