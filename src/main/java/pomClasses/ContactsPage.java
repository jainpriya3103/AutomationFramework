package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactLookUp ;
	
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getContactLookUp()
	{
		return createContactLookUp;
	}
	/**
	 * This method will click on create contact look up
	 */
	
	public void clickOnCreateContactLookUp()
	{
		createContactLookUp.click();
	}
	
	
}
