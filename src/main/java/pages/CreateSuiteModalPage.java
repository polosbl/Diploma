package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateSuiteModalPage extends BasePage {

    public CreateSuiteModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='name']")
    public WebElement suiteName;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement createButton;

    public RepositoriesPage createNewSuite (String name) {
        suiteName.sendKeys(name);
        createButton.click();
        return new RepositoriesPage(driver);
    }

    public RepositoriesPage editSuiteName (String newName) {
        suiteName.clear();
        suiteName.sendKeys(newName);
        createButton.click();
        return new RepositoriesPage(driver);
    }
}
