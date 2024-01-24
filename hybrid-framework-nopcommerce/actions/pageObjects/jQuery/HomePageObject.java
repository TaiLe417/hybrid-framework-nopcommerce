package pageObjects.jQuery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.HomePageUI;

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
}
