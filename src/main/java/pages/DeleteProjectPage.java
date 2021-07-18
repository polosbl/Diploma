package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteProjectPage extends HeaderPage {

    public DeleteProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement deleteProjectButton;

    public ProjectsPage clickDeleteProjectButton() {
        deleteProjectButton.click();
        return new ProjectsPage(driver);
    }
}
