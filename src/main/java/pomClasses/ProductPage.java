package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createProductLookUpImg;
	
	public ProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateProductLookUpImg() {
		return createProductLookUpImg;
	}
	
	public void clickOnCreateProductLookUpImg()
	{
		createProductLookUpImg.click();
	}

}
