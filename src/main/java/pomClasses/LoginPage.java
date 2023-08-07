package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
@FindBy(name= "user_name")
private WebElement userNameEdt;
@FindBy(name="user_password")
private WebElement passwordEdt;
@FindBy(id="submitButton")
private WebElement loginButton;

public LoginPage(WebDriver driver)
{
	PageFactory.initElements(driver,this);	
}
public WebElement getUserNameEdt() {
	return userNameEdt;
}
public WebElement getPasswordEdt() {
	return passwordEdt;
}
public WebElement getLoginButton() {
	return loginButton;
}
// business libraries - project specific generic class
/**
 * This method will login to application
 * @param username
 * @param password
 */
public void loginToApp(String username,String password)
{
	userNameEdt.sendKeys(username);
	passwordEdt.sendKeys(password);
	loginButton.click();
}

}
