package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class DeleteSuiteModalPage extends BasePage {

    public DeleteSuiteModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement deleteSuiteButton;

    /**
     * Click delete suite button repositories page.
     *
     * @return the repositories page
     */
    @Step ("Confirming suite deletion")
    public RepositoriesPage clickDeleteSuiteButton() {
        log.info("Clicking delete button.");
        deleteSuiteButton.click();
        return new RepositoriesPage(driver);
    }
}
