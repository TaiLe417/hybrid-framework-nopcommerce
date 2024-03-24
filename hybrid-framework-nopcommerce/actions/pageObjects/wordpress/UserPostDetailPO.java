package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
    WebDriver driver;

    public UserPostDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public boolean isPostInfoDisplayedWithTitle(String postTitle) {
        waitForElementVisible(UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
        return isElementDisplayed(UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
    }

    public boolean isPostInfoDisplayedWithBody(String postTitle, String postBody) {
        waitForElementVisible(UserPostDetailPageUI.POST_BODY_TEXT, postTitle, postBody);
        return isElementDisplayed(UserPostDetailPageUI.POST_BODY_TEXT, postTitle, postBody);
    }

    public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
        waitForElementVisible(UserPostDetailPageUI.POST_AUTHOR_TEXT, postTitle, authorName);
        return isElementDisplayed(UserPostDetailPageUI.POST_AUTHOR_TEXT, postTitle, authorName);
    }

    public boolean isPostInfoDisplayedWithDate(String postTitle, String currentDay) {
        waitForElementVisible(UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
        return isElementDisplayed(UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
    }
}
