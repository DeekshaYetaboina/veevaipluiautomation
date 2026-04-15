package com.automation.tests.testcomponents;

import com.automation.testutils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

/**
 * BaseTest class is responsible for initializing and closing
 * the WebDriver instance for test execution.
 * <p>
 * It provides browser setup before each test method and ensures
 * proper teardown after execution.
 */
public class BaseTest {

    public WebDriver driver;

    @BeforeSuite
    public void setupSuite() {
        ConfigReader.loadConfig();
    }

    @BeforeMethod
    @Parameters("browser")
    public void openBrowser(String browser) {

        driver = DriverFactory.initDriver(browser);

        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
