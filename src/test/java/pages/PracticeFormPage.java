package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class PracticeFormPage extends BasePage{


    //Locatori specifici
    private By pageTitle= By.xpath("//h1[@class]");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderMale = By.xpath("//label[@for='gender-radio-1']");
    private By genderFemale = By.xpath("//label[@for='gender-radio-2']");
    private By genderOther = By.xpath("//label[@for='gender-radio-3']");
    private By mobilePhoneField = By.id("userNumber");
    private By dateOfBirthInput = By.id("dateOfBirthInput");
    private By monthOfBirthElement = By.xpath("//select[@class='react-datepicker__month-select']");
    private By yearOfBirthElement = By.xpath("//select[@class='react-datepicker__year-select']");
    private By dayOfBirthList = By.xpath("//div[contains(@class,'react-datepicker__day')]");
    private By subjectInputElement = By.id("subjectsInput");
    private By sportHobbyElement= By.xpath("//label[@for='hobbies-checkbox-1']");
    private By readHobbyElement= By.xpath("//label[@for='hobbies-checkbox-2']");
    private By musicHobbyElement= By.xpath("//label[@for='hobbies-checkbox-3']");
    private By uploadFileElement = By.id("uploadPicture");
    private By addressField = By.id("currentAddress");
    private By stateInputElement = By.id("react-select-3-input");
    private By cityInputElement = By.id("react-select-4-input");
    private By submitButton = By.id("submit");

    String firstNameText = "Mario";
    String lastNameText = "Luigi";
    String emailFieldText = "test@gmail.com";
    String genderValueText = "Male";
    String mobilePhoneText = "0756950504";
    String monthValueText = "April";
    String yearValueText = "2025";
    String dayValueText = "14";
    String mathSubjectText = "Maths";
    String physicsSubjectText = "Physics";
    String sportValueText = "Sports";
    String readValueText = "Reading";
    String musicValueText = "Music";
    String pictureFileText = "TestImage.jpg";
    String pictureFilePaths = "src/test/resources/picture/" + pictureFileText;
    String addressValueText = "Strada Sigismund Toduta, nr. 1, ap. 5";
    String stateValueText = "NCR"; //cream variabila locala;
    String cityValueText = "Delhi"; //cream variabila locala;

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void isPageLoaded() {
        Assert.assertEquals(driver.findElement(pageTitle).getText(),"Practice Form","Page is not loaded properly");
    }

    public void fillEntireForm (){
        fillFirstName();
        fillLastName();
        fillEmail();
        scrollPageDown("500");
        chooseGender();
        fillPhone();
        fillDateOfBirth();
        scrollPageDown("200");
        selectHobbies();
        scrollPageDown("200");
        chooseSubject();
        uploadImage();
        fillAddress();
        fillStateAndCity();
        clickSubmitButton();

    }

    public void fillFirstName(){
        driver.findElement(firstNameField).sendKeys(firstNameText);
    }

    public void fillLastName(){
        driver.findElement(lastNameField).sendKeys(lastNameText);
    }

    public void fillEmail(){
        driver.findElement(emailField).sendKeys(emailFieldText);
    }

    public void scrollPageDown(String scrollValue) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + scrollValue + ")");
    }

    public void chooseGender(){
        List<WebElement> genderList = List.of(driver.findElement(genderMale),
                driver.findElement(genderFemale), driver.findElement(genderOther)); //un nou mod de a crea o lista;
        for (int i = 0; i < genderList.size(); i++) {
            if (genderList.get(i).getText().equals(genderValueText)) {
                genderList.get(i).click();
                break;
            }
        }
    }

    public void fillPhone (){
        driver.findElement(mobilePhoneField).sendKeys(mobilePhoneText);
    }

    public void fillDateOfBirth(){
        driver.findElement(dateOfBirthInput).click();

        Select selectMonth = new Select(driver.findElement(monthOfBirthElement));
        selectMonth.selectByVisibleText(monthValueText);

        Select selectYear = new Select(driver.findElement(yearOfBirthElement));
        selectYear.selectByVisibleText(yearValueText);

        for (int i = 0; i < driver.findElements(dayOfBirthList).size(); i++) {
            if (driver.findElements(dayOfBirthList).get(i).getText().equals(dayValueText)) {
                driver.findElements(dayOfBirthList).get(i).click();
                break;
            }
        }
    }

    public void chooseSubject(){
        driver.findElement(subjectInputElement).sendKeys(mathSubjectText);
        driver.findElement(subjectInputElement).sendKeys(Keys.ENTER);
        driver.findElement(subjectInputElement).sendKeys(physicsSubjectText);
        driver.findElement(subjectInputElement).sendKeys(Keys.ENTER);
    }

    public void selectHobbies(){
        List<WebElement> hobbiesList = List.of(driver.findElement(sportHobbyElement),
                driver.findElement(readHobbyElement), driver.findElement(musicHobbyElement));
        List<String> hobbyValueTextList = List.of(sportValueText, readValueText, musicValueText);
        for (String hobby : hobbyValueTextList) {
            for (int i = 0; i < hobbiesList.size(); i++) {
                if (hobbiesList.get(i).getText().equals(hobby)) {
                    hobbiesList.get(i).click();
                }
            }
        }
    }

    public void uploadImage(){
        File file = new File(pictureFilePaths);
        driver.findElement(uploadFileElement).sendKeys(file.getAbsolutePath());
    }

    public void fillAddress(){
        driver.findElement(addressField).sendKeys(addressValueText);
    }

    public void fillStateAndCity(){
        driver.findElement(stateInputElement).sendKeys(stateValueText);
        driver.findElement(stateInputElement).sendKeys(Keys.ENTER);
        driver.findElement(cityInputElement).sendKeys(cityValueText);
        driver.findElement(cityInputElement).sendKeys(Keys.ENTER);
    }

    public void clickSubmitButton(){
        driver.findElement(submitButton).click();
    }

    //public void validateFormPage

}
