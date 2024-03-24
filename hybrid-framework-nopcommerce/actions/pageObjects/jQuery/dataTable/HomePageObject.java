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
        super(driver);
        this.driver = driver;
    }

    public void openPagingByPageNumber(String pageNumber) {
        waitForElementClickable(HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
        clickToElement(HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
    }

    public void enterToHeaderTextboxByLabel(String headerLabel, String text) {
        waitForElementVisible(HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
        sendKeyToElement(HomePageUI.HEADER_TEXTBOX_BY_LABEL, text, headerLabel);
        pressKeyToElement(HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
    }

    public boolean isPageNumberActivated(String pageNumber) {
        waitForElementVisible(HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
        return isElementDisplayed(HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
    }

    public List getValueEachRowAtAllPage() {
        int totalPages = getElementsSize(HomePageUI.TOTAL_PAGINATION);
        System.out.println("Total pages: " + totalPages);
        List<String> allRowValueAllPage = new ArrayList<String>();
        for (int i = 1; i <= totalPages; i++) {
            clickToElement(HomePageUI.PAGINATION_PAGE_INDEX, String.valueOf(i));
            List<WebElement> allRowEachPage = getElements(HomePageUI.ALL_ROW_EACH_PAGE);
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
        int columnIndex = getElementsSize(HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        //Sendkey vao dong nao
        waitForElementVisible(HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        sendKeyToElement(HomePageUI.TEXTBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowName, String.valueOf(columnIndex));
    }

    public void selectDropDownByColumnNameAtRowNumber(String columnName, String rowName, String value) {
        int columnIndex = getElementsSize(HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        selectItemInDefaultDropDown(HomePageUI.DROPDOWN_BY_COLUMN_INDEX_AND_ROW_INDEX, value, rowName, String.valueOf(columnIndex));
    }

    public void clickLoadButton() {
        waitForElementClickable(HomePageUI.LOAD_BUTTON);
        clickToElement(HomePageUI.LOAD_BUTTON);
    }

    public void checkToCheckBoxByColumnNameAtRowNumber(String columnName, String rowName) {
        int columnIndex = getElementsSize(HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        checkTheCheckBoxOrRadio(HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
    }

    public void unCheckToCheckBoxByColumnNameAtRowNumber(String columnName, String rowName) {
        int columnIndex = getElementsSize(HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;

        waitForElementClickable(HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
        unCheckTheCheckBoxOrRadio(HomePageUI.CHECKBOX_BY_COLUMN_INDEX_AND_ROW_INDEX, rowName, String.valueOf(columnIndex));
    }

    public void clickToIconByRowNumber(String columnName, String iconName) {
        waitForElementClickable(HomePageUI.ICON_BY_COLUMN_INDEX_AND_ROW_INDEX, columnName, iconName);
        clickToElement(HomePageUI.ICON_BY_COLUMN_INDEX_AND_ROW_INDEX, columnName, iconName);
    }
}
