package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.RandomGenerators;

@Log4j2
public class CreateProjectPage extends HeaderPage {

    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='inputTitle']")
    public WebElement projectNameField;

    @FindBy(xpath = "//*[@id='inputCode']")
    public WebElement projectCodeField;

    @FindBy(xpath = "//*[@id='inputDescription']")
    public WebElement projectDescriptionField;

    @FindBy(xpath = "//*[@id='private-access-type']")
    public WebElement privateAccessRadioButton;

    @FindBy(xpath = "//*[@id='public-access-type']")
    public WebElement publicAccessRadioButton;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement createProjectButton;

    /**
     * @param name
     * @param code
     * @return
     */
    @Step ("Creating project {name} with code - {code}")
    public RepositoriesPage createProject(String name, String code) {
        log.info(String.format("Filling in project name: '%s'",name));
        projectNameField.sendKeys(name);
        projectCodeField.clear();
        log.info(String.format("Filling in project code: '%s'",code));
        projectCodeField.sendKeys(code);
        log.info("Clicking create button.");
        createProjectButton.click();
        return new RepositoriesPage(driver);
    }
}
