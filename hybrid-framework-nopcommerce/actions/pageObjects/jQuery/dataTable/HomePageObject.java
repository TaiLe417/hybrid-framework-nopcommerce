package pageObjects.jQuery.dataTable;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.dataTable.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openPagingByPageNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
        clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
    }

    public void enterToHeaderTextboxByLabel(String headerLabel, String text) {
        waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
        sendKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, text, headerLabel);
        pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
    }

    public boolean isPageNumberActivated(String pageNumber) {
        waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
        return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
    }

    public List getValueEachRowAtAllPage() {
        int totalPages = getElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
        System.out.println("Total pages: " + totalPages);
        List<String> allRowValueAllPage = new ArrayList<String>();
        for (int i = 1; i <= totalPages; i++) {
            clickToElement(driver, HomePageUI.PAGINATION_PAGE_INDEX, String.valueOf(i));
            List<WebElement> allRowEachPage = getElements(driver, HomePageUI.ALL_ROW_EACH_PAGE);
            for (WebElement row : allRowEachPage) {
                allRowValueAllPage.add(row.getText());
            }
        }
        for (String value : allRowValueAllPage) {
            System.out.println(value);
        }
        return allRowValueAllPage;
    }

    public void enterToTextboxByColumnNameAtRowNumber(String columnName, String rowName, String value) {
        //Column index dua vao ten cot
        int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        //Sendkey vao dong nao
        waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        sendKeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowName, String.valueOf(columnIndex));
    }

    public void selectDropDownByColumnNameAtRowNumber(String columnName, String rowName, String value) {
        int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        selectItemInDefaultDropDown(driver, HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowName, String.valueOf(columnIndex));
    }

    public void clickLoadButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_BUTTON);
        clickToElement(driver, HomePageUI.LOAD_BUTTON);
    }

    public void checkToCheckBoxByColumnNameAtRowNumber(String columnName, String rowName) {
        int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        checkTheCheckBoxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
    }

    public void unCheckToCheckBoxByColumnNameAtRowNumber(String columnName, String rowName) {
        int columnIndex = getElementsSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        unCheckTheCheckBoxOrRadio(driver, HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
    }

    public void clickToIconByRowNumber(String columnName, String iconName) {
        waitForElementClickable(driver, HomePageUI.ICON_BY_COLUMN_INDEX_AND_ROW_INDEX, columnName, iconName);
        clickToElement(driver, HomePageUI.ICON_BY_COLUMN_INDEX_AND_ROW_INDEX, columnName, iconName);
    }
}
