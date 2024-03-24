package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
    WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver drive) {
        super(drive);
        this.driver = drive;
    }

    public boolean isCustomerInfoDisplayed() {
        waitForElementVisible(UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
        return isElementDisplayed(UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
    }
}
