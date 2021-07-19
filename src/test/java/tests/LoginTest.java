package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        loginPage
                .openPage()
                .login(System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        Assert.assertEquals(projectSteps.getCurrentUrl(), PROJECTS_URL);
    }
}
