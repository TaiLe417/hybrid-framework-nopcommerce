package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
    WebDriver driver;

    public AdminPostSearchPO(WebDriver driver) {
        this.driver = driver;
    }

    public AdminPostAddNewPO clickToAddNewButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_BUTTON);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void enterToSearchTextBox(String postTitle) {
        waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
        sendKeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
    }

    public void clickToSearchPostButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_BUTTON);
    }

    public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
        int headerIndex = getElementsSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) + 1;
        waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_INDEX_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
        return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_INDEX_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
    }

    public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.TABLE_ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
        clickToElement(driver, AdminPostSearchPageUI.TABLE_ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void selectPostCheckBoxByTitle(String editPostTitle) {
        waitForElementClickable(driver, AdminPostSearchPageUI.ROW_CHECK_BOX_BY_TITLE_NAME, editPostTitle);
        checkTheCheckBoxOrRadio(driver, AdminPostSearchPageUI.ROW_CHECK_BOX_BY_TITLE_NAME, editPostTitle);
    }

    public void selectTextItemInActionDropDown(String item) {
        waitForElementClickable(driver, AdminPostSearchPageUI.ACTION_DROPDOWN);
        selectItemInDefaultDropDown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, item);
    }

    public void clickApplyButton() {
        waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
    }

    public boolean isMovedToTrashMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
        return isElementDisplayed(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
    }

    public boolean isNoPostsFoundMessageDisplayed(String message) {
        waitForElementVisible(driver, AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
        return isElementDisplayed(driver, AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
    }
}
