package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;
import utils.RandomGenerators;

public class SuitsTest extends BaseTest {
    @Test
    public void createNewSuiteTest() {
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

    @Test
    public void editSuiteNameTest() throws InterruptedException {
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
        //TODO: Implement waiter
        Thread.sleep(2000);
        Assert.assertEquals(suiteSteps.getCreatedSuiteName(), newName);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test
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
