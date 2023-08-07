package pomClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewProduct extends WebDriverUtility{

@FindBy(name = "productname")
private WebElement productNameEdt;

@FindBy(name = "glacct")
private WebElement glAccountDD;

@FindBy(xpath = "//img[@alt='Select']")
private WebElement selectVendorLookUpImg;

@FindBy(name = "assigned_group_id")
private WebElement handlerDD;

@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

@FindBy(id = "search_txt")
private WebElement searchEdt;

@FindBy(name = "search_field")
private WebElement searchDD;

@FindBy(xpath = "//input[@name='search']")
private WebElement searchBtn;

public CreateNewProduct(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

public WebElement getProductNameEdt() {
	return productNameEdt;
}

public WebElement getGlAccountDD() {
	return glAccountDD;
}

public WebElement getSelectVendorLookUpImg() {
	return selectVendorLookUpImg;
}

public WebElement getHandlerDD() {
	return handlerDD;
}

public WebElement getSaveBtn() {
	return saveBtn;
}



public WebElement getSearchEdt() {
	return searchEdt;
}

public WebElement getSearchDD() {
	return searchDD;
}

public WebElement getSearchBtn() {
	return searchBtn;
}

public void createProduct(WebDriver driver,String productName,String glValue,String vendorName) throws InterruptedException
{
	productNameEdt.sendKeys(productName);
	selectVendorLookUpImg.click();
	switchToWindow(driver, "Vendors");
	searchEdt.sendKeys(vendorName);
	HandleDropDown(searchDD, "vendorname");
	searchBtn.click();
	driver.findElement(By.xpath("//a[.='"+vendorName+"']")).click();
	Thread.sleep(2000);
	switchToWindow(driver, "Products");
	HandleDropDown(glAccountDD, glValue);
	saveBtn.click();
	
}

public void createProduct (WebDriver driver,String productName)
{
	productNameEdt.sendKeys(productName);
	//selectVendorLookUpImg.click();
	saveBtn.click();
	
}





}
