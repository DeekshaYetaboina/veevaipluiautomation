package com.automation.tests;

import com.automation.resources.driver.DriverFactory;
import com.automation.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * BaseTest class provides common setup and teardown methods for all test classes.
 */
public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    public WebDriver driver;

    /**
     * Loads configuration before the test suite starts.
     */
    @BeforeSuite
    public void setupSuite() {
        log.info("========== Test Suite Execution Started ==========");
        log.info("Loading configuration file...");
        ConfigReader.loadConfig();
        log.info("Configuration loaded successfully");
    }

    /**
     * Initializes the browser before each test method.
     */
    @BeforeMethod
    @Parameters("browser")
    public void openBrowser(String browser) {
        log.info("--------------------------------------------------");
        log.info("Starting new test execution");
        log.info("Launching browser: {}", browser);
        driver = DriverFactory.initDriver(browser);
        String url = ConfigReader.get("baseUrl");
        log.info("Navigating to URL: {}", url);
        driver.get(url);
        log.info("Application launched successfully");
    }

    /**
     * Closes the browser after each test method execution.
     */
    @AfterMethod
    public void tearDown() {
        log.info("Closing browser");
        DriverFactory.quitDriver();
        log.info("Test execution completed");
        log.info("--------------------------------------------------");
    }
}