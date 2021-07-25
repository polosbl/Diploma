package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonUtils;

@Log4j2
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

    @FindBy(xpath = "//*[@class='suite-block']")
    public WebElement suiteBlock;

    @FindBy(xpath = "//*[@id='inputCode']")
    public WebElement projectCodeField;

    @FindBy(xpath = "//*[@id='inputDescription']")
    public WebElement projectDescriptionField;

    @FindBy(xpath = "//*[@id='private-access-type']")
    public WebElement privateAccessRadioButton;

    @FindBy(xpath = "//*[@id='public-access-type']")
    public WebElement publicAccessRadioButton;

    @FindBy(xpath = "//*[contains(@class,'text-start')]//*[contains(text(), 'Update')]")
    public WebElement updateSettingsButton;

    @FindBy(xpath = "//*[contains(@class,'text-start')]//*[contains(text(), 'Delete')]")
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

    @FindBy(xpath = "//*[contains(text(),'Suite was successfully edited.')]")
    public WebElement successSuiteEditMessage;

    @FindBy(xpath = "//*[contains(text(),'Suite was successfully deleted.')]")
    public WebElement successSuiteDeletionMessage;

    @FindBy(xpath = "//*[contains(text(),'1 test case was successfully deleted')]")
    public WebElement successTestCaseDeletionMessage;

    @FindBy(xpath = "//*[contains(text(),'Project settings were successfully updated!')]")
    public WebElement successEditProjectSettingsMessage;

    /**
     * Edit project name repositories page.
     *
     * @param name the name
     * @return the repositories page
     */
    @Step("Creating new name to project - {name}")
    public RepositoriesPage editProjectName(String name) {
        log.info("Opening settings");
        settingsMenuItem.click();
        log.info("Clearing name field");
        projectNameField.clear();
        log.info("Typing new name");
        projectNameField.sendKeys(name);
        log.info("Updating project");
        updateSettingsButton.click();
        return this;
    }

    /**
     * Delete project from repositories delete project page.
     *
     * @return the delete project page
     */
    @Step("Deleting project using settings menu")
    public DeleteProjectPage deleteProjectFromRepositories() {
        log.info("Opening settings");
        settingsMenuItem.click();
        log.info("Clicking delete button");
        deleteProjectButton.click();
        return new DeleteProjectPage(driver);
    }

    /**
     * Gets project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        log.info("Getting project name form header");
        return projectName.getText();
    }

    public String getProjectNameAfterEdition() {
        waitForSuccessProjectSettingsEditMessage();
        log.info("Getting project name form header");
        return projectName.getText();
    }

    /**
     * Click create new suite button create suite modal page.
     *
     * @return the create suite modal page
     */
    public CreateSuiteModalPage clickCreateNewSuiteButton() {
        log.info("Clicking create new suite button");
        createNewSuiteButtonFromEmptyRepository.click();
        return new CreateSuiteModalPage(driver);
    }

    /**
     * Edit suite create suite modal page.
     *
     * @param name the name
     * @return the create suite modal page
     */
    @Step("Opening suite settings")
    public CreateSuiteModalPage editSuite(String name) {
        CommonUtils.hoverOverElement(driver, SUITE_HEADER, name);
        log.info("Clicking edit suite button");
        driver.findElement(By.xpath(String.format(EDIT_SUITE_BUTTON, name))).click();
        return new CreateSuiteModalPage(driver);
    }

    /**
     * Delete suite delete suite modal page.
     *
     * @param name the name
     * @return the delete suite modal page
     */
    @Step("Deleting suite {name}")
    public DeleteSuiteModalPage deleteSuite(String name) {
        CommonUtils.hoverOverElement(driver, SUITE_HEADER, name);
        log.info("Clicking delete suite button");
        driver.findElement(By.xpath(String.format(DELETE_SUITE_BUTTON, name))).click();
        return new DeleteSuiteModalPage(driver);
    }

    /**
     * Create new test case repositories page.
     *
     * @param name the name
     * @return the repositories page
     */
    @Step("Creating new test case {name}")
    public RepositoriesPage createNewTestCase(String name) {
        log.info("Clicking create new test case to suite button");
        driver.findElement(By.xpath(String.format(CREATE_TEST_CASE_TO_SUITE_BUTTON, name))).click();
        log.info(String.format("Typing test case name '%s'", name));
        driver.findElement(By.xpath(String.format(NEW_TEST_CASE_TITLE_FIELD, name))).sendKeys(name + Keys.ENTER);
        return this;
    }

    /**
     * Delete test case delete test case modal page.
     *
     * @param name the name
     * @return the delete test case modal page
     */
    @Step("Deleting test case {name}")
    public DeleteTestCaseModalPage deleteTestCase(String name) {
        log.info(String.format("Activating checkbox of test case '%s'", name));
        driver.findElement(By.xpath(String.format(TEST_CASE_CHECKBOX, name))).click();
        log.info("Clicking delete button");
        deleteButton.click();
        return new DeleteTestCaseModalPage(driver);
    }

    /**
     * Gets created suite name.
     *
     * @return the created suite name
     */
    public String getCreatedSuiteName() {
        log.info("Getting created suite name");
        return createdSuiteName.getText();
    }

    public String getCreatedSuiteNameAfterEdit() {
        waitForSuccessEditSuiteMessage();
        log.info("Getting created suite name");
        return createdSuiteName.getText();
    }

    /**
     * Is suite deleted boolean.
     *
     * @param suiteName the suite name
     * @return the boolean
     */
    public boolean isSuiteDeleted(String suiteName) {
        waitForSuccessDeletionSuiteMessage();
        return driver.findElements(By.xpath(String.format(SUITE_HEADER, suiteName))).isEmpty();
    }

    /**
     * Gets created test case name from suite.
     *
     * @param suiteName the suite name
     * @return the created test case name from suite
     */
    public String getCreatedTestCaseNameFromSuite(String suiteName) {
        log.info("Getting created test case name");
        return driver.findElement(By.xpath(String.format(CREATED_TEST_CASE_NAME, suiteName))).getText();
    }

    /**
     * Is test case deleted boolean.
     *
     * @param suiteName the suite name
     * @return the boolean
     */
    public boolean isTestCaseDeleted(String suiteName) {
        waitForSuccessDeletionTestCaseMessage();
        return driver.findElements(By.xpath(String.format(CREATED_TEST_CASE_NAME, suiteName))).isEmpty();
    }

    /**
     * Wait for success edit suite message repositories page.
     *
     * @return the repositories page
     */
    @Step("Waiting for suite to be edited")
    public RepositoriesPage waitForSuccessEditSuiteMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(successSuiteEditMessage));
        return this;
    }

    @Step("Waiting for suite to be deleted")
    public RepositoriesPage waitForSuccessDeletionSuiteMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(successSuiteDeletionMessage));
        return this;
    }

    @Step("Waiting for test case to be deleted")
    public RepositoriesPage waitForSuccessDeletionTestCaseMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(successTestCaseDeletionMessage));
        return this;
    }

    @Step("Waiting for project sttings to be edited")
    public RepositoriesPage waitForSuccessProjectSettingsEditMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(successEditProjectSettingsMessage));
        return this;
    }

}
