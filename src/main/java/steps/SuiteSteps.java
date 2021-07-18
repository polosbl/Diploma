package steps;

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

    public SuiteSteps loginAndCreateProjectWithSuite(String email, String password, String name, String code) {
        loginPage
                .openPage()
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name);
        return this;
    }

    public SuiteSteps createProjectAndEditSuite(String email, String password, String name, String code, String newName) {
        loginPage
                .openPage()
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .editSuite(name)
                .editSuiteName(newName);
        return this;
    }

    public SuiteSteps deleteSuite(String email, String password, String name, String code) {
        loginPage
                .openPage()
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .deleteSuite(name)
                .clickDeleteSuiteButton();
        return this;
    }

    public String getCreatedSuiteName() {
        return repositoriesPage.getCreatedSuiteName();
    }

    public boolean isSuiteDeleted(String name) {
        return repositoriesPage.isSuiteDeleted(name);
    }
}
