package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
    WebDriver driver;

    public AdminPostAddNewPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToAddNewPostTitle(String postTitle) {
        waitForElementVisible(AdminPostAddNewPageUI.TITLE_TEXTBOX);
        sendKeyToElement(AdminPostAddNewPageUI.TITLE_TEXTBOX, postTitle);
    }

    public void enterToAddNewPostBody(String postBody) {
        waitForElementClickable(AdminPostAddNewPageUI.BODY_BUTTON);
        clickToElement(AdminPostAddNewPageUI.BODY_BUTTON);

        waitForElementVisible(AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendKeyToElement(AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
    }

    public void enterToEditPostBody(String postBody) {
        waitForElementClickable(AdminPostAddNewPageUI.BODY_TEXTBOX);
        clickToElement(AdminPostAddNewPageUI.BODY_TEXTBOX);

        waitForElementVisible(AdminPostAddNewPageUI.BODY_TEXTBOX);
        clearValueInElementByPressKey(AdminPostAddNewPageUI.BODY_TEXTBOX);
        sendKeyToElement(AdminPostAddNewPageUI.BODY_TEXTBOX, postBody);
    }

    public void clickToPublishOrUpdateButton() {
        waitForElementClickable(AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
        clickToElement(AdminPostAddNewPageUI.PUBLISH_OR_UPDATE_BUTTON);
    }

    public boolean isPostPublishMessageDisplayed(String postMessage) {
        waitForElementVisible(AdminPostAddNewPageUI.PUBLISHED_OR_UPDATED_MESSAGE, postMessage);
        return isElementDisplayed(AdminPostAddNewPageUI.PUBLISHED_OR_UPDATED_MESSAGE, postMessage);
    }

    public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
        openPageUrl(searchPostUrl);
        return PageGeneratorManager.getAdminPostSearchPage(driver);
    }

    public void clickToPrePublishButton() {
        waitForElementVisible(AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
        clickToElement(AdminPostAddNewPageUI.PRE_PUBLISH_BUTTON);
    }
}
