package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class AlertsPage extends BasePage{
    private By pageTitle= By.xpath("//h1[@class]");
    private By firstAlertButton= By.id("alertButton");
    private By timerAlertButton= By.id("timerAlertButton");
    private By confirmAlertButton=By.id("confirmButton");
    private By alertResultText=By.id("confirmResult");
    private By confirmPromptButton=By.id("promtButton");
    private By promptResult=By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that AlertPage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle),"Alerts","Page is not loaded properly");
    }

    public void interactWithFirstAlert() {
        logInfo(INFO_STEP,"User interact with first alert");
        logInfo(INFO_STEP,"User clicks first alert button");
        elementMethods.clickElement(firstAlertButton);
        logInfo(INFO_STEP,"User accepts alert");
        alertsMethods.alertOk();
//        Alert FirstAlert = driver.switchTo().alert();
//        FirstAlert.accept();
    }

    //facem o metoda care sa interactioneze cu prima alerta;
    public void interactWithTimerAlert() {
        logInfo(INFO_STEP,"User interact with timer alert");
        logInfo(INFO_STEP,"User clicks timer alert button");
        elementMethods.clickElement(timerAlertButton);
        logInfo(INFO_STEP,"User accepts timer alert");
        alertsMethods.timerAlert();
//        //Inainte sa schimbam focusul pe alerta, trebuie sa punem un wait explicit;
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.alertIsPresent());
//        Alert timerAlert = driver.switchTo().alert();
//        timerAlert.accept();
    }

    public void interactWithConfirmAlert(String alertValue) {
        logInfo(INFO_STEP,"User interact with confirm alert");
        logInfo(INFO_STEP,"User clicks confirm alert button");
        elementMethods.clickElement(confirmAlertButton);
        alertsMethods.confirmAlert(alertValue);
        logInfo(INFO_STEP,"User choose: " + alertValue);
        logInfo(INFO_STEP,"Validate that the value we choose: " + alertValue + "is correctly displayed");
        Assert.assertTrue(elementMethods.getTextFromElement(alertResultText).contains(alertValue), "You didn't " +
                "select Ok. You selected: " + elementMethods.getTextFromElement(alertResultText));

//        if (alertValue.equals("OK")) {
//            confirmAlert.accept();
//            Assert.assertTrue(elementMethods.getElement(alertResultText).getText().contains(alertValue), "You didn't " +
//                    "select Ok. You selected: " + elementMethods.getElement(alertResultText).getText());
//        }
//        if (alertValue.equals("Cancel")) {
//            confirmAlert.dismiss();
//            Assert.assertTrue(elementMethods.getElement(alertResultText).getText().contains(alertValue), "You didn't "
//                    + "select Cancel. You selected: " + elementMethods.getElement(alertResultText).getText());
//        }
    }

    public void interactWithPromptBox(String alertValue){
        logInfo(INFO_STEP,"User interact with prompt box");
        logInfo(INFO_STEP,"User clicks prompt box alert button");
        elementMethods.clickElement(confirmPromptButton);
        alertsMethods.promptAlert(alertValue);
        logInfo(INFO_STEP,"User choose: " + alertValue);
        logInfo(INFO_STEP,"Validate that the value we choose: " + alertValue + "is correctly displayed");
//        Alert promptAlert = driver.switchTo().alert();
//        //introduce numele meu in casuta de prompt;
//        promptAlert.sendKeys(alertValue);
//        //apoi apasa pe butonul ok;
//        promptAlert.accept();
        Assert.assertTrue(elementMethods.getTextFromElement(promptResult).contains(alertValue),"You didn't enter the " +
                "right name. In that box " + elementMethods.getTextFromElement(promptResult));
    }
}

