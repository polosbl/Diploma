package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        suiteName.sendKeys(name);
        createButton.click();
        return new RepositoriesPage(driver);
    }

    /**
     * @param newName
     * @return
     */
    @Step ("Changing name of the suite to {newName}")
    public RepositoriesPage editSuiteName (String newName) {
        suiteName.clear();
        suiteName.sendKeys(newName);
        createButton.click();
        return new RepositoriesPage(driver);
    }
}
