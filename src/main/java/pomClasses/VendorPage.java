package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorPage {

	@FindBy(xpath = "//img[@alt='Create Vendor...']")
	private WebElement vendorLookUpImg;
	
   public VendorPage(WebDriver driver)
   {
	   PageFactory.initElements(driver, this);
	}

   public WebElement getVendorLookUpImg() {
	return vendorLookUpImg;
}
   
   public void clickOnVendorLookUpImg()
   {
	   vendorLookUpImg.click();
   }
	
}
