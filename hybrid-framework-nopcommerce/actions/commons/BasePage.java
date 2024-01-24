package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.nopCommerce.user.UserBasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private long longTimeout = 30;

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void back(WebDriver driver) {
        driver.navigate().back();
    }

    public void forWard(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert(WebDriver driver) {
        driver.switchTo().alert().dismiss();
    }

    public String getTextAlert(WebDriver driver) {
        return driver.switchTo().alert().getText();
    }

    public void sendKeyToAlert(WebDriver driver, String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    private By getByLocator(String locatorType) {
        By by = null;
//        System.out.println("Locator type = " + locatorType);
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported!");
        }
        return by;
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private String getDynamicLocator(String locatorType, String... values) {
        if ((locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath="))) {
            locatorType = String.format(locatorType, (Object[]) values);
        }
        return locatorType;
    }

    private WebElement getElement(WebDriver driver, String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getElements(WebDriver driver, String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public void clickToElement(WebDriver driver, String locatorType) {
        getElement(driver, locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
        getElement(driver, getDynamicLocator(locatorType, dynamicValues)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locatorTypeType, String textValue) {
        WebElement element = getElement(driver, locatorTypeType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeyToElement(WebDriver driver, String locatorTypeType, String textValue, String... dynamicValues) {
        WebElement element = getElement(driver, getDynamicLocator(locatorTypeType, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    public String getTextElement(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).getText();
    }

    public String getTextElement(WebDriver driver, String locatorType, String... dynamicValues) {
        return getElement(driver, getDynamicLocator(locatorType, dynamicValues)).getText();
    }

    public void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String textValue) {
        Select select = new Select(getElement(driver, locatorType));
        select.selectByValue(textValue);
    }

    public String getSelectItemInDefaultDropDown(WebDriver driver, String locatorType) {
        Select select = new Select(getElement(driver, locatorType));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropDown(WebDriver driver,
                                           String locatorType, String dropdownMenu, String expectedItem) {
        getElement(driver, locatorType).click();
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        List<WebElement> speedDropdownItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(dropdownMenu)));
        for (WebElement item : speedDropdownItem) {
            if (item.getText().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public boolean isDropDownMultiple(WebDriver driver, String locatorType) {
        Select select = new Select(getElement(driver, locatorType));
        return select.isMultiple();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getAttributeValue(WebDriver driver, String locatorType, String attributeName) {
        return getElement(driver, locatorType).getAttribute(attributeName);
    }

    public String getCssText(WebDriver driver, String locatorType, String propertyName) {
        return getElement(driver, locatorType).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbValue) {
        return Color.fromString(rgbValue).asHex();
    }

    public int getElementsSize(WebDriver driver, String locatorType) {
        return getElements(driver, locatorType).size();
    }

    public int getElementsSize(WebDriver driver, String locatorType, String... dynamicValues) {
        return getElements(driver, getDynamicLocator(locatorType, dynamicValues)).size();
    }

    public void checkTheCheckBoxOrRadio(WebDriver driver, String locatorType) {
        WebElement element = getElement(driver, locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckTheCheckBoxOrRadio(WebDriver driver, String locatorType) {
        WebElement element = getElement(driver, locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
        return getElement(driver, getDynamicLocator(locatorType, dynamicValues)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locatorType) {
        return getElement(driver, locatorType).isSelected();
    }

    public void switchToFrameOrIframe(WebDriver driver, String locatorType) {
        driver.switchTo().frame(getElement(driver, locatorType));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String locatorType) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(driver, locatorType)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys keys) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(driver, locatorType), keys).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locatorType, Keys keys, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(driver, getDynamicLocator(locatorType, dynamicValues)), keys).perform();
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locatorType));
    }

    public void scrollToElement(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locatorType));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locatorType, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locatorType));
    }

    public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locatorType));
    }

    public String getElementValidationMessage(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locatorType));
    }

    public boolean isImageLoaded(WebDriver driver, String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locatorType));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAllElementsVisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, locatorType)));
    }

    public void waitForAllElementsInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }


    public UserAddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.ADDRESS_LINK);
        clickToElement(driver, UserBasePageUI.ADDRESS_LINK);
        return PageGeneratorManager.getUserAddressPageObject(driver);
    }

    public UserMyProductReviewPageObject openMyProductsReviewPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
        clickToElement(driver, UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
        return PageGeneratorManager.getUserMyProductReviewPageObject(driver);
    }

    public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.REWARD_POINTS_LINK);
        clickToElement(driver, UserBasePageUI.REWARD_POINTS_LINK);
        return PageGeneratorManager.getUserRewardPointPageObject(driver);
    }

    public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
        waitForElementVisible(driver, UserBasePageUI.DYNAMIC_MY_ACCPOUNT_LINK, pageName);
        clickToElement(driver, UserBasePageUI.DYNAMIC_MY_ACCPOUNT_LINK, pageName);
        switch (pageName) {
            case "Customer info":
                return PageGeneratorManager.getUserCustomerInfoPageObject(driver);
            case "Addresses":
                return PageGeneratorManager.getUserAddressPageObject(driver);
            case "Reward points":
                return PageGeneratorManager.getUserRewardPointPageObject(driver);
            case "My product reviews":
                return PageGeneratorManager.getUserMyProductReviewPageObject(driver);
            default:
                throw new RuntimeException("Invalid page name at My Account area");
        }
    }

    public void openPageAtMyAccountName(WebDriver driver, String pageName) {
        waitForElementVisible(driver, UserBasePageUI.DYNAMIC_MY_ACCPOUNT_LINK, pageName);
        clickToElement(driver, UserBasePageUI.DYNAMIC_MY_ACCPOUNT_LINK, pageName);
    }

}