package steps;

import org.openqa.selenium.WebDriver;
import pages.CreateProjectPage;
import pages.LoginPage;
import pages.ProjectsPage;
import pages.RepositoriesPage;

public class TestCaseSteps {
    private LoginPage loginPage;
    private ProjectsPage projectsPage;
    private CreateProjectPage createProjectPage;
    private RepositoriesPage repositoriesPage;

    public TestCaseSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        repositoriesPage = new RepositoriesPage(driver);
    }

    public TestCaseSteps createProjectWithSuiteAndTestCase(String url, String email, String password, String name, String code) {
        loginPage
                .openPage(url)
                .login(email,password)
                .clickCreateProjectButton()
                .createProject(name,code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .createNewTestCase(name);
        return this;
    }

    public TestCaseSteps deleteTestCaseFromSuite(String url, String email, String password, String name, String code) {
        loginPage
                .openPage(url)
                .login(email,password)
                .clickCreateProjectButton()
                .createProject(name,code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .createNewTestCase(name)
                .deleteTestCase(name)
                .confirmDeleteTestCase();
        return this;
    }

    public String getCreatedTestCaseNameFromSuite(String suiteName) {
        return repositoriesPage.getCreatedTestCaseNameFromSuite(suiteName);
    }

    public boolean isTestCaseDeleted(String suiteName) {
        return repositoriesPage.isTestCaseDeleted(suiteName);
    }
}
