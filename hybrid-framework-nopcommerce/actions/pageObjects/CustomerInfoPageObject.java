package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class CustomerInfoPageObject extends BasePage {
    WebDriver driver;

    public CustomerInfoPageObject(WebDriver drive) {
        this.driver = drive;
    }
}
