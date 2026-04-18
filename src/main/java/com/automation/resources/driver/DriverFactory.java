package com.automation.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
/**
 * DriverFactory class provides methods for:
 * 1. Initializing WebDriver instances based on browser type
 * 2. Managing WebDriver using ThreadLocal for parallel execution
 * 3. Providing access to WebDriver instance across the framework
 * 4. Properly closing and cleaning up WebDriver instances
 * This class ensures thread-safe WebDriver handling and supports
 * multiple browser execution.
 */
public class DriverFactory {

    /**
     * ThreadLocal WebDriver instance to support parallel test execution.
     */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initializes WebDriver based on the given browser type.
     *
     * @param browser the browser name (e.g., chrome, edge)
     * @return initialized WebDriver instance
     *
     * This method:
     * 1. Sets up the required driver using WebDriverManager
     * 2. Creates a browser instance
     * 3. Maximizes the browser window
     */
    public static WebDriver initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            try {
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
            } catch (Exception e) {
                String path = System.getProperty("user.dir") + "/src/main/java/com/automation/resources/seleniumdrivers/msedgedriver.exe";
                System.setProperty("webdriver.edge.driver", path);
                driver.set(new EdgeDriver());
            }
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().window().maximize();
        return getDriver();
    }
    /**
     * Returns the current thread's WebDriver instance.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
    /**
     * Quits the WebDriver instance and removes it from ThreadLocal.
     * Ensures proper cleanup after test execution.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
