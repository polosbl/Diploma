package utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

@Log4j2
public class CommonUtils {

    public static void hoverOverElement(WebDriver driver, String element, String elementName) {
        Actions builder = new Actions(driver);
        log.info(String.format("Hovering over element '%s'",elementName));
        builder.moveToElement(driver.findElement(By.xpath(String.format(element, elementName)))).perform();
    }
}
