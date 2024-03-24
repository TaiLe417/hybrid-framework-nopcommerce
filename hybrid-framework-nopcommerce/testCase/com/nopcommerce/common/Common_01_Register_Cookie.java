package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import net.datafaker.Faker;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.nopCommerce.user.*;

import java.util.Set;

public class Common_01_Register_Cookie extends BaseTest {
    private WebDriver driver;
    public static String emailAddress, password;
    private String firstName, lastName;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObject;
    public static Set<Cookie> loggedCookies;

    @Parameters("browser")
    @BeforeTest(description = "Create new user for all class test")
    public void Register(String browserName) {
        driver = getBrowserName(browserName);
        Faker faker = new Faker();
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);

        emailAddress = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        password = "123456";

        log.info("Pre-condition - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        log.info("Pre-condition - Step 02: Enter the Firstname textbox with value is '" + firstName + "'");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);

        log.info("Pre-condition - Step 03: Enter the Lastname textbox with value is '" + lastName + "'");
        userRegisterPageObject.inputToLastNameTextbox(lastName);

        log.info("Pre-condition - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
        userRegisterPageObject.inputToEmailTextbox(emailAddress);

        log.info("Pre-condition - Step 05: Enter the Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToPasswordTextbox(password);

        log.info("Pre-condition - Step 06: Enter the Confirm Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        log.info("Pre-condition - Step 07: Click to 'Register button'");
        userRegisterPageObject.clickRegisterButton();

        log.info("Pre-condition - Step 08: Verify register success message is displayed");
        verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

        log.info("Pre-condition - Step 09:  Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        log.info("Pre-condition - Step 10: enter the Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToEmailTextBox(emailAddress);

        log.info("Pre-condition - Step 11: enter the Password textbox with value is '" + password + "'");
        userLoginPageObject.inputToPasswordTextBox(password);

        log.info("Pre-condition - Step 12: Click Log In button");
        userHomePageObject = userLoginPageObject.clickLogInButton();

        loggedCookies = userHomePageObject.getAllCookies();

    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
