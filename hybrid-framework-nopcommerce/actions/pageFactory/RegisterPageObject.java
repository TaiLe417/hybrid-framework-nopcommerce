package pageFactory;

import commons.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePageFactory {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "FirstName-error")
    private WebElement firstNameError;

    @FindBy(id = "LastName-error")
    private WebElement lastNameError;

    @FindBy(id = "Email-error")
    private WebElement emailError;

    @FindBy(id = "Password-error")
    private WebElement passwordError;

    @FindBy(id = "ConfirmPassword-error")
    private WebElement confirmPasswordError;

    @FindBy(xpath = "//div[contains(@class,'message-error')]")
    private WebElement wrongEmailError;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement registerSuccessMessage;

    @FindBy(xpath = "//div[contains(@class,'message-error')]")
    private WebElement emailExistMessage;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    public void clickRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(registerButton);
    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(lastNameTextbox, lastName);
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(emailTextbox, emailAddress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox, password);
    }

    public void inputToConfirmPasswordTextbox(String confirmPassword) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToElement(confirmPasswordTextbox, confirmPassword);
    }

    public String getFirstNameErrorMessage() {
        return getTextElement(firstNameError);
    }

    public String getLastNameErrorMessage() {
        return getTextElement(lastNameError);
    }

    public String getEmailErrorMessage() {
        return getTextElement(emailError);
    }

    public String getWrongEmailErrorMessage() {
        return getTextElement(wrongEmailError);
    }

    public String getPasswordErrorMessage() {
        return getTextElement(passwordError);
    }

    public String getConfirmPasswordErrorMessage() {
        return getTextElement(confirmPasswordError);
    }


    public String getRegisterSuccessMessage() {
        return getTextElement(registerSuccessMessage);
    }

    public String getEmailExistMessage() {
        return getTextElement(emailExistMessage);
    }
}
