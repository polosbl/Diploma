package tests;

import adapters.ProjectsAdapter;
import objects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;
import utils.RandomGenerators;

public class SuitsTest extends BaseTest {
    @Test (description = "Logging in and creating project, then creating suite",groups = "SuiteTest")
    public void createNewSuiteViaUITest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        suiteSteps
                .loginAndCreateProjectWithSuite(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertEquals(suiteSteps.getCreatedSuiteName(), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test (description = "Creating project via API, logging in and creating suite in created project",groups = "SuiteTest")
    public void createProjectViaAPIAndCreateNewSuiteTest() {
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
        suiteSteps
                .createSuite(name);
        Assert.assertEquals(suiteSteps.getCreatedSuiteName(), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test (description = "Logging in and creating project, then creating and editing suite's name",groups = "SuiteTest")
    public void editSuiteNameTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        String newName = RandomGenerators.randomId();
        suiteSteps
                .createProjectAndEditSuite(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code,
                        newName);
        Assert.assertEquals(suiteSteps.getCreatedSuiteNameAfterEdit(), newName);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test (description = "Logging in and creating project, then creating and deleting suite",groups = "SuiteTest")
    public void deleteSuiteTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        suiteSteps
                .deleteSuite(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertTrue(suiteSteps.isSuiteDeleted(name));
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }
}
