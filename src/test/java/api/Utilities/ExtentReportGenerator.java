package api.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.batik.css.engine.value.svg.MarkerManager;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.joda.time.DateTime;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportGenerator  extends TestListenerAdapter{

	
	public ExtentHtmlReporter htmlreporter;
	public ExtentReports report;
	public ExtentTest test;
	@Override
	public void onTestSuccess(ITestResult tr) {
		test=report.createTest(tr.getName()+" Passed");
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName() + "  "+tr.getParameters(),ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		test=report.createTest(tr.getName()+" Failed");
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName() + "  "+tr.getInstanceName(), ExtentColor.RED));
		
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		test=report.createTest(tr.getName()+" Skipped");
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName() + "  "+tr.getInstanceName(), ExtentColor.ORANGE));
	}

	@Override
	public void onStart(ITestContext testContext) {
		String timestand= new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		String fileName="Report "+timestand+".html";
		
		File reportFile=new File(System.getProperty("user.dir"),"/Reports/"+fileName);
		htmlreporter=new ExtentHtmlReporter(reportFile);
		
		htmlreporter.config().setDocumentTitle("Pet Store API Automation");
		htmlreporter.config().setReportName("API Automation");
		htmlreporter.config().setTheme(Theme.DARK);
	
		report=new ExtentReports();
		report.attachReporter(htmlreporter);
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("Host", "Local Host");
		report.setSystemInfo("System", "Windows11");
		report.setSystemInfo("Tester", "Jack");
		report.setSystemInfo("DateTime", timestand.toString());
		
		
		
	}

	@Override
	public void onFinish(ITestContext testContext) {
		report.flush();
	}
	
	



}
