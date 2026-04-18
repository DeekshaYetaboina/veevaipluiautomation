package com.automation.tests;

import com.automation.pageobjects.LogoAndYearsPage;
import com.automation.utils.TestDataUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

/**
 * VerifyLogoAndYearsTest class validates the logos and years
 * displayed on the IPL website.
 * This test uses data-driven testing by fetching expected values
 * from JSON files and comparing them with actual UI data.
 */
public class VerifyLogoAndYearsTest extends BaseTest {
    TestDataUtils testDataUtils = new TestDataUtils();
    LogoAndYearsPage logoAndYearsPage;

    /**
     * Initializes the page object before each test method.
     */
    @BeforeMethod
    public void initialize() {
        logoAndYearsPage = new LogoAndYearsPage(driver);
    }

    /**
     * Verifies that the logos displayed on the page match
     * the expected URLs from the JSON file.
     * Steps:
     * 1. Fetch expected logo URLs and header from JSON
     * 2. Navigate using header
     * 3. Retrieve actual logo URLs from UI
     * 4. Compare actual and expected values
     *
     * @throws IOException if there is an issue reading JSON data
     */
    @Test
    public void verifyLogos() throws IOException {
        String path = testDataUtils.getPath("ExpectedUrls");
        List<String> expectedUrls = testDataUtils.getJsonData(path, "expectedUrls");
        String header = testDataUtils.getJsonString(path, "header");
        logoAndYearsPage.execute(header);
        List<String> actualUrls = logoAndYearsPage.logos();
        Assert.assertEquals(actualUrls, expectedUrls, "Expected Logos are Actual Logos differ");
    }

    /**
     * Verifies that the years displayed on the page match
     * the expected values from the JSON file.
     * Steps:
     * 1. Fetch expected years and header from JSON
     * 2. Navigate using header
     * 3. Retrieve actual years from UI
     * 4. Compare actual and expected values
     *
     * @throws IOException if there is an issue reading JSON data
     */
    @Test
    public void verifyYears() throws IOException {
        String path = testDataUtils.getPath("ExpectedYears");
        String header = testDataUtils.getJsonString(path, "header");
        logoAndYearsPage.execute(header);
        List<String> actualYears = logoAndYearsPage.getYears();
        List<String> expectedYears = testDataUtils.getJsonData(path, "expectedYears");
        Assert.assertEquals(actualYears, expectedYears, "Expected Actual Years differ");
    }
}