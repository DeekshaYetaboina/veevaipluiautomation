package com.automation.tests.testcomponents;

import com.automation.testutils.TestUtils;
import com.automation.resources.ExtentReportsNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
/**
 * Listeners class implements TestNG's ITestListener interface
 * to track test execution events and generate Extent Reports.
 *
 * It logs test status (pass/fail) and captures screenshots on failure,
 * attaching them to the report for better debugging and analysis.
 */
public class Listeners implements ITestListener{
    /**
     * ExtentTest instance used to log test steps and status.
     */
    ExtentTest test;
    /**
     * ExtentReports instance used to generate the HTML report.
     */
    ExtentReports extent= ExtentReportsNG.getReport();
    /**
     * Invoked when a test method starts execution.
     * Creates a new test entry in the Extent Report.
     *
     * @param result contains information about the current test
     */
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());

    }
    /**
     * Invoked when a test method passes successfully.
     * Logs the pass status in the Extent Report.
     *
     * @param result contains information about the current test
     */

    @Override
    public void onTestSuccess(ITestResult result) {
    test.log(Status.PASS,"Test Passed");
    }
    /**
     * Invoked when a test method fails.
     * Logs the failure details and captures a screenshot,
     * which is then attached to the Extent Report.
     *
     * @param result contains information about the failed test
     */

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        WebDriver driver = ((BaseTest) result.getInstance()).driver;
        String filepath=null;
        try {
            filepath = TestUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
    }
    /**
     * Invoked after all tests in the suite have finished execution.
     * Flushes the Extent Report to write all logs to the HTML file.
     *
     * @param context provides test execution context
     */
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
    }

