package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteTestCaseModalPage extends HeaderPage {
    public DeleteTestCaseModalPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[contains(@class,'btn-danger')]")
    public WebElement deleteTestCaseButton;
    @FindBy (xpath = "//*[@name='confirm']")
    public WebElement confirmDeleteField;

    /**
     * @return
     */
    public RepositoriesPage confirmDeleteTestCase () {
        confirmDeleteField.sendKeys("CONFIRM");
        deleteTestCaseButton.click();
        return new RepositoriesPage(driver);
    }
}
