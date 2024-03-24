package com.nopcommerce.product.user;

//import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import commons.PageGeneratorManager;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class Level_15_ExtendV2_Screenshot extends BaseTest {
    WebDriver driver;
    String emailAddress, firstName, lastName, password;
    UserHomePageObject userHomePageObject;
    UserRegisterPageObject userRegisterPageObject;
    UserLoginPageObject userLoginPageObject;
    UserCustomerInfoPageObject userCustomerInfoPageObject;
    UserAddressPageObject addressPage;
    UserMyProductReviewPageObject myProductsReviewPage;
    UserRewardPointPageObject rewardPointpage;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
        Faker faker = new Faker();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");

        emailAddress = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        password = "123456";
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);


//        System.out.println("Pre-condition Step 5: Log out");
//        Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_01_Register(Method method) {
//        ExtentManagerV5.startTest(method.getName(), "User_01_Register");
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to 'Register' page");
//        userRegisterPageObject = userHomePageObject.clickRegisterLink();
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 02: Enter the Firstname textbox with value is '" + firstName + "'");
//        userRegisterPageObject.inputToFirstNameTextbox(firstName);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 03: Enter the Lastname textbox with value is '" + lastName + "'");
//        userRegisterPageObject.inputToLastNameTextbox(lastName);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
//        userRegisterPageObject.inputToEmailTextbox(emailAddress);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 05: Enter the Password textbox with value is '" + password + "'");
//        userRegisterPageObject.inputToPasswordTextbox(password);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 06: Enter the Confirm Password textbox with value is '" + password + "'");
//        userRegisterPageObject.inputToConfirmPasswordTextbox(password);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 07: Click to 'Register button'");
//        userRegisterPageObject.clickRegisterButton();
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Register - Step 08: Verify register success message is displayed");
//        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed.");
//        ExtentManagerV5.endTest();
    }

    @Test
    public void User_02_Login(Method method) {
//        ExtentManagerV5.startTest(method.getName(), "User_02_Login");
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Login - Step 01:  Navigate to Login page");
//        userLoginPageObject = userHomePageObject.clickLogInLink();
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Login - Step 02: enter the Email textbox with value is '" + emailAddress + "'");
//        userLoginPageObject.inputToEmailTextBox(emailAddress);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Login - Step 03: enter the Password textbox with value is '" + password + "'");
//        userLoginPageObject.inputToPasswordTextBox(password);
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Login - Step 04: Click Log In button");
//        userHomePageObject = userLoginPageObject.clickLogInButton();
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
//        Assert.assertFalse(userHomePageObject.isMyAccountDisplayed());
//
//        ExtentManagerV5.getTest().log(LogStatus.INFO, "Login - Step 06: Navigate to 'My Account page'");
//        userCustomerInfoPageObject = userHomePageObject.clickToMyAccountLink();
//        ExtentManagerV5.endTest();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
