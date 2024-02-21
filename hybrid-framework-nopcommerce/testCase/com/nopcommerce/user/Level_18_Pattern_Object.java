package com.nopcommerce.user;

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

import java.time.Duration;

public class Level_18_Pattern_Object extends BaseTest {
    private WebDriver driver;
    private String emailAddress, firstName, lastName, password, day, month, year;
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
        day = "12";
        month = "May";
        year = "1995";


//        System.out.println("Pre-condition Step 5: Log out");
//        Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_01_Register() {
        log.info("Register - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        userRegisterPageObject.clickToRadioButtonByLabel(driver,"Male");

        log.info("Register - Step 02: Enter the Firstname textbox with value is '" + firstName + "'");
//        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToTextboxByID(driver, "FirstName", firstName);

        log.info("Register - Step 03: Enter the Lastname textbox with value is '" + lastName + "'");
//        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToTextboxByID(driver, "LastName", lastName);

        userRegisterPageObject.selectToDropDownByName(driver, "DateOfBirthDay", day);
        userRegisterPageObject.selectToDropDownByName(driver, "DateOfBirthMonth", month);
        userRegisterPageObject.selectToDropDownByName(driver, "DateOfBirthYear", year);

        log.info("Register - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
//        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToTextboxByID(driver, "Email", emailAddress);

        userRegisterPageObject.clickToCheckboxByLabel(driver,"Newsletter");

        log.info("Register - Step 05: Enter the Password textbox with value is '" + password + "'");
//        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToTextboxByID(driver, "Password", password);

        log.info("Register - Step 06: Enter the Confirm Password textbox with value is '" + password + "'");
//        userRegisterPageObject.inputToConfirmPasswordTextbox(password);
        userRegisterPageObject.inputToTextboxByID(driver, "ConfirmPassword", password);

        log.info("Register - Step 07: Click to 'Register button'");
        userRegisterPageObject.clickToButtonByText(driver, "Register");

        log.info("Register - Step 08: Verify register success message is displayed");
        verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {

        log.info("Login - Step 01:  Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        log.info("Login - Step 02: enter the Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToTextboxByID(driver, "Email", emailAddress);

        log.info("Login - Step 03: enter the Password textbox with value is '" + password + "'");
        userLoginPageObject.inputToTextboxByID(driver, "Password", password);

        log.info("Login - Step 04: Click Log In button");
        userLoginPageObject.clickToButtonByText(driver, "Log in");
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);


        log.info("Login - Step 05: Verify 'My Account' link is displayed");
        verifyTrue(userHomePageObject.isMyAccountDisplayed());


    }

    @Test
    public void User_03_MyAccount() {
        log.info("My Account - Step 01: Navigate to 'My Account page'");
        userCustomerInfoPageObject = userHomePageObject.clickToMyAccountLink();

        log.info("My Account - Step 02: Customer Info page is displayed");
        Assert.assertTrue(userCustomerInfoPageObject.isCustomerInfoDisplayed());

        log.info("My Account - Step 03: Verify 'First Name' value is correctly");
        Assert.assertEquals(userCustomerInfoPageObject.getTextboxValueByID(driver,"FirstName"), firstName);

        log.info("My Account - Step 04: Verify 'Last Name' value is correctly");
        Assert.assertEquals(userCustomerInfoPageObject.getTextboxValueByID(driver,"LastName"), lastName);

        log.info("My Account - Step 01:  Verify 'Email' value is correctly");
        Assert.assertEquals(userCustomerInfoPageObject.getTextboxValueByID(driver,"Email"), emailAddress);

    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();
    }
}
