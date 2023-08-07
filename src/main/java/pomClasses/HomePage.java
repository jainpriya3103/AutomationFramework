package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility{

@FindBy(linkText="Organizations")
private WebElement organizationLink;

@FindBy(linkText="Contacts")
private WebElement contactLink;

@FindBy(linkText="Opportunities")
private WebElement opportunityLnk;

@FindBy(xpath = "//a[.='More']")
private WebElement moreDD;

@FindBy(xpath = "//a[@name='Vendors']")
private WebElement vendorLnk;

@FindBy(xpath="//img[@src ='themes/softed/images/user.PNG']")
private WebElement adminImg;

@FindBy(linkText="Sign Out")
private WebElement signOutLnk;

@FindBy(linkText="Products")
private WebElement productLnk;

@FindBy(linkText="Campaigns")
private WebElement campaignsLnk;


public HomePage (WebDriver driver)
{
	PageFactory.initElements(driver, this);
	
	}

public WebElement getOrganization()
{
	return organizationLink;
	}
public WebElement getContact()
{
	return contactLink;
	}
public WebElement getOpportunity()
{
	return opportunityLnk;
	}
public WebElement getAdminImg()
{
	return adminImg;
	}
public WebElement getSignOutLink()
{
	return signOutLnk;
	}
public WebElement getMoreDD()
{
	return moreDD;
	}

public WebElement getvendorLnk()
{
	return vendorLnk;
	}


public WebElement getProductLnk() {
	return productLnk;
}


public WebElement getCampaignsLnk() {
	return campaignsLnk;
}

public void clickOnOrganization()
{
	organizationLink.click();
	
}
public void clickOnContact()
{
	contactLink.click();
	
}

public void clickOnOppotunity()
{
	opportunityLnk.click();
}

public void clickonSignOut(WebDriver driver)
{
	MouseHoverAction(driver,adminImg);
	signOutLnk.click();
	
}

public void clickOnVendorLink(WebDriver driver)
{
	MouseHoverAction(driver, moreDD);
	vendorLnk.click();
}

public void clickOnProductLink()
{
	productLnk.click();
}

public void clickOnCampaignsLink(WebDriver driver)
{
	MouseHoverAction(driver, moreDD);
	campaignsLnk.click();
}
}
