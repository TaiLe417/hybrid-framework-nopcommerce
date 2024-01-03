package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private long longTimeout = 30;

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

    public void foreard(WebDriver driver) {
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

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String xpathLocator) {
        getElement(driver, xpathLocator).click();
    }

    public void sendKeyToElement(WebDriver driver, String xpathLocator, String textValue) {
        WebElement element = getElement(driver, xpathLocator);
        element.clear();
        element.sendKeys(textValue);
    }

    public String getTextElement(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).getText();
    }

    public void selectItemInDefaultDropDown(WebDriver driver, String xpathLocator, String textValue) {
        Select select = new Select(getElement(driver, xpathLocator));
        select.selectByValue(textValue);
    }

    public String getSelectItemInDefaultDropDown(WebDriver driver, String xpathLocator) {
        Select select = new Select(getElement(driver, xpathLocator));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropDown(WebDriver driver,
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

    public boolean isDropDownMultiple(WebDriver driver, String xpathLocator) {
        Select select = new Select(getElement(driver, xpathLocator));
        return select.isMultiple();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
        return getElement(driver, xpathLocator).getAttribute(attributeName);
    }

    public String getCssText(WebDriver driver, String xpathLocator, String propertyName) {
        return getElement(driver, xpathLocator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbValue) {
        return Color.fromString(rgbValue).asHex();
    }

    public int getElementsSize(WebDriver driver, String xpathLocator) {
        return getElements(driver, xpathLocator).size();
    }

    public void checkTheCheckBoxOrRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getElement(driver, xpathLocator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckTheCheckBoxOrRadio(WebDriver driver, String xpathLocator) {
        WebElement element = getElement(driver, xpathLocator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayd(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String xpathLocator) {
        return getElement(driver, xpathLocator).isSelected();
    }

    public void switchToFrameOrIframe(WebDriver driver, String xpathLocator) {
        driver.switchTo().frame(getElement(driver, xpathLocator));
    }

    public void switchToDefaultContent(WebDriver driver, String xpathLocator) {
        driver.switchTo().defaultContent();
    }

    public void moveToElement(WebDriver driver, String xpathLocator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(driver, xpathLocator)).perform();
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

    public void highlightElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    public void scrollToElement(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }


    public String getElementValidationMessage(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
    }

    public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
    }

    public void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, xpathLocator)));
    }
}