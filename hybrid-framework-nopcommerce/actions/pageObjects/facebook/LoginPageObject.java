package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewAccountButton() {
        waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
        clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }

    public boolean isEmailAddressTextboxDisplayed() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
    }

    public void enterEmailAddress(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
    }

    public void clickCloseIcon() {
        waitForElementVisible(driver, LoginPageUI.CLOSE_ICON);
        clickToElement(driver, LoginPageUI.CLOSE_ICON);
    }

    public boolean isConfirmEmailIsDisplayed() {
        waitForElementVisible(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementDisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }

    public boolean isConfirmEmailIsUndisplayed() {
        waitForElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
        return isElementUndisplayed(driver, LoginPageUI.CONFIRM_EMAIL_ADDRESS_TEXTBOX);
    }
}
