package tests;

import adapters.ProjectsAdapter;
import objects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;
import utils.RandomGenerators;

public class ProjectsTest extends BaseTest {

    @Test(description = "Logging in and creating project", groups = "ProjectsTest")
    public void createProjectTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        projectSteps
                .loginAndCreateProject(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertEquals(projectSteps.getProjectName(), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test(description = "Creating project via API, logging in and checking created project's name", groups = "ProjectsTest")
    public void newCreateProjectTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        loginSteps
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .findAndOpenProject(name);
        Assert.assertEquals(projectSteps.getProjectName(), name);
        //Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test(description = "Logging in and creating project, then editing created project's name", groups = "ProjectsTest")
    public void editProjectNameTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        String newName = RandomGenerators.randomId();
        projectSteps
                .createProjectAndEditName(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code,
                        newName);
        Assert.assertEquals(projectSteps.getProjectName(), newName);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(newName);
    }

    @Test(description = "Creating project via API, logging in and editing created project's name", groups = "ProjectsTest")
    public void newEditProjectNameTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        String newName = RandomGenerators.randomId();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        loginSteps
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .findAndEditProject(name, newName);
        Assert.assertEquals(projectSteps.getProjectName(), newName);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(newName);
    }

    @Test(description = "Logging in and creating project, then deleting created project", groups = "ProjectsTest")
    public void findAndDeleteProjectFromListTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        projectSteps
                .loginAndCreateProject(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        projectSteps
                .findAndDeleteProject(name);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    @Test(description = "Creating project via API, logging in and deleting created project", groups = "ProjectsTest")
    public void newFindAndDeleteProjectFromListTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        loginSteps
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .findAndDeleteProject(name);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    @Test(description = "Logging in and creating project, then deleting created project", groups = "ProjectsTest")
    public void findAndDeleteProjectFromRepositoriesTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        projectSteps
                .createAndDeleteProjectFromRepository(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    @Test(description = "Creating project via API, logging in and deleting created project", groups = "ProjectsTest")
    public void newFindAndDeleteProjectFromRepositoriesTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        loginSteps
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .createAndDeleteProject(name, code);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    @Test(description = "Creating project via API, logging in and editing created project's name", groups = "ProjectsTest")
    public void createProjectAndEditName() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        String newName = RandomGenerators.randomId();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        projectSteps
                .findAndEditProject(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        newName);
        Assert.assertEquals(projectSteps.getProjectName(), newName);
        projectSteps
                .findAndDeleteProject(newName);
    }
}
