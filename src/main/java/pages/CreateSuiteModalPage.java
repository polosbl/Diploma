package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class CreateSuiteModalPage extends BasePage {

    public CreateSuiteModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='name']")
    public WebElement suiteName;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement createButton;

    @FindBy(xpath = "//*[@id='descriptionGroup']//*[@class='ProseMirror']")
    public WebElement descriptionField;


    /**
     * Create new suite repositories page.
     *
     * @param name the name
     * @return the repositories page
     */
    @Step ("Creating new suite with name - {name}")
    public RepositoriesPage createNewSuite (String name) {
        log.info("Filling in suite {name}");
        suiteName.sendKeys(name);
        log.info("Clicking create button.");
        createButton.click();
        return new RepositoriesPage(driver);
    }

    /**
     * Edit suite name repositories page.
     *
     * @param newName the new name
     * @return the repositories page
     */
    @Step ("Changing name of the suite to {newName}")
    public RepositoriesPage editSuiteName (String newName) {
        log.info("Adding description");
        descriptionField.sendKeys(newName);
        log.info("Deleting old name");
        suiteName.clear();
        log.info("Creating new name {newName}");
        suiteName.sendKeys(newName);
        log.info("Clicking create button.");
        createButton.click();
        return new RepositoriesPage(driver);
    }

    /**
     * Wait for modal page to open create suite modal page.
     *
     * @return create suite modal page
     */
    @Step("Waiting for page to open")
    public CreateSuiteModalPage waitForModalPageToOpen(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementValue(suiteName,name));
        return this;
    }
}
