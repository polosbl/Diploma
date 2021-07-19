package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;
import utils.RandomGenerators;

public class TestCaseTest extends BaseTest {
    @Test
    public void createTestCaseToSuite() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        testCaseSteps
                .createProjectWithSuiteAndTestCase(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertEquals(testCaseSteps.getCreatedTestCaseNameFromSuite(name), name);
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }

    @Test
    public void deleteTestCaseTest() {
        String name = RandomGenerators.randomId();
        String code = RandomGenerators.randomCode();
        testCaseSteps
                .deleteTestCaseFromSuite(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")),
                        name,
                        code);
        Assert.assertTrue(testCaseSteps.isTestCaseDeleted(name));
        // Postcondition: delete project
        projectSteps
                .findAndDeleteProject(name);
    }
}
