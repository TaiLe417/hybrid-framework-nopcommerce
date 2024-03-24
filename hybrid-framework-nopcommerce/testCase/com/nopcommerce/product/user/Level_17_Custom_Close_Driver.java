package com.nopcommerce.product.user;

import commons.BaseTest;
import commons.PageGeneratorManager;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;


public class Level_17_Custom_Close_Driver extends BaseTest {
    private WebDriver driver;
    private String emailAddress, firstName, lastName, password;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObject;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
        Faker faker = new Faker();

        emailAddress = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        password = "123456";
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);

        log.info("Pre-condition - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        log.info("Pre-condition  - Step 02: Enter the Firstname textbox with value is '" + firstName + "'");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);

        log.info("Pre-condition  - Step 03: Enter the Lastname textbox with value is '" + lastName + "'");
        userRegisterPageObject.inputToLastNameTextbox(lastName);

        log.info("Pre-condition  - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
        userRegisterPageObject.inputToEmailTextbox(emailAddress);

        log.info("Pre-condition  - Step 05: Enter the Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToPasswordTextbox(password);

        log.info("Pre-condition  - Step 06: Enter the Confirm Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        log.info("Pre-condition  - Step 07: Click to 'Register button'");
        userRegisterPageObject.clickRegisterButton();

        log.info("Pre-condition  - Step 08: Verify register success message is displayed");
        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed..");

        log.info("Login - Step 01:  Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        log.info("Login - Step 02: enter the Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToEmailTextBox(emailAddress);

        log.info("Login - Step 03: enter the Password textbox with value is '" + password + "'");
        userLoginPageObject.inputToPasswordTextBox(password);

        log.info("Login - Step 04: Click Log In button");
        userHomePageObject = userLoginPageObject.clickLogInButton();
    }

    @Test
    public void User_01_Register() {


    }

    @Test
    public void User_02_Login() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
