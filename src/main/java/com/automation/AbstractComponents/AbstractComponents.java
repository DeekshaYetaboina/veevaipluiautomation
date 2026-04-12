package com.automation.AbstractComponents;
import com.automation.IPLPageSelectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * AbstractComponents class provides reusable methods for common UI interactions
 * across different pages in the automation framework.
 *
 * This class acts as a base component class and is intended to be extended
 * by page classes to avoid code duplication.
 */
public class AbstractComponents {
    /**
     * WebDriver instance used to interact with the browser.
     */
    protected WebDriver driver;
    /**
     * Constructor to initialize WebDriver for child classes.
     *
     * @param driver the WebDriver instance passed from BaseTest
     */
    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }
    /**
     * Performs click action on a header element dynamically.
     *
     * This method replaces the placeholder in the locator with the provided
     * header text and clicks on the corresponding element.
     *
     * Example:
     * If locator is "//a[text()='%s']" and header = "Teams",
     * it clicks on the "Teams" tab.
     *
     * @param header the visible text of the header to be clicked
     */
    public void execute(String header) {
        driver.findElement(By.cssSelector((IPLPageSelectors.headers).replace("%s", header))).click();

    }

}
