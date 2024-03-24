package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserHomePageUI;

public class UserHomePO extends BasePage {
    WebDriver driver;

    public UserHomePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public UserPostDetailPO clickToPostTitle(String postTitle) {
        waitForElementClickable(UserHomePageUI.POST_TITLE_TEXT, postTitle);
        clickToElement(UserHomePageUI.POST_TITLE_TEXT, postTitle);
        return PageGeneratorManager.getUserPostDetailPage(driver);
    }

    public boolean isPostInfoDisplayedWithTitle(String postTitle) {
        waitForElementVisible(UserHomePageUI.POST_TITLE_TEXT, postTitle);
        return isElementDisplayed(UserHomePageUI.POST_TITLE_TEXT, postTitle);
    }

    public boolean isPostInfoDisplayedWithBody(String postTitle, String postBody) {
        waitForElementVisible(UserHomePageUI.POST_BODY_TEXT, postTitle, postBody);
        return isElementDisplayed(UserHomePageUI.POST_BODY_TEXT, postTitle, postBody);
    }

    public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
        waitForElementVisible(UserHomePageUI.POST_AUTHOR_TEXT, postTitle, authorName);
        return isElementDisplayed(UserHomePageUI.POST_AUTHOR_TEXT, postTitle, authorName);
    }

    public boolean isPostInfoDisplayedWithDate(String postTitle, String currentDay) {
        waitForElementVisible(UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
        return isElementDisplayed(UserHomePageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
    }

    public boolean isPostInfoUndisplayedWithTitle(String editPostTitle) {
        return isElementUndisplayed(UserHomePageUI.POST_TITLE_TEXT, editPostTitle);
    }

    public void enterToSearchTextBox(String editPostTitle) {
        waitForElementVisible(UserHomePageUI.SEARCH_TEXTBOX);
        sendKeyToElement(UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);

    }

    public UserSearchPostPO clickToSearchButton() {
        waitForElementClickable(UserHomePageUI.SEARCH_BUTTON);
        clickToElement(UserHomePageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getUserSearchPostPage(driver);
    }
}
