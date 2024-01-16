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
import pageObjects.user.*;

import java.time.Duration;

public class Level_07_Switch_Page extends BaseTest {
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
    public void User_01_Register() {
        System.out.println("Pre-condition Step 1: Click Register link");
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        System.out.println("Pre-condition Step 2: Input data into textbox");
        userRegisterPageObject.inputToFirstNameTextbox(firstName);
        userRegisterPageObject.inputToLastNameTextbox(lastName);
        userRegisterPageObject.inputToEmailTextbox(emailAddress);
        userRegisterPageObject.inputToPasswordTextbox(password);
        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        System.out.println("Pre-condition Step 3: Click register button");
        userRegisterPageObject.clickRegisterButton();

        System.out.println("Pre-condition Step 4: Verify register success message displayed");
        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void User_02_Login() {
        System.out.println("Login Step 1: Click log in link");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        System.out.println("Login Step 2: Input mail");
        userLoginPageObject.inputToEmailTextBox(emailAddress);

        System.out.println("Login Step 3: Input password");
        userLoginPageObject.inputToPasswordTextBox(password);

        System.out.println("Login Step 4: Click log in button");
        userHomePageObject = userLoginPageObject.clickLogInButton();

        System.out.println("Login Step 5: Verify login successful");
        Assert.assertTrue(userHomePageObject.isMyAccountDisplayed());
    }

    @Test
    public void User_03_MyAccount() {
        userCustomerInfoPageObject = userHomePageObject.clickToMyAccountLink();
    }

    @Test
    public void User_04_Switch_Page() {
        addressPage = userCustomerInfoPageObject.openAddressPage(driver);

        myProductsReviewPage = addressPage.openMyProductsReviewPage(driver);

        rewardPointpage = myProductsReviewPage.openRewardPointPage(driver);

        addressPage = rewardPointpage.openAddressPage(driver);

        rewardPointpage = addressPage.openRewardPointPage(driver);

        myProductsReviewPage = rewardPointpage.openMyProductsReviewPage(driver);

        addressPage = myProductsReviewPage.openAddressPage(driver);
    }

    @Test
    public void User_05_Switch_Role() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
