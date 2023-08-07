package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewCampaign extends WebDriverUtility {
	
	@FindBy(name ="campaignname")
	private WebElement campaignNameEdt;
	
	@FindBy(name = "campaigntype")
	private WebElement campaigntypeDD;
	
	@FindBy(name = "campaignstatus")
	private WebElement campaignstatusDD;
	
	@FindBy(xpath = "//img[@alt='Select']")
	private WebElement productBtn;
	
	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchEdt;
	
	@FindBy(xpath ="//input[@name='search']")
	private WebElement searchBtn;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public CreateNewCampaign(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaignNameEdt() {
		return campaignNameEdt;
	}

	public WebElement getCampaigntypeDD() {
		return campaigntypeDD;
	}

	public WebElement getCampaignstatusDD() {
		return campaignstatusDD;
	}

	public WebElement getProductBtn() {
		return productBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	/**
	 * This method will create campaign with product 
	 * @param driver
	 * @param campaignName
	 * @param campaignTypeValue
	 * @param campaignStatusValue
	 * @param productName
	 * @throws InterruptedException
	 */
	
	public void createCampaign(WebDriver driver,String campaignName,String campaignTypeValue,String campaignStatusValue,String productName) throws InterruptedException
	{
		campaignNameEdt.sendKeys(campaignName);
		HandleDropDown(campaigntypeDD, campaignTypeValue);
		HandleDropDown(campaignstatusDD, campaignStatusValue);
		productBtn.click();
		switchToWindow(driver, "Products");
		searchEdt.sendKeys(productName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
		Thread.sleep(2000);
		switchToWindow(driver, "Campaigns");
		saveBtn.click();
		
	}
	
	
	
	
	
	
	
	
	

}
