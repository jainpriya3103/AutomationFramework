package pomClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OppotunityInformationPage {

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerText;

	public WebElement getHeaderText() {
		return headerText;
	}
	
	public String getHeader()
	{
		return headerText.getText();
	}
}
