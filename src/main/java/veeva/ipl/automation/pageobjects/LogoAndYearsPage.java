package veeva.ipl.automation.pageobjects;

import veeva.ipl.automation.abstractcomponents.AbstractComponents;
import veeva.ipl.automation.webutils.SeleniumUtils;
import veeva.ipl.automation.locators.IPLPageSelectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * LogoAndYearsPage handles operations related to team logos
 * and winning years in the Teams section.
 * This class verifies:
 * - Team logo images
 * - Winning years displayed on hover
 */
public class LogoAndYearsPage extends AbstractComponents {
    SeleniumUtils seleniumUtils;

    /**
     * Constructor to initialize WebDriver and utilities.
     *
     * @param driver WebDriver instance passed from test class
     */
    public LogoAndYearsPage(WebDriver driver) {
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

    By teamLogo = By.cssSelector(IPLPageSelectors.teamLogo);
    By teamCards = By.cssSelector(IPLPageSelectors.teamCards);

    /**
     * Retrieves all team logo image URLs.
     *
     * @return list of logo URLs
     */
    public List<String> logos() {
        List<String> logoUrls = new ArrayList<>();
        List<WebElement> logos = driver.findElements(teamLogo);
        for (WebElement logo : logos) {
            logoUrls.add(logo.getAttribute("src"));
        }
        return logoUrls;
    }

    /**
     * Retrieves winning years for each team by hovering over cards.
     *
     * @return list of winning years
     */
    public List<String> getYears() {
        List<String> years = new ArrayList<>();
        List<WebElement> cards = driver.findElements(teamCards);
        for (WebElement card : cards) {
            seleniumUtils.moveToElement(card);
            WebElement hover = card.findElement(By.cssSelector(IPLPageSelectors.teamWinningYears));
            String year = hover.getText();
            years.add(year);
        }
        return years;
    }
}