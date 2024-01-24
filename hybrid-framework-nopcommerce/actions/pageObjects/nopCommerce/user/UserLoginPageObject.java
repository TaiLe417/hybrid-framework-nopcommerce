package pageObjects.nopCommerce.user;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    WebDriver driver;

    public UserLoginPageObject(WebDriver drive) {
        this.driver = drive;
    }

    public UserHomePageObject clickLogInButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getUserHomePageObject(driver);
    }

    public void inputToEmailTextBox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

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
