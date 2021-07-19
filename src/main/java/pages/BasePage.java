package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;

    public static final String BASE_URL = "https://app.qase.io";

    BasePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void openPage(String url) {
        driver.get(url);
    }
}
