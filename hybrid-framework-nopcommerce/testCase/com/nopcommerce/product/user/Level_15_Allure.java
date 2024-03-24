package com.nopcommerce.product.user;

//import com.relevantcodes.extentreports.LogStatus;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class Level_15_Allure extends BaseTest {
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

    @Description("Register to system")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_01_Register() {
        userRegisterPageObject = userHomePageObject.clickRegisterLink();

        userRegisterPageObject.inputToFirstNameTextbox(firstName);

        userRegisterPageObject.inputToLastNameTextbox(lastName);

        userRegisterPageObject.inputToEmailTextbox(emailAddress);

        userRegisterPageObject.inputToPasswordTextbox(password);

        userRegisterPageObject.inputToConfirmPasswordTextbox(password);

        userRegisterPageObject.clickRegisterButton();

        Assert.assertEquals(userRegisterPageObject.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Description("Login to system")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void User_02_Login() {
        userLoginPageObject = userHomePageObject.clickLogInLink();

        userLoginPageObject.inputToEmailTextBox(emailAddress);

        userLoginPageObject.inputToPasswordTextBox(password);

        userHomePageObject = userLoginPageObject.clickLogInButton();

        Assert.assertFalse(userHomePageObject.isMyAccountDisplayed());

        userCustomerInfoPageObject = userHomePageObject.clickToMyAccountLink();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
