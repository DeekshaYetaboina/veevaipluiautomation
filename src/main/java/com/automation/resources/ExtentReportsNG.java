package com.automation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * ExtentReportsNG class configures and provides a singleton
 * instance of ExtentReports for reporting.
 */
public class ExtentReportsNG {
    static ExtentReports extent;

    /**
     * Creates and returns ExtentReports instance.
     * @return ExtentReports instance
     */
    public static ExtentReports getReport() {

        if (extent == null) {

            String path = System.getProperty("user.dir") + "//reports//index.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Precision UI Hybrid Results");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Deeksha");
        }

        return extent;
    }
}