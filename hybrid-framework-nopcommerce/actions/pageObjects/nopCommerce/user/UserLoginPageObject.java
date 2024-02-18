package pageObjects.nopCommerce.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    WebDriver driver;

    public UserLoginPageObject(WebDriver drive) {
        this.driver = drive;
    }

    @Step("Click to Login button")
    public UserHomePageObject clickLogInButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePageObject(driver);
    }

    @Step("Enter to email address textbox with value {0}")
    public void inputToEmailTextBox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Enter to password textbox with value {0}")
    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
        return getTextElement(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getUnsuccessfulErrorMessage() {
        waitForElementVisible(driver, UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
        return getTextElement(driver, UserLoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
    }

    public UserHomePageObject loginAsUser(String emailAddress, String password) {
        inputToEmailTextBox(emailAddress);
        inputToPasswordTextBox(password);
        return clickLogInButton();
    }
}
