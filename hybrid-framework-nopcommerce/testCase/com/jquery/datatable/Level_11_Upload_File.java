package com.jquery.datatable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

import java.time.Duration;

public class Level_11_Upload_File extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePageObject;
    String beachFileName = "Beach.jpg";
    String carFileName = "Car.jpg";
    String dogFileName = "Dog.jpg";
    String[] multipleFileNames = {beachFileName, carFileName, dogFileName};

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserName(browserName, url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        homePageObject = PageGeneratorManager.getHomePageObject(driver);
    }


    @Test
    public void Upload_01_One_File_Per_Time() {
        //Log single file
        homePageObject.uploadFile(driver, beachFileName);
        //Verify file loaded success
        Assert.assertTrue(homePageObject.isFileLoadedByName(beachFileName));
        //click to start button
        homePageObject.clickToStartButton();
        //Verify file link uploaded success
        Assert.assertTrue(homePageObject.isFileLinkUploadedByName(beachFileName));
        //Verify file img uploaded success
        Assert.assertTrue(homePageObject.isFileImgUploadedByName(beachFileName));
    }

    @Test
    public void Upload_02_Multiple_Files_Per_Time() {
        homePageObject.refresh(driver);
        //Log multi file
        homePageObject.uploadFile(driver, multipleFileNames);
        //Verify file loaded success
        Assert.assertTrue(homePageObject.isFileLoadedByName(beachFileName));
        Assert.assertTrue(homePageObject.isFileLoadedByName(carFileName));
        Assert.assertTrue(homePageObject.isFileLoadedByName(dogFileName));
        //click to start button
        homePageObject.clickToStartButton();
        //Verify file link uploaded success
        Assert.assertTrue(homePageObject.isFileLinkUploadedByName(beachFileName));
        Assert.assertTrue(homePageObject.isFileLinkUploadedByName(carFileName));
        Assert.assertTrue(homePageObject.isFileLinkUploadedByName(dogFileName));
        //Verify file img uploaded success
        Assert.assertTrue(homePageObject.isFileImgUploadedByName(beachFileName));
        Assert.assertTrue(homePageObject.isFileImgUploadedByName(carFileName));
        Assert.assertTrue(homePageObject.isFileImgUploadedByName(dogFileName));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
