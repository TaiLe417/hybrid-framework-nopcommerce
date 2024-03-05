package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserSearchPostPageUI;

public class UserSearchPostPO extends BasePage {
    WebDriver driver;

    public UserSearchPostPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNothingFoundMessageDisplayed(String message) {
        waitForElementVisible(driver, UserSearchPostPageUI.NOTHING_FOUND_MESSAGE, message);
        return isElementDisplayed(driver, UserSearchPostPageUI.NOTHING_FOUND_MESSAGE, message);

    }
}
