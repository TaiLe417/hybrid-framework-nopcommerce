package pageObjects.wordpress;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
    WebDriver driver;

    public AdminPostSearchPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public AdminPostAddNewPO clickToAddNewButton() {
        waitForElementClickable(AdminPostSearchPageUI.ADD_NEW_BUTTON);
        clickToElement(AdminPostSearchPageUI.ADD_NEW_BUTTON);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void enterToSearchTextBox(String postTitle) {
        waitForElementVisible(AdminPostSearchPageUI.SEARCH_TEXTBOX);
        sendKeyToElement(AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
    }

    public void clickToSearchPostButton() {
        waitForElementClickable(AdminPostSearchPageUI.SEARCH_BUTTON);
        clickToElement(AdminPostSearchPageUI.SEARCH_BUTTON);
    }

    public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
        int headerIndex = getElementsSize(AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) + 1;
        waitForElementVisible(AdminPostSearchPageUI.TABLE_ROW_INDEX_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
        return isElementDisplayed(AdminPostSearchPageUI.TABLE_ROW_INDEX_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
    }

    public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {
        waitForElementClickable(AdminPostSearchPageUI.TABLE_ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
        clickToElement(AdminPostSearchPageUI.TABLE_ROW_TITLE_DETAIL_BY_TITLE_NAME, postTitle);
        return PageGeneratorManager.getAdminPostAddNewPage(driver);
    }

    public void selectPostCheckBoxByTitle(String editPostTitle) {
        waitForElementClickable(AdminPostSearchPageUI.ROW_CHECK_BOX_BY_TITLE_NAME, editPostTitle);
        checkTheCheckBoxOrRadio(AdminPostSearchPageUI.ROW_CHECK_BOX_BY_TITLE_NAME, editPostTitle);
    }

    public void selectTextItemInActionDropDown(String item) {
        waitForElementClickable(AdminPostSearchPageUI.ACTION_DROPDOWN);
        selectItemInDefaultDropDown(AdminPostSearchPageUI.ACTION_DROPDOWN, item);
    }

    public void clickApplyButton() {
        waitForElementClickable(AdminPostSearchPageUI.APPLY_BUTTON);
        clickToElement(AdminPostSearchPageUI.APPLY_BUTTON);
    }

    public boolean isMovedToTrashMessageDisplayed(String message) {
        waitForElementVisible(AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
        return isElementDisplayed(AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE, message);
    }

    public boolean isNoPostsFoundMessageDisplayed(String message) {
        waitForElementVisible(AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
        return isElementDisplayed(AdminPostSearchPageUI.NO_POSTS_FOUND_MESSAGE, message);
    }
}
