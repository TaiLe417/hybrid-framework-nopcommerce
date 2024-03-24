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
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.UserHomePO;
import pageUIs.jQuery.uploadFile.BasePageJQuery;
import pageUIs.nopCommerce.user.UserBasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private final WebDriver driver;
    private final long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private final long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageUrl(String url) {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void back() {
        driver.navigate().back();
    }

    public void forWard() {
        driver.navigate().forward();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void cancelAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getTextAlert() {
        return driver.switchTo().alert().getText();
    }

    public void sendKeyToAlert(String value) {
        driver.switchTo().alert().sendKeys(value);
    }

    public void switchToWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(String parentID) {
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

    private WebElement getElement(String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public List<WebElement> getElements(String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    public void clickToElement(String locatorType) {
        getElement(locatorType).click();
    }

    public void clickToElement(String locatorType, String... dynamicValues) {
        getElement(getDynamicLocator(locatorType, dynamicValues)).click();
    }

    public void sendKeyToElement(String locatorTypeType, String textValue) {
        WebElement element = getElement(locatorTypeType);
        element.clear();
        element.sendKeys(textValue);
    }

    public void clearValueInElementByPressKey(String locatorTypeType) {
        WebElement element = getElement(locatorTypeType);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
    }

    public void sendKeyToElement(String locatorTypeType, String textValue, String... dynamicValues) {
        WebElement element = getElement(getDynamicLocator(locatorTypeType, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    public String getTextElement(String locatorType) {
        return getElement(locatorType).getText();
    }

    public String getTextElement(String locatorType, String... dynamicValues) {
        return getElement(getDynamicLocator(locatorType, dynamicValues)).getText();
    }

    public void selectItemInDefaultDropDown(String locatorType, String textValue) {
        Select select = new Select(getElement(locatorType));
        select.selectByVisibleText(textValue);
    }

    public void selectItemInDefaultDropDown(String locatorType, String textValue, String... dynamicValues) {
        Select select = new Select(getElement(getDynamicLocator(locatorType, dynamicValues)));
        select.selectByVisibleText(textValue);
    }

    public String getSelectItemInDefaultDropDown(String locatorType) {
        Select select = new Select(getElement(locatorType));
        return select.getFirstSelectedOption().getText();
    }

    public void selectItemInCustomDropDown(String locatorType, String dropdownMenu, String expectedItem) {
        getElement(locatorType).click();
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        List<WebElement> speedDropdownItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(dropdownMenu)));
        for (WebElement item : speedDropdownItem) {
            if (item.getText().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public boolean isDropDownMultiple(String locatorType) {
        Select select = new Select(getElement(locatorType));
        return select.isMultiple();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getAttributeValue(String locatorType, String attributeName) {
        return getElement(locatorType).getAttribute(attributeName);
    }

    public String getAttributeValue(String locatorType, String attributeName, String... dynamicValues) {
        return getElement(getDynamicLocator(locatorType, dynamicValues)).getAttribute(attributeName);
    }

    public String getCssText(String locatorType, String propertyName) {
        return getElement(locatorType).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbValue) {
        return Color.fromString(rgbValue).asHex();
    }

    public int getElementsSize(String locatorType) {
        return getElements(locatorType).size();
    }

    public int getElementsSize(String locatorType, String... dynamicValues) {
        return getElements(getDynamicLocator(locatorType, dynamicValues)).size();
    }

    public void checkTheCheckBoxOrRadio(String locatorType) {
        WebElement element = getElement(locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkTheCheckBoxOrRadio(String locatorType, String... dynamicValues) {
        WebElement element = getElement(getDynamicLocator(locatorType, dynamicValues));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckTheCheckBoxOrRadio(String locatorType) {
        WebElement element = getElement(locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void unCheckTheCheckBoxOrRadio(String locatorType, String... dynamicValues) {
        WebElement element = getElement(getDynamicLocator(locatorType, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayed(String locatorType) {
        return getElement(locatorType).isDisplayed();
    }

    public boolean isElementUndisplayed(String locatorType) {
        overrideImplicitTimeOut(shortTimeout);
        List<WebElement> elements = getElements(locatorType);
        overrideImplicitTimeOut(longTimeout);
        if (elements.size() == 0) {
            return true;
        } else return elements.size() > 0 && !elements.get(0).isDisplayed();
    }

    public boolean isElementUndisplayed(String locatorType, String... dynamicValues) {
        overrideImplicitTimeOut(shortTimeout);
        List<WebElement> elements = getElements(getDynamicLocator(locatorType, dynamicValues));
        overrideImplicitTimeOut(longTimeout);
        if (elements.size() == 0) {
            return true;
        } else return elements.size() > 0 && !elements.get(0).isDisplayed();
    }

    public void overrideImplicitTimeOut(long timeOut) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }

    public boolean isElementDisplayed(String locatorType, String... dynamicValues) {
        return getElement(getDynamicLocator(locatorType, dynamicValues)).isDisplayed();
    }

    public boolean isElementEnabled(String locatorType) {
        return getElement(locatorType).isEnabled();
    }

    public boolean isElementSelected(String locatorType) {
        return getElement(locatorType).isSelected();
    }

    public void switchToFrameOrIframe(String locatorType) {
        driver.switchTo().frame(getElement(locatorType));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void moveToElement(String locatorType) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement(locatorType)).perform();
    }

    public void pressKeyToElement(String locatorType, Keys keys) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(locatorType), keys).perform();
    }

    public void pressKeyToElement(String locatorType, Keys keys, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getElement(getDynamicLocator(locatorType, dynamicValues)), keys).perform();
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void highlightElement(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(locatorType));
    }

    public String getElementByJSXpath(String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.replace("xpath=", "");
        return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()");
    }

    public void scrollToElementByJS(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locatorType));
    }

    public void sendkeyToElementByJS(String locatorType, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locatorType));
    }

    public void removeAttributeInDOM(String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locatorType));
    }

    public String getElementValidationMessage(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locatorType));
    }

    public boolean isImageLoaded(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locatorType));
        return status;
    }

    public boolean isImageLoaded(String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(getDynamicLocator(locatorType, dynamicValues)));
        return status;
    }

    public void uploadFile(String... fileNames) {
        //Dường dẫn thư mục upload File
        String filePath = GlobalConstants.UPLOAD_FILE;
        String fullName = "";
        for (String file : fileNames) {
            fullName = fullName + filePath + file + "\n";
        }
        fullName = fullName.trim();
        getElement(BasePageJQuery.UPLOAD_FILE).sendKeys(fullName);
    }

    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    public void waitForElementVisible(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForAllElementsVisible(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitForAllElementsVisible(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForElementInvisible(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementUndisplayed(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
        overrideImplicitTimeOut(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeOut(longTimeout);
    }

    public void waitForElementInvisible(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForAllElementsInvisible(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(locatorType)));
    }

    public void waitForAllElementsInvisible(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(getDynamicLocator(locatorType, dynamicValues))));
    }

    public void waitForElementClickable(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }


    public UserAddressPageObject openAddressPage() {
        waitForElementClickable(UserBasePageUI.ADDRESS_LINK);
        clickToElement(UserBasePageUI.ADDRESS_LINK);
        return PageGeneratorManager.getUserAddressPageObject(driver);
    }

    public UserMyProductReviewPageObject openMyProductsReviewPage() {
        waitForElementClickable(UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
        clickToElement(UserBasePageUI.MY_PRODUCT_REVIEWS_LINK);
        return PageGeneratorManager.getUserMyProductReviewPageObject(driver);
    }

    public UserRewardPointPageObject openRewardPointPage() {
        waitForElementClickable(UserBasePageUI.REWARD_POINTS_LINK);
        clickToElement(UserBasePageUI.REWARD_POINTS_LINK);
        return PageGeneratorManager.getUserRewardPointPageObject(driver);
    }

    public BasePage openPageAtMyAccountByName(String pageName) {
        waitForElementVisible(UserBasePageUI.DYNAMIC_MY_ACCOUNT_LINK, pageName);
        clickToElement(UserBasePageUI.DYNAMIC_MY_ACCOUNT_LINK, pageName);
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

    public void openPageAtMyAccountName(String pageName) {
        waitForElementVisible(UserBasePageUI.DYNAMIC_MY_ACCOUNT_LINK, pageName);
        clickToElement(UserBasePageUI.DYNAMIC_MY_ACCOUNT_LINK, pageName);
    }

    /**
     * Enter to dynamic Textbox by ID
     *  @param textboxID
     * @param value
     */
    public void inputToTextboxByID(String textboxID, String value) {
        waitForElementVisible(UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        sendKeyToElement(UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
    }

    /**
     * Click to dynamic Button by text
     *
     * @param buttonText
     */
    public void clickToButtonByText(String buttonText) {
        waitForElementClickable(UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
        clickToElement(UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
    }

    /**
     * Select dropdown by Name attribute
     *  @param dropDownAttributeName
     * @param itemValue
     */
    public void selectToDropDownByName(String dropDownAttributeName, String itemValue) {
        waitForElementClickable(UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, dropDownAttributeName);
        selectItemInDefaultDropDown(UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropDownAttributeName);
    }

    /**
     * Click to Radio button by Label
     *
     */
    public void clickToRadioButtonByLabel(String radiobutton) {
        waitForElementClickable(UserBasePageUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radiobutton);
        checkTheCheckBoxOrRadio(UserBasePageUI.DYNAMIC_RADIOBUTTON_BY_LABEL, radiobutton);
    }

    /**
     * Click to checkbox by Label
     *
     * @param checkboxLabelName
     */
    public void clickToCheckboxByLabel(String checkboxLabelName) {
        waitForElementClickable(UserBasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
        checkTheCheckBoxOrRadio(UserBasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
    }

    /**
     * Get value in textbox by textboxID
     *
     * @param textboxID
     * @return
     */
    public String getTextboxValueByID(String textboxID) {
        waitForElementVisible(UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
        return getAttributeValue(UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
    }

    public UserHomePO openEndUserSite(String urlUser) {
        openPageUrl(urlUser);
        return pageObjects.wordpress.PageGeneratorManager.getUserHomePage(driver);
    }

    public AdminDashboardPO openAdminSite(String urlAdmin) {
        openPageUrl(urlAdmin);
        return pageObjects.wordpress.PageGeneratorManager.getAdminDashboardPage(driver);
    }
}