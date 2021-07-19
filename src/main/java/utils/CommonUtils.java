package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CommonUtils {

    public static void hoverOverElement(WebDriver driver, String element, String elementName) {
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(By.xpath(String.format(element, elementName)))).perform();
    }
}
