package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.nopCommerce.user.*;

public class Common_01_Register_End_User extends BaseTest {
    private WebDriver driver;
    public static String emailAddress, password;
    private String firstName, lastName;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObject;
    private UserCustomerInfoPageObject userCustomerInfoPageObject;
    private UserAddressPageObject addressPage;
    private UserMyProductReviewPageObject myProductsReviewPage;
    private UserRewardPointPageObject rewardPointpage;

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

        log.info("Register - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        log.info("Register - Step 02: Enter the Firstname textbox with value is '" + firstName + "'");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);

        log.info("Register - Step 03: Enter the Lastname textbox with value is '" + lastName + "'");
        userRegisterPageObject.inputToLastNameTextbox(lastName);

        log.info("Register - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
        userRegisterPageObject.inputToEmailTextbox(emailAddress);

        log.info("Register - Step 05: Enter the Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToPasswordTextbox(password);

        log.info("Register - Step 06: Enter the Confirm Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        log.info("Register - Step 07: Click to 'Register button'");
        userRegisterPageObject.clickRegisterButton();

        log.info("Register - Step 08: Verify register success message is displayed");
        verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");

        driver.quit();
    }


}
