package pageObjects.wordpress.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.admin.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
    WebDriver driver;

    public AdminPostAddNewPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToAddNewPostTitle(String postTitle) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX);
        sendKeyToElement(driver, AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitle);
    }

    public void enterToAddNewPostBody(String postBody) {
        waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.BODY_BUTTON);
        sleepInSecond(1);
        waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendKeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
    }

    public void clickToPublishButton() {
        waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
    }

    public boolean isPostPublishMessageDisplayed(String postMessage) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postMessage);
        return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISHED_MESSAGE, postMessage);
    }

    public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
        openPageUrl(driver, searchPostUrl);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }

    public void clickToPrePublishButton() {
        waitForElementVisible(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
    }
}
