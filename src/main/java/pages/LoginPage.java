package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='inputEmail']")
    public WebElement emailInput;

    @FindBy(xpath = "//*[@id='inputPassword']")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id='btnLogin']")
    public WebElement loginButton;

    public LoginPage openPage(String url) {
        driver.get(url);
        return this;
    }

    /**
     * @param email
     * @param password
     * @return
     */
    @Step("Logging in as {email} with password - {password}")
    public ProjectsPage login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new ProjectsPage(driver);
    }
}
