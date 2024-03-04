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

    public void clickToSearchPostTextBox() {
        waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_BUTTON);
        clickToElement(driver, AdminPostSearchPageUI.SEARCH_BUTTON);
    }

    public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
        int headerIndex = getElementsSize(driver, AdminPostSearchPageUI.TABLE_HEADER_INDEX_BY_HEADER_NAME, headerID) + 1;
        waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_ROW_INDEX_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
        return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_INDEX_BY_HEADER_INDEX, String.valueOf(headerIndex), cellValue);
    }

}
