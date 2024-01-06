package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessage() {
        return getTextElement(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getLastNameErrorMessage() {
        return getTextElement(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        return getTextElement(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        return getTextElement(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getConfirmPassowrdErrorMessage() {
        return getTextElement(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public String getWrongEmailErrorMessage() {
        return getTextElement(driver, RegisterPageUI.WRONG_EMAIL_MESSAGE);
    }

    public String getRegisterSuccessMessage() {
        return getTextElement(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getEmailExistMessage() {
        return getTextElement(driver, RegisterPageUI.EMAIL_EXIST_MESSAGE);
    }
}
