package tests;

import adapters.ProjectsAdapter;
import objects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class ProjectsTest extends BaseTest {

    @Test (description = "Logging in and creating project",groups = "ProjectsTest")
    public void createProjectTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        projectSteps
                .loginAndCreateProject(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertEquals(projectSteps.getProjectName(), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    //NEW
    //TODO: Implement page object and steps
    @Test (description = "Creating project via API, logging in and checking created project's name",groups = "ProjectsTest")
    public void newCreateProjectTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomCode();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        loginSteps
                .login(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .findAndOpenProject(name);
        Assert.assertEquals(projectSteps.getProjectName(), name);
        //Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test (description = "Logging in and creating project, then editing created project's name",groups = "ProjectsTest")
    public void editProjectNameTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        String newName = randomGenerators.randomId();
        projectSteps
                .createProjectAndEditName(
                        BASE_URL,
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

    //NEW
    //TODO: Implement page object and steps
    @Test (description = "Creating project via API, logging in and editing created project's name",groups = "ProjectsTest")
    public void newEditProjectNameTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomCode();
        String newName = randomGenerators.randomId();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        loginSteps
                .login(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .findAndEditProject(name, newName);
        Assert.assertEquals(projectSteps.getProjectName(), newName);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(newName);
    }

    @Test (description = "Logging in and creating project, then deleting created project",groups = "ProjectsTest")
    public void findAndDeleteProjectFromListTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        projectSteps
                .loginAndCreateProject(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        projectSteps
                .findAndDeleteProject(name);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    //NEW
    //TODO: Implement page object and steps
    @Test (description = "Creating project via API, logging in and deleting created project",groups = "ProjectsTest")
    public void newFindAndDeleteProjectFromListTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        loginSteps
                .login(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .findAndDeleteProject(name);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    @Test (description = "Logging in and creating project, then deleting created project",groups = "ProjectsTest")
    public void findAndDeleteProjectFromRepositoriesTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        projectSteps
                .createAndDeleteProjectFromRepository(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    //NEW
    @Test (description = "Creating project via API, logging in and deleting created project",groups = "ProjectsTest")
    public void newFindAndDeleteProjectFromRepositoriesTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        loginSteps
                .login(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        projectSteps
                .createAndDeleteProject(name, code);
        Assert.assertTrue(projectSteps.isProjectDeleted(name));
    }

    //TODO: Implement page object and steps
    @Test (description = "Creating project via API, logging in and editing created project's name",groups = "ProjectsTest")
    public void createProjectAndEditName() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomCode();
        String newName = randomGenerators.randomId();
        Project project = Project.builder()
                .title(name)
                .code(code)
                .access("all")
                .group(null)
                .build();
        new ProjectsAdapter().create(project);
        projectSteps
                .findAndEditProject(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        newName);
        Assert.assertEquals(projectSteps.getProjectName(), newName);
        projectSteps
                .findAndDeleteProject(newName);
    }
}
