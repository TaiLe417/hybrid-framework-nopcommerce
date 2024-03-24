package pageObjects.nopCommerce.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    WebDriver driver;

    public UserLoginPageObject(WebDriver drive) {
        super(drive);
        this.driver = drive;
    }

    @Step("Click to Login button")
    public UserHomePageObject clickLogInButton() {
        waitForElementClickable(UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePageObject(driver);
    }

    @Step("Enter to email address textbox with value {0}")
    public void inputToEmailTextBox(String emailAddress) {
        waitForElementVisible(UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Enter to password textbox with value {0}")
    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(UserLoginPageUI.EMAIL_ERROR_MESSAGE);
        return getTextElement(UserLoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getUnsuccessfulErrorMessage() {
        waitForElementVisible(UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
        return getTextElement(UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
    }

    public UserHomePageObject loginAsUser(String emailAddress, String password) {
        inputToEmailTextBox(emailAddress);
        inputToPasswordTextBox(password);
        return clickLogInButton();
    }
}
