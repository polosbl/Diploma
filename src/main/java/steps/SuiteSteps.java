package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateProjectPage;
import pages.LoginPage;
import pages.ProjectsPage;
import pages.RepositoriesPage;

public class SuiteSteps {

    private LoginPage loginPage;
    private ProjectsPage projectsPage;
    private CreateProjectPage createProjectPage;
    private RepositoriesPage repositoriesPage;

    public SuiteSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        repositoriesPage = new RepositoriesPage(driver);
    }

    @Step ("Logging in and creating project, then creating new suite to project")
    public SuiteSteps loginAndCreateProjectWithSuite(String email, String password, String name,String code) {
        loginPage
                .openPage()
                .login(email,password)
                .clickCreateProjectButton()
                .createProject(name,code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name);
        return this;
    }

    @Step ("Logging in and creating project, then creating and editing suite")
    public SuiteSteps createProjectAndEditSuite(String email, String password, String name, String code, String newName) {
        loginPage
                .openPage()
                .login(email,password)
                .clickCreateProjectButton()
                .createProject(name,code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .editSuite(name)
//                .waitForModalPageToOpen(name)
                .editSuiteName(name,newName);
        return this;
    }

    @Step ("Logging in and creating project, then creating deleting suite")
    public SuiteSteps deleteSuite(String email, String password, String name, String code) {
        loginPage
                .openPage()
                .login(email,password)
                .clickCreateProjectButton()
                .createProject(name,code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .deleteSuite(name)
                .clickDeleteSuiteButton();
        return this;
    }

    @Step ("Creating new suite")
    public SuiteSteps createSuite(String name) {
        repositoriesPage
                .clickCreateNewSuiteButton()
                .createNewSuite(name);
        return this;
    }

    public String getCreatedSuiteName() {
        return repositoriesPage.getCreatedSuiteName();
    }

    @Step ("Checking is suite {name} deleted")
    public boolean isSuiteDeleted(String name) {
        return repositoriesPage.isSuiteDeleted(name);
    }
}
