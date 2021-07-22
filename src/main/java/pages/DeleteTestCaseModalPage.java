package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class DeleteTestCaseModalPage extends BasePage {
    public DeleteTestCaseModalPage(WebDriver driver) {
        super(driver);
    }

    private static final String CONFIRMATION_WORD = "CONFIRM";

    @FindBy(xpath = "//button[contains(@class,'btn-danger')]")
    public WebElement deleteTestCaseButton;
    @FindBy (xpath = "//*[@name='confirm']")
    public WebElement confirmDeleteField;

    /**
     * Confirm delete test case repositories page.
     *
     * @return the repositories page
     */
    @Step ("Confirming test case deletion")
    public RepositoriesPage confirmDeleteTestCase () {
        log.info("Typing {CONFIRMATION_WORD}");
        confirmDeleteField.sendKeys(CONFIRMATION_WORD);
        log.info("Clicking delete button.");
        deleteTestCaseButton.click();
        return new RepositoriesPage(driver);
    }
}
