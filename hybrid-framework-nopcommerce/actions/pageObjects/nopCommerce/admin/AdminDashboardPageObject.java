package pageObjects.nopCommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.nopCommerce.admin.AdminDashboardPageUI;

public class AdminDashboardPageObject extends BasePage {
    private final WebDriver driver;

    public AdminDashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDashboardDisplayed(){
        waitForElementVisible(AdminDashboardPageUI.DASHBOARD_HEADER);
        return isElementDisplayed(AdminDashboardPageUI.DASHBOARD_HEADER);
    }
}
