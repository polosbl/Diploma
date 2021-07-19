package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        driver.get(LOGIN_URL);
        return this;
    }

    public ProjectsPage login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new ProjectsPage(driver);
    }
}
