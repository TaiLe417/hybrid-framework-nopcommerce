package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }


    public UserPostDetailPO clickToPostTitle(String postTitle) {
        waitForElementClickable(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
        clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
        return PageGeneratorManager.getUserPostDetailPage(driver);
    }

    public boolean isPostInfoDisplayedWithTitle(String postTitle) {
        waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
        return isElementDisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
    }

    public boolean isPostInfoDisplayedWithBody(String postTitle, String postBody) {
        waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT, postTitle, postBody);
        return isElementDisplayed(driver, UserHomePageUI.POST_BODY_TEXT, postTitle, postBody);
    }

    public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
        waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_TEXT, postTitle, authorName);
        return isElementDisplayed(driver, UserHomePageUI.POST_AUTHOR_TEXT, postTitle, authorName);
    }

    public boolean isPostInfoDisplayedWithDate(String postTitle, String currentDay) {
        waitForElementVisible(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
        return isElementDisplayed(driver, UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
    }

    public boolean isPostInfoUndisplayedWithTitle(String editPostTitle) {
        return isElementUndisplayed(driver, UserHomePageUI.POST_TITLE_TEXT, editPostTitle);
    }

    public void enterToSearchTextBox(String editPostTitle) {
        waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);

    }

    public UserSearchPostPO clickToSearchButton() {
        waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
        clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getUserSearchPostPage(driver);
    }
}
