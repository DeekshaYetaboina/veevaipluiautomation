package com.automation.testutils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TestUtils {
    /**
     * Captures a screenshot of the current browser window.
     *
     * The screenshot is saved in the "Reports" directory and the
     * relative file name is returned for report attachment.
     *
     * @param driver the WebDriver instance
     * @param testName the name of the test (used as file name)
     * @return the relative path of the saved screenshot
     * @throws IOException if there is an issue saving the file
     */
    public static String takeScreenshot(WebDriver driver, String testName) throws IOException {

        TakesScreenshot t = (TakesScreenshot) driver;
        File source = t.getScreenshotAs(OutputType.FILE);

        File dir = new File(System.getProperty("user.dir") + "/target/reports");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = System.getProperty("user.dir") + "/target/reports/" + testName + ".png";
        FileUtils.copyFile(source, new File(path));

        return testName + ".png";
    }
}
