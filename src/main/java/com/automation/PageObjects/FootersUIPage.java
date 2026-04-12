package com.automation.PageObjects;

import com.automation.AbstractComponents.AbstractComponents;
import com.automation.IPLPageSelectors;
import com.automation.SeleniumUtils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * FootersUIPage handles interactions with the footer section of the IPL website.
 *
 * This class is responsible for:
 * - Navigating to the footer section
 * - Retrieving footer menu section text
 * - Validating footer links
 *
 * It extends AbstractComponents to reuse common UI actions.
 */
public class FootersUIPage extends AbstractComponents {
    /**
     * Utility class for handling waits and user actions.
     */
    SeleniumUtils seleniumUtils;
    /**
     * Constructor to initialize WebDriver and utilities.
     *
     * @param driver WebDriver instance passed from test class
     */
    public FootersUIPage(WebDriver driver) {
        super(driver);
        seleniumUtils = new SeleniumUtils(driver);
    }

    /**
     * Locator for footer section container.
     */
    By footer = By.cssSelector(IPLPageSelectors.footerSection);

    /**
     * Locator for footer menu sections (TEAM, ABOUT, etc.).
     */
    By menuSections =By.cssSelector(IPLPageSelectors.footerMenuSections);

    /**
     * Locator for all footer links.
     */
    By footerLinks =By.cssSelector(IPLPageSelectors.footerLinks);
    /**
     * Scrolls to the footer section after ensuring it is visible.
     */
    public void goToFooter() {
        seleniumUtils.waitForElementToAppear(footer);
        seleniumUtils.scrollToElement(footer);
    }
    /**
     * Retrieves text values of all footer menu sections.
     *
     * @return List of footer section texts
     */
    public List<String> getActualValues() {
        List<String> text = new ArrayList<>();
        seleniumUtils.waitForElementToAppear(menuSections);
        List<WebElement> sections =driver.findElements(menuSections);
        seleniumUtils.waitForElementToAppear(sections.getFirst());
        for (WebElement section : sections) {
            String value = section.getText().replaceAll("\n", " ").trim();
            text.add(value);
        }
        return text;
    }
    /**
     * Validates footer links by navigating to each URL and comparing
     * expected vs actual URL after page load.
     *
     * Process:
     * - Collect all href values from footer links
     * - Navigate to each URL
     * - Verify the loaded URL matches expected
     * - Return to footer page
     *
     * @return Map containing expected URL as key and actual URL as value
     */
    public Map<String, String> getLinks() {
        String footerPageUrl = driver.getCurrentUrl();
        Map<String, String> expectedVsActual = new HashMap<>();
        List<String> urls = new ArrayList<>();
        seleniumUtils.waitForElementToAppear(footerLinks);
        List<WebElement> links = driver.findElements(footerLinks);
        seleniumUtils.waitForElementToAppear(links.getFirst());
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                urls.add(href);
            }
        }
        for (String url : urls)
        {
            driver.get(url);
            seleniumUtils.waitForUrlToLoad(url);
            expectedVsActual.put(url, driver.getCurrentUrl());
            driver.get(footerPageUrl);
            goToFooter();
        }
        return expectedVsActual;
    }
}




