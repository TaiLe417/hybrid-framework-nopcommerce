package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserCustomerInfoPageObject extends BasePage {
    WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver drive) {
        this.driver = drive;
    }
}
