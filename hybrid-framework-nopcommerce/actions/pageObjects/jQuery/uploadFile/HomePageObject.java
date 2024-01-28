package pageObjects.jQuery.uploadFile;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.uploadFile.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public boolean isFileLinkUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons = getElements(driver, HomePageUI.START_BUTTON);
        for (WebElement startButton : startButtons) {
            startButton.click();
        }
    }

    public boolean isFileImgUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
        return isImageLoaded(driver, HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
    }
}
