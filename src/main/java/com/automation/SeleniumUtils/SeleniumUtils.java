package com.automation.SeleniumUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * SeleniumUtils provides reusable methods for:
 * - Explicit waits
 * - Mouse actions
 * - Scrolling
 * Helps in handling synchronization issues.
 */
public class SeleniumUtils {
    WebDriver driver;

    /**
     * Constructor to initialize WebDriver.
     * @param driver WebDriver instance
     */
    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;

    }

    /**
     * Waits for element to be visible.
     * @param element WebElement to wait for
     */
    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element using locator.
     * @param locator By locator
     */
    public void waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Performs hover action on element.
     * @param element WebElement to hover
     */
    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Waits until URL matches expected.
     * @param expectedUrl expected URL
     */
    public void waitForUrlToLoad(String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    /**
     * Scrolls to a specific element.
     * @param locator By locator
     */
    public void scrollToElement(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.scrollToElement(element).perform();
    }
}