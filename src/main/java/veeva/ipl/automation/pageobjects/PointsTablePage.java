package veeva.ipl.automation.pageobjects;

import veeva.ipl.automation.abstractcomponents.AbstractComponents;
import veeva.ipl.automation.locators.IPLPageSelectors;
import veeva.ipl.automation.webutils.WebUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * PointsTablePage handles interactions with the IPL points table.
 */
public class PointsTablePage extends AbstractComponents {
    WebUtils webUtils;

    /**
     * Constructor to initialize WebDriver and utilities.
     *
     * @param driver WebDriver instance
     */
    public PointsTablePage(WebDriver driver) {
        super(driver);
        webUtils = new WebUtils(driver);
    }

    By pointsTable = By.cssSelector(IPLPageSelectors.pointsTable);
    By teams = By.cssSelector(IPLPageSelectors.teams);
    By matchesPlayed = By.cssSelector(IPLPageSelectors.matches);
    By points = By.cssSelector(IPLPageSelectors.points);

    /**
     * Retrieves the name of the top-ranked team.
     *
     * @return top team name
     */
    public String getTopTeam() {
        return driver.findElements(teams).get(0).getText();
    }

    /**
     * Retrieves matches played by top team.
     *
     * @return matches played
     */
    public String getActualMatchesPlayed() {
        return driver.findElements(matchesPlayed).get(0).getText();
    }

    /**
     * Retrieves points of the top team.
     *
     * @return points scored
     */
    public String getActualPoints() {
        return driver.findElements(points).get(0).getText();
    }

    /**
     * Navigates to points table section and waits for it to load.
     *
     * @param header navigation menu text
     */
    @Override
    public void execute(String header) {
        super.execute(header);
        webUtils.waitForElementToAppear(pointsTable);
    }
}