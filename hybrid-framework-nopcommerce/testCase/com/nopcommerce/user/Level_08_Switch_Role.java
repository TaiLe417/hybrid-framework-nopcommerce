package com.nopcommerce.user;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.*;

import java.time.Duration;

public class Level_08_Switch_Role extends BaseTest {
    private WebDriver driver;
    private String userEmailAddress, firstName, lastName, userPassword, adminEmailAddress, adminPassword;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserLoginPageObject userLoginPageObject;
    private AdminLoginPageObject adminLoginPageObject;
    private AdminDashboardPageObject adminDashboardPageObject;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
        Faker faker = new Faker();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://demo.nopcommerce.com/");

        userEmailAddress = faker.internet().emailAddress();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userPassword = "123456";
        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);

//        System.out.println("Pre-condition Step 5: Log out");
//        Assert.assertEquals(registerPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void TC_01_Register() {
        System.out.println("Pre-condition Step 1: Click Register link");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        System.out.println("Pre-condition Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox(userEmailAddress);
        userRegisterPageObject.inputToPasswordTextbox(userPassword);
        userRegisterPageObject.inputToConfirmPasswordTextbox(userPassword);

        System.out.println("Pre-condition Step 3: Click register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Pre-condition Step 4: Verify register success message displayed");
        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void TC_02_User() {
        userLoginPageObject = userHomePageObject.clickLogInLink();

        //Login as User role
        userHomePageObject = userLoginPageObject.loginAsUser(userEmailAddress, userPassword);
        Assert.assertTrue(userHomePageObject.isMyAccountDisplayed());
    }

    @Test
    public void TC_03_Admin() {
        userHomePageObject.openPageUrl(driver, GlobalConstants.ADMIN_URL);
        adminLoginPageObject = PageGeneratorManager.getAdminLoginPageObject(driver);

        adminDashboardPageObject = adminLoginPageObject.loginAsAdmin(adminEmailAddress, adminPassword);
        Assert.assertTrue(adminDashboardPageObject.isDashboardDisplayed());


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
