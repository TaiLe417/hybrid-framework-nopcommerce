package pageFactory;

import commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends BasePageFactory {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-register']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[@class='ico-login']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[@class='ico-account']")
    private WebElement myAccountLink;

    public void clickRegisterLink() {
        waitForElementClickable(driver, registerLink);
        clickToElement(registerLink);
//        return new RegisterPageObject(driver);
//        return PageGeneratorManager.getRegisterPage(driver);
    }

    public void clickLogInLink() {
        waitForElementClickable(driver, loginLink);
        clickToElement(loginLink);
//        return new LoginPageObject(driver);
//        return PageGeneratorManager.getLoginPageObject(driver);
    }

    public boolean isMyAccountDisplayed() {
        waitForElementVisible(driver, myAccountLink);
        return isElementDisplayed(myAccountLink);
    }
}
