package pageObjects.nopCommerce.user;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessage() {
        return getTextElement(driver, UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getLastNameErrorMessage() {
        return getTextElement(driver, UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        return getTextElement(driver, UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        return getTextElement(driver, UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getConfirmPasswordErrorMessage() {
        return getTextElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public String getWrongEmailErrorMessage() {
        return getTextElement(driver, UserRegisterPageUI.WRONG_EMAIL_MESSAGE);
    }

    public String getRegisterSuccessMessage() {
        return getTextElement(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getEmailExistMessage() {
        return getTextElement(driver, UserRegisterPageUI.EMAIL_EXIST_MESSAGE);
    }
}
