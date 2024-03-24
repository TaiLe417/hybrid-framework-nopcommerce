package pageObjects.jQuery.uploadFile;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jQuery.uploadFile.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(HomePageUI.FILE_NAME_LOADED, fileName);
        return isElementDisplayed(HomePageUI.FILE_NAME_LOADED, fileName);
    }

    public boolean isFileLinkUploadedByName(String fileName) {
        waitForElementVisible(HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
        return isElementDisplayed(HomePageUI.FILE_NAME_UPLOADED_LINK, fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtons = getElements(HomePageUI.START_BUTTON);
        for (WebElement startButton : startButtons) {
            startButton.click();
        }
    }

    public boolean isFileImgUploadedByName(String fileName) {
        waitForElementVisible(HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
        return isImageLoaded(HomePageUI.FILE_NAME_UPLOADED_IMG, fileName);
    }
}
