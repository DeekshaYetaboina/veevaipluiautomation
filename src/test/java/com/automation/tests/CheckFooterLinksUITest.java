package com.automation.tests;


import com.automation.pageobjects.FootersUIPage;
import com.automation.testutils.TestDataUtils;
import com.automation.tests.testcomponents.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * CheckFooterLinksUITest class verifies the footer section of the IPL website.
 *
 * It validates:
 * 1. Footer section text values against expected data from JSON
 * 2. Footer links to ensure correct navigation labels
 *
 * This test uses SoftAssert to collect all assertion results
 * and report them together at the end of execution.
 */
public class CheckFooterLinksUITest extends BaseTest {
    /**
     * Utility class instance used to fetch test data from JSON files.
     */
    TestDataUtils testDataUtils = new TestDataUtils();
    /**
     * Page object representing footer UI elements and actions.
     */
    FootersUIPage footer;
    /**
     * Initializes the page object before each test method.
     */
    @BeforeMethod
    public void initialize(){
        footer = new FootersUIPage(driver);
    }

    /**
     * Validates footer links and section values.
     *
     * Steps:
     * 1. Navigate to footer section
     * 2. Fetch actual footer values from UI
     * 3. Fetch expected values from JSON file
     * 4. Compare actual and expected values
     * 5. Verify each footer link text matches its expected label
     *
     * Uses SoftAssert to ensure all validations are executed
     * even if some assertions fail.
     *
     * @throws IOException if there is an issue reading JSON data
     */
    @Test
    public void checkFooterLinks() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        footer.goToFooter();
        List<String> actualValues=footer.getActualValues();
        List<String> expectedValues=testDataUtils.getJsonData(testDataUtils.getPath("ExpectedFooterData"), "expectedFooterData");
        softAssert.assertEquals(actualValues, expectedValues, "Footer values mismatch");
        Map<String, String> expectedVsActual = footer.getLinks();
        for (Map.Entry<String, String> entry : expectedVsActual.entrySet()) {
            softAssert.assertTrue(entry.getValue().equals(entry.getKey()), "Links are not verified");
        }
        softAssert.assertAll();
}
}
