package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminPostTagPO extends BasePage {
    WebDriver driver;

    public AdminPostTagPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
