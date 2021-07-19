package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteSuiteModalPage extends BasePage {

    public DeleteSuiteModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement deleteSuiteButton;

    public RepositoriesPage clickDeleteSuiteButton() {
        deleteSuiteButton.click();
        return new RepositoriesPage(driver);
    }
}
