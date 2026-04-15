package com.automation.tests.testcomponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

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

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
