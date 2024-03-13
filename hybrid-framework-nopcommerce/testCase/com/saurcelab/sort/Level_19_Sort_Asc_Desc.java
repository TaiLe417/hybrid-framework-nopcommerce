package com.saurcelab.sort;

import commons.BaseTest;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.user.*;
import pageObjects.saurceLab.LoginPO;
import pageObjects.saurceLab.PageGeneratorManager;
import pageObjects.saurceLab.ProductPO;

public class Level_19_Sort_Asc_Desc extends BaseTest {
    private WebDriver driver;
    LoginPO loginPO;
    ProductPO productPO;

    @Parameters({"browser", "appUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserName(browserName, url);
        Faker faker = new Faker();
        loginPO = PageGeneratorManager.getLoginPage(driver);
        loginPO.enterToUserName("standard_user");
        loginPO.enterToPassword("secret_sauce");
        productPO = loginPO.clickLoginButton();
    }

    @Test
    public void Sort_01_Name() {
        productPO.selectItemInProductSortDropdown("Name (A to Z)");
        productPO.sleepInSecond(3);
        Assert.assertTrue(productPO.isProductNameSortByAscending());

        productPO.selectItemInProductSortDropdown("Name (Z to A)");
        productPO.sleepInSecond(3);
        Assert.assertTrue(productPO.isProductNameSortByDescending());
    }

    @Test
    public void Sort_02_Price() {
        productPO.selectItemInProductSortDropdown("Price (low to high)");
        productPO.sleepInSecond(3);
        Assert.assertTrue(productPO.isProductPriceSortByAscending());

        productPO.selectItemInProductSortDropdown("Price (high to low)");
        productPO.sleepInSecond(3);
        Assert.assertTrue(productPO.isProductPriceSortByDescending());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
