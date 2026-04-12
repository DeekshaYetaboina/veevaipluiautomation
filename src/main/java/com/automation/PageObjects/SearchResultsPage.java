package com.automation.PageObjects;

import com.automation.AbstractComponents.AbstractComponents;
import com.automation.IPLPageSelectors;
import com.automation.SeleniumUtils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
/**
 * SearchResultsPage handles search functionality in the News section.
 *
 * This class verifies:
 * - Search execution
 * - Article retrieval
 * - Keyword matching in results
 */
public class SearchResultsPage extends AbstractComponents {
    /**
     * Utility class for waits and actions.
     */
    SeleniumUtils seleniumUtils;
    /**
     * Constructor to initialize WebDriver and utilities.
     *
     * @param driver WebDriver instance
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        seleniumUtils = new SeleniumUtils(driver);
    }
    /**
     * Navigates to a section using header.
     *
     * @param header navigation menu text
     */
    @Override
    public void execute(String header) {
        super.execute(header);
    }

    By searchButton = By.cssSelector(IPLPageSelectors.searchButton);
    By searchTab = By.cssSelector(IPLPageSelectors.searchTab);
    By newsSection = By.cssSelector(IPLPageSelectors.newsSection);
    By allArticles = By.cssSelector(IPLPageSelectors.allArticles);

    /**
     * Navigates to News section and performs search.
     *
     * @param searchText text to search
     */
    public void navigateToNewsSection(String searchText) {
        seleniumUtils.waitForUrlToLoad("https://www.iplt20.com/news");
        driver.findElement(searchButton).click();
        seleniumUtils.waitForElementToAppear(searchTab);
        WebElement search = driver.findElement(searchTab);
        search.sendKeys(searchText, Keys.ENTER);

    }
    /**
     * Retrieves all article titles from search results.
     *
     * @return list of article titles
     */
    public List<String> verifySearchResultsPage() {
        List<String> articleTitles = new ArrayList<>();
        seleniumUtils.waitForElementToAppear(newsSection);
        List<WebElement> articles = driver.findElements(allArticles);

        for (WebElement article : articles) {
            articleTitles.add(article.getText().toLowerCase());
        }
        return articleTitles;
    }

    /**
     * Checks if any article contains all expected keywords.
     *
     * @param keywords list of expected keywords
     * @return true if match found, false otherwise
     */
    public boolean isArticleFound(List<String> keywords) {
        List<String> articlesFound = verifySearchResultsPage();
        for (String article : articlesFound) {
            boolean match = true;
            for (String keyword : keywords) {
                if (!article.contains(keyword)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true;
            }
        }
        return false;


    }
}
