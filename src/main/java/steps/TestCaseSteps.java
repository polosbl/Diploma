package steps;

import io.qameta.allure.Step;
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

    @Step("Logging in and creating project, then creating new suite and test case")
    public TestCaseSteps createProjectWithSuiteAndTestCase(String email, String password, String name, String code) {
        loginPage
                .openPage()
                .login(email,password)
                .clickCreateProjectButton()
                .createProject(name,code)
                .clickCreateNewSuiteButton()
                .createNewSuite(name)
                .createNewTestCase(name);
        return this;
    }

    @Step("Logging in and creating project, then deleting created test case")
    public TestCaseSteps deleteTestCaseFromSuite(String email, String password, String name, String code) {
        loginPage
                .openPage()
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

    @Step ("Checking is test case deleted")
    public boolean isTestCaseDeleted(String caseName) {
        return repositoriesPage.isTestCaseDeleted(caseName);
    }
}
