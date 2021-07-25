package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateProjectPage;
import pages.LoginPage;
import pages.ProjectsPage;
import pages.RepositoriesPage;

public class ProjectSteps {
    private LoginPage loginPage;
    private ProjectsPage projectsPage;
    private CreateProjectPage createProjectPage;
    private RepositoriesPage repositoriesPage;

    public ProjectSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        repositoriesPage = new RepositoriesPage(driver);
    }

    @Step ("Logging in and creating project")
    public ProjectSteps loginAndCreateProject(String email, String password, String name, String code) {
        loginPage
                .openPage()
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code);
        return this;
    }

    @Step ("Logging in and creating project, then editing name")
    public ProjectSteps createProjectAndEditName(String email, String password, String name, String code, String newName) {
        loginAndCreateProject(email, password, name, code);
        repositoriesPage.editProjectName(newName);
        return this;
    }
    @Step ("Logging in and creating project, then deleting project")
    public ProjectSteps createAndDeleteProjectFromRepository(String email, String password, String name, String code) {
        loginPage
                .openPage()
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code)
                .deleteProjectFromRepositories()
                .clickDeleteProjectButton();
        return this;
    }

    @Step ("Finding and deleting project")
    public ProjectSteps findAndDeleteProject(String name) {
        projectsPage
                .openPage()
                .waitForProjectsPageToOpen()
                .clearSearchField()
                .findProjectByName(name)
                .deleteProjectFromList(name, "Delete")
                .clickDeleteProjectButton();
        return this;
    }

    @Step ("Logging in and finding project, then editing name")
    public ProjectSteps findAndEditProject(String email, String password, String name, String newName) {
        loginPage
                .openPage()
                .login(email, password)
                .waitForProjectsPageToOpen()
                .clearSearchField()
                .findProjectByName(name)
                .openProjectRepository(name)
                .editProjectName(newName);
        return this;
    }

    @Step ("Logging in and finding project, then opening project")
    public ProjectSteps loginAndOpenProject(String email, String password, String name) {
        loginPage
                .openPage()
                .login(email, password)
                .waitForProjectsPageToOpen()
                .clearSearchField()
                .findProjectByName(name)
                .openProjectRepository(name);
        return this;
    }

    @Step ("Finding and opening project")
    public ProjectSteps findAndOpenProject(String projectName) {
        projectsPage
                .waitForProjectsPageToOpen()
                .clearSearchField()
                .findProjectByName(projectName)
                .openProjectRepository(projectName);
        return this;
    }

    @Step ("Finding and editing project")
    public ProjectSteps findAndEditProject(String name, String newName) {
        projectsPage
                .waitForProjectsPageToOpen()
                .clearSearchField()
                .findProjectByName(name)
                .openProjectRepository(name)
                .editProjectName(newName);
        return this;
    }

    @Step ("Creating project")
    public ProjectSteps createProject(String name, String code) {
        projectsPage
                .clickCreateProjectButton()
                .createProject(name,code);
        return this;
    }

    @Step ("Creating and editing project")
    public ProjectSteps createAndEditProject (String name, String code, String newName) {
        projectsPage
                .clickCreateProjectButton()
                .createProject(name,code)
                .editProjectName(newName);
        return this;
    }

    @Step ("Creating and deleting project")
    public ProjectSteps createAndDeleteProject (String name, String code) {
        projectsPage
                .clickCreateProjectButton()
                .createProject(name,code)
                .deleteProjectFromRepositories();
        return this;
    }

    @Step("Editing project name")
    public ProjectSteps editProjectName (String newName) {
        repositoriesPage
                .editProjectName(newName);
        return this;
    }

    @Step ("Checking is project {projectName} deleted")
    public boolean isProjectDeleted(String projectName) {
        return projectsPage.isProjectDeleted(projectName);
    }

    public String getProjectName() {
        return repositoriesPage.getProjectName();
    }

    public String getCurrentUrl() {
        return projectsPage.getCurrentUrl();
    }

}
