package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class WindowsPage extends BasePage {

    //locatori specifici paginii;
    private By pageTitle = By.xpath("//h1[@class]");
    private By newTabButton = By.id("tabButton");
    private By windowsTextValidationLocator = By.id("sampleHeading");
    private By newWindowButton = By.id("windowButton");
    private By newWindowMessageButton = By.id("messageWindowButton");

    String expectedText = "This is a sample page";

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that WindowsPage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Browser Windows",
                "Page is not loaded properly");
    }

    public void interactWithNewTab(String expectedTextValue) {
        logInfo(INFO_STEP,"User interacts with new tab");
        logInfo(INFO_STEP,"User clicks on new tab button");
        elementMethods.clickElement(newTabButton);
//        driver.findElement(newTabButton).click();
        logInfo(INFO_STEP,"User switch to new tab");
        windowsMethods.switchToWindow(1);
//        // declaram o lista de ferestre: noi avem 2 ferestre;
//        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
//        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
//        driver.switchTo().window(windowsList.get(1));
//        //identificam elementul pe care este textul;
        logInfo(PASS_STEP,"Validate that the text from new tab is the one that we expect: " + expectedTextValue);
        Assert.assertEquals(elementMethods.getTextFromElement(windowsTextValidationLocator), expectedTextValue,
                "Text is not displayed properly");
        logInfo(INFO_STEP,"User closes the new tab");
        windowsMethods.closeWindowOrTab();
//        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
//        driver.close();
        logInfo(INFO_STEP,"User switch to default page");
        windowsMethods.switchToWindow(0);
//        //acum trebuie sa schimbam focusul;
//        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewWindow(String expectedTextValue) {
        logInfo(INFO_STEP,"User interacts with new window");
        logInfo(INFO_STEP,"User clicks on new window button");
        elementMethods.clickElement(newWindowButton);
//        driver.findElement(newWindowButton).click();
        windowsMethods.switchToWindow(1);
        logInfo(PASS_STEP,"Validate that the text from new window is the one that we expect: " + expectedTextValue);
//        // declaram o lista de ferestre: noi avem 2 ferestre;
//        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
//        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
//        driver.switchTo().window(windowsList.get(1));
        Assert.assertEquals(elementMethods.getTextFromElement(windowsTextValidationLocator), expectedTextValue,
                "Text is not displayed properly");
        logInfo(INFO_STEP,"User closes the new window");
        windowsMethods.closeWindowOrTab();
//        //driver close iti inchide doar tab-ul si driver.quit inchide intreaga instanta, toate taburile;
//        driver.close();
        logInfo(INFO_STEP,"User switch to default page");
        windowsMethods.switchToWindow(0);
//        //acum trebuie sa schimbam focusul;
//        driver.switchTo().window(windowsList.get(0));
    }

    public void interactWithNewMessageWindow() {
        logInfo(INFO_STEP,"User interacts with new message window");
        logInfo(INFO_STEP,"User clicks on new message window button");
        elementMethods.clickElement(newWindowMessageButton);
        windowsMethods.newMessageWindow();
        windowsMethods.switchToWindow(1);
        logInfo(INFO_STEP,"User closes the new window");
        windowsMethods.closeWindowOrTab();
        logInfo(INFO_STEP,"User switch to default page");
        windowsMethods.switchToWindow(0);
//        //declaram o lista de ferestre: noi avem 2 ferestre;
//        List<String> windowsList = new ArrayList<>(driver.getWindowHandles());
//        //noua ne trebuie focus pe al doilea tab, care are indexul 1;
//        if (windowsList.size() > 1) {
//            System.out.println("A new window is successfully opened");
//        } else {
//            System.out.println("New window cannot be opened");
//        }
//        driver.switchTo().window(windowsList.get(1));
//        driver.close();
//        driver.switchTo().window(windowsList.get(0));
    }
}
