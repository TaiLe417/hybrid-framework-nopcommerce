package pageObjects.saurceLab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.saurceLab.LoginPageUI;

public class LoginPO extends BasePage {
    WebDriver driver;

    public LoginPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToUserName(String userName) {
        waitForElementVisible(LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(LoginPageUI.USERNAME_TEXTBOX, userName);
    }

    public void enterToPassword(String password) {
        waitForElementVisible(LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public ProductPO clickLoginButton() {
        waitForElementClickable(LoginPageUI.LOGIN_BUTTON);
        clickToElement(LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getProductPage(driver);
    }
}
