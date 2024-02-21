package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserCustomerInfoPageUI;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserCustomerInfoPageObject extends BasePage {
    WebDriver driver;

    public UserCustomerInfoPageObject(WebDriver drive) {
        this.driver = drive;
    }

    public boolean isCustomerInfoDisplayed() {
        waitForElementVisible(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
        return isElementDisplayed(driver, UserCustomerInfoPageUI.CUSTOMER_INFO_HEADER);
    }
}
