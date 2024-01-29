package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        ExtentSparkReporter html = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReportFile.html");
        extent = new ExtentReports();
        extent.attachReporter(html);
        return extent;
    }
}
