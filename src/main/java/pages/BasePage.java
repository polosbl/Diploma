package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class BasePage {
    WebDriver driver;

    public static final String BASE_URL = "https://app.qase.io";

    BasePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getCurrentUrl() {
        log.debug("Getting current URL.");
        return driver.getCurrentUrl();
    }
}
