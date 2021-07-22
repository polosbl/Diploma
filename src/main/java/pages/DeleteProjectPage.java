package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class DeleteProjectPage extends HeaderPage {

    public DeleteProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement deleteProjectButton;

    /**
     * Click delete project button projects page.
     *
     * @return the projects page
     */
    @Step("Confirming project deletion")
    public ProjectsPage clickDeleteProjectButton() {
        log.info("Clicking delete button.");
        deleteProjectButton.click();
        return new ProjectsPage(driver);
    }
}
