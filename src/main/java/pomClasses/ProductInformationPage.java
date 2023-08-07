package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {

	@FindBy(xpath = "//span[@class='lvtHeaderText']")
	private WebElement header;
	
	public ProductInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getHeader()
	{
		return header;
	}
	
	public String getHeaderText()
	{
		return header.getText();
	}
}
