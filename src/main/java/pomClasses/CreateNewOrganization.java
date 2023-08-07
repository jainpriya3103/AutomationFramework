package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class CreateNewOrganization extends WebDriverUtility {

	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	public CreateNewOrganization(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}
	public WebElement getsaveBtn() {
		return saveBtn;
	}
	
	public void crateOrganization(String orgName)
	{
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	public void crateOrganization(String orgName,String industryName)
	{
		orgNameEdt.sendKeys(orgName);
		System.out.println("element clicked");
		HandleDropDown(industryDropDown,industryName);
		System.out.println("dopdown");
		saveBtn.click();
	}
}
