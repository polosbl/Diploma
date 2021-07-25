package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.LoginPage;
import pages.ProjectsPage;
import steps.LoginSteps;
import steps.ProjectSteps;
import steps.SuiteSteps;
import steps.TestCaseSteps;
import testConstants.ITestConstants;
import utils.RandomGenerators;

import java.util.concurrent.TimeUnit;
@Log4j2
@Listeners(TestListener.class)
public class BaseTest implements ITestConstants {
    WebDriver driver;
    LoginPage loginPage;
    ProjectsPage projectsPage;
    ProjectSteps projectSteps;
    SuiteSteps suiteSteps;
    TestCaseSteps testCaseSteps;
    LoginSteps loginSteps;

    @BeforeMethod
    public void initTest(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        try {
            driver = new ChromeDriver(options);
        } catch (WebDriverException exception) {
            log.fatal("Driver was not started!");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        initPages();
        PageFactory.initElements(driver, this);
        //Screenshots
        String variable = "driver";
        System.out.println("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }

    public void initPages() {
        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        projectSteps = new ProjectSteps(driver);
        suiteSteps = new SuiteSteps(driver);
        testCaseSteps = new TestCaseSteps(driver);
        loginSteps = new LoginSteps(driver);
    }

//    @AfterMethod
//    public void endTest() {
//        driver.quit();
//    }
}
