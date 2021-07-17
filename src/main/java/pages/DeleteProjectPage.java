package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteProjectPage extends HeaderPage {

    public DeleteProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement deleteProjectButton;

    /**
     * @return
     */
    @Step("Confirming project deletion")
    public ProjectsPage confirmDelete() {
        deleteProjectButton.click();
        return new ProjectsPage(driver);
    }
}
