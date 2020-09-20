package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestExecutionListener implements ITestListener {

    private ExtentReports extent;       // Report manager
    private ExtentSparkReporter spark;  // Report writer
    private ExtentTest eachTestCase;        // Each test case


    // === Test Context Related === //
    public void onStart(ITestContext testContext) {
        String reportPath = System.getProperty("user.dir") + "/reports/result.html";
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        extent.attachReporter(spark);
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }

    // === Test Cases  Related === //
    public void onTestStart(ITestResult testcase) {
        eachTestCase = extent.createTest(testcase.getName());
        Steps.init(eachTestCase);
    }

    public void onTestSuccess(ITestResult result) {
        eachTestCase.pass("This test case has passed");
    }

    public void onTestFailure(ITestResult result) {
        eachTestCase.fail("This test case has failed");
    }

    public void onTestSkipped(ITestResult result) {
        // Do Nothing
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Do Nothing
    }
}
