package pageFactory;

import commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "Email-error")
    private WebElement emailErrorMessaage;

    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
    private WebElement unsuccessfulErrorMessage;

    public void clickLogInButton() {
        waitForElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);
    }

    public void inputToEmailTextBox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(driver, emailTextbox, emailAddress);
    }

    public void inputToPasswordTextBox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(driver, passwordTextbox, password);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, emailErrorMessaage);
        return getTextElement(driver, emailErrorMessaage);
    }

    public String getUnsuccessfulErrorMessage() {
        waitForElementVisible(driver, unsuccessfulErrorMessage);
        return getTextElement(driver, unsuccessfulErrorMessage);
    }

}
