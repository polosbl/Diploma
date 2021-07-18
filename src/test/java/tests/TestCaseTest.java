package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;
import utils.RandomGenerators;

public class TestCaseTest extends BaseTest {
    @Test (description = "Logging in and creating project, then creating suite and test case",groups = "TestCaseTest")
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

    @Test (description = "Logging in and creating project and suite, then creating and  deleting test case",groups = "TestCaseTest")
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
