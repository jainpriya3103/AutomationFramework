package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewContacts extends WebDriverUtility
{
@FindBy(name = "salutationtype")
private WebElement salutationDropDown;

@FindBy(name = "firstname")
private WebElement firstNameEdt;

@FindBy(name = "lastname")
private WebElement lastNameEdt;

@FindBy(name = "leadsource")
private WebElement leadSourceDD;

@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

@FindBy(xpath="//input[@name='account_id']/following-sibling::img[@alt='Select']")
private WebElement organizationLookUpImg;

public CreateNewContacts(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

public WebElement getSalutationDropDown() {
	return salutationDropDown;
}

public WebElement getFirstNameEdt() {
	return firstNameEdt;
}

public WebElement getLastNameEdt() {
	return lastNameEdt;
}

public WebElement getSaveBtn() {
	return saveBtn;
}

public WebElement getleadSourceDD()
{
	return leadSourceDD;
}

public WebElement getorganizationLookUpImg() {
	return organizationLookUpImg;
}

public void createContact(String firstName,String lastName)
{
	firstNameEdt.sendKeys(firstName);
	lastNameEdt.sendKeys(lastName);
	saveBtn.click();
	
	}
public void createContact(String firstName,String lastName,String salutationType)
{
 HandleDropDown(salutationDropDown,salutationType);
 firstNameEdt.sendKeys(firstName);
	lastNameEdt.sendKeys(lastName);
	saveBtn.click();
}

public void createContact(String lastName,WebDriver driver,String orgName)
{
	lastNameEdt.sendKeys(lastName);
	organizationLookUpImg.click();
	switchToWindow(driver, "Accounts");
	driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgName,Keys.ENTER);
	driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
	switchToWindow(driver, "Contacts");
	saveBtn.click();
	}

public void createContact(WebDriver driver,String leadSourceValue,String lastName)
{
	lastNameEdt.sendKeys(lastName);
	HandleDropDown(leadSourceDD, leadSourceValue);
	saveBtn.click();
}
}
