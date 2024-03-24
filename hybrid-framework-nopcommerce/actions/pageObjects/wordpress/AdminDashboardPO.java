package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
    WebDriver driver;

    public AdminDashboardPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AdminPostSearchPO clickToPostMenuLink() {
        waitForElementClickable(AdminDashboardPageUI.POST_MENU_LINK);
        clickToElement(AdminDashboardPageUI.POST_MENU_LINK);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }
}
