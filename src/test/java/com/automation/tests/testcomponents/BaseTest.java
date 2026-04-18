package com.automation.tests.testcomponents;

import com.automation.testutils.ConfigReader;
import com.automation.testutils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
/**
 * BaseTest class provides common setup and teardown methods for all test classes.
 * This class is responsible for:
 * 1. Loading configuration before test execution
 * 2. Initializing WebDriver based on the browser parameter
 * 3. Navigating to the application URL
 * 4. Closing the browser after each test method
 * It acts as a parent class for all test classes to ensure
 * reusable and consistent test execution flow.
 */
public class BaseTest {
    /**
     * WebDriver instance used for browser interactions.
     */
    public WebDriver driver;
    /**
     * Loads configuration before the test suite starts.
     * This ensures all required properties are available for tests.
     */
    @BeforeSuite
    public void setupSuite() {
        ConfigReader.loadConfig();
    }
    /**
     * Initializes the browser before each test method.
     *
     * @param browser the browser type passed from TestNG XML (e.g., chrome, edge)
     *
     * This method:
     * 1. Creates WebDriver instance using DriverFactory
     * 2. Launches the application using base URL from config file
     */
    @BeforeMethod
    @Parameters("browser")
    public void openBrowser(String browser) {

        driver = DriverFactory.initDriver(browser);

        driver.get(ConfigReader.get("baseUrl"));
    }
    /**
     * Closes the browser after each test method execution.
     * Ensures proper cleanup and avoids memory leaks.
     */
    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
