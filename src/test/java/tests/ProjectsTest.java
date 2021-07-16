package tests;

import adapters.ProjectsAdapter;
import objects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class ProjectsTest extends BaseTest {

    @Test
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
    @Test
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

    @Test
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
                .findAndDeleteProject(name);
    }

    //NEW
    //TODO: Implement page object and steps
    @Test
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

    @Test
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
    @Test
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

    @Test
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
    @Test
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
    @Test
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
