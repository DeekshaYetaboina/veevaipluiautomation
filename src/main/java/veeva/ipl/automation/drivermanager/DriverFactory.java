package veeva.ipl.automation.drivermanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

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
     * Logger instance for DriverFactory class.
     */
    private static final Logger log = LogManager.getLogger(DriverFactory.class);
    /**
     * ThreadLocal WebDriver instance to support parallel test execution.
     */
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initializes WebDriver based on the given browser type.
     *
     * @param browser the browser name (e.g., chrome, edge)
     * @return initialized WebDriver instance
     */
    public static WebDriver initDriver(String browser) {
        log.info("Initializing WebDriver for browser: {}", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            log.info("Setting up ChromeDriver");
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            log.info("Setting up EdgeDriver");
            try {
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
            } catch (Exception e) {
                log.warn("WebDriverManager failed, falling back to local EdgeDriver");
                String path = System.getProperty("user.dir")
                        + "/src/main/resources/drivers/msedgedriver.exe";
                log.info("Using local EdgeDriver path: {}", path);
                System.setProperty("webdriver.edge.driver", path);
                driver.set(new EdgeDriver());
            }
        } else {
            log.error("Unsupported browser: {}", browser);
            throw new RuntimeException("Browser not supported: " + browser);
        }
        getDriver().manage().window().maximize();
        log.info("Browser window maximized");
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        log.info("Implicit wait set to 10 seconds");
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        log.info("Page load timeout set to 30 seconds");
        log.info("WebDriver initialized successfully");
        return getDriver();
    }

    /**
     * Returns the current thread's WebDriver instance.
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the WebDriver instance and removes it from ThreadLocal.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Quitting WebDriver");
            driver.get().quit();
            driver.remove();
            log.info("WebDriver closed successfully");
        } else {
            log.warn("Attempted to quit WebDriver, but it was null");
        }
    }
}