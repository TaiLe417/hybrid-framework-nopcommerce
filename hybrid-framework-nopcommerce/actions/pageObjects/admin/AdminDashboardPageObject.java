package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    private WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardDisplayed(){
        waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
        return isElementDisplayd(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
    }
}
