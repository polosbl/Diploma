package pages;

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
    public ProjectsPage login(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new ProjectsPage(driver);
    }
}
