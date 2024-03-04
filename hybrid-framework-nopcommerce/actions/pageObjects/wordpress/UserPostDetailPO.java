package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.UserHomePageUI;
import pageUIs.wordpress.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage {
    WebDriver driver;

    public UserPostDetailPO(WebDriver driver) {
        this.driver = driver;
    }


    public boolean isPostInfoDisplayedWithTitle(String postTitle) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
    }

    public boolean isPostInfoDisplayedWithBody(String postTitle, String postBody) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY_TEXT, postTitle, postBody);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_BODY_TEXT, postTitle, postBody);
    }

    public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT, postTitle, authorName);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT, postTitle, authorName);
    }

    public boolean isPostInfoDisplayedWithDate(String postTitle, String currentDay) {
        waitForElementVisible(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
        return isElementDisplayed(driver, UserPostDetailPageUI.POST_CURRENT_DATE_TEXT_BY_POST_TITLE, postTitle, currentDay);
    }
}
