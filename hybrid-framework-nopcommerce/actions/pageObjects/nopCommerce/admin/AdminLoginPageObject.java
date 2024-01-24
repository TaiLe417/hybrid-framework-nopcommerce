package pageObjects.nopCommerce.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    private WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToEmailTextBox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public AdminDashboardPageObject clickLogInButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPageObject(driver);
    }

    public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
        inputToEmailTextBox(emailAddress);
        inputToPasswordTextBox(password);
        return clickLogInButton();
    }
}
