package pages;

import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static extentUtility.ExtentHelper.logInfo;
import static extentUtility.ReportEventType.INFO_STEP;
import static extentUtility.ReportEventType.PASS_STEP;

public class PracticeFormPage extends BasePage {


    //Locatori specifici
    private By pageTitle = By.xpath("//h1[@class]");
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
    private By sportHobbyElement = By.xpath("//label[@for='hobbies-checkbox-1']");
    private By readHobbyElement = By.xpath("//label[@for='hobbies-checkbox-2']");
    private By musicHobbyElement = By.xpath("//label[@for='hobbies-checkbox-3']");
    private By uploadFileElement = By.id("uploadPicture");
    private By addressField = By.id("currentAddress");
    private By stateInputElement = By.id("react-select-3-input");
    private By cityInputElement = By.id("react-select-4-input");
    private By submitButton = By.id("submit");
    private By submitTableKeysLocator = By.xpath("//tbody//td[1]");
    private By submitTableValuesLocator = By.xpath("//tbody//td[2]");

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void isPageLoaded() {
        logInfo(PASS_STEP,"Validate that PracticeFormPage is loaded properly");
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Practice Form",
                "Page is not loaded properly");
    }

    public void fillEntireForm(Map<String, Object> practiceFormValue) {
        fillFirstName((String) practiceFormValue.get("firstNameText"));
        fillLastName((String) practiceFormValue.get("lastNameText"));
        fillEmail((String) practiceFormValue.get("emailFieldText"));
        elementMethods.scrollPageDown("500");
        chooseGender((String) practiceFormValue.get("genderValueText"));
        fillPhone((String) practiceFormValue.get("mobilePhoneText"));
        fillDateOfBirth((String) practiceFormValue.get("monthValueText"),
                (String) practiceFormValue.get("yearValueText"), (String) practiceFormValue.get("dayValueText"));
        elementMethods.scrollPageDown("200");
        chooseSubject((String) practiceFormValue.get("mathSubjectText"),
                (String) practiceFormValue.get("physicsSubjectText"));
        elementMethods.scrollPageDown("200");
        selectHobbies((String) practiceFormValue.get("sportValueText"),
                (String) practiceFormValue.get("readValueText"), (String) practiceFormValue.get("musicValueText"));
        uploadImage((String) practiceFormValue.get("pictureFileText"));
        fillAddress((String) practiceFormValue.get("addressValueText"));
        fillStateAndCity((String) practiceFormValue.get("stateValueText"), (String) practiceFormValue.get("cityValueText"));
        clickSubmitButton();
    }

    public void fillFirstName(String firstNameValue) {
        logInfo(INFO_STEP,"User filled first name field with: " + firstNameValue);
        elementMethods.fillElement(firstNameField, firstNameValue);
    }

    public void fillLastName(String lastNameValue) {
        logInfo(INFO_STEP,"User filled last name field with: " + lastNameValue);
        elementMethods.fillElement(lastNameField, lastNameValue);
    }

    public void fillEmail(String emailValue) {
        logInfo(INFO_STEP,"User filled email field with: " + emailValue);
        elementMethods.fillElement(emailField, emailValue);
    }

    public void chooseGender(String genderValue) {
        logInfo(INFO_STEP,"User chooses gender: " + genderValue);
        List<WebElement> genderList = List.of(driver.findElement(genderMale),
                driver.findElement(genderFemale), driver.findElement(genderOther)); //un nou mod de a crea o lista;
        elementMethods.chooseElementFromListByText(genderList, genderValue);
    }

    public void fillPhone(String mobilePhoneValue) {
        logInfo(INFO_STEP,"User filled phone number field with: " + mobilePhoneValue);
        elementMethods.fillElement(mobilePhoneField, mobilePhoneValue);
    }

    public void fillDateOfBirth(String monthValue, String yearValue, String dayValue) {
        logInfo(INFO_STEP,"User filled date of birth field with: " + dayValue+ " " +monthValue+ " "+ yearValue);
        elementMethods.clickElement(dateOfBirthInput);
        elementMethods.selectElementByText(monthOfBirthElement, monthValue);
        elementMethods.selectElementByText(yearOfBirthElement, yearValue);
        elementMethods.chooseElementFromListByText(dayOfBirthList, dayValue);
    }

    public void chooseSubject(String mathSubjectValue, String physicsSubjectValue) {
        logInfo(INFO_STEP,"User filled subject field with: " + mathSubjectValue + " and " + physicsSubjectValue);
        elementMethods.fillElement(subjectInputElement, mathSubjectValue);
        elementMethods.fillElement(subjectInputElement, Keys.ENTER);
        elementMethods.fillElement(subjectInputElement, physicsSubjectValue);
        elementMethods.fillElement(subjectInputElement, Keys.ENTER);
    }

    public void selectHobbies(String sportValue, String readValue, String musicValue) {
        logInfo(INFO_STEP,"User filled chooses the following hobbies: " + sportValue+ " and " +readValue+ " and "+ musicValue);
        List<WebElement> hobbiesList = List.of(driver.findElement(sportHobbyElement),
                driver.findElement(readHobbyElement), driver.findElement(musicHobbyElement));
        List<String> hobbyValueTextList = List.of(sportValue, readValue, musicValue);
        for (String hobby : hobbyValueTextList) {
            elementMethods.chooseElementFromListByText(hobbiesList, hobby);
        }
    }

    public void uploadImage(String pictureFileValue) {
        logInfo(INFO_STEP,"User uploaded the following picture: " + pictureFileValue);
        elementMethods.uploadDocument(uploadFileElement, pictureFileValue);
    }

    public void fillAddress(String addressValue) {
        logInfo(INFO_STEP,"User filled address field: " + addressValue);
        elementMethods.fillElement(addressField, addressValue);
    }

    public void fillStateAndCity(String stateValue, String cityValue) {
        logInfo(INFO_STEP,"User chooses city: " + cityValue + " and state: " + stateValue);
        elementMethods.fillElement(stateInputElement, stateValue);
        elementMethods.fillElement(stateInputElement, Keys.ENTER);
        elementMethods.fillElement(cityInputElement, cityValue);
        elementMethods.fillElement(cityInputElement, Keys.ENTER);
    }

    public void clickSubmitButton() {
        logInfo(INFO_STEP,"User clicks submit button");
        elementMethods.clickElement(submitButton);
    }

    public HashMap<String, String> getExpectedValues(Map<String, Object> practiceFormData) {
        HashMap<String, String> expectedValues = new HashMap<>();
        expectedValues.put("Student Name", practiceFormData.get("firstNameText") + " " + practiceFormData.get("lastNameText"));
        expectedValues.put("Student Email", (String) practiceFormData.get("emailFieldText"));
        expectedValues.put("Gender", (String) practiceFormData.get("genderValueText"));
        expectedValues.put("Mobile", (String) practiceFormData.get("mobilePhoneText"));
        expectedValues.put("Date of Birth", practiceFormData.get("dayValueText") + " " + practiceFormData.get("monthValueText") +
                "," + practiceFormData.get("yearValueText"));
        expectedValues.put("Subjects", practiceFormData.get("mathSubjectText") + ", " + practiceFormData.get("physicsSubjectText"));
        expectedValues.put("Hobbies", practiceFormData.get("sportValueText") + ", " + practiceFormData.get("readValueText") +
                ", " + practiceFormData.get("musicValueText"));
        expectedValues.put("Picture", (String) practiceFormData.get("pictureFileText"));
        expectedValues.put("Address", (String) practiceFormData.get("addressValueText"));
        expectedValues.put("State and City", practiceFormData.get("stateValueText") + " " + practiceFormData.get("cityValueText"));
        return expectedValues;
    }

    public HashMap<String, String> getActualValues() {
        HashMap<String, String> actualValues = new HashMap<>();
        //indiferent ca alegem submitTablekeys sau values, parcurge oricum ambele liste;
        for (int i = 0; i < elementMethods.getElements(submitTableKeysLocator).size(); i++) {
            actualValues.put(elementMethods.getElements(submitTableKeysLocator).get(i).getText(), elementMethods.
                    getElements(submitTableValuesLocator).get(i).getText());
            //parcurgem fiecare valoare/cheie si luam textul de pe ea si o adagam in HashMap;
        }
        return actualValues;
    }

    public void validateThatExpectedValuesEqualActualValues(Map<String, Object> practiceFormData) {
        logInfo(PASS_STEP,"Validate that input data are the displayed properly in the verification table" + getActualValues());
        Assert.assertEquals(getActualValues(), getExpectedValues(practiceFormData), "Actual Values: " + getActualValues() +
                "are not equal/are not the same with the expected values: " + getExpectedValues(practiceFormData));
    }

}
