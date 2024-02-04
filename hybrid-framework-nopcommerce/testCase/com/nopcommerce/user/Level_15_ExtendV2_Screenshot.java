package com.nopcommerce.user;

import com.relevantcodes.extentreports.LogStatus;
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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;
import java.time.Duration;

public class Level_15_ExtendV2_Screenshot extends BaseTest {
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
        ExtentManager.startTest(method.getName(), "User_01_Register");
        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02: Enter the Firstname textbox with value is '" + firstName + "'");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03: Enter the Lastname textbox with value is '" + lastName + "'");
        userRegisterPageObject.inputToLastNameTextbox(lastName);

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
        userRegisterPageObject.inputToEmailTextbox(emailAddress);

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05: Enter the Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToPasswordTextbox(password);

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter the Confirm Password textbox with value is '" + password + "'");
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 07: Click to 'Register button'");
        userRegisterPageObject.clickRegisterButton();

        ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 08: Verify register success message is displayed");
        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed.");
        ExtentManager.endTest();
    }

    @Test
    public void User_02_Login(Method method) {
        ExtentManager.startTest(method.getName(), "User_02_Login");
        ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 01:  Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 02: enter the Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToEmailTextBox(emailAddress);

        ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 03: enter the Password textbox with value is '" + password + "'");
        userLoginPageObject.inputToPasswordTextBox(password);

        ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 04: Click Log In button");
        userHomePageObject = userLoginPageObject.clickLogInButton();

        ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 05: Verify 'My Account' link is displayed");
        Assert.assertFalse(userHomePageObject.isMyAccountDisplayed());

        ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 06: Navigate to 'My Account page'");
        userCustomerInfoPageObject = userHomePageObject.clickToMyAccountLink();
        ExtentManager.endTest();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
