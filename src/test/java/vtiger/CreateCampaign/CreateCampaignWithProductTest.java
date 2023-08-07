package vtiger.CreateCampaign;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;
import genericUtilities.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import pomClasses.CampaignInformationPage;
import pomClasses.CampaignsPage;
import pomClasses.CreateNewCampaign;
import pomClasses.CreateNewProduct;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.ProductInformationPage;
import pomClasses.ProductPage;
//@Listeners(genericUtilities.ListenerImplementation.class)
public class CreateCampaignWithProductTest {
    @Test
	public void createCampaignWithProductTest() throws IOException, InterruptedException {
     // create objects for all utilities
		PropertyFileUtility pUtil = new PropertyFileUtility();
		ExcelFileUtility eUtil = new ExcelFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		int randomNumber = jUtil.getRandomNumber();
		
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD= pUtil.readDataFromPropertyFile("password");
		String productName = eUtil.readDataFromExcel("product", 4, 2) + randomNumber;
		String campaignName = eUtil.readDataFromExcel("campaign", 4, 2) + randomNumber;
		String campaignTypeValue = eUtil.readDataFromExcel("campaign", 4, 3);
		String campaignStatusValue = eUtil.readDataFromExcel("campaign", 4, 4);
		
		//Step 1 launch the browser
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
		
		//Step 3 login to application with valid credentials
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 4 navigate to products link from home page
		HomePage hp = new HomePage(driver);
		hp.clickOnProductLink();
		
		//Step 5 click on create product look up image
		ProductPage pp = new ProductPage(driver);
		pp.clickOnCreateProductLookUpImg();
		
		//Step 6 Fill all the mandatory fields
		CreateNewProduct cnp = new CreateNewProduct(driver);
		cnp.createProduct(driver, productName);
		
		//Step 7 Verify the created product 
		ProductInformationPage pip = new ProductInformationPage(driver);
		String createdProduct = pip.getHeaderText();
		if (createdProduct.contains(productName)) {
			System.out.println("product created");
		}
		else
		{
			System.out.println("script is fail product is not created");
		}
		
		//Step 8 navigate to campaign page
		hp.clickOnCampaignsLink(driver);
		
		//Step 9 click on create campaign look up image
		CampaignsPage cp = new CampaignsPage(driver);
		cp.clickOnCampaignLookUpImg();
		
		//Step 10 Fill all the mandatory fields along with product
		CreateNewCampaign cnc = new CreateNewCampaign(driver);
		cnc.createCampaign(driver, campaignName, campaignTypeValue, campaignStatusValue, productName);
		
		//Step 11 Verify the created campaign
		CampaignInformationPage cip = new CampaignInformationPage(driver);
		String createdCampaign = cip.getCampaignHeaderText();
		if (createdCampaign.contains(campaignName)) {
			System.out.println("script is pass");
		}
		else
		{
			System.out.println("script is fail");
		}
		
		//Step 12 logout the application
		hp.clickonSignOut(driver);
		System.out.println("logout successful");
		
		Thread.sleep(1000);
		driver.quit();
	}

}
