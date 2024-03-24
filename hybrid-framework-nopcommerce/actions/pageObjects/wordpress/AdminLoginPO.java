package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToUsernameTextbox(String adminUsername) {
        waitForElementVisible(AdminLoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(AdminLoginPageUI.USERNAME_TEXTBOX, adminUsername);
    }

    public void enterToPasswordTextbox(String adminPassword) {
        waitForElementVisible(AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitForElementClickable(AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPage(driver);
    }
}
