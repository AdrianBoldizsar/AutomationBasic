package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class WebTablePage extends BasePage {

    //locatori specifici;
    private By pageTitle = By.xpath("//h1[@class]");
    private By tableRowList = By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr -odd' or @class='rt-tr -even']");
    private By addNewRecordButton = By.id("addNewRecordButton");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailAddressField = By.id("userEmail");
    private By ageField = By.id("age");
    private By salaryField = By.id("salary");
    private By departmentField = By.id("department");
    private By submitButtonField = By.id("submit");

    public int initialTableSize = 0;
    String firstName = "Firicel";
    String lastName = "Celentano";
    String email = "test@gmail.com";
    String age = "25";
    String salary = "100000";
    String department = "Testing";


    public WebTablePage(WebDriver driver) {
        super(driver);
    }

    @Override

    public void isPageLoaded() {
        Assert.assertEquals(elementMethods.getTextFromElement(pageTitle), "Web Tables",
                "Page is not loaded properly");
    }

    public void webTablePageFlow(){
        getTableSize();
        clickToAddNewRecord();
        fillFormValues();
        validateThatNewRecordsAreAddedProperly();
    }

    public int getTableSize() {
        initialTableSize = elementMethods.getElements(tableRowList).size();
        System.out.println("Numarul initial de randuri in tabel este: " + initialTableSize);
        return initialTableSize;
    }

    //facem o metoda noua care adauga rand nou in tabel, dand click pe ADD;
    public void clickToAddNewRecord() {
        elementMethods.clickElement(addNewRecordButton);
    }

    //facem o metoda care sa completeze toate campurile din formular;
    public void fillFormValues() {
        elementMethods.fillElement(firstNameField, firstName);
        elementMethods.fillElement(lastNameField, lastName);
        elementMethods.fillElement(emailAddressField, email);
        elementMethods.fillElement(ageField, age);
        elementMethods.fillElement(salaryField,salary);
        elementMethods.fillElement(departmentField, department);
        elementMethods.clickElement(submitButtonField);
    }

    //facem o metoda care sa valideze ca am adaugat o intrare noua in tabel si sa verifice valorile pe care le-am dat;
    public void validateThatNewRecordsAreAddedProperly() {
        Assert.assertTrue(elementMethods.getElements(tableRowList).size() > initialTableSize, "There are no " +
                "new entries in the table!, initial table size: " +
                initialTableSize + " is the same with actual table size: " + elementMethods.getElements(tableRowList).size());
        String actualTableValues = elementMethods.getElements(tableRowList).get(elementMethods.getElements(tableRowList).
                size() - 1).getText();
        System.out.println("New record values are: " + actualTableValues);
        Assert.assertTrue(actualTableValues.contains(firstName), "First name value is not correct. Expected first Name: "
                + firstName);
        Assert.assertTrue(actualTableValues.contains(lastName), "Last name value is not correct. Expected last Name: "
                + lastName);
        Assert.assertTrue(actualTableValues.contains(email), "Email value is not correct. Expected email: " + email);
        Assert.assertTrue(actualTableValues.contains(age), "Age value is not correct. Expected age: " + age);
        Assert.assertTrue(actualTableValues.contains(salary), "Salary value is not correct. Expected salary: " + salary);
        Assert.assertTrue(actualTableValues.contains(department), "Department value is not correct. Expected department: "
                + department);
    }
}
