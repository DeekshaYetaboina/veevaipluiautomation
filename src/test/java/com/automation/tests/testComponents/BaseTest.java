package com.automation.tests.testComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
/**
 * BaseTest class is responsible for initializing and closing
 * the WebDriver instance for test execution.
 *
 * It provides browser setup before each test method and ensures
 * proper teardown after execution.
 */
public class BaseTest {
    /**
     * WebDriver instance used to interact with the browser.
     */
    public WebDriver driver;
    /**
     * Initializes the browser before each test method.
     *
     * The browser type is passed as a parameter from the TestNG XML file.
     * Based on the input, the corresponding browser driver is launched.
     *
     * @param browser the name of the browser (e.g., "chrome", "edge")
     * @throws RuntimeException if the provided browser is not supported
     */
    @BeforeMethod
    @Parameters("browser")
    public void openBrowser(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else{
            throw new RuntimeException("Browser not supported"+browser);
        }
    driver.get("https://www.iplt20.com/");
    driver.manage().window().maximize();
    }
    /**
     * Closes the browser after each test method execution.
     * Ensures proper cleanup of WebDriver resources.
     */
    @AfterMethod
    public void closeBrowser() {
    driver.quit();
    }
    }
