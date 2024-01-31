package com.facebook.register;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {
    private WebDriver driver;
    private LoginPageObject loginPageObject;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserName(browserName, url);
        loginPageObject = PageGeneratorManager.getLoginPageObject(driver);
    }

    @Test
    public void TC_01_Verify_Element_Is_Displayed() {
        loginPageObject.clickToCreateNewAccountButton();
        verifyTrue(loginPageObject.isEmailAddressTextboxDisplayed());

    }

    @Test
    public void TC_02_Verify_Element_Undisplayed_Is_In_DOM() {
        loginPageObject.enterEmailAddress("automation@gmail.com");
        verifyTrue(loginPageObject.isConfirmEmailIsDisplayed());
        loginPageObject.enterEmailAddress("");
        verifyTrue(loginPageObject.isConfirmEmailIsDisplayed());
    }

    @Test
    public void TC_03_Verify_Element_Undisplayed_Is_Not_In_DOM() {
        loginPageObject.clickCloseIcon();
        verifyTrue(loginPageObject.isConfirmEmailIsUndisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
