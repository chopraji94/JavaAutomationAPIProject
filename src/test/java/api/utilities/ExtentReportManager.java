package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReport;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public  void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		repName = "Test Report-"+timeStamp+".html";
		sparkReport = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReport.config().setDocumentTitle("Extent report of tests");
		sparkReport.config().setReportName("Functional Testing");
		sparkReport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		extent.setSystemInfo("Application", "LoginCheck");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub module", "Customer");
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Envioment", "QA");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed ");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());

		test.log(Status.FAIL, result.getName() + " got failed ");
		test.log(Status.INFO, result.getThrowable().getMessage());
		 
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skiped ");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
