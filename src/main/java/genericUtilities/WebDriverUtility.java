package genericUtilities;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


/**
 * This class contains web diver related utilities
 * @author Priya
 *
 */
public class WebDriverUtility {
	/**
	 * This method is created to maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
	 	driver.manage().window().maximize();
	}
	
	/**
	 * This method is created to minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * This method will wait for page to load for 10 seconds
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait for 20 seconds for the element to be clickable
	 * @param driver
	 * @param element
	 */
	public void WaitForAnElementToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));	
	}
	
	/**
	 * This method will handle drop down based on index value
	 * @param element
	 * @param index
	 */
	public void HandleDropDown(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	/**
	 * This method will handle drop down based on value
	 * @param element
	 * @param value
	 */
	public void HandleDropDown(WebElement element,String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	/**
	 * This method will handle drop down based on visible text
	 * @param visibleText
	 * @param element
	 */
	public void HandleDropDown(String visibleText,WebElement element)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	/**
	 * This method will perform mouse hover action on particular element
	 * @param driver
	 * @param element
	 */
	public void MouseHoverAction(WebDriver driver,WebElement element)
	{
		Actions act  = new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method will perform right click action any where on the web page
	 * @param driver
	 */
	public void RightClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	/**
	 * his method will perform right click action on particular element
	 * @param driver
	 */
	public void RightClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	/**
	 * This method will perform drag and drop from sourceElement to targetElement
	 * @param driver
	 * @param source
	 * @param target
	 */
	public void DragAndDropAction(WebDriver driver,WebElement sourceElement,WebElement targetElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(sourceElement, targetElement).perform();
	}
	
	/**
	 * This method will drag and drop from sourceElement to target element offset
	 * @param driver
	 * @param sourceElement
	 * @param x
	 * @param y
	 */
	public void DragAndDropAction(WebDriver driver,WebElement sourceElement,int x, int y)
	{
		Actions act = new Actions(driver);
		act.dragAndDropBy(sourceElement, x, y).perform();
	}
	
	/**
	 * This method will perform double click any where on the web page
	 * @param driver
	 */
	public void DoubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	/**
	 * This method will perform double click on particular web element 
	 * @param driver
	 */
	public void DoubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This method will handle frame based on the index value
	 * @param driver
	 * @param index
	 */
	public void SwitchToFrame(WebDriver driver,int index )
	{
		driver.switchTo().frame(index);
	}
	/**
	 * This method will handle frame based on name or id
	 * @param driver
	 * @param nameorid
	 */
	public void switchToFrame(WebDriver driver,String nameorid)
	{
		driver.switchTo().frame(nameorid);
	}
	/**
	 * This method will handle frame based on web element
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method will switch the control from child frame to parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	/**
	 * This method will switch the control from child frame to default frame
	 * @param driver
	 */
	public void switchToDefaultContent(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	/**
	 * This method will accept the alert pop-up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
	Alert alt =	driver.switchTo().alert();
		alt.accept();
	}
	/**
	 * This method will dismiss the alert pop-up
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		Alert alt = driver.switchTo().alert();
		alt.dismiss();
	}
	/**
	 * This method will capture the alert pop-up text and return it to user
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will scrollup the web page based on the x and y coordinates
	 * @param driver
	 * @param lastValue
	 * @param xaxis
	 * @param yaxis
	 * @throws InterruptedException
	 */
	public void scrollUPAction(WebDriver driver,int lastValue,int x,int y) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for(int i=0;i<lastValue;i++)
		{
			js.executeScript("window.scrollBy(x,y)");
			Thread.sleep(1000);
		}
	}
	/**
	 * This method will take screenshot and return absolute path
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver,String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(".\\ScreenShot\\" +screenshotName +".png");
		Files.copy(src, dest);
		return dest.getAbsolutePath(); //used for extent reports
		 
	}
	
	public void switchToWindow(WebDriver driver,String partialWondowTitle)
	{
		//capture all window ids
		Set<String> windowIds = driver.getWindowHandles();
		
		//navigate through all window
		for(String winId :windowIds)
		{
			//switch to child window
			driver.switchTo().window(winId);
			//get window title
			String winTitle = driver.switchTo().window(winId).getTitle();
			if(winTitle.contains(partialWondowTitle))
			{
			break;	
			}
			
		}
	}
}
