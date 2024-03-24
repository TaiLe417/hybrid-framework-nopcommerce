package pageObjects.nopCommerce.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
    private final WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step ("Navigate to Register page")
    public UserRegisterPageObject clickRegisterLink() {
        waitForElementClickable(UserHomePageUI.REGISTER_LINK);
        clickToElement(UserHomePageUI.REGISTER_LINK);
        return new UserRegisterPageObject(driver);
    }
    @Step ("Navigate to Login page")
    public UserLoginPageObject clickLogInLink() {
        waitForElementClickable(UserHomePageUI.LOGIN_LINK);
        clickToElement(UserHomePageUI.LOGIN_LINK);
        return new UserLoginPageObject(driver);
    }
    @Step ("Verify 'My Account' is displayed")
    public boolean isMyAccountDisplayed() {
        waitForElementVisible(UserHomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(UserHomePageUI.MY_ACCOUNT_LINK);
    }
    @Step ("Navigate to My Account page")
    public UserCustomerInfoPageObject clickToMyAccountLink() {
        waitForElementClickable(UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserCustomerInfoPageObject(driver);
    }
}
