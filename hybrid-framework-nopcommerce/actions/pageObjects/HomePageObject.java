package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
    }

    public void clickLogInLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
    }

    public boolean isMyAccountDisplayed() {
        waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayd(driver, HomePageUI.MY_ACCOUNT_LINK);
    }

}
