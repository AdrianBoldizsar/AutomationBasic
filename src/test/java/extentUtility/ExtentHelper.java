package extentUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import static extentUtility.ReportEventType.*;

public class ExtentHelper {

    private static ExtentReports extentReports;

    private static ExtentTest extentTest;

    private static ExtentSparkReporter extentSparkReporter;

    private static final String pathProject = System.getProperty("user.dir") + "/target/extentReports/";

    private static final ConcurrentHashMap<String, ExtentTest> context = new ConcurrentHashMap<>();

    private static void createDirectory() {
        File directory = new File(pathProject);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static synchronized void initiateReport() {
        createDirectory();
        extentSparkReporter = new ExtentSparkReporter(pathProject + "ExtentReport.html");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("Test Report");
        extentSparkReporter.config().setReportName("Automated Tests Results");

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static synchronized void logInfo(String eventType, String message) {
        String threadName = Thread.currentThread().getName();
        if (eventType.equals(INFO_STEP)) {
            context.get(threadName).log(Status.INFO, message);
        }
        if (eventType.equals(PASS_STEP)) {
            context.get(threadName).log(Status.PASS, message);
        }
    }

    public static synchronized void createTest(String testName) {
        extentTest = extentReports.createTest(testName + " - report");
        context.put(Thread.currentThread().getName(), extentTest);
        logInfo(INFO_STEP, "--- Start test ---" + testName);
    }

    public static synchronized void finishTest(String testName) {
        logInfo(INFO_STEP, "--- Finish test ---" + testName);
    }

    private static String getScreenshot(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public static synchronized void logFailScreenshot(WebDriver driver, String message){
        context.get(Thread.currentThread().getName()).fail(message, MediaEntityBuilder
                .createScreenCaptureFromBase64String(getScreenshot(driver)).build());
    }

    public static synchronized void generateReport(){
        extentReports.flush();
    }
}
