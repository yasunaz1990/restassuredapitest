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
    private ExtentTest eachTestCase;    // Each test case


    // ============ Test Context Related ============= //

    // This method will be executed as each Test context stated in testng.xml
    // is about to be executed. It prepares report generation objects.
    public void onStart(ITestContext testContext) {
        String reportPath = System.getProperty("user.dir") + "/reports/result.html";
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        extent.attachReporter(spark);
    }

    // This method will be executed as each Test context stated in testng.xml
    // is concluding.  It saves the generated report.
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }



    // ============ Test Cases Related ============= //


    // This method will be executed before each test case in any selected
    // Test context executes. It creates test cases section in the report
    public void onTestStart(ITestResult testcase) {
        eachTestCase = extent.createTest(testcase.getName());
        Steps.init(eachTestCase);
        String description = testcase.getMethod().getDescription();
        Steps.log("Test Case Name: " + description);
    }


    // This method will be executed after test case in the test case section
    // resulted in success
    public void onTestSuccess(ITestResult result) {
        eachTestCase.pass("This test case has passed");
    }

    // This method will be executed after test case in the test cases section
    // resulted in failure
    public void onTestFailure(ITestResult result) {
        eachTestCase.fail("This test case has failed");
    }

    // This method will be executed after test case in the test case section
    // results in skipped
    public void onTestSkipped(ITestResult result) {
        // Do Nothing
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // TODO: No implementation
    }

}
