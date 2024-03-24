package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserAddressPageObject extends BasePage {
    private final WebDriver driver;

    public UserAddressPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
