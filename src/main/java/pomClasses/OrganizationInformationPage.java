package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {

	@FindBy(xpath = "//input[@title='Edit [Alt+E]']")
	private WebElement editBtn;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerText;
	
	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getEditButton()
	{
		return editBtn;
	}
	public WebElement getHeaderText()
	{
		return headerText;
	}
	public void clickOnEditButton()
	{
		editBtn.click();
	}
	public String getHeader()
	
	{
		return headerText.getText();
	}
}
