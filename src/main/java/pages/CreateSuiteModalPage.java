package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class CreateSuiteModalPage extends HeaderPage {

    public CreateSuiteModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='name']")
    public WebElement suiteName;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement createButton;

    /**
     * @param name
     * @return
     */
    @Step ("Creating new suite with name - {name}")
    public RepositoriesPage createNewSuite (String name) {
        log.info("Filling in suite name");
        suiteName.sendKeys(name);
        log.info("Clicking create button.");
        createButton.click();
        return new RepositoriesPage(driver);
    }

    /**
     * @param newName
     * @return
     */
    @Step ("Changing name of the suite to {newName}")
    public RepositoriesPage editSuiteName (String newName) {
        log.info("Deleting old name");
        suiteName.clear();
        log.info("Creating new name");
        suiteName.sendKeys(newName);
        log.info("Clicking create button.");
        createButton.click();
        return new RepositoriesPage(driver);
    }
}
