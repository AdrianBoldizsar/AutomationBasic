package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class FramesPage extends BasePage {

    //locatori specifici paginii;
    private By pageTitle= By.xpath("//h1[@class]");
    private By frameOneLocator= By.id("frame1");
    private By frameTextValueLocator= By.id("sampleHeading");
    private By frameTwoLocator= By.id("frame2");

    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that FramesPage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle),"Frames","Page is not loaded properly");
    }

    public void interactWithFrameOne(String expectedTextValue){
        logInfo(INFO_STEP,"User interact with first frame");
        logInfo(INFO_STEP,"User switched to first frame");
        //schimbare de focus pe frame(prima pagina);
        frameMethods.switchToFrame(frameOneLocator);
        logInfo(PASS_STEP,"Validate that the text from first frame is the one that we expect: " + expectedTextValue);
//        driver.switchTo().frame(driver.findElement(frameOneLocator));
        Assert.assertEquals(elementMethods.getTextFromElement(frameTextValueLocator), expectedTextValue, "Text is " +
                "not displayed properly");
        System.out.println("Frame one text is: " + elementMethods.getTextFromElement(frameTextValueLocator));
//        driver.switchTo().defaultContent(); //shimbam focusul pe pagina initiala;
        frameMethods.switchToDefaultPage();
    }
    public void interactWithFrameTwo(String expectedTextValue){
        logInfo(INFO_STEP,"User interact with second frame");
        logInfo(INFO_STEP,"User switched to second frame");
        //schimbare de focus pe frame(prima pagina);
        frameMethods.switchToFrame(frameTwoLocator);
        logInfo(PASS_STEP,"Validate that the text from second frame is the one that we expect: " + expectedTextValue);
        Assert.assertEquals(elementMethods.getTextFromElement(frameTextValueLocator), expectedTextValue, "Text is " +
                "not displayed properly");
        System.out.println("Frame two text is: " + elementMethods.getTextFromElement(frameTextValueLocator));
//        driver.switchTo().defaultContent(); //shimbam focusul pe pagina initiala;
        frameMethods.switchToDefaultPage();
    }
}
