package com.jquery.datatable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

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

    //    @Test
    public void Table_01_Paging() {
        homePageObject.openPagingByPageNumber("10");
        Assert.assertTrue(homePageObject.isPageNumberActivated("10"));
        homePageObject.openPagingByPageNumber("4");
        Assert.assertTrue(homePageObject.isPageNumberActivated("4"));
        homePageObject.openPagingByPageNumber("15");
        Assert.assertTrue(homePageObject.isPageNumberActivated("15"));
    }

    //    @Test
    public void Table_02_Enter_To_Header() {
        homePageObject.refresh(driver);
        homePageObject.enterToHeaderTextboxByLabel("Country", "Algeria");
        homePageObject.enterToHeaderTextboxByLabel("Females", "283821");
        homePageObject.enterToHeaderTextboxByLabel("Males", "295140");
        homePageObject.enterToHeaderTextboxByLabel("Total", "578961");
    }

    //    @Test
    public void Table_03() {
        homePageObject.refresh(driver);
        homePageObject.getValueEachRowAtAllPage();
    }

    @Test
    public void Table_04_Enter_To_Textbox_At_Any_Row() {
        homePageObject.clickLoadButton();
//        homePageObject.enterToTextboxByColumnNameAtRowNumber("Company", "1", "LG");
//        homePageObject.enterToTextboxByColumnNameAtRowNumber("Contact Person", "2", "123654");
//        homePageObject.enterToTextboxByColumnNameAtRowNumber("Order Placed", "4", "2");
//
//        homePageObject.selectDropDownByColumnNameAtRowNumber("Country", "3", "Japan");
//        homePageObject.selectDropDownByColumnNameAtRowNumber("Country", "1", "Hong Kong");
//        homePageObject.selectDropDownByColumnNameAtRowNumber("Country", "7", "Malaysia");
//        homePageObject.selectDropDownByColumnNameAtRowNumber("Country", "5", "United States");
//
//        homePageObject.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "2");
//        homePageObject.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "3");
//        homePageObject.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "6");
//        homePageObject.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "7");
//        homePageObject.checkToCheckBoxByColumnNameAtRowNumber("NPO?", "8");
//        homePageObject.unCheckToCheckBoxByColumnNameAtRowNumber("NPO?", "1");
//        homePageObject.unCheckToCheckBoxByColumnNameAtRowNumber("NPO?", "4");
//        homePageObject.unCheckToCheckBoxByColumnNameAtRowNumber("NPO?", "5");
        homePageObject.clickToIconByRowNumber("2", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("1", "Insert Row Above");
        homePageObject.clickToIconByRowNumber("3", "Move Up");
        homePageObject.clickToIconByRowNumber("5", "Move Down");
        homePageObject.clickToIconByRowNumber("8", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("7", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("6", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("5", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("4", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("3", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("2", "Remove Current Row");
        homePageObject.clickToIconByRowNumber("1", "Remove Current Row");
    }

    //    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
