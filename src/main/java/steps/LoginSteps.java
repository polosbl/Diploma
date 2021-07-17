package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProjectsPage;
import pages.RepositoriesPage;

public class LoginSteps {
    private LoginPage loginPage;
    private ProjectsPage projectsPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
    }

    @Step("Logging in as {email} with password {password}")
    public LoginSteps login(String url,String email, String password) {
        loginPage
                .openPage(url)
                .login(email, password);
        return this;
    }
}
