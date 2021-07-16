package steps;

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

    public ProjectSteps loginAndCreateProject(String url,String email, String password, String name, String code) {
        loginPage
                .openPage(url)
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code);
        return this;
    }

    public ProjectSteps CreateProjectAndEditName(String url, String email, String password, String name, String code, String newName) {
        loginAndCreateProject(url, email, password, name, code);
        repositoriesPage.editProjectName(newName);
        return this;
    }

    public ProjectSteps createAndDeleteProjectFromRepository(String url, String email, String password, String name, String code) {
        loginPage
                .openPage(url)
                .login(email, password)
                .clickCreateProjectButton()
                .createProject(name, code)
                .deleteProjectFromRepositories()
                .confirmDelete();
        return this;
    }

    public ProjectSteps findAndDeleteProject(String name) {
        projectsPage
                .openPage()
                .clearSearchField()
                .findProjectByName(name)
                .deleteProjectFromList(name, "Delete")
                .confirmDelete();
        return this;
    }

    public ProjectSteps findAndEditProject(String url, String email, String password, String name, String newName) {
        loginPage
                .openPage(url)
                .login(email, password)
                .clearSearchField()
                .findProjectByName(name)
                .openProjectRepository(name)
                .editProjectName(newName);
        return this;
    }

    public ProjectSteps loginAndOpenProject(String url, String email, String password, String name) {
        loginPage
                .openPage(url)
                .login(email, password)
                .clearSearchField()
                .findProjectByName(name)
                .openProjectRepository(name);
        return this;
    }

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
