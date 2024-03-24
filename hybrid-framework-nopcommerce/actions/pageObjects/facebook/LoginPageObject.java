package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickToCreateNewAccountButton() {
        waitForElementClickable(LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
        clickToElement(LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }

    public boolean isEmailAddressTextboxDisplayed() {
        waitForElementVisible(LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
    }

    public void enterEmailAddress(String emailAddress) {
        waitForElementVisible(LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public void clickCloseIcon() {
        waitForElementVisible(LoginPageUI.CLOSE_ICON);
        clickToElement(LoginPageUI.CLOSE_ICON);
    }

    public boolean isConfirmEmailIsDisplayed() {
        waitForElementVisible(LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public boolean isConfirmEmailIsUndisplayed() {
        waitForElementUndisplayed(LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementUndisplayed(LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }
}
