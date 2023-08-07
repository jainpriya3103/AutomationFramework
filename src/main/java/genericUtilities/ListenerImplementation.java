package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
 * This class will provide implementation to ITestListener interface method
 */

public class ListenerImplementation implements ITestListener
{
	
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"Test Started");
		test = report.createTest(methodName);
		test.log(Status.INFO, methodName + "--->Test Started");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"Test Successful");
		test.log(Status.INFO, methodName + "-->Pass");
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println("Test Failed");
		System.out.println(result.getThrowable());
		test.log(Status.FAIL,methodName +" --> Fail" );
		test.log(Status.WARNING, result.getThrowable());
		String screenshotName = methodName +"-" + new JavaUtility().getFormattedDate();
		WebDriverUtility wUtil = new WebDriverUtility();
		try {
			String path = 	wUtil.takeScreenShot(BaseClass.sDriver,screenshotName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println("Test Skipped");
		test.log(Status.SKIP,methodName + " --> Skip" );
	}

	

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub..
		
		System.out.println("Execution Started");
		// Configure the extent report
		ExtentSparkReporter html = new ExtentSparkReporter(".\\ExtentReport\\Report-" +new JavaUtility().getFormattedDate() +".html");
		html.config().setDocumentTitle("Vtiger execution report");
		html.config().setReportName("Execution report buildV2.3.1");
		html.config().setTheme(Theme.DARK);
		report = new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Base browser","Chrome");
		report.setSystemInfo("Base platform","Windows-Family");
		report.setSystemInfo("Reporter name", "Priya");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("Execution Finished");
		report.flush();
	}
	

}
