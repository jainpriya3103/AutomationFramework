package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOpportunity extends WebDriverUtility {

	@FindBy(name = "potentialname")
	private WebElement opportuityNameEdt;
	
	@FindBy(id = "related_to_type")
	private WebElement realtedToTypeDD;
	
	@FindBy(xpath = "//input[@name='related_to_display']/following-sibling::img[@title='Select']")
	private WebElement relatedToLookUpImg;
	
	@FindBy(xpath = "//input[@value='T']")
	private WebElement assignedToRb;
	
	@FindBy(id = "search_txt")
	private WebElement searchEdt;
	
	@FindBy(xpath = "//input[@class='crmbutton small create']")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name = "search_field")
	private WebElement searchDD;
	
	public CreateNewOpportunity(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOpportuityNameEdt() {
		return opportuityNameEdt;
	}

	public WebElement getRealtedToTypeDD() {
		return realtedToTypeDD;
	}

	public WebElement getRelatedToLookUpImg() {
		return relatedToLookUpImg;
	}

	public WebElement getAssignedToRb() {
		return assignedToRb;
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
	
	public WebElement getSearchDD() {
		return searchDD;
	}

	public void createOpportunity(WebDriver driver,String opportunityName,String realtedToValue,String contactName,String orgName) throws InterruptedException
	{
		opportuityNameEdt.sendKeys(opportunityName);
		HandleDropDown(realtedToTypeDD,realtedToValue);
		relatedToLookUpImg.click();
		
		if (realtedToValue.contains("Contacts")) {
			
			switchToWindow(driver,"Contacts");	
			searchEdt.sendKeys(contactName);
			HandleDropDown(searchDD, "firstname");
			searchBtn.click();
			driver.findElement(By.xpath("//a[.='"+contactName+"']")).click();
			System.out.println("created");
			Thread.sleep(2000);
			switchToWindow(driver, "Potentials");
		}
		
		else
		{
			switchToWindow(driver,"Accounts");
			searchEdt.sendKeys(orgName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
			Thread.sleep(2000);
			switchToWindow(driver, "Potentials");
		}
		
		assignedToRb.click();
		saveBtn.click();
		
	}
	
	
	
	
	
	
}
