package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.AddressPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.PageGeneratorManager;
import pageObjects.RewardPointPageObject;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private long longTimeout = 30;

    protected static BasePage getBasePageObject() {
        return new BasePage();
    }

    protected void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    protected String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void back(WebDriver driver) {
        driver.navigate().back();
    }

    protected void foreard(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    protected void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    protected String getTextAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    protected void sendKeyToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    protected void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    protected void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    protected void clickToElement(WebDriver driver, String xpathLocator) {
        getElement(driver, xpathLocator).click();
    }

    protected void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = getElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }

    protected String getTextElement(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).getText();
    }

    protected void selectItemInDefaultDropDown(WebDriver driver, String xpathLocator, String textValue) {
        Select select = new Select(getElement(driver, xpathLocator));
        select.selectByValue(textValue);
    }

    protected String getSelectItemInDefaultDropDown(WebDriver driver, String xpathLocator) {
        Select select = new Select(getElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }

    protected void selectItemInCustomDropDown(WebDriver driver,
                                              String xpathLocator, String dropdownMenu, String expectedItem) {
        getElement(driver, xpathLocator).click();
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        List<WebElement> speedDropdownItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(dropdownMenu)));
        for (WebElement item : speedDropdownItem) {
            if (item.getText().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    protected boolean isDropDownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(getElement(driver, xpathLocator));
        return select.isMultiple();
    }

    protected void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
        return getElement(driver, xpathLocator).getAttribute(attributeName);
    }

    protected String getCssText(WebDriver driver, String xpathLocator, String propertyName) {
        return getElement(driver, xpathLocator).getCssValue(propertyName);
    }

    protected String getHexaColorFromRGBA(String rgbValue) {
        return Color.fromString(rgbValue).asHex();
    }

    protected int getElementsSize(WebDriver driver, String xpathLocator) {
        return getElements(driver, xpathLocator).size();
    }

    protected void checkTheCheckBoxOrRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getElement(driver, xpathLocator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckTheCheckBoxOrRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getElement(driver, xpathLocator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayd(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isDisplayed();
    }

    protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isSelected();
    }

    protected void switchToFrameOrIframe(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getElement(driver, xpathLocator));
    }

    protected void switchToDefaultContent(WebDriver driver, String xpathLocator) {
        driver.switchTo().defaultContent();
    }

    protected void moveToElement(WebDriver driver, String xpathLocator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(driver, xpathLocator)).perform();
    }


    protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    protected void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    protected void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    protected String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, xpathLocator)));
    }

    protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
    }

    public AddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
        return PageGeneratorManager.getAddressPageObject(driver);
    }

    public MyProductReviewPageObject openMyProductsReviewPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
        clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEWS_LINK);
        return PageGeneratorManager.getMyProductReviewPageObject(driver);
    }

    public RewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.REWARD_POINTS_LINK);
        clickToElement(driver, BasePageUI.REWARD_POINTS_LINK);
        return PageGeneratorManager.getRewardPointPageObject(driver);
    }

}