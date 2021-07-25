package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class ProjectsPage extends HeaderPage {

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public static final String PROJECTS_URL = BASE_URL + "/projects";
    public static final String PROJECT_NAME = "//*[@class='project-row']//*[text()='%s']";
    public static final String PROJECT_DROPDOWN = "//*[text()='%s']/ancestor::*[@class='project-row']//*[contains(@class,'btn')]";
    public static final String PROJECT_DROPDOWN_ITEM = "//*[@class='dropdown']//*[contains(text(),'%s')]";


    @FindBy(xpath = "//*[@id='createButton']")
    public WebElement createProjectButton;

    @FindBy(xpath = "//*[@class='filters-block']//*[@name='title']")
    public WebElement searchField;

    @FindBy(xpath = "//*[@class='project-row']")
    public WebElement rows;

    public ProjectsPage openPage() {
        driver.get(PROJECTS_URL);
        return this;
    }

    /**
     * Click create project button create project page.
     *
     * @return the create project page
     */
    public CreateProjectPage clickCreateProjectButton() {
        log.info("Clicking create button.");
        createProjectButton.click();
        return new CreateProjectPage(driver);
    }

    /**
     * Clear search field projects page.
     *
     * @return the projects page
     */
    @Step ("Clearing search field")
    public ProjectsPage clearSearchField() {
        searchField.click();
        log.info("Clearing search field");
        searchField.clear();
        return this;
    }

    /**
     * Find project by name projects page.
     *
     * @param name the name
     * @return the projects page
     */
    @Step ("Finding project by name - {name}")
    public ProjectsPage findProjectByName(String name) {
        log.info("Typing project name {name} to search field");
        searchField.sendKeys(name);
        return this;
    }

    /**
     * Open project repository repositories page.
     *
     * @param name the name
     * @return the repositories page
     */
    @Step ("Opening project {name} repository")
    public RepositoriesPage openProjectRepository(String name) {
        log.info(String.format("Opening project '%s'",name));
        driver.findElement(By.xpath(String.format(PROJECT_NAME, name))).click();
        return new RepositoriesPage(driver);
    }

    /**
     * Delete project from list delete project page.
     *
     * @param name         the name
     * @param dropdownItem the dropdown item
     * @return the delete project page
     */
    @Step ("Deleting project {name} using dropdown menu")
    public DeleteProjectPage deleteProjectFromList(String name, String dropdownItem) {
        log.info("Opening settings dropdown");
        driver.findElement(By.xpath(String.format(PROJECT_DROPDOWN, name))).click();
        log.info(String.format("Clicking '%s' option",dropdownItem));
        driver.findElement(By.xpath(String.format(PROJECT_DROPDOWN_ITEM, dropdownItem))).click();
        return new DeleteProjectPage(driver);
    }

    /**
     * Is project deleted boolean.
     *
     * @param projectName the project name
     * @return the boolean
     */
    @Step ("Checking is project {projectName} deleted")
    public boolean isProjectDeleted(String projectName) {
        return driver.findElements(By.xpath(String.format(PROJECT_NAME,projectName))).isEmpty();
    }

    @Step("Waiting for page to open")
    public ProjectsPage waitForPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(rows));
        return this;
    }
}
