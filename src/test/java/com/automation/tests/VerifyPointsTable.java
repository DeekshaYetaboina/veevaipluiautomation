package com.automation.tests;

import com.automation.pageobjects.PointsTablePage;
import com.automation.testutils.TestDataUtils;
import com.automation.tests.testcomponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
/**
 * VerifyPointsTable class validates the points table data
 * displayed on the IPL website.
 *
 * This test verifies:
 * 1. Points scored by the top team
 * 2. Matches played by the top team
 *
 * The expected values are fetched from a JSON file,
 * making this a data-driven test.
 */
public class VerifyPointsTable extends BaseTest {
    /**
     * Utility class used to fetch test data from JSON files.
     */
    TestDataUtils  testDataUtils = new TestDataUtils();
    /**
     * Page object representing the points table UI.
     */
    PointsTablePage pointsTablePage;
    /**
     * Initializes the PointsTable page object before each test.
     */
    @BeforeMethod
    public void initialize(){
        pointsTablePage = new PointsTablePage(driver);
    }
    /**
     * Verifies that the points and matches played for the top team
     * match the expected values from the JSON file.
     *
     * Steps:
     * 1. Fetch test data (header, expected points, matches played) from JSON
     * 2. Navigate to the points table page using header
     * 3. Retrieve actual values from UI
     * 4. Compare actual and expected values
     *
     * @throws IOException if there is an issue reading JSON data
     */
    @Test
    public void verifyPointsTable() throws IOException {

        String path = testDataUtils.getPath("ExpectedPointsTable");
        String header = testDataUtils.getJsonString(path,"header");
        pointsTablePage.execute(header);
        String topTeam = pointsTablePage.getTopTeam();
        String expectedPoints= testDataUtils.getJsonString(path,"points");
        String expectedMatchesPlayed= testDataUtils.getJsonString(path,"matchesPlayed");
        String actualPoints = pointsTablePage.getActualPoints();
        String actualMatchesPlayed = pointsTablePage.getActualMatchesPlayed();

        System.out.println("Top Team: " + topTeam);
        Assert.assertEquals(actualPoints, expectedPoints,"The actual and expected points don't match");
        Assert.assertEquals(actualMatchesPlayed, expectedMatchesPlayed,"the actual matches played and expected don't match");

    }
}
