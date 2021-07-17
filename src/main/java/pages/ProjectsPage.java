package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public ProjectsPage openPage() {
        driver.get(PROJECTS_URL);
        return this;
    }

    /**
     * @return
     */
    public CreateProjectPage clickCreateProjectButton() {
        createProjectButton.click();
        return new CreateProjectPage(driver);
    }

    /**
     * @return
     */
    public ProjectsPage clearSearchField() {
        searchField.click();
        searchField.clear();
        return this;
    }

    /**
     * @param name
     * @return
     */
    public ProjectsPage findProjectByName(String name) {
        searchField.sendKeys(name);
        return this;
    }

    /**
     * @param name
     * @return
     */
    public RepositoriesPage openProjectRepository(String name) {
        driver.findElement(By.xpath(String.format(PROJECT_NAME, name))).click();
        return new RepositoriesPage(driver);
    }

    /**
     * @param name
     * @param dropdownItem
     * @return
     */
    public DeleteProjectPage deleteProjectFromList(String name, String dropdownItem) {
        driver.findElement(By.xpath(String.format(PROJECT_DROPDOWN, name))).click();
        driver.findElement(By.xpath(String.format(PROJECT_DROPDOWN_ITEM, dropdownItem))).click();
        return new DeleteProjectPage(driver);
    }

    /**
     * @param projectName
     * @return
     */
    public boolean isProjectDeleted(String projectName) {
        return driver.findElements(By.xpath(String.format(PROJECT_NAME,projectName))).isEmpty();
    }
}
