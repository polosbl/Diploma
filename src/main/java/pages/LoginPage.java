package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final String LOGIN_URL = BASE_URL + "/login";

    @FindBy(xpath = "//*[@id='inputEmail']")
    public WebElement emailInput;

    @FindBy(xpath = "//*[@id='inputPassword']")
    public WebElement passwordInput;

    @FindBy(xpath = "//*[@id='btnLogin']")
    public WebElement loginButton;

    public LoginPage openPage() {
        log.info("Opening Login page, URL: " + LOGIN_URL);
        driver.get(LOGIN_URL);
        return this;
    }

    /**
     * Login projects page.
     *
     * @param email    the email
     * @param password the password
     * @return the projects page
     */
    @Step("Logging in as {email} with password - {password}")
    public ProjectsPage login(String email, String password) {
        log.info(String.format("Filling in username: '%s' in email field",email));
        emailInput.sendKeys(email);
        log.info(String.format("Filling in password: '%s' in password field",password));
        passwordInput.sendKeys(password);
        log.info("Clicking Login button.");
        loginButton.click();
        return new ProjectsPage(driver);
    }
}
