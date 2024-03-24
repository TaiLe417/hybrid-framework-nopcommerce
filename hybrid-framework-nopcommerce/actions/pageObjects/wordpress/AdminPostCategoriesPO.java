package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AdminPostCategoriesPO extends BasePage {
    WebDriver driver;

    public AdminPostCategoriesPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}

