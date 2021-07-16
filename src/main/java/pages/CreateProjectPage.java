package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.RandomGenerators;

public class CreateProjectPage extends HeaderPage {
    RandomGenerators randomGenerators;

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

//    public String generateProjectName() {
//        return randomGenerators.randomId();
//    }
//
//    public String generateProjectCode() {
//        return randomGenerators.randomId();
//    }

    public RepositoriesPage createProject(String name, String code) {
        projectNameField.sendKeys(name);
        projectCodeField.clear();
        projectCodeField.sendKeys(code);
        createProjectButton.click();
        return new RepositoriesPage(driver);
    }
}
