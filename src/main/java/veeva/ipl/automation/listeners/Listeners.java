package veeva.ipl.automation.listeners;

import veeva.ipl.automation.reports.ExtentManager;
import veeva.ipl.automation.drivermanager.DriverFactory;
import veeva.ipl.automation.utils.TestUtils;
import com.aventstack.extentreports.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.IOException;

/**
 * Listeners class implements TestNG's ITestListener interface
 * to track test execution events and generate Extent Reports.
 */
public class Listeners implements ITestListener {
    /**
     * Logger instance for Listeners class.
     */
    private static final Logger log = LogManager.getLogger(Listeners.class);
    /**
     * Thread-safe ExtentTest instance.
     */
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    /**
     * ExtentReports instance used to generate the HTML report.
     */
    ExtentReports extent = ExtentManager.getReport();

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        log.info("Test Started: {}", testName);

        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = result.getMethod().getMethodName();

        log.info("Test Passed: {}", testName);

        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.error("Test Failed: {}", testName);
        log.error("Failure Reason: ", result.getThrowable());
        test.get().fail(result.getThrowable());
        WebDriver driver = DriverFactory.getDriver();
        try {
            String filepath = TestUtils.takeScreenshot(driver, testName);
            log.info("Screenshot captured at: {}", filepath);
            test.get().addScreenCaptureFromPath(filepath, testName);
        } catch (IOException e) {
            log.error("Failed to capture screenshot", e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        log.warn("Test Skipped: {}", testName);
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("========== Test Execution Started ==========");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("========== Test Execution Finished ==========");
        log.info("Flushing Extent Report...");
        extent.flush();
        log.info("Extent Report generated successfully");
    }
}