package com.jquery.datatable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;

import java.time.Duration;

public class Level_10_DataTable_DataGrid extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePageObject;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserName(browserName, url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        homePageObject = PageGeneratorManager.getHomePageObject(driver);
    }

    @Test
    public void Table_01_Paging() {
        homePageObject.openPagingByPageNumber("10");
        Assert.assertTrue(homePageObject.isPageNumberActivated("10"));
        homePageObject.openPagingByPageNumber("4");
        Assert.assertTrue(homePageObject.isPageNumberActivated("4"));
        homePageObject.openPagingByPageNumber("15");
        Assert.assertTrue(homePageObject.isPageNumberActivated("15"));
    }

    @Test
    public void Table_02_Enter_To_Header() {
        homePageObject.refresh(driver);
        homePageObject.enterToHeaderTextboxByLabel("Country","Algeria");
        homePageObject.enterToHeaderTextboxByLabel("Females","283821");
        homePageObject.enterToHeaderTextboxByLabel("Males","295140");
        homePageObject.enterToHeaderTextboxByLabel("Total","578961");
    }

    @Test
    public void Table_03() {
        homePageObject.refresh(driver);
        homePageObject.getValueEachRowAtAllPage();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
