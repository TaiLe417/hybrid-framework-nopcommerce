package com.nopcommerce.product.user;

//import com.relevantcodes.extentreports.LogStatus;
import com.aventstack.extentreports.Status;
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
import reportConfig.ExtentTestManagerV5;

import java.lang.reflect.Method;
import java.time.Duration;

public class Level_15_ExtendV5_Screenshot extends BaseTest {
    private WebDriver driver;
    private String emailAddress, firstName, lastName, password;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObject;
    private UserCustomerInfoPageObject userCustomerInfoPageObject;
    private UserAddressPageObject addressPage;
    private UserMyProductReviewPageObject myProductsReviewPage;
    private UserRewardPointPageObject rewardPointpage;


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
    }

    @Test
    public void User_01_Register(Method method) {
        ExtentTestManagerV5.startTest(method.getName(), "Register to system with valid Email and Password");
        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 02: Enter to Firstname textbox with value is '" + firstName + "'");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
        userRegisterPageObject.inputToLastNameTextbox(lastName);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 04: Enter to Email textbox with value is '" + emailAddress + "'");
        userRegisterPageObject.inputToEmailTextbox(emailAddress);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToPasswordTextbox(password);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 07: Click to 'Register' button");
        userRegisterPageObject.clickRegisterButton();

        ExtentTestManagerV5.getTest().log(Status.INFO, "Register - Step 08: Verify register success message is displayed");
        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login(Method method) {
        ExtentTestManagerV5.startTest(method.getName(), "Login to system successfully");
        ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 01: Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToEmailTextBox(emailAddress);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is '" + password + "'");
        userLoginPageObject.inputToPasswordTextBox(password);

        ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 04: Click to Login button");
        userHomePageObject = userLoginPageObject.clickLogInButton();

        ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
        Assert.assertFalse(userHomePageObject.isMyAccountDisplayed());

        ExtentTestManagerV5.getTest().log(Status.INFO, "Login - Step 06: Navigate to 'My Account' page");
        userCustomerInfoPageObject = userHomePageObject.clickToMyAccountLink();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
