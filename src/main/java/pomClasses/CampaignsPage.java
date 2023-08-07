package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage {
	@FindBy(xpath="//img[@alt='Create Campaign...']")
	private WebElement campaignLookUpImg;
	
	public CampaignsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getCampaignLookUpImg()
	{
		return campaignLookUpImg;
	}
	
	public void clickOnCampaignLookUpImg()
	{
		campaignLookUpImg.click();
	}

}
