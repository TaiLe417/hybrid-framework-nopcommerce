package com.nopcommerce.user;

import com.nopcommmerce.data.UserDataMapper;
import commons.BaseTest;
import commons.PageGeneratorManager;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_21_Multiple_Environment_Parameter extends BaseTest {
    UserDataMapper userData;
    private WebDriver driver;
    private String emailAddress;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObject;
    private UserCustomerInfoPageObject userCustomerInfoPageObject;


    @Parameters({"browser", "environment"})
    @BeforeClass
    public void beforeClass(String browserName, String environmentName) {
        driver = getBrowserName(browserName, environmentName);
        Faker faker = new Faker();
        userData = UserDataMapper.getUserData();
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);
        emailAddress = userData.getEmailAddress() + faker.number().randomNumber() + "@fakemail.com";
    }

    @Test
    public void User_01_Register() {
        log.info("Register - Step 01: Navigate to 'Register' page");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        userRegisterPageObject.clickToRadioButtonByLabel("Male");

        log.info("Register - Step 02: Enter the Firstname textbox with value is '" + userData.getFirstName() + "'");
//        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToTextboxByID("FirstName", userData.getFirstName());

        log.info("Register - Step 03: Enter the Lastname textbox with value is '" + userData.getLastName() + "'");
//        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToTextboxByID("LastName", userData.getLastName());

        userRegisterPageObject.selectToDropDownByName("DateOfBirthDay", userData.getDate());
        userRegisterPageObject.selectToDropDownByName("DateOfBirthMonth", userData.getMonth());
        userRegisterPageObject.selectToDropDownByName("DateOfBirthYear", userData.getYear());

        log.info("Register - Step 04: Enter the Email textbox with value is '" + emailAddress + "'");
//        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToTextboxByID("Email", emailAddress);

        userRegisterPageObject.clickToCheckboxByLabel("Newsletter");

        log.info("Register - Step 05: Enter the Password textbox with value is '" + userData.getPassword() + "'");
//        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToTextboxByID("Password", userData.getPassword());

        log.info("Register - Step 06: Enter the Confirm Password textbox with value is '" + userData.getPassword() + "'");
//        userRegisterPageObject.inputToConfirmPasswordTextbox(password);
        userRegisterPageObject.inputToTextboxByID("ConfirmPassword", userData.getPassword());

        log.info("Register - Step 07: Click to 'Register button'");
        userRegisterPageObject.clickToButtonByText("Register");

        log.info("Register - Step 08: Verify register success message is displayed");
        verifyEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {

        log.info("Login - Step 01:  Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        log.info("Login - Step 02: enter the Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToTextboxByID("Email", emailAddress);

        log.info("Login - Step 03: enter the Password textbox with value is '" + userData.getPassword() + "'");
        userLoginPageObject.inputToTextboxByID("Password", userData.getPassword());

        log.info("Login - Step 04: Click Log In button");
        userLoginPageObject.clickToButtonByText("Log in");
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
        Assert.assertEquals(userCustomerInfoPageObject.getTextboxValueByID("FirstName"), userData.getFirstName());

        log.info("My Account - Step 04: Verify 'Last Name' value is correctly");
        Assert.assertEquals(userCustomerInfoPageObject.getTextboxValueByID("LastName"), userData.getLastName());

        log.info("My Account - Step 01:  Verify 'Email' value is correctly");
        Assert.assertEquals(userCustomerInfoPageObject.getTextboxValueByID("Email"), emailAddress);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
