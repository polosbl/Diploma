package tests;

import adapters.ProjectsAdapter;
import objects.Project;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class SuitsTest extends BaseTest {
    @Test
    public void createNewSuiteTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        suiteSteps
                .loginAndCreateProjectWithSuite(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertEquals(suiteSteps.getCreatedSuiteName(), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    //NEW
    //TODO: Implement page object and steps
    @Test
    public void newCreateNewSuiteTest() {
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
                .findAndOpenProject(name);
        suiteSteps
                .createSuite(name);
        Assert.assertEquals(suiteSteps.getCreatedSuiteName(), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test
    public void editSuiteNameTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        String newName = randomGenerators.randomId();
        suiteSteps
                .createProjectAndEditSuite(
                        BASE_URL,
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code,
                        newName);
        Assert.assertEquals(suiteSteps.getCreatedSuiteName(), newName);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test
    public void deleteSuiteTest() {
        String name = randomGenerators.randomId();
        String code = randomGenerators.randomId();
        suiteSteps
                .deleteSuite(
                        BASE_URL,
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
