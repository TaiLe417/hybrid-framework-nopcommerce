package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserRewardPointPageObject extends BasePage {
    private final WebDriver driver;

    public UserRewardPointPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
