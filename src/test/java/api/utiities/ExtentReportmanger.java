package api.utiities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportmanger implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timer stamp
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter("/Users/amriteshkumar/eclipse-workspace/RestAssured-training/PetstoreAutomation/Reports/" + repName);  // folder: reports
        sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");  // Title of report
        sparkReporter.config().setReportName("Pet Store Users API");  // Report name
        sparkReporter.config().setTheme(Theme.DARK);  // Theme

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add environment info
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "Puja");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush(); // write everything to the report
    }
}
