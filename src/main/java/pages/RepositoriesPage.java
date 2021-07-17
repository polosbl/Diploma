package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RepositoriesPage extends HeaderPage {

    public RepositoriesPage(WebDriver driver) {
        super(driver);
    }

    public static final String SUITE_HEADER = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'suite-header')]";
    public static final String EDIT_SUITE_BUTTON = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'suite-header')]//*[contains(@class,'fa-pencil-alt')]";
    public static final String DELETE_SUITE_BUTTON = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'suite-header')]//*[contains(@class,'fa-trash')]";
    public static final String CREATE_TEST_CASE_TO_SUITE_BUTTON = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'suite root')]//*[contains(@class,'quick-button')]";
    public static final String NEW_TEST_CASE_TITLE_FIELD = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'suite root')]//*[contains(@class,'quick-input')]";
    public static final String TEST_CASE_CHECKBOX = "//*[contains(text(),'%s')]/ancestor::*[@class='cases-container']//*[@class='custom-control-indicator']";
    public static final String CREATED_TEST_CASE_NAME = "(//*[contains(text(),'%s')]/ancestor::*[@class='suite root']//*[@class='case-row-title'])[last()]";

    @FindBy(xpath = "//*[@class='controls-block']//*[contains(@class,'fa-trash')]")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[@class='sub-menu ']//*[@class='header']")
    public WebElement projectName;

    @FindBy(xpath = "//*[@class='sub-menu ']//*[contains(text(), 'Settings')]")
    public WebElement settingsMenuItem;

    @FindBy(xpath = "//*[@id='inputTitle']")
    public WebElement projectNameField;

    @FindBy(xpath = "//*[@id='inputCode']")
    public WebElement projectCodeField;

    @FindBy(xpath = "//*[@id='inputDescription']")
    public WebElement projectDescriptionField;

    @FindBy(xpath = "//*[@id='private-access-type']")
    public WebElement privateAccessRadioButton;

    @FindBy(xpath = "//*[@id='public-access-type']")
    public WebElement publicAccessRadioButton;

    @FindBy(xpath = "//*[@class='col text-start']//*[contains(text(), 'Update')]")
    public WebElement updateSettingsButton;

    @FindBy(xpath = "//*[@class='col text-start']//*[contains(text(), 'Delete')]")
    public WebElement deleteProjectButton;

    @FindBy(xpath = "//*[contains(@class,'btn-primary') and contains(text(),'suite')]")
    public WebElement createNewSuiteButtonFromEmptyRepository;

    @FindBy(xpath = "//*[contains(@class,'btn-primary') and contains(text(),'case')]")
    public WebElement createNewTestCaseButtonFromEmpty;

    @FindBy(xpath = "//*[@id='create-suite-button']")
    public WebElement createNewSuiteButton;

    @FindBy(xpath = "//*[@id='create-case-button']")
    public WebElement createNewTestCaseButton;

    @FindBy(xpath = "(//*[@class='suite-header-title'])[last()]")
    public WebElement createdSuiteName;

    /**
     * @param name
     * @return
     */
    @Step ("Creating new name to project - {name}")
    public RepositoriesPage editProjectName(String name) {
        settingsMenuItem.click();
        projectNameField.clear();
        projectNameField.sendKeys(name);
        updateSettingsButton.click();
        return this;
    }

    /**
     * @return
     */
    @Step ("Deleting project using settings menu")
    public DeleteProjectPage deleteProjectFromRepositories() {
        settingsMenuItem.click();
        deleteProjectButton.click();
        return new DeleteProjectPage(driver);
    }

    /**
     * @return
     */
    public String getProjectName() {
        return projectName.getText();
    }

    /**
     * @return
     */
    public CreateSuiteModalPage clickCreateNewSuiteButton() {
        createNewSuiteButtonFromEmptyRepository.click();
        return new CreateSuiteModalPage(driver);
    }

    /**
     * @param name
     * @return
     */
    @Step ("Opening suite settings")
    public CreateSuiteModalPage editSuite(String name) {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(String.format(SUITE_HEADER, name)))).perform();
        driver.findElement(By.xpath(String.format(EDIT_SUITE_BUTTON, name))).click();
        return new CreateSuiteModalPage(driver);
    }

    /**
     * @param name
     * @return
     */
    @Step ("Deleting suite {name}")
    public DeleteSuiteModalPage deleteSuite(String name) {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(String.format(SUITE_HEADER, name)))).perform();
        driver.findElement(By.xpath(String.format(DELETE_SUITE_BUTTON, name))).click();
        return new DeleteSuiteModalPage(driver);
    }

    /**
     * @param name
     * @return
     */
    @Step ("Creating new test case {name}")
    public RepositoriesPage createNewTestCase(String name) {
        driver.findElement(By.xpath(String.format(CREATE_TEST_CASE_TO_SUITE_BUTTON, name))).click();
        driver.findElement(By.xpath(String.format(NEW_TEST_CASE_TITLE_FIELD, name))).sendKeys(name + Keys.ENTER);
        return this;
    }

    /**
     * @param name
     * @return
     */
    @Step ("Deleting test case {name}")
    public DeleteTestCaseModalPage deleteTestCase(String name) {
        driver.findElement(By.xpath(String.format(TEST_CASE_CHECKBOX, name))).click();
        deleteButton.click();
        return new DeleteTestCaseModalPage(driver);
    }

    /**
     * @return
     */
    public String getCreatedSuiteName() {
        return createdSuiteName.getText();
    }

    /**
     * @param suiteName
     * @return
     */
    public boolean isSuiteDeleted(String suiteName) {
        return driver.findElements(By.xpath(String.format(SUITE_HEADER,suiteName))).isEmpty();
    }

    /**
     * @param suiteName
     * @return
     */
    public String getCreatedTestCaseNameFromSuite(String suiteName) {
        return driver.findElement(By.xpath(String.format(CREATED_TEST_CASE_NAME,suiteName))).getText();
    }

    /**
     * @param suiteName
     * @return
     */
    public boolean isTestCaseDeleted(String suiteName) {
        return driver.findElements(By.xpath(String.format(CREATED_TEST_CASE_NAME,suiteName))).isEmpty();
    }

}
