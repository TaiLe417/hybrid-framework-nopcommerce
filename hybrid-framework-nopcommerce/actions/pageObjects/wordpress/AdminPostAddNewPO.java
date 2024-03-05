package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostAddNewPageUI;

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

        waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendKeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
    }

    public void enterToEditPostBody(String postBody) {
        waitForElementClickable(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        clickToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);

        waitForElementVisible(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        clearValueInElementByPressKey(driver, AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendKeyToElement(driver, AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
    }

    public void clickToPublishOrUpdateButton() {
        waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
        clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
    }

    public boolean isPostPublishMessageDisplayed(String postMessage) {
        waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISHED_OR_UPDATED_MESSAGE, postMessage);
        return isElementDisplayed(driver, AdminPostAddNewPageUI.PUBLISHED_OR_UPDATED_MESSAGE, postMessage);
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
