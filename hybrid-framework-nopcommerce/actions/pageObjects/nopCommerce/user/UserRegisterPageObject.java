package pageObjects.nopCommerce.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Click to Register button")
    public void clickRegisterButton() {
        waitForElementClickable(UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(UserRegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessage() {
        return getTextElement(UserRegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getLastNameErrorMessage() {
        return getTextElement(UserRegisterPageUI.LAST_NAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        return getTextElement(UserRegisterPageUI.EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessage() {
        return getTextElement(UserRegisterPageUI.PASSWORD_ERROR_MESSAGE);
    }

    public String getConfirmPasswordErrorMessage() {
        return getTextElement(UserRegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    @Step("Enter to firstname textbox with value {0}")
    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    @Step("Enter to lastname textbox with value {0}")
    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    @Step("Enter to email address textbox with value {0}")
    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    @Step("Enter to password textbox with value {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    @Step("Enter to confirm password textbox with value {0}")
    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
    }

    public String getWrongEmailErrorMessage() {
        return getTextElement(UserRegisterPageUI.WRONG_EMAIL_MESSAGE);
    }

    @Step("Verify register success message is displayed")
    public String getRegisterSuccessMessage() {
        waitForElementVisible(UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getTextElement(UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    public String getEmailExistMessage() {
        return getTextElement(UserRegisterPageUI.EMAIL_EXIST_MESSAGE);
    }
}
