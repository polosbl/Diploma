package tests;

import adapters.ProjectsAdapter;
import objects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;
import utils.RandomGenerators;

public class ProjectsTest extends BaseTest {

    @Test
    public void createProjectTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomId();
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

    @Test
    public void editProjectNameTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        String newName = RandomGenerators.randomId();
        projectSteps
                .CreateProjectAndEditName(
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

    @Test
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

    @Test
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

    //TODO: Implement page object and steps
    @Test
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
