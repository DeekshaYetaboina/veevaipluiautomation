package com.automation.tests;

import com.automation.PageObjects.SearchResultsPage;
import com.automation.TestUtils.TestDataUtils;

import com.automation.tests.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
/**
 * VerifySearchResultsTest class validates the search functionality
 * on the IPL website.
 *
 * This test ensures that relevant articles are displayed based on
 * the search input and expected keywords from JSON data.
 */
public class VerifySearchResultsTest extends BaseTest {

    /**
     * Page object representing search results functionality.
     */
    SearchResultsPage searchResultsPage;
    /**
     * Utility class used to fetch test data from JSON files.
     */
    TestDataUtils testDataUtils = new TestDataUtils();
    /**
     * Initializes the SearchResultsPage object before each test.
     */
    @BeforeMethod
    public void initialize(){
            searchResultsPage=new SearchResultsPage(driver);
        }
    /**
     * Verifies that search results contain expected keywords.
     *
     * Steps:
     * 1. Fetch header, search text, and expected keywords from JSON
     * 2. Navigate to the required section using header
     * 3. Perform search using input text
     * 4. Validate that at least one article contains expected keywords
     *
     * @throws IOException if there is an issue reading JSON data
     */
    @Test
    public void verifySearchResultsPage() throws IOException {
    String path = testDataUtils.getPath("ExpectedSearchResult");
    String header=testDataUtils.getJsonString(path,"header");
    String searchText =testDataUtils.getJsonString(path,"searchText");
    List<String> expectedText=testDataUtils.getJsonData(path,"expectedKeywords");
    searchResultsPage.execute(header);
    searchResultsPage.navigateToNewsSection(searchText);
    boolean textFound =searchResultsPage.isArticleFound(expectedText);
    Assert.assertTrue(textFound, "No article found on auction 2026");
        }
}
