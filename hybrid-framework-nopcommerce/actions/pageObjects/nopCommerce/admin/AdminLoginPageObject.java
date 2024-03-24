package pageObjects.nopCommerce.admin;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    private final WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void inputToEmailTextBox(String emailAddress) {
        waitForElementVisible(AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public AdminDashboardPageObject clickLogInButton() {
        waitForElementClickable(UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(UserLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminDashboardPageObject(driver);
    }

    public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password) {
        inputToEmailTextBox(emailAddress);
        inputToPasswordTextBox(password);
        return clickLogInButton();
    }
}
