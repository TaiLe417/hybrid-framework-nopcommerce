package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Register_End_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;

public class Level_16_Share_Data_A extends BaseTest {
    private WebDriver driver;
    private String emailAddress, password;
    private UserHomePageObject userHomePageObject;
    private UserLoginPageObject userLoginPageObject;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserName(browserName);
        userHomePageObject = PageGeneratorManager.getUserHomePageObject(driver);
        emailAddress = Common_01_Register_End_User.emailAddress;
        password = Common_01_Register_End_User.password;


        log.info("Login - Step 01:  Navigate to Login page");
        userLoginPageObject = userHomePageObject.clickLogInLink();

        log.info("Login - Step 02: enter the Email textbox with value is '" + emailAddress + "'");
        userLoginPageObject.inputToEmailTextBox(emailAddress);

        log.info("Login - Step 03: enter the Password textbox with value is '" + password + "'");
        userLoginPageObject.inputToPasswordTextBox(password);

        log.info("Login - Step 04: Click Log In button");
        userHomePageObject = userLoginPageObject.clickLogInButton();
    }


    @Test
    public void Search_01_Empty_Data() {
    }

    @Test
    public void Search_02_Relative_Product_Name() {
    }

    @Test
    public void Search_03_Absolute_Product_Name() {
    }

    @Test
    public void Search_04_Parent_Category() {
    }

    @Test
    public void Search_05_Incorrect_Manufacturer() {
    }

    @Test
    public void Search_06_Correct_Manufacturer() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
